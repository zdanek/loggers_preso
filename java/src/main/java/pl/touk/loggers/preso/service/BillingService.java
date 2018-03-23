package pl.touk.loggers.preso.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.touk.loggers.preso.config.Tracking;
import pl.touk.loggers.preso.rest.BillingDto;

import java.util.Arrays;
import java.util.List;

@Service
public class BillingService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Value("${billing.url}")
    private String billingSystemURL;

    private RestTemplate restTemplate;

    BillingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<BillingDto> getBilling(String phoneNo) {

        log.info("Invoking billing request for phoneNo [{}]", phoneNo);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set(Tracking.CORRELATION_ID_HEADER_NAME, MDC.get(Tracking.CORRELATION_ID_MDC_KEY));

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getBillingApiURL())
                .queryParam("phoneNo", phoneNo);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<BillingDto[]> responseEntity = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                BillingDto[].class);

        log.trace("Received Http headers: [{}]", responseEntity.getHeaders());

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Unexpected response code " + responseEntity.getStatusCodeValue());
        }

        List<BillingDto> billings = Arrays.asList(responseEntity.getBody());
        log.trace("Received Billing [{}]", billings);

        return billings;
    }

    private String getBillingApiURL() {
        return billingSystemURL + "/api/billing";
    }
}
