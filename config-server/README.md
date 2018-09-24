# Config Server

Spring Cloud configuration server.

## Endpoints and Security

Actuator endpoints are not protected so you can access them as a guest. Try http://localhost:9000/actuator/health for example.

If you are accessing a protected endpoint such as `GET http://localhost:9000/api/local` or `POST http://localhost:9000/refresh` you will need to provide valid user credentials (configured in bootstrap.yml).

## Screenshot

![Google Chrome](../screenshots/config-server.png)
