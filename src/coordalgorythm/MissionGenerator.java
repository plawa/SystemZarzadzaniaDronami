/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coordalgorythm;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import nu.xom.*;
/**
 *
 * @author Michał
 */
public class MissionGenerator {
    
    private static ArrayList<Coordinates> coordinatesBase = new ArrayList<>();
     
     public MissionGenerator() {
    
    }
    public static void main(String[] args) {
        readCoordinatesFromKml();
     }
     //  public ArrayList<Coordinates> readCoordinatesFromKml(File file){
     public static void readCoordinatesFromKml(){
      
     File file = new File("C:\\Users\\Michał\\Downloads\\Mapa bez nazwy.kml");
     
      
         
         String koordynaty = "";
          Document kml = null;
        try  {
                  Builder builder = new Builder();
        kml = builder.build(file);
            
        
            
        } catch (IOException ex) {
       
        }
        catch (ParsingException ex){
            
            
        }
        
        Nodes nodes = kml.query("/kml/Document/name");
      //  System.out.println(nodes.get(0).getValue());
        
//        //parsowanie koordynatów z tekstu
//        String[] koordynat = koordynaty.split(" ");
//
//        for (String koord : koordynat) {
//            String[] dane = koord.split(",");
//            double x = Double.parseDouble(dane[0]);
//            double y = Double.parseDouble(dane[1]);
//            coordinatesBase.add(new Coordinates(x, y));
//        }
//
//        try {
//            Charset charset = StandardCharsets.UTF_8;
//            String content = new String(Files.readAllBytes(file.toPath()), charset);
//            content = content.replaceFirst(koordynaty, newKoordynaty);
//            Files.write(newPath, content.getBytes(charset));
//        } catch (IOException ex) {
//            Logger.getLogger(CoordAlgorythm.class.getName()).log(Level.SEVERE, null, ex);
//        }
     //   return coordinatesBase;
     }
     
     }

