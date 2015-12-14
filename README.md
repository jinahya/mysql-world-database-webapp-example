# mysql-world-database-webapp-example

## the world database
* <a href="https://dev.mysql.com/doc/world-setup/en/">Setting Up the world Database</a>
* <a href="https://dev.mysql.com/doc/index-other.html">Other MySQL Documentation</a>

## resources
three root resource classes defined in `com.github.jinahya.example.mysql.world.ws.rs` package.

|path                                          |description|
|----------------------------------------------|-----------|
|`/api/world.xsd`                                  |reads XML Schema|
|`/api/cities;countryCode=`                        |reads all cities|
|`/api/cities/{id: \\d+}`                          |reads a city whose id matches to given|
|`/api/countries`                                  |reads all countries|
|`/api/countries/{code: [A-Z]{3}}`                 |reads a country whose code matches to given|
|`/api/countries/{code: [A-Z]{3}}/cities`          |reads all cities of a specific country|
|`/api/countries/{code: [A-Z]{3}}/countryLanguages`|reads all countryLanguages of a specific country|
|`/api/countryLanguages;countryCode=`              |reads all countryLanguages|

## swagger-ui

|path                                          |description|
|----------------------------------------------|-----------|
|`/swagger-ui`                                 ||

## deploying to an existing application server
use your own configured value for `-Djta-data-source` parameter.
````
$ mvn -Djta-data-source=jdbc/worldDS clean package
````

## launching with an embedded-glassfish
use your own values for those `-Djdbc.xxx` paramters.
````
$ mvn -Pembedded-glassfish \
-Djdbc.url=jdbc:mysql://address:port/database \
-Djdbc.user=user \
-Djdbc.password=password \
clean package embedded-glassfish:run
````
browse following url.
```
http://localhost:8080/world/api/...
http://localhost:8080/world/swagger-ui
```
