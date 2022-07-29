FROM openjdk:8
EXPOSE 8080
ADD target/bi-integration-image-v1.jar bi-integration-image-v1.jar
ENTRYPOINT ["java","-jar","/bi-integration-image-v1.jar"]