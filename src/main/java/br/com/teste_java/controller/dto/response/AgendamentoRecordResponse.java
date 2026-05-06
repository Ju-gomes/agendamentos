/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */

package br.com.teste.teste.controller.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.teste.teste.infrastructure.enums.StatusNotificacaoEnum;

public record AgendamentoRecordResponse(Long id, 
                                        String emailDestinatario, 
                                        String telefoneDestinatario, 
                                        String mensagem, 
                                        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                                        LocalDateTime dataHoraEnvio,
                                        StatusNotificacaoEnum statusNotificacao) {

}
