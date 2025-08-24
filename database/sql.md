# SQL Interview Questions for Senior Java Backend Developers (MNC Service-Based Companies)

---

## 1. SQL Basics and Queries

### Basic Questions
1. What is SQL, and what are its main types of commands (DDL, DML, DCL, TCL)? _(TCS, Infosys)_

**Answer:** SQL (Structured Query Language) is a standard language for managing relational databases. Its main command types are:
- **DDL (Data Definition Language)**: CREATE, ALTER, DROP, TRUNCATE - Define database structure
- **DML (Data Manipulation Language)**: SELECT, INSERT, UPDATE, DELETE - Manipulate data
- **DCL (Data Control Language)**: GRANT, REVOKE - Control access permissions
- **TCL (Transaction Control Language)**: COMMIT, ROLLBACK, SAVEPOINT - Manage transactions

**Memory Trick:** "DDL = Define, DML = Manipulate, DCL = Control, TCL = Transaction"

2. What is the difference between SELECT, INSERT, UPDATE, and DELETE statements? _(Capgemini)_

**Answer:** 
- **SELECT**: Retrieves/reads data from tables (READ operation)
- **INSERT**: Adds new records to tables (CREATE operation)
- **UPDATE**: Modifies existing records in tables (MODIFY operation)
- **DELETE**: Removes records from tables (REMOVE operation)

**Memory Trick:** "CRUD operations - Create(INSERT), Read(SELECT), Update(UPDATE), Delete(DELETE)"

3. How do you use WHERE, ORDER BY, and GROUP BY clauses? _(Wipro)_

**Answer:**
- **WHERE**: Filters rows based on conditions (WHERE salary > 50000)
- **ORDER BY**: Sorts results (ORDER BY name ASC/DESC)
- **GROUP BY**: Groups rows for aggregate functions (GROUP BY department)

**Memory Trick:** "WHERE filters, ORDER sorts, GROUP combines"

4. What is a primary key, and how does it differ from a unique key? _(Capgemini)_

**Answer:**
- **Primary Key**: Unique identifier for each row, cannot be NULL, only one per table
- **Unique Key**: Ensures uniqueness but can be NULL, multiple allowed per table

**Memory Trick:** "Primary = One & Required, Unique = Many & Optional"

5. What is the difference between DELETE, TRUNCATE, and DROP commands? _(Tech Mahindra)_

**Answer:**
- **DELETE**: Removes specific rows, can be rolled back, slower, maintains structure
- **TRUNCATE**: Removes all rows, cannot be rolled back, faster, resets auto-increment
- **DROP**: Removes entire table, cannot be rolled back, fastest, destroys structure

**Memory Trick:** "DELETE = Rows, TRUNCATE = All Data, DROP = Everything"

### Basic Query Questions
1. Write a query to retrieve all columns from a table named `employees`. _(TCS)_

**Answer:**
```sql
SELECT * FROM employees;
```
**Memory Trick:** "SELECT * = Get everything"

2. Write a query to insert a new record into a table named `products`. _(Infosys)_

**Answer:**
```sql
INSERT INTO products (product_id, name, price, category) 
VALUES (1, 'Laptop', 999.99, 'Electronics');
```
**Memory Trick:** "INSERT INTO table (columns) VALUES (data)"

3. Write a query to update the salary of an employee with a specific ID in the `employees` table. _(Capgemini)_

**Answer:**
```sql
UPDATE employees 
SET salary = 75000 
WHERE employee_id = 101;
```
**Memory Trick:** "UPDATE table SET column = value WHERE condition"

4. Write a query to delete records from the `orders` table where the order date is older than a specific date. _(Wipro)_

**Answer:**
```sql
DELETE FROM orders 
WHERE order_date < '2023-01-01';
```
**Memory Trick:** "DELETE FROM table WHERE condition"

5. Write a query to select the top 10 rows from the `customers` table ordered by `last_name`. _(Accenture)_

**Answer:**
```sql
SELECT TOP 10 * FROM customers 
ORDER BY last_name ASC;
```
**Memory Trick:** "TOP N + ORDER BY = Limited sorted results"

### Intermediate Questions
1. What is the difference between HAVING and WHERE clauses? _(Accenture)_

**Answer:**
- **WHERE**: Filters individual rows before grouping
- **HAVING**: Filters grouped results after GROUP BY

**Example:**
```sql
SELECT department, AVG(salary) 
FROM employees 
WHERE salary > 30000        -- Filters employees first
GROUP BY department 
HAVING AVG(salary) > 50000; -- Filters departments after grouping
```
**Memory Trick:** "WHERE = Before GROUP, HAVING = After GROUP"

2. How do you use aggregate functions like COUNT, SUM, AVG, MIN, and MAX? _(TCS)_

**Answer:**
```sql
SELECT 
    COUNT(*) as total_employees,    -- Count all rows
    SUM(salary) as total_salary,    -- Sum of values
    AVG(salary) as avg_salary,      -- Average
    MIN(salary) as min_salary,      -- Minimum value
    MAX(salary) as max_salary       -- Maximum value
FROM employees;
```
**Memory Trick:** "Count, Sum, Average, Min, Max - CSAMM"

3. What is the purpose of the DISTINCT keyword in SQL? _(Infosys)_

**Answer:** DISTINCT removes duplicate rows from the result set.

```sql
SELECT DISTINCT department FROM employees;  -- Unique departments only
SELECT DISTINCT city, state FROM customers; -- Unique city-state combinations
```
**Memory Trick:** "DISTINCT = No duplicates"

4. What is a subquery, and when would you use it? _(TCS Digital)_

**Answer:** A subquery is a query within another query. Used for:
- **WHERE clause**: Filter based on another query's result
- **FROM clause**: Use query result as a table
- **SELECT clause**: Get single value for each row

```sql
-- Subquery in WHERE
SELECT * FROM employees 
WHERE salary > (SELECT AVG(salary) FROM employees);

-- Subquery in FROM
SELECT * FROM (SELECT department, COUNT(*) as count FROM employees GROUP BY department) dept_stats;
```
**Memory Trick:** "Subquery = Query inside Query"

5. What is a foreign key, and how does it enforce referential integrity? _(Accenture)_

**Answer:**
- **Foreign Key**: Column that references primary key of another table
- **Referential Integrity**: Ensures data consistency between related tables

```sql
CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
```
**Memory Trick:** "Foreign Key = Link to Another Table"

### Intermediate Query Questions
1. Write a query to find the average salary of employees grouped by department. _(Wipro)_

**Answer:**
```sql
SELECT department, AVG(salary) as avg_salary
FROM employees
GROUP BY department;
```
**Memory Trick:** "GROUP BY + AVG = Average per group"

2. Write a query to retrieve unique customer emails from the `customers` table. _(Accenture)_

**Answer:**
```sql
SELECT DISTINCT email FROM customers;
```
**Memory Trick:** "DISTINCT = Unique values only"

3. Write a query to find the top 5 highest-paid employees in the `employees` table. _(TCS Digital)_

**Answer:**
```sql
SELECT TOP 5 name, salary
FROM employees
ORDER BY salary DESC;
```
**Memory Trick:** "TOP N + ORDER BY DESC = Highest values"

4. Write a query to count the number of orders per customer using the `orders` table. _(Infosys)_

**Answer:**
```sql
SELECT customer_id, COUNT(*) as order_count
FROM orders
GROUP BY customer_id;
```
**Memory Trick:** "GROUP BY + COUNT = Count per group"

5. Write a query to find employees with salaries above the company average. _(Cognizant)_

**Answer:**
```sql
SELECT name, salary
FROM employees
WHERE salary > (SELECT AVG(salary) FROM employees);
```
**Memory Trick:** "Subquery in WHERE = Compare with calculated value"

### Advanced Questions
1. What is a Common Table Expression (CTE), and when would you use it over a subquery? _(Zoho)_

**Answer:** CTE is a temporary named result set that exists only within the scope of a single statement.

**Advantages over subquery:**
- More readable and maintainable
- Can be referenced multiple times
- Better for recursive queries

```sql
WITH employee_stats AS (
    SELECT department, AVG(salary) as avg_salary
    FROM employees
    GROUP BY department
)
SELECT * FROM employee_stats WHERE avg_salary > 50000;
```
**Memory Trick:** "CTE = WITH clause = Temporary named query"

2. What is the difference between UNION and UNION ALL? _(TCS Digital)_

**Answer:**
- **UNION**: Combines results and removes duplicates
- **UNION ALL**: Combines results and keeps duplicates (faster)

```sql
-- UNION (removes duplicates)
SELECT name FROM employees
UNION
SELECT name FROM contractors;

-- UNION ALL (keeps duplicates)
SELECT name FROM employees
UNION ALL
SELECT name FROM contractors;
```
**Memory Trick:** "UNION = Unique, UNION ALL = All (including duplicates)"

3. What is database normalization, and why is it important? _(Deloitte)_

