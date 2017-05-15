(function ($) {
    $.register = {
        formMessage: function (msg) {
            $('.login_tips').find('.tips_msg').remove();
            $('.login_tips').append('<div class="tips_msg"><i class="fa fa-question-circle"></i>' + msg + '</div>');
        },
        registerClick: function () {
            var $account = $("#txt_account");
            if ($account.val() == "") {
                $account.focus();
                $.register.formMessage('请输入用户名');
                return false;
            }
            var accountregx = /^[a-z0-9]{4,10}$/;
            var accountbo = accountregx.test($account.val());
            if (!accountbo) {
                $account.focus();
                $.register.formMessage('用户名 4～10个小写英文字母或数字');
                return false;
            }

            var $paaword = $("#txt_password");
            if ($paaword.val() == "") {
                $paaword.focus();
                $.register.formMessage('请输入密码');
                return false;
            }
            var passwordregx = /^[a-zA-Z0-9]{6,18}$/;
            var passwordbo = passwordregx.test($paaword.val());
            if (!passwordbo) {
                $paaword.focus();
                $.register.formMessage('密码 6～18个英文字母或数字');
                return false;
            }

            if ($("#accountType").val() == "1") {
                var $company = $("#txt_company");
                if ($company.val() == "") {
                    $company.focus();
                    $.register.formMessage('请输入企业名称');
                    return false;
                }
            }
            var $truename = $("#txt_trueName");
            if ($truename.val() == "") {
                $truename.focus();
                $.register.formMessage('请输入真实姓名');
                return false;
            }
            var $email = $("#txt_email");
            if ($email.val() == "") {

                $email.focus();
                $.register.formMessage('请输入邮箱');
                return false;
            }
            var isemail = checkEmail($email.val());
            if (!isemail) {
                $email.focus();
                $.register.formMessage('请输入正确邮箱');
                return false;
            }

            var numRegx = /^[0-9]*$/;

            var $phone = $("#txt_phone");
            if ($phone.val() == "") {
                $phone.focus();
                $.register.formMessage('请输入手机号');
                return false;
            }

            var phonebo = numRegx.test($phone.val());
            if (!phonebo) {
                $phone.focus();
                $.register.formMessage('手机 数字格式');
                return false;
            }

            if (!(/^1(3|4|5|7|8)\d{9}$/.test($phone.val()))) {
                $phone.focus();
                $.register.formMessage('手机号码有误');
                return false;
            }

            var $code = $("#txt_code");
            if ($code.val() == "") {
                $code.focus();
                $.register.formMessage('请输入验证码');
                return false;
            }
            $("#register_button").attr('disabled', 'disabled').find('span').html("loading...");
            var companyName = $.trim($("#accountType").val()) == "1" ? $.trim($company.val()) : "";
            postAjax("/auth/doRegister", {
                "username": $.trim($account.val()),
                "password": $.trim($paaword.val()),
                "accountType": $.trim($("#accountType").val()),
                "companyName": companyName,
                "actualName": $.trim($truename.val()),
                "email": $.trim($email.val()),
                "phone": $.trim($phone.val()),
                "code": $.trim($code.val())
            }, function (data) {
                if (data.success) {
                    $("#register_button").find('span').html("注册成功，正在跳转...");
                    $.register.formMessage("");
                    window.setTimeout(function () {
                        window.location.href = "/home/index";
                    }, 3000);
                } else {
                    $("#register_button").removeAttr('disabled').find('span').html("注册");
                    $code.val('');
                    $.register.formMessage(data.message);
                }
            })
        },
        init: function () {
            $("#register_button").click(function () {
                $.register.registerClick();
            });
            document.onkeydown = function (e) {
                if (!e) e = window.event;
                if ((e.keyCode || e.which) == 13) {
                    document.getElementById("register_button").focus();
                    document.getElementById("register_button").click();
                }
            }
        }
    };
    $(function () {
        Anla.regiserInit();
        $.register.init()
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

//获取验证码
function GetShortMessage() {

    if (!(/^1(3|4|5|7|8)\d{9}$/.test($("#txt_phone").val()))) {
        $("#txt_phone").focus();
        $.register.formMessage('手机号码有误');
        return false;
    }

    $.ajax({
        url: "/captcha/GetAuthCode",
        data: {UserPhone: $.trim($("#txt_phone").val())},
        type: "post",
        dataType: "json",
        success: function (data) {
            if (data.success) {
                $.register.formMessage(data.message);
            }
        }
    });
}