package com.mockneat.generator.units;

import com.mockneat.generator.MockGenerator;

/**
 * Created by andreinicolinciobanu on 23/01/2017.
 */
public class ReferenceUnit implements MockGeneratorUnit {

    private MockGenerator mockGenerator;

    public static MockGeneratorUnit from(MockGenerator mockGenerator) {
        return new ReferenceUnit(mockGenerator);
    }

    protected ReferenceUnit(MockGenerator mockGenerator) {
        this.mockGenerator = mockGenerator;
    }

    @Override
    public Object next() {
        return mockGenerator.newInstance().orElse(null);
    }
}
