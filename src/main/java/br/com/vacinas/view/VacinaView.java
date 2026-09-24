package br.com.vacinas.view;

import br.com.vacinas.service.VacinaService;
import br.com.vacinas.model.Vacina;

import java.util.List;
import java.util.Scanner;

public class VacinaView {
    private final Scanner leitor;
    private final VacinaService service;

    public VacinaView(Scanner leitor) {
        this.leitor = leitor;
        this.service = new VacinaService();
    }

    public void menu() {

        String opcao;

        do {

            System.out.println("\n----- VACINAS -----");
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

        System.out.print("Nome da vacina: ");
        String nome = leitor.nextLine();

        System.out.print("Tipo: ");
        String tipo = leitor.nextLine();

        System.out.print("Número de doses: ");
        int numeroDoses = Integer.parseInt(leitor.nextLine());

        System.out.print("ID do fabricante: ");
        Long fabricanteId = Long.parseLong(leitor.nextLine());

        service.cadastrar(nome, tipo, numeroDoses, fabricanteId);

        System.out.println("Vacina cadastrada com sucesso!");
    }

    private void listar() {

        List<Vacina> vacinas = service.listarTodos();

        if (vacinas.isEmpty()) {
            System.out.println("Nenhuma vacina cadastrada.");
            return;
        }

        for (Vacina vacina : vacinas) {
            System.out.println(vacina);
        }
    }

    private void buscar() {

        System.out.print("ID: ");
        Long id = Long.parseLong(leitor.nextLine());

        Vacina vacina = service.buscarPorId(id);

        if (vacina == null) {
            System.out.println("Vacina não encontrada.");
        } else {
            System.out.println(vacina);
        }
    }

    private void atualizar() {

        System.out.print("ID da vacina: ");
        Long id = Long.parseLong(leitor.nextLine());

        System.out.print("Nome: ");
        String nome = leitor.nextLine();

        System.out.print("Tipo: ");
        String tipo = leitor.nextLine();

        System.out.print("Número de doses: ");
        int numeroDoses = Integer.parseInt(leitor.nextLine());

        System.out.print("ID do fabricante: ");
        Long fabricanteId = Long.parseLong(leitor.nextLine());

        service.atualizar(id, nome, tipo, numeroDoses, fabricanteId);

        System.out.println("Vacina atualizada!");
    }

    private void excluir() {

        System.out.print("ID da vacina: ");
        Long id = Long.parseLong(leitor.nextLine());

        service.excluir(id);

        System.out.println("Vacina excluída!");
    }
}
