# ShopMart 

A mini online E-commerce Business to Consumer platform for users (Sellers) to sell their products to users (Buyers). 

ShopMart is a mini Walmart clone designed to aid our learning and usage of the technologies being used in this project.

## Technologies Used

 - ShopMart consists of a [REST API Backend](https://github.com/sekayasin/shopmart-backend) developed using [Java SpringBoot](https://spring.io/projects/spring-boot) and a [Front-end](https://github.com/sekayasin/shopmart-frontend) consuming it developed using [Reactjs](https://reactjs.org/)
 - The Backend runs on a [PostgreSQL Database](https://www.postgresql.org/), the World's Most Advanced Open Source Relational Database.
 - [Docker](https://www.docker.com/), a popular containerization tool is used to create docker images for both our Backend and Frontend, which leads to faster production deployment and a more efficient containerization in production.

## Prerequisites

- Programming language prerequisites

    - Java JDK - [Java JDK 11+](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
    - Nodejs version - preferably [version 16 LTS](https://nodejs.org/en/) and above
  
        - package managers - npm or yarn
- Editors / IDEs

    - Java IDE - preferably [IntelliJ IDEA](https://www.jetbrains.com/idea/)
    - JavaScript IDE - preferably [VS Code](https://code.visualstudio.com/)

## About the Application

The application consists of 3 Types of Users
- Admin
- Buyer
- Seller

BUYER/SELLER user are required to signup first to use the application.

The ADMIN user is required to approve the buyer/seller accounts before their start using the application

### Application Demo




## Running the Dockerized Application

The application has been containerized and can be run using the docker-compose file

There is a `docker-compose.yml` file that can be run to start all the services of the application

Both the backend and front-end docker images are referenced in the docker-compose file

The images are hosted on docker-hub repository, hence they will be pulled once the application is started as instructed below.



    ```
    $ git clone git@github.com:sekayasin/shopmart-backend.git
    
    ## cd into shopmart-backend directory
    $ cd shopmart-backend
  
    ## start the application -- run 
    $ docker compose up -d
    
    ## Open http://localhost to view the frontend in your browser.
    
    ```
- Troubleshooting in case of any error
  
  - run the application and monitor the logs

    ```
    ## stop the application
    $ docker compose down
    
    ## start the application and monitor the logs, don't run in detached mode
    $ docker compose up
    
    ## make sure you don't have another instance of postgres running on port 5432
    ```

### ShopMart Backend

This repo consist of the Backend code base built using SpringBoot

#### Getting Started - Setting up and running the Backend codebase locally


    ```
    $ git clone git@github.com:sekayasin/shopmart-backend.git
    
    ## cd into shopmart-backend directory
    $ cd shopmart-backend
  
    ## starting progtres database using a docker container
    $ docker run --name postgres_container -p 5432:5432 -e POSTGRES_PASSWORD=1 -d postgres
  
    ## create an new database named final_project
    
    ## exec into the postgres_container
    $ docker exec -it postgres_container bash
    root@1526c6bb2f6b:/# psql -h localhost -p 5432 -U postgres -W
    Password:
    
    psql (14.2 (Debian 14.2-1.pgdg110+1))
    Type "help" for help.
    
    postgres=# create database final_project;
  
    postgres=# grant all privileges on database final_project to postgres;
    
    postgres=# \q
    root@1526c6bb2f6b:/# exit
  
    ## import project into your Java IDE - intellij IDE
    ## Wait for all the dependencies to get resolved
    ## Run the Application
  
    ## The server will start running on port 8080
    ```
#### ShopMart Backend API endpoints

The Backend API servers the users that is the BUYER, SELLER, and the ADMIN

The Backend is configured with these default accounts.
    
    Default Admin details
    username: admin
    password: pass123

    Default Seller details
    username: seller
    password: pass123

    Default Buyer details
    username: buyer
    password: pass123

### Special Thanks

##### Contributors

| Name                   | ID     | Github username                               |
|------------------------|--------|-----------------------------------------------|
|   






