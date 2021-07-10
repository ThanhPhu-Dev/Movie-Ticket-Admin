function ImagesFileAsURL() {
    var fileSelected = document.getElementById('poster').files;
    if (fileSelected.length > 0) {
        var fileToLoad = fileSelected[0];
        var fileReader = new FileReader();
        fileReader.onload = function (fileLoaderEvent) {
            var srcData = fileLoaderEvent.target.result;
            var newImage = document.createElement('img');
            newImage.style.width = "200";
            newImage.style.height = "200";
            newImage.src = srcData;
            document.getElementById('avatar').innerHTML = newImage.outerHTML;
        }
        fileReader.readAsDataURL(fileToLoad);
    }
}

