package br.com.vacinas.service;

import br.com.vacinas.dao.FabricanteDAO;
import br.com.vacinas.model.Fabricante;

import java.util.List;

public class FabricanteService {

    private final FabricanteDAO dao = new FabricanteDAO();

    public void cadastrar(String nome, String pais) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do fabricante é obrigatório.");
        }

        Fabricante fabricante = new Fabricante(nome, pais);

        dao.salvar(fabricante);
    }

    public Fabricante buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }

    public List<Fabricante> listarTodos() {
        return dao.listarTodos();
    }

    public void atualizar(Long id, String nome, String pais) {

        Fabricante fabricante = dao.buscarPorId(id);

        if (fabricante == null) {
            throw new IllegalArgumentException("Fabricante não encontrado.");
        }

        fabricante.setNome(nome);
        fabricante.setPais(pais);

        dao.atualizar(fabricante);
    }

    public void excluir(Long id) {
        dao.excluir(id);
    }
}