package guru.springframework.msscbreweryclient.web.config;

import guru.springframework.msscbreweryclient.config.HttpClientConfigProperties;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.DefaultConnectionKeepAliveStrategy;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

    private final HttpClientConfigProperties httpClientConfig;

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(httpClientConfig.getMaxTotal());
        connectionManager.setDefaultMaxPerRoute(httpClientConfig.getMaxPerRoute());

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(httpClientConfig.getConnectionRequestTimeout(), TimeUnit.MILLISECONDS)
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    /**
     * A description of the entire Java function.
     *
     * @param  restTemplate  the RestTemplate object to be customized
     */
    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }
}
