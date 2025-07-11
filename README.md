# Warehouse Clustered Data Demo

### Description
The application contain one rest api with post method to upload file. The application expect the key of the file to be "file" and the file format to be "csv"

### Guides
* create database in mysql database , for example you name it my_database
* rename it here : spring.datasource.url=jdbc:mysql://localhost:3306/my_database
*  add you mysql user and password here:  spring.datasource.username=root spring.datasource.password=password
* you can use a tool like postman to run the post request
* the post request : localhost:8080/upload
* the body : form-data with key: file , type file
