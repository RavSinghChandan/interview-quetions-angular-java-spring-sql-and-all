# SQL Interview Questions for Senior Java Backend Developers (Full Stack)

This document contains the most frequently asked SQL interview questions, including query-writing questions, curated for senior Java backend developers transitioning to full-stack roles. Questions are organized by topic and include those repeatedly asked in interviews by companies like **TCS, Infosys, Cognizant, Capgemini**, etc., along with additional critical questions to ensure comprehensive coverage for senior-level interviews.

---

## 1. SQL Basics and Queries

### Basic Questions

1. What is SQL, and what are its main types of commands (DDL, DML, DCL, TCL)? _(Asked in TCS, Infosys)_
2. What is the difference between SELECT, INSERT, UPDATE, and DELETE statements? _(Asked in Capgemini)_
3. How do you use WHERE, ORDER BY, and GROUP BY clauses? _(Asked in Wipro)_

### Basic Query Questions

1. Write a query to retrieve all columns from a table named employees. _(Asked in TCS)_
2. Write a query to insert a new record into a table named products. _(Asked in Infosys)_
3. Write a query to update the salary of an employee with a specific ID in the employees table. _(Asked in Capgemini)_

### Intermediate Questions

1. What is the difference between HAVING and WHERE clauses? _(Asked in Accenture)_
2. How do you use aggregate functions like COUNT, SUM, AVG, MIN, and MAX? _(Asked in TCS)_
3. What is the purpose of the DISTINCT keyword in SQL? _(Asked in Infosys)_

### Intermediate Query Questions

1. Write a query to find the average salary of employees grouped by department. _(Asked in Wipro)_
2. Write a query to retrieve unique customer emails from the customers table. _(Asked in Accenture)_
3. Write a query to find the top 5 highest-paid employees in the employees table. _(Asked in TCS Digital)_

### Advanced Questions

1. How do you write a query to retrieve the top N rows from a table? _(Asked in Cognizant)_
2. What is the difference between UNION and UNION ALL? _(Asked in TCS Digital)_

### Advanced Query Questions

1. Write a query to combine records from two tables, orders_2023 and orders_2024, excluding duplicates. _(Asked in Cognizant)_
2. Write a query to find the second-highest salary in the employees table. _(Asked in Infosys)_

### Tough Questions

1. How would you write a query to find duplicate records in a table with millions of rows? _(Asked in Amazon)_

### Tough Query Questions

1. Write a query to identify and delete duplicate rows in a table based on specific columns. _(Asked in Amazon)_
2. Write a query to find employees who have not placed any orders in the orders table. _(Asked in Deloitte)_

### Situational / Real-world Questions

1. You need to generate a report summarizing sales data by region and product. How would you write the SQL query? _(Asked in HCL)_
2. Write a query to generate a report showing monthly sales totals for the past year. _(Asked in HCL)_

---

## 2. Joins

### Basic Questions

1. What are the different types of joins in SQL? _(Asked in TCS, Capgemini)_
2. What is the difference between INNER JOIN and LEFT JOIN? _(Asked in Infosys)_

### Basic Query Questions

1. Write a query to join employees and departments tables to show employee names and their department names. _(Asked in TCS)_

### Intermediate Questions

1. How does a FULL OUTER JOIN differ from a CROSS JOIN? _(Asked in Wipro)_
2. What happens when you join tables with missing or null values? _(Asked in Accenture)_

### Intermediate Query Questions

1. Write a query to retrieve all customers and their orders, including customers with no orders. _(Asked in Wipro)_
2. Write a query to perform a cross join between products and categories tables. _(Asked in Accenture)_

### Advanced Questions

1. How do you optimize a query with multiple joins on large tables? _(Asked in Cognizant)_
2. What is a self-join, and when would you use it? _(Asked in TCS Digital)_

### Advanced Query Questions

1. Write a query to join employees with itself to list employees and their managers. _(Asked in TCS Digital)_
2. Write a query to join three tables (orders, customers, products) to show order details with customer and product names. _(Asked in Cognizant)_

### Tough Questions

1. How would you write a query to join three tables to retrieve hierarchical data (e.g., employee-manager relationships)? _(Asked in Deloitte)_

### Tough Query Questions

1. Write a query to find employees who work in the same department as their manager using a self-join. _(Asked in Deloitte)_

### Situational / Real-world Questions

1. Your application retrieves data from multiple related tables, but the query is slow. How would you analyze and improve it? _(Asked in HCL)_
2. Write a query to generate a report joining sales, products, and regions tables, handling missing region data. _(Asked in HCL)_

---

## 3. Indexes

### Basic Questions

1. What is an index in SQL, and why is it used? _(Asked in TCS, Infosys)_
2. What is the difference between a clustered and non-clustered index? _(Asked in Capgemini)_

