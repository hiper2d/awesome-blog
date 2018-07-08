# API

Backend application for providing REST endpoints. It's secured by Spring OAuth2 and requires the `Authorization` header for each HTTP call. It uses `Webflux` router functions instead of plain old controllers but I didn't manage to get rid of servlets because Spring OAuth2 doesn not support Webflux for now.