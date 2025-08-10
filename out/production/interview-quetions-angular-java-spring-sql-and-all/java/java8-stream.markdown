# Java 8 Stream API Interview Questions – MNC (Senior Developer)

## 🔹 Basic Level

### 1. What is Stream API in Java 8?

**Answer**:\
The Stream API in Java 8 provides a declarative, functional approach to process collections of objects. It enables operations like filtering, mapping, and reducing in a pipeline without mutating the source.

> Asked in: Infosys, TCS

### 2. How is Stream different from Collection in Java?

**Answer**:

- A **Collection** is a data structure that holds data in memory.
- A **Stream** is a pipeline for processing data.
- Collections are **eagerly** constructed and evaluated; Streams are **lazily** evaluated.
- Collections support iteration; Streams support functional-style operations.

> Asked in: Capgemini, Cognizant

### 3. How do you create a Stream in Java?

**Answer**:\
Streams can be created in several ways:

- From collections: `list.stream()`
- From arrays: `Arrays.stream(arr)`
- Using static methods: `Stream.of(1, 2, 3)`
- Infinite streams: `Stream.iterate(seed, fn)`

> Asked in: Wipro, Accenture

### 4. What are intermediate and terminal operations?

**Answer**:

- **Intermediate operations** return a new Stream (e.g., `filter()`, `map()`, `sorted()`).
- **Terminal operations** trigger the processing and return a result (e.g., `collect()`, `forEach()`, `count()`).

> Asked in: IBM, Infosys

### 5. Can a stream be reused? Why or why not?

**Answer**:\
No, streams cannot be reused. Once a terminal operation is called, the stream is closed. Trying to use it again will throw `IllegalStateException`. You need to create a new stream to reprocess.

> Asked in: L&T, Mindtree

## 🔸 Intermediate Level

### 6. Difference between `map()` and `flatMap()` with examples?

**Companies**: HCL, EPAM, TCS Digital

**Answer**:

- `map()`: Transforms each element in a stream to another object, producing a one-to-one (1:1) mapping. Each input element results in exactly one output element.
- `flatMap()`: Flattens nested structures (e.g., a stream of collections) into a single stream, producing a one-to-many (1:N) mapping. It is used to handle nested streams or collections.

**Example**:

```java
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String[] args) {
        // map() example
        List<String> words = List.of("Java", "Stream");
        List<Integer> lengths = words.stream()
                                    .map(String::length) // Maps each string to its length
                                    .collect(Collectors.toList());
        System.out.println(lengths); // Output: [4, 6]

        // flatMap() example
        List<List<Integer>> numbers = List.of(List.of(1, 2), List.of(3, 4));
        List<Integer> flat = numbers.stream()
                                   .flatMap(List::stream) // Flattens nested lists into a single stream
                                   .collect(Collectors.toList());
        System.out.println(flat); // Output: [1, 2, 3, 4]
    }
}
```

### 7. What is lazy evaluation in streams?

**Companies**: Oracle, Wipro

**Answer**:\
Lazy evaluation in streams means that intermediate operations (e.g., `map()`, `filter()`) are not executed until a terminal operation (e.g., `collect()`, `forEach()`) is invoked. This improves performance by avoiding unnecessary computations and allows optimization of the stream pipeline.

**Example**:

```java
import java.util.List;

public class LazyEvaluation {
    public static void main(String[] args) {
        List<String> words = List.of("Java", "Stream", "API");
        words.stream()
             .filter(s -> {
                 System.out.println("Filtering: " + s);
                 return s.length() > 3;
             })
             .map(String::toUpperCase); // No output until terminal operation

        // Adding a terminal operation
        words.stream()
             .filter(s -> {
                 System.out.println("Filtering: " + s);
                 return s.length() > 3;
             })
             .map(String::toUpperCase)
             .forEach(System.out::println); // Output: Filtering, then "JAVA", "STREAM"
    }
}
```

### 8. How do you collect results from a stream? (`collect()`, `Collectors`)

**Companies**: Capgemini, Infosys

**Answer**:\
The `collect()` method is a terminal operation that gathers the elements of a stream into a collection or other data structure using a `Collector`. The `Collectors` class provides utility methods to create common collectors, such as `toList()`, `toSet()`, `toMap()`, or `joining()`.

**Example**:

```java
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectExample {
    public static void main(String[] args) {
        List<String> words = List.of("Java", "Stream", "API");
        // Collect to List
        List<String> filtered = words.stream()
                                    .filter(s -> s.length() > 3)
                                    .collect(Collectors.toList());
        System.out.println(filtered); // Output: [Java, Stream]

        // Collect to Map
        Map<String, Integer> wordLengthMap = words.stream()
                                                 .collect(Collectors.toMap(
                                                     s -> s,
                                                     String::length
                                                 ));
        System.out.println(wordLengthMap); // Output: {Java=4, Stream=6, API=3}
    }
}
```

