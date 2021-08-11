let arrayBookSeat = [];
let totalPrice = 0;
let total_element = document.getElementById("total-price");

$(".seat-checkbox").click( function(){
    const seat = $(this)[0].value;
    fare = +total_element.dataset.price;
    if( $(this).is(':checked') ){
        arrayBookSeat.push(seat);
        totalPrice = totalPrice + fare;
    }
    else{
        arrayBookSeat = arrayBookSeat.filter(item => item !== seat)
        totalPrice = totalPrice - fare;
    }
    total_element.innerText = "Tổng Tiền: "+ totalPrice + " VNĐ";
});

document.getElementById("submit").addEventListener("click", e =>{
    url = '/api/booking';
    let data = JSON.stringify({"showtimeId": e.target.dataset.id,
                                     "total": totalPrice,
                                     "seats": arrayBookSeat})
    console.log(data)
    axios.post(url, data, {
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(function (reponse){
        Swal.fire({
            icon: 'success',
            title: '',
            text: 'Đặt Chổ Thành Công!',
            timer: 1000
        });
        window.location.href = "/list-booking";
    }).catch(function (error){
        Swal.fire({
            icon: 'error',
            title: '',
            text: 'Đặt Chổ Thất Bại!',
            timer: 1000
        });
    });
});

