$(function () {
    gridList();
})
function gridList() {
    var $gridList = $("#gridList");
    $gridList.dataGrid({
        url: "/Rebate/RebateManger/RebateSiteList",
        height: $(window).height() - 128,
        colModel: [
            { label: '主键', name: 'SiteID', hidden: true },
            { label: '网站名称', name: 'SiteName', width: 200, align: 'left' },
            {
                label: '返利点', name: 'Point', width: 120, align: 'left',
                formatter: function (cellvalue, options, rowObject) {
                    return cellvalue+"%";
                }
            },
            {
                label: '网站Url', name: 'SiteUrl', width: 240, align: 'left',
                formatter: function (cellvalue, options, rowObject) {
                    return "<a href='http://www." + cellvalue + "'  style='text-decoration:none;color:blue;font-weight:bold;'><u>http://www." + cellvalue + "</u></a>";
                }
            },

        ],
        pager: "#gridPager",
        sortname: 'CreateTime desc',
        viewrecords: true
    });

    $("#btn_search").click(function () {
        $gridList.jqGrid('setGridParam', {
            postData: { keyword: $("#txt_keyword").val() },
        }).trigger('reloadGrid');
    });
}

function btn_add() {
    $.modalOpen({
        id: "Form",
        title: "新增网址",
        url: "/Rebate/Bank/Form",
        width: "700px",
        height: "610px",
        callBack: function (iframeId) {
            top.frames[iframeId].submitForm();
        }
    });
}