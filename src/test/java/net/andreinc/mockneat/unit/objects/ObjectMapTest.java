package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.utils.LoopsUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static net.andreinc.mockneat.Constants.MOCKS;
import static net.andreinc.mockneat.Constants.OBJECT_MAP_CYCLES;

public class ObjectMapTest {

    @Test
    public void constantMockUnitFieldValues() {
        LoopsUtils.loop(
                OBJECT_MAP_CYCLES,
                MOCKS,
                m -> m.objectMap().put("field1", "const1")
                                  .put("field2", "const2")
                                  .put("field3", m.objectMap().put("subField1", "subValue1")
                                                                    .put("subField2", "subValue2"))
                                  .put("num1", 1)
                                  .put("num2", 1l)
                                  .get(),
                val -> {
                    Assert.assertEquals(val.get("field1"), "const1");
                    Assert.assertEquals(val.get("field2"), "const2");
                    Assert.assertTrue((val.get("field3") instanceof Map));

                    Map subMap = (Map) val.get("field3");
                    Assert.assertEquals(subMap.get("subField1"), "subValue1");
                    Assert.assertEquals(subMap.get("subField2"), "subValue2");

                    Assert.assertEquals(val.get("num1"), 1);
                    Assert.assertEquals(val.get("num2"), 1l);
                }
        );
    }

}
