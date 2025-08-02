# SQL Interview Questions for Senior Java Backend Developers (MNC Service-Based Companies)

---

## 1. SQL Basics and Queries

### Basic Questions
1. What is SQL, and what are its main types of commands (DDL, DML, DCL, TCL)? _(TCS, Infosys)_
2. What is the difference between SELECT, INSERT, UPDATE, and DELETE statements? _(Capgemini)_
3. How do you use WHERE, ORDER BY, and GROUP BY clauses? _(Wipro)_
4. What is a primary key, and how does it differ from a unique key? _(Capgemini)_
5. What is the difference between DELETE, TRUNCATE, and DROP commands? _(Tech Mahindra)_

### Basic Query Questions
1. Write a query to retrieve all columns from a table named `employees`. _(TCS)_
2. Write a query to insert a new record into a table named `products`. _(Infosys)_
3. Write a query to update the salary of an employee with a specific ID in the `employees` table. _(Capgemini)_
4. Write a query to delete records from the `orders` table where the order date is older than a specific date. _(Wipro)_
5. Write a query to select the top 10 rows from the `customers` table ordered by `last_name`. _(Accenture)_

### Intermediate Questions
1. What is the difference between HAVING and WHERE clauses? _(Accenture)_
2. How do you use aggregate functions like COUNT, SUM, AVG, MIN, and MAX? _(TCS)_
3. What is the purpose of the DISTINCT keyword in SQL? _(Infosys)_
4. What is a subquery, and when would you use it? _(TCS Digital)_
5. What is a foreign key, and how does it enforce referential integrity? _(Accenture)_

### Intermediate Query Questions
1. Write a query to find the average salary of employees grouped by department. _(Wipro)_
2. Write a query to retrieve unique customer emails from the `customers` table. _(Accenture)_
3. Write a query to find the top 5 highest-paid employees in the `employees` table. _(TCS Digital)_
4. Write a query to count the number of orders per customer using the `orders` table. _(Infosys)_
5. Write a query to find employees with salaries above the company average. _(Cognizant)_

### Advanced Questions
1. What is a Common Table Expression (CTE), and when would you use it over a subquery? _(Zoho)_
2. What is the difference between UNION and UNION ALL? _(TCS Digital)_
3. What is database normalization, and why is it important? _(Deloitte)_
4. What is the COALESCE function, and how is it used to handle NULLs? _(EY)_
5. How do you write a query to retrieve the top N rows from a table? _(Cognizant)_

### Advanced Query Questions
1. Write a query to combine records from two tables, `orders_2023` and `orders_2024`, excluding duplicates. _(Cognizant)_
2. Write a query to find the second-highest salary in the `employees` table. _(Infosys)_
3. Write a query to find employees with the highest salary in each department. _(TCS BFSI)_
4. Write a query to pivot data to show sales by product as columns for each region. _(Deloitte)_
5. Write a query using a CTE to calculate running totals of sales by month. _(HCL)_

### Hard Questions
1. How would you write a query to find duplicate records in a table with millions of rows? _(Amazon)_
2. How do you optimize a query with multiple subqueries for better performance? _(Cognizant)_
3. What are window functions, and how do they differ from aggregate functions? _(Amazon)_
4. How do you handle recursive queries in SQL, and when are they useful? _(Deloitte)_
5. What is database partitioning, and how does it improve query performance? _(Infosys)_

### Hard Query Questions
1. Write a query to identify and delete duplicate rows in a table based on specific columns. _(Amazon)_
2. Write a query to find employees who have not placed any orders in the `orders` table. _(Deloitte)_
3. Write a query to find the top 3 products by sales in each region using window functions. _(Amazon)_
4. Write a recursive query to list all employees in a hierarchical organization structure. _(Cognizant)_
5. Write a query to detect and resolve overlapping date ranges in a `schedules` table. _(HCL)_

### Application-Based Questions
1. You need to generate a report summarizing sales data by region and product. How would you write the SQL query? _(HCL)_
2. Write a query to generate a report showing monthly sales totals for the past year. _(HCL)_
3. Design a query to identify customers who have not made a purchase in the last 6 months. _(Accenture)_
4. Write a query to calculate the year-over-year growth percentage for sales by product. _(Deloitte)_
5. How would you write a query to audit data changes in a table using a history table? _(Cognizant)_

