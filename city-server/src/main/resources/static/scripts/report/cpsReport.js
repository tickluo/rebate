$(function () {
    gridList();

    //得到当前用户的返利总额
    GetUserRebateMoney();
})
function gridList() {
    var $gridList = $("#gridList");
    $gridList.dataGrid({
        url: "/Rebate/Report/GetItemData",
        height: $(window).height() - 160,
        colModel: [
            { label: '主键', name: 'ID', hidden: true },
            { label: '商品编号', name: 'ItemID', width: 125, align: 'left' },
            {
                label: '商品状态', name: 'ItemStatue', width: 100, align: 'left',
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
                label: '商品名称', name: 'ItemName', width: 320, align: 'left',
                formatter: function (cellvalue, options, rowObject) {
                    return "<a href='" + rowObject.ItemUrl + "'   style='text-decoration:none;color:blue;font-weight:bold;'><u>" + cellvalue + "</u></a>";
                }
            },
            { label: '数量', name: 'ItemNum', width: 100, align: 'left' },
            {
                label: '外币价格(单价)', name: 'ItemPrice', width: 130, align: 'left',
                formatter: function (cellvalue, options, rowObject) {
                    return rowObject.PriceType +" "+"<label style='color:red'>"+cellvalue.toFixed(2)+"</label>";
                }
            },
            {
                label: '实付价格(单价:元)', name: 'ItemActuallyPay', width: 130, align: 'left',
                formatter: function (cellvalue, options, rowObject) {
                    return cellvalue.toFixed(2);
                }
            },
            {
                label: '返点', name: 'RebatesPoint', width: 100, align: 'left',
                formatter: function (cellvalue, options, rowObject) {
                    return cellvalue + "%";
                }
            },
            {
                label: '返利金额(总价:元)', name: 'RebateAllPrice', width: 130, align: 'left',
                formatter: function (cellvalue, options, rowObject) {
                    return cellvalue.toFixed(2);
                }
            },
            { label: '下单时间', name: 'ItemOrderTime', width: 150, align: 'left' },
            { label: '转运时间', name: 'ItemTransTime', width: 150, align: 'left' },

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
        var queryJson = {
            statue: $("#txt_statue").find('.dropdown-text').attr('data-value'),
            keyword: $("#txt_keyword").val(),
            timeType: $("#txt_timeType").find('.dropdown-text').attr('data-value'),
            startTime: $("#txt_startTime").val(),
            endTime: $("#txt_endTime").val(),
        }
        $gridList.jqGrid('setGridParam', {
            postData: { queryJson: JSON.stringify(queryJson) },
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
    $.ajax({
        url: "/Report/GetUserRebateMoney",
        type: "Get",
        dataType: "json",
        success: function (data) {
            $("#mon").text(data.money.toFixed(2));
        },
        error: function () {

        }

    });

}