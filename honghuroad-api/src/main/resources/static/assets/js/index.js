// var send = false
var send2 = false
/*发送数据*/
var submittimes = 0;
function put() {
    var data = $("#data").serialize();
    console.log(data)
   if (submittimes>=2){
    // if (submittimes>=999){
        alert("请勿重复提交!")
    }
    else if (send2 ==false){
        checkInput()
    }else{
        $.ajax({
            url:"/record/save",
            data: data,
            type: "post",
            success:function (res) {
                console.log(res)
                if("ok"==res){
                    alert("提交成功!")
                    submittimes++;
                }else{
                    alert("提交失败!")
                }
            },
            error:function (err) {
                console.log(err)
                alert("提交失败!")
            }
        })
    }
}

function inputValue(obj){
  //console.log(obj)
    //获取数据类型
    var type = obj.parentElement.getAttribute("name");
    var content = prompt("请输入")
    if (content==null){
        content = ""
    }
    obj.innerHTML=content+'<input name ="'+type+'" type=hidden value="'+content+'">';
}
var family = 1;
function addMember(size) {
        if(size == 4){ //家庭
            var count = document.getElementById("family").getElementsByTagName("tr").length;
            if(count>=size){
                alert("最多填写4名.")
            }else {
                document.getElementById("family").insertAdjacentHTML("beforeend", '<tr name="family'+family+'">\n' +
                    '                                                    <td onclick="inputValue(this)">请点击<input name="family'+family+'" type="hidden" value=""></td>\n' +
                    '                                                    <td onclick="inputValue(this)">请点击<input name="family'+family+'" type="hidden" value=""></td>\n' +
                    '                                                    <td onclick="inputValue(this)">请点击<input name="family'+family+'" type="hidden" value=""></td>\n' +
                    '                                                </tr>');
                family++;
            }
        }
}
//全变量,当前页数.
var page = 1;   //1 代表第一页,2代表第二页,3代表第三页   +1  下一步   -1后退

//移动到顶部
function toNext() {
    document.body.scrollTop = document.documentElement.scrollTop = 0;
    // page++;
    checkInput()
}
/* 检查填写的内容*/
var right = false;
var sos = false;
var exps = false;
function checkContent(selector){
    $(selector).each(function(){
        var b = selector;
        var a= $(this).children()
        if (a.context.innerText.indexOf("请点击")!=-1){  //存在  请点击
            if (selector.indexOf("family")!=-1){   //且是family
                right = false;//没填写完整
            }else if (selector.indexOf("sos")!=-1){
                sos = false ;
            }else if (selector.indexOf("experience")!=-1){
                exps = false;
            }

        }else {   //完整
            if (selector.indexOf("family")!=-1){   //且是family
                right = true;//填写完整
            }else if (selector.indexOf("sos")!=-1){
                sos = true ;
            }else if (selector.indexOf("experience")!=-1){
                exps = true;
            }
        }
    })
}
//检查用户输入
function checkInput(){
        $("#title").children().each(function () {
            var type = $(this)
            console.log("id:"+type.context.id)
            var id = type.context.id;
        })

}