**Answer:** Normalization is organizing data to reduce redundancy and improve data integrity.

**Benefits:**
- Eliminates data redundancy
- Prevents update anomalies
- Ensures data consistency
- Reduces storage space

**Normal Forms:**
- 1NF: Atomic values, no repeating groups
- 2NF: 1NF + no partial dependencies
- 3NF: 2NF + no transitive dependencies

**Memory Trick:** "Normalization = No Redundancy + Data Integrity"

4. What is the COALESCE function, and how is it used to handle NULLs? _(EY)_

**Answer:** COALESCE returns the first non-NULL value from a list of expressions.

```sql
SELECT 
    name,
    COALESCE(phone, email, 'No Contact') as contact_info
FROM customers;
```
**Memory Trick:** "COALESCE = First non-NULL value"

5. How do you write a query to retrieve the top N rows from a table? _(Cognizant)_

**Answer:**
```sql
-- SQL Server
SELECT TOP 10 * FROM employees ORDER BY salary DESC;

-- MySQL
SELECT * FROM employees ORDER BY salary DESC LIMIT 10;

-- Oracle
SELECT * FROM employees WHERE ROWNUM <= 10 ORDER BY salary DESC;
```
**Memory Trick:** "TOP (SQL Server), LIMIT (MySQL), ROWNUM (Oracle)"

### Advanced Query Questions
1. Write a query to combine records from two tables, `orders_2023` and `orders_2024`, excluding duplicates. _(Cognizant)_

**Answer:**
```sql
SELECT * FROM orders_2023
UNION
SELECT * FROM orders_2024;
```
**Memory Trick:** "UNION = Combine + Remove duplicates"

2. Write a query to find the second-highest salary in the `employees` table. _(Infosys)_

**Answer:**
```sql
SELECT MAX(salary) as second_highest_salary
FROM employees
WHERE salary < (SELECT MAX(salary) FROM employees);
```
**Memory Trick:** "MAX of values less than MAX = Second highest"

3. Write a query to find employees with the highest salary in each department. _(TCS BFSI)_

**Answer:**
```sql
SELECT e.name, e.department, e.salary
FROM employees e
WHERE e.salary = (
    SELECT MAX(salary) 
    FROM employees 
    WHERE department = e.department
);
```
**Memory Trick:** "Correlated subquery = Compare with same group"

4. Write a query to pivot data to show sales by product as columns for each region. _(Deloitte)_

**Answer:**
```sql
SELECT region,
    SUM(CASE WHEN product = 'Laptop' THEN sales END) as laptop_sales,
    SUM(CASE WHEN product = 'Phone' THEN sales END) as phone_sales,
    SUM(CASE WHEN product = 'Tablet' THEN sales END) as tablet_sales
FROM sales_data
GROUP BY region;
```
**Memory Trick:** "CASE WHEN + SUM = Pivot data"

5. Write a query using a CTE to calculate running totals of sales by month. _(HCL)_

**Answer:**
```sql
WITH monthly_sales AS (
    SELECT 
        month,
        SUM(sales) as total_sales
    FROM sales_data
    GROUP BY month
    ORDER BY month
)
SELECT 
    month,
    total_sales,
    SUM(total_sales) OVER (ORDER BY month) as running_total
FROM monthly_sales;
```
**Memory Trick:** "CTE + Window Function = Running totals"

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

**Answer:** Main types of SQL joins:
- **INNER JOIN**: Returns matching records from both tables
- **LEFT JOIN**: Returns all records from left table + matching from right
- **RIGHT JOIN**: Returns all records from right table + matching from left
- **FULL OUTER JOIN**: Returns all records from both tables
- **CROSS JOIN**: Returns Cartesian product (all combinations)
- **SELF JOIN**: Joins table with itself

**Memory Trick:** "INNER = Match, LEFT/RIGHT = All from one side, FULL = All from both"

2. What is the difference between INNER JOIN and LEFT JOIN? _(Infosys)_

**Answer:**
- **INNER JOIN**: Only returns rows where join condition is met in both tables
- **LEFT JOIN**: Returns all rows from left table + matching rows from right table (NULL if no match)

```sql
-- INNER JOIN: Only employees with departments
SELECT e.name, d.department_name
FROM employees e
INNER JOIN departments d ON e.dept_id = d.dept_id;

-- LEFT JOIN: All employees (even without departments)
SELECT e.name, d.department_name
FROM employees e
LEFT JOIN departments d ON e.dept_id = d.dept_id;
```
**Memory Trick:** "INNER = Both must match, LEFT = Left table always included"

3. What is a self-join, and when would you use it? _(Wipro)_

**Answer:** Self-join joins a table with itself. Used for:
- Hierarchical data (employees and managers)
- Comparing rows within same table
- Finding relationships within same entity

```sql
-- Find employees and their managers
SELECT e1.name as employee, e2.name as manager
FROM employees e1
LEFT JOIN employees e2 ON e1.manager_id = e2.employee_id;
```
**Memory Trick:** "Self-join = Table joins itself with aliases"

4. What is a cross join, and how is it different from other joins? _(Accenture)_

**Answer:** CROSS JOIN returns Cartesian product - every row from first table paired with every row from second table.

```sql
-- CROSS JOIN: All combinations
SELECT p.name as product, c.name as category
FROM products p
CROSS JOIN categories c;
```
**Memory Trick:** "CROSS JOIN = All possible combinations (no ON clause)"

5. What is a natural join, and what are its limitations? _(Cognizant)_

**Answer:** NATURAL JOIN automatically joins tables on columns with same names.

**Limitations:**
- Requires columns with identical names
- Can cause unexpected results if multiple columns match
- Less explicit and harder to debug

```sql
-- NATURAL JOIN (joins on common column names)
SELECT * FROM employees NATURAL JOIN departments;
```
**Memory Trick:** "NATURAL JOIN = Automatic join on same column names (avoid in production)"

### Basic Query Questions
1. Write a query to join `employees` and `departments` tables to show employee names and their department names. _(TCS)_

**Answer:**
```sql
SELECT e.name, d.department_name
FROM employees e
INNER JOIN departments d ON e.department_id = d.department_id;
```
**Memory Trick:** "INNER JOIN = Get matching records from both tables"

2. Write a query to perform a left join between `customers` and `orders` to include all customers. _(Infosys)_

**Answer:**
```sql
SELECT c.customer_name, o.order_id
FROM customers c
LEFT JOIN orders o ON c.customer_id = o.customer_id;
```
**Memory Trick:** "LEFT JOIN = All customers + their orders (NULL if no orders)"

3. Write a query to join `products` and `categories` using a cross join. _(Accenture)_

**Answer:**
```sql
SELECT p.product_name, c.category_name
FROM products p
CROSS JOIN categories c;
```
**Memory Trick:** "CROSS JOIN = Every product with every category"

4. Write a query to join `employees` with itself to find pairs of employees in the same department. _(Wipro)_

**Answer:**
```sql
SELECT e1.name as employee1, e2.name as employee2, e1.department
FROM employees e1
INNER JOIN employees e2 ON e1.department = e2.department
WHERE e1.employee_id < e2.employee_id;
```
**Memory Trick:** "Self-join with aliases + WHERE to avoid duplicates"

5. Write a query to join `orders` and `order_details` to get order IDs and product IDs. _(Capgemini)_

**Answer:**
```sql
SELECT o.order_id, od.product_id, od.quantity
FROM orders o
INNER JOIN order_details od ON o.order_id = od.order_id;
```
**Memory Trick:** "INNER JOIN = Get order details for each order"

### Intermediate Questions
1. How does a FULL OUTER JOIN differ from a CROSS JOIN? _(Wipro)_

**Answer:**
- **FULL OUTER JOIN**: Returns all records from both tables (matching + non-matching)
- **CROSS JOIN**: Returns Cartesian product (every row from first table with every row from second)

```sql
-- FULL OUTER JOIN: All employees and departments
SELECT e.name, d.department_name
FROM employees e
FULL OUTER JOIN departments d ON e.dept_id = d.dept_id;

-- CROSS JOIN: All combinations
SELECT e.name, d.department_name
FROM employees e
CROSS JOIN departments d;
```
**Memory Trick:** "FULL OUTER = All from both tables, CROSS = All combinations"

2. What happens when you join tables with missing or null values? _(Accenture)_

**Answer:** NULL values in join conditions don't match, so:
- **INNER JOIN**: Rows with NULL join values are excluded
- **LEFT/RIGHT JOIN**: Rows with NULL join values from preserved table are included with NULL values from other table

```sql
-- NULL values don't match in joins
SELECT e.name, d.department_name
FROM employees e
LEFT JOIN departments d ON e.dept_id = d.dept_id;
-- Employees with NULL dept_id will have NULL department_name
```
**Memory Trick:** "NULL in join = No match, excluded from INNER JOIN"

3. How do you handle joins with large datasets to avoid performance issues? _(Cognizant)_

