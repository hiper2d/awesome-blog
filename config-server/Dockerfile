FROM openjdk:8-jre-alpine
MAINTAINER hiper2d <hiper2d@gmail.com>

VOLUME /tmp

COPY build/libs/config-server-1.0.jar /config.jar
EXPOSE 80

ENTRYPOINT exec java -jar config.jar