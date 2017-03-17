package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.unit.objects.model.Customer1;
import net.andreinc.mockneat.unit.objects.model.FinalValue;
import net.andreinc.mockneat.unit.objects.model.TheAbstractClass;
import net.andreinc.mockneat.Constants;
import net.andreinc.mockneat.utils.NamesCheckUtils;
import org.junit.Test;

import static java.time.LocalDate.of;
import static net.andreinc.mockneat.types.enums.CreditCardType.AMERICAN_EXPRESS;
import static net.andreinc.mockneat.types.enums.NameType.FIRST_NAME;
import static net.andreinc.mockneat.types.enums.NameType.LAST_NAME;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class ReflectTest {

    @Test(expected = NullPointerException.class)
    public void testReflectNullClass() throws Exception {
        Constants.M.reflect(null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReflectInvalidParam() throws Exception {
        Constants.M.reflect(Customer1.class)
                .field("f Name", Constants.M.names().full())
                .val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReflectNotExistentParam() throws Exception {
        Constants.M.reflect(Customer1.class)
                .field("firstNamex", Constants.M.names().full())
                .val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReflectionFinalValue() throws Exception {
        Constants.M.reflect(FinalValue.class)
                .field("name", "Test")
                .val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReflectionAbstractClass() throws Exception {
        Constants.M.reflect(TheAbstractClass.class)
                .field("name", "Test")
                .val();
    }

    @Test
    public void testReflectionConstruct() throws Exception {
        loop(
            Constants.OBJS_CYCLES,
            Constants.MOCKS,
            m -> m.reflect(Customer1.class)
                    .field("firstName", m.names().first())
                    .field("lastName", m.names().last())
                    .field("age", m.ints().range(18, 100))
                    .field("email", m.emails())
                    .field("country", m.countries().names())
                    .field("cardId", m.uuids())
                    .field("dateOfBirth", m.localDates().past(of(1907, 1, 1)))
                    .field("ipv4", m.ipv4s())
                    .field("ipv6", m.iPv6s())
                    .field("description", m.strings().size(128))
                    .field("amex", m.creditCards().type(AMERICAN_EXPRESS))
                    .field("cvv", m.cvvs())
                    .val(),
            c -> {
                assertTrue(c!=null);
                assertTrue(NamesCheckUtils.isNameOfType(c.getFirstName(), FIRST_NAME));
                assertTrue(NamesCheckUtils.isNameOfType(c.getLastName(), LAST_NAME));
            }
        );
    }
}
