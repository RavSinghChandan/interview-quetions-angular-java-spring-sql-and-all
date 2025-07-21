# SQL Interview Questions for Senior Java Backend Developers (Full Stack)

This document contains the most frequently asked SQL interview questions, including query-writing questions, curated for senior Java backend developers transitioning to full-stack roles. Questions are organized by topic and include those repeatedly asked in interviews by companies like **TCS, Infosys, Cognizant, Capgemini**, etc., along with additional critical questions to ensure comprehensive coverage for senior-level interviews.

---

## 1. SQL Basics and Queries

### Basic Questions

1. What is SQL, and what are its main types of commands (DDL, DML, DCL, TCL)? _(Asked in TCS, Infosys)_

**🧩 Foundation:** SQL (Structured Query Language) is a standard language for managing and manipulating relational databases. It consists of four main command types:

**⚙️ Function:** Each command type serves a specific purpose in database management and data manipulation.

**🚀 Features:**
- **DDL (Data Definition Language):** Defines database structure
- **DML (Data Manipulation Language):** Manages data within tables
- **DCL (Data Control Language):** Controls access and permissions
- **TCL (Transaction Control Language):** Manages transactions

**🔁 Flow:**
```sql
-- DDL (Data Definition Language) - Define structure
CREATE TABLE employees (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    salary DECIMAL(10,2),
    department_id INT
);

ALTER TABLE employees ADD COLUMN email VARCHAR(255);
DROP TABLE employees;

-- DML (Data Manipulation Language) - Manage data
INSERT INTO employees (id, name, salary) VALUES (1, 'John Doe', 50000);
SELECT * FROM employees WHERE salary > 40000;
UPDATE employees SET salary = 55000 WHERE id = 1;
DELETE FROM employees WHERE id = 1;

-- DCL (Data Control Language) - Control access
GRANT SELECT, INSERT ON employees TO user1;
REVOKE DELETE ON employees FROM user2;

-- TCL (Transaction Control Language) - Manage transactions
BEGIN TRANSACTION;
UPDATE employees SET salary = salary * 1.1;
COMMIT;

BEGIN TRANSACTION;
DELETE FROM employees WHERE id = 1;
ROLLBACK; -- Undo the delete
```
2. What is the difference between SELECT, INSERT, UPDATE, and DELETE statements? _(Asked in Capgemini)_

**🧩 Foundation:** These are the four fundamental DML operations in SQL, each serving a specific purpose in data manipulation.

**⚙️ Function:** SELECT retrieves data, INSERT adds new data, UPDATE modifies existing data, and DELETE removes data.

**🔁 Flow:**
```sql
-- SELECT - Retrieve data (READ operation)
SELECT id, name, salary FROM employees WHERE department_id = 1;
SELECT * FROM employees ORDER BY salary DESC;
SELECT COUNT(*) FROM employees WHERE salary > 50000;

-- INSERT - Add new data (CREATE operation)
INSERT INTO employees (id, name, salary, department_id) 
VALUES (1, 'John Doe', 50000, 1);

INSERT INTO employees (id, name, salary, department_id) 
VALUES 
    (2, 'Jane Smith', 60000, 1),
    (3, 'Bob Johnson', 45000, 2);

-- UPDATE - Modify existing data (UPDATE operation)
UPDATE employees 
SET salary = 55000, email = 'john.doe@company.com' 
WHERE id = 1;

UPDATE employees 
SET salary = salary * 1.1 
WHERE department_id = 1;

-- DELETE - Remove data (DELETE operation)
DELETE FROM employees WHERE id = 1;
DELETE FROM employees WHERE department_id = 2;
DELETE FROM employees; -- Removes all records (dangerous!)
```
3. How do you use WHERE, ORDER BY, and GROUP BY clauses? _(Asked in Wipro)_

**🧩 Foundation:** These clauses are fundamental for filtering, sorting, and aggregating data in SQL queries.

**⚙️ Function:** WHERE filters rows, ORDER BY sorts results, and GROUP BY groups rows for aggregation.

**🔁 Flow:**
```sql
-- WHERE - Filter rows based on conditions
SELECT * FROM employees WHERE salary > 50000;
SELECT * FROM employees WHERE department_id = 1 AND salary > 40000;
SELECT * FROM employees WHERE name LIKE 'J%';
SELECT * FROM employees WHERE salary BETWEEN 40000 AND 60000;
SELECT * FROM employees WHERE department_id IN (1, 2, 3);

-- ORDER BY - Sort results
SELECT name, salary FROM employees ORDER BY salary DESC;
SELECT name, department_id, salary FROM employees 
ORDER BY department_id ASC, salary DESC;

-- GROUP BY - Group rows for aggregation
SELECT department_id, COUNT(*) as emp_count, AVG(salary) as avg_salary
FROM employees 
GROUP BY department_id;

SELECT department_id, 
       COUNT(*) as emp_count, 
       AVG(salary) as avg_salary,
       MAX(salary) as max_salary,
       MIN(salary) as min_salary
FROM employees 
GROUP BY department_id
HAVING AVG(salary) > 45000
ORDER BY avg_salary DESC;
```

### Basic Query Questions

1. Write a query to retrieve all columns from a table named employees. _(Asked in TCS)_

**🧩 Foundation:** The SELECT statement with * retrieves all columns from a table.

**⚙️ Function:** This is the most basic form of data retrieval in SQL.

**🔁 Flow:**
```sql
-- Basic SELECT with all columns
SELECT * FROM employees;

-- More specific approach (recommended for production)
SELECT id, name, email, salary, department_id, hire_date 
FROM employees;

-- With WHERE clause for filtering
SELECT * FROM employees WHERE department_id = 1;

-- With ORDER BY for sorting
SELECT * FROM employees ORDER BY name ASC;

-- Combining all clauses
SELECT * FROM employees 
WHERE department_id = 1 
ORDER BY salary DESC;
```
2. Write a query to insert a new record into a table named products. _(Asked in Infosys)_

**🧩 Foundation:** The INSERT statement adds new records to a table.

**⚙️ Function:** INSERT is used to populate tables with new data.

**🔁 Flow:**
```sql
-- Basic INSERT with all columns
INSERT INTO products (id, name, price, category, stock_quantity) 
VALUES (1, 'Laptop', 999.99, 'Electronics', 50);

-- INSERT with only some columns (others use defaults)
INSERT INTO products (name, price, category) 
VALUES ('Mouse', 29.99, 'Electronics');

-- INSERT multiple records
INSERT INTO products (id, name, price, category, stock_quantity) 
VALUES 
    (2, 'Keyboard', 59.99, 'Electronics', 100),
    (3, 'Monitor', 299.99, 'Electronics', 25),
    (4, 'Desk Chair', 199.99, 'Furniture', 30);

-- INSERT with SELECT (copy data from another table)
INSERT INTO products (name, price, category)
SELECT name, price, category FROM old_products WHERE active = 1;

-- INSERT with subquery
INSERT INTO products (name, price, category)
VALUES ('New Product', 
        (SELECT AVG(price) FROM products WHERE category = 'Electronics'),
        'Electronics');
```
3. Write a query to update the salary of an employee with a specific ID in the employees table. _(Asked in Capgemini)_

**🧩 Foundation:** The UPDATE statement modifies existing records in a table.

**⚙️ Function:** UPDATE is used to change data values in existing records.

**🔁 Flow:**
```sql
-- Basic UPDATE with WHERE clause
UPDATE employees 
SET salary = 55000 
WHERE id = 1;

-- UPDATE multiple columns
UPDATE employees 
SET salary = 55000, 
    email = 'john.doe@company.com',
    last_updated = CURRENT_TIMESTAMP
WHERE id = 1;

-- UPDATE with calculation
UPDATE employees 
SET salary = salary * 1.1 
WHERE department_id = 1;

-- UPDATE with subquery
UPDATE employees 
SET salary = (SELECT AVG(salary) FROM employees WHERE department_id = 1)
WHERE id = 1;

-- UPDATE with CASE statement
UPDATE employees 
SET salary = CASE 
    WHEN department_id = 1 THEN salary * 1.1
    WHEN department_id = 2 THEN salary * 1.05
    ELSE salary * 1.02
END
WHERE active = 1;

-- Safe UPDATE with LIMIT (MySQL)
UPDATE employees 
SET salary = 55000 
WHERE id = 1 
LIMIT 1;
```

### Intermediate Questions

1. What is the difference between HAVING and WHERE clauses? _(Asked in Accenture)_

**🧩 Foundation:** WHERE filters individual rows before grouping, while HAVING filters groups after GROUP BY.

**⚙️ Function:** WHERE is used for row-level filtering, HAVING for group-level filtering with aggregate functions.

**🔁 Flow:**
```sql
-- WHERE - Filters rows before grouping
SELECT department_id, AVG(salary) as avg_salary
FROM employees 
WHERE salary > 30000  -- Filters individual employees
GROUP BY department_id;

-- HAVING - Filters groups after grouping
SELECT department_id, AVG(salary) as avg_salary
FROM employees 
GROUP BY department_id
HAVING AVG(salary) > 50000;  -- Filters departments

-- Combining WHERE and HAVING
SELECT department_id, COUNT(*) as emp_count, AVG(salary) as avg_salary
FROM employees 
WHERE salary > 30000  -- Filter employees first
GROUP BY department_id
HAVING COUNT(*) > 5 AND AVG(salary) > 45000;  -- Filter groups

-- WHERE with aggregate functions (ERROR - won't work)
-- SELECT department_id, AVG(salary) 
-- FROM employees 
-- WHERE AVG(salary) > 50000;  -- This will fail

-- HAVING without GROUP BY (ERROR - won't work)
-- SELECT name, salary 
-- FROM employees 
-- HAVING salary > 50000;  -- This will fail

-- Correct usage examples
SELECT department_id, 
       COUNT(*) as emp_count,
       AVG(salary) as avg_salary,
       MAX(salary) as max_salary
FROM employees 
WHERE hire_date > '2020-01-01'  -- Filter by hire date
GROUP BY department_id
HAVING COUNT(*) >= 3 AND AVG(salary) > 40000;  -- Filter groups
```
2. How do you use aggregate functions like COUNT, SUM, AVG, MIN, and MAX? _(Asked in TCS)_

**🧩 Foundation:** Aggregate functions perform calculations on sets of values and return a single result.

**⚙️ Function:** These functions are used for data analysis and reporting, often with GROUP BY clauses.

**🔁 Flow:**
```sql
-- COUNT - Count rows or non-null values
SELECT COUNT(*) FROM employees;  -- Total employees
SELECT COUNT(email) FROM employees;  -- Employees with email
SELECT COUNT(DISTINCT department_id) FROM employees;  -- Unique departments

-- SUM - Sum of numeric values
SELECT SUM(salary) FROM employees;  -- Total salary budget
SELECT department_id, SUM(salary) as total_salary
FROM employees 
GROUP BY department_id;

-- AVG - Average of numeric values
SELECT AVG(salary) FROM employees;  -- Average salary
SELECT department_id, AVG(salary) as avg_salary
FROM employees 
GROUP BY department_id;

-- MIN - Minimum value
SELECT MIN(salary) FROM employees;  -- Lowest salary
SELECT department_id, MIN(salary) as min_salary
FROM employees 
GROUP BY department_id;

-- MAX - Maximum value
SELECT MAX(salary) FROM employees;  -- Highest salary
SELECT department_id, MAX(salary) as max_salary
FROM employees 
GROUP BY department_id;

-- Combining multiple aggregate functions
SELECT 
    department_id,
    COUNT(*) as emp_count,
    SUM(salary) as total_salary,
    AVG(salary) as avg_salary,
    MIN(salary) as min_salary,
    MAX(salary) as max_salary
FROM employees 
GROUP BY department_id;

-- Aggregate functions with WHERE
SELECT 
    AVG(salary) as avg_salary,
    COUNT(*) as emp_count
FROM employees 
WHERE department_id = 1;

-- Aggregate functions with HAVING
SELECT 
    department_id,
    AVG(salary) as avg_salary,
    COUNT(*) as emp_count
FROM employees 
GROUP BY department_id
HAVING AVG(salary) > 50000 AND COUNT(*) > 5;
```
3. What is the purpose of the DISTINCT keyword in SQL? _(Asked in Infosys)_

**🧩 Foundation:** DISTINCT eliminates duplicate rows from query results, returning only unique values.

**⚙️ Function:** DISTINCT is used to remove duplicates and get unique combinations of values.

**🔁 Flow:**
```sql
-- Basic DISTINCT usage
SELECT DISTINCT department_id FROM employees;

-- DISTINCT with multiple columns
SELECT DISTINCT department_id, job_title FROM employees;

-- DISTINCT vs non-DISTINCT comparison
SELECT department_id FROM employees;  -- May show duplicates
SELECT DISTINCT department_id FROM employees;  -- No duplicates

-- DISTINCT with aggregate functions
SELECT COUNT(DISTINCT department_id) FROM employees;  -- Count unique departments
SELECT COUNT(DISTINCT job_title) FROM employees;  -- Count unique job titles

-- DISTINCT with WHERE clause
SELECT DISTINCT department_id 
FROM employees 
WHERE salary > 50000;

-- DISTINCT with ORDER BY
SELECT DISTINCT department_id, job_title 
FROM employees 
ORDER BY department_id, job_title;

-- DISTINCT with JOIN
SELECT DISTINCT e.department_id, d.department_name
FROM employees e
JOIN departments d ON e.department_id = d.id;

-- DISTINCT with subquery
SELECT name, salary 
FROM employees 
WHERE department_id IN (
    SELECT DISTINCT department_id 
    FROM employees 
    WHERE salary > 60000
);

-- DISTINCT vs GROUP BY (similar results)
SELECT DISTINCT department_id, AVG(salary) as avg_salary
FROM employees;  -- This won't work as expected

SELECT department_id, AVG(salary) as avg_salary
FROM employees 
GROUP BY department_id;  -- Correct way
```

### Intermediate Query Questions

1. Write a query to find the average salary of employees grouped by department. _(Asked in Wipro)_

**🧩 Foundation:** This query uses GROUP BY to aggregate data by department and AVG function to calculate average salary.

**⚙️ Function:** GROUP BY groups rows by department_id, then AVG calculates the average salary for each group.

**🔁 Flow:**
```sql
-- Basic average salary by department
SELECT department_id, AVG(salary) as avg_salary
FROM employees 
GROUP BY department_id;

-- With department name using JOIN
SELECT d.department_name, AVG(e.salary) as avg_salary
FROM employees e
JOIN departments d ON e.department_id = d.id
GROUP BY d.department_name;

-- With additional aggregate functions
SELECT 
    department_id,
    COUNT(*) as emp_count,
    AVG(salary) as avg_salary,
    MIN(salary) as min_salary,
    MAX(salary) as max_salary,
    SUM(salary) as total_salary
FROM employees 
GROUP BY department_id;

-- With HAVING to filter departments
SELECT department_id, AVG(salary) as avg_salary
FROM employees 
GROUP BY department_id
HAVING AVG(salary) > 50000;

-- With WHERE to filter employees first
SELECT department_id, AVG(salary) as avg_salary
FROM employees 
WHERE active = 1  -- Only active employees
GROUP BY department_id;

-- With ORDER BY for sorted results
SELECT department_id, AVG(salary) as avg_salary
FROM employees 
GROUP BY department_id
ORDER BY avg_salary DESC;

-- Complete example with all clauses
SELECT 
    d.department_name,
    COUNT(e.id) as emp_count,
    AVG(e.salary) as avg_salary,
    ROUND(AVG(e.salary), 2) as avg_salary_rounded
FROM employees e
JOIN departments d ON e.department_id = d.id
WHERE e.active = 1
GROUP BY d.department_name
HAVING COUNT(e.id) > 2
ORDER BY avg_salary DESC;
```
2. Write a query to retrieve unique customer emails from the customers table. _(Asked in Accenture)_

**🧩 Foundation:** This query uses DISTINCT to eliminate duplicate email addresses from the customers table.

**⚙️ Function:** DISTINCT ensures only unique email values are returned, removing any duplicates.

**🔁 Flow:**
```sql
-- Basic DISTINCT query for unique emails
SELECT DISTINCT email FROM customers;

-- With additional filtering
SELECT DISTINCT email 
FROM customers 
WHERE email IS NOT NULL;

-- With WHERE clause for active customers
SELECT DISTINCT email 
FROM customers 
WHERE active = 1 AND email IS NOT NULL;

-- With ORDER BY for sorted results
SELECT DISTINCT email 
FROM customers 
WHERE email IS NOT NULL
ORDER BY email;

-- With multiple columns (unique combinations)
SELECT DISTINCT email, customer_type 
FROM customers 
WHERE email IS NOT NULL;

-- Using GROUP BY as alternative to DISTINCT
SELECT email 
FROM customers 
WHERE email IS NOT NULL
GROUP BY email;

-- With COUNT to see how many unique emails
SELECT COUNT(DISTINCT email) as unique_email_count
FROM customers 
WHERE email IS NOT NULL;

-- With JOIN to get customer names with unique emails
SELECT DISTINCT c.email, c.name
FROM customers c
WHERE c.email IS NOT NULL
ORDER BY c.name;

-- With subquery to find customers with duplicate emails
SELECT email, COUNT(*) as email_count
FROM customers 
WHERE email IS NOT NULL
GROUP BY email
HAVING COUNT(*) > 1;
```
3. Write a query to find the top 5 highest-paid employees in the employees table. _(Asked in TCS Digital)_

**🧩 Foundation:** This query uses ORDER BY to sort by salary in descending order and LIMIT to restrict results to top 5.

**⚙️ Function:** ORDER BY sorts employees by salary (highest first), then LIMIT returns only the first 5 rows.

**🔁 Flow:**
```sql
-- Basic top 5 highest-paid employees
SELECT name, salary 
FROM employees 
ORDER BY salary DESC 
LIMIT 5;

-- With additional columns
SELECT id, name, salary, department_id, email
FROM employees 
ORDER BY salary DESC 
LIMIT 5;

-- With department information using JOIN
SELECT e.name, e.salary, d.department_name
FROM employees e
JOIN departments d ON e.department_id = d.id
ORDER BY e.salary DESC 
LIMIT 5;

-- Using ROW_NUMBER() for ranking (SQL Server, PostgreSQL)
SELECT name, salary, 
       ROW_NUMBER() OVER (ORDER BY salary DESC) as rank
FROM employees 
ORDER BY salary DESC 
LIMIT 5;

-- Using RANK() to handle ties (SQL Server, PostgreSQL)
SELECT name, salary, 
       RANK() OVER (ORDER BY salary DESC) as rank
FROM employees 
ORDER BY salary DESC 
LIMIT 5;

-- With WHERE clause for active employees only
SELECT name, salary, department_id
FROM employees 
WHERE active = 1
ORDER BY salary DESC 
LIMIT 5;

-- Using subquery approach (alternative)
SELECT name, salary
FROM employees e1
WHERE (
    SELECT COUNT(*) 
    FROM employees e2 
    WHERE e2.salary > e1.salary
) < 5
ORDER BY salary DESC;

-- With additional filtering and sorting
SELECT 
    e.name, 
    e.salary, 
    d.department_name,
    e.hire_date
FROM employees e
JOIN departments d ON e.department_id = d.id
WHERE e.active = 1 AND e.salary > 30000
ORDER BY e.salary DESC, e.hire_date ASC
LIMIT 5;
```

### Advanced Questions

1. How do you write a query to retrieve the top N rows from a table? _(Asked in Cognizant)_

**🧩 Foundation:** To retrieve top N rows, use ORDER BY to sort and LIMIT to restrict the number of results.

**⚙️ Function:** ORDER BY sorts the data, then LIMIT returns only the first N rows.

**🔁 Flow:**
```sql
-- Basic top N rows
SELECT * FROM employees ORDER BY salary DESC LIMIT 10;

-- Top N with specific columns
SELECT name, salary, department_id 
FROM employees 
ORDER BY salary DESC 
LIMIT 5;

-- Top N with WHERE clause
SELECT name, salary 
FROM employees 
WHERE department_id = 1
ORDER BY salary DESC 
LIMIT 3;

-- Using ROW_NUMBER() for ranking (SQL Server, PostgreSQL)
SELECT name, salary,
       ROW_NUMBER() OVER (ORDER BY salary DESC) as rank
FROM employees 
ORDER BY salary DESC 
LIMIT 10;

-- Using RANK() to handle ties
SELECT name, salary,
       RANK() OVER (ORDER BY salary DESC) as rank
FROM employees 
ORDER BY salary DESC 
LIMIT 10;

-- Top N from each group (department)
SELECT name, salary, department_id,
       ROW_NUMBER() OVER (PARTITION BY department_id ORDER BY salary DESC) as dept_rank
FROM employees 
WHERE ROW_NUMBER() OVER (PARTITION BY department_id ORDER BY salary DESC) <= 3;

-- Using subquery approach
SELECT name, salary
FROM employees e1
WHERE (
    SELECT COUNT(*) 
    FROM employees e2 
    WHERE e2.salary > e1.salary
) < 10
ORDER BY salary DESC;

-- Top N with multiple sort criteria
SELECT name, salary, hire_date
FROM employees 
ORDER BY salary DESC, hire_date ASC
LIMIT 10;
```
2. What is the difference between UNION and UNION ALL? _(Asked in TCS Digital)_

**🧩 Foundation:** UNION combines results from multiple queries and removes duplicates, while UNION ALL keeps all rows including duplicates.

**⚙️ Function:** UNION performs DISTINCT operation, UNION ALL is faster as it doesn't remove duplicates.

**🔁 Flow:**
```sql
-- UNION - removes duplicates
SELECT name, salary FROM employees WHERE department_id = 1
UNION
SELECT name, salary FROM employees WHERE department_id = 2;

-- UNION ALL - keeps all rows including duplicates
SELECT name, salary FROM employees WHERE department_id = 1
UNION ALL
SELECT name, salary FROM employees WHERE department_id = 2;

-- Example with duplicate data
-- Table: employees_2023
SELECT name, salary FROM employees_2023
UNION  -- Removes duplicates
SELECT name, salary FROM employees_2024;

SELECT name, salary FROM employees_2023
UNION ALL  -- Keeps all rows
SELECT name, salary FROM employees_2024;

-- Performance comparison
-- UNION ALL is faster (no duplicate removal)
SELECT id, name FROM table1
UNION ALL
SELECT id, name FROM table2;

-- UNION is slower (removes duplicates)
SELECT id, name FROM table1
UNION
SELECT id, name FROM table2;

-- Multiple UNION operations
SELECT name, 'Active' as status FROM employees WHERE active = 1
UNION
SELECT name, 'Inactive' as status FROM employees WHERE active = 0
UNION
SELECT name, 'New' as status FROM new_employees;

-- With ORDER BY (must be at the end)
SELECT name, salary FROM employees WHERE department_id = 1
UNION
SELECT name, salary FROM employees WHERE department_id = 2
ORDER BY salary DESC;

-- Combining different tables
SELECT customer_name, email FROM customers
UNION
SELECT employee_name, email FROM employees
ORDER BY email;
```

### Advanced Query Questions

1. Write a query to combine records from two tables, orders_2023 and orders_2024, excluding duplicates. _(Asked in Cognizant)_

**🧩 Foundation:** Use UNION to combine records from both tables and automatically exclude duplicates.

**⚙️ Function:** UNION merges results and removes duplicate rows based on all columns.

**🔁 Flow:**
```sql
-- Basic UNION to combine and exclude duplicates
SELECT order_id, customer_id, amount, order_date 
FROM orders_2023
UNION
SELECT order_id, customer_id, amount, order_date 
FROM orders_2024;

-- With additional columns and filtering
SELECT order_id, customer_id, amount, order_date, '2023' as year
FROM orders_2023
WHERE amount > 100
UNION
SELECT order_id, customer_id, amount, order_date, '2024' as year
FROM orders_2024
WHERE amount > 100;

-- With ORDER BY for sorted results
SELECT order_id, customer_id, amount, order_date
FROM orders_2023
UNION
SELECT order_id, customer_id, amount, order_date
FROM orders_2024
ORDER BY order_date DESC;

-- Using UNION ALL if you want to see all records (including duplicates)
SELECT order_id, customer_id, amount, order_date
FROM orders_2023
UNION ALL
SELECT order_id, customer_id, amount, order_date
FROM orders_2024;

-- With JOIN to get customer information
SELECT o.order_id, o.customer_id, c.customer_name, o.amount, o.order_date
FROM (
    SELECT order_id, customer_id, amount, order_date FROM orders_2023
    UNION
    SELECT order_id, customer_id, amount, order_date FROM orders_2024
) o
JOIN customers c ON o.customer_id = c.id
ORDER BY o.order_date DESC;

-- With aggregate functions
SELECT customer_id, COUNT(*) as total_orders, SUM(amount) as total_amount
FROM (
    SELECT customer_id, amount FROM orders_2023
    UNION
    SELECT customer_id, amount FROM orders_2024
) combined_orders
GROUP BY customer_id
ORDER BY total_amount DESC;
```
2. Write a query to find the second-highest salary in the employees table. _(Asked in Infosys)_

**🧩 Foundation:** Use ORDER BY with LIMIT and OFFSET, or window functions like ROW_NUMBER() to find the second-highest salary.

**⚙️ Function:** Sort salaries in descending order and select the second row.

**🔁 Flow:**
```sql
-- Method 1: Using LIMIT and OFFSET
SELECT salary 
FROM employees 
ORDER BY salary DESC 
LIMIT 1 OFFSET 1;

-- Method 2: Using subquery with LIMIT
SELECT salary 
FROM employees 
ORDER BY salary DESC 
LIMIT 1 OFFSET 1;

-- Method 3: Using ROW_NUMBER() window function
SELECT salary 
FROM (
    SELECT salary, ROW_NUMBER() OVER (ORDER BY salary DESC) as rn
    FROM employees
) ranked
WHERE rn = 2;

-- Method 4: Using subquery to find max salary less than the maximum
SELECT MAX(salary) 
FROM employees 
WHERE salary < (SELECT MAX(salary) FROM employees);

-- Method 5: Using DENSE_RANK() to handle ties
SELECT salary 
FROM (
    SELECT salary, DENSE_RANK() OVER (ORDER BY salary DESC) as rank
    FROM employees
) ranked
WHERE rank = 2;

-- Method 6: Using self-join
SELECT e1.salary
FROM employees e1
WHERE 1 = (
    SELECT COUNT(DISTINCT e2.salary)
    FROM employees e2
    WHERE e2.salary > e1.salary
);

-- Method 7: Using TOP (SQL Server)
SELECT TOP 1 salary
FROM employees
WHERE salary < (SELECT MAX(salary) FROM employees)
ORDER BY salary DESC;

-- Method 8: With additional information
SELECT name, salary, department_id
FROM employees
ORDER BY salary DESC
LIMIT 1 OFFSET 1;

-- Method 9: Using LAG() window function
SELECT salary
FROM (
    SELECT salary, LAG(salary) OVER (ORDER BY salary DESC) as prev_salary
    FROM employees
) lagged
WHERE prev_salary IS NOT NULL
LIMIT 1;
```

### Tough Questions

1. How would you write a query to find duplicate records in a table with millions of rows? _(Asked in Amazon)_

**🧩 Foundation:** Use GROUP BY with HAVING COUNT(*) > 1, or window functions like ROW_NUMBER() to identify duplicate records efficiently.

**⚙️ Function:** Group by the columns that should be unique and count occurrences to find duplicates.

**🔁 Flow:**
```sql
-- Method 1: Using GROUP BY and HAVING
SELECT email, COUNT(*) as duplicate_count
FROM employees
GROUP BY email
HAVING COUNT(*) > 1;

-- Method 2: Using ROW_NUMBER() window function
SELECT id, name, email, salary
FROM (
    SELECT *, ROW_NUMBER() OVER (PARTITION BY email ORDER BY id) as rn
    FROM employees
) ranked
WHERE rn > 1;

-- Method 3: Using self-join for specific columns
SELECT e1.id, e1.name, e1.email
FROM employees e1
JOIN employees e2 ON e1.email = e2.email AND e1.id != e2.id;

-- Method 4: Using EXISTS
SELECT id, name, email
FROM employees e1
WHERE EXISTS (
    SELECT 1 FROM employees e2 
    WHERE e2.email = e1.email AND e2.id != e1.id
);

-- Method 5: Finding duplicates based on multiple columns
SELECT name, email, department_id, COUNT(*) as duplicate_count
FROM employees
GROUP BY name, email, department_id
HAVING COUNT(*) > 1;

-- Method 6: Using DENSE_RANK() to handle multiple duplicates
SELECT id, name, email
FROM (
    SELECT *, DENSE_RANK() OVER (PARTITION BY email ORDER BY id) as rank
    FROM employees
) ranked
WHERE rank > 1;

-- Method 7: Efficient approach for large tables (using CTE)
WITH duplicates AS (
    SELECT email, COUNT(*) as cnt
    FROM employees
    GROUP BY email
    HAVING COUNT(*) > 1
)
SELECT e.id, e.name, e.email, d.cnt
FROM employees e
JOIN duplicates d ON e.email = d.email
ORDER BY e.email, e.id;

-- Method 8: Using LAG() to find consecutive duplicates
SELECT id, name, email
FROM (
    SELECT *, LAG(email) OVER (ORDER BY email) as prev_email
    FROM employees
) lagged
WHERE email = prev_email;

-- Method 9: Performance optimized for millions of rows
SELECT email, COUNT(*) as duplicate_count
FROM employees
WHERE email IS NOT NULL
GROUP BY email
HAVING COUNT(*) > 1
ORDER BY duplicate_count DESC;
```

### Tough Query Questions

1. Write a query to identify and delete duplicate rows in a table based on specific columns. _(Asked in Amazon)_

**🧩 Foundation:** Use window functions like ROW_NUMBER() to identify duplicates and DELETE to remove them, keeping only one copy of each duplicate.

**⚙️ Function:** Partition by the columns that should be unique and keep only the first occurrence of each duplicate group.

**🔁 Flow:**
```sql
-- Method 1: Using ROW_NUMBER() to identify and delete duplicates
DELETE FROM employees 
WHERE id IN (
    SELECT id FROM (
        SELECT id, ROW_NUMBER() OVER (PARTITION BY email ORDER BY id) as rn
        FROM employees
    ) ranked
    WHERE rn > 1
);

-- Method 2: Using CTE for better readability
WITH duplicates AS (
    SELECT id, ROW_NUMBER() OVER (PARTITION BY email ORDER BY id) as rn
    FROM employees
)
DELETE FROM employees 
WHERE id IN (SELECT id FROM duplicates WHERE rn > 1);

-- Method 3: Delete duplicates based on multiple columns
DELETE FROM employees 
WHERE id IN (
    SELECT id FROM (
        SELECT id, ROW_NUMBER() OVER (PARTITION BY name, email ORDER BY id) as rn
        FROM employees
    ) ranked
    WHERE rn > 1
);

-- Method 4: Using self-join to delete duplicates
DELETE e1 FROM employees e1
JOIN employees e2 ON e1.email = e2.email AND e1.id > e2.id;

-- Method 5: Using EXISTS to delete duplicates
DELETE FROM employees e1
WHERE EXISTS (
    SELECT 1 FROM employees e2 
    WHERE e2.email = e1.email AND e2.id < e1.id
);

-- Method 6: Create new table without duplicates
CREATE TABLE employees_clean AS
SELECT * FROM (
    SELECT *, ROW_NUMBER() OVER (PARTITION BY email ORDER BY id) as rn
    FROM employees
) ranked
WHERE rn = 1;

-- Method 7: Using DENSE_RANK() for handling ties
DELETE FROM employees 
WHERE id IN (
    SELECT id FROM (
        SELECT id, DENSE_RANK() OVER (PARTITION BY email ORDER BY id) as rank
        FROM employees
    ) ranked
    WHERE rank > 1
);

-- Method 8: Safe approach - first identify, then delete
-- Step 1: Identify duplicates
SELECT email, COUNT(*) as duplicate_count
FROM employees
GROUP BY email
HAVING COUNT(*) > 1;

-- Step 2: Delete duplicates (keeping the one with lowest ID)
DELETE FROM employees 
WHERE id NOT IN (
    SELECT MIN(id) 
    FROM employees 
    GROUP BY email
);

-- Method 9: Using temporary table for large datasets
CREATE TEMPORARY TABLE temp_employees AS
SELECT * FROM employees WHERE 1=0;

INSERT INTO temp_employees
SELECT * FROM (
    SELECT *, ROW_NUMBER() OVER (PARTITION BY email ORDER BY id) as rn
    FROM employees
) ranked
WHERE rn = 1;

DROP TABLE employees;
RENAME TABLE temp_employees TO employees;
```
2. Write a query to find employees who have not placed any orders in the orders table. _(Asked in Deloitte)_

**🧩 Foundation:** Use LEFT JOIN with NULL check, NOT EXISTS, or NOT IN to find employees who don't have corresponding records in the orders table.

**⚙️ Function:** Find employees who are not present in the orders table (employees with no orders).

**🔁 Flow:**
```sql
-- Method 1: Using LEFT JOIN with NULL check
SELECT e.id, e.name, e.email
FROM employees e
LEFT JOIN orders o ON e.id = o.employee_id
WHERE o.employee_id IS NULL;

-- Method 2: Using NOT EXISTS
SELECT e.id, e.name, e.email
FROM employees e
WHERE NOT EXISTS (
    SELECT 1 FROM orders o WHERE o.employee_id = e.id
);

-- Method 3: Using NOT IN
SELECT e.id, e.name, e.email
FROM employees e
WHERE e.id NOT IN (
    SELECT DISTINCT employee_id FROM orders WHERE employee_id IS NOT NULL
);

-- Method 4: Using EXCEPT (if employee_id is the same in both tables)
SELECT id, name, email
FROM employees
EXCEPT
SELECT e.id, e.name, e.email
FROM employees e
JOIN orders o ON e.id = o.employee_id;

-- Method 5: With additional employee information
SELECT e.id, e.name, e.email, e.department_id, d.department_name
FROM employees e
LEFT JOIN departments d ON e.department_id = d.id
LEFT JOIN orders o ON e.id = o.employee_id
WHERE o.employee_id IS NULL
ORDER BY e.name;

-- Method 6: Using COUNT to show order count
SELECT e.id, e.name, e.email, COUNT(o.id) as order_count
FROM employees e
LEFT JOIN orders o ON e.id = o.employee_id
GROUP BY e.id, e.name, e.email
HAVING COUNT(o.id) = 0;

-- Method 7: With date range filter
SELECT e.id, e.name, e.email
FROM employees e
WHERE NOT EXISTS (
    SELECT 1 FROM orders o 
    WHERE o.employee_id = e.id 
    AND o.order_date >= '2024-01-01'
);

-- Method 8: Using anti-join pattern
SELECT e.id, e.name, e.email
FROM employees e
WHERE e.id NOT IN (
    SELECT employee_id 
    FROM orders 
    WHERE employee_id IS NOT NULL
);

-- Method 9: With employee status check
SELECT e.id, e.name, e.email, e.hire_date
FROM employees e
LEFT JOIN orders o ON e.id = o.employee_id
WHERE o.employee_id IS NULL 
  AND e.active = 1
  AND e.hire_date < '2024-01-01';
```

### Situational / Real-world Questions

1. You need to generate a report summarizing sales data by region and product. How would you write the SQL query? _(Asked in HCL)_

**🧩 Foundation:** Use GROUP BY to aggregate sales data by region and product, with aggregate functions to calculate totals, averages, and counts.

**⚙️ Function:** Create a comprehensive sales report showing performance metrics by region and product category.

**🔁 Flow:**
```sql
-- Basic sales summary by region and product
SELECT 
    r.region_name,
    p.product_name,
    COUNT(o.id) as total_orders,
    SUM(o.quantity) as total_quantity,
    SUM(o.quantity * o.unit_price) as total_revenue,
    AVG(o.unit_price) as avg_unit_price
FROM orders o
JOIN products p ON o.product_id = p.id
JOIN customers c ON o.customer_id = c.id
JOIN regions r ON c.region_id = r.id
GROUP BY r.region_name, p.product_name
ORDER BY r.region_name, total_revenue DESC;

-- Enhanced report with additional metrics
SELECT 
    r.region_name,
    p.product_category,
    p.product_name,
    COUNT(DISTINCT o.customer_id) as unique_customers,
    COUNT(o.id) as total_orders,
    SUM(o.quantity) as total_quantity,
    SUM(o.quantity * o.unit_price) as total_revenue,
    AVG(o.quantity * o.unit_price) as avg_order_value,
    MIN(o.order_date) as first_order_date,
    MAX(o.order_date) as last_order_date
FROM orders o
JOIN products p ON o.product_id = p.id
JOIN customers c ON o.customer_id = c.id
JOIN regions r ON c.region_id = r.id
WHERE o.order_date >= '2024-01-01'
GROUP BY r.region_name, p.product_category, p.product_name
HAVING total_revenue > 10000
ORDER BY r.region_name, total_revenue DESC;

-- Monthly sales trend by region and product
SELECT 
    r.region_name,
    p.product_name,
    DATE_FORMAT(o.order_date, '%Y-%m') as month,
    COUNT(o.id) as orders_count,
    SUM(o.quantity * o.unit_price) as monthly_revenue,
    AVG(o.quantity * o.unit_price) as avg_order_value
FROM orders o
JOIN products p ON o.product_id = p.id
JOIN customers c ON o.customer_id = c.id
JOIN regions r ON c.region_id = r.id
WHERE o.order_date >= '2023-01-01'
GROUP BY r.region_name, p.product_name, DATE_FORMAT(o.order_date, '%Y-%m')
ORDER BY r.region_name, p.product_name, month;

-- Top performing products by region
SELECT 
    r.region_name,
    p.product_name,
    SUM(o.quantity * o.unit_price) as total_revenue,
    RANK() OVER (PARTITION BY r.region_name ORDER BY SUM(o.quantity * o.unit_price) DESC) as region_rank
FROM orders o
JOIN products p ON o.product_id = p.id
JOIN customers c ON o.customer_id = c.id
JOIN regions r ON c.region_id = r.id
WHERE o.order_date >= '2024-01-01'
GROUP BY r.region_name, p.product_name
HAVING region_rank <= 5;

-- Sales comparison with previous year
SELECT 
    r.region_name,
    p.product_name,
    SUM(CASE WHEN YEAR(o.order_date) = 2024 THEN o.quantity * o.unit_price ELSE 0 END) as revenue_2024,
    SUM(CASE WHEN YEAR(o.order_date) = 2023 THEN o.quantity * o.unit_price ELSE 0 END) as revenue_2023,
    (SUM(CASE WHEN YEAR(o.order_date) = 2024 THEN o.quantity * o.unit_price ELSE 0 END) - 
     SUM(CASE WHEN YEAR(o.order_date) = 2023 THEN o.quantity * o.unit_price ELSE 0 END)) as revenue_growth
FROM orders o
JOIN products p ON o.product_id = p.id
JOIN customers c ON o.customer_id = c.id
JOIN regions r ON c.region_id = r.id
WHERE o.order_date >= '2023-01-01'
GROUP BY r.region_name, p.product_name
ORDER BY revenue_growth DESC;
```
2. Write a query to generate a report showing monthly sales totals for the past year. _(Asked in HCL)_

**🧩 Foundation:** Use DATE functions to group sales by month and aggregate functions to calculate monthly totals.

**⚙️ Function:** Create a time-series report showing sales performance over the past 12 months.

**🔁 Flow:**
```sql
-- Basic monthly sales totals
SELECT 
    DATE_FORMAT(order_date, '%Y-%m') as month,
    COUNT(*) as total_orders,
    SUM(quantity * unit_price) as total_revenue
FROM orders
WHERE order_date >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
GROUP BY DATE_FORMAT(order_date, '%Y-%m')
ORDER BY month;

-- Enhanced monthly report with additional metrics
SELECT 
    DATE_FORMAT(order_date, '%Y-%m') as month,
    COUNT(*) as total_orders,
    COUNT(DISTINCT customer_id) as unique_customers,
    SUM(quantity) as total_quantity,
    SUM(quantity * unit_price) as total_revenue,
    AVG(quantity * unit_price) as avg_order_value,
    MIN(order_date) as first_order_date,
    MAX(order_date) as last_order_date
FROM orders
WHERE order_date >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
GROUP BY DATE_FORMAT(order_date, '%Y-%m')
ORDER BY month;

-- Monthly sales with year-over-year comparison
SELECT 
    DATE_FORMAT(order_date, '%m') as month_number,
    DATE_FORMAT(order_date, '%M') as month_name,
    SUM(CASE WHEN YEAR(order_date) = YEAR(CURDATE()) THEN quantity * unit_price ELSE 0 END) as current_year_revenue,
    SUM(CASE WHEN YEAR(order_date) = YEAR(CURDATE()) - 1 THEN quantity * unit_price ELSE 0 END) as previous_year_revenue,
    (SUM(CASE WHEN YEAR(order_date) = YEAR(CURDATE()) THEN quantity * unit_price ELSE 0 END) - 
     SUM(CASE WHEN YEAR(order_date) = YEAR(CURDATE()) - 1 THEN quantity * unit_price ELSE 0 END)) as revenue_growth
FROM orders
WHERE order_date >= DATE_SUB(CURDATE(), INTERVAL 2 YEAR)
GROUP BY DATE_FORMAT(order_date, '%m'), DATE_FORMAT(order_date, '%M')
ORDER BY month_number;

-- Monthly sales by product category
SELECT 
    DATE_FORMAT(o.order_date, '%Y-%m') as month,
    p.product_category,
    COUNT(o.id) as orders_count,
    SUM(o.quantity * o.unit_price) as category_revenue
FROM orders o
JOIN products p ON o.product_id = p.id
WHERE o.order_date >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
GROUP BY DATE_FORMAT(o.order_date, '%Y-%m'), p.product_category
ORDER BY month, category_revenue DESC;

-- Rolling 3-month average
SELECT 
    month,
    total_revenue,
    AVG(total_revenue) OVER (ORDER BY month ROWS BETWEEN 2 PRECEDING AND CURRENT ROW) as rolling_3month_avg
FROM (
    SELECT 
        DATE_FORMAT(order_date, '%Y-%m') as month,
        SUM(quantity * unit_price) as total_revenue
    FROM orders
    WHERE order_date >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
    GROUP BY DATE_FORMAT(order_date, '%Y-%m')
) monthly_sales
ORDER BY month;

-- Monthly sales with percentage of total
SELECT 
    DATE_FORMAT(order_date, '%Y-%m') as month,
    SUM(quantity * unit_price) as monthly_revenue,
    SUM(quantity * unit_price) * 100.0 / SUM(SUM(quantity * unit_price)) OVER () as percentage_of_total
FROM orders
WHERE order_date >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
GROUP BY DATE_FORMAT(order_date, '%Y-%m')
ORDER BY month;
```

---

## 2. Joins

### Basic Questions

1. What are the different types of joins in SQL? _(Asked in TCS, Capgemini)_

**🧩 Foundation:** SQL joins combine rows from two or more tables based on related columns. The main types are INNER, LEFT, RIGHT, FULL OUTER, and CROSS joins.

**⚙️ Function:** Each join type serves different purposes for combining data from multiple tables.

**🔁 Flow:**
```sql
-- INNER JOIN - Returns only matching rows from both tables
SELECT e.name, d.department_name
FROM employees e
INNER JOIN departments d ON e.department_id = d.id;

-- LEFT JOIN - Returns all rows from left table and matching rows from right
SELECT c.customer_name, o.order_id
FROM customers c
LEFT JOIN orders o ON c.id = o.customer_id;

-- RIGHT JOIN - Returns all rows from right table and matching rows from left
SELECT c.customer_name, o.order_id
FROM customers c
RIGHT JOIN orders o ON c.id = o.customer_id;

-- FULL OUTER JOIN - Returns all rows from both tables
SELECT e.name, d.department_name
FROM employees e
FULL OUTER JOIN departments d ON e.department_id = d.id;

-- CROSS JOIN - Returns Cartesian product (all combinations)
SELECT p.product_name, c.category_name
FROM products p
CROSS JOIN categories c;

-- Self JOIN - Joining table with itself
SELECT e1.name as employee, e2.name as manager
FROM employees e1
JOIN employees e2 ON e1.manager_id = e2.id;

-- Multiple JOINs
SELECT e.name, d.department_name, p.project_name
FROM employees e
JOIN departments d ON e.department_id = d.id
JOIN employee_projects ep ON e.id = ep.employee_id
JOIN projects p ON ep.project_id = p.id;
```
2. What is the difference between INNER JOIN and LEFT JOIN? _(Asked in Infosys)_

**🧩 Foundation:** INNER JOIN returns only matching rows from both tables, while LEFT JOIN returns all rows from the left table and matching rows from the right table.

**⚙️ Function:** INNER JOIN filters out non-matching rows, LEFT JOIN preserves all rows from the left table.

**🔁 Flow:**
```sql
-- INNER JOIN - Only matching rows
SELECT e.name, d.department_name
FROM employees e
INNER JOIN departments d ON e.department_id = d.id;
-- Result: Only employees with valid department_id

-- LEFT JOIN - All employees, even without departments
SELECT e.name, d.department_name
FROM employees e
LEFT JOIN departments d ON e.department_id = d.id;
-- Result: All employees, NULL for department_name if no match

-- Example with data:
-- employees: (1, 'John', 1), (2, 'Jane', NULL), (3, 'Bob', 2)
-- departments: (1, 'IT'), (2, 'HR')

-- INNER JOIN result:
-- John, IT
-- Bob, HR

-- LEFT JOIN result:
-- John, IT
-- Jane, NULL
-- Bob, HR

-- Practical example - finding employees without departments
SELECT e.name, e.email
FROM employees e
LEFT JOIN departments d ON e.department_id = d.id
WHERE d.id IS NULL;

-- INNER JOIN with WHERE vs LEFT JOIN with WHERE
SELECT e.name, d.department_name
FROM employees e
INNER JOIN departments d ON e.department_id = d.id
WHERE d.department_name = 'IT';

SELECT e.name, d.department_name
FROM employees e
LEFT JOIN departments d ON e.department_id = d.id
WHERE d.department_name = 'IT' OR d.department_name IS NULL;
```

### Basic Query Questions

1. Write a query to join employees and departments tables to show employee names and their department names. _(Asked in TCS)_

**🧩 Foundation:** Use INNER JOIN to combine employee and department data based on the department_id foreign key.

**⚙️ Function:** Join the tables to display employee names alongside their corresponding department names.

**🔁 Flow:**
```sql
-- Basic INNER JOIN
SELECT e.name, d.department_name
FROM employees e
JOIN departments d ON e.department_id = d.id;

-- With additional employee information
SELECT e.name, e.email, d.department_name, e.salary
FROM employees e
JOIN departments d ON e.department_id = d.id;

-- With table aliases for clarity
SELECT emp.name, emp.email, dept.department_name
FROM employees emp
JOIN departments dept ON emp.department_id = dept.id;

-- With WHERE clause for filtering
SELECT e.name, d.department_name
FROM employees e
JOIN departments d ON e.department_id = d.id
WHERE e.active = 1;

-- With ORDER BY for sorting
SELECT e.name, d.department_name, e.salary
FROM employees e
JOIN departments d ON e.department_id = d.id
ORDER BY d.department_name, e.salary DESC;

-- Using LEFT JOIN to include employees without departments
SELECT e.name, COALESCE(d.department_name, 'No Department') as department
FROM employees e
LEFT JOIN departments d ON e.department_id = d.id;

-- With aggregate functions
SELECT d.department_name, COUNT(e.id) as employee_count
FROM employees e
JOIN departments d ON e.department_id = d.id
GROUP BY d.department_name
ORDER BY employee_count DESC;
```

### Intermediate Questions

1. How does a FULL OUTER JOIN differ from a CROSS JOIN? _(Asked in Wipro)_

**🧩 Foundation:** FULL OUTER JOIN returns all rows from both tables (matching and non-matching), while CROSS JOIN returns the Cartesian product (all possible combinations).

**⚙️ Function:** FULL OUTER JOIN preserves all data from both tables, CROSS JOIN creates all possible row combinations.

**🔁 Flow:**
```sql
-- FULL OUTER JOIN - All rows from both tables
SELECT e.name, d.department_name
FROM employees e
FULL OUTER JOIN departments d ON e.department_id = d.id;
-- Result: All employees and all departments, NULL for non-matches

-- CROSS JOIN - All possible combinations
SELECT e.name, d.department_name
FROM employees e
CROSS JOIN departments d;
-- Result: Every employee paired with every department

-- Example with sample data:
-- employees: (1, 'John'), (2, 'Jane')
-- departments: (1, 'IT'), (2, 'HR')

-- FULL OUTER JOIN result:
-- John, IT
-- Jane, HR
-- NULL, Marketing (if department exists but no employees)

-- CROSS JOIN result:
-- John, IT
-- John, HR
-- John, Marketing
-- Jane, IT
-- Jane, HR
-- Jane, Marketing

-- Practical FULL OUTER JOIN example
SELECT 
    COALESCE(e.name, 'No Employee') as employee_name,
    COALESCE(d.department_name, 'No Department') as department_name
FROM employees e
FULL OUTER JOIN departments d ON e.department_id = d.id;

-- CROSS JOIN for generating test data
SELECT p.product_name, c.category_name
FROM products p
CROSS JOIN categories c;

-- CROSS JOIN with WHERE for specific combinations
SELECT e.name, d.department_name
FROM employees e
CROSS JOIN departments d
WHERE e.salary > 50000 AND d.location = 'HQ';
```
2. What happens when you join tables with missing or null values? _(Asked in Accenture)_

**🧩 Foundation:** NULL values in join conditions don't match any other values, including other NULLs, causing rows to be excluded from INNER JOINs but preserved in OUTER JOINs.

**⚙️ Function:** NULL values affect join results differently based on the join type and how NULLs are handled.

**🔁 Flow:**
```sql
-- NULL values in INNER JOIN - excluded from results
SELECT e.name, d.department_name
FROM employees e
INNER JOIN departments d ON e.department_id = d.id;
-- Employees with NULL department_id are excluded

-- NULL values in LEFT JOIN - preserved from left table
SELECT e.name, d.department_name
FROM employees e
LEFT JOIN departments d ON e.department_id = d.id;
-- Employees with NULL department_id show NULL for department_name

-- Handling NULL values with COALESCE
SELECT e.name, COALESCE(d.department_name, 'Unassigned') as department
FROM employees e
LEFT JOIN departments d ON e.department_id = d.id;

-- NULL values in WHERE clause
SELECT e.name, d.department_name
FROM employees e
LEFT JOIN departments d ON e.department_id = d.id
WHERE e.department_id IS NULL;  -- Find employees without departments

-- Using ISNULL (SQL Server) or IFNULL (MySQL)
SELECT e.name, ISNULL(d.department_name, 'No Department') as department
FROM employees e
LEFT JOIN departments d ON e.department_id = d.id;

-- Multiple NULL conditions
SELECT e.name, d.department_name
FROM employees e
LEFT JOIN departments d ON e.department_id = d.id
WHERE e.department_id IS NULL OR d.department_name IS NULL;

-- NULL-safe comparison using <=>
SELECT e.name, d.department_name
FROM employees e
LEFT JOIN departments d ON e.department_id <=> d.id;  -- MySQL only

-- Using CASE to handle NULLs
SELECT e.name,
       CASE 
           WHEN d.department_name IS NULL THEN 'Unassigned'
           ELSE d.department_name 
       END as department
FROM employees e
LEFT JOIN departments d ON e.department_id = d.id;
```

### Intermediate Query Questions

1. Write a query to retrieve all customers and their orders, including customers with no orders. _(Asked in Wipro)_

**🧩 Foundation:** Use LEFT JOIN to include all customers from the left table, even those without matching orders in the right table.

**⚙️ Function:** Preserve all customer records while showing their orders, with NULL for customers who have no orders.

**🔁 Flow:**
```sql
-- Basic LEFT JOIN to include all customers
SELECT c.customer_name, o.order_id, o.order_date
FROM customers c
LEFT JOIN orders o ON c.id = o.customer_id;

-- With additional customer information
SELECT c.customer_name, c.email, o.order_id, o.total_amount
FROM customers c
LEFT JOIN orders o ON c.id = o.customer_id;

-- Using COALESCE to handle NULL values
SELECT c.customer_name, 
       COALESCE(o.order_id, 'No Orders') as order_info,
       COALESCE(o.total_amount, 0) as total_spent
FROM customers c
LEFT JOIN orders o ON c.id = o.customer_id;

-- Finding customers with no orders
SELECT c.customer_name, c.email
FROM customers c
LEFT JOIN orders o ON c.id = o.customer_id
WHERE o.customer_id IS NULL;

-- With aggregate functions to show order counts
SELECT c.customer_name, 
       COUNT(o.id) as order_count,
       COALESCE(SUM(o.total_amount), 0) as total_spent
FROM customers c
LEFT JOIN orders o ON c.id = o.customer_id
GROUP BY c.id, c.customer_name;

-- With date filtering
SELECT c.customer_name, o.order_id, o.order_date
FROM customers c
LEFT JOIN orders o ON c.id = o.customer_id 
    AND o.order_date >= '2024-01-01';

-- Multiple LEFT JOINs
SELECT c.customer_name, o.order_id, p.product_name
FROM customers c
LEFT JOIN orders o ON c.id = o.customer_id
LEFT JOIN order_items oi ON o.id = oi.order_id
LEFT JOIN products p ON oi.product_id = p.id;

-- With ORDER BY to group customers with orders first
SELECT c.customer_name, o.order_id
FROM customers c
LEFT JOIN orders o ON c.id = o.customer_id
ORDER BY CASE WHEN o.id IS NULL THEN 1 ELSE 0 END, c.customer_name;
```
2. Write a query to perform a cross join between products and categories tables. _(Asked in Accenture)_

**🧩 Foundation:** CROSS JOIN creates a Cartesian product, pairing every product with every category.

**⚙️ Function:** Generates all possible combinations between products and categories.

**🔁 Flow:**
```sql
-- Basic CROSS JOIN
SELECT p.product_name, c.category_name
FROM products p
CROSS JOIN categories c;

-- With additional columns
SELECT p.product_name, p.price, c.category_name, c.description
FROM products p
CROSS JOIN categories c;

-- Using explicit CROSS JOIN syntax
SELECT p.product_name, c.category_name
FROM products p, categories c;  -- Implicit CROSS JOIN

-- With WHERE clause to filter combinations
SELECT p.product_name, c.category_name
FROM products p
CROSS JOIN categories c
WHERE p.price > 100 AND c.active = 1;

-- CROSS JOIN for generating test data
SELECT p.product_name, c.category_name, 
       CONCAT(p.product_name, ' - ', c.category_name) as combination
FROM products p
CROSS JOIN categories c;

-- With ORDER BY for organized output
SELECT p.product_name, c.category_name
FROM products p
CROSS JOIN categories c
ORDER BY c.category_name, p.product_name;

-- CROSS JOIN with multiple tables
SELECT p.product_name, c.category_name, s.supplier_name
FROM products p
CROSS JOIN categories c
CROSS JOIN suppliers s;

-- Practical use case - generating all possible combinations
SELECT p.product_name, c.category_name, 
       CASE WHEN pc.product_id IS NOT NULL THEN 'Exists' ELSE 'Missing' END as relationship
FROM products p
CROSS JOIN categories c
LEFT JOIN product_categories pc ON p.id = pc.product_id AND c.id = pc.category_id;

-- CROSS JOIN for data analysis
SELECT p.product_name, c.category_name, COUNT(o.id) as order_count
FROM products p
CROSS JOIN categories c
LEFT JOIN orders o ON p.id = o.product_id
GROUP BY p.product_name, c.category_name;
```

### Advanced Questions

1. How do you optimize a query with multiple joins on large tables? _(Asked in Cognizant)_

**🧩 Foundation:** Query optimization involves proper indexing, join order, filtering early, and using appropriate join types.

**⚙️ Function:** Improve query performance by reducing data processing and optimizing execution plans.

**🔁 Flow:**
```sql
-- Poor performance - no filtering, multiple joins
SELECT e.name, d.department_name, p.project_name, c.client_name
FROM employees e
JOIN departments d ON e.department_id = d.id
JOIN employee_projects ep ON e.id = ep.employee_id
JOIN projects p ON ep.project_id = p.id
JOIN clients c ON p.client_id = c.id;

-- Optimized - filter early, use indexes
SELECT e.name, d.department_name, p.project_name, c.client_name
FROM employees e
JOIN departments d ON e.department_id = d.id
JOIN employee_projects ep ON e.id = ep.employee_id
JOIN projects p ON ep.project_id = p.id
JOIN clients c ON p.client_id = c.id
WHERE e.active = 1  -- Filter early
  AND p.status = 'active'
  AND e.department_id IN (1, 2, 3);  -- Use indexed columns

-- Use appropriate indexes
CREATE INDEX idx_employees_dept_active ON employees(department_id, active);
CREATE INDEX idx_employee_projects_emp ON employee_projects(employee_id);
CREATE INDEX idx_projects_status ON projects(status);

-- Optimize join order - start with smallest filtered table
SELECT e.name, d.department_name
FROM departments d  -- Start with filtered departments
JOIN employees e ON d.id = e.department_id
WHERE d.location = 'HQ'
  AND e.salary > 50000;

-- Use EXISTS instead of JOIN when you only need to check existence
SELECT e.name, e.salary
FROM employees e
WHERE EXISTS (
    SELECT 1 FROM projects p 
    WHERE p.manager_id = e.id 
    AND p.status = 'active'
);

-- Use INNER JOIN instead of LEFT JOIN when possible
SELECT e.name, d.department_name
FROM employees e
INNER JOIN departments d ON e.department_id = d.id;  -- Faster than LEFT JOIN

-- Limit result set early
SELECT e.name, d.department_name
FROM employees e
JOIN departments d ON e.department_id = d.id
WHERE e.hire_date >= '2020-01-01'
LIMIT 1000;

-- Use covering indexes for frequently accessed columns
CREATE INDEX idx_emp_covering ON employees(id, name, department_id, salary);

-- Partition large tables by date
CREATE TABLE orders_2024 PARTITION OF orders
FOR VALUES FROM ('2024-01-01') TO ('2025-01-01');
```
2. What is a self-join, and when would you use it? _(Asked in TCS Digital)_

**🧩 Foundation:** A self-join is when a table is joined with itself, typically used for hierarchical data or comparing rows within the same table.

**⚙️ Function:** Self-joins are useful for employee-manager relationships, product hierarchies, and comparing records within the same table.

**🔁 Flow:**
```sql
-- Employee-Manager relationship
SELECT e1.name as employee, e2.name as manager
FROM employees e1
JOIN employees e2 ON e1.manager_id = e2.id;

-- Employee-Manager with department info
SELECT e1.name as employee, 
       e1.department_id as emp_dept,
       e2.name as manager,
       e2.department_id as mgr_dept
FROM employees e1
LEFT JOIN employees e2 ON e1.manager_id = e2.id;

-- Finding employees in same department as their manager
SELECT e1.name as employee, e2.name as manager, d.department_name
FROM employees e1
JOIN employees e2 ON e1.manager_id = e2.id
JOIN departments d ON e1.department_id = d.id
WHERE e1.department_id = e2.department_id;

-- Product hierarchy (parent-child relationship)
SELECT p1.product_name as child_product,
       p2.product_name as parent_product
FROM products p1
LEFT JOIN products p2 ON p1.parent_id = p2.id;

-- Finding employees with same salary
SELECT e1.name as employee1, e2.name as employee2, e1.salary
FROM employees e1
JOIN employees e2 ON e1.salary = e2.salary AND e1.id < e2.id;

-- Manager hierarchy (multiple levels)
SELECT e1.name as employee,
       e2.name as manager,
       e3.name as manager_manager
FROM employees e1
LEFT JOIN employees e2 ON e1.manager_id = e2.id
LEFT JOIN employees e3 ON e2.manager_id = e3.id;

-- Finding employees hired before their manager
SELECT e1.name as employee, e1.hire_date as emp_hire_date,
       e2.name as manager, e2.hire_date as mgr_hire_date
FROM employees e1
JOIN employees e2 ON e1.manager_id = e2.id
WHERE e1.hire_date < e2.hire_date;

-- Self-join for data validation
SELECT e1.id, e1.email, e2.id, e2.email
FROM employees e1
JOIN employees e2 ON e1.email = e2.email AND e1.id != e2.id;
```

### Advanced Query Questions

1. Write a query to join employees with itself to list employees and their managers. _(Asked in TCS Digital)_

**🧩 Foundation:** Use a self-join to connect employees table with itself using the manager_id foreign key.

**⚙️ Function:** Create a hierarchical view showing employee-manager relationships.

**🔁 Flow:**
```sql
-- Basic self-join for employee-manager relationship
SELECT e1.name as employee, e2.name as manager
FROM employees e1
JOIN employees e2 ON e1.manager_id = e2.id;

-- With additional employee information
SELECT e1.name as employee, e1.email as emp_email,
       e2.name as manager, e2.email as mgr_email
FROM employees e1
JOIN employees e2 ON e1.manager_id = e2.id;

-- Using LEFT JOIN to include employees without managers
SELECT e1.name as employee, 
       COALESCE(e2.name, 'No Manager') as manager
FROM employees e1
LEFT JOIN employees e2 ON e1.manager_id = e2.id;

-- With department information
SELECT e1.name as employee, d1.department_name as emp_dept,
       e2.name as manager, d2.department_name as mgr_dept
FROM employees e1
JOIN employees e2 ON e1.manager_id = e2.id
JOIN departments d1 ON e1.department_id = d1.id
JOIN departments d2 ON e2.department_id = d2.id;

-- Finding employees who are also managers
SELECT e1.name as employee, e2.name as manager
FROM employees e1
JOIN employees e2 ON e1.manager_id = e2.id
WHERE e1.id IN (SELECT DISTINCT manager_id FROM employees WHERE manager_id IS NOT NULL);

-- Manager hierarchy with multiple levels
SELECT e1.name as employee,
       e2.name as manager,
       e3.name as senior_manager
FROM employees e1
LEFT JOIN employees e2 ON e1.manager_id = e2.id
LEFT JOIN employees e3 ON e2.manager_id = e3.id;

-- With salary comparison
SELECT e1.name as employee, e1.salary as emp_salary,
       e2.name as manager, e2.salary as mgr_salary,
       e2.salary - e1.salary as salary_difference
FROM employees e1
JOIN employees e2 ON e1.manager_id = e2.id
ORDER BY salary_difference DESC;

-- Finding managers with most direct reports
SELECT e2.name as manager, COUNT(e1.id) as direct_reports
FROM employees e1
JOIN employees e2 ON e1.manager_id = e2.id
GROUP BY e2.id, e2.name
ORDER BY direct_reports DESC;
```
2. Write a query to join three tables (orders, customers, products) to show order details with customer and product names. _(Asked in Cognizant)_

**🧩 Foundation:** Use multiple INNER JOINs to connect orders with customers and products tables through their respective foreign keys.

**⚙️ Function:** Combine order information with customer and product details for comprehensive order reporting.

**🔁 Flow:**
```sql
-- Basic three-table join
SELECT o.order_id, c.customer_name, p.product_name, o.quantity, o.total_amount
FROM orders o
JOIN customers c ON o.customer_id = c.id
JOIN products p ON o.product_id = p.id;

-- With additional details
SELECT o.order_id, o.order_date,
       c.customer_name, c.email as customer_email,
       p.product_name, p.price as unit_price,
       o.quantity, o.total_amount
FROM orders o
JOIN customers c ON o.customer_id = c.id
JOIN products p ON o.product_id = p.id;

-- With WHERE clause for filtering
SELECT o.order_id, c.customer_name, p.product_name, o.total_amount
FROM orders o
JOIN customers c ON o.customer_id = c.id
JOIN products p ON o.product_id = p.id
WHERE o.order_date >= '2024-01-01'
  AND o.total_amount > 100;

-- With ORDER BY for sorting
SELECT o.order_id, c.customer_name, p.product_name, o.total_amount
FROM orders o
JOIN customers c ON o.customer_id = c.id
JOIN products p ON o.product_id = p.id
ORDER BY o.total_amount DESC;

-- Using table aliases for clarity
SELECT ord.order_id, cust.customer_name, prod.product_name
FROM orders ord
JOIN customers cust ON ord.customer_id = cust.id
JOIN products prod ON ord.product_id = prod.id;

-- With aggregate functions
SELECT c.customer_name, p.product_name,
       COUNT(o.id) as order_count,
       SUM(o.total_amount) as total_spent
FROM orders o
JOIN customers c ON o.customer_id = c.id
JOIN products p ON o.product_id = p.id
GROUP BY c.customer_name, p.product_name
ORDER BY total_spent DESC;

-- With LEFT JOIN to include all customers and products
SELECT c.customer_name, p.product_name, COUNT(o.id) as order_count
FROM customers c
CROSS JOIN products p
LEFT JOIN orders o ON c.id = o.customer_id AND p.id = o.product_id
GROUP BY c.customer_name, p.product_name
ORDER BY order_count DESC;
```

### Tough Questions

1. How would you write a query to join three tables to retrieve hierarchical data (e.g., employee-manager relationships)? _(Asked in Deloitte)_

**🧩 Foundation:** Use multiple self-joins or recursive CTEs to traverse hierarchical relationships across multiple levels.

**⚙️ Function:** Retrieve multi-level hierarchical data showing the complete chain of relationships.

**🔁 Flow:**
```sql
-- Three-level employee hierarchy using self-joins
SELECT e1.name as employee,
       e2.name as manager,
       e3.name as senior_manager
FROM employees e1
LEFT JOIN employees e2 ON e1.manager_id = e2.id
LEFT JOIN employees e3 ON e2.manager_id = e3.id;

-- With department information
SELECT e1.name as employee, d1.department_name as emp_dept,
       e2.name as manager, d2.department_name as mgr_dept,
       e3.name as senior_manager, d3.department_name as sr_mgr_dept
FROM employees e1
LEFT JOIN employees e2 ON e1.manager_id = e2.id
LEFT JOIN employees e3 ON e2.manager_id = e3.id
LEFT JOIN departments d1 ON e1.department_id = d1.id
LEFT JOIN departments d2 ON e2.department_id = d2.id
LEFT JOIN departments d3 ON e3.department_id = d3.id;

-- Using recursive CTE for unlimited levels
WITH RECURSIVE employee_hierarchy AS (
    -- Base case: employees without managers (top level)
    SELECT id, name, manager_id, 1 as level, CAST(name AS VARCHAR(1000)) as hierarchy_path
    FROM employees
    WHERE manager_id IS NULL
    
    UNION ALL
    
    -- Recursive case: employees with managers
    SELECT e.id, e.name, e.manager_id, eh.level + 1,
           CONCAT(eh.hierarchy_path, ' -> ', e.name) as hierarchy_path
    FROM employees e
    JOIN employee_hierarchy eh ON e.manager_id = eh.id
)
SELECT name, level, hierarchy_path
FROM employee_hierarchy
ORDER BY hierarchy_path;

-- Product category hierarchy
SELECT p1.name as product,
       p2.name as parent_category,
       p3.name as grandparent_category
FROM products p1
LEFT JOIN products p2 ON p1.parent_id = p2.id
LEFT JOIN products p3 ON p2.parent_id = p3.id;

-- Organizational structure with roles
SELECT e1.name as employee, r1.role_name as emp_role,
       e2.name as manager, r2.role_name as mgr_role,
       e3.name as director, r3.role_name as dir_role
FROM employees e1
LEFT JOIN employees e2 ON e1.manager_id = e2.id
LEFT JOIN employees e3 ON e2.manager_id = e3.id
LEFT JOIN roles r1 ON e1.role_id = r1.id
LEFT JOIN roles r2 ON e2.role_id = r2.id
LEFT JOIN roles r3 ON e3.role_id = r3.id;

-- Finding complete reporting chain
SELECT e1.name as employee,
       GROUP_CONCAT(e2.name ORDER BY e2.id SEPARATOR ' -> ') as reporting_chain
FROM employees e1
LEFT JOIN employees e2 ON e1.manager_id = e2.id
GROUP BY e1.id, e1.name;
```

### Tough Query Questions

1. Write a query to find employees who work in the same department as their manager using a self-join. _(Asked in Deloitte)_

**🧩 Foundation:** Use a self-join to compare employee and manager department IDs, filtering for matches.

**⚙️ Function:** Identify employees who share the same department as their direct manager.

**🔁 Flow:**
```sql
-- Basic self-join to find employees in same department as manager
SELECT e1.name as employee, e2.name as manager, d.department_name
FROM employees e1
JOIN employees e2 ON e1.manager_id = e2.id
JOIN departments d ON e1.department_id = d.id
WHERE e1.department_id = e2.department_id;

-- With additional employee information
SELECT e1.name as employee, e1.email as emp_email,
       e2.name as manager, e2.email as mgr_email,
       d.department_name, e1.salary as emp_salary, e2.salary as mgr_salary
FROM employees e1
JOIN employees e2 ON e1.manager_id = e2.id
JOIN departments d ON e1.department_id = d.id
WHERE e1.department_id = e2.department_id;

-- Using table aliases for clarity
SELECT emp.name as employee, mgr.name as manager, dept.department_name
FROM employees emp
JOIN employees mgr ON emp.manager_id = mgr.id
JOIN departments dept ON emp.department_id = dept.id
WHERE emp.department_id = mgr.department_id;

-- With salary comparison
SELECT e1.name as employee, e2.name as manager, d.department_name,
       e1.salary as emp_salary, e2.salary as mgr_salary,
       e2.salary - e1.salary as salary_difference
FROM employees e1
JOIN employees e2 ON e1.manager_id = e2.id
JOIN departments d ON e1.department_id = d.id
WHERE e1.department_id = e2.department_id
ORDER BY salary_difference DESC;

-- Finding employees NOT in same department as manager
SELECT e1.name as employee, d1.department_name as emp_dept,
       e2.name as manager, d2.department_name as mgr_dept
FROM employees e1
JOIN employees e2 ON e1.manager_id = e2.id
JOIN departments d1 ON e1.department_id = d1.id
JOIN departments d2 ON e2.department_id = d2.id
WHERE e1.department_id != e2.department_id;

-- With aggregate functions to count by department
SELECT d.department_name, COUNT(*) as same_dept_emp_mgr_pairs
FROM employees e1
JOIN employees e2 ON e1.manager_id = e2.id
JOIN departments d ON e1.department_id = d.id
WHERE e1.department_id = e2.department_id
GROUP BY d.department_name
ORDER BY same_dept_emp_mgr_pairs DESC;

-- Including employees without managers
SELECT e1.name as employee, 
       COALESCE(e2.name, 'No Manager') as manager,
       d.department_name
FROM employees e1
LEFT JOIN employees e2 ON e1.manager_id = e2.id
JOIN departments d ON e1.department_id = d.id
WHERE e1.department_id = e2.department_id OR e2.id IS NULL;
```

### Situational / Real-world Questions

1. Your application retrieves data from multiple related tables, but the query is slow. How would you analyze and improve it? _(Asked in HCL)_

**🧩 Foundation:** Query optimization involves analyzing execution plans, identifying bottlenecks, and implementing performance improvements.

**⚙️ Function:** Diagnose and resolve performance issues in complex multi-table queries.

**🔁 Flow:**
```sql
-- Step 1: Analyze the slow query
EXPLAIN SELECT e.name, d.department_name, p.project_name
FROM employees e
JOIN departments d ON e.department_id = d.id
JOIN employee_projects ep ON e.id = ep.employee_id
JOIN projects p ON ep.project_id = p.id
WHERE e.active = 1;

-- Step 2: Check existing indexes
SHOW INDEX FROM employees;
SHOW INDEX FROM departments;
SHOW INDEX FROM employee_projects;
SHOW INDEX FROM projects;

-- Step 3: Create missing indexes
CREATE INDEX idx_employees_active_dept ON employees(active, department_id);
CREATE INDEX idx_employee_projects_emp ON employee_projects(employee_id);
CREATE INDEX idx_projects_status ON projects(status);

-- Step 4: Optimize the query - filter early
SELECT e.name, d.department_name, p.project_name
FROM employees e
JOIN departments d ON e.department_id = d.id
JOIN employee_projects ep ON e.id = ep.employee_id
JOIN projects p ON ep.project_id = p.id
WHERE e.active = 1  -- Filter early
  AND p.status = 'active'
  AND e.department_id IN (1, 2, 3);  -- Use indexed columns

-- Step 5: Use covering indexes
CREATE INDEX idx_emp_covering ON employees(id, name, active, department_id);
CREATE INDEX idx_dept_covering ON departments(id, department_name);

-- Step 6: Optimize join order - start with smallest filtered table
SELECT e.name, d.department_name, p.project_name
FROM departments d  -- Start with filtered departments
JOIN employees e ON d.id = e.department_id
JOIN employee_projects ep ON e.id = ep.employee_id
JOIN projects p ON ep.project_id = p.id
WHERE d.location = 'HQ'
  AND e.active = 1;

-- Step 7: Use EXISTS instead of JOIN when appropriate
SELECT e.name, d.department_name
FROM employees e
JOIN departments d ON e.department_id = d.id
WHERE EXISTS (
    SELECT 1 FROM employee_projects ep 
    JOIN projects p ON ep.project_id = p.id
    WHERE ep.employee_id = e.id AND p.status = 'active'
);

-- Step 8: Limit result set
SELECT e.name, d.department_name, p.project_name
FROM employees e
JOIN departments d ON e.department_id = d.id
JOIN employee_projects ep ON e.id = ep.employee_id
JOIN projects p ON ep.project_id = p.id
WHERE e.active = 1
LIMIT 1000;

-- Step 9: Use materialized views for complex aggregations
CREATE MATERIALIZED VIEW emp_dept_summary AS
SELECT d.department_name, COUNT(e.id) as emp_count, AVG(e.salary) as avg_salary
FROM employees e
JOIN departments d ON e.department_id = d.id
WHERE e.active = 1
GROUP BY d.department_name;

-- Step 10: Monitor query performance
SELECT query, execution_time, rows_examined
FROM performance_schema.events_statements_summary_by_digest
WHERE query LIKE '%employees%'
ORDER BY execution_time DESC;
```
2. Write a query to generate a report joining sales, products, and regions tables, handling missing region data. _(Asked in HCL)_

**🧩 Foundation:** Use LEFT JOINs to preserve all sales data while handling missing region information gracefully.

**⚙️ Function:** Create a comprehensive sales report that includes all sales records, even when region data is missing.

**🔁 Flow:**
```sql
-- Basic report with LEFT JOIN to handle missing region data
SELECT s.sale_id, p.product_name, 
       COALESCE(r.region_name, 'Unknown Region') as region_name,
       s.quantity, s.total_amount
FROM sales s
JOIN products p ON s.product_id = p.id
LEFT JOIN regions r ON s.region_id = r.id;

-- Enhanced report with additional handling
SELECT s.sale_id, s.sale_date,
       p.product_name, p.category,
       COALESCE(r.region_name, 'Unassigned') as region_name,
       COALESCE(r.country, 'Unknown') as country,
       s.quantity, s.total_amount
FROM sales s
JOIN products p ON s.product_id = p.id
LEFT JOIN regions r ON s.region_id = r.id
WHERE s.sale_date >= '2024-01-01';

-- Report with aggregate functions
SELECT p.product_name,
       COALESCE(r.region_name, 'Unknown Region') as region_name,
       COUNT(s.id) as sale_count,
       SUM(s.quantity) as total_quantity,
       SUM(s.total_amount) as total_revenue,
       AVG(s.total_amount) as avg_sale_value
FROM sales s
JOIN products p ON s.product_id = p.id
LEFT JOIN regions r ON s.region_id = r.id
GROUP BY p.product_name, r.region_name
ORDER BY total_revenue DESC;

-- Report with CASE statements for better categorization
SELECT p.product_name,
       CASE 
           WHEN r.region_name IS NULL THEN 'Unknown Region'
           WHEN r.region_name = '' THEN 'Unassigned'
           ELSE r.region_name 
       END as region_name,
       s.total_amount
FROM sales s
JOIN products p ON s.product_id = p.id
LEFT JOIN regions r ON s.region_id = r.id;

-- Report showing sales with and without region data
SELECT 
    CASE 
        WHEN r.region_name IS NULL THEN 'No Region Data'
        ELSE 'Has Region Data'
    END as data_status,
    COUNT(s.id) as sale_count,
    SUM(s.total_amount) as total_revenue
FROM sales s
LEFT JOIN regions r ON s.region_id = r.id
GROUP BY CASE WHEN r.region_name IS NULL THEN 'No Region Data' ELSE 'Has Region Data' END;

-- Report with multiple region levels
SELECT s.sale_id, p.product_name,
       COALESCE(r.region_name, 'Unknown') as region,
       COALESCE(r.state, 'Unknown') as state,
       COALESCE(r.city, 'Unknown') as city,
       s.total_amount
FROM sales s
JOIN products p ON s.product_id = p.id
LEFT JOIN regions r ON s.region_id = r.id;

-- Report with data quality indicators
SELECT p.product_name,
       COALESCE(r.region_name, 'Missing') as region_name,
       CASE WHEN r.region_name IS NULL THEN 'Data Quality Issue' ELSE 'OK' END as data_quality,
       COUNT(s.id) as sale_count
FROM sales s
JOIN products p ON s.product_id = p.id
LEFT JOIN regions r ON s.region_id = r.id
GROUP BY p.product_name, r.region_name
ORDER BY data_quality, sale_count DESC;
```

---

## 3. Indexes

### Basic Questions

1. What is an index in SQL, and why is it used? _(Asked in TCS, Infosys)_

**🧩 Foundation:** An index is a database structure that improves query performance by providing fast access to data without scanning entire tables.

**⚙️ Function:** Indexes speed up SELECT, WHERE, ORDER BY, and JOIN operations by creating sorted references to data.

**🔁 Flow:**
```sql
-- Creating a basic index
CREATE INDEX idx_employees_email ON employees(email);

-- Creating a composite index
CREATE INDEX idx_employees_dept_salary ON employees(department_id, salary);

-- Creating a unique index
CREATE UNIQUE INDEX idx_employees_email_unique ON employees(email);

-- Creating an index on multiple columns
CREATE INDEX idx_orders_customer_date ON orders(customer_id, order_date);

-- Using indexes in queries
SELECT name, salary FROM employees WHERE department_id = 1;  -- Uses index
SELECT name, salary FROM employees WHERE salary > 50000;      -- May use index
SELECT name FROM employees ORDER BY department_id;            -- Uses index

-- Checking if indexes are being used
EXPLAIN SELECT name FROM employees WHERE department_id = 1;

-- Creating indexes for JOIN operations
CREATE INDEX idx_orders_customer_id ON orders(customer_id);
CREATE INDEX idx_orders_product_id ON orders(product_id);

-- Index for date range queries
CREATE INDEX idx_orders_date ON orders(order_date);

-- Partial index (PostgreSQL)
CREATE INDEX idx_active_employees ON employees(name) WHERE active = 1;

-- Dropping an index
DROP INDEX idx_employees_email ON employees;

-- Viewing existing indexes
SHOW INDEX FROM employees;
```
2. What is the difference between a clustered and non-clustered index? _(Asked in Capgemini)_

**🧩 Foundation:** Clustered indexes determine the physical storage order of data, while non-clustered indexes are separate structures pointing to data.

**⚙️ Function:** Clustered indexes store data in sorted order, non-clustered indexes store pointers to data.

**🔁 Flow:**
```sql
-- Clustered Index (Primary Key is typically clustered)
CREATE TABLE employees (
    id INT PRIMARY KEY,  -- Clustered index
    name VARCHAR(100),
    email VARCHAR(100)
);

-- Non-clustered index
CREATE INDEX idx_employees_email ON employees(email);  -- Non-clustered

-- Multiple non-clustered indexes
CREATE INDEX idx_employees_name ON employees(name);
CREATE INDEX idx_employees_department ON employees(department_id);

-- Composite non-clustered index
CREATE INDEX idx_employees_dept_salary ON employees(department_id, salary);

-- Clustered index on different column (SQL Server)
CREATE CLUSTERED INDEX idx_employees_email_clustered ON employees(email);

-- Non-clustered index with included columns
CREATE INDEX idx_employees_dept_covering ON employees(department_id) 
INCLUDE (name, salary);

-- Unique non-clustered index
CREATE UNIQUE INDEX idx_employees_email_unique ON employees(email);

-- Filtered non-clustered index
CREATE INDEX idx_active_employees ON employees(department_id) 
WHERE active = 1;

-- Viewing index types
SELECT 
    i.name as index_name,
    i.type_desc as index_type,
    c.name as column_name
FROM sys.indexes i
JOIN sys.index_columns ic ON i.object_id = ic.object_id AND i.index_id = ic.index_id
JOIN sys.columns c ON ic.object_id = c.object_id AND ic.column_id = c.column_id
WHERE i.object_id = OBJECT_ID('employees');
```

### Basic Query Questions

1. Write a query to create an index on the email column of the customers table. _(Asked in TCS)_

**🧩 Foundation:** Use CREATE INDEX statement to create an index on the email column for faster lookups.

**⚙️ Function:** Improve query performance when searching or filtering by email address.

**🔁 Flow:**
```sql
-- Basic index on email column
CREATE INDEX idx_customers_email ON customers(email);

-- Unique index to prevent duplicate emails
CREATE UNIQUE INDEX idx_customers_email_unique ON customers(email);

-- Index with specific name
CREATE INDEX idx_customer_email_lookup ON customers(email);

-- Composite index with email and other columns
CREATE INDEX idx_customers_email_name ON customers(email, name);

-- Index with included columns (covering index)
CREATE INDEX idx_customers_email_covering ON customers(email) 
INCLUDE (name, phone, active);

-- Partial index for active customers only
CREATE INDEX idx_active_customers_email ON customers(email) 
WHERE active = 1;

-- Index with specific storage parameters
CREATE INDEX idx_customers_email ON customers(email) 
USING BTREE;

-- Checking if index was created
SHOW INDEX FROM customers WHERE Key_name = 'idx_customers_email';

-- Using the index in queries
SELECT name, phone FROM customers WHERE email = 'john@example.com';
SELECT name FROM customers WHERE email LIKE '%@gmail.com';

-- Dropping the index if needed
DROP INDEX idx_customers_email ON customers;
```

### Intermediate Questions

1. How do you create an index on a table, and what are the trade-offs? _(Asked in Wipro)_

**🧩 Foundation:** Indexes improve query performance but increase storage space and slow down INSERT/UPDATE/DELETE operations.

**⚙️ Function:** Balance between query performance and write performance based on application needs.

**🔁 Flow:**
```sql
-- Creating indexes with different strategies
CREATE INDEX idx_employees_department ON employees(department_id);
CREATE INDEX idx_employees_salary ON employees(salary);
CREATE INDEX idx_employees_hire_date ON employees(hire_date);

-- Composite index for multiple columns
CREATE INDEX idx_employees_dept_salary ON employees(department_id, salary);

-- Trade-off: More indexes = slower INSERT
INSERT INTO employees (name, email, department_id, salary) 
VALUES ('John Doe', 'john@example.com', 1, 50000);  -- Slower with many indexes

-- Trade-off: More indexes = slower UPDATE
UPDATE employees SET salary = 55000 WHERE id = 1;  -- Updates all indexes

-- Trade-off: More indexes = slower DELETE
DELETE FROM employees WHERE id = 1;  -- Removes from all indexes

-- Benefit: Faster SELECT queries
SELECT name, salary FROM employees WHERE department_id = 1;  -- Uses index
SELECT name FROM employees ORDER BY salary;  -- Uses index

-- Covering index to reduce lookups
CREATE INDEX idx_employees_dept_covering ON employees(department_id) 
INCLUDE (name, salary);

-- Partial index to reduce storage
CREATE INDEX idx_active_employees ON employees(department_id) 
WHERE active = 1;

-- Monitoring index usage
SELECT 
    index_name,
    table_name,
    cardinality,
    sub_part,
    packed,
    null,
    index_type
FROM information_schema.statistics 
WHERE table_schema = 'your_database'
AND table_name = 'employees';

-- Analyzing index effectiveness
EXPLAIN SELECT name FROM employees WHERE department_id = 1;

-- Dropping unused indexes
DROP INDEX idx_unused_index ON employees;
```
2. What is a composite index, and when should you use it? _(Asked in Accenture)_

**🧩 Foundation:** A composite index is an index on multiple columns, useful when queries filter or sort by multiple columns together.

**⚙️ Function:** Optimize queries that use multiple columns in WHERE, ORDER BY, or JOIN conditions.

**🔁 Flow:**
```sql
-- Creating composite indexes
CREATE INDEX idx_employees_dept_salary ON employees(department_id, salary);
CREATE INDEX idx_orders_customer_date ON orders(customer_id, order_date);
CREATE INDEX idx_products_category_price ON products(category_id, price);

-- Composite index with three columns
CREATE INDEX idx_employees_dept_salary_hire ON employees(department_id, salary, hire_date);

-- Using composite indexes in queries
SELECT name, salary FROM employees 
WHERE department_id = 1 AND salary > 50000;  -- Uses composite index

SELECT name FROM employees 
WHERE department_id = 1 
ORDER BY salary;  -- Uses composite index

-- Partial composite index usage
SELECT name FROM employees 
WHERE department_id = 1;  -- Uses first column of composite index

-- Composite index for JOIN operations
SELECT e.name, d.department_name 
FROM employees e
JOIN departments d ON e.department_id = d.id
WHERE e.salary > 50000;  -- Uses composite index

-- Composite index with included columns
CREATE INDEX idx_employees_dept_salary_covering ON employees(department_id, salary) 
INCLUDE (name, email);

-- Order matters in composite indexes
CREATE INDEX idx_employees_salary_dept ON employees(salary, department_id);  -- Different order

-- Query that uses salary first (needs different index order)
SELECT name FROM employees 
WHERE salary > 50000 AND department_id = 1;  -- Better with salary first

-- Composite unique index
CREATE UNIQUE INDEX idx_employees_email_dept ON employees(email, department_id);

-- Composite index for date ranges
CREATE INDEX idx_orders_date_customer ON orders(order_date, customer_id);

-- Using composite index for range queries
SELECT * FROM orders 
WHERE order_date >= '2024-01-01' AND customer_id = 123;  -- Uses composite index
```

### Intermediate Query Questions

1. Write a query to create a composite index on order_date and customer_id in the orders table. _(Asked in Wipro)_

**🧩 Foundation:** Create a composite index on order_date and customer_id to optimize queries that filter or sort by both columns.

**⚙️ Function:** Improve performance for queries that search orders by date and customer combinations.

**🔁 Flow:**
```sql
-- Basic composite index
CREATE INDEX idx_orders_date_customer ON orders(order_date, customer_id);

-- Composite index with specific name
CREATE INDEX idx_orders_date_customer_lookup ON orders(order_date, customer_id);

-- Composite index with included columns for covering
CREATE INDEX idx_orders_date_customer_covering ON orders(order_date, customer_id) 
INCLUDE (total_amount, status);

-- Composite unique index (if needed)
CREATE UNIQUE INDEX idx_orders_date_customer_unique ON orders(order_date, customer_id);

-- Using the composite index in queries
SELECT * FROM orders 
WHERE order_date >= '2024-01-01' AND customer_id = 123;  -- Uses composite index

SELECT * FROM orders 
WHERE order_date = '2024-01-15' AND customer_id IN (1, 2, 3);  -- Uses composite index

SELECT customer_id, COUNT(*) as order_count
FROM orders 
WHERE order_date >= '2024-01-01' AND order_date <= '2024-01-31'
GROUP BY customer_id;  -- Uses composite index

-- Order by using composite index
SELECT * FROM orders 
WHERE customer_id = 123 
ORDER BY order_date;  -- Uses composite index

-- Partial composite index for specific date range
CREATE INDEX idx_orders_recent_date_customer ON orders(order_date, customer_id) 
WHERE order_date >= '2024-01-01';

-- Composite index with different column order
CREATE INDEX idx_orders_customer_date ON orders(customer_id, order_date);

-- Checking if index was created
SHOW INDEX FROM orders WHERE Key_name LIKE '%date_customer%';

-- Analyzing index usage
EXPLAIN SELECT * FROM orders 
WHERE order_date >= '2024-01-01' AND customer_id = 123;
```

### Advanced Questions

1. How does a database engine use an index to optimize query performance? _(Asked in Cognizant)_

**🧩 Foundation:** Database engines use indexes to quickly locate data without scanning entire tables, using B-tree structures for efficient lookups.

**⚙️ Function:** Indexes provide fast access paths to data, reducing I/O operations and improving query execution time.

**🔁 Flow:**
```sql
-- Creating indexes for optimization
CREATE INDEX idx_employees_department ON employees(department_id);
CREATE INDEX idx_employees_salary ON employees(salary);
CREATE INDEX idx_employees_email ON employees(email);

-- Query that uses index for fast lookup
SELECT name, salary FROM employees WHERE department_id = 1;  -- Uses B-tree index

-- Query that uses index for range scan
SELECT name, salary FROM employees WHERE salary BETWEEN 40000 AND 60000;  -- Uses index

-- Query that uses index for sorting
SELECT name, salary FROM employees ORDER BY department_id;  -- Uses index

-- Query that uses index for JOIN
SELECT e.name, d.department_name 
FROM employees e
JOIN departments d ON e.department_id = d.id;  -- Uses index on department_id

-- Composite index usage
CREATE INDEX idx_employees_dept_salary ON employees(department_id, salary);

SELECT name FROM employees 
WHERE department_id = 1 AND salary > 50000;  -- Uses composite index

-- Covering index to avoid table lookups
CREATE INDEX idx_employees_dept_covering ON employees(department_id) 
INCLUDE (name, salary);

SELECT name, salary FROM employees WHERE department_id = 1;  -- Uses covering index

-- Index for unique constraints
CREATE UNIQUE INDEX idx_employees_email_unique ON employees(email);

-- Analyzing index usage with EXPLAIN
EXPLAIN SELECT name FROM employees WHERE department_id = 1;

-- Checking index statistics
SELECT 
    index_name,
    cardinality,
    sub_part,
    packed,
    null,
    index_type
FROM information_schema.statistics 
WHERE table_name = 'employees';

-- Index maintenance
ANALYZE TABLE employees;  -- Update index statistics
OPTIMIZE TABLE employees;  -- Rebuild indexes
```
2. What is the impact of indexes on INSERT, UPDATE, and DELETE operations? _(Asked in TCS Digital)_

**🧩 Foundation:** Indexes improve SELECT performance but slow down INSERT, UPDATE, and DELETE operations because the database must maintain all indexes.

**⚙️ Function:** Balance between read and write performance based on application requirements.

**🔁 Flow:**
```sql
-- Creating multiple indexes
CREATE INDEX idx_employees_department ON employees(department_id);
CREATE INDEX idx_employees_salary ON employees(salary);
CREATE INDEX idx_employees_email ON employees(email);
CREATE INDEX idx_employees_hire_date ON employees(hire_date);

-- INSERT operation - slower with more indexes
INSERT INTO employees (name, email, department_id, salary, hire_date) 
VALUES ('John Doe', 'john@example.com', 1, 50000, '2024-01-15');  -- Updates 4 indexes

-- Bulk INSERT - significantly slower with indexes
INSERT INTO employees (name, email, department_id, salary, hire_date)
SELECT name, email, department_id, salary, hire_date FROM temp_employees;  -- Updates all indexes

-- UPDATE operation - slower with more indexes
UPDATE employees 
SET salary = 55000, department_id = 2 
WHERE id = 1;  -- Updates indexes on salary and department_id

-- DELETE operation - slower with more indexes
DELETE FROM employees WHERE id = 1;  -- Removes from all indexes

-- Optimizing INSERT operations
-- Disable indexes temporarily for bulk operations
ALTER TABLE employees DISABLE KEYS;
INSERT INTO employees (name, email, department_id, salary) 
SELECT name, email, department_id, salary FROM temp_employees;
ALTER TABLE employees ENABLE KEYS;

-- Using LOAD DATA INFILE for bulk inserts
LOAD DATA INFILE 'employees.csv' 
INTO TABLE employees 
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

-- Minimizing index impact on UPDATE
UPDATE employees 
SET name = 'John Smith' 
WHERE id = 1;  -- Only affects primary key index, not other indexes

-- Batch operations to reduce index overhead
UPDATE employees 
SET salary = salary * 1.1 
WHERE department_id = 1;  -- Updates salary index once per affected row

-- Monitoring index impact
SHOW STATUS LIKE 'Handler_write%';
SHOW STATUS LIKE 'Slow_queries';

-- Analyzing write performance
EXPLAIN UPDATE employees SET salary = 55000 WHERE id = 1;
```

### Advanced Query Questions

1. Write a query to drop an index named idx_email from the customers table. _(Asked in Cognizant)_

**🧩 Foundation:** Use DROP INDEX statement to remove an index from a table when it's no longer needed.

**⚙️ Function:** Remove unnecessary indexes to improve write performance and reduce storage overhead.

**🔁 Flow:**
```sql
-- Basic DROP INDEX statement
DROP INDEX idx_email ON customers;

-- Drop index with specific syntax
DROP INDEX idx_email FROM customers;

-- Drop index using ALTER TABLE
ALTER TABLE customers DROP INDEX idx_email;

-- Drop multiple indexes
DROP INDEX idx_email ON customers;
DROP INDEX idx_customer_name ON customers;

-- Drop index with IF EXISTS (MySQL 8.0+)
DROP INDEX IF EXISTS idx_email ON customers;

-- Drop index and verify it's gone
DROP INDEX idx_email ON customers;
SHOW INDEX FROM customers WHERE Key_name = 'idx_email';

-- Drop index and recreate it
DROP INDEX idx_email ON customers;
CREATE INDEX idx_email ON customers(email);

-- Drop index with specific database
DROP INDEX idx_email ON database_name.customers;

-- Drop index and check table structure
DROP INDEX idx_email ON customers;
DESCRIBE customers;
SHOW CREATE TABLE customers;

-- Drop index and monitor performance impact
-- Before dropping
EXPLAIN SELECT * FROM customers WHERE email = 'test@example.com';

DROP INDEX idx_email ON customers;

-- After dropping
EXPLAIN SELECT * FROM customers WHERE email = 'test@example.com';

-- Drop index and recreate with different structure
DROP INDEX idx_email ON customers;
CREATE INDEX idx_email_covering ON customers(email) INCLUDE (name, phone);
```

### Tough Questions

1. How would you design an indexing strategy for a high-write, high-read application? _(Asked in Amazon)_

**🧩 Foundation:** Balance between read and write performance by creating minimal but effective indexes, using covering indexes, and considering read replicas.

**⚙️ Function:** Optimize for both frequent writes and reads while maintaining acceptable performance for both operations.

**🔁 Flow:**
```sql
-- Strategy 1: Minimal essential indexes only
CREATE INDEX idx_employees_email ON employees(email);  -- For lookups
CREATE INDEX idx_employees_department ON employees(department_id);  -- For filtering
CREATE INDEX idx_orders_customer_date ON orders(customer_id, order_date);  -- For joins

-- Strategy 2: Covering indexes to reduce table lookups
CREATE INDEX idx_employees_dept_covering ON employees(department_id) 
INCLUDE (name, salary, email);

-- Strategy 3: Partial indexes for active data only
CREATE INDEX idx_active_employees ON employees(department_id) 
WHERE active = 1;

-- Strategy 4: Composite indexes for common query patterns
CREATE INDEX idx_orders_status_date ON orders(status, order_date);
CREATE INDEX idx_employees_dept_salary ON employees(department_id, salary);

-- Strategy 5: Separate read and write tables
-- Write table (minimal indexes)
CREATE TABLE orders_write (
    id INT PRIMARY KEY,
    customer_id INT,
    order_date DATE,
    total_amount DECIMAL(10,2)
);

-- Read table (full indexes)
CREATE TABLE orders_read (
    id INT PRIMARY KEY,
    customer_id INT,
    order_date DATE,
    total_amount DECIMAL(10,2),
    INDEX idx_customer_date (customer_id, order_date),
    INDEX idx_date (order_date),
    INDEX idx_amount (total_amount)
);

-- Strategy 6: Partitioning for large tables
CREATE TABLE orders_partitioned (
    id INT,
    customer_id INT,
    order_date DATE,
    total_amount DECIMAL(10,2)
) PARTITION BY RANGE (YEAR(order_date)) (
    PARTITION p2023 VALUES LESS THAN (2024),
    PARTITION p2024 VALUES LESS THAN (2025),
    PARTITION p2025 VALUES LESS THAN (2026)
);

-- Strategy 7: Index maintenance schedule
-- During low-traffic hours
OPTIMIZE TABLE employees;
ANALYZE TABLE employees;

-- Strategy 8: Monitoring index usage
SELECT 
    index_name,
    table_name,
    cardinality,
    sub_part
FROM information_schema.statistics 
WHERE table_schema = 'your_database'
AND table_name = 'employees'
ORDER BY cardinality DESC;

-- Strategy 9: Read replica setup
-- Master (write-optimized)
CREATE INDEX idx_orders_customer ON orders(customer_id);

-- Replica (read-optimized)
CREATE INDEX idx_orders_customer_date_amount ON orders(customer_id, order_date, total_amount);
CREATE INDEX idx_orders_date ON orders(order_date);
CREATE INDEX idx_orders_status ON orders(status);

-- Strategy 10: Adaptive indexing
-- Monitor query patterns and create indexes based on usage
SELECT 
    query,
    COUNT(*) as execution_count,
    AVG(duration) as avg_duration
FROM performance_schema.events_statements_summary_by_digest
WHERE query LIKE '%employees%'
GROUP BY query
ORDER BY execution_count DESC;
```

### Situational / Real-world Questions

1. Your database query is slow due to missing indexes. How would you identify and add the right indexes? _(Asked in HCL)_

**🧩 Foundation:** Use EXPLAIN, slow query logs, and performance monitoring to identify missing indexes and create appropriate ones.

**⚙️ Function:** Diagnose performance issues and implement targeted indexing solutions.

**🔁 Flow:**
```sql
-- Step 1: Identify slow queries
SELECT query, COUNT(*) as execution_count, AVG(duration) as avg_duration
FROM performance_schema.events_statements_summary_by_digest
WHERE avg_duration > 1000000  -- Queries taking more than 1 second
ORDER BY avg_duration DESC;

-- Step 2: Analyze specific slow query
EXPLAIN SELECT e.name, d.department_name 
FROM employees e
JOIN departments d ON e.department_id = d.id
WHERE e.salary > 50000;

-- Step 3: Check existing indexes
SHOW INDEX FROM employees;
SHOW INDEX FROM departments;

-- Step 4: Identify missing indexes from EXPLAIN output
-- If 'type' shows 'ALL', index is missing
-- If 'key' shows NULL, index is not being used

-- Step 5: Create missing indexes based on WHERE clauses
CREATE INDEX idx_employees_salary ON employees(salary);
CREATE INDEX idx_employees_department ON employees(department_id);

-- Step 6: Create indexes for JOIN conditions
CREATE INDEX idx_employees_dept_id ON employees(department_id);
CREATE INDEX idx_departments_id ON departments(id);

-- Step 7: Create composite indexes for multiple conditions
CREATE INDEX idx_employees_dept_salary ON employees(department_id, salary);

-- Step 8: Create covering indexes to avoid table lookups
CREATE INDEX idx_employees_dept_covering ON employees(department_id) 
INCLUDE (name, salary);

-- Step 9: Verify index usage
EXPLAIN SELECT e.name, d.department_name 
FROM employees e
JOIN departments d ON e.department_id = d.id
WHERE e.salary > 50000;

-- Step 10: Monitor performance improvement
SELECT 
    query,
    COUNT(*) as execution_count,
    AVG(duration) as avg_duration,
    MAX(duration) as max_duration
FROM performance_schema.events_statements_summary_by_digest
WHERE query LIKE '%employees%'
GROUP BY query
ORDER BY avg_duration DESC;

-- Step 11: Check index usage statistics
SELECT 
    index_name,
    table_name,
    cardinality,
    sub_part
FROM information_schema.statistics 
WHERE table_schema = 'your_database'
AND table_name = 'employees';

-- Step 12: Remove unused indexes
SELECT 
    index_name,
    table_name
FROM information_schema.statistics 
WHERE table_schema = 'your_database'
AND cardinality = 0;  -- Unused indexes

-- Step 13: Optimize table after adding indexes
OPTIMIZE TABLE employees;
ANALYZE TABLE employees;
```
2. Write a query to analyze whether an index is being used for a specific query. _(Asked in HCL)_

**🧩 Foundation:** Use EXPLAIN, SHOW INDEX, and performance monitoring queries to analyze index usage for specific queries.

**⚙️ Function:** Determine if indexes are being utilized and identify opportunities for optimization.

**🔁 Flow:**
```sql
-- Method 1: Using EXPLAIN to analyze index usage
EXPLAIN SELECT name, salary FROM employees WHERE department_id = 1;

-- Method 2: Detailed EXPLAIN analysis
EXPLAIN FORMAT=JSON SELECT name, salary FROM employees WHERE department_id = 1;

-- Method 3: Check if specific index is being used
EXPLAIN SELECT name, salary FROM employees 
WHERE department_id = 1 AND salary > 50000;

-- Method 4: Compare queries with and without indexes
-- Before creating index
EXPLAIN SELECT name FROM employees WHERE email = 'john@example.com';

-- After creating index
CREATE INDEX idx_employees_email ON employees(email);
EXPLAIN SELECT name FROM employees WHERE email = 'john@example.com';

-- Method 5: Analyze index usage statistics
SELECT 
    index_name,
    table_name,
    cardinality,
    sub_part,
    packed,
    null,
    index_type
FROM information_schema.statistics 
WHERE table_schema = 'your_database'
AND table_name = 'employees'
AND index_name = 'idx_employees_email';

-- Method 6: Check index usage in performance schema
SELECT 
    object_name,
    index_name,
    count_read,
    count_write,
    count_fetch,
    count_insert,
    count_update,
    count_delete
FROM performance_schema.table_io_waits_summary_by_index_usage
WHERE object_schema = 'your_database'
AND object_name = 'employees';

-- Method 7: Analyze query performance with different indexes
-- Query using single column index
EXPLAIN SELECT name FROM employees WHERE department_id = 1;

-- Query using composite index
EXPLAIN SELECT name FROM employees 
WHERE department_id = 1 AND salary > 50000;

-- Method 8: Check index cardinality and selectivity
SELECT 
    index_name,
    cardinality,
    (cardinality / (SELECT COUNT(*) FROM employees)) * 100 as selectivity_percent
FROM information_schema.statistics 
WHERE table_schema = 'your_database'
AND table_name = 'employees';

-- Method 9: Analyze covering index usage
CREATE INDEX idx_employees_dept_covering ON employees(department_id) 
INCLUDE (name, salary);

EXPLAIN SELECT name, salary FROM employees WHERE department_id = 1;

-- Method 10: Monitor index usage over time
SELECT 
    query,
    COUNT(*) as execution_count,
    AVG(duration) as avg_duration,
    SUM(rows_examined) as total_rows_examined
FROM performance_schema.events_statements_summary_by_digest
WHERE query LIKE '%employees%'
AND query LIKE '%department_id%'
GROUP BY query
ORDER BY execution_count DESC;

-- Method 11: Check for unused indexes
SELECT 
    index_name,
    table_name,
    cardinality
FROM information_schema.statistics 
WHERE table_schema = 'your_database'
AND cardinality = 0;  -- Unused indexes

-- Method 12: Analyze index fragmentation
SELECT 
    table_name,
    index_name,
    avg_fragmentation_in_percent
FROM sys.dm_db_index_physical_stats(DB_ID(), NULL, NULL, NULL, 'LIMITED')
WHERE avg_fragmentation_in_percent > 10;  -- Fragmented indexes
```

---

## 4. Transactions and Concurrency

### Basic Questions

1. What is a transaction in SQL, and why is it important? _(Asked in TCS, Capgemini)_

**🧩 Foundation:** A transaction is a logical unit of work that ensures data consistency by grouping multiple SQL operations together.

**⚙️ Function:** Transactions provide ACID properties to maintain data integrity and handle concurrent access safely.

**🔁 Flow:**
```sql
-- Basic transaction structure
START TRANSACTION;
    INSERT INTO employees (name, email, department_id) VALUES ('John Doe', 'john@example.com', 1);
    UPDATE departments SET employee_count = employee_count + 1 WHERE id = 1;
COMMIT;

-- Transaction with error handling
START TRANSACTION;
    INSERT INTO employees (name, email, department_id) VALUES ('Jane Smith', 'jane@example.com', 2);
    UPDATE departments SET employee_count = employee_count + 1 WHERE id = 2;
    
    -- Check for errors
    IF @@ERROR <> 0 THEN
        ROLLBACK;
    ELSE
        COMMIT;
    END IF;

-- Transaction with savepoints
START TRANSACTION;
    INSERT INTO employees (name, email) VALUES ('Bob Johnson', 'bob@example.com');
    SAVEPOINT sp1;
    
    UPDATE employees SET department_id = 1 WHERE email = 'bob@example.com';
    
    -- Rollback to savepoint if needed
    ROLLBACK TO SAVEPOINT sp1;
    
    UPDATE employees SET department_id = 2 WHERE email = 'bob@example.com';
COMMIT;

-- Nested transactions
BEGIN TRANSACTION;
    INSERT INTO employees (name, email) VALUES ('Alice Brown', 'alice@example.com');
    
    BEGIN TRANSACTION;
        UPDATE employees SET department_id = 1 WHERE email = 'alice@example.com';
        UPDATE departments SET employee_count = employee_count + 1 WHERE id = 1;
    COMMIT;
    
    UPDATE employees SET salary = 50000 WHERE email = 'alice@example.com';
COMMIT;

-- Transaction with multiple tables
START TRANSACTION;
    INSERT INTO employees (name, email, department_id) VALUES ('Charlie Wilson', 'charlie@example.com', 1);
    INSERT INTO employee_projects (employee_id, project_id) VALUES (LAST_INSERT_ID(), 1);
    UPDATE projects SET team_size = team_size + 1 WHERE id = 1;
    UPDATE departments SET employee_count = employee_count + 1 WHERE id = 1;
COMMIT;

-- Transaction with validation
START TRANSACTION;
    DECLARE @dept_id INT = 1;
    DECLARE @max_employees INT;
    
    SELECT @max_employees = max_employees FROM departments WHERE id = @dept_id;
    
    IF (SELECT COUNT(*) FROM employees WHERE department_id = @dept_id) < @max_employees THEN
        INSERT INTO employees (name, email, department_id) VALUES ('David Lee', 'david@example.com', @dept_id);
        COMMIT;
    ELSE
        ROLLBACK;
    END IF;
```
2. What are the ACID properties of a transaction? _(Asked in Infosys)_

**🧩 Foundation:** ACID stands for Atomicity, Consistency, Isolation, and Durability - the four essential properties that ensure reliable transaction processing.

**⚙️ Function:** ACID properties guarantee data integrity and reliability in database transactions.

**🔁 Flow:**
```sql
-- ATOMICITY - All operations succeed or all fail
START TRANSACTION;
    INSERT INTO employees (name, email) VALUES ('John Doe', 'john@example.com');
    UPDATE departments SET employee_count = employee_count + 1 WHERE id = 1;
    -- If any operation fails, entire transaction rolls back
COMMIT;

-- Demonstrating atomicity with error
START TRANSACTION;
    INSERT INTO employees (name, email) VALUES ('Jane Smith', 'jane@example.com');
    INSERT INTO employees (name, email) VALUES (NULL, 'invalid@example.com');  -- This will fail
    UPDATE departments SET employee_count = employee_count + 2 WHERE id = 1;
    -- All changes will be rolled back due to NULL constraint violation
COMMIT;

-- CONSISTENCY - Database remains in valid state
START TRANSACTION;
    -- Check constraint before update
    IF (SELECT COUNT(*) FROM employees WHERE department_id = 1) < 10 THEN
        INSERT INTO employees (name, email, department_id) VALUES ('Bob Johnson', 'bob@example.com', 1);
        UPDATE departments SET employee_count = employee_count + 1 WHERE id = 1;
        COMMIT;
    ELSE
        ROLLBACK;  -- Maintains consistency
    END IF;

-- ISOLATION - Concurrent transactions don't interfere
-- Transaction 1
START TRANSACTION;
    SELECT salary FROM employees WHERE id = 1;  -- Reads 50000
    UPDATE employees SET salary = 55000 WHERE id = 1;
    -- Transaction 2 cannot see this change until commit
COMMIT;

-- Transaction 2 (concurrent)
START TRANSACTION;
    SELECT salary FROM employees WHERE id = 1;  -- Still sees 50000 until Transaction 1 commits
    UPDATE employees SET salary = 52000 WHERE id = 1;
COMMIT;

-- DURABILITY - Committed changes persist
START TRANSACTION;
    INSERT INTO employees (name, email) VALUES ('Alice Brown', 'alice@example.com');
    UPDATE departments SET employee_count = employee_count + 1 WHERE id = 1;
COMMIT;  -- Changes are now permanent, even if system crashes

-- Demonstrating all ACID properties together
START TRANSACTION;
    -- Atomicity: All operations succeed or fail together
    INSERT INTO employees (name, email, department_id) VALUES ('Charlie Wilson', 'charlie@example.com', 1);
    
    -- Consistency: Check business rules
    IF (SELECT COUNT(*) FROM employees WHERE department_id = 1) <= 10 THEN
        UPDATE departments SET employee_count = employee_count + 1 WHERE id = 1;
        
        -- Isolation: Other transactions can't see these changes yet
        -- Durability: Will persist after commit
        COMMIT;
    ELSE
        ROLLBACK;  -- Maintains consistency
    END IF;

-- ACID with savepoints
START TRANSACTION;
    INSERT INTO employees (name, email) VALUES ('David Lee', 'david@example.com');
    SAVEPOINT sp1;
    
    UPDATE employees SET department_id = 1 WHERE email = 'david@example.com';
    
    -- Can rollback to savepoint while maintaining ACID
    ROLLBACK TO SAVEPOINT sp1;
    
    UPDATE employees SET department_id = 2 WHERE email = 'david@example.com';
COMMIT;
```

### Basic Query Questions

1. Write a query to start a transaction, update a record, and commit it. _(Asked in TCS)_

**🧩 Foundation:** Use START TRANSACTION, perform the update operation, and COMMIT to make changes permanent.

**⚙️ Function:** Ensure data consistency by grouping the update operation within a transaction boundary.

**🔁 Flow:**
```sql
-- Basic transaction with update
START TRANSACTION;
    UPDATE employees SET salary = 55000 WHERE id = 1;
COMMIT;

-- Transaction with multiple updates
START TRANSACTION;
    UPDATE employees SET salary = 55000 WHERE id = 1;
    UPDATE employees SET last_updated = CURRENT_TIMESTAMP WHERE id = 1;
    UPDATE departments SET total_salary = total_salary + 5000 WHERE id = 1;
COMMIT;

-- Transaction with error handling
START TRANSACTION;
    UPDATE employees SET salary = 55000 WHERE id = 1;
    
    IF @@ROWCOUNT = 0 THEN
        ROLLBACK;
    ELSE
        COMMIT;
    END IF;

-- Transaction with validation
START TRANSACTION;
    DECLARE @old_salary DECIMAL(10,2);
    SELECT @old_salary = salary FROM employees WHERE id = 1;
    
    IF @old_salary < 60000 THEN
        UPDATE employees SET salary = 55000 WHERE id = 1;
        COMMIT;
    ELSE
        ROLLBACK;
    END IF;

-- Transaction with savepoint
START TRANSACTION;
    UPDATE employees SET salary = 55000 WHERE id = 1;
    SAVEPOINT salary_update;
    
    UPDATE employees SET department_id = 2 WHERE id = 1;
    
    -- Rollback department change but keep salary change
    ROLLBACK TO SAVEPOINT salary_update;
    
    UPDATE employees SET department_id = 3 WHERE id = 1;
COMMIT;

-- Transaction with multiple tables
START TRANSACTION;
    UPDATE employees SET salary = 55000 WHERE id = 1;
    UPDATE salary_history SET end_date = CURRENT_DATE WHERE employee_id = 1 AND end_date IS NULL;
    INSERT INTO salary_history (employee_id, salary, start_date) VALUES (1, 55000, CURRENT_DATE);
COMMIT;

-- Transaction with conditional update
START TRANSACTION;
    IF EXISTS (SELECT 1 FROM employees WHERE id = 1 AND active = 1) THEN
        UPDATE employees SET salary = 55000 WHERE id = 1;
        COMMIT;
    ELSE
        ROLLBACK;
    END IF;

-- Transaction with audit trail
START TRANSACTION;
    INSERT INTO audit_log (table_name, action, record_id, old_value, new_value, user_id)
    SELECT 'employees', 'UPDATE', id, salary, 55000, USER_ID()
    FROM employees WHERE id = 1;
    
    UPDATE employees SET salary = 55000 WHERE id = 1;
COMMIT;
```

### Intermediate Questions

1. How do you use COMMIT and ROLLBACK in SQL? _(Asked in Wipro)_

**🧩 Foundation:** COMMIT makes transaction changes permanent, while ROLLBACK undoes all changes made in the current transaction.

**⚙️ Function:** Control transaction outcomes - either save all changes or discard them entirely.

**🔁 Flow:**
```sql
-- Basic COMMIT and ROLLBACK usage
START TRANSACTION;
    INSERT INTO employees (name, email) VALUES ('John Doe', 'john@example.com');
    UPDATE departments SET employee_count = employee_count + 1 WHERE id = 1;
    
    -- If everything is OK, commit
    COMMIT;
    -- If there's an error, rollback
    -- ROLLBACK;

-- COMMIT after successful operations
START TRANSACTION;
    INSERT INTO employees (name, email, department_id) VALUES ('Jane Smith', 'jane@example.com', 1);
    UPDATE departments SET employee_count = employee_count + 1 WHERE id = 1;
    INSERT INTO employee_projects (employee_id, project_id) VALUES (LAST_INSERT_ID(), 1);
COMMIT;  -- All changes are now permanent

-- ROLLBACK on error
START TRANSACTION;
    INSERT INTO employees (name, email) VALUES ('Bob Johnson', 'bob@example.com');
    
    -- Simulate an error
    INSERT INTO employees (name, email) VALUES (NULL, 'invalid@example.com');  -- This will fail
    
    -- Rollback all changes due to error
    ROLLBACK;

-- Conditional COMMIT/ROLLBACK
START TRANSACTION;
    INSERT INTO employees (name, email, department_id) VALUES ('Alice Brown', 'alice@example.com', 1);
    
    -- Check if department exists
    IF EXISTS (SELECT 1 FROM departments WHERE id = 1) THEN
        UPDATE departments SET employee_count = employee_count + 1 WHERE id = 1;
        COMMIT;
    ELSE
        ROLLBACK;  -- Rollback if department doesn't exist
    END IF;

-- ROLLBACK with savepoints
START TRANSACTION;
    INSERT INTO employees (name, email) VALUES ('Charlie Wilson', 'charlie@example.com');
    SAVEPOINT sp1;
    
    UPDATE employees SET department_id = 1 WHERE email = 'charlie@example.com';
    
    -- Rollback to savepoint (keeps the insert, undoes the update)
    ROLLBACK TO SAVEPOINT sp1;
    
    UPDATE employees SET department_id = 2 WHERE email = 'charlie@example.com';
COMMIT;

-- Error handling with ROLLBACK
START TRANSACTION;
    DECLARE @error_count INT = 0;
    
    BEGIN TRY
        INSERT INTO employees (name, email) VALUES ('David Lee', 'david@example.com');
        UPDATE departments SET employee_count = employee_count + 1 WHERE id = 1;
        COMMIT;
    END TRY
    BEGIN CATCH
        SET @error_count = @error_count + 1;
        ROLLBACK;
    END CATCH;

-- ROLLBACK in nested transactions
BEGIN TRANSACTION;
    INSERT INTO employees (name, email) VALUES ('Eve Johnson', 'eve@example.com');
    
    BEGIN TRANSACTION;
        UPDATE employees SET department_id = 1 WHERE email = 'eve@example.com';
        -- Inner transaction rollback
        ROLLBACK;
    
    -- Outer transaction continues
    UPDATE employees SET salary = 50000 WHERE email = 'eve@example.com';
COMMIT;

-- ROLLBACK with audit trail
START TRANSACTION;
    INSERT INTO audit_log (action, table_name, record_id, user_id)
    VALUES ('INSERT', 'employees', 1, USER_ID());
    
    INSERT INTO employees (name, email) VALUES ('Frank Miller', 'frank@example.com');
    
    IF @@ERROR <> 0 THEN
        ROLLBACK;  -- Rolls back both audit log and employee insert
    ELSE
        COMMIT;
    END IF;
```
2. What is the difference between SERIALIZABLE and REPEATABLE READ isolation levels? _(Asked in Accenture)_

**🧩 Foundation:** SERIALIZABLE provides the highest isolation level preventing all concurrency anomalies, while REPEATABLE READ prevents dirty reads and non-repeatable reads but allows phantom reads.

**⚙️ Function:** Different isolation levels balance between data consistency and concurrency performance.

**🔁 Flow:**
```sql
-- Setting isolation levels
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

-- SERIALIZABLE - Prevents all concurrency anomalies
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
START TRANSACTION;
    -- This transaction will see a consistent snapshot
    SELECT COUNT(*) FROM employees WHERE department_id = 1;  -- Returns 5
    
    -- Other transactions cannot modify this data until this transaction completes
    -- This prevents phantom reads, dirty reads, and non-repeatable reads
    
    UPDATE employees SET salary = salary * 1.1 WHERE department_id = 1;
COMMIT;

-- REPEATABLE READ - Prevents dirty reads and non-repeatable reads
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
START TRANSACTION;
    SELECT salary FROM employees WHERE id = 1;  -- Returns 50000
    
    -- Other transactions can insert new rows (phantom reads possible)
    -- But cannot modify existing rows that this transaction has read
    
    SELECT salary FROM employees WHERE id = 1;  -- Still returns 50000 (non-repeatable read prevented)
COMMIT;

-- Demonstrating phantom reads in REPEATABLE READ
-- Transaction 1
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
START TRANSACTION;
    SELECT COUNT(*) FROM employees WHERE department_id = 1;  -- Returns 5
    -- Transaction 2 can insert new employees in department 1
    
    SELECT COUNT(*) FROM employees WHERE department_id = 1;  -- Still returns 5 (phantom read prevented)
COMMIT;

-- Transaction 2 (concurrent)
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
START TRANSACTION;
    INSERT INTO employees (name, email, department_id) VALUES ('New Employee', 'new@example.com', 1);
COMMIT;

-- SERIALIZABLE prevents phantom reads
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
START TRANSACTION;
    SELECT COUNT(*) FROM employees WHERE department_id = 1;  -- Returns 5
    
    -- This will block until Transaction 2 commits or rolls back
    UPDATE employees SET salary = salary * 1.1 WHERE department_id = 1;
COMMIT;

-- Performance comparison
-- REPEATABLE READ (faster, less blocking)
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
START TRANSACTION;
    SELECT * FROM employees WHERE department_id = 1;
    -- Other transactions can proceed with inserts/updates
COMMIT;

-- SERIALIZABLE (slower, more blocking)
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
START TRANSACTION;
    SELECT * FROM employees WHERE department_id = 1;
    -- Other transactions are blocked until this completes
COMMIT;

-- Deadlock prevention in SERIALIZABLE
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
START TRANSACTION;
    UPDATE employees SET salary = 55000 WHERE id = 1;
    UPDATE employees SET salary = 52000 WHERE id = 2;
    -- This may cause deadlock with concurrent transactions
COMMIT;

-- Using row-level locking with REPEATABLE READ
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
START TRANSACTION;
    SELECT salary FROM employees WHERE id = 1 FOR UPDATE;  -- Row-level lock
    UPDATE employees SET salary = 55000 WHERE id = 1;
COMMIT;
```

### Intermediate Query Questions

1. Write a query to update a customer’s balance within a transaction and roll back if an error occurs. _(Asked in Wipro)_

### Advanced Questions

1. How do you handle deadlock situations in a database? _(Asked in Cognizant)_

**🧩 Foundation:** Deadlocks occur when two or more transactions wait for each other to release locks, creating a circular dependency.

**⚙️ Function:** Implement deadlock detection, prevention strategies, and retry mechanisms to handle deadlock situations.

**🔁 Flow:**
```sql
-- Deadlock detection and handling
-- Transaction 1
START TRANSACTION;
    UPDATE employees SET salary = 55000 WHERE id = 1;  -- Locks row 1
    -- Wait for row 2
    UPDATE employees SET salary = 52000 WHERE id = 2;  -- May cause deadlock
COMMIT;

-- Transaction 2 (concurrent)
START TRANSACTION;
    UPDATE employees SET salary = 52000 WHERE id = 2;  -- Locks row 2
    -- Wait for row 1
    UPDATE employees SET salary = 55000 WHERE id = 1;  -- May cause deadlock
COMMIT;

-- Deadlock prevention - consistent order
START TRANSACTION;
    -- Always update in ascending order to prevent deadlocks
    UPDATE employees SET salary = 55000 WHERE id = 1;
    UPDATE employees SET salary = 52000 WHERE id = 2;
COMMIT;

-- Deadlock prevention with timeout
SET LOCK_TIMEOUT = 5000;  -- 5 seconds timeout
START TRANSACTION;
    UPDATE employees SET salary = 55000 WHERE id = 1;
    
    -- If lock cannot be acquired within 5 seconds, transaction fails
    UPDATE employees SET salary = 52000 WHERE id = 2;
COMMIT;

-- Deadlock detection and retry
DECLARE @retry_count INT = 0;
DECLARE @max_retries INT = 3;

WHILE @retry_count < @max_retries
BEGIN
    BEGIN TRY
        START TRANSACTION;
            UPDATE employees SET salary = 55000 WHERE id = 1;
            UPDATE employees SET salary = 52000 WHERE id = 2;
        COMMIT;
        BREAK;  -- Success, exit loop
    END TRY
    BEGIN CATCH
        IF ERROR_NUMBER() = 1205 THEN  -- Deadlock error
            ROLLBACK;
            SET @retry_count = @retry_count + 1;
            WAITFOR DELAY '00:00:01';  -- Wait 1 second before retry
        ELSE
            THROW;  -- Re-throw non-deadlock errors
        END IF
    END CATCH
END

-- Using row-level locking to reduce deadlocks
START TRANSACTION;
    SELECT salary FROM employees WHERE id = 1 FOR UPDATE;  -- Row-level lock
    UPDATE employees SET salary = 55000 WHERE id = 1;
    
    SELECT salary FROM employees WHERE id = 2 FOR UPDATE;  -- Row-level lock
    UPDATE employees SET salary = 52000 WHERE id = 2;
COMMIT;

-- Deadlock monitoring
SELECT 
    request_session_id,
    resource_type,
    resource_description,
    request_mode,
    request_status
FROM sys.dm_tran_locks
WHERE request_status = 'WAIT';

-- Deadlock graph analysis
SELECT 
    event_time,
    deadlock_graph
FROM system_health_xe_file_target
WHERE event_name = 'xml_deadlock_report';

-- Preventing deadlocks with application-level ordering
-- Always access tables in the same order across all transactions
START TRANSACTION;
    -- Order: departments, employees, projects
    UPDATE departments SET employee_count = employee_count + 1 WHERE id = 1;
    UPDATE employees SET department_id = 1 WHERE id = 1;
    UPDATE projects SET team_size = team_size + 1 WHERE id = 1;
COMMIT;
```
2. What is the role of locking mechanisms in SQL transactions? _(Asked in TCS Digital)_

**🧩 Foundation:** Locking mechanisms ensure data consistency by preventing concurrent transactions from accessing the same data simultaneously.

**⚙️ Function:** Locks protect data integrity and maintain ACID properties during concurrent access.

**🔁 Flow:**
```sql
-- Shared locks (S) - for reading
START TRANSACTION;
    SELECT salary FROM employees WHERE id = 1;  -- Acquires shared lock
    -- Other transactions can also read this row
COMMIT;

-- Exclusive locks (X) - for writing
START TRANSACTION;
    UPDATE employees SET salary = 55000 WHERE id = 1;  -- Acquires exclusive lock
    -- Other transactions cannot read or write this row
COMMIT;

-- Intent locks - table-level locks
START TRANSACTION;
    UPDATE employees SET salary = salary * 1.1 WHERE department_id = 1;  -- Intent exclusive lock
    -- Prevents other transactions from acquiring table-level locks
COMMIT;

-- Row-level locking
START TRANSACTION;
    SELECT salary FROM employees WHERE id = 1 FOR UPDATE;  -- Row-level exclusive lock
    UPDATE employees SET salary = 55000 WHERE id = 1;
COMMIT;

-- Page-level locking
START TRANSACTION;
    UPDATE employees SET salary = 55000 WHERE id BETWEEN 1 AND 100;  -- Page-level lock
COMMIT;

-- Table-level locking
START TRANSACTION;
    ALTER TABLE employees ADD COLUMN bonus DECIMAL(10,2);  -- Table-level lock
COMMIT;

-- Lock escalation
START TRANSACTION;
    -- Multiple row updates may escalate to page or table lock
    UPDATE employees SET salary = salary * 1.1 WHERE department_id = 1;
COMMIT;

-- Lock timeout
SET LOCK_TIMEOUT = 10000;  -- 10 seconds
START TRANSACTION;
    UPDATE employees SET salary = 55000 WHERE id = 1;  -- Will timeout if lock not acquired
COMMIT;

-- Lock compatibility matrix
-- Shared (S) + Shared (S) = Compatible
START TRANSACTION;
    SELECT salary FROM employees WHERE id = 1;  -- S lock
    -- Another transaction can also SELECT (S lock)
COMMIT;

-- Shared (S) + Exclusive (X) = Incompatible
START TRANSACTION;
    SELECT salary FROM employees WHERE id = 1;  -- S lock
    -- Another transaction cannot UPDATE (X lock) until this commits
COMMIT;

-- Exclusive (X) + Exclusive (X) = Incompatible
START TRANSACTION;
    UPDATE employees SET salary = 55000 WHERE id = 1;  -- X lock
    -- Another transaction cannot UPDATE (X lock) until this commits
COMMIT;

-- Lock monitoring
SELECT 
    request_session_id,
    resource_type,
    resource_description,
    request_mode,
    request_status,
    request_type
FROM sys.dm_tran_locks
WHERE resource_database_id = DB_ID();

-- Lock wait analysis
SELECT 
    blocking_session_id,
    wait_duration_ms,
    wait_type,
    resource_description
FROM sys.dm_os_wait_stats
WHERE wait_type LIKE '%lock%';

-- Preventing lock contention
START TRANSACTION;
    -- Use specific WHERE clauses to minimize lock scope
    UPDATE employees SET salary = 55000 WHERE id = 1;  -- Row-level lock
    -- Instead of: UPDATE employees SET salary = 55000;  -- Table-level lock
COMMIT;

-- Using optimistic locking
START TRANSACTION;
    -- Read data without locks
    SELECT salary, version FROM employees WHERE id = 1;
    
    -- Update only if version hasn't changed
    UPDATE employees 
    SET salary = 55000, version = version + 1 
    WHERE id = 1 AND version = @original_version;
    
    IF @@ROWCOUNT = 0 THEN
        ROLLBACK;  -- Someone else modified the data
    ELSE
        COMMIT;
    END IF;
```

### Advanced Query Questions

1. Write a query to set the transaction isolation level to SERIALIZABLE before updating a table. _(Asked in Cognizant)_

**🧩 Foundation:** Use SET TRANSACTION ISOLATION LEVEL to specify the highest isolation level that prevents all concurrency anomalies.

**⚙️ Function:** Ensure complete data consistency by preventing dirty reads, non-repeatable reads, and phantom reads.

**🔁 Flow:**
```sql
-- Setting isolation level to SERIALIZABLE
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
START TRANSACTION;
    UPDATE employees SET salary = 55000 WHERE id = 1;
COMMIT;

-- SERIALIZABLE with multiple operations
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
START TRANSACTION;
    SELECT COUNT(*) FROM employees WHERE department_id = 1;  -- Consistent snapshot
    UPDATE employees SET salary = salary * 1.1 WHERE department_id = 1;
    UPDATE departments SET total_salary = total_salary * 1.1 WHERE id = 1;
COMMIT;

-- SERIALIZABLE with error handling
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
START TRANSACTION;
    BEGIN TRY
        UPDATE employees SET salary = 55000 WHERE id = 1;
        UPDATE departments SET employee_count = employee_count + 1 WHERE id = 1;
        COMMIT;
    END TRY
    BEGIN CATCH
        ROLLBACK;
        -- Handle error
    END CATCH

-- SERIALIZABLE with validation
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
START TRANSACTION;
    DECLARE @current_count INT;
    SELECT @current_count = COUNT(*) FROM employees WHERE department_id = 1;
    
    IF @current_count < 10 THEN
        INSERT INTO employees (name, email, department_id) VALUES ('John Doe', 'john@example.com', 1);
        UPDATE departments SET employee_count = employee_count + 1 WHERE id = 1;
        COMMIT;
    ELSE
        ROLLBACK;
    END IF;

-- SERIALIZABLE with savepoints
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
START TRANSACTION;
    UPDATE employees SET salary = 55000 WHERE id = 1;
    SAVEPOINT salary_update;
    
    UPDATE employees SET department_id = 2 WHERE id = 1;
    
    -- Can rollback to savepoint while maintaining SERIALIZABLE isolation
    ROLLBACK TO SAVEPOINT salary_update;
    
    UPDATE employees SET department_id = 3 WHERE id = 1;
COMMIT;

-- SERIALIZABLE with multiple tables
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
START TRANSACTION;
    UPDATE employees SET salary = 55000 WHERE id = 1;
    UPDATE salary_history SET end_date = CURRENT_DATE WHERE employee_id = 1 AND end_date IS NULL;
    INSERT INTO salary_history (employee_id, salary, start_date) VALUES (1, 55000, CURRENT_DATE);
    UPDATE departments SET total_salary = total_salary + 5000 WHERE id = 1;
COMMIT;

-- SERIALIZABLE with conditional logic
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
START TRANSACTION;
    IF EXISTS (SELECT 1 FROM employees WHERE id = 1 AND active = 1) THEN
        UPDATE employees SET salary = 55000 WHERE id = 1;
        UPDATE employees SET last_updated = CURRENT_TIMESTAMP WHERE id = 1;
        COMMIT;
    ELSE
        ROLLBACK;
    END IF;

-- SERIALIZABLE with audit trail
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
START TRANSACTION;
    DECLARE @old_salary DECIMAL(10,2);
    SELECT @old_salary = salary FROM employees WHERE id = 1;
    
    INSERT INTO audit_log (table_name, action, record_id, old_value, new_value, user_id)
    VALUES ('employees', 'UPDATE', 1, @old_salary, 55000, USER_ID());
    
    UPDATE employees SET salary = 55000 WHERE id = 1;
COMMIT;

-- SERIALIZABLE with timeout
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
SET LOCK_TIMEOUT = 30000;  -- 30 seconds timeout
START TRANSACTION;
    UPDATE employees SET salary = 55000 WHERE id = 1;
    -- Will timeout if lock cannot be acquired within 30 seconds
COMMIT;
```

### Tough Questions

1. How would you implement optimistic locking in a high-concurrency application? _(Asked in Deloitte)_

**🧩 Foundation:** Optimistic locking assumes conflicts are rare and uses version numbers or timestamps to detect concurrent modifications.

**⚙️ Function:** Improve concurrency by avoiding locks and detecting conflicts at commit time.

**🔁 Flow:**
```sql
-- Optimistic locking with version column
-- Table structure
CREATE TABLE employees (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    salary DECIMAL(10,2),
    version INT DEFAULT 1
);

-- Read data without locks
DECLARE @employee_id INT = 1;
DECLARE @current_version INT;
DECLARE @current_salary DECIMAL(10,2);

SELECT @current_salary = salary, @current_version = version 
FROM employees WHERE id = @employee_id;

-- Update with version check
UPDATE employees 
SET salary = 55000, version = version + 1 
WHERE id = @employee_id AND version = @current_version;

IF @@ROWCOUNT = 0 THEN
    -- Conflict detected - someone else modified the data
    RAISERROR('Concurrent modification detected', 16, 1);
ELSE
    -- Update successful
    PRINT 'Update completed successfully';
END IF;

-- Optimistic locking with timestamp
CREATE TABLE employees (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    salary DECIMAL(10,2),
    last_modified TIMESTAMP
);

-- Update with timestamp check
DECLARE @employee_id INT = 1;
DECLARE @current_timestamp TIMESTAMP;

SELECT @current_timestamp = last_modified 
FROM employees WHERE id = @employee_id;

UPDATE employees 
SET salary = 55000 
WHERE id = @employee_id AND last_modified = @current_timestamp;

-- Optimistic locking with hash
CREATE TABLE employees (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    salary DECIMAL(10,2),
    data_hash VARCHAR(64)
);

-- Calculate hash of current data
DECLARE @employee_id INT = 1;
DECLARE @current_hash VARCHAR(64);

SELECT @current_hash = data_hash 
FROM employees WHERE id = @employee_id;

-- Update with hash verification
UPDATE employees 
SET salary = 55000, 
    data_hash = HASHBYTES('SHA2_256', CONCAT(name, salary, '55000'))
WHERE id = @employee_id AND data_hash = @current_hash;

-- Optimistic locking with retry mechanism
DECLARE @retry_count INT = 0;
DECLARE @max_retries INT = 3;
DECLARE @success BIT = 0;

WHILE @retry_count < @max_retries AND @success = 0
BEGIN
    BEGIN TRY
        DECLARE @current_version INT;
        SELECT @current_version = version FROM employees WHERE id = 1;
        
        UPDATE employees 
        SET salary = 55000, version = version + 1 
        WHERE id = 1 AND version = @current_version;
        
        IF @@ROWCOUNT > 0 THEN
            SET @success = 1;
        ELSE
            SET @retry_count = @retry_count + 1;
            WAITFOR DELAY '00:00:00.1';  -- 100ms delay
        END IF
    END TRY
    BEGIN CATCH
        SET @retry_count = @retry_count + 1;
        WAITFOR DELAY '00:00:00.1';
    END CATCH
END

-- Optimistic locking with application-level versioning
-- Java/C# application code equivalent
/*
String sql = "SELECT salary, version FROM employees WHERE id = ?";
// Read current data
// User modifies salary in UI
String updateSql = "UPDATE employees SET salary = ?, version = version + 1 " +
                   "WHERE id = ? AND version = ?";
*/

-- Optimistic locking for multiple columns
UPDATE employees 
SET salary = 55000, 
    department_id = 2, 
    version = version + 1 
WHERE id = 1 
  AND version = @current_version
  AND salary = @original_salary
  AND department_id = @original_dept_id;

-- Optimistic locking with audit trail
DECLARE @old_salary DECIMAL(10,2);
DECLARE @old_version INT;

SELECT @old_salary = salary, @old_version = version 
FROM employees WHERE id = 1;

UPDATE employees 
SET salary = 55000, version = version + 1 
WHERE id = 1 AND version = @old_version;

IF @@ROWCOUNT > 0 THEN
    -- Log successful update
    INSERT INTO audit_log (table_name, action, record_id, old_value, new_value)
    VALUES ('employees', 'UPDATE', 1, @old_salary, 55000);
ELSE
    -- Log conflict
    INSERT INTO conflict_log (table_name, record_id, conflict_type, timestamp)
    VALUES ('employees', 1, 'OPTIMISTIC_LOCK', CURRENT_TIMESTAMP);
END IF;

-- Optimistic locking with business rules
DECLARE @current_salary DECIMAL(10,2);
DECLARE @current_version INT;
DECLARE @new_salary DECIMAL(10,2) = 55000;

SELECT @current_salary = salary, @current_version = version 
FROM employees WHERE id = 1;

-- Business rule: salary increase cannot exceed 20%
IF @new_salary <= @current_salary * 1.2 THEN
    UPDATE employees 
    SET salary = @new_salary, version = version + 1 
    WHERE id = 1 AND version = @current_version;
    
    IF @@ROWCOUNT = 0 THEN
        RAISERROR('Concurrent modification or business rule violation', 16, 1);
    END IF
ELSE
    RAISERROR('Salary increase exceeds 20% limit', 16, 1);
END IF;
```

### Tough Query Questions

1. Write a query to implement optimistic locking by checking a version column during an update. _(Asked in Deloitte)_

**🧩 Foundation:** Use a version column to detect concurrent modifications and prevent data corruption.

**⚙️ Function:** Implement optimistic locking by comparing version numbers before updating.

**🔁 Flow:**
```sql
-- Table with version column
CREATE TABLE employees (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    salary DECIMAL(10,2),
    version INT DEFAULT 1
);

-- Basic optimistic locking update
DECLARE @employee_id INT = 1;
DECLARE @current_version INT = 1;
DECLARE @new_salary DECIMAL(10,2) = 55000;

UPDATE employees 
SET salary = @new_salary, version = version + 1 
WHERE id = @employee_id AND version = @current_version;

IF @@ROWCOUNT = 0 THEN
    RAISERROR('Concurrent modification detected', 16, 1);
END IF;

-- Optimistic locking with read-then-update pattern
DECLARE @employee_id INT = 1;
DECLARE @current_version INT;
DECLARE @current_salary DECIMAL(10,2);
DECLARE @new_salary DECIMAL(10,2) = 55000;

-- Read current data
SELECT @current_salary = salary, @current_version = version 
FROM employees WHERE id = @employee_id;

-- Update with version check
UPDATE employees 
SET salary = @new_salary, version = version + 1 
WHERE id = @employee_id AND version = @current_version;

-- Check if update was successful
IF @@ROWCOUNT = 0 THEN
    -- Someone else modified the data
    SELECT 'Concurrent modification detected. Current version: ' + CAST(version AS VARCHAR) + 
           ', Expected version: ' + CAST(@current_version AS VARCHAR)
    FROM employees WHERE id = @employee_id;
ELSE
    SELECT 'Update successful. New version: ' + CAST(version AS VARCHAR)
    FROM employees WHERE id = @employee_id;
END IF;

-- Optimistic locking with multiple column updates
DECLARE @employee_id INT = 1;
DECLARE @current_version INT;
DECLARE @new_salary DECIMAL(10,2) = 55000;
DECLARE @new_department_id INT = 2;

-- Read current version
SELECT @current_version = version FROM employees WHERE id = @employee_id;

-- Update multiple columns with version check
UPDATE employees 
SET salary = @new_salary, 
    department_id = @new_department_id, 
    version = version + 1,
    last_updated = CURRENT_TIMESTAMP
WHERE id = @employee_id AND version = @current_version;

-- Optimistic locking with retry logic
DECLARE @employee_id INT = 1;
DECLARE @new_salary DECIMAL(10,2) = 55000;
DECLARE @retry_count INT = 0;
DECLARE @max_retries INT = 3;
DECLARE @success BIT = 0;

WHILE @retry_count < @max_retries AND @success = 0
BEGIN
    DECLARE @current_version INT;
    
    -- Get current version
    SELECT @current_version = version FROM employees WHERE id = @employee_id;
    
    -- Attempt update
    UPDATE employees 
    SET salary = @new_salary, version = version + 1 
    WHERE id = @employee_id AND version = @current_version;
    
    IF @@ROWCOUNT > 0 THEN
        SET @success = 1;
        PRINT 'Update successful after ' + CAST(@retry_count AS VARCHAR) + ' retries';
    ELSE
        SET @retry_count = @retry_count + 1;
        WAITFOR DELAY '00:00:00.1';  -- 100ms delay
    END IF
END

IF @success = 0 THEN
    RAISERROR('Failed to update after %d retries', 16, 1, @max_retries);
END IF;

-- Optimistic locking with audit trail
DECLARE @employee_id INT = 1;
DECLARE @new_salary DECIMAL(10,2) = 55000;
DECLARE @current_version INT;
DECLARE @old_salary DECIMAL(10,2);

-- Read current data
SELECT @current_version = version, @old_salary = salary 
FROM employees WHERE id = @employee_id;

-- Update with version check
UPDATE employees 
SET salary = @new_salary, version = version + 1 
WHERE id = @employee_id AND version = @current_version;

IF @@ROWCOUNT > 0 THEN
    -- Log successful update
    INSERT INTO audit_log (table_name, action, record_id, old_value, new_value, user_id)
    VALUES ('employees', 'UPDATE', @employee_id, @old_salary, @new_salary, USER_ID());
    
    PRINT 'Update successful and logged';
ELSE
    -- Log conflict
    INSERT INTO conflict_log (table_name, record_id, conflict_type, timestamp)
    VALUES ('employees', @employee_id, 'VERSION_MISMATCH', CURRENT_TIMESTAMP);
    
    RAISERROR('Version conflict detected', 16, 1);
END IF;

-- Optimistic locking with business validation
DECLARE @employee_id INT = 1;
DECLARE @new_salary DECIMAL(10,2) = 55000;
DECLARE @current_version INT;
DECLARE @current_salary DECIMAL(10,2);

-- Read current data
SELECT @current_version = version, @current_salary = salary 
FROM employees WHERE id = @employee_id;

-- Business rule: salary increase cannot exceed 20%
IF @new_salary <= @current_salary * 1.2 THEN
    UPDATE employees 
    SET salary = @new_salary, version = version + 1 
    WHERE id = @employee_id AND version = @current_version;
    
    IF @@ROWCOUNT = 0 THEN
        RAISERROR('Concurrent modification detected', 16, 1);
    ELSE
        PRINT 'Salary updated successfully';
    END IF
ELSE
    RAISERROR('Salary increase exceeds 20%% limit', 16, 1);
END IF;

-- Optimistic locking with stored procedure
CREATE PROCEDURE UpdateEmployeeSalary
    @employee_id INT,
    @new_salary DECIMAL(10,2),
    @expected_version INT
AS
BEGIN
    SET NOCOUNT ON;
    
    UPDATE employees 
    SET salary = @new_salary, version = version + 1 
    WHERE id = @employee_id AND version = @expected_version;
    
    IF @@ROWCOUNT = 0 THEN
        RAISERROR('Concurrent modification detected', 16, 1);
    END IF
END;

-- Execute the stored procedure
EXEC UpdateEmployeeSalary @employee_id = 1, @new_salary = 55000, @expected_version = 1;
```

### Situational / Real-world Questions

1. Your application experiences transaction failures due to concurrent updates. How would you diagnose and resolve this? _(Asked in HCL)_

**🧩 Foundation:** Diagnose concurrent update issues by analyzing deadlocks, lock timeouts, and transaction conflicts.

**⚙️ Function:** Identify root causes and implement appropriate solutions to prevent transaction failures.

**🔁 Flow:**
```sql
-- Step 1: Monitor deadlock events
SELECT 
    event_time,
    deadlock_graph,
    database_name,
    object_name
FROM system_health_xe_file_target
WHERE event_name = 'xml_deadlock_report'
ORDER BY event_time DESC;

-- Step 2: Check for blocking sessions
SELECT 
    blocking.session_id as blocking_session,
    blocked.session_id as blocked_session,
    blocking.wait_type,
    blocking.wait_time,
    blocking.wait_resource
FROM sys.dm_exec_requests blocked
JOIN sys.dm_exec_requests blocking ON blocked.blocking_session_id = blocking.session_id
WHERE blocked.blocking_session_id > 0;

-- Step 3: Analyze lock waits
SELECT 
    request_session_id,
    resource_type,
    resource_description,
    request_mode,
    request_status,
    wait_duration_ms
FROM sys.dm_tran_locks
WHERE request_status = 'WAIT'
ORDER BY wait_duration_ms DESC;

-- Step 4: Check transaction isolation levels
SELECT 
    session_id,
    transaction_isolation_level,
    lock_timeout,
    deadlock_priority
FROM sys.dm_exec_sessions
WHERE is_user_process = 1;

-- Step 5: Monitor long-running transactions
SELECT 
    session_id,
    transaction_id,
    transaction_begin_time,
    DATEDIFF(second, transaction_begin_time, GETDATE()) as duration_seconds
FROM sys.dm_tran_active_transactions
WHERE transaction_begin_time < DATEADD(minute, -5, GETDATE());

-- Step 6: Implement deadlock prevention
-- Use consistent ordering
START TRANSACTION;
    -- Always update in ascending order
    UPDATE employees SET salary = 55000 WHERE id = 1;
    UPDATE employees SET salary = 52000 WHERE id = 2;
COMMIT;

-- Step 7: Add timeout handling
SET LOCK_TIMEOUT = 10000;  -- 10 seconds
START TRANSACTION;
    UPDATE employees SET salary = 55000 WHERE id = 1;
    -- Will timeout if lock not acquired within 10 seconds
COMMIT;

-- Step 8: Implement retry logic
DECLARE @retry_count INT = 0;
DECLARE @max_retries INT = 3;

WHILE @retry_count < @max_retries
BEGIN
    BEGIN TRY
        START TRANSACTION;
            UPDATE employees SET salary = 55000 WHERE id = 1;
        COMMIT;
        BREAK;  -- Success
    END TRY
    BEGIN CATCH
        IF ERROR_NUMBER() = 1205 THEN  -- Deadlock
            ROLLBACK;
            SET @retry_count = @retry_count + 1;
            WAITFOR DELAY '00:00:01';
        ELSE
            THROW;  -- Re-throw non-deadlock errors
        END IF
    END CATCH
END

-- Step 9: Use optimistic locking
DECLARE @employee_id INT = 1;
DECLARE @current_version INT;
DECLARE @new_salary DECIMAL(10,2) = 55000;

SELECT @current_version = version FROM employees WHERE id = @employee_id;

UPDATE employees 
SET salary = @new_salary, version = version + 1 
WHERE id = @employee_id AND version = @current_version;

IF @@ROWCOUNT = 0 THEN
    RAISERROR('Concurrent modification detected', 16, 1);
END IF;

-- Step 10: Implement application-level solutions
-- Use shorter transactions
START TRANSACTION;
    -- Only essential operations
    UPDATE employees SET salary = 55000 WHERE id = 1;
COMMIT;

-- Separate read and write operations
SELECT salary FROM employees WHERE id = 1;  -- Read without transaction
-- Process in application
START TRANSACTION;
    UPDATE employees SET salary = 55000 WHERE id = 1;  -- Write with transaction
COMMIT;

-- Step 11: Monitor and alert
-- Create monitoring query
SELECT 
    COUNT(*) as deadlock_count,
    MAX(event_time) as last_deadlock
FROM system_health_xe_file_target
WHERE event_name = 'xml_deadlock_report'
AND event_time > DATEADD(hour, -1, GETDATE());

-- Step 12: Implement circuit breaker pattern
DECLARE @failure_count INT = 0;
DECLARE @circuit_open BIT = 0;

-- Check circuit breaker state
IF @circuit_open = 1 AND @failure_count > 10 THEN
    RAISERROR('Circuit breaker open - too many failures', 16, 1);
END IF

-- Attempt operation
BEGIN TRY
    START TRANSACTION;
        UPDATE employees SET salary = 55000 WHERE id = 1;
    COMMIT;
    SET @failure_count = 0;  -- Reset on success
END TRY
BEGIN CATCH
    SET @failure_count = @failure_count + 1;
    IF @failure_count > 10 THEN
        SET @circuit_open = 1;
    END IF
    THROW;
END CATCH
```
2. Write a query to transfer funds between two accounts within a transaction, ensuring data consistency. _(Asked in HCL)_

**🧩 Foundation:** Use a transaction to ensure both debit and credit operations succeed or fail together, maintaining account balance consistency.

**⚙️ Function:** Implement atomic fund transfer with proper validation and error handling.

**🔁 Flow:**
```sql
-- Basic fund transfer transaction
DECLARE @from_account_id INT = 1;
DECLARE @to_account_id INT = 2;
DECLARE @transfer_amount DECIMAL(10,2) = 1000;

START TRANSACTION;
    -- Debit from source account
    UPDATE accounts SET balance = balance - @transfer_amount WHERE id = @from_account_id;
    
    -- Credit to destination account
    UPDATE accounts SET balance = balance + @transfer_amount WHERE id = @to_account_id;
    
    -- Insert transaction record
    INSERT INTO transactions (from_account_id, to_account_id, amount, transaction_date)
    VALUES (@from_account_id, @to_account_id, @transfer_amount, CURRENT_TIMESTAMP);
COMMIT;

-- Fund transfer with validation
DECLARE @from_account_id INT = 1;
DECLARE @to_account_id INT = 2;
DECLARE @transfer_amount DECIMAL(10,2) = 1000;
DECLARE @from_balance DECIMAL(10,2);

START TRANSACTION;
    -- Check source account balance
    SELECT @from_balance = balance FROM accounts WHERE id = @from_account_id;
    
    IF @from_balance >= @transfer_amount THEN
        -- Validate destination account exists
        IF EXISTS (SELECT 1 FROM accounts WHERE id = @to_account_id) THEN
            -- Debit from source account
            UPDATE accounts SET balance = balance - @transfer_amount WHERE id = @from_account_id;
            
            -- Credit to destination account
            UPDATE accounts SET balance = balance + @transfer_amount WHERE id = @to_account_id;
            
            -- Insert transaction record
            INSERT INTO transactions (from_account_id, to_account_id, amount, transaction_date, status)
            VALUES (@from_account_id, @to_account_id, @transfer_amount, CURRENT_TIMESTAMP, 'COMPLETED');
            
            COMMIT;
        ELSE
            ROLLBACK;
            RAISERROR('Destination account does not exist', 16, 1);
        END IF
    ELSE
        ROLLBACK;
        RAISERROR('Insufficient funds', 16, 1);
    END IF;

-- Fund transfer with audit trail
DECLARE @from_account_id INT = 1;
DECLARE @to_account_id INT = 2;
DECLARE @transfer_amount DECIMAL(10,2) = 1000;
DECLARE @from_old_balance DECIMAL(10,2);
DECLARE @to_old_balance DECIMAL(10,2);

START TRANSACTION;
    -- Get current balances
    SELECT @from_old_balance = balance FROM accounts WHERE id = @from_account_id;
    SELECT @to_old_balance = balance FROM accounts WHERE id = @to_account_id;
    
    -- Validate transfer
    IF @from_old_balance >= @transfer_amount THEN
        -- Debit from source account
        UPDATE accounts SET balance = balance - @transfer_amount WHERE id = @from_account_id;
        
        -- Credit to destination account
        UPDATE accounts SET balance = balance + @transfer_amount WHERE id = @to_account_id;
        
        -- Insert transaction record
        INSERT INTO transactions (from_account_id, to_account_id, amount, transaction_date)
        VALUES (@from_account_id, @to_account_id, @transfer_amount, CURRENT_TIMESTAMP);
        
        -- Insert audit records
        INSERT INTO audit_log (table_name, action, record_id, old_value, new_value)
        VALUES ('accounts', 'UPDATE', @from_account_id, @from_old_balance, @from_old_balance - @transfer_amount);
        
        INSERT INTO audit_log (table_name, action, record_id, old_value, new_value)
        VALUES ('accounts', 'UPDATE', @to_account_id, @to_old_balance, @to_old_balance + @transfer_amount);
        
        COMMIT;
    ELSE
        ROLLBACK;
        RAISERROR('Insufficient funds', 16, 1);
    END IF;

-- Fund transfer with error handling
DECLARE @from_account_id INT = 1;
DECLARE @to_account_id INT = 2;
DECLARE @transfer_amount DECIMAL(10,2) = 1000;

BEGIN TRY
    START TRANSACTION;
        -- Debit from source account
        UPDATE accounts SET balance = balance - @transfer_amount WHERE id = @from_account_id;
        
        IF @@ROWCOUNT = 0 THEN
            RAISERROR('Source account not found', 16, 1);
        END IF
        
        -- Credit to destination account
        UPDATE accounts SET balance = balance + @transfer_amount WHERE id = @to_account_id;
        
        IF @@ROWCOUNT = 0 THEN
            RAISERROR('Destination account not found', 16, 1);
        END IF
        
        -- Insert transaction record
        INSERT INTO transactions (from_account_id, to_account_id, amount, transaction_date, status)
        VALUES (@from_account_id, @to_account_id, @transfer_amount, CURRENT_TIMESTAMP, 'COMPLETED');
        
        COMMIT;
        PRINT 'Transfer completed successfully';
    END TRY
    BEGIN CATCH
        ROLLBACK;
        INSERT INTO error_log (error_message, from_account, to_account, amount)
        VALUES (ERROR_MESSAGE(), @from_account_id, @to_account_id, @transfer_amount);
        THROW;
    END CATCH

-- Fund transfer with business rules
DECLARE @from_account_id INT = 1;
DECLARE @to_account_id INT = 2;
DECLARE @transfer_amount DECIMAL(10,2) = 1000;
DECLARE @daily_limit DECIMAL(10,2) = 5000;

START TRANSACTION;
    -- Check daily transfer limit
    DECLARE @daily_total DECIMAL(10,2);
    SELECT @daily_total = COALESCE(SUM(amount), 0)
    FROM transactions 
    WHERE from_account_id = @from_account_id 
    AND transaction_date >= CAST(GETDATE() AS DATE);
    
    IF @daily_total + @transfer_amount <= @daily_limit THEN
        -- Check account balance
        IF (SELECT balance FROM accounts WHERE id = @from_account_id) >= @transfer_amount THEN
            -- Perform transfer
            UPDATE accounts SET balance = balance - @transfer_amount WHERE id = @from_account_id;
            UPDATE accounts SET balance = balance + @transfer_amount WHERE id = @to_account_id;
            
            INSERT INTO transactions (from_account_id, to_account_id, amount, transaction_date)
            VALUES (@from_account_id, @to_account_id, @transfer_amount, CURRENT_TIMESTAMP);
            
            COMMIT;
        ELSE
            ROLLBACK;
            RAISERROR('Insufficient funds', 16, 1);
        END IF
    ELSE
        ROLLBACK;
        RAISERROR('Daily transfer limit exceeded', 16, 1);
    END IF;

-- Fund transfer with savepoints
DECLARE @from_account_id INT = 1;
DECLARE @to_account_id INT = 2;
DECLARE @transfer_amount DECIMAL(10,2) = 1000;

START TRANSACTION;
    -- Debit from source account
    UPDATE accounts SET balance = balance - @transfer_amount WHERE id = @from_account_id;
    SAVEPOINT debit_complete;
    
    -- Credit to destination account
    UPDATE accounts SET balance = balance + @transfer_amount WHERE id = @to_account_id;
    SAVEPOINT credit_complete;
    
    -- Insert transaction record
    INSERT INTO transactions (from_account_id, to_account_id, amount, transaction_date)
    VALUES (@from_account_id, @to_account_id, @transfer_amount, CURRENT_TIMESTAMP);
    
    COMMIT;
```

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

**🧩 Foundation:** Slow queries are typically caused by inefficient execution plans, missing indexes, poor table design, or suboptimal query structure.

**⚙️ Function:** Identify and resolve performance bottlenecks to improve query execution time and system responsiveness.

**🔁 Flow:**
```sql
-- 1. Missing Indexes - Most Common Cause
-- Slow query without index
SELECT * FROM employees WHERE department_id = 1;  -- Full table scan

-- Create index to improve performance
CREATE INDEX idx_employees_department_id ON employees(department_id);

-- Fast query with index
SELECT * FROM employees WHERE department_id = 1;  -- Index seek

-- 2. Inefficient WHERE Clauses
-- Slow: Using functions on indexed columns
SELECT * FROM employees WHERE YEAR(hire_date) = 2023;  -- Can't use index

-- Fast: Direct comparison
SELECT * FROM employees WHERE hire_date >= '2023-01-01' AND hire_date < '2024-01-01';

-- 3. SELECT * Instead of Specific Columns
-- Slow: Retrieving unnecessary data
SELECT * FROM employees WHERE department_id = 1;

-- Fast: Select only needed columns
SELECT id, name, email FROM employees WHERE department_id = 1;

-- 4. Poor JOIN Conditions
-- Slow: Cartesian product
SELECT e.name, d.name 
FROM employees e, departments d 
WHERE e.department_id = d.id;

-- Fast: Explicit JOIN
SELECT e.name, d.name 
FROM employees e 
INNER JOIN departments d ON e.department_id = d.id;

-- 5. Subqueries in WHERE Clause
-- Slow: Correlated subquery
SELECT * FROM employees e 
WHERE salary > (SELECT AVG(salary) FROM employees WHERE department_id = e.department_id);

-- Fast: JOIN with aggregation
SELECT e.* 
FROM employees e 
INNER JOIN (
    SELECT department_id, AVG(salary) as avg_salary 
    FROM employees 
    GROUP BY department_id
) dept_avg ON e.department_id = dept_avg.department_id 
WHERE e.salary > dept_avg.avg_salary;

-- 6. Lack of Proper Filtering
-- Slow: Processing all rows
SELECT COUNT(*) FROM employees;

-- Fast: Filter early
SELECT COUNT(*) FROM employees WHERE active = 1;

-- 7. Inefficient ORDER BY
-- Slow: Sorting large result sets
SELECT * FROM employees ORDER BY name;

-- Fast: Use indexed columns for sorting
SELECT * FROM employees ORDER BY department_id, name;

-- 8. N+1 Query Problem
-- Slow: Multiple queries in loop
-- Application code: for each department, query employees
SELECT * FROM departments;
-- Then for each department: SELECT * FROM employees WHERE department_id = ?

-- Fast: Single query with JOIN
SELECT d.name as dept_name, e.name as emp_name 
FROM departments d 
LEFT JOIN employees e ON d.id = e.department_id;

-- 9. Large Result Sets
-- Slow: Returning too many rows
SELECT * FROM employees;

-- Fast: Use LIMIT/Pagination
SELECT * FROM employees LIMIT 100 OFFSET 0;

-- 10. Missing Statistics
-- Slow: Outdated statistics cause poor execution plans
-- Check statistics
SELECT * FROM sys.stats WHERE object_id = OBJECT_ID('employees');

-- Update statistics
UPDATE STATISTICS employees;

-- 11. Table Scans on Large Tables
-- Slow: No WHERE clause
SELECT * FROM employees;

-- Fast: Add WHERE clause or use indexed columns
SELECT * FROM employees WHERE department_id IN (1, 2, 3);

-- 12. Inefficient Aggregations
-- Slow: Aggregating without proper grouping
SELECT department_id, AVG(salary) 
FROM employees 
GROUP BY department_id;

-- Fast: Use indexed columns for grouping
CREATE INDEX idx_employees_dept_salary ON employees(department_id, salary);

-- 13. String Operations
-- Slow: LIKE with wildcard at start
SELECT * FROM employees WHERE name LIKE '%John%';

-- Fast: Use prefix matching
SELECT * FROM employees WHERE name LIKE 'John%';

-- 14. Date Range Queries
-- Slow: Using functions on date columns
SELECT * FROM orders WHERE DATE(order_date) = '2023-01-01';

-- Fast: Direct date comparison
SELECT * FROM orders WHERE order_date >= '2023-01-01' AND order_date < '2023-01-02';

-- 15. Complex OR Conditions
-- Slow: OR conditions that can't use indexes
SELECT * FROM employees WHERE department_id = 1 OR salary > 50000;

-- Fast: Use UNION
SELECT * FROM employees WHERE department_id = 1
UNION
SELECT * FROM employees WHERE salary > 50000 AND department_id != 1;

-- 16. Missing Composite Indexes
-- Slow: Multiple single-column indexes
CREATE INDEX idx_dept ON employees(department_id);
CREATE INDEX idx_salary ON employees(salary);

-- Fast: Composite index for common query patterns
CREATE INDEX idx_dept_salary ON employees(department_id, salary);

-- 17. Inefficient DISTINCT
-- Slow: DISTINCT on large result sets
SELECT DISTINCT department_id, name FROM employees;

-- Fast: Use GROUP BY or filter early
SELECT department_id, name FROM employees GROUP BY department_id, name;

-- 18. Poor Table Design
-- Slow: Denormalized data
SELECT e.name, d.name, p.name 
FROM employees e, departments d, projects p 
WHERE e.dept_id = d.id AND e.proj_id = p.id;

-- Fast: Proper normalization with indexes
SELECT e.name, d.name, p.name 
FROM employees e 
INNER JOIN departments d ON e.department_id = d.id 
INNER JOIN employee_projects ep ON e.id = ep.employee_id 
INNER JOIN projects p ON ep.project_id = p.id;
```
2. How do you use the EXPLAIN plan to analyze query performance? _(Asked in Capgemini)_

**🧩 Foundation:** EXPLAIN shows the query execution plan, revealing how the database engine will process the query and identify performance bottlenecks.

**⚙️ Function:** Analyze execution plans to optimize queries, identify missing indexes, and understand query performance characteristics.

**🔁 Flow:**
```sql
-- Basic EXPLAIN usage
EXPLAIN SELECT * FROM employees WHERE department_id = 1;

-- EXPLAIN with detailed information
EXPLAIN (ANALYZE, BUFFERS, FORMAT TEXT) 
SELECT e.name, d.name 
FROM employees e 
INNER JOIN departments d ON e.department_id = d.id 
WHERE e.salary > 50000;

-- EXPLAIN with JSON format for detailed analysis
EXPLAIN (ANALYZE, BUFFERS, FORMAT JSON) 
SELECT * FROM employees WHERE department_id = 1;

-- Analyzing table scan vs index scan
-- Table scan (slow)
EXPLAIN SELECT * FROM employees WHERE name LIKE '%John%';

-- Index scan (fast)
EXPLAIN SELECT * FROM employees WHERE department_id = 1;

-- Analyzing JOIN performance
EXPLAIN 
SELECT e.name, d.name, p.name 
FROM employees e 
INNER JOIN departments d ON e.department_id = d.id 
INNER JOIN employee_projects ep ON e.id = ep.employee_id 
INNER JOIN projects p ON ep.project_id = p.id;

-- Analyzing subquery performance
EXPLAIN 
SELECT * FROM employees e 
WHERE salary > (SELECT AVG(salary) FROM employees WHERE department_id = e.department_id);

-- Analyzing aggregation performance
EXPLAIN 
SELECT department_id, AVG(salary), COUNT(*) 
FROM employees 
GROUP BY department_id 
HAVING AVG(salary) > 50000;

-- Analyzing ORDER BY performance
EXPLAIN 
SELECT * FROM employees 
ORDER BY department_id, salary DESC;

-- Analyzing LIMIT/OFFSET performance
EXPLAIN 
SELECT * FROM employees 
ORDER BY id 
LIMIT 100 OFFSET 1000;

-- Comparing execution plans before and after optimization
-- Before optimization
EXPLAIN 
SELECT e.name, d.name 
FROM employees e, departments d 
WHERE e.department_id = d.id AND e.salary > 50000;

-- After optimization
EXPLAIN 
SELECT e.name, d.name 
FROM employees e 
INNER JOIN departments d ON e.department_id = d.id 
WHERE e.salary > 50000;

-- Analyzing index usage
EXPLAIN 
SELECT * FROM employees 
WHERE department_id = 1 AND salary > 50000;

-- Check if composite index is being used
CREATE INDEX idx_dept_salary ON employees(department_id, salary);
EXPLAIN 
SELECT * FROM employees 
WHERE department_id = 1 AND salary > 50000;

-- Analyzing window function performance
EXPLAIN 
SELECT name, salary, 
       ROW_NUMBER() OVER (PARTITION BY department_id ORDER BY salary DESC) as rank
FROM employees;

-- Analyzing CTE performance
EXPLAIN 
WITH dept_avg AS (
    SELECT department_id, AVG(salary) as avg_salary 
    FROM employees 
    GROUP BY department_id
)
SELECT e.name, e.salary, d.avg_salary 
FROM employees e 
INNER JOIN dept_avg d ON e.department_id = d.department_id 
WHERE e.salary > d.avg_salary;

-- Analyzing UNION performance
EXPLAIN 
SELECT name, salary FROM employees WHERE department_id = 1
UNION
SELECT name, salary FROM employees WHERE salary > 50000;

-- Analyzing DISTINCT performance
EXPLAIN 
SELECT DISTINCT department_id, name FROM employees;

-- Analyzing function usage in WHERE clause
EXPLAIN 
SELECT * FROM employees WHERE YEAR(hire_date) = 2023;

-- Better approach
EXPLAIN 
SELECT * FROM employees WHERE hire_date >= '2023-01-01' AND hire_date < '2024-01-01';

-- Analyzing LIKE patterns
EXPLAIN 
SELECT * FROM employees WHERE name LIKE '%John%';  -- Slow

EXPLAIN 
SELECT * FROM employees WHERE name LIKE 'John%';   -- Fast

-- Analyzing IN vs OR performance
EXPLAIN 
SELECT * FROM employees WHERE department_id IN (1, 2, 3);

EXPLAIN 
SELECT * FROM employees WHERE department_id = 1 OR department_id = 2 OR department_id = 3;

-- Analyzing EXISTS vs IN performance
EXPLAIN 
SELECT * FROM employees e 
WHERE EXISTS (SELECT 1 FROM departments d WHERE d.id = e.department_id);

EXPLAIN 
SELECT * FROM employees e 
WHERE department_id IN (SELECT id FROM departments);

-- Analyzing temporary table usage
EXPLAIN 
SELECT e.name, d.name 
FROM employees e 
INNER JOIN (
    SELECT id, name FROM departments WHERE active = 1
) d ON e.department_id = d.id;

-- Analyzing materialized view vs regular query
-- Regular query
EXPLAIN 
SELECT department_id, AVG(salary), COUNT(*) 
FROM employees 
GROUP BY department_id;

-- Materialized view (if available)
EXPLAIN 
SELECT * FROM dept_salary_summary;

-- Analyzing partition performance
EXPLAIN 
SELECT * FROM orders 
WHERE order_date >= '2023-01-01' AND order_date < '2024-01-01';

-- Analyzing full-text search performance
EXPLAIN 
SELECT * FROM employees 
WHERE MATCH(name, description) AGAINST('John' IN NATURAL LANGUAGE MODE);

-- Analyzing stored procedure execution
EXPLAIN 
CALL GetEmployeeByDepartment(1);

-- Analyzing view performance
EXPLAIN 
SELECT * FROM employee_details WHERE department_id = 1;

-- Analyzing trigger impact
EXPLAIN 
UPDATE employees SET salary = 55000 WHERE id = 1;

-- Performance monitoring with EXPLAIN
-- Log slow queries with execution plans
CREATE TABLE query_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    query_text TEXT,
    execution_time DECIMAL(10,4),
    execution_plan JSON,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert execution plan for slow queries
INSERT INTO query_log (query_text, execution_time, execution_plan)
SELECT 
    'SELECT * FROM employees WHERE department_id = 1',
    0.1250,
    JSON_EXTRACT(EXPLAIN_FORMAT(JSON, 'SELECT * FROM employees WHERE department_id = 1'), '$');
```

### Basic Query Questions

1. Write a query and explain its execution plan using EXPLAIN. _(Asked in TCS)_

**🧩 Foundation:** Use EXPLAIN to analyze how the database engine will execute a query and identify potential performance issues.

**⚙️ Function:** Understand the execution plan to optimize queries and improve performance.

**🔁 Flow:**
```sql
-- Query to analyze: Find employees with above-average salary in their department
SELECT e.name, e.salary, d.name as department_name
FROM employees e
INNER JOIN departments d ON e.department_id = d.id
WHERE e.salary > (
    SELECT AVG(salary) 
    FROM employees 
    WHERE department_id = e.department_id
)
ORDER BY e.salary DESC;

-- Step 1: Basic EXPLAIN analysis
EXPLAIN 
SELECT e.name, e.salary, d.name as department_name
FROM employees e
INNER JOIN departments d ON e.department_id = d.id
WHERE e.salary > (
    SELECT AVG(salary) 
    FROM employees 
    WHERE department_id = e.department_id
)
ORDER BY e.salary DESC;

-- Step 2: Detailed EXPLAIN with analysis
EXPLAIN (ANALYZE, BUFFERS, FORMAT TEXT) 
SELECT e.name, e.salary, d.name as department_name
FROM employees e
INNER JOIN departments d ON e.department_id = d.id
WHERE e.salary > (
    SELECT AVG(salary) 
    FROM employees 
    WHERE department_id = e.department_id
)
ORDER BY e.salary DESC;

-- Step 3: Optimized version using CTE
EXPLAIN 
WITH dept_avg AS (
    SELECT department_id, AVG(salary) as avg_salary
    FROM employees
    GROUP BY department_id
)
SELECT e.name, e.salary, d.name as department_name
FROM employees e
INNER JOIN departments d ON e.department_id = d.id
INNER JOIN dept_avg da ON e.department_id = da.department_id
WHERE e.salary > da.avg_salary
ORDER BY e.salary DESC;

-- Step 4: Compare execution plans
-- Original query plan
EXPLAIN (FORMAT JSON) 
SELECT e.name, e.salary, d.name as department_name
FROM employees e
INNER JOIN departments d ON e.department_id = d.id
WHERE e.salary > (
    SELECT AVG(salary) 
    FROM employees 
    WHERE department_id = e.department_id
)
ORDER BY e.salary DESC;

-- Optimized query plan
EXPLAIN (FORMAT JSON) 
WITH dept_avg AS (
    SELECT department_id, AVG(salary) as avg_salary
    FROM employees
    GROUP BY department_id
)
SELECT e.name, e.salary, d.name as department_name
FROM employees e
INNER JOIN departments d ON e.department_id = d.id
INNER JOIN dept_avg da ON e.department_id = da.department_id
WHERE e.salary > da.avg_salary
ORDER BY e.salary DESC;

-- Step 5: Analyze index usage
-- Check if indexes exist
SHOW INDEX FROM employees;
SHOW INDEX FROM departments;

-- Create indexes if needed
CREATE INDEX idx_employees_dept_salary ON employees(department_id, salary);
CREATE INDEX idx_departments_id ON departments(id);

-- Analyze with indexes
EXPLAIN 
SELECT e.name, e.salary, d.name as department_name
FROM employees e
INNER JOIN departments d ON e.department_id = d.id
WHERE e.salary > (
    SELECT AVG(salary) 
    FROM employees 
    WHERE department_id = e.department_id
)
ORDER BY e.salary DESC;

-- Step 6: Analyze different query patterns
-- Simple WHERE clause
EXPLAIN 
SELECT * FROM employees WHERE department_id = 1;

-- JOIN operation
EXPLAIN 
SELECT e.name, d.name 
FROM employees e 
INNER JOIN departments d ON e.department_id = d.id;

-- Subquery in WHERE
EXPLAIN 
SELECT * FROM employees e 
WHERE salary > (SELECT AVG(salary) FROM employees);

-- ORDER BY with index
EXPLAIN 
SELECT * FROM employees ORDER BY department_id, salary;

-- Step 7: Performance analysis with timing
-- Enable query timing
SET profiling = 1;

-- Run query
SELECT e.name, e.salary, d.name as department_name
FROM employees e
INNER JOIN departments d ON e.department_id = d.id
WHERE e.salary > (
    SELECT AVG(salary) 
    FROM employees 
    WHERE department_id = e.department_id
)
ORDER BY e.salary DESC;

-- Check execution time
SHOW PROFILES;

-- Step 8: Analyze specific execution plan components
-- Table scan vs Index scan
EXPLAIN 
SELECT * FROM employees WHERE name LIKE '%John%';  -- Likely table scan

EXPLAIN 
SELECT * FROM employees WHERE department_id = 1;   -- Likely index scan

-- JOIN types
EXPLAIN 
SELECT e.name, d.name 
FROM employees e 
INNER JOIN departments d ON e.department_id = d.id;

-- Subquery execution
EXPLAIN 
SELECT * FROM employees e 
WHERE EXISTS (SELECT 1 FROM departments d WHERE d.id = e.department_id);

-- Step 9: Identify bottlenecks
-- Look for these in EXPLAIN output:
-- - "Using filesort" (expensive sorting)
-- - "Using temporary" (temporary table creation)
-- - "Using where" (filtering after table access)
-- - "Using index" (good - using index)
-- - "Using index condition" (index pushdown)

-- Step 10: Optimization suggestions based on EXPLAIN
-- If you see "Using filesort":
CREATE INDEX idx_employees_salary_dept ON employees(salary DESC, department_id);

-- If you see "Using temporary":
-- Consider using CTEs or materialized views

-- If you see table scans:
-- Add appropriate indexes

-- Step 11: Monitor query performance over time
CREATE TABLE query_performance_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    query_hash VARCHAR(64),
    execution_time DECIMAL(10,4),
    rows_examined INT,
    rows_returned INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Log performance metrics
INSERT INTO query_performance_log (query_hash, execution_time, rows_examined, rows_returned)
VALUES (
    MD5('SELECT * FROM employees WHERE department_id = 1'),
    0.1250,
    1000,
    50
);
```

### Intermediate Questions

1. How do you optimize a query with multiple joins and aggregations? _(Asked in Wipro)_

**🧩 Foundation:** Optimize complex queries by using proper indexes, efficient JOIN strategies, and query restructuring to minimize execution time.

**⚙️ Function:** Improve performance of multi-table queries with aggregations through strategic optimization techniques.

**🔁 Flow:**
```sql
-- Original complex query
SELECT 
    d.name as department_name,
    COUNT(e.id) as employee_count,
    AVG(e.salary) as avg_salary,
    SUM(e.salary) as total_salary,
    p.name as project_name,
    COUNT(ep.employee_id) as project_employees
FROM departments d
LEFT JOIN employees e ON d.id = e.department_id
LEFT JOIN employee_projects ep ON e.id = ep.employee_id
LEFT JOIN projects p ON ep.project_id = p.id
WHERE e.active = 1
GROUP BY d.id, d.name, p.id, p.name
HAVING AVG(e.salary) > 50000
ORDER BY total_salary DESC;

-- Step 1: Create proper indexes
-- Composite indexes for JOIN conditions
CREATE INDEX idx_employees_dept_active ON employees(department_id, active);
CREATE INDEX idx_employee_projects_emp ON employee_projects(employee_id);
CREATE INDEX idx_employee_projects_proj ON employee_projects(project_id);
CREATE INDEX idx_projects_id ON projects(id);

-- Indexes for WHERE and ORDER BY
CREATE INDEX idx_employees_active_salary ON employees(active, salary);
CREATE INDEX idx_departments_id_name ON departments(id, name);

-- Step 2: Optimize JOIN order
-- Start with smallest table or most filtered table
SELECT 
    d.name as department_name,
    COUNT(e.id) as employee_count,
    AVG(e.salary) as avg_salary,
    SUM(e.salary) as total_salary,
    p.name as project_name,
    COUNT(ep.employee_id) as project_employees
FROM employees e  -- Start with filtered table
INNER JOIN departments d ON e.department_id = d.id
LEFT JOIN employee_projects ep ON e.id = ep.employee_id
LEFT JOIN projects p ON ep.project_id = p.id
WHERE e.active = 1
GROUP BY d.id, d.name, p.id, p.name
HAVING AVG(e.salary) > 50000
ORDER BY total_salary DESC;

-- Step 3: Use CTEs for better readability and performance
WITH active_employees AS (
    SELECT id, name, department_id, salary
    FROM employees 
    WHERE active = 1
),
dept_stats AS (
    SELECT 
        d.id as dept_id,
        d.name as dept_name,
        COUNT(e.id) as employee_count,
        AVG(e.salary) as avg_salary,
        SUM(e.salary) as total_salary
    FROM departments d
    INNER JOIN active_employees e ON d.id = e.department_id
    GROUP BY d.id, d.name
    HAVING AVG(e.salary) > 50000
),
project_stats AS (
    SELECT 
        p.id as proj_id,
        p.name as proj_name,
        COUNT(ep.employee_id) as project_employees
    FROM projects p
    LEFT JOIN employee_projects ep ON p.id = ep.project_id
    LEFT JOIN active_employees e ON ep.employee_id = e.id
    GROUP BY p.id, p.name
)
SELECT 
    ds.dept_name,
    ds.employee_count,
    ds.avg_salary,
    ds.total_salary,
    ps.proj_name,
    ps.project_employees
FROM dept_stats ds
CROSS JOIN project_stats ps
ORDER BY ds.total_salary DESC;

-- Step 4: Use materialized views for frequently accessed data
CREATE MATERIALIZED VIEW dept_employee_summary AS
SELECT 
    d.id as dept_id,
    d.name as dept_name,
    COUNT(e.id) as employee_count,
    AVG(e.salary) as avg_salary,
    SUM(e.salary) as total_salary
FROM departments d
LEFT JOIN employees e ON d.id = e.department_id AND e.active = 1
GROUP BY d.id, d.name;

-- Query using materialized view
SELECT 
    des.dept_name,
    des.employee_count,
    des.avg_salary,
    des.total_salary,
    p.name as project_name,
    COUNT(ep.employee_id) as project_employees
FROM dept_employee_summary des
LEFT JOIN employees e ON des.dept_id = e.department_id AND e.active = 1
LEFT JOIN employee_projects ep ON e.id = ep.employee_id
LEFT JOIN projects p ON ep.project_id = p.id
WHERE des.avg_salary > 50000
GROUP BY des.dept_id, des.dept_name, p.id, p.name
ORDER BY des.total_salary DESC;

-- Step 5: Optimize with window functions
SELECT 
    d.name as department_name,
    COUNT(e.id) OVER (PARTITION BY d.id) as employee_count,
    AVG(e.salary) OVER (PARTITION BY d.id) as avg_salary,
    SUM(e.salary) OVER (PARTITION BY d.id) as total_salary,
    p.name as project_name,
    COUNT(ep.employee_id) OVER (PARTITION BY p.id) as project_employees
FROM departments d
INNER JOIN employees e ON d.id = e.department_id AND e.active = 1
LEFT JOIN employee_projects ep ON e.id = ep.employee_id
LEFT JOIN projects p ON ep.project_id = p.id
WHERE e.salary > 50000
ORDER BY total_salary DESC;

-- Step 6: Use EXISTS instead of JOINs when appropriate
SELECT 
    d.name as department_name,
    COUNT(e.id) as employee_count,
    AVG(e.salary) as avg_salary,
    SUM(e.salary) as total_salary
FROM departments d
INNER JOIN employees e ON d.id = e.department_id
WHERE e.active = 1
    AND EXISTS (SELECT 1 FROM employee_projects ep WHERE ep.employee_id = e.id)
GROUP BY d.id, d.name
HAVING AVG(e.salary) > 50000
ORDER BY total_salary DESC;

-- Step 7: Partition large tables
-- Create partitioned table for employees
CREATE TABLE employees_partitioned (
    id INT,
    name VARCHAR(100),
    department_id INT,
    salary DECIMAL(10,2),
    active BOOLEAN,
    hire_date DATE
) PARTITION BY RANGE (YEAR(hire_date)) (
    PARTITION p2020 VALUES LESS THAN (2021),
    PARTITION p2021 VALUES LESS THAN (2022),
    PARTITION p2022 VALUES LESS THAN (2023),
    PARTITION p2023 VALUES LESS THAN (2024),
    PARTITION p_future VALUES LESS THAN MAXVALUE
);

-- Query on partitioned table
SELECT 
    d.name as department_name,
    COUNT(e.id) as employee_count,
    AVG(e.salary) as avg_salary
FROM departments d
INNER JOIN employees_partitioned e ON d.id = e.department_id
WHERE e.active = 1 AND YEAR(e.hire_date) = 2023
GROUP BY d.id, d.name;

-- Step 8: Use covering indexes
-- Covering index includes all columns needed by the query
CREATE INDEX idx_employees_covering ON employees(department_id, active, salary, id, name);

-- Query using covering index
SELECT 
    d.name as department_name,
    COUNT(e.id) as employee_count,
    AVG(e.salary) as avg_salary
FROM departments d
INNER JOIN employees e ON d.id = e.department_id
WHERE e.active = 1
GROUP BY d.id, d.name;

-- Step 9: Optimize aggregation with pre-aggregation
-- Create summary table
CREATE TABLE dept_daily_summary (
    dept_id INT,
    summary_date DATE,
    employee_count INT,
    total_salary DECIMAL(12,2),
    avg_salary DECIMAL(10,2),
    PRIMARY KEY (dept_id, summary_date)
);

-- Insert daily summaries
INSERT INTO dept_daily_summary
SELECT 
    department_id,
    CURRENT_DATE,
    COUNT(*),
    SUM(salary),
    AVG(salary)
FROM employees
WHERE active = 1
GROUP BY department_id;

-- Query using summary table
SELECT 
    d.name as department_name,
    dds.employee_count,
    dds.avg_salary,
    dds.total_salary
FROM departments d
INNER JOIN dept_daily_summary dds ON d.id = dds.dept_id
WHERE dds.summary_date = CURRENT_DATE
    AND dds.avg_salary > 50000
ORDER BY dds.total_salary DESC;

-- Step 10: Use query hints for optimization
SELECT /*+ INDEX(e idx_employees_dept_active) */
    d.name as department_name,
    COUNT(e.id) as employee_count,
    AVG(e.salary) as avg_salary
FROM departments d
INNER JOIN employees e ON d.id = e.department_id
WHERE e.active = 1
GROUP BY d.id, d.name;

-- Step 11: Monitor and analyze performance
-- Enable query profiling
SET profiling = 1;

-- Run optimized query
SELECT 
    d.name as department_name,
    COUNT(e.id) as employee_count,
    AVG(e.salary) as avg_salary
FROM departments d
INNER JOIN employees e ON d.id = e.department_id
WHERE e.active = 1
GROUP BY d.id, d.name;

-- Check performance
SHOW PROFILES;
```
2. What is the role of statistics in query optimization? _(Asked in Accenture)_

**🧩 Foundation:** Database statistics provide information about data distribution, table sizes, and column cardinality that the query optimizer uses to choose the best execution plan.

**⚙️ Function:** Statistics help the optimizer estimate query costs, select optimal join orders, and choose the most efficient access methods.

**🔁 Flow:**
```sql
-- Check current statistics
SELECT 
    table_name,
    table_rows,
    avg_row_length,
    data_length,
    index_length
FROM information_schema.tables 
WHERE table_schema = 'your_database';

-- View column statistics
SELECT 
    table_name,
    column_name,
    cardinality,
    nulls_ratio,
    avg_length
FROM information_schema.statistics 
WHERE table_schema = 'your_database';

-- Check statistics for specific table
SHOW TABLE STATUS LIKE 'employees';

-- Analyze table statistics
ANALYZE TABLE employees;

-- Update statistics for all tables
ANALYZE TABLE employees, departments, projects;

-- Check statistics age
SELECT 
    table_name,
    update_time
FROM information_schema.tables 
WHERE table_schema = 'your_database'
    AND table_name IN ('employees', 'departments');

-- Force statistics update
ANALYZE TABLE employees UPDATE HISTOGRAM ON department_id, salary;

-- Check histogram statistics
SELECT 
    column_name,
    histogram_type,
    histogram_buckets
FROM information_schema.column_statistics 
WHERE table_schema = 'your_database'
    AND table_name = 'employees';

-- Monitor statistics impact on query plans
-- Before updating statistics
EXPLAIN 
SELECT * FROM employees WHERE department_id = 1 AND salary > 50000;

-- Update statistics
ANALYZE TABLE employees;

-- After updating statistics
EXPLAIN 
SELECT * FROM employees WHERE department_id = 1 AND salary > 50000;

-- Check statistics for index usage
SHOW INDEX FROM employees;

-- Analyze index statistics
ANALYZE TABLE employees UPDATE HISTOGRAM ON department_id;

-- Check cardinality estimates
SELECT 
    index_name,
    cardinality,
    sub_part,
    packed,
    null,
    index_type
FROM information_schema.statistics 
WHERE table_schema = 'your_database'
    AND table_name = 'employees';

-- Monitor statistics freshness
CREATE TABLE statistics_monitor (
    table_name VARCHAR(100),
    last_analyzed TIMESTAMP,
    row_count BIGINT,
    avg_row_length INT,
    data_length BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert current statistics
INSERT INTO statistics_monitor (table_name, last_analyzed, row_count, avg_row_length, data_length)
SELECT 
    table_name,
    update_time,
    table_rows,
    avg_row_length,
    data_length
FROM information_schema.tables 
WHERE table_schema = 'your_database'
    AND table_name IN ('employees', 'departments');

-- Check statistics drift
SELECT 
    table_name,
    DATEDIFF(CURRENT_TIMESTAMP, last_analyzed) as days_since_analyzed,
    row_count,
    data_length
FROM statistics_monitor
ORDER BY days_since_analyzed DESC;

-- Automate statistics updates
-- Create event to update statistics weekly
CREATE EVENT update_statistics_weekly
ON SCHEDULE EVERY 1 WEEK
DO
BEGIN
    ANALYZE TABLE employees, departments, projects;
    
    INSERT INTO statistics_monitor (table_name, last_analyzed, row_count, avg_row_length, data_length)
    SELECT 
        table_name,
        update_time,
        table_rows,
        avg_row_length,
        data_length
    FROM information_schema.tables 
    WHERE table_schema = 'your_database'
        AND table_name IN ('employees', 'departments', 'projects');
END;

-- Check statistics impact on complex queries
-- Query with multiple joins
EXPLAIN 
SELECT 
    d.name as department_name,
    COUNT(e.id) as employee_count,
    AVG(e.salary) as avg_salary
FROM departments d
INNER JOIN employees e ON d.id = e.department_id
WHERE e.active = 1
GROUP BY d.id, d.name;

-- Update statistics and re-analyze
ANALYZE TABLE departments, employees;

EXPLAIN 
SELECT 
    d.name as department_name,
    COUNT(e.id) as employee_count,
    AVG(e.salary) as avg_salary
FROM departments d
INNER JOIN employees e ON d.id = e.department_id
WHERE e.active = 1
GROUP BY d.id, d.name;

-- Check statistics for partitioned tables
SHOW TABLE STATUS LIKE 'employees_partitioned';

-- Analyze specific partition
ANALYZE TABLE employees_partitioned PARTITION(p2023);

-- Check partition statistics
SELECT 
    partition_name,
    partition_description,
    table_rows,
    avg_row_length,
    data_length
FROM information_schema.partitions 
WHERE table_schema = 'your_database'
    AND table_name = 'employees_partitioned';

-- Monitor statistics accuracy
-- Compare estimated vs actual row counts
SELECT 
    'employees' as table_name,
    COUNT(*) as actual_rows,
    (SELECT table_rows FROM information_schema.tables 
     WHERE table_schema = 'your_database' AND table_name = 'employees') as estimated_rows
FROM employees

UNION ALL

SELECT 
    'departments' as table_name,
    COUNT(*) as actual_rows,
    (SELECT table_rows FROM information_schema.tables 
     WHERE table_schema = 'your_database' AND table_name = 'departments') as estimated_rows
FROM departments;

-- Check statistics for specific columns
SELECT 
    column_name,
    data_type,
    is_nullable,
    column_default,
    character_maximum_length
FROM information_schema.columns 
WHERE table_schema = 'your_database'
    AND table_name = 'employees'
ORDER BY ordinal_position;

-- Analyze statistics impact on different query types
-- Range queries
EXPLAIN 
SELECT * FROM employees WHERE salary BETWEEN 40000 AND 60000;

-- Update salary statistics
ANALYZE TABLE employees UPDATE HISTOGRAM ON salary;

EXPLAIN 
SELECT * FROM employees WHERE salary BETWEEN 40000 AND 60000;

-- LIKE queries
EXPLAIN 
SELECT * FROM employees WHERE name LIKE 'John%';

-- Update name statistics
ANALYZE TABLE employees UPDATE HISTOGRAM ON name;

EXPLAIN 
SELECT * FROM employees WHERE name LIKE 'John%';

-- Check statistics for foreign key relationships
SELECT 
    constraint_name,
    table_name,
    column_name,
    referenced_table_name,
    referenced_column_name
FROM information_schema.key_column_usage 
WHERE table_schema = 'your_database'
    AND referenced_table_name IS NOT NULL;

-- Analyze statistics for join optimization
-- Check join cardinality estimates
EXPLAIN 
SELECT e.name, d.name 
FROM employees e 
INNER JOIN departments d ON e.department_id = d.id;

-- Update statistics on join columns
ANALYZE TABLE employees UPDATE HISTOGRAM ON department_id;
ANALYZE TABLE departments UPDATE HISTOGRAM ON id;

EXPLAIN 
SELECT e.name, d.name 
FROM employees e 
INNER JOIN departments d ON e.department_id = d.id;

-- Monitor statistics performance impact
-- Enable query profiling
SET profiling = 1;

-- Run query before statistics update
SELECT COUNT(*) FROM employees WHERE department_id = 1;

-- Update statistics
ANALYZE TABLE employees;

-- Run query after statistics update
SELECT COUNT(*) FROM employees WHERE department_id = 1;

-- Compare execution times
SHOW PROFILES;
```

### Intermediate Query Questions

1. Write an optimized query to retrieve the top 10 customers by total order value. _(Asked in Wipro)_

**🧩 Foundation:** Optimize the query using proper indexes, efficient aggregation, and limiting result sets to improve performance.

**⚙️ Function:** Retrieve top customers efficiently while minimizing resource usage and execution time.

**🔁 Flow:**
```sql
-- Basic query (not optimized)
SELECT 
    c.name as customer_name,
    SUM(o.total_amount) as total_order_value
FROM customers c
LEFT JOIN orders o ON c.id = o.customer_id
GROUP BY c.id, c.name
ORDER BY total_order_value DESC
LIMIT 10;

-- Step 1: Create proper indexes
-- Index for JOIN condition
CREATE INDEX idx_orders_customer_id ON orders(customer_id);

-- Index for aggregation and ordering
CREATE INDEX idx_orders_customer_total ON orders(customer_id, total_amount);

-- Composite index for covering query
CREATE INDEX idx_orders_covering ON orders(customer_id, total_amount, order_date);

-- Index for customer table
CREATE INDEX idx_customers_id_name ON customers(id, name);

-- Step 2: Optimized query with proper JOIN
SELECT 
    c.name as customer_name,
    SUM(o.total_amount) as total_order_value
FROM customers c
INNER JOIN orders o ON c.id = o.customer_id  -- Use INNER JOIN if all customers have orders
GROUP BY c.id, c.name
ORDER BY total_order_value DESC
LIMIT 10;

-- Step 3: Use CTE for better performance
WITH customer_totals AS (
    SELECT 
        customer_id,
        SUM(total_amount) as total_order_value
    FROM orders
    GROUP BY customer_id
    ORDER BY total_order_value DESC
    LIMIT 10
)
SELECT 
    c.name as customer_name,
    ct.total_order_value
FROM customer_totals ct
INNER JOIN customers c ON ct.customer_id = c.id
ORDER BY ct.total_order_value DESC;

-- Step 4: Use window functions for ranking
SELECT 
    customer_name,
    total_order_value
FROM (
    SELECT 
        c.name as customer_name,
        SUM(o.total_amount) as total_order_value,
        ROW_NUMBER() OVER (ORDER BY SUM(o.total_amount) DESC) as rank
    FROM customers c
    INNER JOIN orders o ON c.id = o.customer_id
    GROUP BY c.id, c.name
) ranked_customers
WHERE rank <= 10;

-- Step 5: Optimize with pre-aggregation
-- Create summary table for frequent queries
CREATE TABLE customer_order_summary (
    customer_id INT,
    total_order_value DECIMAL(12,2),
    order_count INT,
    last_order_date DATE,
    PRIMARY KEY (customer_id),
    INDEX idx_total_value (total_order_value DESC)
);

-- Populate summary table
INSERT INTO customer_order_summary
SELECT 
    customer_id,
    SUM(total_amount) as total_order_value,
    COUNT(*) as order_count,
    MAX(order_date) as last_order_date
FROM orders
GROUP BY customer_id;

-- Query using summary table
SELECT 
    c.name as customer_name,
    cos.total_order_value,
    cos.order_count
FROM customer_order_summary cos
INNER JOIN customers c ON cos.customer_id = c.id
ORDER BY cos.total_order_value DESC
LIMIT 10;

-- Step 6: Use materialized view for complex aggregations
CREATE MATERIALIZED VIEW top_customers_view AS
SELECT 
    c.id as customer_id,
    c.name as customer_name,
    SUM(o.total_amount) as total_order_value,
    COUNT(o.id) as order_count,
    AVG(o.total_amount) as avg_order_value
FROM customers c
INNER JOIN orders o ON c.id = o.customer_id
GROUP BY c.id, c.name;

-- Query using materialized view
SELECT 
    customer_name,
    total_order_value,
    order_count,
    avg_order_value
FROM top_customers_view
ORDER BY total_order_value DESC
LIMIT 10;

-- Step 7: Optimize with partitioning
-- Create partitioned orders table
CREATE TABLE orders_partitioned (
    id INT,
    customer_id INT,
    total_amount DECIMAL(10,2),
    order_date DATE,
    INDEX idx_customer_amount (customer_id, total_amount)
) PARTITION BY RANGE (YEAR(order_date)) (
    PARTITION p2022 VALUES LESS THAN (2023),
    PARTITION p2023 VALUES LESS THAN (2024),
    PARTITION p2024 VALUES LESS THAN (2025),
    PARTITION p_future VALUES LESS THAN MAXVALUE
);

-- Query on partitioned table
SELECT 
    c.name as customer_name,
    SUM(o.total_amount) as total_order_value
FROM customers c
INNER JOIN orders_partitioned o ON c.id = o.customer_id
WHERE o.order_date >= '2023-01-01'
GROUP BY c.id, c.name
ORDER BY total_order_value DESC
LIMIT 10;

-- Step 8: Use covering index for maximum performance
-- Create covering index that includes all needed columns
CREATE INDEX idx_orders_covering_complete ON orders(customer_id, total_amount, order_date, id);

-- Query using covering index
SELECT 
    c.name as customer_name,
    SUM(o.total_amount) as total_order_value
FROM customers c
INNER JOIN orders o ON c.id = o.customer_id
WHERE o.total_amount > 0  -- Add filter to use index effectively
GROUP BY c.id, c.name
ORDER BY total_order_value DESC
LIMIT 10;

-- Step 9: Optimize with query hints
SELECT /*+ INDEX(o idx_orders_customer_total) */
    c.name as customer_name,
    SUM(o.total_amount) as total_order_value
FROM customers c
INNER JOIN orders o ON c.id = o.customer_id
GROUP BY c.id, c.name
ORDER BY total_order_value DESC
LIMIT 10;

-- Step 10: Use EXISTS for better performance with large datasets
SELECT 
    c.name as customer_name,
    (SELECT SUM(total_amount) FROM orders WHERE customer_id = c.id) as total_order_value
FROM customers c
WHERE EXISTS (SELECT 1 FROM orders WHERE customer_id = c.id)
ORDER BY total_order_value DESC
LIMIT 10;

-- Step 11: Optimize with batch processing for very large datasets
-- Process in batches to avoid memory issues
SET @batch_size = 1000;
SET @offset = 0;

-- First, get customer IDs with highest totals
SELECT customer_id, total_order_value
FROM (
    SELECT 
        customer_id,
        SUM(total_amount) as total_order_value
    FROM orders
    GROUP BY customer_id
    ORDER BY total_order_value DESC
    LIMIT 10
) top_customers;

-- Then get customer details
SELECT 
    c.name as customer_name,
    c.email,
    c.phone,
    ct.total_order_value
FROM (
    SELECT 
        customer_id,
        SUM(total_amount) as total_order_value
    FROM orders
    GROUP BY customer_id
    ORDER BY total_order_value DESC
    LIMIT 10
) ct
INNER JOIN customers c ON ct.customer_id = c.id
ORDER BY ct.total_order_value DESC;

-- Step 12: Monitor and analyze performance
-- Enable query profiling
SET profiling = 1;

-- Run optimized query
SELECT 
    c.name as customer_name,
    SUM(o.total_amount) as total_order_value
FROM customers c
INNER JOIN orders o ON c.id = o.customer_id
GROUP BY c.id, c.name
ORDER BY total_order_value DESC
LIMIT 10;

-- Check execution time
SHOW PROFILES;

-- Analyze execution plan
EXPLAIN 
SELECT 
    c.name as customer_name,
    SUM(o.total_amount) as total_order_value
FROM customers c
INNER JOIN orders o ON c.id = o.customer_id
GROUP BY c.id, c.name
ORDER BY total_order_value DESC
LIMIT 10;
```

### Advanced Questions

1. How do you use partitioning to improve query performance in large tables? _(Asked in Cognizant)_

**🧩 Foundation:** Partitioning divides large tables into smaller, manageable pieces based on specific criteria, improving query performance by reducing the amount of data scanned.

**⚙️ Function:** Partition tables to improve query performance, maintenance operations, and data management for large datasets.

**🔁 Flow:**
```sql
-- Step 1: Range Partitioning by Date
-- Create partitioned orders table
CREATE TABLE orders_partitioned (
    id INT,
    customer_id INT,
    order_date DATE,
    total_amount DECIMAL(10,2),
    status VARCHAR(20)
) PARTITION BY RANGE (YEAR(order_date)) (
    PARTITION p2020 VALUES LESS THAN (2021),
    PARTITION p2021 VALUES LESS THAN (2022),
    PARTITION p2022 VALUES LESS THAN (2023),
    PARTITION p2023 VALUES LESS THAN (2024),
    PARTITION p2024 VALUES LESS THAN (2025),
    PARTITION p_future VALUES LESS THAN MAXVALUE
);

-- Query on specific partition
SELECT COUNT(*), SUM(total_amount)
FROM orders_partitioned PARTITION(p2023)
WHERE order_date >= '2023-01-01' AND order_date < '2024-01-01';

-- Step 2: List Partitioning by Region
CREATE TABLE sales_partitioned (
    id INT,
    region VARCHAR(50),
    amount DECIMAL(10,2),
    sale_date DATE
) PARTITION BY LIST (region) (
    PARTITION p_north VALUES IN ('North', 'Northeast'),
    PARTITION p_south VALUES IN ('South', 'Southeast'),
    PARTITION p_east VALUES IN ('East', 'Northeast'),
    PARTITION p_west VALUES IN ('West', 'Northwest'),
    PARTITION p_other VALUES IN ('Central', 'Midwest')
);

-- Query specific region
SELECT SUM(amount) FROM sales_partitioned PARTITION(p_north);

-- Step 3: Hash Partitioning for Even Distribution
CREATE TABLE employees_partitioned (
    id INT,
    name VARCHAR(100),
    department_id INT,
    salary DECIMAL(10,2)
) PARTITION BY HASH(department_id) PARTITIONS 4;

-- Query with hash partitioning
SELECT AVG(salary) FROM employees_partitioned 
WHERE department_id = 1;

-- Step 4: Composite Partitioning (Range + Hash)
CREATE TABLE orders_composite (
    id INT,
    customer_id INT,
    order_date DATE,
    total_amount DECIMAL(10,2)
) PARTITION BY RANGE (YEAR(order_date))
SUBPARTITION BY HASH(customer_id) SUBPARTITIONS 4 (
    PARTITION p2023 VALUES LESS THAN (2024),
    PARTITION p2024 VALUES LESS THAN (2025),
    PARTITION p_future VALUES LESS THAN MAXVALUE
);

-- Query composite partitioned table
SELECT COUNT(*) FROM orders_composite 
WHERE YEAR(order_date) = 2023 AND customer_id = 123;

-- Step 5: Partition Pruning Examples
-- Query that uses partition pruning
EXPLAIN 
SELECT COUNT(*), SUM(total_amount)
FROM orders_partitioned
WHERE order_date >= '2023-01-01' AND order_date < '2024-01-01';

-- Query that doesn't use partition pruning
EXPLAIN 
SELECT COUNT(*), SUM(total_amount)
FROM orders_partitioned
WHERE total_amount > 1000;  -- No date filter

-- Step 6: Managing Partitions
-- Add new partition
ALTER TABLE orders_partitioned 
ADD PARTITION (PARTITION p2025 VALUES LESS THAN (2026));

-- Drop old partition
ALTER TABLE orders_partitioned 
DROP PARTITION p2020;

-- Reorganize partition
ALTER TABLE orders_partitioned 
REORGANIZE PARTITION p_future INTO (
    PARTITION p2025 VALUES LESS THAN (2026),
    PARTITION p_future VALUES LESS THAN MAXVALUE
);

-- Step 7: Partition Maintenance
-- Analyze specific partition
ANALYZE TABLE orders_partitioned PARTITION(p2023);

-- Check partition information
SELECT 
    partition_name,
    partition_description,
    table_rows,
    avg_row_length,
    data_length
FROM information_schema.partitions 
WHERE table_schema = 'your_database'
    AND table_name = 'orders_partitioned'
ORDER BY partition_ordinal_position;

-- Step 8: Performance Comparison
-- Query on non-partitioned table
SELECT COUNT(*), SUM(total_amount)
FROM orders
WHERE order_date >= '2023-01-01' AND order_date < '2024-01-01';

-- Query on partitioned table
SELECT COUNT(*), SUM(total_amount)
FROM orders_partitioned
WHERE order_date >= '2023-01-01' AND order_date < '2024-01-01';

-- Step 9: Partitioning with Indexes
-- Create local indexes on partitioned table
CREATE INDEX idx_orders_customer_date 
ON orders_partitioned(customer_id, order_date);

-- Create global index
CREATE INDEX idx_orders_amount 
ON orders_partitioned(total_amount);

-- Query using partitioned index
SELECT * FROM orders_partitioned 
WHERE customer_id = 123 AND order_date >= '2023-01-01';

-- Step 10: Partitioning for Archival
-- Archive old data to separate partition
CREATE TABLE orders_archive (
    id INT,
    customer_id INT,
    order_date DATE,
    total_amount DECIMAL(10,2)
) PARTITION BY RANGE (YEAR(order_date)) (
    PARTITION p_old VALUES LESS THAN (2020),
    PARTITION p_archive VALUES LESS THAN (2023)
);

-- Move old data to archive
INSERT INTO orders_archive 
SELECT * FROM orders_partitioned 
WHERE YEAR(order_date) < 2023;

-- Step 11: Partitioning for Parallel Processing
-- Query that can use parallel processing
SELECT 
    YEAR(order_date) as year,
    COUNT(*) as order_count,
    SUM(total_amount) as total_sales
FROM orders_partitioned
WHERE order_date >= '2020-01-01'
GROUP BY YEAR(order_date)
ORDER BY year;

-- Step 12: Partitioning with Foreign Keys
-- Create partitioned table with foreign key
CREATE TABLE order_items_partitioned (
    id INT,
    order_id INT,
    product_id INT,
    quantity INT,
    price DECIMAL(10,2),
    order_date DATE,
    FOREIGN KEY (order_id) REFERENCES orders(id)
) PARTITION BY RANGE (YEAR(order_date)) (
    PARTITION p2023 VALUES LESS THAN (2024),
    PARTITION p2024 VALUES LESS THAN (2025)
);

-- Step 13: Partitioning for Data Lifecycle Management
-- Create table with different storage for different partitions
CREATE TABLE logs_partitioned (
    id INT,
    log_date DATETIME,
    message TEXT,
    level VARCHAR(10)
) PARTITION BY RANGE (TO_DAYS(log_date)) (
    PARTITION p_current VALUES LESS THAN (TO_DAYS(NOW())),
    PARTITION p_old VALUES LESS THAN (TO_DAYS(DATE_SUB(NOW(), INTERVAL 30 DAY))),
    PARTITION p_archive VALUES LESS THAN MAXVALUE
);

-- Step 14: Monitoring Partition Performance
-- Check partition usage
SELECT 
    partition_name,
    table_rows,
    data_length,
    index_length,
    (data_length + index_length) as total_size
FROM information_schema.partitions 
WHERE table_schema = 'your_database'
    AND table_name = 'orders_partitioned';

-- Monitor partition access patterns
SELECT 
    partition_name,
    COUNT(*) as access_count
FROM performance_schema.table_io_waits_summary_by_index_usage
WHERE object_schema = 'your_database'
    AND object_name = 'orders_partitioned'
GROUP BY partition_name;

-- Step 15: Partitioning Best Practices
-- Use appropriate partition key
-- Good: Date columns, categorical columns with limited values
-- Bad: Random values, frequently changing columns

-- Keep partition sizes manageable
-- Too small: Many partitions, overhead
-- Too large: No performance benefit

-- Plan for partition maintenance
-- Regular cleanup of old partitions
-- Monitoring partition sizes
-- Backup strategies for partitions

-- Step 16: Partitioning for Specific Use Cases
-- Time-series data
CREATE TABLE metrics_partitioned (
    id INT,
    metric_name VARCHAR(100),
    metric_value DECIMAL(10,2),
    timestamp DATETIME
) PARTITION BY RANGE (TO_DAYS(timestamp)) (
    PARTITION p_today VALUES LESS THAN (TO_DAYS(NOW())),
    PARTITION p_week VALUES LESS THAN (TO_DAYS(DATE_ADD(NOW(), INTERVAL 7 DAY))),
    PARTITION p_month VALUES LESS THAN (TO_DAYS(DATE_ADD(NOW(), INTERVAL 30 DAY)))
);

-- Geographic data
CREATE TABLE locations_partitioned (
    id INT,
    latitude DECIMAL(10,8),
    longitude DECIMAL(11,8),
    location_name VARCHAR(100)
) PARTITION BY LIST (FLOOR(latitude/10)) (
    PARTITION p_north VALUES IN (4, 5, 6, 7, 8, 9),
    PARTITION p_south VALUES IN (0, 1, 2, 3),
    PARTITION p_other VALUES IN (DEFAULT)
);
```
2. What is the difference between a table scan and an index seek? _(Asked in TCS Digital)_

**🧩 Foundation:** Table scan reads all rows in a table sequentially, while index seek uses an index to directly locate specific rows, making it much faster for selective queries.

**⚙️ Function:** Understanding the difference helps optimize queries by choosing the most efficient access method for data retrieval.

**🔁 Flow:**
```sql
-- Table Scan Examples
-- Query that causes table scan (no WHERE clause)
SELECT * FROM employees;  -- Reads all rows

-- Query with non-indexed column
SELECT * FROM employees WHERE name LIKE '%John%';  -- Table scan

-- Query with function on indexed column
SELECT * FROM employees WHERE YEAR(hire_date) = 2023;  -- Table scan

-- Query with OR conditions that can't use indexes
SELECT * FROM employees WHERE department_id = 1 OR salary > 50000;  -- Table scan

-- Index Seek Examples
-- Query with indexed column
SELECT * FROM employees WHERE department_id = 1;  -- Index seek

-- Query with composite index
SELECT * FROM employees WHERE department_id = 1 AND salary > 50000;  -- Index seek

-- Query with primary key
SELECT * FROM employees WHERE id = 123;  -- Index seek

-- Query with unique index
SELECT * FROM employees WHERE email = 'john@example.com';  -- Index seek

-- Performance Comparison
-- Enable query profiling
SET profiling = 1;

-- Table scan query
SELECT COUNT(*) FROM employees WHERE name LIKE '%John%';

-- Index seek query
SELECT COUNT(*) FROM employees WHERE department_id = 1;

-- Compare execution times
SHOW PROFILES;

-- EXPLAIN Analysis
-- Table scan execution plan
EXPLAIN 
SELECT * FROM employees WHERE name LIKE '%John%';

-- Index seek execution plan
EXPLAIN 
SELECT * FROM employees WHERE department_id = 1;

-- Create indexes for comparison
CREATE INDEX idx_employees_department_id ON employees(department_id);
CREATE INDEX idx_employees_name ON employees(name);

-- Analyze with indexes
EXPLAIN 
SELECT * FROM employees WHERE department_id = 1;  -- Should show index seek

EXPLAIN 
SELECT * FROM employees WHERE name LIKE 'John%';  -- Should show index seek

EXPLAIN 
SELECT * FROM employees WHERE name LIKE '%John%';  -- Still table scan

-- Index Seek with Different Index Types
-- Single column index
CREATE INDEX idx_employees_salary ON employees(salary);
SELECT * FROM employees WHERE salary = 50000;  -- Index seek

-- Composite index
CREATE INDEX idx_employees_dept_salary ON employees(department_id, salary);
SELECT * FROM employees WHERE department_id = 1 AND salary > 50000;  -- Index seek

-- Covering index
CREATE INDEX idx_employees_covering ON employees(department_id, salary, name);
SELECT department_id, salary, name FROM employees WHERE department_id = 1;  -- Index seek

-- Unique index
CREATE UNIQUE INDEX idx_employees_email ON employees(email);
SELECT * FROM employees WHERE email = 'john@example.com';  -- Index seek

-- Table Scan Scenarios
-- Large table without proper indexes
CREATE TABLE large_table (
    id INT,
    name VARCHAR(100),
    description TEXT,
    created_date DATETIME
);

-- Insert large amount of data
INSERT INTO large_table (id, name, description, created_date)
SELECT 
    n,
    CONCAT('Name', n),
    CONCAT('Description for record ', n),
    DATE_ADD('2020-01-01', INTERVAL n DAY)
FROM (
    SELECT 1 + ones.n + 10 * tens.n + 100 * hundreds.n as n
    FROM (SELECT 0 n UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) ones,
         (SELECT 0 n UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) tens,
         (SELECT 0 n UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) hundreds
    WHERE 1 + ones.n + 10 * tens.n + 100 * hundreds.n <= 100000
) numbers;

-- Query causing table scan
EXPLAIN 
SELECT COUNT(*) FROM large_table WHERE name LIKE '%John%';

-- Index Seek Optimization
-- Create index to convert table scan to index seek
CREATE INDEX idx_large_table_name ON large_table(name);

-- Query now uses index seek
EXPLAIN 
SELECT COUNT(*) FROM large_table WHERE name LIKE 'John%';

-- Still table scan (can't use index with leading wildcard)
EXPLAIN 
SELECT COUNT(*) FROM large_table WHERE name LIKE '%John%';

-- Index Seek with Range Queries
-- Create index for range queries
CREATE INDEX idx_large_table_date ON large_table(created_date);

-- Range query using index seek
EXPLAIN 
SELECT COUNT(*) FROM large_table 
WHERE created_date >= '2023-01-01' AND created_date < '2024-01-01';

-- Index Seek with ORDER BY
-- Query with ORDER BY using index
EXPLAIN 
SELECT * FROM employees 
WHERE department_id = 1 
ORDER BY salary;  -- Uses index for both filtering and sorting

-- Query without proper index for ORDER BY
EXPLAIN 
SELECT * FROM employees 
ORDER BY name;  -- May use table scan if no index on name

-- Index Seek vs Table Scan Performance Monitoring
-- Create performance monitoring table
CREATE TABLE query_performance_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    query_type ENUM('table_scan', 'index_seek'),
    execution_time DECIMAL(10,4),
    rows_examined INT,
    rows_returned INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Monitor table scan performance
INSERT INTO query_performance_log (query_type, execution_time, rows_examined, rows_returned)
SELECT 
    'table_scan',
    0.1250,
    100000,
    50
FROM DUAL;

-- Monitor index seek performance
INSERT INTO query_performance_log (query_type, execution_time, rows_examined, rows_returned)
SELECT 
    'index_seek',
    0.0025,
    1000,
    50
FROM DUAL;

-- Analyze performance differences
SELECT 
    query_type,
    AVG(execution_time) as avg_execution_time,
    AVG(rows_examined) as avg_rows_examined,
    COUNT(*) as query_count
FROM query_performance_log
GROUP BY query_type;

-- Index Seek Optimization Techniques
-- Use covering indexes
CREATE INDEX idx_employees_covering ON employees(department_id, salary, name, email);

-- Query using covering index (no table access needed)
EXPLAIN 
SELECT department_id, salary, name, email 
FROM employees 
WHERE department_id = 1 AND salary > 50000;

-- Use index hints
SELECT /*+ INDEX(employees idx_employees_department_id) */
    * FROM employees WHERE department_id = 1;

-- Force table scan when needed
SELECT /*+ FULL(employees) */
    * FROM employees WHERE department_id = 1;

-- Index Seek with JOINs
-- Create indexes for JOIN conditions
CREATE INDEX idx_orders_customer_id ON orders(customer_id);
CREATE INDEX idx_customers_id ON customers(id);

-- JOIN query using index seek
EXPLAIN 
SELECT c.name, o.total_amount
FROM customers c
INNER JOIN orders o ON c.id = o.customer_id
WHERE c.id = 123;

-- Index Seek with Subqueries
-- Query with EXISTS using index seek
EXPLAIN 
SELECT * FROM customers c
WHERE EXISTS (
    SELECT 1 FROM orders o 
    WHERE o.customer_id = c.id AND o.total_amount > 1000
);

-- Query with IN using index seek
EXPLAIN 
SELECT * FROM customers c
WHERE c.id IN (
    SELECT customer_id FROM orders 
    WHERE total_amount > 1000
);

-- Index Seek Best Practices
-- Choose appropriate index columns
-- High selectivity columns (many unique values)
-- Columns used in WHERE, JOIN, ORDER BY clauses
-- Avoid indexes on low selectivity columns

-- Monitor index usage
SELECT 
    index_name,
    cardinality,
    sub_part,
    packed,
    null,
    index_type
FROM information_schema.statistics 
WHERE table_schema = 'your_database'
    AND table_name = 'employees';

-- Regular index maintenance
ANALYZE TABLE employees;
OPTIMIZE TABLE employees;
```

### Advanced Query Questions

1. Write a query to partition a large sales table by year and retrieve data for a specific year. _(Asked in Cognizant)_

**🧩 Foundation:** Partition large sales tables by year to improve query performance and data management for time-series data.

**⚙️ Function:** Create partitioned tables and write efficient queries to retrieve data from specific partitions.

**🔁 Flow:**
```sql
-- Step 1: Create partitioned sales table
CREATE TABLE sales_partitioned (
    id INT AUTO_INCREMENT,
    customer_id INT,
    product_id INT,
    sale_date DATE,
    quantity INT,
    unit_price DECIMAL(10,2),
    total_amount DECIMAL(12,2),
    region VARCHAR(50),
    salesperson_id INT,
    PRIMARY KEY (id, sale_date)
) PARTITION BY RANGE (YEAR(sale_date)) (
    PARTITION p2020 VALUES LESS THAN (2021),
    PARTITION p2021 VALUES LESS THAN (2022),
    PARTITION p2022 VALUES LESS THAN (2023),
    PARTITION p2023 VALUES LESS THAN (2024),
    PARTITION p2024 VALUES LESS THAN (2025),
    PARTITION p_future VALUES LESS THAN MAXVALUE
);

-- Step 2: Insert sample data
INSERT INTO sales_partitioned (customer_id, product_id, sale_date, quantity, unit_price, total_amount, region, salesperson_id)
VALUES 
(1, 101, '2023-01-15', 2, 25.00, 50.00, 'North', 1),
(2, 102, '2023-02-20', 1, 100.00, 100.00, 'South', 2),
(3, 103, '2023-03-10', 3, 75.00, 225.00, 'East', 1),
(1, 104, '2023-04-05', 1, 150.00, 150.00, 'North', 3),
(4, 105, '2023-05-12', 2, 50.00, 100.00, 'West', 2),
(2, 106, '2023-06-18', 1, 200.00, 200.00, 'South', 1),
(5, 107, '2023-07-22', 4, 30.00, 120.00, 'East', 3),
(3, 108, '2023-08-30', 1, 300.00, 300.00, 'North', 2),
(1, 109, '2023-09-14', 2, 80.00, 160.00, 'West', 1),
(4, 110, '2023-10-25', 1, 120.00, 120.00, 'South', 3);

-- Step 3: Query data for specific year (2023)
-- Basic query for 2023 data
SELECT 
    customer_id,
    product_id,
    sale_date,
    total_amount
FROM sales_partitioned
WHERE YEAR(sale_date) = 2023;

-- Step 4: Query with partition pruning
-- Query that uses partition pruning (efficient)
SELECT 
    customer_id,
    product_id,
    sale_date,
    total_amount
FROM sales_partitioned
WHERE sale_date >= '2023-01-01' AND sale_date < '2024-01-01';

-- Step 5: Query specific partition directly
-- Query specific partition (most efficient)
SELECT 
    customer_id,
    product_id,
    sale_date,
    total_amount
FROM sales_partitioned PARTITION(p2023);

-- Step 6: Aggregated queries for specific year
-- Total sales for 2023
SELECT 
    COUNT(*) as total_sales,
    SUM(total_amount) as total_revenue,
    AVG(total_amount) as avg_sale_amount
FROM sales_partitioned
WHERE sale_date >= '2023-01-01' AND sale_date < '2024-01-01';

-- Monthly breakdown for 2023
SELECT 
    MONTH(sale_date) as month,
    COUNT(*) as sales_count,
    SUM(total_amount) as monthly_revenue
FROM sales_partitioned
WHERE sale_date >= '2023-01-01' AND sale_date < '2024-01-01'
GROUP BY MONTH(sale_date)
ORDER BY month;

-- Step 7: Complex queries with JOINs
-- Sales with customer information for 2023
SELECT 
    c.name as customer_name,
    s.sale_date,
    s.total_amount,
    s.region
FROM sales_partitioned s
INNER JOIN customers c ON s.customer_id = c.id
WHERE s.sale_date >= '2023-01-01' AND s.sale_date < '2024-01-01'
ORDER BY s.sale_date;

-- Step 8: Performance comparison
-- Query on non-partitioned table (slower)
SELECT COUNT(*), SUM(total_amount)
FROM sales
WHERE sale_date >= '2023-01-01' AND sale_date < '2024-01-01';

-- Query on partitioned table (faster)
SELECT COUNT(*), SUM(total_amount)
FROM sales_partitioned
WHERE sale_date >= '2023-01-01' AND sale_date < '2024-01-01';

-- Step 9: Partition management
-- Add new partition for 2025
ALTER TABLE sales_partitioned 
ADD PARTITION (PARTITION p2025 VALUES LESS THAN (2026));

-- Drop old partition (2020)
ALTER TABLE sales_partitioned 
DROP PARTITION p2020;

-- Reorganize partitions
ALTER TABLE sales_partitioned 
REORGANIZE PARTITION p_future INTO (
    PARTITION p2025 VALUES LESS THAN (2026),
    PARTITION p_future VALUES LESS THAN MAXVALUE
);

-- Step 10: Partition information
-- Check partition details
SELECT 
    partition_name,
    partition_description,
    table_rows,
    avg_row_length,
    data_length
FROM information_schema.partitions 
WHERE table_schema = 'your_database'
    AND table_name = 'sales_partitioned'
ORDER BY partition_ordinal_position;

-- Step 11: Advanced queries with partitioning
-- Top customers by sales in 2023
SELECT 
    c.name as customer_name,
    COUNT(s.id) as sales_count,
    SUM(s.total_amount) as total_spent
FROM sales_partitioned s
INNER JOIN customers c ON s.customer_id = c.id
WHERE s.sale_date >= '2023-01-01' AND s.sale_date < '2024-01-01'
GROUP BY s.customer_id, c.name
ORDER BY total_spent DESC
LIMIT 10;

-- Regional sales analysis for 2023
SELECT 
    region,
    COUNT(*) as sales_count,
    SUM(total_amount) as regional_revenue,
    AVG(total_amount) as avg_sale_amount
FROM sales_partitioned
WHERE sale_date >= '2023-01-01' AND sale_date < '2024-01-01'
GROUP BY region
ORDER BY regional_revenue DESC;

-- Step 12: Partitioning with indexes
-- Create local indexes on partitioned table
CREATE INDEX idx_sales_customer_date 
ON sales_partitioned(customer_id, sale_date);

-- Create global index
CREATE INDEX idx_sales_amount 
ON sales_partitioned(total_amount);

-- Query using partitioned index
SELECT * FROM sales_partitioned 
WHERE customer_id = 1 AND sale_date >= '2023-01-01';

-- Step 13: Partitioning for archival
-- Create archive table
CREATE TABLE sales_archive (
    id INT,
    customer_id INT,
    product_id INT,
    sale_date DATE,
    quantity INT,
    unit_price DECIMAL(10,2),
    total_amount DECIMAL(12,2),
    region VARCHAR(50),
    salesperson_id INT
) PARTITION BY RANGE (YEAR(sale_date)) (
    PARTITION p_old VALUES LESS THAN (2020),
    PARTITION p_archive VALUES LESS THAN (2023)
);

-- Move old data to archive
INSERT INTO sales_archive 
SELECT * FROM sales_partitioned 
WHERE YEAR(sale_date) < 2023;

-- Step 14: Partitioning with data lifecycle
-- Create table with different storage for different partitions
CREATE TABLE sales_lifecycle (
    id INT,
    customer_id INT,
    sale_date DATE,
    total_amount DECIMAL(12,2)
) PARTITION BY RANGE (TO_DAYS(sale_date)) (
    PARTITION p_current VALUES LESS THAN (TO_DAYS(NOW())),
    PARTITION p_recent VALUES LESS THAN (TO_DAYS(DATE_SUB(NOW(), INTERVAL 30 DAY))),
    PARTITION p_old VALUES LESS THAN (TO_DAYS(DATE_SUB(NOW(), INTERVAL 1 YEAR))),
    PARTITION p_archive VALUES LESS THAN MAXVALUE
);

-- Step 15: Monitoring partition performance
-- Check partition usage
SELECT 
    partition_name,
    table_rows,
    data_length,
    index_length,
    (data_length + index_length) as total_size
FROM information_schema.partitions 
WHERE table_schema = 'your_database'
    AND table_name = 'sales_partitioned';

-- Monitor partition access patterns
SELECT 
    partition_name,
    COUNT(*) as access_count
FROM performance_schema.table_io_waits_summary_by_index_usage
WHERE object_schema = 'your_database'
    AND object_name = 'sales_partitioned'
GROUP BY partition_name;

-- Step 16: Partitioning best practices
-- Use appropriate partition key
-- Good: Date columns, categorical columns with limited values
-- Bad: Random values, frequently changing columns

-- Keep partition sizes manageable
-- Too small: Many partitions, overhead
-- Too large: No performance benefit

-- Plan for partition maintenance
-- Regular cleanup of old partitions
-- Monitoring partition sizes
-- Backup strategies for partitions

-- Step 17: Partitioning for specific use cases
-- Time-series data with multiple granularities
CREATE TABLE metrics_partitioned (
    id INT,
    metric_name VARCHAR(100),
    metric_value DECIMAL(10,2),
    timestamp DATETIME
) PARTITION BY RANGE (TO_DAYS(timestamp)) (
    PARTITION p_today VALUES LESS THAN (TO_DAYS(NOW())),
    PARTITION p_week VALUES LESS THAN (TO_DAYS(DATE_ADD(NOW(), INTERVAL 7 DAY))),
    PARTITION p_month VALUES LESS THAN (TO_DAYS(DATE_ADD(NOW(), INTERVAL 30 DAY))),
    PARTITION p_year VALUES LESS THAN (TO_DAYS(DATE_ADD(NOW(), INTERVAL 1 YEAR)))
);

-- Query recent metrics
SELECT 
    metric_name,
    AVG(metric_value) as avg_value
FROM metrics_partitioned
WHERE timestamp >= DATE_SUB(NOW(), INTERVAL 1 DAY)
GROUP BY metric_name;
```

### Tough Questions

1. How would you optimize a query on a table with billions of rows for real-time reporting? _(Asked in Amazon)_

**🧩 Foundation:** Optimize billion-row tables for real-time reporting using partitioning, indexing, materialized views, and query optimization techniques.

**⚙️ Function:** Implement strategies to achieve sub-second response times for analytical queries on massive datasets.

**🔁 Flow:**
```sql
-- Step 1: Table Design for Billion-Row Optimization
-- Create optimized table structure
CREATE TABLE sales_billion (
    id BIGINT AUTO_INCREMENT,
    customer_id INT,
    product_id INT,
    sale_date DATE,
    sale_timestamp TIMESTAMP,
    quantity INT,
    unit_price DECIMAL(10,2),
    total_amount DECIMAL(12,2),
    region VARCHAR(50),
    salesperson_id INT,
    PRIMARY KEY (id, sale_date)  -- Composite primary key for partitioning
) PARTITION BY RANGE (YEAR(sale_date)) (
    PARTITION p2020 VALUES LESS THAN (2021),
    PARTITION p2021 VALUES LESS THAN (2022),
    PARTITION p2022 VALUES LESS THAN (2023),
    PARTITION p2023 VALUES LESS THAN (2024),
    PARTITION p2024 VALUES LESS THAN (2025),
    PARTITION p_future VALUES LESS THAN MAXVALUE
);

-- Step 2: Strategic Indexing
-- Covering indexes for common queries
CREATE INDEX idx_sales_customer_date_covering 
ON sales_billion(customer_id, sale_date, total_amount, region);

-- Index for time-based queries
CREATE INDEX idx_sales_timestamp 
ON sales_billion(sale_timestamp);

-- Index for aggregation queries
CREATE INDEX idx_sales_region_date 
ON sales_billion(region, sale_date, total_amount);

-- Step 3: Materialized Views for Pre-computed Aggregations
-- Daily aggregations
CREATE MATERIALIZED VIEW sales_daily_summary AS
SELECT 
    sale_date,
    COUNT(*) as sales_count,
    SUM(total_amount) as daily_revenue,
    AVG(total_amount) as avg_sale_amount,
    COUNT(DISTINCT customer_id) as unique_customers
FROM sales_billion
GROUP BY sale_date;

-- Monthly aggregations
CREATE MATERIALIZED VIEW sales_monthly_summary AS
SELECT 
    YEAR(sale_date) as year,
    MONTH(sale_date) as month,
    COUNT(*) as sales_count,
    SUM(total_amount) as monthly_revenue,
    AVG(total_amount) as avg_sale_amount,
    COUNT(DISTINCT customer_id) as unique_customers
FROM sales_billion
GROUP BY YEAR(sale_date), MONTH(sale_date);

-- Step 4: Real-time Query Optimization
-- Query using materialized views (fastest)
SELECT 
    year,
    month,
    monthly_revenue,
    unique_customers
FROM sales_monthly_summary
WHERE year = 2023
ORDER BY month;

-- Query with partition pruning
SELECT 
    COUNT(*) as sales_count,
    SUM(total_amount) as total_revenue
FROM sales_billion
WHERE sale_date >= '2023-01-01' AND sale_date < '2024-01-01';

-- Step 5: Incremental Materialized Views
-- Create incremental update procedure
DELIMITER //
CREATE PROCEDURE UpdateDailySummary()
BEGIN
    DECLARE last_update_date DATE;
    
    -- Get last update date
    SELECT MAX(sale_date) INTO last_update_date 
    FROM sales_daily_summary;
    
    -- Insert new daily summaries
    INSERT INTO sales_daily_summary (sale_date, sales_count, daily_revenue, avg_sale_amount, unique_customers)
    SELECT 
        sale_date,
        COUNT(*) as sales_count,
        SUM(total_amount) as daily_revenue,
        AVG(total_amount) as avg_sale_amount,
        COUNT(DISTINCT customer_id) as unique_customers
    FROM sales_billion
    WHERE sale_date > last_update_date
    GROUP BY sale_date
    ON DUPLICATE KEY UPDATE
        sales_count = VALUES(sales_count),
        daily_revenue = VALUES(daily_revenue),
        avg_sale_amount = VALUES(avg_sale_amount),
        unique_customers = VALUES(unique_customers);
END //
DELIMITER ;

-- Step 6: Query Optimization Techniques
-- Use covering indexes
SELECT 
    customer_id,
    sale_date,
    total_amount,
    region
FROM sales_billion
WHERE customer_id = 123 AND sale_date >= '2023-01-01';

-- Use window functions for ranking
SELECT 
    customer_id,
    total_amount,
    ROW_NUMBER() OVER (ORDER BY total_amount DESC) as rank
FROM sales_billion
WHERE sale_date >= '2023-01-01'
LIMIT 1000;

-- Step 7: Partitioning Strategies
-- Sub-partitioning for better performance
CREATE TABLE sales_subpartitioned (
    id BIGINT,
    customer_id INT,
    sale_date DATE,
    total_amount DECIMAL(12,2)
) PARTITION BY RANGE (YEAR(sale_date))
SUBPARTITION BY HASH(customer_id) SUBPARTITIONS 4 (
    PARTITION p2023 VALUES LESS THAN (2024),
    PARTITION p2024 VALUES LESS THAN (2025)
);

-- Step 8: Caching Strategies
-- Create summary tables for caching
CREATE TABLE sales_cache (
    cache_key VARCHAR(100) PRIMARY KEY,
    cache_value JSON,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP
);

-- Insert cached results
INSERT INTO sales_cache (cache_key, cache_value, expires_at)
VALUES (
    'top_customers_2023',
    JSON_OBJECT(
        'customers', JSON_ARRAY(
            JSON_OBJECT('customer_id', 1, 'total_spent', 50000),
            JSON_OBJECT('customer_id', 2, 'total_spent', 45000)
        ),
        'generated_at', NOW()
    ),
    DATE_ADD(NOW(), INTERVAL 1 HOUR)
);

-- Step 9: Query Hints and Optimization
-- Use query hints for optimization
SELECT /*+ INDEX(sales_billion idx_sales_customer_date_covering) */
    customer_id,
    COUNT(*) as sales_count,
    SUM(total_amount) as total_spent
FROM sales_billion
WHERE sale_date >= '2023-01-01'
GROUP BY customer_id
HAVING total_spent > 10000;

-- Step 10: Parallel Processing
-- Enable parallel query execution
SET SESSION innodb_parallel_read_threads = 8;

-- Query that can use parallel processing
SELECT 
    region,
    COUNT(*) as sales_count,
    SUM(total_amount) as regional_revenue
FROM sales_billion
WHERE sale_date >= '2023-01-01'
GROUP BY region;

-- Step 11: Data Archival Strategy
-- Archive old data to separate table
CREATE TABLE sales_archive (
    id BIGINT,
    customer_id INT,
    sale_date DATE,
    total_amount DECIMAL(12,2)
) PARTITION BY RANGE (YEAR(sale_date)) (
    PARTITION p_old VALUES LESS THAN (2020),
    PARTITION p_archive VALUES LESS THAN (2023)
);

-- Move old data to archive
INSERT INTO sales_archive 
SELECT * FROM sales_billion 
WHERE YEAR(sale_date) < 2023;

-- Step 12: Real-time Dashboard Queries
-- Current day sales
SELECT 
    COUNT(*) as today_sales,
    SUM(total_amount) as today_revenue,
    AVG(total_amount) as avg_sale_amount
FROM sales_billion
WHERE DATE(sale_timestamp) = CURDATE();

-- Real-time top products
SELECT 
    product_id,
    COUNT(*) as sales_count,
    SUM(total_amount) as total_revenue
FROM sales_billion
WHERE sale_date >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)
GROUP BY product_id
ORDER BY total_revenue DESC
LIMIT 10;

-- Step 13: Monitoring and Performance Tuning
-- Create performance monitoring table
CREATE TABLE query_performance (
    id INT AUTO_INCREMENT PRIMARY KEY,
    query_hash VARCHAR(64),
    execution_time DECIMAL(10,4),
    rows_examined BIGINT,
    rows_returned INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Monitor slow queries
INSERT INTO query_performance (query_hash, execution_time, rows_examined, rows_returned)
VALUES (
    MD5('SELECT COUNT(*) FROM sales_billion WHERE sale_date >= "2023-01-01"'),
    0.1250,
    1000000,
    1
);

-- Step 14: Advanced Optimization Techniques
-- Use CTEs for complex queries
WITH daily_totals AS (
    SELECT 
        sale_date,
        SUM(total_amount) as daily_revenue
    FROM sales_billion
    WHERE sale_date >= '2023-01-01'
    GROUP BY sale_date
),
running_totals AS (
    SELECT 
        sale_date,
        daily_revenue,
        SUM(daily_revenue) OVER (ORDER BY sale_date) as cumulative_revenue
    FROM daily_totals
)
SELECT * FROM running_totals
ORDER BY sale_date;

-- Step 15: Scalability Considerations
-- Horizontal partitioning by customer ranges
CREATE TABLE sales_customer_1 (
    id BIGINT,
    customer_id INT,
    sale_date DATE,
    total_amount DECIMAL(12,2)
) PARTITION BY RANGE (YEAR(sale_date));

CREATE TABLE sales_customer_2 (
    id BIGINT,
    customer_id INT,
    sale_date DATE,
    total_amount DECIMAL(12,2)
) PARTITION BY RANGE (YEAR(sale_date));

-- Step 16: Backup and Recovery Strategy
-- Create backup procedures for large tables
-- Use incremental backups
-- Implement point-in-time recovery

-- Step 17: Monitoring and Alerting
-- Set up monitoring for query performance
-- Alert on slow queries
-- Monitor partition sizes and growth

-- Step 18: Best Practices Summary
-- 1. Use appropriate partitioning strategy
-- 2. Create covering indexes for common queries
-- 3. Implement materialized views for aggregations
-- 4. Use query hints when necessary
-- 5. Monitor and tune performance regularly
-- 6. Implement proper caching strategies
-- 7. Plan for data archival
-- 8. Use parallel processing where possible
```

### Tough Query Questions

1. Write a query to retrieve real-time sales data from a billion-row table with minimal latency. _(Asked in Amazon)_

**🧩 Foundation:** Design ultra-fast queries for billion-row tables using strategic indexing, partitioning, and optimization techniques to achieve minimal latency.

**⚙️ Function:** Retrieve real-time sales data with sub-second response times from massive datasets.

**🔁 Flow:**
```sql
-- Step 1: Optimized Table Structure for Real-time Queries
CREATE TABLE sales_realtime (
    id BIGINT AUTO_INCREMENT,
    customer_id INT,
    product_id INT,
    sale_timestamp TIMESTAMP(3),  -- Microsecond precision
    sale_date DATE,
    quantity INT,
    unit_price DECIMAL(10,2),
    total_amount DECIMAL(12,2),
    region VARCHAR(50),
    salesperson_id INT,
    PRIMARY KEY (id, sale_date)  -- Composite key for partitioning
) PARTITION BY RANGE (YEAR(sale_date)) (
    PARTITION p_current VALUES LESS THAN (2024),
    PARTITION p_2024 VALUES LESS THAN (2025),
    PARTITION p_future VALUES LESS THAN MAXVALUE
);

-- Step 2: Strategic Indexing for Real-time Access
-- Covering index for most common real-time queries
CREATE INDEX idx_realtime_covering 
ON sales_realtime(sale_timestamp, customer_id, total_amount, region, product_id);

-- Index for time-based queries
CREATE INDEX idx_realtime_timestamp 
ON sales_realtime(sale_timestamp DESC);

-- Index for customer-specific queries
CREATE INDEX idx_realtime_customer 
ON sales_realtime(customer_id, sale_timestamp DESC);

-- Step 3: Real-time Dashboard Queries
-- Current hour sales (ultra-fast)
SELECT 
    COUNT(*) as sales_count,
    SUM(total_amount) as total_revenue,
    AVG(total_amount) as avg_sale_amount
FROM sales_realtime
WHERE sale_timestamp >= DATE_SUB(NOW(), INTERVAL 1 HOUR);

-- Last 5 minutes sales (real-time)
SELECT 
    COUNT(*) as recent_sales,
    SUM(total_amount) as recent_revenue
FROM sales_realtime
WHERE sale_timestamp >= DATE_SUB(NOW(), INTERVAL 5 MINUTE);

-- Step 4: Real-time Top Performers
-- Top customers in last hour
SELECT 
    customer_id,
    COUNT(*) as sales_count,
    SUM(total_amount) as total_spent
FROM sales_realtime
WHERE sale_timestamp >= DATE_SUB(NOW(), INTERVAL 1 HOUR)
GROUP BY customer_id
ORDER BY total_spent DESC
LIMIT 10;

-- Top products in last 30 minutes
SELECT 
    product_id,
    COUNT(*) as sales_count,
    SUM(total_amount) as total_revenue
FROM sales_realtime
WHERE sale_timestamp >= DATE_SUB(NOW(), INTERVAL 30 MINUTE)
GROUP BY product_id
ORDER BY total_revenue DESC
LIMIT 10;

-- Step 5: Real-time Regional Analysis
-- Regional performance in last hour
SELECT 
    region,
    COUNT(*) as sales_count,
    SUM(total_amount) as regional_revenue,
    AVG(total_amount) as avg_sale_amount
FROM sales_realtime
WHERE sale_timestamp >= DATE_SUB(NOW(), INTERVAL 1 HOUR)
GROUP BY region
ORDER BY regional_revenue DESC;

-- Step 6: Real-time Trend Analysis
-- Sales trend by minute (last hour)
SELECT 
    DATE_FORMAT(sale_timestamp, '%Y-%m-%d %H:%i') as minute,
    COUNT(*) as sales_count,
    SUM(total_amount) as minute_revenue
FROM sales_realtime
WHERE sale_timestamp >= DATE_SUB(NOW(), INTERVAL 1 HOUR)
GROUP BY DATE_FORMAT(sale_timestamp, '%Y-%m-%d %H:%i')
ORDER BY minute;

-- Step 7: Real-time Alerting Queries
-- High-value sales alert (last 5 minutes)
SELECT 
    id,
    customer_id,
    total_amount,
    sale_timestamp
FROM sales_realtime
WHERE sale_timestamp >= DATE_SUB(NOW(), INTERVAL 5 MINUTE)
    AND total_amount > 10000
ORDER BY sale_timestamp DESC;

-- Unusual activity detection
SELECT 
    COUNT(*) as sales_count,
    AVG(total_amount) as avg_amount
FROM sales_realtime
WHERE sale_timestamp >= DATE_SUB(NOW(), INTERVAL 10 MINUTE)
HAVING sales_count > 100 OR avg_amount > 5000;

-- Step 8: Real-time Customer Queries
-- Customer's recent activity
SELECT 
    sale_timestamp,
    product_id,
    total_amount,
    region
FROM sales_realtime
WHERE customer_id = 123
    AND sale_timestamp >= DATE_SUB(NOW(), INTERVAL 24 HOUR)
ORDER BY sale_timestamp DESC
LIMIT 50;

-- Customer's real-time spending
SELECT 
    SUM(total_amount) as total_spent_today,
    COUNT(*) as sales_count_today
FROM sales_realtime
WHERE customer_id = 123
    AND DATE(sale_timestamp) = CURDATE();

-- Step 9: Real-time Inventory Impact
-- Products sold in last hour
SELECT 
    product_id,
    SUM(quantity) as units_sold,
    COUNT(*) as sales_count
FROM sales_realtime
WHERE sale_timestamp >= DATE_SUB(NOW(), INTERVAL 1 HOUR)
GROUP BY product_id
ORDER BY units_sold DESC
LIMIT 20;

-- Step 10: Real-time Performance Monitoring
-- Query performance metrics
SELECT 
    COUNT(*) as total_sales,
    SUM(total_amount) as total_revenue,
    MIN(sale_timestamp) as earliest_sale,
    MAX(sale_timestamp) as latest_sale
FROM sales_realtime
WHERE sale_timestamp >= DATE_SUB(NOW(), INTERVAL 1 HOUR);

-- Step 11: Real-time Comparison Queries
-- Compare current hour vs previous hour
WITH current_hour AS (
    SELECT 
        COUNT(*) as sales_count,
        SUM(total_amount) as revenue
    FROM sales_realtime
    WHERE sale_timestamp >= DATE_SUB(NOW(), INTERVAL 1 HOUR)
),
previous_hour AS (
    SELECT 
        COUNT(*) as sales_count,
        SUM(total_amount) as revenue
    FROM sales_realtime
    WHERE sale_timestamp >= DATE_SUB(NOW(), INTERVAL 2 HOUR)
        AND sale_timestamp < DATE_SUB(NOW(), INTERVAL 1 HOUR)
)
SELECT 
    ch.sales_count as current_sales,
    ph.sales_count as previous_sales,
    ch.revenue as current_revenue,
    ph.revenue as previous_revenue,
    ((ch.revenue - ph.revenue) / ph.revenue * 100) as revenue_change_percent
FROM current_hour ch, previous_hour ph;

-- Step 12: Real-time Aggregation with Materialized Views
-- Create real-time summary table
CREATE TABLE sales_realtime_summary (
    summary_type ENUM('hourly', 'daily', 'monthly'),
    summary_date DATE,
    summary_hour INT,
    sales_count INT,
    total_revenue DECIMAL(15,2),
    unique_customers INT,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (summary_type, summary_date, summary_hour)
);

-- Update real-time summary
INSERT INTO sales_realtime_summary (summary_type, summary_date, summary_hour, sales_count, total_revenue, unique_customers)
SELECT 
    'hourly',
    DATE(sale_timestamp),
    HOUR(sale_timestamp),
    COUNT(*),
    SUM(total_amount),
    COUNT(DISTINCT customer_id)
FROM sales_realtime
WHERE sale_timestamp >= DATE_SUB(NOW(), INTERVAL 1 HOUR)
GROUP BY DATE(sale_timestamp), HOUR(sale_timestamp)
ON DUPLICATE KEY UPDATE
    sales_count = VALUES(sales_count),
    total_revenue = VALUES(total_revenue),
    unique_customers = VALUES(unique_customers),
    last_updated = NOW();

-- Query from summary table (fastest)
SELECT 
    summary_hour,
    sales_count,
    total_revenue
FROM sales_realtime_summary
WHERE summary_type = 'hourly'
    AND summary_date = CURDATE()
ORDER BY summary_hour;

-- Step 13: Real-time Partitioning Strategy
-- Query specific partition for current data
SELECT 
    COUNT(*) as current_sales,
    SUM(total_amount) as current_revenue
FROM sales_realtime PARTITION(p_current)
WHERE sale_timestamp >= DATE_SUB(NOW(), INTERVAL 1 HOUR);

-- Step 14: Real-time Caching Strategy
-- Create cache table for frequently accessed data
CREATE TABLE realtime_cache (
    cache_key VARCHAR(100) PRIMARY KEY,
    cache_value JSON,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP
);

-- Cache current hour data
INSERT INTO realtime_cache (cache_key, cache_value, expires_at)
VALUES (
    'current_hour_sales',
    JSON_OBJECT(
        'sales_count', (SELECT COUNT(*) FROM sales_realtime WHERE sale_timestamp >= DATE_SUB(NOW(), INTERVAL 1 HOUR)),
        'total_revenue', (SELECT SUM(total_amount) FROM sales_realtime WHERE sale_timestamp >= DATE_SUB(NOW(), INTERVAL 1 HOUR)),
        'generated_at', NOW()
    ),
    DATE_ADD(NOW(), INTERVAL 5 MINUTE)
);

-- Query from cache
SELECT 
    JSON_EXTRACT(cache_value, '$.sales_count') as sales_count,
    JSON_EXTRACT(cache_value, '$.total_revenue') as total_revenue
FROM realtime_cache
WHERE cache_key = 'current_hour_sales'
    AND expires_at > NOW();

-- Step 15: Real-time Query Optimization
-- Use query hints for maximum performance
SELECT /*+ INDEX(sales_realtime idx_realtime_timestamp) */
    COUNT(*) as sales_count,
    SUM(total_amount) as total_revenue
FROM sales_realtime
WHERE sale_timestamp >= DATE_SUB(NOW(), INTERVAL 1 HOUR);

-- Use covering index for complete data retrieval
SELECT /*+ INDEX(sales_realtime idx_realtime_covering) */
    customer_id,
    total_amount,
    region,
    product_id
FROM sales_realtime
WHERE sale_timestamp >= DATE_SUB(NOW(), INTERVAL 30 MINUTE)
ORDER BY sale_timestamp DESC
LIMIT 1000;

-- Step 16: Real-time Monitoring and Alerting
-- Monitor query performance
CREATE TABLE realtime_performance_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    query_type VARCHAR(50),
    execution_time DECIMAL(10,4),
    rows_examined BIGINT,
    rows_returned INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Log performance metrics
INSERT INTO realtime_performance_log (query_type, execution_time, rows_examined, rows_returned)
VALUES (
    'current_hour_sales',
    0.0025,
    50000,
    1
);

-- Step 17: Real-time Data Quality Checks
-- Check for data anomalies
SELECT 
    COUNT(*) as total_sales,
    COUNT(DISTINCT customer_id) as unique_customers,
    AVG(total_amount) as avg_amount,
    STDDEV(total_amount) as amount_stddev
FROM sales_realtime
WHERE sale_timestamp >= DATE_SUB(NOW(), INTERVAL 1 HOUR)
HAVING amount_stddev > 1000;  -- Alert if high variance

-- Step 18: Real-time Scalability Considerations
-- Use read replicas for real-time queries
-- Implement connection pooling
-- Use query result caching
-- Monitor and tune performance continuously
```

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

1. Your application's dashboard queries are slow, impacting user experience. How would you identify and resolve the bottleneck? _(Asked in HCL)_

**🧩 Foundation:** Systematically diagnose and resolve dashboard query performance issues by identifying bottlenecks and implementing targeted optimizations.

**⚙️ Function:** Improve dashboard response times through performance analysis, query optimization, and infrastructure improvements.

**🔁 Flow:**
```sql
-- Step 1: Identify Slow Queries
-- Enable slow query log
SET GLOBAL slow_query_log = 'ON';
SET GLOBAL long_query_time = 1;  -- Log queries taking more than 1 second
SET GLOBAL log_queries_not_using_indexes = 'ON';

-- Check slow query log
SELECT 
    start_time,
    query_time,
    rows_examined,
    rows_sent,
    sql_text
FROM mysql.slow_log
WHERE start_time >= DATE_SUB(NOW(), INTERVAL 1 HOUR)
ORDER BY query_time DESC
LIMIT 10;

-- Step 2: Analyze Query Performance
-- Use EXPLAIN to analyze slow queries
EXPLAIN 
SELECT 
    d.name as department_name,
    COUNT(e.id) as employee_count,
    AVG(e.salary) as avg_salary,
    SUM(e.salary) as total_salary
FROM departments d
LEFT JOIN employees e ON d.id = e.department_id
WHERE e.active = 1
GROUP BY d.id, d.name
ORDER BY total_salary DESC;

-- Step 3: Monitor System Resources
-- Check CPU and memory usage
SHOW PROCESSLIST;

-- Check table sizes
SELECT 
    table_name,
    table_rows,
    data_length,
    index_length,
    (data_length + index_length) as total_size
FROM information_schema.tables 
WHERE table_schema = 'your_database'
ORDER BY total_size DESC;

-- Step 4: Identify Missing Indexes
-- Find queries not using indexes
SELECT 
    sql_text,
    COUNT(*) as execution_count,
    AVG(query_time) as avg_time
FROM mysql.slow_log
WHERE start_time >= DATE_SUB(NOW(), INTERVAL 1 HOUR)
    AND sql_text LIKE '%SELECT%'
GROUP BY sql_text
HAVING avg_time > 2
ORDER BY avg_time DESC;

-- Step 5: Create Performance Monitoring Table
CREATE TABLE dashboard_performance_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    query_name VARCHAR(100),
    execution_time DECIMAL(10,4),
    rows_examined INT,
    rows_returned INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id INT,
    session_id VARCHAR(100)
);

-- Step 6: Optimize Dashboard Queries
-- Original slow query
SELECT 
    d.name as department_name,
    COUNT(e.id) as employee_count,
    AVG(e.salary) as avg_salary
FROM departments d
LEFT JOIN employees e ON d.id = e.department_id
WHERE e.active = 1
GROUP BY d.id, d.name;

-- Optimized version with proper indexes
CREATE INDEX idx_employees_dept_active ON employees(department_id, active);
CREATE INDEX idx_employees_salary ON employees(salary);

-- Optimized query
SELECT 
    d.name as department_name,
    COUNT(e.id) as employee_count,
    AVG(e.salary) as avg_salary
FROM departments d
INNER JOIN employees e ON d.id = e.department_id
WHERE e.active = 1
GROUP BY d.id, d.name;

-- Step 7: Use Materialized Views for Complex Aggregations
-- Create materialized view for dashboard data
CREATE TABLE dashboard_summary (
    department_id INT,
    department_name VARCHAR(100),
    employee_count INT,
    avg_salary DECIMAL(10,2),
    total_salary DECIMAL(12,2),
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (department_id)
);

-- Populate materialized view
INSERT INTO dashboard_summary (department_id, department_name, employee_count, avg_salary, total_salary)
SELECT 
    d.id,
    d.name,
    COUNT(e.id),
    AVG(e.salary),
    SUM(e.salary)
FROM departments d
LEFT JOIN employees e ON d.id = e.department_id AND e.active = 1
GROUP BY d.id, d.name
ON DUPLICATE KEY UPDATE
    employee_count = VALUES(employee_count),
    avg_salary = VALUES(avg_salary),
    total_salary = VALUES(total_salary),
    last_updated = NOW();

-- Fast dashboard query using materialized view
SELECT * FROM dashboard_summary ORDER BY total_salary DESC;

-- Step 8: Implement Query Caching
-- Create cache table
CREATE TABLE dashboard_cache (
    cache_key VARCHAR(100) PRIMARY KEY,
    cache_value JSON,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP
);

-- Cache dashboard data
INSERT INTO dashboard_cache (cache_key, cache_value, expires_at)
VALUES (
    'department_summary',
    JSON_OBJECT(
        'departments', JSON_ARRAY(
            JSON_OBJECT('name', 'Engineering', 'count', 50, 'avg_salary', 75000),
            JSON_OBJECT('name', 'Sales', 'count', 30, 'avg_salary', 65000)
        ),
        'generated_at', NOW()
    ),
    DATE_ADD(NOW(), INTERVAL 5 MINUTE)
);

-- Step 9: Optimize JOIN Operations
-- Identify expensive JOINs
EXPLAIN 
SELECT 
    c.name as customer_name,
    COUNT(o.id) as order_count,
    SUM(o.total_amount) as total_spent
FROM customers c
LEFT JOIN orders o ON c.id = o.customer_id
LEFT JOIN order_items oi ON o.id = oi.order_id
LEFT JOIN products p ON oi.product_id = p.id
WHERE o.order_date >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)
GROUP BY c.id, c.name;

-- Optimize with proper indexes and query structure
CREATE INDEX idx_orders_customer_date ON orders(customer_id, order_date);
CREATE INDEX idx_order_items_order ON order_items(order_id);

-- Optimized query
SELECT 
    c.name as customer_name,
    COUNT(o.id) as order_count,
    SUM(o.total_amount) as total_spent
FROM customers c
INNER JOIN orders o ON c.id = o.customer_id
WHERE o.order_date >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)
GROUP BY c.id, c.name;

-- Step 10: Implement Pagination
-- Original query without pagination
SELECT * FROM employees ORDER BY salary DESC;

-- Optimized with pagination
SELECT * FROM employees 
ORDER BY salary DESC 
LIMIT 20 OFFSET 0;  -- First page

SELECT * FROM employees 
ORDER BY salary DESC 
LIMIT 20 OFFSET 20;  -- Second page

-- Step 11: Use CTEs for Complex Queries
-- Complex dashboard query
WITH department_stats AS (
    SELECT 
        department_id,
        COUNT(*) as employee_count,
        AVG(salary) as avg_salary
    FROM employees
    WHERE active = 1
    GROUP BY department_id
),
top_departments AS (
    SELECT 
        d.name,
        ds.employee_count,
        ds.avg_salary
    FROM departments d
    INNER JOIN department_stats ds ON d.id = ds.department_id
    WHERE ds.employee_count > 10
)
SELECT * FROM top_departments ORDER BY avg_salary DESC;

-- Step 12: Monitor Query Performance Over Time
-- Create performance tracking
CREATE TABLE query_performance_trend (
    id INT AUTO_INCREMENT PRIMARY KEY,
    query_name VARCHAR(100),
    avg_execution_time DECIMAL(10,4),
    max_execution_time DECIMAL(10,4),
    execution_count INT,
    recorded_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Track performance daily
INSERT INTO query_performance_trend (query_name, avg_execution_time, max_execution_time, execution_count, recorded_date)
SELECT 
    'dashboard_department_summary',
    AVG(execution_time),
    MAX(execution_time),
    COUNT(*),
    CURDATE()
FROM dashboard_performance_log
WHERE query_name = 'dashboard_department_summary'
    AND created_at >= CURDATE();

-- Step 13: Implement Connection Pooling
-- Monitor connection usage
SHOW STATUS LIKE 'Threads_connected';
SHOW STATUS LIKE 'Max_used_connections';

-- Check for connection leaks
SELECT 
    user,
    host,
    db,
    command,
    time,
    state,
    info
FROM information_schema.processlist
WHERE command != 'Sleep'
ORDER BY time DESC;

-- Step 14: Optimize Database Configuration
-- Check current settings
SHOW VARIABLES LIKE 'innodb_buffer_pool_size';
SHOW VARIABLES LIKE 'query_cache_size';
SHOW VARIABLES LIKE 'tmp_table_size';

-- Optimize settings for dashboard queries
SET GLOBAL innodb_buffer_pool_size = 1073741824;  -- 1GB
SET GLOBAL query_cache_size = 67108864;  -- 64MB
SET GLOBAL tmp_table_size = 67108864;  -- 64MB

-- Step 15: Implement Read Replicas
-- Route dashboard queries to read replica
-- Application level: Use read replica for SELECT queries
-- Monitor replica lag
SHOW SLAVE STATUS;

-- Step 16: Use Query Hints for Optimization
-- Force index usage
SELECT /*+ INDEX(employees idx_employees_dept_active) */
    d.name,
    COUNT(e.id) as employee_count
FROM departments d
INNER JOIN employees e ON d.id = e.department_id
WHERE e.active = 1
GROUP BY d.id, d.name;

-- Step 17: Implement Real-time Monitoring
-- Create alerting system
CREATE TABLE performance_alerts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    alert_type ENUM('slow_query', 'high_cpu', 'memory_usage'),
    message TEXT,
    severity ENUM('low', 'medium', 'high', 'critical'),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    resolved_at TIMESTAMP NULL
);

-- Insert alert for slow queries
INSERT INTO performance_alerts (alert_type, message, severity)
SELECT 
    'slow_query',
    CONCAT('Query "', query_name, '" taking ', AVG(execution_time), ' seconds'),
    CASE 
        WHEN AVG(execution_time) > 5 THEN 'critical'
        WHEN AVG(execution_time) > 2 THEN 'high'
        WHEN AVG(execution_time) > 1 THEN 'medium'
        ELSE 'low'
    END
FROM dashboard_performance_log
WHERE created_at >= DATE_SUB(NOW(), INTERVAL 1 HOUR)
GROUP BY query_name
HAVING AVG(execution_time) > 1;

-- Step 18: Implement A/B Testing for Query Optimization
-- Test different query approaches
-- Compare performance between original and optimized queries
-- Monitor user experience metrics

-- Step 19: Create Performance Dashboard
-- Monitor key metrics
SELECT 
    'Total Queries' as metric,
    COUNT(*) as value
FROM dashboard_performance_log
WHERE created_at >= DATE_SUB(NOW(), INTERVAL 1 HOUR)

UNION ALL

SELECT 
    'Avg Response Time' as metric,
    AVG(execution_time) as value
FROM dashboard_performance_log
WHERE created_at >= DATE_SUB(NOW(), INTERVAL 1 HOUR)

UNION ALL

SELECT 
    'Slow Queries (>2s)' as metric,
    COUNT(*) as value
FROM dashboard_performance_log
WHERE created_at >= DATE_SUB(NOW(), INTERVAL 1 HOUR)
    AND execution_time > 2;

-- Step 20: Best Practices Summary
-- 1. Monitor and log all dashboard queries
-- 2. Use EXPLAIN to analyze query plans
-- 3. Create appropriate indexes
-- 4. Implement materialized views for complex aggregations
-- 5. Use caching for frequently accessed data
-- 6. Implement pagination for large result sets
-- 7. Optimize JOIN operations
-- 8. Use read replicas for heavy read workloads
-- 9. Monitor system resources
-- 10. Set up alerting for performance issues
```

2. Write a query to optimize a slow dashboard report aggregating sales data across multiple dimensions. _(Asked in HCL)_

**🧩 Foundation:** Optimize complex dashboard reports by using materialized views, proper indexing, and query restructuring to improve performance.

**⚙️ Function:** Transform slow multi-dimensional aggregations into fast, efficient queries for real-time dashboard reporting.

**🔁 Flow:**
```sql
-- Original slow dashboard query
SELECT 
    d.name as department_name,
    r.name as region_name,
    p.category as product_category,
    DATE_FORMAT(s.sale_date, '%Y-%m') as month,
    COUNT(s.id) as sales_count,
    SUM(s.total_amount) as total_revenue,
    AVG(s.total_amount) as avg_sale_amount,
    COUNT(DISTINCT s.customer_id) as unique_customers
FROM sales s
LEFT JOIN departments d ON s.department_id = d.id
LEFT JOIN regions r ON s.region_id = r.id
LEFT JOIN products p ON s.product_id = p.id
WHERE s.sale_date >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH)
GROUP BY d.id, d.name, r.id, r.name, p.category, DATE_FORMAT(s.sale_date, '%Y-%m')
ORDER BY total_revenue DESC;

-- Step 1: Create Materialized View for Pre-aggregated Data
CREATE TABLE sales_dashboard_summary (
    department_id INT,
    department_name VARCHAR(100),
    region_id INT,
    region_name VARCHAR(100),
    product_category VARCHAR(50),
    sale_month VARCHAR(7),
    sales_count INT,
    total_revenue DECIMAL(15,2),
    avg_sale_amount DECIMAL(10,2),
    unique_customers INT,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (department_id, region_id, product_category, sale_month),
    INDEX idx_revenue (total_revenue DESC),
    INDEX idx_month (sale_month)
);

-- Step 2: Populate Materialized View
INSERT INTO sales_dashboard_summary (
    department_id, department_name, region_id, region_name, 
    product_category, sale_month, sales_count, total_revenue, 
    avg_sale_amount, unique_customers
)
SELECT 
    d.id,
    d.name,
    r.id,
    r.name,
    p.category,
    DATE_FORMAT(s.sale_date, '%Y-%m'),
    COUNT(s.id),
    SUM(s.total_amount),
    AVG(s.total_amount),
    COUNT(DISTINCT s.customer_id)
FROM sales s
INNER JOIN departments d ON s.department_id = d.id
INNER JOIN regions r ON s.region_id = r.id
INNER JOIN products p ON s.product_id = p.id
WHERE s.sale_date >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH)
GROUP BY d.id, d.name, r.id, r.name, p.category, DATE_FORMAT(s.sale_date, '%Y-%m')
ON DUPLICATE KEY UPDATE
    sales_count = VALUES(sales_count),
    total_revenue = VALUES(total_revenue),
    avg_sale_amount = VALUES(avg_sale_amount),
    unique_customers = VALUES(unique_customers),
    last_updated = NOW();

-- Step 3: Optimized Dashboard Query Using Materialized View
SELECT 
    department_name,
    region_name,
    product_category,
    sale_month,
    sales_count,
    total_revenue,
    avg_sale_amount,
    unique_customers
FROM sales_dashboard_summary
WHERE sale_month >= DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 12 MONTH), '%Y-%m')
ORDER BY total_revenue DESC;

-- Step 4: Create Incremental Update Procedure
DELIMITER //
CREATE PROCEDURE UpdateSalesDashboard()
BEGIN
    DECLARE last_update_date TIMESTAMP;
    
    -- Get last update time
    SELECT MAX(last_updated) INTO last_update_date 
    FROM sales_dashboard_summary;
    
    -- Update only new/changed data
    INSERT INTO sales_dashboard_summary (
        department_id, department_name, region_id, region_name, 
        product_category, sale_month, sales_count, total_revenue, 
        avg_sale_amount, unique_customers
    )
    SELECT 
        d.id,
        d.name,
        r.id,
        r.name,
        p.category,
        DATE_FORMAT(s.sale_date, '%Y-%m'),
        COUNT(s.id),
        SUM(s.total_amount),
        AVG(s.total_amount),
        COUNT(DISTINCT s.customer_id)
    FROM sales s
    INNER JOIN departments d ON s.department_id = d.id
    INNER JOIN regions r ON s.region_id = r.id
    INNER JOIN products p ON s.product_id = p.id
    WHERE s.sale_date >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH)
        AND s.created_at > last_update_date
    GROUP BY d.id, d.name, r.id, r.name, p.category, DATE_FORMAT(s.sale_date, '%Y-%m')
    ON DUPLICATE KEY UPDATE
        sales_count = VALUES(sales_count),
        total_revenue = VALUES(total_revenue),
        avg_sale_amount = VALUES(avg_sale_amount),
        unique_customers = VALUES(unique_customers),
        last_updated = NOW();
END //
DELIMITER ;

-- Step 5: Create Partitioned Summary Tables
CREATE TABLE sales_summary_partitioned (
    department_id INT,
    region_id INT,
    product_category VARCHAR(50),
    sale_month VARCHAR(7),
    sales_count INT,
    total_revenue DECIMAL(15,2),
    avg_sale_amount DECIMAL(10,2),
    unique_customers INT
) PARTITION BY RANGE (YEAR(STR_TO_DATE(sale_month, '%Y-%m'))) (
    PARTITION p2022 VALUES LESS THAN (2023),
    PARTITION p2023 VALUES LESS THAN (2024),
    PARTITION p2024 VALUES LESS THAN (2025)
);

-- Step 6: Optimized Queries for Different Dashboard Views
-- Top performing departments
SELECT 
    department_name,
    SUM(sales_count) as total_sales,
    SUM(total_revenue) as total_revenue,
    AVG(avg_sale_amount) as avg_sale_amount
FROM sales_dashboard_summary
WHERE sale_month >= DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 6 MONTH), '%Y-%m')
GROUP BY department_id, department_name
ORDER BY total_revenue DESC
LIMIT 10;

-- Regional performance
SELECT 
    region_name,
    SUM(sales_count) as total_sales,
    SUM(total_revenue) as total_revenue,
    COUNT(DISTINCT department_id) as departments_count
FROM sales_dashboard_summary
WHERE sale_month >= DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 3 MONTH), '%Y-%m')
GROUP BY region_id, region_name
ORDER BY total_revenue DESC;

-- Monthly trends
SELECT 
    sale_month,
    SUM(sales_count) as total_sales,
    SUM(total_revenue) as total_revenue,
    AVG(avg_sale_amount) as avg_sale_amount
FROM sales_dashboard_summary
WHERE sale_month >= DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 12 MONTH), '%Y-%m')
GROUP BY sale_month
ORDER BY sale_month;

-- Step 7: Create Caching Strategy
CREATE TABLE dashboard_cache (
    cache_key VARCHAR(100) PRIMARY KEY,
    cache_value JSON,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP
);

-- Cache top performers
INSERT INTO dashboard_cache (cache_key, cache_value, expires_at)
VALUES (
    'top_departments',
    JSON_OBJECT(
        'departments', JSON_ARRAY(
            JSON_OBJECT('name', 'Sales', 'revenue', 1000000),
            JSON_OBJECT('name', 'Engineering', 'revenue', 800000)
        ),
        'generated_at', NOW()
    ),
    DATE_ADD(NOW(), INTERVAL 1 HOUR)
);

-- Step 8: Use CTEs for Complex Aggregations
WITH monthly_totals AS (
    SELECT 
        sale_month,
        SUM(sales_count) as total_sales,
        SUM(total_revenue) as total_revenue
    FROM sales_dashboard_summary
    WHERE sale_month >= DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 12 MONTH), '%Y-%m')
    GROUP BY sale_month
),
running_totals AS (
    SELECT 
        sale_month,
        total_sales,
        total_revenue,
        SUM(total_revenue) OVER (ORDER BY sale_month) as cumulative_revenue
    FROM monthly_totals
)
SELECT * FROM running_totals ORDER BY sale_month;

-- Step 9: Implement Real-time Updates
-- Create trigger for real-time updates
DELIMITER //
CREATE TRIGGER sales_update_trigger
AFTER INSERT ON sales
FOR EACH ROW
BEGIN
    -- Update summary table in real-time
    INSERT INTO sales_dashboard_summary (
        department_id, department_name, region_id, region_name,
        product_category, sale_month, sales_count, total_revenue,
        avg_sale_amount, unique_customers
    )
    SELECT 
        d.id, d.name, r.id, r.name, p.category,
        DATE_FORMAT(NEW.sale_date, '%Y-%m'),
        1, NEW.total_amount, NEW.total_amount, 1
    FROM departments d, regions r, products p
    WHERE d.id = NEW.department_id 
        AND r.id = NEW.region_id 
        AND p.id = NEW.product_id
    ON DUPLICATE KEY UPDATE
        sales_count = sales_count + 1,
        total_revenue = total_revenue + NEW.total_amount,
        avg_sale_amount = total_revenue / sales_count,
        last_updated = NOW();
END //
DELIMITER ;

-- Step 10: Performance Monitoring
CREATE TABLE dashboard_performance (
    id INT AUTO_INCREMENT PRIMARY KEY,
    query_name VARCHAR(100),
    execution_time DECIMAL(10,4),
    rows_examined INT,
    rows_returned INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Monitor dashboard query performance
INSERT INTO dashboard_performance (query_name, execution_time, rows_examined, rows_returned)
VALUES (
    'sales_dashboard_summary',
    0.1250,
    1000,
    500
);

-- Step 11: Best Practices Implementation
-- 1. Use materialized views for complex aggregations
-- 2. Implement incremental updates
-- 3. Use appropriate indexing
-- 4. Implement caching strategies
-- 5. Monitor performance continuously
-- 6. Use partitioning for large datasets
-- 7. Implement real-time updates where needed
-- 8. Use CTEs for complex calculations
-- 9. Optimize query structure
-- 10. Implement proper error handling
```

3. You inherit a poorly designed database with redundant data. How would you refactor it without disrupting the application? _(Asked in HCL)_
4. Write a query to migrate data from a denormalized table to a normalized schema. _(Asked in HCL)_

---

## 9. SQL Security

### Basic Questions

1. How do you prevent SQL injection attacks in queries? _(Asked in TCS, Infosys)_

**🧩 Foundation:** SQL injection is prevented by using parameterized queries, prepared statements, and input validation.

**⚙️ Function:** Protect database from malicious SQL code injection through user inputs.

**🔁 Flow:**
```sql
-- Method 1: Using Prepared Statements (Recommended)
-- Java/JDBC Example
PreparedStatement ps = conn.prepareStatement("SELECT * FROM employees WHERE id = ?");
ps.setInt(1, userId);
ResultSet rs = ps.executeQuery();

-- MySQL Stored Procedure with Parameters
DELIMITER //
CREATE PROCEDURE GetEmployee(IN emp_id INT)
BEGIN
    SELECT * FROM employees WHERE id = emp_id;
END //
DELIMITER ;

-- Method 2: Input Validation
-- Validate numeric input
SELECT * FROM employees WHERE id = ? AND ? REGEXP '^[0-9]+$';

-- Method 3: Escape Special Characters
-- MySQL
SELECT * FROM employees WHERE name = ?;
-- Application: escape single quotes, semicolons, etc.

-- Method 4: Use ORM Frameworks
-- Hibernate/JPA automatically handles parameterization
@Query("SELECT e FROM Employee e WHERE e.id = :id")
Employee findById(@Param("id") Long id);

-- Method 5: Database Views
-- Create view to limit access
CREATE VIEW employee_public AS
SELECT id, name, department_id FROM employees WHERE active = 1;

-- Method 6: Stored Procedures Only
-- Force all access through stored procedures
GRANT EXECUTE ON PROCEDURE GetEmployee TO user1;
REVOKE SELECT ON employees FROM user1;

-- Method 7: Input Sanitization
-- Remove dangerous characters
UPDATE employees SET name = REPLACE(REPLACE(name, ';', ''), '--', '') WHERE id = ?;

-- Method 8: Parameter Binding Examples
-- MySQL
SET @emp_id = 1;
PREPARE stmt FROM 'SELECT * FROM employees WHERE id = ?';
EXECUTE stmt USING @emp_id;
DEALLOCATE PREPARE stmt;

-- PostgreSQL
SELECT * FROM employees WHERE id = $1;

-- SQL Server
SELECT * FROM employees WHERE id = @emp_id;

-- Method 9: Validation Functions
-- Create validation function
DELIMITER //
CREATE FUNCTION IsValidEmployeeId(emp_id INT) RETURNS BOOLEAN
BEGIN
    DECLARE valid BOOLEAN DEFAULT FALSE;
    IF emp_id > 0 AND emp_id <= 10000 THEN
        SET valid = TRUE;
    END IF;
    RETURN valid;
END //
DELIMITER ;

-- Use validation function
SELECT * FROM employees WHERE id = ? AND IsValidEmployeeId(id);

-- Method 10: Error Handling for Injection Attempts
-- Log suspicious patterns
CREATE TABLE security_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    query_pattern VARCHAR(255),
    ip_address VARCHAR(45),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Application level: Log suspicious patterns
-- Check for: UNION, DROP, DELETE, INSERT, UPDATE, etc.
```

2. What are the roles of GRANT and REVOKE in SQL? _(Asked in Capgemini)_

**🧩 Foundation:** GRANT gives permissions to users/roles; REVOKE removes permissions.

**⚙️ Function:** Control database access and security through permission management.

**🔁 Flow:**
```sql
-- Basic GRANT Examples
-- Grant SELECT permission on specific table
GRANT SELECT ON employees TO user1;

-- Grant multiple permissions
GRANT SELECT, INSERT, UPDATE ON employees TO user1;

-- Grant all permissions on table
GRANT ALL PRIVILEGES ON employees TO user1;

-- Grant permissions on specific columns
GRANT SELECT (id, name, email) ON employees TO user1;

-- Grant permissions on database
GRANT SELECT, INSERT, UPDATE, DELETE ON mydb.* TO user1;

-- Grant all privileges on database
GRANT ALL PRIVILEGES ON mydb.* TO user1;

-- Grant global privileges
GRANT SELECT ON *.* TO user1;

-- Grant with specific host
GRANT SELECT ON employees TO 'user1'@'localhost';

-- Grant with password
GRANT SELECT ON employees TO user1 IDENTIFIED BY 'password123';

-- Basic REVOKE Examples
-- Revoke specific permission
REVOKE DELETE ON employees FROM user1;

-- Revoke multiple permissions
REVOKE INSERT, UPDATE ON employees FROM user1;

-- Revoke all permissions on table
REVOKE ALL PRIVILEGES ON employees FROM user1;

-- Revoke permissions on specific columns
REVOKE SELECT (salary) ON employees FROM user1;

-- Revoke database permissions
REVOKE ALL PRIVILEGES ON mydb.* FROM user1;

-- Revoke global permissions
REVOKE SELECT ON *.* FROM user1;

-- Role-Based Access Control
-- Create roles
CREATE ROLE read_only_role;
CREATE ROLE write_role;
CREATE ROLE admin_role;

-- Grant permissions to roles
GRANT SELECT ON employees TO read_only_role;
GRANT SELECT, INSERT, UPDATE ON employees TO write_role;
GRANT ALL PRIVILEGES ON employees TO admin_role;

-- Assign roles to users
GRANT read_only_role TO user1;
GRANT write_role TO user2;
GRANT admin_role TO user3;

-- Revoke roles
REVOKE admin_role FROM user3;

-- Temporary Permissions
-- Grant with expiration (MySQL 8.0+)
GRANT SELECT ON employees TO user1 WITH GRANT OPTION;

-- Grant with specific time
-- Application level: Schedule permission removal

-- View Current Permissions
-- Show user privileges
SHOW GRANTS FOR user1;

-- Show current user privileges
SHOW GRANTS;

-- Check specific table permissions
SELECT * FROM information_schema.table_privileges 
WHERE table_name = 'employees' AND grantee = 'user1';

-- Security Best Practices
-- Principle of least privilege
GRANT SELECT ON employees TO user1;  -- Only what's needed

-- Regular permission audits
SELECT grantee, table_name, privilege_type 
FROM information_schema.table_privileges 
WHERE table_schema = 'mydb';

-- Remove unused permissions
REVOKE ALL PRIVILEGES ON employees FROM user1;
GRANT SELECT ON employees TO user1;  -- Grant only needed

-- Create application-specific users
CREATE USER 'app_user'@'%' IDENTIFIED BY 'strong_password';
GRANT SELECT, INSERT, UPDATE ON mydb.* TO 'app_user'@'%';

-- Restrict access by IP
GRANT SELECT ON employees TO 'user1'@'192.168.1.%';

-- Use SSL connections
GRANT SELECT ON employees TO 'user1'@'%' REQUIRE SSL;
```

### Basic Query Questions

1. Write a query to grant SELECT and UPDATE permissions to a user on the employees table. _(Asked in TCS)_

**🧩 Foundation:** Use GRANT statement to provide specific permissions to users.

**⚙️ Function:** Give users controlled access to read and modify employee data.

**🔁 Flow:**
```sql
-- Basic permission grant
GRANT SELECT, UPDATE ON employees TO user1;

-- Grant with specific host
GRANT SELECT, UPDATE ON employees TO 'user1'@'localhost';

-- Grant with password
GRANT SELECT, UPDATE ON employees TO user1 IDENTIFIED BY 'password123';

-- Grant on specific columns only
GRANT SELECT (id, name, email), UPDATE (email, phone) ON employees TO user1;

-- Grant with additional options
GRANT SELECT, UPDATE ON employees TO user1 WITH GRANT OPTION;

-- Grant to multiple users
GRANT SELECT, UPDATE ON employees TO user1, user2, user3;

-- Grant with role assignment
CREATE ROLE employee_editor;
GRANT SELECT, UPDATE ON employees TO employee_editor;
GRANT employee_editor TO user1;

-- Grant with time restrictions (application level)
-- Grant permissions and schedule removal

-- Grant with IP restrictions
GRANT SELECT, UPDATE ON employees TO 'user1'@'192.168.1.%';

-- Grant with SSL requirement
GRANT SELECT, UPDATE ON employees TO user1 REQUIRE SSL;

-- Verify granted permissions
SHOW GRANTS FOR user1;

-- Check specific table permissions
SELECT grantee, privilege_type, is_grantable 
FROM information_schema.table_privileges 
WHERE table_name = 'employees' AND grantee = 'user1';

-- Grant with database context
USE mydb;
GRANT SELECT, UPDATE ON employees TO user1;

-- Grant with schema specification
GRANT SELECT, UPDATE ON mydb.employees TO user1;

-- Grant with column-level security
GRANT SELECT (id, name, department_id), 
      UPDATE (email, phone, last_updated) 
ON employees TO user1;

-- Grant with audit logging
GRANT SELECT, UPDATE ON employees TO user1;
INSERT INTO audit_log (action, user_id, table_name, permissions) 
VALUES ('GRANT', 'user1', 'employees', 'SELECT, UPDATE');

-- Grant with conditions (application level)
-- Grant permissions based on user role or department

-- Grant with expiration (MySQL 8.0+)
GRANT SELECT, UPDATE ON employees TO user1;

-- Grant with resource limits
GRANT SELECT, UPDATE ON employees TO user1 
WITH MAX_QUERIES_PER_HOUR 1000 
WITH MAX_UPDATES_PER_HOUR 100;

-- Grant with connection limits
GRANT SELECT, UPDATE ON employees TO user1 
WITH MAX_USER_CONNECTIONS 10;

-- Grant with account lock/unlock
GRANT SELECT, UPDATE ON employees TO user1;
ALTER USER user1 ACCOUNT UNLOCK;

-- Grant with password expiration
GRANT SELECT, UPDATE ON employees TO user1 
PASSWORD EXPIRE INTERVAL 90 DAY;

-- Grant with SSL/TLS requirements
GRANT SELECT, UPDATE ON employees TO user1 REQUIRE SSL;

-- Grant with X509 certificate requirements
GRANT SELECT, UPDATE ON employees TO user1 REQUIRE X509;

-- Grant with specific cipher requirements
GRANT SELECT, UPDATE ON employees TO user1 REQUIRE CIPHER 'DHE-RSA-AES256-SHA';

-- Grant with connection count monitoring
GRANT SELECT, UPDATE ON employees TO user1;
-- Monitor: SELECT * FROM performance_schema.users WHERE USER = 'user1';
```

### Intermediate Questions

1. How do you implement row-level security in a database? _(Asked in Wipro)_

**🧩 Foundation:** Row-level security restricts access to specific rows based on user identity or context.

**⚙️ Function:** Control data access at the row level for enhanced security and data privacy.

**🔁 Flow:**
```sql
-- Method 1: Using Views (Universal)
-- Create view for user-specific data
CREATE VIEW my_employees AS
SELECT * FROM employees WHERE manager_id = CURRENT_USER_ID();

-- Create view for department-specific access
CREATE VIEW dept_employees AS
SELECT * FROM employees WHERE department_id = (
    SELECT department_id FROM employees WHERE id = CURRENT_USER_ID()
);

-- Method 2: PostgreSQL Row Level Security
-- Enable RLS on table
ALTER TABLE employees ENABLE ROW LEVEL SECURITY;

-- Create policy for managers
CREATE POLICY manager_policy ON employees
    FOR ALL
    USING (manager_id = current_setting('app.current_user_id')::int);

-- Create policy for department access
CREATE POLICY dept_policy ON employees
    FOR SELECT
    USING (department_id = (
        SELECT department_id FROM employees WHERE id = current_setting('app.current_user_id')::int
    ));

-- Method 3: Application-Level Filtering
-- Java/Application code
String query = "SELECT * FROM employees WHERE department_id = ?";
PreparedStatement ps = conn.prepareStatement(query);
ps.setInt(1, getCurrentUserDepartmentId());

-- Method 4: Stored Procedures with Context
DELIMITER //
CREATE PROCEDURE GetMyEmployees()
BEGIN
    DECLARE user_dept INT;
    SELECT department_id INTO user_dept 
    FROM employees WHERE id = CURRENT_USER_ID();
    
    SELECT * FROM employees WHERE department_id = user_dept;
END //
DELIMITER ;

-- Method 5: Column-Based Security
-- Add security columns to tables
ALTER TABLE employees ADD COLUMN access_level ENUM('public', 'private', 'confidential');
ALTER TABLE employees ADD COLUMN owner_id INT;

-- Create filtered view
CREATE VIEW secure_employees AS
SELECT * FROM employees 
WHERE access_level = 'public' 
   OR owner_id = CURRENT_USER_ID()
   OR department_id = (SELECT department_id FROM employees WHERE id = CURRENT_USER_ID());

-- Method 6: Time-Based Security
-- Add time-based access control
CREATE VIEW time_restricted_employees AS
SELECT * FROM employees 
WHERE (CURRENT_TIME BETWEEN '09:00:00' AND '17:00:00')
  AND department_id = (SELECT department_id FROM employees WHERE id = CURRENT_USER_ID());

-- Method 7: Multi-Tenant Security
-- Add tenant_id to all tables
ALTER TABLE employees ADD COLUMN tenant_id INT;

-- Create tenant-specific view
CREATE VIEW tenant_employees AS
SELECT * FROM employees WHERE tenant_id = CURRENT_TENANT_ID();

-- Method 8: Role-Based Row Security
-- Create role-specific views
CREATE VIEW hr_employees AS
SELECT * FROM employees WHERE 'HR_ROLE' = ANY(CURRENT_USER_ROLES());

CREATE VIEW manager_employees AS
SELECT * FROM employees WHERE manager_id = CURRENT_USER_ID();

-- Method 9: Data Masking
-- Mask sensitive data based on user role
CREATE VIEW masked_employees AS
SELECT 
    id,
    name,
    CASE 
        WHEN 'HR_ROLE' = ANY(CURRENT_USER_ROLES()) THEN salary
        ELSE NULL 
    END as salary,
    department_id
FROM employees;

-- Method 10: Audit Trail with RLS
-- Create audit table
CREATE TABLE access_audit (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    table_name VARCHAR(50),
    action VARCHAR(20),
    rows_accessed INT,
    access_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create trigger for audit
DELIMITER //
CREATE TRIGGER employee_access_audit
AFTER SELECT ON employees
FOR EACH ROW
BEGIN
    INSERT INTO access_audit (user_id, table_name, action, rows_accessed)
    VALUES (CURRENT_USER_ID(), 'employees', 'SELECT', 1);
END //
DELIMITER ;

-- Method 11: Dynamic Row Filtering
-- Use session variables for dynamic filtering
SET @user_department = (SELECT department_id FROM employees WHERE id = CURRENT_USER_ID());

CREATE VIEW dynamic_employees AS
SELECT * FROM employees WHERE department_id = @user_department;

-- Method 12: Hierarchical Security
-- Implement manager-subordinate hierarchy
CREATE VIEW hierarchical_employees AS
WITH RECURSIVE emp_hierarchy AS (
    SELECT id, name, manager_id, 1 as level
    FROM employees WHERE id = CURRENT_USER_ID()
    
    UNION ALL
    
    SELECT e.id, e.name, e.manager_id, eh.level + 1
    FROM employees e
    JOIN emp_hierarchy eh ON e.manager_id = eh.id
    WHERE eh.level < 3
)
SELECT e.* FROM employees e
JOIN emp_hierarchy eh ON e.id = eh.id;
```

2. What is the purpose of database roles and privileges? _(Asked in Accenture)_

**🧩 Foundation:** Roles group privileges for easier management and assignment to users.

**⚙️ Function:** Simplify permission management and enforce security policies through role-based access control.

**🔁 Flow:**
```sql
-- Creating and Managing Roles
-- Create basic roles
CREATE ROLE read_only_role;
CREATE ROLE write_role;
CREATE ROLE admin_role;
CREATE ROLE hr_role;
CREATE ROLE manager_role;

-- Grant privileges to roles
GRANT SELECT ON employees TO read_only_role;
GRANT SELECT, INSERT, UPDATE ON employees TO write_role;
GRANT ALL PRIVILEGES ON employees TO admin_role;
GRANT SELECT, UPDATE ON employees TO hr_role;
GRANT SELECT, UPDATE ON employees TO manager_role;

-- Assign roles to users
GRANT read_only_role TO user1;
GRANT write_role TO user2;
GRANT admin_role TO user3;
GRANT hr_role TO hr_user;
GRANT manager_role TO manager_user;

-- Role Hierarchy
-- Create hierarchical roles
CREATE ROLE junior_analyst;
CREATE ROLE senior_analyst;
CREATE ROLE lead_analyst;

-- Grant privileges in hierarchy
GRANT SELECT ON employees TO junior_analyst;
GRANT SELECT, INSERT ON employees TO senior_analyst;
GRANT SELECT, INSERT, UPDATE ON employees TO lead_analyst;

-- Inherit privileges
GRANT junior_analyst TO senior_analyst;
GRANT senior_analyst TO lead_analyst;

-- Application-Specific Roles
-- Create roles for different applications
CREATE ROLE web_app_role;
CREATE ROLE mobile_app_role;
CREATE ROLE reporting_role;
CREATE ROLE batch_process_role;

-- Grant application-specific privileges
GRANT SELECT, INSERT, UPDATE ON orders TO web_app_role;
GRANT SELECT ON products TO mobile_app_role;
GRANT SELECT ON sales_summary TO reporting_role;
GRANT SELECT, INSERT, UPDATE, DELETE ON temp_data TO batch_process_role;

-- Department-Based Roles
-- Create department-specific roles
CREATE ROLE sales_role;
CREATE ROLE finance_role;
CREATE ROLE engineering_role;
CREATE ROLE marketing_role;

-- Grant department-specific access
GRANT SELECT, INSERT, UPDATE ON sales_data TO sales_role;
GRANT SELECT, INSERT, UPDATE ON financial_data TO finance_role;
GRANT SELECT, INSERT, UPDATE ON project_data TO engineering_role;
GRANT SELECT, INSERT, UPDATE ON campaign_data TO marketing_role;

-- Time-Based Roles
-- Create roles with time restrictions
CREATE ROLE business_hours_role;
CREATE ROLE after_hours_role;

-- Grant time-based privileges
GRANT SELECT, INSERT, UPDATE ON employees TO business_hours_role;
GRANT SELECT ON employees TO after_hours_role;

-- Security Level Roles
-- Create security clearance roles
CREATE ROLE public_role;
CREATE ROLE internal_role;
CREATE ROLE confidential_role;
CREATE ROLE secret_role;

-- Grant security-level privileges
GRANT SELECT (id, name, department_id) ON employees TO public_role;
GRANT SELECT ON employees TO internal_role;
GRANT SELECT, UPDATE ON employees TO confidential_role;
GRANT ALL PRIVILEGES ON employees TO secret_role;

-- Role with Conditions
-- Create conditional roles (application level)
CREATE ROLE conditional_access_role;
GRANT SELECT ON employees TO conditional_access_role;

-- Application logic: Check conditions before allowing access

-- Role Inheritance and Composition
-- Create composite roles
CREATE ROLE data_analyst;
CREATE ROLE report_writer;
CREATE ROLE data_scientist;

-- Grant base privileges
GRANT SELECT ON employees TO data_analyst;
GRANT SELECT ON sales_data TO data_analyst;

-- Compose roles
GRANT data_analyst TO report_writer;
GRANT data_analyst TO data_scientist;
GRANT SELECT ON advanced_analytics TO data_scientist;

-- Role Management Commands
-- List all roles
SELECT role_name FROM information_schema.applicable_roles;

-- Show role privileges
SHOW GRANTS FOR 'read_only_role';

-- Show user roles
SHOW GRANTS FOR user1;

-- Revoke roles
REVOKE admin_role FROM user3;

-- Drop roles
DROP ROLE unused_role;

-- Role with Resource Limits
-- Create roles with resource constraints
CREATE ROLE limited_user;
GRANT SELECT ON employees TO limited_user;

-- Set resource limits (MySQL)
ALTER USER limited_user 
WITH MAX_QUERIES_PER_HOUR 100 
WITH MAX_CONNECTIONS_PER_HOUR 10;

-- Role with Connection Restrictions
-- Create roles with connection limits
CREATE ROLE remote_user;
GRANT SELECT ON employees TO remote_user;

-- Restrict connections
ALTER USER remote_user 
WITH MAX_USER_CONNECTIONS 5;

-- Role with SSL Requirements
-- Create secure roles
CREATE ROLE secure_user;
GRANT SELECT ON employees TO secure_user;

-- Require SSL
ALTER USER secure_user REQUIRE SSL;

-- Role Audit and Monitoring
-- Create audit table for role usage
CREATE TABLE role_audit (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    role_name VARCHAR(50),
    action VARCHAR(20),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Monitor role assignments
SELECT user_id, role_name, action, timestamp 
FROM role_audit 
WHERE timestamp >= DATE_SUB(NOW(), INTERVAL 1 DAY);

-- Role with Expiration
-- Create temporary roles
CREATE ROLE temp_access_role;
GRANT SELECT ON employees TO temp_access_role;

-- Application level: Schedule role removal

-- Role with Geographic Restrictions
-- Create location-based roles
CREATE ROLE us_office_role;
CREATE ROLE eu_office_role;

-- Grant location-specific access
GRANT SELECT ON us_employees TO us_office_role;
GRANT SELECT ON eu_employees TO eu_office_role;

-- Role with Data Sensitivity
-- Create sensitivity-based roles
CREATE ROLE pii_access_role;
CREATE ROLE non_pii_access_role;

-- Grant sensitivity-based privileges
GRANT SELECT ON employees_anonymized TO non_pii_access_role;
GRANT SELECT ON employees TO pii_access_role;
```

### Intermediate Query Questions

1. Write a query to implement row-level security for users accessing their own orders. _(Asked in Wipro)_

**🧩 Foundation:** Use views, stored procedures, or application logic to restrict users to their own data.

**⚙️ Function:** Ensure users can only access orders they created or are authorized to view.

**🔁 Flow:**
```sql
-- Method 1: Using Views
-- Create view for user-specific orders
CREATE VIEW my_orders AS
SELECT * FROM orders WHERE user_id = CURRENT_USER_ID();

-- Create view for customer-specific orders
CREATE VIEW customer_orders AS
SELECT * FROM orders WHERE customer_id = (
    SELECT customer_id FROM customers WHERE user_id = CURRENT_USER_ID()
);

-- Method 2: Stored Procedure with Security
DELIMITER //
CREATE PROCEDURE GetMyOrders()
BEGIN
    DECLARE user_customer_id INT;
    
    -- Get customer ID for current user
    SELECT customer_id INTO user_customer_id 
    FROM customers WHERE user_id = CURRENT_USER_ID();
    
    -- Return only user's orders
    SELECT * FROM orders WHERE customer_id = user_customer_id;
END //
DELIMITER ;

-- Method 3: Application-Level Security
-- Java/Application code
String query = "SELECT * FROM orders WHERE user_id = ?";
PreparedStatement ps = conn.prepareStatement(query);
ps.setInt(1, getCurrentUserId());

-- Method 4: PostgreSQL Row Level Security
-- Enable RLS on orders table
ALTER TABLE orders ENABLE ROW LEVEL SECURITY;

-- Create policy for user access
CREATE POLICY user_orders_policy ON orders
    FOR ALL
    USING (user_id = current_setting('app.current_user_id')::int);

-- Create policy for customer access
CREATE POLICY customer_orders_policy ON orders
    FOR SELECT
    USING (customer_id = (
        SELECT customer_id FROM customers 
        WHERE user_id = current_setting('app.current_user_id')::int
    ));

-- Method 5: Role-Based Order Access
-- Create role-specific views
CREATE VIEW manager_orders AS
SELECT o.* FROM orders o
JOIN employees e ON o.salesperson_id = e.id
WHERE e.manager_id = CURRENT_USER_ID();

CREATE VIEW salesperson_orders AS
SELECT * FROM orders WHERE salesperson_id = CURRENT_USER_ID();

-- Method 6: Time-Based Order Access
-- Create time-restricted view
CREATE VIEW recent_orders AS
SELECT * FROM orders 
WHERE user_id = CURRENT_USER_ID()
  AND order_date >= DATE_SUB(CURDATE(), INTERVAL 30 DAY);

-- Method 7: Status-Based Order Access
-- Create status-specific views
CREATE VIEW active_orders AS
SELECT * FROM orders 
WHERE user_id = CURRENT_USER_ID()
  AND status IN ('pending', 'processing', 'shipped');

CREATE VIEW completed_orders AS
SELECT * FROM orders 
WHERE user_id = CURRENT_USER_ID()
  AND status = 'completed';

-- Method 8: Department-Based Order Access
-- Create department-specific view
CREATE VIEW dept_orders AS
SELECT o.* FROM orders o
JOIN customers c ON o.customer_id = c.id
WHERE c.department_id = (
    SELECT department_id FROM employees WHERE id = CURRENT_USER_ID()
);

-- Method 9: Hierarchical Order Access
-- Create hierarchical view for managers
CREATE VIEW hierarchical_orders AS
WITH RECURSIVE emp_hierarchy AS (
    SELECT id, manager_id, 1 as level
    FROM employees WHERE id = CURRENT_USER_ID()
    
    UNION ALL
    
    SELECT e.id, e.manager_id, eh.level + 1
    FROM employees e
    JOIN emp_hierarchy eh ON e.manager_id = eh.id
    WHERE eh.level < 3
)
SELECT o.* FROM orders o
JOIN employees e ON o.salesperson_id = e.id
JOIN emp_hierarchy eh ON e.id = eh.id;

-- Method 10: Multi-Tenant Order Access
-- Create tenant-specific view
CREATE VIEW tenant_orders AS
SELECT o.* FROM orders o
JOIN customers c ON o.customer_id = c.id
WHERE c.tenant_id = CURRENT_TENANT_ID();

-- Method 11: Audit Trail with Order Access
-- Create audit table
CREATE TABLE order_access_audit (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    order_id INT,
    action VARCHAR(20),
    access_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ip_address VARCHAR(45)
);

-- Create trigger for audit
DELIMITER //
CREATE TRIGGER order_access_audit_trigger
AFTER SELECT ON orders
FOR EACH ROW
BEGIN
    INSERT INTO order_access_audit (user_id, order_id, action, ip_address)
    VALUES (CURRENT_USER_ID(), NEW.id, 'SELECT', USER());
END //
DELIMITER ;

-- Method 12: Conditional Order Access
-- Create conditional view based on user role
CREATE VIEW conditional_orders AS
SELECT o.* FROM orders o
WHERE CASE 
    WHEN 'ADMIN_ROLE' = ANY(CURRENT_USER_ROLES()) THEN TRUE
    WHEN 'MANAGER_ROLE' = ANY(CURRENT_USER_ROLES()) THEN 
        o.salesperson_id IN (
            SELECT id FROM employees WHERE manager_id = CURRENT_USER_ID()
        )
    ELSE o.user_id = CURRENT_USER_ID()
END;

-- Method 13: Geographic Order Access
-- Create location-based view
CREATE VIEW local_orders AS
SELECT o.* FROM orders o
JOIN customers c ON o.customer_id = c.id
WHERE c.region = (
    SELECT region FROM employees WHERE id = CURRENT_USER_ID()
);

-- Method 14: Data Masking for Orders
-- Create masked view for sensitive data
CREATE VIEW masked_orders AS
SELECT 
    o.id,
    o.order_date,
    o.status,
    CASE 
        WHEN 'FINANCE_ROLE' = ANY(CURRENT_USER_ROLES()) THEN o.total_amount
        ELSE NULL 
    END as total_amount,
    o.user_id
FROM orders o
WHERE o.user_id = CURRENT_USER_ID();

-- Method 15: Session-Based Order Access
-- Use session variables for dynamic filtering
SET @user_orders_only = TRUE;
SET @user_id = CURRENT_USER_ID();

CREATE VIEW session_orders AS
SELECT * FROM orders 
WHERE (@user_orders_only = FALSE OR user_id = @user_id);

-- Method 16: Order Access with Encryption
-- Create view for encrypted order data
CREATE VIEW encrypted_orders AS
SELECT 
    o.id,
    o.order_date,
    o.status,
    AES_DECRYPT(o.encrypted_amount, 'key') as total_amount,
    o.user_id
FROM orders o
WHERE o.user_id = CURRENT_USER_ID();

-- Method 17: Order Access with Data Retention
-- Create view respecting data retention policies
CREATE VIEW retained_orders AS
SELECT * FROM orders 
WHERE user_id = CURRENT_USER_ID()
  AND order_date >= DATE_SUB(CURDATE(), INTERVAL 7 YEAR);  -- 7-year retention

-- Method 18: Order Access with Performance Optimization
-- Create indexed view for better performance
CREATE VIEW indexed_orders AS
SELECT * FROM orders 
WHERE user_id = CURRENT_USER_ID()
  AND order_date >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR);

-- Create index on the view
CREATE INDEX idx_user_orders ON orders(user_id, order_date);

-- Method 19: Order Access with Real-time Updates
-- Create view with real-time data
CREATE VIEW realtime_orders AS
SELECT o.*, 
       CASE 
           WHEN o.status = 'pending' AND o.order_date < DATE_SUB(NOW(), INTERVAL 1 HOUR) 
           THEN 'delayed'
           ELSE o.status 
       END as enhanced_status
FROM orders o
WHERE o.user_id = CURRENT_USER_ID();

-- Method 20: Order Access with Compliance
-- Create GDPR-compliant view
CREATE VIEW gdpr_orders AS
SELECT 
    o.id,
    o.order_date,
    o.status,
    o.total_amount,
    CASE 
        WHEN c.consent_given = 1 THEN o.customer_id
        ELSE NULL 
    END as customer_id
FROM orders o
JOIN customers c ON o.customer_id = c.id
WHERE o.user_id = CURRENT_USER_ID()
  AND c.consent_given = 1;
```

### Advanced Questions

1. How do you secure sensitive data (e.g., passwords) in a database? _(Asked in Cognizant)_

**🧩 Foundation:** Use encryption, hashing, and access controls to protect sensitive data.

**⚙️ Function:** Ensure sensitive data is protected from unauthorized access and breaches.

**🔁 Flow:**
```sql
-- Method 1: Password Hashing (Application Level)
-- Use bcrypt, Argon2, or PBKDF2 in application
-- Never store plain text passwords

-- Method 2: Column-Level Encryption
-- Encrypt sensitive columns
ALTER TABLE customers ADD COLUMN ssn_encrypted VARBINARY(255);
ALTER TABLE customers ADD COLUMN credit_card_encrypted VARBINARY(255);

-- Encrypt data
UPDATE customers SET 
    ssn_encrypted = AES_ENCRYPT(ssn, 'encryption_key'),
    credit_card_encrypted = AES_ENCRYPT(credit_card, 'encryption_key')
WHERE id = 1;

-- Decrypt data (only for authorized users)
SELECT 
    id,
    name,
    AES_DECRYPT(ssn_encrypted, 'encryption_key') as ssn,
    AES_DECRYPT(credit_card_encrypted, 'encryption_key') as credit_card
FROM customers WHERE id = 1;

-- Method 3: Transparent Data Encryption (TDE)
-- Enable TDE at database level
-- MySQL: ALTER INSTANCE RELOAD KEYRING;
-- SQL Server: CREATE DATABASE ENCRYPTION KEY

-- Method 4: Data Masking
-- Create masked views
CREATE VIEW masked_customers AS
SELECT 
    id,
    name,
    CONCAT('***-**-', RIGHT(ssn, 4)) as ssn_masked,
    CONCAT('****-****-****-', RIGHT(credit_card, 4)) as card_masked,
    email
FROM customers;

-- Method 5: Field-Level Encryption
-- Encrypt specific fields
CREATE TABLE secure_data (
    id INT PRIMARY KEY,
    data_type VARCHAR(50),
    encrypted_value VARBINARY(255),
    encryption_key_id VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert encrypted data
INSERT INTO secure_data (data_type, encrypted_value, encryption_key_id)
VALUES ('ssn', AES_ENCRYPT('123-45-6789', 'key1'), 'key1');

-- Method 6: Key Management
-- Create key management table
CREATE TABLE encryption_keys (
    key_id VARCHAR(50) PRIMARY KEY,
    key_value VARBINARY(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE
);

-- Rotate encryption keys
UPDATE encryption_keys SET is_active = FALSE WHERE key_id = 'old_key';
INSERT INTO encryption_keys (key_id, key_value, expires_at)
VALUES ('new_key', AES_ENCRYPT('new_key_value', 'master_key'), DATE_ADD(NOW(), INTERVAL 1 YEAR));

-- Method 7: Access Control for Sensitive Data
-- Create role-based access
CREATE ROLE sensitive_data_role;
GRANT SELECT ON masked_customers TO sensitive_data_role;

-- Create procedure for controlled access
DELIMITER //
CREATE PROCEDURE GetCustomerSSN(IN customer_id INT, IN user_id INT)
BEGIN
    DECLARE user_role VARCHAR(50);
    
    -- Check user role
    SELECT role INTO user_role FROM users WHERE id = user_id;
    
    IF user_role = 'HR_MANAGER' OR user_role = 'ADMIN' THEN
        SELECT 
            id,
            name,
            AES_DECRYPT(ssn_encrypted, 'encryption_key') as ssn
        FROM customers WHERE id = customer_id;
    ELSE
        SELECT 'Access denied' as error;
    END IF;
END //
DELIMITER ;

-- Method 8: Audit Trail for Sensitive Data Access
-- Create audit table
CREATE TABLE sensitive_data_audit (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    table_name VARCHAR(50),
    record_id INT,
    action VARCHAR(20),
    access_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ip_address VARCHAR(45),
    user_agent TEXT
);

-- Create trigger for audit
DELIMITER //
CREATE TRIGGER sensitive_data_access_audit
AFTER SELECT ON customers
FOR EACH ROW
BEGIN
    INSERT INTO sensitive_data_audit (user_id, table_name, record_id, action, ip_address)
    VALUES (CURRENT_USER_ID(), 'customers', NEW.id, 'SELECT', USER());
END //
DELIMITER ;

-- Method 9: Data Anonymization
-- Create anonymized views
CREATE VIEW anonymized_customers AS
SELECT 
    id,
    CONCAT('Customer_', LPAD(id, 6, '0')) as anonymized_name,
    CONCAT('***-**-', RIGHT(ssn, 4)) as ssn_anonymized,
    CONCAT(LEFT(email, 2), '***@', SUBSTRING_INDEX(email, '@', -1)) as email_anonymized,
    region,
    created_at
FROM customers;

-- Method 10: Encryption at Rest
-- Use database-level encryption
-- MySQL: innodb_encrypt_tables=ON
-- PostgreSQL: pgcrypto extension

-- Method 11: Secure Data Transmission
-- Use SSL/TLS for database connections
-- Require SSL for sensitive operations
GRANT SELECT ON customers TO user1 REQUIRE SSL;

-- Method 12: Data Classification
-- Add classification columns
ALTER TABLE customers ADD COLUMN data_classification ENUM('public', 'internal', 'confidential', 'restricted');

-- Create classification-based views
CREATE VIEW public_customers AS
SELECT id, name, region FROM customers WHERE data_classification = 'public';

CREATE VIEW confidential_customers AS
SELECT * FROM customers WHERE data_classification = 'confidential';

-- Method 13: Time-Based Access Control
-- Create time-restricted access
CREATE VIEW business_hours_customers AS
SELECT * FROM customers 
WHERE (CURRENT_TIME BETWEEN '09:00:00' AND '17:00:00')
  AND data_classification IN ('public', 'internal');

-- Method 14: Geographic Data Protection
-- Create region-based access
CREATE VIEW regional_customers AS
SELECT * FROM customers 
WHERE region = CURRENT_USER_REGION()
  AND data_classification != 'restricted';

-- Method 15: Data Retention Policies
-- Implement automatic data deletion
CREATE EVENT delete_old_sensitive_data
ON SCHEDULE EVERY 1 DAY
DO
DELETE FROM customers 
WHERE created_at < DATE_SUB(NOW(), INTERVAL 7 YEAR)
  AND data_classification = 'temporary';

-- Method 16: Secure Backup Encryption
-- Encrypt database backups
-- mysqldump --single-transaction --routines --triggers --encrypt-backup

-- Method 17: Data Loss Prevention
-- Create DLP triggers
DELIMITER //
CREATE TRIGGER dlp_email_check
BEFORE INSERT ON customers
FOR EACH ROW
BEGIN
    IF NEW.email LIKE '%@gmail.com' AND NEW.data_classification = 'confidential' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Confidential data cannot use personal email';
    END IF;
END //
DELIMITER ;

-- Method 18: Secure Data Export
-- Create secure export procedure
DELIMITER //
CREATE PROCEDURE ExportSecureData(IN user_id INT, IN export_type VARCHAR(50))
BEGIN
    DECLARE user_role VARCHAR(50);
    
    SELECT role INTO user_role FROM users WHERE id = user_id;
    
    IF user_role = 'ADMIN' THEN
        SELECT 
            id,
            name,
            AES_DECRYPT(ssn_encrypted, 'encryption_key') as ssn
        FROM customers;
    ELSE
        SELECT 'Insufficient privileges' as error;
    END IF;
END //
DELIMITER ;

-- Method 19: Data Integrity Checks
-- Create integrity verification
CREATE TABLE data_integrity_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    table_name VARCHAR(50),
    record_count INT,
    checksum_value VARCHAR(64),
    verified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Method 20: Compliance Monitoring
-- Create compliance audit table
CREATE TABLE compliance_audit (
    id INT AUTO_INCREMENT PRIMARY KEY,
    audit_type VARCHAR(50),
    findings TEXT,
    risk_level ENUM('low', 'medium', 'high', 'critical'),
    remediation_required BOOLEAN,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Monitor compliance
INSERT INTO compliance_audit (audit_type, findings, risk_level, remediation_required)
SELECT 
    'data_access',
    CONCAT('User ', user_id, ' accessed sensitive data'),
    CASE 
        WHEN COUNT(*) > 100 THEN 'high'
        ELSE 'medium'
    END,
    COUNT(*) > 50
FROM sensitive_data_audit
WHERE access_time >= DATE_SUB(NOW(), INTERVAL 1 DAY)
GROUP BY user_id;
```

### Advanced Query Questions

1. Write a query to encrypt sensitive data in the customers table (e.g., using database encryption functions). _(Asked in Cognizant)_

**🧩 Foundation:** Use database encryption functions to protect sensitive data at rest and in transit.

**⚙️ Function:** Encrypt sensitive customer data to prevent unauthorized access and comply with security regulations.

**🔁 Flow:**
```sql
-- Step 1: Add encrypted columns to customers table
ALTER TABLE customers ADD COLUMN ssn_encrypted VARBINARY(255);
ALTER TABLE customers ADD COLUMN credit_card_encrypted VARBINARY(255);
ALTER TABLE customers ADD COLUMN email_encrypted VARBINARY(255);
ALTER TABLE customers ADD COLUMN phone_encrypted VARBINARY(255);

-- Step 2: Encrypt existing sensitive data
UPDATE customers SET 
    ssn_encrypted = AES_ENCRYPT(ssn, 'encryption_key_2024'),
    credit_card_encrypted = AES_ENCRYPT(credit_card, 'encryption_key_2024'),
    email_encrypted = AES_ENCRYPT(email, 'encryption_key_2024'),
    phone_encrypted = AES_ENCRYPT(phone, 'encryption_key_2024')
WHERE ssn IS NOT NULL;

-- Step 3: Create secure view for authorized access
CREATE VIEW secure_customers AS
SELECT 
    id,
    name,
    AES_DECRYPT(ssn_encrypted, 'encryption_key_2024') as ssn,
    AES_DECRYPT(credit_card_encrypted, 'encryption_key_2024') as credit_card,
    AES_DECRYPT(email_encrypted, 'encryption_key_2024') as email,
    AES_DECRYPT(phone_encrypted, 'encryption_key_2024') as phone,
    region,
    created_at
FROM customers;

-- Step 4: Create masked view for general access
CREATE VIEW masked_customers AS
SELECT 
    id,
    name,
    CONCAT('***-**-', RIGHT(AES_DECRYPT(ssn_encrypted, 'encryption_key_2024'), 4)) as ssn_masked,
    CONCAT('****-****-****-', RIGHT(AES_DECRYPT(credit_card_encrypted, 'encryption_key_2024'), 4)) as card_masked,
    CONCAT(LEFT(AES_DECRYPT(email_encrypted, 'encryption_key_2024'), 2), '***@', 
           SUBSTRING_INDEX(AES_DECRYPT(email_encrypted, 'encryption_key_2024'), '@', -1)) as email_masked,
    CONCAT('***-***-', RIGHT(AES_DECRYPT(phone_encrypted, 'encryption_key_2024'), 4)) as phone_masked,
    region,
    created_at
FROM customers;

-- Step 5: Create stored procedure for secure data access
DELIMITER //
CREATE PROCEDURE GetCustomerSecureData(IN customer_id INT, IN user_role VARCHAR(50))
BEGIN
    DECLARE access_granted BOOLEAN DEFAULT FALSE;
    
    -- Check user role for access
    IF user_role IN ('ADMIN', 'HR_MANAGER', 'FINANCE_MANAGER') THEN
        SET access_granted = TRUE;
    END IF;
    
    IF access_granted THEN
        SELECT 
            id,
            name,
            AES_DECRYPT(ssn_encrypted, 'encryption_key_2024') as ssn,
            AES_DECRYPT(credit_card_encrypted, 'encryption_key_2024') as credit_card,
            AES_DECRYPT(email_encrypted, 'encryption_key_2024') as email,
            AES_DECRYPT(phone_encrypted, 'encryption_key_2024') as phone,
            region,
            created_at
        FROM customers WHERE id = customer_id;
    ELSE
        SELECT 'Access denied' as error;
    END IF;
END //
DELIMITER ;

-- Step 6: Create trigger for automatic encryption on insert
DELIMITER //
CREATE TRIGGER encrypt_customer_data_insert
BEFORE INSERT ON customers
FOR EACH ROW
BEGIN
    IF NEW.ssn IS NOT NULL THEN
        SET NEW.ssn_encrypted = AES_ENCRYPT(NEW.ssn, 'encryption_key_2024');
        SET NEW.ssn = NULL;  -- Remove plain text
    END IF;
    
    IF NEW.credit_card IS NOT NULL THEN
        SET NEW.credit_card_encrypted = AES_ENCRYPT(NEW.credit_card, 'encryption_key_2024');
        SET NEW.credit_card = NULL;  -- Remove plain text
    END IF;
    
    IF NEW.email IS NOT NULL THEN
        SET NEW.email_encrypted = AES_ENCRYPT(NEW.email, 'encryption_key_2024');
        SET NEW.email = NULL;  -- Remove plain text
    END IF;
    
    IF NEW.phone IS NOT NULL THEN
        SET NEW.phone_encrypted = AES_ENCRYPT(NEW.phone, 'encryption_key_2024');
        SET NEW.phone = NULL;  -- Remove plain text
    END IF;
END //
DELIMITER ;

-- Step 7: Create trigger for automatic encryption on update
DELIMITER //
CREATE TRIGGER encrypt_customer_data_update
BEFORE UPDATE ON customers
FOR EACH ROW
BEGIN
    IF NEW.ssn IS NOT NULL AND NEW.ssn != OLD.ssn THEN
        SET NEW.ssn_encrypted = AES_ENCRYPT(NEW.ssn, 'encryption_key_2024');
        SET NEW.ssn = NULL;
    END IF;
    
    IF NEW.credit_card IS NOT NULL AND NEW.credit_card != OLD.credit_card THEN
        SET NEW.credit_card_encrypted = AES_ENCRYPT(NEW.credit_card, 'encryption_key_2024');
        SET NEW.credit_card = NULL;
    END IF;
    
    IF NEW.email IS NOT NULL AND NEW.email != OLD.email THEN
        SET NEW.email_encrypted = AES_ENCRYPT(NEW.email, 'encryption_key_2024');
        SET NEW.email = NULL;
    END IF;
    
    IF NEW.phone IS NOT NULL AND NEW.phone != OLD.phone THEN
        SET NEW.phone_encrypted = AES_ENCRYPT(NEW.phone, 'encryption_key_2024');
        SET NEW.phone = NULL;
    END IF;
END //
DELIMITER ;

-- Step 8: Create key management table
CREATE TABLE encryption_keys (
    key_id VARCHAR(50) PRIMARY KEY,
    key_value VARBINARY(255),
    key_version INT DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE,
    key_type ENUM('AES', 'RSA', 'ECC') DEFAULT 'AES'
);

-- Insert encryption key
INSERT INTO encryption_keys (key_id, key_value, expires_at)
VALUES ('encryption_key_2024', AES_ENCRYPT('my_secret_key_2024', 'master_key'), 
        DATE_ADD(NOW(), INTERVAL 1 YEAR));

-- Step 9: Create audit trail for encrypted data access
CREATE TABLE encrypted_data_audit (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    customer_id INT,
    action VARCHAR(20),
    data_type VARCHAR(50),
    access_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ip_address VARCHAR(45),
    success BOOLEAN
);

-- Create trigger for audit
DELIMITER //
CREATE TRIGGER audit_encrypted_data_access
AFTER SELECT ON secure_customers
FOR EACH ROW
BEGIN
    INSERT INTO encrypted_data_audit (user_id, customer_id, action, data_type, ip_address, success)
    VALUES (CURRENT_USER_ID(), NEW.id, 'SELECT', 'encrypted_data', USER(), TRUE);
END //
DELIMITER ;

-- Step 10: Create data search functionality for encrypted data
DELIMITER //
CREATE PROCEDURE SearchEncryptedCustomers(IN search_term VARCHAR(255), IN user_role VARCHAR(50))
BEGIN
    DECLARE access_granted BOOLEAN DEFAULT FALSE;
    
    IF user_role IN ('ADMIN', 'HR_MANAGER') THEN
        SET access_granted = TRUE;
    END IF;
    
    IF access_granted THEN
        SELECT 
            id,
            name,
            AES_DECRYPT(ssn_encrypted, 'encryption_key_2024') as ssn,
            AES_DECRYPT(email_encrypted, 'encryption_key_2024') as email,
            region
        FROM customers 
        WHERE AES_DECRYPT(name, 'encryption_key_2024') LIKE CONCAT('%', search_term, '%')
           OR AES_DECRYPT(email_encrypted, 'encryption_key_2024') LIKE CONCAT('%', search_term, '%');
    ELSE
        SELECT 'Access denied' as error;
    END IF;
END //
DELIMITER ;

-- Step 11: Create key rotation procedure
DELIMITER //
CREATE PROCEDURE RotateEncryptionKeys()
BEGIN
    DECLARE new_key_id VARCHAR(50);
    DECLARE old_key_id VARCHAR(50);
    
    SET new_key_id = CONCAT('encryption_key_', YEAR(NOW()));
    SET old_key_id = CONCAT('encryption_key_', YEAR(NOW()) - 1);
    
    -- Create new key
    INSERT INTO encryption_keys (key_id, key_value, expires_at)
    VALUES (new_key_id, AES_ENCRYPT(CONCAT('new_key_', YEAR(NOW())), 'master_key'), 
            DATE_ADD(NOW(), INTERVAL 1 YEAR));
    
    -- Re-encrypt data with new key
    UPDATE customers SET 
        ssn_encrypted = AES_ENCRYPT(AES_DECRYPT(ssn_encrypted, old_key_id), new_key_id),
        credit_card_encrypted = AES_ENCRYPT(AES_DECRYPT(credit_card_encrypted, old_key_id), new_key_id),
        email_encrypted = AES_ENCRYPT(AES_DECRYPT(email_encrypted, old_key_id), new_key_id),
        phone_encrypted = AES_ENCRYPT(AES_DECRYPT(phone_encrypted, old_key_id), new_key_id);
    
    -- Deactivate old key
    UPDATE encryption_keys SET is_active = FALSE WHERE key_id = old_key_id;
END //
DELIMITER ;

-- Step 12: Create data export with encryption
DELIMITER //
CREATE PROCEDURE ExportEncryptedData(IN user_role VARCHAR(50), IN export_format VARCHAR(20))
BEGIN
    IF user_role = 'ADMIN' THEN
        SELECT 
            id,
            name,
            AES_DECRYPT(ssn_encrypted, 'encryption_key_2024') as ssn,
            AES_DECRYPT(credit_card_encrypted, 'encryption_key_2024') as credit_card,
            AES_DECRYPT(email_encrypted, 'encryption_key_2024') as email,
            AES_DECRYPT(phone_encrypted, 'encryption_key_2024') as phone,
            region,
            created_at
        FROM customers
        ORDER BY id;
    ELSE
        SELECT 'Insufficient privileges for export' as error;
    END IF;
END //
DELIMITER ;

-- Step 13: Create data integrity verification
DELIMITER //
CREATE PROCEDURE VerifyEncryptedDataIntegrity()
BEGIN
    SELECT 
        COUNT(*) as total_records,
        COUNT(ssn_encrypted) as encrypted_ssn_count,
        COUNT(credit_card_encrypted) as encrypted_card_count,
        COUNT(email_encrypted) as encrypted_email_count,
        COUNT(phone_encrypted) as encrypted_phone_count,
        CASE 
            WHEN COUNT(ssn_encrypted) = COUNT(*) THEN 'All data encrypted'
            ELSE 'Some data not encrypted'
        END as encryption_status
    FROM customers;
END //
DELIMITER ;

-- Step 14: Create backup encryption procedure
DELIMITER //
CREATE PROCEDURE CreateEncryptedBackup()
BEGIN
    -- Create backup table with encrypted data
    CREATE TABLE customers_backup_encrypted AS
    SELECT 
        id,
        name,
        ssn_encrypted,
        credit_card_encrypted,
        email_encrypted,
        phone_encrypted,
        region,
        created_at,
        'encryption_key_2024' as key_version
    FROM customers;
    
    SELECT 'Encrypted backup created successfully' as status;
END //
DELIMITER ;

-- Step 15: Create compliance reporting
DELIMITER //
CREATE PROCEDURE GenerateEncryptionComplianceReport()
BEGIN
    SELECT 
        'Encryption Compliance Report' as report_title,
        NOW() as generated_at,
        COUNT(*) as total_customers,
        COUNT(ssn_encrypted) as ssn_encrypted_count,
        COUNT(credit_card_encrypted) as card_encrypted_count,
        COUNT(email_encrypted) as email_encrypted_count,
        COUNT(phone_encrypted) as phone_encrypted_count,
        ROUND((COUNT(ssn_encrypted) / COUNT(*)) * 100, 2) as encryption_percentage,
        CASE 
            WHEN COUNT(ssn_encrypted) = COUNT(*) THEN 'COMPLIANT'
            ELSE 'NON-COMPLIANT'
        END as compliance_status
    FROM customers;
END //
DELIMITER ;
```

### Tough Questions

1. How would you design a database to comply with GDPR data protection requirements? _(Asked in Deloitte)_

**🧩 Foundation:** GDPR requires data protection, consent management, right to be forgotten, and data portability.

**⚙️ Function:** Design database architecture that supports GDPR compliance through data protection, consent tracking, and audit trails.

**🔁 Flow:**
```sql
-- Step 1: Create GDPR-compliant table structure
-- Add consent and data protection columns
ALTER TABLE customers ADD COLUMN consent_given BOOLEAN DEFAULT FALSE;
ALTER TABLE customers ADD COLUMN consent_date TIMESTAMP NULL;
ALTER TABLE customers ADD COLUMN consent_version VARCHAR(10);
ALTER TABLE customers ADD COLUMN data_retention_until DATE;
ALTER TABLE customers ADD COLUMN data_processing_purpose VARCHAR(100);
ALTER TABLE customers ADD COLUMN data_anonymized BOOLEAN DEFAULT FALSE;
ALTER TABLE customers ADD COLUMN right_to_forget_requested BOOLEAN DEFAULT FALSE;
ALTER TABLE customers ADD COLUMN right_to_forget_date TIMESTAMP NULL;

-- Step 2: Create consent management table
CREATE TABLE consent_management (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    consent_type ENUM('marketing', 'analytics', 'essential', 'third_party'),
    consent_given BOOLEAN,
    consent_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    consent_version VARCHAR(10),
    consent_text TEXT,
    ip_address VARCHAR(45),
    user_agent TEXT,
    consent_withdrawn BOOLEAN DEFAULT FALSE,
    withdrawal_date TIMESTAMP NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

-- Step 3: Create data processing log
CREATE TABLE data_processing_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    processing_purpose VARCHAR(100),
    data_categories TEXT,
    legal_basis VARCHAR(50),
    processing_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_controller VARCHAR(100),
    data_processor VARCHAR(100),
    retention_period INT,  -- in days
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

-- Step 4: Create data access audit trail
CREATE TABLE gdpr_audit_trail (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    action_type ENUM('access', 'modification', 'deletion', 'export', 'consent_change'),
    action_description TEXT,
    user_id INT,
    ip_address VARCHAR(45),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_before JSON,
    data_after JSON,
    legal_basis VARCHAR(50),
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

-- Step 5: Create data retention policy table
CREATE TABLE data_retention_policies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_category VARCHAR(50),
    retention_period_days INT,
    legal_basis VARCHAR(100),
    automatic_deletion BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert retention policies
INSERT INTO data_retention_policies (data_category, retention_period_days, legal_basis) VALUES
('marketing_data', 2555, 'Consent'),  -- 7 years
('transaction_data', 2555, 'Legal obligation'),  -- 7 years
('analytics_data', 1095, 'Legitimate interest'),  -- 3 years
('support_data', 730, 'Contract performance');  -- 2 years

-- Step 6: Create GDPR-compliant views
-- View for customers with valid consent
CREATE VIEW gdpr_compliant_customers AS
SELECT 
    c.id,
    c.name,
    c.email,
    c.region,
    c.consent_given,
    c.consent_date,
    c.data_retention_until,
    CASE 
        WHEN c.right_to_forget_requested = TRUE THEN 'ANONYMIZED'
        WHEN c.data_anonymized = TRUE THEN 'ANONYMIZED'
        ELSE 'ACTIVE'
    END as data_status
FROM customers c
WHERE c.consent_given = TRUE
  AND (c.data_retention_until IS NULL OR c.data_retention_until > CURDATE())
  AND c.right_to_forget_requested = FALSE;

-- Step 7: Create data anonymization procedure
DELIMITER //
CREATE PROCEDURE AnonymizeCustomerData(IN customer_id INT)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Anonymization failed';
    END;
    
    START TRANSACTION;
    
    -- Anonymize customer data
    UPDATE customers SET 
        name = CONCAT('Customer_', LPAD(customer_id, 6, '0')),
        email = CONCAT('customer_', customer_id, '@anonymized.com'),
        phone = CONCAT('***-***-', RIGHT(phone, 4)),
        address = 'ANONYMIZED',
        data_anonymized = TRUE,
        right_to_forget_date = NOW()
    WHERE id = customer_id;
    
    -- Log the anonymization
    INSERT INTO gdpr_audit_trail (customer_id, action_type, action_description, user_id)
    VALUES (customer_id, 'deletion', 'Data anonymized due to right to be forgotten', 1);
    
    COMMIT;
END //
DELIMITER ;

-- Step 8: Create data export procedure (Right to Data Portability)
DELIMITER //
CREATE PROCEDURE ExportCustomerData(IN customer_id INT)
BEGIN
    DECLARE customer_consent BOOLEAN;
    
    -- Check if customer has given consent
    SELECT consent_given INTO customer_consent 
    FROM customers WHERE id = customer_id;
    
    IF customer_consent = TRUE THEN
        -- Export customer data in JSON format
        SELECT 
            JSON_OBJECT(
                'customer_id', c.id,
                'name', c.name,
                'email', c.email,
                'phone', c.phone,
                'region', c.region,
                'consent_date', c.consent_date,
                'orders', (
                    SELECT JSON_ARRAYAGG(
                        JSON_OBJECT(
                            'order_id', o.id,
                            'order_date', o.order_date,
                            'total_amount', o.total_amount,
                            'status', o.status
                        )
                    )
                    FROM orders o WHERE o.customer_id = c.id
                ),
                'consent_history', (
                    SELECT JSON_ARRAYAGG(
                        JSON_OBJECT(
                            'consent_type', cm.consent_type,
                            'consent_given', cm.consent_given,
                            'consent_date', cm.consent_date,
                            'withdrawal_date', cm.withdrawal_date
                        )
                    )
                    FROM consent_management cm WHERE cm.customer_id = c.id
                )
            ) as customer_data_export
        FROM customers c
        WHERE c.id = customer_id;
        
        -- Log the export
        INSERT INTO gdpr_audit_trail (customer_id, action_type, action_description, user_id)
        VALUES (customer_id, 'export', 'Data exported for portability', 1);
    ELSE
        SELECT 'Customer has not given consent for data export' as error;
    END IF;
END //
DELIMITER ;

-- Step 9: Create consent withdrawal procedure
DELIMITER //
CREATE PROCEDURE WithdrawConsent(IN customer_id INT, IN consent_type VARCHAR(50))
BEGIN
    START TRANSACTION;
    
    -- Update consent management
    UPDATE consent_management SET 
        consent_withdrawn = TRUE,
        withdrawal_date = NOW()
    WHERE customer_id = customer_id 
      AND consent_type = consent_type
      AND consent_withdrawn = FALSE;
    
    -- Update customer consent status
    UPDATE customers SET 
        consent_given = FALSE,
        consent_date = NULL
    WHERE id = customer_id;
    
    -- Log the withdrawal
    INSERT INTO gdpr_audit_trail (customer_id, action_type, action_description, user_id)
    VALUES (customer_id, 'consent_change', CONCAT('Consent withdrawn for: ', consent_type), 1);
    
    COMMIT;
END //
DELIMITER ;

-- Step 10: Create data retention cleanup procedure
DELIMITER //
CREATE PROCEDURE CleanupExpiredData()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE customer_id INT;
    DECLARE data_category VARCHAR(50);
    DECLARE retention_days INT;
    
    -- Cursor for expired data
    DECLARE expired_data_cursor CURSOR FOR
    SELECT c.id, drp.data_category, drp.retention_period_days
    FROM customers c
    JOIN data_processing_log dpl ON c.id = dpl.customer_id
    JOIN data_retention_policies drp ON dpl.processing_purpose = drp.data_category
    WHERE c.consent_date < DATE_SUB(NOW(), INTERVAL drp.retention_period_days DAY)
      AND c.data_anonymized = FALSE;
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    OPEN expired_data_cursor;
    
    read_loop: LOOP
        FETCH expired_data_cursor INTO customer_id, data_category, retention_days;
        IF done THEN
            LEAVE read_loop;
        END IF;
        
        -- Anonymize expired data
        CALL AnonymizeCustomerData(customer_id);
        
    END LOOP;
    
    CLOSE expired_data_cursor;
END //
DELIMITER ;

-- Step 11: Create GDPR compliance monitoring
DELIMITER //
CREATE PROCEDURE GenerateGDPRComplianceReport()
BEGIN
    SELECT 
        'GDPR Compliance Report' as report_title,
        NOW() as generated_at,
        COUNT(*) as total_customers,
        COUNT(CASE WHEN consent_given = TRUE THEN 1 END) as customers_with_consent,
        COUNT(CASE WHEN consent_given = FALSE THEN 1 END) as customers_without_consent,
        COUNT(CASE WHEN data_anonymized = TRUE THEN 1 END) as anonymized_customers,
        COUNT(CASE WHEN right_to_forget_requested = TRUE THEN 1 END) as forget_requests,
        ROUND((COUNT(CASE WHEN consent_given = TRUE THEN 1 END) / COUNT(*)) * 100, 2) as consent_percentage,
        CASE 
            WHEN COUNT(CASE WHEN consent_given = TRUE THEN 1 END) = COUNT(*) THEN 'COMPLIANT'
            ELSE 'NON-COMPLIANT'
        END as gdpr_status
    FROM customers;
END //
DELIMITER ;

-- Step 12: Create data breach notification system
CREATE TABLE data_breach_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    breach_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    breach_description TEXT,
    affected_customers INT,
    data_categories_affected TEXT,
    notification_sent BOOLEAN DEFAULT FALSE,
    notification_date TIMESTAMP NULL,
    regulatory_report_filed BOOLEAN DEFAULT FALSE,
    report_date TIMESTAMP NULL
);

-- Step 13: Create data processing impact assessment
CREATE TABLE data_processing_impact (
    id INT AUTO_INCREMENT PRIMARY KEY,
    processing_purpose VARCHAR(100),
    risk_level ENUM('low', 'medium', 'high'),
    data_categories TEXT,
    legal_basis VARCHAR(50),
    retention_period INT,
    security_measures TEXT,
    assessment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Step 14: Create automated GDPR compliance checks
DELIMITER //
CREATE PROCEDURE CheckGDPRCompliance()
BEGIN
    -- Check for customers without consent
    SELECT 'Customers without consent' as issue, COUNT(*) as count
    FROM customers WHERE consent_given = FALSE;
    
    -- Check for expired data
    SELECT 'Expired data found' as issue, COUNT(*) as count
    FROM customers c
    JOIN data_retention_policies drp ON 1=1
    WHERE c.consent_date < DATE_SUB(NOW(), INTERVAL drp.retention_period_days DAY)
      AND c.data_anonymized = FALSE;
    
    -- Check for pending forget requests
    SELECT 'Pending forget requests' as issue, COUNT(*) as count
    FROM customers WHERE right_to_forget_requested = TRUE AND data_anonymized = FALSE;
    
    -- Check for missing audit trails
    SELECT 'Missing audit trails' as issue, COUNT(*) as count
    FROM customers c
    LEFT JOIN gdpr_audit_trail gat ON c.id = gat.customer_id
    WHERE gat.id IS NULL;
END //
DELIMITER ;

-- Step 15: Create data subject rights management
CREATE TABLE data_subject_rights (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    right_type ENUM('access', 'rectification', 'erasure', 'portability', 'objection'),
    request_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('pending', 'processing', 'completed', 'rejected'),
    completion_date TIMESTAMP NULL,
    response_text TEXT,
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

-- Step 16: Create automated data protection measures
DELIMITER //
CREATE EVENT gdpr_automated_cleanup
ON SCHEDULE EVERY 1 DAY
DO
BEGIN
    -- Run daily GDPR compliance checks
    CALL CleanupExpiredData();
    CALL CheckGDPRCompliance();
END //
DELIMITER ;

-- Step 17: Create GDPR training and awareness tracking
CREATE TABLE gdpr_training_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    training_type VARCHAR(50),
    completion_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    score DECIMAL(5,2),
    certification_expiry DATE
);

-- Step 18: Create data transfer safeguards
CREATE TABLE data_transfers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    transfer_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    destination_country VARCHAR(50),
    data_categories TEXT,
    legal_basis VARCHAR(50),
    safeguards_implemented TEXT,
    transfer_impact_assessment BOOLEAN
);

-- Step 19: Create privacy by design implementation
CREATE TABLE privacy_by_design (
    id INT AUTO_INCREMENT PRIMARY KEY,
    feature_name VARCHAR(100),
    privacy_controls TEXT,
    data_minimization BOOLEAN,
    purpose_limitation BOOLEAN,
    storage_limitation BOOLEAN,
    implementation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Step 20: Create GDPR incident response
CREATE TABLE gdpr_incidents (
    id INT AUTO_INCREMENT PRIMARY KEY,
    incident_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    incident_type VARCHAR(50),
    severity ENUM('low', 'medium', 'high', 'critical'),
    affected_data_subjects INT,
    containment_measures TEXT,
    resolution_date TIMESTAMP NULL,
    lessons_learned TEXT
);
```

### Tough Query Questions

1. Write a query to anonymize personal data in the customers table for GDPR compliance. _(Asked in Deloitte)_

**🧩 Foundation:** Anonymize personal data to comply with GDPR's right to be forgotten while maintaining data integrity.

**⚙️ Function:** Remove personally identifiable information while preserving data structure for analytics and compliance.

**🔁 Flow:**
```sql
-- Step 1: Create anonymization procedure
DELIMITER //
CREATE PROCEDURE AnonymizeCustomerDataGDPR(IN customer_id INT)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Anonymization failed';
    END;
    
    START TRANSACTION;
    
    -- Anonymize personal data
    UPDATE customers SET 
        name = CONCAT('Customer_', LPAD(customer_id, 6, '0')),
        email = CONCAT('customer_', customer_id, '@anonymized.com'),
        phone = CONCAT('***-***-', RIGHT(phone, 4)),
        address = 'ANONYMIZED',
        ssn = CONCAT('***-**-', RIGHT(ssn, 4)),
        credit_card = CONCAT('****-****-****-', RIGHT(credit_card, 4)),
        data_anonymized = TRUE,
        right_to_forget_date = NOW(),
        data_anonymized_at = NOW()
    WHERE id = customer_id;
    
    -- Log anonymization in audit trail
    INSERT INTO gdpr_audit_trail (customer_id, action_type, action_description, user_id, data_before, data_after)
    VALUES (
        customer_id, 
        'deletion', 
        'Data anonymized for GDPR compliance', 
        1,
        JSON_OBJECT('customer_id', customer_id, 'action', 'anonymization'),
        JSON_OBJECT('customer_id', customer_id, 'status', 'anonymized')
    );
    
    COMMIT;
END //
DELIMITER ;

-- Step 2: Create batch anonymization procedure
DELIMITER //
CREATE PROCEDURE BatchAnonymizeCustomers(IN batch_size INT)
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE customer_id INT;
    DECLARE processed_count INT DEFAULT 0;
    
    -- Cursor for customers requiring anonymization
    DECLARE anonymize_cursor CURSOR FOR
    SELECT id FROM customers 
    WHERE right_to_forget_requested = TRUE 
      AND data_anonymized = FALSE
    LIMIT batch_size;
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    OPEN anonymize_cursor;
    
    anonymize_loop: LOOP
        FETCH anonymize_cursor INTO customer_id;
        IF done THEN
            LEAVE anonymize_loop;
        END IF;
        
        CALL AnonymizeCustomerDataGDPR(customer_id);
        SET processed_count = processed_count + 1;
        
    END LOOP;
    
    CLOSE anonymize_cursor;
    
    SELECT CONCAT('Anonymized ', processed_count, ' customers') as result;
END //
DELIMITER ;

-- Step 3: Create selective anonymization procedure
DELIMITER //
CREATE PROCEDURE SelectiveAnonymization(
    IN data_type ENUM('all', 'name', 'email', 'phone', 'address', 'ssn', 'credit_card'),
    IN customer_id INT
)
BEGIN
    CASE data_type
        WHEN 'name' THEN
            UPDATE customers SET 
                name = CONCAT('Customer_', LPAD(customer_id, 6, '0')),
                data_anonymized_at = NOW()
            WHERE id = customer_id;
            
        WHEN 'email' THEN
            UPDATE customers SET 
                email = CONCAT('customer_', customer_id, '@anonymized.com'),
                data_anonymized_at = NOW()
            WHERE id = customer_id;
            
        WHEN 'phone' THEN
            UPDATE customers SET 
                phone = CONCAT('***-***-', RIGHT(phone, 4)),
                data_anonymized_at = NOW()
            WHERE id = customer_id;
            
        WHEN 'address' THEN
            UPDATE customers SET 
                address = 'ANONYMIZED',
                data_anonymized_at = NOW()
            WHERE id = customer_id;
            
        WHEN 'ssn' THEN
            UPDATE customers SET 
                ssn = CONCAT('***-**-', RIGHT(ssn, 4)),
                data_anonymized_at = NOW()
            WHERE id = customer_id;
            
        WHEN 'credit_card' THEN
            UPDATE customers SET 
                credit_card = CONCAT('****-****-****-', RIGHT(credit_card, 4)),
                data_anonymized_at = NOW()
            WHERE id = customer_id;
            
        WHEN 'all' THEN
            CALL AnonymizeCustomerDataGDPR(customer_id);
    END CASE;
    
    -- Log the selective anonymization
    INSERT INTO gdpr_audit_trail (customer_id, action_type, action_description, user_id)
    VALUES (customer_id, 'deletion', CONCAT('Selective anonymization: ', data_type), 1);
END //
DELIMITER ;

-- Step 4: Create anonymized view for analytics
CREATE VIEW anonymized_customers_analytics AS
SELECT 
    id,
    CASE 
        WHEN data_anonymized = TRUE THEN CONCAT('Customer_', LPAD(id, 6, '0'))
        ELSE name 
    END as customer_name,
    CASE 
        WHEN data_anonymized = TRUE THEN CONCAT('customer_', id, '@anonymized.com')
        ELSE email 
    END as customer_email,
    region,
    created_at,
    data_anonymized,
    data_anonymized_at
FROM customers;

-- Step 5: Create data retention-based anonymization
DELIMITER //
CREATE PROCEDURE AnonymizeExpiredData()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE customer_id INT;
    DECLARE retention_days INT;
    
    -- Cursor for expired data
    DECLARE expired_cursor CURSOR FOR
    SELECT c.id, drp.retention_period_days
    FROM customers c
    JOIN data_retention_policies drp ON 1=1
    WHERE c.consent_date < DATE_SUB(NOW(), INTERVAL drp.retention_period_days DAY)
      AND c.data_anonymized = FALSE;
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    OPEN expired_cursor;
    
    expired_loop: LOOP
        FETCH expired_cursor INTO customer_id, retention_days;
        IF done THEN
            LEAVE expired_loop;
        END IF;
        
        CALL AnonymizeCustomerDataGDPR(customer_id);
        
    END LOOP;
    
    CLOSE expired_cursor;
END //
DELIMITER ;

-- Step 6: Create anonymization with data preservation
DELIMITER //
CREATE PROCEDURE AnonymizeWithPreservation(IN customer_id INT)
BEGIN
    -- Create backup of original data
    INSERT INTO customer_data_backup (
        customer_id, original_name, original_email, original_phone,
        backup_date, backup_reason
    )
    SELECT 
        id, name, email, phone, NOW(), 'GDPR anonymization backup'
    FROM customers WHERE id = customer_id;
    
    -- Perform anonymization
    CALL AnonymizeCustomerDataGDPR(customer_id);
    
    SELECT 'Anonymization completed with data preservation' as status;
END //
DELIMITER ;

-- Step 7: Create reversible anonymization (for legal requirements)
DELIMITER //
CREATE PROCEDURE ReversibleAnonymization(IN customer_id INT)
BEGIN
    -- Store encrypted original data
    UPDATE customers SET 
        name_encrypted = AES_ENCRYPT(name, 'legal_key'),
        email_encrypted = AES_ENCRYPT(email, 'legal_key'),
        phone_encrypted = AES_ENCRYPT(phone, 'legal_key'),
        name = CONCAT('Customer_', LPAD(customer_id, 6, '0')),
        email = CONCAT('customer_', customer_id, '@anonymized.com'),
        phone = CONCAT('***-***-', RIGHT(phone, 4)),
        data_anonymized = TRUE,
        reversible_anonymization = TRUE
    WHERE id = customer_id;
    
    -- Log reversible anonymization
    INSERT INTO gdpr_audit_trail (customer_id, action_type, action_description, user_id)
    VALUES (customer_id, 'deletion', 'Reversible anonymization applied', 1);
END //
DELIMITER ;

-- Step 8: Create anonymization verification
DELIMITER //
CREATE PROCEDURE VerifyAnonymization(IN customer_id INT)
BEGIN
    SELECT 
        id,
        name,
        email,
        phone,
        data_anonymized,
        data_anonymized_at,
        CASE 
            WHEN name LIKE 'Customer_%' THEN 'Name anonymized'
            ELSE 'Name not anonymized'
        END as name_status,
        CASE 
            WHEN email LIKE '%@anonymized.com' THEN 'Email anonymized'
            ELSE 'Email not anonymized'
        END as email_status,
        CASE 
            WHEN phone LIKE '***-***-%' THEN 'Phone anonymized'
            ELSE 'Phone not anonymized'
        END as phone_status
    FROM customers WHERE id = customer_id;
END //
DELIMITER ;

-- Step 9: Create anonymization report
DELIMITER //
CREATE PROCEDURE GenerateAnonymizationReport()
BEGIN
    SELECT 
        'GDPR Anonymization Report' as report_title,
        NOW() as generated_at,
        COUNT(*) as total_customers,
        COUNT(CASE WHEN data_anonymized = TRUE THEN 1 END) as anonymized_customers,
        COUNT(CASE WHEN data_anonymized = FALSE THEN 1 END) as non_anonymized_customers,
        COUNT(CASE WHEN right_to_forget_requested = TRUE THEN 1 END) as forget_requests,
        COUNT(CASE WHEN right_to_forget_requested = TRUE AND data_anonymized = FALSE THEN 1 END) as pending_requests,
        ROUND((COUNT(CASE WHEN data_anonymized = TRUE THEN 1 END) / COUNT(*)) * 100, 2) as anonymization_percentage
    FROM customers;
END //
DELIMITER ;

-- Step 10: Create automated anonymization trigger
DELIMITER //
CREATE TRIGGER auto_anonymize_on_forget_request
AFTER UPDATE ON customers
FOR EACH ROW
BEGIN
    IF NEW.right_to_forget_requested = TRUE AND OLD.right_to_forget_requested = FALSE THEN
        -- Schedule anonymization (can be immediate or scheduled)
        INSERT INTO anonymization_queue (customer_id, priority, scheduled_time)
        VALUES (NEW.id, 'high', NOW());
    END IF;
END //
DELIMITER ;

-- Step 11: Create anonymization queue management
CREATE TABLE anonymization_queue (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    priority ENUM('low', 'medium', 'high', 'urgent'),
    scheduled_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    processed BOOLEAN DEFAULT FALSE,
    processed_at TIMESTAMP NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

-- Step 12: Create data minimization anonymization
DELIMITER //
CREATE PROCEDURE MinimizeCustomerData(IN customer_id INT)
BEGIN
    -- Keep only essential data, anonymize the rest
    UPDATE customers SET 
        name = CONCAT('Customer_', LPAD(customer_id, 6, '0')),
        email = NULL,  -- Remove email completely
        phone = NULL,  -- Remove phone completely
        address = 'ANONYMIZED',
        ssn = NULL,    -- Remove SSN completely
        credit_card = NULL,  -- Remove credit card completely
        data_minimized = TRUE,
        data_minimized_at = NOW()
    WHERE id = customer_id;
    
    -- Log data minimization
    INSERT INTO gdpr_audit_trail (customer_id, action_type, action_description, user_id)
    VALUES (customer_id, 'deletion', 'Data minimization applied', 1);
END //
DELIMITER ;

-- Step 13: Create purpose-based anonymization
DELIMITER //
CREATE PROCEDURE PurposeBasedAnonymization(IN customer_id INT, IN purpose VARCHAR(50))
BEGIN
    CASE purpose
        WHEN 'analytics' THEN
            -- Keep only data needed for analytics
            UPDATE customers SET 
                name = CONCAT('Customer_', LPAD(customer_id, 6, '0')),
                email = NULL,
                phone = NULL,
                ssn = NULL,
                credit_card = NULL,
                analytics_anonymized = TRUE
            WHERE id = customer_id;
            
        WHEN 'billing' THEN
            -- Keep billing-related data, anonymize personal data
            UPDATE customers SET 
                name = CONCAT('Customer_', LPAD(customer_id, 6, '0')),
                email = CONCAT('customer_', customer_id, '@anonymized.com'),
                phone = CONCAT('***-***-', RIGHT(phone, 4)),
                ssn = NULL,
                credit_card = CONCAT('****-****-****-', RIGHT(credit_card, 4)),
                billing_anonymized = TRUE
            WHERE id = customer_id;
            
        WHEN 'support' THEN
            -- Keep support-related data, anonymize sensitive data
            UPDATE customers SET 
                name = CONCAT('Customer_', LPAD(customer_id, 6, '0')),
                email = CONCAT('customer_', customer_id, '@anonymized.com'),
                phone = CONCAT('***-***-', RIGHT(phone, 4)),
                ssn = NULL,
                credit_card = NULL,
                support_anonymized = TRUE
            WHERE id = customer_id;
    END CASE;
    
    -- Log purpose-based anonymization
    INSERT INTO gdpr_audit_trail (customer_id, action_type, action_description, user_id)
    VALUES (customer_id, 'deletion', CONCAT('Purpose-based anonymization: ', purpose), 1);
END //
DELIMITER ;

-- Step 14: Create anonymization compliance check
DELIMITER //
CREATE PROCEDURE CheckAnonymizationCompliance()
BEGIN
    SELECT 
        'Anonymization Compliance Check' as check_type,
        NOW() as check_time,
        COUNT(*) as total_customers,
        COUNT(CASE WHEN right_to_forget_requested = TRUE AND data_anonymized = FALSE THEN 1 END) as non_compliant,
        COUNT(CASE WHEN right_to_forget_requested = TRUE AND data_anonymized = TRUE THEN 1 END) as compliant,
        CASE 
            WHEN COUNT(CASE WHEN right_to_forget_requested = TRUE AND data_anonymized = FALSE THEN 1 END) = 0 THEN 'COMPLIANT'
            ELSE 'NON-COMPLIANT'
        END as compliance_status
    FROM customers;
END //
DELIMITER ;

-- Step 15: Create anonymization monitoring
CREATE TABLE anonymization_monitoring (
    id INT AUTO_INCREMENT PRIMARY KEY,
    monitoring_date DATE DEFAULT CURDATE(),
    total_anonymizations INT DEFAULT 0,
    successful_anonymizations INT DEFAULT 0,
    failed_anonymizations INT DEFAULT 0,
    average_processing_time DECIMAL(10,2),
    compliance_score DECIMAL(5,2)
);

-- Insert daily monitoring data
INSERT INTO anonymization_monitoring (
    total_anonymizations, successful_anonymizations, failed_anonymizations, compliance_score
)
SELECT 
    COUNT(*) as total,
    COUNT(CASE WHEN data_anonymized = TRUE THEN 1 END) as successful,
    COUNT(CASE WHEN right_to_forget_requested = TRUE AND data_anonymized = FALSE THEN 1 END) as failed,
    ROUND((COUNT(CASE WHEN data_anonymized = TRUE THEN 1 END) / COUNT(*)) * 100, 2) as compliance_score
FROM customers;
```

### Situational / Real-world Questions

1. Your Java application exposes sensitive data through a SQL query. How would you secure it? _(Asked in TCS Digital)_

**🧩 Foundation:** Implement multiple layers of security including input validation, parameterized queries, access controls, and data encryption.

**⚙️ Function:** Secure the application against SQL injection, unauthorized access, and data breaches.

**🔁 Flow:**
```sql
-- Step 1: Create secure user authentication table
CREATE TABLE secure_users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password_hash VARCHAR(255),  -- bcrypt hash
    salt VARCHAR(100),
    role ENUM('user', 'admin', 'manager') DEFAULT 'user',
    is_active BOOLEAN DEFAULT TRUE,
    failed_login_attempts INT DEFAULT 0,
    last_login TIMESTAMP NULL,
    password_changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Step 2: Create secure session management
CREATE TABLE user_sessions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    session_token VARCHAR(255) UNIQUE,
    ip_address VARCHAR(45),
    user_agent TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP,
    is_valid BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (user_id) REFERENCES secure_users(id)
);

-- Step 3: Create parameterized query views
-- Secure view for employee data
CREATE VIEW secure_employee_data AS
SELECT 
    id,
    name,
    department_id,
    CASE 
        WHEN CURRENT_USER_ROLE() = 'admin' THEN salary
        WHEN CURRENT_USER_ROLE() = 'manager' AND department_id = CURRENT_USER_DEPARTMENT() THEN salary
        ELSE NULL 
    END as salary,
    email,
    hire_date
FROM employees
WHERE is_active = TRUE;

-- Step 4: Create stored procedures for secure data access
DELIMITER //
CREATE PROCEDURE GetEmployeeSecureData(IN emp_id INT, IN user_id INT)
BEGIN
    DECLARE user_role VARCHAR(50);
    DECLARE user_dept INT;
    DECLARE access_granted BOOLEAN DEFAULT FALSE;
    
    -- Get user role and department
    SELECT role, department_id INTO user_role, user_dept 
    FROM secure_users WHERE id = user_id;
    
    -- Check access permissions
    IF user_role = 'admin' THEN
        SET access_granted = TRUE;
    ELSEIF user_role = 'manager' THEN
        SELECT COUNT(*) INTO @dept_match 
        FROM employees WHERE id = emp_id AND department_id = user_dept;
        IF @dept_match > 0 THEN
            SET access_granted = TRUE;
        END IF;
    ELSEIF user_role = 'user' THEN
        IF emp_id = user_id THEN
            SET access_granted = TRUE;
        END IF;
    END IF;
    
    -- Return data if access granted
    IF access_granted THEN
        SELECT 
            e.id,
            e.name,
            e.department_id,
            CASE 
                WHEN user_role = 'admin' THEN e.salary
                WHEN user_role = 'manager' THEN e.salary
                ELSE NULL 
            END as salary,
            e.email,
            e.hire_date
        FROM employees e
        WHERE e.id = emp_id AND e.is_active = TRUE;
        
        -- Log access
        INSERT INTO data_access_log (user_id, table_name, record_id, action, ip_address)
        VALUES (user_id, 'employees', emp_id, 'SELECT', USER());
    ELSE
        SELECT 'Access denied' as error;
    END IF;
END //
DELIMITER ;

-- Step 5: Create input validation functions
DELIMITER //
CREATE FUNCTION ValidateEmployeeId(emp_id VARCHAR(50)) RETURNS BOOLEAN
BEGIN
    DECLARE is_valid BOOLEAN DEFAULT FALSE;
    
    -- Check if input is numeric and within valid range
    IF emp_id REGEXP '^[0-9]+$' AND CAST(emp_id AS UNSIGNED) > 0 AND CAST(emp_id AS UNSIGNED) <= 10000 THEN
        SET is_valid = TRUE;
    END IF;
    
    RETURN is_valid;
END //
DELIMITER ;

-- Step 6: Create data access logging
CREATE TABLE data_access_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    table_name VARCHAR(50),
    record_id INT,
    action VARCHAR(20),
    ip_address VARCHAR(45),
    user_agent TEXT,
    access_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    success BOOLEAN,
    error_message TEXT,
    FOREIGN KEY (user_id) REFERENCES secure_users(id)
);

-- Step 7: Create SQL injection prevention triggers
DELIMITER //
CREATE TRIGGER prevent_sql_injection_employees
BEFORE INSERT ON employees
FOR EACH ROW
BEGIN
    -- Check for suspicious patterns
    IF NEW.name LIKE '%UNION%' OR NEW.name LIKE '%SELECT%' OR NEW.name LIKE '%DROP%' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Suspicious input detected';
    END IF;
    
    -- Validate email format
    IF NEW.email NOT REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid email format';
    END IF;
END //
DELIMITER ;

-- Step 8: Create rate limiting table
CREATE TABLE rate_limiting (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    endpoint VARCHAR(100),
    request_count INT DEFAULT 1,
    window_start TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES secure_users(id)
);

-- Step 9: Create secure data encryption
-- Encrypt sensitive columns
ALTER TABLE employees ADD COLUMN salary_encrypted VARBINARY(255);
ALTER TABLE employees ADD COLUMN ssn_encrypted VARBINARY(255);

-- Update existing data
UPDATE employees SET 
    salary_encrypted = AES_ENCRYPT(salary, 'encryption_key'),
    ssn_encrypted = AES_ENCRYPT(ssn, 'encryption_key')
WHERE salary IS NOT NULL;

-- Step 10: Create secure data masking
CREATE VIEW masked_employee_data AS
SELECT 
    id,
    name,
    department_id,
    CONCAT('***-**-', RIGHT(AES_DECRYPT(ssn_encrypted, 'encryption_key'), 4)) as ssn_masked,
    CONCAT(LEFT(email, 2), '***@', SUBSTRING_INDEX(email, '@', -1)) as email_masked,
    hire_date
FROM employees
WHERE is_active = TRUE;

-- Step 11: Create audit trail for sensitive operations
DELIMITER //
CREATE TRIGGER audit_employee_changes
AFTER UPDATE ON employees
FOR EACH ROW
BEGIN
    INSERT INTO audit_trail (
        table_name, record_id, action, old_values, new_values, user_id, timestamp
    )
    VALUES (
        'employees',
        NEW.id,
        'UPDATE',
        JSON_OBJECT('name', OLD.name, 'salary', OLD.salary, 'email', OLD.email),
        JSON_OBJECT('name', NEW.name, 'salary', NEW.salary, 'email', NEW.email),
        CURRENT_USER_ID(),
        NOW()
    );
END //
DELIMITER ;

-- Step 12: Create secure backup procedure
DELIMITER //
CREATE PROCEDURE CreateSecureBackup()
BEGIN
    -- Create encrypted backup
    CREATE TABLE employees_backup_secure AS
    SELECT 
        id,
        name,
        department_id,
        salary_encrypted,
        ssn_encrypted,
        email,
        hire_date,
        is_active,
        'encryption_key' as key_version
    FROM employees;
    
    -- Log backup creation
    INSERT INTO audit_trail (table_name, action, user_id, timestamp)
    VALUES ('employees', 'BACKUP', CURRENT_USER_ID(), NOW());
END //
DELIMITER ;

-- Step 13: Create security monitoring
CREATE TABLE security_events (
    id INT AUTO_INCREMENT PRIMARY KEY,
    event_type ENUM('login_failure', 'sql_injection_attempt', 'unauthorized_access', 'data_breach'),
    user_id INT,
    ip_address VARCHAR(45),
    event_details TEXT,
    severity ENUM('low', 'medium', 'high', 'critical'),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    resolved BOOLEAN DEFAULT FALSE
);

-- Step 14: Create automated security checks
DELIMITER //
CREATE PROCEDURE SecurityHealthCheck()
BEGIN
    -- Check for failed login attempts
    SELECT 'Failed login attempts' as check_type, COUNT(*) as count
    FROM secure_users WHERE failed_login_attempts > 5;
    
    -- Check for suspicious access patterns
    SELECT 'Suspicious access patterns' as check_type, COUNT(*) as count
    FROM data_access_log 
    WHERE access_time >= DATE_SUB(NOW(), INTERVAL 1 HOUR)
    GROUP BY user_id, ip_address
    HAVING COUNT(*) > 100;
    
    -- Check for unencrypted sensitive data
    SELECT 'Unencrypted sensitive data' as check_type, COUNT(*) as count
    FROM employees WHERE salary IS NOT NULL AND salary_encrypted IS NULL;
END //
DELIMITER ;

-- Step 15: Create secure data export
DELIMITER //
CREATE PROCEDURE ExportSecureData(IN user_id INT, IN export_type VARCHAR(50))
BEGIN
    DECLARE user_role VARCHAR(50);
    
    SELECT role INTO user_role FROM secure_users WHERE id = user_id;
    
    IF user_role = 'admin' THEN
        SELECT 
            id,
            name,
            department_id,
            AES_DECRYPT(salary_encrypted, 'encryption_key') as salary,
            email,
            hire_date
        FROM employees
        WHERE is_active = TRUE
        ORDER BY id;
        
        -- Log export
        INSERT INTO data_access_log (user_id, table_name, action, ip_address)
        VALUES (user_id, 'employees', 'EXPORT', USER());
    ELSE
        SELECT 'Insufficient privileges for export' as error;
    END IF;
END //
DELIMITER ;
```

2. Write a query to audit access to sensitive data in the employees table. _(Asked in TCS Digital)_

**🧩 Foundation:** Create comprehensive audit trails to monitor and track all access to sensitive employee data.

**⚙️ Function:** Track, monitor, and alert on unauthorized or suspicious access to sensitive employee information.

**🔁 Flow:**
```sql
-- Step 1: Create comprehensive audit table
CREATE TABLE employee_data_audit (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    session_id VARCHAR(255),
    table_name VARCHAR(50) DEFAULT 'employees',
    record_id INT,
    action_type ENUM('SELECT', 'INSERT', 'UPDATE', 'DELETE', 'EXPORT'),
    column_name VARCHAR(50),
    old_value TEXT,
    new_value TEXT,
    ip_address VARCHAR(45),
    user_agent TEXT,
    access_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    success BOOLEAN DEFAULT TRUE,
    error_message TEXT,
    data_sensitivity ENUM('public', 'internal', 'confidential', 'restricted'),
    legal_basis VARCHAR(100),
    processing_purpose VARCHAR(100)
);

-- Step 2: Create triggers for automatic audit logging
DELIMITER //
CREATE TRIGGER audit_employee_select
AFTER SELECT ON employees
FOR EACH ROW
BEGIN
    INSERT INTO employee_data_audit (
        user_id, record_id, action_type, column_name, 
        new_value, ip_address, data_sensitivity
    )
    VALUES (
        CURRENT_USER_ID(),
        NEW.id,
        'SELECT',
        'all_columns',
        JSON_OBJECT('name', NEW.name, 'department_id', NEW.department_id),
        USER(),
        CASE 
            WHEN NEW.salary IS NOT NULL THEN 'confidential'
            WHEN NEW.ssn IS NOT NULL THEN 'restricted'
            ELSE 'internal'
        END
    );
END //
DELIMITER ;

-- Step 3: Create trigger for UPDATE operations
DELIMITER //
CREATE TRIGGER audit_employee_update
AFTER UPDATE ON employees
FOR EACH ROW
BEGIN
    -- Log salary changes
    IF OLD.salary != NEW.salary THEN
        INSERT INTO employee_data_audit (
            user_id, record_id, action_type, column_name,
            old_value, new_value, ip_address, data_sensitivity
        )
        VALUES (
            CURRENT_USER_ID(),
            NEW.id,
            'UPDATE',
            'salary',
            OLD.salary,
            NEW.salary,
            USER(),
            'confidential'
        );
    END IF;
    
    -- Log SSN changes
    IF OLD.ssn != NEW.ssn THEN
        INSERT INTO employee_data_audit (
            user_id, record_id, action_type, column_name,
            old_value, new_value, ip_address, data_sensitivity
        )
        VALUES (
            CURRENT_USER_ID(),
            NEW.id,
            'UPDATE',
            'ssn',
            CONCAT('***-**-', RIGHT(OLD.ssn, 4)),
            CONCAT('***-**-', RIGHT(NEW.ssn, 4)),
            USER(),
            'restricted'
        );
    END IF;
    
    -- Log email changes
    IF OLD.email != NEW.email THEN
        INSERT INTO employee_data_audit (
            user_id, record_id, action_type, column_name,
            old_value, new_value, ip_address, data_sensitivity
        )
        VALUES (
            CURRENT_USER_ID(),
            NEW.id,
            'UPDATE',
            'email',
            OLD.email,
            NEW.email,
            USER(),
            'internal'
        );
    END IF;
END //
DELIMITER ;

-- Step 4: Create audit reporting queries
-- Recent access to sensitive data
SELECT 
    ead.user_id,
    u.username,
    ead.record_id,
    ead.action_type,
    ead.column_name,
    ead.data_sensitivity,
    ead.access_time,
    ead.ip_address
FROM employee_data_audit ead
JOIN secure_users u ON ead.user_id = u.id
WHERE ead.data_sensitivity IN ('confidential', 'restricted')
  AND ead.access_time >= DATE_SUB(NOW(), INTERVAL 24 HOUR)
ORDER BY ead.access_time DESC;

-- Step 5: Create suspicious activity detection
DELIMITER //
CREATE PROCEDURE DetectSuspiciousActivity()
BEGIN
    -- Multiple failed access attempts
    SELECT 'Multiple failed access attempts' as alert_type, user_id, COUNT(*) as failed_attempts
    FROM employee_data_audit
    WHERE success = FALSE
      AND access_time >= DATE_SUB(NOW(), INTERVAL 1 HOUR)
    GROUP BY user_id
    HAVING COUNT(*) > 5;
    
    -- Unusual access patterns
    SELECT 'Unusual access patterns' as alert_type, user_id, COUNT(*) as access_count
    FROM employee_data_audit
    WHERE access_time >= DATE_SUB(NOW(), INTERVAL 1 HOUR)
    GROUP BY user_id
    HAVING COUNT(*) > 100;
    
    -- Access outside business hours
    SELECT 'After hours access' as alert_type, user_id, access_time
    FROM employee_data_audit
    WHERE HOUR(access_time) NOT BETWEEN 8 AND 18
      AND access_time >= DATE_SUB(NOW(), INTERVAL 24 HOUR)
      AND data_sensitivity IN ('confidential', 'restricted');
    
    -- Access from unusual IP addresses
    SELECT 'Unusual IP access' as alert_type, user_id, ip_address, COUNT(*) as access_count
    FROM employee_data_audit
    WHERE ip_address NOT LIKE '192.168.%'
      AND ip_address NOT LIKE '10.%'
      AND access_time >= DATE_SUB(NOW(), INTERVAL 24 HOUR)
    GROUP BY user_id, ip_address;
END //
DELIMITER ;

-- Step 6: Create compliance reporting
DELIMITER //
CREATE PROCEDURE GenerateAuditComplianceReport(IN start_date DATE, IN end_date DATE)
BEGIN
    SELECT 
        'Employee Data Access Audit Report' as report_title,
        start_date as report_start,
        end_date as report_end,
        NOW() as generated_at,
        COUNT(*) as total_access_events,
        COUNT(CASE WHEN data_sensitivity = 'restricted' THEN 1 END) as restricted_access_count,
        COUNT(CASE WHEN data_sensitivity = 'confidential' THEN 1 END) as confidential_access_count,
        COUNT(CASE WHEN success = FALSE THEN 1 END) as failed_access_count,
        COUNT(DISTINCT user_id) as unique_users,
        COUNT(DISTINCT record_id) as unique_records_accessed
    FROM employee_data_audit
    WHERE DATE(access_time) BETWEEN start_date AND end_date;
    
    -- Top users accessing sensitive data
    SELECT 
        u.username,
        COUNT(*) as access_count,
        COUNT(CASE WHEN ead.data_sensitivity = 'restricted' THEN 1 END) as restricted_access,
        COUNT(CASE WHEN ead.data_sensitivity = 'confidential' THEN 1 END) as confidential_access
    FROM employee_data_audit ead
    JOIN secure_users u ON ead.user_id = u.id
    WHERE DATE(ead.access_time) BETWEEN start_date AND end_date
      AND ead.data_sensitivity IN ('restricted', 'confidential')
    GROUP BY ead.user_id, u.username
    ORDER BY access_count DESC
    LIMIT 10;
END //
DELIMITER ;

-- Step 7: Create real-time monitoring
CREATE TABLE audit_alerts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    alert_type VARCHAR(50),
    user_id INT,
    severity ENUM('low', 'medium', 'high', 'critical'),
    alert_message TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    acknowledged BOOLEAN DEFAULT FALSE,
    acknowledged_by INT,
    acknowledged_at TIMESTAMP NULL
);

-- Step 8: Create automated alerting
DELIMITER //
CREATE EVENT audit_monitoring_event
ON SCHEDULE EVERY 5 MINUTE
DO
BEGIN
    -- Check for critical access patterns
    INSERT INTO audit_alerts (alert_type, user_id, severity, alert_message)
    SELECT 
        'High frequency access',
        user_id,
        'high',
        CONCAT('User accessed sensitive data ', COUNT(*), ' times in the last hour')
    FROM employee_data_audit
    WHERE access_time >= DATE_SUB(NOW(), INTERVAL 1 HOUR)
      AND data_sensitivity IN ('restricted', 'confidential')
    GROUP BY user_id
    HAVING COUNT(*) > 50;
    
    -- Check for failed access attempts
    INSERT INTO audit_alerts (alert_type, user_id, severity, alert_message)
    SELECT 
        'Failed access attempts',
        user_id,
        'medium',
        CONCAT('User had ', COUNT(*), ' failed access attempts')
    FROM employee_data_audit
    WHERE access_time >= DATE_SUB(NOW(), INTERVAL 1 HOUR)
      AND success = FALSE
    GROUP BY user_id
    HAVING COUNT(*) > 10;
END //
DELIMITER ;

-- Step 9: Create data access analytics
CREATE VIEW employee_access_analytics AS
SELECT 
    DATE(access_time) as access_date,
    HOUR(access_time) as access_hour,
    data_sensitivity,
    action_type,
    COUNT(*) as access_count,
    COUNT(DISTINCT user_id) as unique_users,
    COUNT(DISTINCT record_id) as unique_records
FROM employee_data_audit
WHERE access_time >= DATE_SUB(NOW(), INTERVAL 30 DAY)
GROUP BY DATE(access_time), HOUR(access_time), data_sensitivity, action_type
ORDER BY access_date DESC, access_hour;

-- Step 10: Create user behavior profiling
DELIMITER //
CREATE PROCEDURE AnalyzeUserBehavior(IN user_id INT, IN days_back INT)
BEGIN
    SELECT 
        u.username,
        COUNT(*) as total_access,
        COUNT(CASE WHEN ead.data_sensitivity = 'restricted' THEN 1 END) as restricted_access,
        COUNT(CASE WHEN ead.data_sensitivity = 'confidential' THEN 1 END) as confidential_access,
        COUNT(CASE WHEN ead.success = FALSE THEN 1 END) as failed_access,
        MIN(ead.access_time) as first_access,
        MAX(ead.access_time) as last_access,
        COUNT(DISTINCT ead.ip_address) as unique_ips,
        COUNT(DISTINCT ead.record_id) as unique_records
    FROM employee_data_audit ead
    JOIN secure_users u ON ead.user_id = u.id
    WHERE ead.user_id = user_id
      AND ead.access_time >= DATE_SUB(NOW(), INTERVAL days_back DAY)
    GROUP BY ead.user_id, u.username;
    
    -- Access pattern by hour
    SELECT 
        HOUR(access_time) as hour_of_day,
        COUNT(*) as access_count
    FROM employee_data_audit
    WHERE user_id = user_id
      AND access_time >= DATE_SUB(NOW(), INTERVAL days_back DAY)
    GROUP BY HOUR(access_time)
    ORDER BY hour_of_day;
END //
DELIMITER ;

-- Step 11: Create data retention for audit logs
DELIMITER //
CREATE PROCEDURE CleanupOldAuditLogs(IN retention_days INT)
BEGIN
    -- Archive old audit logs
    INSERT INTO employee_data_audit_archive
    SELECT * FROM employee_data_audit
    WHERE access_time < DATE_SUB(NOW(), INTERVAL retention_days DAY);
    
    -- Delete old audit logs
    DELETE FROM employee_data_audit
    WHERE access_time < DATE_SUB(NOW(), INTERVAL retention_days DAY);
    
    SELECT CONCAT('Cleaned up audit logs older than ', retention_days, ' days') as result;
END //
DELIMITER ;

-- Step 12: Create audit log export for compliance
DELIMITER //
CREATE PROCEDURE ExportAuditLogs(IN start_date DATE, IN end_date DATE, IN user_role VARCHAR(50))
BEGIN
    IF user_role = 'admin' THEN
        SELECT 
            ead.id,
            u.username,
            ead.record_id,
            ead.action_type,
            ead.column_name,
            ead.data_sensitivity,
            ead.access_time,
            ead.ip_address,
            ead.success,
            ead.legal_basis,
            ead.processing_purpose
        FROM employee_data_audit ead
        JOIN secure_users u ON ead.user_id = u.id
        WHERE DATE(ead.access_time) BETWEEN start_date AND end_date
        ORDER BY ead.access_time DESC;
    ELSE
        SELECT 'Insufficient privileges for audit log export' as error;
    END IF;
END //
DELIMITER ;

-- Step 13: Create audit log integrity verification
DELIMITER //
CREATE PROCEDURE VerifyAuditLogIntegrity()
BEGIN
    -- Check for missing audit entries
    SELECT 'Missing audit entries' as issue, COUNT(*) as count
    FROM employees e
    LEFT JOIN employee_data_audit ead ON e.id = ead.record_id
    WHERE ead.id IS NULL AND e.is_active = TRUE;
    
    -- Check for orphaned audit entries
    SELECT 'Orphaned audit entries' as issue, COUNT(*) as count
    FROM employee_data_audit ead
    LEFT JOIN employees e ON ead.record_id = e.id
    WHERE e.id IS NULL;
    
    -- Check for data consistency
    SELECT 'Data consistency check' as check_type,
           COUNT(*) as total_audit_entries,
           COUNT(DISTINCT record_id) as unique_records_audited,
           COUNT(DISTINCT user_id) as unique_users_audited
    FROM employee_data_audit;
END //
DELIMITER ;

-- Step 14: Create audit dashboard queries
-- Daily access summary
SELECT 
    DATE(access_time) as access_date,
    data_sensitivity,
    COUNT(*) as access_count,
    COUNT(DISTINCT user_id) as unique_users
FROM employee_data_audit
WHERE access_time >= DATE_SUB(NOW(), INTERVAL 7 DAY)
GROUP BY DATE(access_time), data_sensitivity
ORDER BY access_date DESC, data_sensitivity;

-- Top accessed records
SELECT 
    record_id,
    COUNT(*) as access_count,
    COUNT(DISTINCT user_id) as unique_users,
    MAX(access_time) as last_access
FROM employee_data_audit
WHERE access_time >= DATE_SUB(NOW(), INTERVAL 30 DAY)
GROUP BY record_id
ORDER BY access_count DESC
LIMIT 10;

-- Step 15: Create automated audit reporting
DELIMITER //
CREATE EVENT daily_audit_report
ON SCHEDULE EVERY 1 DAY
STARTS CURRENT_TIMESTAMP
DO
BEGIN
    -- Generate daily audit summary
    INSERT INTO audit_summary (
        summary_date,
        total_access_events,
        restricted_access_count,
        confidential_access_count,
        failed_access_count,
        unique_users,
        alerts_generated
    )
    SELECT 
        CURDATE(),
        COUNT(*),
        COUNT(CASE WHEN data_sensitivity = 'restricted' THEN 1 END),
        COUNT(CASE WHEN data_sensitivity = 'confidential' THEN 1 END),
        COUNT(CASE WHEN success = FALSE THEN 1 END),
        COUNT(DISTINCT user_id),
        (SELECT COUNT(*) FROM audit_alerts WHERE DATE(created_at) = CURDATE())
    FROM employee_data_audit
    WHERE DATE(access_time) = CURDATE();
END //
DELIMITER ;
```

---

## 10. Window Functions

### Basic Questions

1. What are window functions in SQL, and why are they used? _(Asked in TCS, Infosys)_

**🧩 Foundation:** Window functions perform calculations across a set of table rows related to the current row, without reducing the result set.

**⚙️ Function:** Enable advanced analytics, ranking, running totals, and comparisons while preserving all rows in the result set.

**🔁 Flow:**
```sql
-- Basic Window Function Syntax
-- OVER() clause defines the window frame
SELECT 
    employee_id,
    name,
    salary,
    department_id,
    AVG(salary) OVER() as avg_salary_all,
    AVG(salary) OVER(PARTITION BY department_id) as avg_salary_dept,
    ROW_NUMBER() OVER(ORDER BY salary DESC) as salary_rank
FROM employees;

-- Window Functions vs Aggregate Functions
-- Aggregate function (reduces rows)
SELECT department_id, AVG(salary) as avg_salary
FROM employees
GROUP BY department_id;

-- Window function (preserves all rows)
SELECT 
    employee_id,
    name,
    department_id,
    salary,
    AVG(salary) OVER(PARTITION BY department_id) as avg_salary_dept
FROM employees;

-- Common Window Functions
-- 1. Ranking Functions
SELECT 
    name,
    salary,
    ROW_NUMBER() OVER(ORDER BY salary DESC) as row_num,
    RANK() OVER(ORDER BY salary DESC) as rank_num,
    DENSE_RANK() OVER(ORDER BY salary DESC) as dense_rank_num
FROM employees;

-- 2. Aggregate Functions
SELECT 
    order_id,
    customer_id,
    order_amount,
    SUM(order_amount) OVER(PARTITION BY customer_id) as customer_total,
    AVG(order_amount) OVER(PARTITION BY customer_id) as customer_avg,
    COUNT(*) OVER(PARTITION BY customer_id) as customer_orders
FROM orders;

-- 3. Navigation Functions
SELECT 
    employee_id,
    name,
    salary,
    LAG(salary, 1) OVER(ORDER BY hire_date) as prev_salary,
    LEAD(salary, 1) OVER(ORDER BY hire_date) as next_salary,
    FIRST_VALUE(salary) OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_highest,
    LAST_VALUE(salary) OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_lowest
FROM employees;

-- 4. Distribution Functions
SELECT 
    name,
    salary,
    PERCENT_RANK() OVER(ORDER BY salary) as salary_percentile,
    CUME_DIST() OVER(ORDER BY salary) as salary_cumulative_dist,
    NTILE(4) OVER(ORDER BY salary DESC) as salary_quartile
FROM employees;

-- Window Frame Specification
-- ROWS BETWEEN
SELECT 
    order_date,
    daily_sales,
    SUM(daily_sales) OVER(
        ORDER BY order_date 
        ROWS BETWEEN 2 PRECEDING AND CURRENT ROW
    ) as rolling_3day_total
FROM daily_sales;

-- RANGE BETWEEN
SELECT 
    salary,
    COUNT(*) OVER(
        ORDER BY salary 
        RANGE BETWEEN 1000 PRECEDING AND 1000 FOLLOWING
    ) as employees_in_range
FROM employees;

-- UNBOUNDED PRECEDING/FOLLOWING
SELECT 
    employee_id,
    salary,
    SUM(salary) OVER(
        ORDER BY hire_date 
        ROWS UNBOUNDED PRECEDING
    ) as running_total
FROM employees;

-- Practical Use Cases
-- 1. Running Totals
SELECT 
    order_date,
    order_amount,
    SUM(order_amount) OVER(ORDER BY order_date) as running_total,
    AVG(order_amount) OVER(ORDER BY order_date ROWS BETWEEN 6 PRECEDING AND CURRENT ROW) as moving_avg_7day
FROM orders;

-- 2. Ranking and Percentiles
SELECT 
    product_name,
    sales_amount,
    RANK() OVER(ORDER BY sales_amount DESC) as sales_rank,
    PERCENT_RANK() OVER(ORDER BY sales_amount) as sales_percentile,
    NTILE(5) OVER(ORDER BY sales_amount DESC) as sales_quintile
FROM products;

-- 3. Department Comparisons
SELECT 
    employee_id,
    name,
    department_id,
    salary,
    salary - AVG(salary) OVER(PARTITION BY department_id) as salary_diff_from_dept_avg,
    salary / AVG(salary) OVER(PARTITION BY department_id) * 100 as salary_percent_of_dept_avg
FROM employees;

-- 4. Time Series Analysis
SELECT 
    month,
    revenue,
    LAG(revenue, 1) OVER(ORDER BY month) as prev_month_revenue,
    revenue - LAG(revenue, 1) OVER(ORDER BY month) as revenue_change,
    (revenue - LAG(revenue, 1) OVER(ORDER BY month)) / LAG(revenue, 1) OVER(ORDER BY month) * 100 as revenue_growth_percent
FROM monthly_revenue;

-- 5. Customer Segmentation
SELECT 
    customer_id,
    total_purchases,
    CASE 
        WHEN NTILE(4) OVER(ORDER BY total_purchases DESC) = 1 THEN 'Premium'
        WHEN NTILE(4) OVER(ORDER BY total_purchases DESC) = 2 THEN 'Gold'
        WHEN NTILE(4) OVER(ORDER BY total_purchases DESC) = 3 THEN 'Silver'
        ELSE 'Bronze'
    END as customer_segment
FROM customer_summary;

-- Performance Considerations
-- Use indexes on ORDER BY columns
CREATE INDEX idx_employees_salary ON employees(salary);
CREATE INDEX idx_orders_date ON orders(order_date);

-- Partition large datasets
SELECT 
    employee_id,
    salary,
    ROW_NUMBER() OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_rank
FROM employees
WHERE department_id IN (1, 2, 3);  -- Process in batches

-- Window Functions with CTEs
WITH ranked_employees AS (
    SELECT 
        employee_id,
        name,
        salary,
        department_id,
        ROW_NUMBER() OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_rank
    FROM employees
)
SELECT * FROM ranked_employees WHERE dept_rank <= 3;
```

2. What is the difference between RANK, DENSE_RANK, and ROW_NUMBER? _(Asked in Capgemini)_

**🧩 Foundation:** All three are ranking functions, but they handle ties differently and have different numbering patterns.

**⚙️ Function:** Provide different ranking strategies for ordering data with or without handling duplicate values.

**🔁 Flow:**
```sql
-- Create sample data with ties
CREATE TABLE sample_scores (
    student_id INT,
    student_name VARCHAR(50),
    score INT
);

INSERT INTO sample_scores VALUES
(1, 'Alice', 95),
(2, 'Bob', 95),
(3, 'Charlie', 90),
(4, 'David', 85),
(5, 'Eve', 85),
(6, 'Frank', 80);

-- Compare all three ranking functions
SELECT 
    student_name,
    score,
    ROW_NUMBER() OVER(ORDER BY score DESC) as row_number,
    RANK() OVER(ORDER BY score DESC) as rank_num,
    DENSE_RANK() OVER(ORDER BY score DESC) as dense_rank_num
FROM sample_scores
ORDER BY score DESC;

-- ROW_NUMBER() - Always unique, no gaps
-- Result: 1, 2, 3, 4, 5, 6 (even with ties)
SELECT 
    student_name,
    score,
    ROW_NUMBER() OVER(ORDER BY score DESC) as row_number
FROM sample_scores
ORDER BY score DESC;

-- RANK() - Handles ties, creates gaps
-- Result: 1, 1, 3, 4, 4, 6 (ties get same rank, next rank skips)
SELECT 
    student_name,
    score,
    RANK() OVER(ORDER BY score DESC) as rank_num
FROM sample_scores
ORDER BY score DESC;

-- DENSE_RANK() - Handles ties, no gaps
-- Result: 1, 1, 2, 3, 3, 4 (ties get same rank, next rank continues)
SELECT 
    student_name,
    score,
    DENSE_RANK() OVER(ORDER BY score DESC) as dense_rank_num
FROM sample_scores
ORDER BY score DESC;

-- Practical Examples with Employee Data
-- ROW_NUMBER() - Unique identification
SELECT 
    employee_id,
    name,
    salary,
    ROW_NUMBER() OVER(ORDER BY salary DESC) as salary_position
FROM employees;

-- RANK() - Competition ranking (Olympic style)
SELECT 
    employee_id,
    name,
    salary,
    RANK() OVER(ORDER BY salary DESC) as salary_rank
FROM employees;

-- DENSE_RANK() - Grade ranking (A, A, B, C, C, D)
SELECT 
    employee_id,
    name,
    salary,
    DENSE_RANK() OVER(ORDER BY salary DESC) as salary_grade
FROM employees;

-- Partitioned Ranking Examples
-- Rank within departments
SELECT 
    employee_id,
    name,
    department_id,
    salary,
    ROW_NUMBER() OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_row_num,
    RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_rank,
    DENSE_RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_dense_rank
FROM employees;

-- Multiple ORDER BY columns
SELECT 
    employee_id,
    name,
    department_id,
    salary,
    hire_date,
    ROW_NUMBER() OVER(ORDER BY department_id, salary DESC, hire_date) as overall_row_num,
    RANK() OVER(ORDER BY department_id, salary DESC, hire_date) as overall_rank,
    DENSE_RANK() OVER(ORDER BY department_id, salary DESC, hire_date) as overall_dense_rank
FROM employees;

-- Real-world Use Cases
-- 1. Top N per category (ROW_NUMBER)
WITH ranked_products AS (
    SELECT 
        product_name,
        category,
        sales_amount,
        ROW_NUMBER() OVER(PARTITION BY category ORDER BY sales_amount DESC) as category_rank
    FROM products
)
SELECT * FROM ranked_products WHERE category_rank <= 3;

-- 2. Competition results (RANK)
SELECT 
    athlete_name,
    event,
    score,
    RANK() OVER(PARTITION BY event ORDER BY score DESC) as event_rank
FROM competition_results;

-- 3. Grade distribution (DENSE_RANK)
SELECT 
    student_name,
    grade,
    DENSE_RANK() OVER(ORDER BY grade DESC) as grade_rank
FROM student_grades;

-- 4. Sales performance tiers
SELECT 
    salesperson_id,
    total_sales,
    CASE 
        WHEN DENSE_RANK() OVER(ORDER BY total_sales DESC) <= 3 THEN 'Top Performer'
        WHEN DENSE_RANK() OVER(ORDER BY total_sales DESC) <= 10 THEN 'High Performer'
        WHEN DENSE_RANK() OVER(ORDER BY total_sales DESC) <= 20 THEN 'Average Performer'
        ELSE 'Needs Improvement'
    END as performance_tier
FROM sales_performance;

-- 5. Customer segmentation by purchase amount
SELECT 
    customer_id,
    total_purchases,
    CASE 
        WHEN RANK() OVER(ORDER BY total_purchases DESC) <= 10 THEN 'VIP'
        WHEN RANK() OVER(ORDER BY total_purchases DESC) <= 50 THEN 'Premium'
        WHEN RANK() OVER(ORDER BY total_purchases DESC) <= 100 THEN 'Regular'
        ELSE 'Occasional'
    END as customer_segment
FROM customer_summary;

-- Advanced Examples
-- Conditional ranking
SELECT 
    employee_id,
    name,
    department_id,
    salary,
    CASE 
        WHEN department_id = 1 THEN RANK() OVER(PARTITION BY department_id ORDER BY salary DESC)
        WHEN department_id = 2 THEN DENSE_RANK() OVER(PARTITION BY department_id ORDER BY salary DESC)
        ELSE ROW_NUMBER() OVER(PARTITION BY department_id ORDER BY salary DESC)
    END as conditional_rank
FROM employees;

-- Ranking with multiple criteria
SELECT 
    employee_id,
    name,
    performance_score,
    years_experience,
    RANK() OVER(ORDER BY performance_score DESC, years_experience DESC) as overall_rank,
    RANK() OVER(ORDER BY years_experience DESC, performance_score DESC) as experience_rank
FROM employees;

-- Ranking with window frames
SELECT 
    order_date,
    daily_revenue,
    RANK() OVER(ORDER BY daily_revenue DESC) as revenue_rank,
    RANK() OVER(
        ORDER BY daily_revenue DESC 
        ROWS BETWEEN 6 PRECEDING AND CURRENT ROW
    ) as rolling_week_rank
FROM daily_revenue;

-- Performance comparison
-- ROW_NUMBER() is fastest (no tie handling)
-- RANK() and DENSE_RANK() are slower (tie handling logic)

-- Use ROW_NUMBER() when:
-- - You need unique identifiers
-- - No ties are expected
-- - Performance is critical

-- Use RANK() when:
-- - You want Olympic-style ranking
-- - Ties should get same rank, next rank skips
-- - Competition results

-- Use DENSE_RANK() when:
-- - You want grade-style ranking
-- - Ties should get same rank, next rank continues
-- - Customer segmentation
```

### Basic Query Questions

1. Write a query to assign row numbers to records in the orders table ordered by order_date. _(Asked in TCS)_

**🧩 Foundation:** Use ROW_NUMBER() window function to assign unique sequential numbers to each row based on order_date.

**⚙️ Function:** Create a unique identifier for each order based on chronological order for pagination, tracking, or analysis.

**🔁 Flow:**
```sql
-- Basic ROW_NUMBER() assignment
SELECT 
    order_id,
    customer_id,
    order_date,
    order_amount,
    ROW_NUMBER() OVER(ORDER BY order_date) as row_num
FROM orders;

-- ROW_NUMBER() with multiple ORDER BY columns
SELECT 
    order_id,
    customer_id,
    order_date,
    order_amount,
    ROW_NUMBER() OVER(ORDER BY order_date, order_id) as row_num
FROM orders;

-- ROW_NUMBER() with descending order
SELECT 
    order_id,
    customer_id,
    order_date,
    order_amount,
    ROW_NUMBER() OVER(ORDER BY order_date DESC) as row_num_desc
FROM orders;

-- ROW_NUMBER() partitioned by customer
SELECT 
    order_id,
    customer_id,
    order_date,
    order_amount,
    ROW_NUMBER() OVER(PARTITION BY customer_id ORDER BY order_date) as customer_order_num
FROM orders;

-- ROW_NUMBER() with filtering
SELECT 
    order_id,
    customer_id,
    order_date,
    order_amount,
    ROW_NUMBER() OVER(ORDER BY order_date) as row_num
FROM orders
WHERE order_amount > 100
  AND order_date >= '2024-01-01';

-- ROW_NUMBER() for pagination
WITH numbered_orders AS (
    SELECT 
        order_id,
        customer_id,
        order_date,
        order_amount,
        ROW_NUMBER() OVER(ORDER BY order_date) as row_num
    FROM orders
)
SELECT * FROM numbered_orders 
WHERE row_num BETWEEN 1 AND 10;  -- First page

-- ROW_NUMBER() with window frame
SELECT 
    order_id,
    customer_id,
    order_date,
    order_amount,
    ROW_NUMBER() OVER(
        ORDER BY order_date 
        ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
    ) as cumulative_row_num
FROM orders;

-- ROW_NUMBER() for duplicate detection
WITH duplicate_check AS (
    SELECT 
        order_id,
        customer_id,
        order_date,
        order_amount,
        ROW_NUMBER() OVER(
            PARTITION BY customer_id, order_date, order_amount 
            ORDER BY order_id
        ) as duplicate_rank
    FROM orders
)
SELECT * FROM duplicate_check WHERE duplicate_rank > 1;

-- ROW_NUMBER() for data sampling
WITH sampled_orders AS (
    SELECT 
        order_id,
        customer_id,
        order_date,
        order_amount,
        ROW_NUMBER() OVER(ORDER BY RAND()) as random_row_num
    FROM orders
)
SELECT * FROM sampled_orders 
WHERE random_row_num <= 100;  -- Sample 100 random orders

-- ROW_NUMBER() for time-based analysis
SELECT 
    order_id,
    customer_id,
    order_date,
    order_amount,
    ROW_NUMBER() OVER(ORDER BY order_date) as chronological_order,
    ROW_NUMBER() OVER(PARTITION BY YEAR(order_date), MONTH(order_date) ORDER BY order_date) as monthly_order
FROM orders;

-- ROW_NUMBER() with conditional ordering
SELECT 
    order_id,
    customer_id,
    order_date,
    order_amount,
    order_status,
    ROW_NUMBER() OVER(
        ORDER BY 
            CASE WHEN order_status = 'completed' THEN 1 ELSE 2 END,
            order_date
    ) as priority_row_num
FROM orders;

-- ROW_NUMBER() for data validation
SELECT 
    order_id,
    customer_id,
    order_date,
    order_amount,
    ROW_NUMBER() OVER(ORDER BY order_date) as row_num,
    CASE 
        WHEN order_amount < 0 THEN 'Invalid negative amount'
        WHEN order_date > CURRENT_DATE THEN 'Future date'
        WHEN customer_id IS NULL THEN 'Missing customer'
        ELSE 'Valid'
    END as validation_status
FROM orders;

-- ROW_NUMBER() for performance analysis
SELECT 
    order_id,
    customer_id,
    order_date,
    order_amount,
    ROW_NUMBER() OVER(ORDER BY order_date) as row_num,
    AVG(order_amount) OVER(
        ORDER BY order_date 
        ROWS BETWEEN 9 PRECEDING AND CURRENT ROW
    ) as moving_avg_10_orders
FROM orders;

-- ROW_NUMBER() for data archiving
WITH order_ranking AS (
    SELECT 
        order_id,
        customer_id,
        order_date,
        order_amount,
        ROW_NUMBER() OVER(ORDER BY order_date) as row_num
    FROM orders
)
SELECT 
    order_id,
    customer_id,
    order_date,
    order_amount,
    CASE 
        WHEN row_num <= 1000 THEN 'Recent'
        WHEN row_num <= 5000 THEN 'Archive'
        ELSE 'Historical'
    END as data_category
FROM order_ranking;

-- ROW_NUMBER() for data migration
SELECT 
    order_id,
    customer_id,
    order_date,
    order_amount,
    ROW_NUMBER() OVER(ORDER BY order_date) as migration_batch_num,
    CASE 
        WHEN ROW_NUMBER() OVER(ORDER BY order_date) <= 1000 THEN 'Batch 1'
        WHEN ROW_NUMBER() OVER(ORDER BY order_date) <= 2000 THEN 'Batch 2'
        WHEN ROW_NUMBER() OVER(ORDER BY order_date) <= 3000 THEN 'Batch 3'
        ELSE 'Batch 4'
    END as migration_batch
FROM orders;

-- ROW_NUMBER() for data quality checks
SELECT 
    order_id,
    customer_id,
    order_date,
    order_amount,
    ROW_NUMBER() OVER(ORDER BY order_date) as row_num,
    LAG(order_date, 1) OVER(ORDER BY order_date) as prev_order_date,
    DATEDIFF(order_date, LAG(order_date, 1) OVER(ORDER BY order_date)) as days_since_prev_order
FROM orders;

-- ROW_NUMBER() for business intelligence
SELECT 
    order_id,
    customer_id,
    order_date,
    order_amount,
    ROW_NUMBER() OVER(ORDER BY order_date) as order_sequence,
    SUM(order_amount) OVER(
        ORDER BY order_date 
        ROWS UNBOUNDED PRECEDING
    ) as cumulative_revenue,
    AVG(order_amount) OVER(
        ORDER BY order_date 
        ROWS BETWEEN 29 PRECEDING AND CURRENT ROW
    ) as moving_avg_30_orders
FROM orders;

-- ROW_NUMBER() for data partitioning
SELECT 
    order_id,
    customer_id,
    order_date,
    order_amount,
    ROW_NUMBER() OVER(ORDER BY order_date) as row_num,
    NTILE(4) OVER(ORDER BY order_date) as data_quarter
FROM orders;

-- ROW_NUMBER() for audit trails
SELECT 
    order_id,
    customer_id,
    order_date,
    order_amount,
    ROW_NUMBER() OVER(ORDER BY order_date) as audit_sequence,
    'Order processed' as audit_event,
    NOW() as audit_timestamp
FROM orders;

-- ROW_NUMBER() for data synchronization
SELECT 
    order_id,
    customer_id,
    order_date,
    order_amount,
    ROW_NUMBER() OVER(ORDER BY order_date) as sync_sequence,
    CASE 
        WHEN ROW_NUMBER() OVER(ORDER BY order_date) % 100 = 0 THEN 'Sync checkpoint'
        ELSE 'Regular record'
    END as sync_status
FROM orders;
```

### Intermediate Questions

1. How do you use the PARTITION BY clause in window functions? _(Asked in Wipro)_

**🧩 Foundation:** PARTITION BY divides the result set into groups (partitions) and applies window functions within each partition independently.

**⚙️ Function:** Enable calculations and rankings within specific groups while maintaining the original row structure.

**🔁 Flow:**
```sql
-- Basic PARTITION BY usage
SELECT 
    employee_id,
    name,
    department_id,
    salary,
    AVG(salary) OVER(PARTITION BY department_id) as dept_avg_salary,
    RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_salary_rank
FROM employees;

-- Multiple partition columns
SELECT 
    order_id,
    customer_id,
    product_category,
    order_amount,
    SUM(order_amount) OVER(PARTITION BY customer_id, product_category) as customer_category_total,
    ROW_NUMBER() OVER(PARTITION BY customer_id, product_category ORDER BY order_date) as category_order_num
FROM orders;

-- PARTITION BY with different window functions
SELECT 
    employee_id,
    name,
    department_id,
    salary,
    hire_date,
    -- Ranking within department
    ROW_NUMBER() OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_row_num,
    RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_rank,
    DENSE_RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_dense_rank,
    -- Aggregates within department
    AVG(salary) OVER(PARTITION BY department_id) as dept_avg_salary,
    SUM(salary) OVER(PARTITION BY department_id) as dept_total_salary,
    COUNT(*) OVER(PARTITION BY department_id) as dept_employee_count,
    -- Navigation within department
    LAG(salary, 1) OVER(PARTITION BY department_id ORDER BY hire_date) as prev_dept_salary,
    LEAD(salary, 1) OVER(PARTITION BY department_id ORDER BY hire_date) as next_dept_salary,
    FIRST_VALUE(salary) OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_highest_salary,
    LAST_VALUE(salary) OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_lowest_salary
FROM employees;

-- PARTITION BY with time-based partitions
SELECT 
    order_id,
    customer_id,
    order_date,
    order_amount,
    SUM(order_amount) OVER(PARTITION BY customer_id, YEAR(order_date)) as customer_yearly_total,
    AVG(order_amount) OVER(PARTITION BY customer_id, MONTH(order_date)) as customer_monthly_avg,
    ROW_NUMBER() OVER(PARTITION BY customer_id, QUARTER(order_date) ORDER BY order_date) as quarter_order_num
FROM orders;

-- PARTITION BY with conditional logic
SELECT 
    employee_id,
    name,
    department_id,
    salary,
    performance_rating,
    CASE 
        WHEN performance_rating >= 4 THEN 'High Performer'
        WHEN performance_rating >= 3 THEN 'Average Performer'
        ELSE 'Low Performer'
    END as performance_category,
    RANK() OVER(PARTITION BY 
        CASE 
            WHEN performance_rating >= 4 THEN 'High Performer'
            WHEN performance_rating >= 3 THEN 'Average Performer'
            ELSE 'Low Performer'
        END 
        ORDER BY salary DESC
    ) as category_salary_rank
FROM employees;

-- PARTITION BY with window frames
SELECT 
    order_date,
    customer_id,
    daily_sales,
    SUM(daily_sales) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date 
        ROWS BETWEEN 2 PRECEDING AND CURRENT ROW
    ) as customer_3day_rolling_total,
    AVG(daily_sales) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date 
        ROWS BETWEEN 6 PRECEDING AND CURRENT ROW
    ) as customer_7day_moving_avg
FROM daily_sales;

-- PARTITION BY for data analysis
SELECT 
    product_id,
    product_name,
    category,
    region,
    sales_amount,
    -- Sales rank within category
    RANK() OVER(PARTITION BY category ORDER BY sales_amount DESC) as category_rank,
    -- Sales rank within region
    RANK() OVER(PARTITION BY region ORDER BY sales_amount DESC) as region_rank,
    -- Category average within region
    AVG(sales_amount) OVER(PARTITION BY category, region) as category_region_avg,
    -- Percentage of category total
    sales_amount / SUM(sales_amount) OVER(PARTITION BY category) * 100 as category_percentage
FROM product_sales;

-- PARTITION BY for customer analysis
SELECT 
    customer_id,
    order_date,
    order_amount,
    -- Customer's order sequence
    ROW_NUMBER() OVER(PARTITION BY customer_id ORDER BY order_date) as customer_order_num,
    -- Customer's cumulative spending
    SUM(order_amount) OVER(PARTITION BY customer_id ORDER BY order_date) as customer_cumulative_spend,
    -- Customer's average order value
    AVG(order_amount) OVER(PARTITION BY customer_id) as customer_avg_order,
    -- Days since customer's first order
    DATEDIFF(order_date, FIRST_VALUE(order_date) OVER(PARTITION BY customer_id ORDER BY order_date)) as days_since_first_order
FROM orders;

-- PARTITION BY for performance comparison
SELECT 
    employee_id,
    name,
    department_id,
    salary,
    hire_date,
    -- Salary percentile within department
    PERCENT_RANK() OVER(PARTITION BY department_id ORDER BY salary) as dept_salary_percentile,
    -- Salary quartile within department
    NTILE(4) OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_salary_quartile,
    -- Years of service within department
    DATEDIFF(CURRENT_DATE, hire_date) / 365.25 as years_of_service,
    -- Service rank within department
    RANK() OVER(PARTITION BY department_id ORDER BY hire_date) as dept_service_rank
FROM employees;

-- PARTITION BY for trend analysis
SELECT 
    month,
    product_category,
    sales_amount,
    -- Month-over-month growth within category
    LAG(sales_amount, 1) OVER(PARTITION BY product_category ORDER BY month) as prev_month_sales,
    sales_amount - LAG(sales_amount, 1) OVER(PARTITION BY product_category ORDER BY month) as month_over_month_change,
    -- Category's share of total sales each month
    sales_amount / SUM(sales_amount) OVER(PARTITION BY month) * 100 as category_monthly_share,
    -- Category's year-to-date total
    SUM(sales_amount) OVER(PARTITION BY product_category, YEAR(month) ORDER BY month) as category_ytd_total
FROM monthly_sales;

-- PARTITION BY for data quality checks
SELECT 
    order_id,
    customer_id,
    order_date,
    order_amount,
    -- Duplicate detection within customer
    ROW_NUMBER() OVER(PARTITION BY customer_id, order_date, order_amount ORDER BY order_id) as duplicate_rank,
    -- Unusual order amounts within customer
    order_amount - AVG(order_amount) OVER(PARTITION BY customer_id) as deviation_from_customer_avg,
    -- Order frequency within customer
    COUNT(*) OVER(PARTITION BY customer_id, DATE(order_date)) as customer_daily_orders
FROM orders;

-- PARTITION BY for business intelligence
SELECT 
    salesperson_id,
    sales_date,
    sales_amount,
    product_category,
    -- Salesperson's daily performance
    SUM(sales_amount) OVER(PARTITION BY salesperson_id, sales_date) as daily_total_sales,
    -- Salesperson's category performance
    RANK() OVER(PARTITION BY salesperson_id ORDER BY sales_amount DESC) as personal_sales_rank,
    -- Category performance within salesperson
    RANK() OVER(PARTITION BY salesperson_id, product_category ORDER BY sales_amount DESC) as category_sales_rank,
    -- Salesperson's moving average
    AVG(sales_amount) OVER(PARTITION BY salesperson_id ORDER BY sales_date ROWS BETWEEN 6 PRECEDING AND CURRENT ROW) as personal_7day_avg
FROM sales_data;
```

2. What is the purpose of the OVER clause in window functions? _(Asked in Accenture)_

**🧩 Foundation:** The OVER clause defines the window frame - the set of rows that the window function operates on and how they are ordered.

**⚙️ Function:** Specify the scope, ordering, and partitioning of rows for window function calculations.

**🔁 Flow:**
```sql
-- Basic OVER clause structure
-- OVER([PARTITION BY column] [ORDER BY column] [window_frame])
SELECT 
    employee_id,
    name,
    salary,
    AVG(salary) OVER() as overall_avg,  -- No partitioning, no ordering
    AVG(salary) OVER(ORDER BY hire_date) as running_avg,  -- Ordered, no partitioning
    AVG(salary) OVER(PARTITION BY department_id) as dept_avg,  -- Partitioned, no ordering
    AVG(salary) OVER(PARTITION BY department_id ORDER BY hire_date) as dept_running_avg  -- Both
FROM employees;

-- OVER() without parameters - entire result set
SELECT 
    order_id,
    order_amount,
    SUM(order_amount) OVER() as total_revenue,
    AVG(order_amount) OVER() as avg_order_value,
    COUNT(*) OVER() as total_orders
FROM orders;

-- OVER with ORDER BY - cumulative calculations
SELECT 
    order_date,
    daily_sales,
    SUM(daily_sales) OVER(ORDER BY order_date) as cumulative_sales,
    AVG(daily_sales) OVER(ORDER BY order_date) as running_avg_sales,
    COUNT(*) OVER(ORDER BY order_date) as cumulative_orders
FROM daily_sales;

-- OVER with PARTITION BY - group-based calculations
SELECT 
    employee_id,
    name,
    department_id,
    salary,
    AVG(salary) OVER(PARTITION BY department_id) as dept_avg_salary,
    SUM(salary) OVER(PARTITION BY department_id) as dept_total_salary,
    COUNT(*) OVER(PARTITION BY department_id) as dept_employee_count
FROM employees;

-- OVER with both PARTITION BY and ORDER BY
SELECT 
    customer_id,
    order_date,
    order_amount,
    SUM(order_amount) OVER(PARTITION BY customer_id ORDER BY order_date) as customer_cumulative_spend,
    AVG(order_amount) OVER(PARTITION BY customer_id ORDER BY order_date) as customer_running_avg,
    ROW_NUMBER() OVER(PARTITION BY customer_id ORDER BY order_date) as customer_order_sequence
FROM orders;

-- Window frame specifications in OVER clause
-- ROWS BETWEEN
SELECT 
    order_date,
    daily_sales,
    SUM(daily_sales) OVER(
        ORDER BY order_date 
        ROWS BETWEEN 2 PRECEDING AND CURRENT ROW
    ) as rolling_3day_total,
    AVG(daily_sales) OVER(
        ORDER BY order_date 
        ROWS BETWEEN 6 PRECEDING AND CURRENT ROW
    ) as rolling_7day_avg
FROM daily_sales;

-- RANGE BETWEEN
SELECT 
    salary,
    COUNT(*) OVER(
        ORDER BY salary 
        RANGE BETWEEN 1000 PRECEDING AND 1000 FOLLOWING
    ) as employees_in_salary_range
FROM employees;

-- UNBOUNDED PRECEDING/FOLLOWING
SELECT 
    employee_id,
    salary,
    SUM(salary) OVER(
        ORDER BY hire_date 
        ROWS UNBOUNDED PRECEDING
    ) as cumulative_payroll,
    AVG(salary) OVER(
        ORDER BY hire_date 
        ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
    ) as running_avg_salary
FROM employees;

-- OVER clause with multiple ORDER BY columns
SELECT 
    employee_id,
    name,
    department_id,
    salary,
    hire_date,
    ROW_NUMBER() OVER(ORDER BY department_id, salary DESC, hire_date) as overall_rank,
    RANK() OVER(PARTITION BY department_id ORDER BY salary DESC, hire_date) as dept_rank
FROM employees;

-- OVER clause with conditional logic
SELECT 
    order_id,
    customer_id,
    order_amount,
    order_status,
    SUM(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
        ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
    ) as customer_total_spend,
    SUM(CASE WHEN order_status = 'completed' THEN order_amount ELSE 0 END) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ) as customer_completed_spend
FROM orders;

-- OVER clause for time series analysis
SELECT 
    month,
    revenue,
    LAG(revenue, 1) OVER(ORDER BY month) as prev_month_revenue,
    LEAD(revenue, 1) OVER(ORDER BY month) as next_month_revenue,
    revenue - LAG(revenue, 1) OVER(ORDER BY month) as month_over_month_change,
    (revenue - LAG(revenue, 1) OVER(ORDER BY month)) / LAG(revenue, 1) OVER(ORDER BY month) * 100 as growth_percentage
FROM monthly_revenue;

-- OVER clause for ranking and percentiles
SELECT 
    employee_id,
    name,
    salary,
    ROW_NUMBER() OVER(ORDER BY salary DESC) as salary_row_num,
    RANK() OVER(ORDER BY salary DESC) as salary_rank,
    DENSE_RANK() OVER(ORDER BY salary DESC) as salary_dense_rank,
    PERCENT_RANK() OVER(ORDER BY salary) as salary_percentile,
    NTILE(4) OVER(ORDER BY salary DESC) as salary_quartile
FROM employees;

-- OVER clause for data comparison
SELECT 
    employee_id,
    name,
    department_id,
    salary,
    salary - AVG(salary) OVER(PARTITION BY department_id) as salary_diff_from_dept_avg,
    salary / AVG(salary) OVER(PARTITION BY department_id) * 100 as salary_percent_of_dept_avg,
    salary - MIN(salary) OVER(PARTITION BY department_id) as salary_above_dept_min,
    MAX(salary) OVER(PARTITION BY department_id) - salary as salary_below_dept_max
FROM employees;

-- OVER clause for data validation
SELECT 
    order_id,
    customer_id,
    order_amount,
    order_date,
    CASE 
        WHEN order_amount > AVG(order_amount) OVER(PARTITION BY customer_id) * 3 THEN 'Unusually high'
        WHEN order_amount < AVG(order_amount) OVER(PARTITION BY customer_id) * 0.1 THEN 'Unusually low'
        ELSE 'Normal'
    END as order_amount_flag,
    COUNT(*) OVER(PARTITION BY customer_id, DATE(order_date)) as customer_daily_orders
FROM orders;

-- OVER clause for performance optimization
-- Use indexes on ORDER BY columns
CREATE INDEX idx_employees_hire_date ON employees(hire_date);
CREATE INDEX idx_orders_date ON orders(order_date);

-- Partition large datasets
SELECT 
    employee_id,
    salary,
    AVG(salary) OVER(PARTITION BY department_id ORDER BY hire_date)
FROM employees
WHERE department_id IN (1, 2, 3);  -- Process in batches

-- OVER clause with CTEs for complex calculations
WITH employee_rankings AS (
    SELECT 
        employee_id,
        name,
        department_id,
        salary,
        ROW_NUMBER() OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_rank,
        AVG(salary) OVER(PARTITION BY department_id) as dept_avg_salary
    FROM employees
)
SELECT * FROM employee_rankings WHERE dept_rank <= 3;
```

### Intermediate Query Questions

1. Write a query to calculate the running total of sales by customer using a window function. _(Asked in Wipro)_

**🧩 Foundation:** Use SUM() with OVER clause and PARTITION BY to calculate cumulative totals for each customer over time.

**⚙️ Function:** Track each customer's cumulative spending to analyze spending patterns and customer value over time.

**🔁 Flow:**
```sql
-- Basic running total by customer
SELECT 
    customer_id,
    order_date,
    order_amount,
    SUM(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ) as customer_running_total
FROM orders
ORDER BY customer_id, order_date;

-- Running total with additional customer information
SELECT 
    o.customer_id,
    c.customer_name,
    o.order_date,
    o.order_amount,
    SUM(o.order_amount) OVER(
        PARTITION BY o.customer_id 
        ORDER BY o.order_date
    ) as customer_running_total,
    COUNT(*) OVER(
        PARTITION BY o.customer_id 
        ORDER BY o.order_date
    ) as customer_order_count
FROM orders o
JOIN customers c ON o.customer_id = c.customer_id
ORDER BY o.customer_id, o.order_date;

-- Running total with window frame specification
SELECT 
    customer_id,
    order_date,
    order_amount,
    SUM(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
        ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
    ) as customer_running_total,
    SUM(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
        ROWS BETWEEN 2 PRECEDING AND CURRENT ROW
    ) as customer_3order_total
FROM orders
ORDER BY customer_id, order_date;

-- Running total with multiple time periods
SELECT 
    customer_id,
    order_date,
    order_amount,
    -- Overall running total
    SUM(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ) as customer_total_spend,
    -- Monthly running total
    SUM(order_amount) OVER(
        PARTITION BY customer_id, YEAR(order_date), MONTH(order_date)
        ORDER BY order_date
    ) as customer_monthly_spend,
    -- Quarterly running total
    SUM(order_amount) OVER(
        PARTITION BY customer_id, YEAR(order_date), QUARTER(order_date)
        ORDER BY order_date
    ) as customer_quarterly_spend
FROM orders
ORDER BY customer_id, order_date;

-- Running total with percentage of total
SELECT 
    customer_id,
    order_date,
    order_amount,
    SUM(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ) as customer_running_total,
    order_amount / SUM(order_amount) OVER(PARTITION BY customer_id) * 100 as order_percentage_of_total,
    SUM(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ) / SUM(order_amount) OVER(PARTITION BY customer_id) * 100 as cumulative_percentage
FROM orders
ORDER BY customer_id, order_date;

-- Running total with moving averages
SELECT 
    customer_id,
    order_date,
    order_amount,
    SUM(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ) as customer_running_total,
    AVG(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
        ROWS BETWEEN 2 PRECEDING AND CURRENT ROW
    ) as customer_3order_avg,
    AVG(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
        ROWS BETWEEN 5 PRECEDING AND CURRENT ROW
    ) as customer_6order_avg
FROM orders
ORDER BY customer_id, order_date;

-- Running total with growth analysis
SELECT 
    customer_id,
    order_date,
    order_amount,
    SUM(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ) as customer_running_total,
    LAG(SUM(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ), 1) OVER(PARTITION BY customer_id ORDER BY order_date) as prev_running_total,
    SUM(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ) - LAG(SUM(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ), 1) OVER(PARTITION BY customer_id ORDER BY order_date) as running_total_increase
FROM orders
ORDER BY customer_id, order_date;

-- Running total with customer segmentation
SELECT 
    customer_id,
    order_date,
    order_amount,
    SUM(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ) as customer_running_total,
    CASE 
        WHEN SUM(order_amount) OVER(
            PARTITION BY customer_id 
            ORDER BY order_date
        ) >= 10000 THEN 'VIP'
        WHEN SUM(order_amount) OVER(
            PARTITION BY customer_id 
            ORDER BY order_date
        ) >= 5000 THEN 'Premium'
        WHEN SUM(order_amount) OVER(
            PARTITION BY customer_id 
            ORDER BY order_date
        ) >= 1000 THEN 'Regular'
        ELSE 'New'
    END as customer_segment
FROM orders
ORDER BY customer_id, order_date;

-- Running total with order sequence
SELECT 
    customer_id,
    order_date,
    order_amount,
    ROW_NUMBER() OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ) as customer_order_sequence,
    SUM(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ) as customer_running_total,
    AVG(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ) as customer_running_avg
FROM orders
ORDER BY customer_id, order_date;

-- Running total with time-based analysis
SELECT 
    customer_id,
    order_date,
    order_amount,
    SUM(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ) as customer_running_total,
    DATEDIFF(order_date, FIRST_VALUE(order_date) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    )) as days_since_first_order,
    SUM(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ) / DATEDIFF(order_date, FIRST_VALUE(order_date) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    )) as daily_spending_rate
FROM orders
ORDER BY customer_id, order_date;

-- Running total with product category breakdown
SELECT 
    o.customer_id,
    o.order_date,
    o.order_amount,
    p.product_category,
    SUM(o.order_amount) OVER(
        PARTITION BY o.customer_id 
        ORDER BY o.order_date
    ) as customer_total_spend,
    SUM(o.order_amount) OVER(
        PARTITION BY o.customer_id, p.product_category
        ORDER BY o.order_date
    ) as customer_category_spend
FROM orders o
JOIN order_items oi ON o.order_id = oi.order_id
JOIN products p ON oi.product_id = p.product_id
ORDER BY o.customer_id, o.order_date;

-- Running total with performance comparison
SELECT 
    customer_id,
    order_date,
    order_amount,
    SUM(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ) as customer_running_total,
    SUM(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ) - AVG(order_amount) OVER(PARTITION BY customer_id) as deviation_from_avg,
    SUM(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ) / AVG(order_amount) OVER(PARTITION BY customer_id) as multiple_of_avg
FROM orders
ORDER BY customer_id, order_date;

-- Running total with business intelligence
SELECT 
    customer_id,
    order_date,
    order_amount,
    SUM(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ) as customer_running_total,
    -- Customer's share of total revenue
    SUM(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ) / SUM(order_amount) OVER() * 100 as customer_revenue_share,
    -- Customer's rank by total spending
    RANK() OVER(
        ORDER BY SUM(order_amount) OVER(
            PARTITION BY customer_id 
            ORDER BY order_date
        ) DESC
    ) as customer_spending_rank
FROM orders
ORDER BY customer_id, order_date;

-- Running total with data quality checks
SELECT 
    customer_id,
    order_date,
    order_amount,
    SUM(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ) as customer_running_total,
    CASE 
        WHEN order_amount > AVG(order_amount) OVER(PARTITION BY customer_id) * 3 THEN 'Unusually high order'
        WHEN order_amount < AVG(order_amount) OVER(PARTITION BY customer_id) * 0.1 THEN 'Unusually low order'
        ELSE 'Normal order'
    END as order_flag,
    COUNT(*) OVER(
        PARTITION BY customer_id, DATE(order_date)
    ) as customer_daily_orders
FROM orders
ORDER BY customer_id, order_date;

-- Running total with forecasting indicators
SELECT 
    customer_id,
    order_date,
    order_amount,
    SUM(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ) as customer_running_total,
    -- Trend indicator
    CASE 
        WHEN order_amount > LAG(order_amount, 1) OVER(PARTITION BY customer_id ORDER BY order_date) THEN 'Increasing'
        WHEN order_amount < LAG(order_amount, 1) OVER(PARTITION BY customer_id ORDER BY order_date) THEN 'Decreasing'
        ELSE 'Stable'
    END as spending_trend,
    -- Frequency indicator
    DATEDIFF(order_date, LAG(order_date, 1) OVER(PARTITION BY customer_id ORDER BY order_date)) as days_since_prev_order
FROM orders
ORDER BY customer_id, order_date;

-- Running total with customer lifecycle analysis
SELECT 
    customer_id,
    order_date,
    order_amount,
    SUM(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ) as customer_running_total,
    ROW_NUMBER() OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ) as customer_order_number,
    CASE 
        WHEN ROW_NUMBER() OVER(PARTITION BY customer_id ORDER BY order_date) = 1 THEN 'First Order'
        WHEN ROW_NUMBER() OVER(PARTITION BY customer_id ORDER BY order_date) <= 3 THEN 'Early Customer'
        WHEN ROW_NUMBER() OVER(PARTITION BY customer_id ORDER BY order_date) <= 10 THEN 'Growing Customer'
        ELSE 'Established Customer'
    END as customer_lifecycle_stage
FROM orders
ORDER BY customer_id, order_date;
```

### Advanced Questions

1. How do you calculate a running total using window functions? _(Asked in Cognizant)_

**🧩 Foundation:** Use SUM() with OVER clause and ORDER BY to calculate cumulative totals that accumulate row by row.

**⚙️ Function:** Track cumulative values over time or sequence for trend analysis, financial calculations, and performance monitoring.

**🔁 Flow:**
```sql
-- Basic running total
SELECT 
    order_date,
    daily_sales,
    SUM(daily_sales) OVER(ORDER BY order_date) as running_total
FROM daily_sales;

-- Running total with partitioning
SELECT 
    customer_id,
    order_date,
    order_amount,
    SUM(order_amount) OVER(
        PARTITION BY customer_id 
        ORDER BY order_date
    ) as customer_running_total
FROM orders;

-- Running total with window frames
SELECT 
    order_date,
    daily_sales,
    SUM(daily_sales) OVER(
        ORDER BY order_date 
        ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
    ) as cumulative_total,
    SUM(daily_sales) OVER(
        ORDER BY order_date 
        ROWS BETWEEN 2 PRECEDING AND CURRENT ROW
    ) as rolling_3day_total
FROM daily_sales;

-- Running total with multiple metrics
SELECT 
    employee_id,
    salary,
    hire_date,
    SUM(salary) OVER(ORDER BY hire_date) as cumulative_payroll,
    COUNT(*) OVER(ORDER BY hire_date) as cumulative_employees,
    AVG(salary) OVER(ORDER BY hire_date) as running_avg_salary
FROM employees;

-- Running total with percentage calculation
SELECT 
    month,
    revenue,
    SUM(revenue) OVER(ORDER BY month) as cumulative_revenue,
    revenue / SUM(revenue) OVER(ORDER BY month) * 100 as monthly_contribution_percent,
    SUM(revenue) OVER(ORDER BY month) / SUM(revenue) OVER() * 100 as cumulative_percent_of_total
FROM monthly_revenue;

-- Running total with moving averages
SELECT 
    order_date,
    daily_sales,
    SUM(daily_sales) OVER(ORDER BY order_date) as running_total,
    AVG(daily_sales) OVER(
        ORDER BY order_date 
        ROWS BETWEEN 6 PRECEDING AND CURRENT ROW
    ) as moving_avg_7day
FROM daily_sales;

-- Running total with growth analysis
SELECT 
    month,
    revenue,
    SUM(revenue) OVER(ORDER BY month) as cumulative_revenue,
    LAG(SUM(revenue) OVER(ORDER BY month), 1) OVER(ORDER BY month) as prev_cumulative,
    SUM(revenue) OVER(ORDER BY month) - LAG(SUM(revenue) OVER(ORDER BY month), 1) OVER(ORDER BY month) as monthly_increase
FROM monthly_revenue;

-- Running total with conditional logic
SELECT 
    order_date,
    order_amount,
    order_status,
    SUM(CASE WHEN order_status = 'completed' THEN order_amount ELSE 0 END) OVER(ORDER BY order_date) as completed_running_total,
    SUM(CASE WHEN order_status = 'pending' THEN order_amount ELSE 0 END) OVER(ORDER BY order_date) as pending_running_total
FROM orders;

-- Running total with time-based windows
SELECT 
    order_date,
    daily_sales,
    SUM(daily_sales) OVER(
        ORDER BY order_date 
        RANGE BETWEEN INTERVAL 7 DAY PRECEDING AND CURRENT ROW
    ) as rolling_week_total,
    SUM(daily_sales) OVER(
        ORDER BY order_date 
        RANGE BETWEEN INTERVAL 30 DAY PRECEDING AND CURRENT ROW
    ) as rolling_month_total
FROM daily_sales;

-- Running total with multiple partitions
SELECT 
    product_category,
    order_date,
    daily_sales,
    SUM(daily_sales) OVER(
        PARTITION BY product_category 
        ORDER BY order_date
    ) as category_running_total,
    SUM(daily_sales) OVER(ORDER BY order_date) as overall_running_total
FROM product_daily_sales;

-- Running total for financial analysis
SELECT 
    transaction_date,
    amount,
    transaction_type,
    SUM(CASE WHEN transaction_type = 'credit' THEN amount ELSE -amount END) OVER(ORDER BY transaction_date) as running_balance,
    SUM(amount) OVER(ORDER BY transaction_date) as running_total
FROM transactions;

-- Running total with performance optimization
-- Use indexes for better performance
CREATE INDEX idx_orders_date ON orders(order_date);
CREATE INDEX idx_daily_sales_date ON daily_sales(order_date);

-- Partition large datasets
SELECT 
    order_date,
    daily_sales,
    SUM(daily_sales) OVER(ORDER BY order_date) as running_total
FROM daily_sales
WHERE order_date >= '2024-01-01';  -- Process in chunks
```

### Advanced Query Questions

1. Write a query to rank employees by salary within each department using a window function. _(Asked in Cognizant)_

**🧩 Foundation:** Use RANK(), DENSE_RANK(), or ROW_NUMBER() with PARTITION BY department_id and ORDER BY salary.

**⚙️ Function:** Identify top performers, salary distribution, and compensation analysis within each department.

**🔁 Flow:**
```sql
-- Basic department salary ranking
SELECT 
    employee_id,
    name,
    department_id,
    salary,
    RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_salary_rank
FROM employees;

-- Multiple ranking methods comparison
SELECT 
    employee_id,
    name,
    department_id,
    salary,
    ROW_NUMBER() OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_row_num,
    RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_rank,
    DENSE_RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_dense_rank
FROM employees;

-- Department ranking with additional information
SELECT 
    e.employee_id,
    e.name,
    e.department_id,
    d.department_name,
    e.salary,
    e.hire_date,
    RANK() OVER(PARTITION BY e.department_id ORDER BY e.salary DESC) as dept_salary_rank,
    AVG(e.salary) OVER(PARTITION BY e.department_id) as dept_avg_salary,
    e.salary - AVG(e.salary) OVER(PARTITION BY e.department_id) as salary_diff_from_dept_avg
FROM employees e
JOIN departments d ON e.department_id = d.department_id;

-- Top 3 earners per department
WITH dept_rankings AS (
    SELECT 
        employee_id,
        name,
        department_id,
        salary,
        RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_rank
    FROM employees
)
SELECT * FROM dept_rankings WHERE dept_rank <= 3;

-- Department ranking with salary percentiles
SELECT 
    employee_id,
    name,
    department_id,
    salary,
    RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_rank,
    PERCENT_RANK() OVER(PARTITION BY department_id ORDER BY salary) as dept_salary_percentile,
    NTILE(4) OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_salary_quartile
FROM employees;

-- Department ranking with multiple criteria
SELECT 
    employee_id,
    name,
    department_id,
    salary,
    performance_rating,
    RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) as salary_rank,
    RANK() OVER(PARTITION BY department_id ORDER BY performance_rating DESC, salary DESC) as performance_salary_rank
FROM employees;

-- Department ranking with time-based analysis
SELECT 
    employee_id,
    name,
    department_id,
    salary,
    hire_date,
    RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) as current_salary_rank,
    RANK() OVER(PARTITION BY department_id ORDER BY hire_date) as seniority_rank,
    DATEDIFF(CURRENT_DATE, hire_date) / 365.25 as years_of_service
FROM employees;

-- Department ranking with salary bands
SELECT 
    employee_id,
    name,
    department_id,
    salary,
    RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_rank,
    CASE 
        WHEN RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) = 1 THEN 'Top Earner'
        WHEN RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) <= 3 THEN 'High Earner'
        WHEN RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) <= 10 THEN 'Above Average'
        ELSE 'Standard'
    END as salary_band
FROM employees;

-- Department ranking with comparison to overall ranking
SELECT 
    employee_id,
    name,
    department_id,
    salary,
    RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_rank,
    RANK() OVER(ORDER BY salary DESC) as overall_rank,
    RANK() OVER(ORDER BY salary DESC) - RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) as rank_difference
FROM employees;

-- Department ranking with salary distribution analysis
SELECT 
    employee_id,
    name,
    department_id,
    salary,
    RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_rank,
    salary / MAX(salary) OVER(PARTITION BY department_id) * 100 as salary_percent_of_dept_max,
    salary / AVG(salary) OVER(PARTITION BY department_id) as salary_multiple_of_dept_avg
FROM employees;

-- Department ranking with performance tiers
SELECT 
    employee_id,
    name,
    department_id,
    salary,
    performance_rating,
    RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_salary_rank,
    CASE 
        WHEN performance_rating >= 4.5 AND RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) <= 3 THEN 'Top Performer'
        WHEN performance_rating >= 4.0 AND RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) <= 5 THEN 'High Performer'
        WHEN performance_rating >= 3.5 THEN 'Good Performer'
        ELSE 'Needs Improvement'
    END as performance_category
FROM employees;

-- Department ranking with salary growth analysis
SELECT 
    employee_id,
    name,
    department_id,
    current_salary,
    previous_salary,
    RANK() OVER(PARTITION BY department_id ORDER BY current_salary DESC) as current_dept_rank,
    RANK() OVER(PARTITION BY department_id ORDER BY previous_salary DESC) as previous_dept_rank,
    current_dept_rank - RANK() OVER(PARTITION BY department_id ORDER BY previous_salary DESC) as rank_change,
    (current_salary - previous_salary) / previous_salary * 100 as salary_growth_percent
FROM employee_salary_history;

-- Department ranking with market comparison
SELECT 
    e.employee_id,
    e.name,
    e.department_id,
    e.salary,
    m.market_avg_salary,
    RANK() OVER(PARTITION BY e.department_id ORDER BY e.salary DESC) as dept_rank,
    CASE 
        WHEN e.salary > m.market_avg_salary * 1.2 THEN 'Above Market'
        WHEN e.salary < m.market_avg_salary * 0.8 THEN 'Below Market'
        ELSE 'Market Rate'
    END as market_position
FROM employees e
JOIN market_salaries m ON e.department_id = m.department_id;

-- Department ranking with retention risk analysis
SELECT 
    employee_id,
    name,
    department_id,
    salary,
    years_of_service,
    RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_salary_rank,
    CASE 
        WHEN years_of_service > 5 AND RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) > 10 THEN 'Retention Risk'
        WHEN years_of_service < 2 AND RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) <= 5 THEN 'High Potential'
        ELSE 'Stable'
    END as retention_status
FROM employees;

-- Department ranking with budget analysis
SELECT 
    department_id,
    employee_id,
    name,
    salary,
    RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_rank,
    SUM(salary) OVER(PARTITION BY department_id) as dept_total_budget,
    salary / SUM(salary) OVER(PARTITION BY department_id) * 100 as salary_percent_of_dept_budget
FROM employees;

-- Department ranking with promotion eligibility
SELECT 
    employee_id,
    name,
    department_id,
    salary,
    years_of_service,
    RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) as dept_salary_rank,
    CASE 
        WHEN years_of_service >= 3 AND RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) <= 5 THEN 'Promotion Ready'
        WHEN years_of_service >= 2 AND RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) <= 10 THEN 'Developing'
        ELSE 'Early Career'
    END as promotion_status
FROM employees;
```

### Tough Questions

1. How would you use window functions to identify gaps in sequential data (e.g., missing invoice numbers)? _(Asked in Deloitte)_

**🧩 Foundation:** Use LAG() and LEAD() functions to compare current values with previous/next values to identify missing sequences.

**⚙️ Function:** Detect data integrity issues, missing records, and gaps in sequential identifiers for audit and quality control.

**🔁 Flow:**
```sql
-- Basic gap detection using LAG
SELECT 
    invoice_number,
    LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as prev_invoice,
    invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as gap_size
FROM invoices
WHERE invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) > 1;

-- Gap detection with range identification
WITH gap_analysis AS (
    SELECT 
        invoice_number,
        LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as prev_invoice,
        LEAD(invoice_number, 1) OVER(ORDER BY invoice_number) as next_invoice,
        invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as gap_size
    FROM invoices
)
SELECT 
    prev_invoice + 1 as gap_start,
    invoice_number - 1 as gap_end,
    gap_size - 1 as missing_count,
    CONCAT(prev_invoice + 1, ' to ', invoice_number - 1) as gap_range
FROM gap_analysis
WHERE gap_size > 1;

-- Gap detection with multiple sequences
SELECT 
    sequence_type,
    sequence_number,
    LAG(sequence_number, 1) OVER(PARTITION BY sequence_type ORDER BY sequence_number) as prev_number,
    sequence_number - LAG(sequence_number, 1) OVER(PARTITION BY sequence_type ORDER BY sequence_number) as gap_size
FROM sequences
WHERE sequence_number - LAG(sequence_number, 1) OVER(PARTITION BY sequence_type ORDER BY sequence_number) > 1;

-- Gap detection with date-based sequences
SELECT 
    order_date,
    order_id,
    LAG(order_id, 1) OVER(ORDER BY order_date) as prev_order_id,
    order_id - LAG(order_id, 1) OVER(ORDER BY order_date) as gap_size,
    DATEDIFF(order_date, LAG(order_date, 1) OVER(ORDER BY order_date)) as days_since_prev
FROM orders
WHERE order_id - LAG(order_id, 1) OVER(ORDER BY order_date) > 1;

-- Gap detection with business logic
SELECT 
    invoice_number,
    invoice_date,
    LAG(invoice_number, 1) OVER(ORDER BY invoice_date) as prev_invoice,
    LAG(invoice_date, 1) OVER(ORDER BY invoice_date) as prev_date,
    invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_date) as gap_size,
    CASE 
        WHEN invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_date) > 10 THEN 'Large Gap'
        WHEN invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_date) > 5 THEN 'Medium Gap'
        ELSE 'Small Gap'
    END as gap_severity
FROM invoices
WHERE invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_date) > 1;

-- Gap detection with customer-specific sequences
SELECT 
    customer_id,
    invoice_number,
    LAG(invoice_number, 1) OVER(PARTITION BY customer_id ORDER BY invoice_number) as prev_invoice,
    invoice_number - LAG(invoice_number, 1) OVER(PARTITION BY customer_id ORDER BY invoice_number) as gap_size
FROM invoices
WHERE invoice_number - LAG(invoice_number, 1) OVER(PARTITION BY customer_id ORDER BY invoice_number) > 1;

-- Gap detection with time-based analysis
SELECT 
    invoice_number,
    invoice_date,
    LAG(invoice_number, 1) OVER(ORDER BY invoice_date) as prev_invoice,
    LAG(invoice_date, 1) OVER(ORDER BY invoice_date) as prev_date,
    invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_date) as gap_size,
    DATEDIFF(invoice_date, LAG(invoice_date, 1) OVER(ORDER BY invoice_date)) as days_between_invoices
FROM invoices
WHERE invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_date) > 1
  AND DATEDIFF(invoice_date, LAG(invoice_date, 1) OVER(ORDER BY invoice_date)) > 30;

-- Gap detection with statistical analysis
SELECT 
    invoice_number,
    LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as prev_invoice,
    LEAD(invoice_number, 1) OVER(ORDER BY invoice_number) as next_invoice,
    invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as gap_size,
    AVG(invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number)) OVER() as avg_gap_size,
    CASE 
        WHEN invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) > 
             AVG(invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number)) OVER() * 2 THEN 'Unusual Gap'
        ELSE 'Normal Gap'
    END as gap_analysis
FROM invoices
WHERE invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) > 1;

-- Gap detection with missing value generation
WITH gap_ranges AS (
    SELECT 
        invoice_number,
        LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as prev_invoice,
        invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as gap_size
    FROM invoices
    WHERE invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) > 1
),
missing_numbers AS (
    SELECT 
        prev_invoice + n as missing_invoice_number
    FROM gap_ranges
    CROSS JOIN (
        SELECT 1 as n UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5
        UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10
    ) numbers
    WHERE n < gap_size
)
SELECT DISTINCT missing_invoice_number
FROM missing_numbers
ORDER BY missing_invoice_number;

-- Gap detection with audit trail
SELECT 
    invoice_number,
    invoice_date,
    created_by,
    LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as prev_invoice,
    LAG(created_by, 1) OVER(ORDER BY invoice_number) as prev_created_by,
    invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as gap_size,
    CASE 
        WHEN LAG(created_by, 1) OVER(ORDER BY invoice_number) != created_by THEN 'User Change'
        ELSE 'Same User'
    END as user_analysis
FROM invoices
WHERE invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) > 1;

-- Gap detection with performance metrics
SELECT 
    invoice_number,
    invoice_amount,
    LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as prev_invoice,
    LAG(invoice_amount, 1) OVER(ORDER BY invoice_number) as prev_amount,
    invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as gap_size,
    invoice_amount - LAG(invoice_amount, 1) OVER(ORDER BY invoice_number) as amount_change
FROM invoices
WHERE invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) > 1;

-- Gap detection with data quality scoring
SELECT 
    invoice_number,
    LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as prev_invoice,
    invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as gap_size,
    CASE 
        WHEN invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) = 1 THEN 100
        WHEN invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) <= 3 THEN 80
        WHEN invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) <= 5 THEN 60
        ELSE 40
    END as data_quality_score
FROM invoices;

-- Gap detection with trend analysis
SELECT 
    invoice_number,
    invoice_date,
    LAG(invoice_number, 1) OVER(ORDER BY invoice_date) as prev_invoice,
    LAG(invoice_date, 1) OVER(ORDER BY invoice_date) as prev_date,
    invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_date) as gap_size,
    CASE 
        WHEN invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_date) > 
             LAG(invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_date), 1) OVER(ORDER BY invoice_date) THEN 'Increasing Gaps'
        WHEN invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_date) < 
             LAG(invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_date), 1) OVER(ORDER BY invoice_date) THEN 'Decreasing Gaps'
        ELSE 'Stable Gaps'
    END as gap_trend
FROM invoices
WHERE invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_date) > 1;

-- Gap detection with business impact analysis
SELECT 
    invoice_number,
    invoice_amount,
    LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as prev_invoice,
    LAG(invoice_amount, 1) OVER(ORDER BY invoice_number) as prev_amount,
    invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as gap_size,
    SUM(invoice_amount) OVER(ORDER BY invoice_number) as cumulative_amount,
    CASE 
        WHEN invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) > 10 THEN 'High Impact Gap'
        WHEN invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) > 5 THEN 'Medium Impact Gap'
        ELSE 'Low Impact Gap'
    END as business_impact
FROM invoices
WHERE invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) > 1;
```

### Tough Query Questions

1. Write a query to find gaps in invoice numbers using a window function. _(Asked in Deloitte)_

**🧩 Foundation:** Use LAG() and LEAD() functions with CTEs to identify missing invoice numbers and generate the complete list of missing values.

**⚙️ Function:** Create a comprehensive gap analysis system to identify, quantify, and report missing invoice numbers for audit and reconciliation purposes.

**🔁 Flow:**
```sql
-- Comprehensive gap detection with missing values
WITH invoice_sequence AS (
    SELECT 
        invoice_number,
        invoice_date,
        LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as prev_invoice,
        LEAD(invoice_number, 1) OVER(ORDER BY invoice_number) as next_invoice,
        invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as gap_size
    FROM invoices
),
gap_analysis AS (
    SELECT 
        prev_invoice + 1 as gap_start,
        invoice_number - 1 as gap_end,
        gap_size - 1 as missing_count,
        CONCAT(prev_invoice + 1, ' to ', invoice_number - 1) as gap_range
    FROM invoice_sequence
    WHERE gap_size > 1
)
SELECT * FROM gap_analysis ORDER BY gap_start;

-- Generate all missing invoice numbers
WITH RECURSIVE missing_invoices AS (
    SELECT 
        invoice_number,
        LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as prev_invoice,
        invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as gap_size
    FROM invoices
    WHERE invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) > 1
    
    UNION ALL
    
    SELECT 
        prev_invoice + 1 as missing_number,
        prev_invoice,
        gap_size - 1
    FROM missing_invoices
    WHERE gap_size > 1
)
SELECT DISTINCT prev_invoice + 1 as missing_invoice_number
FROM missing_invoices
ORDER BY missing_invoice_number;

-- Gap analysis with business context
WITH gap_details AS (
    SELECT 
        invoice_number,
        invoice_date,
        customer_id,
        invoice_amount,
        LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as prev_invoice,
        LAG(invoice_date, 1) OVER(ORDER BY invoice_number) as prev_date,
        LAG(customer_id, 1) OVER(ORDER BY invoice_number) as prev_customer,
        invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as gap_size
    FROM invoices
)
SELECT 
    prev_invoice + 1 as gap_start,
    invoice_number - 1 as gap_end,
    gap_size - 1 as missing_count,
    DATEDIFF(invoice_date, prev_date) as days_between_invoices,
    CASE 
        WHEN prev_customer = customer_id THEN 'Same Customer'
        ELSE 'Different Customer'
    END as customer_analysis,
    CASE 
        WHEN gap_size > 10 THEN 'Critical Gap'
        WHEN gap_size > 5 THEN 'Significant Gap'
        ELSE 'Minor Gap'
    END as gap_severity
FROM gap_details
WHERE gap_size > 1;

-- Gap analysis with financial impact
WITH financial_gaps AS (
    SELECT 
        invoice_number,
        invoice_amount,
        LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as prev_invoice,
        LAG(invoice_amount, 1) OVER(ORDER BY invoice_number) as prev_amount,
        invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as gap_size,
        SUM(invoice_amount) OVER(ORDER BY invoice_number) as cumulative_amount
    FROM invoices
)
SELECT 
    prev_invoice + 1 as gap_start,
    invoice_number - 1 as gap_end,
    gap_size - 1 as missing_count,
    (prev_amount + invoice_amount) / 2 as estimated_missing_amount,
    (gap_size - 1) * (prev_amount + invoice_amount) / 2 as total_estimated_impact
FROM financial_gaps
WHERE gap_size > 1;

-- Gap analysis with time-based patterns
WITH time_gaps AS (
    SELECT 
        invoice_number,
        invoice_date,
        LAG(invoice_number, 1) OVER(ORDER BY invoice_date) as prev_invoice,
        LAG(invoice_date, 1) OVER(ORDER BY invoice_date) as prev_date,
        invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_date) as gap_size,
        DATEDIFF(invoice_date, LAG(invoice_date, 1) OVER(ORDER BY invoice_date)) as days_between
    FROM invoices
)
SELECT 
    prev_invoice + 1 as gap_start,
    invoice_number - 1 as gap_end,
    gap_size - 1 as missing_count,
    days_between,
    CASE 
        WHEN days_between > 30 THEN 'Long Gap'
        WHEN days_between > 7 THEN 'Medium Gap'
        ELSE 'Short Gap'
    END as time_analysis,
    CASE 
        WHEN WEEKDAY(prev_date) = 5 OR WEEKDAY(prev_date) = 6 THEN 'Weekend Gap'
        ELSE 'Weekday Gap'
    END as day_type_analysis
FROM time_gaps
WHERE gap_size > 1;

-- Gap analysis with statistical outliers
WITH statistical_gaps AS (
    SELECT 
        invoice_number,
        LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as prev_invoice,
        invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as gap_size,
        AVG(invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number)) OVER() as avg_gap,
        STDDEV(invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number)) OVER() as std_gap
    FROM invoices
)
SELECT 
    prev_invoice + 1 as gap_start,
    invoice_number - 1 as gap_end,
    gap_size - 1 as missing_count,
    gap_size as actual_gap,
    avg_gap,
    std_gap,
    (gap_size - avg_gap) / std_gap as z_score,
    CASE 
        WHEN ABS((gap_size - avg_gap) / std_gap) > 2 THEN 'Statistical Outlier'
        ELSE 'Normal Gap'
    END as outlier_analysis
FROM statistical_gaps
WHERE gap_size > 1;

-- Gap analysis with data quality metrics
WITH quality_gaps AS (
    SELECT 
        invoice_number,
        invoice_date,
        created_by,
        LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as prev_invoice,
        LAG(created_by, 1) OVER(ORDER BY invoice_number) as prev_created_by,
        invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as gap_size
    FROM invoices
)
SELECT 
    prev_invoice + 1 as gap_start,
    invoice_number - 1 as gap_end,
    gap_size - 1 as missing_count,
    CASE 
        WHEN prev_created_by = created_by THEN 'Same User'
        ELSE 'User Change'
    END as user_consistency,
    CASE 
        WHEN gap_size = 1 THEN 100
        WHEN gap_size <= 3 THEN 80
        WHEN gap_size <= 5 THEN 60
        ELSE 40
    END as data_quality_score
FROM quality_gaps
WHERE gap_size > 1;

-- Gap analysis with audit trail
WITH audit_gaps AS (
    SELECT 
        invoice_number,
        invoice_date,
        created_by,
        created_at,
        LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as prev_invoice,
        LAG(created_at, 1) OVER(ORDER BY invoice_number) as prev_created_at,
        invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as gap_size,
        TIMESTAMPDIFF(MINUTE, LAG(created_at, 1) OVER(ORDER BY invoice_number), created_at) as minutes_between_creation
    FROM invoices
)
SELECT 
    prev_invoice + 1 as gap_start,
    invoice_number - 1 as gap_end,
    gap_size - 1 as missing_count,
    minutes_between_creation,
    CASE 
        WHEN minutes_between_creation < 60 THEN 'Quick Succession'
        WHEN minutes_between_creation < 1440 THEN 'Same Day'
        ELSE 'Different Day'
    END as creation_pattern
FROM audit_gaps
WHERE gap_size > 1;

-- Gap analysis with business rules validation
WITH business_gaps AS (
    SELECT 
        invoice_number,
        invoice_date,
        customer_id,
        invoice_type,
        LAG(invoice_number, 1) OVER(PARTITION BY customer_id ORDER BY invoice_number) as prev_invoice,
        LAG(invoice_type, 1) OVER(PARTITION BY customer_id ORDER BY invoice_number) as prev_type,
        invoice_number - LAG(invoice_number, 1) OVER(PARTITION BY customer_id ORDER BY invoice_number) as gap_size
    FROM invoices
)
SELECT 
    customer_id,
    prev_invoice + 1 as gap_start,
    invoice_number - 1 as gap_end,
    gap_size - 1 as missing_count,
    prev_type,
    invoice_type,
    CASE 
        WHEN prev_type = invoice_type THEN 'Consistent Type'
        ELSE 'Type Change'
    END as type_analysis
FROM business_gaps
WHERE gap_size > 1;

-- Gap analysis with performance impact
WITH performance_gaps AS (
    SELECT 
        invoice_number,
        invoice_amount,
        processing_time_minutes,
        LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as prev_invoice,
        LAG(processing_time_minutes, 1) OVER(ORDER BY invoice_number) as prev_processing_time,
        invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as gap_size
    FROM invoices
)
SELECT 
    prev_invoice + 1 as gap_start,
    invoice_number - 1 as gap_end,
    gap_size - 1 as missing_count,
    prev_processing_time,
    processing_time_minutes,
    CASE 
        WHEN processing_time_minutes > prev_processing_time * 2 THEN 'Performance Degradation'
        WHEN processing_time_minutes < prev_processing_time * 0.5 THEN 'Performance Improvement'
        ELSE 'Stable Performance'
    END as performance_analysis
FROM performance_gaps
WHERE gap_size > 1;

-- Gap analysis with forecasting
WITH forecast_gaps AS (
    SELECT 
        invoice_number,
        invoice_date,
        LAG(invoice_number, 1) OVER(ORDER BY invoice_date) as prev_invoice,
        LAG(invoice_date, 1) OVER(ORDER BY invoice_date) as prev_date,
        invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_date) as gap_size,
        AVG(invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_date)) OVER(
            ORDER BY invoice_date ROWS BETWEEN 10 PRECEDING AND CURRENT ROW
        ) as moving_avg_gap
    FROM invoices
)
SELECT 
    prev_invoice + 1 as gap_start,
    invoice_number - 1 as gap_end,
    gap_size - 1 as missing_count,
    moving_avg_gap,
    CASE 
        WHEN gap_size > moving_avg_gap * 1.5 THEN 'Above Trend'
        WHEN gap_size < moving_avg_gap * 0.5 THEN 'Below Trend'
        ELSE 'On Trend'
    END as trend_analysis
FROM forecast_gaps
WHERE gap_size > 1;

-- Gap analysis with comprehensive reporting
WITH comprehensive_gaps AS (
    SELECT 
        invoice_number,
        invoice_date,
        customer_id,
        invoice_amount,
        LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as prev_invoice,
        LAG(invoice_date, 1) OVER(ORDER BY invoice_number) as prev_date,
        LAG(customer_id, 1) OVER(ORDER BY invoice_number) as prev_customer,
        LAG(invoice_amount, 1) OVER(ORDER BY invoice_number) as prev_amount,
        invoice_number - LAG(invoice_number, 1) OVER(ORDER BY invoice_number) as gap_size
    FROM invoices
)
SELECT 
    prev_invoice + 1 as gap_start,
    invoice_number - 1 as gap_end,
    gap_size - 1 as missing_count,
    DATEDIFF(invoice_date, prev_date) as days_between,
    (prev_amount + invoice_amount) / 2 as estimated_missing_amount,
    CASE 
        WHEN prev_customer = customer_id THEN 'Same Customer'
        ELSE 'Different Customer'
    END as customer_consistency,
    CASE 
        WHEN gap_size > 10 THEN 'Critical'
        WHEN gap_size > 5 THEN 'Significant'
        ELSE 'Minor'
    END as gap_severity,
    CONCAT('Missing ', gap_size - 1, ' invoices from ', prev_invoice + 1, ' to ', invoice_number - 1) as gap_description
FROM comprehensive_gaps
WHERE gap_size > 1
ORDER BY gap_size DESC, gap_start;
```

### Situational / Real-world Questions

1. You need to rank employees by performance within each department. How would you write the query using window functions? _(Asked in HCL)_

**🧩 Foundation:** Use RANK() or DENSE_RANK() with PARTITION BY department_id and ORDER BY performance metrics to create department-specific rankings.

**⚙️ Function:** Evaluate employee performance relative to their department peers for promotions, bonuses, and performance management.

**🔁 Flow:**
```sql
-- Basic performance ranking by department
SELECT 
    employee_id,
    name,
    department_id,
    performance_score,
    RANK() OVER(PARTITION BY department_id ORDER BY performance_score DESC) as dept_performance_rank
FROM employees;

-- Performance ranking with multiple metrics
SELECT 
    employee_id,
    name,
    department_id,
    performance_score,
    sales_amount,
    customer_satisfaction,
    RANK() OVER(PARTITION BY department_id ORDER BY performance_score DESC) as overall_rank,
    RANK() OVER(PARTITION BY department_id ORDER BY sales_amount DESC) as sales_rank,
    RANK() OVER(PARTITION BY department_id ORDER BY customer_satisfaction DESC) as satisfaction_rank
FROM employees;

-- Performance ranking with weighted scoring
SELECT 
    employee_id,
    name,
    department_id,
    performance_score,
    sales_amount,
    customer_satisfaction,
    (performance_score * 0.4 + sales_amount * 0.4 + customer_satisfaction * 0.2) as weighted_score,
    RANK() OVER(PARTITION BY department_id ORDER BY (performance_score * 0.4 + sales_amount * 0.4 + customer_satisfaction * 0.2) DESC) as weighted_rank
FROM employees;

-- Performance ranking with time-based analysis
SELECT 
    employee_id,
    name,
    department_id,
    current_performance,
    previous_performance,
    RANK() OVER(PARTITION BY department_id ORDER BY current_performance DESC) as current_rank,
    RANK() OVER(PARTITION BY department_id ORDER BY previous_performance DESC) as previous_rank,
    RANK() OVER(PARTITION BY department_id ORDER BY previous_performance DESC) - RANK() OVER(PARTITION BY department_id ORDER BY current_performance DESC) as rank_improvement
FROM employee_performance_history;

-- Performance ranking with percentile analysis
SELECT 
    employee_id,
    name,
    department_id,
    performance_score,
    RANK() OVER(PARTITION BY department_id ORDER BY performance_score DESC) as dept_rank,
    PERCENT_RANK() OVER(PARTITION BY department_id ORDER BY performance_score) as dept_percentile,
    NTILE(4) OVER(PARTITION BY department_id ORDER BY performance_score DESC) as dept_quartile
FROM employees;

-- Performance ranking with performance tiers
SELECT 
    employee_id,
    name,
    department_id,
    performance_score,
    RANK() OVER(PARTITION BY department_id ORDER BY performance_score DESC) as dept_rank,
    CASE 
        WHEN RANK() OVER(PARTITION BY department_id ORDER BY performance_score DESC) <= 3 THEN 'Top Performer'
        WHEN RANK() OVER(PARTITION BY department_id ORDER BY performance_score DESC) <= 10 THEN 'High Performer'
        WHEN RANK() OVER(PARTITION BY department_id ORDER BY performance_score DESC) <= 20 THEN 'Average Performer'
        ELSE 'Needs Improvement'
    END as performance_tier
FROM employees;

-- Performance ranking with department comparison
SELECT 
    employee_id,
    name,
    department_id,
    performance_score,
    RANK() OVER(PARTITION BY department_id ORDER BY performance_score DESC) as dept_rank,
    RANK() OVER(ORDER BY performance_score DESC) as overall_rank,
    performance_score - AVG(performance_score) OVER(PARTITION BY department_id) as score_vs_dept_avg,
    performance_score / AVG(performance_score) OVER(PARTITION BY department_id) * 100 as percent_of_dept_avg
FROM employees;

-- Performance ranking with year-over-year analysis
SELECT 
    employee_id,
    name,
    department_id,
    current_year_performance,
    previous_year_performance,
    RANK() OVER(PARTITION BY department_id ORDER BY current_year_performance DESC) as current_year_rank,
    RANK() OVER(PARTITION BY department_id ORDER BY previous_year_performance DESC) as previous_year_rank,
    (current_year_performance - previous_year_performance) / previous_year_performance * 100 as performance_growth_percent
FROM employee_yearly_performance;

-- Performance ranking with promotion eligibility
SELECT 
    employee_id,
    name,
    department_id,
    performance_score,
    years_of_service,
    RANK() OVER(PARTITION BY department_id ORDER BY performance_score DESC) as dept_rank,
    CASE 
        WHEN years_of_service >= 3 AND RANK() OVER(PARTITION BY department_id ORDER BY performance_score DESC) <= 5 THEN 'Promotion Ready'
        WHEN years_of_service >= 2 AND RANK() OVER(PARTITION BY department_id ORDER BY performance_score DESC) <= 10 THEN 'Developing'
        ELSE 'Early Career'
    END as promotion_status
FROM employees;

-- Performance ranking with bonus calculation
SELECT 
    employee_id,
    name,
    department_id,
    performance_score,
    base_salary,
    RANK() OVER(PARTITION BY department_id ORDER BY performance_score DESC) as dept_rank,
    CASE 
        WHEN RANK() OVER(PARTITION BY department_id ORDER BY performance_score DESC) = 1 THEN base_salary * 0.15
        WHEN RANK() OVER(PARTITION BY department_id ORDER BY performance_score DESC) <= 3 THEN base_salary * 0.10
        WHEN RANK() OVER(PARTITION BY department_id ORDER BY performance_score DESC) <= 10 THEN base_salary * 0.05
        ELSE 0
    END as bonus_amount
FROM employees;

-- Performance ranking with retention risk analysis
SELECT 
    employee_id,
    name,
    department_id,
    performance_score,
    salary,
    RANK() OVER(PARTITION BY department_id ORDER BY performance_score DESC) as dept_rank,
    CASE 
        WHEN RANK() OVER(PARTITION BY department_id ORDER BY performance_score DESC) <= 5 AND salary < AVG(salary) OVER(PARTITION BY department_id) THEN 'Retention Risk - High Performer, Low Pay'
        WHEN RANK() OVER(PARTITION BY department_id ORDER BY performance_score DESC) > 20 THEN 'Performance Risk'
        ELSE 'Stable'
    END as risk_status
FROM employees;

-- Performance ranking with team analysis
SELECT 
    employee_id,
    name,
    department_id,
    team_id,
    performance_score,
    RANK() OVER(PARTITION BY department_id ORDER BY performance_score DESC) as dept_rank,
    RANK() OVER(PARTITION BY team_id ORDER BY performance_score DESC) as team_rank,
    AVG(performance_score) OVER(PARTITION BY team_id) as team_avg_performance,
    performance_score - AVG(performance_score) OVER(PARTITION BY team_id) as team_performance_diff
FROM employees;

-- Performance ranking with quarterly analysis
SELECT 
    employee_id,
    name,
    department_id,
    quarter,
    performance_score,
    RANK() OVER(PARTITION BY department_id, quarter ORDER BY performance_score DESC) as quarterly_dept_rank,
    RANK() OVER(PARTITION BY department_id ORDER BY performance_score DESC) as overall_dept_rank,
    LAG(performance_score, 1) OVER(PARTITION BY employee_id ORDER BY quarter) as prev_quarter_score
FROM employee_quarterly_performance;

-- Performance ranking with comprehensive evaluation
SELECT 
    e.employee_id,
    e.name,
    e.department_id,
    d.department_name,
    e.performance_score,
    e.sales_amount,
    e.customer_satisfaction,
    e.attendance_rate,
    (e.performance_score * 0.3 + e.sales_amount * 0.3 + e.customer_satisfaction * 0.2 + e.attendance_rate * 0.2) as composite_score,
    RANK() OVER(PARTITION BY e.department_id ORDER BY (e.performance_score * 0.3 + e.sales_amount * 0.3 + e.customer_satisfaction * 0.2 + e.attendance_rate * 0.2) DESC) as dept_rank,
    RANK() OVER(ORDER BY (e.performance_score * 0.3 + e.sales_amount * 0.3 + e.customer_satisfaction * 0.2 + e.attendance_rate * 0.2) DESC) as overall_rank
FROM employees e
JOIN departments d ON e.department_id = d.department_id;
```

2. Write a query to calculate the percentage contribution of each product to total sales using window functions. _(Asked in HCL)_

**🧩 Foundation:** Use SUM() with OVER() to calculate total sales and divide individual product sales by the total to get percentage contribution.

**⚙️ Function:** Analyze product performance, identify top contributors, and make inventory and marketing decisions based on sales contribution.

**🔁 Flow:**
```sql
-- Basic percentage contribution calculation
SELECT 
    product_id,
    product_name,
    sales_amount,
    SUM(sales_amount) OVER() as total_sales,
    sales_amount / SUM(sales_amount) OVER() * 100 as contribution_percentage
FROM products;

-- Percentage contribution with ranking
SELECT 
    product_id,
    product_name,
    sales_amount,
    SUM(sales_amount) OVER() as total_sales,
    sales_amount / SUM(sales_amount) OVER() * 100 as contribution_percentage,
    RANK() OVER(ORDER BY sales_amount DESC) as sales_rank
FROM products;

-- Percentage contribution by category
SELECT 
    product_id,
    product_name,
    category,
    sales_amount,
    SUM(sales_amount) OVER() as total_sales,
    SUM(sales_amount) OVER(PARTITION BY category) as category_total,
    sales_amount / SUM(sales_amount) OVER() * 100 as overall_contribution_percentage,
    sales_amount / SUM(sales_amount) OVER(PARTITION BY category) * 100 as category_contribution_percentage
FROM products;

-- Percentage contribution with cumulative analysis
SELECT 
    product_id,
    product_name,
    sales_amount,
    SUM(sales_amount) OVER() as total_sales,
    sales_amount / SUM(sales_amount) OVER() * 100 as contribution_percentage,
    SUM(sales_amount) OVER(ORDER BY sales_amount DESC) as cumulative_sales,
    SUM(sales_amount) OVER(ORDER BY sales_amount DESC) / SUM(sales_amount) OVER() * 100 as cumulative_percentage
FROM products;

-- Percentage contribution with time-based analysis
SELECT 
    product_id,
    product_name,
    month,
    monthly_sales,
    SUM(monthly_sales) OVER(PARTITION BY product_id) as product_total_sales,
    SUM(monthly_sales) OVER(PARTITION BY month) as monthly_total_sales,
    monthly_sales / SUM(monthly_sales) OVER(PARTITION BY month) * 100 as monthly_contribution_percentage,
    SUM(monthly_sales) OVER(PARTITION BY product_id) / SUM(monthly_sales) OVER() * 100 as overall_contribution_percentage
FROM product_monthly_sales;

-- Percentage contribution with performance tiers
SELECT 
    product_id,
    product_name,
    sales_amount,
    SUM(sales_amount) OVER() as total_sales,
    sales_amount / SUM(sales_amount) OVER() * 100 as contribution_percentage,
    CASE 
        WHEN sales_amount / SUM(sales_amount) OVER() * 100 >= 10 THEN 'Top Contributor'
        WHEN sales_amount / SUM(sales_amount) OVER() * 100 >= 5 THEN 'Major Contributor'
        WHEN sales_amount / SUM(sales_amount) OVER() * 100 >= 1 THEN 'Moderate Contributor'
        ELSE 'Minor Contributor'
    END as contribution_tier
FROM products;

-- Percentage contribution with profit analysis
SELECT 
    p.product_id,
    p.product_name,
    p.sales_amount,
    p.profit_amount,
    SUM(p.sales_amount) OVER() as total_sales,
    SUM(p.profit_amount) OVER() as total_profit,
    p.sales_amount / SUM(p.sales_amount) OVER() * 100 as sales_contribution_percentage,
    p.profit_amount / SUM(p.profit_amount) OVER() * 100 as profit_contribution_percentage,
    p.profit_amount / p.sales_amount * 100 as profit_margin
FROM products p;

-- Percentage contribution with regional analysis
SELECT 
    p.product_id,
    p.product_name,
    r.region,
    p.sales_amount,
    SUM(p.sales_amount) OVER() as total_sales,
    SUM(p.sales_amount) OVER(PARTITION BY r.region) as regional_total_sales,
    p.sales_amount / SUM(p.sales_amount) OVER() * 100 as overall_contribution_percentage,
    p.sales_amount / SUM(p.sales_amount) OVER(PARTITION BY r.region) * 100 as regional_contribution_percentage
FROM products p
JOIN regions r ON p.region_id = r.region_id;

-- Percentage contribution with customer analysis
SELECT 
    p.product_id,
    p.product_name,
    c.customer_segment,
    p.sales_amount,
    SUM(p.sales_amount) OVER() as total_sales,
    SUM(p.sales_amount) OVER(PARTITION BY c.customer_segment) as segment_total_sales,
    p.sales_amount / SUM(p.sales_amount) OVER() * 100 as overall_contribution_percentage,
    p.sales_amount / SUM(p.sales_amount) OVER(PARTITION BY c.customer_segment) * 100 as segment_contribution_percentage
FROM products p
JOIN customers c ON p.customer_id = c.customer_id;

-- Percentage contribution with trend analysis
SELECT 
    product_id,
    product_name,
    month,
    monthly_sales,
    LAG(monthly_sales, 1) OVER(PARTITION BY product_id ORDER BY month) as prev_month_sales,
    monthly_sales / SUM(monthly_sales) OVER(PARTITION BY month) * 100 as monthly_contribution_percentage,
    (monthly_sales - LAG(monthly_sales, 1) OVER(PARTITION BY product_id ORDER BY month)) / LAG(monthly_sales, 1) OVER(PARTITION BY product_id ORDER BY month) * 100 as month_over_month_growth
FROM product_monthly_sales;

-- Percentage contribution with inventory analysis
SELECT 
    p.product_id,
    p.product_name,
    p.sales_amount,
    p.inventory_level,
    SUM(p.sales_amount) OVER() as total_sales,
    p.sales_amount / SUM(p.sales_amount) OVER() * 100 as contribution_percentage,
    p.sales_amount / p.inventory_level as inventory_turnover,
    CASE 
        WHEN p.sales_amount / p.inventory_level > 10 THEN 'High Turnover'
        WHEN p.sales_amount / p.inventory_level > 5 THEN 'Medium Turnover'
        ELSE 'Low Turnover'
    END as turnover_category
FROM products p;

-- Percentage contribution with seasonality analysis
SELECT 
    product_id,
    product_name,
    quarter,
    quarterly_sales,
    SUM(quarterly_sales) OVER(PARTITION BY product_id) as product_total_sales,
    SUM(quarterly_sales) OVER(PARTITION BY quarter) as quarterly_total_sales,
    quarterly_sales / SUM(quarterly_sales) OVER(PARTITION BY quarter) * 100 as quarterly_contribution_percentage,
    quarterly_sales / SUM(quarterly_sales) OVER(PARTITION BY product_id) * 100 as product_quarterly_distribution
FROM product_quarterly_sales;

-- Percentage contribution with marketing analysis
SELECT 
    p.product_id,
    p.product_name,
    p.sales_amount,
    p.marketing_spend,
    SUM(p.sales_amount) OVER() as total_sales,
    SUM(p.marketing_spend) OVER() as total_marketing_spend,
    p.sales_amount / SUM(p.sales_amount) OVER() * 100 as sales_contribution_percentage,
    p.marketing_spend / SUM(p.marketing_spend) OVER() * 100 as marketing_spend_percentage,
    p.sales_amount / p.marketing_spend as marketing_roi
FROM products p;

-- Percentage contribution with comprehensive analysis
SELECT 
    p.product_id,
    p.product_name,
    p.category,
    p.sales_amount,
    p.profit_amount,
    p.inventory_level,
    SUM(p.sales_amount) OVER() as total_sales,
    SUM(p.profit_amount) OVER() as total_profit,
    p.sales_amount / SUM(p.sales_amount) OVER() * 100 as sales_contribution_percentage,
    p.profit_amount / SUM(p.profit_amount) OVER() * 100 as profit_contribution_percentage,
    RANK() OVER(ORDER BY p.sales_amount DESC) as sales_rank,
    RANK() OVER(ORDER BY p.profit_amount DESC) as profit_rank,
    CASE 
        WHEN p.sales_amount / SUM(p.sales_amount) OVER() * 100 >= 5 AND p.profit_amount / SUM(p.profit_amount) OVER() * 100 >= 5 THEN 'Star Product'
        WHEN p.sales_amount / SUM(p.sales_amount) OVER() * 100 >= 5 THEN 'High Volume, Low Margin'
        WHEN p.profit_amount / SUM(p.profit_amount) OVER() * 100 >= 5 THEN 'High Margin, Low Volume'
        ELSE 'Standard Product'
    END as product_category
FROM products p;
```

---

## 11. Error Handling and Debugging

### Basic Questions

1. How do you handle errors in SQL queries or stored procedures? _(Asked in TCS)_

**🧩 Foundation:** Use TRY-CATCH blocks, error handling functions, and proper error logging to manage exceptions and maintain data integrity.

**⚙️ Function:** Prevent application crashes, maintain data consistency, and provide meaningful error information for debugging and monitoring.

**🔁 Flow:**
```sql
-- Basic TRY-CATCH error handling
BEGIN TRY
    INSERT INTO employees (employee_id, name, salary) 
    VALUES (1, 'John Doe', 50000);
END TRY
BEGIN CATCH
    SELECT 
        ERROR_NUMBER() as error_number,
        ERROR_MESSAGE() as error_message,
        ERROR_LINE() as error_line,
        ERROR_PROCEDURE() as error_procedure;
END CATCH;

-- Error handling with custom error messages
BEGIN TRY
    DECLARE @salary DECIMAL(10,2) = 50000;
    
    IF @salary < 0
        RAISERROR('Salary cannot be negative', 16, 1);
    
    INSERT INTO employees (employee_id, name, salary) 
    VALUES (1, 'John Doe', @salary);
END TRY
BEGIN CATCH
    INSERT INTO error_log (
        error_number, 
        error_message, 
        error_line, 
        error_procedure,
        error_time
    ) VALUES (
        ERROR_NUMBER(),
        ERROR_MESSAGE(),
        ERROR_LINE(),
        ERROR_PROCEDURE(),
        GETDATE()
    );
    
    SELECT 'Error occurred and logged' as status;
END CATCH;

-- Error handling with transaction rollback
BEGIN TRANSACTION;
BEGIN TRY
    INSERT INTO employees (employee_id, name, salary) VALUES (1, 'John Doe', 50000);
    INSERT INTO employee_details (employee_id, department) VALUES (1, 'IT');
    
    COMMIT TRANSACTION;
    SELECT 'Transaction completed successfully' as status;
END TRY
BEGIN CATCH
    ROLLBACK TRANSACTION;
    
    INSERT INTO error_log (
        error_number, 
        error_message, 
        error_line, 
        error_procedure,
        error_time,
        transaction_status
    ) VALUES (
        ERROR_NUMBER(),
        ERROR_MESSAGE(),
        ERROR_LINE(),
        ERROR_PROCEDURE(),
        GETDATE(),
        'ROLLED_BACK'
    );
    
    SELECT 'Transaction rolled back due to error' as status;
END CATCH;

-- Error handling with validation
CREATE PROCEDURE InsertEmployee
    @employee_id INT,
    @name VARCHAR(100),
    @salary DECIMAL(10,2)
AS
BEGIN
    BEGIN TRY
        -- Input validation
        IF @employee_id IS NULL OR @name IS NULL OR @salary IS NULL
            RAISERROR('All parameters must be provided', 16, 1);
        
        IF @salary < 0
            RAISERROR('Salary cannot be negative', 16, 1);
        
        IF EXISTS (SELECT 1 FROM employees WHERE employee_id = @employee_id)
            RAISERROR('Employee ID already exists', 16, 1);
        
        -- Insert operation
        INSERT INTO employees (employee_id, name, salary) 
        VALUES (@employee_id, @name, @salary);
        
        SELECT 'Employee inserted successfully' as status;
    END TRY
    BEGIN CATCH
        INSERT INTO error_log (
            error_number, 
            error_message, 
            error_line, 
            error_procedure,
            error_time,
            input_parameters
        ) VALUES (
            ERROR_NUMBER(),
            ERROR_MESSAGE(),
            ERROR_LINE(),
            ERROR_PROCEDURE(),
            GETDATE(),
            CONCAT('ID:', @employee_id, ', Name:', @name, ', Salary:', @salary)
        );
        
        SELECT 'Error occurred during employee insertion' as status;
    END CATCH
END;

-- Error handling with retry logic
CREATE PROCEDURE InsertEmployeeWithRetry
    @employee_id INT,
    @name VARCHAR(100),
    @salary DECIMAL(10,2)
AS
BEGIN
    DECLARE @retry_count INT = 0;
    DECLARE @max_retries INT = 3;
    DECLARE @success BIT = 0;
    
    WHILE @retry_count < @max_retries AND @success = 0
    BEGIN
        BEGIN TRY
            INSERT INTO employees (employee_id, name, salary) 
            VALUES (@employee_id, @name, @salary);
            
            SET @success = 1;
            SELECT 'Employee inserted successfully' as status;
        END TRY
        BEGIN CATCH
            SET @retry_count = @retry_count + 1;
            
            INSERT INTO error_log (
                error_number, 
                error_message, 
                error_line, 
                error_procedure,
                error_time,
                retry_count
            ) VALUES (
                ERROR_NUMBER(),
                ERROR_MESSAGE(),
                ERROR_LINE(),
                ERROR_PROCEDURE(),
                GETDATE(),
                @retry_count
            );
            
            IF @retry_count >= @max_retries
                SELECT 'Max retries exceeded' as status;
            ELSE
                WAITFOR DELAY '00:00:01'; -- Wait 1 second before retry
        END CATCH
    END
END;

-- Error handling with custom error codes
CREATE PROCEDURE ProcessOrder
    @order_id INT,
    @customer_id INT,
    @amount DECIMAL(10,2)
AS
BEGIN
    BEGIN TRY
        -- Check if customer exists
        IF NOT EXISTS (SELECT 1 FROM customers WHERE customer_id = @customer_id)
            RAISERROR(50001, 16, 1, 'Customer not found');
        
        -- Check if amount is valid
        IF @amount <= 0
            RAISERROR(50002, 16, 1, 'Invalid order amount');
        
        -- Process order
        INSERT INTO orders (order_id, customer_id, amount, order_date) 
        VALUES (@order_id, @customer_id, @amount, GETDATE());
        
        SELECT 'Order processed successfully' as status;
    END TRY
    BEGIN CATCH
        DECLARE @error_code INT = ERROR_NUMBER();
        DECLARE @error_message NVARCHAR(4000) = ERROR_MESSAGE();
        
        INSERT INTO error_log (
            error_number, 
            error_message, 
            error_line, 
            error_procedure,
            error_time,
            order_id,
            customer_id
        ) VALUES (
            @error_code,
            @error_message,
            ERROR_LINE(),
            ERROR_PROCEDURE(),
            GETDATE(),
            @order_id,
            @customer_id
        );
        
        -- Return specific error messages based on error code
        IF @error_code = 50001
            SELECT 'Customer not found' as error_status;
        ELSE IF @error_code = 50002
            SELECT 'Invalid order amount' as error_status;
        ELSE
            SELECT 'Unexpected error occurred' as error_status;
    END CATCH
END;

-- Error handling with logging levels
CREATE PROCEDURE LogError
    @error_number INT,
    @error_message NVARCHAR(4000),
    @error_line INT,
    @error_procedure NVARCHAR(200),
    @severity VARCHAR(20) = 'ERROR'
AS
BEGIN
    INSERT INTO error_log (
        error_number, 
        error_message, 
        error_line, 
        error_procedure,
        error_time,
        severity_level,
        application_name
    ) VALUES (
        @error_number,
        @error_message,
        @error_line,
        @error_procedure,
        GETDATE(),
        @severity,
        'SQL_Application'
    );
END;

-- Error handling with performance monitoring
CREATE PROCEDURE ExecuteWithMonitoring
    @sql_query NVARCHAR(MAX)
AS
BEGIN
    DECLARE @start_time DATETIME = GETDATE();
    DECLARE @end_time DATETIME;
    DECLARE @execution_time_ms INT;
    
    BEGIN TRY
        EXEC sp_executesql @sql_query;
        
        SET @end_time = GETDATE();
        SET @execution_time_ms = DATEDIFF(MILLISECOND, @start_time, @end_time);
        
        -- Log successful execution
        INSERT INTO performance_log (
            query_text,
            execution_time_ms,
            execution_status,
            execution_time
        ) VALUES (
            @sql_query,
            @execution_time_ms,
            'SUCCESS',
            @start_time
        );
    END TRY
    BEGIN CATCH
        SET @end_time = GETDATE();
        SET @execution_time_ms = DATEDIFF(MILLISECOND, @start_time, @end_time);
        
        -- Log error
        INSERT INTO error_log (
            error_number, 
            error_message, 
            error_line, 
            error_procedure,
            error_time,
            query_text,
            execution_time_ms
        ) VALUES (
            ERROR_NUMBER(),
            ERROR_MESSAGE(),
            ERROR_LINE(),
            ERROR_PROCEDURE(),
            GETDATE(),
            @sql_query,
            @execution_time_ms
        );
    END CATCH
END;
```

2. What tools do you use to debug slow SQL queries? _(Asked in Infosys)_

**🧩 Foundation:** Use built-in database tools, system views, and monitoring utilities to identify performance bottlenecks and optimize query execution.

**⚙️ Function:** Diagnose performance issues, identify slow queries, and optimize database performance for better user experience.

**🔁 Flow:**
```sql
-- Check current running queries
SELECT 
    session_id,
    login_name,
    status,
    command,
    cpu_time,
    total_elapsed_time,
    reads,
    writes,
    logical_reads,
    text
FROM sys.dm_exec_requests r
CROSS APPLY sys.dm_exec_sql_text(r.sql_handle) t
WHERE r.status = 'running';

-- Find slow queries using execution statistics
SELECT 
    qs.sql_handle,
    qs.execution_count,
    qs.total_elapsed_time / qs.execution_count as avg_elapsed_time,
    qs.total_worker_time / qs.execution_count as avg_cpu_time,
    qs.total_logical_reads / qs.execution_count as avg_logical_reads,
    qs.total_physical_reads / qs.execution_count as avg_physical_reads,
    qs.last_execution_time,
    SUBSTRING(st.text, (qs.statement_start_offset/2)+1, 
        ((CASE qs.statement_end_offset
            WHEN -1 THEN DATALENGTH(st.text)
            ELSE qs.statement_end_offset
        END - qs.statement_start_offset)/2) + 1) as statement_text
FROM sys.dm_exec_query_stats qs
CROSS APPLY sys.dm_exec_sql_text(qs.sql_handle) st
WHERE qs.total_elapsed_time / qs.execution_count > 1000000 -- Queries taking more than 1 second
ORDER BY avg_elapsed_time DESC;

-- Check for blocking queries
SELECT 
    blocking.session_id as blocking_session_id,
    blocking.login_name as blocking_login,
    blocking.host_name as blocking_host,
    blocked.session_id as blocked_session_id,
    blocked.login_name as blocked_login,
    blocked.host_name as blocked_host,
    blocked.wait_type,
    blocked.wait_time,
    blocked.wait_resource
FROM sys.dm_exec_requests blocked
INNER JOIN sys.dm_exec_sessions blocking ON blocked.blocking_session_id = blocking.session_id
WHERE blocked.blocking_session_id > 0;

-- Analyze query execution plan
SET STATISTICS IO ON;
SET STATISTICS TIME ON;

-- Your query here
SELECT * FROM employees WHERE department_id = 1;

SET STATISTICS IO OFF;
SET STATISTICS TIME OFF;

-- Check index usage statistics
SELECT 
    OBJECT_NAME(i.object_id) as table_name,
    i.name as index_name,
    ius.user_seeks,
    ius.user_scans,
    ius.user_lookups,
    ius.user_updates,
    ius.last_user_seek,
    ius.last_user_scan
FROM sys.dm_db_index_usage_stats ius
INNER JOIN sys.indexes i ON ius.object_id = i.object_id AND ius.index_id = i.index_id
WHERE ius.database_id = DB_ID()
ORDER BY (ius.user_seeks + ius.user_scans + ius.user_lookups) DESC;

-- Monitor wait statistics
SELECT 
    wait_type,
    waiting_tasks_count,
    wait_time_ms,
    max_wait_time_ms,
    signal_wait_time_ms
FROM sys.dm_os_wait_stats
WHERE wait_time_ms > 0
ORDER BY wait_time_ms DESC;

-- Check for missing indexes
SELECT 
    dm_mid.database_id,
    dm_migs.avg_user_impact,
    dm_migs.last_user_seek,
    dm_mid.statement as table_name,
    dm_mid.equality_columns,
    dm_mid.inequality_columns,
    dm_mid.included_columns,
    dm_migs.unique_compiles,
    dm_migs.user_seeks,
    dm_migs.avg_total_user_cost,
    dm_migs.avg_user_impact
FROM sys.dm_db_missing_index_group_stats dm_migs
INNER JOIN sys.dm_db_missing_index_groups dm_mig ON dm_migs.group_handle = dm_mig.index_group_handle
INNER JOIN sys.dm_db_missing_index_details dm_mid ON dm_mig.index_handle = dm_mid.index_handle
WHERE dm_mid.database_id = DB_ID()
ORDER BY dm_migs.avg_user_impact DESC;

-- Monitor tempdb usage
SELECT 
    session_id,
    request_id,
    task_alloc_pages,
    task_dealloc_pages,
    host_name,
    login_name,
    program_name
FROM sys.dm_db_task_space_usage tsu
INNER JOIN sys.dm_exec_requests r ON tsu.session_id = r.session_id
INNER JOIN sys.dm_exec_sessions s ON r.session_id = s.session_id
WHERE tsu.task_alloc_pages > 0;

-- Check for long-running transactions
SELECT 
    s.session_id,
    s.login_name,
    s.host_name,
    s.program_name,
    t.transaction_id,
    t.name as transaction_name,
    t.transaction_begin_time,
    DATEDIFF(MINUTE, t.transaction_begin_time, GETDATE()) as transaction_duration_minutes
FROM sys.dm_tran_active_transactions t
INNER JOIN sys.dm_tran_session_transactions st ON t.transaction_id = st.transaction_id
INNER JOIN sys.dm_exec_sessions s ON st.session_id = s.session_id
WHERE t.transaction_begin_time < DATEADD(MINUTE, -5, GETDATE()); -- Transactions older than 5 minutes

-- Monitor deadlocks
SELECT 
    event_time,
    deadlock_graph
FROM sys.fn_xe_file_target_read_file(
    'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\Log\system_health*.xel',
    NULL, NULL, NULL
)
WHERE event_name = 'xml_deadlock_report';

-- Check for memory pressure
SELECT 
    type,
    pages_kb,
    pages_kb / 1024.0 as pages_mb
FROM sys.dm_os_memory_clerks
WHERE pages_kb > 0
ORDER BY pages_kb DESC;

-- Monitor CPU usage by queries
SELECT 
    qs.sql_handle,
    qs.execution_count,
    qs.total_worker_time / qs.execution_count as avg_cpu_time,
    qs.total_elapsed_time / qs.execution_count as avg_elapsed_time,
    qs.total_logical_reads / qs.execution_count as avg_logical_reads,
    SUBSTRING(st.text, (qs.statement_start_offset/2)+1, 
        ((CASE qs.statement_end_offset
            WHEN -1 THEN DATALENGTH(st.text)
            ELSE qs.statement_end_offset
        END - qs.statement_start_offset)/2) + 1) as statement_text
FROM sys.dm_exec_query_stats qs
CROSS APPLY sys.dm_exec_sql_text(qs.sql_handle) st
WHERE qs.total_worker_time / qs.execution_count > 100000 -- High CPU queries
ORDER BY avg_cpu_time DESC;

-- Check for parameter sniffing issues
SELECT 
    qs.sql_handle,
    qs.execution_count,
    qs.total_elapsed_time,
    qs.total_worker_time,
    qs.total_logical_reads,
    qs.total_physical_reads,
    qs.plan_generation_num,
    qs.creation_time,
    qs.last_execution_time,
    SUBSTRING(st.text, (qs.statement_start_offset/2)+1, 
        ((CASE qs.statement_end_offset
            WHEN -1 THEN DATALENGTH(st.text)
            ELSE qs.statement_end_offset
        END - qs.statement_start_offset)/2) + 1) as statement_text
FROM sys.dm_exec_query_stats qs
CROSS APPLY sys.dm_exec_sql_text(qs.sql_handle) st
WHERE qs.plan_generation_num > 1 -- Multiple plan generations
ORDER BY qs.plan_generation_num DESC;

-- Monitor query performance over time
CREATE TABLE query_performance_log (
    log_id INT IDENTITY(1,1) PRIMARY KEY,
    query_hash BINARY(8),
    execution_count INT,
    total_elapsed_time BIGINT,
    total_worker_time BIGINT,
    total_logical_reads BIGINT,
    log_time DATETIME DEFAULT GETDATE()
);

-- Insert performance data
INSERT INTO query_performance_log (query_hash, execution_count, total_elapsed_time, total_worker_time, total_logical_reads)
SELECT 
    qs.query_hash,
    qs.execution_count,
    qs.total_elapsed_time,
    qs.total_worker_time,
    qs.total_logical_reads
FROM sys.dm_exec_query_stats qs
WHERE qs.last_execution_time > DATEADD(HOUR, -1, GETDATE()); -- Last hour

-- Analyze performance trends
SELECT 
    query_hash,
    AVG(total_elapsed_time / execution_count) as avg_elapsed_time,
    AVG(total_worker_time / execution_count) as avg_cpu_time,
    AVG(total_logical_reads / execution_count) as avg_logical_reads,
    COUNT(*) as sample_count
FROM query_performance_log
WHERE log_time > DATEADD(DAY, -7, GETDATE()) -- Last 7 days
GROUP BY query_hash
HAVING AVG(total_elapsed_time / execution_count) > 1000000 -- Queries taking more than 1 second
ORDER BY avg_elapsed_time DESC;
```

### Basic Query Questions

1. Write a query with error handling in a stored procedure to log errors to an audit table. _(Asked in TCS)_

**🧩 Foundation:** Create a comprehensive error logging system using TRY-CATCH blocks and structured error tables to track and monitor database errors.

**⚙️ Function:** Maintain audit trails of errors for debugging, monitoring, and compliance purposes while ensuring application stability.

**🔁 Flow:**
```sql
-- Create error audit table
CREATE TABLE error_audit_log (
    error_id INT IDENTITY(1,1) PRIMARY KEY,
    error_number INT,
    error_message NVARCHAR(4000),
    error_severity INT,
    error_state INT,
    error_line INT,
    error_procedure NVARCHAR(200),
    error_time DATETIME DEFAULT GETDATE(),
    user_name NVARCHAR(100),
    host_name NVARCHAR(100),
    application_name NVARCHAR(100),
    session_id INT,
    transaction_id BIGINT,
    input_parameters NVARCHAR(MAX),
    stack_trace NVARCHAR(MAX)
);

-- Basic error handling stored procedure
CREATE PROCEDURE InsertEmployeeWithErrorLogging
    @employee_id INT,
    @name NVARCHAR(100),
    @salary DECIMAL(10,2),
    @department_id INT
AS
BEGIN
    BEGIN TRY
        -- Input validation
        IF @employee_id IS NULL OR @name IS NULL OR @salary IS NULL
            RAISERROR('All parameters must be provided', 16, 1);
        
        IF @salary < 0
            RAISERROR('Salary cannot be negative', 16, 1);
        
        -- Check for duplicate employee
        IF EXISTS (SELECT 1 FROM employees WHERE employee_id = @employee_id)
            RAISERROR('Employee ID %d already exists', 16, 1, @employee_id);
        
        -- Insert employee
        INSERT INTO employees (employee_id, name, salary, department_id)
        VALUES (@employee_id, @name, @salary, @department_id);
        
        SELECT 'Employee inserted successfully' as status;
    END TRY
    BEGIN CATCH
        -- Log error to audit table
        INSERT INTO error_audit_log (
            error_number,
            error_message,
            error_severity,
            error_state,
            error_line,
            error_procedure,
            user_name,
            host_name,
            application_name,
            session_id,
            transaction_id,
            input_parameters
        ) VALUES (
            ERROR_NUMBER(),
            ERROR_MESSAGE(),
            ERROR_SEVERITY(),
            ERROR_STATE(),
            ERROR_LINE(),
            ERROR_PROCEDURE(),
            SYSTEM_USER,
            HOST_NAME(),
            APP_NAME(),
            @@SPID,
            @@TRANCOUNT,
            CONCAT('employee_id:', @employee_id, ', name:', @name, ', salary:', @salary, ', department_id:', @department_id)
        );
        
        -- Re-throw the error
        THROW;
    END CATCH
END;

-- Enhanced error handling with transaction management
CREATE PROCEDURE ProcessOrderWithErrorLogging
    @order_id INT,
    @customer_id INT,
    @amount DECIMAL(10,2),
    @items NVARCHAR(MAX)
AS
BEGIN
    DECLARE @transaction_id BIGINT = @@TRANCOUNT;
    DECLARE @start_time DATETIME = GETDATE();
    
    BEGIN TRY
        BEGIN TRANSACTION;
        
        -- Validate customer
        IF NOT EXISTS (SELECT 1 FROM customers WHERE customer_id = @customer_id)
            RAISERROR('Customer ID %d not found', 16, 1, @customer_id);
        
        -- Validate amount
        IF @amount <= 0
            RAISERROR('Order amount must be greater than zero', 16, 1);
        
        -- Insert order
        INSERT INTO orders (order_id, customer_id, amount, order_date)
        VALUES (@order_id, @customer_id, @amount, GETDATE());
        
        -- Process order items (simplified)
        -- Additional logic for order items would go here
        
        COMMIT TRANSACTION;
        
        SELECT 'Order processed successfully' as status;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;
        
        -- Log detailed error information
        INSERT INTO error_audit_log (
            error_number,
            error_message,
            error_severity,
            error_state,
            error_line,
            error_procedure,
            user_name,
            host_name,
            application_name,
            session_id,
            transaction_id,
            input_parameters,
            stack_trace
        ) VALUES (
            ERROR_NUMBER(),
            ERROR_MESSAGE(),
            ERROR_SEVERITY(),
            ERROR_STATE(),
            ERROR_LINE(),
            ERROR_PROCEDURE(),
            SYSTEM_USER,
            HOST_NAME(),
            APP_NAME(),
            @@SPID,
            @transaction_id,
            CONCAT('order_id:', @order_id, ', customer_id:', @customer_id, ', amount:', @amount, ', items:', @items),
            ERROR_LINE() + ' in ' + ISNULL(ERROR_PROCEDURE(), 'Unknown Procedure')
        );
        
        -- Return error information
        SELECT 
            'Error occurred during order processing' as status,
            ERROR_NUMBER() as error_code,
            ERROR_MESSAGE() as error_message;
    END CATCH
END;

-- Error handling with retry logic and logging
CREATE PROCEDURE UpdateEmployeeWithRetry
    @employee_id INT,
    @new_salary DECIMAL(10,2),
    @max_retries INT = 3
AS
BEGIN
    DECLARE @retry_count INT = 0;
    DECLARE @success BIT = 0;
    DECLARE @error_message NVARCHAR(4000);
    
    WHILE @retry_count < @max_retries AND @success = 0
    BEGIN
        BEGIN TRY
            -- Validate employee exists
            IF NOT EXISTS (SELECT 1 FROM employees WHERE employee_id = @employee_id)
                RAISERROR('Employee ID %d not found', 16, 1, @employee_id);
            
            -- Update salary
            UPDATE employees 
            SET salary = @new_salary, 
                last_updated = GETDATE()
            WHERE employee_id = @employee_id;
            
            SET @success = 1;
            SELECT 'Employee updated successfully' as status;
        END TRY
        BEGIN CATCH
            SET @retry_count = @retry_count + 1;
            SET @error_message = ERROR_MESSAGE();
            
            -- Log each retry attempt
            INSERT INTO error_audit_log (
                error_number,
                error_message,
                error_severity,
                error_state,
                error_line,
                error_procedure,
                user_name,
                host_name,
                application_name,
                session_id,
                input_parameters
            ) VALUES (
                ERROR_NUMBER(),
                CONCAT('Retry ', @retry_count, ': ', @error_message),
                ERROR_SEVERITY(),
                ERROR_STATE(),
                ERROR_LINE(),
                ERROR_PROCEDURE(),
                SYSTEM_USER,
                HOST_NAME(),
                APP_NAME(),
                @@SPID,
                CONCAT('employee_id:', @employee_id, ', new_salary:', @new_salary, ', retry_count:', @retry_count)
            );
            
            IF @retry_count >= @max_retries
            BEGIN
                SELECT 'Max retries exceeded. Update failed.' as status;
                BREAK;
            END
            ELSE
            BEGIN
                WAITFOR DELAY '00:00:01'; -- Wait 1 second before retry
            END
        END CATCH
    END
END;

-- Error handling with custom error codes and logging
CREATE PROCEDURE DeleteEmployeeWithErrorLogging
    @employee_id INT
AS
BEGIN
    DECLARE @error_code INT;
    DECLARE @error_message NVARCHAR(4000);
    
    BEGIN TRY
        -- Check if employee exists
        IF NOT EXISTS (SELECT 1 FROM employees WHERE employee_id = @employee_id)
        BEGIN
            SET @error_code = 50001;
            SET @error_message = 'Employee not found';
            RAISERROR(@error_message, 16, 1);
        END
        
        -- Check if employee has active orders
        IF EXISTS (SELECT 1 FROM orders WHERE employee_id = @employee_id AND status = 'active')
        BEGIN
            SET @error_code = 50002;
            SET @error_message = 'Cannot delete employee with active orders';
            RAISERROR(@error_message, 16, 1);
        END
        
        -- Delete employee
        DELETE FROM employees WHERE employee_id = @employee_id;
        
        SELECT 'Employee deleted successfully' as status;
    END TRY
    BEGIN CATCH
        SET @error_code = ERROR_NUMBER();
        SET @error_message = ERROR_MESSAGE();
        
        -- Log error with custom categorization
        INSERT INTO error_audit_log (
            error_number,
            error_message,
            error_severity,
            error_state,
            error_line,
            error_procedure,
            user_name,
            host_name,
            application_name,
            session_id,
            input_parameters
        ) VALUES (
            @error_code,
            @error_message,
            ERROR_SEVERITY(),
            ERROR_STATE(),
            ERROR_LINE(),
            ERROR_PROCEDURE(),
            SYSTEM_USER,
            HOST_NAME(),
            APP_NAME(),
            @@SPID,
            CONCAT('employee_id:', @employee_id)
        );
        
        -- Return appropriate error response
        IF @error_code = 50001
            SELECT 'Employee not found' as error_status;
        ELSE IF @error_code = 50002
            SELECT 'Employee has active orders' as error_status;
        ELSE
            SELECT 'Unexpected error occurred' as error_status;
    END CATCH
END;

-- Error handling with performance monitoring
CREATE PROCEDURE ExecuteQueryWithMonitoring
    @sql_query NVARCHAR(MAX),
    @timeout_seconds INT = 30
AS
BEGIN
    DECLARE @start_time DATETIME = GETDATE();
    DECLARE @end_time DATETIME;
    DECLARE @execution_time_ms INT;
    DECLARE @error_occurred BIT = 0;
    
    BEGIN TRY
        -- Set query timeout
        SET LOCK_TIMEOUT @timeout_seconds * 1000;
        
        -- Execute query
        EXEC sp_executesql @sql_query;
        
        SET @end_time = GETDATE();
        SET @execution_time_ms = DATEDIFF(MILLISECOND, @start_time, @end_time);
        
        -- Log successful execution
        INSERT INTO performance_log (
            query_text,
            execution_time_ms,
            execution_status,
            execution_time,
            user_name
        ) VALUES (
            @sql_query,
            @execution_time_ms,
            'SUCCESS',
            @start_time,
            SYSTEM_USER
        );
        
        SELECT 'Query executed successfully' as status;
    END TRY
    BEGIN CATCH
        SET @error_occurred = 1;
        SET @end_time = GETDATE();
        SET @execution_time_ms = DATEDIFF(MILLISECOND, @start_time, @end_time);
        
        -- Log error with performance data
        INSERT INTO error_audit_log (
            error_number,
            error_message,
            error_severity,
            error_state,
            error_line,
            error_procedure,
            user_name,
            host_name,
            application_name,
            session_id,
            input_parameters
        ) VALUES (
            ERROR_NUMBER(),
            ERROR_MESSAGE(),
            ERROR_SEVERITY(),
            ERROR_STATE(),
            ERROR_LINE(),
            ERROR_PROCEDURE(),
            SYSTEM_USER,
            HOST_NAME(),
            APP_NAME(),
            @@SPID,
            CONCAT('query:', @sql_query, ', timeout:', @timeout_seconds, ', execution_time_ms:', @execution_time_ms)
        );
        
        SELECT 'Query execution failed' as status;
    END CATCH
END;

-- Error handling with data validation and logging
CREATE PROCEDURE ValidateAndInsertData
    @table_name NVARCHAR(100),
    @data_json NVARCHAR(MAX)
AS
BEGIN
    DECLARE @validation_errors TABLE (
        error_message NVARCHAR(4000),
        field_name NVARCHAR(100)
    );
    
    BEGIN TRY
        -- Parse JSON data
        IF ISJSON(@data_json) = 0
            RAISERROR('Invalid JSON format', 16, 1);
        
        -- Validate table exists
        IF NOT EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = @table_name)
            RAISERROR('Table %s does not exist', 16, 1, @table_name);
        
        -- Additional validation logic would go here
        -- This is a simplified example
        
        -- Insert data (simplified)
        -- Actual implementation would parse JSON and insert data
        
        SELECT 'Data validated and inserted successfully' as status;
    END TRY
    BEGIN CATCH
        -- Log validation errors
        INSERT INTO error_audit_log (
            error_number,
            error_message,
            error_severity,
            error_state,
            error_line,
            error_procedure,
            user_name,
            host_name,
            application_name,
            session_id,
            input_parameters
        ) VALUES (
            ERROR_NUMBER(),
            ERROR_MESSAGE(),
            ERROR_SEVERITY(),
            ERROR_STATE(),
            ERROR_LINE(),
            ERROR_PROCEDURE(),
            SYSTEM_USER,
            HOST_NAME(),
            APP_NAME(),
            @@SPID,
            CONCAT('table_name:', @table_name, ', data_json:', LEFT(@data_json, 1000))
        );
        
        SELECT 'Data validation failed' as status;
    END CATCH
END;

-- Query to analyze error patterns
SELECT 
    error_number,
    error_message,
    COUNT(*) as error_count,
    MIN(error_time) as first_occurrence,
    MAX(error_time) as last_occurrence,
    AVG(DATEDIFF(MINUTE, LAG(error_time) OVER(ORDER BY error_time), error_time)) as avg_time_between_errors
FROM error_audit_log
WHERE error_time > DATEADD(DAY, -7, GETDATE()) -- Last 7 days
GROUP BY error_number, error_message
ORDER BY error_count DESC;

-- Query to find most problematic procedures
SELECT 
    error_procedure,
    COUNT(*) as error_count,
    COUNT(DISTINCT user_name) as affected_users,
    MIN(error_time) as first_error,
    MAX(error_time) as last_error
FROM error_audit_log
WHERE error_procedure IS NOT NULL
  AND error_time > DATEADD(DAY, -30, GETDATE()) -- Last 30 days
GROUP BY error_procedure
ORDER BY error_count DESC;

-- Query to monitor error trends
SELECT 
    CAST(error_time AS DATE) as error_date,
    COUNT(*) as total_errors,
    COUNT(DISTINCT error_number) as unique_error_types,
    COUNT(DISTINCT user_name) as affected_users
FROM error_audit_log
WHERE error_time > DATEADD(DAY, -30, GETDATE()) -- Last 30 days
GROUP BY CAST(error_time AS DATE)
ORDER BY error_date DESC;
```

### Intermediate Questions

1. How do you log errors in a database transaction? _(Asked in Capgemini)_

**🧩 Foundation:** Use TRY-CATCH blocks within transactions to capture and log errors while maintaining data integrity through proper rollback mechanisms.

**⚙️ Function:** Ensure transaction safety, maintain audit trails, and provide detailed error information for debugging and monitoring.

**🔁 Flow:**
```sql
-- Create transaction error log table
CREATE TABLE transaction_error_log (
    log_id INT IDENTITY(1,1) PRIMARY KEY,
    transaction_id BIGINT,
    error_number INT,
    error_message NVARCHAR(4000),
    error_severity INT,
    error_state INT,
    error_line INT,
    error_procedure NVARCHAR(200),
    error_time DATETIME DEFAULT GETDATE(),
    user_name NVARCHAR(100),
    session_id INT,
    transaction_status VARCHAR(20),
    affected_tables NVARCHAR(MAX),
    rollback_successful BIT,
    execution_time_ms INT
);

-- Basic transaction error logging
BEGIN TRANSACTION;
BEGIN TRY
    -- First operation
    INSERT INTO employees (employee_id, name, salary) VALUES (1, 'John Doe', 50000);
    
    -- Second operation
    INSERT INTO employee_details (employee_id, department, hire_date) VALUES (1, 'IT', GETDATE());
    
    -- Third operation
    UPDATE employee_salaries SET current_salary = 50000 WHERE employee_id = 1;
    
    COMMIT TRANSACTION;
    SELECT 'Transaction completed successfully' as status;
END TRY
BEGIN CATCH
    DECLARE @error_number INT = ERROR_NUMBER();
    DECLARE @error_message NVARCHAR(4000) = ERROR_MESSAGE();
    DECLARE @transaction_id BIGINT = @@TRANCOUNT;
    
    -- Log error before rollback
    INSERT INTO transaction_error_log (
        transaction_id,
        error_number,
        error_message,
        error_severity,
        error_state,
        error_line,
        error_procedure,
        user_name,
        session_id,
        transaction_status,
        affected_tables
    ) VALUES (
        @transaction_id,
        @error_number,
        @error_message,
        ERROR_SEVERITY(),
        ERROR_STATE(),
        ERROR_LINE(),
        ERROR_PROCEDURE(),
        SYSTEM_USER,
        @@SPID,
        'FAILED',
        'employees, employee_details, employee_salaries'
    );
    
    -- Rollback transaction
    IF @@TRANCOUNT > 0
        ROLLBACK TRANSACTION;
    
    -- Update log with rollback status
    UPDATE transaction_error_log 
    SET rollback_successful = 1,
        transaction_status = 'ROLLED_BACK'
    WHERE transaction_id = @transaction_id;
    
    SELECT 'Transaction failed and rolled back' as status;
END CATCH;

-- Transaction error logging with performance monitoring
CREATE PROCEDURE ProcessOrderTransaction
    @order_id INT,
    @customer_id INT,
    @amount DECIMAL(10,2)
AS
BEGIN
    DECLARE @start_time DATETIME = GETDATE();
    DECLARE @end_time DATETIME;
    DECLARE @execution_time_ms INT;
    DECLARE @transaction_id BIGINT;
    
    BEGIN TRANSACTION;
    SET @transaction_id = @@TRANCOUNT;
    
    BEGIN TRY
        -- Validate customer
        IF NOT EXISTS (SELECT 1 FROM customers WHERE customer_id = @customer_id)
            RAISERROR('Customer ID %d not found', 16, 1, @customer_id);
        
        -- Insert order
        INSERT INTO orders (order_id, customer_id, amount, order_date)
        VALUES (@order_id, @customer_id, @amount, GETDATE());
        
        -- Update customer total
        UPDATE customers 
        SET total_orders = total_orders + 1,
            total_amount = total_amount + @amount
        WHERE customer_id = @customer_id;
        
        -- Insert order audit
        INSERT INTO order_audit (order_id, action, action_time)
        VALUES (@order_id, 'ORDER_CREATED', GETDATE());
        
        COMMIT TRANSACTION;
        
        SET @end_time = GETDATE();
        SET @execution_time_ms = DATEDIFF(MILLISECOND, @start_time, @end_time);
        
        -- Log successful transaction
        INSERT INTO transaction_error_log (
            transaction_id,
            error_number,
            error_message,
            user_name,
            session_id,
            transaction_status,
            affected_tables,
            rollback_successful,
            execution_time_ms
        ) VALUES (
            @transaction_id,
            0,
            'Transaction completed successfully',
            SYSTEM_USER,
            @@SPID,
            'COMMITTED',
            'customers, orders, order_audit',
            0,
            @execution_time_ms
        );
        
        SELECT 'Order processed successfully' as status;
    END TRY
    BEGIN CATCH
        SET @end_time = GETDATE();
        SET @execution_time_ms = DATEDIFF(MILLISECOND, @start_time, @end_time);
        
        -- Log error details
        INSERT INTO transaction_error_log (
            transaction_id,
            error_number,
            error_message,
            error_severity,
            error_state,
            error_line,
            error_procedure,
            user_name,
            session_id,
            transaction_status,
            affected_tables,
            rollback_successful,
            execution_time_ms
        ) VALUES (
            @transaction_id,
            ERROR_NUMBER(),
            ERROR_MESSAGE(),
            ERROR_SEVERITY(),
            ERROR_STATE(),
            ERROR_LINE(),
            ERROR_PROCEDURE(),
            SYSTEM_USER,
            @@SPID,
            'FAILED',
            'customers, orders, order_audit',
            0,
            @execution_time_ms
        );
        
        -- Rollback transaction
        IF @@TRANCOUNT > 0
        BEGIN
            ROLLBACK TRANSACTION;
            
            -- Update log with rollback status
            UPDATE transaction_error_log 
            SET rollback_successful = 1,
                transaction_status = 'ROLLED_BACK'
            WHERE transaction_id = @transaction_id;
        END
        
        SELECT 'Transaction failed and rolled back' as status;
    END CATCH
END;

-- Transaction error logging with nested transactions
CREATE PROCEDURE ProcessComplexTransaction
    @data_json NVARCHAR(MAX)
AS
BEGIN
    DECLARE @transaction_id BIGINT;
    DECLARE @savepoint_name NVARCHAR(50);
    DECLARE @step_number INT = 1;
    
    BEGIN TRANSACTION;
    SET @transaction_id = @@TRANCOUNT;
    
    BEGIN TRY
        -- Step 1: Parse and validate data
        SET @savepoint_name = 'STEP1_VALIDATION';
        SAVE TRANSACTION @savepoint_name;
        
        IF ISJSON(@data_json) = 0
            RAISERROR('Invalid JSON format', 16, 1);
        
        -- Step 2: Insert primary records
        SET @step_number = 2;
        SET @savepoint_name = 'STEP2_PRIMARY_INSERT';
        SAVE TRANSACTION @savepoint_name;
        
        -- Insert logic here
        INSERT INTO primary_table (data) VALUES (@data_json);
        
        -- Step 3: Insert related records
        SET @step_number = 3;
        SET @savepoint_name = 'STEP3_RELATED_INSERT';
        SAVE TRANSACTION @savepoint_name;
        
        -- Related insert logic here
        INSERT INTO related_table (primary_id, data) 
        SELECT SCOPE_IDENTITY(), @data_json;
        
        -- Step 4: Update summary
        SET @step_number = 4;
        SET @savepoint_name = 'STEP4_SUMMARY_UPDATE';
        SAVE TRANSACTION @savepoint_name;
        
        -- Summary update logic here
        UPDATE summary_table SET record_count = record_count + 1;
        
        COMMIT TRANSACTION;
        
        -- Log successful transaction
        INSERT INTO transaction_error_log (
            transaction_id,
            error_number,
            error_message,
            user_name,
            session_id,
            transaction_status,
            affected_tables
        ) VALUES (
            @transaction_id,
            0,
            'Complex transaction completed successfully',
            SYSTEM_USER,
            @@SPID,
            'COMMITTED',
            'primary_table, related_table, summary_table'
        );
        
        SELECT 'Complex transaction completed successfully' as status;
    END TRY
    BEGIN CATCH
        -- Log error with step information
        INSERT INTO transaction_error_log (
            transaction_id,
            error_number,
            error_message,
            error_severity,
            error_state,
            error_line,
            error_procedure,
            user_name,
            session_id,
            transaction_status,
            affected_tables
        ) VALUES (
            @transaction_id,
            ERROR_NUMBER(),
            CONCAT('Step ', @step_number, ': ', ERROR_MESSAGE()),
            ERROR_SEVERITY(),
            ERROR_STATE(),
            ERROR_LINE(),
            ERROR_PROCEDURE(),
            SYSTEM_USER,
            @@SPID,
            'FAILED',
            'primary_table, related_table, summary_table'
        );
        
        -- Rollback to appropriate savepoint or full rollback
        IF @@TRANCOUNT > 0
        BEGIN
            IF @step_number > 1
            BEGIN
                -- Rollback to previous savepoint
                ROLLBACK TRANSACTION @savepoint_name;
                
                UPDATE transaction_error_log 
                SET rollback_successful = 1,
                    transaction_status = 'PARTIAL_ROLLBACK'
                WHERE transaction_id = @transaction_id;
            END
            ELSE
            BEGIN
                -- Full rollback
                ROLLBACK TRANSACTION;
                
                UPDATE transaction_error_log 
                SET rollback_successful = 1,
                    transaction_status = 'FULL_ROLLBACK'
                WHERE transaction_id = @transaction_id;
            END
        END
        
        SELECT 'Transaction failed' as status;
    END CATCH
END;

-- Transaction error logging with retry logic
CREATE PROCEDURE ProcessTransactionWithRetry
    @operation_data NVARCHAR(MAX),
    @max_retries INT = 3
AS
BEGIN
    DECLARE @retry_count INT = 0;
    DECLARE @success BIT = 0;
    DECLARE @transaction_id BIGINT;
    DECLARE @error_message NVARCHAR(4000);
    
    WHILE @retry_count < @max_retries AND @success = 0
    BEGIN
        BEGIN TRANSACTION;
        SET @transaction_id = @@TRANCOUNT;
        
        BEGIN TRY
            -- Perform transaction operations
            INSERT INTO operation_log (operation_data, attempt_number)
            VALUES (@operation_data, @retry_count + 1);
            
            -- Additional operations here
            
            COMMIT TRANSACTION;
            SET @success = 1;
            
            -- Log successful transaction
            INSERT INTO transaction_error_log (
                transaction_id,
                error_number,
                error_message,
                user_name,
                session_id,
                transaction_status,
                affected_tables
            ) VALUES (
                @transaction_id,
                0,
                CONCAT('Transaction completed successfully on attempt ', @retry_count + 1),
                SYSTEM_USER,
                @@SPID,
                'COMMITTED',
                'operation_log'
            );
            
            SELECT 'Transaction completed successfully' as status;
        END TRY
        BEGIN CATCH
            SET @error_message = ERROR_MESSAGE();
            
            -- Log transaction error
            INSERT INTO transaction_error_log (
                transaction_id,
                error_number,
                error_message,
                error_severity,
                error_state,
                error_line,
                error_procedure,
                user_name,
                session_id,
                transaction_status,
                affected_tables
            ) VALUES (
                @transaction_id,
                ERROR_NUMBER(),
                CONCAT('Attempt ', @retry_count + 1, ': ', @error_message),
                ERROR_SEVERITY(),
                ERROR_STATE(),
                ERROR_LINE(),
                ERROR_PROCEDURE(),
                SYSTEM_USER,
                @@SPID,
                'FAILED',
                'operation_log'
            );
            
            -- Rollback transaction
            IF @@TRANCOUNT > 0
            BEGIN
                ROLLBACK TRANSACTION;
                
                UPDATE transaction_error_log 
                SET rollback_successful = 1,
                    transaction_status = 'ROLLED_BACK'
                WHERE transaction_id = @transaction_id;
            END
            
            SET @retry_count = @retry_count + 1;
            
            IF @retry_count >= @max_retries
            BEGIN
                SELECT 'Max retries exceeded. Transaction failed.' as status;
                BREAK;
            END
            ELSE
            BEGIN
                WAITFOR DELAY '00:00:01'; -- Wait 1 second before retry
            END
        END CATCH
    END
END;

-- Query to analyze transaction error patterns
SELECT 
    transaction_status,
    COUNT(*) as transaction_count,
    AVG(execution_time_ms) as avg_execution_time,
    COUNT(CASE WHEN rollback_successful = 1 THEN 1 END) as successful_rollbacks,
    COUNT(CASE WHEN rollback_successful = 0 THEN 1 END) as failed_rollbacks
FROM transaction_error_log
WHERE error_time > DATEADD(DAY, -7, GETDATE())
GROUP BY transaction_status
ORDER BY transaction_count DESC;

-- Query to monitor transaction performance
SELECT 
    CAST(error_time AS DATE) as transaction_date,
    COUNT(*) as total_transactions,
    COUNT(CASE WHEN transaction_status = 'COMMITTED' THEN 1 END) as successful_transactions,
    COUNT(CASE WHEN transaction_status IN ('FAILED', 'ROLLED_BACK') THEN 1 END) as failed_transactions,
    AVG(execution_time_ms) as avg_execution_time
FROM transaction_error_log
WHERE error_time > DATEADD(DAY, -30, GETDATE())
GROUP BY CAST(error_time AS DATE)
ORDER BY transaction_date DESC;
```

2. How do you troubleshoot a query that returns incorrect results? _(Asked in Wipro)_

**🧩 Foundation:** Use systematic debugging approaches including data validation, query analysis, execution plan review, and result verification to identify and fix incorrect query results.

**⚙️ Function:** Ensure data accuracy, identify query logic errors, and validate business requirements through comprehensive testing and analysis.

**🔁 Flow:**
```sql
-- Step 1: Verify data integrity
-- Check for data inconsistencies
SELECT 
    'Data Integrity Check' as check_type,
    COUNT(*) as total_records,
    COUNT(DISTINCT employee_id) as unique_employees,
    COUNT(*) - COUNT(DISTINCT employee_id) as duplicate_employees
FROM employees;

-- Check for NULL values in critical columns
SELECT 
    'NULL Value Check' as check_type,
    COUNT(*) as total_records,
    COUNT(CASE WHEN employee_id IS NULL THEN 1 END) as null_employee_ids,
    COUNT(CASE WHEN name IS NULL THEN 1 END) as null_names,
    COUNT(CASE WHEN salary IS NULL THEN 1 END) as null_salaries
FROM employees;

-- Check for data type issues
SELECT 
    'Data Type Check' as check_type,
    employee_id,
    name,
    salary,
    CASE 
        WHEN ISNUMERIC(salary) = 0 THEN 'Invalid Salary'
        WHEN salary < 0 THEN 'Negative Salary'
        ELSE 'Valid'
    END as salary_validation
FROM employees
WHERE ISNUMERIC(salary) = 0 OR salary < 0;

-- Step 2: Analyze query logic step by step
-- Break down complex query into parts
-- Original query: SELECT * FROM employees WHERE department_id = 1 AND salary > 50000;

-- Part 1: Check department filter
SELECT 
    'Department Filter Check' as check_type,
    COUNT(*) as total_employees,
    COUNT(CASE WHEN department_id = 1 THEN 1 END) as dept_1_employees
FROM employees;

-- Part 2: Check salary filter
SELECT 
    'Salary Filter Check' as check_type,
    COUNT(*) as total_employees,
    COUNT(CASE WHEN salary > 50000 THEN 1 END) as high_salary_employees
FROM employees;

-- Part 3: Check combined filter
SELECT 
    'Combined Filter Check' as check_type,
    COUNT(*) as total_employees,
    COUNT(CASE WHEN department_id = 1 AND salary > 50000 THEN 1 END) as filtered_employees
FROM employees;

-- Step 3: Compare with expected results
-- Create expected results table
CREATE TABLE expected_results (
    employee_id INT,
    name NVARCHAR(100),
    salary DECIMAL(10,2),
    department_id INT
);

-- Insert expected data
INSERT INTO expected_results VALUES 
(1, 'John Doe', 55000, 1),
(2, 'Jane Smith', 60000, 1);

-- Compare actual vs expected results
SELECT 
    'Result Comparison' as check_type,
    'Actual' as result_type,
    COUNT(*) as record_count
FROM employees 
WHERE department_id = 1 AND salary > 50000

UNION ALL

SELECT 
    'Result Comparison' as check_type,
    'Expected' as result_type,
    COUNT(*) as record_count
FROM expected_results;

-- Step 4: Check for JOIN issues
-- Analyze JOIN conditions
SELECT 
    e.employee_id,
    e.name,
    e.department_id,
    d.department_name,
    CASE 
        WHEN d.department_id IS NULL THEN 'Missing Department'
        ELSE 'Valid Join'
    END as join_status
FROM employees e
LEFT JOIN departments d ON e.department_id = d.department_id
WHERE e.department_id = 1;

-- Check for duplicate JOIN results
SELECT 
    e.employee_id,
    e.name,
    COUNT(*) as join_count
FROM employees e
INNER JOIN departments d ON e.department_id = d.department_id
WHERE e.department_id = 1
GROUP BY e.employee_id, e.name
HAVING COUNT(*) > 1;

-- Step 5: Verify aggregation functions
-- Check GROUP BY logic
SELECT 
    department_id,
    COUNT(*) as employee_count,
    AVG(salary) as avg_salary,
    SUM(salary) as total_salary,
    MIN(salary) as min_salary,
    MAX(salary) as max_salary
FROM employees
GROUP BY department_id
ORDER BY department_id;

-- Compare with individual calculations
SELECT 
    'Individual Calculation Check' as check_type,
    department_id,
    COUNT(*) as manual_count,
    SUM(salary) / COUNT(*) as manual_avg
FROM employees
GROUP BY department_id
ORDER BY department_id;

-- Step 6: Check for data type conversion issues
-- Identify implicit conversions
SELECT 
    employee_id,
    name,
    salary,
    CASE 
        WHEN salary = '50000' THEN 'String Comparison'
        WHEN salary = 50000 THEN 'Numeric Comparison'
        ELSE 'Other'
    END as comparison_type
FROM employees
WHERE salary = 50000 OR salary = '50000';

-- Check for precision issues
SELECT 
    employee_id,
    name,
    salary,
    CAST(salary AS DECIMAL(10,2)) as cast_salary,
    ROUND(salary, 2) as rounded_salary
FROM employees
WHERE ABS(salary - CAST(salary AS DECIMAL(10,2))) > 0.01;

-- Step 7: Analyze execution plan
-- Enable execution plan
SET STATISTICS IO ON;
SET STATISTICS TIME ON;

-- Run query with execution plan
SELECT * FROM employees WHERE department_id = 1 AND salary > 50000;

SET STATISTICS IO OFF;
SET STATISTICS TIME OFF;

-- Step 8: Check for parameter sniffing issues
-- Compare execution with different parameters
DECLARE @dept_id INT = 1;
DECLARE @min_salary DECIMAL(10,2) = 50000;

-- Query with parameters
SELECT * FROM employees WHERE department_id = @dept_id AND salary > @min_salary;

-- Query with literal values
SELECT * FROM employees WHERE department_id = 1 AND salary > 50000;

-- Step 9: Verify business logic
-- Check if business rules are correctly implemented
SELECT 
    'Business Logic Check' as check_type,
    employee_id,
    name,
    salary,
    department_id,
    CASE 
        WHEN department_id = 1 AND salary > 50000 THEN 'Meets Criteria'
        WHEN department_id = 1 AND salary <= 50000 THEN 'Department OK, Salary Low'
        WHEN department_id != 1 AND salary > 50000 THEN 'Salary OK, Wrong Department'
        ELSE 'Does Not Meet Criteria'
    END as business_logic_status
FROM employees
ORDER BY department_id, salary DESC;

-- Step 10: Create debugging queries
-- Query to identify specific issues
WITH debugging_data AS (
    SELECT 
        employee_id,
        name,
        salary,
        department_id,
        CASE 
            WHEN department_id = 1 THEN 'Correct Department'
            ELSE 'Wrong Department'
        END as dept_check,
        CASE 
            WHEN salary > 50000 THEN 'High Salary'
            ELSE 'Low Salary'
        END as salary_check
    FROM employees
)
SELECT 
    dept_check,
    salary_check,
    COUNT(*) as record_count
FROM debugging_data
GROUP BY dept_check, salary_check
ORDER BY dept_check, salary_check;

-- Step 11: Validate against business requirements
-- Create business requirement validation
SELECT 
    'Business Requirement Validation' as validation_type,
    COUNT(*) as total_employees,
    COUNT(CASE WHEN department_id = 1 THEN 1 END) as dept_1_total,
    COUNT(CASE WHEN department_id = 1 AND salary > 50000 THEN 1 END) as dept_1_high_salary,
    COUNT(CASE WHEN department_id = 1 AND salary <= 50000 THEN 1 END) as dept_1_low_salary
FROM employees;

-- Step 12: Create test cases
-- Test case 1: Empty result set
SELECT 'Test Case 1: No Results' as test_case,
       COUNT(*) as result_count
FROM employees 
WHERE department_id = 999 AND salary > 50000;

-- Test case 2: All records
SELECT 'Test Case 2: All Records' as test_case,
       COUNT(*) as result_count
FROM employees 
WHERE 1 = 1;

-- Test case 3: Specific employee
SELECT 'Test Case 3: Specific Employee' as test_case,
       employee_id,
       name,
       salary,
       department_id
FROM employees 
WHERE employee_id = 1;

-- Step 13: Monitor query performance
-- Check query execution statistics
SELECT 
    qs.sql_handle,
    qs.execution_count,
    qs.total_elapsed_time / qs.execution_count as avg_elapsed_time,
    qs.total_logical_reads / qs.execution_count as avg_logical_reads,
    SUBSTRING(st.text, (qs.statement_start_offset/2)+1, 
        ((CASE qs.statement_end_offset
            WHEN -1 THEN DATALENGTH(st.text)
            ELSE qs.statement_end_offset
        END - qs.statement_start_offset)/2) + 1) as statement_text
FROM sys.dm_exec_query_stats qs
CROSS APPLY sys.dm_exec_sql_text(qs.sql_handle) st
WHERE st.text LIKE '%employees%' AND st.text LIKE '%department_id%'
ORDER BY avg_elapsed_time DESC;

-- Step 14: Create comprehensive debugging report
SELECT 
    'Comprehensive Debugging Report' as report_type,
    GETDATE() as report_time,
    (SELECT COUNT(*) FROM employees) as total_employees,
    (SELECT COUNT(*) FROM employees WHERE department_id = 1) as dept_1_employees,
    (SELECT COUNT(*) FROM employees WHERE salary > 50000) as high_salary_employees,
    (SELECT COUNT(*) FROM employees WHERE department_id = 1 AND salary > 50000) as target_employees,
    (SELECT COUNT(*) FROM departments) as total_departments,
    (SELECT COUNT(*) FROM departments WHERE department_id = 1) as dept_1_exists;
```

### Intermediate Query Questions

1. Write a query to log transaction errors to an error_log table within a stored procedure. _(Asked in Wipro)_

**🧩 Foundation:** Create a comprehensive transaction error logging system that captures detailed error information and transaction context for debugging and monitoring.

**⚙️ Function:** Maintain complete audit trails of transaction failures, provide detailed error context, and enable systematic debugging of transaction issues.

**🔁 Flow:**
```sql
-- Create comprehensive error log table
CREATE TABLE error_log (
    log_id INT IDENTITY(1,1) PRIMARY KEY,
    transaction_id BIGINT,
    error_number INT,
    error_message NVARCHAR(4000),
    error_severity INT,
    error_state INT,
    error_line INT,
    error_procedure NVARCHAR(200),
    error_time DATETIME DEFAULT GETDATE(),
    user_name NVARCHAR(100),
    host_name NVARCHAR(100),
    application_name NVARCHAR(100),
    session_id INT,
    transaction_status VARCHAR(20),
    affected_tables NVARCHAR(MAX),
    input_parameters NVARCHAR(MAX),
    execution_time_ms INT,
    rollback_successful BIT,
    retry_count INT,
    error_context NVARCHAR(MAX)
);

-- Stored procedure with comprehensive error logging
CREATE PROCEDURE ProcessOrderWithErrorLogging
    @order_id INT,
    @customer_id INT,
    @amount DECIMAL(10,2),
    @items NVARCHAR(MAX)
AS
BEGIN
    DECLARE @start_time DATETIME = GETDATE();
    DECLARE @end_time DATETIME;
    DECLARE @execution_time_ms INT;
    DECLARE @transaction_id BIGINT;
    DECLARE @error_context NVARCHAR(MAX);
    DECLARE @retry_count INT = 0;
    DECLARE @max_retries INT = 3;
    DECLARE @success BIT = 0;
    
    WHILE @retry_count < @max_retries AND @success = 0
    BEGIN
        BEGIN TRANSACTION;
        SET @transaction_id = @@TRANCOUNT;
        SET @error_context = CONCAT('Attempt ', @retry_count + 1, ' - Processing Order ', @order_id);
        
        BEGIN TRY
            -- Step 1: Validate customer
            IF NOT EXISTS (SELECT 1 FROM customers WHERE customer_id = @customer_id)
            BEGIN
                SET @error_context = CONCAT(@error_context, ' - Customer validation failed');
                RAISERROR('Customer ID %d not found', 16, 1, @customer_id);
            END
            
            -- Step 2: Validate amount
            IF @amount <= 0
            BEGIN
                SET @error_context = CONCAT(@error_context, ' - Amount validation failed');
                RAISERROR('Order amount must be greater than zero', 16, 1);
            END
            
            -- Step 3: Check for duplicate order
            IF EXISTS (SELECT 1 FROM orders WHERE order_id = @order_id)
            BEGIN
                SET @error_context = CONCAT(@error_context, ' - Duplicate order check failed');
                RAISERROR('Order ID %d already exists', 16, 1, @order_id);
            END
            
            -- Step 4: Insert order
            INSERT INTO orders (order_id, customer_id, amount, order_date, status)
            VALUES (@order_id, @customer_id, @amount, GETDATE(), 'pending');
            
            -- Step 5: Update customer statistics
            UPDATE customers 
            SET total_orders = total_orders + 1,
                total_amount = total_amount + @amount,
                last_order_date = GETDATE()
            WHERE customer_id = @customer_id;
            
            -- Step 6: Insert order audit
            INSERT INTO order_audit (order_id, action, action_time, user_name)
            VALUES (@order_id, 'ORDER_CREATED', GETDATE(), SYSTEM_USER);
            
            -- Step 7: Process order items (simplified)
            -- Additional logic for order items would go here
            
            COMMIT TRANSACTION;
            SET @success = 1;
            
            SET @end_time = GETDATE();
            SET @execution_time_ms = DATEDIFF(MILLISECOND, @start_time, @end_time);
            
            -- Log successful transaction
            INSERT INTO error_log (
                transaction_id,
                error_number,
                error_message,
                user_name,
                host_name,
                application_name,
                session_id,
                transaction_status,
                affected_tables,
                input_parameters,
                execution_time_ms,
                rollback_successful,
                retry_count,
                error_context
            ) VALUES (
                @transaction_id,
                0,
                'Transaction completed successfully',
                SYSTEM_USER,
                HOST_NAME(),
                APP_NAME(),
                @@SPID,
                'COMMITTED',
                'customers, orders, order_audit',
                CONCAT('order_id:', @order_id, ', customer_id:', @customer_id, ', amount:', @amount),
                @execution_time_ms,
                0,
                @retry_count,
                @error_context
            );
            
            SELECT 'Order processed successfully' as status;
        END TRY
        BEGIN CATCH
            SET @end_time = GETDATE();
            SET @execution_time_ms = DATEDIFF(MILLISECOND, @start_time, @end_time);
            
            -- Log detailed error information
            INSERT INTO error_log (
                transaction_id,
                error_number,
                error_message,
                error_severity,
                error_state,
                error_line,
                error_procedure,
                user_name,
                host_name,
                application_name,
                session_id,
                transaction_status,
                affected_tables,
                input_parameters,
                execution_time_ms,
                rollback_successful,
                retry_count,
                error_context
            ) VALUES (
                @transaction_id,
                ERROR_NUMBER(),
                ERROR_MESSAGE(),
                ERROR_SEVERITY(),
                ERROR_STATE(),
                ERROR_LINE(),
                ERROR_PROCEDURE(),
                SYSTEM_USER,
                HOST_NAME(),
                APP_NAME(),
                @@SPID,
                'FAILED',
                'customers, orders, order_audit',
                CONCAT('order_id:', @order_id, ', customer_id:', @customer_id, ', amount:', @amount),
                @execution_time_ms,
                0,
                @retry_count,
                @error_context
            );
            
            -- Rollback transaction
            IF @@TRANCOUNT > 0
            BEGIN
                ROLLBACK TRANSACTION;
                
                -- Update log with rollback status
                UPDATE error_log 
                SET rollback_successful = 1,
                    transaction_status = 'ROLLED_BACK'
                WHERE transaction_id = @transaction_id;
            END
            
            SET @retry_count = @retry_count + 1;
            
            IF @retry_count >= @max_retries
            BEGIN
                SELECT 'Max retries exceeded. Order processing failed.' as status;
                BREAK;
            END
            ELSE
            BEGIN
                WAITFOR DELAY '00:00:01'; -- Wait 1 second before retry
            END
        END CATCH
    END
END;

-- Stored procedure with conditional error logging
CREATE PROCEDURE UpdateEmployeeWithConditionalLogging
    @employee_id INT,
    @new_salary DECIMAL(10,2),
    @log_level VARCHAR(20) = 'ERROR' -- ERROR, WARNING, INFO
AS
BEGIN
    DECLARE @start_time DATETIME = GETDATE();
    DECLARE @transaction_id BIGINT;
    DECLARE @error_context NVARCHAR(MAX);
    
    BEGIN TRANSACTION;
    SET @transaction_id = @@TRANCOUNT;
    SET @error_context = CONCAT('Updating employee ', @employee_id, ' salary to ', @new_salary);
    
    BEGIN TRY
        -- Validate employee exists
        IF NOT EXISTS (SELECT 1 FROM employees WHERE employee_id = @employee_id)
        BEGIN
            SET @error_context = CONCAT(@error_context, ' - Employee not found');
            RAISERROR('Employee ID %d not found', 16, 1, @employee_id);
        END
        
        -- Validate salary
        IF @new_salary < 0
        BEGIN
            SET @error_context = CONCAT(@error_context, ' - Invalid salary');
            RAISERROR('Salary cannot be negative', 16, 1);
        END
        
        -- Get current salary for comparison
        DECLARE @current_salary DECIMAL(10,2);
        SELECT @current_salary = salary FROM employees WHERE employee_id = @employee_id;
        
        -- Check for significant salary changes
        IF ABS(@new_salary - @current_salary) > @current_salary * 0.5 -- 50% change
        BEGIN
            -- Log warning for significant change
            INSERT INTO error_log (
                transaction_id,
                error_number,
                error_message,
                user_name,
                session_id,
                transaction_status,
                affected_tables,
                input_parameters,
                error_context
            ) VALUES (
                @transaction_id,
                0,
                CONCAT('Significant salary change detected: ', @current_salary, ' to ', @new_salary),
                SYSTEM_USER,
                @@SPID,
                'WARNING',
                'employees',
                CONCAT('employee_id:', @employee_id, ', new_salary:', @new_salary),
                @error_context
            );
        END
        
        -- Update salary
        UPDATE employees 
        SET salary = @new_salary,
            last_updated = GETDATE(),
            updated_by = SYSTEM_USER
        WHERE employee_id = @employee_id;
        
        -- Insert salary history
        INSERT INTO salary_history (employee_id, old_salary, new_salary, change_date, changed_by)
        VALUES (@employee_id, @current_salary, @new_salary, GETDATE(), SYSTEM_USER);
        
        COMMIT TRANSACTION;
        
        -- Log successful update
        INSERT INTO error_log (
            transaction_id,
            error_number,
            error_message,
            user_name,
            session_id,
            transaction_status,
            affected_tables,
            input_parameters,
            error_context
        ) VALUES (
            @transaction_id,
            0,
            'Employee salary updated successfully',
            SYSTEM_USER,
            @@SPID,
            'COMMITTED',
            'employees, salary_history',
            CONCAT('employee_id:', @employee_id, ', new_salary:', @new_salary),
            @error_context
        );
        
        SELECT 'Employee updated successfully' as status;
    END TRY
    BEGIN CATCH
        -- Log error based on log level
        IF @log_level IN ('ERROR', 'WARNING')
        BEGIN
            INSERT INTO error_log (
                transaction_id,
                error_number,
                error_message,
                error_severity,
                error_state,
                error_line,
                error_procedure,
                user_name,
                session_id,
                transaction_status,
                affected_tables,
                input_parameters,
                error_context
            ) VALUES (
                @transaction_id,
                ERROR_NUMBER(),
                ERROR_MESSAGE(),
                ERROR_SEVERITY(),
                ERROR_STATE(),
                ERROR_LINE(),
                ERROR_PROCEDURE(),
                SYSTEM_USER,
                @@SPID,
                'FAILED',
                'employees, salary_history',
                CONCAT('employee_id:', @employee_id, ', new_salary:', @new_salary),
                @error_context
            );
        END
        
        -- Rollback transaction
        IF @@TRANCOUNT > 0
        BEGIN
            ROLLBACK TRANSACTION;
            
            UPDATE error_log 
            SET rollback_successful = 1,
                transaction_status = 'ROLLED_BACK'
            WHERE transaction_id = @transaction_id;
        END
        
        SELECT 'Employee update failed' as status;
    END CATCH
END;

-- Stored procedure with error categorization
CREATE PROCEDURE DeleteEmployeeWithErrorCategorization
    @employee_id INT
AS
BEGIN
    DECLARE @transaction_id BIGINT;
    DECLARE @error_category VARCHAR(50);
    DECLARE @error_context NVARCHAR(MAX);
    
    BEGIN TRANSACTION;
    SET @transaction_id = @@TRANCOUNT;
    SET @error_context = CONCAT('Deleting employee ', @employee_id);
    
    BEGIN TRY
        -- Check if employee exists
        IF NOT EXISTS (SELECT 1 FROM employees WHERE employee_id = @employee_id)
        BEGIN
            SET @error_category = 'VALIDATION_ERROR';
            SET @error_context = CONCAT(@error_context, ' - Employee not found');
            RAISERROR('Employee ID %d not found', 16, 1, @employee_id);
        END
        
        -- Check if employee has active orders
        IF EXISTS (SELECT 1 FROM orders WHERE employee_id = @employee_id AND status = 'active')
        BEGIN
            SET @error_category = 'BUSINESS_RULE_VIOLATION';
            SET @error_context = CONCAT(@error_context, ' - Employee has active orders');
            RAISERROR('Cannot delete employee with active orders', 16, 1);
        END
        
        -- Check if employee is a manager
        IF EXISTS (SELECT 1 FROM employees WHERE manager_id = @employee_id)
        BEGIN
            SET @error_category = 'BUSINESS_RULE_VIOLATION';
            SET @error_context = CONCAT(@error_context, ' - Employee is a manager');
            RAISERROR('Cannot delete employee who is a manager', 16, 1);
        END
        
        -- Delete employee
        DELETE FROM employees WHERE employee_id = @employee_id;
        
        -- Insert deletion audit
        INSERT INTO employee_audit (employee_id, action, action_time, performed_by)
        VALUES (@employee_id, 'EMPLOYEE_DELETED', GETDATE(), SYSTEM_USER);
        
        COMMIT TRANSACTION;
        
        -- Log successful deletion
        INSERT INTO error_log (
            transaction_id,
            error_number,
            error_message,
            user_name,
            session_id,
            transaction_status,
            affected_tables,
            input_parameters,
            error_context
        ) VALUES (
            @transaction_id,
            0,
            'Employee deleted successfully',
            SYSTEM_USER,
            @@SPID,
            'COMMITTED',
            'employees, employee_audit',
            CONCAT('employee_id:', @employee_id),
            @error_context
        );
        
        SELECT 'Employee deleted successfully' as status;
    END TRY
    BEGIN CATCH
        SET @error_category = CASE 
            WHEN ERROR_NUMBER() = 547 THEN 'CONSTRAINT_VIOLATION'
            WHEN ERROR_NUMBER() = 2627 THEN 'DUPLICATE_KEY'
            WHEN ERROR_NUMBER() = 2601 THEN 'UNIQUE_CONSTRAINT'
            ELSE 'UNEXPECTED_ERROR'
        END;
        
        -- Log categorized error
        INSERT INTO error_log (
            transaction_id,
            error_number,
            error_message,
            error_severity,
            error_state,
            error_line,
            error_procedure,
            user_name,
            session_id,
            transaction_status,
            affected_tables,
            input_parameters,
            error_context
        ) VALUES (
            @transaction_id,
            ERROR_NUMBER(),
            CONCAT(@error_category, ': ', ERROR_MESSAGE()),
            ERROR_SEVERITY(),
            ERROR_STATE(),
            ERROR_LINE(),
            ERROR_PROCEDURE(),
            SYSTEM_USER,
            @@SPID,
            'FAILED',
            'employees, employee_audit',
            CONCAT('employee_id:', @employee_id),
            @error_context
        );
        
        -- Rollback transaction
        IF @@TRANCOUNT > 0
        BEGIN
            ROLLBACK TRANSACTION;
            
            UPDATE error_log 
            SET rollback_successful = 1,
                transaction_status = 'ROLLED_BACK'
            WHERE transaction_id = @transaction_id;
        END
        
        SELECT CONCAT('Employee deletion failed: ', @error_category) as status;
    END CATCH
END;

-- Query to analyze error patterns by category
SELECT 
    CASE 
        WHEN error_message LIKE '%VALIDATION_ERROR%' THEN 'Validation Error'
        WHEN error_message LIKE '%BUSINESS_RULE_VIOLATION%' THEN 'Business Rule Violation'
        WHEN error_message LIKE '%CONSTRAINT_VIOLATION%' THEN 'Constraint Violation'
        WHEN error_message LIKE '%DUPLICATE_KEY%' THEN 'Duplicate Key'
        WHEN error_message LIKE '%UNIQUE_CONSTRAINT%' THEN 'Unique Constraint'
        WHEN error_message LIKE '%UNEXPECTED_ERROR%' THEN 'Unexpected Error'
        ELSE 'Other'
    END as error_category,
    COUNT(*) as error_count,
    AVG(execution_time_ms) as avg_execution_time,
    COUNT(CASE WHEN rollback_successful = 1 THEN 1 END) as successful_rollbacks
FROM error_log
WHERE error_time > DATEADD(DAY, -7, GETDATE())
  AND error_number > 0
GROUP BY 
    CASE 
        WHEN error_message LIKE '%VALIDATION_ERROR%' THEN 'Validation Error'
        WHEN error_message LIKE '%BUSINESS_RULE_VIOLATION%' THEN 'Business Rule Violation'
        WHEN error_message LIKE '%CONSTRAINT_VIOLATION%' THEN 'Constraint Violation'
        WHEN error_message LIKE '%DUPLICATE_KEY%' THEN 'Duplicate Key'
        WHEN error_message LIKE '%UNIQUE_CONSTRAINT%' THEN 'Unique Constraint'
        WHEN error_message LIKE '%UNEXPECTED_ERROR%' THEN 'Unexpected Error'
        ELSE 'Other'
    END
ORDER BY error_count DESC;

-- Query to monitor transaction success rates
SELECT 
    CAST(error_time AS DATE) as transaction_date,
    COUNT(*) as total_transactions,
    COUNT(CASE WHEN transaction_status = 'COMMITTED' THEN 1 END) as successful_transactions,
    COUNT(CASE WHEN transaction_status IN ('FAILED', 'ROLLED_BACK') THEN 1 END) as failed_transactions,
    CAST(COUNT(CASE WHEN transaction_status = 'COMMITTED' THEN 1 END) * 100.0 / COUNT(*) AS DECIMAL(5,2)) as success_rate_percent,
    AVG(execution_time_ms) as avg_execution_time
FROM error_log
WHERE error_time > DATEADD(DAY, -30, GETDATE())
GROUP BY CAST(error_time AS DATE)
ORDER BY transaction_date DESC;

-- Query to identify problematic procedures
SELECT 
    error_procedure,
    COUNT(*) as error_count,
    COUNT(DISTINCT user_name) as affected_users,
    AVG(execution_time_ms) as avg_execution_time,
    COUNT(CASE WHEN rollback_successful = 1 THEN 1 END) as successful_rollbacks,
    MIN(error_time) as first_error,
    MAX(error_time) as last_error
FROM error_log
WHERE error_procedure IS NOT NULL
  AND error_time > DATEADD(DAY, -30, GETDATE())
  AND error_number > 0
GROUP BY error_procedure
ORDER BY error_count DESC;

-- Query to analyze retry patterns
SELECT 
    retry_count,
    COUNT(*) as transaction_count,
    COUNT(CASE WHEN transaction_status = 'COMMITTED' THEN 1 END) as successful_after_retry,
    COUNT(CASE WHEN transaction_status IN ('FAILED', 'ROLLED_BACK') THEN 1 END) as failed_after_retry,
    AVG(execution_time_ms) as avg_execution_time
FROM error_log
WHERE error_time > DATEADD(DAY, -7, GETDATE())
GROUP BY retry_count
ORDER BY retry_count;
```

### Advanced Questions

1. How do you debug a deadlock in a production database? _(Asked in Cognizant)_

**🧩 Foundation:** Use system views, extended events, and deadlock analysis tools to identify, analyze, and resolve deadlock situations in production environments.

**⚙️ Function:** Diagnose deadlock causes, identify problematic queries, and implement preventive measures to minimize deadlock occurrences.

**🔁 Flow:**
```sql
-- Step 1: Enable deadlock monitoring
-- Enable trace flags for deadlock monitoring
DBCC TRACEON (1222, -1);  -- Log deadlock information to error log
DBCC TRACEON (1204, -1);  -- Log deadlock information to error log (detailed)

-- Create extended event session for deadlock monitoring
CREATE EVENT SESSION [Deadlock_Monitoring] ON SERVER 
ADD EVENT sqlserver.xml_deadlock_report(
    ACTION(
        sqlserver.client_app_name,
        sqlserver.client_hostname,
        sqlserver.database_name,
        sqlserver.sql_text,
        sqlserver.username
    )
)
ADD TARGET package0.event_file(
    SET filename=N'C:\Deadlock_Monitoring.xel',
    max_file_size=5,
    max_rollover_files=4
)
WITH (MAX_MEMORY=4096 KB, EVENT_RETENTION_MODE=ALLOW_SINGLE_EVENT_LOSS);

-- Start the extended event session
ALTER EVENT SESSION [Deadlock_Monitoring] ON SERVER STATE = START;

-- Step 2: Monitor current deadlock situations
-- Check for blocking and potential deadlocks
SELECT 
    blocking.session_id as blocking_session_id,
    blocking.login_name as blocking_login,
    blocking.host_name as blocking_host,
    blocking.program_name as blocking_program,
    blocked.session_id as blocked_session_id,
    blocked.login_name as blocked_login,
    blocked.host_name as blocked_host,
    blocked.program_name as blocked_program,
    blocked.wait_type,
    blocked.wait_time,
    blocked.wait_resource,
    blocked.blocking_session_id
FROM sys.dm_exec_requests blocked
INNER JOIN sys.dm_exec_sessions blocking ON blocked.blocking_session_id = blocking.session_id
WHERE blocked.blocking_session_id > 0;

-- Step 3: Analyze deadlock information from system health
-- Query deadlock information from system health extended events
SELECT 
    CAST(target_data AS XML) as deadlock_xml,
    event_time
FROM sys.dm_xe_session_targets st
INNER JOIN sys.dm_xe_sessions s ON st.event_session_address = s.address
CROSS APPLY sys.dm_xe_session_targets st2
CROSS APPLY sys.dm_xe_session_events se
WHERE s.name = 'system_health'
  AND st2.target_name = 'ring_buffer'
  AND se.event_name = 'xml_deadlock_report';

-- Step 4: Create deadlock analysis procedure
CREATE PROCEDURE AnalyzeDeadlocks
    @hours_back INT = 24
AS
BEGIN
    -- Query deadlock information from system health
    WITH deadlock_data AS (
        SELECT 
            CAST(target_data AS XML) as deadlock_xml,
            event_time
        FROM sys.dm_xe_session_targets st
        INNER JOIN sys.dm_xe_sessions s ON st.event_session_address = s.address
        CROSS APPLY sys.dm_xe_session_targets st2
        CROSS APPLY sys.dm_xe_session_events se
        WHERE s.name = 'system_health'
          AND st2.target_name = 'ring_buffer'
          AND se.event_name = 'xml_deadlock_report'
          AND event_time > DATEADD(HOUR, -@hours_back, GETDATE())
    )
    SELECT 
        event_time,
        deadlock_xml.value('(/event/@timestamp)[1]', 'datetime2') as deadlock_time,
        deadlock_xml.value('(/event/data/value)[1]', 'varchar(max)') as deadlock_graph
    FROM deadlock_data
    ORDER BY event_time DESC;
END;

-- Step 5: Create deadlock prevention monitoring
CREATE PROCEDURE MonitorDeadlockPrevention
AS
BEGIN
    -- Check for long-running transactions
    SELECT 
        s.session_id,
        s.login_name,
        s.host_name,
        s.program_name,
        t.transaction_id,
        t.name as transaction_name,
        t.transaction_begin_time,
        DATEDIFF(MINUTE, t.transaction_begin_time, GETDATE()) as transaction_duration_minutes,
        r.command,
        r.status,
        r.wait_type,
        r.wait_time,
        r.blocking_session_id
    FROM sys.dm_tran_active_transactions t
    INNER JOIN sys.dm_tran_session_transactions st ON t.transaction_id = st.transaction_id
    INNER JOIN sys.dm_exec_sessions s ON st.session_id = s.session_id
    INNER JOIN sys.dm_exec_requests r ON s.session_id = r.session_id
    WHERE t.transaction_begin_time < DATEADD(MINUTE, -5, GETDATE()) -- Transactions older than 5 minutes
    ORDER BY transaction_duration_minutes DESC;

    -- Check for lock escalation
    SELECT 
        OBJECT_NAME(p.object_id) as table_name,
        p.index_id,
        p.partition_number,
        p.rows,
        l.resource_type,
        l.resource_description,
        l.request_mode,
        l.request_status,
        l.request_session_id
    FROM sys.dm_tran_locks l
    INNER JOIN sys.partitions p ON l.resource_associated_entity_id = p.hobt_id
    WHERE l.resource_type = 'OBJECT'
      AND l.request_mode IN ('X', 'IX', 'SIX')
    ORDER BY p.rows DESC;

    -- Check for blocking chains
    WITH blocking_chain AS (
        SELECT 
            session_id,
            blocking_session_id,
            0 as level
        FROM sys.dm_exec_requests
        WHERE blocking_session_id = 0
        
        UNION ALL
        
        SELECT 
            r.session_id,
            r.blocking_session_id,
            bc.level + 1
        FROM sys.dm_exec_requests r
        INNER JOIN blocking_chain bc ON r.session_id = bc.blocking_session_id
        WHERE r.blocking_session_id > 0
          AND bc.level < 10 -- Prevent infinite recursion
    )
    SELECT 
        level,
        session_id,
        blocking_session_id,
        s.login_name,
        s.host_name,
        s.program_name,
        r.command,
        r.wait_type,
        r.wait_time
    FROM blocking_chain bc
    INNER JOIN sys.dm_exec_sessions s ON bc.session_id = s.session_id
    INNER JOIN sys.dm_exec_requests r ON bc.session_id = r.session_id
    ORDER BY level, session_id;
END;

-- Step 6: Create deadlock resolution procedure
CREATE PROCEDURE ResolveDeadlocks
    @kill_blocking_sessions BIT = 0
AS
BEGIN
    DECLARE @blocking_sessions TABLE (
        session_id INT,
        login_name NVARCHAR(100),
        host_name NVARCHAR(100),
        program_name NVARCHAR(100),
        command NVARCHAR(50),
        wait_time INT,
        blocking_level INT
    );

    -- Identify blocking sessions
    WITH blocking_hierarchy AS (
        SELECT 
            session_id,
            blocking_session_id,
            0 as level
        FROM sys.dm_exec_requests
        WHERE blocking_session_id = 0
        
        UNION ALL
        
        SELECT 
            r.session_id,
            r.blocking_session_id,
            bh.level + 1
        FROM sys.dm_exec_requests r
        INNER JOIN blocking_hierarchy bh ON r.session_id = bh.blocking_session_id
        WHERE r.blocking_session_id > 0
    )
    INSERT INTO @blocking_sessions
    SELECT 
        bh.session_id,
        s.login_name,
        s.host_name,
        s.program_name,
        r.command,
        r.wait_time,
        bh.level
    FROM blocking_hierarchy bh
    INNER JOIN sys.dm_exec_sessions s ON bh.session_id = s.session_id
    INNER JOIN sys.dm_exec_requests r ON bh.session_id = r.session_id
    WHERE bh.level > 0; -- Only blocking sessions, not the root

    -- Display blocking information
    SELECT 
        'Blocking Sessions Found' as status,
        COUNT(*) as blocking_count,
        MAX(blocking_level) as max_blocking_level
    FROM @blocking_sessions;

    -- Kill blocking sessions if requested
    IF @kill_blocking_sessions = 1
    BEGIN
        DECLARE @session_id INT;
        DECLARE @kill_statement NVARCHAR(100);
        
        DECLARE blocking_cursor CURSOR FOR
        SELECT session_id FROM @blocking_sessions
        ORDER BY blocking_level DESC, wait_time DESC;
        
        OPEN blocking_cursor;
        FETCH NEXT FROM blocking_cursor INTO @session_id;
        
        WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @kill_statement = 'KILL ' + CAST(@session_id AS NVARCHAR(10));
            EXEC sp_executesql @kill_statement;
            
            PRINT 'Killed session: ' + CAST(@session_id AS NVARCHAR(10));
            
            FETCH NEXT FROM blocking_cursor INTO @session_id;
        END
        
        CLOSE blocking_cursor;
        DEALLOCATE blocking_cursor;
        
        SELECT 'Blocking sessions killed' as result;
    END
    ELSE
    BEGIN
        SELECT 'Blocking sessions identified but not killed' as result;
    END
END;

-- Step 7: Create deadlock prevention recommendations
CREATE PROCEDURE GenerateDeadlockRecommendations
AS
BEGIN
    -- Analyze transaction patterns
    SELECT 
        'Transaction Analysis' as analysis_type,
        COUNT(*) as total_transactions,
        AVG(DATEDIFF(MILLISECOND, transaction_begin_time, GETDATE())) as avg_transaction_duration,
        MAX(DATEDIFF(MILLISECOND, transaction_begin_time, GETDATE())) as max_transaction_duration
    FROM sys.dm_tran_active_transactions;

    -- Analyze lock patterns
    SELECT 
        'Lock Analysis' as analysis_type,
        resource_type,
        request_mode,
        COUNT(*) as lock_count
    FROM sys.dm_tran_locks
    GROUP BY resource_type, request_mode
    ORDER BY lock_count DESC;

    -- Identify potential deadlock scenarios
    SELECT 
        'Potential Deadlock Scenarios' as analysis_type,
        s1.session_id as session1,
        s2.session_id as session2,
        s1.login_name as user1,
        s2.login_name as user2,
        s1.program_name as program1,
        s2.program_name as program2
    FROM sys.dm_exec_sessions s1
    CROSS JOIN sys.dm_exec_sessions s2
    WHERE s1.session_id < s2.session_id
      AND s1.database_id = s2.database_id
      AND s1.session_id IN (SELECT session_id FROM sys.dm_tran_locks)
      AND s2.session_id IN (SELECT session_id FROM sys.dm_tran_locks);

    -- Generate recommendations
    SELECT 
        'Recommendations' as recommendation_type,
        'Use SET TRANSACTION ISOLATION LEVEL READ COMMITTED' as recommendation,
        'Reduces lock contention' as reason
    UNION ALL
    SELECT 
        'Recommendations',
        'Keep transactions short and focused',
        'Reduces lock hold time'
    UNION ALL
    SELECT 
        'Recommendations',
        'Access tables in consistent order',
        'Prevents circular wait conditions'
    UNION ALL
    SELECT 
        'Recommendations',
        'Use appropriate indexes',
        'Reduces lock escalation'
    UNION ALL
    SELECT 
        'Recommendations',
        'Consider using READ UNCOMMITTED for reporting',
        'Eliminates blocking for read operations';
END;

-- Step 8: Create deadlock monitoring dashboard
CREATE PROCEDURE DeadlockDashboard
    @hours_back INT = 24
AS
BEGIN
    -- Deadlock count by hour
    SELECT 
        'Deadlock Count by Hour' as metric,
        DATEPART(HOUR, event_time) as hour_of_day,
        COUNT(*) as deadlock_count
    FROM (
        SELECT CAST(target_data AS XML).value('(/event/@timestamp)[1]', 'datetime2') as event_time
        FROM sys.dm_xe_session_targets st
        INNER JOIN sys.dm_xe_sessions s ON st.event_session_address = s.address
        CROSS APPLY sys.dm_xe_session_targets st2
        CROSS APPLY sys.dm_xe_session_events se
        WHERE s.name = 'system_health'
          AND st2.target_name = 'ring_buffer'
          AND se.event_name = 'xml_deadlock_report'
          AND CAST(target_data AS XML).value('(/event/@timestamp)[1]', 'datetime2') > DATEADD(HOUR, -@hours_back, GETDATE())
    ) deadlocks
    GROUP BY DATEPART(HOUR, event_time)
    ORDER BY hour_of_day;

    -- Current blocking situation
    SELECT 
        'Current Blocking' as metric,
        COUNT(*) as blocked_sessions,
        COUNT(DISTINCT blocking_session_id) as blocking_sessions
    FROM sys.dm_exec_requests
    WHERE blocking_session_id > 0;

    -- Long-running transactions
    SELECT 
        'Long Running Transactions' as metric,
        COUNT(*) as long_running_count
    FROM sys.dm_tran_active_transactions t
    WHERE t.transaction_begin_time < DATEADD(MINUTE, -10, GETDATE());

    -- Lock escalation
    SELECT 
        'Lock Escalation' as metric,
        COUNT(*) as escalated_locks
    FROM sys.dm_tran_locks l
    INNER JOIN sys.partitions p ON l.resource_associated_entity_id = p.hobt_id
    WHERE l.resource_type = 'OBJECT'
      AND p.rows > 1000; -- Large tables with object-level locks
END;

-- Step 9: Create deadlock alert procedure
CREATE PROCEDURE CheckDeadlockAlerts
    @deadlock_threshold INT = 5, -- Alert if more than 5 deadlocks in last hour
    @blocking_threshold INT = 10 -- Alert if more than 10 blocked sessions
AS
BEGIN
    DECLARE @deadlock_count INT;
    DECLARE @blocking_count INT;
    DECLARE @alert_message NVARCHAR(MAX);

    -- Count recent deadlocks
    SELECT @deadlock_count = COUNT(*)
    FROM (
        SELECT CAST(target_data AS XML).value('(/event/@timestamp)[1]', 'datetime2') as event_time
        FROM sys.dm_xe_session_targets st
        INNER JOIN sys.dm_xe_sessions s ON st.event_session_address = s.address
        CROSS APPLY sys.dm_xe_session_targets st2
        CROSS APPLY sys.dm_xe_session_events se
        WHERE s.name = 'system_health'
          AND st2.target_name = 'ring_buffer'
          AND se.event_name = 'xml_deadlock_report'
          AND CAST(target_data AS XML).value('(/event/@timestamp)[1]', 'datetime2') > DATEADD(HOUR, -1, GETDATE())
    ) deadlocks;

    -- Count current blocking
    SELECT @blocking_count = COUNT(*)
    FROM sys.dm_exec_requests
    WHERE blocking_session_id > 0;

    -- Generate alerts
    SET @alert_message = '';

    IF @deadlock_count > @deadlock_threshold
        SET @alert_message = @alert_message + 'HIGH DEADLOCK COUNT: ' + CAST(@deadlock_count AS NVARCHAR(10)) + ' deadlocks in last hour. ';

    IF @blocking_count > @blocking_threshold
        SET @alert_message = @alert_message + 'HIGH BLOCKING: ' + CAST(@blocking_count AS NVARCHAR(10)) + ' blocked sessions. ';

    IF @alert_message != ''
    BEGIN
        -- Log alert
        INSERT INTO system_alerts (alert_type, alert_message, alert_time, severity)
        VALUES ('DEADLOCK', @alert_message, GETDATE(), 'HIGH');

        -- Return alert information
        SELECT 
            'ALERT' as status,
            @alert_message as message,
            @deadlock_count as deadlock_count,
            @blocking_count as blocking_count;
    END
    ELSE
    BEGIN
        SELECT 
            'OK' as status,
            'No deadlock alerts' as message,
            @deadlock_count as deadlock_count,
            @blocking_count as blocking_count;
    END
END;

-- Step 10: Create deadlock prevention index recommendations
CREATE PROCEDURE AnalyzeDeadlockIndexes
AS
BEGIN
    -- Identify tables with high lock contention
    SELECT 
        OBJECT_NAME(p.object_id) as table_name,
        p.index_id,
        p.partition_number,
        p.rows,
        COUNT(*) as lock_count,
        COUNT(CASE WHEN l.request_mode IN ('X', 'IX', 'SIX') THEN 1 END) as exclusive_locks
    FROM sys.dm_tran_locks l
    INNER JOIN sys.partitions p ON l.resource_associated_entity_id = p.hobt_id
    WHERE l.resource_type = 'OBJECT'
    GROUP BY p.object_id, p.index_id, p.partition_number, p.rows
    HAVING COUNT(*) > 10 -- Tables with high lock activity
    ORDER BY lock_count DESC;

    -- Check for missing indexes that might cause table scans
    SELECT 
        dm_mid.statement as table_name,
        dm_mid.equality_columns,
        dm_mid.inequality_columns,
        dm_mid.included_columns,
        dm_migs.avg_user_impact,
        dm_migs.user_seeks,
        dm_migs.user_scans
    FROM sys.dm_db_missing_index_group_stats dm_migs
    INNER JOIN sys.dm_db_missing_index_groups dm_mig ON dm_migs.group_handle = dm_mig.index_group_handle
    INNER JOIN sys.dm_db_missing_index_details dm_mid ON dm_mig.index_handle = dm_mid.index_handle
    WHERE dm_migs.avg_user_impact > 50 -- High impact missing indexes
    ORDER BY dm_migs.avg_user_impact DESC;
END;
```

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
