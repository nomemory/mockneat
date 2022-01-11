 <img src="https://github.com/nomemory/mockneat/blob/master/logo/logo.png" width="200" height="200">

Mockneat [![Maven Central](https://img.shields.io/maven-central/v/net.andreinc/mockneat.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22net.andreinc%22%20AND%20a:%22mockneat%22) [![Build Status](https://travis-ci.org/nomemory/mockneat.svg?branch=master)](https://travis-ci.org/nomemory/mockneat.svg?branch=master) [![codecov](https://codecov.io/gh/nomemory/mockneat/branch/master/graph/badge.svg)](https://codecov.io/gh/nomemory/mockneat) is an arbitrary data-generator open-source library written in Java.

It provides a simple but powerful (fluent) API that enables developers to create json, xml, csv and sql data programatically.

It can also act as a powerful Random substitute or a mocking library.

Official Documentation: **[www.mockneat.com](http://www.mockneat.com)**

Official Tutorial: **[www.mockneat.com](http://www.mockneat.com/tutorial)**

If you want to use **mockneat** to mock REST APIs checkout my other project: **[serverneat](https://github.com/nomemory/serverneat)**.

### Installing

#### >= 0.4.4

Maven:
```xml
<dependency>
  <groupId>net.andreinc</groupId>
  <artifactId>mockneat</artifactId>
  <version>0.4.8</version>
</dependency>

```

Gradle:
```groovy
implementation 'net.andreinc:mockneat:0.4.8'
```

#### <= 0.4.2

Maven:
```xml
<repositories>
    <repository>
        <id>jcenter</id>
        <url>https://jcenter.bintray.com/</url>
    </repository>
</repositories>
<dependencies>
    <dependency>
        <groupId>net.andreinc.mockneat</groupId>
        <artifactId>mockneat</artifactId>
        <version>0.4.2</version>
    </dependency>
</dependencies>
```

Gradle:
```groovy
repositories {
  jcenter()
}
dependencies {
  compile 'net.andreinc.mockneat:mockneat:0.4.2'
}

```


### Example - A random dice roll

```java
List<String> somePeople = names().full().list(10).get();

fmt("#{person} rolled: #{roll1} #{roll2}")
            .param("person", seq(somePeople))
            .param("roll1", ints().rangeClosed(1, 6))
            .param("roll2", ints().rangeClosed(1, 6))
            .accumulate(10, "\n")
            .consume(System.out::println);

System.out.println("\nWho wins ?\n");
```    

(possible) Output:

```
Sal Clouden rolled: 3 3
Cinthia Myrum rolled: 1 5
Wyatt Imber rolled: 5 1
Fidel Quist rolled: 2 2
Brandon Scrape rolled: 6 4
Arlene Cesare rolled: 6 4
Brandie Sumsion rolled: 3 4
Norris Tunby rolled: 3 5
Kareem Willoughby rolled: 1 5
Zoraida Finnerty rolled: 1 6

Who wins ?
```

### Example - A simple CSV

```java
System.out.println("First Name, Last Name, Email, Site, IP, Credit Card, Date");

csvs()
  .column(names().first())
  .column(names().last())
  .column(emails().domain("mockneat.com"))
  .column(urls().domains(POPULAR))
  .column(ipv4s().types(CLASS_B, CLASS_C_NONPRIVATE))
  .column(creditCards().types(AMERICAN_EXPRESS, VISA_16))
  .column(localDates().thisYear())
  .separator(" ; ")
  .accumulate(25, "\n")
  .consume(System.out::println);
```

(possible) Output:

```
Lorrie ; Urquilla ; slycriselda@mockneat.com ; http://www.sugaredherlinda.com ; 172.150.99.65 ; 4991053014393849 ; 2019-05-25
Tabitha ; Copsey ; headsoutdanced@mockneat.com ; http://www.arightcarnify.io ; 166.192.196.15 ; 4143903215740668 ; 2019-07-13
Laurine ; Patrylak ; doggonetews@mockneat.com ; http://www.ninthbanc.gov ; 187.28.250.76 ; 4450754596171263 ; 2019-09-10
Starla ; Peiper ; typicsteres@mockneat.com ; http://www.eathlessen.edu ; 202.189.115.252 ; 4470988734574428 ; 2019-02-18
Lakiesha ; Zevenbergen ; stalegaye@mockneat.com ; http://www.unbendingeyes.edu ; 204.112.195.47 ; 4040555724781858 ; 2019-11-12

... and so on
```

--------

Special thanks to the contributors:

* https://github.com/mariotrucco
* https://hatchful.shopify.com/ (for creating the logo)
