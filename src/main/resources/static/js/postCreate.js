function postUpload(){
    var data = new FormData();
    data.append("text", text.value);
    data.append("title", title.value);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/post/create");
    xhr.send(data); 
    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            updateFeed()
        }
    };
}

document.getElementById("postButton").addEventListener("click", function(event){
    event.preventDefault()
    postUpload()
});