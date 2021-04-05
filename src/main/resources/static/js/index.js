class PostRetriver{

    constructor(element, route){
        this.element = element;
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

    drawPost(){
        console.log(this.data);
    }

}

new PostRetriver("posts", "/post/all")