package com.tanmoy.assignment_service.loader;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Service
public class ConfigLoader {

    private final Map<String, Map<String, Object>> sectionData = new HashMap<>();

    public void load(String filePath) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(filePath));

        String currentSection = null;
        Map<String, Object> currentMap = null;

        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;

            // Section Name
            if (!line.contains("=")) {
                currentSection = line;
                currentMap = new HashMap<>();
                sectionData.put(currentSection, currentMap);
                continue;
            }

            // Key = Value
            String[] parts = line.split("=", 2);
            String key = parts[0].trim();
            String value = parts[1].trim();

            // Prevent null-section case
            if (currentMap == null) {
                currentMap = new HashMap<>();
                sectionData.put("DEFAULT", currentMap);
            }

            // Topic should be array
            if (key.equalsIgnoreCase("topic")) {
                List<String> topics = Arrays.stream(value.split(","))
                        .map(String::trim)
                        .filter(v -> !v.isEmpty())
                        .toList();
                currentMap.put(key, topics);
            } else {
                currentMap.put(key, value);
            }

        }
    }

    public Map<String, Object> getSection(String section) {
        return sectionData.get(section);
    }
}
