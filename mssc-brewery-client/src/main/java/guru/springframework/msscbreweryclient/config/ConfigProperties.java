package guru.springframework.msscbreweryclient.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
@RequiredArgsConstructor
@Getter
public class ConfigProperties {

    @NotBlank
    private final String apiHost;

    @NotBlank
    private final String beerPathV1;

}
