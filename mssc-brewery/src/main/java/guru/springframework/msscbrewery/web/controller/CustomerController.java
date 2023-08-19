package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable UUID id) {
        log.info("HTTP GET /api/v1/customer/{}", id);
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping
    public ResponseEntity<Void> saveNewCustomer(@RequestBody CustomerDto customerDto) {
        log.info("HTTP POST /api/v1/customer {}", customerDto);
        CustomerDto savedDto = customerService.saveNewCustomer(customerDto);

        HttpHeaders headers = new HttpHeaders();
        //todo add hostname to location uri
        headers.add("Location", "/api/v1/customer/" + savedDto.getId().toString());

        return ResponseEntity.noContent().headers(headers).build();
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable UUID customerId, @RequestBody CustomerDto customerDto) {
        log.info("HTTP PUT /api/v1/customer/{} {}", customerId, customerDto);
        customerDto.setId(customerId);
        customerService.updateCustomer(customerDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable UUID customerId) {
        log.info("HTTP DELETE /api/v1/customer/{}", customerId);
        customerService.deleteCustomer(customerId);
    }

}
