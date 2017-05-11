package net.andreinc.mockneat.unit.objects;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.interfaces.MockUnit;
import net.andreinc.mockneat.interfaces.MockUnitDouble;
import net.andreinc.mockneat.interfaces.MockValue;
import net.andreinc.mockneat.types.Pair;
import net.andreinc.mockneat.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static net.andreinc.mockneat.interfaces.MockConstValue.constant;
import static net.andreinc.mockneat.interfaces.MockUnitValue.unit;
import static net.andreinc.mockneat.utils.ValidationUtils.*;

public class Probabilities<T> implements MockUnit<T> {

    private final MockNeat mockNeat;
    private final Class<T> cls;
    private final List<Pair<Double, MockValue>> probs = new ArrayList<>();
    private final MockUnitDouble mud;

    public Probabilities(MockNeat mockNeat, Class<T> cls) {
        this.cls = cls;
        this.mockNeat = mockNeat;
        this.mud = mockNeat.doubles().range(0, 1.0);
    }

    @Override
    public Supplier<T> supplier() {
        return () -> getMock();
    }

    public Probabilities<T> add(Double prob, MockUnit<T> mock) {
        notNull(prob, "prob");
        isTrue(prob.compareTo(0.0)>0, PROBABILITY_NOT_NEGATIVE, "prob", prob);
        double lastVal = lastVal();
        double toAdd = lastVal + prob;
        isTrue(!(lastVal + prob > 1.0), PROBABILITIES_SUM_BIGGER);
        probs.add(Pair.of(toAdd, unit(mock)));
        return this;
    }

    public Probabilities<T> add(Double prob, T obj) {
        notNull(prob, "prob");
        isTrue(prob.compareTo(0.0)>0, PROBABILITY_NOT_NEGATIVE, "prob", prob);
        double lastVal = lastVal();
        double toAdd = lastVal + prob;
        isTrue(!(lastVal + prob > 1.0), PROBABILITIES_SUM_BIGGER);
        probs.add(Pair.of(toAdd, constant(obj)));
        return this;
    }

    private double lastVal() {
       return (probs.size()==0) ? 0.0 : probs.get(probs.size()-1).getFirst();
    }

    private T getMock() {
        isTrue(!(probs.get(probs.size()-1).getFirst()<1.0) , ValidationUtils.PROBABILITIES_SUM_NOT_1);
        double rVal = mud.val();
        int i = 0;
        while(probs.get(i).getFirst() < rVal) { i++; }
        return (T) probs.get(i).getSecond().get();
    }
}