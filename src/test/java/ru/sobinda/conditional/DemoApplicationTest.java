package ru.sobinda.conditional;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class DemoApplicationTest {
    @Autowired
    TestRestTemplate restTemplate;

    @Container
    private final GenericContainer<?> myAppFirst = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    @Container
    private final GenericContainer<?> myAppSecond = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @Test
    void contextLoadsFirst() {
        ResponseEntity<String> forEntityFirst = restTemplate.getForEntity("http://localhost:"
                + myAppFirst.getMappedPort(8080)
                + "/profile", String.class);
        System.out.println(forEntityFirst.getBody());
        String expected = "Current profile is dev";
        Assertions.assertEquals(expected, forEntityFirst.getBody());

    }

    @Test
    void contextLoadsSecond() {
        ResponseEntity<String> forEntitySecond = restTemplate.getForEntity("http://localhost:"
                + myAppSecond.getMappedPort(8081)
                + "/profile", String.class);
        System.out.println(forEntitySecond.getBody());
        String expected = "Current profile is production";
        Assertions.assertEquals(expected, forEntitySecond.getBody());
    }

}
