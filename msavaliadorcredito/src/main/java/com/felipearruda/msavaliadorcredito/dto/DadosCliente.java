package com.felipearruda.msavaliadorcredito.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DadosCliente {

    private Long id;
    private String nome;
    private LocalDate dataAniversario;

}
