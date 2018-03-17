package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitInt;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by andreinicolinciobanu on 17/03/18.
 */
public class MockUnitExamplesMapKey {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();

        MockUnitInt keysGenerator = m.intSeq();
        Map<Integer, String> namesMap = m.names()
                                         .mapKeys(() -> new LinkedHashMap<>(), keysGenerator.list(10).val())
                                         .val();
        System.out.println(namesMap);
    }
}