---

## 2. Joins

### Basic Questions
1. What are the different types of joins in SQL? _(TCS, Capgemini)_
2. What is the difference between INNER JOIN and LEFT JOIN? _(Infosys)_
3. What is a self-join, and when would you use it? _(Wipro)_
4. What is a cross join, and how is it different from other joins? _(Accenture)_
5. What is a natural join, and what are its limitations? _(Cognizant)_

### Basic Query Questions
1. Write a query to join `employees` and `departments` tables to show employee names and their department names. _(TCS)_
2. Write a query to perform a left join between `customers` and `orders` to include all customers. _(Infosys)_
3. Write a query to join `products` and `categories` using a cross join. _(Accenture)_
4. Write a query to join `employees` with itself to find pairs of employees in the same department. _(Wipro)_
5. Write a query to join `orders` and `order_details` to get order IDs and product IDs. _(Capgemini)_

### Intermediate Questions
1. How does a FULL OUTER JOIN differ from a CROSS JOIN? _(Wipro)_
2. What happens when you join tables with missing or null values? _(Accenture)_
3. How do you handle joins with large datasets to avoid performance issues? _(Cognizant)_
4. What is the difference between a join and a subquery in terms of use cases? _(Infosys)_
5. How do you ensure data integrity when joining tables with foreign keys? _(TCS)_

### Intermediate Query Questions
1. Write a query to retrieve all customers and their orders, including customers with no orders. _(Wipro)_
2. Write a query to join `employees` and `projects` to find employees not assigned to any project. _(Deloitte)_
3. Write a query to join three tables (`orders`, `customers`, `order_details`) to get order details with customer names. _(Capgemini)_
4. Write a query to find departments with no employees using a left join. _(Accenture)_
5. Write a query to join `products` and `suppliers` to list products with supplier names, handling missing suppliers. _(HCL)_

### Advanced Questions
1. How do you optimize a query with multiple joins on large tables? _(Cognizant)_
2. What is a hash join, and when is it used by the database engine? _(Amazon)_
3. How do you handle joins in a distributed database environment? _(Deloitte)_
4. What are the performance implications of using non-equijoins? _(Infosys)_
5. How do you debug a query with incorrect join results? _(TCS Digital)_

### Advanced Query Questions
1. Write a query to join `sales` and `regions` tables to calculate total sales per region, handling missing regions. _(Cognizant)_
2. Write a query to perform a multi-table join to generate a report with customer, order, and product details. _(HCL)_
3. Write a query to join `employees` and `salaries` tables to find the latest salary for each employee. _(Deloitte)_
4. Write a query to join `orders` and `returns` to find orders with no returns using a left join. _(Accenture)_
5. Write a query to join `products` and `inventory` tables to find products with low stock levels. _(TCS)_

### Hard Questions
1. How do you optimize a query with nested joins across multiple large tables? _(Amazon)_
2. What are the challenges of joining tables across different databases? _(Deloitte)_
3. How do you handle joins with dynamic or unpredictable data volumes? _(Cognizant)_
4. What is a merge join, and when is it more efficient than other join types? _(Amazon)_
5. How do you ensure scalability when joining tables in a high-transaction environment? _(Infosys)_

### Hard Query Questions
1. Write a query to join `orders`, `customers`, and `payments` to find unpaid orders with customer details. _(Amazon)_
2. Write a query to join `employees` and `attendance` to find employees absent for more than 5 days. _(Deloitte)_
3. Write a query to join `products`, `sales`, and `regions` to rank products by sales within each region. _(Cognizant)_
4. Write a query to join `orders` and `shipments` to identify delayed shipments with order details. _(HCL)_
5. Write a query to join `employees` and `managers` hierarchically to list all reporting structures. _(TCS)_

### Application-Based Questions
1. Design a query to join `sales`, `products`, and `categories` to generate a sales report by category. _(HCL)_
2. Write a query to join `customers` and `orders` to identify repeat customers with multiple orders. _(Accenture)_
3. Write a query to join `employees`, `projects`, and `tasks` to track project progress by employee. _(Deloitte)_
4. Write a query to join `inventory` and `orders` to flag products at risk of stockout. _(Cognizant)_
5. Write a query to join `sales` and `regions` to analyze regional sales trends over time. _(TCS)_

