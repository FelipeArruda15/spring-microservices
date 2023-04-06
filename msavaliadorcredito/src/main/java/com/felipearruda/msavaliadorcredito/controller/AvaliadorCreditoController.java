package com.felipearruda.msavaliadorcredito.controller;

import com.felipearruda.msavaliadorcredito.dto.DadosAvaliacao;
import com.felipearruda.msavaliadorcredito.dto.RetornoAvaliacao;
import com.felipearruda.msavaliadorcredito.dto.SituacaoCliente;
import com.felipearruda.msavaliadorcredito.exceptions.ClienteNotFoundException;
import com.felipearruda.msavaliadorcredito.exceptions.ErroComunicacaoMicrosservicesException;
import com.felipearruda.msavaliadorcredito.service.AvaliadorCreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avaliadorcredito")
@RequiredArgsConstructor
public class AvaliadorCreditoController {

    private final AvaliadorCreditoService avaliadorCreditoService;

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf){
        try{
            SituacaoCliente situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoCliente);
        } catch (ClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicrosservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity realizaAvaliacaoCliente(@RequestBody DadosAvaliacao dadosAvaliacao){
        try{
            RetornoAvaliacao retornoAvaliacao = avaliadorCreditoService.realizaAvaliacao(dadosAvaliacao.getCpf(), dadosAvaliacao.getRenda());
            return ResponseEntity.ok(retornoAvaliacao);
        } catch (ClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicrosservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }

    }

}
