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

    public void menu(){
        int opcao;

        do {
            System.out.println("\n===== Fabricantes ======");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Excluir");
            System.out.println("0 - Voltar");

            System.out.println("Escolha uma opcao: ");
            opcao = Integer.parseInt(leitor.nextLine());

            try{
                switch(opcao){
                    case 1 -> cadastrar();
                    case 2 -> listar();
                    case 3 -> buscar();
                    case 4 -> atualizar();
                    case 5 -> excluir();
                    case 0 -> System.out.println("Voltando...");
                    default -> System.out.println("Opcao invalida");
                }
            }
            catch (Exception erro){
                System.out.println("Erro: " + erro.getMessage());
            }
        }

        while (opcao != 0);
    }

    private void cadastrar(){
        System.out.println("Digite o nome do fabricante: ");
        String nome = leitor.nextLine();

        System.out.println("Pais do Fabricante: ");
        String pais = leitor.nextLine();

        service.cadastrar(nome,pais);

        System.out.println("Fabricante cadastrado com sucesso!");
    }

    private void listar(){
        List<Fabricante> fabricantes = service.listasTodos();

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
        int id = Integer.parseInt(leitor.nextLine());

        Fabricante fabricante = service.buscarPorId(id);
        if(fabricante == null){
            System.out.println("Fabricante não encontrado.");
        }else {
            System.out.println(fabricante);
        }
    }

    private void atualizar(){
        System.out.println("ID do Fabricante: ");
        int id = Integer.parseInt(leitor.nextLine());

        System.out.println("Novo nome: ");
        String nome = leitor.nextLine();

        System.out.println("Novo pais: ");
        String pais = leitor.nextLine();

        service.atualizar(id, nome, pais);
        System.out.println("Fabricante atualizado.");
    }

    private void excluir(){
     System.out.println("ID do Fabricante: ");
     int id = Integer.parseInt(leitor.nextLine());

     service.excluir(id);

     System.out.println("Fabricante excluido.");
    }
}
