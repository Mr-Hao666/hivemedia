$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'order/buyorder/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '卖家用户id', name: 'saleUserId', index: 'sale_user_id', width: 80 }, 			
			{ label: '出售订单ID', name: 'saleOrderId', index: 'sale_order_id', width: 80 }, 			
			{ label: '买家用户id', name: 'buyUserId', index: 'buy_user_id', width: 80 }, 			
			{ label: '订单单号', name: 'orderNo', index: 'order_no', width: 80 },
			{ label: '商品Id', name: 'goodsId', index: 'goods_id', width: 80 }, 			
			{ label: '商品尺码Id', name: 'sizeId', index: 'size_id', width: 80 }, 			
			{ label: '1:立即购买 2:出价求购', name: 'buyType', index: 'buy_type', width: 80 }, 			
			{ label: '订单状态：-1：待支付保证金  0：求购中；1:待付款 2:已付款 3:已发往Biu平台 4:买家待收货(平台验货通过) 5:平台验货未通过 6:已签收 7:退货申请中 8:退货中 9:已完成退货 10:取消交易', name: 'orderStatus', index: 'order_status', width: 80 }, 			
			{ label: '商品总价(定价)', name: 'amountTotal', index: 'amount_total', width: 80 },
			{ label: '实际付款金额', name: 'orderAmountTotal', index: 'order_amount_total', width: 80 }, 			
			{ label: '运费金额', name: 'logisticsFee', index: 'logistics_fee', width: 80 }, 			
			{ label: '订单收货地址', name: 'address', index: 'address', width: 80 },
			{ label: '物流id', name: 'logisticsId', index: 'logistics_id', width: 80 }, 			
			{ label: '支付渠道 1:支付宝 2:微信...', name: 'payChannel', index: 'pay_channel', width: 80 }, 			
			{ label: '下单时间', name: 'createTime', index: 'create_time', width: 80 },
			{ label: '付款时间', name: 'payTime', index: 'pay_time', width: 80 }, 			
			{ label: '订单备注', name: 'remarks', index: 'remarks', width: 80 }, 			
			{ label: '更新时间', name: 'updateTime', index: 'update_time', width: 80 }, 			
			{ label: '订单状态 1:有效 2:无效', name: 'curState', index: 'cur_state', width: 80 }, 			
			{ label: '保证金', name: 'deposit', index: 'deposit', width: 80 },
			{ label: '快递费', name: 'expressFee', index: 'express_fee', width: 80 }, 			
			{ label: '求购时间', name: 'wantBuyDays', index: 'want_buy_days', width: 80 }, 			
			{ label: '是否被卖家变现，1：是；0：否', name: 'isCash', index: 'is_cash', width: 80 }
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
		buyOrder: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.buyOrder = {};
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
			var url = vm.buyOrder.id == null ? "order/buyorder/save" : "order/buyorder/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.buyOrder),
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
				    url: baseURL + "order/buyorder/delete",
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
		shipped: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}

			confirm('平台确定要发货？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "order/buyorder/shipped",
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
		unpass: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}

			confirm('确定平台验货未通过？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "order/buyorder/unpass",
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
		inInspection: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}

			confirm('确定已签收？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "order/buyorder/inInspection",
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
			$.get(baseURL + "order/buyorder/info/"+id, function(r){
                vm.buyOrder = r.buyOrder;
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