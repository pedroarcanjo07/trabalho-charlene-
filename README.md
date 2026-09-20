# Sistema de Atendimento Clínico

## Visão geral

Este projeto implementa um sistema de apoio ao atendimento clínico com foco em:

- recepção e triagem
- consulta de pacientes por CPF
- registro de sintomas
- classificação de risco pelo Protocolo de Manchester
- painel médico com histórico e resumo automático
- suporte à decisão com regras condicionais e alertas de alergias

## Requisitos atendidos

- Entrada e saída de dados via terminal
- Validação do CPF
- Estruturas de repetição com menu iterativo
- Listas e registros de histórico
- Modularização por funções/métodos
- Backend em Java

## Estrutura do projeto

- `src/main/java/com/clinica/model` — classes de domínio
- `src/main/java/com/clinica/repository` — persistência em memória
- `src/main/java/com/clinica/service` — regras de negócio
- `src/main/java/com/clinica/controller` — fluxo de aplicação
- `src/main/java/com/clinica/app` — ponto de entrada
- `src/test/java` — testes unitários

## Como executar

```bash
mvn clean test
mvn exec:java -Dexec.mainClass=com.clinica.app.Main
```

Se `exec-maven-plugin` não estiver configurado, use:

```bash
mvn package
java -cp target/classes com.clinica.app.Main
```

## Fluxo principal

1. Recepção consulta paciente pelo CPF.
2. Se não existir, cadastra novo paciente.
3. O usuário registra sintomas e observações.
4. O sistema classifica o risco pelo Protocolo de Manchester.
5. O médico visualiza o histórico e gera o resumo.
6. O sistema sugere diagnósticos e condutas prováveis.

## Funções principais

- `buscarPaciente()`
- `triarAtendimento()`
- `gerarResumoMedico()`

## Observações

A versão atual usa armazenamento em memória para facilitar a execução local e demonstração didática. O desafio extra de banco relacional pode ser evoluído para uma implementação com JDBC/SQL futuramente.
