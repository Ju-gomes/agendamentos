package br.com.teste.teste.controller.dto.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record AgendamentoRecordRequest(String emailDestinatario, 
                                String telefoneDestinatario, 
                                String mensagem, 
                                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                                LocalDateTime dataHoraEnvio) {
                                   
}