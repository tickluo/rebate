$(function () {
    gridList();
})
function gridList() {
    var $gridList = $("#gridList");
    $gridList.dataGrid({
        url: "/Rebate/RebateManger/GetListCashRecord",
        height: $(window).height() - 128,
        colModel: [
            {label: '主键', name: 'ID', hidden: true},
            {label: '申请日期', name: 'ApplyTime', width: 160, align: 'left'},
            {label: '支付日期', name: 'PayTime', width: 160, align: 'left'},
            {label: '开户名', name: 'AcountName', width: 130, align: 'left'},
            {label: '银行名称', name: 'BankName', width: 130, align: 'left'},
            {label: '银行账号', name: 'BankNum', width: 130, align: 'left'},
            {
                label: '申请金额', name: 'ApplyGold', width: 130, align: 'left',
                formatter: function (cellvalue, options, rowObject) {
                    return cellvalue.toFixed(2);
                }
            }
        ],
        pager: "#gridPager",
        sortname: 'CreateTime desc',
        viewrecords: true
    });


}