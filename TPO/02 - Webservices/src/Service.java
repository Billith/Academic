/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad1;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Currency;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Service {

    private final String weatherApiUrl = "http://api.openweathermap.org/data/2.5/weather?q=%s&APPID=3469620e6ca9715b6b72027ab9521a32";
    private final String currencyRateApiUrl = "https://api.exchangeratesapi.io/latest?base=%s";
    private final String currencyNBPRatesAURL = "http://www.nbp.pl/kursy/kursya.html";
    private final String currencyNBPRatesBURL = "http://www.nbp.pl/kursy/kursyb.html";

    private String country;
    private String city;
    private Locale countryLocale;
    private Currency countryCurrency;
    private String usersProvidedCurrency;

    public Service(String country) {
        this.country = country;
        this.countryLocale = getCountryLocale();
        this.countryCurrency = Currency.getInstance(this.countryLocale);
    }

    public Double getRateFor(String currency) {
        this.usersProvidedCurrency = currency;
        String currencyRateFormattedApiUrl = String.format(currencyRateApiUrl, currency);
        String response = getHTTPResponseBody(currencyRateFormattedApiUrl);

        String regexString = String.format("\"%s\":(\\d+.\\d+)", this.countryCurrency.getCurrencyCode());
        Pattern currencyValuePattern = Pattern.compile(regexString);
        Matcher currencyValueMatcher = currencyValuePattern.matcher(response);

        while(currencyValueMatcher.find())
            return Double.parseDouble(currencyValueMatcher.group(1));

        return 0d;
    }

    public Double getNBPRate() {

        if (this.country.toLowerCase().equals("poland"))
            return 1d;

        String response = getHTTPResponseBody(currencyNBPRatesAURL) + getHTTPResponseBody(currencyNBPRatesBURL);

        String regexString = String.format("%s</td>\\R(.+?)<td(.+?)>(\\d+,\\d+)", this.countryCurrency.getCurrencyCode());
        Pattern ratePattern = Pattern.compile(regexString);
        Matcher rateMatcher = ratePattern.matcher(response);

        while(rateMatcher.find())
            return Double.parseDouble(rateMatcher.group(3).replace(',','.'));

        return 0d;
    }

    private String getHTTPResponseBody(String url) {
        try {
            InputStream response = prepareHttpConnection(url);
            byte[] buffer = new byte[8192];
            String responseString = "";

            int read_bytes;
            while((read_bytes = response.read(buffer)) != -1) {
                byte[] respondBytesChunk = new byte[read_bytes];
                for(int i = 0; i < read_bytes; i++) {
                    respondBytesChunk[i] = buffer[i];
                }
                responseString += new String(respondBytesChunk);
            }

            return responseString;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private InputStream prepareHttpConnection(String url) throws IOException {
        URL customURLConnection = new URL(url);
        HttpURLConnection customHttpURLConnection = (HttpURLConnection) customURLConnection.openConnection();
        customHttpURLConnection.setRequestMethod("GET");
        return customHttpURLConnection.getInputStream();
    }

    private Locale getCountryLocale() {
        for (String country : Locale.getISOCountries()) {
            Locale countryLocale = new Locale("", country);
            if (countryLocale.getDisplayCountry().equals(this.country)) {
                return countryLocale;
            }
        }
        return new Locale("", "pl");
    }

    public String getWeather(String city) {
        this.city = city;
        String weatherFormattedApiUrl = String.format(weatherApiUrl, city);
        return getHTTPResponseBody(weatherFormattedApiUrl);
    }

    public String getWeather() {
        return this.getWeather(this.city);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
        this.countryLocale = getCountryLocale();
        this.countryCurrency = Currency.getInstance(this.countryLocale);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUsersProvidedCurrency() {
        return usersProvidedCurrency;
    }

    public void setUsersProvidedCurrency(String usersProvidedCurrency) {
        this.usersProvidedCurrency = usersProvidedCurrency;
    }
}
