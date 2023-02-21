package com.javarush.country.redis;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
@Getter @Setter
@ToString
@EqualsAndHashCode

public class Language {
    private String language;
    private Boolean isOfficial;
    private BigDecimal percentage;
}
