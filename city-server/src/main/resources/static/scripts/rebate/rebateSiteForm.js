$(document).ready(function () {
    getAjax("/rebate/getSiteRebateList", {}, function (data) {
        var selectList = data.map(function (item) {
            return {
                id:item.id,
                text:item.siteName
            }
        });
        $(".js-data-example-ajax").select2({
            data: selectList,
            escapeMarkup: function (markup) {
                return markup;
            } // let our custom formatter work
            //minimumInputLength: 1,
            // templateResult: formatRepo, // omitted for brevity, see the source of this page
            //templateSelection: formatRepoSelection // omitted for brevity, see the source of this page
        });
    });
});

