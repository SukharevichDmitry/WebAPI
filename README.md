# CRUD Library-Web-API
This project contains three microservices: authservice, bookservice, and libraryservice, along with a PostgreSQL database, eureka server and api-gateway.

## How to run
#### The first thing you need to do is build an application. You can do this by running the following command:
```bash
mvn clean package
```
#### You can run the entire project with this commands:
```bash
docker-compose build
docker-compose up
```
#### Аnother option for starting the project is manually. You need to manually run each of the modules in the following order:
* eureka-server
* authservice, bookservice, libraryservice
* api-gateway
## Commands for AuthService:
### If you use [Postman](https://www.postman.com/downloads/):

* #### to register:
```HTML
URL: http://localhost:8084/auth/register
Method: POST
Body: raw JSON
Data:
{
  "username": "your-username",
  "password": "your-password"
}
```
* #### to login:
```HTML
URL: http://localhost:8084/auth/login 
Method: POST
Bodde: raw JSON
Data:
{
  "username": "your-username",
  "password": "your-password"
}
```

### If you use [cURL](https://curl.se/):

* #### to register:
```bash
curl -X POST http://localhost:8084/auth/register -H "Content-Type: application/json" -d '{"username":"your-username", "password":"your-password"}'
```
* #### to login:
```bash
curl -X POST http://localhost:8084/auth/login -H "Content-Type: application/json" -d '{"username":"your-username", "password":"your-password"}'
```


### If you use JavaScript(Fetch API):

* #### to register:
```JavaScript 
fetch('http://localhost:8084/auth/register', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify({
        username: 'new-username',
        password: 'new-password'
    })
})
.then(response => {
    if (response.ok) {
        console.log('Registration successful');
    } else {
        console.error('Registration failed');
    }
})
.catch(error => console.error('Error:', error));
```

* #### to login:
```JavaScript
fetch('http://localhost:8084/auth/login', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify({
        username: 'your-username',
        password: 'your-password'
    })
})
.then(response => response.json())
.then(data => console.log('Success:', data))
.catch(error => console.error('Error:', error));
```

## Commands for BookService:

### If you use [Postman](https://www.postman.com/downloads/):

* #### to get all books:
```HTML
URL: http://localhost:8084/books
Method: GET
Body: raw JSON
Data: null
```

* #### to get book by id:
```HTML
URL: http://localhost:8084/books/{id}
Method: GET
Body: raw JSON
Data: null
```
* #### to get book by isbn:
```HTML
URL: http://localhost:8084/books/isbn/{isbn}
Method: GET
Body: raw JSON
Data: null
```
* #### to create book:
```HTML
URL: http://localhost:8084/books
Method: POST
Body: raw JSON
Data: 
{
   "title" : "your_title",
   "author" : "your_author",
   "isbn" : "your_isbn",
   "genre" : "your_genre",
   "description" : "your_description"
}
```
* #### to update book:
```HTML
URL: http://localhost:8084/books/{id}
Method: PUT
Body: raw JSON
Data:
{
   "title" : "your_title",
   "author" : "your_author",
   "isbn" : "your_isbn",
   "genre" : "your_genre",
   "description" : "your_description"
}
```
* #### to delete book:
```HTML
URL: http://localhost:8084/books/{id}
Method: DELETE
Body: raw JSON
Data: null
```

### If you use [cURL](https://curl.se/):
* #### To get all books:
```bash
curl -X GET http://localhost:8084/books
```
* #### To get book by id:
```bash
curl -X GET http://localhost:8084/books/{id}
```
* #### To get book by isbn:
```bash
curl -X GET http://localhost:8084/books/isbn/{isbn}
```

* #### To create book
```bash
curl -X POST http://localhost:8084/books -H "Content-Type: application/json" -d '{"title":"New Book Title", "author":"Author Name", "isbn":"1234567890123", "publishedDate":"2024-01-01"}'
```

* #### To update book
```bash
curl -X PUT http://localhost:8084/books/{id} -H "Content-Type: application/json" -d '{"title":"Updated Book Title", "author":"Updated Author Name", "isbn":"9876543210987", "publishedDate":"2025-01-01"}'
```

* #### To delete book:
```bash
curl -X DELETE http://localhost:8084/books/{id}
```

### If you use JavaScript(Fetch API):

* #### To get all books:
```JavaScript
fetch('http://localhost:8084/books', {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json'
    }
})
.then(response => response.json())
.then(data => console.log('Books:', data))
.catch(error => console.error('Error:', error));
```

