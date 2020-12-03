package net.andreinc.mockneat.abstraction;

import net.andreinc.mockneat.types.enums.DictType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static net.andreinc.mockneat.Constants.M;
import static net.andreinc.mockneat.types.enums.DictType.CITIES_US;

public class MockUnitDictsTest {

    @Test(expected = NullPointerException.class)
    public void dictTypeNull() {
        M.dicts().type(null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void dictTypesEmpty() {
        DictType[] dictTypes = {};
        M.dicts().types(dictTypes).val();
    }

    @Test(expected = NullPointerException.class)
    public void dictDataNull() {
        M.dicts().data(null);
    }

    @Test
    public void dictData() {
        List<String> data = M.dicts().data(DictType.COUNTRY_ISO_CODE_2);
        data.forEach((code) -> Assert.assertEquals(2, code.length()));
    }

    @Test
    public void correctFileName() {
        Assert.assertEquals("cities/cities-us", CITIES_US.getFileName());
    }
}
