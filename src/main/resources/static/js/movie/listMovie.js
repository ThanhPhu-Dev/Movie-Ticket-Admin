let input = document.getElementById("search-name");
let all_delete_text = document.getElementById("icon-deleteAll-text");
const btnSubmit = document.getElementById("form-submit");
let category = document.getElementById("category");


input.addEventListener("input", (e) => {
    if (e.target.value.length == 0) {
        all_delete_text.style.display = "none"
        findByName("");
    } else {
        all_delete_text.style.display = "block"
    }
})

btnSubmit.addEventListener("submit", e => {
    e.preventDefault();
    findByName(input.value)
})

all_delete_text.addEventListener("click", e => {
    input.value = '';
    all_delete_text.style.display = "none"
    findByName("");
})

category.addEventListener("change", async e => {
    let divContent = document.getElementById("list-movie");
    let idCategory = e.target.value;
    let url = '/api/category-movie/' + idCategory;
    axios.get(url).then(function (response) {
        renderDataSearch(response.data, 2);
    }).catch(function (error) {
        console.log(error)
    })
})

async function findByName(name) {
    let idCategory = category.value;
    let url = "/api/movie/" + idCategory + "/" + name;
    if (name == '') {
        url = "/api/movie/" + idCategory;
    }
    axios.get(url).then(function (response) {
        let data = response.data;
        renderDataSearch(data, 1);
    }).catch(function (error) {
        console.log(error)
    })
}

function renderDataSearch(data, flat) {
    if (flat == 1) {
        evt = "nextprevSearch";
    } else {
        evt = "nextprevCategory";
    }
    let divContent = document.getElementById("list-movie");
    let paging = document.getElementById("pagination");

    let content = data.content.map(element => {
        return ` <div class="movie-info d-inline-block ml-3 mr-2 mb-1 mt-2">
                    <a href="/movie/${element.id}">
                        <div class="movie-info--img">
                            <img  src="${element.posterUrl}" width="200" style="height: auto">
                        </div>
                        <p>${element.name}</p>
                    </a>
                </div>`
    });
    divContent.innerHTML = content.join('');

    /*paginated*/
    let btn_last = `<a class="${data.currentPage > 1 ? '' : 'disabled'}" onclick="${evt}(${data.currentPage - 1})" href="#">Last</a>`;
    let itempage = '';
    for (let i = 1; i <= data.totalPages; i++) {
        itempage = itempage + `<a class="page-link ${data.currentPage == i ? 'active disabled ' : ''}"
                                   href="#" onclick="${evt}(${i})"
                                >${i}</a>
                                 `;
    }
    let btn_next = `<a class="${data.currentPage < data.totalPages ? '' : 'disabled'}" onclick="${evt}(${data.currentPage + 1})"
                                    href="#">Next</a>`;
    paging.innerHTML = '';
    if (data.totalPages > 1) {
        paging.innerHTML = btn_last;
        paging.insertAdjacentHTML("beforeend", itempage);
        paging.insertAdjacentHTML("beforeend", btn_next);
    }
}

function nextprevSearch(currentPage) {
    let name = input.value;
    let idCategory = category.value;
    let url = "/api/movie/" + idCategory + "/" + name + "/page/" + currentPage;
    if (name == "") {
        url = "/api/movie/" + idCategory + "/page/" + currentPage;
    }
    axios.get(url).then(function (response) {
        let data = response.data;
        renderDataSearch(data, 1);
    }).catch(function (error) {
        console.log(error)
    })
}

function nextprevCategory(currentPage) {
    let idCategory = category.value;
    let url = '/api/category-movie/' + idCategory + "/page/" + currentPage;
    axios.get(url).then(function (response) {
        renderDataSearch(response.data, 2);
    }).catch(function (error) {
        console.log(error)
    })
}