**Answer:**
- **Use indexes** on join columns
- **Limit result set** with WHERE clauses before joining
- **Use appropriate join types** (INNER vs OUTER)
- **Consider query optimization** and execution plans
- **Use table aliases** for readability

**Memory Trick:** "Indexes + WHERE + Right join type = Performance"

4. What is the difference between a join and a subquery in terms of use cases? _(Infosys)_

**Answer:**
- **JOIN**: When you need data from multiple tables in same result set
- **SUBQUERY**: When you need to filter or calculate based on another query's result

```sql
-- JOIN: Get employee and department info together
SELECT e.name, d.department_name
FROM employees e
JOIN departments d ON e.dept_id = d.dept_id;

-- SUBQUERY: Filter employees based on department condition
SELECT name FROM employees
WHERE dept_id IN (SELECT dept_id FROM departments WHERE location = 'NY');
```
**Memory Trick:** "JOIN = Combine data, SUBQUERY = Filter/Calculate"

5. How do you ensure data integrity when joining tables with foreign keys? _(TCS)_

**Answer:**
- **Define proper foreign key constraints** in table creation
- **Use appropriate join types** (INNER JOIN for required relationships)
- **Handle NULL values** appropriately
- **Validate data** before joining
- **Use referential integrity** constraints

```sql
-- Proper foreign key constraint
CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
```
**Memory Trick:** "Foreign keys + Constraints = Data integrity"

### Intermediate Query Questions
1. Write a query to retrieve all customers and their orders, including customers with no orders. _(Wipro)_

**Answer:**
```sql
SELECT c.customer_name, o.order_id, o.order_date
FROM customers c
LEFT JOIN orders o ON c.customer_id = o.customer_id;
```
**Memory Trick:** "LEFT JOIN = All customers + their orders (NULL if no orders)"

2. Write a query to join `employees` and `projects` to find employees not assigned to any project. _(Deloitte)_

**Answer:**
```sql
SELECT e.name
FROM employees e
LEFT JOIN employee_projects ep ON e.employee_id = ep.employee_id
WHERE ep.project_id IS NULL;
```
**Memory Trick:** "LEFT JOIN + WHERE NULL = Find records without matches"

3. Write a query to join three tables (`orders`, `customers`, `order_details`) to get order details with customer names. _(Capgemini)_

**Answer:**
```sql
SELECT c.customer_name, o.order_id, od.product_id, od.quantity
FROM customers c
INNER JOIN orders o ON c.customer_id = o.customer_id
INNER JOIN order_details od ON o.order_id = od.order_id;
```
**Memory Trick:** "Multiple INNER JOINs = Chain of relationships"

4. Write a query to find departments with no employees using a left join. _(Accenture)_

**Answer:**
```sql
SELECT d.department_name
FROM departments d
LEFT JOIN employees e ON d.department_id = e.department_id
WHERE e.employee_id IS NULL;
```
**Memory Trick:** "LEFT JOIN + WHERE NULL = Find empty departments"

5. Write a query to join `products` and `suppliers` to list products with supplier names, handling missing suppliers. _(HCL)_

**Answer:**
```sql
SELECT p.product_name, COALESCE(s.supplier_name, 'Unknown Supplier') as supplier
FROM products p
LEFT JOIN suppliers s ON p.supplier_id = s.supplier_id;
```
**Memory Trick:** "LEFT JOIN + COALESCE = Handle missing data gracefully"

### Advanced Questions
1. How do you optimize a query with multiple joins on large tables? _(Cognizant)_

**Answer:**
- **Use indexes** on join columns
- **Join smaller tables first** to reduce intermediate result sets
- **Use WHERE clauses** to filter before joining
- **Consider join order** (most selective joins first)
- **Use appropriate join types** (INNER vs OUTER)
- **Monitor execution plans** for optimization opportunities

**Memory Trick:** "Indexes + Filter early + Right join order = Performance"

2. What is a hash join, and when is it used by the database engine? _(Amazon)_

**Answer:** Hash join is a join algorithm where:
- **Smaller table** is used to build a hash table in memory
- **Larger table** is scanned to find matches using hash function
- **Used when** one table is much smaller than the other
- **Efficient for** equijoins on large datasets

**Memory Trick:** "Hash join = Build hash table from small table + scan large table"

3. How do you handle joins in a distributed database environment? _(Deloitte)_

**Answer:**
- **Data locality**: Join data on same node when possible
- **Partitioning strategy**: Design tables with same partitioning key
- **Network overhead**: Minimize data transfer between nodes
- **Join algorithms**: Use distributed join techniques
- **Query optimization**: Consider distributed execution plans

**Memory Trick:** "Distributed joins = Minimize network + Use partitioning"

4. What are the performance implications of using non-equijoins? _(Infosys)_

**Answer:** Non-equijoins (>, <, >=, <=, !=) are generally slower because:
- **Cannot use indexes** effectively
- **Require full table scans** in many cases
- **Generate larger result sets** than equijoins
- **More complex optimization** for query planner

```sql
-- Non-equijoin (slower)
SELECT a.name, b.name
FROM table_a a
JOIN table_b b ON a.value > b.value;
```
**Memory Trick:** "Non-equijoins = Slower + No index usage"

5. How do you debug a query with incorrect join results? _(TCS Digital)_

**Answer:**
- **Check join conditions** for correct column references
- **Verify data types** of join columns match
- **Test joins individually** to isolate issues
- **Use table aliases** to avoid column name conflicts
- **Check for NULL values** in join columns
- **Review execution plan** for unexpected behavior

**Memory Trick:** "Debug joins = Check conditions + Test individually + Review plan"

### Advanced Query Questions
1. Write a query to join `sales` and `regions` tables to calculate total sales per region, handling missing regions. _(Cognizant)_

**Answer:**
```sql
SELECT COALESCE(r.region_name, 'Unknown Region') as region,
       SUM(s.sales_amount) as total_sales
FROM sales s
LEFT JOIN regions r ON s.region_id = r.region_id
GROUP BY r.region_name;
```
**Memory Trick:** "LEFT JOIN + COALESCE + GROUP BY = Handle missing data with aggregation"

2. Write a query to perform a multi-table join to generate a report with customer, order, and product details. _(HCL)_

**Answer:**
```sql
SELECT c.customer_name, o.order_date, p.product_name, od.quantity, od.unit_price
FROM customers c
INNER JOIN orders o ON c.customer_id = o.customer_id
INNER JOIN order_details od ON o.order_id = od.order_id
INNER JOIN products p ON od.product_id = p.product_id
WHERE o.order_date >= '2024-01-01';
```
**Memory Trick:** "Chain INNER JOINs = Complete order details with customer and product info"

3. Write a query to join `employees` and `salaries` tables to find the latest salary for each employee. _(Deloitte)_

**Answer:**
```sql
SELECT e.name, s.salary, s.effective_date
FROM employees e
INNER JOIN salaries s ON e.employee_id = s.employee_id
WHERE s.effective_date = (
    SELECT MAX(effective_date) 
    FROM salaries 
    WHERE employee_id = e.employee_id
);
```
**Memory Trick:** "Correlated subquery in WHERE = Get latest record per employee"

4. Write a query to join `orders` and `returns` to find orders with no returns using a left join. _(Accenture)_

**Answer:**
```sql
SELECT o.order_id, o.order_date, o.total_amount
FROM orders o
LEFT JOIN returns r ON o.order_id = r.order_id
WHERE r.return_id IS NULL;
```
**Memory Trick:** "LEFT JOIN + WHERE NULL = Find orders without returns"

5. Write a query to join `products` and `inventory` tables to find products with low stock levels. _(TCS)_

**Answer:**
```sql
SELECT p.product_name, i.quantity_in_stock, i.reorder_level
FROM products p
INNER JOIN inventory i ON p.product_id = i.product_id
WHERE i.quantity_in_stock <= i.reorder_level;
```
**Memory Trick:** "INNER JOIN + WHERE condition = Find products needing reorder"

### Hard Questions
1. How do you optimize a query with nested joins across multiple large tables? _(Amazon)_

**Answer:**
- **Analyze execution plan** to understand join order
- **Use table hints** to force specific join strategies
- **Partition large tables** to reduce scan time
- **Create covering indexes** for join columns
- **Consider materialized views** for complex joins
- **Use query rewriting** to optimize join order

**Memory Trick:** "Plan + Hints + Partition + Index + Views = Optimize complex joins"

2. What are the challenges of joining tables across different databases? _(Deloitte)_

**Answer:**
- **Network latency** affects performance
- **Data type differences** between databases
- **Transaction isolation** across systems
- **Security and authentication** for cross-database access
- **Data consistency** and synchronization issues
- **Limited join optimization** by query planner

**Memory Trick:** "Cross-database joins = Network + Types + Security + Consistency issues"

3. How do you handle joins with dynamic or unpredictable data volumes? _(Cognizant)_

