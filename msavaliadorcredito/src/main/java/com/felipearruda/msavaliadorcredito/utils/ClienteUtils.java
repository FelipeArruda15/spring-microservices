package com.felipearruda.msavaliadorcredito.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@UtilityClass
public final class ClienteUtils {

    public static Integer getIdadeByDataAniversario(LocalDate dataAniversario) {
        Period periodo = Period.between(dataAniversario, LocalDate.now());
        return periodo.getYears();
    }
}
