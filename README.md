# internet-shop

internet-shop is an application with REST API architecture, performs Internet shop functions. The main functions of the application are:
* make an orders;
* buy various kinds of items;
* view the products available in the catalog.

## Technologies

Project is created with:
* java 17 version;
* Spring (modules: Spring boot, Spring Data, Spring Security);
* lombok;
* MySQL;
* Maven;
* JUnit 5;
* Mail sender;

## Installation

* Install Net-shop(FinalProject) from gitHub.
* Then open it with Intelij IDEA.
* When the project is open go to **application.properties** - path:"src/main/resources/application.properties". In order to run the application you should create new database and change the settings of db: change user, password and db url;
* After connection is made, open DDL file - path :"src/main/resources/DDL" copy and past info from this file to db console, it will make all the needed tables;
* Upload db data from **code_entity.csv**, **item_entity.csv**, **item_stack_entity.csv**, **order_entity.csv**,**order_entity_items.csv**, **user_entity.csv**, **user_entity_orders.csv** in this exact order to the tables - paths of csv files :"src/main/resources";
* Run Tests to check the application;
* After that start the **FinalProjectApplication** class.

## Registration
* go to "http://localhost:8080/api/user/register" via postman
* User have to pass json format (**POST** method: {"username" : "testName", "email": "testMail@gmail.com", "password": "testPassword"});
* the user is saved to the database with ROLE_USER;

## Login
* to log in to the application you need to follow the link "http://localhost:8080/api/user/authenticate" and pass json format (**POST** method: {"username": "testName, "password": "testPassword"});
* use the generated token for further operations.

## User capabilities

**Available endpointsROLE_USER:**
* "http://localhost:8080//api/user/register"  –  register.
* Example:( POST method: "http://localhost:8080//api/user/register" {"username" : "testName", "email": "testMail@gmail.com", "password": "testPassword"});
* "http://localhost:8080/api/user/authenticate" –  login.
* Example:( POST method: "http://localhost:8080/api/user/authenticate" {"username": "testName, "password": "testPassword"});
* "http://localhost:8080//api/item/all"  – get List of items.
* Example:( GET method: "http://localhost:8080//api/item/all");
* "http://llocalhost:8080/api/item/{id}"  – search item by id.
* Example:( GET method: "http://llocalhost:8080/api/item/1");
* "http://localhost:8080//api/order/current"  – get orders for current user.
* Example:( GET method: "http://localhost:8080//api/order/current");
* "http://localhost:8080/api/order/create"  – make an order.
* Example:( POST method: "http://localhost:8080/api/order/create" {"items": [{"itemId": 1, "amount": 5}]});
* After you make an order you should approve or decline it on your email. When you approve order it will be saved in database. When you decline -> deleted.

## Admin capabilities

**Available endpointsROLE_ADMIN:**
* "http://localhost:8080//api/item/add"  –  add items in list.
* Example:( POST method: "http://localhost:8080//api/item/add" {"name": "testName", "description": "something", "price": 100});
* "http://localhost:8080//api/order/belongs/{id}" –  get all orders for specific user.
* Example:( GET method: "http://localhost:8080//api/order/belongs/2");
* Admin can also perform all the same operations as the user.
