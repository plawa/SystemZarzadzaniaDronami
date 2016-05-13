package GUI;

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
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author Piotrek
 */
public class MapsTest extends MapView {

    public MapsTest(MapViewOptions options) {
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
                    map.setCenter(new LatLng(map, 0, -180));
                    // Setting initial zoom value
                    map.setZoom(3.0);
                    // Creating a path (array of coordinates) that represents a polyline
                    LatLng[] path = {new LatLng(map, 37.772, -122.214),
                        new LatLng(map, 21.291, -157.821),
                        new LatLng(map, -18.142, 178.431),
                        new LatLng(map, -57.467, -173.027)};
                    // Creating a new polyline object
                    Polyline polyline = new Polyline(map);
                    // Initializing the polyline with created path
                    polyline.setPath(path);
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
        MapViewOptions options = new MapViewOptions();
        options.importPlaces();
        final MapsTest mapView = new MapsTest(options);

        JFrame frame = new JFrame("JxMaps - Hello, World!");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(mapView, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


}
