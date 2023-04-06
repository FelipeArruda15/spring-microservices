package com.felipearruda.msavaliadorcredito.clients;

import com.felipearruda.msavaliadorcredito.dto.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msclientes", path = "/api/cliente")
public interface ClienteControllerClient {

    @GetMapping(params = "cpf")
    ResponseEntity<DadosCliente> getClienteByCpf(@RequestParam String cpf);
}