**Answer:**
- **Use adaptive query execution** plans
- **Implement query timeout** mechanisms
- **Use approximate queries** for large datasets
- **Implement pagination** for result sets
- **Use sampling** for initial analysis
- **Monitor and adjust** based on data growth

**Memory Trick:** "Dynamic joins = Adaptive plans + Timeouts + Pagination + Sampling"

4. What is a merge join, and when is it more efficient than other join types? _(Amazon)_

**Answer:** Merge join is efficient when:
- **Both tables are sorted** on join columns
- **Large datasets** with good selectivity
- **Memory constraints** prevent hash joins
- **Indexes exist** on join columns
- **Data is already ordered** by join key

**Memory Trick:** "Merge join = Sorted data + Large datasets + Memory efficient"

5. How do you ensure scalability when joining tables in a high-transaction environment? _(Infosys)_

**Answer:**
- **Use read replicas** for reporting queries
- **Implement connection pooling** to manage resources
- **Use query result caching** for repeated joins
- **Optimize indexes** for join patterns
- **Consider denormalization** for frequently joined data
- **Use asynchronous processing** for complex joins

**Memory Trick:** "Scalable joins = Replicas + Pooling + Caching + Indexes + Denormalization"

### Hard Query Questions
1. Write a query to join `orders`, `customers`, and `payments` to find unpaid orders with customer details. _(Amazon)_

**Answer:**
```sql
SELECT o.order_id, c.customer_name, o.order_date, o.total_amount
FROM orders o
INNER JOIN customers c ON o.customer_id = c.customer_id
LEFT JOIN payments p ON o.order_id = p.order_id
WHERE p.payment_id IS NULL;
```
**Memory Trick:** "Multi-table join + LEFT JOIN + WHERE NULL = Find unpaid orders"

2. Write a query to join `employees` and `attendance` to find employees absent for more than 5 days. _(Deloitte)_

**Answer:**
```sql
SELECT e.name, COUNT(a.attendance_date) as absent_days
FROM employees e
LEFT JOIN attendance a ON e.employee_id = a.employee_id 
    AND a.status = 'present'
WHERE a.attendance_date IS NULL
GROUP BY e.employee_id, e.name
HAVING COUNT(a.attendance_date) > 5;
```
**Memory Trick:** "LEFT JOIN + WHERE NULL + GROUP BY + HAVING = Count absences"

3. Write a query to join `products`, `sales`, and `regions` to rank products by sales within each region. _(Cognizant)_

**Answer:**
```sql
SELECT region_name, product_name, sales_amount,
       RANK() OVER (PARTITION BY region_name ORDER BY sales_amount DESC) as rank
FROM products p
INNER JOIN sales s ON p.product_id = s.product_id
INNER JOIN regions r ON s.region_id = r.region_id;
```
**Memory Trick:** "Window function + PARTITION BY = Rank within groups"

4. Write a query to join `orders` and `shipments` to identify delayed shipments with order details. _(HCL)_

**Answer:**
```sql
SELECT o.order_id, o.order_date, s.expected_delivery, s.actual_delivery
FROM orders o
INNER JOIN shipments s ON o.order_id = s.order_id
WHERE s.actual_delivery > s.expected_delivery;
```
**Memory Trick:** "INNER JOIN + WHERE comparison = Find delayed shipments"

5. Write a query to join `employees` and `managers` hierarchically to list all reporting structures. _(TCS)_

**Answer:**
```sql
WITH RECURSIVE org_hierarchy AS (
    SELECT employee_id, name, manager_id, 1 as level
    FROM employees WHERE manager_id IS NULL
    UNION ALL
    SELECT e.employee_id, e.name, e.manager_id, oh.level + 1
    FROM employees e
    INNER JOIN org_hierarchy oh ON e.manager_id = oh.employee_id
)
SELECT * FROM org_hierarchy ORDER BY level, name;
```
**Memory Trick:** "Recursive CTE = Hierarchical data traversal"

### Application-Based Questions
1. Design a query to join `sales`, `products`, and `categories` to generate a sales report by category. _(HCL)_

**Answer:**
```sql
SELECT c.category_name, 
       COUNT(s.sale_id) as total_sales,
       SUM(s.amount) as total_revenue,
       AVG(s.amount) as avg_sale_value
FROM categories c
LEFT JOIN products p ON c.category_id = p.category_id
LEFT JOIN sales s ON p.product_id = s.product_id
WHERE s.sale_date >= '2024-01-01'
GROUP BY c.category_id, c.category_name
ORDER BY total_revenue DESC;
```
**Memory Trick:** "Multi-table LEFT JOIN + Aggregation + Filtering = Sales report"

2. Write a query to join `customers` and `orders` to identify repeat customers with multiple orders. _(Accenture)_

**Answer:**
```sql
SELECT c.customer_name, COUNT(o.order_id) as order_count
FROM customers c
INNER JOIN orders o ON c.customer_id = o.customer_id
GROUP BY c.customer_id, c.customer_name
HAVING COUNT(o.order_id) > 1
ORDER BY order_count DESC;
```
**Memory Trick:** "INNER JOIN + GROUP BY + HAVING = Find repeat customers"

3. Write a query to join `employees`, `projects`, and `tasks` to track project progress by employee. _(Deloitte)_

**Answer:**
```sql
SELECT e.name, p.project_name, 
       COUNT(t.task_id) as total_tasks,
       SUM(CASE WHEN t.status = 'completed' THEN 1 ELSE 0 END) as completed_tasks
FROM employees e
INNER JOIN employee_projects ep ON e.employee_id = ep.employee_id
INNER JOIN projects p ON ep.project_id = p.project_id
LEFT JOIN tasks t ON p.project_id = t.project_id AND e.employee_id = t.assigned_to
GROUP BY e.employee_id, e.name, p.project_id, p.project_name;
```
**Memory Trick:** "Multi-table joins + CASE WHEN + Aggregation = Progress tracking"

4. Write a query to join `inventory` and `orders` to flag products at risk of stockout. _(Cognizant)_

**Answer:**
```sql
SELECT p.product_name, i.current_stock, 
       SUM(od.quantity) as pending_orders,
       CASE WHEN i.current_stock < SUM(od.quantity) THEN 'At Risk' ELSE 'Safe' END as status
FROM products p
INNER JOIN inventory i ON p.product_id = i.product_id
LEFT JOIN order_details od ON p.product_id = od.product_id
LEFT JOIN orders o ON od.order_id = o.order_id AND o.status = 'pending'
GROUP BY p.product_id, p.product_name, i.current_stock
HAVING i.current_stock < SUM(COALESCE(od.quantity, 0));
```
**Memory Trick:** "Inventory + Orders + CASE WHEN + HAVING = Stockout risk analysis"

5. Write a query to join `sales` and `regions` to analyze regional sales trends over time. _(TCS)_

**Answer:**
```sql
SELECT r.region_name, 
       DATE_TRUNC('month', s.sale_date) as month,
       SUM(s.amount) as monthly_sales,
       LAG(SUM(s.amount)) OVER (PARTITION BY r.region_name ORDER BY DATE_TRUNC('month', s.sale_date)) as prev_month_sales
FROM regions r
LEFT JOIN sales s ON r.region_id = s.region_id
WHERE s.sale_date >= '2023-01-01'
GROUP BY r.region_id, r.region_name, DATE_TRUNC('month', s.sale_date)
ORDER BY r.region_name, month;
```
**Memory Trick:** "Window function + Date grouping + LAG = Trend analysis"

---

## 3. Transactions

### Basic Questions
1. What is a database transaction, and what are the ACID properties? _(TCS, Infosys)_

**Answer:** A transaction is a logical unit of work that contains one or more SQL statements.

**ACID Properties:**
- **Atomicity**: All operations succeed or all fail (all-or-nothing)
- **Consistency**: Database remains in valid state before and after transaction
- **Isolation**: Concurrent transactions don't interfere with each other
- **Durability**: Committed changes are permanent and survive system failures

**Memory Trick:** "ACID = All-or-nothing, Consistent, Isolated, Durable"

2. What is the difference between COMMIT and ROLLBACK in SQL? _(Capgemini)_

**Answer:**
- **COMMIT**: Makes all changes permanent, ends transaction successfully
- **ROLLBACK**: Undoes all changes, returns database to previous state

```sql
BEGIN TRANSACTION;
UPDATE accounts SET balance = balance - 100 WHERE account_id = 1;
UPDATE accounts SET balance = balance + 100 WHERE account_id = 2;
-- If both succeed
COMMIT;
-- If any fails
ROLLBACK;
```
**Memory Trick:** "COMMIT = Save changes, ROLLBACK = Undo changes"

3. What is a savepoint in a transaction, and how is it used? _(Wipro)_

**Answer:** Savepoint creates a point within a transaction to which you can rollback without ending the entire transaction.

```sql
BEGIN TRANSACTION;
UPDATE accounts SET balance = balance - 100 WHERE account_id = 1;
SAVEPOINT sp1;
UPDATE accounts SET balance = balance + 100 WHERE account_id = 2;
-- If second update fails, rollback to savepoint
ROLLBACK TO SAVEPOINT sp1;
-- Continue with transaction
COMMIT;
```
**Memory Trick:** "Savepoint = Checkpoint within transaction"

