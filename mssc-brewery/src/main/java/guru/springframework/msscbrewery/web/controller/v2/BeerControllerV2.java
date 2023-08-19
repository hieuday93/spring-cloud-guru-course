package guru.springframework.msscbrewery.web.controller.v2;

import guru.springframework.msscbrewery.services.v2.BeerServiceV2;
import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v2/beer")
@RequiredArgsConstructor
@Slf4j
@RestController
public class BeerControllerV2 {

    private final BeerServiceV2 beerServiceV2;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDtoV2> getBeer(@PathVariable UUID beerId) {
        log.info("HTTP GET /api/v2/beer/{}", beerId);
        return ResponseEntity.ok(beerServiceV2.getBeerById(beerId));
    }

    @PostMapping
    public ResponseEntity<Void> saveNewBeer(@RequestBody BeerDtoV2 beerDto) {
        log.info("HTTP POST /api/v2/beer {}", beerDto);
        BeerDtoV2 savedDto = beerServiceV2.saveNewBeer(beerDto);

        HttpHeaders headers = new HttpHeaders();
        //todo add hostname to location uri
        headers.add("Location", "/api/v2/beer/" + savedDto.getId().toString());

        return ResponseEntity.noContent().headers(headers).build();
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDtoV2> updateBeer(@PathVariable UUID beerId, @RequestBody BeerDtoV2 beerDto) {
        log.info("HTTP PUT /api/v2/beer/{} {}", beerId, beerDto);
        beerDto.setId(beerId);
        beerServiceV2.updateBeer(beerDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable UUID beerId) {
        log.info("HTTP DELETE /api/v2/beer/{}", beerId);
        beerServiceV2.deleteBeer(beerId);
    }

}
