

---

### 1️⃣ **Joins (INNER, LEFT, RIGHT, FULL, SELF, CROSS)**

*(Interviewers test you with join edge cases and performance traps.)*

1. What is the difference between `INNER JOIN` and `LEFT JOIN` in terms of execution plan?
2. When does a `LEFT JOIN` behave like an `INNER JOIN`?
3. How does a `FULL OUTER JOIN` work internally? When would you actually use it?
4. Can you join a table with itself without using aliases?
5. What’s the impact of join order in SQL queries?
6. What’s the difference between a `CROSS JOIN` and `INNER JOIN` with no ON condition?
7. What happens when you join on non-indexed columns in large datasets?
8. What is a **Cartesian product** and when is it dangerous?
9. Can you perform a `JOIN` on inequality conditions (e.g., `a.salary > b.salary`)? What’s the cost?
10. What are **anti-joins** and **semi-joins**, and how are they implemented in SQL?

---

### 2️⃣ **Subqueries & CTEs (WITH clauses)**

*(CTEs and subqueries are goldmines for trick questions)*

1. What’s the difference between a **correlated** and **uncorrelated** subquery?
2. Which is more efficient: a subquery in `WHERE` or an equivalent `JOIN`? Why?
3. Can a CTE be recursive? How does it work?
4. How are CTEs stored and reused in execution plans?
5. How does a subquery in `SELECT` vs `WHERE` behave differently?
6. Can you modify data using a CTE (e.g., `UPDATE` via CTE)?
7. What’s the real difference between using `IN`, `EXISTS`, and `JOIN` in filtering?
8. How does SQL engine optimize subqueries used multiple times?
9. How can subqueries be replaced with window functions?
10. What happens if you reference a CTE multiple times? Is it re-executed?

---

### 3️⃣ **Indexes & Performance Optimization**

*(Must-know for anyone dealing with large datasets)*

1. How does a B-tree index work? What operations is it optimized for?
2. What’s the difference between clustered and non-clustered indexes?
3. Can a table have more than one clustered index?
4. How does SQL decide which index to use in a query?
5. What are **covering indexes**, and how do they reduce I/O?
6. What are **index scan**, **index seek**, and **table scan**? What triggers each?
7. What happens if you index a column with high cardinality vs low cardinality?
8. How do **composite indexes** work and what is column order importance?
9. Why might adding an index slow down an `INSERT` or `UPDATE`?
10. How can you view the execution plan of a query? What does `EXPLAIN` show?

---

### 4️⃣ **Transactions, Isolation & Concurrency**

*(This separates senior-level devs from the rest)*

1. What is a transaction, and how is **ACID** enforced?
2. What is the difference between **read committed** and **repeatable read**?
3. What are **dirty reads**, **phantom reads**, and **non-repeatable reads**?
4. How do **MVCC (multi-version concurrency control)** systems avoid locks?
5. What happens if two transactions update the same row at the same time?
6. What is optimistic vs pessimistic locking?
7. What is the `FOR UPDATE` clause, and what does it do under the hood?
8. Can you rollback a transaction partially? What are savepoints?
9. How do deadlocks happen in SQL? How does the engine detect and resolve them?
10. How does `READ UNCOMMITTED` behave? When is it safe to use?

---

### 5️⃣ **Window Functions & Advanced Aggregates**

*(Heavily asked in data engineer, BI, analytics, and backend interviews)*

1. What is the difference between `RANK()`, `DENSE_RANK()`, and `ROW_NUMBER()`?
2. What does `PARTITION BY` actually do in a window function?
3. How is a window aggregate different from `GROUP BY`?
4. Can window functions use `WHERE` filters? How?
5. What’s the difference between `LAG()` and `LEAD()` and how do they behave at boundaries?
6. What is `FIRST_VALUE()` and how is it different from just using `ORDER BY LIMIT 1`?
7. Can you use window functions inside subqueries or CTEs?
8. What is the performance impact of window functions?
9. How would you calculate a moving average or cumulative sum in SQL?
10. How do `RANGE` and `ROWS` in `OVER()` clause change the output?

---

### 6️⃣ **Data Modeling & Schema Design**

*(For architects and smart full-stack/backend devs)*

1. What is database normalization? What are 1NF, 2NF, and 3NF?
2. When would you denormalize a database on purpose?
3. What is the difference between surrogate and natural keys?
4. Why are UUIDs bad as clustered primary keys?
5. What is the difference between star and snowflake schema?
6. How do foreign key constraints affect performance and inserts?
7. How would you model a many-to-many relationship in SQL?
8. What’s the benefit of using enum-like lookup tables?
9. How do constraints and default values affect insert/update behavior?
10. How does normalization relate to index performance?

---

### 7️⃣ **Tricky SQL Logic & Query Traps**

*(These are smart one-off questions to test how alert and experienced you are)*

1. What’s the result of `NULL = NULL` in SQL? Why?
2. How does `COALESCE()` differ from `IFNULL()` or `ISNULL()`?
3. Why might a query using `NOT IN` return no results unexpectedly?
4. What happens if you do `WHERE column = NULL`? Why doesn’t it work?
5. What’s the result of grouping by a column that contains NULLs?
6. What’s the difference between `HAVING` and `WHERE`?
7. Why does `DISTINCT COUNT(column)` behave differently with nulls?
8. Can you sort a query by a column that is not in SELECT?
9. Why might `SELECT TOP 1 ... ORDER BY RAND()` be a terrible idea?
10. Can you write a SQL query that returns rows with duplicated values only?

---