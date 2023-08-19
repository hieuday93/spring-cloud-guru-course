package guru.springframework.msscbreweryclient.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
@RequiredArgsConstructor
@Getter
@Validated
public class ExternalApiConfigProperties {

    @NotBlank
    private final String apiHost;

    @NotBlank
    private final String beerPathV1;

    @NotBlank
    private final String customerPathV1;

}
