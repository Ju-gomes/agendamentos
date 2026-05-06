package br.com.teste.teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.teste.business.service.AgendamentoService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.teste.teste.controller.dto.request.AgendamentoRecordRequest;
import br.com.teste.teste.controller.dto.response.AgendamentoRecordResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequiredArgsConstructor
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;    

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AgendamentoRecordResponse> cadastrar(@RequestBody AgendamentoRecordRequest request) {
        return ResponseEntity.ok(service.salvar(request));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AgendamentoRecordResponse> buscarAgendamentoPorId(@PathVariable("id") Long id) throws Throwable {
        return ResponseEntity.ok(service.buscarAgendamentoPorId(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AgendamentoRecordResponse> cancelarAgendamento(@PathVariable("id") Long id) throws Throwable {
        return ResponseEntity.ok(service.buscarAgendamentoPorId(id));
    }
    
    


















}
