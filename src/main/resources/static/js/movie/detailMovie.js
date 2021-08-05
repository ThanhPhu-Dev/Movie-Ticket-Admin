var swiper = new Swiper(".mySwiper", {
    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
    },
});

function videoplay() {
    const video = document.querySelector("#video");
    if (video.paused)
        video.play();
    else
        video.pause();
}

let btn_delete = document.getElementById("btn-delete_movie");
btn_delete.addEventListener("click", () => {
    Swal.fire({
        title: 'Bạn có chắc chắn?',
        text: "Thao tác này sẽ xóa vĩnh viễn Phim này!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Xóa',
        cancelButtonText: 'Hủy bỏ'
    }).then((result) => {
        if (result.isConfirmed) {
            TurnOnLoading();
            deleteActor()
        }
    })
})

function deleteActor() {
    let id = btn_delete.dataset.id;
    let url = "/api/delete/movie/" + id;
    axios.delete(url).then(async function (response) {
        TurnOfLoading();
        Swal.fire({
            icon: 'success',
            title: 'Xóa!',
            text: 'Xóa Phim thành công.',
            timer: 1500
        })
        setTimeout(function () {
            window.location.href = "/listMovie";
        }, 800);

    }).catch(function (err) {
        TurnOfLoading();
        Swal.fire({
            icon: 'error',
            title: 'Xóa!',
            text: 'Xóa phim không thành công.',
            timer: 1500
        })
    })
}