* #### To get book by id:
```JavaScript
fetch('http://localhost:8084/books/{id}', { 
    method: 'GET',
    headers: {
        'Content-Type': 'application/json'
    }
})
.then(response => response.json())
.then(data => console.log('Book by ID:', data))
.catch(error => console.error('Error:', error));
```
* #### To get book by isbn:
```JavaScript
To get book by isbn:

fetch('http://localhost:8084/books/isbn/{isbn}', { 
    method: 'GET',
    headers: {
        'Content-Type': 'application/json'
    }
})
.then(response => response.json())
.then(data => console.log('Book by ISBN:', data))
.catch(error => console.error('Error:', error));
```
* #### To create book:
```JavaScript
fetch('http://localhost:8084/books', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify({
   	title : 'your_title',
   	author : 'your_author',
   	isbn : 'your_isbn',
   	genre : 'your_genre',
   	description : 'your_description'
    })
})
.then(response => response.json())
.then(data => console.log('Created Book:', data))
.catch(error => console.error('Error:', error));
```
* #### To update book:
```JavaScript
To update book:

fetch('http://localhost:8084/books/{id}', { 
    method: 'PUT',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify({
   	title : 'your_title',
   	author : 'your_author',
   	isbn : 'your_isbn',
   	genre : 'your_genre',
   	description : 'your_description'
    })
})
.then(response => response.json())
.then(data => console.log('Updated Book:', data))
.catch(error => console.error('Error:', error));
```
* #### To delete book:
```JavaScript
To delete book:

fetch('http://localhost:8084/books/{id}', {
    method: 'DELETE',
    headers: {
        'Content-Type': 'application/json'
    }
})
.then(response => {
    if (response.ok) {
        console.log('Book deleted');
    } else {
        console.error('Error deleting book');
    }
})
.catch(error => console.error('Error:', error));
```
## Commands for LibraryService:
### If you use [Postman](https://www.postman.com/downloads/):

* #### To get all books:
```HTML
URL: http://localhost:8084/library/books
Method: GET
Body: raw JSON
Data: null
```
* #### To get books by id:
```HTML
URL: http://localhost:8084/library/books/{id}
Method: GET
Body: raw JSON
Data: null
```
* #### To get all available books:
```HTML
URL: http://localhost:8084/library/books/available
Method: GET
Body: raw JSON
Data: null
```
* #### To add book:
```HTML
URL: http://localhost:8084/library/books/add
Method: POST
Body: raw JSON
Data: 
{
  "is_available" : true,
  "borrow_time" : 2024-10-14 18:45:17.722095,
  "return_time" : 2024-10-21 18:45:17.722095,
}
```
* #### To update book:
```HTML
URL: http://localhost:8084/library/books/{id}
Method: PUT
Body: raw JSON
Data: 
{
  "is_available" : true,
  "borrow_time" : 2024-10-14 18:45:17.722095,
  "return_time" : 2024-10-21 18:45:17.722095,
}
```
* #### To delete book:
```HTML
URL: http://localhost:8084/library/books/{id}
Method: DELETE
Body: raw JSON
Data: null
```
### If you use [cURL](https://curl.se/):
* #### To get all books:
```bash
curl -X GET http://localhost:8084/library/books
```
* #### To get books by id:
```bash
curl -X GET http://localhost:8084/library/books/{id}
```
* #### To get all available books:
```bash
curl -X GET http://localhost:8084/library/books/available
```
* #### To add book:
```bash
curl -X POST http://localhost:8084/library/add -H "Content-Type: application/json" -d '{"title":"Book Title", "author":"Author Name", "available":true}'
```
* #### To update book:
```bash
curl -X PUT http://localhost:8084/library/books/{id} -H "Content-Type: application/json" -d '{"title":"Updated Book Title", "author":"Updated Author Name", "available":false}'
```
* #### To delete book:
```bash
curl -X DELETE http://localhost:8084/library/books/{id}
```
### If you use JavaScript(Fetch API):
* #### To get all books:
```JavaScript
fetch('http://localhost:8084/library/books', {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json'
    }
})
.then(response => response.json())
.then(data => console.log('All Books:', data))
.catch(error => console.error('Error:', error));
```
* #### To get book by id:
```JavaScript
fetch('http://localhost:8084/library/books/{id}', {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json'
    }
})
.then(response => response.json())
.then(data => console.log('Book by ID:', data))
.catch(error => console.error('Error:', error));
```
* #### To get all available books:
```JavaScript
fetch('http://localhost:8084/library/books/available', {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json'
    }
})
.then(response => response.json())
.then(data => console.log('Available Books:', data))
.catch(error => console.error('Error:', error));
```
* #### To add book:
```JavaScript
fetch('http://localhost:8084/library/add', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify({
        title: 'New Library Book',
        author: 'Author Name',
        isbn: '978-3-16-148410-0',
        available: true
    })
})
.then(response => response.json())
.then(data => console.log('Created Library Book:', data))
.catch(error => console.error('Error:', error));
```
* #### To update book:
```JavaScript
fetch('http://localhost:8084/library/books/{id}', {
    method: 'PUT',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify({
        title: 'Updated Library Book Title',
        author: 'Updated Author Name',
        isbn: '978-3-16-148410-0',
        available: false
    })
})
.then(response => response.json())
.then(data => console.log('Updated Library Book:', data))
.catch(error => console.error('Error:', error));
```
* #### To delete book:
```JavaScript
fetch('http://localhost:8084/library/books/{id}', {
    method: 'DELETE',
    headers: {
        'Content-Type': 'application/json'
    }
})
.then(response => {
    if (response.ok) {
        console.log('Library Book deleted');
    } else {
        console.error('Error deleting book');
    }
})
.catch(error => console.error('Error:', error));
```
