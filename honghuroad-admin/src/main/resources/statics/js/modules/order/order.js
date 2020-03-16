$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'order/order/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '单位ID', name: 'parentId', index: 'parent_id', width: 80 }, 			
			{ label: '部门ID', name: 'deptId', index: 'dept_id', width: 80 }, 			
			{ label: '小组ID', name: 'groupId', index: 'group_id', width: 80 }, 			
			{ label: '博主ID', name: 'blogId', index: 'blog_id', width: 80 }, 			
			{ label: '平台ID', name: 'platformId', index: 'platform_id', width: 80 }, 			
			{ label: '订单编号', name: 'orderNo', index: 'order_no', width: 80 }, 			
			{ label: '发布日期', name: 'publishDate', index: 'publish_date', width: 80 }, 			
			{ label: '账号ID', name: 'accountId', index: 'account_id', width: 80 }, 			
			{ label: '账号类型', name: 'accountType', index: 'account_type', width: 80 }, 			
			{ label: '发布方式', name: 'publishType', index: 'publish_type', width: 80 }, 			
			{ label: '类型补充', name: 'supply', index: 'supply', width: 80 }, 			
			{ label: '项目类型', name: 'itemType', index: 'item_type', width: 80 }, 			
			{ label: '项目名称', name: 'itemName', index: 'item_name', width: 80 }, 			
			{ label: '链接', name: 'url', index: 'url', width: 80 }, 			
			{ label: '报价', name: 'price', index: 'price', width: 80 }, 			
			{ label: '扣税（按9.72%）', name: 'tax', index: 'tax', width: 80 }, 			
			{ label: '微任务成本', name: 'microtaskCost', index: 'microtask_cost', width: 80 }, 			
			{ label: '外签/外部成本', name: 'externalCost', index: 'external_cost', width: 80 }, 			
			{ label: '粉丝头条', name: 'fansCost', index: 'fans_cost', width: 80 }, 			
			{ label: '计算提成（毛利润）金额', name: 'grossProfit', index: 'gross_profit', width: 80 }, 			
			{ label: '其他（如承担博主差旅费）', name: 'otherCost', index: 'other_cost', width: 80 }, 			
			{ label: '提成比率', name: 'proportion', index: 'proportion', width: 80 }, 			
			{ label: '比例提成金额', name: 'royalty', index: 'royalty', width: 80 }, 			
			{ label: '固定提成金额', name: 'fixedRoyalty', index: 'fixed_royalty', width: 80 }, 			
			{ label: '总提成金额', name: 'totalRoyalty', index: 'total_royalty', width: 80 }			
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
		order: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.order = {};
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
			var url = vm.order.id == null ? "order/order/save" : "order/order/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.order),
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
				    url: baseURL + "order/order/delete",
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
			$.get(baseURL + "order/order/info/"+id, function(r){
                vm.order = r.order;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});