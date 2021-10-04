package com.github.nikhrom.javatraining.http.practice.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Flight {
    Integer id;
    String flightNo;
    LocalDateTime departureDate;
    String departureAirportCode;
    LocalDateTime arrivalDate;
    String arrivalAirportCode;
    Integer aircraftId;
    FlightStatus status;
}
