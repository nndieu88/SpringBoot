$(document).ready(function () {
    var data = {};

    $(".register-user").on("click", function () {
        data.name = $(".name").val();
        data.email = $(".email").val();
        data.password = $(".password").val();

        axios.post("http://localhost:8080/admins/users", data)
            .then(function (data) {
                dataLogin = {
                    email: $(".email").val(),
                    password: $(".password").val()
                };
                axios.post("http://localhost:8080/login", dataLogin)
                    .then(function () {
                        swal("succesfull");
                        location.replace("/mobile")
                    })
            }).catch(function (err) {
            swal("err");
        })
    });

});