# showcase-backend

Welcome to the backend app of my showcase!

This is a Java 20 and Spring Boot 3 REST API to showcase-frontend app (https://github.com/fndribeiro/showcase-frontend).

## stack

- Spring Oauth2 Client to handle social logins.
- Spring Security for API protection. Stateless session management with JWT authentication.
- Spring Web for REST API.
- Spring Doc Open API for documentation.
- Spring Data MongoDB for database operations.

  
## code-design

![image](https://github.com/fndribeiro/showcase-backend/assets/45038374/2b1cd0e9-74ac-4346-ae2e-96c9bc370da1)

## deployment

#### application

Free tier webservice deployment with Dockerfile from https://dashboard.render.com/.

Available at: https://showcase-backend-zojn.onrender.com/docs

#### database

Free tier Cloud Mongo DB from https://cloud.mongodb.com/.

## authentication-worflow

![image](https://github.com/fndribeiro/showcase-backend/assets/45038374/3907eb53-876b-47ff-bdfb-c1cf814f8347)

## setup

If you wish to run this application locally, the following properties must be provided in application.yaml. After all properties are set, start backend server by running ShowcaseBackendApplication.java.

#### frontendBaseUrl
Frontend URL that backend will redirect user after success authentication. You can set http://localhost:3000/ if running local React app or any unprotected endpoint from backend.

#### tokenSecret
Secret used to sign JWT. Provide any value you want.

#### clientId
Google client ID for Oauth2 authentication. This must be generated in your own GCP console. Just follow any tutorial for social login with Google to learn how to generate this.

#### clientSecret
Google client secret Oauth2 authentication. Generate in the same step as clientId.

#### uri
Mongo DB connection URI. Must create a new cluster in Cloud Mongo DB to generate new credentials.

