INTERACT:
    1. To use the application, navitage to "http://hutchisonui.s3-website.eu-north-1.amazonaws.com" from a browser.

    2. The UI has the list of breeds and breed types sorted in Alphabetical order.

    3. A Breed can be added using the Add new button on the right hand side. While adding, user can even type in the breed type alongside, the application then adds the new breed and adds the breed type into it.

    4. When hovered onto the breed, the user can see the add, update or delete options. When the user clicks on add, a new breed type is added to the breed and the page is refreshed automatically.

    5. The same thing goes with breed type, but there are update and delete options as per the required dataset.

    6. The top right button on the navigation bar is for download. User can download the json file of the database at that moment.

    The interaction is simple and intuitive.

BUILD and RUN:
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

DEPLOY SCRIPT:
    1. Downloaded .pem file into your directory

    2. Run "chmod 700 <.pem file name>" to retrict the permissions for the .pem file

    3. Launch the EC2 instance on AWS console.

    4. To connect to the instance visa SSH,
        ssh -i "hutchison_challenge.pem" ec2-user@ec2-13-48-30-73.eu-north-1.compute.amazonaws.com

    4. To install java environment on the instance,
        sudo yum install java-21

    6. To send the jar file to the instance via SSH,
        scp -i hutchison_challenge.pem dogsAPI-0.0.1-SNAPSHOT.jar ec2-user@ec2-13-48-30-73.eu-north-1.compute.amazonaws.com:~

    7. To send the json file to the instance via SSH,
        scp -i hutchison_challenge.pem dogs.json ec2-user@ec2-13-48-30-73.eu-north-1.compute.amazonaws.com:~

    8. To run the server application on the instance,
        nohup java -jar dogsAPI-0.0.1-SNAPSHOT.jar > output.log &

    9. Server is deployed at baseUrl = "http://ec2-51-20-65-141.eu-north-1.compute.amazonaws.com:8000/dogsApi"

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
