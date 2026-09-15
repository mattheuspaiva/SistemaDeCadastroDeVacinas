package br.com.vacinas.service;

import br.com.vacinas.dao.FabricanteDAO;
import br.com.vacinas.dao.VacinaDAO;
import br.com.vacinas.model.Fabricante;
import br.com.vacinas.model.Vacina;

import java.util.List;

public class VacinaService {

    private final VacinaDAO vacinaDAO = new VacinaDAO();
    private final FabricanteDAO fabricanteDAO = new FabricanteDAO();

    public void cadastrar(
            String nome,
            String tipo,
            int numeroDoses,
            Long fabricanteId) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome da vacina é obrigatório.");
        }

        if (numeroDoses <= 0) {
            throw new IllegalArgumentException(
                    "O número de doses deve ser maior que zero."
            );
        }

        Fabricante fabricante = fabricanteDAO.buscarPorId(fabricanteId);

        if (fabricante == null) {
            throw new IllegalArgumentException(
                    "Fabricante não encontrado."
            );
        }

        Vacina vacina = new Vacina(
                nome,
                tipo,
                numeroDoses,
                fabricante
        );

        vacinaDAO.salvar(vacina);
    }

    public Vacina buscarPorId(Long id) {
        return vacinaDAO.buscarPorId(id);
    }

    public List<Vacina> listarTodos() {
        return vacinaDAO.listarTodos();
    }

    public void atualizar(
            Long id,
            String nome,
            String tipo,
            int numeroDoses,
            Long fabricanteId) {

        Vacina vacina = vacinaDAO.buscarPorId(id);

        if (vacina == null) {
            throw new IllegalArgumentException(
                    "Vacina não encontrada."
            );
        }

        Fabricante fabricante = fabricanteDAO.buscarPorId(fabricanteId);

        if (fabricante == null) {
            throw new IllegalArgumentException(
                    "Fabricante não encontrado."
            );
        }

        vacina.setNome(nome);
        vacina.setTipo(tipo);
        vacina.setNumeroDoses(numeroDoses);
        vacina.setFabricante(fabricante);

        vacinaDAO.atualizar(vacina);
    }

    public void excluir(Long id) {
        vacinaDAO.excluir(id);
    }
}