package com.mockneat.mock.unit.objects;

import com.mockneat.mock.MockNeat;
import com.mockneat.mock.interfaces.MockUnit;
import com.mockneat.mock.interfaces.MockUnitDouble;
import com.mockneat.mock.interfaces.MockValue;
import com.mockneat.types.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.mockneat.mock.interfaces.MockValueFactory.value;
import static com.mockneat.mock.utils.ValidationUtils.PROBABILITIES_SUM_BIGGER;
import static com.mockneat.mock.utils.ValidationUtils.PROBABILITIES_SUM_NOT_1;
import static org.apache.commons.lang3.Validate.isTrue;

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
        double lastVal = lastVal();
        double toAdd = lastVal + prob;
        isTrue(!(lastVal + prob > 1.0), PROBABILITIES_SUM_BIGGER);
        probs.add(Pair.of(toAdd, value(mock)));
        return this;
    }

    public Probabilities<T> add(Double prob, T obj) {
        double lastVal = lastVal();
        double toAdd = lastVal + prob;
        isTrue(!(lastVal + prob > 1.0), PROBABILITIES_SUM_BIGGER);
        probs.add(Pair.of(toAdd, value(obj)));
        return this;
    }

    private double lastVal() {
       return (probs.size()==0) ? 0.0 : probs.get(probs.size()-1).getFirst();
    }

    private T getMock() {
        isTrue(!(probs.get(probs.size()-1).getFirst()<1.0) ,PROBABILITIES_SUM_NOT_1);
        double rVal = mud.val();
        int i = 0;
        while(probs.get(i).getFirst() < rVal) { i++; }
        return (T) probs.get(i).getSecond().get();
    }
}