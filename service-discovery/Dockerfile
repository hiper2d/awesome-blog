FROM openjdk:8-jre-alpine
MAINTAINER hiper2d <hiper2d@gmail.com>

VOLUME /tmp

COPY build/libs/service-discovery-1.0.jar /discovery.jar
EXPOSE 80

ENTRYPOINT exec java -jar discovery.jar