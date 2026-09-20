package com.clinica.model;

import java.time.LocalDate;
import java.util.List;

public class Atendimento {
    private String cpfPaciente;
    private LocalDate data;
    private List<String> sintomas;
    private String urgencia;
    private String observacao;
    private String prescricao;
    private String resumoMedico;

    public Atendimento(String cpfPaciente, LocalDate data, List<String> sintomas, String urgencia,
                       String observacao, String prescricao, String resumoMedico) {
        this.cpfPaciente = cpfPaciente;
        this.data = data;
        this.sintomas = sintomas;
        this.urgencia = urgencia;
        this.observacao = observacao;
        this.prescricao = prescricao;
        this.resumoMedico = resumoMedico;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public LocalDate getData() {
        return data;
    }

    public List<String> getSintomas() {
        return sintomas;
    }

    public String getUrgencia() {
        return urgencia;
    }

    public String getObservacao() {
        return observacao;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public String getResumoMedico() {
        return resumoMedico;
    }
}
