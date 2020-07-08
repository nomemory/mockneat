package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.types.enums.DictType;
import net.andreinc.mockneat.types.enums.NameType;
import net.andreinc.mockneat.utils.LoopsUtils;
import net.andreinc.mockneat.utils.NamesCheckUtils;
import net.andreinc.mockneat.utils.file.FileManager;
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

    @Test
    public void mockUnitFieldValues() {
        LoopsUtils.loop(
                OBJECT_MAP_CYCLES,
                MOCKS,
                m -> m.objectMap().put("firstName", m.names().first())
                                  .put("lastName",  m.names().last())
                                  .put("visits",    m.cities().capitals().array(10))
                                  .put("sub",       m.objectMap().put("anInt", m.ints().range(0, 10))
                                                                        .put("aDouble", m.doubles().range(0.0d, 0.1d))
                                  )
                                  .get(),
                val -> {
                    Assert.assertTrue(val.get("sub") instanceof Map);
                    Map subMap = (Map) val.get("sub");

                    Assert.assertTrue(val.get("firstName") instanceof String);
                    String firstName = (String) val.get("firstName");
                    Assert.assertNotNull(firstName);
                    Assert.assertTrue(NamesCheckUtils.isNameOfType(firstName, NameType.FIRST_NAME));

                    Assert.assertTrue(val.get("lastName") instanceof String);
                    String lastName = (String) val.get("lastName");
                    Assert.assertNotNull(lastName);
                    Assert.assertTrue(NamesCheckUtils.isNameOfType(lastName, NameType.LAST_NAME));

                    String[] visits = (String[]) val.get("visits");
                    Assert.assertEquals(visits.length, 10);
                    for(String visit : visits) {
                        Assert.assertTrue(FileManager
                                                .getInstance()
                                                .getUniqueLines(DictType.CITIES_CAPITALS)
                                                .contains(visit));
                    }

                    Integer anInt = (Integer) subMap.get("anInt");
                    Assert.assertTrue(anInt < 10 && anInt >= 0);

                    Double aDouble = (Double) subMap.get("aDouble");
                    Assert.assertTrue(aDouble < 0.1d && aDouble >= 0.0d);
                }
        );
    }
}
