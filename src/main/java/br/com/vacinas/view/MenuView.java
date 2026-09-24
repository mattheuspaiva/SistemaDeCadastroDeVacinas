package br.com.vacinas.view;

import java.util.Scanner;

public class MenuView {

    private final Scanner leitor;

    private final FabricanteView fabricanteView;
    private final VacinaView vacinaView;
    private final LoteView loteView;

    public MenuView() {
        leitor = new Scanner(System.in);

        fabricanteView = new FabricanteView(leitor);
        vacinaView = new VacinaView(leitor);
        loteView = new LoteView(leitor);
    }

    public void exibir() {

        String opcao;

        do {

            System.out.println();
            System.out.println("     SISTEMA DE CADASTRO DE      ");
            System.out.println("             VACINAS             ");
            System.out.println("---------------------------------");
            System.out.println("1 - Fabricantes");
            System.out.println("2 - Vacinas");
            System.out.println("3 - Lotes");
            System.out.println("0 - Sair");
            System.out.println("---------------------------------");

            System.out.print("Escolha: ");
            opcao = leitor.nextLine().trim();

            switch (opcao) {

                case "1":
                    fabricanteView.menu();
                    break;

                case "2":
                    vacinaView.menu();
                    break;

                case "3":
                    loteView.menu();
                    break;

                case "0":
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (!opcao.equals("0"));

        leitor.close();
    }
}

