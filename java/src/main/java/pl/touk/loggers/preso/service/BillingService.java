package pl.touk.loggers.preso.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.touk.loggers.preso.rest.BillingDto;

import java.util.Arrays;
import java.util.HashMap;
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

    public List<BillingDto> getBillingDto(String phoneNo) {

        log.info("Invoking billind request for phoneNo [{}]", phoneNo);
        ResponseEntity<BillingDto[]> responseEntity = restTemplate.exchange(billingSystemURL, HttpMethod.GET, new HttpEntity<>(new HashMap<>()), BillingDto[].class);

        log.debug("Received Http headers: [{}]", responseEntity.getHeaders());

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Unexpected response code " + responseEntity.getStatusCodeValue());
        }

        List<BillingDto> BillingDto = Arrays.asList(responseEntity.getBody());
        log.trace("Received Billing [{}]", BillingDto);

        return BillingDto;
    }
}
