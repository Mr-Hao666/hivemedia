$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'order/orderreturns/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '退货编号', name: 'returnsNo', index: 'returns_no', width: 80 }, 			
			{ label: '订单id', name: 'orderId', index: 'order_id', width: 80 }, 			
			{ label: '物流单号', name: 'expressNo', index: 'express_no', width: 80 }, 			
			{ label: '收货人姓名', name: 'consigneeRealname', index: 'consignee_realname', width: 80 }, 			
			{ label: '联系电话', name: 'consigneeTelphone', index: 'consignee_telphone', width: 80 }, 			
			{ label: '收货地址', name: 'consigneeAddress', index: 'consignee_address', width: 80 }, 			
			{ label: '邮政编码', name: 'consigneeZip', index: 'consignee_zip', width: 80 }, 			
			{ label: '物流方式 1:ems...', name: 'logisticsType', index: 'logistics_type', width: 80 }, 			
			{ label: '退货运费', name: 'logisticsFee', index: 'logistics_fee', width: 80 }, 			
			{ label: '物流状态', name: 'orderlogisticsStatus', index: 'orderlogistics_status', width: 80 }, 			
			{ label: '物流描述', name: 'orderlogisticsDesc', index: 'orderlogistics_desc', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '更新时间', name: 'updateTime', index: 'update_time', width: 80 }			
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
		orderReturns: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.orderReturns = {};
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
			var url = vm.orderReturns.id == null ? "order/orderreturns/save" : "order/orderreturns/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.orderReturns),
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
				    url: baseURL + "order/orderreturns/delete",
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
			$.get(baseURL + "order/orderreturns/info/"+id, function(r){
                vm.orderReturns = r.orderReturns;
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