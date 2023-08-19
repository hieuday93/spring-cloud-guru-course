package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getCustomerById(UUID id) {
        log.info("getCustomerById({})", id);
        return CustomerDto.builder()
                .id(id)
                .name("Bob")
                .build();
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customerDto) {
        log.info("saveNewCustomer({})", customerDto);
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name(customerDto.getName())
                .build();
    }

    @Override
    public void updateCustomer(CustomerDto customerDto) {
        log.info("updateCustomer({})", customerDto);
        //todo implement actual update
    }

    @Override
    public void deleteCustomer(UUID customerId) {
        log.info("deleteCustomer({})", customerId);
        //todo implement actual delete
    }
}
