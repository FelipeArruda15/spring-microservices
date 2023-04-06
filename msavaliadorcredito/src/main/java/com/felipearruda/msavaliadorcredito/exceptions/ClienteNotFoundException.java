package com.felipearruda.msavaliadorcredito.exceptions;

public class ClienteNotFoundException extends Exception {

    public ClienteNotFoundException() {
        super("Dados do cliente não encontrados para o CPF informado.");
    }

}
