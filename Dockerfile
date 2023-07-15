FROM gradle:7.2 AS build
USER root
WORKDIR /application
COPY . .
RUN gradle clean build -x test
RUN cp /application/build/libs/example-0.0.1-SNAPSHOT.jar myapp.jar

##Run Stage
FROM openjdk:8 as deploy
WORKDIR /application
EXPOSE 8080
COPY --from=build /application/ .
ENTRYPOINT ["java","-jar","/application/myapp.jar"]