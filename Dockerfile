FROM openjdk:11
EXPOSE 8080
ADD target/codeup_demo-0.0.1-SNAPSHOT.jar codeup_demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java", "-jar", "/codeup_demo-0.0.1-SNAPSHOT.jar" ]