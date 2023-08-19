package guru.springframework.msscbreweryclient.config;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sfg.http.client")
@RequiredArgsConstructor
@Getter
public class HttpClientConfigProperties {

    @Min(1)
    private final long connectionRequestTimeout;

    @Min(1)
    private final int maxTotal;

    @Min(1)
    private final int maxPerRoute;

}
