
$(function () {
    gridList();
});
function gridList() {
    var $gridList = $("#gridList");
    $gridList.dataGrid({
        url: "/bank/getBankList",
        height: $(window).height() - 128,
        colModel: [
            { label: '主键', name: 'id', hidden: true },
            { label: '开户人/公司账户名称', name: 'openAccountName', width: 120, align: 'left' },
            { label: '身份证号/营业执照号', name: 'licenceNum', width: 150, align: 'left' },
            { label: '银行名称', name: 'bankName', width: 120, align: 'left' },
            { label: '支行名称', name: 'bankBranchName', width: 120, align: 'left' },
            { label: '银行账号', name: 'bankNum', width: 150, align: 'left' },
            {
                label: '操作', name: 'bankName', width: 80, align: 'left',
                formatter: function (cellvalue, options, rowObject) {
                    return "不可修改";
                }
            }
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
        title: "新增银行信息",
        url: "/bank/bankForm",
        width: "700px",
        height: "610px",
        callBack: function (iframeId) {
            top.frames[iframeId].submitForm();
        }
    });
}

var layerSu = function () {

    setTimeout(function () {

        $.modalMsg("添加成功", "success")

    },500)

};

window.layerSu = layerSu;