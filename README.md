# GameStore

Web application for game store.

API avalible at: https://gamestore-383516.uc.r.appspot.com/view/

API for admin avalible via Postman with username: admin | password: admin
## Tools & Frameworks

**Used technologies**
### Back
* Java
* Spring Boot
* Hibernate
* Maven
* H2 Database

### Other
* JUnit
* Lombok
* Git

### Hosting 
* Google Cloud

### Front End
* HTML
* CSS
* Thymeleaf

# API

### Guest

API                               | Description                     | URL
----------------------------------|---------------------------------|--------------
GET /api/guest/games              | Get all games                   | GET [/api/guest/games](https://gamestore-383516.uc.r.appspot.com/api/guest/games)
GET /api/guest/games/{id}         | Get a game by ID                | GET [/api/guest/games/1](https://gamestore-383516.uc.r.appspot.com/api/guest/games/1)
GET /api/guest/games/cheapest     | Get all games, cheapest first   | GET [/api/guest/games/cheapest](https://gamestore-383516.uc.r.appspot.com/api/guest/games/cheapest)
GET /api/guest/games/title/{title}| Get all games with given title  | GET [/api/guest/games/title/{title}](https://gamestore-383516.uc.r.appspot.com/api/guest/games/title/Cyberpunk)
GET /api/guest/games/genre/{genre}| Get all games from given genre  | GET [/api/guest/games/genre/{genre}](https://gamestore-383516.uc.r.appspot.com/api/guest/games/genre/RPG)

### Admin

API                               | Description                     | URL
----------------------------------|---------------------------------|--------------
DELETE /api/admin/games/{id}      | Delete game with given id       | DELETE https://gamestore-383516.uc.r.appspot.com/api/admin/games/1
POST /api/admin/newGame           | Add a new game                  | POST [https://gamestore-383516.uc.r.appspot.com/api/admin/newGame](https://gamestore-383516.uc.r.appspot.com/api/admin/newGame)
PUT /api/admin/editGame/{id}      | Edit existing game              | PUT [https://gamestore-383516.uc.r.appspot.com/api/admin/editGame/2](https://gamestore-383516.uc.r.appspot.com/api/admin/editGame/2)
