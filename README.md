#Api Documentation
Swagger URL - http://http://192.168.1.102/:8082/swagger-ui/index.html#/

APIs:
- CRUD - User should be able to do all the CRUD operations
- PAGINATION
    - The API should return the number of records for a requested page.
    - First page should be returned if no pages are requested.
    - A default number of records should be returned if the number of records are not mentioned.
- SEARCH AMENITIES
    - User should be able to get the amenities for the provided flight number and cabin type from an EXTERNAL API based on seat availability.
    - A meaningful business error should be returned if no seats are available

Install the Json local server
    
    - npm install -g json-server
    - Create the db.json file with some data (Amenities.json)
    - json-server --watch db.json
    - go to http://localhost:3000/amenities    

