package br.com.vacinas.service;

import br.com.vacinas.dao.LoteDAO;
import br.com.vacinas.dao.VacinaDAO;
import br.com.vacinas.model.Lote;
import br.com.vacinas.model.Vacina;

import java.time.LocalDate;
import java.util.List;

public class LoteService {

    private final LoteDAO loteDAO = new LoteDAO();
    private final VacinaDAO vacinaDAO = new VacinaDAO();

    public void cadastrar(
            String numero,
            LocalDate dataFabricacao,
            LocalDate dataValidade,
            int quantidade,
            Long vacinaId) {

        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException(
                    "O número do lote é obrigatório."
            );
        }

        if (quantidade <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade deve ser maior que zero."
            );
        }

        if (dataValidade.isBefore(dataFabricacao)) {
            throw new IllegalArgumentException(
                    "A validade não pode ser anterior à fabricação."
            );
        }

        Vacina vacina = vacinaDAO.buscarPorId(vacinaId);

        if (vacina == null) {
            throw new IllegalArgumentException(
                    "Vacina não encontrada."
            );
        }

        Lote lote = new Lote(
                numero,
                dataFabricacao,
                dataValidade,
                quantidade,
                vacina
        );

        loteDAO.salvar(lote);
    }

    public List<Lote> listarTodos() {
        return loteDAO.listarTodos();
    }

    public Lote buscarPorId(Long id) {
        return loteDAO.buscarPorId(id);
    }

    public void atualizar(
            Long id,
            String numero,
            LocalDate dataFabricacao,
            LocalDate dataValidade,
            int quantidade,
            Long vacinaId) {

        Lote lote = loteDAO.buscarPorId(id);

        if (lote == null) {
            throw new IllegalArgumentException(
                    "Lote não encontrado."
            );
        }

        Vacina vacina = vacinaDAO.buscarPorId(vacinaId);

        if (vacina == null) {
            throw new IllegalArgumentException(
                    "Vacina não encontrada."
            );
        }

        lote.setNumero(numero);
        lote.setDataFabricacao(dataFabricacao);
        lote.setDataValidade(dataValidade);
        lote.setQuantidade(quantidade);
        lote.setVacina(vacina);

        loteDAO.atualizar(lote);
    }

    public void excluir(Long id) {
        loteDAO.excluir(id);
    }
}