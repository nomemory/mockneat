package net.andreinc.mockneat.unit.text;

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
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.DictType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

//TODO Document and test
public class Words extends MockUnitBase implements MockUnitString {

    private final List<MockUnitString> adjectives =
            unmodifiableList(asList(
                    mockNeat.dicts().type(DictType.EN_ADJECTIVE_1SYLL),
                    mockNeat.dicts().type(DictType.EN_ADJECTIVE_2SYLL),
                    mockNeat.dicts().type(DictType.EN_ADJECTIVE_3SYLL),
                    mockNeat.dicts().type(DictType.EN_ADJECTIVE_4SYLL)
            ));

    private final List<MockUnitString> adverbs =
            unmodifiableList(asList(
                    mockNeat.dicts().type(DictType.EN_ADVERB_1SYLL),
                    mockNeat.dicts().type(DictType.EN_ADVERB_2SYLL),
                    mockNeat.dicts().type(DictType.EN_ADVERB_3SYLL),
                    mockNeat.dicts().type(DictType.EN_ADVERB_4SYLL)
            ));

    private final List<MockUnitString> nouns =
            unmodifiableList(asList(
                    mockNeat.dicts().type(DictType.EN_NOUN_1SYLL),
                    mockNeat.dicts().type(DictType.EN_NOUN_2SYLL),
                    mockNeat.dicts().type(DictType.EN_NOUN_3SYLL),
                    mockNeat.dicts().type(DictType.EN_NOUN_4SYLL)
            ));

    private final List<MockUnitString> verbs =
            unmodifiableList(asList(
                    mockNeat.dicts().type(DictType.EN_VERB_1SYLL),
                    mockNeat.dicts().type(DictType.EN_VERB_2SYLL),
                    mockNeat.dicts().type(DictType.EN_VERB_3SYLL),
                    mockNeat.dicts().type(DictType.EN_VERB_4SYLL)
            ));

    private final List<MockUnitString> all;

    public Words(MockNeat mockNeat) {
        super(mockNeat);

        ArrayList<MockUnitString> all = new ArrayList<>();

        all.addAll(adjectives);
        all.addAll(adverbs);
        all.addAll(nouns);
        all.addAll(verbs);

        this.all = unmodifiableList(all);
    }

    @Override
    public Supplier<String> supplier() {
        return mockNeat.from(all).val().supplier();
    }

    public MockUnitString adjectives() {
        return () -> mockNeat.from(adjectives).val().supplier();
    }

    public MockUnitString adverbs() {
        return () -> mockNeat.from(adverbs).val().supplier();
    }

    public MockUnitString nouns() {
        return () -> mockNeat.from(nouns).val().supplier();
    }

    public MockUnitString verbs() {
        return () -> mockNeat.from(verbs).val().supplier();
    }
}
