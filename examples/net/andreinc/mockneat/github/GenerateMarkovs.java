package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

import static net.andreinc.mockneat.types.enums.MarkovChainType.KAFKA;

/**
 * Created by andreinicolinciobanu on 04/03/2017.
 */
public class GenerateMarkovs {

    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        String text = mock.markovs().size(512).type(KAFKA).val();
        System.out.println(text);
    }
}
