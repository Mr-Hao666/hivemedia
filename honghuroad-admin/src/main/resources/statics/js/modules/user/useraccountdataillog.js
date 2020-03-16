$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'user/useraccountdataillog/list',
        datatype: "json",
        colModel: [
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '用户昵称', name: 'nickname', index: 'nickname',sortable:false, width: 80 },
			{ label: '账户名', name: 'userName', index: 'userName',sortable:false, width: 80 },
			{ label: '支付宝账号', name: 'userAccount', index: 'userAccount',sortable:false, width: 80 },
			{ label: '申请金额', name: 'changeMoney', index: 'change_money', width: 80 },
			{ label: '申请前金额', name: 'beforeChangeMoney', index: 'before_change_money', width: 80 },
			{ label: '提现状态', name: 'cashStatus', index: 'cash_status', width: 80,
				formatter:function (value) {
					if(value === '0'){
						return '待提现';
					}else if(value === '1'){
						return '已提现';
					}
				}
			 },
			{ label: '申请时间', name: 'createTime', index: 'create_time', width: 80 },
			{ label: '操作时间', name: 'updateTime', index: 'update_time', width: 80 }
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
		userAccountDatailLog: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.userAccountDatailLog = {};
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
		cash(){
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			confirm('确定通过提现申请吗？', function(){
				$.ajax({
					type: "POST",
					url: baseURL + `user/useraccountdataillog/cash/${id}`,
					contentType: "application/json",
					success: function(r){
						if(r.code === 0){
							alert('提现申请已通过',function(index){
								vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		saveOrUpdate: function (event) {
			var url = vm.userAccountDatailLog.id == null ? "user/useraccountdataillog/save" : "user/useraccountdataillog/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.userAccountDatailLog),
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
				    url: baseURL + "user/useraccountdataillog/delete",
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
			$.get(baseURL + "user/useraccountdataillog/info/"+id, function(r){
                vm.userAccountDatailLog = r.userAccountDatailLog;
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