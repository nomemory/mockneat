# MockNeat 

**MockNeat** is a Java 8+ library that facilitates the generation of arbitrary data for your applications.

*Latest Stable Version: 0.2.4*

[![Build Status](https://travis-ci.org/nomemory/mockneat.svg?branch=master)](https://travis-ci.org/nomemory/mockneat.svg?branch=master) [![codecov](https://codecov.io/gh/nomemory/mockneat/branch/master/graph/badge.svg)](https://codecov.io/gh/nomemory/mockneat)

## Installing

The library is available in [jcenter](https://bintray.com/nomemory/maven/mockneat) as a maven or gradle dependency.

For detailed information on how you can include **MockNeat** in your projects please check the **[wiki page](https://github.com/nomemory/mockneat/wiki/Installing)** 

## Contributing

Any type of contributions, suggestions or new ideas are stongly appreciated. Please check this **[wiki page](https://github.com/nomemory/mockneat/wiki/Contributing)** for more information.

## Documentation 

For extensive documentation please visit the **[wiki](https://github.com/nomemory/mockneat/wiki)**. 

Quick links: 

- [Using the library in your applications](https://github.com/nomemory/mockneat/wiki/Installing)
- [Creating MockNeat Objects](https://github.com/nomemory/mockneat/wiki/Creating--a-MockNeat-object)
- [The MockNeat Class](https://github.com/nomemory/mockneat/wiki/MockNeat);
- [The MockUnit Interface](https://github.com/nomemory/mockneat/wiki/MockUnits)

Real world examples:

- [Creating CSV files with arbitrary data](https://github.com/nomemory/mockneat/wiki/Creating-CSV-files-with-arbitrary-data);

- [Programatically populate MySQL Schema with arbitrary test data](https://github.com/nomemory/mockneat/wiki/Programatically-populate-MySQL-Schema-with-arbitrary-test-data).

- [Generate arbitrary JSON strings](https://github.com/nomemory/mockneat/wiki/CreateJsonObject)

DZone Article(s): 
- [Generating and Mocking Data With MockNeat](https://dzone.com/articles/generating-arbitrary-data-using-mockneat)

- [Provisioning SQL Databases With Initial Sets of Data Using Java and MockNeat](https://dzone.com/articles/generating-data-using-mockneat)

## Examples

### A much more powerful random

```java
// Generate an integer value in the interval [0, 10)
int x = mockNeat.ints().bound(10).val();

// Possible Output: 7
```

```java
// Generate an array of int[] with random values in interval [2, 5)
int[] arrRand = mockNeat.ints()
                        .range(0, 5)
                        .arrayPrimitive(10)
                        .val();
                        
// Possible Output:
// [2, 2, 4, 2, 0, 2, 1, 1, 1, 3]
```                        

```java
// Generate a list of random people names and append a random number to resulting string
List<String> namesAndNumbers =
                mockNeat.fmt("#{name} #{number}")
                        .param("name", mockNeat.names().full())
                        .param("number", mockNeat.ints().bound(5).map((num) -> "No. " + num))
                        .list(() -> new ArrayList<>(100), 10)
                        .val();


// Possible Output:
// [Aurore Mings No. 4, Crista Gassel No. 0, Aubrey Leash No. 1, German Klar No. 2, ...an so on...
```

```java
// Create a generator that generates LocalDate(s) before the year 2000
MockUnitLocalDate dateGenerator =
                mockNeat.localDates()
                        .past(LocalDate.of(2000, 1, 1));

// Use the generator to generate a LinkedList with 10 elements containing
// dates before 2000.01.01
List<Date> beforeTheYear2000Dates = dateGenerator
                                            .toUtilDate()
                                            .list(() -> new LinkedList<>(), 10)
                                            .val()                                                                             

// Use the generator to generate a HashSet with a maximum of 
// 100 dates before 2000.01.01
Set<Date> uniqueDatesBeforeTheYear2000 = dateGenerator
                                                    .toUtilDate()
                                                    .set(() -> new HashSet<>(), 100)
                                                    .val();
```

### Mocking Real-World Objects

The library supports several ways of filling/mocking the "model layer" of your application with relevant data. (*Note*: In the following examples `User` is a *custom* bean class from the "model layer").

a) Using some "lambda magic" and referencing the setter methods:

```java
MockNeat m = MockNeat.threadLocal();

User user1 = m.filler(() -> new User()) // Object is created throught the supplier
              .setter(User::setUserName,m.users())
              .setter(User::setFirstName, m.names().first())
              .setter(User::setLastName, m.names().last())
              .setter(User::setCreated, m.localDates().thisYear().toUtilDate())
              .setter(User::setModified, m.localDates().thisMonth().toUtilDate())
              .val(); // When val() is called all the setters are applied in order over the object created with the supplier
              
// Output:
// User{userName='toomjefferey', firstName='Lia', lastName='Noyd', created=Tue May 08 00:00:00 EEST 2018, modified=Fri Mar 23 00:00:00 EET 2018}
```

Creating a `List<User>` is easy. We just collect the results using the `list()` method. Actually we can collect the results in a different structures and collections. Check the `MockUnit<T>` [interface documentation](https://github.com/nomemory/mockneat/wiki/MockUnit) for all the options:

```java
List<User> users = m.filler(() -> new User())
                    .setter(User::setUserName,m.users())
                    .setter(User::setFirstName, m.names().first())
                    .setter(User::setLastName, m.names().last())
                    .setter(User::setCreated, m.localDates().thisYear().toUtilDate())
                    .setter(User::setModified, m.localDates().thisMonth().toUtilDate())
                    .list(() -> new ArrayList<>(), 10) // Collecting all the results ina  List of 10 elements.
                    .val();
```

b) Using reflection at setter level:

```java
User user2 = m.reflect(User.class)
              .field("userName", m.users())
              .field("firstName", m.names().first())
              .field("lastName", m.names().last())
              .field("created", m.localDates().thisYear().toUtilDate())
              .field("modified", m.localDates().thisMonth().toUtilDate())
              .val(); // Nohting happens until you call val()
// Output:
// User{userName='serelovella', firstName='Hassan', lastName='Reich', created=Sat Sep 15 00:00:00 EEST 2018, modified=Tue Mar 20 00:00:00 EET 2018}
```

c) Using reflection at constructor level. The invocation assumes a constructor having the exact set of arguments exists:

```java
User user3 = m.constructor(User.class)
              .params(
                        m.users(),
                        m.names().first(),
                        m.names().last(),
                        m.localDates().thisYear().toUtilDate(),
                        m.localDates().thisMonth().toUtilDate()
                      )
              .val(); // Nothing happens until you call val()
//Output:
// User{userName='meetswipple', firstName='Florine', lastName='Buchmiller', created=Tue May 15 00:00:00 EEST 2018, modified=Wed Mar 14 00:00:00 EET 2018}
```

### Generating SQL Inserts

MockNeat can generate SQL Inserts for your Databases:

```java
MockNeat m = MockNeat.threadLocal();

int empTableRows = 20;
SQLTable empTable = m.sqlInserts()
                          .tableName("emp")
                          .column("id", m.intSeq().increment(10))
                          .column("first_name", m.names().first(), TEXT_BACKSLASH)
                          .column("last_name", m.names().last(), TEXT_BACKSLASH)
                          .column("username", m.users(), TEXT_BACKSLASH)
                          .column("email", m.emails(), TEXT_BACKSLASH)
                          .column("description", m.markovs().size(32).type(LOREM_IPSUM), TEXT_BACKSLASH)
                          .column("created", m.localDates().thisYear().display(BASIC_ISO_DATE), TEXT_BACKSLASH)
                          .table(empTableRows) // Generate a table instead of a single Insert
                          .val();

// After the table is generated we can modify the data in it
empTable.updateAll((i, insert) -> {
            // Update all the descriptions with 'N/A'
            insert.setValue("description", "N/A");
});

System.out.println(empTable);
```

Output:

```
INSERT INTO emp (id, first_name, last_name, username, email, description, created) VALUES (0, 'Odessa', 'Tolmie', 'dashedtommy', 'snidecherry@att.net', 'N/A', '20181213');
INSERT INTO emp (id, first_name, last_name, username, email, description, created) VALUES (10, 'Janette', 'Ellestad', 'oftcontemns', 'thallicreeks@verizon.net', 'N/A', '20181021');
INSERT INTO emp (id, first_name, last_name, username, email, description, created) VALUES (20, 'Brooke', 'Zickuhr', 'murkcelia', 'reprovedsieg@mac.com', 'N/A', '20180710');
INSERT INTO emp (id, first_name, last_name, username, email, description, created) VALUES (30, 'Clarissa', 'Castanio', 'bustagnes', 'punkhal@yahoo.co.uk', 'N/A', '20180420');
INSERT INTO emp (id, first_name, last_name, username, email, description, created) VALUES (40, 'Echo', 'Ruffini', 'roasttrouts', 'naychines@verizon.net', 'N/A', '20180425');
INSERT INTO emp (id, first_name, last_name, username, email, description, created) VALUES (50, 'Margit', 'Pliler', 'faincedi', 'thopicks@verizon.net', 'N/A', '20180319');
INSERT INTO emp (id, first_name, last_name, username, email, description, created) VALUES (60, 'Ingeborg', 'Bushman', 'poshchook', 'yonlavette@att.net', 'N/A', '20180926');
INSERT INTO emp (id, first_name, last_name, username, email, description, created) VALUES (70, 'Kimberli', 'Kayser', 'ebbalexa', 'trinebroil@aol.com', 'N/A', '20181229');
INSERT INTO emp (id, first_name, last_name, username, email, description, created) VALUES (80, 'Minerva', 'Beasley', 'meekdoloris', 'brochdent@mail.com', 'N/A', '20180702');
INSERT INTO emp (id, first_name, last_name, username, email, description, created) VALUES (90, 'Twanna', 'Mattison', 'sveltestsearight', 'fremdtoby@comcast.net', 'N/A', '20180315');
INSERT INTO emp (id, first_name, last_name, username, email, description, created) VALUES (100, 'Birgit', 'Aley', 'mailedhotbeds', 'bakedjamie@verizon.net', 'N/A', '20180103');
INSERT INTO emp (id, first_name, last_name, username, email, description, created) VALUES (110, 'Rozella', 'Algire', 'widebecame', 'toostores@gmx.com', 'N/A', '20180809');
INSERT INTO emp (id, first_name, last_name, username, email, description, created) VALUES (120, 'Becki', 'Kahn', 'vagueclams', 'corkedwhaups@att.net', 'N/A', '20180521');
INSERT INTO emp (id, first_name, last_name, username, email, description, created) VALUES (130, 'Dennise', 'Balck', 'roastsobina', 'sthenicdarsch@yahoo.com', 'N/A', '20180409');
INSERT INTO emp (id, first_name, last_name, username, email, description, created) VALUES (140, 'Karie', 'Adams', 'anesomits', 'scalledcapello@live.com', 'N/A', '20180917');
INSERT INTO emp (id, first_name, last_name, username, email, description, created) VALUES (150, 'Micah', 'Koone', 'tiedplaytimes', 'gravereframe@gmx.com', 'N/A', '20180705');
INSERT INTO emp (id, first_name, last_name, username, email, description, created) VALUES (160, 'Evalyn', 'Bonkowski', 'scabbyfedder', 'cuppedgrader@mail.com', 'N/A', '20180419');
INSERT INTO emp (id, first_name, last_name, username, email, description, created) VALUES (170, 'Meridith', 'Annable', 'trimlizzie', 'gunsmellissa@verizon.net', 'N/A', '20180114');
INSERT INTO emp (id, first_name, last_name, username, email, description, created) VALUES (180, 'Geneva', 'Wiley', 'chancelessdamned', 'tressedshinty@hotmail.co.uk', 'N/A', '20181218');
INSERT INTO emp (id, first_name, last_name, username, email, description, created) VALUES (190, 'Jessika', 'Baun', 'muteclotilde', 'fatlyn@msn.com', 'N/A', '20180320');
```

### Generating CSV files

Example for creating a CSV file with arbitrary data that has the following structure:

`id|firstName|lastName|email|salary (euro)`

The file should contain 1000 lines.

```java
M.csvs().column(M.intSeq())
        .column(M.names().first())
        .column(M.names().last())
        .column(M.emails())
        .column(M.money().locale(Locale.GERMANY).range(1000, 5000))
        .separator(",")
        .write("test.csv", 1000);
```

Possible Output:

```
0|Shaun|Muckerman|plumpveils@hotmail.com|"1.383,13 €"
1|Maria|Theilen|unmeantbier@mail.com|"1.308,64 €"
2|Barry|Friedler|trefkip@msn.com|"4.998,63 €"
3|Sid|Brandon|glegrepaints@mac.com|"1.472,58 €"
4|Leigh|Wormley|thencegangbang@yahoo.com|"3.473,98 €"
5|Refugio|Ripka|starredlincoln@aol.com|"3.200,58 €"
6|Jamaal|Cortinas|glumval@comcast.net|"4.789,84 €"
7|Tobias|Stehly|nutswill@email.com|"1.385,04 €"
8|Randall|Gennarelli|secmaryam@mac.com|"4.460,86 €"
9|Erik|Sweeting|turdineslicker@yahoo.co.uk|"1.882,63 €"
10|Oscar|Crumrine|mannedkarl@att.net|"4.432,08 €"
11|Augustine|Gilchrist|childlesskilt@verizon.net|"3.727,49 €"
12|Al|Philo|bentvalentine@aol.com|"3.798,17 €"
13|Moises|Kalmer|keptearl@mail.com|"2.348,30 €"
14|Adam|Beauparlant|sprymilbrandt@msn.com|"4.832,38 €"
15|Anderson|Folino|somewhilebloats@att.net|"3.418,83 €"
16|Dale|Schei|grimgerry@aol.com|"3.197,65 €"
17|Nathanael|Veazie|toomchanel@aol.com|"2.132,70 €"
18|Cedrick|Tome|starredagings@aol.com|"3.614,68 €"
19|Ariel|Garufi|firmvelia@gmail.com|"2.984,32 €"
....
....
```

### Randomize with Probabilities

Example for generating numbers in intervals based on probabilities: 
- Generating a number in interval `[0, 100)` - 20% chance;
- Generating a number in interval `[100, 200)` - 50% chance;
- Generating a number in interval `[200, 300)` - 30% chance;

```java
Integer x = m.probabilites(Integer.class)
             .add(0.2, m.ints().range(0, 100))
             .add(0.5, m.ints().range(100, 200))
             .add(0.3, m.ints().range(200, 300))
             .val();
```

### Generating (Markov) Text from the writings of Franz Kafka ... or Lorem Ipsum

```java
MockNeat mock = MockNeat.threadLocal();
String text = mock.markovs().size(1024).type(KAFKA).val();
System.out.println(text);
```

Possible Output:

```
From under the couch and put her finger on her forehead. Her words seemed to regret her behaviour, as she approached the room, despite the chilly weather, gregor's mother and put more folds into it so that grete would probably be the right time to spare him this ordeal, but it became difficult after that, especially as he lay his head would fall wearily against the door again straight away now, pack up my samples and set off. Will you report everything accurately, then? it's quite possible for him it was nearly carrying her. What shall we take now, then? , gregor asked himself as he ran after him, gregor's father seized the chief clerk go away in front of her hands. Drawn in by the window where they remained with their hands in their chairs reading them and break the connection with their leader. Once there, all three had jobs which were very good and held particularly good promise for the other foods on the landing and the longer it went so slowly. Gregor remained all this time would normally be sat with his
```

If you don't like the type of text generated by [The Metamorphosis](https://en.wikipedia.org/wiki/The_Metamorphosis) novel, you can go with the "industry standard" Lorem Ipsum:

```java
MockNeat mock = MockNeat.threadLocal();
String text = mock.markovs().size(1024).type(LOREM_IPSUM).val();
System.out.println(text);
```

Possible Output:

```
Consectetur lorem. Fusce a varius magna. Duis semper lorem ac porttitor sagittis. Curabitur massa nulla, maximus eu maximus vel, ultrices id lacus. Praesent mattis, arcu sit amet tempor venenatis, nunc est placerat purus, vel mollis enim ante ac purus. Suspendisse dapibus porta lectus non egestas. Sed cursus ligula et odio ultricies, at vehicula nibh semper. Morbi porta sed magna at hendrerit. Nulla eget neque dictum, efficitur mi ac, posuere nulla. Morbi diam ante, lobortis vitae tempor ac, mattis vel odio. Integer scelerisque at nisi a pharetra. Phasellus commodo ornare viverra. Etiam feugiat suscipit ultrices. Suspendisse dictum eget sem id ultricies. Nam quis nibh lorem. In sodales vel purus sed vehicula. Sed mollis elit velit, in condimentum dui rutrum eget. Sed ex justo, feugiat ac aliquet eu, commodo non tortor. Morbi nec dolor vel elit lobortis consectetur malesuada id metus. Fusce ut pellentesque nunc, eu luctus nisl. Etiam odio felis, accumsan in turpis nec, ornare laoreet elit. Aliquam sed ante pla
```

### Fill-up complex structures with data

Generate a "matrix" (`Integer[][]`) of "0" and "1":

```java
// Generating a matrix of random integers with 5 rows and 5 columns
Integer[][] a = m.from(new Integer[]{0, 1})
                 .array(() -> new Integer[5]) // Cols
                 .array(() -> new Integer[5][]) // Rows
                 .val();
                 
// Possible Output:
// 1 1 1 0 1
// 1 1 0 1 0
// 1 1 1 0 0
// 0 1 1 0 1
// 0 1 1 1 1 
```                     

Filling-up a complex structure:

```java
MockNeat mock = MockNeat.threadLocal();

Map<String, List<Map<Set<Integer>, List<Integer>>>> result =
                mock.ints()
                    .list(2)
                    .mapKeys(2, mock.ints().set(3)::val)
                    .list(2)
                    .mapKeys(4, mock.strings()::val)
                    .val();

System.out.println(result);
```

Possible Output (figure that!):

```
{3D2Ly=[{[1188658698, -57519401, -1864634426]=[2052830538, 366685266], [-133985978, -2085629065, 1907531435]=[1485192096, 605946545]}, {[450717932, -1027874751, -549281331]=[900908182, -1603177013], [742214495, -1457376922, 1024095212]=[86581883, 271158555]}], tTobl=[{[-1886416467, 548674791, -593491043]=[-1631835207, 127044558], [2070408663, 1969285421, 1886566844]=[2029888013, 1401655408]}, {[-2086648400, -305706082, -707025980]=[178357740, 1657815118], [235507533, 63522348, 1439128176]=[-1800049424, -1714421491]}], qIQLs=[{[1106366866, 663699257, 368333112]=[-1857289744, 600277178], [1526858982, -1690364246, 28655773]=[358915749, -1177167700]}, {[2006554761, -1416799941, -1912526788]=[-1768470769, 1934286466], [-1679536093, -1582849360, 35999417]=[1795480034, -705569340]}], w3LIX=[{[1859659934, 1564658075, -1996131138]=[-791077342, 1086818886], [1843489282, 423382881, 1587909770]=[-1350074159, -304332972]}, {[921761090, -376877683, 34301027]=[1680999098, 1039071483], [1696152588, 387405184, 363183726]=[1040085467, 1395835033]}]}
```

### Build random values from regex 

Generating a [`LOLs`](https://en.wikipedia.org/wiki/LOL) with varying levels of intensity:

```java
mock.regex("LO{1,15}L!").consume(10, (i, s) -> System.out.println(s));
```

Possible output:

```
LOOL!
LOOOL!
LOL!
LOOL!
LOL!
LOL!
LOOOOL!
LOOOOOOOOOOL!
LOL!
LOOL!
```

