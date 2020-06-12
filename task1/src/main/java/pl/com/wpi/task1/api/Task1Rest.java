package pl.com.wpi.task1.api;

import java.util.Collection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.com.wpi.task1.model.Temperature;
import pl.com.wpi.task1.service.TemperatureService;

import javax.validation.Valid;


@RestController
@Slf4j

public class Task1Rest {

    private final TemperatureService temperatureService;

    public Task1Rest(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }


    @PostMapping("/temperature/value")
    public ResponseEntity<Temperature> postValue(@RequestBody @Valid Temperature temperature) throws Exception {
        log.info("Preparing to post temperature {}", temperature);
        return temperatureService.postValue(temperature);
    }

    @GetMapping("/temperature/values")
    public Collection<Temperature> getValues() {
        log.info("Preparing to get all temperatures");
        return temperatureService.getValues();
    }
}