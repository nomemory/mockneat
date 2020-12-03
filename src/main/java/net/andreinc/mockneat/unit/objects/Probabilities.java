package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitDouble;
import net.andreinc.mockneat.abstraction.MockValue;
import net.andreinc.mockneat.types.Pair;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static java.math.BigDecimal.valueOf;
import static net.andreinc.mockneat.abstraction.MockConstValue.constant;
import static net.andreinc.mockneat.abstraction.MockUnitValue.unit;
import static net.andreinc.mockneat.utils.ValidationUtils.*;

public class Probabilities<T> extends MockUnitBase implements MockUnit<T> {

    private final List<Pair<BigDecimal, MockValue<T>>> probabilities = new ArrayList<>();
    private final MockUnitDouble mud;
    private final Class<T> cls;

    public static <T> Probabilities<T> probabilities(Class<T> cls) {
        return MockNeat.threadLocal().probabilites(cls);
    }

    public Probabilities(MockNeat mockNeat, Class<T> cls) {
        super(mockNeat);
        this.mud = mockNeat.doubles().range(0, 1.0);
        this.cls = cls;
    }

    @Override
    public Supplier<T> supplier() {
        return this::getMock;
    }

    public Probabilities<T> add(Double prob, MockUnit<T> mock) {
        notNull(prob, "prob");
        isTrue(prob.compareTo(0.0)>0, PROBABILITY_NOT_NEGATIVE, "prob", prob);
        BigDecimal probInternal = valueOf(prob);
        BigDecimal lastVal = lastVal();
        BigDecimal toAdd = lastVal.add(probInternal);
        isTrue(toAdd.compareTo(valueOf(1.0)) <= 0, PROBABILITIES_SUM_BIGGER);
        probabilities.add(Pair.of(toAdd, unit(mock)));
        return this;
    }

    public Probabilities<T> add(Double prob, T obj) {
        notNull(prob, "prob");
        isTrue(prob.compareTo(0.0)>0, PROBABILITY_NOT_NEGATIVE, "prob", prob);
        BigDecimal probInternal = valueOf(prob);
        BigDecimal lastVal = lastVal();
        BigDecimal toAdd = lastVal.add(probInternal);
        isTrue((toAdd.compareTo(valueOf(1.0)) <= 0), PROBABILITIES_SUM_BIGGER);
        probabilities.add(Pair.of(toAdd, constant(obj)));
        return this;
    }

    private BigDecimal lastVal() {
        return (probabilities.isEmpty()) ? valueOf(0.0) : probabilities.get(probabilities.size()-1).getFirst();
    }

    private T getMock() {
        isTrue((probabilities.get(probabilities.size()-1).getFirst().compareTo(valueOf(1.0))) == 0, PROBABILITIES_SUM_NOT_1);
        BigDecimal rVal = mud.map(BigDecimal::valueOf).val();
        int i = 0;
        while(probabilities.get(i).getFirst().compareTo(rVal) < 0) {
            i++;
        }
        return cls.cast(probabilities.get(i).getSecond().get());
    }
}