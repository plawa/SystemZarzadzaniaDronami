
package controllers;

import database.Drony;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Piotrek
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("coordAlgorythmPU");
        DronyJpaController controler = new DronyJpaController(entityManagerFactory);
        List<Drony> dronyEntities = controler.findDronyEntities();
        Drony dron = dronyEntities.get(0);
        System.out.println(dron.getPrzebieg());
    }
    
}
