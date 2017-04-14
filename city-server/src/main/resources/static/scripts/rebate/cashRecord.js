$(function () {
    gridList();
});
function gridList() {
    var $gridList = $("#gridList");
    $gridList.dataGrid({
        url: "/rebate/getCashRecordList",
        height: $(window).height() - 128,
        colModel: [
            {label: '主键', name: 'id', hidden: true},
            {
                label: '申请日期', name: 'createTime', width: 160, align: 'left',
                formatter: "date",
                formatoptions: {srcformat: 'U', newformat: 'Y-m-d H:i:s'}, sortable: false
            },
            {label: '支付日期', name: 'payTime', width: 160, align: 'left', sortable: false},
            {label: '开户名', name: 'accountName', width: 130, align: 'left', sortable: false},
            {label: '银行名称', name: 'bankName', width: 130, align: 'left', sortable: false},
            {label: '银行账号', name: 'bankNum', width: 130, align: 'left', sortable: false},
            {
                label: '申请金额', name: 'applyMoney', width: 130, align: 'left',
                formatter: function (cellvalue, options, rowObject) {
                    return cellvalue.toFixed(2);
                }, sortable: false
            }
        ],
        pager: "#gridPager",
        sortname: 'CreateTime desc',
        viewrecords: true
    });


}