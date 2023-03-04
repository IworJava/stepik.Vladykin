package part3_5.analyzers.impl;

import part3_5.analyzers.Label;

import java.util.Arrays;

public class SpamAnalyzer extends KeywordAnalyzer {
    private final String[] keywords;

    public SpamAnalyzer(String[] keywords) {
        this.keywords = keywords;
    }

    @Override
    public String[] getKeywords() {
        return Arrays.copyOf(keywords, keywords.length);
    }

    @Override
    public Label getLabel() {
        return Label.SPAM;
    }
}