---

## 3. Transactions

### Basic Questions
1. What is a database transaction, and what are the ACID properties? _(TCS, Infosys)_
2. What is the difference between COMMIT and ROLLBACK in SQL? _(Capgemini)_
3. What is a savepoint in a transaction, and how is it used? _(Wipro)_
4. What is the role of the BEGIN TRANSACTION statement? _(Accenture)_
5. How does a database ensure data consistency in transactions? _(Cognizant)_

### Basic Query Questions
1. Write a query to demonstrate a transaction that updates two related tables with rollback on failure. _(TCS)_
2. Write a query to insert a record into the `orders` table within a transaction. _(Infosys)_
3. Write a query to update the `inventory` table and log the change in a transaction. _(Capgemini)_
4. Write a query to delete records from `employees` with a transaction to ensure rollback on error. _(Wipro)_
5. Write a query to transfer funds between two accounts in a transaction. _(Accenture)_

### Intermediate Questions
1. How do you handle transaction isolation levels in SQL? _(Wipro)_
2. What is the difference between READ COMMITTED and SERIALIZABLE isolation levels? _(Cognizant)_
3. How do you manage transaction errors in a stored procedure? _(Accenture)_
4. What is a nested transaction, and how does it work? _(Infosys)_
5. How do you ensure transaction atomicity in a distributed database? _(Deloitte)_

### Intermediate Query Questions
1. Write a query to log transaction errors to an `error_log` table within a stored procedure. _(Wipro)_
2. Write a transaction to update `orders` and `order_details` tables with error handling. _(Capgemini)_
3. Write a query to implement a savepoint in a transaction for partial rollback. _(Accenture)_
4. Write a stored procedure to transfer stock between warehouses in a transaction. _(HCL)_
5. Write a query to audit transaction changes in a `history` table. _(Cognizant)_

### Advanced Questions
1. How do you debug a deadlock in a production database? _(Cognizant)_
2. What is two-phase commit, and when is it used in transactions? _(Deloitte)_
3. How do you analyze transaction performance using database tools? _(Amazon)_
4. What are the trade-offs of different transaction isolation levels? _(Infosys)_
5. How do you handle long-running transactions in a high-throughput system? _(TCS Digital)_

### Advanced Query Questions
1. Write a query to identify recent deadlocks in a database using system views (e.g., SQL Server’s `sys.dm_tran_locks`). _(Cognizant)_
2. Write a query to monitor transaction logs for long-running transactions. _(Amazon)_
3. Write a stored procedure to handle batch updates with transaction retry logic. _(Deloitte)_
4. Write a query to detect uncommitted transactions in a database. _(HCL)_
5. Write a query to analyze transaction wait times using database performance views. _(Accenture)_

### Hard Questions
1. A query works locally but fails in production with a timeout. How would you diagnose and resolve this? _(Deloitte)_
2. How do you prevent deadlocks in a high-concurrency database environment? _(Amazon)_
3. What are the challenges of managing transactions in a distributed database? _(Cognizant)_
4. How do you optimize transaction performance in a multi-user system? _(Infosys)_
5. How do you recover a database after a transaction failure in production? _(TCS)_

### Hard Query Questions
1. Write a query to analyze query performance using database profiling tools or system views. _(Deloitte)_
2. Write a transaction to synchronize data between two databases with error handling. _(Amazon)_
3. Write a query to detect and resolve transaction conflicts in a high-concurrency system. _(Cognizant)_
4. Write a stored procedure to handle distributed transactions across two databases. _(Deloitte)_
5. Write a query to monitor and alert on transaction deadlocks in real-time. _(HCL)_

### Application-Based Questions
1. Your Java application logs database errors but lacks context. How would you improve error handling and logging? _(HCL)_
2. Write a query to trace and log slow-running queries in a production database. _(HCL)_
3. Design a transaction system to handle order processing with inventory updates and error logging. _(Accenture)_
4. Write a query to implement a transaction for a payment processing system with rollback on failure. _(TCS)_
5. How would you design a transaction audit system for a financial application? _(Deloitte)_

---

## 4. Integration with Java Applications

