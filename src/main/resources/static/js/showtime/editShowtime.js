const submit = document.getElementById("submit");
let cinema = document.getElementById("cinema");
let movie = document.getElementById("movie");


submit.addEventListener("click",async e => {
    e.preventDefault();
    let data = {};
    data.cinemaId = +document.querySelector("#listCinema option[value='" + cinema.value + "']").dataset.value;
    data.movieId = +document.querySelector("#listMovie option[value='" + movie.value + "']").dataset.value;
    data.startTime = new Date(document.getElementsByName("startTime")[0].value)
    data.endTime = new Date(document.getElementsByName("endTime")[0].value)
    data.fare = +document.getElementsByName("fare")[0].value;
    data.id = +document.getElementsByName("id")[0]?.value;
    if(data.id){
        await updateShowtime(data);
        window.location.href = "/list-showtime";
    }else{
        await addShowtime(data);
        setValueDefault();
    }
});

function setValueDefault(){
    document.getElementsByName("startTime")[0].value = '';
    document.getElementsByName("endTime")[0].value = '';
    document.getElementsByName("cinemaName")[0].value = '';
    document.getElementsByName("movieName")[0].value = '';
    document.getElementsByName("fare")[0].value = '';
}

async function updateShowtime(data){
    return new Promise(resolve => {
        let url = "/api/edit-showtime";
        let json = JSON.stringify(data);
        axios.put(url, json,{
            headers:{
                "Content-Type": "application/json"
            }
        }).then(function (response){
            Swal.fire({
                icon: 'success',
                title: '',
                text: 'Cập Nhật Suất Chiếu Phim Thành Công!',
                timer: 1000
            });
            return resolve(response);
        });
    }).catch(function (error){
        alert(error);
    });
}

async function addShowtime(data) {
    return new Promise(resolve => {
        let url = "/api/edit-showtime";
        let json = JSON.stringify(data);
        axios.post(url, json, {
            headers: {
                "Content-Type": "application/json"
            }
        }).then(function (response) {
            Swal.fire({
                icon: 'success',
                title: '',
                text: 'Thêm Suất Chiếu Phim Thành Công!',
                timer: 1000
            });
            return resolve(response);
        }).catch(function (error) {
            Swal.fire({
                icon: 'error',
                title: '',
                text: 'Thêm Suất Chiếu Phim Thất Bại!',
                timer: 1000
            });
        })
    });
}