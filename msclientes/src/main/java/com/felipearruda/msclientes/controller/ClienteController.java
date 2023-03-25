package com.felipearruda.msclientes.controller;

import com.felipearruda.msclientes.domain.Cliente;
import com.felipearruda.msclientes.dto.ClienteDTO;
import com.felipearruda.msclientes.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/cliente")
@RequiredArgsConstructor
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

        return ResponseEntity.created(headerLocation).build();
    }

}
