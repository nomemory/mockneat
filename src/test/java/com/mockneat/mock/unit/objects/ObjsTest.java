package com.mockneat.mock.unit.objects;

import com.mockneat.mock.unit.objects.model.Customer1;
import com.mockneat.mock.utils.LoopsUtils;
import org.junit.Test;

import static com.mockneat.mock.Constants.MOCKS;
import static com.mockneat.mock.Constants.OBJS_CYCLES;
import static com.mockneat.mock.utils.NamesCheckUtils.isNameOfType;
import static com.mockneat.types.enums.CreditCardType.AMERICAN_EXPRESS;
import static com.mockneat.types.enums.NameType.FIRST_NAME;
import static com.mockneat.types.enums.NameType.LAST_NAME;
import static java.time.LocalDate.of;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 26/02/2017.
 */
public class ObjsTest {

    @Test
    public void testConstruct1() throws Exception {
        LoopsUtils.loop(
            OBJS_CYCLES,
            MOCKS,
            m -> m.objs(Customer1.class)
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
                assertTrue(isNameOfType(c.getFirstName(), FIRST_NAME));
                assertTrue(isNameOfType(c.getLastName(), LAST_NAME));
            }
        );
    }
}
