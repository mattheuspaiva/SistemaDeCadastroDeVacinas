package br.com.vacinas.view;

import br.com.vacinas.model.Fabricante;
import br.com.vacinas.service.FabricanteService;

import java.util.List;
import java.util.Scanner;

public class FabricanteView {
    private final Scanner leitor;
    private final FabricanteService service;

    public FabricanteView(Scanner leitor) {
        this.leitor = leitor;
        this.service = new FabricanteService();
    }

    public void menu() {

        String opcao;

        do {
            System.out.println("\n----- FABRICANTES -----");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Excluir");
            System.out.println("0 - Voltar");

            System.out.print("Escolha uma opcao: ");
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
                    System.out.println("Opcao invalida!");
                    break;
            }

        } while (!opcao.equals("0"));
    }

    private void cadastrar(){
        System.out.println("Digite o nome do fabricante: ");
        String nome = leitor.nextLine();

        System.out.println("País do Fabricante: ");
        String pais = leitor.nextLine();

        service.cadastrar(nome,pais);

        System.out.println("Fabricante cadastrado com sucesso!");
    }

    private void listar(){
        List<Fabricante> fabricantes = service.listarTodos();

        if(fabricantes.isEmpty()){
            System.out.println("Nenhum fabricante encontrado!");
            return;
        }

        for(Fabricante fabricante : fabricantes){
            System.out.println(fabricante);
        }
    }

    private void buscar(){
        System.out.println("ID do Fabricante: ");
        Long id = Long.parseLong(leitor.nextLine());

        Fabricante fabricante = service.buscarPorId(id);
        if(fabricante == null){
            System.out.println("Fabricante não encontrado.");
        }else {
            System.out.println(fabricante);
        }
    }

    private void atualizar(){
        System.out.println("ID do Fabricante: ");
        Long id = Long.parseLong(leitor.nextLine());

        System.out.println("Novo nome: ");
        String nome = leitor.nextLine();

        System.out.println("Novo pais: ");
        String pais = leitor.nextLine();

        service.atualizar(id, nome, pais);
        System.out.println("Fabricante atualizado.");
    }

    private void excluir(){
     System.out.println("ID do Fabricante: ");
     Long id = Long.parseLong(leitor.nextLine());

     service.excluir(id);

     System.out.println("Fabricante excluido.");
    }
}
