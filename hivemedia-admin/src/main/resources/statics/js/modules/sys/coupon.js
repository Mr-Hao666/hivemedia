$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/coupon/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '优惠券名', name: 'name', index: 'name', width: 80 }, 			
			{ label: '使用条件的值', name: 'useAmount', index: 'use_amount', width: 80 }, 			
			{ label: '优惠券金额', name: 'amount', index: 'amount', width: 80 }, 			
			{ label: '发放总数', name: 'total', index: 'total', width: 80 }, 			
			{ label: '已领取数量', name: 'issusedNum', index: 'issused_num', width: 80 }, 			
			{ label: '状态 1:有效 2:无效 3，删除；', name: 'curState', index: 'cur_state', width: 80 }, 			
			{ label: '一段时间内有效的天数', name: 'number', index: 'number', width: 80 }, 			
			{ label: '生效类型  1：有效期内生效，2：领取一段时间内有效', name: 'type', index: 'type', width: 80 }, 			
			{ label: '开始时间', name: 'beginTime', index: 'begin_time', width: 80 }, 			
			{ label: '结束时间', name: 'endTime', index: 'end_time', width: 80 }, 			
			{ label: '简介描述', name: 'summary', index: 'summary', width: 80 }, 			
			{ label: '创建人id', name: 'createBy', index: 'create_by', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '更新人id', name: 'updateBy', index: 'update_by', width: 80 }, 			
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
		coupon: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.coupon = {};
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
			var url = vm.coupon.id == null ? "sys/coupon/save" : "sys/coupon/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.coupon),
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
				    url: baseURL + "sys/coupon/delete",
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
			$.get(baseURL + "sys/coupon/info/"+id, function(r){
                vm.coupon = r.coupon;
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