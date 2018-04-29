# Booking RESTful API using MongoDB 

## Overview
This project implement the following process:
- Searching flights between two airports (origin and destination)
- Display all available flights (flight number, airline, dates and price)
- Select the best flight and fill traveller information
- Book the flight
- Pay for the flight using third party payment service.

## Application configuration
check application.properties file

## Swagger Documentation
check http://localhost:8086/swagger-ui.html

## Data Storage 
This project uses MongoDB as a data storage. 
- Three mongo repositories exist: 
    - Airline
    - Flights
    - Booking
each of those repositories have custom interface and implementation.

## Project structure
- Main Model Classes
- MongoDB repositories 
- Data Bulk Initiation (loading flights and airports from JSON data files)
- Restful Web API implementation 
- Exceptions models and handlers 
- Swagger Documentation Plugin 







