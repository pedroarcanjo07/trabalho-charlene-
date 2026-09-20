package com.clinica.model;

import java.util.ArrayList;
import java.util.List;

public class Paciente {
    private String cpf;
    private String nome;
    private String dataNascimento;
    private String alergias;
    private List<Atendimento> historico;

    public Paciente(String cpf, String nome, String dataNascimento, String alergias) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.alergias = alergias;
        this.historico = new ArrayList<>();
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getAlergias() {
        return alergias;
    }

    public List<Atendimento> getHistorico() {
        return historico;
    }

    public void adicionarAtendimento(Atendimento atendimento) {
        historico.add(atendimento);
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", alergias='" + alergias + '\'' +
                '}';
    }
}
