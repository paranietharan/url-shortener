# URL Shortener

## Overview
This is a Spring Boot project to shorten URLs and track analytics. Users can register, login, create short URLs, view their URLs, and see analytics on clicks.

## Requirements
* Java 17+
* Maven 3+
* Running on `http://localhost:8080`

## Build and Run
1. Clone the repository.
2. In a terminal, navigate to the project folder.
3. Run \`mvn clean install\`.
4. Run \`mvn spring-boot:run\`.
5. The server listens on port 8080.

## Authentication Endpoints
* **Signup**  
  **Method**: POST  
  **URL**: `http://localhost:8080/api/auth/public/register`  
  **Body (JSON)**:
```json
{
  "username": "string",
  "email": "string",
  "password": "string"
}
```

* ** Login**  
  **Method**: POST  
  **URL**: `http://localhost:8080/api/auth/public/login`  
  **Body (JSON)**:
```json
{
  "username": "string",
  "password": "string"
}
```

## URL Management Endpoints

* **Create Short URL**  
  **Method**: POST  
  **URL**: `http://localhost:8080/api/short-url`  
  **Headers**:
  - Authorization: Bearer {token}  
  **Body (JSON)**:
```json
{
  "url": "string"
}
```

* **Get All Short URLs**  
  **Method**: GET  
  **URL**: `http://localhost:8080/api/short-url`  
  **Headers**:
  - Authorization: Bearer {token}
  - Content-Type: application/json
  - Accept: application/json


## Redirection
* **Redirect to Original URL**  
  **Method**: GET  
  **URL**: `http://localhost:8080/r/{shortUrl}`  
  Redirects to the stored \`oringinalUrl\`.

## Notes
* Make sure to include the auth token in requests that require authorization.
* Ensure protocols (http:// or https://) are present in the stored URLs for proper redirection.