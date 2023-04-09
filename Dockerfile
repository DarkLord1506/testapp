FROM openjdk:8
EXPOSE 8080
ADD build/libs/example-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]