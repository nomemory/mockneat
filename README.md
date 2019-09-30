Mockneat [![Build Status](https://travis-ci.org/nomemory/mockneat.svg?branch=master)](https://travis-ci.org/nomemory/mockneat.svg?branch=master) [![codecov](https://codecov.io/gh/nomemory/mockneat/branch/master/graph/badge.svg)](https://codecov.io/gh/nomemory/mockneat) is an arbitrary data-generator open-source library written in Java.

It provides a simple but powerful (fluent) API that enables developers to create json, xml, csv and sql data programatically.

It can also act as a powerful Random substitute or a mocking library.

Official Documentation: [www.mockneat.com](http://www.mockneat.com)

<a href='https://bintray.com/nomemory/maven/mockneat?source=watch' alt='Get automatic notifications about new "mockneat" versions'><img src='https://www.bintray.com/docs/images/bintray_badge_color.png'></a>

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
Heidi ; Toppah ; helplessspargur@mockneat.com ; http://www.gunnedteak.info ; 132.179.188.63 ; 4971418965545834 ; 2019-09-26
Mitchell ; Ottey ; earthwardcorea@mockneat.com ; http://www.bridgeboardscolin.com ; 136.83.218.147 ; 4944053646009563 ; 2019-08-29
Lachelle ; Giliberto ; tiredawilda@mockneat.com ; http://www.streakierrosana.info ; 190.184.229.206 ; 377061114362210 ; 2019-07-29
Brett ; Rhude ; byesanford@mockneat.com ; http://www.tellinglop.info ; 129.108.214.132 ; 4780665014255333 ; 2019-09-30
Noelia ; Escobeo ; worthwhilechrisley@mockneat.com ; http://www.crosserudolf.io ; 199.215.208.93 ; 4575973786002662 ; 2019-03-12
France ; Felarca ; clippedwyatt@mockneat.com ; http://www.airjoel.gov ; 220.119.151.146 ; 4616235548543685 ; 2019-04-19
Dorthy ; Lafreniere ; workedgwen@mockneat.com ; http://www.incogroke.org ; 136.131.164.42 ; 371404249922753 ; 2019-09-04
Nancie ; Brom ; yeldjohnnie@mockneat.com ; http://www.yonfudge.io ; 176.180.234.45 ; 4903347293758780 ; 2019-01-17
Latrina ; Hogon ; livedcervix@mockneat.com ; http://www.thriftiestwarmth.io ; 145.250.160.75 ; 4473715266279036 ; 2019-07-31
Danita ; Milord ; wiredconcepcion@mockneat.com ; http://www.fledgedshanon.edu ; 154.10.150.97 ; 374510146688219 ; 2019-11-13
Trudi ; Hewgley ; juttinggaar@mockneat.com ; http://www.terriblesheri.gov ; 200.138.164.210 ; 379728296690844 ; 2019-09-05
Aretha ; Jeminez ; taisung@mockneat.com ; http://www.worldlyjames.info ; 161.187.83.85 ; 4298082840623087 ; 2019-12-18
Sharee ; Leaven ; couthkaylee@mockneat.com ; http://www.tearlesslaity.info ; 153.51.166.144 ; 345630142606050 ; 2019-02-22
Dennis ; Solivan ; loneverda@mockneat.com ; http://www.pockedhumus.edu ; 198.246.110.149 ; 346704086911146 ; 2019-08-10
Kami ; Cioni ; triterodak@mockneat.com ; http://www.sparrydana.edu ; 203.99.140.45 ; 346437996374747 ; 2019-10-03
Marry ; Kauder ; nettruss@mockneat.com ; http://www.cryptalarmand.org ; 172.217.87.40 ; 4552346247013718 ; 2019-07-15
Donita ; Sudderth ; slubrobin@mockneat.com ; http://www.yeldrueben.net ; 137.229.117.141 ; 4981023263797919 ; 2019-09-17
Aleida ; Hoysock ; twofoldlegalley@mockneat.com ; http://www.faultlesslyawing.io ; 158.110.143.33 ; 370342574360507 ; 2019-02-15
Evelyn ; Minella ; fiercejarvis@mockneat.com ; http://www.prudentfun.gov ; 133.177.78.12 ; 374283975881012 ; 2019-03-01
Marica ; Chukes ; blondehector@mockneat.com ; http://www.thusnosh.net ; 212.170.85.140 ; 377726419486282 ; 2019-01-05
```
