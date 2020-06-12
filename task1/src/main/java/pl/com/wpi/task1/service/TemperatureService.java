package pl.com.wpi.task1.service;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import pl.com.wpi.task1.model.Temperature;

public interface TemperatureService {

    Collection<Temperature> getValues();

    ResponseEntity<Temperature> postValue(Temperature temperature);
}