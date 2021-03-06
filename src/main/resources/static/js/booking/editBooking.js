let table;

async function getDateShowtime() {
    return new Promise(resolve => {
        let url = "api/list-showtime-booking";
        axios.get(url).then(function (response) {
            let modified = response.data.map((e, index) => {
                return {
                    id: e.id,
                    movie: e.movie.name,
                    cinema: e.cinema.name,
                    fare: e.fare,
                    startDate: e.startTime,
                    endTime: e.endTime,
                }
            });
            return resolve(modified);
        });
    }).catch(function (error) {
        alert(error)
    })
}

async function initDataTables() {
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
        data: datas,
        columns: [
            {data: 'movie'},
            {data: 'cinema'},
            {data: 'fare'},
            {
                data: 'startDate',
                render: function (data, type, row) {
                    if (type === "sort" || type === "type") {
                        return data;
                    }
                    return moment(data).format("DD-MM-YYYY HH:mm");
                },
            },
            {
                data: 'endTime',
                render: function (data, type, row) {
                    if (type === "sort" || type === "type") {
                        return data;
                    }
                    return moment(data).format("DD-MM-YYYY HH:mm");
                },
            },
            {
                "className": 'details-control',
                "data": "id",
                render: function (data, type, row){
                    return `<a href="/book-seat/${data}" title="Đặt Vé"><i class="fas fa-check-double"></i></a>`
                },
            },
        ],
    });
}

window.addEventListener("load", e => {
    initDataTables();
});