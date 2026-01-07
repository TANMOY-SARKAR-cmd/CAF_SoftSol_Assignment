package com.tanmoy.assignment_service;

import com.tanmoy.assignment_service.service.PriceEngine;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.Optional;

@SpringBootTest
class PriceEngineTests {

    @Autowired
    PriceEngine engine;

    @BeforeEach
    void setup() throws Exception {
        var file = new MockMultipartFile(
                "file",
                Files.readAllBytes(Path.of("src/test/resources/prices.tsv"))
        );
        engine.loadTsv(file);
    }

    @Test
    void priceBeforeOfferReturnsEmpty() {
        Assertions.assertEquals(Optional.empty(),
                engine.findPrice("u00006541", LocalTime.parse("09:55")));
    }

    @Test
    void priceInsideRangeReturns101() {
        Assertions.assertEquals(Optional.of(101),
                engine.findPrice("u00006541", LocalTime.parse("10:03")));
    }

    @Test
    void boundarySwitchReturns99() {
        Assertions.assertEquals(Optional.of(99),
                engine.findPrice("u00006541", LocalTime.parse("10:05")));
    }
}
