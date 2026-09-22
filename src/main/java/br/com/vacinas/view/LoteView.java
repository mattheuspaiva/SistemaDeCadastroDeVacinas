package br.com.vacinas.view;

import br.com.vacinas.model.Lote;
import br.com.vacinas.service.LoteService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class LoteView {

    private final Scanner scanner;
    private final LoteService service;

    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public LoteView(Scanner leitor) {
        this.scanner = leitor;
        this.service = new LoteService();
    }

    public void menu() {

        int opcao;

        do {

            System.out.println("\n===== LOTES =====");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Excluir");
            System.out.println("0 - Voltar");

            System.out.print("Escolha: ");
            opcao = Integer.parseInt(scanner.nextLine());

            try {

                switch (opcao) {

                    case 1 -> cadastrar();

                    case 2 -> listar();

                    case 3 -> buscar();

                    case 4 -> atualizar();

                    case 5 -> excluir();

                    case 0 -> System.out.println("Voltando...");

                    default -> System.out.println("Opção inválida.");
                }

            } catch (Exception erro) {

                System.out.println("Erro: " + erro.getMessage());
            }

        } while (opcao != 0);
    }

    private void cadastrar() {

        System.out.print("Número do lote: ");
        String numero = scanner.nextLine();

        System.out.print("Data de fabricação (DD/MM/AAAA): ");
        LocalDate dataFabricacao =
                LocalDate.parse(scanner.nextLine(), formatter);

        System.out.print("Data de validade (DD/MM/AAAA): ");
        LocalDate dataValidade =
                LocalDate.parse(scanner.nextLine(), formatter);

        System.out.print("Quantidade: ");
        int quantidade =
                Integer.parseInt(scanner.nextLine());

        System.out.print("ID da vacina: ");
        Long vacinaId =
                Long.parseLong(scanner.nextLine());

        service.cadastrar(
                numero,
                dataFabricacao,
                dataValidade,
                quantidade,
                vacinaId
        );

        System.out.println("Lote cadastrado com sucesso!");
    }

    private void listar() {

        List<Lote> lotes = service.listarTodos();

        if (lotes.isEmpty()) {
            System.out.println("Nenhum lote cadastrado.");
            return;
        }

        for (Lote lote : lotes) {
            System.out.println(lote);
        }
    }

    private void buscar() {

        System.out.print("ID: ");
        Long id = Long.parseLong(scanner.nextLine());

        Lote lote = service.buscarPorId(id);

        if (lote == null) {
            System.out.println("Lote não encontrado.");
        } else {
            System.out.println(lote);
        }
    }

    private void atualizar() {

        System.out.print("ID do lote: ");
        Long id = Long.parseLong(scanner.nextLine());

        System.out.print("Número do lote: ");
        String numero = scanner.nextLine();

        System.out.print("Data de fabricação (DD/MM/AAAA): ");
        LocalDate dataFabricacao =
                LocalDate.parse(scanner.nextLine(), formatter);

        System.out.print("Data de validade (DD/MM/AAAA): ");
        LocalDate dataValidade =
                LocalDate.parse(scanner.nextLine(), formatter);

        System.out.print("Quantidade: ");
        int quantidade =
                Integer.parseInt(scanner.nextLine());

        System.out.print("ID da vacina: ");
        Long vacinaId =
                Long.parseLong(scanner.nextLine());

        service.atualizar(
                id,
                numero,
                dataFabricacao,
                dataValidade,
                quantidade,
                vacinaId
        );

        System.out.println("Lote atualizado!");
    }

    private void excluir() {

        System.out.print("ID do lote: ");
        Long id = Long.parseLong(scanner.nextLine());

        service.excluir(id);

        System.out.println("Lote excluído!");
    }
}