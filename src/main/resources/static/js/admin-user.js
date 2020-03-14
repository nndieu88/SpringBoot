$(document).ready(function () {
    var dataUser = {};

    $(".close, #btn-close").on("click", function () {
        location.reload();
    });


    //  add
    $("#new-category").on("click", function () {
        $("#modal-create-cate").modal();

        $(".btn-save-category").on("click", function () {
            dataUser.name = $("#input-category-name").val();
            dataUser.email = $("#input-category-email").val();
            dataUser.password = $("#input-category-password").val();

            axios.post("http://localhost:8080/admins/users", dataUser)
                .then(function (data) {
                    swal("successful")
                }).catch(function (err) {
                swal("err")
            });
        });
    });

    //  update
    $(".btn-edit-cate").on("click", function () {
        $("#modal-update-cate").modal();
        var id = $(this).attr("cateid");

        axios.get("http://localhost:8080/admins/users/" + id)
            .then(function (data) {
                $("#input-cate-name-update").val(data.data.name);
                $("#input-cate-address-update").val(data.data.address);
                $("#input-cate-phone-update").val(data.data.phone);
                $("#input-cate-avatar-update").val(data.data.avatar);
                $("#input-cate-email-update").val(data.data.email);
                $("#input-cate-password-update").val(data.data.password);
            });

        $("#btn-update-cate").on("click", function () {
            dataUser.name = $("#input-cate-name-update").val();
            dataUser.address = $("#input-cate-address-update").val();
            dataUser.phone = $("#input-cate-phone-update").val();
            dataUser.avatar = $("#input-cate-avatar-update").val();
            dataUser.email = $("#input-cate-email-update").val();
            dataUser.password = $("#input-cate-password-update").val();

            axios.put("http://localhost:8080/admins/users/" + id, dataUser)
                .then(function (data) {
                    swal("successfull");
                    location.reload();
                }).catch(function (err) {
                swal("err");
            })
        })
    });

    // delete
    $(".btn-delete-cate").on("click", function () {
        var id = $(this).attr("cateid");
        if (confirm("bạn có muốn xóa không")) {
            axios.delete("http://localhost:8080/admins/users/" + id)
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
