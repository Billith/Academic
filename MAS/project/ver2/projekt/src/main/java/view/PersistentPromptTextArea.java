package view;

import javafx.scene.control.TextArea;

// https://stackoverflow.com/questions/25125563/clear-prompt-text-in-javafx-textfield-only-when-user-starts-typing
public class PersistentPromptTextArea extends TextArea {
    PersistentPromptTextArea(String text, String prompt) {
        super(text);
        setPromptText(prompt);
        getStyleClass().add("persistent-prompt");
        refreshPromptVisibility();

        textProperty().addListener(observable -> refreshPromptVisibility());
    }

    private void refreshPromptVisibility() {
        final String text = getText();
        if (isEmptyString(text)) {
            getStyleClass().remove("no-prompt");
        } else {
            if (!getStyleClass().contains("no-prompt")) {
                getStyleClass().add("no-prompt");
            }
        }
    }

    private boolean isEmptyString(String text) {
        return text == null || text.isEmpty();
    }
}