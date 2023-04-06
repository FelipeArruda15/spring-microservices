package com.felipearruda.msavaliadorcredito.clients;

import com.felipearruda.msavaliadorcredito.dto.Cartao;
import com.felipearruda.msavaliadorcredito.dto.CartaoCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscartoes", path = "/api/cartao")
public interface CartaoControllerClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CartaoCliente>> getCartoesByCliente(@RequestParam String cpf);

    @GetMapping(params = "renda")
    ResponseEntity<List<Cartao>> getCartoesByRenda(@RequestParam Long renda);
}