### Basic Questions
1. How do you connect a Java application to a database using JDBC? _(TCS, Infosys)_
2. What is the difference between Statement and PreparedStatement in JDBC? _(Capgemini)_
3. What is a ResultSet, and how do you iterate through it in Java? _(Wipro)_
4. What is the role of the DriverManager class in JDBC? _(Accenture)_
5. How do you close JDBC resources properly to avoid leaks? _(Cognizant)_

### Basic Query Questions
1. Write a Java method using JDBC to execute a SELECT query on the `employees` table. _(TCS)_
2. Write a Java method to insert a record into the `products` table using Statement. _(Infosys)_
3. Write a Java method to update a record in the `orders` table using JDBC. _(Capgemini)_
4. Write a Java method to delete records from the `customers` table using JDBC. _(Wipro)_
5. Write a Java method to retrieve all records from the `departments` table using ResultSet. _(Accenture)_

### Intermediate Questions
1. How do you handle database transactions in a Java application? _(Wipro)_
2. How do you map query results to Java objects efficiently? _(Accenture)_
3. What is the difference between executeQuery, executeUpdate, and execute in JDBC? _(Cognizant)_
4. How do you use PreparedStatement to prevent SQL injection? _(Infosys)_
5. What is the role of Connection.setAutoCommit in JDBC transactions? _(TCS)_

### Intermediate Query Questions
1. Write a Java method using PreparedStatement to insert a record into the `orders` table. _(Wipro)_
2. Write a Java method to execute a batch insert into the `products` table using JDBC. _(Cognizant)_
3. Write a Java method to map a ResultSet to a list of Employee objects. _(Accenture)_
4. Write a Java method to execute a parameterized SELECT query with WHERE clause. _(Infosys)_
5. Write a Java method to handle a transaction updating `orders` and `inventory` tables. _(TCS)_

### Advanced Questions
1. How do you implement connection pooling in a Java application? _(Cognizant)_
2. What is the difference between JDBC and ORM frameworks like Hibernate? _(Deloitte)_
3. How do you handle large ResultSets efficiently in Java? _(Amazon)_
4. How do you optimize JDBC performance for high-throughput applications? _(Infosys)_
5. What are the benefits of using DataSource over DriverManager in JDBC? _(TCS Digital)_

### Advanced Query Questions
1. Write a Java method to execute a batch update of multiple records using JDBC. _(Cognizant)_
2. Write a Java method to execute a complex query with joins and map results to a custom object. _(Amazon)_
3. Write a Java method to handle pagination for a large dataset using JDBC. _(Deloitte)_
4. Write a Java method to execute a stored procedure with input and output parameters. _(HCL)_
5. Write a Java method to stream a large ResultSet to avoid memory issues. _(Accenture)_

### Hard Questions
1. How would you optimize a Java application’s database interactions for high throughput? _(Amazon)_
2. How do you handle connection leaks in a Java application? _(Deloitte)_
3. How do you implement retry logic for failed database transactions in Java? _(Cognizant)_
4. How do you secure sensitive data in JDBC connections? _(Infosys)_
5. How do you handle database connection failures in a production environment? _(TCS)_

### Hard Query Questions
1. Write a Java method to execute a transaction across multiple tables with error handling. _(HCL)_
2. Write a Java method to execute a dynamic SQL query with user input safely. _(Amazon)_
3. Write a Java method to handle a transaction with retry logic for deadlocks. _(Cognizant)_
4. Write a Java method to log database query performance metrics. _(Deloitte)_
5. Write a Java method to execute a query with connection pooling and error recovery. _(TCS)_

### Application-Based Questions
1. Your Java application experiences slow database queries in production. How would you identify and resolve the issue? _(HCL)_
2. Write a Java method to handle a transaction for an order processing system with inventory updates. _(Accenture)_
3. Design a Java method to generate a report from a complex SQL query with joins and aggregations. _(Deloitte)_
4. Write a Java method to implement a retry mechanism for failed database operations. _(Cognizant)_
5. How would you design a Java application to handle batch processing of SQL queries efficiently? _(TCS)_

---

## 5. Indexes and Performance Optimization

