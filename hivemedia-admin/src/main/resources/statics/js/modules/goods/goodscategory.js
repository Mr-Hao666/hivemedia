var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pid",
            rootPId: -1
        },
        key: {
            url:"nourl"
        }
    }
};
var ztree;
var GoodsCate = {
    id: "jqGrid",
    table: null,
    layerIndex: -1
};
GoodsCate.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: '分类名称', field: 'cateName', align: 'center', valign: 'middle', sortable: true, width: '100px'},
        {title: '商品名称', field: 'goodsName', align: 'center', valign: 'middle', sortable: false, width: '200px'},
    ]
    return columns;
};
$(function () {
    var colunms = GoodsCate.initColumn();
    var table = new TreeTable(GoodsCate.id, baseURL + "goods/goodscategory/queryAll", colunms);
    table.setRootCodeValue(0);
    table.setExpandColumn(1);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("pid");
    table.setExpandAll(false);
    table.init();
    GoodsCate.table = table;
});
function getGoodsCateId () {
    var selected = $('#jqGrid').bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        alert("请选择一条记录");
        return null;
    } else {
        return selected[0].id;
    }
}
// $(function () {
//     $("#jqGrid").jqGrid({
//         url: baseURL + 'goods/goodscategory/list',
//         datatype: "json",
//         colModel: [
// 			{ label: 'id', name: 'id', index: 'id', width: 50, key: true ,hidden:true},
// 			{ label: '分类名称', name: 'cateName', index: 'category_id', width: 80 },
// 			{ label: '商品名称', name: 'goodsName', index: 'goods_id', width: 80 }
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

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		goodsCategory: {
            cateName: ''
		},
		goodsList:[],
        ruleValidate:{
            goodsId: [
                {required: true, message: '商品名称不能为空', trigger: 'change', type: 'integer'},

            ],
            cateName: [
                {required: true, message: '分类名称不能为空', trigger: 'blur'},
            ],
        },
        isLoading: true
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.goodsCategory = {
                cateName: ''
			};
            vm.goodsList = []
            vm.getCate();
            vm.getGoodsList("");
		},
		update: function (event) {
			var id = getGoodsCateId();
			if(id == null){
				return ;
			}
            if(id < 0) {
                alert("修改分类名称请去商品分类管理!")
                return;
            }
			vm.showList = false;
            vm.title = "修改";
            vm.getCate();
            vm.getInfo(id)

		},
		saveOrUpdate: function (event) {
			var url = vm.goodsCategory.id == null ? "goods/goodscategory/save" : "goods/goodscategory/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.goodsCategory),
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
			var ids = getGoodsCateId();
			if(ids == null){
				return ;
			}
            if(ids < 0) {
                alert("删除分类名称请去商品分类管理!")
                return;
            }
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + `goods/goodscategory/delete/${ids}`,
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

			$.get(baseURL + "goods/goodscategory/info/"+id, function(r){
                vm.goodsCategory = r.goodsCategory;
            });
		},
		reload: function (event) {
			vm.showList = true;
			// var page = $("#jqGrid").jqGrid('getGridParam','page');
			// $("#jqGrid").jqGrid('setGridParam',{
            //     page:page
            // }).trigger("reloadGrid");
            GoodsCate.table.refresh();
		},
        cateTree: function(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择分类",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#cateLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    vm.goodsCategory.categoryId = node[0].id;
                    vm.goodsCategory.cateName = node[0].name;
                    layer.close(index);
                }
            });
        },
        getCate: function(){
            //加载分类树
            $.get(baseURL + "goods/category/list", function(r){
                ztree = $.fn.zTree.init($("#cateTree"), setting, r.list);
                var node = ztree.getNodeByParam("id", 0);
                ztree.selectNode(node);
            })
        },
        getGoodsList(name){
            $.get(baseURL + `goods/goods/select?name=${name}`, function(r){
                vm.goodsList = r.goodsList;
                vm.isLoading = false
            });
		},
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },

	}
});
