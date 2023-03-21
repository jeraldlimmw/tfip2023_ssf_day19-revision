package sg.edu.nus.iss.day19revision.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import sg.edu.nus.iss.day19revision.model.LoveCalculator;
import sg.edu.nus.iss.day19revision.repository.LoveCalculatorRepo;

@Service
public class LoveCalculatorService {
    @Value("${revision19.lovecalculator.url}")
    private String loveCalculatorUrl;

    @Value("${revision19.lovecalculator.api.key}")
    private String loveCalculatorApiKey;

    @Value("${revision19.lovecalculator.api.host}")
    private String loveCalculatorApiHost;

    @Autowired
    private LoveCalculatorRepo lcRepo;

    public LoveCalculator getPercentage(LoveCalculator lc) throws IOException {
        
        String lcUrl = UriComponentsBuilder.fromUriString(loveCalculatorUrl)
                .queryParam("fname", lc.getFname())
                .queryParam("sname", lc.getSname())
                .toUriString();

        RestTemplate template = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", loveCalculatorApiKey);
        headers.set("X-RapidAPI-Host", loveCalculatorApiHost);

        RequestEntity<Void> req = RequestEntity
                .get(lcUrl)
                .headers(headers)
                .accept(MediaType.APPLICATION_JSON)
                .build();

        ResponseEntity<String> r = template.exchange(req, String.class);

        LoveCalculator result = LoveCalculator.jsonToObj(r.getBody());
        lcRepo.save(result);

        return result;
    }

    public void save(LoveCalculator lc) {
        lcRepo.save(lc);
    }
    
    public List<LoveCalculator> getRecord(){
        return lcRepo.getRecord();
    }
}
