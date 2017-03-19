# MockNeat

**MockNeat** is a Java 8+ library that facilitates the generation of arbitrary data for your applications. 

## Installing

Please check the [releases](https://github.com/nomemory/mockneat/releases) page. 

The library is available in [jcenter](https://bintray.com/nomemory/maven/mockneat):

gradle:
```
compile 'net.andreinc.mockneat:mockneat:0.0.1'
```

maven:
```
<dependency>
  <groupId>net.andreinc.mockneat</groupId>
  <artifactId>mockneat</artifactId>
  <version>0.0.1</version>
  <type>pom</type>
</dependency>
```

## Documentation 

For extensive documentation please visit the **[wiki](https://github.com/nomemory/mockneat/wiki)**. 

Quick links: 

- [Creating MockNeat Objects](https://github.com/nomemory/mockneat/wiki/Creating--a-MockNeat-object)
- [The MockNeat Class](https://github.com/nomemory/mockneat/wiki/MockNeat);
- [The MockUnit Interface](https://github.com/nomemory/mockneat/wiki/MockUnits)

## Contributions

If you want to contribute please take a look at the [Project Page](https://github.com/nomemory/mockneat/projects/1).

## Examples

### 1. Mocking Real-World Objects

Example for Generating a List<Employee> of 1000 arbitrary employees that work for a fictional company called "Company.com":

```java
// Creates a MockNeat object that internally uses
// a ThreadLocalRandom.
MockNeat m = MockNeat.threadLocal();

List<Employee> companyEmployees =
                m.reflect(Employee.class)
                 .field("uniqueId",
                        m.uuids())
                 .field("id",
                        m.longSeq())
                 .field("fullName",
                        m.names().full())
                 .field("companyEmail",
                        m.emails().domain("company.com"))
                 .field("personalEmail",
                        m.emails())
                 .field("salaryCreditCard",
                        m.creditCards().types(AMERICAN_EXPRESS, MASTERCARD))
                 .field("external",
                        m.bools().probability(20.0))
                 .field("hireDate",
                        m.localDates().past(of(1999, 1, 1)))
                 .field("birthDate",
                        m.localDates().between(of(1950, 1, 1), of(1994, 1, 1)))
                 .field("pcs",
                        m.reflect(EmployeePC.class)
                         .field("uuid",
                                m.uuids())
                         .field("username",
                                m.users())
                         .field("operatingSystem",
                                m.from(new String[]{"Linux", "Windows 10", "Windows 8"}))
                         .field("ipAddress",
                                m.ipv4s().type(CLASS_B))
                         .field("macAddress",
                                m.macs())
                         .list(2))
                 .list(1000)
                 .val();
```

### 2. Writing CSV files

Example for creating a CSV file with arbitrary data that has the following structure:

`id, firstName, lastName, email, salary (euro)`

The file should contain 1000 lines.

```java
MockNeat m = MockNeat.threadLocal();
final Path path = Paths.get("./test.csv");

m.fmt("#{id},#{first},#{last},#{email},#{salary}")
 .param("id", m.longSeq())
 .param("first", m.names().first())
 .param("last", m.names().last())
 .param("email", m.emails())
 .param("salary", m.money().locale(GERMANY).range(2000, 5000))
 .list(1000)
 .consume(list -> {
            try { Files.write(path, list, CREATE, WRITE); }
            catch (IOException e) { e.printStackTrace(); }
 });
```

### 3. Probabilities

Example for generating numbers in intervals based on probabilities: 
- Generating a number in interval [0, 100) - 20% chance;
- Generating a number in interval [100, 200) - 50% chance;
- Generating a number in interval [200, 300) - 30% chance;

```java
Integer x = m.probabilites(Integer.class)
             .add(0.2, m.ints().range(0, 100))
             .add(0.5, m.ints().range(100, 200))
             .add(0.3, m.ints().range(200, 300))
             .val();
```

