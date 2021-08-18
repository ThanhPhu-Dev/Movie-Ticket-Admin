const submitAdd = document.getElementById("submit-add-cinema");
const btn_delete = document.getElementById("btn-delete");
const btn_add = document.getElementById("btn-add");
let table;

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

btn_add.addEventListener("click", function (){
    let formsubmit = document.getElementById("formSubmit");
    document.getElementById('exampleModalToggleLabel').innerText= 'Thêm';
    formsubmit.getElementsByTagName("input")[0].value = '';
    formsubmit.getElementsByTagName("input")[1].value = '';
    formsubmit.getElementsByTagName("input")[2].value = '';
    formsubmit.getElementsByTagName("input")[3].value = '';
    formsubmit.getElementsByTagName("input")[4].value = '';
})

btn_delete.addEventListener("click", function (e) {
    let arrayDelete = [];
    document.querySelectorAll("input[type='checkbox']:checked").forEach(element => {
        arrayDelete.push(+element.value);
    });

    let url = "/api/delete-cinema"
    let json = JSON.stringify(arrayDelete);
    axios.delete(url, {
        headers: {
            "Content-Type": "application/json"
        },
        data: json
    }).then(async function (reponse) {
        Swal.fire({
            icon: 'success',
            title: '',
            text: 'Xóa Rạp Thành Công!',
            timer: 1000
        });
        await sleep(1300);
        location.reload();
        return;
    }).catch(function (error) {
        alert(error);
    })
})

submitAdd.addEventListener("click", function (e) {
    e.preventDefault();
    // TurnOnLoading();
    var data = {};
    var formData = $('#formSubmit').serializeArray();
    $.each(formData, function (i, v) {
        data["" + v.name + ""] = v.value;
    });
    if (data.id == "") {
        submitAdd.innerText = "Thêm";
        addCinema(data);

    } else {
        updateCinema(data);
    }
});

async function updateCinema(data) {
    var url = "/api/update-cinema";
    let dataJson = JSON.stringify(data);
    axios.put(url, dataJson, {
        headers: {
            "Content-Type": "application/json"
        }
    }).then(async function (reponse) {
        TurnOfLoading();
        Swal.fire({
            icon: 'success',
            title: '',
            text: 'Cập Nhật Rạp Thành Công!',
            timer: 1000
        });
        await sleep(1300);
        location.reload();
        return;
    }).catch(function (error) {
        TurnOfLoading();
        Swal.fire({
            icon: 'error',
            title: '',
            text: 'Cập Nhật Rạp Thất Bại!',
            timer: 1000
        });

    });
}

async function addCinema(data) {
    var url = "/api/add-cinema";
    let dataJson = JSON.stringify(data);
    axios.post(url, dataJson, {
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function (reponse) {
        TurnOfLoading();
        Swal.fire({
            icon: 'success',
            title: '',
            text: 'Thêm Rạp Thành Công!',
            timer: 1000
        });
        const tr = $(`<tr>
                                <td><input type="checkbox" id="vehicle1" name="vehicle1"></td>
                                <td>${reponse.data.name}</td>
                                <td>${reponse.data.address}</td>
                                <td class="text-center">${reponse.data.lenght}</td>
                                <td class="text-center">${reponse.data.width}</td>
                                <td>${reponse.data.createBy}</td>
                                <td>${reponse.data.createDate}</td>
                                <td>${reponse.data.modifyBy}</td>
                                <td>${reponse.data.modifyDate}</td>
                                <td><a  onclick=updateCinemaView(${reponse.data.id}) class="btn p-0" data-bs-toggle="modal" href="#exampleModalToggle" role="button"><i class="fas fa-tools"></i></a></td>
                              </tr>`);
        table.row.add(tr[0]).draw();
    }).catch(function (error) {
        TurnOfLoading();
        Swal.fire({
            icon: 'error',
            title: '',
            text: 'Thêm Rạp Thất Bại!',
            timer: 1000
        });
    });
}

async function getDataCinema() {
    return new Promise(resolve => {
        let url = "/api/list-cinema";
        axios.get(url)
            .then(function (response) {
                let modified = response.data.map((e, index) => {
                    return {
                        id: `<input type="checkbox" id="vehicle1" name="vehicle1" value="${e.id}">`,
                        name: e.name,
                        address: e.address,
                        lenght: e.lenght,
                        width: e.width,
                        createBy: e.createBy,
                        createDate: e.createDate,
                        modifyBy: e.modifyBy,
                        modifyDate: e.modifyDate,
                        detail: `<a  onclick=updateCinemaView(${e.id}) class="btn p-0" data-bs-toggle="modal" href="#exampleModalToggle" role="button"><i class="fas fa-tools"></i></a>`

                        // `<span onclick=updateCinemaView(${e.id})><i class="fas fa-tools"></i></i></span>`
                    }
                });
                return resolve(modified);
            }).catch(function (error) {
            alert(error);
        });
    });
}

async function initDataTable() {
    let datas = await getDataCinema();
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
            searchPlaceholder: "Tìm kiếm Cinema"
        },
        "bInfo": false,
        "dom": '<"top"i>rt<"bottom"flp><"clear">',
        initComplete: function () {
            $("#datatables_filter").detach().appendTo('#new-search-area');
        },

        data: datas,
        columns: [
            {
                data: 'id',
            },
            {data: 'name'},
            { data: 'address'},
            {
                className: 'text-center',
                data: 'lenght'
            },
            {
                className: 'text-center',
                data: 'width'},
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
                "width": "13%"
            },
            {
                data: 'detail',
                "width": "5%"
            },
        ],
    });
}



async function updateCinemaView(id) {
    let formsubmit = document.getElementById("formSubmit");
    let url = "api/cinema/" + id;

    axios.get(url).then(function (reponse) {
        document.getElementById('exampleModalToggleLabel').innerText= 'Cập Nhật';
        formsubmit.getElementsByTagName("input")[0].value = reponse.data.id;
        formsubmit.getElementsByTagName("input")[1].value = reponse.data.name;
        formsubmit.getElementsByTagName("input")[2].value = reponse.data.lenght;
        formsubmit.getElementsByTagName("input")[3].value = reponse.data.width;
        formsubmit.getElementsByTagName("input")[4].value = reponse.data.address;
    }).catch(function (error) {
        alert(error);
    })
}

initDataTable();