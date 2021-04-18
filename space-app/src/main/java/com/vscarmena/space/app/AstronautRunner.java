package com.vscarmena.space.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AstronautRunner implements CommandLineRunner {

    private final AstronautRepository astronautRepository;

    public AstronautRunner(AstronautRepository astronautRepository) {
        this.astronautRepository = astronautRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        astronautRepository.deleteAll();
        astronautRepository.save(new Astronaut("NEIL A. ARMSTRONG", Country.USA));
        astronautRepository.save(new Astronaut("MICHAEL COLLINS", Country.ITA));
        astronautRepository.save(new Astronaut("BUZZ ALDRIN", Country.USA));

    }
}
