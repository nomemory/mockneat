package net.andreinc.mockneat.unit.id;

import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;

import java.util.UUID;
import java.util.function.Supplier;

public class UUIDs extends MockUnitBase implements MockUnitString {

    public static UUIDs uuids() { return new UUIDs(); }

    public UUIDs() {
        super();
    }

    @Override
    public Supplier<String> supplier() {
       return ()->UUID.randomUUID().toString();
    }

}
