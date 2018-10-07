ARG ELK_VERSION

FROM docker.elastic.co/logstash/logstash:${ELK_VERSION}
MAINTAINER hiper2d <hiper2d@gmail.com>

EXPOSE 5000 9600

RUN rm -f /usr/share/logstash/pipeline/logstash.conf
ADD pipeline/ /usr/share/logstash/pipeline/
ADD config/ /usr/share/logstash/config/