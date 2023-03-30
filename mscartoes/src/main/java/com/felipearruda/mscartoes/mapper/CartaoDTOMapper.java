package com.felipearruda.mscartoes.mapper;

import com.felipearruda.mscartoes.domain.Cartao;
import com.felipearruda.mscartoes.dto.CartaoDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CartaoDTOMapper implements Function<Cartao, CartaoDTO> {

    @Override
    public CartaoDTO apply(Cartao cartao) {
        if (cartao == null)
            return null;

        return  CartaoDTO
                .builder()
                    .nome(cartao.getNome())
                    .bandeira(cartao.getBandeira().name())
                    .limite(cartao.getLimite())
                    .renda(cartao.getRenda())
                .build();
    }

}
