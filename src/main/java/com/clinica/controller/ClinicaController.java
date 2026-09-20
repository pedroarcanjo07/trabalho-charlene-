package com.clinica.controller;

import com.clinica.model.Paciente;
import com.clinica.service.ClinicaService;

import java.util.Scanner;

public class ClinicaController {
    private final ClinicaService clinicaService = new ClinicaService();
    private final Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        clinicaService.cadastrarPacienteExemplo();

        while (true) {
            System.out.println("\n=== Sistema de Atendimento Clínico ===");
            System.out.println("1 - Buscar paciente por CPF");
            System.out.println("2 - Cadastrar novo paciente");
            System.out.println("3 - Registrar atendimento");
            System.out.println("4 - Ver histórico do paciente");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    buscarPaciente();
                    break;
                case "2":
                    cadastrarPaciente();
                    break;
                case "3":
                    registrarAtendimento();
                    break;
                case "4":
                    mostrarHistorico();
                    break;
                case "5":
                    System.out.println("Encerrando sistema...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void buscarPaciente() {
        System.out.print("Informe o CPF: ");
        String cpf = scanner.nextLine();

        if (!clinicaService.validarCpf(cpf)) {
            System.out.println("CPF inválido.");
            return;
        }

        Paciente paciente = clinicaService.buscarPaciente(cpf);
        if (paciente == null) {
            System.out.println("Paciente não encontrado. Cadastre primeiro.");
            return;
        }

        System.out.println("Paciente encontrado: " + paciente.getNome());
    }

    private void cadastrarPaciente() {
        Paciente paciente = clinicaService.cadastrarPaciente();
        if (paciente != null) {
            System.out.println("Paciente cadastrado com sucesso: " + paciente.getNome());
        }
    }

    private void registrarAtendimento() {
        System.out.print("Informe o CPF do paciente: ");
        String cpf = scanner.nextLine();

        if (!clinicaService.validarCpf(cpf)) {
            System.out.println("CPF inválido.");
            return;
        }

        Paciente paciente = clinicaService.buscarPaciente(cpf);
        if (paciente == null) {
            System.out.println("Paciente não encontrado. Realize o cadastro primeiro.");
            return;
        }

        clinicaService.registrarAtendimento(paciente);
    }

    private void mostrarHistorico() {
        System.out.print("Informe o CPF do paciente: ");
        String cpf = scanner.nextLine();

        if (!clinicaService.validarCpf(cpf)) {
            System.out.println("CPF inválido.");
            return;
        }

        Paciente paciente = clinicaService.buscarPaciente(cpf);
        clinicaService.exibirHistorico(paciente);
    }
}
