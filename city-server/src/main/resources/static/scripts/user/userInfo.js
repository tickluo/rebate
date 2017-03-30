(function ($) {
    $.update = {
        updateClick: function () {
            $("#mes").text("");
            var $email = $("#email");
            if ($email.val() == "") {
                $email.focus();
                $("#mes").text("请输入邮箱");
                return false;
            }
            var isemail = checkEmail($email.val());
            if (!isemail) {
                $email.focus();
                $("#mes").text("邮箱无效，请输入邮箱");
                return false;
            }

            var numRegx = /^[0-9]*$/;

            var $phone = $("#phone");
            if ($phone.val() == "") {
                $phone.focus();
                $("#mes").text("请输入手机号");
                return false;
            }

            var phonebo = numRegx.test($phone.val());
            if (!phonebo) {
                $phone.focus();
                $("#mes").text("手机 数字格式");
                return false;
            }

            if (!(/^1(3|4|5|7|8)\d{9}$/.test($phone.val()))) {
                $phone.focus();
                $("#mes").text("手机号码有误");
                return false;
            }

            postAjax("/user/updateUserInfo",{
                email: $.trim($email.val()),
                phone: $.trim($phone.val())
            },function (data) {
                if (data.state == "success") {
                    $("#mes").text(data.message);
                } else {
                    $("#mes").text(data.message);
                }
            })
        },
        setUserMessage: function () {
            $("#userName").val(top.globalUser.username);
            $("#account").val(top.globalUser.accountType == "1" ? "公司" : "个人");
            if (top.globalUser.accountType == "1") {
                $("#company").val(top.globalUser.companyName);
            } else {
                $(".company").hide();
            }
            $("#truename").val(top.globalUser.actualName);
            $("#email").val(top.globalUser.email);
            $("#phone").val(top.globalUser.phone);
        },
        init: function () {
            $("#update_button").click(function () {
                $.update.updateClick();
            });
            document.onkeydown = function (e) {
                if (!e) e = window.event;
                if ((e.keyCode || e.which) == 13) {
                    document.getElementById("update_button").focus();
                    document.getElementById("update_button").click();
                }
            };
            $.update.setUserMessage();
        }
    };
    $(function () {
        $.update.init()
    });
})(jQuery);


function changeType(me) {
    var ss = $(me).val();
    if (ss == 2) {
        $(".type").hide();
    } else {
        $(".type").show();
    }
}