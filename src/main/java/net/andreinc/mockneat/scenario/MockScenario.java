package net.andreinc.mockneat.scenario;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitValue;


import java.util.HashMap;
import java.util.Map;

import static net.andreinc.mockneat.utils.ValidationUtils.RETURN_VALUE_MUST_MATCH_THE_TYPE;
import static net.andreinc.mockneat.utils.ValidationUtils.isTrue;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public class MockScenario {

    private Map<String, Object> map = new HashMap();

    public <T> MockScenario add(String key, Class<T> type, MockUnitValue mockUnitValue) {

        notNull(key, "key");
        notNull(type, "type");
        notNull(mockUnitValue, "mockUnitValue");

        Object val = mockUnitValue.get();

        isTrue(type.isInstance(val), RETURN_VALUE_MUST_MATCH_THE_TYPE, "value", val, "type", type);

        if (!map.containsKey(key)) {
            map.put(key, mockUnitValue.get());
        }

        return this;
    }

    public <T> T get(String key, Class<T> type) {
        notNull(key, "key");
        notNull(type, "type");

        Object val = map.get(key);

        isTrue(type.isInstance(val), RETURN_VALUE_MUST_MATCH_THE_TYPE, "value", val, "type", type);

        return (T) val;
    }

    public static void main(String[] args) {

        MockNeat mock = MockNeat.threadLocal();
        MockScenario scenario = new MockScenario();

    }
}
