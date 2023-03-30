package com.felipearruda.mscartoes.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ClienteCartaoDTO {

    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;
}
