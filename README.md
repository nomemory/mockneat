# mockneat

**MockNeat** is a Java 8+ library that facilitates the generation of random data for your applications. 

The good:
- The first stable "alpha" release is going to come soon;
- There is a small amount of documentation that can be found [here](https://github.com/nomemory/mockneat/wiki). Documenting the library is going to become one of the priorities;

Still a lot of work:
- The test coverage is far from being complete. At this moment there are around 380 Unit Tests that cover most of the functionality, but a lot of "corner-cases" are still not tested;
- There is no Maven Central / JCenter repository yet. The only way you can test the library is by downloading it from github and build it using gradle;

The bad:
- The library makes heavy use of Java 8 features (Streams, Functional Interfaces, etc.), thus it only supports Java 8+ with no plans for supporting previous Java versions;

Plans for the future:
- Document the API;
- Add new random generators (IBAN, SSC, Lorem Ipsum, etc.);
- Add functionality to help the developer generate SQL Inserts directly;
- Improve the MockUnit<T> API;
- Drastically improve code quality;

## Sneak-Peek

```java
// Create a instance of the mocking object
MockNeat m = MockNeat.threadLocal();
```

Afterwards we can play the ``m`` objects in ways "unimaginable":

### Sneak-Peek 1 - Money
We can generate a list of 10 Credit Card numbers and limit the type of the cards to AMEX and MasterCard.
```java
List<String> creditCards =
                m.ccs()
                 .types(AMERICAN_EXPRESS, MASTERCARD)
                 .list(10)
                 .val();
```
### Sneak-Peak 2 - Complex Use

We can generate advanced structures of data. For example we can generate a:
``List<Map<String, Map<List<String>, Set<Integer>>>>``:sweat: 
writing only a few Lines of code:

```java
List<Map<String, Map<List<String>, Set<Integer>>>> complexStructure
                     = m.ints().bound(10)
                        // Create a Set<Integer>
                        .set(10)
                        // Create a Map<List<String>, Set<Integer>>
                        .mapKeys(20, m.strings().size(3).list(10)::val)
                        // Creates a Map<String, Map<List<String>, Set<Integer>>>
                        .mapKeys(10, m.strings().size(5).type(HEX)::val) //
                        // Creates a List<Map<String, Map<List<String>, Set<Integer>>>>
                        .list(ArrayList.class, 100)
                        .val();
```                        

### Sneak-Peak 3 - Literature :)

We can generate random (Markov) text from the opera of the famous writer Franz Kafka (just for fun, of course):

```java
String literature = m.markovs().size(1024).type(KAFKA).val();
```

The results are not spectacular, but they can pass as a better form of giberish.
```
Only when the clock struck again, seven o'clock already, he shouted as he swang himself with anger, into the room on the beds and slipped out again and even felt much hungrier than usual. (...)
```
### Sneak-Peak 4 - Mocking Real-World Objects

Mocking objects with "realistic" data is easy:

> Generate a List<Employee> of 1000 random employees of a fictional company "company.com":

```java
// Creates a MockNeat object that internally uses
// a ThreadLocalRandom.
MockNeat m = MockNeat.threadLocal();

List<Employee> companyEmployees =
                m.objs(Employee.class)
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
                        m.objs(EmployeePC.class)
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
Employee id: 2
	 uuid: 3429a900-1e31-4cfe-bae3-83637a7bcb41
	 fullName: Ellyn Chisum
	 companyEmail: stagedlatoyia@company.com
	 personalEmail: scantspeans@msn.com
	 salaryCreditCard: 348088614035992
	 external: false
	 hireDate: 2007-12-24
	 birthDate: 1991-10-25
	 pcs: 2
		 uuid: 5e7198fd-93c7-4817-819d-6e7ea910c4ce
		 username: blestmauricio
		 operatingSystem: Linux
		 ipAddress: 171.234.134.207
		 macAddress: 23:7a:5b:44:99:8a
		-
		 uuid: 77c6532d-82e3-4293-9e1a-c40651c6a7e2
		 username: tradsunny
		 operatingSystem: Windows 10
		 ipAddress: 159.152.113.70
		 macAddress: 48:3b:10:e8:31:7b
		-
.........
------------------------------
Employee id: 999
	 uuid: 4bc3a718-37b8-4dd6-bcf5-33acac32f3db
	 fullName: Fredrick Apuzzo
	 companyEmail: spryemil@company.com
	 personalEmail: feattherese@gmail.com
	 salaryCreditCard: 376770913061234
	 external: false
	 hireDate: 2003-01-03
	 birthDate: 1967-04-07
	 pcs: 2
		 uuid: 21653ab6-c183-41f9-89c6-914d82f8742d
		 username: shoalvince
		 operatingSystem: Linux
		 ipAddress: 178.195.251.36
		 macAddress: 38:86:f8:89:a0:ee
		-
		 uuid: 03e0a722-cd4a-44e6-aeed-bcaa718a0625
		 username: jadehennagir
		 operatingSystem: Linux
		 ipAddress: 149.247.39.157
		 macAddress: 27:8d:63:da:3f:3b
		-
```
