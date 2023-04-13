package com.felipearruda.msavaliadorcredito.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felipearruda.msavaliadorcredito.dto.DadosSolicitacaoEmissaoCartao;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SolicitacaoEmissaoCartaoPublisher {

    private final Queue queueEmissaoCartoes;
    private final RabbitTemplate rabbitTemplate;

     public void solicitarCartao(DadosSolicitacaoEmissaoCartao dados) throws JsonProcessingException {
         var json = convertIntoJson(dados);
        rabbitTemplate.convertAndSend(queueEmissaoCartoes.getName(), json);
     }

     private String convertIntoJson(DadosSolicitacaoEmissaoCartao dados) throws JsonProcessingException {
         ObjectMapper maoper = new ObjectMapper();
         var json = maoper.writeValueAsString(dados);
         return json;
     }
}
