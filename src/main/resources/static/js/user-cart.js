$(document).ready(function () {
    var dataOrder = {};

    $("#btn-order").on("click", function () {
        dataOrder.nameProd = $(this).attr("name");
        dataOrder.price = $(this).attr("price");

        axios.get("http://localhost:8080/current-user")
            .then(function (data) {
                dataOrder.nameUser = data.data.name;
                dataOrder.phone = data.data.phone;

                axios.post("http://localhost:8080/admins/order", dataOrder)
                    .then(function (data) {
                        swal("successfull");
                        // location.replace("/mobile");
                    }).catch(function (err) {
                    swal("err create order");
                })
            }).catch(function (err) {
            swal("Vui lòng đăng nhập để thanh toán",
                "http://localhost:8080/mobile/register");
        })
    })
});