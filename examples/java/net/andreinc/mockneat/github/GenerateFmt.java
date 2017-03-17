package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

import static net.andreinc.mockneat.alphabets.Alphabets.SPECIAL_CHARACTERS;

/**
 * Created by andreinicolinciobanu on 03/03/2017.
 */
public class GenerateFmt {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        String templ = "#{d1}#{d2}#{l1}#{sc}";
        String result = mock.fmt(templ)
                            .param("d1", mock.chars().digits())
                            .param("d2", mock.chars().digits())
                            .param("l1", mock.chars().letters())
                            .param("sc", mock.from(SPECIAL_CHARACTERS))
                            .val();
        System.out.println(result);
    }
}