### 9. What is the use of `filter()` in stream API?

**Companies**: IBM, L&T

**Answer**:\
The `filter()` method is an intermediate operation that selects elements from a stream based on a predicate (a condition that returns `true` or `false`). It retains only the elements that satisfy the condition.

**Example**:

```java
import java.util.List;
import java.util.stream.Collectors;

public class FilterExample {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        List<Integer> evenNumbers = numbers.stream()
                                          .filter(n -> n % 2 == 0)
                                          .collect(Collectors.toList());
        System.out.println(evenNumbers); // Output: [2, 4]
    }
}
```

### 10. Difference between `Stream.of()` and `Arrays.stream()`?

**Companies**: ThoughtWorks, Cognizant

**Answer**:

- `Stream.of()`: Creates a stream from a variable number of arguments or a single array. It is used for creating streams from individual elements or a single array object.
- `Arrays.stream()`: Creates a stream from an array, typically used when you have an array and want to process its elements as a stream.

**Example**:

```java
import java.util.Arrays;
import java.util.stream.Stream;

public class StreamCreation {
    public static void main(String[] args) {
        // Stream.of()
        Stream<String> stream1 = Stream.of("Java", "Stream", "API");
        stream1.forEach(System.out::println); // Output: Java, Stream, API

        // Arrays.stream()
        String[] array = {"Java", "Stream", "API"};
        Stream<String> stream2 = Arrays.stream(array);
        stream2.forEach(System.out::println); // Output: Java, Stream, API
    }
}
```

### 11. Can you modify the original list using streams?

**Companies**: Infosys, Deloitte

**Answer**:\
Streams do not modify the original list because they are immutable by design and create a new stream for processing. However, you can modify the original list using `forEach()` or by collecting the stream results and reassigning them to the original list reference.

**Example**:

```java
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModifyList {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3));
        // Cannot modify original list directly
        numbers.stream().map(n -> n * 2).forEach(System.out::println); // Output: 2, 4, 6
        System.out.println(numbers); // Original: [1, 2, 3]

        // Modify by reassigning
        numbers = numbers.stream().map(n -> n * 2).collect(Collectors.toList());
        System.out.println(numbers); // Modified: [2, 4, 6]
    }
}
```

### 12. What are stateful vs stateless operations in Stream API?

**Companies**: Mindtree, TCS

**Answer**:

- **Stateless operations**: Do not depend on the state of other elements in the stream (e.g., `map()`, `filter()`). Each element is processed independently.
- **Stateful operations**: Require knowledge of the entire stream or maintain state (e.g., `sorted()`, `distinct()`). These operations may need to process all elements before producing a result.

**Example**:

```java
import java.util.List;

public class StatefulStateless {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(3, 1, 4, 1, 5);
        // Stateless: map
        numbers.stream().map(n -> n * 2).forEach(System.out::println); // Output: 6, 2, 8, 2, 10

        // Stateful: sorted
        numbers.stream().sorted().forEach(System.out::println); // Output: 1, 1, 3, 4, 5
    }
}
```

### 13. What are short-circuiting operations in streams?

**Companies**: Accenture, Wipro

**Answer**:\
Short-circuiting operations terminate the stream pipeline early once a condition is met, improving efficiency. Examples include `findFirst()`, `findAny()`, `anyMatch()`, `allMatch()`, and `noneMatch()`.

**Example**:

```java
import java.util.List;

public class ShortCircuiting {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        // Short-circuiting with findFirst
        Integer firstEven = numbers.stream()
                                  .filter(n -> {
                                      System.out.println("Checking: " + n);
                                      return n % 2 == 0;
                                  })
                                  .findFirst()
                                  .orElse(null);
        System.out.println(firstEven); // Output: Checking: 1, Checking: 2, 2
    }
}
```

### 14. What happens if you use `null` in a stream pipeline?

**Companies**: Capgemini, HCL

**Answer**:\
Using `null` in a stream pipeline can cause a `NullPointerException` if the stream operations try to access methods or properties of a `null` element. To avoid this, you can filter out `null` values using `filter(Objects::nonNull)`.

**Example**:

```java
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class NullInStream {
    public static void main(String[] args) {
        List<String> words = List.of("Java", null, "Stream");
        // Handle nulls safely
        List<String> nonNullWords = words.stream()
                                        .filter(Objects::nonNull)
                                        .collect(Collectors.toList());
        System.out.println(nonNullWords); // Output: [Java, Stream]

        // Without filter, this will throw NullPointerException
        try {
            words.stream()
                 .map(String::length)
                 .forEach(System.out::println);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException occurred!");
        }
    }
}
```

