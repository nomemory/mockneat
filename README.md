# MockNeat

**MockNeat** is a Java 8+ library that facilitates the generation of arbitrary data for your applications. 


## Documentation 

For extensive documentation please visit the **[wiki](https://github.com/nomemory/mockneat/wiki)**. 

Quick links: 

- [The MockNeat Class](https://github.com/nomemory/mockneat/wiki/MockNeat);
- [The MockUnit Interface](https://github.com/nomemory/mockneat/wiki/MockUnits)

## Contributions

If you want to contribute please take a look at the [Project Page](https://github.com/nomemory/mockneat/projects/1).

## Examples

### Mocking Real-World Objects

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

Possible Output:
```
------------------------------
Employee id: 1
	 uuid: 6d7ac78d-5e9c-4e6f-83cd-22d7acfa6239
	 fullName: Jessenia Houdek
	 companyEmail: spiralmise@company.com
	 personalEmail: quickerace@hotmail.co.uk
	 salaryCreditCard: 341198734420730
	 external: false
	 hireDate: 2000-07-26
	 birthDate: 1964-01-18
	 pcs: 2
		 uuid: 7f4366b7-2311-445e-afbc-705aa32f2bf8
		 username: fledgedzlotys
		 operatingSystem: Windows 8
		 ipAddress: 175.153.40.147
		 macAddress: 95:ea:54:a3:92:e3
		-
		 uuid: ee908321-4038-4caa-8eac-e30aa1a6eec0
		 username: wellcleopatra
		 operatingSystem: Windows 8
		 ipAddress: 151.55.102.66
		 macAddress: 00:23:c0:cd:d0:f6
		-
------------------------------
...
// and so on
```


### Writing CSV files

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
