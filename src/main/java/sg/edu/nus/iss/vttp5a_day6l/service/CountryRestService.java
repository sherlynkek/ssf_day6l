package sg.edu.nus.iss.vttp5a_day6l.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import sg.edu.nus.iss.vttp5a_day6l.constant.Url;
import sg.edu.nus.iss.vttp5a_day6l.model.Country;

@Service
public class CountryRestService {
    
    RestTemplate restTemplate = new RestTemplate();

    public List<Country> getCountries() {
        
        String countryData = restTemplate.getForObject(Url.countryUrl, String.class);
        // System.out.println(countryData);

        JsonReader jReader = Json.createReader(new StringReader(countryData));
        JsonObject jObject = jReader.readObject();

        JsonObject jDataObject = jObject.getJsonObject("data");
        
        List<Country> countries = new ArrayList<>();

        Set<Entry<String, JsonValue>> entries = jDataObject.entrySet();
        for (Entry<String, JsonValue> entry : entries) {
            Country c = new Country();
            c.setCode(entry.getKey());
            c.setName(entry.getValue().asJsonObject().getString("country"));
            countries.add(c);
        }

        return countries;
    }
}
