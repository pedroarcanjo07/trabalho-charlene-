package com.clinica.service;

import com.clinica.model.Atendimento;
import com.clinica.model.Paciente;
import com.clinica.repository.PacienteRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ClinicaService {
    private final PacienteRepository repository = new PacienteRepository();
    private final Scanner scanner = new Scanner(System.in);

    public Paciente buscarPaciente(String cpf) {
        return repository.buscarPorCpf(cpf);
    }

    public Paciente cadastrarPaciente() {
        System.out.println("\nCadastro de novo paciente");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        if (!validarCpf(cpf)) {
            System.out.println("CPF inválido.");
            return null;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Data de nascimento (dd/mm/aaaa): ");
        String dataNascimento = scanner.nextLine();
        System.out.print("Alergias: ");
        String alergias = scanner.nextLine();

        Paciente paciente = new Paciente(cpf, nome, dataNascimento, alergias);
        repository.salvar(paciente);
        return paciente;
    }

    public void registrarAtendimento(Paciente paciente) {
        System.out.println("\nRegistro de sintomas e triagem");
        System.out.print("Digite os sintomas separados por vírgula: ");
        String sintomasInput = scanner.nextLine();

        List<String> sintomas = Arrays.stream(sintomasInput.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();

        System.out.println("Selecione a urgência:");
        System.out.println("1 - Emergência");
        System.out.println("2 - Muito Urgente");
        System.out.println("3 - Urgente");
        System.out.println("4 - Pouco Urgente");
        System.out.println("5 - Não Urgente");
        System.out.print("Opção: ");

        int opcao = Integer.parseInt(scanner.nextLine());
        String urgencia = triarAtendimento(opcao, sintomas);

        System.out.print("Observações: ");
        String observacao = scanner.nextLine();
        System.out.print("Prescrição: ");
        String prescricao = scanner.nextLine();

        String resumo = gerarResumoMedico(paciente, sintomas, urgencia);

        Atendimento atendimento = new Atendimento(
                paciente.getCpf(),
                LocalDate.now(),
                sintomas,
                urgencia,
                observacao,
                prescricao,
                resumo
        );

        paciente.adicionarAtendimento(atendimento);
        System.out.println("\nAtendimento registrado com sucesso.");
        System.out.println("Resumo médico: " + resumo);
    }

    public String triarAtendimento(int nivel, List<String> sintomas) {
        String risco = "Não Urgente";

        if (sintomas.contains("dor no peito") || sintomas.contains("dificuldade para respirar") || sintomas.contains("sangramento intenso")) {
            risco = "Emergência";
        } else if (nivel == 1) {
            risco = "Emergência";
        } else if (nivel == 2) {
            risco = "Muito Urgente";
        } else if (nivel == 3) {
            risco = "Urgente";
        } else if (nivel == 4) {
            risco = "Pouco Urgente";
        } else if (nivel == 5) {
            risco = "Não Urgente";
        }

        return risco;
    }

    public String gerarResumoMedico(Paciente paciente, List<String> sintomas, String urgencia) {
        StringBuilder resumo = new StringBuilder();
        resumo.append("Paciente: ").append(paciente.getNome())
                .append(" | CPF: ").append(paciente.getCpf())
                .append(" | Alergias: ").append(paciente.getAlergias())
                .append(" | Sintomas: ").append(String.join(", ", sintomas))
                .append(" | Urgência: ").append(urgencia);

        String sugestao = sugerirConduta(sintomas, paciente.getAlergias());
        resumo.append(" | Sugestão: ").append(sugestao);

        return resumo.toString();
    }

    public String sugerirConduta(List<String> sintomas, String alergias) {
        if (alergias != null && !alergias.isBlank()) {
            return "Alerta de alergia registrada. Revisar medicações antes da prescrição.";
        }

        if (sintomas.contains("febre") && sintomas.contains("tosse")) {
            return "Considerar avaliação respiratória e possível infecção. Solicitar exames complementares.";
        }

        if (sintomas.contains("dor abdominal") && sintomas.contains("náusea")) {
            return "Avaliar quadro gástrico ou gastrointestinal; considerar exames específicos conforme evolução.";
        }

        if (sintomas.contains("dor no peito") || sintomas.contains("desmaio")) {
            return "Urgência cardiológica. Encaminhar avaliação imediata e monitoramento.";
        }

        return "Observação clínica e reavaliação conforme evolução do quadro.";
    }

    public boolean validarCpf(String cpf) {
        if (cpf == null) {
            return false;
        }

        String digits = cpf.replaceAll("\\D", "");
        if (digits.length() != 11) {
            return false;
        }

        if (digits.chars().distinct().count() == 1) {
            return false;
        }

        int soma = 0;
        int peso = 10;
        for (int i = 0; i < 9; i++) {
            soma += (digits.charAt(i) - '0') * peso;
            peso--;
        }

        int digito1 = 11 - (soma % 11);
        if (digito1 >= 10) {
            digito1 = 0;
        }

        if ((digits.charAt(9) - '0') != digito1) {
            return false;
        }

        soma = 0;
        peso = 11;
        for (int i = 0; i < 10; i++) {
            soma += (digits.charAt(i) - '0') * peso;
            peso--;
        }

        int digito2 = 11 - (soma % 11);
        if (digito2 >= 10) {
            digito2 = 0;
        }

        return (digits.charAt(10) - '0') == digito2;
    }

    public void exibirHistorico(Paciente paciente) {
        if (paciente == null) {
            System.out.println("Paciente não encontrado.");
            return;
        }

        System.out.println("\nHistórico de atendimentos de " + paciente.getNome());
        if (paciente.getHistorico().isEmpty()) {
            System.out.println("Nenhum atendimento registrado até o momento.");
            return;
        }

        for (int i = 0; i < paciente.getHistorico().size(); i++) {
            Atendimento atendimento = paciente.getHistorico().get(i);
            System.out.println("\nAtendimento " + (i + 1));
            System.out.println("Data: " + atendimento.getData());
            System.out.println("Sintomas: " + String.join(", ", atendimento.getSintomas()));
            System.out.println("Urgência: " + atendimento.getUrgencia());
            System.out.println("Resumo: " + atendimento.getResumoMedico());
            System.out.println("Prescrição: " + atendimento.getPrescricao());
        }
    }

    public void cadastrarPacienteExemplo() {
        Paciente p1 = new Paciente("12345678909", "Maria Souza", "15/05/1990", "Penicilina");
        Paciente p2 = new Paciente("98765432100", "João Pereira", "20/11/1985", "Nenhuma");
        repository.salvar(p1);
        repository.salvar(p2);
    }
}
