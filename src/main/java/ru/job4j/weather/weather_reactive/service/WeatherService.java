package ru.job4j.weather.weather_reactive.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.job4j.weather.weather_reactive.model.Weather;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    private final Map<Integer, Weather>  weathers = new HashMap<>();

    {
        weathers.put(1, new Weather(1, "Msc", 20));
        weathers.put(2, new Weather(2, "SPb", 15));
        weathers.put(3, new Weather(3, "Bryansk", 15));
        weathers.put(4, new Weather(4, "Smolensk", 15));
        weathers.put(5, new Weather(5, "Kiev", 15));
        weathers.put(6, new Weather(6, "Minsk", 15));
    }

    public Mono<Weather> findById(Integer id) {
        return Mono.justOrEmpty(weathers.get(id));
    }

    public Flux<Weather> all() {
        return Flux.fromIterable(weathers.values());
    }

    public Mono<Weather> maxTemperature() {
        Comparator<Weather> maxTemperature = new Comparator<Weather>() {
            @Override
            public int compare(Weather w1, Weather w2) {
                return Integer.compare(w1.getTemperature(), w2.getTemperature());
            }
        };
        return Mono.just(weathers.values().stream().max(maxTemperature).get());
    }

    public Flux<Weather> greatThan(Integer temperature) {
        List<Weather> rsl = weathers.values().stream()
                .filter(w -> w.getTemperature() > temperature)
                .collect(Collectors.toList());
        return Flux.fromIterable(rsl);
    }
}
