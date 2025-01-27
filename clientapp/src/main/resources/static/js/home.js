// Get Profile
const getUserProfile = () => {
    const profileId = $("#userId");
    const profileName = $("#userName");
    const profileEmail = $("#userEmail");
    const profilePhone = $("#userPhone");


    $.ajax({
        url: "/profile",
        type: "GET",
        success: (res) => {
            profileId.val(res.id);
            profileName.text(res.name);
            profileEmail.text(res.email);
            profilePhone.text(res.phone);
        },
        error: (err) => {
            console.log(err);
        },
    });

};

    $("#updateUserProfile").click(() => {

        const profileId = $("#userId").val();

        $.ajax({
            type: "GET",
            url: "/api/employee/" + profileId,
            success: (res) => {
                $("#updateId").val(res.id);
                $("#updateName").val(res.name);
                $("#updateEmail").val(res.email);
                $("#updatePhone").val(res.phone);
            }
        })
    });

    $("#updateProfile").click((event) => {
        event.preventDefault();
        const profileId = $("#userId").val();
        const updateName = $("#updateName").val();
        const updateEmail = $("#updateEmail").val();
        const updatePhone = $("#updatePhone").val();

        const UpdatedProfile = {
            name: updateName,
            email: updateEmail,
            phone: updatePhone
        };

        $.ajax({
            type: "PUT",
            url: "/api/employee/" + profileId,
            dataType: "JSON",
            contentType: "application/json",
            beforeSend: addCSRFToken(),
            data: JSON.stringify(UpdatedProfile),
            success:(res) => {
                $("#updateProfileModal").modal("hide");
                Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "Profile anda berhasil di update",
                    showConfirmButton: false,
                    timer: 2000,
                });
            },
            error: () => {
                Swal.fire({
                    icon: "error",
                    title: "Oh no...",
                    text: "Profile gagal update!",
                });
            }
        })
    })

