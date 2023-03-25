package com.felipearruda.msclientes.mapper;

import com.felipearruda.msclientes.domain.Cliente;
import com.felipearruda.msclientes.dto.ClienteDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ClienteDTOMapper implements Function<Cliente, ClienteDTO> {

    @Override
    public ClienteDTO apply(Cliente cliente) {
        if (cliente == null)
            return null;

        return ClienteDTO.builder()
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .dataAniversario(cliente.getDataAniversario())
                .build();
    }

}
