package com.felipearruda.msclientes.mapper;

import com.felipearruda.msclientes.domain.Cliente;
import com.felipearruda.msclientes.dto.ClienteDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ClienteMapper implements Function<ClienteDTO, Cliente> {
    
    @Override
    public Cliente apply(ClienteDTO dto) {
        if (dto == null)
            return null;

        Cliente cliente = new Cliente(dto.getNome(), dto.getCpf(), dto.getDataAniversario());

        return cliente;
    }

}
