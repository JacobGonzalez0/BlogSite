let text = document.getElementById("text")
let title = document.getElementById("title")
let file = document.getElementById("file")

function postUpload(){
    var data = new FormData();
    data.append("text", text.value);
    data.append("title", title.value);
    data.append("image", file.files[0])
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/post/edit/" + postid);
    xhr.send(data); 
    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log("success!")
            console.log(this.responseText)
        }
    };
}
let postid = document.getElementById("postid").value;
document.getElementById("postButton").addEventListener("click", function(event){
    event.preventDefault()
    postUpload()
});
