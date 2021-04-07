# Codeup-SpringBlog

![enter image description here](https://lh3.googleusercontent.com/HZSjiw-PbcqSTZpMy3ZwSBGHL1J6iKh_pdFLQfoo63f36H2eg96pPYyNnRge9VWSakitnXC_9E8VE1K_NEw2rLo6q6H0bZvJ6nZ1xneFmRoyUwQboJVWs2FvQsZR3ZizkUUm3K1qO55zVjCmZXg2r0TzC0aYX7DNdW7HLKy1vPFVvqvY8zEsnCGZFkc7DguvzFjqosNpDLJjq1g5QQHU5-WLEinPivKcVa0OF22j4f3Ol3H5xIVVNhYpb2FQNPBLMQnEM1DuaYskfxKkq4bSFcooUvo46x_ww6OIXGJy9K8o4xfMOdmWLmpv8PXX5do83VsUEtUU1HYn63qYF_R_pubLcCbL4tZ6xaf-yxqX-pcCEf9-1dldXQXc1QwepzatZhNiguSFYg6KzThnagkwMQxmg_d0Oc4aq26edI2Bs8HPU-e2sxflY8Hj9sXyGqVo2LB-hy1Fjfniybwf1wDcQk_SzQkii-fcUeB8GX1jTGocL2wuBS6bMZz_ETJs8w4wCQfGa0HbxTrtQlev1J27NCllHoW7c77TEwBgtMJheA0meb34smMH0I97s6pda5kyAwebgRJ1lTfJLth8D_Z3AfApBG8E-R_LeWkiflAp3vfguaUlTLa8s2cIwn8X0rxwMW0qsyCvx_3nl6whzsXNXzhfJ6I-FHPKJiYuzXbCuPobAQcQ-Ojl5QRT9rtbawdPSxFyDJf1noRwolkbd_bcXw=w980-h725-no?authuser=0)
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



