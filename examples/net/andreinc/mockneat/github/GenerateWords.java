package andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

import static net.andreinc.mockneat.types.enums.StringFormatType.CAPITALIZED;
import static net.andreinc.mockneat.types.enums.StringFormatType.LOWER_CASE;

/**
 * Created by andreinicolinciobanu on 25/08/17.
 */
public class GenerateWords {
    public static void main(String[] args) {
        MockNeat mockNeat = MockNeat.threadLocal();

        mockNeat.fmt("#{adj} #{noun}")
                .param("adj", mockNeat.words().adjectives().format(CAPITALIZED))
                .param("noun", mockNeat.words().nouns().format(LOWER_CASE))
                .list(10)
                .consume(list -> System.out.println(list));
    }
}