4. What is the role of the BEGIN TRANSACTION statement? _(Accenture)_

**Answer:** BEGIN TRANSACTION explicitly starts a new transaction and sets the transaction isolation level.

```sql
BEGIN TRANSACTION;
-- SQL statements here
COMMIT;
```
**Memory Trick:** "BEGIN TRANSACTION = Start new transaction block"

5. How does a database ensure data consistency in transactions? _(Cognizant)_

**Answer:**
- **Constraints**: Primary keys, foreign keys, check constraints
- **Locking**: Prevents concurrent modifications to same data
- **Isolation levels**: Control how transactions see each other's changes
- **Validation**: Ensures data meets business rules before commit

**Memory Trick:** "Consistency = Constraints + Locks + Isolation + Validation"

### Basic Query Questions
1. Write a query to demonstrate a transaction that updates two related tables with rollback on failure. _(TCS)_

**Answer:**
```sql
BEGIN TRANSACTION;
BEGIN TRY
    UPDATE inventory SET quantity = quantity - 5 WHERE product_id = 101;
    UPDATE orders SET status = 'shipped' WHERE order_id = 1001;
    COMMIT;
END TRY
BEGIN CATCH
    ROLLBACK;
    THROW;
END CATCH;
```
**Memory Trick:** "BEGIN TRY + COMMIT + BEGIN CATCH + ROLLBACK = Error handling"

2. Write a query to insert a record into the `orders` table within a transaction. _(Infosys)_

**Answer:**
```sql
BEGIN TRANSACTION;
INSERT INTO orders (order_id, customer_id, order_date, total_amount)
VALUES (1001, 101, GETDATE(), 299.99);
COMMIT;
```
**Memory Trick:** "BEGIN + INSERT + COMMIT = Safe insert"

3. Write a query to update the `inventory` table and log the change in a transaction. _(Capgemini)_

**Answer:**
```sql
BEGIN TRANSACTION;
UPDATE inventory SET quantity = quantity - 10 WHERE product_id = 101;
INSERT INTO inventory_log (product_id, change_amount, change_date, reason)
VALUES (101, -10, GETDATE(), 'Sale');
COMMIT;
```
**Memory Trick:** "UPDATE + INSERT + COMMIT = Atomic logging"

4. Write a query to delete records from `employees` with a transaction to ensure rollback on error. _(Wipro)_

**Answer:**
```sql
BEGIN TRANSACTION;
BEGIN TRY
    DELETE FROM employees WHERE department_id = 5;
    -- Check if deletion was successful
    IF @@ROWCOUNT = 0
        THROW 50001, 'No employees found in department', 1;
    COMMIT;
END TRY
BEGIN CATCH
    ROLLBACK;
    SELECT ERROR_MESSAGE();
END CATCH;
```
**Memory Trick:** "DELETE + Error check + ROLLBACK = Safe deletion"

5. Write a query to transfer funds between two accounts in a transaction. _(Accenture)_

**Answer:**
```sql
BEGIN TRANSACTION;
BEGIN TRY
    UPDATE accounts SET balance = balance - 100 WHERE account_id = 1;
    UPDATE accounts SET balance = balance + 100 WHERE account_id = 2;
    COMMIT;
END TRY
BEGIN CATCH
    ROLLBACK;
    THROW;
END CATCH;
```
**Memory Trick:** "Two UPDATEs + COMMIT = Atomic transfer"

### Intermediate Questions
1. How do you handle transaction isolation levels in SQL? _(Wipro)_

**Answer:** Transaction isolation levels control how transactions see each other's changes:

- **READ UNCOMMITTED**: Lowest isolation, can see uncommitted changes (dirty reads)
- **READ COMMITTED**: Default level, only sees committed changes
- **REPEATABLE READ**: Consistent reads within transaction
- **SERIALIZABLE**: Highest isolation, transactions appear to run sequentially

```sql
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
BEGIN TRANSACTION;
-- Transaction code here
COMMIT;
```
**Memory Trick:** "Isolation levels = Control what transactions see from each other"

2. What is the difference between READ COMMITTED and SERIALIZABLE isolation levels? _(Cognizant)_

**Answer:**
- **READ COMMITTED**: Prevents dirty reads, but allows non-repeatable reads and phantom reads
- **SERIALIZABLE**: Prevents dirty reads, non-repeatable reads, and phantom reads (highest isolation)

**Memory Trick:** "READ COMMITTED = Basic protection, SERIALIZABLE = Complete isolation"

3. How do you manage transaction errors in a stored procedure? _(Accenture)_

**Answer:**
```sql
CREATE PROCEDURE TransferFunds
    @fromAccount INT, @toAccount INT, @amount DECIMAL
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION;
        UPDATE accounts SET balance = balance - @amount WHERE account_id = @fromAccount;
        UPDATE accounts SET balance = balance + @amount WHERE account_id = @toAccount;
        COMMIT;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK;
        THROW;
    END CATCH
END;
```
**Memory Trick:** "TRY-CATCH + Transaction check + ROLLBACK = Error handling"

4. What is a nested transaction, and how does it work? _(Infosys)_

**Answer:** Nested transactions allow transactions within transactions. Only the outermost COMMIT makes changes permanent.

```sql
BEGIN TRANSACTION; -- Outer transaction
    INSERT INTO table1 VALUES (1);
    BEGIN TRANSACTION; -- Inner transaction
        INSERT INTO table2 VALUES (1);
    COMMIT; -- Inner commit (changes still not permanent)
COMMIT; -- Outer commit (all changes permanent)
```
**Memory Trick:** "Nested transactions = Only outer COMMIT makes permanent"

5. How do you ensure transaction atomicity in a distributed database? _(Deloitte)_

**Answer:**
- **Two-Phase Commit (2PC)**: Coordinator ensures all nodes commit or rollback
- **Saga Pattern**: Break transaction into smaller, compensatable steps
- **Event Sourcing**: Store events instead of state changes
- **CQRS**: Separate read and write operations

**Memory Trick:** "Distributed atomicity = 2PC + Saga + Event sourcing + CQRS"

### Intermediate Query Questions
1. Write a query to log transaction errors to an `error_log` table within a stored procedure. _(Wipro)_

**Answer:**
```sql
CREATE PROCEDURE ProcessOrder
    @orderId INT
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION;
        UPDATE orders SET status = 'processing' WHERE order_id = @orderId;
        UPDATE inventory SET quantity = quantity - 1 WHERE product_id = 101;
        COMMIT;
    END TRY
    BEGIN CATCH
        INSERT INTO error_log (error_message, error_time, procedure_name)
        VALUES (ERROR_MESSAGE(), GETDATE(), 'ProcessOrder');
        IF @@TRANCOUNT > 0 ROLLBACK;
        THROW;
    END CATCH
END;
```
**Memory Trick:** "Error logging + Transaction rollback = Complete error handling"

2. Write a transaction to update `orders` and `order_details` tables with error handling. _(Capgemini)_

**Answer:**
```sql
BEGIN TRANSACTION;
BEGIN TRY
    UPDATE orders SET total_amount = 299.99 WHERE order_id = 1001;
    UPDATE order_details SET unit_price = 149.99 WHERE order_id = 1001 AND product_id = 101;
    COMMIT;
END TRY
BEGIN CATCH
    ROLLBACK;
    SELECT 'Transaction failed: ' + ERROR_MESSAGE();
END CATCH;
```
**Memory Trick:** "Multiple UPDATEs + Error handling = Atomic updates"

3. Write a query to implement a savepoint in a transaction for partial rollback. _(Accenture)_

**Answer:**
```sql
BEGIN TRANSACTION;
UPDATE accounts SET balance = balance - 100 WHERE account_id = 1;
SAVEPOINT sp1;
UPDATE accounts SET balance = balance + 100 WHERE account_id = 2;
-- If second update fails
ROLLBACK TO SAVEPOINT sp1;
-- Continue with transaction
UPDATE accounts SET balance = balance + 100 WHERE account_id = 3;
COMMIT;
```
**Memory Trick:** "Savepoint + Partial rollback = Selective undo"

4. Write a stored procedure to transfer stock between warehouses in a transaction. _(HCL)_

**Answer:**
```sql
CREATE PROCEDURE TransferStock
    @fromWarehouse INT, @toWarehouse INT, @productId INT, @quantity INT
AS
BEGIN
    BEGIN TRANSACTION;
    BEGIN TRY
        UPDATE warehouse_inventory 
        SET quantity = quantity - @quantity 
        WHERE warehouse_id = @fromWarehouse AND product_id = @productId;
        
        UPDATE warehouse_inventory 
        SET quantity = quantity + @quantity 
        WHERE warehouse_id = @toWarehouse AND product_id = @productId;
        
        INSERT INTO transfer_log (from_warehouse, to_warehouse, product_id, quantity, transfer_date)
        VALUES (@fromWarehouse, @toWarehouse, @productId, @quantity, GETDATE());
        
        COMMIT;
    END TRY
    BEGIN CATCH
        ROLLBACK;
        THROW;
    END CATCH
END;
```
**Memory Trick:** "Two UPDATEs + Logging + Transaction = Atomic transfer"

