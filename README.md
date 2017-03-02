# mockneat

**MockNeat** is a Java 8+ library that facilitates the generation of random data for your applications. 

The good:
- The first stable "alpha" release is going to come soon;

Still a lot of work:
- The test coverage is far from being complete. At this moment there are around 380 Unit Tests that cover most of the functionality, but a lot of "corner-cases" are still not tested;
- There is no Maven Central / JCenter repository yet. The only way you can the library is through github and build it yourself using gradle;

The bad:
- The library makes heavy use of Java 8 features. At this moment it only supports Java 8+, and there are no plans for supporting previous Java versions.

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

We can generate advanced structures of data. For example we can generate a ``List<Map<String, Map<List<String>, Set<Integer>>>>``:sweat: writing a few Lines of code:
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

### Sneak-Peak 3 - Literature

We can generate Markov text from the opera of the Famous writer Franz Kafka (just for fun, of course):

```java
String literature = m.markovs().size(1024).type(KAFKA).val();
```

The results are not spectacular, but they can pass as a better form of giberish.
```
Only when the clock struck again, seven o'clock already, he shouted as he swang himself with anger, into the room on the beds and slipped out again and even felt much hungrier than usual. (...)
```
### Sneak-Peak 4 - Mocking Objects

We can mock complex objects just like anything else:

```java
 List<Customer> customers = r.objs(Customer.class)
                                        .field("UUID", r.uuids())
                                        .field("firstName", r.names()
                                                             .type(FIRST_NAME_MALE_AMERICAN)
                                                             .format(CAPITALIZED))
                                        .field("lastName", r.names())
                                        .field("userName", r.users().type(ADJECTIVE_NOUN))
                                        .field("description", r.markovs().type(KAFKA))
                                        .field("country", r.countries().names())
                                        .field("lastIpAddress", r.ipv4s().type(CLASS_B))
                                        .field("registeredMacAddress", r.macs())
                                        .field("creditCardsCVVs", r.ccs()
                                                                   .type(AMERICAN_EXPRESS)
                                                                   .mapVals(10, r.cvvs()::val))
                                        .list(100)
                                        .val();
```
