$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'goods/goodssize/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true,hidden: true  },
			{ label: '商品名称', name: 'goodsName', index: 'goods_id', width: 180 },
			{ label: '商品货号', name: 'artNo', width: 80 },
            { label: '尺码详情', name: 'sizeDesc', index: 'size_id', width: 80 },
			{ label: '最高求购价格', name: 'expectMaxPrice', index: 'expect_max_price', width: 80 },
			{ label: '最低售价', name: 'expectMinPrice', index: 'expect_min_price', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		goodsSize: {
		},
        ruleValidate:{
            sizeId: [
                {required: true, message: '商品尺码不能为空', trigger: 'change', type: 'integer'},

            ],
            goodsId: [
                {required: true, message: '商品名称不能为空', trigger: 'change', type: 'integer'},

            ],
        },
        goodsList:[],
		sizeList:[],
        isSizeLoading: true,
        isGoodsLoading: true,
        q:{
            goodsName: '',
            artNo: ''
        }
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.goodsSize = {
			};
			vm.goodsList = [];
            vm.sizeList = [],
            vm.getGoodsList("");
            vm.getSizeList("");
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.goodsSize.id == null ? "goods/goodssize/save" : "goods/goodssize/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.goodsSize),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "goods/goodssize/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "goods/goodssize/info/"+id, function(r){
                vm.goodsSize = r.goodsSize;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{
                    'goodsName': vm.q.goodsName,
                    'artNo': vm.q.artNo,

                },
                page:page
            }).trigger("reloadGrid");
		},
        getGoodsList(name){
            $.get(baseURL + `goods/goods/select?name=${name}`, function(r){
                vm.goodsList = r.goodsList;
                vm.isGoodsLoading = false
            });
        },
		getSizeList(desc){
            $.get(baseURL + `goods/size/list?desc=${desc}`, function(r){
                vm.sizeList = r.page.list;
                vm.isSizeLoading = false
            });
		},
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
        reloadSearch: function() {
            vm.q = {
                goodsName: '',
                artNo: '',
            }
            vm.reload();
        },
	}
});