package com.tanmoy.assignment_service.controller;

import com.tanmoy.assignment_service.service.ConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/config")
public class ConfigController {

    private final ConfigService service;

    @GetMapping
    public ResponseEntity<?> getSection(@RequestParam String section) {
        try {
            return ResponseEntity.ok(service.getSection(section));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
