package coordalgorythm;

import java.util.Locale;

public class Coordinates {
    private double x; //x (długość geograficzna)
    private double y;  //y (szerokość geograficzna)
    
    public Coordinates(){
        this.x = 0.0;
        this.y = 0.0;
    }
    
    public Coordinates(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    
    
    @Override
    public String toString(){
        return String.format(Locale.US, "%.5f,%5f,0.0 ", getX(), getY());
    }
}
