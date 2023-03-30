package com.felipearruda.mscartoes.service;

import com.felipearruda.mscartoes.domain.ClienteCartao;
import com.felipearruda.mscartoes.dto.ClienteCartaoDTO;
import com.felipearruda.mscartoes.mapper.ClienteCartaoDTOMapper;
import com.felipearruda.mscartoes.repo.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

    private final ClienteCartaoRepository cartaoRepository;
    private final ClienteCartaoDTOMapper clienteCartaoDTOMapper;

    @Transactional(readOnly = true)
    public List<ClienteCartaoDTO> getCartoesByCpf(String cpf) {
        List<ClienteCartao> cartoes = cartaoRepository.findByCpf(cpf);
        return cartoes
                .stream()
                .map(cartao -> clienteCartaoDTOMapper.apply(cartao.getCartao()))
                .collect(Collectors.toList());
    }

}
