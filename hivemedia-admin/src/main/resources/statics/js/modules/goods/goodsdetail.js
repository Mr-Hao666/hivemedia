$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'goods/goodsdetail/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true, hidden:true },
			{ label: '用户id', name: 'userId', index: 'user_id', width: 80 },
            { label: '尺码详情', name: 'sizeDesc', index: 'size_id', width: 80 },
            { label: '商品名称', name: 'goodsName', index: 'goods_id', width: 200 },
            { label: '商品货号', name: 'artNo', width: 80 },
			{ label: '押金', name: 'cashPledge', index: 'cash_pledge', width: 80 }, 			
			{ label: '商品状态', name: 'curState', index: 'cur_state', width: 80,
				formatter:function (value) {
					if(value === 1){
						return "待售"
					}else if(value === 2){
						return "已定"
					}else if(value == 3){
						return "已售"
					}else{
						return "-"
					}
                }
			}
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
		goodsDetail: {},
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
			vm.goodsDetail = {};
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
			var url = vm.goodsDetail.id == null ? "goods/goodsdetail/save" : "goods/goodsdetail/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.goodsDetail),
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
				    url: baseURL + "goods/goodsdetail/delete",
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
			$.get(baseURL + "goods/goodsdetail/info/"+id, function(r){
                vm.goodsDetail = r.goodsDetail;
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
        reloadSearch: function() {
            vm.q = {
                goodsName: '',
                artNo: '',
            }
            vm.reload();
        },
	}
});