package com.felipearruda.msclientes.service;

import com.felipearruda.msclientes.domain.Cliente;
import com.felipearruda.msclientes.dto.ClienteDTO;
import com.felipearruda.msclientes.mapper.ClienteDTOMapper;
import com.felipearruda.msclientes.mapper.ClienteMapper;
import com.felipearruda.msclientes.repo.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteDTOMapper dtoMapper;
    private final ClienteMapper clienteMapper;

    public ClienteDTO getClienteByCpf(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        return dtoMapper.apply(cliente);
    }

    public Cliente saveCliente(ClienteDTO dto) {
        Cliente cliente = clienteMapper.apply(dto);

        return clienteRepository.save(cliente);
    }

}
