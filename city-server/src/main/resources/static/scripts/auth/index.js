var Anla = {
    view:function (param) {
        if($(".container").height() < $(window).height()){
            $('.wrapper').height($(window).height());
        }

    },
    content:function (param) {
        $(".container").css("margin-top", ($(window).height() - $(".container").height()) / 2 - 50);
    },
    loginInit:function (param) {
        var vm = this;
        this.view();
        this.content();
        $(window).resize(function (e) {
            vm.view();
            vm.content();
        });
    },
    regiserInit:function (param) {
        var vm = this;
        this.view();
        $(window).resize(function (e) {
            vm.view();
        });
    }
};

function checkEmail(str) {
    var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
    return re.test(str);
}