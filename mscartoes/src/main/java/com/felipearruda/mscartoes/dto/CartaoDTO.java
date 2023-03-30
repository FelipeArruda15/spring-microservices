package com.felipearruda.mscartoes.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CartaoDTO {

    private String nome;
    private String bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

}
