package com.tanmoy.assignment_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class PriceSlot {
    LocalTime start;
    LocalTime end;
    int price;
}
