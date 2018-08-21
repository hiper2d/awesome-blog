Awesome blog
=============

[![TravisCI Build](https://travis-ci.org/hiper2d/awesome-blog.svg)](https://travis-ci.org/hiper2d/awesome-blog)

This is a blog site designed with microservices architecture using `Spring Cloud Netflix` features, `Kotlin` as a main server side language and `Angular` for user interface parts.

##### Client npm dependencies status:

[![dependencies Status](https://david-dm.org/hiper2d/awesome-blog/status.svg?path=client)](https://david-dm.org/hiper2d/awesome-blog?path=frontend/src/main/ng)
[![devDependencies Status](https://david-dm.org/hiper2d/awesome-blog/dev-status.svg?path=frontend/src/main/ng)](https://david-dm.org/hiper2d/awesome-blog?path=frontend/src/main/ng&type=dev)

##### Technology stack
* Spring Framework 5 with Webflux/Reactor
* Spring Boot 2.0
* Spring Cloud Netflix 2.0
* Spring Webflux Security with JWT
* Kotlin 1.2
* Angular 6
* Gradle 4.9 with Kotlin Script and jUnit 5
* Docker

##### Prerequisites
1. Node 8+
2. Yarn
3. JRE 8+
4. Docker

## Microservices architecture:

![diagram](https://raw.githubusercontent.com/hiper2d/awesome-blog/master/uml/services-diagram.png)

- **Config Server**: A Spring Boot application which provides configs (yml files) to all other services. Should be run first.
- **Service Discovery**: A Spring Boot application with embedded `Service Discovery Server` (Eureka server). Each other services except `Config Server` are registered in it and can access each other by names instead of host-port combination using `Api Gateway Router` service. Should be run second.
- **Frontend**: Spring Boot application with Angular parts. Contains embedded `Api Gateway Router` (Zuul), `Client Side Load Balancer` (Ribbon) and `Service Discovery Client` (Eureka client) which help to redirect requests to Api service instances registered in `Service Discovery Server`.
- **Api**: Spring Boot application with backend information for the Frontend. Includes `Spring Security` parts to provide Json Web Tokens and validate them.
