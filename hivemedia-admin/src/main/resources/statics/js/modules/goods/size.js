// $(function () {
//     $("#jqGrid").jqGrid({
//         url: baseURL + 'goods/size/list',
//         datatype: "json",
//         colModel: [
// 			{ label: 'id', name: 'id', index: 'id', width: 50, key: true ,hidden:true},
// 			{ label: '商品Tab', name: 'goodsTab', index: 'goods_tab', width: 80 },
// 			{ label: '尺码描述', name: 'desc', index: '`desc`', width: 80 },
// 			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 },
// 			{ label: '更新时间', name: 'updateTime', index: '`update_time`', width: 80 },
//         ],
// 		viewrecords: true,
//         height: 385,
//         rowNum: 10,
// 		rowList : [10,30,50],
//         rownumbers: true,
//         rownumWidth: 25,
//         autowidth:true,
//         multiselect: true,
//         pager: "#jqGridPager",
//         jsonReader : {
//             root: "page.list",
//             page: "page.currPage",
//             total: "page.totalPage",
//             records: "page.totalCount"
//         },
//         prmNames : {
//             page:"page",
//             rows:"limit",
//             order: "order"
//         },
//         gridComplete:function(){
//         	//隐藏grid底部滚动条
//         	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
//         }
//     });
// });



var ztree;
var Size = {
    id: "sizeTable",
    table: null,
    layerIndex: -1
};
Size.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: '商品Tab', field: 'goodsTab', align: 'center', valign: 'middle', sortable: true, width: '180px'},
        {title: '尺码描述', field: 'desc', align: 'center', valign: 'middle', sortable: false, width: '100px'},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle', sortable: true, width: '100px'},
        {title: '更新时间', field: 'updateTime', align: 'center', valign: 'middle', sortable: true, width: '100px'},
    ]
    return columns;
};
$(function () {
    var colunms = Size.initColumn();
    var table = new TreeTable(Size.id, baseURL + "goods/size/queryAll", colunms);
    table.setRootCodeValue(0);
    table.setExpandColumn(1);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("pid");
    table.setExpandAll(false);
    table.init();
    Size.table = table;
});


var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		size: {},
        goodsTabs:[],
        ruleValidate: {
            goodsTab: [
                {required: true, message: '商品Tab不能为空', trigger: 'change'},

            ],
            desc: [
                {required: true, message: '尺码详情不能为空', trigger: 'blur'},
            ],
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.size = {
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
            vm.getDictByType('goodsTab');
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.size.id == null ? "goods/size/save" : "goods/size/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.size),
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
			var ids = getCateId ();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + `goods/size/delete/${ids}`,
                    contentType: "application/json",
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "goods/size/info/"+id, function(r){
                vm.size = r.size;
            });
		},
		reload: function (event) {
			vm.showList = true;
            // var page = $("#jqGrid").jqGrid('getGridParam','page');
            // $("#jqGrid").jqGrid('setGridParam',{
            //     page:page
            // }).trigger("reloadGrid");
            Size.table.refresh();
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
function getCateId () {
    var selected = $('#sizeTable').bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        alert("请选择一条记录");
        return null;
    } else {
        return selected[0].id;
    }
}