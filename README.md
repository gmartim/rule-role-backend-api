# rule-role-backend-api

### Overview

This sample intends to demonstrate my abilities understanding the requirements for the Tempo's code challenge and implementing these requirements to a clean code, which is easy to read and understand, documented and runs unit and integration tests.

### Design Consideration

I considered an MVC design pattern approach, where I split the controller from the service layer and also tried to make every service as much specific as possible, making each of them responsible for a minimum number of tasks. It makes it easier to implement and create unit tests.

### Preparation

To run it local you can use Docker or Maven (Java 11), make sure you have one of them before move forward. If you are already familiar with Docker, or even already have it installed, use this options will be easier for you.

_Docker_

```
➜  ~ docker version 
Client: Docker Engine - Community
 Cloud integration: 1.0.7
 Version:           20.10.2
 API version:       1.41
 Go version:        go1.13.15
 Git commit:        2291f61
 Built:             Mon Dec 28 16:12:42 2020
 OS/Arch:           darwin/amd64
 Context:           default
 Experimental:      true

Server: Docker Engine - Community
 Engine:
  Version:          20.10.2
  API version:      1.41 (minimum version 1.12)
  Go version:       go1.13.15
  Git commit:       8891c58
  Built:            Mon Dec 28 16:15:28 2020
  OS/Arch:          linux/amd64
  Experimental:     false
 containerd:
  Version:          1.4.3
  GitCommit:        269548fa27e0089a8b8278fc4fc781d7f65a939b
 runc:
  Version:          1.0.0-rc92
  GitCommit:        ff819c7e9184c13b7c2607fe6c30ae19403a7aff
 docker-init:
  Version:          0.19.0
  GitCommit:        de40ad0
➜  ~ 
```

_Java_

```
➜  ~ java -version
java version "11.0.9" 2020-10-20 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.9+7-LTS)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.9+7-LTS, mixed mode)
➜  ~ 
```

_Maven_

```
➜  ~ mvn -version 
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: /Library/ApacheMaven
Java version: 11.0.9, vendor: Oracle Corporation, runtime: /Library/Java/JavaVirtualMachines/jdk-11.0.9.jdk/Contents/Home
Default locale: en_BR, platform encoding: UTF-8
OS name: "mac os x", version: "10.16", arch: "x86_64", family: "mac"
➜  ~ 
```

If needed, here are some links to help to prepare your environment:

