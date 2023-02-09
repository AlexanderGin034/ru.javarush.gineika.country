package com.javarush.country.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter

@Entity
@Table(schema = "world", name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code", length = 3)
    private String code;

    @Column(name = "code_2", length = 2)
    private String extraCode;

    @Column(name = "name", length = 52)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "continent", columnDefinition = "0-ASIA, 1-EUROPE, 2-NORTH_AMERICA, 3-AFRICA, 4-OCEANIA, 5-ANTARCTICA, 6-SOUTH_AMERICA")
    private Continent continent;

    @Column(name = "region", length = 26)
    private String region;

    @Column(name = "surface_area")
    private BigDecimal surfaceArea;

    @Column(name = "indep_year")
    private Short indepYear;

    @Column(name = "population")
    private Integer population;

    @Column(name = "life_expectancy")
    private BigDecimal lifeExpectancy;

    @Column(name = "gnp")
    private BigDecimal GNP;

    @Column(name = "gnpo_id")
    private BigDecimal DNPOId;

    @Column(name = "local_name", length = 45)
    private String localName;

    @Column(name = "government_form", length = 45)
    private String governmentForm;

    @Column(name = "head_of_state", length = 45)
    private String headOfState;

    @OneToOne
    @JoinColumn(name = "capital")
    private City capital;
}
