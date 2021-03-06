package adaptors;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.net.http.*;
import java.io.IOException;
import java.net.URI;

public class MarketAPI {
    private final DateTimeFormatter formatter;
    private final LocalDate today;
    private final HttpClient client;
    private final String key;
    private final int sign;

    
    /**
     * Initializes a new MarketAPI object, responsible for making and parsing API calls for Dogecoin stock data.
     * @throws IOException throws an IOException.
     * @throws InterruptedException throws an InterruptedException.
     */
    public MarketAPI()  throws IOException, InterruptedException {
        this.client = HttpClient.newHttpClient();
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.today = LocalDate.now();
        this.key = "JpDXBpys9trydlvlz9DVhLqldVbKZv3n";

        this.sign = this.marketState();
    }

    /**
     * Makes API call and returns whether the dogecoin price changed today.
     * @return +1 if price increased, -1 if decreased, 0 if no change
     * @throws IOException throws an IOException.
     * @throws InterruptedException throws an InterruptedException.
     */
    private int marketState() throws IOException, InterruptedException {
        String formattedToday = this.today.format(this.formatter);
        String query = "https://api.polygon.io/v1/open-close/crypto/DOGE/CAD/" + formattedToday + 
        "?adjusted=true&apiKey=" + this.key;


        HttpRequest request = HttpRequest.newBuilder()
                                            .uri(URI.create(query))
                                            .build();

        HttpResponse<String> response = this.client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());

        String[] responseSplit = response.body().split(",");

        int open = Character.getNumericValue(responseSplit[3].charAt(7));
        int close = Character.getNumericValue(responseSplit[4].charAt(8));
        return (int) Math.signum(open - close);
    }

    /**
     * @return the price change direction of today's dogecoin stock price.
     */
    public int getSign() {
        return this.sign;
    }

}



