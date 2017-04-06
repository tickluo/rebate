$(function () {
    //加载类型
    autoType();
    //省级联动
    Provinceslinkage();

})
//省级联动
function Provinceslinkage() {
    $("#city_4").citySelect({
        prov: "上海",
        city: "普陀区",
        dist: "none",
        nodata: "none"
    });
}


//加载类型
function autoType() {
    var type = $("#BankType").val();
    if (type == "1") {//企业
        $("#OpenAcountName").attr("placeholder", "请输入公司账户名称")
        $("#LicenceNum").attr("placeholder", "请输入营业执照号");
        $(".person").hide();
    } else {
        $("#OpenAcountName").attr("placeholder", "请输入开户人")
        $("#LicenceNum").attr("placeholder", "请输入身份证号");
        $(".company").hide();
    }
}

//切换账户类型
function ChangeType() {
    var type = $("#BankType").val();
    if (type == "1") {//企业
        $("#OpenAcountName").attr("placeholder", "请输入公司账户名称")
        $("#LicenceNum").attr("placeholder", "请输入营业执照号");
        $(".company").show();
        $(".person").hide();
    } else {
        $("#OpenAcountName").attr("placeholder", "请输入开户人")
        $("#LicenceNum").attr("placeholder", "请输入身份证号");
        $(".person").show()
        $(".company").hide();
    }
}
//form 表单提交
function submitForm() {
    $("#mes").text("");

    var $BankName = $("#BankName option:selected");
    if ($BankName.val() == "0") {
        $("#mes").text("请选择银行");
        return false;
    }
    var $BankBranchName = $("#BankBranchName");
    if ($BankBranchName.val() == "") {
        $("#mes").text("请输入支行名称");
        return false;
    }
    var $BankNum = $("#BankNum");
    if ($BankNum.val() == "") {
        $("#mes").text("请输入银行卡号");
        return false;
    }

    var type = $("#BankType").val();

    var $OpenAcountName = $("#OpenAcountName");
    if ($OpenAcountName.val() == "") {
        if (type == "1") {
            $("#mes").text("请输入公司账户名称");
        } else {
            $("#mes").text("请输入开户人");
        }

        return false;
    }

    var $LicenceNum = $("#LicenceNum");
    if ($LicenceNum.val() == "") {
        if (type == "1") {
            $("#mes").text("请输入营业执照号");
        } else {
            $("#mes").text("请输入身份证号");
        }

        return false;
    }

    var $LicencePositive = $("#LicencePositive");
    if ($LicencePositive.val() == "") {
        if (type == "1") {
            $("#mes").text("请输入营业执正面");
        } else {
            $("#mes").text("请输入身份证正面");
        }

        return false;
    }

    var $LicenceSide = $("#LicenceSide");
    if ($LicenceSide.val() == "") {
        if (type == "1") {
            $("#mes").text("请输入开户许可证");
        } else {
            $("#mes").text("请输入身份证反面");
        }

        return false;
    }
    var $BankDistrict = $("#BankDistrict").val() == null ? "" : $("#BankDistrict").val();
    console.log($BankDistrict);

    postAjax("/bank/addBankInfo",{
        bankNo: $.trim($BankName.val()),
        bankName: $.trim($BankName.text()),
        bankProvince: $("#BankProvince").val(),
        bankCity: $("#BankCity").val(),
        bankDistrict: $BankDistrict,
        bankBranchName: $.trim($BankBranchName.val()),
        bankType: $.trim(type),
        bankNum: $.trim($BankNum.val()),
        openAccountName: $.trim($OpenAcountName.val()),
        licenceNum: $.trim($LicenceNum.val()),
        licencePositive: $.trim($LicencePositive.val()),
        licenceSide: $.trim($LicenceSide.val())
    },function (data) {
        if (data.statue == "1") {
            $("#mes").text("新添加成功");
            $.currentWindow().$("#gridList").trigger("reloadGrid");

            $.currentWindow().layerSu();

            top.layer.closeAll();
        } else {
            $("#mes").text(data.message);
        }

    });

}

function upload(input, form, url, callback) {
    input.change(function () {
        if (this.value) {
            var formData = new FormData(form);
            //formData.append("image",form);
            $.ajax({
                async: false,
                cache: false,
                enctype: 'multipart/form-data',
                contentType: false,
                processData: false,
                type: "POST",
                dataType: "JSON",
                url: url,
                data: formData,
                error: function (e) {
                },
                success: function (data) {
                    if (typeof (callback) == "function") {
                        callback.call(null, data);
                    }
                }
            });
        }
    });
}

$(document).ready(function () {
    upload($("#FileUpload1"), $("#fileForm1")[0], "/upload/uploadImage", function (data) {
        if (data.state == "success") {
            $("#res1").text("上传成功");
            $("#LicencePositive").val(data.data.path);
        } else {
            $("#res1").text("上传失败");
        }

    });
    upload($("#FileUpload2"), $("#fileForm2")[0], "/upload/uploadImage", function (data) {
        if (data.state == "success") {
            $("#res2").text("上传成功");
            $("#LicenceSide").val(data.data.path);
        } else {
            $("#res2").text("上传失败");
        }

    });
});