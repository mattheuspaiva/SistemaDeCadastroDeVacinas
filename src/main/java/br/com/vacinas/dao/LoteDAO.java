package br.com.vacinas.dao;

import br.com.vacinas.model.Lote;
import br.com.vacinas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LoteDAO {

    public void salvar(Lote lote) {

        Transaction transacao = null;

        try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {

            transacao = sessao.beginTransaction();

            sessao.persist(lote);

            transacao.commit();

        } catch (Exception erro) {

            if (transacao != null) {
                transacao.rollback();
            }

            throw erro;
        }
    }

    public Lote buscarPorId(Long id) {

        try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {

            return sessao.get(Lote.class, id);
        }
    }

    public List<Lote> listarTodos() {

        try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {

            return sessao
                    .createQuery("FROM Lote", Lote.class)
                    .list();
        }
    }

    public void atualizar(Lote lote) {

        Transaction transacao = null;

        try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {

            transacao = sessao.beginTransaction();

            sessao.merge(lote);

            transacao.commit();

        } catch (Exception erro) {

            if (transacao != null) {
                transacao.rollback();
            }

            throw erro;
        }
    }

    public void excluir(Long id) {

        Transaction transacao = null;

        try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {

            transacao = sessao.beginTransaction();

            Lote lote = sessao.get(Lote.class, id);

            if (lote != null) {
                sessao.remove(lote);
            }

            transacao.commit();

        } catch (Exception erro) {

            if (transacao != null) {
                transacao.rollback();
            }

            throw erro;
        }
    }
}