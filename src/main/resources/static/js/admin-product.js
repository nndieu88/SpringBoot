$(document).ready(function () {
    var dataProduct = {};

    $(".close, #btn-close").on("click", function () {
        location.reload();
    });

    // add
    $("#new-product").on("click", function () {
        $("#modal-create-cate").modal();

        $("#save-cate").on("click", function () {
            dataProduct.nameProd = $("#input-category-name").val();
            dataProduct.model = $("#input-category-model").val();
            // dataProduct.image
            dataProduct.price = $("#input-category-price").val();
            dataProduct.status = $("#input-category-status").val();
            dataProduct.description = $("#input-category-decription").val();

            axios.post("http://localhost:8080/admins/products", dataProduct)
                .then(function (data) {
                    swal("successful");
                }).catch(function (err) {
                swal("err");
            });
        });
    });

    // update
    $(".btn-edit-product").on("click", function () {
        $("#modal-update-cate").modal();
        var id = $(this).attr("cateid");

        axios.get("http://localhost:8080/admins/products/" + id)
            .then(function (data) {
                $("#input-cate-name-update").val(data.data.nameProd);
                $("#input-cate-model-update").val(data.data.model);
                // $("#input-cate-image-update").val(data.data.image);
                $("#input-cate-price-update").val(data.data.price);
                $("#input-cate-status-update").val(data.data.status);
                $("#input-cate-decription-update").val(data.data.description);
                console.log(data);
            });

        $("#btn-update-cate").on("click", function () {
            dataProduct.nameProd = $("#input-cate-name-update").val();
            dataProduct.model = $("#input-cate-model-update").val();
            // dataProduct.image=$()
            dataProduct.price = $("#input-cate-price-update").val();
            dataProduct.status = $("#input-cate-status-update").val();
            dataProduct.description = $("#input-cate-decription-update").val();

            axios.put("http://localhost:8080/admins/products/" + id, dataProduct)
                .then(function (data) {
                    swal("successful");
                }).catch(function (err) {
                swal("err");
            })
        })

    });

    // delete
    $(".btn-delete-product").on("click", function () {
        var id = $(this).attr("cateid");
        if (confirm("bạn có muốn xóa không")) {
            axios.delete("http://localhost:8080/admins/products/" + id)
                .then(function (data) {
                    swal("successful");
                    location.reload();
                }).catch(function (err) {

            });
            return;
        }
        location.reload();
    })

});