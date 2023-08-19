package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.config.ExternalApiConfigProperties;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
public class CustomerClient {

    private static final String SLASH = "/";

    private final String apiHost;
    private final String customerPathV1;
    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplateBuilder restTemplateBuilder, ExternalApiConfigProperties properties) {
        this.apiHost = properties.getApiHost();
        this.customerPathV1 = properties.getCustomerPathV1();
        this.restTemplate = restTemplateBuilder.build();
    }

    public CustomerDto getCustomerById(UUID customerId) {
        return restTemplate.getForObject(apiHost + customerPathV1 + SLASH + customerId.toString(),
                CustomerDto.class);
    }

    public URI createCustomer(CustomerDto dto) {
        return restTemplate.postForLocation(apiHost + customerPathV1, dto);
    }

    public void updateCustomer(UUID customerId, CustomerDto dto) {
        restTemplate.put(apiHost + customerPathV1 + SLASH + customerId.toString(), dto);
    }

    public void deleteCustomer(UUID customerId) {
        restTemplate.delete(apiHost + customerPathV1 + SLASH + customerId.toString());
    }

}
