FROM openjdk:10-jre-slim
MAINTAINER hiper2d <hiper2d@gmail.com>

ARG PORT

VOLUME /tmp

COPY build/libs/service-discovery-1.0.jar /sd.jar
EXPOSE $PORT

ENTRYPOINT exec java -jar sd.jar