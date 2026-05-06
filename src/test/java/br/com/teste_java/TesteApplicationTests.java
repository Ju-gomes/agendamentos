/* */
package br.com.teste.teste;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.teste.teste.business.service.AgendamentoService;
import br.com.teste.teste.controller.AgendamentoController;
import br.com.teste.teste.controller.dto.request.AgendamentoRecordRequest;
import br.com.teste.teste.controller.dto.response.AgendamentoRecordResponse;
import br.com.teste.teste.infrastructure.enums.StatusNotificacaoEnum;

import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AgendamentoControllerTest {

	@InjectMocks
	AgendamentoController agendamentoController;

	@Mock
	AgendamentoService service;

	private AgendamentoRecordRequest request;
	private AgendamentoRecordResponse response;

	private MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	@BeforeEach 
	void setUp(){

		mockMvc = MockMvcBuilders.standaloneSetup(agendamentoController).build();
		objectMapper.registerModule(new JavaTimeModule());

		request = new AgendamentoRecordRequest("email@email",
						 					"55654654654",
														"Retornar a loja com urgência",
														LocalDateTime.of(2025, 1, 2, 00, 00, 00));

		response = new AgendamentoRecordResponse(1L,
												"email@email",
						 						"55654654654",
												"Retornar a loja com urgência",
												LocalDateTime.of(2025, 1, 2, 00, 00, 00),
												StatusNotificacaoEnum.AGENDADO);

	}


	/**
	 * @throws Exception 
	 */
	@Test
	void deveCriarAgendamentoComSucesso() throws Exception{
		when(service.salvar(any(AgendamentoRecordRequest.class))).thenReturn(response);

		mockMvc.perform(post("/agendamento")
			   .contentType(MediaType.APPLICATION_JSON)
			   .content(objectMapper.writeValueAsBytes(response)))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.id").value(1L))
			   .andExpect(jsonPath("$.emailDestinatario").value(response.emailDestinatario()))
			   .andExpect(jsonPath("$.telefoneDestinatario").value(response.telefoneDestinatario()))
			   .andExpect(jsonPath("$.mensagem").value(response.mensagem()))
			   .andExpect(jsonPath("$.dataHoraEnvio").value(response.dataHoraEnvio().format(formatter)))
			   .andExpect(jsonPath("$.statusNotificacao").value("AGENDADO"));

		verify(service, times(1)).salvar(request);

			   
			 
	}












}

