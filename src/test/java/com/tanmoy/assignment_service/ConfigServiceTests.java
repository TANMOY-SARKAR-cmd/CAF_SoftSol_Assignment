package com.tanmoy.assignment_service;

import com.tanmoy.assignment_service.loader.ConfigLoader;
import com.tanmoy.assignment_service.service.ConfigService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;

@SpringBootTest
class ConfigServiceTests {

    @Autowired
    ConfigLoader loader;

    @Autowired
    ConfigService service;

    @BeforeEach
    void loadFile() throws Exception {
        loader.load("src/test/resources/config.txt");
    }

    @Test
    void topicsAreParsedAsArray() {
        var data = service.getSection("Order Service");
        @SuppressWarnings("unchecked")
        List<String> topics = (List<String>) data.get("topic");

        Assertions.assertEquals(2, topics.size());
    }

    @Test
    void missingSectionThrowsError() {
        Assertions.assertThrows(NoSuchElementException.class,
                () -> service.getSection("Unknown"));
    }
}
