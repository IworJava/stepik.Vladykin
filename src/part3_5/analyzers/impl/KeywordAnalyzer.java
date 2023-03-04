package part3_5.analyzers.impl;

import part3_5.analyzers.Label;
import part3_5.analyzers.TextAnalyzer;

public abstract class KeywordAnalyzer implements TextAnalyzer {
    protected abstract String[] getKeywords();
    protected abstract Label getLabel();

    @Override
    public Label processText(String text) {
        for (String keyWord : getKeywords()) {
            if (text.contains(keyWord)) {
                return getLabel();
            }
        }
        return Label.OK;
    }
}
