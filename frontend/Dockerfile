FROM openjdk:8-jre-alpine
MAINTAINER hiper2d <hiper2d@gmail.com>

ARG PORT

VOLUME /tmp

COPY build/libs/frontend-1.0.jar /frontend.jar
EXPOSE $PORT

ENTRYPOINT exec java -jar frontend.jar