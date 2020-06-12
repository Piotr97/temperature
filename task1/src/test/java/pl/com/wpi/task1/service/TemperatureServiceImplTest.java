package pl.com.wpi.task1.service;

import com.antkorwin.xsync.XSync;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.com.wpi.task1.dao.TemperatureRepository;
import pl.com.wpi.task1.model.Temperature;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TemperatureServiceImplTest {

    private TemperatureServiceImpl temperatureService;

    private XSync<Integer> xSync;

    private TemperatureRepository temperatureRepository;

    private static Temperature exampleTemperature;

    @BeforeAll
    public static void beforeAll() {
        exampleTemperature = new Temperature(1, 12);
    }

    @BeforeEach
    public void beforeEach() {
        xSync = mock(XSync.class);
        temperatureRepository = mock(TemperatureRepository.class);
        temperatureService = new TemperatureServiceImpl(temperatureRepository, xSync);
    }

    @Test
    public void getValuesShouldReturnValuesFromRepo() {
        List<Temperature> temperatures = Collections.singletonList(exampleTemperature);
        when(temperatureRepository.findAll()).thenReturn(temperatures);
        Collection<Temperature> result = temperatureService.getValues();
        assertSame(result, temperatures);
    }


    @Test
    public void handlePostShouldAddTemperatureIfAbsent() {
        ResponseEntity<Temperature> result = temperatureService.handlePostTemperature(exampleTemperature);
        verify(temperatureRepository).save(exampleTemperature);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void whenFoundTemperatureIsGreater() {
        Temperature newTemperature = new Temperature(exampleTemperature.getLocalization(), exampleTemperature.getValue() + 1);
        when(temperatureRepository.findByLocalization(exampleTemperature.getLocalization()))
                .thenReturn(Optional.of(exampleTemperature));
        ResponseEntity<Temperature> result = temperatureService.handlePostTemperature(newTemperature);
        verify(temperatureRepository).save(newTemperature);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void whenFoundTemperatureIsNotGreater() {
        when(temperatureRepository.findByLocalization(exampleTemperature.getLocalization()))
                .thenReturn(Optional.of(exampleTemperature));
        ResponseEntity<Temperature> result = temperatureService.handlePostTemperature(exampleTemperature);
        verify(temperatureRepository,never()).save(any());
        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
    }
}