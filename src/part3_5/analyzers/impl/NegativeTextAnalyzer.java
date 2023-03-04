package part3_5.analyzers.impl;

import part3_5.analyzers.Label;

import java.util.Arrays;

public class NegativeTextAnalyzer extends KeywordAnalyzer {
    private final String[] keywords;

    public NegativeTextAnalyzer() {
        keywords = new String[]{":(", "=(", ":|"};
    }

    @Override
    public String[] getKeywords() {
        return Arrays.copyOf(keywords, keywords.length);
    }

    @Override
    public Label getLabel() {
        return Label.NEGATIVE_TEXT;
    }
}
