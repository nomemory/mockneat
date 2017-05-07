package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.interfaces.MockUnit;

import java.util.List;

/**
 * Created by andreinicolinciobanu on 11/03/2017.
 */
public class MockUnitExamplesMapToLong {
    public static void main(String[] args) {

        MockNeat m = MockNeat.threadLocal();

        MockUnit<List<Long>> muList = m.longs()
                                        .range(0l, 100l)
                                        .list(10);

        Long sum = muList
                    .mapToLong(list -> list.stream()
                                            .mapToLong(Long::longValue)
                                            .sum())
                    .val();

        System.out.println(sum);
    }
}
