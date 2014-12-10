Open-Domain-Question-Answering
==============================

Requirments:
==============================

1)JDK 1.7 and above
2)Apache Tomcat 7.0
3)MongoDB 2.4.8


Install Notes
==============================
This project consists of three modules
1)POS tag web service
2)Knowledge base web service
3)Main Module (Question Answering)
Steps to running projects
1.First start the POS(part of speech) web service
Go to POS_WS folder and open run.bat wait until web service to be start.
Note: Make sure your machine having jdk1.7 version and environment path is correctly configured and 8086 port shout be available allow the network access.
2. Start Knowledge base web service
Before start knowledge base install mongoDB and restore the database file using following command change to mongodb bin dir
Mongorestore –d answeritdb path to db files
Here the folder answeritdb contains all db files include in your path
Go to KB folder and open run.bat wait until web service to be start.
3)Open eclipse juno and import project select answerit folder config with apache 7 and run it
