let editor;
let Idol;
let Category;
let ImageDelete;
ClassicEditor
    .create(document.querySelector('#description'))
    .then(newEditor => {
        editor = newEditor;
    })
    .catch(error => {
        console.error(error);
    });

window.onload = (event) => {
    findAllIdol();
    findAllCategory();
    ImageDelete = [];
};

async function findAllIdol() {
    axios.get("/api/allIdol").then(function (response) {
        Idol = response.data;
    }).catch(function (error) {
        console.log("error call find all idol")
    });
}

async function findAllCategory() {
    axios.get("/api/allCategory").then(function (response) {
        Category = response.data;
    }).catch(function (error) {
        console.log("error call find all idol")
    });
}

$("#btnadd").click(async function (e) {
    e.preventDefault();
    TurnOnLoading();
    let description = editor.getData();
    let formData = $('#formSubmit')[0];
    let data = new FormData(formData);
    data.append("description", description);
    if(data.get("id") != null){
        ImageDelete.map( e => {
            data.append("imageDelete", e);
        })
        await updateMovie(data);
        window.location.href = "/movie/" + data.get("id");
    }else{
        await addMovie(data);
        setvalueDefaul();
    }
})

function setvalueDefaul() {
    let input = document.getElementsByTagName("input");
    input.forEach(e => {
        e.value = '';
    });
    editor.setData('');
    document.getElementById("avatar").getElementsByTagName('img')[0].src = 'http://placehold.it/300x350';
    document.getElementById("video").innerHTML = `<img height="auto" src="http://placehold.it/1040x500" width="auto">`;
    let imgedes = document.getElementsByClassName('dandev_attach_view')[0];
    imgedes.innerHTML = '';
    imgedes.parentElement.classList.remove('show-btn');

    let actorDiv = document.getElementById("actor").getElementsByTagName('div');
    for (let i = actorDiv.length - 1; i > 0; i--) {
        actorDiv[i].parentNode.removeChild(actorDiv[i]);
    }

    let categoryDiv = document.getElementById("category").getElementsByTagName('div');
    for (let i = categoryDiv.length - 1; i > 0; i--) {
        categoryDiv[i].parentNode.removeChild(categoryDiv[i]);
    }
}

async function updateMovie(data){
    try{
        return new Promise((resolve, reject) => {
            axios.post(
                "/api/edit-movie", data, {
                    headers: {
                        "Content-Type": "multipart/form-data"
                    }
                }
            ).then(function (response) {
                TurnOfLoading();
                Swal.fire({
                    icon: 'success',
                    title: '',
                    text: 'Cập Nhật Phim Thành Công!',
                    timer: 1000
                })
                return resolve(response);
            }).catch(function (error) {
                TurnOfLoading();
                Swal.fire({
                    icon: 'error',
                    title: '',
                    text: 'Cập Nhật Phim Thất Bại!',
                    timer: 1000
                })
                return reject(error);
            })
        });
    }catch (error){
        alert(error);
    }
}

async function addMovie(data) {
    try {
        return new Promise((resolve, reject) => {
            axios.post(
                "/api/edit-movie", data, {
                    headers: {
                        "Content-Type": "multipart/form-data"
                    }
                }
            ).then(function (response) {
                TurnOfLoading();
                Swal.fire({
                    icon: 'success',
                    title: '',
                    text: 'Thêm Phim Thành Công!',
                    timer: 1000
                })
                return resolve(response);
            }).catch(function (error) {
                TurnOfLoading();
                Swal.fire({
                    icon: 'error',
                    title: '',
                    text: 'Thêm Phim Thất Bại!',
                    timer: 1000
                })
                return reject(error);
            })
        });
    } catch (error) {
        alert(error)
    }

}

function addActor(el) {
    let div = document.getElementById(el);
    var d = new Date();
    var _time = d.getTime();
    var option = Idol.map(el => {
        return `<option value="${el.name}">`;
    })
    let _html = `<div class="mt-2 d-flex align-items-center" id="${_time}">
                                    <input class="form-control" name="${el}" list="${_time + 1}" placeholder="Diễn Viên">
                                    <datalist id="${_time + 1}">
                                        ${option.join('')}
                                    </datalist>
                                    <span class="cursorAdd" onclick="removeElement(${_time})"><i class="fas fa-minus"></i></span>
                                </div>`;
    div.insertAdjacentHTML('beforeend', _html);
}

