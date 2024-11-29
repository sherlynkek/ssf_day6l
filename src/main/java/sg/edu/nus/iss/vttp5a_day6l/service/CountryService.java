package sg.edu.nus.iss.vttp5a_day6l.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import sg.edu.nus.iss.vttp5a_day6l.constant.Url;
import sg.edu.nus.iss.vttp5a_day6l.model.Country;

@Service
public class CountryService {

    RestTemplate restTemplate = new RestTemplate();

    public List<Country> getCountries() {

        String countryData = restTemplate.getForObject(Url.countryUrl, String.class);
        System.out.println(countryData);
        
        return null;
        
    }
    
}
