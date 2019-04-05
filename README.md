# s4


## Create a student

~~~~
curl -i -X POST -H "Content-Type:application/json" -d '{"studentId": "123", "lastName": "Smith", "firstName": "John"}' http://localhost:8080/student
HTTP/1.1 201 
Location: http://localhost:8080/student/2
Content-Type: application/hal+json;charset=UTF-8
Transfer-Encoding: chunked
Date: Fri, 05 Apr 2019 00:23:44 GMT

{
  "studentId" : 123,
  "lastName" : "Smith",
  "firstName" : "John",
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/student/2"
    },
    "student" : {
      "href" : "http://localhost:8080/student/2"
    }
  }
}
~~~~

## Retrieve all students
~~~~
$ curl http://localhost:8080/student
{
  "_embedded" : {
    "student" : [ {
      "studentId" : 124,
      "lastName" : "Doe",
      "firstName" : "Maria",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/student/1"
        },
        "student" : {
          "href" : "http://localhost:8080/student/1"
        }
      }
    }, {
      "studentId" : 123,
      "lastName" : "Smith",
      "firstName" : "John",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/student/2"
        },
        "student" : {
          "href" : "http://localhost:8080/student/2"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/student{?page,size,sort}",
      "templated" : true
    },
    "profile" : {
      "href" : "http://localhost:8080/profile/student"
    }
  },
  "page" : {
    "size" : 20,
    "totalElements" : 2,
    "totalPages" : 1,
    "number" : 0
  }
}
~~~~

## Delete a student
~~~~
curl -X "DELETE" http://localhost:8080/student/1
~~~~

## Retrieve a student with id
~~~~
curl  http://localhost:8080/student/3
{
  "studentId" : 123,
  "lastName" : "Smith",
  "firstName" : "John",
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/student/3"
    },
    "student" : {
      "href" : "http://localhost:8080/student/3"
    }
  }

~~~~
