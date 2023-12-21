Clone repository
```
  git clone https://github.com/BartlomiejZaczek/weather-api.git
```
Build project with maven
```
  mvn clean install
```
Run application
```
  java -jar target/weather-api.jar
```
Application is run on default port 8080

Weatherbit Forecast API key should be set in application.properties file<br /><br />
Available locations are located at src/main/properties in locations.csv. Locations may be modified starting from new line as follows: CITY,LATITUDE,LONGITUDE<br />
Geographical coordinates are taken in account only when city is not valid<br /><br />
Endpoints are accessible through Swagger documentation at endpoint: ```http://localhost:8080/api/docs```
