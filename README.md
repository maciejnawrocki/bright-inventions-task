# Bright Inventions Recruitment Task

Bright Inventions Recruitment Task

Content of the task can be found [here](TASK.md) 

### Prerequisites

```
* Java 11
* docker
* docker-compose(optional)
```

## Getting Started

### Database

Application requires MySql database.

Navigate to the root of the project via command line and execute the command to use [docker-compose.yml](docker-compose.yml):
```
docker-compose up
```
or run command:
```
docker run --name myapp-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=demo -e MYSQL_USER=sa -e MYSQL_PASSWORD=password -d mysql:8.0 
```
### Application

Navigate to the root of the project via command line and execute the command:
```
mvn spring-boot:run
```

## Testing

### Swagger

Application has build in interactive [Swagger](https://swagger.io/docs/open-source-tools/swagger-ui/usage/installation/) documentation.

it can be accessed by [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### curl

Example curl methods:

* get all books
```
curl -X GET "http://localhost:8080/books?page=0&size=50"
```

* add new book
```
curl -X POST "http://localhost:8080/books"  -H "Content-Type: application/json" -d "{ \"author\": \"Robert Cecil Martin\", \"isbn\": \"9780132350884\", \"name\": \"Clean Code\", \"pages\": 464, \"rating\": 5}"
```

* edit book
```
curl -X PUT "http://localhost:8080/books/1" -H "Content-Type: application/json" -d "{ \"author\": \"Robert Cecil Martin\", \"isbn\": \"9780132350884\", \"name\": \"Clean Code\", \"pages\": 464, \"rating\": 5}"
```

* delete book
```
curl -X DELETE "http://localhost:8080/books/1"
```

* add comment
```
curl -X POST "http://localhost:8080/books/1/comments" -H "Content-Type: application/json" -d "{ \"content\": \"Excellent book!\"}"
```


### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```
