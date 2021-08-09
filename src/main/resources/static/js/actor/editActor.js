let editor;
ClassicEditor
    .create( document.querySelector( '#biography' ) )
    .then( newEditor => {
        editor = newEditor;
    } )
    .catch( error => {
        console.error( error );
    } );

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

$("#btnadd").click( async  function (e) {

    e.preventDefault();
    TurnOnLoading();
    let biography = editor.getData();
    let formData = $('#formSubmit')[0];
    let data = new FormData(formData);
    data.append("biography", biography);
    if(data.get("id") != null){
        await updateActor(data);
        window.location.href = "/actor/detail/" + data.get("id");
    }else{
        await addActor(data);
        setvalueDefaul();
    }
})

function setvalueDefaul(){
    var newImage = document.createElement('img');
    newImage.style.height = "auto";
    newImage.style.width = "300px"
    newImage.style.maxHeight = "350px";
    newImage.src = "https://via.placeholder.com/540x500";
    document.getElementById('avatar').innerHTML = newImage.outerHTML;
    editor.setData("");
    document.getElementById("name").value = '';
}

async function updateActor(data) {
    try{
        return new Promise((resolve, reject) => {
            axios.post(
                "/api/edit-actor", data, {
                    headers: {
                        "Content-Type": "multipart/form-data"
                    }
                }
            ).then(function (response) {
                TurnOfLoading();
                Swal.fire({
                    icon: 'success',
                    title: '',
                    text: 'Cập nhật Diễn Viên Thành Công!',
                    timer: 1000
                });
                return resolve(response);
            }).catch(function (error) {
                TurnOfLoading();
                Swal.fire({
                    icon: 'error',
                    title: '',
                    text: 'Cập nhật Diễn Viên Thất Bại!',
                    timer: 1000
                })
                return reject(error);
            })
        });
    }catch (error){
        alert(error);
    }
}

async function addActor(data) {
    axios.post(
        "/api/edit-actor", data,{
            headers: {
                "Content-Type": "multipart/form-data"
            }
        }
    ).then(function (response){
        TurnOfLoading();
        Swal.fire({
            icon: 'success',
            title: '',
            text: 'Thêm Diễn Viên Thành Công!',
            timer: 1000
        })
    }).catch(function (error){
        TurnOfLoading();
        Swal.fire({
            icon: 'error',
            title: '',
            text: 'Thêm Diễn Viên Thất Bại!',
            timer: 1000
        })
    })
}