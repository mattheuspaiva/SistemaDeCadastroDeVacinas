package br.com.vacinas;

import br.com.vacinas.util.HibernateUtil;
import br.com.vacinas.view.MenuView;

public class Main {
    public static void main(String[] args) {
        try {
            MenuView menuView = new MenuView();
            menuView.exibir();

        } finally {
            HibernateUtil.shutdown();
        }
    }
}