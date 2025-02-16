package co.edu.unicauca.mycompany.projects.main;

import co.edu.unicauca.mycompany.projects.access.Factory;
import co.edu.unicauca.mycompany.projects.access.ICompanyRepository;
import co.edu.unicauca.mycompany.projects.domain.services.CompanyService;
import co.edu.unicauca.mycompany.projects.presentation.GUIMenu;
import javax.swing.JFrame;

/**
 *
 * @author Ana Sofia Arango Yanza
 * @author Juan Diego Gomez Garces
 * @version 1.0
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

         /**
          * @param repository
         * Usar "ARRAYS" para usar el repositorio de arreglos
         * Usar "SQLITE" para usar el repositorio con SQLite
         * por defecto se encuentra en el repositorio con SQLite
         */
        ICompanyRepository repository = Factory.getInstance().getRepository("SQLITE");// Podria ir SQLITE
        CompanyService service = new CompanyService(repository);

        GUIMenu instance = new GUIMenu(service);
        instance.setExtendedState(JFrame.MAXIMIZED_BOTH);
        instance.setVisible(true);
    }

}
