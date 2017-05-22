package net.andreinc.mockneat.github.hr;

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
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.DictType;

import java.util.List;

import static java.util.Arrays.asList;
import static net.andreinc.mockneat.types.enums.DictType.*;
import static net.andreinc.mockneat.types.enums.StringFormatType.CAPITALIZED;

public class HrSchema {

    private static final int REG_START = 0;
    private static final int REG_END = 5;
    public static final int COUNTRY_NUM = 100;

    private static final List<String> REGIONS =
            asList("Europe", "Americas", "Middle East and Africa", "Asia");

    public static void main(String[] args) {

        MockNeat m = MockNeat.threadLocal();

        // Generate Regions
        // IDs: [0, 2]
        m.fmt("INSERT INTO regions VALUE (#{id}, '#{name}');")
         .param("id", m.intSeq())
         .param("name", m.seq(REGIONS))
         .list(3)
         .consume(HrSchema::prettyPrint);

        // Generate Countries
        m.fmt("INSERT INTO countries VALUES('#{id}', '#{name}', #{regionId});")
         .param("id", m.seq(DictType.COUNTRY_ISO_CODE_2))
         .param("name", m.seq(DictType.COUNTRY_NAME))
         .param("regionId", m.ints().range(0, 3))
         .list(241)
         .consume(HrSchema::prettyPrint);

        // Generate Locations
        MockUnitString streetNameSuffix = m.fromStrings(new String[]{"Rd", "Str", "Blvd"});

        MockUnitString streetNameGenerator = m.fmt("#{word1}#{word2}")
                                             .param("word1", m.probabilites(String.class)
                                                                     .add(0.55, m.dicts().types(EN_ADJECTIVE_1SYLL, EN_ADJECTIVE_2SYLL))
                                                                     .add(0.45, "")
                                                                     .mapToString(s -> s.equals("") ? s : s + " ")
                                                                     .format(CAPITALIZED))
                                             .param("word2", m.dicts().types(EN_NOUN_1SYLL, EN_NOUN_2SYLL, EN_NOUN_3SYLL).format(CAPITALIZED));

        MockUnitString streetGenerator = m.fmt("#{no} #{name} #{suffix}")
                                         .param("no", m.ints().range(1, 10000))
                                         .param("name", streetNameGenerator)
                                         .param( "suffix", streetNameSuffix);

        m.fmt("INSERT INTO locations VALUES(#{id}, '#{street}', '#{postalCode}', '#{city}', #{state}, '#{countryId}');")
         .param("id", m.intSeq().start(1000).increment(100))
         .param("street", streetGenerator)
         .param("city", m.cities().capitals())
         .param( "postalCode", m.regex("[A-Z]{1,3}[0-9]{1,2}[A-Z]{1,2}[0-9]{3,5}"))
         .param("countryId", m.countries().iso2())
         .list(100)
         .consume(HrSchema::prettyPrint);

        // Departments
        m.fmt("INSERT INTO departments VALUES(#{id}, '#{depName}', #{managerId}, #{locationId});")
         .param("id", m.longSeq().start(10).increment(10))
         .param("depName", m.seq(DEPARTMENTS).after(m.strings().size(16).format(CAPITALIZED)))
         .param("managerId", m.longs().range(0, 500))
         .param( "countryId", m.longs().range(10, 110).map(v -> v * 100))
         .list(16)
         .consume(HrSchema::prettyPrint);
    }

    public static void prettyPrint(List<String> list) {
        list.forEach(s -> System.out.println(s));
    }
}
