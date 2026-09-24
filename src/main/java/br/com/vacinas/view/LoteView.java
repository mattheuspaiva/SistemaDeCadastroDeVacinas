package br.com.vacinas.view;

import br.com.vacinas.model.Lote;
import br.com.vacinas.service.LoteService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class LoteView {

    private final Scanner leitor;
    private final LoteService service;

    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public LoteView(Scanner leitor) {
        this.leitor = leitor;
        this.service = new LoteService();
    }

    public void menu() {

        String opcao;

        do {

            System.out.println("\n----- LOTES -----");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Excluir");
            System.out.println("0 - Voltar");

            System.out.print("Escolha: ");
            opcao = leitor.nextLine();

            switch (opcao) {

                case "1":
                    cadastrar();
                    break;

                case "2":
                    listar();
                    break;

                case "3":
                    buscar();
                    break;

                case "4":
                    atualizar();
                    break;

                case "5":
                    excluir();
                    break;

                case "0":
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (!opcao.equals("0"));
    }

    private void cadastrar() {

        System.out.print("Número do lote: ");
        String numero = leitor.nextLine();

        System.out.print("Data de fabricação (DD/MM/AAAA): ");
        LocalDate dataFabricacao =
                LocalDate.parse(leitor.nextLine(), formatter);

        System.out.print("Data de validade (DD/MM/AAAA): ");
        LocalDate dataValidade =
                LocalDate.parse(leitor.nextLine(), formatter);

        System.out.print("Quantidade: ");
        int quantidade =
                Integer.parseInt(leitor.nextLine());

        System.out.print("ID da vacina: ");
        Long vacinaId =
                Long.parseLong(leitor.nextLine());

        service.cadastrar(numero, dataFabricacao, dataValidade, quantidade, vacinaId);

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
        Long id = Long.parseLong(leitor.nextLine());

        Lote lote = service.buscarPorId(id);

        if (lote == null) {
            System.out.println("Lote não encontrado.");
        } else {
            System.out.println(lote);
        }
    }

    private void atualizar() {

        System.out.print("ID do lote: ");
        Long id = Long.parseLong(leitor.nextLine());

        System.out.print("Número do lote: ");
        String numero = leitor.nextLine();

        System.out.print("Data de fabricação (DD/MM/AAAA): ");
        LocalDate dataFabricacao =
                LocalDate.parse(leitor.nextLine(), formatter);

        System.out.print("Data de validade (DD/MM/AAAA): ");
        LocalDate dataValidade =
                LocalDate.parse(leitor.nextLine(), formatter);

        System.out.print("Quantidade: ");
        int quantidade =
                Integer.parseInt(leitor.nextLine());

        System.out.print("ID da vacina: ");
        Long vacinaId =
                Long.parseLong(leitor.nextLine());

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
        Long id = Long.parseLong(leitor.nextLine());

        service.excluir(id);

        System.out.println("Lote excluído!");
    }
}