### Basic Query Questions

1. Write a query to create an index on the email column of the customers table. _(Asked in TCS)_

### Intermediate Questions

1. How do you create an index on a table, and what are the trade-offs? _(Asked in Wipro)_
2. What is a composite index, and when should you use it? _(Asked in Accenture)_

### Intermediate Query Questions

1. Write a query to create a composite index on order_date and customer_id in the orders table. _(Asked in Wipro)_

### Advanced Questions

1. How does a database engine use an index to optimize query performance? _(Asked in Cognizant)_
2. What is the impact of indexes on INSERT, UPDATE, and DELETE operations? _(Asked in TCS Digital)_

### Advanced Query Questions

1. Write a query to drop an index named idx_email from the customers table. _(Asked in Cognizant)_

### Tough Questions

1. How would you design an indexing strategy for a high-write, high-read application? _(Asked in Amazon)_

### Situational / Real-world Questions

1. Your database query is slow due to missing indexes. How would you identify and add the right indexes? _(Asked in HCL)_
2. Write a query to analyze whether an index is being used for a specific query. _(Asked in HCL)_

---

## 4. Transactions and Concurrency

### Basic Questions

1. What is a transaction in SQL, and why is it important? _(Asked in TCS, Capgemini)_
2. What are the ACID properties of a transaction? _(Asked in Infosys)_

### Basic Query Questions

1. Write a query to start a transaction, update a record, and commit it. _(Asked in TCS)_

### Intermediate Questions

1. How do you use COMMIT and ROLLBACK in SQL? _(Asked in Wipro)_
2. What is the difference between SERIALIZABLE and REPEATABLE READ isolation levels? _(Asked in Accenture)_

### Intermediate Query Questions

1. Write a query to update a customer’s balance within a transaction and roll back if an error occurs. _(Asked in Wipro)_

### Advanced Questions

1. How do you handle deadlock situations in a database? _(Asked in Cognizant)_
2. What is the role of locking mechanisms in SQL transactions? _(Asked in TCS Digital)_

### Advanced Query Questions

1. Write a query to set the transaction isolation level to SERIALIZABLE before updating a table. _(Asked in Cognizant)_

### Tough Questions

1. How would you implement optimistic locking in a high-concurrency application? _(Asked in Deloitte)_

### Tough Query Questions

1. Write a query to implement optimistic locking by checking a version column during an update. _(Asked in Deloitte)_

### Situational / Real-world Questions

1. Your application experiences transaction failures due to concurrent updates. How would you diagnose and resolve this? _(Asked in HCL)_
2. Write a query to transfer funds between two accounts within a transaction, ensuring data consistency. _(Asked in HCL)_

---

## 5. Subqueries and Common Table Expressions (CTEs)

### Basic Questions

1. What is a subquery, and when would you use it? _(Asked in TCS, Infosys)_
2. What is the difference between a correlated and non-correlated subquery? _(Asked in Capgemini)_

### Basic Query Questions

1. Write a query to find employees with salaries greater than their department’s average using a subquery. _(Asked in TCS)_

### Intermediate Questions

1. What is a Common Table Expression (CTE), and how does it differ from a subquery? _(Asked in Wipro)_
2. How do you use a CTE to simplify complex queries? _(Asked in Accenture)_

### Intermediate Query Questions

1. Write a CTE to calculate total sales by product category. _(Asked in Wipro)_

### Advanced Questions

1. How do you optimize a query with nested subqueries for better performance? _(Asked in Cognizant)_
2. What are recursive CTEs, and when would you use them? _(Asked in TCS Digital)_

### Advanced Query Questions

1. Write a recursive CTE to list all employees in a manager hierarchy. _(Asked in TCS Digital)_

### Tough Questions

1. How would you rewrite a complex correlated subquery as a CTE to improve readability and performance? _(Asked in Deloitte)_

### Tough Query Questions

1. Write a query to rewrite a correlated subquery finding customers with above-average orders as a CTE. _(Asked in Deloitte)_

### Situational / Real-world Questions

1. You need to generate a hierarchical report (e.g., org chart) from a table. How would you use a recursive CTE? _(Asked in HCL)_
2. Write a recursive CTE to generate a report of all parent-child relationships in a product category tree. _(Asked in HCL)_

---

## 6. Stored Procedures and Functions

### Basic Questions

1. What is a stored procedure, and how is it different from a function? _(Asked in TCS, Infosys)_
2. How do you create a stored procedure in SQL? _(Asked in Capgemini)_

### Basic Query Questions

1. Write a stored procedure to insert a new customer record into the customers table. _(Asked in TCS)_

### Intermediate Questions

1. What are the advantages of using stored procedures in a database? _(Asked in Wipro)_
2. How do you pass parameters to a stored procedure? _(Asked in Accenture)_

### Intermediate Query Questions

