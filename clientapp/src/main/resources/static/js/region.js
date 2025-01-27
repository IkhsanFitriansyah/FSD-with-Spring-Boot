$(document).ready(function() {
    $("#table-region").DataTable({
        ajax: {
            method: "GET",
            url: "api/region",
            dataSrc: "",
        },
        columns: [
            { data : "null",
                render: (data, type, row, meta) => {
                    return meta.row + 1;},
            },
            { data : "name"},
            {
                data : null,
                render: (data) => {
                    return `
                    <div class="d-flex gap-3 justify-content-center">

                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#detail"
                    onclick="getById(${data.id})">
                        Detail
                    </button> 
                    
                    <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#update"
                    onclick="beforeUpdate(${data.id})">
                        Update
                    </button> 

                    <button type="button" class="btn btn-danger""
                    onclick="deleteRegion(${data.id})">
                        Delete
                    </button> 
                    </div>`;
                },
            },
        ],
    });
});

    // Get By Id
    function getById(id) {
        $.ajax({
            method: "GET",
            url: "api/region/" + id,
            dataType: "JSON",
            contentType: "application/json",
            success: (res) => {
                $("#detail-id").val(`${res.id}`);
                $("#detail-name").val(`${res.name}`);
            },
            error: (err) => {
                console.log(err);
            },
        });
    }
    
    // Get By all

        $.ajax({
            method: "GET",
            url: "api/region/",
            dataType: "JSON",
            contentType: "application/json",
            success: (res) => {
                console.log(res);
            },
            error: (err) => {
                console.log(err);
            },
        });


    // Create
    $("#create-region").click((event) => {
        event.preventDefault();
        let valueName = $("#create-name").val();

        $.ajax({
            method: "POST",
            url: "api/region",
            dataType: "JSON",
            contentType: "application/json",
            beforeSend: addCSRFToken(),
            data: JSON.stringify({
                name: valueName,
            }),
            success: (res) => {
                $("#create").modal("hide");
                $("#table-region").DataTable().ajax.reload();
                Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "Region telah berhasil dibuat!",
                    showConfirmButton: false,
                    timer: 2000,
                });
                $("#create-name").val("");
            },
            error: (err) => {
                console.log(err);
                $("#create").modal("hide");
                Swal.fire({
                    icon: "error",
                    title: "Oh no...",
                    text: "Region gagal dibuat!",
                });
                $("#create-name").val("");
            },
        });
    });

    // BeforeUpdate
    function beforeUpdate(id) {
        $.ajax({
            method: "GET",
            url: "api/region/" + id,
            dataType: "JSON",
            contentType: "appliction/json",
            success: (res) => {
                $("#update-id").val(`${res.id}`);
                $("#update-name").val(`${res.name}`);
            },
            error: (err) => {
                console.log(err);
            }, 
        });
    }

    // Update-Region
    $("#update-region").click((event) => {
        event.preventDefault();

        let valueId = $("#update-id").val();
        let valueName = $("#update-name").val();

        $.ajax({
            method: "PUT",
            url: "api/region/" + valueId,
            dataType: "JSON",
            contentType : "application/json",
            beforeSend: addCSRFToken(),
            data: JSON.stringify({
                name: valueName,
            }),
            success: (res) => {
                $("#update").modal("hide");
                $("#table-region").DataTable().ajax.reload();
                
                Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "Region anda berhasil di Update",
                    showConfirmButton: false,
                    timer: 2000,
                });
                $("#update-name").val("");
            },
            error: (err) => {
                console.log(err);
                $("#update").modal("hide");
                Swal.fire({
                    icon: "error",
                    title: "Oh no...",
                    text: "Region gagal dibuat!",
                });
                $("#update-name").val("");
            },
        });
    });

    // Delete-Region
    function deleteRegion(id) {
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: "btn btn-success",
                cancelButton: "btn btn-danger",
            },
            buttonStyling: false,
        });
        swalWithBootstrapButtons
        .fire({
            title: "Are you Sure?",
            text: "You won't be able to delete this!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonText: "Yes, Delete it!",
            cancelButtonText: "No, Cancel that!",
            reverseButtons: true,
        })
        .then((result) => {
            if(result.isConfirmed) {
                $.ajax({
                    method: "DELETE",
                    url: "api/region/" + id,
                    dataType: "JSON",
                    contentType: "application/json",
                    beforeSend: addCSRFToken(),
                    success: (res) => {
                    swalWithBootstrapButtons.fire({
                        title: "Deleted!",
                        text: "Your data has been deleted",
                        icon: "success",
                    });
                    $("#table-region").DataTable().ajax.reload();
                      },
                      error: (err) => {
                        console.log(err);
                    },
                });
            } else if (
                result.dismiss === Swal.DismissReason.cancel
            ) {
                swalWithBootstrapButtons.fire({
                    title: "Cancelled",
                    text: "Your data is safe",
                    icon: "error",
                });
            }
        });
    }