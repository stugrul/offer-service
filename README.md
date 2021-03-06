**Project Title**

Offer Service

**Description**

Offer Service allows merchants to sell offers. Application communicates using HTTP protocol.

**Getting Started**

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.


**Prerequisites**

The application requires Java 8 or higher. Rest of the application is self contained. 

**Installing**

Once the source code is checked out, run mvn clean install to download dependencies using the following command at the root directory of offer-service

./mvnw clean install

To run the application simply run it as Spring Boot application either using your IDE or running 

./mvnw spring-boot:run

By default the application runs on port 8082 in local environment

**Technologies**

The application is built in Java 8 with Spring Boot while benefiting Test Driven Development.

The application is using an H2 in memory database.

There are 5 endpoints available, all endpoints produces and consumes Json representations. (application/json) 

GET http://localhost:8082/offers

GET http://localhost:8082/offers/{id}

POST http://localhost:8082/offers

PUT http://localhost:8082/offers/{id}

DELETE http://localhost:8082/offers/{id}

API allows merchants to advertise, retrieve, update and delete offers.

Below is an Offer object which some of the endpoints consume

{
  "id": 1,
  "name": "Garfield Offer",
  "description": "This is a garfield toy offer",
  "price": 2,
  "currency": "EUR",
  "validUntil": "2018-10-10"
}

Date format needs to follow ISO LOCAL DATE format. (yyyy-MM-dd)

To advertise an offer POST an Offer object to http://localhost:8082/offers

To Retrieve all offers simply do GET http://localhost:8082/offers

To update, the app needs the offer id and the offer object itself, below is an example

PUT http://localhost:8082/offers/1

{
  "id": 1,
  "name": "Garfield Offer",
  "description": "This is a garfield toy",
  "price": 2,
  "currency": "EUR",
  "validUntil": "2018-10-10"
}

To delete simple send 
DELETE http://localhost:8082/offers/{id}

All fields are required and names should be unique.

**Built With**
Maven, 
Java 8,
Spring Boot 2.0.5.RELEASE,
Junit5,
Mockito 2.0,
Spring JPA Data,
Spring Rest Repositories,
H2 in memory Database
