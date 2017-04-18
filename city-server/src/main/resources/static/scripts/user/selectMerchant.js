$(document).ready(function () {
    var $selectMerchant = $('#SelectMerchant');
    var $MesDisplay = $('#mes');
    var selectData;
    var currentSelectUser;
    getAjax("/user/getAllMerchant", {}, function (data) {
        var selectList = data.data.map(function (item) {
            return {
                id: item.id,
                text: item.companyName,
                username: item.username,
                actualName: item.actualName
            }
        });
        $selectMerchant.select2({
            data: selectList,
            escapeMarkup: function (markup) {
                return markup;
            } // let our custom formatter work
            //minimumInputLength: 1,
            // templateResult: formatRepo, // omitted for brevity, see the source of this page
            //templateSelection: formatRepoSelection // omitted for brevity, see the source of this page
        });

        var defaultSelect = selectList.find(function (item) {
            return item.id == $selectMerchant.val();
        });
        postAjax("adminSelectMerchant", {
            username: defaultSelect.username
        }, function (data) {
            if (data.state == 'success') {
                selectData = defaultSelect;
                $('#MerchantUsername').text(selectData.username);
                $('#MerchantActualName').text(selectData.actualName);
                $('#MerchantCompanyName').text(selectData.text);
                currentSelectUser = selectData.id;
            }
            return $MesDisplay.text(data.message);
        })
    });

    $selectMerchant.on("select2:select", function (e) {
        selectData = e.params.data;
        $('#MerchantUsername').text(selectData.username);
        $('#MerchantActualName').text(selectData.actualName);
        $('#MerchantCompanyName').text(selectData.text);

    });

    $('#DoSelect').on('click', function (e) {
        if (currentSelectUser == $selectMerchant.val()) {
            return $MesDisplay.text('不能重复选择商户');
        }
        postAjax("adminSelectMerchant", {
            username: selectData.username
        }, function (data) {
            if (data.state == 'success') {
                currentSelectUser = selectData.id;
                return $MesDisplay.text('已选中商户')
            }
            return $MesDisplay.text(data.message);
        })
    });

});
