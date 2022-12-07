package com.redhat.developer.demos.preference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
public class PreferencesController {

    private static final String RESPONSE_STRING_FORMAT = "preference => %s\n";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final RestTemplate restTemplate;

    @Value("${recommendations.api.url:http://recommendations:8080}")
    private String remoteURL;

    public PreferencesController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // SB 1.5.X actuator does not allow subpaths on custom health checks URL/do in easy way
    @RequestMapping("/health/ready")
    @ResponseStatus(HttpStatus.OK)
    public void ready() {}

    // SB 1.5.X actuator does not allow subpaths on custom health checks URL/do in
    // easy way
    @RequestMapping("/health/live")
    @ResponseStatus(HttpStatus.OK)
    public void live() {
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = "text/plain")
    public ResponseEntity<String> addRecommendation(@RequestBody String body) {
        try {
            return restTemplate.postForEntity(remoteURL, body, String.class);
        } catch (HttpStatusCodeException ex) {
            logger.warn("Exception trying to post to recommendation service.", ex);
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(String.format("%d %s", ex.getRawStatusCode(), createHttpErrorResponseString(ex)));
        } catch (RestClientException ex) {
            logger.warn("Exception trying to post to recommendation service.", ex);
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ex.getMessage());
        }
    }

    @RequestMapping("/")
    public ResponseEntity<?> getPreferences() {
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(remoteURL, String.class);
            String response = responseEntity.getBody();
            return ResponseEntity.ok(String.format(RESPONSE_STRING_FORMAT, response.trim()));
        } catch (HttpStatusCodeException ex) {
            logger.warn("Exception trying to get the response from recommendation service.", ex);
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(String.format(RESPONSE_STRING_FORMAT,
                            String.format("%d %s", ex.getRawStatusCode(), createHttpErrorResponseString(ex))));
        } catch (RestClientException ex) {
            logger.warn("Exception trying to get the response from recommendation service.", ex);
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(String.format(RESPONSE_STRING_FORMAT, ex.getMessage()));
        }
    }

    private String createHttpErrorResponseString(HttpStatusCodeException ex) {
        String responseBody = ex.getResponseBodyAsString().trim();
        if (responseBody.startsWith("null")) {
            return ex.getStatusCode().getReasonPhrase();
        }
        return responseBody;
    }

}
