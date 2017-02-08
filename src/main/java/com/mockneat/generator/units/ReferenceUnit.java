package com.mockneat.generator.units;

import com.mockneat.generator.MockGenerator;

/**
 * Created by andreinicolinciobanu on 23/01/2017.
 */
public class ReferenceUnit implements GeneratorUnit {

    private MockGenerator mockGenerator;

    protected ReferenceUnit(MockGenerator mockGenerator) {
        this.mockGenerator = mockGenerator;
    }

    public static GeneratorUnit from(MockGenerator mockGenerator) {
        return new ReferenceUnit(mockGenerator);
    }

    @Override
    public Object next() {
        return mockGenerator.newInstance().orElse(null);
    }
}
