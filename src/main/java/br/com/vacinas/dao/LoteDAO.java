package br.com.vacinas.dao;

import br.com.vacinas.model.Lote;
import br.com.vacinas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LoteDAO {

    public void salvar(Lote lote) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(lote);

            transaction.commit();

        } catch (Exception erro) {

            if (transaction != null) {
                transaction.rollback();
            }

            throw erro;
        }
    }

    public Lote buscarPorId(Long id) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.get(Lote.class, id);
        }
    }

    public List<Lote> listarTodos() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session
                    .createQuery("FROM Lote", Lote.class)
                    .list();
        }
    }

    public void atualizar(Lote lote) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.merge(lote);

            transaction.commit();

        } catch (Exception erro) {

            if (transaction != null) {
                transaction.rollback();
            }

            throw erro;
        }
    }

    public void excluir(Long id) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Lote lote = session.get(Lote.class, id);

            if (lote != null) {
                session.remove(lote);
            }

            transaction.commit();

        } catch (Exception erro) {

            if (transaction != null) {
                transaction.rollback();
            }

            throw erro;
        }
    }
}