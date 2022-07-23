
# DATA WAREHOUSE

This is to analyze fx deals by accepting deals and persisting in DB. Validation is on fields to ensure correct data are persisted in the data.

Lombok is used for getter and setter

##  Run - Docker
```sh
docker-compose up
```
On the first run, it fails because mysql database is yet to download completely. please do not close it as it will reconnect to sync up with the db

## Project Run
#### How to run the project
- Go to application.yml in resources package and input your username and password for MYSQL

- Run this command
```sh
 mvn clean spring-boot:run
```

## PROJECT DOCUMENTATION

#### Technology Used
- SPRINGBOOT
- MYSQL
- DOCKER
- MAPSTRUCT

# Project Packages
#### Resource
- POST - /create/fx-deal - Create a fx deal
- POST - /fx-deals - Create list of fx deals
- GET - /fx-deal/{id} Get a fx deal by id

### Service and Impl
- The business logic is in the impl package. The service class shich is an interface implements the impl class.
- The FxDealDTO is also in the service package

#### Domain
- id - Unique id with Datatype: String-UUID
- from - ISO Currency from deal with Datatype: Currency
- to - ISO Currency to deal with Datatype: Currency
- dealTimeStamp - Instantaneous time with Datatype: Instant
- dealAmount - Amount with Datatype: BigDecimal

#### Logging
- Logging used to log errors for controllers, services and repositories, spring ASPECT was used for cross-cutting concerns

### Request body
{
"from": "NGN",
"to": "USD",
"dealTimestamp": 1645211796,
"dealAmount": 4345.24
}

### Success Response
```json
{
  "status": "Success",
  "message": "OPERATION SUCCESSFUL",
  "data": {
    "statusCode": 200,
    "message": ""
  }
}
```

### Error Response
```json
{
  "status": "Failed",
  "message": "An error has occurred at entity",
  "data": {
    "statusCode": 500,
    "message": ""
  }
}
```

TEST
- Unit test in the test folder