5. Write a query to audit transaction changes in a `history` table. _(Cognizant)_

**Answer:**
```sql
BEGIN TRANSACTION;
BEGIN TRY
    UPDATE employees SET salary = 75000 WHERE employee_id = 101;
    
    INSERT INTO employee_history (employee_id, old_salary, new_salary, change_date, change_type)
    SELECT 101, (SELECT salary FROM employees WHERE employee_id = 101), 75000, GETDATE(), 'UPDATE';
    
    COMMIT;
END TRY
BEGIN CATCH
    ROLLBACK;
    THROW;
END CATCH;
```
**Memory Trick:** "UPDATE + History logging + Transaction = Audit trail"

### Advanced Questions
1. How do you debug a deadlock in a production database? _(Cognizant)_

**Answer:**
- **Monitor deadlock graphs** using system views
- **Analyze lock wait chains** to identify blocking
- **Review transaction isolation levels** and lock escalation
- **Use deadlock priority** settings
- **Implement retry logic** with exponential backoff
- **Optimize query patterns** to reduce lock contention

**Memory Trick:** "Deadlock debugging = Monitor + Analyze + Review + Retry + Optimize"

2. What is two-phase commit, and when is it used in transactions? _(Deloitte)_

**Answer:** Two-Phase Commit (2PC) ensures atomicity across distributed databases:

**Phase 1 (Prepare)**: Coordinator asks all participants to prepare
**Phase 2 (Commit)**: If all prepare successfully, coordinator tells all to commit

**Used for**: Distributed transactions, microservices coordination, cross-database operations

**Memory Trick:** "2PC = Prepare phase + Commit phase = Distributed atomicity"

3. How do you analyze transaction performance using database tools? _(Amazon)_

**Answer:**
- **Query execution plans** to understand performance bottlenecks
- **Transaction logs** to monitor duration and resource usage
- **Lock monitoring** to identify contention
- **Performance counters** for throughput and latency
- **Wait statistics** to identify blocking operations

**Memory Trick:** "Performance analysis = Execution plans + Logs + Locks + Counters + Wait stats"

4. What are the trade-offs of different transaction isolation levels? _(Infosys)_

**Answer:**
- **READ UNCOMMITTED**: Fastest but allows dirty reads
- **READ COMMITTED**: Good balance of performance and consistency
- **REPEATABLE READ**: Better consistency but more locking
- **SERIALIZABLE**: Highest consistency but slowest performance

**Memory Trick:** "Isolation trade-off = Performance vs Consistency"

5. How do you handle long-running transactions in a high-throughput system? _(TCS Digital)_

**Answer:**
- **Break into smaller transactions** to reduce lock duration
- **Use optimistic locking** instead of pessimistic
- **Implement timeout mechanisms** to prevent indefinite blocking
- **Use batch processing** for large operations
- **Consider eventual consistency** for non-critical operations

**Memory Trick:** "Long transactions = Break down + Optimistic + Timeout + Batch + Eventual consistency"

### Advanced Query Questions
1. Write a query to identify recent deadlocks in a database using system views (e.g., SQL Server’s `sys.dm_tran_locks`). _(Cognizant)_
2. Write a query to monitor transaction logs for long-running transactions. _(Amazon)_
3. Write a stored procedure to handle batch updates with transaction retry logic. _(Deloitte)_
4. Write a query to detect uncommitted transactions in a database. _(HCL)_
5. Write a query to analyze transaction wait times using database performance views. _(Accenture)_

### Hard Questions
1. A query works locally but fails in production with a timeout. How would you diagnose and resolve this? _(Deloitte)_

**Answer:**
- **Compare environments**: Data volume, indexes, statistics, configuration differences
- **Analyze execution plans** in both environments
- **Check resource constraints**: Memory, CPU, disk I/O, connection limits
- **Monitor blocking and deadlocks** in production
- **Review transaction isolation levels** and lock escalation
- **Implement query optimization** and indexing strategies

**Memory Trick:** "Environment comparison + Execution plans + Resource monitoring + Optimization = Production debugging"

2. How do you prevent deadlocks in a high-concurrency database environment? _(Amazon)_

**Answer:**
- **Consistent access order**: Always access tables in same sequence
- **Use appropriate isolation levels**: Avoid unnecessary high isolation
- **Implement timeout mechanisms**: Prevent indefinite waiting
- **Use optimistic locking**: Reduce lock contention
- **Monitor and analyze deadlock patterns**: Identify root causes
- **Implement retry logic**: Handle deadlocks gracefully

**Memory Trick:** "Consistent order + Right isolation + Timeouts + Optimistic + Monitor + Retry = Deadlock prevention"

3. What are the challenges of managing transactions in a distributed database? _(Cognizant)_

**Answer:**
- **Network failures**: Can cause partial commits or rollbacks
- **Clock synchronization**: Timestamp ordering across nodes
- **Data consistency**: Ensuring ACID properties across distributed systems
- **Performance overhead**: Network latency and coordination costs
- **Complex failure recovery**: Handling node failures during transactions
- **Transaction coordination**: Managing distributed commit protocols

**Memory Trick:** "Network + Clock + Consistency + Performance + Recovery + Coordination = Distributed challenges"

4. How do you optimize transaction performance in a multi-user system? _(Infosys)_

**Answer:**
- **Minimize transaction duration**: Keep transactions short and focused
- **Use appropriate isolation levels**: Balance consistency with performance
- **Optimize locking strategies**: Use row-level locks when possible
- **Implement connection pooling**: Reduce connection overhead
- **Use batch operations**: Reduce round trips to database
- **Monitor and tune**: Regular performance analysis and optimization

**Memory Trick:** "Short duration + Right isolation + Optimized locks + Pooling + Batching + Monitoring = Performance optimization"

5. How do you recover a database after a transaction failure in production? _(TCS)_

**Answer:**
- **Identify the failure**: Analyze error logs and transaction state
- **Assess data integrity**: Check for partial updates or inconsistencies
- **Use transaction logs**: Replay or rollback using database logs
- **Implement manual recovery**: Fix data inconsistencies if needed
- **Verify system state**: Ensure database is in consistent state
- **Document and prevent**: Learn from failure and implement safeguards

**Memory Trick:** "Identify + Assess + Logs + Manual fix + Verify + Document = Recovery process"

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

**Answer:**
```java
// Load driver and establish connection
Class.forName("com.mysql.cj.jdbc.Driver");
String url = "jdbc:mysql://localhost:3306/mydb";
String username = "user";
String password = "password";
Connection conn = DriverManager.getConnection(url, username, password);
```
**Memory Trick:** "Load driver + URL + Credentials + getConnection = JDBC connection"

2. What is the difference between Statement and PreparedStatement in JDBC? _(Capgemini)_

**Answer:**
- **Statement**: Simple SQL execution, vulnerable to SQL injection
- **PreparedStatement**: Pre-compiled SQL, prevents SQL injection, better performance

```java
// Statement (vulnerable)
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id = " + userId);

// PreparedStatement (secure)
PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
pstmt.setInt(1, userId);
ResultSet rs = pstmt.executeQuery();
```
**Memory Trick:** "Statement = Simple but unsafe, PreparedStatement = Secure and fast"

3. What is a ResultSet, and how do you iterate through it in Java? _(Wipro)_

**Answer:** ResultSet is an object that contains the results of a SQL query.

```java
PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM employees");
ResultSet rs = pstmt.executeQuery();

while (rs.next()) {
    String name = rs.getString("name");
    int salary = rs.getInt("salary");
    System.out.println(name + ": " + salary);
}
```
**Memory Trick:** "ResultSet = Query results + next() + get methods = Data iteration"

4. What is the role of the DriverManager class in JDBC? _(Accenture)_

**Answer:** DriverManager manages database drivers and provides methods to establish connections.

```java
// DriverManager responsibilities
DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
Connection conn = DriverManager.getConnection(url, username, password);
```
**Memory Trick:** "DriverManager = Driver registration + Connection management"

5. How do you close JDBC resources properly to avoid leaks? _(Cognizant)_

**Answer:** Always close resources in reverse order of creation using try-with-resources.

```java
try (Connection conn = DriverManager.getConnection(url, username, password);
     PreparedStatement pstmt = conn.prepareStatement(sql);
     ResultSet rs = pstmt.executeQuery()) {
    // Use resources
} catch (SQLException e) {
    // Handle exception
}
```
**Memory Trick:** "Try-with-resources = Automatic cleanup + Reverse order closing"