## 🚀 Advanced Level

### 15. How does `reduce()` work with examples?

**Companies**: Amazon, JPMorgan, Visa

**Answer**:\
The `reduce()` method performs a reduction on the elements of a stream, using an associative accumulation function, and returns an `Optional` or a value if an identity is provided. It combines elements into a single result, such as summing numbers or concatenating strings.

**Example**:

```java
import java.util.List;
import java.util.Optional;

public class ReduceExample {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4);
        // Reduce with identity
        int sum = numbers.stream()
                         .reduce(0, (a, b) -> a + b);
        System.out.println(sum); // Output: 10

        // Reduce without identity (returns Optional)
        Optional<Integer> product = numbers.stream()
                                          .reduce((a, b) -> a * b);
        System.out.println(product.orElse(1)); // Output: 24
    }
}
```

### 16. How does `groupingBy()` work with `Collectors`?

**Companies**: HCL, Paytm

**Answer**:\
The `Collectors.groupingBy()` method groups stream elements based on a classification function, typically returning a `Map` where keys are the classification results and values are lists of elements. It supports multi-level grouping and downstream collectors for further processing.

**Example**:

```java
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingByExample {
    public static void main(String[] args) {
        List<String> words = List.of("Java", "JavaScript", "Python", "Perl");
        // Group by string length
        Map<Integer, List<String>> groupedByLength = words.stream()
                                                         .collect(Collectors.groupingBy(String::length));
        System.out.println(groupedByLength); // Output: {4=[Java, Perl], 6=[Python], 10=[JavaScript]}
    }
}
```

### 17. How to group employees by department and get average salary using streams?

**Companies**: Infosys, Cognizant

**Answer**:\
You can use `Collectors.groupingBy()` to group employees by department and `Collectors.averagingDouble()` to compute the average salary for each group.

**Example**:

```java
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private String department;
    private double salary;

    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
}

public class GroupByAverageSalary {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
            new Employee("Alice", "HR", 50000),
            new Employee("Bob", "IT", 60000),
            new Employee("Charlie", "HR", 55000),
            new Employee("David", "IT", 65000)
        );

        Map<String, Double> avgSalaryByDept = employees.stream()
                                                      .collect(Collectors.groupingBy(
                                                          Employee::getDepartment,
                                                          Collectors.averagingDouble(Employee::getSalary)
                                                      ));
        System.out.println(avgSalaryByDept); // Output: {HR=52500.0, IT=62500.0}
    }
}
```

### 18. How does `parallelStream()` work?

**Companies**: Flipkart, Google

**Answer**:\
`parallelStream()` creates a parallel stream that splits the data into multiple parts and processes them concurrently using multiple threads from the Fork/Join pool. It is designed to leverage multi-core processors for computationally intensive tasks.

**Example**:

```java
import java.util.List;

public class ParallelStreamExample {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        numbers.parallelStream()
               .map(n -> {
                   System.out.println("Processing " + n + " on thread: " + Thread.currentThread().getName());
                   return n * 2;
               })
               .forEach(System.out::println);
        // Output: Processing on different threads, results like 2, 4, 6, 8, 10 (order may vary)
    }
}
```

### 19. When should you use `parallelStream()` vs `stream()`?

**Companies**: Microsoft, Oracle

**Answer**:

- Use `stream()` for sequential processing, small datasets, or operations with low computational cost.
- Use `parallelStream()` for large datasets, computationally expensive operations (e.g., complex transformations), and when the stream operations are stateless and thread-safe.
- Avoid `parallelStream()` for I/O-bound tasks, small datasets, or stateful operations due to overhead and potential side effects.

**Example**:

```java
import java.util.List;

public class StreamVsParallel {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        // Sequential stream
        numbers.stream().map(n -> n * 2).forEach(System.out::println); // Output: 2, 4, 6, 8, 10

        // Parallel stream
        numbers.parallelStream().map(n -> n * 2).forEach(System.out::println); // Output: Unordered, e.g., 4, 2, 10, 6, 8
    }
}
```

### 20. What are the side effects of using `parallelStream()`?

**Companies**: ThoughtWorks, Accenture

**Answer**:

- **Unpredictable order**: Results may not maintain the original order unless explicitly ordered (e.g., using `forEachOrdered()`).
- **Thread safety issues**: Using non-thread-safe operations or shared mutable state can lead to race conditions.
- **Performance overhead**: Splitting tasks and merging results can introduce overhead, making `parallelStream()` slower for small datasets.
- **Non-deterministic results**: Stateful operations (e.g., `distinct()`) may produce inconsistent results in parallel execution.

**Example**:

