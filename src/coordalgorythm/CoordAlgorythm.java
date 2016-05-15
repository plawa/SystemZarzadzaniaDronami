package coordalgorythm;

import com.teamdev.jxmaps.LatLng;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoordAlgorythm {

    private final ArrayList<Coordinates> coordinatesBase = new ArrayList<>();
    private final ArrayList<Coordinates> coordinatesNewBase = new ArrayList<>();
    private double odleglosc;

    public double getOdleglosc() {
        return odleglosc;
    }

    public void setOdleglosc(double odleglosc) {
        this.odleglosc = odleglosc;
    }

    public CoordAlgorythm() {
        this.odleglosc = 0.0003;
    }

    //jako parametr podajemy trzy punkty tworzące łamaną
    //punkt 'b' - środek łamanej
    public Coordinates calculateMiddleOffsetPoint(Coordinates a, Coordinates b, Coordinates c) {

        //** prosta a-b (12) **
        //obliczamy środek odcinka prostej
        double centerX = (a.getX() + b.getX()) / 2;
        double centerY = (a.getY() + b.getY()) / 2;
        //obliczamy wektor normalny dla prostej
        double vectorX = odleglosc;
        //inicjalizujemy zerem, które pozostanie jeśli poniższy warunek nie zostanie spełniony
        double vectorY = 0;
        //współczynnik kierunkowy dla prostej 1-2 (inicjalizowany zerem, jeśli poprzedni warunek nie zostanie spełniony)
        double A12 = 0;
        if (a.getX() != b.getX()) { //rozpatrujemy proste, które nie są pionowe
            vectorX = (a.getY() - b.getY()) / (a.getX() - b.getX());
            A12 = vectorX; //to też współczynnik kierunkowy prostej 1-2, który zachowujemy na później
            vectorY = -1.0;
            //normalizacja wektora normalnego (dlugość ma być równa '1')
            double length = sqrt(pow(vectorX, 2) + pow(vectorY, 2));
            vectorX = odleglosc * vectorX / length;
            vectorY = odleglosc * vectorY / length;
        }
        //przesunięcie punktu środkowego o obliczony wektor normalny
        Coordinates endPoint12;
        if (a.getX() > b.getX()) //kiedy wyznaczona prosta jest malejąca
        {
            endPoint12 = new Coordinates(centerX - vectorX, centerY - vectorY);
        } else {
            endPoint12 = new Coordinates(centerX + vectorX, centerY + vectorY);
        }
        //obliczenie nowego 'b' przesuniętej prostej
        double B12 = endPoint12.getY() - A12 * endPoint12.getX();
        //mamy już równanie prostej przesuniętej danymi a i b (A12 i B12)

        //** prosta b-c (23) **
        centerX = (b.getX() + c.getX()) / 2;
        centerY = (b.getY() + c.getY()) / 2;

        vectorX = odleglosc;
        vectorY = 0;
        double A23 = 0;
        if (b.getX() != c.getX()) {
            vectorX = (b.getY() - c.getY()) / (b.getX() - c.getX());
            A23 = vectorX; //to też współczynnik kierunkowy prostej 1-2, który zachowujemy na później
            vectorY = -1.0;
            //normalizacja wektora normalnego (dlugość ma być równa '1')
            double length = sqrt(pow(vectorX, 2) + pow(vectorY, 2));
            vectorX = odleglosc * vectorX / length;
            vectorY = odleglosc * vectorY / length;
        }

        Coordinates endPoint23;
        if (b.getX() > c.getX()) //kiedy wyznaczona prosta jest malejąca
        {
            endPoint23 = new Coordinates(centerX - vectorX, centerY - vectorY);
        } else {
            endPoint23 = new Coordinates(centerX + vectorX, centerY + vectorY);
        }
        double B23 = endPoint23.getY() - A23 * endPoint23.getX();
        //mamy już równanie prostej przesuniętej danymi b i c (A23 i B23)

        //teraz znajdujemy punkt wspólny tych dwóch obliczonych prostych
        double wspolneX;
        double wspolneY;
        if (A12 == A23) { //jeśli a są równe, to są to te same proste - jako punkt należy wziąć punkt uśredniony
            wspolneX = (endPoint12.getX() + endPoint23.getX()) / 2;
            wspolneY = (endPoint12.getY() + endPoint23.getY()) / 2;
        } else {
            wspolneX = (B23 - B12) / (A12 - A23);  //obliczamy X
            wspolneY = A12 * wspolneX + B12;   //i podstawiamy je do pierwszego równania
        }

        return new Coordinates(wspolneX, wspolneY);
    }

    public void runAlgorithm() {
        for (int i = 1; i < coordinatesBase.size() - 1; i++) {
            coordinatesNewBase.add(calculateMiddleOffsetPoint(coordinatesBase.get(i - 1), coordinatesBase.get(i),
                    coordinatesBase.get(i + 1)));
        }
    }

    public ArrayList<Coordinates> generateNewKMLFile(File file, double offset) {
        setOdleglosc(offset);

        Path newPath = Paths.get(file.getPath() + "new.kml");
        String koordynaty = "";
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("<coordinates>")) {
                    koordynaty = line.substring(17, line.length() - 14);
                    break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(CoordAlgorythm.class.getName()).log(Level.SEVERE, null, ex);
        }
        //parsowanie koordynatów z tekstu
        String[] koordynat = koordynaty.split(" ");

        for (String koord : koordynat) {
            String[] dane = koord.split(",");
            double x = Double.parseDouble(dane[0]);
            double y = Double.parseDouble(dane[1]);
            coordinatesBase.add(new Coordinates(x, y));
        }

        runAlgorithm();

        String newKoordynaty = "";
        for (Coordinates coord : coordinatesNewBase) {
            newKoordynaty += coord;
        }

        try {
            Charset charset = StandardCharsets.UTF_8;
            String content = new String(Files.readAllBytes(file.toPath()), charset);
            content = content.replaceFirst(koordynaty, newKoordynaty);
            Files.write(newPath, content.getBytes(charset));
        } catch (IOException ex) {
            Logger.getLogger(CoordAlgorythm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return coordinatesNewBase;
    }

    public static void main(String[] args) {
        CoordAlgorythm fasada = new CoordAlgorythm();

        Scanner stdin = new Scanner(System.in);
        System.out.println("Podaj nazwę pliku źródłowego (.kml): ");
        String fileName = stdin.nextLine();

        Path oldPath = Paths.get(fileName); //plik źródłowy

        fasada.generateNewKMLFile(oldPath.toFile(), 0.0005);

    }

}
