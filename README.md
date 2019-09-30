Mockneat [![Build Status](https://travis-ci.org/nomemory/mockneat.svg?branch=master)](https://travis-ci.org/nomemory/mockneat.svg?branch=master) [![codecov](https://codecov.io/gh/nomemory/mockneat/branch/master/graph/badge.svg)](https://codecov.io/gh/nomemory/mockneat) is an arbitrary data-generator open-source library written in Java.

It provides a simple but powerful (fluent) API that enables developers to create json, xml, csv and sql data programatically.

It can also act as a powerful Random substitute or a mocking library.

Official Documentation: [www.mockneat.com](http://www.mockneat.com)

<a href='https://bintray.com/nomemory/maven/mockneat?source=watch' alt='Get automatic notifications about new "mockneat" versions'><img src='https://www.bintray.com/docs/images/bintray_badge_color.png'></a>

# Example - A random dice roll

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