```java
import java.util.ArrayList;
import java.util.List;

public class ParallelSideEffects {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        List<Integer> sharedList = new ArrayList<>();
        // Unsafe: modifying shared list in parallel
        numbers.parallelStream()
               .forEach(n -> sharedList.add(n * 2));
        System.out.println(sharedList); // Output: Unpredictable, may miss elements or have duplicates
    }
}
```

### 21. How to debug values inside a stream pipeline (using `peek()`)?

**Companies**: Deloitte, Mindtree

**Answer**:\
The `peek()` method is an intermediate operation that allows you to inspect elements in a stream pipeline without modifying them. It is useful for debugging by printing intermediate results.

**Example**:

```java
import java.util.List;
import java.util.stream.Collectors;

public class PeekExample {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        List<Integer> result = numbers.stream()
                                     .filter(n -> n % 2 == 0)
                                     .peek(n -> System.out.println("Filtered: " + n))
                                     .map(n -> n * 2)
                                     .peek(n -> System.out.println("Mapped: " + n))
                                     .collect(Collectors.toList());
        System.out.println(result); // Output: Filtered: 2, Mapped: 4, Filtered: 4, Mapped: 8, [4, 8]
    }
}
```

### 22. How to flatten nested lists using `flatMap()`?

**Companies**: TCS, Infosys

**Answer**:\
The `flatMap()` method flattens nested collections (e.g., `List<List<T>>`) into a single stream of elements, allowing further processing.

**Example**:

```java
import java.util.List;
import java.util.stream.Collectors;

public class FlattenList {
    public static void main(String[] args) {
        List<List<Integer>> nestedList = List.of(List.of(1, 2), List.of(3, 4), List.of(5));
        List<Integer> flatList = nestedList.stream()
                                          .flatMap(List::stream)
                                          .collect(Collectors.toList());
        System.out.println(flatList); // Output: [1, 2, 3, 4, 5]
    }
}
```

### 23. How to remove duplicate elements using streams?

**Companies**: HCL, Cognizant

**Answer**:\
The `distinct()` method is a stateful intermediate operation that removes duplicate elements from a stream based on their `equals()` method.

**Example**:

```java
import java.util.List;
import java.util.stream.Collectors;

public class RemoveDuplicates {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 2, 3, 3, 4);
        List<Integer> uniqueNumbers = numbers.stream()
                                            .distinct()
                                            .collect(Collectors.toList());
        System.out.println(uniqueNumbers); // Output: [1, 2, 3, 4]
    }
}
```

### 24. How to count frequency of elements using streams?

**Companies**: Capgemini, Wipro

**Answer**:\
You can use `Collectors.groupingBy()` with `Collectors.counting()` to count the frequency of elements in a stream, resulting in a `Map` where keys are elements and values are their counts.

**Example**:

```java
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FrequencyCount {
    public static void main(String[] args) {
        List<String> words = List.of("Java", "Python", "Java", "C++");
        Map<String, Long> frequencyMap = words.stream()
                                             .collect(Collectors.groupingBy(
                                                 s -> s,
                                                 Collectors.counting()
                                             ));
        System.out.println(frequencyMap); // Output: {Java=2, Python=1, C++=1}
    }
}
```

### 25. Can a stream work on infinite data?

**Companies**: ThoughtWorks, Oracle

**Answer**:\
Yes, streams can work with infinite data using lazy evaluation and short-circuiting operations. Methods like `Stream.generate()` or `Stream.iterate()` create infinite streams, and operations like `limit()`, `findFirst()`, or `anyMatch()` ensure only a finite portion is processed.

**Example**:

```java
import java.util.stream.Stream;

public class InfiniteStream {
    public static void main(String[] args) {
        Stream<Integer> infiniteStream = Stream.iterate(1, n -> n + 1);
        infiniteStream.limit(5)
                     .forEach(System.out::println); // Output: 1, 2, 3, 4, 5
    }
}
```

### 26. What is the difference between `findFirst()` and `findAny()`?

**Companies**: Infosys, IBM

**Answer**:

- `findFirst()`: Returns the first element in the stream that matches the given conditions, maintaining encounter order. It is deterministic.
- `findAny()`: Returns any element that matches the conditions, useful in parallel streams for better performance as it doesn’t guarantee order.

**Example**:

```java
import java.util.List;
import java.util.Optional;

public class FindFirstVsFindAny {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        // findFirst
        Optional<Integer> firstEven = numbers.stream()
                                            .filter(n -> n % 2 == 0)
                                            .findFirst();
        System.out.println(firstEven); // Output: Optional[2]

        // findAny (in parallel stream)
        Optional<Integer> anyEven = numbers.parallelStream()
                                          .filter(n -> n % 2 == 0)
                                          .findAny();
        System.out.println(anyEven); // Output: Optional[2] or Optional[4] (non-deterministic)
    }
}
```

