package com.felipearruda.msavaliadorcredito.exceptions;

import lombok.Getter;

public class ErroComunicacaoMicrosservicesException extends Exception {

    @Getter
    private Integer status;

    public ErroComunicacaoMicrosservicesException(String message, Integer status) {
        super(message);
        this.status = status;
    }
}
