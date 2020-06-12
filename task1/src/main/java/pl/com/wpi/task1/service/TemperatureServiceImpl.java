package pl.com.wpi.task1.service;

import java.util.Collection;
import java.util.Optional;

import com.antkorwin.xsync.XSync;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import pl.com.wpi.task1.dao.TemperatureRepository;
import pl.com.wpi.task1.model.Temperature;

@Slf4j
@Service
public class TemperatureServiceImpl implements TemperatureService {

    private final TemperatureRepository temperatureRepository;
    private final XSync<Integer> xSync;

    public TemperatureServiceImpl(TemperatureRepository temperatureRepository, XSync<Integer> xSync) {
        this.temperatureRepository = temperatureRepository;
        this.xSync = xSync;
    }


    @Override
    public Collection<Temperature> getValues() {
        return temperatureRepository.findAll();
    }


    @Override
    public ResponseEntity<Temperature> postValue(Temperature temperature) {
        return xSync.evaluate(temperature.getLocalization(), () ->
                handlePostTemperature(temperature));
    }

    ResponseEntity<Temperature> handlePostTemperature(Temperature temperature) {
        HttpStatus status = HttpStatus.OK;

        Optional<Temperature> temperatureInDbOpt = temperatureRepository.findByLocalization(temperature.getLocalization());
        if (temperatureInDbOpt.isPresent()) {
            status = validateTemperature(temperature, temperatureInDbOpt.get());
        } else {
            log.info("Temperature {} was saved", temperature);
            temperatureRepository.save(temperature);
        }
        return new ResponseEntity<>(temperature, status);
    }

    private HttpStatus validateTemperature(Temperature temperature, Temperature temperatureInDb) {
        if (newTemperatureGreaterThenSave(temperature, temperatureInDb)) {
            log.info("Temperature {} has replaced {}", temperature, temperatureInDb);
            temperatureRepository.save(temperature);
            return HttpStatus.OK;
        } else {
            log.info("Temperature {} hasn't been replaced with {} ", temperature, temperatureInDb);
            return HttpStatus.CONFLICT;
        }
    }

    private boolean newTemperatureGreaterThenSave(Temperature temperature, Temperature temperatureInDb) {
        return temperature.getValue().compareTo(temperatureInDb.getValue()) > 0;
    }
}