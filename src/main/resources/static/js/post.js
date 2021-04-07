class CommentRetriver{

    constructor(element, route){
        this.element = document.getElementById(element);
        this.element.innerHTML = '<img src="/img/loading.png" alt="Loading">'
        this.route = route;
        this.data = null;
        this.request = null;
        this.getData();
    }

    getData(){
        this.request = new XMLHttpRequest();
        this.request.onreadystatechange = ()=>{
            if (this.request.readyState == 4 && this.request.status == 200) {
                let data = JSON.parse(this.request.responseText);
                this.drawPost(data);
            }
        }
        this.request.open("GET", this.route, true);
        this.request.send();
    }

    drawPost(data){
        this.data = data;
        this.element.innerHTML = '';
        this.data.forEach( post =>{

            let card = document.createElement("div");
            card.setAttribute("class","card shadow m-2");
            card.style.maxWidth = "350px";

            let image = document.createElement("img");
            image.setAttribute("class","rounded w-100");

            if(post.images.length == 0){
                image.src = "https://via.placeholder.com/350x150"
            }else{
                image.src = post.images[0].url;
            }

            let content = document.createElement("div");
            content.setAttribute("class","p-2");

            let title = document.createElement("h2");
            title.innerHTML = post.title;

            let text = document.createElement("p");
            if(text.length >= 200){
                text.innerHTML = post.text.substring(1, 200) + "...";
            }else{
                text.innerHTML = post.text;
            }

            content.appendChild(title);
            content.appendChild(text);

            let footer = document.createElement("div");
            footer.setAttribute("class","d-flex justify-content-between align-items-center p-2")

            let dateOutput = document.createElement("div");
            dateOutput.innerHTML = new Date("2021-04-11T19:00").toLocaleDateString()

            let button = document.createElement("a");
            button.setAttribute("class","btn btn-primary")
            button.href = "/post/view/" + post.post_id;
            button.innerHTML = "Read More <i class='fas fa-arrow-right'></i> "

            


            footer.appendChild(dateOutput);

            if(document.getElementById("showDelete")){
                let deleteBtn = document.createElement("button");
                deleteBtn.setAttribute("class","btn btn-danger")
                deleteBtn.setAttribute("data-bs-toggle","modal");
                deleteBtn.setAttribute("data-bs-target","#deleteModal");
                deleteBtn.onclick = ()=>{
                    //changes modal button
                    document.getElementById("confirmDelete").addEventListener("click", function(event){
                        event.preventDefault();
                        var data = new FormData();
                        var xhr = new XMLHttpRequest();
                        xhr.open("POST", "/post/delete/" + post.post_id);
                        xhr.send(data); 
                        xhr.onreadystatechange = function() {
                            if (this.readyState == 4 && this.status == 200) {
                                new PostRetriver("posts", "/post/all")
                                console.log(this.responseText)
                            }
                        };
                    });
                    
                }
                deleteBtn.innerHTML = "<i class='far fa-trash-alt'></i> Delete"

                let editButton = document.createElement("a");
                editButton.setAttribute("class","btn btn-primary")
                editButton.href = "/post/edit/" + post.post_id;
                editButton.innerHTML = "<i class='far fa-edit'></i> Edit"

                footer.appendChild(editButton);
                footer.appendChild(deleteBtn);
            }
        
            footer.appendChild(button);
            
            card.appendChild(image);
            card.appendChild(content);
            card.appendChild(footer);

            this.element.appendChild(card);

        })
    }

    postUpload(route){
        var data = new FormData();
        var xhr = new XMLHttpRequest();
        xhr.open("POST", route);
        xhr.send(data); 
        xhr.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                console.log("success!")
                console.log(this.responseText)
            }
        };
    }

}

new PostRetriver("posts", "/post/all")