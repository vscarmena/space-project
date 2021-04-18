FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} service.jar
ENTRYPOINT ["java", "-jar", "service.jar"]