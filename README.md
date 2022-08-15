#Api Documentation
Swagger URL - http://http://192.168.1.102/:8082/swagger-ui/index.html#/

Project Title
Task Project using Multi module spring boot application with Postgres database
 Project Description 
Flight Amenity search service
APIs:

CRUD - User should be able to do all the CRUD operations
PAGINATION

The API should return the number of records for a requested page.
First page should be returned if no pages are requested.
A default number of records should be returned if the number of records are not mentioned.


SEARCH AMENITIES

User should be able to get the amenities for the provided flight number and cabin type from an EXTERNAL API based on seat availability.
A meaningful business error should be returned if no seats are available



EXTERNAL API:

Create a local instance of JSON server

 Steps & Project Structure 
1 - Create a spring boot project from this website https://start.spring.io/
2 - Create the multi modules structure for project

common package includes:

Entity
Dto
Enums
Exception
Repository
Mapper
Service


core package includes:

Services Implementations


Web package includes:

Controller
Main class



 Requirements 

install Postgres database
Install the Json local server

npm install -g json-server
Create the db.json file with some data (Amenities.json)
json-server --watch db.json
go to http://localhost:3000/amenities



Install Postman for call the APIs
