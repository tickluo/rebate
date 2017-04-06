var openAccountList;
var globalUser = {};

$(function () {

    //得到开户人
    GetOpenAccount();

    //用户的余额查询
    GetUserAmount();

    //用户本月提现次数查询
    GetUserRebateTimes();

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
        var $rebateCount = $("#rebateCount");
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
        if ($rebateCount.text() <= 0) {
            $("#errMes").text("您本月提现次数已经用完，请等待下月");
            return false;
        }
        postAjax("/rebate/doRebateCash", {
            bankId: $.trim($("#OpenAcountName").val()),
            applyMoney: $money.val()
        }, function (data) {
            if (data.state == "success") {
                //金额显示减掉
                var $can = $("#can");
                var canMoney = $can.text();
                var money = $("#Money").val();

                var mm = sub(canMoney, money);
                $can.text(mm.toFixed(2));
                //提现次数减一
                $rebateCount.text($rebateCount.text() - 1);

                $("#errMes").text("提现成功");
            } else {
                $("#errMes").text(data.message);
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

function GetUserRebateTimes() {
    postAjax("/rebate/getUserRebateTimes", {},
        function (data) {
            if (data.state == "success") {
                $("#rebateCount").text(10 - data.data)
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