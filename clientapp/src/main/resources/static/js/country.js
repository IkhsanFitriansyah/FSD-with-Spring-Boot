$(document).ready(function() {
    $("#table-country").DataTable({
        ajax: {
            method: "GET",
            url: "api/country",
            dataSrc: "",
        },
        columns: [
            { data : "null",
                render: (data,type, row, meta) => {
                    return meta.row + 1;
                },
        },
        { data : "code"},
        { data : "name"},
        { data : "region.name"},
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
                    onclick="deleteCountry(${data.id})">
                        Delete
                    </button> 
                    </div>`;
                },
            },
        ],
    });
});

    // GetById - Country
    function getById(id) {
        $.ajax({
            method: "GET",
            url: "api/country/" + id,
            dataType: "JSON",
            contentType: "application/json",
            success: (res) => {
                $("#detail-id").val(`${res.id}`);
                $("#detail-code").val(`${res.code}`);
                $("#detail-name").val(`${res.name}`);
                $("#detail-region-name").val(`${res.region.name}`);
            },
            error: (err) => {
                console.log(err);
            },
        });
    }

    $.ajax({
        method: "GET",
        url: "api/country/",
        dataType: "JSON",
        contentType: "application/json",
        success: (res) => {
            $("#detail-id").val(`${res.id}`);
            $("#detail-code").val(`${res.code}`);
            $("#detail-name").val(`${res.name}`);
            $("#detail-region-name").val(`${res.region.name}`);
        },
        error: (err) => {
            console.log(err);
        },
    });

    // Get all Region
    $.ajax({
        method: "GET",
        url: "api/region",
        dataType: "JSON",
        contentType: "application/json",
        success: (res) => {
            const select = $("#region");
            res.forEach((region) => {
                const option = `<option value="${region.id}">${region.name}</option>`;
                select.append(option);
            });
        },
        error: (err) => {
            console.log(err);
        },
    });

    // Create
    $('#create-country').click((event) => {
        event.preventDefault();

        let valueCode = $("#create-code").val();
        let valueName = $("#create-name").val();
        let regionId = $("#region").val();

        $.ajax({
            method: "POST",
            url: "api/country",
            dataType: "JSON",
            contentType: "application/json",
            beforeSend: addCSRFToken(),
            data: JSON.stringify({
                code : valueCode,
                name : valueName,
                region : {id : regionId},
            }),
            success: (res) => {
                $("#create").modal("hide");
                $("#table-country").DataTable().ajax.reload();
                console.log(regionId);
                Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "Country telah berhasil dibuat!",
                    showConfirmButton: false,
                    timer: 2000,
                });
                $("#create-code").val("");
                $("#create-name").val("");
                $("#region").val("");
            },
            error: (err) => {
                console.log(err);
                $("#create").modal("hide");
                Swal.fire({
                    icon: "error",
                    title: "Oh no...",
                    text: "Country gagal dibuat!",
                });
                $("#create-code").val("");
                $("#create-name").val("");
                $("#region").val("");
            },
        });
    });

    
    // BeforeUpdate
    function beforeUpdate(id) {

        // Get Region
        $.ajax({
            method: "GET",
            url: "api/region",
            dataType: "JSON",
            contentType: "application/json",
            success: (res) => {
                const select = $("#update-list-region");
                select.empty();
                res.forEach((region) => {
                    const option = `<option value="${region.id}">${region.name}</option>`;
                    select.append(option);
                });

                // Get Country
                $.ajax({
                    method: "GET",
                    url: "api/country/" + id,
                    dataType: "JSON",
                    contentType: "application/json",
                    success: (res) => {
                        
                        $("#update-id").val(`${res.id}`);
                        $("#update-code").val(`${res.code}`);
                        $("#update-name").val(`${res.name}`);
                        $("#update-list-region").val(`${res.region.id}`);
                    },
                    
                });
            },
            error: (err) => {
                console.log(err);
            },
            
        });

        // Update-Country
    $("#update-country").click((event) => {
        event.preventDefault();

        let valueId = $("#update-id").val();
        let valueCode = $("#update-code").val();
        let valueName = $("#update-name").val();
        let regionId = $("#update-list-region").val();

        $.ajax({
            method: "PUT",
            url: "api/country/" + valueId,
            dataType: "JSON",
            contentType : "application/json",
            beforeSend: addCSRFToken(),
            data: JSON.stringify({
                name: valueName,
                code: valueCode,
                region: {id: regionId},
            }),
            success: (res) => {
                $("#update").modal("hide");
                $("#table-country").DataTable().ajax.reload();

                Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "Country anda berhasil di Update",
                    showConfirmButton: false,
                    timer: 2000,
                });
                $("update-code").val("");
                $("update-name").val("");
                $("#update-list-region").val("");
            },
            error: (err) => {
                console.log(err);
                $("#update").modal("hide");
                Swal.fire({
                    icon: "error",
                    title: "Oh no...",
                    text: "Country gagal di Update!",
                });
                $("update-code").val("");
                $("update-name").val("");
                $("#update-list-region").val("");
            },
        });
    });
}

    

    // delete
    function deleteCountry(id) {
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: "btn btn-success",
                cancelButton: "btn btn-danger",
            },
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
            if(result.isConfirmed) { // ketika user yes
                $.ajax({
                    method: "DELETE",
                    url: "api/country/" + id,
                    dataType: "JSON",
                    contentType: "application/json",
                    beforeSend: addCSRFToken(),
                    success: (res) => {
                    swalWithBootstrapButtons.fire({
                        title: "Deleted!",
                        text: "Your data has been deleted",
                        icon: "success",
                    });
                    $("#table-country").DataTable().ajax.reload();
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

