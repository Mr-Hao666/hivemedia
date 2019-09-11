function transImg(url) {
    if (url) {
        return '<img width="50px" height="50px" src="' + url + '">';
    } else {
        return '-';
    }
};
$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'goods/goodsrecbanner/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true,hidden: true  },
			{ label: '商品Tab', name: 'tabId', index: 'tab_id', width: 80 },
			{ label: '商品图片', name: 'picUrl', index: 'pic_id', width: 80,
                formatter:function (value) {
                    return transImg(value)
                }
			},
			{ label: '描述', name: 'desc', index: '`desc`', width: 80 },
			{ label: '跳转链接', name: 'url', index: 'url', width: 80 }, 			
			{ label: '排序', name: 'sortNo', index: 'sort_no', width: 80 }, 			
			{ label: '类型', name: 'type', index: 'type', width: 80 ,
                formatter:function (value) {
                    if(value === 1){
                        return "商品id"
                    }else{
                        return "url"
                    }
                }},
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 },
			{ label: '更新时间', name: 'updateTime', index: 'update_time', width: 80 },
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
		goodsRecBanner: {
			imgUrl: '',
            sortNo: 1,
            type: 1
		},
        ruleValidate:{
            tabId: [
                {required: true, message: 'goodsTab不能为空', trigger: 'change'},

            ],
        },
        goodsTabs:[],
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.goodsRecBanner = {
                imgUrl: '',
                sortNo: 1,
                type:1
			};
            vm.getDictByType('goodsTab');
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
            vm.getDictByType('goodsTab');
		},
		saveOrUpdate: function (event) {
			var url = vm.goodsRecBanner.id == null ? "goods/goodsrecbanner/save" : "goods/goodsrecbanner/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.goodsRecBanner),
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
				    url: baseURL + "goods/goodsrecbanner/delete",
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
			$.get(baseURL + "goods/goodsrecbanner/info/"+id, function(r){
                vm.goodsRecBanner = r.goodsRecBanner;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
        handleSuccessListPicUrl(res, file){
            vm.goodsRecBanner.picUrl = res.url
            vm.goodsRecBanner.picId = res.id
        },
        handleFormatError: function (file) {
            this.$Notice.warning({
                title: '文件格式不正确',
                desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg 或 png 格式的图片。'
            });
        },
        handleMaxSize: function (file) {
            this.$Notice.warning({
                title: '超出文件大小限制',
                desc: '文件 ' + file.name + ' 太大，不能超过 2M。'
            });
        },
        eyeImageListPicUrl(url){
            eyeImage(url)
        },
        getDictByType(type){
            $.ajax({
                type: "POST",
                url: baseURL + `/sys/dict/queryDictByType?type=${type}`,
                contentType: "application/json",
                success: function(r){
                    if(r.code === 0){
                        if(type === 'goodsLabel'){
                            vm.labels = r.list
                        }else if(type === 'goodsUnit'){
                            vm.units = r.list
                        }else if(type === 'goodsTab'){
                            vm.goodsTabs = r.list
                        }
                    }else{
                    }
                }
            });
        },
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
	}
});