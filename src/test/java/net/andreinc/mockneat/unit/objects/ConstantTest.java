package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.abstraction.MockUnit;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static net.andreinc.mockneat.Constants.M;

/**
 * Copyright 2020, Andrei N. Ciobanu

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

public class ConstantTest {

    @Test
    public void testConstant() {
        MockUnit<Integer> ints = M.constant(3);

        int x = ints.get();
        int y = ints.get();

        Assert.assertEquals(x, y);
    }

    @Test
    public void testConstant_arr() {
        MockUnit<String> aAAAs = M.constant("AAA");

        List<String> l1 = aAAAs.list(10).get();
        List<String> l2 = aAAAs.list(10).get();

        Assert.assertTrue(CollectionUtils.isEqualCollection(l1, l2));
    }
}