### Basic Query Questions
1. Write a Java method using JDBC to execute a SELECT query on the `employees` table. _(TCS)_

**Answer:**
```java
public List<Employee> getAllEmployees() throws SQLException {
    List<Employee> employees = new ArrayList<>();
    String sql = "SELECT * FROM employees";
    
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        
        while (rs.next()) {
            Employee emp = new Employee();
            emp.setId(rs.getInt("employee_id"));
            emp.setName(rs.getString("name"));
            emp.setSalary(rs.getDouble("salary"));
            employees.add(emp);
        }
    }
    return employees;
}
```
**Memory Trick:** "Connection + PreparedStatement + ResultSet + Object mapping = JDBC SELECT"

2. Write a Java method to insert a record into the `products` table using Statement. _(Infosys)_

**Answer:**
```java
public void insertProduct(String name, double price, String category) throws SQLException {
    String sql = "INSERT INTO products (name, price, category) VALUES ('" + name + "', " + price + ", '" + category + "')";
    
    try (Connection conn = getConnection();
         Statement stmt = conn.createStatement()) {
        stmt.executeUpdate(sql);
    }
}
```
**Memory Trick:** "Statement + executeUpdate + SQL string = Basic INSERT (not recommended for production)"

3. Write a Java method to update a record in the `orders` table using JDBC. _(Capgemini)_

**Answer:**
```java
public void updateOrderStatus(int orderId, String status) throws SQLException {
    String sql = "UPDATE orders SET status = ? WHERE order_id = ?";
    
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, status);
        pstmt.setInt(2, orderId);
        pstmt.executeUpdate();
    }
}
```
**Memory Trick:** "PreparedStatement + set methods + executeUpdate = Safe UPDATE"

4. Write a Java method to delete records from the `customers` table using JDBC. _(Wipro)_

**Answer:**
```java
public void deleteCustomer(int customerId) throws SQLException {
    String sql = "DELETE FROM customers WHERE customer_id = ?";
    
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, customerId);
        int rowsAffected = pstmt.executeUpdate();
        System.out.println("Deleted " + rowsAffected + " rows");
    }
}
```
**Memory Trick:** "PreparedStatement + executeUpdate + Row count = Safe DELETE"

5. Write a Java method to retrieve all records from the `departments` table using ResultSet. _(Accenture)_

**Answer:**
```java
public List<Department> getAllDepartments() throws SQLException {
    List<Department> departments = new ArrayList<>();
    String sql = "SELECT department_id, department_name, location FROM departments";
    
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        
        while (rs.next()) {
            Department dept = new Department();
            dept.setId(rs.getInt("department_id"));
            dept.setName(rs.getString("department_name"));
            dept.setLocation(rs.getString("location"));
            departments.add(dept);
        }
    }
    return departments;
}
```
**Memory Trick:** "ResultSet iteration + Object creation + List collection = Data retrieval"

### Intermediate Questions
1. How do you handle database transactions in a Java application? _(Wipro)_

**Answer:**
```java
public void transferMoney(int fromAccount, int toAccount, double amount) throws SQLException {
    Connection conn = null;
    try {
        conn = getConnection();
        conn.setAutoCommit(false); // Start transaction
        
        // Debit from account
        PreparedStatement debitStmt = conn.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE account_id = ?");
        debitStmt.setDouble(1, amount);
        debitStmt.setInt(2, fromAccount);
        debitStmt.executeUpdate();
        
        // Credit to account
        PreparedStatement creditStmt = conn.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE account_id = ?");
        creditStmt.setDouble(1, amount);
        creditStmt.setInt(2, toAccount);
        creditStmt.executeUpdate();
        
        conn.commit(); // Commit transaction
    } catch (SQLException e) {
        if (conn != null) conn.rollback(); // Rollback on error
        throw e;
    } finally {
        if (conn != null) conn.close();
    }
}
```
**Memory Trick:** "setAutoCommit(false) + Multiple operations + commit/rollback = Transaction handling"

2. How do you map query results to Java objects efficiently? _(Accenture)_

**Answer:**
```java
// Using RowMapper pattern
public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee emp = new Employee();
        emp.setId(rs.getInt("employee_id"));
        emp.setName(rs.getString("name"));
        emp.setSalary(rs.getDouble("salary"));
        emp.setDepartment(rs.getString("department"));
        return emp;
    }
}

// Using reflection or BeanUtils
public <T> T mapResultSetToObject(ResultSet rs, Class<T> clazz) throws SQLException {
    T obj = clazz.newInstance();
    ResultSetMetaData metaData = rs.getMetaData();
    for (int i = 1; i <= metaData.getColumnCount(); i++) {
        String columnName = metaData.getColumnName(i);
        Object value = rs.getObject(i);
        // Use reflection to set field value
    }
    return obj;
}
```
**Memory Trick:** "RowMapper pattern + Reflection + Bean mapping = Efficient object mapping"

3. What is the difference between executeQuery, executeUpdate, and execute in JDBC? _(Cognizant)_

**Answer:**
- **executeQuery()**: Returns ResultSet for SELECT queries
- **executeUpdate()**: Returns int (rows affected) for INSERT, UPDATE, DELETE
- **execute()**: Returns boolean, true if ResultSet, false if update count

```java
// executeQuery for SELECT
ResultSet rs = pstmt.executeQuery("SELECT * FROM employees");

// executeUpdate for INSERT/UPDATE/DELETE
int rowsAffected = pstmt.executeUpdate("UPDATE employees SET salary = 50000");

// execute for dynamic SQL
boolean hasResultSet = stmt.execute(sql);
if (hasResultSet) {
    ResultSet rs = stmt.getResultSet();
} else {
    int count = stmt.getUpdateCount();
}
```
**Memory Trick:** "executeQuery = ResultSet, executeUpdate = Row count, execute = Boolean + dynamic"

4. How do you use PreparedStatement to prevent SQL injection? _(Infosys)_

**Answer:**
```java
// Vulnerable to SQL injection
String sql = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";

// Safe with PreparedStatement
String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setString(1, username);
pstmt.setString(2, password);
ResultSet rs = pstmt.executeQuery();
```
**Memory Trick:** "PreparedStatement + Parameter placeholders + set methods = SQL injection prevention"

5. What is the role of Connection.setAutoCommit in JDBC transactions? _(TCS)_

**Answer:** Controls whether each SQL statement is automatically committed.

```java
Connection conn = getConnection();

// Auto-commit mode (default)
conn.setAutoCommit(true); // Each statement committed immediately

// Manual transaction mode
conn.setAutoCommit(false); // Statements grouped into transactions
// ... multiple operations ...
conn.commit(); // or conn.rollback();
```
**Memory Trick:** "setAutoCommit(true) = Auto commit, setAutoCommit(false) = Manual transaction control"

### Intermediate Query Questions
1. Write a Java method using PreparedStatement to insert a record into the `orders` table. _(Wipro)_

**Answer:**
```java
public void insertOrder(int customerId, double totalAmount, String status) throws SQLException {
    String sql = "INSERT INTO orders (customer_id, order_date, total_amount, status) VALUES (?, ?, ?, ?)";
    
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, customerId);
        pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
        pstmt.setDouble(3, totalAmount);
        pstmt.setString(4, status);
        pstmt.executeUpdate();
    }
}
```
**Memory Trick:** "PreparedStatement + set methods + executeUpdate = Safe parameterized INSERT"

2. Write a Java method to execute a batch insert into the `products` table using JDBC. _(Cognizant)_

**Answer:**
```java
public void batchInsertProducts(List<Product> products) throws SQLException {
    String sql = "INSERT INTO products (name, price, category) VALUES (?, ?, ?)";
    
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        conn.setAutoCommit(false);
        for (Product product : products) {
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setString(3, product.getCategory());
            pstmt.addBatch();
        }
        
        int[] results = pstmt.executeBatch();
        conn.commit();
        System.out.println("Inserted " + results.length + " products");
    }
}
```
**Memory Trick:** "addBatch + executeBatch + Transaction = Efficient batch processing"

3. Write a Java method to map a ResultSet to a list of Employee objects. _(Accenture)_

**Answer:**
```java
public List<Employee> mapResultSetToEmployees(ResultSet rs) throws SQLException {
    List<Employee> employees = new ArrayList<>();
    
    while (rs.next()) {
        Employee emp = new Employee();
        emp.setId(rs.getInt("employee_id"));
        emp.setName(rs.getString("name"));
        emp.setEmail(rs.getString("email"));
        emp.setSalary(rs.getDouble("salary"));
        emp.setDepartment(rs.getString("department"));
        emp.setHireDate(rs.getDate("hire_date"));
        employees.add(emp);
    }
    
    return employees;
}
```
**Memory Trick:** "ResultSet iteration + Object creation + List population = Data mapping"

4. Write a Java method to execute a parameterized SELECT query with WHERE clause. _(Infosys)_

