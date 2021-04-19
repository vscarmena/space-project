package com.vscarmena.space.app;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class AstronautIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private AstronautRepository astronautRepository;

    @BeforeAll
    public void setup() {
        RestAssured.port = port;
    }

    @AfterEach
    public void tearDown() throws Exception {
        astronautRepository.deleteAll();
    }

    @Test
    public void potatoes() {
        given()
                .log()
                .all()
                .get("/");
    }

    @Test
    public void shouldReturn200_whenGetAstronauts() {
        createAstronauts();

        Response response = when()
                .get("/astronauts")
                .then()
                .extract()
                .response();

        response.getBody().prettyPrint();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(response.jsonPath().getList("_embedded.astronauts").size()).isEqualTo(3);
    }

    @Test
    public void shouldReturn200_whenGetProductById() {
        Astronaut astronaut = createAstronaut(AstronautMother.getAstronaut());

        Response response = when()
                .get("/astronauts/" + astronaut.getId())
                .then()
                .extract()
                .response();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(response.jsonPath().getString("name")).isEqualTo("NEIL A. ARMSTRONG");
    }

    @Test
    public void shouldReturn200_whenAAstronautIsModified() {
        Astronaut astronaut = createAstronaut(AstronautMother.getAstronaut());
        astronaut.setName("NEIL A. ARMSTRONG --MODIFIED");

        Response response = with()
                .body(astronaut)
                .contentType(ContentType.JSON)
                .when()
                .put("/astronauts/" + astronaut.getId())
                .then()
                .extract()
                .response();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(response.jsonPath().getString("name")).isEqualTo("NEIL A. ARMSTRONG --MODIFIED");
    }

    @Test
    public void shouldReturn200_whenSortAstronautsByName() {
        createAstronauts();

        Response response = given()
                .param("sort", "name,asc")
                .get("/astronauts")
                .then()
                .extract()
                .response();

        HashMap<String, String> firstAstronaut = (HashMap<String, String>) response.jsonPath().getList("_embedded.astronauts").get(0);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(firstAstronaut.get("name")).isEqualTo("BUZZ ALDRIN");
    }

    @Test
    public void shouldReturn201_whenAddAstronaut() {
        Astronaut astronaut = AstronautMother.getAstronaut();

        Response response = with()
                .body(astronaut)
                .contentType(ContentType.JSON)
                .when()
                .post("/astronauts")
                .then()
                .extract()
                .response();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_CREATED);
    }

    @Test
    public void shouldReturn204_whenAAstronautIsDeleted() {
        Astronaut astronaut = createAstronaut(AstronautMother.getAstronaut());

        Response response = when()
                .delete("/astronauts/" + astronaut.getId())
                .then()
                .extract()
                .response();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void shouldReturn404_whenAstronautNotFound() {
        Response response = when()
                .get("/astronauts/1")
                .then()
                .extract()
                .response();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void shouldReturn409_whenAddAstronautAndExists() {
        Astronaut astronaut = AstronautMother.getAstronaut();
        createAstronaut(astronaut);
        Astronaut astronautAlreadyExists = AstronautMother.getAstronaut();

        Response response = with()
                .body(astronautAlreadyExists)
                .contentType(ContentType.JSON)
                .when()
                .post("/astronauts")
                .then()
                .extract()
                .response();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_CONFLICT);
    }

    @Test
    public void shouldReturn500_whenFailedConversionTypes() {
        Response response = when()
                .get("/astronauts/d")
                .then()
                .extract()
                .response();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    private void createAstronauts() {
        AstronautMother
                .getAstronauts()
                .forEach(astronaut -> astronautRepository.save(astronaut));
    }

    private Astronaut createAstronaut(Astronaut astronaut) {
        return astronautRepository.save(astronaut);
    }
}
