package phrase;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class Phrase {
    private final String compareString;
    private final Map<Integer, Character> charMap;
    private double fitness = -1;
    private String phrase;

    public Phrase(String compareString) {
        if (compareString.isEmpty()) throw new InvalidParameterException("Compare String cannot be empty");
        this.phrase = RandomStringUtils.randomAlphanumeric(compareString.length());
        this.compareString = compareString;
        charMap = new HashMap<>();
        calculateFitness();
    }

    public double getFitness() {
        return fitness;
    }

    public String getPhrase() {
        return phrase;
    }

    public Phrase reproduce() {
        final var phrase = new Phrase(compareString);
        var builder = new StringBuilder(phrase.getPhrase());
        charMap.forEach(builder::setCharAt);
        phrase.update(builder.toString());

        return phrase;
    }

    public boolean isDone() {
        return compareString.equals(phrase);
    }

    public String getCompareString() {
        return compareString;
    }

    // Private
    public void update(String phrase) {
        this.phrase = phrase;
        calculateFitness();
    }

    private void calculateFitness() {
        final var phraseChars = phrase.toCharArray();
        final var compareChars = compareString.toCharArray();

        var matching = 0;

        for(int i = 0; i < compareString.length(); i++) {
            if (phraseChars[i] == compareChars[i]) {
                charMap.put(i, phraseChars[i]);
                matching++;
            }
        }

        this.fitness = matching;
    }
}
