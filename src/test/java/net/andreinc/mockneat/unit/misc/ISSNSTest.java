package net.andreinc.mockneat.unit.misc;

import org.apache.commons.validator.routines.ISSNValidator;
import org.junit.Test;

import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertNotNull;

public class ISSNSTest {

    private final ISSNValidator validator = ISSNValidator.getInstance();

    @Test
    public void test() {
        loop(
                ISSNS_CYCLES,
                MOCKS,
                m -> m.issns().val(),
                issn -> assertNotNull(validator.validate(issn))
        );
    }
}
