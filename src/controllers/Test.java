
package controllers;

import database.Drony;
import database.Stacja;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("coordAlgorythmPU");
//        DronyJpaController controler = new DronyJpaController(entityManagerFactory);
//        List<Drony> dronyEntities = controler.findDronyEntities();
//        Drony dron = dronyEntities.get(0);
//        System.out.println(dron.getPrzebieg());
//        
//        Stacja nowaStacja = new Stacja();
//        nowaStacja.setId(1);
//        
//        
//        
//        Drony nowyDron = new Drony();
//        nowyDron.setId(3);
//        nowyDron.setCzyAdokowany(Short.parseShort("0"));
//        nowyDron.setIDstacji(nowaStacja);
//        try {
//            controler.create(nowyDron);
//        } catch (Exception ex) {
//            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//        }
        DatabaseController kontroler = new DatabaseController();
        List<Drony> drony = kontroler.getAllDrony();
        for (Drony dron : drony){
            System.out.println(dron);
        }
        
    }
    
}
