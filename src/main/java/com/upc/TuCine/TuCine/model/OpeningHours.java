package com.upc.TuCine.TuCine.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OpeningHours")
public class OpeningHours {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "start_time",nullable = false)
    private LocalDate startTime;
    @Column(name = "end_time", nullable = false)
    private LocalDate endTime;

}
