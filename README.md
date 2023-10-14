INTERACT:
    1. To use the application, navitage to "http://hutchisonui.s3-website.eu-north-1.amazonaws.com" from a browser.

    2. The UI has the list of breeds and breed types sorted in Alphabetical order.

    3. A Breed can be added using the Add new button on the right hand side. While adding, user can even type in the breed type alongside, the application then adds the new breed and adds the breed type into it.

    4. When hovered onto the breed, the user can see the add, update or delete options. When the user clicks on add, a new breed type is added to the breed and the page is refreshed automatically.

    5. The same thing goes with breed type, but there are update and delete options as per the required dataset.

    6. The top right button on the navigation bar is for download. User can download the json file of the database at that moment.

    The interaction is simple and intuitive.

RUN:
    To run the application manually,    pull the repo
                                        navigate into ui directory on local terminal:
                                            run the command "python -m http.server 8080" or with python3
                                            open the browser and enter "http://localhost:8080/"
                                        navigate into the dogsAPI directory on local terminal:
                                            run the command "mvn clean package"
                                            run the command "cd dogsAPI/target/"
                                            run "java -jar dogsAPI-0.0.1-SNAPSHOT.jar"
    Now the server is up and running and serves the ui

NOTE:
    While trying to run on local machine, please open the "script.js" file and change the baseUrl to 
    "http://localhost:8000/dogsApi" as the server will be running locally on that port.


The tree structure of the submitted directory is as follows:
    ├── README.md
    ├── dogs.json
    ├── dogsAPI
    │   ├── pom.xml
    │   └── src
    │       ├── main
    │       │   ├── java
    │       │   │   └── com
    │       │   │       └── hutchison
    │       │   │           └── dogsAPI
    │       │   │               ├── DogsApiApplication.java
    │       │   │               ├── controller
    │       │   │               │   └── DogsController.java
    │       │   │               ├── exceptions
    │       │   │               │   └── DogException.java
    │       │   │               ├── repo
    │       │   │               │   └── DogsData.java
    │       │   │               ├── service
    │       │   │               │   ├── DogsService.java
    │       │   │               │   └── DogsServiceImpl.java
    │       │   │               └── utils
    │       │   │                   └── ResponseResult.java
    │       │   └── resources
    │       │       ├── application.properties
    │       │       ├── banner.txt
    │       │       ├── static
    │       │       └── templates
    │       └── test
    │           └── java
    │               └── com
    │                   └── hutchison
    │                       └── dogsAPI
    │                           ├── DogsApiApplicationTests.java
    │                           └── RepoTests.java
    └── ui
        ├── index.html
        ├── script.js
        └── style.css
