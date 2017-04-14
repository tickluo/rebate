$(function () {
    gridList();

    //得到当前用户的返利总额
    GetUserRebateMoney();
});
function gridList() {
    var $gridList = $("#gridList");
    $gridList.dataGrid({
        url: "/report/getCpsReportList",
        height: $(window).height() - 160,
        colModel: [
            {label: '主键', name: 'ID', hidden: true},
            {label: '商品编号', name: 'itemId', width: 125, align: 'left'},
            {
                label: '商品状态', name: 'productStatus', width: 100, align: 'left',
                formatter: function (cellvalue, options, rowObject) {
                    if (cellvalue == 9) {
                        return "已下单";
                    }
                    else if (cellvalue == 10) {
                        return "待付款";
                    } else if (cellvalue == 11) {
                        return "已取消";
                    } else if (cellvalue == 12) {
                        return "等待官网发货";
                    } else if (cellvalue == 13) {
                        return "官方已发货";
                    } else if (cellvalue == 14) {
                        return "转运中(可结算)";
                    } else if (cellvalue == 15) {
                        return "已收货(可结算)";
                    } else {
                        return "未知状态";
                    }
                }
            },
            {
                label: '商品名称', name: 'name', width: 320, align: 'left',
                formatter: function (cellvalue, options, rowObject) {
                    return "<a href='" + rowObject.ItemUrl + "'   style='text-decoration:none;color:blue;font-weight:bold;'><u>" + cellvalue + "</u></a>";
                }
            },
            {label: '数量', name: 'quantity', width: 100, align: 'left'},
            {
                label: '外币价格(单价)', name: 'price', width: 130, align: 'left',
                formatter: function (cellvalue, options, rowObject) {
                    return rowObject.currency || "" + " " + "<label style='color:red'>" + cellvalue.toFixed(2) + "</label>";
                }
            },
            {
                label: '实付价格(单价:元)', name: 'actuallyPay', width: 130, align: 'left',
                formatter: function (cellvalue, options, rowObject) {
                    return cellvalue.toFixed(2);
                }
            },
            {
                label: '返点', name: 'rebatePoint', width: 100, align: 'left',
                formatter: function (cellvalue, options, rowObject) {
                    return cellvalue + "%";
                }
            },
            {
                label: '返利金额(总价:元)', name: 'rebateTotalPrice', width: 130, align: 'left',
                formatter: function (cellvalue, options, rowObject) {
                    return cellvalue.toFixed(2);
                }
            },
            {
                label: '下单时间', name: 'orderTime', width: 150, align: 'left',
                formatter: "date",
                formatoptions: {srcformat: 'U', newformat: 'Y-m-d H:i:s'}
            },
            {
                label: '转运时间', name: 'transTime', width: 150, align: 'left',
                formatter: function (cellvalue, options, rowObject) {
                    if (!cellvalue || cellvalue == '') return '未转运';
                    return getLocalTime(cellvalue);
                }
            }

        ],
        pager: "#gridPager",
        sortname: 'CreateTime desc',
        viewrecords: true
    });

    //状态选择
    $("#txt_statue .dropdown-menu li").click(function () {
        var text = $(this).find('a').html();
        var value = $(this).find('a').attr('data-value');
        $("#txt_statue .dropdown-text").html(text).attr('data-value', value);
    });
    //时间选择
    $("#txt_timeType .dropdown-menu li").click(function () {
        var text = $(this).find('a').html();
        var value = $(this).find('a').attr('data-value');
        $("#txt_timeType .dropdown-text").html(text).attr('data-value', value);
    });

    //搜索
    $("#btn_search").click(function () {
        var timeType = $("#txt_timeType").find('.dropdown-text').attr('data-value') || 0;
        var startTime = $.trim($("#txt_startTime").val().replace('年', '-').replace('月', '-').replace('日', ''));
        var endTime = $.trim($("#txt_endTime").val().replace('年', '-').replace('月', '-').replace('日', ''));
        var productStatus = $("#txt_statue").find('.dropdown-text').attr('data-value');
        var itemId = $("#txt_keyword").val();
        $gridList.jqGrid('setGridParam', {
            postData: {
                productStatus: productStatus,
                itemId: itemId,
                timeType: timeType,
                startTime: startTime,
                endTime: endTime
            }
        }).trigger('reloadGrid');
    });
}

//导出所有数据
function ExportData() {


    var queryJson = {
        statue: $("#txt_statue").find('.dropdown-text').attr('data-value'),
        keyword: $("#txt_keyword").val(),
        timeType: $("#txt_timeType").find('.dropdown-text').attr('data-value'),
        startTime: $("#txt_startTime").val(),
        endTime: $("#txt_endTime").val(),
    };

    location.href = '/Rebate/Report/CpsExportData?queryJson=' + JSON.stringify(queryJson);
    //$.ajax({
    //    url: "/Rebate/Report/CpsExportData",
    //    data: { queryJson: JSON.stringify(queryJson) },
    //    type: "Get",
    //    dataType: "json",
    //    success: function (data) {
    //        console.log(data);

    //    }
    //});
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

function getLocalTime(nS) {
    return new Date(parseInt(nS)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ").replace(/上午/g, "");
}