package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

import static net.andreinc.mockneat.types.enums.DomainSuffixType.POPULAR;
import static net.andreinc.mockneat.types.enums.HostNameType.ADVERB_VERB;
import static net.andreinc.mockneat.types.enums.URLSchemeType.HTTP;

/**
 * Created by andreinicolinciobanu on 09/03/18.
 */
public class GenerateURLs {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();

        m.urls()
         .scheme(HTTP)
         //.auth()
         .domain(POPULAR)
         .host(ADVERB_VERB)
         .ports(80, 8080, 8090)
         .list(10)
         .consume(System.out::println);
    }
}
