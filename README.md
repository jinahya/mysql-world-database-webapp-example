# mysql-world-database-webapp-example

## the world database

## resources paths
|path|
|----|
|`/countries`    |
|`/countries/{code: [A-Z]{3}}`|
|`/countries/{code: [A-Z]{3}}/countryLanguages`|
|`/countries/{code: [A-Z]{3}}/cities`|
|`/cities`|
|`/countryLanguages`|

## deploying to an existing application server
````
$ mvn -Djta-data-source=jdbc/worldDS
````

## using embedded-glassfish
````
$ mvn -Pembedded-glassfish \
-Djdbc.url=jdbc:mysql://address:port/database \
-Djdbc.user=user \
-Djdbc.password=password \
clean package embedded-glassfish:run
````
