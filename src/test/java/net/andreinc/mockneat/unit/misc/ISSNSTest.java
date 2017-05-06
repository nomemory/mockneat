package net.andreinc.mockneat.unit.misc;

import org.apache.commons.validator.routines.ISSNValidator;
import org.junit.Test;

import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 26/03/17.
 */
public class ISSNSTest {

    private ISSNValidator validator = ISSNValidator.getInstance();

    @Test
    public void test() throws Exception {
        loop(
                true,
                ISSNS_CYCLES,
                MOCKS,
                m -> m.issns().val(),
                issn -> assertTrue(validator.validate(issn)!=null)
        );
    }
}
