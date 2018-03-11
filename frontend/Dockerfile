FROM openjdk:9-jre-slim
LABEL maintainer="hiper2d"

ARG JAR_NAME
ARG JAVA_OPTS
ARG PORT

VOLUME /tmp

ADD $JAR_NAME client.jar
ENV JAVA_OPTS=$JAVA_OPTS
EXPOSE $PORT

ENTRYPOINT exec java $JAVA_OPTS -jar /client.jar