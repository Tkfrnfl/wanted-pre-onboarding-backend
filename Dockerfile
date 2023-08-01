FROM eclipse-temurin:17-jre-alpine
RUN mkdir -p /ctk
COPY target/ctk_consumer-*.jar /ctk/ctk_consumer.jar
WORKDIR /ctk/
ENTRYPOINT java -jar ctk_consumer.jar