## 🧪 Practical Coding Questions

### 27. Find the second highest salary using Stream API

**Companies**: Infosys, HCL

**Problem**: Given a list of employees, find the second highest salary.

**Solution**:

```java
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public double getSalary() { return salary; }
}

public class SecondHighestSalary {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
            new Employee("Alice", 50000),
            new Employee("Bob", 60000),
            new Employee("Charlie", 55000),
            new Employee("David", 65000)
        );

        Optional<Double> secondHighest = employees.stream()
                                                 .map(Employee::getSalary)
                                                 .distinct()
                                                 .sorted(Comparator.reverseOrder())
                                                 .skip(1)
                                                 .findFirst();
        System.out.println(secondHighest.orElse(null)); // Output: 60000.0
    }
}
```

### 28. Get top 3 elements from a list using stream

**Companies**: Wipro, Capgemini

**Problem**: Retrieve the top 3 elements from a list in descending order.

**Solution**:

```java
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TopThreeElements {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(10, 5, 8, 12, 3, 15);
        List<Integer> topThree = numbers.stream()
                                       .sorted(Comparator.reverseOrder())
                                       .limit(3)
                                       .collect(Collectors.toList());
        System.out.println(topThree); // Output: [15, 12, 10]
    }
}
```

### 29. Convert List to Map&lt;String, Integer&gt; (word → length)

**Companies**: TCS, Cognizant

**Problem**: Convert a list of strings to a map where the key is the string and the value is its length.

**Solution**:

```java
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListToMap {
    public static void main(String[] args) {
        List<String> words = List.of("Java", "Python", "C++");
        Map<String, Integer> wordLengthMap = words.stream()
                                                 .collect(Collectors.toMap(
                                                     s -> s,
                                                     String::length
                                                 ));
        System.out.println(wordLengthMap); // Output: {Java=4, Python=6, C++=3}
    }
}
```

### 30. Filter out null/empty strings from a list

**Companies**: IBM, Oracle

**Problem**: Remove null or empty strings from a list using streams.

**Solution**:

```java
import java.util.List;
import java.util.stream.Collectors;

public class FilterNullEmpty {
    public static void main(String[] args) {
        List<String> words = List.of("Java", "", null, "Python", " ");
        List<String> filtered = words.stream()
                                    .filter(s -> s != null && !s.trim().isEmpty())
                                    .collect(Collectors.toList());
        System.out.println(filtered); // Output: [Java, Python]
    }
}
```

### 31. Count number of words in a list using streams

**Companies**: Accenture, Mindtree

**Problem**: Count the total number of non-empty words in a list.

**Solution**:

```java
import java.util.List;

public class CountWords {
    public static void main(String[] args) {
        List<String> words = List.of("Java", "", "Python", "C++", null);
        long wordCount = words.stream()
                             .filter(s -> s != null && !s.isEmpty())
                             .count();
        System.out.println(wordCount); // Output: 3
    }
}
```

### 32. Group transactions by status and sum revenue

**Companies**: Paytm, PhonePe

**Problem**: Given a list of transactions, group them by status and calculate the total revenue for each status.

**Solution**:

```java
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Transaction {
    private String status;
    private double revenue;

    public Transaction(String status, double revenue) {
        this.status = status;
        this.revenue = revenue;
    }

    public String getStatus() { return status; }
    public double getRevenue() { return revenue; }
}

public class GroupByStatusSumRevenue {
    public static void main(String[] args) {
        List<Transaction> transactions = List.of(
            new Transaction("SUCCESS", 100.0),
            new Transaction("FAILED", 50.0),
            new Transaction("SUCCESS", 200.0),
            new Transaction("FAILED", 30.0)
        );

        Map<String, Double> revenueByStatus = transactions.stream()
                                                         .collect(Collectors.groupingBy(
                                                             Transaction::getStatus,
                                                             Collectors.summingDouble(Transaction::getRevenue)
                                                         ));
        System.out.println(revenueByStatus); // Output: {SUCCESS=300.0, FAILED=80.0}
    }
}
```

### 33. Sort a list of employees by salary in descending order using streams

**Companies**: Infosys, Amazon

**Problem**: Sort a list of employees by their salary in descending order.

**Solution**:

```java
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public double getSalary() { return salary; }
    @Override
    public String toString() { return name + ": " + salary; }
}

public class SortEmployeesBySalary {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
            new Employee("Alice", 50000),
            new Employee("Bob", 60000),
            new Employee("Charlie", 55000)
        );

        List<Employee> sortedEmployees = employees.stream()
                                                 .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                                                 .collect(Collectors.toList());
        System.out.println(sortedEmployees); // Output: [Bob: 60000.0, Charlie: 55000.0, Alice: 50000.0]
    }
}
```

