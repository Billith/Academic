package zad1;

import java.util.ListResourceBundle;

public class Places_pl_PL extends ListResourceBundle {

    private Object[][] contents = {
        { "sea", "morze"},
        { "lake", "jezioro"},
        { "mountains", "góry"}
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
