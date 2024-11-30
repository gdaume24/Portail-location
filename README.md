Spring app created from an angular front.

To start the project :

Clone the repository
Open the project with your IDE
Set your secret variables in a .env in the root of the project :
SPRING_DATASOURCE_URL with those settings at the end : ?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
SECURITY_JWT_SECRET_KEY
SECURITY_JWT_EXPIRATION_TIME

Start mysql database with docker :
docker-compose up --build

Start Spring application : 
Go in Back/src/main/java/com/rentals and right clic on RentalPortalApplication.java -> debug java 

Start angular application in another terminal : 
cd Front
ng serve

You will now be able to use the application on localhost:4200



