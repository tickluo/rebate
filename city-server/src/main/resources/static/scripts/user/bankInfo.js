$(function () {
    gridList();
});
function gridList() {
    var $gridList = $("#gridList");
    $gridList.dataGrid({
        url: "/bank/getBankList",
        height: $(window).height() - 128,
        colModel: [
            { label: '主键', name: 'BankID', hidden: true },
            { label: '账户', name: 'BankName', width: 80, align: 'left' }
        ],
        pager: "#gridPager",
        sortname: 'CreateTime desc',
        viewrecords: true
    });

    $("#btn_search").click(function () {
        $gridList.jqGrid('setGridParam', {
            postData: { keyword: $("#txt_keyword").val() }
        }).trigger('reloadGrid');
    });
}

function btn_add() {
    $.modalOpen({
        id: "Form",
        title: "新增用户",
        url: "/SystemManage/User/Form",
        width: "700px",
        height: "510px",
        callBack: function (iframeId) {
            top.frames[iframeId].submitForm();
        }
    });
}