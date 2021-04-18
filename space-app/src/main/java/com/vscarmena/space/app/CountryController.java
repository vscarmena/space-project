package com.vscarmena.space.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CountryController {

    private final WebClient webClient;

    public CountryController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/astronauts/countries")
    public List<String> getCountries() {
        Mono<String[]> countriesResponse = webClient
                .get()
                .uri("/countries")
                .retrieve()
                .bodyToMono(String[].class)
                .log();

        return Arrays
                .stream(countriesResponse.block())
                .collect(Collectors.toList());
    }
}
