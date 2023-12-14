# OxaTrade Spring Project [Backend]

 There is a prototype of eCommerce REST API and can be used with any type of project that needs products, users, categories, authentication, and users in JSON format.
___

## This API includes features like:

- WebFlux
- oAuth2
- Spring Security
- Flyway-Migration
- r2dbc
- Mapstruct
- JsonWebToken
- Passay-Library for password validation
- Commons Validator for email-send, fileupload, crypto and so on...

___

## OAuth2 login-links (still by development):

- [GET] http://localhost:8083/oauth2/authorization/google <b>(with Google)</b>
- [GET] http://localhost:8083/oauth2/authorization/facebook <b>(with Facebook)</b>
___

## API requests:

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
    "email": "proba8@proba.com",
    "password": "12345$aA"
	}
```

- [GET] http://localhost:8083/api/v1/profile/info <b>(Get users info after login)</b>


- [POST] http://localhost:8083/api/v1/profile/update <b>(Update main info about himself after login)</b>

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
(look at oxatrade.png)

### Table users ( WebShop's users table )

```
Field     |Type           |Null|Key|Default          |Extra                                        |
----------+---------------+----+---+-----------------+---------------------------------------------+
id        |bigint unsigned|NO  |PRI|                 |auto_increment                               |
email     |varchar(64)    |NO  |UNI|                 |                                             |
password  |varchar(2048)  |NO  |   |                 |                                             |
user_role |varchar(32)    |NO  |   |USER             |                                             |
first_name|varchar(64)    |NO  |   |                 |                                             |
last_name |varchar(64)    |YES |   |                 |                                             |
title     |varchar(32)    |YES |   |                 |                                             |
enabled   |tinyint(1)     |NO  |   |0                |                                             |
created_at|timestamp      |NO  |   |CURRENT_TIMESTAMP|DEFAULT_GENERATED                            |
updated_at|timestamp      |NO  |   |CURRENT_TIMESTAMP|DEFAULT_GENERATED on update CURRENT_TIMESTAMP|
```

### Table addresses ( WebShop's addresses table of users or organizations )

```
Field        |Type           |Null|Key|Default          |Extra                                        |
-------------+---------------+----+---+-----------------+---------------------------------------------+
id           |bigint unsigned|NO  |PRI|                 |auto_increment                               |
street_name  |varchar(70)    |YES |   |                 |                                             |
house_number |varchar(9)     |YES |   |                 |                                             |
district_name|varchar(200)   |YES |   |                 |                                             |
city_name    |varchar(200)   |NO  |   |                 |                                             |
zip_code     |varchar(9)     |NO  |   |                 |                                             |
state_name   |varchar(75)    |YES |   |                 |                                             |
country_id   |int unsigned   |NO  |MUL|                 |                                             |
email        |varchar(64)    |YES |   |                 |                                             |
phone        |varchar(25)    |YES |   |                 |                                             |
creator      |bigint unsigned|NO  |   |                 |                                             |
org_id       |bigint unsigned|YES |   |                 |                                             |
created_at   |timestamp      |NO  |   |CURRENT_TIMESTAMP|DEFAULT_GENERATED                            |
updated_at   |timestamp      |NO  |   |CURRENT_TIMESTAMP|DEFAULT_GENERATED on update CURRENT_TIMESTAMP|
```

### Table countries ( List of all available countries with phone-prefix for addresses )

```
Field       |Type        |Null|Key|Default|Extra         |
------------+------------+----+---+-------+--------------+
id          |int unsigned|NO  |PRI|       |auto_increment|
country_code|char(2)     |NO  |   |       |              |
country_name|varchar(200)|YES |   |       |              |
phone_prefix|varchar(10) |YES |   |       |              |
```

### Table organizations ( WebShop's organizations table )

```
Field     |Type           |Null|Key|Default          |Extra                                        |
----------+---------------+----+---+-----------------+---------------------------------------------+
id        |bigint unsigned|NO  |PRI|                 |auto_increment                               |
org_name  |varchar(64)    |NO  |   |                 |                                             |
address   |bigint unsigned|YES |   |                 |                                             |
email     |varchar(64)    |YES |   |                 |                                             |
phone     |varchar(25)    |YES |   |                 |                                             |
enabled   |tinyint(1)     |NO  |   |1                |                                             |
created_at|timestamp      |NO  |   |CURRENT_TIMESTAMP|DEFAULT_GENERATED                            |
updated_at|timestamp      |NO  |   |CURRENT_TIMESTAMP|DEFAULT_GENERATED on update CURRENT_TIMESTAMP|
```

### Table users_organizations ( Many-to-many connection between users and organizations ) 

```
Field  |Type           |Null|Key|Default|Extra|
-------+---------------+----+---+-------+-----+
user_id|bigint unsigned|NO  |PRI|       |     |
org_id |bigint unsigned|NO  |PRI|       |     |
```
