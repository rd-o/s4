# s4

### Technologies used in the project

#### spring-boot-starter-data-rest

To implement a REST API it was used the Spring Data REST, this allows to have the
basic CRUD operations without much effort.

#### spring-boot-starter-data-jpa (h2)

Using this dependency is more simpler to manage data and helps to reduce the amount 
of boilerplate code.

A list of ClassModel class were declared in the Student class and also a list of 
Student class in ClassModel. For each list the ```@ManyToMany``` and ```@JoinTable```
annotation was used, to allow the requested relationship between class and student. So
a student can attend to several classes and a class can have several students. 
To avoid having a student attending the same class many times, the ```@UniqueConstraint``` 
annotation was used, but in other contexts can be useful not use this.

With the help of a in-memory database (h2) and data from resources/data.sql, after 
executing the spring boot application it is possible to consume from the implemented API.

In the Repository classes were declared methods like ```findByStudentId```, to search the
models by its attributes.

#### Maven

To easily install the dependencies, test and execute the project.


### Error handling
A GlobalExceptionHandler class declared, in here can declare methods to handle 
several types of Exceptions.

In the class it was declared a ```handleHttpMessageNotReadableException``` method, and it
is capable to handle the cause exception like ```JsonMappingException```. Please note in the
 the section of selecting the exception cause, there are actions commented, these need to be 
implemented.

The following output show how the exception is handled and shows the custom message: 
"Please check your request"
~~~~
curl -i -X POST -H "Content-Type:application/json" -d '{"studentId": "322", "lastName": "Smith", "firstName": "Olivia", "classModels":["http://localhost:8080/class/5"}' http://localhost:8080/student
HTTP/1.1 400 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Fri, 05 Apr 2019 16:33:24 GMT
Connection: close

{"timestamp":"2019-04-05T16:33:24.388+0000","status":400,"error":"Bad Request","message":"Please check your request","path":"/student"}
~~~~

### Unit tests
Two unit test for Student Repository were added to ```StudentRepositoryTest``` class

### Project usage
#### Run the project

```$ java -jar target/s4-0.0.1-SNAPSHOT.jar```

With maven

```$ mvn spring-boot:run ```
#### Retrieve all data from student and class
~~~~
$ curl http://localhost:8080/student
$ curl http://localhost:8080/class
~~~~

#### Create a new student and assign it to a previously created class
~~~~
$ curl -i -X POST -H "Content-Type:application/json" -d '{"studentId": "322", "lastName": "Smith", "firstName": "Olivia", "classModels":["http://localhost:8080/class/5"]}' http://localhost:8080/student
~~~~

#### Delete a student
~~~~
$ curl -X "DELETE" http://localhost:8080/student/1
~~~~

#### Search students or classes using some of its parameters
~~~~
$ curl http://localhost:8080/student/search/
{
  "_links" : {
    "findByLastName" : {
      "href" : "http://localhost:8080/student/search/findByLastName{?lastName}",
      "templated" : true
    },
    "findByFirstName" : {
      "href" : "http://localhost:8080/student/search/findByFirstName{?firstName}",
      "templated" : true
    },
    "findByStudentId" : {
      "href" : "http://localhost:8080/student/search/findByStudentId{?studentId}",
      "templated" : true
    },
    "self" : {
      "href" : "http://localhost:8080/student/search/"
    }
  }
}
~~~~

~~~~
$ curl http://localhost:8080/class/search/
~~~~
Search students by the last name
~~~~
$ curl http://localhost:8080/student/search/findByLastName?lastName=Smith
{
  "_embedded" : {
    "student" : [ {
      "studentId" : 123,
      "lastName" : "Smith",
      "firstName" : "John",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/student/1"
        },
        "student" : {
          "href" : "http://localhost:8080/student/1"
        },
        "classModels" : {
          "href" : "http://localhost:8080/student/1/classModels"
        }
      }
    }, {
      "studentId" : 128,
      "lastName" : "Smith",
      "firstName" : "Liam",
...
}
~~~~

#### Get all students for a specified class
~~~~
$ curl http://localhost:8080/class/2/students
~~~~

#### Get all classes for a specified student
~~~~
$ curl http://localhost:8080/student/1/classModels
~~~~

#### Edit an already created class
~~~~
$ curl -H 'Content-Type: application/json' -X PUT -d '{"code" : "CAL111", "title" : "A description 11", "description" : "Calculus 111"}' http://localhost:8080/class/2
~~~~

