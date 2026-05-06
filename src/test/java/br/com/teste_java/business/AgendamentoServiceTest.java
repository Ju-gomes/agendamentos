package br.com.teste.teste.business;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.teste.teste.business.mapper.IAgendamentoMapper;
import br.com.teste.teste.business.service.AgendamentoService;
import br.com.teste.teste.controller.dto.request.AgendamentoRecordRequest;
import br.com.teste.teste.controller.dto.response.AgendamentoRecordResponse;
import br.com.teste.teste.infrastructure.entities.Agendamento;
import br.com.teste.teste.infrastructure.enums.StatusNotificacaoEnum;
import br.com.teste.teste.infrastructure.repositories.AgendamentoRepository;
import jakarta.inject.Inject;

@ExtendWith(MockitoExtension.class)
public class AgendamentoServiceTest {

    @InjectMocks
    private AgendamentoService service;

    @Mock
    private AgendamentoRepository repository;

    @Mock
    private IAgendamentoMapper mapper;

    private AgendamentoRecordRequest record;
    private AgendamentoRecordResponse response;
    private Agendamento agendamento;

    @BeforeEach()
    void setUp(){

        agendamento = new Agendamento(1L, 
                       "email@email",
                    "55123123123", 
                                          LocalDateTime.of(2025, 1, 1, 00, 00, 00),
                                          LocalDateTime.now(), null,
                                 "Retornar a loja com urgência",
                                          StatusNotificacaoEnum.AGENDADO);

        record = new AgendamentoRecordRequest("email@email.com",
                                             "55123123123",
                                                        "Favor retornar a loja com urgência", 
                                                        LocalDateTime.of(2025, 1, 2, 11, 1, 1));                                        

        response = new AgendamentoRecordResponse(1L, "email@email.com", 
                                                      "55123123123",
                                                                 "Favor retornar a loja com urgência", 
                                                                           LocalDateTime.of(2025, 1, 2, 11, 1, 1),
                                                                           StatusNotificacaoEnum.AGENDADO);

    }

    @Test
    void deveGravarAgendamentoComSucesso(){
        when(mapper.paraEntity(record)).thenReturn(agendamento);
        when(repository.save(agendamento)).thenReturn(agendamento);
        when(mapper.paraResponse(agendamento)).thenReturn(response);

        AgendamentoRecordResponse response = service.salvar(record);

        verify(mapper, times(1)).paraEntity(record);
        verify(repository, times(1)).save(agendamento);
        verify(mapper, times(1)).paraResponse(agendamento);
        assertThat(response).usingRecursiveComparison().isEqualTo(response);

    }


    

    

}
