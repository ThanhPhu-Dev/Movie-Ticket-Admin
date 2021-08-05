let input = document.getElementById("search-name");
let all_delete_text = document.getElementById("icon-deleteAll-text");
const btnSubmit = document.getElementById("form-submit");


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

async function findByName(name) {
    let divContent = document.getElementById("list-idol");

    let url = "/api/idol/" + name;
    if (name == '') {
        url = "/api/idol";
    }
    axios.get(url).then(function (response) {
        renderDataSearch(response.data);
    }).catch(function (error) {
        console.log(error)
    })
}

function renderDataSearch(data) {
    let divContent = document.getElementById("list-idol");
    let paging = document.getElementById("pagination");

    let content = data.content.map(element => {
        return ` <div class="actor-info d-inline-block ml-3 mr-2 mb-1 mt-2">
                    <a href="/actor/detail/${element.id}">
                        <img height="auto"
                            style="max-height: 400px"
                             src="${element.public_url}"
                             width="200px">
                        <div class="name-actor">
                            <span>${element.name}</span>
                        </div>
                    </a>
                </div>`
    });
    divContent.innerHTML = content.join('');

    /*paginated*/
    let btn_last = `<a class="${data.currentPage > 1 ? '' : 'disabled'}" onclick="nextprev(${data.currentPage - 1})" href="#">Last</a>`;
    let itempage = '';
    for (let i = 1; i <= data.totalPages; i++) {
        itempage = itempage + `<a class="page-link ${data.currentPage == i ? 'active disabled ' : ''}"
                                   href="#" onclick="nextprev(${i})"
                                >${i}</a>
                                 `;
    }
    let btn_next = `<a class="${data.currentPage < data.totalPages ? '' : 'disabled'}" onclick="nextprev(${data.currentPage + 1})"
                                    href="#">Next</a>`;
    paging.innerHTML = '';
    if (data.totalPages > 1) {
        paging.innerHTML = btn_last;
        paging.insertAdjacentHTML("beforeend", itempage);
        paging.insertAdjacentHTML("beforeend", btn_next);
    }
}

function nextprev(currentPage) {
    let name = input.value;
    let url = "/api/idol/" + name + "/page/" + currentPage;
    if (name == '') {
        url = "/api/idol/page/" + currentPage;
    }
    axios.get(url).then(function (response) {
        let data = response.data;
        renderDataSearch(data);
    }).catch(function (error) {
        console.log(error)
    })
}