1. Write a stored procedure to update product prices by a percentage based on a category ID parameter. _(Asked in Wipro)_

### Advanced Questions

1. How do you handle errors in a stored procedure? _(Asked in Cognizant)_
2. What is the difference between a stored procedure and a trigger? _(Asked in TCS Digital)_

### Advanced Query Questions

1. Write a stored procedure with error handling to transfer funds between accounts. _(Asked in Cognizant)_

### Tough Questions

1. How would you design a stored procedure to handle bulk data processing efficiently? _(Asked in Deloitte)_

### Tough Query Questions

1. Write a stored procedure to process a batch of orders and update inventory in a single transaction. _(Asked in Deloitte)_

### Situational / Real-world Questions

1. Your Java application calls a stored procedure that’s running slowly. How would you debug and optimize it? _(Asked in HCL)_
2. Write a stored procedure to generate a monthly sales report and handle errors gracefully. _(Asked in HCL)_

---

## 7. Query Optimization and Performance

### Basic Questions

1. What are some common causes of slow SQL queries? _(Asked in TCS, Infosys)_
2. How do you use the EXPLAIN plan to analyze query performance? _(Asked in Capgemini)_

### Basic Query Questions

1. Write a query and explain its execution plan using EXPLAIN. _(Asked in TCS)_

### Intermediate Questions

1. How do you optimize a query with multiple joins and aggregations? _(Asked in Wipro)_
2. What is the role of statistics in query optimization? _(Asked in Accenture)_

### Intermediate Query Questions

1. Write an optimized query to retrieve the top 10 customers by total order value. _(Asked in Wipro)_

### Advanced Questions

1. How do you use partitioning to improve query performance in large tables? _(Asked in Cognizant)_
2. What is the difference between a table scan and an index seek? _(Asked in TCS Digital)_

### Advanced Query Questions

1. Write a query to partition a large sales table by year and retrieve data for a specific year. _(Asked in Cognizant)_

### Tough Questions

1. How would you optimize a query on a table with billions of rows for real-time reporting? _(Asked in Amazon)_

### Tough Query Questions

1. Write a query to retrieve real-time sales data from a billion-row table with minimal latency. _(Asked in Amazon)_

### Situational / Real-world Questions

1. Your application’s dashboard queries are slow, impacting user experience. How would you identify and resolve the bottleneck? _(Asked in HCL)_
2. Write a query to optimize a slow dashboard report aggregating sales data across multiple dimensions. _(Asked in HCL)_

---

## 8. Database Design and Normalization

### Basic Questions

1. What is normalization, and what are the first three normal forms? _(Asked in TCS, Capgemini)_
2. What is the difference between a primary key and a foreign key? _(Asked in Infosys)_

### Basic Query Questions

1. Write a query to create a normalized table for storing customer orders. _(Asked in TCS)_

### Intermediate Questions

1. What is denormalization, and when would you use it? _(Asked in Wipro)_
2. How do you design a database schema for a given business requirement? _(Asked in Accenture)_

### Intermediate Query Questions

1. Write a query to create tables for a many-to-many relationship between students and courses. _(Asked in Wipro)_

### Advanced Questions

1. How do you handle many-to-many relationships in database design? _(Asked in Cognizant)_
2. What are the trade-offs between normalization and performance in database design? _(Asked in TCS Digital)_

### Advanced Query Questions

1. Write a query to retrieve data from a many-to-many relationship between employees and projects. _(Asked in Cognizant)_

### Tough Questions

1. How would you design a database schema for a scalable e-commerce platform? _(Asked in Deloitte)_

### Tough Query Questions

1. Write a query to create a schema for an e-commerce platform with tables for products, orders, and customers. _(Asked in Deloitte)_

### Situational / Real-world Questions

1. You inherit a poorly designed database with redundant data. How would you refactor it without disrupting the application? _(Asked in HCL)_
2. Write a query to migrate data from a denormalized table to a normalized schema. _(Asked in HCL)_

---

## 9. SQL Security

### Basic Questions

1. How do you prevent SQL injection attacks in queries? _(Asked in TCS, Infosys)_
2. What are the roles of GRANT and REVOKE in SQL? _(Asked in Capgemini)_

### Basic Query Questions

1. Write a query to grant SELECT and UPDATE permissions to a user on the employees table. _(Asked in TCS)_

### Intermediate Questions

1. How do you implement row-level security in a database? _(Asked in Wipro)_
2. What is the purpose of database roles and privileges? _(Asked in Accenture)_

### Intermediate Query Questions

1. Write a query to implement row-level security for users accessing their own orders. _(Asked in Wipro)_

### Advanced Questions

1. How do you secure sensitive data (e.g., passwords) in a database? _(Asked in Cognizant)_

### Advanced Query Questions

