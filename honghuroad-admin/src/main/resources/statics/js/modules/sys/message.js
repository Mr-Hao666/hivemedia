$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/message/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '类型', name: 'type', index: 'type', width: 80 }, 			
			{ label: '标题', name: 'title', index: 'title', width: 80 }, 			
			{ label: '描述', name: 'desc', index: 'desc', width: 80 }, 			
			{ label: '消息内容', name: 'content', index: 'content', width: 80 }, 			
			{ label: '接收人id(多个以逗号隔开)', name: 'toUserId', index: 'to_user_id', width: 80 }, 			
			{ label: '预计发送时间', name: 'beginTime', index: 'begin_time', width: 80 }, 			
			{ label: '结束时间', name: 'endTime', index: 'end_time', width: 80 }, 			
			{ label: '推送状态 1:等待推送；2:推送中；3:推送失败；4:推送成功', name: 'status', index: 'status', width: 80 }, 			
			{ label: '1:系统通知 2:服务通知 3:活动通知(优先级更高,优先推送) ...', name: 'level', index: 'level', width: 80 }, 			
			{ label: '是否已读 0:未读 1:已读', name: 'isRead', index: 'is_read', width: 80 }, 			
			{ label: '创建人id', name: 'createBy', index: 'create_by', width: 80 }, 			
			{ label: '创建时间', name: 'createdTime', index: 'created_time', width: 80 }, 			
			{ label: '更新时间', name: 'updatedTime', index: 'updated_time', width: 80 }, 			
			{ label: '数据状态 0:有效 1:无效', name: 'delFlag', index: 'del_flag', width: 80 }			
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
		message: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.message = {};
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
			var url = vm.message.id == null ? "sys/message/save" : "sys/message/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.message),
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
				    url: baseURL + "sys/message/delete",
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
			$.get(baseURL + "sys/message/info/"+id, function(r){
                vm.message = r.message;
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