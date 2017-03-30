(function ($) {
    $.update = {
        updateClick: function () {
            $("#mes").text("");
            var $pwd = $("#pwd");
            if ($pwd.val() == "") {
                $pwd.focus();
                $("#mes").text("请输入原始密码");
                return false;
            }
            var $newPwd = $("#newPwd");
            if ($newPwd.val() == "") {
                $newPwd.focus();
                $("#mes").text("请输入新密码");
                return false;
            }

            var passwordregx = /^[a-zA-Z0-9]{6,18}$/;
            var passwordbo = passwordregx.test($newPwd.val());
            if (!passwordbo) {
                $newPwd.focus();
                $("#mes").text('密码 6～18个英文字母或数字');
                return false;
            }


            var $againPwd = $("#againPwd");
            if ($againPwd.val() == "") {
                $againPwd.focus();
                $("#mes").text("请输入确认密码");
                return false;
            }
            if ($.trim($newPwd.val()) != $.trim($againPwd.val())) {
                $againPwd.val("");
                $againPwd.focus();
                $("#mes").text("两次密码填写不一致");
                return false;
            }
            postAjax("/user/resetPassword", {
                    oldPassword: $.trim($pwd.val()),
                    newPassword: $.trim($newPwd.val())
                },
                function (data) {
                    if (data.state == "success") {
                        $("#mes").text(data.message);
                    } else {
                        $("#mes").text(data.message);
                    }
                })
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

        }
    };
    $(function () {
        $.update.init()
    });
})(jQuery);