### Basic Questions
1. What is an index in a database, and how does it improve query performance? _(TCS, Infosys)_
2. What is the difference between a clustered and non-clustered index? _(Capgemini)_
3. What are the disadvantages of creating too many indexes? _(Wipro)_
4. What is a primary index, and how is it different from a secondary index? _(Accenture)_
5. How do you check if an index exists on a table? _(Cognizant)_

### Basic Query Questions
1. Write a query to create a non-clustered index on the `email` column of the `customers` table. _(TCS)_
2. Write a query to drop an index named `idx_email` from the `customers` table. _(Infosys)_
3. Write a query to create a clustered index on the `order_id` column of the `orders` table. _(Capgemini)_
4. Write a query to check the indexes on the `employees` table in SQL Server. _(Wipro)_
5. Write a query to create a composite index on `department_id` and `salary` in the `employees` table. _(Accenture)_

### Intermediate Questions
1. How do you decide which columns to index in a table? _(Wipro)_
2. What is a covering index, and how does it improve query performance? _(Cognizant)_
3. How do you analyze a query execution plan to identify performance issues? _(Accenture)_
4. What is index fragmentation, and how do you resolve it? _(Infosys)_
5. How do indexes impact INSERT, UPDATE, and DELETE operations? _(TCS)_

### Intermediate Query Questions
1. Write a query to rebuild an index on the `orders` table to reduce fragmentation. _(Wipro)_
2. Write a query to create a covering index for a query selecting `customer_id`, `order_date` from `orders`. _(Cognizant)_
3. Write a query to check index fragmentation on the `employees` table in SQL Server. _(Accenture)_
4. Write a query to create a unique index on the `email` column of the `users` table. _(Infosys)_
5. Write a query to analyze the execution plan of a SELECT query on the `sales` table. _(TCS)_

### Advanced Questions
1. How do you optimize a query using indexes for large datasets? _(Cognizant)_
2. What is the difference between a B-tree and a bitmap index? _(Amazon)_
3. How do you handle index maintenance in a high-transaction database? _(Deloitte)_
4. What is a filtered index, and when would you use it? _(Infosys)_
5. How do you balance index usage with storage constraints? _(TCS Digital)_

### Advanced Query Questions
1. Write a query to create a filtered index on `orders` for `status = 'pending'`. _(Cognizant)_
2. Write a query to monitor index usage statistics in a database. _(Amazon)_
3. Write a query to rebuild all indexes on the `sales` table in SQL Server. _(Deloitte)_
4. Write a query to create a bitmap index on a low-cardinality column like `gender` in MySQL. _(HCL)_
5. Write a query to analyze missing indexes for a slow-running query using system views. _(Accenture)_

### Hard Questions
1. How do you optimize a database with millions of rows using indexing strategies? _(Amazon)_
2. What are the challenges of indexing in a distributed database environment? _(Deloitte)_
3. How do you handle index maintenance during peak transaction times? _(Cognizant)_
4. What is the impact of indexing on a read-heavy vs. write-heavy database? _(Infosys)_
5. How do you troubleshoot a query ignoring an existing index? _(TCS)_

### Hard Query Questions
1. Write a query to identify unused indexes in a database and drop them. _(Amazon)_
2. Write a query to create an index for a complex query with multiple joins and filters. _(Deloitte)_
3. Write a query to monitor index performance impact on a high-transaction table. _(Cognizant)_
4. Write a query to defragment an index without locking the table. _(HCL)_
5. Write a query to analyze index contention issues in a production database. _(TCS)_

### Application-Based Questions
1. Design an indexing strategy for a sales reporting system with frequent queries. _(HCL)_
2. Write a query to optimize a slow-running report query using appropriate indexes. _(Accenture)_
3. How would you implement indexing for a real-time analytics dashboard? _(Deloitte)_
4. Write a query to create indexes for a high-traffic e-commerce database. _(Cognizant)_
5. How would you monitor and maintain indexes in a production database? _(TCS)_

---

## 6. Stored Procedures and Triggers

### Basic Questions
1. What is a stored procedure, and how is it different from a function? _(TCS, Infosys)_
2. What is a trigger, and what are its common use cases? _(Capgemini)_
3. What are the advantages of using stored procedures in a database? _(Wipro)_
4. What is the difference between a DML trigger and a DDL trigger? _(Accenture)_
5. How do you call a stored procedure in SQL? _(Cognizant)_

