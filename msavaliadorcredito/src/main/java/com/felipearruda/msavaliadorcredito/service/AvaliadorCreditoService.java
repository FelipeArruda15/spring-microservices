package com.felipearruda.msavaliadorcredito.service;

import com.felipearruda.msavaliadorcredito.clients.CartaoControllerClient;
import com.felipearruda.msavaliadorcredito.clients.ClienteControllerClient;
import com.felipearruda.msavaliadorcredito.dto.*;
import com.felipearruda.msavaliadorcredito.exceptions.ClienteNotFoundException;
import com.felipearruda.msavaliadorcredito.exceptions.ErroComunicacaoMicrosservicesException;
import com.felipearruda.msavaliadorcredito.utils.ClienteUtils;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteControllerClient controllerClient;
    private final CartaoControllerClient cartaoControllerClient;

    public SituacaoCliente obterSituacaoCliente(String cpf) throws ClienteNotFoundException, ErroComunicacaoMicrosservicesException {
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = controllerClient.getClienteByCpf(cpf);
            ResponseEntity<List<CartaoCliente>> dadosCartaoResponse = cartaoControllerClient.getCartoesByCliente(cpf);

            return SituacaoCliente
                    .builder()
                    .cliente(dadosClienteResponse.getBody())
                    .cartoes(dadosCartaoResponse.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();

            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClienteNotFoundException();
            }

            throw new ErroComunicacaoMicrosservicesException(e.getMessage(), e.status());
        }
    }

    public RetornoAvaliacao realizaAvaliacao(String cpf, Long renda) throws ClienteNotFoundException, ErroComunicacaoMicrosservicesException {
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = controllerClient.getClienteByCpf(cpf);
            ResponseEntity<List<Cartao>> cartoesResponse = cartaoControllerClient.getCartoesByRenda(renda);

            List<Cartao> cartoes = cartoesResponse.getBody();

            List<CartaoAprovado> cartaoAprovados = cartoes.stream().map(cartao -> {
                BigDecimal limiteBasico = cartao.getLimite();
                BigDecimal idade = BigDecimal.valueOf(ClienteUtils.getIdadeByDataAniversario(dadosClienteResponse.getBody().getDataAniversario()));
                var fator = idade.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);

                CartaoAprovado cartaoAprovado = new CartaoAprovado();
                cartaoAprovado.setCartao(cartao.getNome());
                cartaoAprovado.setBandeira(cartao.getBandeira());
                cartaoAprovado.setLimiteAprovado(limiteAprovado);

                return cartaoAprovado;
            }).collect(Collectors.toList());

            return new RetornoAvaliacao(cartaoAprovados);

        } catch (FeignException.FeignClientException e) {
            int status = e.status();

            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClienteNotFoundException();
            }

            throw new ErroComunicacaoMicrosservicesException(e.getMessage(), e.status());
        }
    }
}