### 34. Remove duplicates and sort the list using Stream API

**Companies**: Capgemini, Cognizant

**Problem**: Remove duplicates from a list and sort it in ascending order.

**Solution**:

```java
import java.util.List;
import java.util.stream.Collectors;

public class RemoveDuplicatesAndSort {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(3, 1, 4, 1, 5, 9, 2, 6, 5);
        List<Integer> uniqueSorted = numbers.stream()
                                           .distinct()
                                           .sorted()
                                           .collect(Collectors.toList());
        System.out.println(uniqueSorted); // Output: [1, 2, 3, 4, 5, 6, 9]
    }
}
```

### 35. Find all palindromes from a list of strings using streams

**Companies**: L&T, EPAM

**Problem**: Identify all palindromic strings in a list.

**Solution**:

```java
import java.util.List;
import java.util.stream.Collectors;

public class FindPalindromes {
    public static void main(String[] args) {
        List<String> words = List.of("radar", "java", "level", "python", "deed");
        List<String> palindromes = words.stream()
                                       .filter(s -> s.equalsIgnoreCase(new StringBuilder(s).reverse().toString()))
                                       .collect(Collectors.toList());
        System.out.println(palindromes); // Output: [radar, level, deed]
    }
}
```

### 36. Group list of names by first character

**Companies**: Oracle, TCS

**Problem**: Group a list of names by their first character.

**Solution**:

```java
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupByFirstChar {
    public static void main(String[] args) {
        List<String> names = List.of("Alice", "Bob", "Amy", "Ben", "Charlie");
        Map<Character, List<String>> groupedByFirstChar = names.stream()
                                                              .collect(Collectors.groupingBy(
                                                                  s -> s.charAt(0)
                                                              ));
        System.out.println(groupedByFirstChar); // Output: {A=[Alice, Amy], B=[Bob, Ben], C=[Charlie]}
    }
}
```

## 🧠 Scenario/Design Questions

### 37. How will you use streams in a microservices architecture to transform or filter data?

**Companies**: Infosys, Deloitte

**Answer**:\
In a microservices architecture, streams can be used to process data efficiently, transforming or filtering data from APIs, message queues, or databases. For example, a microservice might receive a stream of JSON payloads from another service via a message broker (e.g., Kafka) or REST API. Streams can filter out invalid data, transform payloads into a desired format, or aggregate data for further processing.

**Example**:\
Suppose a microservice processes user data to filter active users and transform their details into a simplified DTO (Data Transfer Object).

```java
import java.util.List;
import java.util.stream.Collectors;

class User {
    private String name;
    private boolean active;
    private int age;

    public User(String name, boolean active, int age) {
        this.name = name;
        this.active = active;
        this.age = age;
    }

    public boolean isActive() { return active; }
    public String getName() { return name; }
    public int getAge() { return age; }
}

class UserDTO {
    private String name;
    private int age;

    public UserDTO(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() { return "UserDTO{name='" + name + "', age=" + age + "}"; }
}

public class MicroserviceStreamExample {
    public static void main(String[] args) {
        List<User> users = List.of(
            new User("Alice", true, 30),
            new User("Bob", false, 25),
            new User("Charlie", true, 35)
        );

        List<UserDTO> activeUserDTOs = users.stream()
                                           .filter(User::isActive)
                                           .map(user -> new UserDTO(user.getName(), user.getAge()))
                                           .collect(Collectors.toList());
        System.out.println(activeUserDTOs); // Output: [UserDTO{name='Alice', age=30}, UserDTO{name='Charlie', age=35}]
    }
}
```

**Use Case**: Streams can be used in microservices to process real-time data (e.g., filtering valid orders from a Kafka topic) or to batch-process data (e.g., transforming database records for reporting).

### 38. What will happen if you call terminal operation twice on the same stream?

**Companies**: TCS, Oracle

**Answer**:\
A stream in Java can only be consumed once. Calling a terminal operation (e.g., `collect()`, `forEach()`) on a stream consumes it, and attempting to call another terminal operation on the same stream will throw an `IllegalStateException` with a message indicating that the stream has already been operated upon or closed.

**Example**:

```java
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminalOperationTwice {
    public static void main(String[] args) {
        List<String> words = List.of("Java", "Stream");
        Stream<String> stream = words.stream();

        // First terminal operation
        List<String> result1 = stream.collect(Collectors.toList());
        System.out.println(result1); // Output: [Java, Stream]

        // Second terminal operation (will throw IllegalStateException)
        try {
            stream.forEach(System.out::println);
        } catch (IllegalStateException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
```