### Basic Query Questions
1. Write a stored procedure to insert a record into the `employees` table. _(TCS)_
2. Write a trigger to log inserts into the `orders` table in an audit table. _(Infosys)_
3. Write a stored procedure to update the `products` table with a new price. _(Capgemini)_
4. Write a trigger to prevent deletion of records in the `customers` table. _(Wipro)_
5. Write a stored procedure to retrieve all orders for a given customer ID. _(Accenture)_

### Intermediate Questions
1. How do you handle errors in a stored procedure? _(Wipro)_
2. What are the performance implications of using triggers? _(Cognizant)_
3. How do you pass parameters to a stored procedure? _(Accenture)_
4. What is the difference between AFTER and INSTEAD OF triggers? _(Infosys)_
5. How do you debug a stored procedure in a database? _(TCS)_

### Intermediate Query Questions
1. Write a stored procedure to calculate the total sales for a given month. _(Wipro)_
2. Write a trigger to update a `stock` table when an order is placed. _(Cognizant)_
3. Write a stored procedure with input and output parameters to validate customer data. _(Accenture)_
4. Write a trigger to log changes to the `employees` table in a history table. _(Infosys)_
5. Write a stored procedure to batch update salaries based on department. _(TCS)_

### Advanced Questions
1. How do you optimize a stored procedure for performance? _(Cognizant)_
2. What are the security risks of using triggers, and how do you mitigate them? _(Amazon)_
3. How do you handle transaction management in a stored procedure? _(Deloitte)_
4. What is a recursive stored procedure, and when is it useful? _(Infosys)_
5. How do you version control stored procedures in a database? _(TCS Digital)_

### Advanced Query Questions
1. Write a stored procedure to handle a complex transaction across multiple tables. _(Cognizant)_
2. Write a trigger to enforce business rules on the `orders` table during updates. _(Amazon)_
3. Write a stored procedure to generate a report with dynamic SQL. _(Deloitte)_
4. Write a trigger to synchronize data between two tables on insert. _(HCL)_
5. Write a stored procedure to process batch inserts with error logging. _(Accenture)_

### Hard Questions
1. How do you troubleshoot a performance issue caused by a trigger in production? _(Amazon)_
2. How do you manage stored procedures in a multi-tenant database? _(Deloitte)_
3. What are the challenges of using triggers in a high-concurrency environment? _(Cognizant)_
4. How do you optimize a stored procedure with dynamic SQL for performance? _(Infosys)_
5. How do you handle stored procedure dependencies in a large database? _(TCS)_

### Hard Query Questions
1. Write a stored procedure to handle data migration between two schemas with validation. _(Amazon)_
2. Write a trigger to prevent circular references in a hierarchical table. _(Deloitte)_
3. Write a stored procedure to process a queue of tasks with retry logic. _(Cognizant)_
4. Write a trigger to audit all DML operations on a sensitive table. _(HCL)_
5. Write a stored procedure to handle recursive data processing for a tree structure. _(TCS)_

### Application-Based Questions
1. Design a stored procedure to automate monthly sales report generation. _(HCL)_
2. Write a trigger to enforce data consistency in an inventory management system. _(Accenture)_
3. Write a stored procedure to handle payment processing with validation and logging. _(Deloitte)_
4. Design a trigger to notify users of critical database changes in real-time. _(Cognizant)_
5. How would you implement a stored procedure for a batch job in a financial system? _(TCS)_

---

## 7. Database Design

### Basic Questions
1. What is database normalization, and what are the first three normal forms? _(TCS, Infosys)_
2. What is denormalization, and when would you use it? _(Capgemini)_
3. What is an ER diagram, and how is it used in database design? _(Wipro)_
4. What is the difference between a primary key and a foreign key? _(Accenture)_
5. What are constraints in a database, and what are their types? _(Cognizant)_

### Basic Query Questions
1. Write a query to create a table `employees` with primary and foreign key constraints. _(TCS)_
2. Write a query to create a table `orders` with a composite primary key. _(Infosys)_
3. Write a query to add a foreign key constraint to the `order_details` table. _(Capgemini)_
4. Write a query to create a table with a CHECK constraint on the `salary` column. _(Wipro)_
5. Write a query to drop a foreign key constraint from the `employees` table. _(Accenture)_

