package com.felipearruda.mscartoes.service;

import com.felipearruda.mscartoes.domain.Cartao;
import com.felipearruda.mscartoes.dto.CartaoDTO;
import com.felipearruda.mscartoes.mapper.CartaoDTOMapper;
import com.felipearruda.mscartoes.mapper.CartaoMapper;
import com.felipearruda.mscartoes.repo.CartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository cartaoRepository;
    private final CartaoDTOMapper dtoMapper;
    private final CartaoMapper cartaoMapper;

    @Transactional
    public void saveCartao(CartaoDTO dto) {
        Cartao cartao = cartaoMapper.apply(dto);
        cartaoRepository.save(cartao);
    }

    @Transactional(readOnly = true)
    public List<CartaoDTO> getCartoesPorRenda(Long renda) {
        BigDecimal rendaDecimal = BigDecimal.valueOf(renda);
        List<Cartao> cartoes = cartaoRepository
                .findByRendaLessThanEqual(rendaDecimal);

        return cartoes
                .stream()
                .map(cartao -> dtoMapper.apply(cartao))
                .collect(Collectors.toList());
    }
}