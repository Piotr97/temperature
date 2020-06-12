package pl.com.wpi.task1.cucumber.temperature;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import pl.com.wpi.task1.model.Temperature;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TemperatureSteps {

    private HttpStatus responseStatus;
    private Temperature temperature;

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();
    private HttpHeaders httpHeaders = new HttpHeaders();
    private ResponseEntity<Temperature> responseTemperature;


    @Given("I have temperature ([^\"]*), ([^\"]*)")
    public void iHaveTemperature(int localization, int value) {
        temperature = new Temperature(localization, value);
    }

    @When("I send request to post temperature")
    public void iSendRequestToPostTemperature() throws JsonProcessingException {
        String url = "http://localhost:7902/temperature/value";
        String temperatureJson = objectMapper.writeValueAsString(temperature);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        try {
            responseTemperature = restTemplate.postForEntity(url, new HttpEntity<>(temperatureJson, httpHeaders), Temperature.class);
            responseStatus = responseTemperature.getStatusCode();
        } catch (HttpStatusCodeException exception) {
            responseStatus = exception.getStatusCode();
        }
    }

    @Then("I got ([^\"]*) status")
    public void iGotOKStatus(HttpStatus httpStatus) {
        assertEquals(httpStatus, responseStatus);
    }

    @And("I ([^\"]*) posted temperature to database")
    public void iPostedPostedTemperatureToDatabase(String posted) {
        boolean exist = getAllTemperatures().stream()
                .anyMatch(temp -> temp.equals(temperature));
        assertEquals(Boolean.valueOf(posted), exist);
    }

    private List<Temperature> getAllTemperatures() {
        String url = "http://localhost:7902/temperature/values";
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Temperature>>() {
        }).getBody();
    }
}
