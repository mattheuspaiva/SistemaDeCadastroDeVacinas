package br.com.vacinas.dao;

import br.com.vacinas.model.Fabricante;
import br.com.vacinas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FabricanteDAO {

    public void salvar(Fabricante fabricante) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(fabricante);

            transaction.commit();

        } catch (Exception erro) {

            if (transaction != null) {
                transaction.rollback();
            }

            throw erro;
        }
    }

    public Fabricante buscarPorId(Long id) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.get(Fabricante.class, id);
        }
    }

    public List<Fabricante> listarTodos() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session
                    .createQuery("FROM Fabricante", Fabricante.class)
                    .list();
        }
    }

    public void atualizar(Fabricante fabricante) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.merge(fabricante);

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

            Fabricante fabricante = session.get(Fabricante.class, id);

            if (fabricante != null) {
                session.remove(fabricante);
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