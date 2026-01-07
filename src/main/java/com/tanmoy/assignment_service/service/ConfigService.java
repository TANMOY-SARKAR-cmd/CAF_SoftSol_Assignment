package com.tanmoy.assignment_service.service;

import com.tanmoy.assignment_service.loader.ConfigLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ConfigService {

    private final ConfigLoader loader;

    public Map<String, Object> getSection(String name) {
        Map<String, Object> data = loader.getSection(name);
        if (data == null)
            throw new NoSuchElementException("Section not found");
        return data;
    }
}