**Answer:**
```java
public List<Employee> getEmployeesByDepartment(String department) throws SQLException {
    List<Employee> employees = new ArrayList<>();
    String sql = "SELECT * FROM employees WHERE department = ? AND salary > ?";
    
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, department);
        pstmt.setDouble(2, 50000.0);
        
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId(rs.getInt("employee_id"));
                emp.setName(rs.getString("name"));
                emp.setSalary(rs.getDouble("salary"));
                employees.add(emp);
            }
        }
    }
    return employees;
}
```
**Memory Trick:** "Parameterized query + Multiple conditions + ResultSet mapping = Flexible search"

5. Write a Java method to handle a transaction updating `orders` and `inventory` tables. _(TCS)_

**Answer:**
```java
public void processOrder(int orderId, int productId, int quantity) throws SQLException {
    Connection conn = null;
    try {
        conn = getConnection();
        conn.setAutoCommit(false);
        
        // Update order status
        String updateOrderSql = "UPDATE orders SET status = 'processing' WHERE order_id = ?";
        try (PreparedStatement orderStmt = conn.prepareStatement(updateOrderSql)) {
            orderStmt.setInt(1, orderId);
            orderStmt.executeUpdate();
        }
        
        // Update inventory
        String updateInventorySql = "UPDATE inventory SET quantity = quantity - ? WHERE product_id = ?";
        try (PreparedStatement inventoryStmt = conn.prepareStatement(updateInventorySql)) {
            inventoryStmt.setInt(1, quantity);
            inventoryStmt.setInt(2, productId);
            inventoryStmt.executeUpdate();
        }
        
        conn.commit();
    } catch (SQLException e) {
        if (conn != null) conn.rollback();
        throw e;
    } finally {
        if (conn != null) conn.close();
    }
}
```
**Memory Trick:** "Transaction + Multiple updates + commit/rollback = Atomic operations"

### Advanced Questions
1. How do you implement connection pooling in a Java application? _(Cognizant)_

**Answer:**
```java
// Using HikariCP (recommended)
HikariConfig config = new HikariConfig();
config.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
config.setUsername("user");
config.setPassword("password");
config.setMaximumPoolSize(20);
config.setMinimumIdle(5);
config.setConnectionTimeout(30000);

HikariDataSource dataSource = new HikariDataSource(config);

// Using the pooled connection
try (Connection conn = dataSource.getConnection()) {
    // Use connection
}
```
**Memory Trick:** "Connection pool = Reuse connections + Performance + Resource management"

2. What is the difference between JDBC and ORM frameworks like Hibernate? _(Deloitte)_

**Answer:**
- **JDBC**: Low-level, manual SQL, direct database control, more boilerplate
- **Hibernate**: High-level, object-relational mapping, automatic SQL generation, less code

```java
// JDBC approach
String sql = "SELECT * FROM employees WHERE department = ?";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setString(1, "IT");
ResultSet rs = pstmt.executeQuery();
// Manual mapping...

// Hibernate approach
List<Employee> employees = session.createQuery("FROM Employee WHERE department = :dept")
    .setParameter("dept", "IT")
    .list();
```
**Memory Trick:** "JDBC = Manual control + SQL, Hibernate = Automatic + Object mapping"

3. How do you handle large ResultSets efficiently in Java? _(Amazon)_

**Answer:**
```java
// Using streaming
public void processLargeResultSet() throws SQLException {
    String sql = "SELECT * FROM large_table";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql, 
             ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
         ResultSet rs = pstmt.executeQuery()) {
        
        pstmt.setFetchSize(1000); // Set fetch size
        
        while (rs.next()) {
            // Process row by row without loading all into memory
            processRow(rs);
        }
    }
}
```
**Memory Trick:** "Streaming + Fetch size + Row-by-row processing = Memory efficient"

4. How do you optimize JDBC performance for high-throughput applications? _(Infosys)_

**Answer:**
- **Connection pooling**: Reuse connections
- **PreparedStatement caching**: Reuse prepared statements
- **Batch operations**: Group multiple operations
- **Fetch size optimization**: Control memory usage
- **Index optimization**: Ensure proper database indexes
- **Connection timeout management**: Prevent hanging connections

**Memory Trick:** "Pooling + Caching + Batching + Fetch size + Indexes + Timeouts = Performance optimization"

5. What are the benefits of using DataSource over DriverManager in JDBC? _(TCS Digital)_

**Answer:**
- **Connection pooling**: Built-in connection management
- **Better performance**: Reuse connections
- **Configuration flexibility**: Easy to configure pool settings
- **Monitoring capabilities**: Track connection usage
- **Failover support**: Handle connection failures
- **JNDI integration**: Lookup from application server

**Memory Trick:** "DataSource = Pooling + Performance + Configuration + Monitoring + Failover"

### Advanced Query Questions
1. Write a Java method to execute a batch update of multiple records using JDBC. _(Cognizant)_

**Answer:**
```java
public void batchUpdateEmployeeSalaries(Map<Integer, Double> salaryUpdates) throws SQLException {
    String sql = "UPDATE employees SET salary = ? WHERE employee_id = ?";
    
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        conn.setAutoCommit(false);
        for (Map.Entry<Integer, Double> entry : salaryUpdates.entrySet()) {
            pstmt.setDouble(1, entry.getValue());
            pstmt.setInt(2, entry.getKey());
            pstmt.addBatch();
        }
        
        int[] results = pstmt.executeBatch();
        conn.commit();
        System.out.println("Updated " + results.length + " employees");
    }
}
```
**Memory Trick:** "Batch update = Map iteration + addBatch + executeBatch + Transaction"

2. Write a Java method to execute a complex query with joins and map results to a custom object. _(Amazon)_

**Answer:**
```java
public List<OrderDetail> getOrderDetailsWithCustomerInfo() throws SQLException {
    List<OrderDetail> orderDetails = new ArrayList<>();
    String sql = "SELECT o.order_id, o.order_date, c.customer_name, p.product_name, od.quantity " +
                 "FROM orders o " +
                 "JOIN customers c ON o.customer_id = c.customer_id " +
                 "JOIN order_details od ON o.order_id = od.order_id " +
                 "JOIN products p ON od.product_id = p.product_id";
    
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        
        while (rs.next()) {
            OrderDetail detail = new OrderDetail();
            detail.setOrderId(rs.getInt("order_id"));
            detail.setOrderDate(rs.getDate("order_date"));
            detail.setCustomerName(rs.getString("customer_name"));
            detail.setProductName(rs.getString("product_name"));
            detail.setQuantity(rs.getInt("quantity"));
            orderDetails.add(detail);
        }
    }
    return orderDetails;
}
```
**Memory Trick:** "Complex query + Multiple joins + Custom object mapping = Rich data retrieval"

3. Write a Java method to handle pagination for a large dataset using JDBC. _(Deloitte)_

**Answer:**
```java
public List<Employee> getEmployeesWithPagination(int page, int pageSize) throws SQLException {
    List<Employee> employees = new ArrayList<>();
    int offset = (page - 1) * pageSize;
    
    String sql = "SELECT * FROM employees ORDER BY employee_id LIMIT ? OFFSET ?";
    
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, pageSize);
        pstmt.setInt(2, offset);
        
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId(rs.getInt("employee_id"));
                emp.setName(rs.getString("name"));
                emp.setSalary(rs.getDouble("salary"));
                employees.add(emp);
            }
        }
    }
    return employees;
}
```
**Memory Trick:** "Pagination = LIMIT + OFFSET + Page calculation + ResultSet mapping"

4. Write a Java method to execute a stored procedure with input and output parameters. _(HCL)_

**Answer:**
```java
public double calculateEmployeeBonus(int employeeId, int year) throws SQLException {
    String sql = "{call CalculateBonus(?, ?, ?)}";
    
    try (Connection conn = getConnection();
         CallableStatement cstmt = conn.prepareCall(sql)) {
        
        cstmt.setInt(1, employeeId);  // Input parameter
        cstmt.setInt(2, year);        // Input parameter
        cstmt.registerOutParameter(3, Types.DOUBLE);  // Output parameter
        
        cstmt.execute();
        
        return cstmt.getDouble(3);  // Get output parameter value
    }
}
```
**Memory Trick:** "CallableStatement + Input parameters + registerOutParameter + execute = Stored procedure"

5. Write a Java method to stream a large ResultSet to avoid memory issues. _(Accenture)_

**Answer:**
```java
public void processLargeResultSetStreaming() throws SQLException {
    String sql = "SELECT * FROM large_table";
    
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql, 
             ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
         ResultSet rs = pstmt.executeQuery()) {
        
        pstmt.setFetchSize(1000);  // Set fetch size for streaming
        
        while (rs.next()) {
            // Process each row individually without loading all into memory
            String data = rs.getString("column_name");
            processRowData(data);
        }
    }
}

private void processRowData(String data) {
    // Process individual row data
    System.out.println("Processing: " + data);
}
```
**Memory Trick:** "Streaming = Forward-only cursor + Fetch size + Row-by-row processing = Memory efficient"

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
