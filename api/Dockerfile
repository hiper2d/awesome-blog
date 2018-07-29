FROM openjdk:10-jre-slim
MAINTAINER hiper2d <hiper2d@gmail.com>

ARG PORT

VOLUME /tmp

COPY build/libs/api-1.0.jar /api.jar
EXPOSE $PORT

ENTRYPOINT exec java -jar api.jar