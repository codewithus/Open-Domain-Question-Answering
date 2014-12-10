Open-Domain-Question-Answering
==============================

Requirments:
==============================
<br>
1)JDK 1.7 and above<br>
2)Apache Tomcat 7.0
<br>
3)MongoDB 2.4.8<br>


Install Notes
==============================
This project consists of three modules<br>
1)POS tag web service<br>
2)Knowledge base web service<br>
3)Main Module (Question Answering)<br>

Steps to running projects<br>
________________________________________
1.First start the POS(part of speech) web service<br>
Go to POS_WS folder and open run.bat wait until web service to be start.<br>
Note: Make sure your machine having jdk1.7 version and environment path is correctly configured and 8086 port shout be available allow the network access.<br>
2. Start Knowledge base web service<br>
Before start knowledge base install mongoDB and restore the database file using following command change to mongodb bin dir<br>
Mongorestore –d answeritdb path to db files<br>
Here the folder answeritdb contains all db files include in your path<br>
Go to KB folder and open run.bat wait until web service to be start.<br>
3)Open eclipse juno and import project select answerit folder config with apache 7 and run it<br>
