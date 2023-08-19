package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.config.ConfigProperties;
import guru.springframework.msscbreweryclient.web.model.BeerDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class BreweryClient {

    private final String beerPathV1;

    private final String apiHost;

    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder, ConfigProperties configProperties) {
        this.restTemplate = restTemplateBuilder.build();
        this.apiHost = configProperties.getApiHost();
        this.beerPathV1 = configProperties.getBeerPathV1();
    }

    public BeerDto getBeerById(UUID beerId) {
        return restTemplate.getForObject(apiHost + beerPathV1 + beerId.toString(), BeerDto.class);
    }
}
