FROM openjdk:8-jdk-alpine as runner

WORKDIR /usr/src/app
COPY ./cloud-eureka/target /usr/src/app/target

EXPOSE 8761

CMD [ "java", "-jar", "./target/cloud-eureka-0.0.1-SNAPSHOT.jar" ]
