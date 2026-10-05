package br.com.vacinas.dao;

import br.com.vacinas.model.Vacina;
import br.com.vacinas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class VacinaDAO {

    public void salvar(Vacina vacina) {

        Transaction transacao = null;

        try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {

            transacao = sessao.beginTransaction();

            sessao.persist(vacina);

            transacao.commit();

        } catch (Exception erro) {

            if (transacao != null) {
                transacao.rollback();
            }

            throw erro;
        }
    }

    public Vacina buscarPorId(Long id) {

        try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {

            return sessao.get(Vacina.class, id);
        }
    }

    public List<Vacina> listarTodos() {

        try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {

            return sessao
                    .createQuery("FROM Vacina", Vacina.class)
                    .list();
        }
    }

    public void atualizar(Vacina vacina) {

        Transaction transacao = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transacao = session.beginTransaction();

            session.merge(vacina);

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

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transacao = session.beginTransaction();

            Vacina vacina = session.get(Vacina.class, id);

            if (vacina != null) {
                session.remove(vacina);
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