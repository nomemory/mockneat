package net.andreinc.mockneat.github;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. PARAM NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER PARAM AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.MockNeat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;
import static net.andreinc.mockneat.types.enums.CreditCardType.*;

public class GenerateCreditCards {

    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();
        final Path path = Paths.get("cc.txt");

        // Write in a file 1000 credit cards AMEX and Mastercard:
        m.creditCards()
                .types(MASTERCARD, AMERICAN_EXPRESS)
                .list(1000)
                .consume(list -> {
                    try { Files.write(path, list, CREATE, WRITE); }
                    catch (IOException e) { e.printStackTrace(); }
                });

        // Generates a single valid credit card number (AMEX)
        String amex = m.creditCards()
                       .type(AMERICAN_EXPRESS)
                       .val();
        System.out.println("AMEX: "  + amex);

        // Generates a single valid credit card number (MASTERCARD)
        String mastercard = m.creditCards()
                             .type(MASTERCARD)
                             .val();
        System.out.println("MASTERCARD: " + mastercard);

        // Generates a single Mastercard or AMEX credit card number
        String amexOrMastercard = m.creditCards()
                                   .types(AMERICAN_EXPRESS, MASTERCARD)
                                   .val();
        System.out.println("AMEX || MASTERCARD: " + amexOrMastercard);

        // Generates a a list of credit card numbers
        List<String> mastercards = m.creditCards()
                                    .type(MASTERCARD)
                                    .list(LinkedList.class, 10)
                                    .val();
        System.out.println("10 MasterCards CARDS: ");
        System.out.println(mastercard);

        // Generates an infinite stream of strings, each String
        // is a valic credit card number
        Stream<String> creditCards = m.creditCards()
                                      .stream()
                                      .val();

        System.out.println(m.creditCards().val());
        System.out.println(m.creditCards().type(VISA_16).val());

        String visaOrMastercard = m.creditCards()
                                   .types(VISA_16, MASTERCARD)
                                   .val();

        String ccName = m.creditCards().names().val();


    }
}
