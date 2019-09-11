// $(function () {
//     $("#jqGrid").jqGrid({
//         url: baseURL + 'goods/category/list',
//         datatype: "json",
//         colModel: [
// 			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
// 			{ label: '父分类ID', name: 'pid', index: 'pid', width: 80 },
// 			{ label: '图标ID', name: 'picId', index: 'pic_id', width: 80 },
// 			{ label: '分类名称', name: 'name', index: 'name', width: 80 },
// 			{ label: '排序', name: 'sortNo', index: 'sort_no', width: 80 },
// 			{ label: '创建人id', name: 'createBy', index: 'create_by', width: 80 },
// 			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 },
// 			{ label: '更新人id', name: 'updaetBy', index: 'updaet_by', width: 80 },
// 			{ label: '更新时间', name: 'updateDate', index: 'update_date', width: 80 },
// 			{ label: '数据状态 0:正常 1:删除', name: 'delFlag', index: 'del_flag', width: 80 }
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
function transImg(url) {
    if (url) {
        return '<img width="50px" height="50px" src="' + url + '">';
    } else {
        return '-';
    }
};
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
var Category = {
    id: "cateTable",
    table: null,
    layerIndex: -1
};
Category.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: '分类id', field: 'id', visible: false, align: 'center', valign: 'middle', width: '80px'},
        {title: '分类名称', field: 'name', align: 'center', valign: 'middle', sortable: true, width: '180px'},
        {title: '分类图标', field: 'imgUrl', align: 'center', valign: 'middle', sortable: true, width: '100px',
            formatter:function (value) {
                return transImg(value.imgUrl);
            }
        },
        {title: '排序', field: 'sortNo', align: 'center', valign: 'middle', sortable: true, width: '100px'},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle', sortable: true, width: '100px'},
        ]
    return columns;
};
$(function () {
    var colunms = Category.initColumn();
    var table = new TreeTable(Category.id, baseURL + "goods/category/queryAll", colunms);
    table.setRootCodeValue(0);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("pid");
    table.setExpandAll(false);
    table.init();
    Category.table = table;
});


var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		category: {
			pid: 0,
            pName: '',
            imgUrl: '',
            sortNo: 1
		},
        ruleValidate:{
            name: [
                {required: true, message: '分类名称不能为空', trigger: 'blur'},

            ],
            pname: [
                {required: true, message: '上级分类不能为空', trigger: 'blur'},
            ]
        },
        baseURL:baseURL
	},
	methods: {
        getCate: function(){
            //加载分类树
            $.get(baseURL + "goods/category/list", function(r){
                ztree = $.fn.zTree.init($("#cateTree"), setting, r.list);
                var node = ztree.getNodeByParam("id", vm.category.pid);
                ztree.selectNode(node);
            })
        },
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.category = {
                pid: 0,
                pname: '',
                imgUrl:'',
                sortNo: 1
			};
            vm.getCate();
		},
		update: function (event) {
            var cateId = getCateId();
            if(cateId == null){
                return ;
            }

            $.get(baseURL + "goods/category/info/"+cateId, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.category = r.category;
                vm.getCate();
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.category.id == null ? "goods/category/save" : "goods/category/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.category),
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
            var cateId = getCateId();
            if(cateId == null){
                return ;
            }
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + `goods/category/delete/${cateId}`,
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
			$.get(baseURL + "goods/category/info/"+id, function(r){
                vm.category = r.category;
            });
		},
		reload: function (event) {
            vm.showList = true;
            Category.table.refresh();
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
                    //选择上级部门
                    vm.category.pid = node[0].id;
                    vm.category.pname = node[0].name;
                    layer.close(index);
                }
            });
        },
        handleSuccessListPicUrl(res, file){
            vm.category.imgUrl = res.url
            vm.category.picId = res.id
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
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
	}
});
function getCateId () {
    var selected = $('#cateTable').bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        alert("请选择一条记录");
        return null;
    } else {
        return selected[0].id;
    }
}