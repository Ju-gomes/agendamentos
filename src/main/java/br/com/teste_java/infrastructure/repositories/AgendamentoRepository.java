package br.com.teste.teste.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.teste.teste.infrastructure.entities.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{

}
