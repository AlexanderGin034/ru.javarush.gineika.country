package com.javarush.country.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter

@Entity
@Table(schema = "world", name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 35)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country countryId;

    @Column(name = "district", length = 20)
    private String district;

    @Column(name = "population")
    private Integer population;
}
