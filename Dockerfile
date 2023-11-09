FROM amazoncorretto:19
COPY ./build/libs/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "app.jar"]