# OxaAuth Microservice API [Backend]

is an API microservice based on Spring Boot and is used to log in / authenticate new users and to edit the customers.
___

## This API includes features like:

- WebFlux
- r2dbc
- Flyway-Migration
- MySql
- Postgres
- Testcontainers
- Spring Security
- JsonWebToken
- Passay-Library for password validation
- Commons Validator for email-send, fileupload, crypto and so on...
- Messages for translation
- Lombok
- Mapstruct
- Swagger 3
- Logback
- Mockito
- Jacoco
- JavaMail
- Thymeleaf
- oAuth2

___

## Swagger 3 API description:

- swagger-ui custom path: [/swagger](http://localhost:8083/api/swagger)
- api-docs endpoint custom path: [/api-docs](http://localhost:8083/api/api-docs)

___

## OAuth2 login-links (still by development):

- [GET] http://localhost:8083/oauth2/authorization/google <b>(with Google)</b>
- [GET] http://localhost:8083/oauth2/authorization/facebook <b>(with Facebook)</b>

___

## API requests:
look at [Postman Collection](webfluxsecurity.postman_collection.json)

- [POST] http://localhost:8083/api/v1/auth/register <b>(Create new user)</b>

```
{
    "first_name": "Ivan",
    "last_name": "Hahn",
    "email": "proba8@proba.com",
    "password": "12345$aA", // musst have min. 8 chars, one upper-case char, one lower-case char, one digit, one symbol
    "confirm_password": "12345$aA"
}
```
	
- [POST] http://localhost:8083/api/v1/auth/login <b>(LogIn for to get a bearer token)</b>

```
{
    "email": "proba@proba.com",
    "password": "12345$aA"
}
```

- [GET] http://localhost:8083/api/v1/profile/info <b>(Get users info after login)</b>


- [PUT] http://localhost:8083/api/v1/profile/update <b>(Update main info about himself after login)</b>

```
{
    "title": "Herr",
    "first_name": "Fritz2",
    "last_name": "Stöer",
    "email": "proba@proba2.com"
}
```
	
___


## Database structure
look at [ER Diagramm](oxaauth_ER_Diagramm.png)

### Table users ( WebShop's users table )

```
Field     |Type         |Null|Key|Default          |Extra                                        |
----------+-------------+----+---+-----------------+---------------------------------------------+
id        |binary(16)   |NO  |PRI|                 |                                             |
email     |varchar(64)  |NO  |UNI|                 |                                             |
password  |varchar(2048)|NO  |   |                 |                                             |
phone     |varchar(25)  |YES |   |                 |                                             |
provider  |varchar(25)  |YES |   |                 |                                             |
user_role |varchar(32)  |NO  |   |USER             |                                             |
first_name|varchar(64)  |NO  |   |                 |                                             |
last_name |varchar(64)  |YES |   |                 |                                             |
title     |varchar(32)  |YES |   |                 |                                             |
enabled   |tinyint(1)   |NO  |   |0                |                                             |
created_at|timestamp    |NO  |   |CURRENT_TIMESTAMP|DEFAULT_GENERATED                            |
updated_at|timestamp    |NO  |   |CURRENT_TIMESTAMP|DEFAULT_GENERATED on update CURRENT_TIMESTAMP|
```

### Table organizations ( WebShop's organizations table )

```
Field     |Type           |Null|Key|Default          |Extra                                        |
----------+---------------+----+---+-----------------+---------------------------------------------+
id        |bigint unsigned|NO  |PRI|                 |auto_increment                               |
org_name  |varchar(64)    |NO  |   |                 |                                             |
email     |varchar(64)    |YES |   |                 |                                             |
phone     |varchar(25)    |YES |   |                 |                                             |
enabled   |tinyint(1)     |NO  |   |1                |                                             |
created_at|timestamp      |NO  |   |CURRENT_TIMESTAMP|DEFAULT_GENERATED                            |
updated_at|timestamp      |NO  |   |CURRENT_TIMESTAMP|DEFAULT_GENERATED on update CURRENT_TIMESTAMP|
```

### Table users_organizations ( Many-to-many connection between users and organizations ) 

```
Field        |Type           |Null|Key|Default|Extra|
-------------+---------------+----+---+-------+-----+
user_id      |binary(16)     |NO  |PRI|       |     |
org_id       |bigint unsigned|NO  |PRI|       |     |
user_org_role|varchar(32)    |YES |   |       |     |
```

### Table addresses ( WebShop's addresses table of organizations )

```
Field        |Type           |Null|Key|Default          |Extra                                        |
-------------+---------------+----+---+-----------------+---------------------------------------------+
id           |bigint unsigned|NO  |PRI|                 |auto_increment                               |
org_id       |bigint unsigned|YES |MUL|                 |                                             |
title_name   |varchar(64)    |YES |   |                 |                                             |
alias_name   |varchar(64)    |YES |   |                 |                                             |
street_name  |varchar(70)    |YES |   |                 |                                             |
house_number |varchar(9)     |YES |   |                 |                                             |
district_name|varchar(200)   |YES |   |                 |                                             |
city_name    |varchar(200)   |NO  |   |                 |                                             |
zip_code     |varchar(9)     |NO  |   |                 |                                             |
state_name   |varchar(75)    |YES |   |                 |                                             |
country_code |char(2)        |NO  |MUL|                 |                                             |
email        |varchar(64)    |YES |   |                 |                                             |
phone        |varchar(25)    |YES |   |                 |                                             |
created_at   |timestamp      |NO  |   |CURRENT_TIMESTAMP|DEFAULT_GENERATED                            |
updated_at   |timestamp      |NO  |   |CURRENT_TIMESTAMP|DEFAULT_GENERATED on update CURRENT_TIMESTAMP|
```

### Table countries ( List of all available countries with phone-prefix for addresses )

```
Field       |Type        |Null|Key|Default|Extra|
------------+------------+----+---+-------+-----+
country_code|char(2)     |NO  |PRI|       |     |
country_name|varchar(200)|YES |   |       |     |
phone_prefix|varchar(10) |YES |   |       |     |
enabled     |tinyint(1)  |NO  |   |1      |     |
```
