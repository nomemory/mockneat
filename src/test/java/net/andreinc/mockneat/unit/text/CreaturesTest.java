package net.andreinc.mockneat.unit.text;

import net.andreinc.mockneat.Constants;
import net.andreinc.mockneat.types.enums.DictType;
import net.andreinc.mockneat.utils.file.FileManager;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class CreaturesTest {

    private final Set<String> allCreatures = new HashSet<>(FileManager.getInstance().getLines(DictType.CREATURES));

    @Test
    public void testCreatures() {
        loop(
                Constants.CREATURES_CYCLES,
                Constants.MOCKS,
                m -> m.creatures().get(),
                c -> assertTrue(allCreatures.contains(c))
        );
    }
}
