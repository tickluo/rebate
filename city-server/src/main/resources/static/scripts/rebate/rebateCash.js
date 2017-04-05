var openAccountList;
var globalUser = {};

$(function () {

    //得到开户人
    GetOpenAccount();

    //用户的余额查询
    GetUserAmount();

    //预计到账时间
    GetArrivalsTime();
});

//开户人
function GetOpenAccount() {
    $.ajax({
        url: "/bank/getOpenAccountName",
        type: "Get",
        dataType: "json",
        success: function (data) {
            if (data.success && data.data.length > 0) {
                openAccountList = data.data;
                var list = data.data;
                var ddl = $("#OpenAcountName");
                //转成Json对象
                var result = eval(list);

                //循环遍历 下拉框绑定
                $(result).each(function (key) {
                    //第一种方法
                    var opt = $("<option></option>").text(result[key].openAccountName).val(result[key].id);
                    ddl.append(opt);
                });

                $("#BankName").text(list[0]["bankName"]);
                $("#BankBranchName").text(list[0]["bankBranchName"]);
                $("#BankNum").text(list[0]["bankNum"]);
            }
            else {
                $("#errMes").text(data.message);
            }
        }

    });
}
//切换账户人
function ChangeType() {
    var id = $("#OpenAcountName").val();
    $(openAccountList).each(function (key) {
        if (id == openAccountList[key].id) {
            $("#BankName").text(openAccountList[key]["bankName"]);
            $("#BankBranchName").text(openAccountList[key]["bankBranchName"]);
            $("#BankNum").text(openAccountList[key]["bankNum"]);
        }
    });
}
//提交申请
function btn_Submit() {
    if (openAccountList != null) {
        var $money = $("#Money");
        if ($money.val() == "") {
            $money.focus();
            $("#errMes").text("请输入提现金额");
            return false;
        }
        if (!checkMoney($money.val())) {
            $money.focus();
            $("#errMes").text("请输入有效的金额");
            return false;
        }

        $.ajax({
            url: "/rebate/doRebateCash",
            data: {
                bankId: $.trim($("#OpenAcountName").val()),
                money: $money.val()
            },
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data.state == "success") {
                    //金额显示减掉
                    var $can = $("#can");
                    var canMoney = $can.text();
                    var money = $("#Money").val();

                    var mm = sub(canMoney, money);
                    $can.text(mm.toFixed(2));
                    //记录插入一行
                    var myDate = new Date();
                    var time = myDate.getFullYear() + "-" + myDate.getMonth() + 1 + "-" + myDate.getDay() + " " + myDate.getHours() + ":" + myDate.getMinutes() + ":" + myDate.getSeconds();

                    //获取开户人
                    var text = "";
                    var obj = document.getElementById("OpenAcountName");
                    for (i = 0; i < obj.length; i++) {//下拉框的长度就是它的选项数.
                        if (obj[i].selected == true) {
                            text = obj[i].text;//获取当前选择项的文本.
                        }
                    }

                    var html = "<tr id='recadd'>" +
                        "<td>" + time + "</td>" +
                        "<td></td>" +
                        "<td>" + text + "</td>" +
                        "<td>" + $("#BankName").text() + "</td>" +
                        "<td>" + $("#BankNum").text() + "</td>" +
                        "<td>" + money + "</td>" +
                        "</tr>";

                    $("#rec").after(html);
                    $("#errMes").text("提现成功");
                } else {
                    $("#errMes").text(data.message);
                }
            }
        });

    } else {
        alert("请先填写银行信息");
    }

}

//用户的余额查询
function GetUserAmount() {
    postAjax("/user/getUserInfoByToken", {},
        function (data) {
            if (data.state == "success") {
                globalUser = data.data;
                $("#can").text(globalUser.amount)
            }
        });
}

//预计到账时间
function GetArrivalsTime() {
    var myDate = new Date();
    var year = myDate.getFullYear();
    var month = myDate.getMonth() + 1;
    if (month + 1 == 13) {
        year = year + 1;
        month = 1;
    } else {
        month = month + 1;
    }
    $("#Arrival").text(year + "-" + month + "-5");

}

function checkMoney(money) {
    var regex = /^\s*-?[1-9]\d*(\.\d{1,2})?\s*$/;
    return regex.test(money)

}