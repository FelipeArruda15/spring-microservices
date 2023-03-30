package com.felipearruda.mscartoes.mapper;

import com.felipearruda.mscartoes.domain.Cartao;
import com.felipearruda.mscartoes.dto.ClienteCartaoDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ClienteCartaoDTOMapper implements Function<Cartao, ClienteCartaoDTO> {

    @Override
    public ClienteCartaoDTO apply(Cartao cartao) {
        return ClienteCartaoDTO
                .builder()
                .nome(cartao.getNome())
                .bandeira(cartao.getBandeira().name())
                .limiteLiberado(cartao.getLimite())
                .build();
    }
}
