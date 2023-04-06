package com.felipearruda.msavaliadorcredito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class RetornoAvaliacao {

    private List<CartaoAprovado> cartaoAprovados = new ArrayList<>();
}
