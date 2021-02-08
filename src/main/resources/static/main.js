$(document).ready(function () {
    $("button").click(function () {
        $.ajax({
            url: 'http://localhost:8080/short?long_link='.concat($("#urlinput").val()),
            type: 'get',
            dataType: 'JSON',
            success: function (response) {
                var hash = response.short_link;
                $("#shorturl").val("http://localhost:8080/s/".concat(hash));
            }
        });
    });
});