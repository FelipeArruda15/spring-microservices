package com.felipearruda.mscartoes.mapper;

import com.felipearruda.mscartoes.domain.Cartao;
import com.felipearruda.mscartoes.domain.enums.BandeiraCartao;
import com.felipearruda.mscartoes.dto.CartaoDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CartaoMapper implements Function<CartaoDTO, Cartao> {

    @Override
    public Cartao apply(CartaoDTO dto) {
        if (dto == null)
            return null;

        Cartao cartao = new Cartao(dto.getNome(), BandeiraCartao.valueOf(dto.getBandeira()), dto.getRenda(), dto.getLimite());
        return cartao;
    }
}
