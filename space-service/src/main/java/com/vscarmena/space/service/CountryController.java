package com.vscarmena.space.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
public class CountryController {

    @GetMapping("/countries")
    public List<String> getCountries() throws InterruptedException {
        TimeUnit.SECONDS.sleep(new Random().nextInt(10));
        return Arrays.stream(Country.values()).map(Country::getValue).collect(Collectors.toList());
    }
}
