package net.andreinc.mockneat;

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

import net.andreinc.mockneat.abstraction.MockUnit;

import java.util.List;

import static java.time.LocalDate.of;
import static net.andreinc.mockneat.types.enums.CreditCardType.AMERICAN_EXPRESS;
import static net.andreinc.mockneat.types.enums.CreditCardType.MASTERCARD;
import static net.andreinc.mockneat.types.enums.IPv4Type.CLASS_B;

public class Example01 {
    public static void main(String[] args) {

        // Creates a MockNeat add that internally uses
        // a ThreadLocalRandom.
        MockNeat m = MockNeat.threadLocal();

        // Generates the list of employees for
        // an imaginary comapny "company.com"
        List<Employee> companyEmployees =
                m.reflect(Employee.class)
                    .field("uniqueId",
                            m.uuids())
                    .field("id",
                            m.longSeq())
                    .field("fullName",
                            m.names().full(50))
                    .field("companyEmail",
                            m.emails().domain("company.com"))
                    .field("personalEmail",
                            m.emails())
                    .field("salaryCreditCard",
                            m.creditCards().types(AMERICAN_EXPRESS, MASTERCARD))
                    .field("external",
                            m.bools().probability(20.0))
                    .field("hireDate",
                            m.localDates().past(of(1999, 1, 1)))
                    .field("birthDate",
                            m.localDates().between(of(1950, 1, 1), of(1994, 1, 1)))
                    .field("pcs",
                            m.reflect(EmployeePC.class)
                                .field("uuid",
                                     m.uuids())
                                .field("username",
                                     m.users())
                                .field("operatingSystem",
                                     m.from(new String[]{"Linux", "Windows 10", "Windows 8"}))
                                .field("ipAddress",
                                        m.ipv4s().type(CLASS_B))
                                .field("macAddress",
                                        m.macs())
                                .list(2))
                    .list(1000)
                    .val();

        System.out.println("Total number of employees: " + companyEmployees.size());
        for(Employee e : companyEmployees) {
            System.out.println("Employee id: " + e.getId());
            System.out.println("\t uuid: " + e.getUniqueId());
            System.out.println("\t fullName: " + e.getFullName());
            System.out.println("\t companyEmail: " + e.getCompanyEmail());
            System.out.println("\t personalEmail: " + e.getPersonalEmail());
            System.out.println("\t salaryCreditCard: " + e.getSalaryCreditCard());
            System.out.println("\t external: " + e.getExternal());
            System.out.println("\t hireDate: " + e.getHireDate());
            System.out.println("\t birthDate: " + e.getBirthDate());
            System.out.println("\t pcs: " + e.getPcs().size());
            for(EmployeePC pc : e.getPcs()) {
                System.out.println("\t\t uuid: " + pc.getUuid());
                System.out.println("\t\t username: " + pc.getUsername());
                System.out.println("\t\t operatingSystem: " + pc.getOperatingSystem());
                System.out.println("\t\t ipAddress: " + pc.getIpAddress());
                System.out.println("\t\t macAddress: " + pc.getMacAddress());
                System.out.println("\t\t-");
            }
            System.out.println("------------------------------");
        }

        MockUnit<List<String>> list = m.from(new String[]{"1", "2", "3"}).list(10);
        System.out.println(list.val());
        System.out.println(list.val());
        System.out.println(list.val());
        System.out.println(list.val());
    }
}
