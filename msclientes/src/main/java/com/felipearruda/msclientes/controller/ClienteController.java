package com.felipearruda.msclientes.controller;

import com.felipearruda.msclientes.domain.Cliente;
import com.felipearruda.msclientes.dto.ClienteDTO;
import com.felipearruda.msclientes.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/cliente")
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping(params = "cpf")
    public ResponseEntity<ClienteDTO> getClienteByCpf(@RequestParam String cpf) {
        return ResponseEntity.ok(clienteService.getClienteByCpf(cpf));
    }

    @PostMapping
    public ResponseEntity saveCliente(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteService.saveCliente(clienteDTO);

        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();

        log.info("Salvando cliente");

        return ResponseEntity.created(headerLocation).build();
    }

}
