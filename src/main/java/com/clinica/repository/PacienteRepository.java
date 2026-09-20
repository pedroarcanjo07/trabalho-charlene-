package com.clinica.repository;

import com.clinica.model.Paciente;

import java.util.HashMap;
import java.util.Map;

public class PacienteRepository {
    private final Map<String, Paciente> pacientes = new HashMap<>();

    public Paciente buscarPorCpf(String cpf) {
        return pacientes.get(cpf);
    }

    public void salvar(Paciente paciente) {
        pacientes.put(paciente.getCpf(), paciente);
    }

    public Map<String, Paciente> getPacientes() {
        return pacientes;
    }
}
