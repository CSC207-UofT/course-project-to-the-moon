package adaptors;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;
import java.io.IOException;
import java.net.URI;

public class MarketAPI {
    private DateTimeFormatter formatter;
    private LocalDate today;
    private HttpClient client;
    private String key;
    
    public MarketAPI() {
        this.client = HttpClient.newHttpClient();
        this.formatter = DateTimeFormatter.ofPattern("yyyy-mm-yy");
        this.today = LocalDate.now();
        this.key = "JpDXBpys9trydlvlz9DVhLqldVbKZv3n";
        
    }

    private void updateDate() {
        this.today = LocalDate.now();
    }

    private String callAPI(String query) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(query)).build();

        HttpResponse<String> response = this.client.send(request, HttpResponse.BodyHandlers.ofString());
        
        
        
    }

    private void marketState() {
        String formattedToday = this.today.format(this.formatter);
        String query = "https://api.polygon.io/v1/open-close/crypto/DOGE/CAD/" + formattedToday + 
        "2020-10-14?adjusted=true&apiKey=" + this.key;
        //String response = this.callAPI(query);

    }

}



