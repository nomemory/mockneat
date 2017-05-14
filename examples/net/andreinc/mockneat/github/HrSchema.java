package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static net.andreinc.mockneat.MockNeat.threadLocal;

/**
 * Created by andreinicolinciobanu on 11/05/17.
 */
public class HrSchema {

    private static final int REG_START = 0;
    private static final int REG_END = 5;
    public static final int COUNTRY_NUM = 100;

    public static void main(String[] args) {

    }

    public List<String> generateCountries() {
        MockNeat mockNeat = threadLocal();

        return mockNeat.fmt("INSERT INTO countries VALUES ('#{code}', '#{name}', #{id}, #{region_id});")
                        .param("code", mockNeat.countries().iso2())
                        .param("name", mockNeat.countries().names())
                        .param("id", mockNeat.longSeq())
                        .param("region_id", mockNeat.ints().range(REG_START, REG_END))
                        .set(COUNTRY_NUM).val().stream()
                        .collect(toList());
    }
}
