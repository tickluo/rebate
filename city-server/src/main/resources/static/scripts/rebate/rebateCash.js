var openAccountList;
$(function () {

    //得到开户人
    GetOpenAccount();

    //返利的产生
    GetProduce(0);

    //提现记录
    GetRecord();

    //时间选择
    ChangeTimeType();

    //用户的余额查询
    GetUserAmount();

    //预计到账时间
    GetArrivalsTime();
});
//返利的产生
function GetProduce(type) {
    var year = $("#txt_timeType").val();
    if (type == 1) {
        year = $("#txt_timeType").val();
    }

    $.ajax({
        url: "/RebateManger/GetRebateProduceList?year=" + year,
        type: "Get",
        dataType: "json",
        success: function (data) {
            if (data.List.length > 0) {

                var html = '';
                for (var i = 0; i < data.List.length; i++) {
                    var ch = new Array;
                    ch = data.List[i].PDate.split("-");
                    var date = ch[0] + "-" + ch[1];

                    html = html + "<tr id='pro" + i + "'>" +
                        "<td>" + date + "</td>" +
                        "<td>" + data.List[i].RebateMoney.toFixed(2) + "</td>" +
                        "<td>" + data.List[i].ApplyMoney.toFixed(2) + "</td>" +
                        "<td>" + data.List[i].balance.toFixed(2) + "</td>" +
                        "</tr>";
                }

                html = html + "<tr id='proLast' style=\"border:1px solid rgb(221, 221, 221)\">" +
                    "<td >总结</td>" +
                    "<td>" + data.AllRebateMon.toFixed(2) + "</td>" +
                    "<td>" + data.AllAplplyMon.toFixed(2) + "</td>" +
                    "<td>" + data.AllBalance.toFixed(2) + "</td>" +
                    "</tr>";

                $("#pro").after(html);
            } else {
                $("#proNoData").empty();
                var html = "";
                html = html + "<tr id='proNoData' style=\"border:1px solid rgb(221, 221, 221)\">" +
                    "<td colspan='4'>暂时还没有数据....</td>" +
                    "</tr>";
                $("#pro").after(html);
            }
        }

    });

}
//提现记录
function GetRecord() {
    var year = 2016;
    $.ajax({
        url: "/RebateManger/GetCashRecordList",
        type: "Get",
        dataType: "json",
        success: function (data) {

            if (data.length > 0) {
                var html = '';
                for (var i = 0; i < data.length; i++) {
                    var payTime = data[i].PayTime == null ? "" : data[i].PayTime;
                    html = html + "<tr id='rec" + i + "'>" +
                        "<td>" + data[i].ApplyTime + "</td>" +
                        "<td>" + payTime + "</td>" +
                        "<td>" + data[i].AcountName + "</td>" +
                        "<td>" + data[i].BankName + "</td>" +
                        "<td>" + data[i].BankNum + "</td>" +
                        "<td>" + data[i].ApplyGold + "</td>" +
                        "</tr>";
                }
                $("#rec").after(html);

            } else {
                $("#cesNoData").empty();
                var html = "";
                html = html + "<tr id='cesNoData'>" +
                    "<td colspan='6'>暂时还没有数据....</td>" +
                    "</tr>";
                $("#rec").after(html);
            }
        }
    });
}
//开户人
function GetOpenAccount() {
    $.ajax({
        url: "/RebateManger/GetOpenAcountName",
        type: "Get",
        dataType: "json",
        success: function (data) {
            if (data.length > 0) {
                openAccountList = data;

                var ddl = $("#OpenAcountName");
                //转成Json对象
                var result = eval(data);

                //循环遍历 下拉框绑定
                $(result).each(function (key) {
                    //第一种方法
                    var opt = $("<option></option>").text(result[key].OpenAcountName).val(result[key].BankID);
                    ddl.append(opt);
                });

                $("#BankName").text(data[0]["BankName"]);
                $("#BankBranchName").text(data[0]["BankBranchName"]);
                $("#BankNum").text(data[0]["BankNum"]);
            }
        }

    });
}
//切换账户人
function ChangeType() {
    var id = $("#OpenAcountName").val();
    $(openAccountList).each(function (key) {
        if (id == openAccountList[key].BankID) {
            $("#BankName").text(openAccountList[key]["BankName"]);
            $("#BankBranchName").text(openAccountList[key]["BankBranchName"]);
            $("#BankNum").text(openAccountList[key]["BankNum"]);
        }
    });
}
//提交申请
function btn_Submit() {
    if (openAccountList != null) {
        $.ajax({
            url: "/Rebate/RebateManger/SetCashRecord",
            data: {BankID: $.trim($("#OpenAcountName").val()), money: $("#Money").val()},
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data.state == 1) {
                    //金额显示减掉
                    var canMoney = $("#can").text();
                    var money = $("#Money").val();

                    var mm = sub(canMoney, money);
                    $("#can").text(mm.toFixed(2));
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
                    alert("提现成功");
                } else {
                    alert(data.message);
                }
            }
        });

    } else {
        alert("请先填写银行信息");
    }

}
//时间选择
function ChangeTimeType() {

    //时间选择
    $("#txt_timeType").change(function () {
        $("#RebateProduce tr:gt(0)").empty();
        GetProduce(1);
    });
}
//用户的余额查询
function GetUserAmount() {
    $.ajax({
        url: "/RebateManger/GetUserAmount",
        type: "Get",
        dataType: "json",
        success: function (data) {
            $("#all").text(data.MonthAllMount.toFixed(2));
            $("#can").text(data.CanWithdrawMount.toFixed(2));
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
