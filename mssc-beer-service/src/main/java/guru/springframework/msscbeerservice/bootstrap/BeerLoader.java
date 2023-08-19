package guru.springframework.msscbeerservice.bootstrap;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Slf4j
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository repository;

    @Override
    public void run(String... args) {
        loadBeers();
    }

    private void loadBeers() {
        if(repository.count() == 0){
            repository.save(Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .price(new BigDecimal("12.95"))
                    .upc(1111111111111L)
                    .build());

            repository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE_ALE")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .price(new BigDecimal("12.95"))
                    .upc(1111111111122L)
                    .build());
        }
        log.info("Loaded {} beers", repository.count());
    }
}
