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

        let content = response.data.map(element => {
            return ` <div class="actor-info d-inline-block ml-3 mr-2 mb-1 mt-2">
                    <a href="/actor/detail/${element.id}">
                        <img height="auto"
                             src="${element.public_url}"
                             width="200px">
                        <div class="name-actor">
                            <span>${element.name}</span>
                        </div>
                    </a>
                </div>`
        });

        divContent.innerHTML = content.join('');
    }).catch(function (error) {
        console.log(error)
    })
}