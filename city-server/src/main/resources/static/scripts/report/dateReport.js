$(function () {
    gridList();

    //得到当前用户的返利总额
    GetUserRebateMoney();
})
function gridList() {
    var $gridList = $("#gridList");

    $gridList.dataGrid({
        url: "/report/getDateReportList",
        height: $(window).height() - 160,
        colModel: [
            {label: '主键', name: 'Data', hidden: true},
            {label: '日期', name: 'orderTime', width: 200, align: 'left'},
            {label: '全部订单数', name: 'allOrderNum', width: 100, align: 'left', sortable: false},
            {label: '可结算订单数', name: 'validOrderNum', width: 100, align: 'left', sortable: false},
            {
                label: '全部订单额', name: 'allOrderPrice', width: 130, align: 'left', sortable: false,
                formatter: function (cellvalue, options, rowObject) {
                    return cellvalue.toFixed(2);
                }
            },
            {
                label: '可结算订单额', name: 'validOrderPrice', width: 130, align: 'left', sortable: false,
                formatter: function (cellvalue, options, rowObject) {
                    return cellvalue.toFixed(2);
                }
            },
            {
                label: '全部返利总额', name: 'allRebatePrice', width: 130, align: 'left', sortable: false,
                formatter: function (cellvalue, options, rowObject) {
                    return cellvalue.toFixed(2);
                }
            },
            {
                label: '有效返利总额', name: 'validRebatePrice', width: 150, align: 'left', sortable: false,
                formatter: function (cellvalue, options, rowObject) {
                    return cellvalue.toFixed(2);
                }
            }
        ],
        pager: "#gridPager",
        viewrecords: true

    });

    //时间选择
    $("#txt_timeType .dropdown-menu li").click(function () {
        var text = $(this).find('a').html();
        var value = $(this).find('a').attr('data-value');
        $("#txt_timeType .dropdown-text").html(text).attr('data-value', value);
    });

    //搜索
    $("#btn_search").click(function () {
        var bo = true;
        //if (startTime == "" || endTime == "") {
        //    if (startTime=="" && endTime=="") {

        //    } else {
        //        alert("请开始结束时间输入完整");
        //        bo = false;
        //    }
        //}
        if (bo) {
            var timeType = $("#txt_timeType").find('.dropdown-text').attr('data-value') || 0;
            var startTime = $.trim($("#txt_startTime").val().replace('年', '-').replace('月', '-').replace('日', ''));
            var endTime = $.trim($("#txt_endTime").val().replace('年', '-').replace('月', '-').replace('日', ''));
            $gridList.jqGrid('setGridParam', {
                postData: {timeType: timeType, startTime: startTime, endTime: endTime}
            }).trigger('reloadGrid');
        }
    });
}


///导出所有日期搜索结果
function ExportData() {
    var startTime = $("#txt_startTime").val();
    var endTime = $("#txt_endTime").val();
    var bo = true;

    //if (startTime == "" || endTime == "") {
    //    if (startTime=="" && endTime=="") {

    //    } else {
    //        alert("请开始结束时间输入完整");
    //        bo = false;
    //    }
    //}
    if (bo) {
        var queryJson = {
            timeType: $("#txt_timeType").find('.dropdown-text').attr('data-value'),
            startTime: $("#txt_startTime").val(),
            endTime: $("#txt_endTime").val(),
        }
    }

    window.location.href = '/Rebate/Report/TimeExportData?queryJson=' + JSON.stringify(queryJson);
}


//得到当前用户的返利总额
function GetUserRebateMoney() {
    getAjax("/user/getUserRebateAmount", {}, function (data) {
        if (data.state == 'success') {
            $("#mon").text(data.data.toFixed(2));
        }
        else $("#mon").text('获取失败');
    });
}