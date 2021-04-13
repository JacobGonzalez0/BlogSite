# Codeup-SpringBlog

![enter image description here](https://lh3.googleusercontent.com/koaJcKG4IfWdanP67cgL4-TgH3MGxoLKKM9m1XacdiWZ4VlFvaD1lL98YYoHzZqDzQn-jhwjzjCQpz9T1vAidrjIDMNDx4m_yWstgYO7kTbwt2uG4Ml0sErFDlZtALAaCamXASwTF9wxpKFeUOFsxnm_W5hP7OAX7AxvBQUdQ7KVO2GIjnyEL64rnt6OH90KJM2hBby8QaM9jjnpHLr1qQbzr8oAFt6kYTeGQz3V2aBruNZ21o8r6L54b8X21v6TDGWasnWQD-jMTX75vNax8DYhdCjG0j_9E7hrGdhy3JtA4mxUHTOduIxngz77BsPHpNenGTUtnaJIg6Mw0g9ZwdJ9ZdQuN3xO4w9sI6TTdkKfcg5bV4SHZMb3JShFlOG2MQTYyjvUrjPeWlt3MEiWBS11hgJYxnt3I1VSSiMLmfOVxKC9dTTWWgh04HLRlPURiq8raGozQqb3Lwvi7PBufYqBp1QYkIVeoDeStDd8TPKuxmLB1CbOgbweC-kllu3q6TCl_KbeOoaqThG1UAwF2vtVTCfyzMyebJ3QqBT1rRep7kpW8_UDpHsNya0B85Fq0vRXyLJjt5RrgrTqGB6LOqkIEDEm6h1rRlfYsuDA_uLh4fuILHkozgAsRIQ_a-u0rf5sXUwcg13EQ1R6q2ESFHv1MpgFMVXXHxQ5TTw8IONgmMey24xPxfOsbkuz-eFMsgcv3qTJzOiQkiAyh9CnTQ=w980-h725-no?authuser=0)
**Codeup-SpringBlog** is a blog CMS made from Springboot and MySQL.

[Live Site Here](https://jacob.serveblog.net/)

The premise of the project was the illustrate an understanding of the CRUD functionality using JPA and various models. This project contains 

 - Springboot
 - RestControllers
 - SpringSecurity
 - ES6 Classes and AJAX 

# Setup

**Codeup-SpringBlog** used Jenkins to Docker pipeline that built, tested and deployed using docker with environment variables. To get the docker container setup just setup a file named .env and have the following

    SPRING_DATASOURCE_URL=jdbc:mysql://<MySQL Server>/<Table>?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
    SPRING_DATASOURCE_USERNAME=<MySQL User>
    SPRING_DATASOURCE_PASSWORD=<MySQL Password>

After saving that file in the same directory, you can pull the docker container and use 

    docker run --env-file=.env jacobgonzalez0/codeup-springblog
If you need to run the application on a different port be sure to include `-p targetPort:8080` before specifying the container name



