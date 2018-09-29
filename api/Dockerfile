FROM openjdk:8-jre-alpine
MAINTAINER hiper2d <hiper2d@gmail.com>

COPY build/libs/api-1.0.jar /api.jar
EXPOSE 80

ENTRYPOINT exec java -jar api.jar