### Intermediate Questions
1. How do you design a database schema for a given business requirement? _(Wipro)_
2. What is the difference between 3NF and BCNF? _(Cognizant)_
3. How do you handle many-to-many relationships in a database schema? _(Accenture)_
4. What are the trade-offs of normalization vs. denormalization? _(Infosys)_
5. How do you ensure data integrity in a database design? _(TCS)_

### Intermediate Query Questions
1. Write a query to create a schema for a library management system with books and authors. _(Wipro)_
2. Write a query to create tables for a many-to-many relationship between `students` and `courses`. _(Cognizant)_
3. Write a query to alter a table to add a NOT NULL constraint to an existing column. _(Accenture)_
4. Write a query to create a table with a default value for the `status` column. _(Infosys)_
5. Write a query to design a schema for an e-commerce order system. _(TCS)_

### Advanced Questions
1. How do you design a database for high scalability and performance? _(Cognizant)_
2. What is sharding, and how does it impact database design? _(Amazon)_
3. How do you handle schema migrations in a production database? _(Deloitte)_
4. What are the challenges of designing a database for a multi-tenant application? _(Infosys)_
5. How do you model hierarchical data in a relational database? _(TCS Digital)_

### Advanced Query Questions
1. Write a query to create a partitioned table for `sales` data by year. _(Cognizant)_
2. Write a query to design a schema for a multi-tenant application with tenant isolation. _(Amazon)_
3. Write a query to migrate data to a new table schema with minimal downtime. _(Deloitte)_
4. Write a query to create a table for storing hierarchical employee data. _(HCL)_
5. Write a query to create a schema for a real-time analytics system. _(Accenture)_

### Hard Questions
1. How do you design a database to handle millions of transactions per day? _(Amazon)_
2. What are the challenges of designing a database for real-time applications? _(Deloitte)_
3. How do you ensure backward compatibility during schema changes? _(Cognizant)_
4. How do you design a database to support audit trails for all changes? _(Infosys)_
5. How do you optimize a database schema for reporting and analytics? _(TCS)_

### Hard Query Questions
1. Write a query to create a schema for a high-transaction banking system. _(Amazon)_
2. Write a query to design a database for a real-time inventory tracking system. _(Deloitte)_
3. Write a query to create a schema with audit tables for tracking data changes. _(Cognizant)_
4. Write a query to partition a table for high-volume log data by date. _(HCL)_
5. Write a query to create a schema for a global e-commerce platform with regional data. _(TCS)_

### Application-Based Questions
1. Design a database schema for an e-commerce platform with products, orders, and customers. _(HCL)_
2. Write a query to create a schema for a project management system with tasks and users. _(Accenture)_
3. Design a database schema for a financial application with transactions and accounts. _(Deloitte)_
4. Write a query to create a schema for a healthcare system with patients and appointments. _(Cognizant)_
5. How would you design a database for a real-time logistics tracking system? _(TCS)_

---

### Tips for Answering in an Interview
1. **Be Concise**: For conceptual questions, provide clear definitions and examples in 2-3 sentences (e.g., for ACID properties, briefly explain Atomicity, Consistency, Isolation, Durability).
2. **Explain Your Approach**: For query or coding questions, outline your logic first (e.g., "I’ll use a LEFT JOIN to include all customers, even those without orders"), then provide the SQL or Java code.
3. **Address Edge Cases**: For advanced/hard questions, mention considerations like NULL handling, performance optimization, or error recovery to demonstrate expertise.
4. **Focus on Optimization**: For performance-related questions, discuss indexing, query plans, connection pooling, or batch processing to show depth.
5. **Practice Common Patterns**: Prioritize JOINs, GROUP BY, transactions, JDBC integration, indexing, stored procedures, and schema design, as these are frequently tested in MNC interviews.

This updated list ensures comprehensive coverage of SQL topics for senior Java backend developers, including newly added sections for Indexes and Performance Optimization, Stored Procedures and Triggers, and Database Design. All questions are aligned with recent MNC interview trends (2023-2025) and tagged with relevant companies. If you need sample answers or further clarification on any question, let me know!
