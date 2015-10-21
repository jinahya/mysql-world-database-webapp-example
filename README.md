# mysql-world-database-webapp-example

## the world database

## resources paths
|path                                          |description|
|----------------------------------------------|-----------|
|`/cities;countryCode=`                        ||
|`/cities/{id: \\d+}`                          |select city whose id matches to given|
|`/countries`                                  ||
|`/countries/{code: [A-Z]{3}}`                 |select country whose code matches to given|
|`/countries/{code: [A-Z]{3}}/countryLanguages`|list all countryLanguages of a specific country|
|`/countries/{code: [A-Z]{3}}/cities`          |list all cities of a specific country|
|`/countryLanguages;countryCode=`              ||

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
