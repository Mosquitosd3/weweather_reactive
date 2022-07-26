package ru.job4j.weather.weather_reactive.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import ru.job4j.weather.weather_reactive.model.Weather;
import ru.job4j.weather.weather_reactive.service.WeatherService;

import java.time.Duration;

@RestController
public class WeatherControl {
    @Autowired
    private final WeatherService service;

    public WeatherControl(WeatherService weathers) {
        this.service = weathers;
    }

    @GetMapping(value = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Weather> all() {
        Flux<Weather> data = service.all();
        Flux<Long> delay = Flux.interval(Duration.ofSeconds(3));
        return Flux.zip(data, delay).map(Tuple2::getT1);
    }

    @GetMapping(value = "/get/{id}")
    public Mono<Weather> get(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping(value = "/ /hottest")
    public Mono<Weather> getMaxTemperature() {
        return service.maxTemperature();
    }

    @GetMapping(value = "/cityGreatThen/{temperature}")
    public Flux<Weather> cityGreatThen(@PathVariable Integer temperature) {
        return service.greatThan(temperature);
    }
}
