package data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;
import java.io.IOException;

public class DataFetcher {
    @SuppressWarnings("resource")
    public String fetch(String urlAsString) throws RuntimeException {
        URL url = null;

        try {
            url = new URL(urlAsString);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error in URL , exiting...");
        }
        HttpURLConnection httpURLConnection;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Error in response code, exiting...");
                throw new RuntimeException("Error in response code, exiting...");

            }

            return new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))
                    .lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException("Error in fetching data, exiting...");
        }
    }

}
