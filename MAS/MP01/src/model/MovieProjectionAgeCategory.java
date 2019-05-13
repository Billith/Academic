package model;

import java.util.Calendar;

public class MovieProjectionAgeCategory extends ObjectPlus {
    String name;
    int minimalAge;
    int minimalBornYear;

    public MovieProjectionAgeCategory(String name, int minimalAge) {
        this.name = name;
        this.minimalAge = minimalAge;
        this.minimalBornYear = Calendar.getInstance().get(Calendar.YEAR) - minimalAge;
    }

    private int getMinimalBornYear() {
        return this.minimalBornYear;
    }

    public String toString() {
        return String.format("[ %s, name=%s, minimalAge=%s, minimalBornYear=%s ]",
                this.getClass().toString().replace(' ', '='),
                this.name,
                this.minimalAge,
                this.getMinimalBornYear()
        );
    }
}
