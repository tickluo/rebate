$(document).ready(function () {
    var $siteName = $("#siteName");
    var $siteUser = $("#siteUser");
    getAjax("/rebate/getSiteRebateList", {}, function (data) {
        var selectList = data.map(function (item) {
            return {
                id: item.id,
                points: item.sitePoints,
                url: item.siteUrl,
                text: item.siteName
            }
        });
        $siteName.select2({
            data: selectList,
            escapeMarkup: function (markup) {
                return markup;
            } // let our custom formatter work
            //minimumInputLength: 1,
            // templateResult: formatRepo, // omitted for brevity, see the source of this page
            //templateSelection: formatRepoSelection // omitted for brevity, see the source of this page
        });
    });
    getAjax("/user/getAllMerchant", {}, function (data) {
        var selectList = data.data.map(function (item) {
            return {
                id: item.id,
                text: item.companyName
            }
        });
        $siteUser.select2({
            data: selectList,
            escapeMarkup: function (markup) {
                return markup;
            } // let our custom formatter work
            //minimumInputLength: 1,
            // templateResult: formatRepo, // omitted for brevity, see the source of this page
            //templateSelection: formatRepoSelection // omitted for brevity, see the source of this page
        });
    });

    $siteName.on("select2:select", function (e) {
        var selectData = e.params.data;
        $("#siteUrl").text(selectData.url);
        if (selectData.points && selectData > 0) {
            $("#sitePoints").val(selectData.points);
        }
    });
    $siteUser.on("select2:select", function (e) {
        var selectData = e.params.data

    });
});

//form 表单提交
function submitForm() {
    $("#siteFormMes").text("");

    var $siteName = $("#siteName option:selected");
    if (!$siteName.val()) {
        $("#siteFormMes").text("请选择网站");
        return false;
    }

    var $siteUser = $("#siteUser option:selected");
    if (!$siteUser.val()) {
        $("#siteFormMes").text("请选择用户");
        return false;
    }

    var $sitePoints = $("#sitePoints");
    var sitePoints = $sitePoints.val();
    if (sitePoints == "") {
        $("#siteFormMes").text("请输入网站返利点");
        return false;
    }
    if (!validatePercentage(sitePoints)) {
        $("#siteFormMes").text("请输入正确的返利点");
        return false;
    }

    postAjax("/rebate/saveSiteRebatePoint", {
        userId: $.trim($siteUser.val()),
        siteId: $.trim($siteName.val()),
        sitePoints: $.trim($sitePoints.val())
    }, function (data) {
        if (data.state == "success") {
            $("#siteFormMes").text("保存成功");
            $.currentWindow().$("#gridList").trigger("reloadGrid");

            $.currentWindow().layerSu();

            top.layer.closeAll();
        } else {
            $("#siteFormMes").text(data.message);
        }

    });

}

function validatePercentage(x) {
    var parts = x.split(".");
    if (typeof parts[1] == "string" && (parts[1].length == 0 || parts[1].length > 2))
        return false;
    var n = parseFloat(x);
    if (isNaN(n))
        return false;
    if (n < 0 || n > 100)
        return false;

    return true;
}
