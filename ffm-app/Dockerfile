FROM balenalib/armv7hf-debian-openjdk:10-jdk-sid-run
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
