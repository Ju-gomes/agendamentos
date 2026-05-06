package br.com.teste.teste.business.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.teste.teste.controller.dto.request.AgendamentoRecordRequest;
import br.com.teste.teste.infrastructure.entities.Agendamento;

import br.com.teste.teste.controller.dto.response.AgendamentoRecordResponse;

@Mapper(componentModel = "spring")
public interface IAgendamentoMapper {

    Agendamento paraEntity(AgendamentoRecordRequest agendamentoRequest);
    AgendamentoRecordResponse paraResponse(Agendamento agendamento);

    @Mapping(target = "dataHoraModificacao", expression = "java(LocalDateTime.now())")
    @Mapping(target = "statusNotificacao", expression = "java(StatusNotificacaoEnum.CANCELADO)")
    Agendamento paraEntityCancelamento(Agendamento agendamento);

}
