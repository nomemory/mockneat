package com.mockneat.mock.unit.companies;

import com.mockneat.mock.MockNeat;
import com.mockneat.mock.interfaces.MockUnitString;

import java.util.function.Supplier;

import static com.mockneat.types.enums.DictType.DEPARTMENTS;

/**
 * Created by andreinicolinciobanu on 23/02/2017.
 */
public class Departments implements MockUnitString {

    private MockNeat mockNeat;

    public Departments(MockNeat mockNeat) {
        this.mockNeat = mockNeat;
    }

    @Override
    public Supplier<String> supplier() {
        return mockNeat.dicts().type(DEPARTMENTS)::val;
    }
}
