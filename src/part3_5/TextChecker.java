package part3_5;

import part3_5.analyzers.Label;
import part3_5.analyzers.impl.NegativeTextAnalyzer;
import part3_5.analyzers.impl.SpamAnalyzer;
import part3_5.analyzers.TextAnalyzer;
import part3_5.analyzers.impl.TooLongTextAnalyzer;

public class TextChecker {
    public static void main(String[] args) {
        TextAnalyzer[] analyzers = {
                new SpamAnalyzer(new String[]{"buy"}),
                new NegativeTextAnalyzer(),
                new TooLongTextAnalyzer(20)
        };
        String text1 = "Hello! :( buy something please";
        String text2 = "Hello! :( something please";
        String text3 = "Hello! How is it going?";
        String text4 = "Hello!";

        System.out.println(checkLabels(analyzers, text1));
        System.out.println(checkLabels(analyzers, text2));
        System.out.println(checkLabels(analyzers, text3));
        System.out.println(checkLabels(analyzers, text4));
    }

    public static Label checkLabels(TextAnalyzer[] analyzers, String text) {
        for (TextAnalyzer ta : analyzers) {
            Label label = ta.processText(text);
            if (label != Label.OK) {
                return label;
            }
        }
        return Label.OK;
    }
}
