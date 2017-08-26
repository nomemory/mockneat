package net.andreinc.mockneat.unit.text;

import net.andreinc.mockneat.Constants;
import net.andreinc.mockneat.types.enums.DictType;
import net.andreinc.mockneat.utils.LoopsUtils;
import net.andreinc.mockneat.utils.file.FileManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class WordsTest {

    public static final FileManager FILE_MANAGER = FileManager.getInstance();

    private final static Set<String> ADVERBS = new HashSet<>();
    private final static Set<String> ADJECTIVES = new HashSet<>();
    private final static Set<String> NOUNS = new HashSet<>();
    private final static Set<String> VERBS = new HashSet<>();

    static {

        ADVERBS.addAll(FILE_MANAGER.getLines(DictType.EN_ADVERB_1SYLL));
        ADVERBS.addAll(FILE_MANAGER.getLines(DictType.EN_ADVERB_2SYLL));
        ADVERBS.addAll(FILE_MANAGER.getLines(DictType.EN_ADVERB_3SYLL));
        ADVERBS.addAll(FILE_MANAGER.getLines(DictType.EN_ADVERB_4SYLL));

        ADJECTIVES.addAll(FILE_MANAGER.getLines(DictType.EN_ADJECTIVE_1SYLL));
        ADJECTIVES.addAll(FILE_MANAGER.getLines(DictType.EN_ADJECTIVE_2SYLL));
        ADJECTIVES.addAll(FILE_MANAGER.getLines(DictType.EN_ADJECTIVE_3SYLL));
        ADJECTIVES.addAll(FILE_MANAGER.getLines(DictType.EN_ADJECTIVE_4SYLL));

        NOUNS.addAll(FILE_MANAGER.getLines(DictType.EN_NOUN_1SYLL));
        NOUNS.addAll(FILE_MANAGER.getLines(DictType.EN_NOUN_2SYLL));
        NOUNS.addAll(FILE_MANAGER.getLines(DictType.EN_NOUN_3SYLL));
        NOUNS.addAll(FILE_MANAGER.getLines(DictType.EN_NOUN_4SYLL));

        VERBS.addAll(FILE_MANAGER.getLines(DictType.EN_VERB_1SYLL));
        VERBS.addAll(FILE_MANAGER.getLines(DictType.EN_VERB_2SYLL));
        VERBS.addAll(FILE_MANAGER.getLines(DictType.EN_VERB_3SYLL));
        VERBS.addAll(FILE_MANAGER.getLines(DictType.EN_VERB_4SYLL));
    }

    @Test
    public void testAdverbs() {
        LoopsUtils.loop(
                Constants.WORDS_CYCLES,
                Constants.MOCKS,
                mockNeat -> mockNeat.words().adverbs().val(),
                adverb -> Assert.assertTrue(ADVERBS.contains(adverb))
        );
    }

    @Test
    public void testAdjectives() {
        LoopsUtils.loop(
                Constants.WORDS_CYCLES,
                Constants.MOCKS,
                mockNeat -> mockNeat.words().adjectives().val(),
                adjective -> Assert.assertTrue(ADJECTIVES.contains(adjective))
        );
    }

    @Test
    public void testNouns() {
        LoopsUtils.loop(
                Constants.WORDS_CYCLES,
                Constants.MOCKS,
                mockNeat -> mockNeat.words().nouns().val(),
                noun -> Assert.assertTrue(NOUNS.contains(noun))
        );
    }

    @Test
    public void testVerbs() {
        LoopsUtils.loop(
                Constants.WORDS_CYCLES,
                Constants.MOCKS,
                mockNeat -> mockNeat.words().verbs().val(),
                verb -> Assert.assertTrue(VERBS.contains(verb))
        );
    }

    @Test
    public void testAll() {
        LoopsUtils.loop(
                Constants.WORDS_CYCLES,
                Constants.MOCKS,
                mockNeat -> mockNeat.words().val(),
                word -> Assert.assertTrue(
                        VERBS.contains(word) ||
                                NOUNS.contains(word) ||
                                ADJECTIVES.contains(word) ||
                                ADVERBS.contains(word)
                )
        );
    }
}
