package net.andreinc.mockneat;


import net.andreinc.mockneat.interfaces.MockUnit;

import java.util.List;

import static java.time.LocalDate.of;
import static net.andreinc.mockneat.types.enums.CreditCardType.AMERICAN_EXPRESS;
import static net.andreinc.mockneat.types.enums.CreditCardType.MASTERCARD;
import static net.andreinc.mockneat.types.enums.IPv4Type.CLASS_B;

/**
 * Created by andreinicolinciobanu on 02/03/2017.
 */
public class Example01 {
    public static void main(String[] args) {

        // Creates a MockNeat object that internally uses
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
