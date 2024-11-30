## Spring app created from an angular front-end with mockoon exigences

To start the project :

- Clone the repository <br />
```console
git clone https://github.com/gdaume24/Portail-location.git
```

- Set your secret variables in a .env file in the root of the project :
  - SPRING_DATASOURCE_URL with those settings at the end : ?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true
  - SPRING_DATASOURCE_USERNAME
  - SPRING_DATASOURCE_PASSWORD
  - SECURITY_JWT_SECRET_KEY
  - SECURITY_JWT_EXPIRATION_TIME

- Start mysql database with docker :
```console
docker-compose up --build
```

- Start Spring application : 
Go in Back/src/main/java/com/rentals and right clic on RentalPortalApplication.java -> debug java 

- Start angular application in another terminal :
```console
cd Front
ng serve
```

You will now be able to use the application on [http://localhost:4200](http://localhost:4200/)

La documentation des root de l'application Spring est disponible à l'url suivante : [http://localhost:3001/api/swagger-ui/index.html](http://localhost:3001/api/swagger-ui/index.html)


