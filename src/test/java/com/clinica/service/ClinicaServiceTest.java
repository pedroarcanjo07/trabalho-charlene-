package com.clinica.service;

import com.clinica.model.Paciente;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClinicaServiceTest {

    private final ClinicaService service = new ClinicaService();

    @Test
    void deveValidarCpfValido() {
        assertTrue(service.validarCpf("12345678909"));
    }

    @Test
    void deveRejeitarCpfInvalido() {
        assertFalse(service.validarCpf("11111111111"));
    }

    @Test
    void deveClassificarEmergenciaQuandoSintomaCritico() {
        String risco = service.triarAtendimento(5, List.of("dor no peito", "desmaio"));
        assertEquals("Emergência", risco);
    }

    @Test
    void deveGerarResumoComAlertaDeAlergia() {
        Paciente paciente = new Paciente("12345678909", "Ana", "10/01/1990", "Penicilina");
        String resumo = service.gerarResumoMedico(paciente, List.of("febre", "tosse"), "Urgente");
        assertTrue(resumo.contains("Alerta de alergia registrada"));
    }

    @Test
    void deveCadastrarPaciente() {
        Paciente paciente = new Paciente("99988877766", "Carlos", "01/02/2000", "Nenhuma");
        assertNotNull(paciente);
        assertEquals("Carlos", paciente.getNome());
    }
}
