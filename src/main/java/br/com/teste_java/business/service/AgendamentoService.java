package br.com.teste.teste.business.service;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import br.com.teste.teste.business.mapper.IAgendamentoMapper;
import br.com.teste.teste.controller.dto.request.AgendamentoRecordRequest;
import br.com.teste.teste.controller.dto.response.AgendamentoRecordResponse;
import br.com.teste.teste.infrastructure.entities.Agendamento;
import br.com.teste.teste.infrastructure.repositories.AgendamentoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AgendamentoService {

    //@Autowired
    private final AgendamentoRepository repository;
  
    //@Autowired
    private final IAgendamentoMapper mapper;

    /*
    public AgendamentoService(IAgendamentoMapper mapper, AgendamentoRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }
         */

    public AgendamentoRecordResponse salvar(AgendamentoRecordRequest request){
        return mapper.paraResponse(
            repository.save(
                mapper.paraEntity(request)));        
    }

    public AgendamentoRecordResponse buscarAgendamentoPorId(Long id) throws Throwable{
        return mapper.paraResponse(repository.findById(id)
                    .orElseThrow(() -> new NameNotFoundException("Id não encontrado.")));
    }

    public void cancelarAgendamento(Long id) throws Throwable{
        Agendamento agendamento = repository.findById(id)
                    .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        repository.save(mapper.paraEntityCancelamento(agendamento));
    }

}
