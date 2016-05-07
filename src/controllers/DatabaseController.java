
package controllers;

import database.Drony;
import database.Koordynaty;
import database.Stacja;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Piotrek
 */
public class DatabaseController {

    private final String persistenceName = "coordAlgorythmPU";
    private EntityManagerFactory entityManagerFactory = null;
    private DronyJpaController dronyControler = null;
    private StacjaJpaController stacjaControler = null;
    
    public DatabaseController() {
        entityManagerFactory = Persistence.createEntityManagerFactory(persistenceName);
        dronyControler = new DronyJpaController(entityManagerFactory);
        stacjaControler = new StacjaJpaController(entityManagerFactory);
    }

    public List<Drony> getAllDrony(){
        return dronyControler.findDronyEntities();
    }
    
    public void addDron(){
       // Drony nowyDron = new Drony()
    }

    public List<Stacja> getAllStacje() {
        return stacjaControler.findStacjaEntities();
    }
    
    public List<Koordynaty> getKoordynatyDrona(Drony dron){
        //TODO!
        return null;
    }
}
