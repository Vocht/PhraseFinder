import phrase.Phrase;

public class Main {
    public static void main(String[] args) {
        var iterations = 0;
        var phrase = new Phrase("ThisIsAVeryLongStringThatWouldTakeAFewIterationsToMatch");

        do {
            System.out.println(phrase.getPhrase());
            phrase = phrase.reproduce();
            iterations++;
        } while(!phrase.isDone());

        System.out.println(phrase.getPhrase());
        System.out.println(iterations + " iterations");
    }
}
