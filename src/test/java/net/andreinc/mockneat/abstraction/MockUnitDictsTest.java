package net.andreinc.mockneat.abstraction;

import net.andreinc.mockneat.types.enums.DictType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static net.andreinc.mockneat.Constants.M;

public class MockUnitDictsTest {

    @Test(expected = NullPointerException.class)
    public void dictTypeNull() {
        DictType ndt = null;
        M.dicts().type(ndt).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void dictTypesEmpty() {
        DictType[] dictTypes = {};
        M.dicts().types(dictTypes).val();
    }

    @Test(expected = NullPointerException.class)
    public void dictDataNull() {
        DictType ndt = null;
        M.dicts().data(ndt);
    }

    @Test
    public void dictData() {
        List<String> data = M.dicts().data(DictType.COUNTRY_ISO_CODE_2);
        data.forEach((code) -> Assert.assertEquals(code.length(), 2));
    }
}
