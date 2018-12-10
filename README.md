# Diabetes Supplies Project

This project was in the back of my mind for a long time. It was previously developed in C, but did not have a GUI or database.
It was very rudimental. I wanted to take a step ahead and improve it (as well as my knowledge with Java, JavaFX, and MySQL). It is a very dear project to me, as I've been diagnosed with type 1 Diabetes since February 2004. I love to apply the skills that I possess to solve real life problems.

This project was incentivated by Professor Vanselow, and his COP 3003 class. 
The files contained in this repository are used for grading purposes in the COP 3003 class of the fall semester 2018.

The languages used are **Java**, **JavaFX**, and **SQL**.
This project is going to be under constant development. Whenever something will need to be added to the program, it will also  be displayed under the **ToDo** section of this README.

## Project Description

This project is aimed towards creating a software to manage diabetes supplies. There can be multiple users added. These users will be stored in a database along with a table reserved for the supplies. The primary key of the user is going to be their username (does not allow duplicates). The primary key of the products is going to be their serial number. There will be a third table used to set each of the primary keys as foreign keys to build a relation. This will prevent items being left in the database when a certain entity, whose relationship is connected to the item, is deleted.

## MySQL Setup

In order to properly run this project, MySQL needs to be installed on the computer.

Once MySql is installed, utilize the command ```mysql -u USERNAME -p```, and it will prompt for the database password. Once inserted, please refer to the myDiabetesSupplies.sql file in order to obtain the SQL queries to create and populate the database.

In order to handle the DB properly, we need to use [MySQL Connector](https://dev.mysql.com/downloads/connector/j/). This can be included in the project by going to ```File -> Project Structure -> Modules```, and then add the JAR file obtained from the download.

The Database Diagram can be seen from the **DB_Diagram.pdf** file, included in this repository.

## GUI Design Principles

This project adheres to some GUI Design Principles, as:

- It provides meaningful contrast between screen elements.
- It uses colors and graphics effectively and simply.
- The interface is visually, conceptually and linguistically clear.
- The user can control interactions effectively.
- The effect of actions on objects are visible (especially alterations to the database).
- It is very responsive.

## ToDo:
- [ ] Add invalid username check label when trying to delete a user.
- [ ] Add username already exist check when trying to add a new user.

## Built With

* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - IDE of choice
* [SceneBuilder](https://gluonhq.com/products/scene-builder) - Great software to create GUI screens with ease
* [MySQL](https://www.mysql.com) - One of the most used and powerful DBMS, Windows & Mac friendly!

## Plugins Used
* [FindBugs](http://findbugs.sourceforge.net/)
* [CheckStyle](http://checkstyle.sourceforge.net/config_naming.html#PackageName) - Google Checks used

## Authors

* **Luca Missaglia** - *Project Developer* - [Lollators](https://github.com/Lollators)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
