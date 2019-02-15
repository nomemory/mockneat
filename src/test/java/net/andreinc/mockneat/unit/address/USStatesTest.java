package net.andreinc.mockneat.unit.address;

import net.andreinc.mockneat.Constants;
import net.andreinc.mockneat.types.enums.DictType;
import net.andreinc.mockneat.utils.LoopsUtils;
import net.andreinc.mockneat.utils.file.FileManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class USStatesTest {

    private static final FileManager FM = FileManager.getInstance();
    private static final Set<String> US_STATES = new HashSet<>();
    private static final Set<String> US_STATES_ISO2 = new HashSet<>();

    static {
        US_STATES.addAll(FM.getLines(DictType.US_STATES));
        US_STATES_ISO2.addAll(FM.getLines(DictType.US_STATES_IS_CODE_2));
    }

    @Test
    public void testUsStates() {
        LoopsUtils.loop(
                Constants.US_STATES_CYCLES,
                Constants.MOCKS,
                mockNeat -> mockNeat.usStates().val(),
                usState -> Assert.assertTrue(US_STATES.contains(usState))
        );
    }

    @Test
    public void testUsStatesIso2() {
        LoopsUtils.loop(
                Constants.US_STATES_CYCLES,
                Constants.MOCKS,
                mockNeat -> mockNeat.usStates().iso2().val(),
                usStateIso2 -> Assert.assertTrue(US_STATES_ISO2.contains(usStateIso2))
        );
    }

}
