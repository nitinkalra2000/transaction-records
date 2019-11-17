Environment
-----------
Java 8
Junit 4
Maven
transaction records are given in file name - resources/transaction-records.csv

Build
-----
 - to build the application, after installing maven, run command: mvn install

 
Run
---
 - Entry point for the application is TransactionRecordsApp.java
 - The class will take 4 arguments, like - 
   ACC334455 transaction-records.csv 20/10/2018T12:00:00 20/10/2018T19:00:00
   
 - These arguments can be set pass from Run configuration if using eclipse.
 
 - If wants to run directly from Jar, first compile using Maven then run the jar from
   target folder. Use jar-with-dependencies
 - java -cp transaction-records-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.mebank.transaction.records.TransactionRecordsApp  ACC334455 transaction-records.csv 20/10/2018T12:00:00 20/10/2018T19:00:00