1. Write a query to encrypt sensitive data in the customers table (e.g., using database encryption functions). _(Asked in Cognizant)_

### Tough Questions

1. How would you design a database to comply with GDPR data protection requirements? _(Asked in Deloitte)_

### Tough Query Questions

1. Write a query to anonymize personal data in the customers table for GDPR compliance. _(Asked in Deloitte)_

### Situational / Real-world Questions

1. Your Java application exposes sensitive data through a SQL query. How would you secure it? _(Asked in TCS Digital)_
2. Write a query to audit access to sensitive data in the employees table. _(Asked in TCS Digital)_

---

## 10. Window Functions

### Basic Questions

1. What are window functions in SQL, and why are they used? _(Asked in TCS, Infosys)_
2. What is the difference between RANK, DENSE_RANK, and ROW_NUMBER? _(Asked in Capgemini)_

### Basic Query Questions

1. Write a query to assign row numbers to records in the orders table ordered by order_date. _(Asked in TCS)_

### Intermediate Questions

1. How do you use the PARTITION BY clause in window functions? _(Asked in Wipro)_
2. What is the purpose of the OVER clause in window functions? _(Asked in Accenture)_

### Intermediate Query Questions

1. Write a query to calculate the running total of sales by customer using a window function. _(Asked in Wipro)_

### Advanced Questions

1. How do you calculate a running total using window functions? _(Asked in Cognizant)_

### Advanced Query Questions

1. Write a query to rank employees by salary within each department using a window function. _(Asked in Cognizant)_

### Tough Questions

1. How would you use window functions to identify gaps in sequential data (e.g., missing invoice numbers)? _(Asked in Deloitte)_

### Tough Query Questions

1. Write a query to find gaps in invoice numbers using a window function. _(Asked in Deloitte)_

### Situational / Real-world Questions

1. You need to rank employees by performance within each department. How would you write the query using window functions? _(Asked in HCL)_
2. Write a query to calculate the percentage contribution of each product to total sales using window functions. _(Asked in HCL)_

---

## 11. Error Handling and Debugging

### Basic Questions

1. How do you handle errors in SQL queries or stored procedures? _(Asked in TCS)_
2. What tools do you use to debug slow SQL queries? _(Asked in Infosys)_

### Basic Query Questions

1. Write a query with error handling in a stored procedure to log errors to an audit table. _(Asked in TCS)_

### Intermediate Questions

1. How do you log errors in a database transaction? _(Asked in Capgemini)_
2. How do you troubleshoot a query that returns incorrect results? _(Asked in Wipro)_

### Intermediate Query Questions

1. Write a query to log transaction errors to an error_log table within a stored procedure. _(Asked in Wipro)_

### Advanced Questions

1. How do you debug a deadlock in a production database? _(Asked in Cognizant)_

### Advanced Query Questions

1. Write a query to identify recent deadlocks in a database using system views (e.g., SQL Server’s sys.dm_tran_locks). _(Asked in Cognizant)_

### Tough Questions

1. A query works locally but fails in production with a timeout. How would you diagnose and resolve this? _(Asked in Deloitte)_

### Tough Query Questions

1. Write a query to analyze query performance using database profiling tools or system views. _(Asked in Deloitte)_

### Situational / Real-world Questions

1. Your Java application logs database errors but lacks context. How would you improve error handling and logging? _(Asked in HCL)_
2. Write a query to trace and log slow-running queries in a production database. _(Asked in HCL)_

---

## 12. Integration with Java Applications

### Basic Questions

1. How do you connect a Java application to a database using JDBC? _(Asked in TCS, Infosys)_
2. What is the difference between Statement and PreparedStatement in JDBC? _(Asked in Capgemini)_

### Basic Query Questions

1. Write a Java method using JDBC to execute a SELECT query on the employees table. _(Asked in TCS)_

### Intermediate Questions

1. How do you handle database transactions in a Java application? _(Asked in Wipro)_
2. How do you map query results to Java objects efficiently? _(Asked in Accenture)_

### Intermediate Query Questions

1. Write a Java method using PreparedStatement to insert a record into the orders table. _(Asked in Wipro)_

### Advanced Questions

1. How do you implement connection pooling in a Java application? _(Asked in Cognizant)_

### Advanced Query Questions

1. Write a Java method to execute a batch update of multiple records using JDBC. _(Asked in Cognizant)_

### Tough Questions

1. How would you optimize a Java application’s database interactions for high throughput? _(Asked in Amazon)_

### Tough Query Questions

1. Write a Java method to execute a complex query with joins and map results to a custom object. _(Asked in Amazon)_

### Situational / Real-world Questions

1. Your Java application experiences slow database queries in production. How would you identify and resolve the issue? _(Asked in HCL)_
2. Write a Java method to handle a transaction across multiple tables with error handling. _(Asked in HCL)_ 
