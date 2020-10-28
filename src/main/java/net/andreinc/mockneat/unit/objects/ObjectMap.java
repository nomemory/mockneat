package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.unit.address.Cities;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.ValidationUtils.notEmpty;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public class ObjectMap extends MockUnitBase implements MockUnit<Map<String, Object>> {

    public static ObjectMap objectMap() {
        return MockNeat.threadLocal().objectMap();
    }

    private Map<String, MockUnit> map = new LinkedHashMap<>();

    public ObjectMap(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<Map<String,Object>> supplier() {
        return () -> traverseObject(this);
    }

    public ObjectMap put(String value, MockUnit unit) {
        notEmpty(value, "value");
        notNull(unit, "unit");
        map.put(value, unit);
        return this;
    }

    public ObjectMap put(String value, String unit) {
        return put(value, mockNeat.constant(unit));
    }

    public ObjectMap put(String value, Character unit) {
        return put(value, mockNeat.constant(unit));
    }

    public ObjectMap put(String value, Integer unit) {
        return put(value, mockNeat.constant(unit));
    }

    public ObjectMap put(String value, Long unit) {
        return put(value, mockNeat.constant(unit));
    }

    public ObjectMap put(String value, Double unit) {
        return put(value, mockNeat.constant(unit));
    }

    protected static Map<String, Object> traverseObject(ObjectMap ojMap) {
        Map<String, MockUnit> map = ojMap.map;
        Map<String, Object> result = new HashMap<>();
        for(String key : map.keySet()) {
            MockUnit value = map.get(key);
            if (value instanceof ObjectMap) {
                result.put(key, traverseObject((ObjectMap) value));
            }
            else {
                result.put(key, value.get());
            }
        }
        return result;
    }
}
