package com.np.monkeypoxtracker.service;
import com.np.monkeypoxtracker.model.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class MonkeypoxDataService {

    private static String VIRUS_DATA_URL = "https://www.cdc.gov/7c5dde7c-fd2a-4cff-b772-8a9319740657";

    private List<LocationStats> allStats = new ArrayList<>();

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *") //Day after day
    public void fetchVirusData() throws IOException, InterruptedException {
        List<LocationStats> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader csvBodyReader = new StringReader(httpResponse.body()); //reader entity body
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader); //csv parse formats
        for (CSVRecord record: records){
            LocationStats stats = new LocationStats();
            stats.setCountry(record.get("Country"));
            int latestCases = Integer.parseInt(record.get("Cases"));//get case numbers
            int deathCount = Integer.parseInt(record.get("Deaths"));
            stats.setLatestTotalCases(latestCases);
            stats.setDeaths(deathCount);

            System.out.println(stats);
            newStats.add(stats);
        }
        this.allStats = newStats;
    }
}