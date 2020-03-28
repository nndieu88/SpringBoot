$(document).ready(function () {
    var data = {};
    $(".login-user").on("click", function () {
        data.email = $(".email").val();
        data.password = $(".password").val();

        axios.post("http://localhost:8080/login", data)
            .then(function (data) {
                if (data.data == "ROLE_ADMIN") {
                    location.replace("/admin")
                } else {
                    location.replace("/mobile");
                }
            }).catch(function () {
            // swal("Tài khoản sai, vui lòng nhập lại")
        })
    })
});