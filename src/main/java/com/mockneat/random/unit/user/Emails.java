package com.mockneat.random.unit.user;

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
 OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import com.mockneat.random.Rand;
import com.mockneat.random.interfaces.RandUnit;
import com.mockneat.random.interfaces.RandUnitString;
import java.util.function.Supplier;
import static com.mockneat.types.enums.DictType.DOMAIN_EMAIL;

public class Emails implements RandUnitString {

    private Rand rand;

    public Emails(Rand rand) {
        this.rand = rand;
    }

    @Override
    public Supplier<String> supplier() {
        return () -> rand.users().val() + "@" + rand.dicts().type(DOMAIN_EMAIL).val();
    }

    public RandUnit<String> withDomains(String... domains) {
        Supplier<String> supp = () -> {
            String user = rand.users().val();
            String domain = rand.from(domains).val();
            return user + "@" + domain;
        };
        return () -> supp;
    }

    public RandUnit<String> withDomain(String domain) {
        return withDomains(domain);
    }
}
