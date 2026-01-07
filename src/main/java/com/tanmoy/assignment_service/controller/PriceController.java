package com.tanmoy.assignment_service.controller;

import com.tanmoy.assignment_service.service.PriceEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/price")
public class PriceController {

    private final PriceEngine engine;

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws Exception {
        engine.loadTsv(file);
        return "File uploaded successfully";
    }

    @GetMapping
    public Map<String,Object> getPrice(
            @RequestParam String skuid,
            @RequestParam(required = false) String time) {

        if (time == null)
            return Map.of("price","NOT SET");

        LocalTime t = LocalTime.parse(time);

        return engine.findPrice(skuid, t)
                .<Map<String,Object>>map(p -> Map.of("price", p))
                .orElse(Map.of("price","NOT SET"));
    }
}
