package pl.touk.loggers.preso.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.touk.loggers.preso.rest.LoggingRestTemplate;

import java.util.Collections;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.build();
        restTemplate.setInterceptors(Collections.singletonList(new LoggingRestTemplate()));
        return restTemplate;
    }
}