function addCategory(el) {
    let div = document.getElementById(el);
    var d = new Date();
    var _time = d.getTime();
    var option = Category.map(el => {
        return `<option value="${el.name}">`;
    })
    let _html = `<div class="mt-2 d-flex align-items-center" id="${_time}">
                                    <input class="form-control" name="${el}" list="${_time + 1}" placeholder="Thể Loại">
                                    <datalist id="${_time + 1}">
                                        ${option.join('')}
                                    </datalist>
                                    <span class="cursorAdd" onclick="removeElement(${_time})"><i class="fas fa-minus"></i></span>
                                </div>`;
    div.insertAdjacentHTML('beforeend', _html);
}

function removeElement(el) {
    var child = document.getElementById(el);
    child.parentNode.removeChild(child);
}

function removeImageDescriptionExists(el, publicid) {
    var child = document.getElementById(el);
    child.parentNode.removeChild(child);
    ImageDelete.push(publicid);
}

/*upload 1 video*/
function videoFileAsURL() {
    var fileSelected = document.getElementById('videoinput').files;
    if (fileSelected.length > 0) {
        var fileToLoad = fileSelected[0];
        var fileReader = new FileReader();
        fileReader.onload = function (fileLoaderEvent) {
            var srcData = fileLoaderEvent.target.result;
            var newvideo = document.createElement('video');
            var sourceMP4 = document.createElement("source");
            sourceMP4.type = "video/mp4";
            sourceMP4.src = srcData;
            newvideo.setAttribute('height', 'auto');
            newvideo.setAttribute('width', '100%');
            newvideo.controls = true;
            newvideo.appendChild(sourceMP4);
            document.getElementById('video').innerHTML = newvideo.outerHTML;
        }
        fileReader.readAsDataURL(fileToLoad);
    }
}

/*upload 1 ảnh*/
function ImagesFileAsURL() {
    var fileSelected = document.getElementById('avatarinput').files;
    if (fileSelected.length > 0) {
        var fileToLoad = fileSelected[0];
        var fileReader = new FileReader();
        fileReader.onload = function (fileLoaderEvent) {
            var srcData = fileLoaderEvent.target.result;
            var newImage = document.createElement('img');
            newImage.style.height = "auto";
            newImage.style.width = "300px"
            newImage.style.maxHeight = "450px";
            newImage.src = srcData;
            document.getElementById('avatar').innerHTML = newImage.outerHTML;
        }
        fileReader.readAsDataURL(fileToLoad);
    }
}


/*upload multi ảnh*/
$('.dandev_insert_attach').click(function () {
    if ($('.list_attach').hasClass('show-btn') === false) {
        $('.list_attach').addClass('show-btn');
    }
    var _lastimg = jQuery('.dandev_attach_view li').last().find('input[type="file"]').val();
    if (_lastimg != '') {
        var d = new Date();
        var _time = d.getTime();
        var _html = `<li id="li_files_${_time}" class="li_file_hide">
                                <div class="img-wrap">
                                <span class="close" onclick="DelImg(this)">x</span>
                                <div class="img-wrap-box"></div>
                                </div>
                                <div class="${_time}">
                                 <input type="file" name="imageDescription" class="hidden" onchange="uploadImg(this)" id="file_${_time}" multiple />
                                </div>
                                </li>`;
        jQuery('.dandev_attach_view').append(_html);
        jQuery('.dandev_attach_view li').last().find('input[type="file"]').click();
    } else {
        if (_lastimg == '') {
            jQuery('.dandev_attach_view li').last().find('input[type="file"]').click();
        } else {
            if ($('.list_attach').hasClass('show-btn') === true) {
                $('.list_attach').removeClass('.show-btn');
            }
        }
    }
});

function uploadImg(el) {
    let file_data = $(el).prop('files')[0];
    let fileToLoad = file_data;
    var fileReader = new FileReader();

    fileReader.onload = function (fileLoadEvent) {
        var srcData = fileLoadEvent.target.result;

        var newImage = document.createElement('img');
        newImage.src = srcData;
        var _li = $(el).closest('li');
        if (_li.hasClass('li_file_hide')) {
            _li.removeClass('li_file_hide');
        }
        _li.find('.img-wrap-box').append(newImage.outerHTML);
    }
    fileReader.readAsDataURL(fileToLoad);
}

function DelImg(el) {
    jQuery(el).closest('li').remove();
}

/*end muilti ảnh*/