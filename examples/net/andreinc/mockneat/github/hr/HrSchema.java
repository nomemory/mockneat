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

import net.andreinc.aleph.AlephFormatter;
import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.github.hr.model.*;
import net.andreinc.mockneat.types.enums.DictType;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static net.andreinc.mockneat.types.enums.DictType.*;
import static net.andreinc.mockneat.types.enums.StringFormatType.CAPITALIZED;

public class HrSchema {

    private static final List<String> REGIONS =
            asList("Europe", "Americas", "Middle East and Africa", "Asia");

    private static final List<String> STREET_SUFFIX =
            asList("Rd", "Str", "Blvd");

    /**
     * Returns an arbitrary street name.
     * @return
     */
    private static final MockUnitString streeNameGenerator() {

        // A reference to the mock add associated with the current thread;
        MockNeat m = MockNeat.threadLocal();

        // Returns a random string from the 'STREET_SUFFIX' list;
        // Eg: "Str"
        MockUnitString streetNameSuffix = m.fromStrings(STREET_SUFFIX);

        // Returns a potential street name
        // - Streets names have 55% chances of containing two words;
        // - Street names have 45% chances of containing one word;
        // The single word is always a noun (1 syllable, 2 syllables or 3 syllables)
        // The first word if exists is an adjective (1 syllable or 2 syllables)
        MockUnitString streetNameGenerator = m.fmt("#{word1}#{word2}")
                .param("word1", m.probabilites(String.class)
                                        .add(0.55, m.dicts().types(EN_ADJECTIVE_1SYLL, EN_ADJECTIVE_2SYLL))
                                        .add(0.45, "")
                                        // If the first word exists (55% chance) then append a space
                                        .mapToString(s -> s.equals("") ? s : s + " ")
                                        // Capitalize the two (or one words) generated
                                        .format(CAPITALIZED))
                .param("word2", m.dicts().types(EN_NOUN_1SYLL, EN_NOUN_2SYLL, EN_NOUN_3SYLL).format(CAPITALIZED));

        // Formatting street name
        // - First section is a number in the range [1, 100000)
        // - Second section is the name as generated above
        // - Last section is the suffix as obtained above
        return m.fmt("#{no} #{name} #{suffix}")
                .param("no", m.ints().range(1, 10000))
                .param("name", streetNameGenerator)
                .param( "suffix", streetNameSuffix);
    }

    /**
     * For the given location appends country ISO2 code to the city name
     * @param location
     * @return
     */
    public static Location cityWithCountryId(Location location) {
        String newCity = location.getCity() + " " + location.getCountryId();
        location.setCity(newCity);
        return location;
    }

    /**
     * Generates a STR_TO_DATE string that is going to be used for the MySQL INSERT
     * @param employee
     * @return
     */
    public static Employee populateHireDateStr(Employee employee) {
        Date date = employee.getHireDate();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        String dateStr = sdf.format(date);
        employee.setHireDateStr("STR_TO_DATE('" + dateStr + "', '%d-%M-%Y')");
        return employee;
    }

    /**
     * Prints a list of objects as a SQL INSERT Statement.
     * @param template The template of the SQL INSERT Statement
     * @param param The name of the parameter used in the template
     * @param list The list of objects we are going to use for the INSERT
     */
    private static <T> void printSql(String template, String param, List<T> list) {
        list.forEach(obj -> System.out.println(
            AlephFormatter.template(template)
                    .arg(param, obj)
                    .fmt()));
    }

    public static void main(String[] args) {

        MockNeat m = MockNeat.threadLocal();

        // Generate Regions
        List<Region> regions = m.reflect(Region.class)
                                .field("id", m.longSeq())
                                .field("name", m.seq(REGIONS))
                                .list(REGIONS.size())
                                .val();

        // Generate Countries
        final int totalCountries = 241;
        List<Country> countries = m.reflect(Country.class)
                                   .field("id", m.seq(DictType.COUNTRY_ISO_CODE_2))
                                   .field("name", m.seq(DictType.COUNTRY_NAME).mapToString().replaceAll("'", "''"))
                                   .field("regionId", m.from(regions).map(Region::getId))
                                   .list(totalCountries)
                                   .val();

        // Generate Locations
        int totalLocations = 200;
        List<Location> locations = m.reflect(Location.class)
                                    .field("id", m.longSeq().start(1000).increment(100))
                                    .field("street", streeNameGenerator())
                                    .field("city", m.cities().capitals())
                                    .field("postalCode", m.regex("[A-Z]{1,3}[0-9]{1,2}[A-Z]{1,2}[0-9]{3,5}"))
                                    .field("countryId", m.from(countries).map(Country::getId))
                                    .map(HrSchema::cityWithCountryId)
                                    .list(totalLocations)
                                    .val();


        // Departments
        int totalDepartments = 16;
        List<Departments> departments = m.reflect(Departments.class)
                                         .field("id", m.longSeq().start(10).increment(10))
                                         .field( "depName", m.seq(DEPARTMENTS))
                                         .field( "locationId", m.from(locations).map(Location::getId))
                                         .list(16)
                                         .val();

        // Employees
        int totalEmployees = 1500;
        LocalDate minDate = LocalDate.of(1990, 1, 1);
        List<Employee> employees = m.reflect(Employee.class)
                                    .field("id", m.longSeq().start(1))
                                    .field("firstName", m.names().first())
                                    .field("lastName", m.names().last())
                                    .field("email", m.emails())
                                    .field("phoneNumber", m.regex("([0-9]{3}) [0-9]{10}"))
                                    .field("hireDate", m.localDates().past(minDate).toUtilDate())
                                    .field("salary", m.doubles().range(2000.0, 10000.0))
                                    .field("depId", m.from(departments).map(Departments::getId))
                                    .map(HrSchema::populateHireDateStr)
                                    .list(totalEmployees)
                                    .val();

        // Decide who is manager
        int totalNumManagers = 100;
        List<Long> managersIds = m.from(employees)
                                  .map(Employee::getId)
                                  .list(totalNumManagers).val()
                                  .stream().distinct().collect(Collectors.toList());

        // Set Up managers for employees
        employees.forEach(
            e ->  {
                long empId = e.getId();
                long mngId = e.getId();

                // Avoid assigning the same number as
                // id and managerId
                while(mngId == empId)
                    mngId = m.from(managersIds).val();

                e.setManagerId(mngId);
            }
        );

        // Set Up Managers for departments
        MockUnit<Long> managersIdSeq = m.seq(managersIds);
        departments.forEach(d -> d.setManagerId(managersIdSeq.val()));

        // Print everything
        System.out.println("SET FOREIGN_KEY_CHECKS = 0;");
        printSql("INSERT INTO regions VALUE (#{region.id}, '#{region.name}');", "region", regions);
        printSql("INSERT INTO countries VALUES('#{country.id}', '#{country.name}', #{country.regionId});", "country", countries);
        printSql("INSERT INTO locations VALUES(#{l.id}, '#{l.street}', '#{l.postalCode}', '#{l.city}', #{l.state}, '#{l.countryId}');", "l", locations);
        printSql("INSERT INTO departments VALUES(#{dep.id}, '#{dep.depName}', #{dep.managerId}, #{dep.locationId});","dep", departments);
        printSql("INSERT INTO employees VALUES(#{e.id}, '#{e.firstName}', '#{e.lastName}', '#{e.email}', '#{e.phoneNumber}', #{e.hireDateStr}, #{e.salary}, #{e.managerId}, #{e.depId});", "e", employees);
        System.out.println("SET FOREIGN_KEY_CHECKS = 1;");
        System.out.println("COMMIT;");
    }
}