- [Get Docker](https://docs.docker.com/get-docker/)
- [How do I install Java ?](https://www.java.com/en/download/help/download_options.html)
- [Installing Apache Maven](https://maven.apache.org/install.html)

### Future Release and Technical Debt

There are a few improvements I would do in the future, also, there are few technical debt:

- **Error handling**: it needs to be improved for a better understand for bad requests
- **Unit test for User and Team Repositories**: the unit tests for these repositories were not implemented
- **Additional unit tests for all classes**: more scenarios should be covered for all classes
- **Improve implementation for User and Team Repositories**: for example, externalize the URL to get to the service
- **Swagger**: implement to have a better documentation for the application
- **Cache**: check the possibility to use cache for repositories when possible
- **Code Documentation**: create a code documentation to methods using Javadoc

Make sure to move to the path where you have the source code, in this example it was used the _rule-role-backend-api_.

```
➜  rule-role-backend-api ls -l
total 80
-rw-r--r--  1 gumar  staff    147 Mar 10 21:20 Dockerfile
-rw-r--r--  1 gumar  staff  10720 Mar 10 17:44 README.md
drwxr-xr-x  2 gumar  staff     64 Mar 10 05:59 e-Core's kickoff
-rwxr-xr-x  1 gumar  staff  10070 Mar  9 15:34 mvnw
-rw-r--r--  1 gumar  staff   6608 Mar  9 15:34 mvnw.cmd
-rw-r--r--  1 gumar  staff   2377 Mar  9 21:13 pom.xml
drwxr-xr-x  4 gumar  staff    128 Mar  9 15:34 src
drwxr-xr-x  6 gumar  staff    192 Mar  9 15:34 target
➜  rule-role-backend-api 
```

** You can run just Maven or Docker on your local at same time, make sure to stop one before try other

### Run Local Using Docker

To run the application using Docker just run:

```
docker build -t rule-role-backend-api .
docker run -p 8080:8080 rule-role-backend-api
```

### Run Local Using Maven

To run the application using Maven just run:

```
chmod +x mvnw
./mvnw spring-boot:run
```

### Executing a Request

This section describes how to run the requests to the service using the _curl_ command, few free to use any other tool that is able to run HTTP requests. The application should be up and running before execute the requests.

#### POST /roles

Create a role by posting a JSON body.

_request_

```
curl -X POST 'http://localhost:8080/roles' -d '{ "name" : "Tech Leader" }' -H 'Content-Type: application/json'
```

_response_

```
{
   "name" : "Tech Leader",
   "id" : "6fa1767e-d5e6-424d-bd72-99e1be11e1ed"
}
```

#### GET /roles

Returns the roles.

_request_

```
curl -X GET 'http://localhost:8080/roles'
```

_response_

```
[
   {
      "name" : "Developer",
      "id" : "3baca4f5-74a8-4fb8-a519-840c66221f88"
   },
   {
      "name" : "Product Owner",
      "id" : "58f50fae-3fd4-449b-b10e-139d806cb07f"
   },
   {
      "name" : "Tester",
      "id" : "a2ef0002-3cd1-4bd9-98a3-197e655e3e94"
   }
]
```

#### GET /roles/{roleId}

Returns a role given a role's ID.

_request_

```
curl -X GET 'http://localhost:8080/roles/58f50fae-3fd4-449b-b10e-139d806cb07f'
```

_response_

```
{
   "name" : "Product Owner",
   "id" : "58f50fae-3fd4-449b-b10e-139d806cb07f"
}
```

#### GET /memberships/{roleId}

Returns the memberships for the given role's ID.

_request_

```
curl -X GET 'http://localhost:8080/memberships/d07a89a8-1b17-41ed-afe8-d8a6c40ddce0'
```

_response_

```
[
   {
      "roleId" : "d07a89a8-1b17-41ed-afe8-d8a6c40ddce0",
      "id" : "c3b36806-36a9-4800-9893-e0d41af0a3d6",
      "teamId" : "bdad9afe-63c3-4ff2-ae6b-f1143d92ca15",
      "userId" : "02095fb1-0e3d-469e-922c-0b2f9f58635d"
   },
   {
      "userId" : "b0a5339a-f7ef-4931-a9e1-bb3cc80c23fc",
      "teamId" : "bdad9afe-63c3-4ff2-ae6b-f1143d92ca15",
      "id" : "59e5ee21-f657-4903-8fad-c2cecdfb9365",
      "roleId" : "d07a89a8-1b17-41ed-afe8-d8a6c40ddce0"
   }
]
```

#### GET /memberships/{userId}/{teamId}

Return the roles for a given user's ID and team's ID.

_request_

```
curl -X GET 'http://localhost:8080/memberships/4408768c-6519-4325-92fb-7404db94e8a0/c6385c7d-e01a-4561-afa0-7157411c4831'
```

_response_

```
[
   {
      "name" : "Developer",
      "id" : "d07a89a8-1b17-41ed-afe8-d8a6c40ddce0"
   }
]
```

#### POST /memberships

Create a membership assigning a role to a given user and team by posting a JSON body.

_request_

```
curl -X POST 'http://localhost:8080/memberships' -d '{ "roleId" : "{roleId}", "userId" : "{userId}", "teamId" : "{teamId}" }' -H 'Content-Type: application/json'
```

_response_

```
{
   "teamId" : "c6385c7d-e01a-4561-afa0-7157411c4831",
   "roleId" : "461cb6ec-a2f4-4d33-a011-577c950d86c0",
   "id" : "810b50c6-c3b3-49db-858e-3515bb7e6f70",
   "userId" : "4408768c-6519-4325-92fb-7404db94e8a0"
}
```

#### DELETE /memberships/{roleId}/{userId}/{teamId}

Delete a membership executing a DELETE request with the role's ID, user's ID and team's ID.

_request_

```
curl -X DELETE 'http://localhost:8080/memberships/3baca4f5-74a8-4fb8-a519-840c66221f88/4871e302-dedf-43bb-8432-35ea13489462/c6385c7d-e01a-4561-afa0-7157411c4831'
```

_response_

```
None
```

#### Error Response

For now, the service is just returning an error with a message for a few requests. The error's message contains a few details about error, it needs to be improve for future releases.
