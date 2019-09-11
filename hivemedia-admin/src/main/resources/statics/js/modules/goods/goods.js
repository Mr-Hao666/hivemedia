
// linjingze add
/**
 * 翻译图片
 * @param url
 * @returns {*}
 */
function transImg(url) {
    if (url) {
        return '<img width="50px" height="50px" src="' + url + '">';
    } else {
        return '-';
    }
};
$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'goods/goods/list',
        datatype: "json",
        colModel: [
            { label: 'id', name: 'id', index: 'id', width: 50, key: true,hidden: true },
            // { label: '店铺id', name: 'shopId', index: 'shop_id', width: 80 },
            { label: '商品Tab', name: 'goodsTab', index: 'goods_tab', width: 80 },

            { label: '商品名', name: 'name', index: 'name', width: 80 },
            { label: '商品所有尺码', name: 'sizeListStr', index: 'name', width: 80 },
            { label: '商品描述', name: 'desc', index: '`desc`', width: 80 },
            { label: '商品货号', name: 'artNo', index: 'art_no', width: 80 },
            { label: '商品关键字', name: 'keyword', index: 'keyword', width: 80 },
            { label: '商品展示主图', name: 'picShowUrl', index: 'pic_show', width: 80,
                formatter:function (value) {
                    return transImg(value)
                }
            },
            { label: '商品详情图1', name: 'picDetail1Url', index: 'pic_detail1', width: 80,
                formatter:function (value) {
                    return transImg(value)
                }
            },
            { label: '商品详情图2', name: 'picDetail2Url', index: 'pic_detail2', width: 80,
                formatter:function (value) {
                    return transImg(value)
                }
            },
            { label: '商品详情图3', name: 'picDetail3Url', index: 'pic_detail3', width: 80,
                formatter:function (value) {
                    return transImg(value)
                }
            },
            { label: '商品详情图4', name: 'picDetail4Url', index: 'pic_detail4', width: 80,
                formatter:function (value) {
                    return transImg(value)
                }
            },
            { label: '商品详情图5', name: 'picDetail5Url', index: 'pic_detail5', width: 80,
                formatter:function (value) {
                    return transImg(value)
                }
            },
            { label: '商品详情底图', name: 'baseMapUrl', index: 'base_map', width: 80,
                formatter:function (value) {
                    return transImg(value)
                }
            },
            { label: '发售日期', name: 'releaseDate', index: 'release_date', width: 80 ,
            },
            { label: '所属品牌名称', name: 'brand', index: 'brand', width: 80,


            },
            { label: '适用性别', name: 'genderFor', index: 'gender_for', width: 80,
                formatter:function (value) {
                    if(value === 0){
                        return '不限'
                    }else if(value === 1) {
                        return '男'
                    }else if(value === 2) {
                        return '女'
                    }else{
                        return '-'
                    }
                }

            },
            { label: '销量', name: 'salesVolume', index: 'sales_volume', width: 80 },
            { label: '最新成交价', name: 'latestDealPrice', index: 'latest_deal_price', width: 80 },
            { label: '单位计量', name: 'unit', index: 'unit', width: 80,
                formatter:function (value) {
                    if(value === '1'){
                        return '件'
                    }else{
                        return '-'
                    }
                }
            },
            { label: '上架', name: 'isOnSale', index: 'is_on_sale', width: 50,
                formatter:function (value) {
                    if(value === 1){
                        return '是'
                    }else {
                        return '否'
                    }
                }
            },
            { label: '单次购买上限数', name: 'limitTimes', index: 'limit_times', width: 80 },
            { label: '排序', name: 'sortNo', index: 'sort_no', width: 50 },
            { label: '标签', name: 'label', index: 'label', width: 80 ,
                formatter:function (value) {
                    if(value === '1'){
                        return '无标签'
                    }else if(value === '2'){
                        return '热卖'
                    }else if(value === '3'){
                        return '包邮'
                    }else if(value === '4'){
                        return '折扣'
                    }else{
                        return '-'
                    }
                }
            },
            { label: '包邮', name: 'isFreePost', index: 'is_free_post', width: 50,
                formatter:function (value) {
                    if(value === 1){
                        return '是'
                    }else {
                        return '否'
                    }
                }
            },
            { label: '创建时间', name: 'createdTime', index: 'created_time', width: 80 },
            { label: '更新时间', name: 'updatedTime', index: 'updated_time', width: 80 },
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
        goods: {
            isOnSale: 1,
            genderFor: 0,
            sortNo: 1,
            limitTimes: 9999,
            picShowUrl: '',
            picDetail1Url: '',
            picDetail2Url: '',
            picDetail3Url: '',
            picDetail4Url: '',
            picDetail5Url: '',
            baseMapUrl: '',
            goodsSizeList: [],
            isFreePost: 0

        },
        ruleValidate:{
            goodsTab: [
                {required: true, message: 'goodsTab不能为空', trigger: 'change'},

            ],
            name: [
                {required: true, message: '商品名称不能为空', trigger: 'blur'},
            ],
            artNo: [
                {required: true, message: '商品货号不能为空', trigger: 'blur'},
            ],
            picShowUrl: [
                {required: true, message: '商品主图不能为空', trigger: 'blur'},
            ],
        },
        labels:[],
        units: [],
        goodsTabs: [],
        q:{
            goodsName: '',
            artNo: ''
        },
        sizeList: [],
        goodsSizeBak: [],
        goodsSizeList: []
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.goods = {
                isOnSale: 1,
                genderFor: 0,
                picShowUrl:'',
                sortNo: 1,
                limitTimes: 9999,
                picDetail1Url: '',
                picDetail2Url: '',
                picDetail3Url: '',
                picDetail4Url: '',
                picDetail5Url: '',
                baseMapUrl: '',
                goodsSizeList: [],
                isFreePost: 0
            };
            vm.sizeList= []
            vm.getDictByType('goodsLabel');
            vm.getDictByType('goodsUnit');
            vm.getDictByType('goodsTab');
        },
        update: function (event) {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            vm.showList = false;
            vm.getInfo(id)
            vm.title = "修改";
            vm.getDictByType('goodsLabel');
            vm.getDictByType('goodsUnit');
            vm.getDictByType('goodsTab');




        },
        recommend(){
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            confirm('确定要推荐该商品吗？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + `goods/goods/recommend/${id}`,
                    contentType: "application/json",
                    success: function(r){
                        if(r.code === 0){
                            alert('商品已加入推荐',function(index){
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
            var url = vm.goods.id == null ? "goods/goods/save" : "goods/goods/update";

            vm.sizeList.forEach(item=>{
                if(vm.goods.goodsSizeList.includes(item.desc)){
                    vm.goodsSizeList.push(item)
                }
            })
            if(vm.goods.id != null){
                vm.goods.goodsSizeList = vm.goodsSizeList.filter(item=> !item.disabled)
            }else{
                vm.goods.goodsSizeList = vm.goodsSizeList;
            }
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.goods),
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
                    url: baseURL + "goods/goods/delete",
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
            $.get(baseURL + "goods/goods/info/"+id, function(r){
                vm.goods = r.goods;
                vm.goodsSizeBak = vm.goods.goodsSizeList
                vm.changeTab(vm.goods.goodsTab)
            });
        },

        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{
                    'goodsName': vm.q.goodsName,
                    'artNo': vm.q.artNo,

                },
                page:page
            }).trigger("reloadGrid");
            vm.sizeList =  [];
            vm.goodsSizeBak = [];
            vm.goodsSizeList = [];

        },
        handleSuccessMain(res, file){
            vm.goods.picShowUrl = res.url
            vm.goods.picShow = res.id
        },
        handleSuccess1(res, file){
            vm.goods.picDetail1Url = res.url
            vm.goods.picDetail1 = res.id
        },
        handleSuccess2(res, file){
            vm.goods.picDetail2Url = res.url
            vm.goods.picDetail2 = res.id
        },
        handleSuccess3(res, file){
            vm.goods.picDetail3Url = res.url
            vm.goods.picDetail3 = res.id
        },
        handleSuccess4(res, file){
            vm.goods.picDetail4Url = res.url
            vm.goods.picDetail4 = res.id
        },
        handleSuccess5(res, file){
            vm.goods.picDetail5Url = res.url
            vm.goods.picDetail5 = res.id
        },
        handleSuccess6(res, file){
            vm.goods.baseMapUrl = res.url
            vm.goods.baseMap = res.id
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
        getReleaseDate(releaseDate){
            vm.goods.releaseDate = releaseDate;
        },
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
        reloadSearch: function() {
            vm.q = {
                goodsName: '',
                artNo: '',
            }
            vm.reload();
        },
        changeTab(tabId){
            vm.goods.goodsSizeList = vm.goodsSizeBak
            $.get(baseURL + `goods/size/select?goodsTab=${tabId}`, function(r){

                r.list.forEach(item=>{
                    if(vm.goods.goodsSizeList.includes(item.desc)){
                        item.disabled = true;
                    }else{
                        item.disabled = false;
                    }
                })
                vm.sizeList = r.list;
            });
        },

    }
});