**Solution**: Create a new stream from the source for each terminal operation.

### 39. How to chain multiple filters and maps in a readable way?

**Companies**: Amazon, Capgemini

**Answer**:\
To chain multiple `filter()` and `map()` operations readably:

- Use line breaks and indentation for each operation.
- Add comments to explain the purpose of each step.
- Break complex pipelines into intermediate collections or methods for clarity.
- Use meaningful variable names or method references.

**Example**:

```java
import java.util.List;
import java.util.stream.Collectors;

public class ReadableStreamPipeline {
    public static void main(String[] args) {
        List<String> words = List.of("Java", "", "Python", "C++", null, "JavaScript");

        List<Integer> processedLengths = words.stream()
                                             // Filter out null or empty strings
                                             .filter(s -> s != null && !s.isEmpty())
                                             // Transform to uppercase
                                             .map(String::toUpperCase)
                                             // Filter strings longer than 3 characters
                                             .filter(s -> s.length() > 3)
                                             // Map to string length
                                             .map(String::length)
                                             // Collect results
                                             .collect(Collectors.toList());

        System.out.println(processedLengths); // Output: [4, 6, 10]
    }
}
```

**Tips**:

- Split complex pipelines into smaller methods for modularity.
- Use `peek()` for debugging intermediate results.
- Ensure the pipeline is logically ordered to minimize processing.

### 40. What design pattern does Stream API follow internally?

**Companies**: ThoughtWorks, Microsoft

**Answer**:\
The Stream API follows the **Builder Pattern** and the **Pipeline Pattern** (a variation of the Chain of Responsibility pattern):

- **Builder Pattern**: Streams use a fluent API where each operation (e.g., `filter()`, `map()`) returns a new `Stream` object, allowing method chaining to build a pipeline.
- **Pipeline Pattern**: Each operation in the stream pipeline processes data and passes it to the next operation, enabling a sequence of transformations.

Additionally, streams leverage the **Iterator Pattern** internally for iterating over elements and the **Functional Programming Paradigm** for operations like `map()` and `reduce()`.

**Example**:\
The fluent chaining in streams (e.g., `stream.filter().map().collect()`) exemplifies the Builder Pattern.

```java
import java.util.List;
import java.util.stream.Collectors;

public class StreamDesignPattern {
    public static void main(String[] args) {
        List<String> words = List.of("Java", "Python");
        List<Integer> lengths = words.stream() // Builder pattern: chaining operations
                                    .filter(s -> s.length() > 3) // Pipeline: filter
                                    .map(String::length) // Pipeline: transform
                                    .collect(Collectors.toList()); // Pipeline: collect
        System.out.println(lengths); // Output: [4, 6]
    }
}
```

### 41. Stream vs For-Each – which is better and when?

**Companies**: Infosys, HCL

**Answer**:

- **Streams**:
    - **Pros**: Declarative, concise, supports functional programming, lazy evaluation, and parallel processing. Ideal for complex data transformations, filtering, and aggregations.
    - **Cons**: Overhead for small datasets, less intuitive for simple iterations, potential thread-safety issues with `parallelStream()`.
    - **Use When**: Processing large datasets, needing parallel processing, or performing complex transformations (e.g., filtering, mapping, grouping).
- **For-Each**:
    - **Pros**: Simple, imperative, better for small datasets or side-effect-heavy operations (e.g., modifying external state).
    - **Cons**: Verbose for complex transformations, no built-in parallelization.
    - **Use When**: Simple iterations, modifying mutable state, or small datasets where readability is key.

**Example**:

```java
import java.util.List;

public class StreamVsForEach {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        // Using for-each (imperative)
        for (Integer n : numbers) {
            if (n % 2 == 0) {
                System.out.println(n * 2);
            }
        } // Output: 4, 8

        // Using stream (declarative)
        numbers.stream()
               .filter(n -> n % 2 == 0)
               .map(n -> n * 2)
               .forEach(System.out::println); // Output: 4, 8
    }
}
```

**Recommendation**: Use streams for complex data processing and functional-style operations; use for-each for simple iterations or when modifying state.

## 📌 Special Topics

### 42. What is the role of `Optional` in stream pipelines?

**Companies**: Capgemini, IBM

**Answer**:\
`Optional` is used in stream pipelines to handle the possibility of absent values, preventing `NullPointerException`. It is commonly returned by operations like `findFirst()`, `findAny()`, `min()`, `max()`, or `reduce()` when the result might be empty. `Optional` encourages explicit null handling using methods like `orElse()`, `orElseGet()`, or `ifPresent()`.

**Example**:

```java
import java.util.List;
import java.util.Optional;

public class OptionalInStreams {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 3, 5);
        Optional<Integer> firstEven = numbers.stream()
                                            .filter(n -> n % 2 == 0)
                                            .findFirst();
        System.out.println(firstEven.orElse(-1)); // Output: -1 (no even numbers)
    }
}
```

**Role**: `Optional` ensures safer, more expressive code by forcing developers to handle the absence of values explicitly.

### 43. How to write a custom collector?

**Companies**: Oracle, Microsoft

**Answer**:\
A custom collector is created by implementing the `Collector` interface, specifying how to accumulate elements, combine partial results, and produce the final result. It requires defining the supplier, accumulator, combiner, finisher, and characteristics.

**Example**: Custom collector to concatenate strings with a prefix and suffix.

```java
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;

public class CustomCollector {
    public static void main(String[] args) {
        List<String> words = List.of("Java", "Python", "C++");

        Collector<String, StringBuilder, String> customCollector = Collector.of(
            StringBuilder::new, // Supplier
            (sb, s) -> sb.append("[").append(s).append("]"), // Accumulator
            (sb1, sb2) -> sb1.append(sb2), // Combiner
            StringBuilder::toString, // Finisher
            Collector.Characteristics.IDENTITY_FINISH
        );

        String result = words.stream()
                            .collect(customCollector);
        System.out.println(result); // Output: [Java][Python][C++]
    }
}
```

**Steps**:

1. **Supplier**: Creates the initial container (`StringBuilder`).
2. **Accumulator**: Adds elements to the container.
3. **Combiner**: Merges partial results (for parallel streams).
4. **Finisher**: Transforms the container into the final result.
5. **Characteristics**: Defines properties like `CONCURRENT`, `UNORDERED`, or `IDENTITY_FINISH`.

### 44. What is backpressure in relation to reactive streams?

**Companies**: Netflix, PayPal

**Answer**:\
Backpressure is a mechanism in reactive streams to control the flow of data between a publisher and a subscriber, preventing the subscriber from being overwhelmed by data it cannot process quickly enough. In Java, reactive streams (via libraries like Reactor or RxJava) implement backpressure by allowing the subscriber to request a specific number of elements from the publisher.

**Explanation**:

- Without backpressure, a fast publisher could flood a slow subscriber, causing memory issues or crashes.
- Backpressure ensures the subscriber signals how much data it can handle, using strategies like buffering, dropping, or requesting data in chunks.

**Example** (Using Reactor, a reactive streams library):

```java
import reactor.core.publisher.Flux;

public class BackpressureExample {
    public static void main(String[] args) {
        Flux.range(1, 100) // Publisher emits numbers 1 to 100
            .onBackpressureBuffer(10) // Buffer up to 10 elements if subscriber is slow
            .subscribe(
                n -> System.out.println("Received: " + n), // Process each element
                System.err::println, // Handle errors
                () -> System.out.println("Done"),
                subscription -> subscription.request(5) // Request only 5 elements at a time
            );
        // Output: Received: 1, 2, 3, 4, 5 (limited by backpressure)
    }
}
```

**Note**: Java’s Stream API is not reactive, but reactive streams libraries integrate with streams for asynchronous processing.

### 45. What are Collector characteristics like `CONCURRENT`, `UNORDERED`, `IDENTITY_FINISH`?

**Companies**: Amazon, ThoughtWorks

**Answer**:\
Collector characteristics optimize the performance and behavior of a `Collector` in a stream pipeline:

- `CONCURRENT`: Indicates the collector can safely accumulate elements concurrently in a shared container, used in parallel streams (e.g., `Collectors.toConcurrentMap()`).
- `UNORDERED`: Indicates the collector does not preserve the encounter order of elements, allowing optimizations (e.g., `Collectors.toSet()`).
- `IDENTITY_FINISH`: Indicates the accumulator’s result is the final result, and no finisher transformation is needed (e.g., `Collectors.toList()`).

**Example**:

```java
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectorCharacteristics {
    public static void main(String[] args) {
        List<String> words = List.of("Java", "Python", "C++");

        // UNORDERED and CONCURRENT: toSet
        Set<String> wordSet = words.parallelStream()
                                   .collect(Collectors.toSet()); // No order guaranteed
        System.out.println(wordSet); // Output: [Java, C++, Python] (order may vary)

        // IDENTITY_FINISH: toList
        List<String> wordList = words.stream()
                                    .collect(Collectors.toList()); // No transformation needed
        System.out.println(wordList); // Output: [Java, Python, C++]
    }
}
```

**Impact**:

- `CONCURRENT`: Improves performance in parallel streams but requires thread-safe accumulators.
- `UNORDERED`: Enables optimizations by ignoring order, useful for sets or maps.

# `IDENTITY_FINISH`: Avoids unnecessary transformations, improving efficiency.