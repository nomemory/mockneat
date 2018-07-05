# MockNeat 

**MockNeat** is a Java 8+ library that facilitates the generation of arbitrary data for your applications.

*Latest Version: 05.JUL.2018 - 0.1.8*

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

## Who is using MockNeat ?
**MockNeat** was downloaded +/-3300 times since May, 2017.

If you are using **MockNeat** in your cool projects please send me a note so I can include you in this list.

## Examples

### 1. Mocking Real-World Objects

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

### 2. Writing CSV files

Example for creating a CSV file with arbitrary data that has the following structure:

`id, firstName, lastName, email, salary (euro)`

The file should contain 1000 lines.

```java
MockNeat m = MockNeat.threadLocal();
final Path path = Paths.get("./test.csv");

m.fmt("#{id},#{first},#{last},#{email},#{salary},#{creditCardNum}")
 .param("id", m.longSeq().start(10).increment(10).mapToString().escapeCsv())
 .param("first", m.names().first().escapeCsv())
 .param("last", m.names().last().escapeCsv())
 .param("email", m.emails().domain("company.com").mapToString().escapeCsv())
 .param("salary", m.ints().range(1000, 5000).mapToString().escapeCsv())
 .param("creditCardNum", m.creditCards().type(AMERICAN_EXPRESS).escapeCsv())
 .list(1000)
 .consume(list -> {
             try { Files.write(path, list, CREATE, WRITE); }
             catch (IOException e) { e.printStackTrace(); }
          });
```

Possible Output:

```
10,Marianna,Duma,laithswoop@company.com,2214,341781929348574
20,Taryn,Contrenas,moistwerner@company.com,3583,343141643983552
30,Melissa,Hayward,costivetian@company.com,1552,345359563978695
40,Vernita,Blind,brutalanastos@company.com,4454,349356219759467
50,Sharon,Susany,poisedkati@company.com,1107,341564268273557
60,Catina,Barroso,rakedgilbert@company.com,2817,373895563308966
70,Joe,Hibbets,acredhordes@company.com,1857,340740009576588
80,Pearlene,Baldon,heeledwinston@company.com,2550,375873851661469
90,Evette,Zeis,plusserita@company.com,1578,375692810718024
100,Chrystal,Ambroz,huedelvin@company.com,2130,342226196016008
110,Thuy,Sturkie,flawedmoorehouse@company.com,1056,376516621386714
120,Colleen,Stachowicz,scoremelba@company.com,2522,343614618335958
130,Lacey,Franza,quitereive@company.com,3712,377995434270084
140,Cherri,Maille,daftumber@company.com,4360,370982611739914
....
....
```

### 3. Probabilities

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

### 4. Generating (Markov) Text from the writings of Franz Kafka ... or Lorem Ipsum

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

### 5. Fill-up complex structures

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

### 6. Generating data that always match a certain (simple) regex:

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

