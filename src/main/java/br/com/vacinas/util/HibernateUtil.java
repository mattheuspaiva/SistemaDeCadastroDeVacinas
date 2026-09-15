package br.com.vacinas.util;

import br.com.vacinas.model.Fabricante;
import br.com.vacinas.model.Vacina;
import br.com.vacinas.model.Lote;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = criarSessionFactory();

    private static SessionFactory criarSessionFactory() {

        try {

            Configuration configuration = new Configuration();

            configuration.configure("hibernate.cfg.xml");

            configuration.addAnnotatedClass(Fabricante.class);
            configuration.addAnnotatedClass(Vacina.class);
            configuration.addAnnotatedClass(Lote.class);

            return configuration.buildSessionFactory();

        } catch (Throwable erro) {

            System.err.println("Erro ao criar SessionFactory.");
            throw new ExceptionInInitializerError(erro);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}