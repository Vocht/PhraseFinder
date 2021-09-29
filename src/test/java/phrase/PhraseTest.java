package phrase;

import org.junit.jupiter.api.Test;
import phrase.Phrase;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

public class PhraseTest {

    @Test
    void phrase_shouldHaveAPhraseString() {
        var phrase = getPhrase();
        assertFalse(phrase.getPhrase().isEmpty());
    }

    @Test
    void phrase_shouldBeRandomlyGenerated() {
        var phrase1 = getPhrase();
        var phrase2 = getPhrase();
        assertNotEquals(phrase1.getPhrase(), phrase2.getPhrase());
    }

    @Test
    void phrase_shouldCalculateFitness() {
        var phrase = getPhrase();
        assertNotEquals(-1, phrase.getFitness());
    }

    @Test
    void phrase_shouldRandomizePhraseAtInit() {
        var phrase = getPhrase();
        var phrase2 = getPhrase();
        assertNotEquals(phrase.getPhrase(), phrase2.getPhrase());
    }

    @Test
    void phrase_shouldGenerateARandomStringOfTheSameLengthAsTheCompareString() {
        var compareString = "A String";
        var phrase = getPhrase(compareString);
        assertEquals(phrase.getPhrase().length(), compareString.length());
    }

    @Test
    void phrase_shouldThrowInvalidParameterExceptionWhenCompareStringIsEmpty() {
        assertThrows(InvalidParameterException.class, () -> getPhrase(""));
    }

    @Test
    void phrase_shouldBeGeneratedOnlyOnce() {
        var phrase = getPhrase();
        assertEquals(phrase.getPhrase(), phrase.getPhrase());
    }

    @Test
    void phrase_shouldReproduce() {
        var phrase = getPhrase();
        var child = phrase.reproduce();
        assertNotNull(child);
    }

    @Test
    void phrase_shouldMutateInReproduction() {
        var phrase = getPhrase();
        var child = phrase.reproduce();
        assertNotEquals(phrase.getPhrase(), child.getPhrase());
    }

    @Test
    void phrase_shouldProduceEqualOrImprovedChild() {
        var phrase = getPhrase();
        var child = phrase.reproduce();
        assertTrue(phrase.getFitness() <= child.getFitness());
    }

    @Test
    void phrase_shouldIndicateWhenDone() {
        var phrase = getPhrase();
        assertFalse(phrase.isDone());
        while(!phrase.isDone()) {
            phrase = phrase.reproduce();
        }
        assertTrue(phrase.isDone());
    }

    // Test Utils
    private Phrase getPhrase(String compareString) {
        return new Phrase(compareString);
    }

    private Phrase getPhrase() {
        return getPhrase("Random");
    }
}
