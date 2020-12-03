package net.andreinc.mockneat.unit.text;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.DictType;
import net.andreinc.mockneat.types.enums.WordsType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static net.andreinc.mockneat.utils.ValidationUtils.notEmptyOrNullValues;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public class Words extends MockUnitBase implements MockUnitString {

    private final MockUnitString adjectives1syll = mockNeat.dicts().type(DictType.EN_ADJECTIVE_1SYLL);
    private final MockUnitString adjectives2syll = mockNeat.dicts().type(DictType.EN_ADJECTIVE_2SYLL);
    private final MockUnitString adjectives3syll = mockNeat.dicts().type(DictType.EN_ADJECTIVE_3SYLL);
    private final MockUnitString adjectives4syll = mockNeat.dicts().type(DictType.EN_ADJECTIVE_4SYLL);
    private final List<MockUnitString> adjectives = unmodifiableList(asList(adjectives1syll, adjectives2syll, adjectives3syll, adjectives4syll));


    private final MockUnitString adverbs1syll = mockNeat.dicts().type(DictType.EN_ADVERB_1SYLL);
    private final MockUnitString adverbs2syll = mockNeat.dicts().type(DictType.EN_ADVERB_2SYLL);
    private final MockUnitString adverbs3syll = mockNeat.dicts().type(DictType.EN_ADVERB_3SYLL);
    private final MockUnitString adverbs4syll = mockNeat.dicts().type(DictType.EN_ADVERB_4SYLL);
    private final List<MockUnitString> adverbs = unmodifiableList(asList(adverbs1syll, adverbs2syll, adverbs3syll, adverbs4syll));

    private final MockUnitString nouns1syll = mockNeat.dicts().type(DictType.EN_NOUN_1SYLL);
    private final MockUnitString nouns2syll = mockNeat.dicts().type(DictType.EN_NOUN_2SYLL);
    private final MockUnitString nouns3syll = mockNeat.dicts().type(DictType.EN_NOUN_3SYLL);
    private final MockUnitString nouns4syll = mockNeat.dicts().type(DictType.EN_NOUN_4SYLL);
    private final List<MockUnitString> nouns = unmodifiableList(asList(nouns1syll, nouns2syll, nouns3syll, nouns4syll));

    private final MockUnitString verbs1syll = mockNeat.dicts().type(DictType.EN_VERB_1SYLL);
    private final MockUnitString verbs2syll = mockNeat.dicts().type(DictType.EN_VERB_2SYLL);
    private final MockUnitString verbs3syll = mockNeat.dicts().type(DictType.EN_VERB_3SYLL);
    private final MockUnitString verbs4syll = mockNeat.dicts().type(DictType.EN_VERB_4SYLL);
    private final List<MockUnitString> verbs = unmodifiableList(asList(verbs1syll, verbs2syll, verbs3syll, verbs4syll));

    private final Map<WordsType, MockUnitString> typesMapping = new ConcurrentHashMap<>();

    private final List<MockUnitString> all;

    public static Words words() {
        return MockNeat.threadLocal().words();
    }

    protected Words() {
        this(MockNeat.threadLocal());
    }

    public Words(MockNeat mockNeat) {
        super(mockNeat);

        ArrayList<MockUnitString> allWords = new ArrayList<>();

        allWords.addAll(adjectives);
        allWords.addAll(adverbs);
        allWords.addAll(nouns);
        allWords.addAll(verbs);

        this.all = unmodifiableList(allWords);

        typesMapping.put(WordsType.ADJECTIVES, adjectives());
        typesMapping.put(WordsType.ADJECTIVES_1SYLL, adjectives1syll());
        typesMapping.put(WordsType.ADJECTIVES_2SYLL, adjectives2syll());
        typesMapping.put(WordsType.ADJECTIVES_3SYLL, adjectives3syll());
        typesMapping.put(WordsType.ADJECTIVES_4SYLL, adjectives4syll());

        typesMapping.put(WordsType.ADVERBS, adverbs());
        typesMapping.put(WordsType.ADVERBS_1SYLL, adverbs1syll());
        typesMapping.put(WordsType.ADVERBS_2SYLL, adverbs2syll());
        typesMapping.put(WordsType.ADVERBS_3SYLL, adverbs3syll());
        typesMapping.put(WordsType.ADVERBS_4SYLL, adverbs4syll());

        typesMapping.put(WordsType.NOUNS, nouns());
        typesMapping.put(WordsType.NOUNS_1SYL, nouns1syll());
        typesMapping.put(WordsType.NOUNS_2SYL, nouns2syll());
        typesMapping.put(WordsType.NOUNS_3SYL, nouns3syll());
        typesMapping.put(WordsType.NOUNS_4SYL, nouns4syll());

        typesMapping.put(WordsType.VERBS, verbs());
        typesMapping.put(WordsType.VERBS_1SYLL, verbs1syll());
        typesMapping.put(WordsType.VERBS_2SYLL, verbs2syll());
        typesMapping.put(WordsType.VERBS_3SYLL, verbs3syll());
        typesMapping.put(WordsType.VERBS_4SYLL, verbs4syll());
    }

    @Override
    public Supplier<String> supplier() {
        return mockNeat.from(all).val().supplier();
    }

    public MockUnitString type(WordsType type) {
        notNull(type, "type");
        return typesMapping.get(type);
    }

    public MockUnitString types(WordsType... types) {
        notEmptyOrNullValues(types, "types");
        return () -> {
            WordsType type = mockNeat.from(types).get();
            return typesMapping.get(type).supplier();
        };
    }

    public MockUnitString adjectives() {
        return () -> mockNeat.from(adjectives).val().supplier();
    }

    public MockUnitString adjectives1syll() { return adjectives1syll; }

    public MockUnitString adjectives2syll() { return adjectives2syll; }

    public MockUnitString adjectives3syll() { return adjectives3syll; }

    public MockUnitString adjectives4syll() { return adjectives4syll; }

    public MockUnitString adverbs() {
        return () -> mockNeat.from(adverbs).val().supplier();
    }

    public MockUnitString adverbs1syll() { return adverbs1syll; }

    public MockUnitString adverbs2syll() { return adverbs2syll; }

    public MockUnitString adverbs3syll() { return adverbs3syll; }

    public MockUnitString adverbs4syll() { return adverbs4syll; }

    public MockUnitString nouns() {
        return () -> mockNeat.from(nouns).val().supplier();
    }

    public MockUnitString nouns1syll() { return nouns1syll; }

    public MockUnitString nouns2syll() { return nouns2syll; }

    public MockUnitString nouns3syll() { return nouns3syll; }

    public MockUnitString nouns4syll() { return nouns4syll; }

    public MockUnitString verbs() {
        return () -> mockNeat.from(verbs).val().supplier();
    }

    public MockUnitString verbs1syll() { return verbs1syll; }

    public MockUnitString verbs2syll() { return verbs2syll; }

    public MockUnitString verbs3syll() { return verbs3syll; }

    public MockUnitString verbs4syll() { return verbs4syll; }
}
