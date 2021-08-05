let btn_delete = document.getElementById("btn-delete_Idol");
btn_delete.addEventListener("click", () => {
    Swal.fire({
        title: 'Bạn có chắc chắn?',
        text: "Thao tác này sẽ xóa vĩnh viễn diễn viên này!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Xóa',
        cancelButtonText: 'Hủy bỏ'
    }).then((result) => {
        if (result.isConfirmed) {
            deleteActor()
        }
    })
})

function deleteActor() {
    let id = btn_delete.dataset.id;
    let url = "/api/delete/actor/" + id;
    axios.delete(url).then(async function (response) {
        Swal.fire({
            icon: 'success',
            title: 'Xóa!',
            text: 'Xóa diễn viên thành công.',
            timer: 1500
        })
        setTimeout(function () {
            window.location.href = "/actorlist";
        }, 800);

    }).catch(function (err) {
        Swal.fire({
            icon: 'error',
            title: 'Xóa!',
            text: 'Xóa diễn viên không thành công.',
            timer: 1500
        })
    })
}