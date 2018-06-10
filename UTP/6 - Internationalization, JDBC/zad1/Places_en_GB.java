package zad1;

import java.util.ListResourceBundle;

public class Places_en_GB extends ListResourceBundle {

    private Object[][] contents = {
        { "morze", "sea"},
        { "jezioro", "lake"},
        { "góry", "mountains"}
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
