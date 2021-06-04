FROM openjdk:16
RUN mkdir -p /app/build/libs
WORKDIR /app
COPY build/libs/*.jar /app/build/libs
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "build/libs/boot-0.1.0.jar"]
