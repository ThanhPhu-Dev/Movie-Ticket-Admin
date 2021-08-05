let table;
let btn_delete = document.getElementById("btn-delete");

btn_delete.addEventListener("click", function (e){
    Swal.fire({
        title: 'Bạn có chắc chắn?',
        text: "Thao tác này sẽ xóa vĩnh viễn, không thể quay lại!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Xóa',
        cancelButtonText: 'Hủy bỏ'
    }).then((result) => {
        if (result.isConfirmed) {
            deleteShowtime()
        }
    })
});


function deleteShowtime(){
    let arrayDelete = [];
    document.querySelectorAll("input[type='checkbox']:checked").forEach( element =>{
        arrayDelete.push(+element.value);
    });
    let url = "/api/delete-showtime";
    axios.delete(url,{
        headers:{
            "Content-Type": "application/json"
        },
        data: JSON.stringify(arrayDelete)
    }).then(function (response){
        location.reload();
    }).catch(function (error){
        alert(error);
    })
}

async function getDateShowtime(){
    return new Promise(resolve => {
        let url = "api/list-showtime";
        axios.get(url).then(function (response){
            let modified = response.data.map((e, index) => {
                return {
                    id: `<input type="checkbox" id="vehicle1" name="vehicle1" value="${e.id}">`,
                    movie: e.movie.name,
                    cinema: e.cinema.name,
                    fare: e.fare,
                    startDate: e.startTime,
                    endTime: e.endTime,
                    createBy: e.createBy,
                    createDate: e.createDate,
                    modifyBy: e.modifyBy,
                    modifyDate: e.modifyDate,
                    detail: `<a  class="btn p-0"  href="/edit-showtime/${e.id}" role="button"><i class="fas fa-tools"></i></a>`
                }
            });
            return resolve(modified);
        });
    }).catch(function (error){
        alert(error)
    })
}

async function initDataTables(){
    let datas = await getDateShowtime();
    table = $('#datatables').DataTable({
        "pageLength": 5,
        "lengthMenu": [5, 10, 15],
        "oLanguage": {
            "sSearch": "",
            "sLengthMenu": "Hiển Thị _MENU_ ",
            "oPaginate": {
                "sPrevious": "Trước đó",
                "sNext": "Tiếp theo"
            }
        },
        language: {
            searchPlaceholder: "Tìm kiếm Suất Chiếu"
        },
        "bInfo": false,
        "dom": '<"top"i>rt<"bottom"flp><"clear">',
        initComplete: function () {
            $("#datatables_filter").detach().appendTo('#new-search-area');
        },
        "scrollX": true,
        scrollCollapse: true,
        fixedColumns:   {
            leftColumns: 4,
            rightColumns: 1
        },
        data: datas,
        columns: [
            {
                data: 'id',
            },
            {data: 'movie'},
            {data: 'cinema'},
            {
                data: 'fare'
            },
            {
                data: 'startDate',
                render: function (data, type, row) {
                    if (type === "sort" || type === "type") {
                        return data;
                    }
                    return moment(data).format("MM-DD-YYYY");
                },
                "width": "13%",
            },
            {
                data: 'endTime',
                render: function (data, type, row) {
                    if (type === "sort" || type === "type") {
                        return data;
                    }
                    return moment(data).format("MM-DD-YYYY");
                },
                "width": "13%",
            },
            {
                data: 'createBy',
                "width": "13%",
            },
            {
                data: 'createDate',
                defaultContent: '',
                render: function (data, type, row) {
                    if (type === "sort" || type === "type") {
                        return data;
                    }
                    return moment(data).format("MM-DD-YYYY");
                },
                "width": "10%",
            },
            {
                data: 'modifyBy',
                "width": "15%"
            },
            {
                data: 'modifyDate',
                render: function (data, type, row) {
                    if (type === "sort" || type === "type") {
                        return data;
                    }
                    return moment(data).format("MM-DD-YYYY");
                },
                "width": "10%"
            },
            {
                data: 'detail',
                "width": "5%"
            },
        ],
    });
}



window.addEventListener("load", e => {
    initDataTables();
});