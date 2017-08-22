package andreinc.mockneat.github;

import net.andreinc.aleph.AlephFormatter;
import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitString;

import java.util.List;

import static net.andreinc.mockneat.types.enums.StringFormatType.CAPITALIZED;

/**
 * Created by andreinicolinciobanu on 21/08/17.
 */
public class MockUnitExamplesString {

    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        // APPEND
        String[] cityAppend = mock.cities()
                            .capitals()
                            .append("-001")
                            .array(10)
                            .val();

        System.out.println(AlephFormatter.template("#{cities}", "cities", cityAppend).fmt());


        // ARRAY
        String[] someDays = mock.days()
                            .display()
                            .array(10)
                            .val();

        System.out.println(AlephFormatter.template("#{days}", "days", someDays).fmt());

        // BASE64

        List<String> names = mock
                                .names()
                                .first()
                                .format(CAPITALIZED)
                                .list(5)
                                .val();

        System.out.println(names);

        List<String> base64names = mock
                                .seq(names)
                                .mapToString()
                                .base64()
                                .list(5)
                                .val();

        System.out.println(base64names);

        // ESCAPE CSV

        String[] notFriendlyCsv = { "\"", /* OTHERS */};

        String friendlyCSV = mock.from(notFriendlyCsv)
                                    .mapToString()
                                    .escapeCsv()
                                    .val();

        System.out.println(friendlyCSV);

        // ESCAPE ECMA SCRIPT

        MockUnitString jsCode = () ->
                                () ->
                                "function myFunction() {\n" +
                                        "    document.getElementById(\"demo1\").innerHTML = \"Hello Dolly!\";\n" +
                                        "    document.getElementById(\"demo2\").innerHTML = \"How are you?\";\n" +
                                        "}";

        String escaped = jsCode
                            .escapeEcmaScript()
                            .val();

        System.out.println(escaped);
    }

}
