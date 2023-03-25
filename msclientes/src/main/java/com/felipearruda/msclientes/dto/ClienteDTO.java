package com.felipearruda.msclientes.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ClienteDTO {

    private String nome;
    private String cpf;
    private LocalDate dataAniversario;
}
