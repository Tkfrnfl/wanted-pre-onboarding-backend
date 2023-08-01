FROM openjdk:17-ea-33-jdk-buster AS builder
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

COPY --from=builder build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=prod", "/app.jar"]