$(document).ready(function () {
    var data = {};

    $(".register-user").on("click", function () {
        data.name = $(".name").val();
        data.email = $(".email").val();
        data.password = $(".password").val();

        axios.post("http://localhost:8080/admins/users", data)
            .then(function (data) {
                localStorage.setItem("name", $(".email").val());
                localStorage.setItem("pass", $(".password").val());
                location.replace("/mobile/tran-page");
            }).catch(function (err) {
            swal("err");
        })
    });

    $(".click-register").on("click", function () {
        let dataLogin = {
            email: localStorage.getItem("name"),
            password: localStorage.getItem("pass")
        };

        axios.post("http://localhost:8080/login", dataLogin)
            .then(function (data) {
                location.replace("/mobile")
            }).catch(function () {
            swal("Lỗi hệ thống, vui lòng đăng nhập thủ công");
        })
    })
});