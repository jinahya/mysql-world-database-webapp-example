# mysql-world-database-webapp-example

## the world database
* <a href="https://dev.mysql.com/doc/world-setup/en/">Setting Up the world Database</a>
* <a href="https://dev.mysql.com/doc/index-other.html">Other MySQL Documentation</a>

## JPA/JAXB
Three classes bound in `com.github.jinahya.example.mysql.world.persistence` package.
* `[City](src/main/java/com/github/jinahya/example/mysql/world/persistence.City)`
* `Country`
* `CountryLanguage`

## EJB
Two classes defined in `com.github.jinahya.example.mysql.world.ejb` package.

## JAX-RS
Three root resource classes defined in `com.github.jinahya.example.mysql.world.ws.rs` package.

|path                                          |description|
|----------------------------------------------|-----------|
|`/cities;countryCode=`                        |reads all cities|
|`/cities/{id: \\d+}`                          |reads a city whose id matches to given|
|`/countries`                                  |reads all countries|
|`/countries/{code: [A-Z]{3}}`                 |reads a country whose code matches to given|
|`/countries/{code: [A-Z]{3}}/cities`          |reads all cities of a specific country|
|`/countries/{code: [A-Z]{3}}/countryLanguages`|reads all countryLanguages of a specific country|
|`/countryLanguages;countryCode=`              |read all countryLanguages|

## deploying to an existing application server
Use your own configured value for `-Djta-data-source` parameter.
````
$ mvn -Djta-data-source=jdbc/worldDS
````

## launching with an embedded-glassfish
Use your own values for those `-Djdbc.xxx` paramters.
````
$ mvn -Pembedded-glassfish \
-Djdbc.url=jdbc:mysql://address:port/database \
-Djdbc.user=user \
-Djdbc.password=password \
clean package embedded-glassfish:run
````
