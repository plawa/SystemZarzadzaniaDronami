package coordalgorythm;

import com.teamdev.jxmaps.ControlPosition;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.MapViewOptions;
import com.teamdev.jxmaps.Polyline;
import com.teamdev.jxmaps.PolylineOptions;
import com.teamdev.jxmaps.swing.MapView;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author Piotrek
 */
public class GoogleMaps extends MapView {

    public GoogleMaps(MapViewOptions options, ArrayList<Coordinates> newCoords) {
        super(options);
        setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
                // Check if the map is loaded correctly
                if (status == MapStatus.MAP_STATUS_OK) {
                    // Getting the associated map object
                    final Map map = getMap();
                    // Creating a map options object
                    MapOptions mapOptions = new MapOptions(map);
                    // Creating a map type control options object
                    MapTypeControlOptions controlOptions = new MapTypeControlOptions(map);
                    // Changing position of the map type control
                    controlOptions.setPosition(ControlPosition.TOP_RIGHT);
                    // Setting map type control options
                    mapOptions.setMapTypeControlOptions(controlOptions);
                    // Setting map options
                    map.setOptions(mapOptions);
                    // Setting the map center
                    map.setCenter(new LatLng(map, 51.059258, 16.373292));
                    // Setting initial zoom value
                    map.setZoom(8.0);
                    // Creating a path (array of coordinates) that represents a polyline
                    
                    ArrayList<LatLng> path = new ArrayList<>();
                    for (Coordinates newCoord : newCoords) {
                        path.add(new LatLng(map, newCoord.getY(), newCoord.getX()));
                    }
                    
                    Polyline polyline = new Polyline(map);
                    // Initializing the polyline with created path
                    polyline.setPath(path.toArray(new LatLng[newCoords.size()]));
                    // Creating a polyline options object
                    PolylineOptions options = new PolylineOptions(map);
                    // Setting geodesic property value
                    options.setGeodesic(true);
                    // Setting stroke color value
                    options.setStrokeColor("#FF0000");
                    // Setting stroke opacity value
                    options.setStrokeOpacity(1.0);
                    // Setting stroke weight value
                    options.setStrokeWeight(2.0);
                    // Applying options to the polyline
                    polyline.setOptions(options);
                }
            }
        });

    }

    public static void main(String[] args) {
        //funkcja main z kodem do testowania
        ArrayList<Coordinates> testowe = new ArrayList<>();
        testowe.add(new Coordinates(10.0, 11.0));
        testowe.add(new Coordinates(12, 12.0));
        testowe.add(new Coordinates(10.2, 14.0));
        testowe.add(new Coordinates(10.3, 11.0));
        testowe.add(new Coordinates(10.4, 11.0));
        testowe.add(new Coordinates(20.5, 11.0));
        MapViewOptions options = new MapViewOptions();
        options.importPlaces();
        final GoogleMaps mapView = new GoogleMaps(options, testowe);

        JFrame frame = new JFrame("JxMaps - Hello, World!");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(mapView, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
