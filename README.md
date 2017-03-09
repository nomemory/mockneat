# mockneat

**MockNeat** is a Java 8+ library that facilitates the generation of arbitrary data for your applications. 

For the project documentation please visit the **[wiki](https://github.com/nomemory/mockneat/wiki)**.

The good:
- The first stable "alpha" release is going to come soon;
- The library unit test coverage is 88% for classes and 81% lines of the code.

Still a lot of work:
- There is no Maven Central / JCenter repository yet. The only way you can test the library is by downloading it from github and build it using gradle;

The bad:
- The library makes heavy use of Java 8 features (Streams, Functional Interfaces, etc.), thus it only supports Java 8+ with no plans for supporting previous Java versions;

Plans for the future:
- Document the API;
- Add new random generators (IBAN, SSC, Lorem Ipsum, etc.);
- Add functionality to help the developer generate SQL Inserts directly;
- Improve the MockUnit<T> API;
- Drastically improve code quality;

# Examples

```java
// Create a instance of the mocking object
MockNeat m = MockNeat.threadLocal();
```

Afterwards we can play the ``m`` objects in ways "unimaginable":

## Example 1 - Money

> "Generating money has never been easier." (Guy Rich)

We can chose the type of the Credit Card Number we want:

```java
// Generates a single valid credit card number (AMEX)
String amex = m.creditCards()
                .type(AMERICAN_EXPRESS)
                .val();
```

```java
// Generates a single valid credit card number (MASTERCARD)
String mastercard = m.creditCards()
                     .type(MASTERCARD)
                     .val();
```

We can randomize the types of the Credit Cards we are going to use for mocking the data:

```java
// Generates a single Mastercard or AMEX credit card number
String amexOrMastercard = m.creditCards()
                           .types(AMERICAN_EXPRESS, MASTERCARD)
                           .val();
```

Instead of calling `val()` directly to get a single value, we can return a Lists, Sets and basically any collection. Streams are also an option.

```java
// Generates a a list of credit card numbers
List<String> mastercards = m.creditCards()
			    .type(MASTERCARD)
			    .list(LinkedList.class, 10)
			    .val();
```

```java
// Generates an infinite stream of strings, each String
// is a valic credit card number
Stream<String> creditCards = m.creditCards()
			      .stream()
			      .val();
```

## Example 2 - Complex Use

We can generate advanced structures of data. For example we can generate a:

`List<Map<String, Map<List<String>, Set<Integer>>>>`

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

## Example 3 - Literature :)

We can generate random (Markov) text from the opera of the famous writer Franz Kafka (just for fun, of course):

```java
String literature = m.markovs().size(1024).type(KAFKA).val();
```

The results are not spectacular, but they can pass as a better form of giberish.

Possible output:
```
Only when the clock struck again, seven o'clock already, he shouted as he swang himself with anger, into the room on the beds and slipped out again and even felt much hungrier than usual. (...)
```

## Example 4 - Mocking Real-World Objects

Mocking objects with arbitrary data is easy:

> Generate a List<Employee> of 1000 random employees of a fictional company "company.com":

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
