package br.com.vacinas.dao;

import br.com.vacinas.model.Fabricante;
import br.com.vacinas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FabricanteDAO {

    public void salvar(Fabricante fabricante) {

        Transaction transacao = null;

        try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {

            transacao = sessao.beginTransaction();

            sessao.persist(fabricante);

            transacao.commit();

        } catch (Exception erro) {

            if (transacao != null) {
                transacao.rollback();
            }

            throw erro;
        }
    }

    public Fabricante buscarPorId(Long id) {

        try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {

            return sessao.get(Fabricante.class, id);
        }
    }

    public List<Fabricante> listarTodos() {

        try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {

            return sessao
                    .createQuery("FROM Fabricante", Fabricante.class)
                    .list();
        }
    }

    public void atualizar(Fabricante fabricante) {

        Transaction transacao = null;

        try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {

            transacao = sessao.beginTransaction();

            sessao.merge(fabricante);

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

            Fabricante fabricante = sessao.get(Fabricante.class, id);

            if (fabricante != null) {
                sessao.remove(fabricante);
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