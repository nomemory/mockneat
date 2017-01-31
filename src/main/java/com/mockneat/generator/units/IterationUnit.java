package com.mockneat.generator.units;

import java.util.Iterator;

/**
 * Created by andreinicolinciobanu on 23/01/2017.
 */
public class IterationUnit implements MockGeneratorUnit {

    private Iterator<?> iterator;

    public static IterationUnit from(Iterator iterator) {
        return new IterationUnit(iterator);
    }

    protected IterationUnit(Iterator<?> iterator) {
        this.iterator = iterator;
    }

    @Override
    public Object next() {
        if (this.iterator.hasNext())
            return this.iterator.next();
        else return null;
    }
}
