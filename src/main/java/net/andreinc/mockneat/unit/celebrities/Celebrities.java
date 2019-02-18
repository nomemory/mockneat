package net.andreinc.mockneat.unit.celebrities;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.unit.user.RockStars;
import net.andreinc.mockneat.unit.user.UKPrimeMinisters;
import net.andreinc.mockneat.unit.user.USPresidents;

public class Celebrities extends MockUnitBase {

    public Celebrities(MockNeat mockNeat) {
        super(mockNeat);
    }

    public RockStars rockStars() {
        return mockNeat.rockStars();
    }

    public UKPrimeMinisters ukPrimeMinisters() {
        return mockNeat.ukPrimeMinisters();
    }

    public USPresidents usPresidents() {
        return mockNeat.usPresidents();
    }
}
