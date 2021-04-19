FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
RUN apk --no-cache add curl
RUN curl -q -L -C - -b "oraclelicense=accept-securebackup-cookie" -o /tmp/jce_policy-8.zip -O http://download.oracle.com/otn-pub/java/jce/8/jce_policy-8.zip \
        && unzip -oj -d /opt/java/openjdk/lib/security /tmp/jce_policy-8.zip \*/\*.jar \
        && rm /tmp/jce_policy-8.zip
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} config-server.jar
ENTRYPOINT ["java", "-jar", "config-server.jar"]