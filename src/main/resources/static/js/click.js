$(document).ready(function () {


    $(".button-cate").on("click", function () {
        var prv = "@{'mobile/category'( page=${products.currentPage - 1})}";

        $(".button-prev").on("click", function () {
            $(this).attr("href", prv);
        });

        $(".button-next").on("click", function () {
            $(this).attr("href", "@{'mobile/category'( page=${products.currentPage + 1})}")
        })

    })

});