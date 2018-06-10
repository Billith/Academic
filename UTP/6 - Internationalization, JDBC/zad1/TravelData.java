package zad1;

import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class TravelData {

    File file;

    public TravelData(File file) {
        this.file = file;
    }

    public List<String> getOffersDescriptionsList(String loc, String dateFormat) {

        Locale outputLocale = Locale.forLanguageTag(loc.replace('_','-'));
        List<String> results = new ArrayList<>();

        for (final File fileEntry : this.file.listFiles()) {
            try {
                Scanner s = new Scanner(fileEntry);
                while(s.hasNextLine()) {
                    String[] line = s.nextLine().split("\t");
                    String country = line[1];
                    String startDate = line[2];
                    String endDate = line[3];
                    String place = line[4];
                    String price = line[5];
                    String currency = line[6];

                    Locale inputLocale = Locale.forLanguageTag(line[0].replace('_','-'));
                    NumberFormat nf = NumberFormat.getInstance(outputLocale);
                    NumberFormat nf1 = NumberFormat.getInstance(inputLocale);
                    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                    ResourceBundle info = ResourceBundle.getBundle("zad1.Places", outputLocale);

                    for (Locale l : Locale.getAvailableLocales()) {
                        if (l.getDisplayCountry(inputLocale).equals(country)) {
                            country = l.getDisplayCountry(outputLocale);
                        }
                    }

                    try {
                        place = info.getString(place);
                    } catch (Exception exc) { }

                    results.add(
                        country + " " + sdf.format(sdf.parse(startDate)) + " " + sdf.format(sdf.parse(endDate))  + " " +
                        place + " " + nf.format(nf1.parse(price)) + " " + currency
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}