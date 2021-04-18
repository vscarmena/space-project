package com.vscarmena.space.app;

import java.util.Arrays;
import java.util.List;

public class AstronautMother {

    public static Astronaut getAstronaut() {
        return new Astronaut("NEIL A. ARMSTRONG", Country.USA);
    }

    public static List<Astronaut> getAstronauts() {
        return Arrays.asList(
                getAstronaut(),
                new Astronaut("MICHAEL COLLINS", Country.ITA),
                new Astronaut("BUZZ ALDRIN", Country.USA)
        );
    }

}
