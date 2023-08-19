package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient client;

    @Test
    void getBeerById() {
        BeerDto dto = client.getBeerById(UUID.randomUUID());
        assertNotNull(dto);
    }

    @Test
    void createBeer() {
        BeerDto dto = BeerDto.builder().beerName("my beer").beerStyle("abc").build();
        URI uri = client.createBeer(dto);
        assertNotNull(uri);
    }

    @Test
    void updateBeer() {
        BeerDto dto = BeerDto.builder().beerName("my beer").beerStyle("abc").build();
        client.updateBeer(UUID.randomUUID(), dto);
    }
}