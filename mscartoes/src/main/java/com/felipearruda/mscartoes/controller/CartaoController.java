package com.felipearruda.mscartoes.controller;

import com.felipearruda.mscartoes.dto.ClienteCartaoDTO;
import com.felipearruda.mscartoes.dto.CartaoDTO;
import com.felipearruda.mscartoes.service.CartaoService;
import com.felipearruda.mscartoes.service.ClienteCartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartao")
@RequiredArgsConstructor
public class CartaoController {

    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;

    @PostMapping
    public ResponseEntity cadastra(@RequestBody CartaoDTO cartaoDTO) {
        cartaoService.saveCartao(cartaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<CartaoDTO>> getCartoesByRenda(@RequestParam Long renda) {
        List<CartaoDTO> cartoesPorRenda = cartaoService.getCartoesPorRenda(renda);
        return ResponseEntity.ok(cartoesPorRenda);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<ClienteCartaoDTO>> getCartoesByCliente(@RequestParam String cpf) {
        List<ClienteCartaoDTO> clienteCartoes = clienteCartaoService.getCartoesByCpf(cpf);
         return ResponseEntity.ok(clienteCartoes);
    }
}
