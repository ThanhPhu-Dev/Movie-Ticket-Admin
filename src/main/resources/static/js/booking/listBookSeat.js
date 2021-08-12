let table;

async function getDateBooking() {
    return new Promise(resolve => {
        let url = "api/list-booking";
        axios.get(url).then(function (response) {
            // let modified = response.data.map((e, index) => {
            //     return {
            //         id: e.id,
            //         movie: e.movie.name,
            //         cinema: e.cinema.name,
            //         fare: e.fare,
            //         startDate: e.startTime,
            //         endTime: e.endTime,
            //     }
            // });
            return resolve(response.data);
        });
    }).catch(function (error) {
        alert(error)
    })
}

async function initDataTables() {
    let datas = await getDateBooking();
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
        // "scrollX": true,
        // scrollCollapse: true,
        // fixedColumns:   {
        //     leftColumns: 5,
        // },
        data: datas,
        columns: [
            {
                data: "id",
                render: function (data, type,row){
                    return `<input type="checkbox" id="vehicle1" name="vehicle1" value="${data}">`
                }
            },
            {data: 'movieName'},
            {data: 'cinemaName'},
            {
                data: 'startDate',
                render: function (data, type, row) {
                    if (type === "sort" || type === "type") {
                        return data;
                    }
                    return moment(data).format("MM-DD-YYYY");
                },
            },
            {
                data: 'endDate',
                render: function (data, type, row) {
                    if (type === "sort" || type === "type") {
                        return data;
                    }
                    return moment(data).format("MM-DD-YYYY");
                },
            },
            {data: 'total'},
            {
                data: 'createBy',
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
            },

            {
                "className": 'details-control',
                "data": null,
                "defaultContent": ''
            },
        ],
    });
}
function detail(row){
    console.log(row);
}

$('.table').on('click', 'tbody td.details-control', function () {
    var tr = $(this).closest('tr');
    var row = table.row(tr);
    if (row.child.isShown()) {
        // This row is already open - close it
        row.child.hide();
        tr.removeClass('shown');
    } else {
        // Open this row
        row.child(format(row.data())).show();
        tr.addClass('shown');
    }
});

function format ( d ) {
    // `d` is the original data object for the row
    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
        '<tr>'+
        '<td>Vị Trí Đặt Ghế:</td>'+
        '<td>'+d.seats.join(", ")+'</td>'+
        '</tr>'+
        '</table>';
}

window.addEventListener("load", e => {
    initDataTables();
});