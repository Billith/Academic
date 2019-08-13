package view;

import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Custom DataPicker control implementation with overwritten format of date displayed in control
 */
// https://code.makery.ch/blog/javafx-8-date-picker/
public class FormattedDataPicker extends DatePicker {

    public FormattedDataPicker(LocalDate date) {
        super(date);

        String pattern = "dd/MM/yy";

        this.setConverter(new StringConverter<LocalDate>() {

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate currentDate) {
                if (currentDate != null) {
                    return dateFormatter.format(currentDate);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
    }

}
