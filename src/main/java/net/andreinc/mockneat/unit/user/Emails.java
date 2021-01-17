package net.andreinc.mockneat.unit.user;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.abstraction.MockUnit;

import java.util.function.Supplier;
import static net.andreinc.mockneat.types.enums.DictType.DOMAIN_EMAIL;

public class Emails extends MockUnitBase implements MockUnitString {

    /**
     * <p>Returns an {@code Email} object that can be used to generate arbitrary email address.</p>
     *
     * @return A re-usable {@code Emails} object. The {@code Emails} class implements {@code MockUnitString}.
     */
    public static Emails emails() {
        return MockNeat.threadLocal().emails();
    }

    public Emails(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return () -> mockNeat.users().val() + "@" + mockNeat.dicts().type(DOMAIN_EMAIL).val();
    }

    /**
     * <p>Generates a new {@code MockUnitString} that can be used to generate emails that have specific domains (eg.: "gmail.com").</p>
     *
     * <p>This is particularly useful when the requirement is to generate "company/enterprise" emails.</p>
     *
     * @param domains A var-arg String array containing the list of the domains to be used.
     * @return A new {@code MockUnitString}.
     */
    public MockUnit<String> domains(String... domains) {
        Supplier<String> supp = () -> {
            String user = mockNeat.users().val();
            String domain = mockNeat.from(domains).val();
            return user + "@" + domain;
        };
        return () -> supp;
    }

    /**
     * <p>Generates a new {@code MockUnitString} that can be used to generate emails that have a specific domain (eg.: "gmail.com").</p>
     *
     * <p>This is particularly useful when the requirement is to generate "company/enterprise" emails.</p>
     *
     * @param domain The domain to be used.
     * @return A new {@code MockUnitString}.
     */
    public MockUnit<String> domain(String domain) {
        return domains(domain);
    }
}
