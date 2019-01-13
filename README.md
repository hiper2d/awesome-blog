Awesome blog
=============

[![TravisCI Build](https://travis-ci.org/hiper2d/awesome-blog.svg)](https://travis-ci.org/hiper2d/awesome-blog)

This is a blog site designed with microservices architecture using `Spring Cloud Netflix` features, `Kotlin` as a main server side language and `Angular` for user interface parts.

##### Client npm dependencies status:

[![dependencies Status](https://david-dm.org/hiper2d/awesome-blog/status.svg?path=client)](https://david-dm.org/hiper2d/awesome-blog?path=frontend/src/main/ng)
[![devDependencies Status](https://david-dm.org/hiper2d/awesome-blog/dev-status.svg?path=frontend/src/main/ng)](https://david-dm.org/hiper2d/awesome-blog?path=frontend/src/main/ng&type=dev)

##### Technology stack
* Spring Framework 5 with Webflux/Reactor and Netty server
* Spring Boot 2.0
* Spring Cloud Netflix 2.0
* Spring Webflux Security with JWT
* Kotlin 1.3
* Angular 7
* Gradle 5 with Kotlin Script and jUnit 5
* Docker

##### Prerequisites
1. Node 8+
2. Yarn
3. JRE 8+
4. Docker

## Microservices architecture:

![diagram](https://raw.githubusercontent.com/hiper2d/awesome-blog/master/docs/uml/services-diagram.png)

- **Config Server**: A Spring Boot application which provides configs (yml files) to all other services. Should be run first.
- **Service Discovery**: A Spring Boot application with embedded `Service Discovery Server` (Eureka server). Each other services except `Config Server` are registered in it and can access each other by names instead of host-port combination using `Api Gateway Router` service. Should be run second.
- **Frontend**: A Spring Boot application with Angular parts. Contains embedded `Api Gateway Router` (Zuul), `Client Side Load Balancer` (Ribbon) and `Service Discovery Client` (Eureka client) which help to redirect requests to Api service instances registered in `Service Discovery Server`.
- **API**: A Spring Boot application with backend information for the Frontend. Includes `Spring Security` parts to provide Json Web Tokens and validate them.

The diagram is created with the help of [draw.io](https://draw.io)

## How to run

First of all the project:
   
       ./gradlew clean build

This also creates a Docker image for every application

### Local environment

1. Set environment variables:

       SPRING_PROFILES_ACTIVE=local,native

2. Run Config Server instance:

       ./gradlew config-server:bootRun

   Check that it's running at http://localhost:9000/actuator/health

3. Run Service Discovery instance:

       ./gradlew service-discovery:bootRun

   Check that it's running at http://localhost:9001

4. Run API instance:

       ./gradlew api:bootRun

   Check that it's running at http://localhost:8081/api/echo

4. Run Frontend instance:

       ./gradlew frontend:bootRun

   Check that it's running at http://localhost:8082

### Docker compose

1. Remove containers from previous runs:

        docker-compose rm

2. Run everything:

        docker-compose up

   Check it running at http://localhost:8082

### Docker containers

0. Remove named containers from previous runs:

        docker rm {config,discovery,api,frontend}

1. Create a custom bridge network if it's not created:

        docker network create --driver bridge awesome-network
        
2. Run the config server container with mounted logs and configs directories:

        docker run --name config --net awesome-network -v $(pwd)/logs:/logs -v $(pwd)/configs:/configs hiper2d/config  
        
3. Run the service discovery container:

        docker run --name discovery --net awesome-network -v $(pwd)/logs:/logs hiper2d/discovery

4. Run the backend container:

        docker run --name api --net awesome-network -v $(pwd)/logs:/logs hiper2d/api
   
5. Run the frontend container exposing the 8082 port:

        docker run --name frontend --net awesome-network -p 8082:80 -v $(pwd)/logs:/logs hiper2d/frontend  

   Check it running at http://localhost:8082
        
### Elk Docker containers

        docker run --rm --name elasticsearch --net awesome-network -p 9200:9200 -p 9300:9300 docker.elastic.co/elasticsearch/elasticsearch:6.5.4
        docker run --rm --name kibana --net awesome-network -p 5601:5601 -v $(pwd)/elk/kibana/config/kibana.yml:/usr/share/kibana/config/kibana.yml docker.elastic.co/kibana/kibana-oss:6.5.4
        docker run --rm -it --name logstash --net awesome-network -p 5000:5000 -p 9600:9600 -v $(pwd)/elk/logstash/config/logstash.yml:/usr/share/logstash/config/logstash.yml -v $(pwd)/elk/logstash/pipeline:/usr/share/logstash/pipeline -v $(pwd)/logs:/logs docker.elastic.co/logstash/logstash:6.5.4
        
## Roadmap

- ~~add Docker Compose~~
- add Logstash, Elasticsearch, Kibana stack for advanced logging
- add Mongodb to the Api service
- start creating user interfaces
