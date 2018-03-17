package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitInt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

/**
 * Created by andreinicolinciobanu on 17/03/18.
 */
public class MockUnitExamplesCollection {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();
        Collection<Boolean> c = m.bools().collection(() -> new Stack<>(), 10).val();

        MockUnitInt sizeGenerator = m.ints().range(0, 10);
        Collection<String> cStr = m.strings().collection(() -> new ArrayList<>(), sizeGenerator).val();

        System.out.println(cStr);
    }
}
