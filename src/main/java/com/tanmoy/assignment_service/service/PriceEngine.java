package com.tanmoy.assignment_service.service;

import com.tanmoy.assignment_service.model.PriceSlot;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalTime;
import java.util.*;

@Service
public class PriceEngine {

    private final Map<String, List<PriceSlot>> priceMap = new HashMap<>();

    public void loadTsv(MultipartFile file) throws IOException {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(file.getInputStream()));

        br.lines().skip(1).forEach(line -> {

            String[] p = line.split("\\|");

            String sku = p[0].trim();
            LocalTime start = LocalTime.parse(p[1].trim());
            LocalTime end   = LocalTime.parse(p[2].trim());
            int price       = Integer.parseInt(p[3].trim());

            priceMap.computeIfAbsent(sku,k->new ArrayList<>())
                    .add(new PriceSlot(start,end,price));
        });
    }

    public Optional<Integer> findPrice(String sku, LocalTime time) {
        
        List<PriceSlot> slots = priceMap.get(sku);
        if (slots == null) return Optional.empty();

        return slots.stream()
            .sorted(Comparator.comparing(PriceSlot::getStart).reversed())
            .filter(s -> !time.isBefore(s.getStart())
                    && !time.isAfter(s.getEnd()))
            .map(PriceSlot::getPrice)
            .findFirst();
    }

}
