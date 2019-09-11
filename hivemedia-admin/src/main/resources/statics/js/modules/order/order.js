$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'order/order/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '卖家用户id', name: 'saleUserId', index: 'sale_user_id', width: 80 }, 			
			{ label: '买家用户id', name: 'buyUserId', index: 'buy_user_id', width: 80 }, 			
			{ label: '订单单号(供查询)', name: 'orderNo', index: 'order_no', width: 80 }, 			
			{ label: '订单状态 1:待付款 2:已付款 3:已发货 4:已签收 5:退货申请中 6:退货中 7:已完成退货 8:取消交易', name: 'orderStatus', index: 'order_status', width: 80 }, 			
			{ label: '仅针对订单状态为8,订单取消原因：1、拍错了；2、商品不发货；3、协商一致退款；4、其他原因'', name: 'cancelReason', index: 'cancel_reason', width: 80 }, 			
			{ label: '商品项目数量(不是商品的数量)', name: 'goodsCount', index: 'goods_count', width: 80 }, 			
			{ label: '商品总价', name: 'amountTotal', index: 'amount_total', width: 80 }, 			
			{ label: '实际付款金额', name: 'orderAmountTotal', index: 'order_amount_total', width: 80 }, 			
			{ label: '运费金额', name: 'logisticsFee', index: 'logistics_fee', width: 80 }, 			
			{ label: '是否开发票 0:否 1:是 (保留字段)', name: 'makeOutInvoice', index: 'make_out_invoice', width: 80 }, 			
			{ label: '发票编号 (保留字段)', name: 'invoiceNo', index: 'invoice_no', width: 80 }, 			
			{ label: '订单收货地址id', name: 'addressId', index: 'address_id', width: 80 }, 			
			{ label: '物流id', name: 'logisticsId', index: 'logistics_id', width: 80 }, 			
			{ label: '支付渠道 1:支付宝 2:微信...', name: 'payChannel', index: 'pay_channel', width: 80 }, 			
			{ label: '第三方支付流水号', name: 'outTradeNo', index: 'out_trade_no', width: 80 }, 			
			{ label: '创建时间(下单时间)', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '付款时间', name: 'payTime', index: 'pay_time', width: 80 }, 			
			{ label: '订单备注', name: 'remarks', index: 'remarks', width: 80 }, 			
			{ label: '更新时间', name: 'updateTime', index: 'update_time', width: 80 }, 			
			{ label: '订单状态 1:有效 2:无效', name: 'curState', index: 'cur_state', width: 80 }, 			
			{ label: '', name: 'couponId', index: 'coupon_id', width: 80 }			
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