package br.com.vacinas.dao;

import br.com.vacinas.model.Vacina;
import br.com.vacinas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class VacinaDAO {

    public void salvar(Vacina vacina) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(vacina);

            transaction.commit();

        } catch (Exception erro) {

            if (transaction != null) {
                transaction.rollback();
            }

            throw erro;
        }
    }

    public Vacina buscarPorId(Long id) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.get(Vacina.class, id);
        }
    }

    public List<Vacina> listarTodos() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session
                    .createQuery("FROM Vacina", Vacina.class)
                    .list();
        }
    }

    public void atualizar(Vacina vacina) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.merge(vacina);

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

            Vacina vacina = session.get(Vacina.class, id);

            if (vacina != null) {
                session.remove(vacina);
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