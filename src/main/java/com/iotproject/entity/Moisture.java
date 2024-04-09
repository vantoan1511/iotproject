package com.iotproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "moisture_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Moisture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double value;
    @Column(name = "created_date")
    private Date createdDate;
}
