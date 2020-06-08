FROM maven:3.6-jdk-8 as builder

WORKDIR /usr/src/app
COPY . .

RUN mvn -f pom.xml -B package



FROM openjdk:8-jdk-alpine as runner

WORKDIR /usr/src/app
COPY --from=builder /usr/src/app/cloud-eureka/target /usr/src/app/target

EXPOSE 8761

CMD [ "java", "-jar", "./target/cloud-eureka-0.0.1-SNAPSHOT.jar" ]
