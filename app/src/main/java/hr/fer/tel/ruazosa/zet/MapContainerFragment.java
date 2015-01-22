package hr.fer.tel.ruazosa.zet;

import android.app.Fragment;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.Tile;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MapContainerFragment extends Fragment implements OnMapReadyCallback{

    private static final String DEPARTURE_ID = "departureID";

    public MapContainerFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map_container, container, false);

        if (getArguments() != null) {
            int departureID = getArguments().getInt(DEPARTURE_ID);

            //TODO dohvat liste polazaka iz baze
        }

        GoogleMapOptions mapOptions = new GoogleMapOptions();
        //TODO mapOptions.mapType(GoogleMap.MAP_TYPE_NONE);
        mapOptions.zoomControlsEnabled(true).zoomGesturesEnabled(true);
        mapOptions.mapToolbarEnabled(false);

        MapFragment mapFragment = MapFragment.newInstance(mapOptions);
        getFragmentManager().beginTransaction().add(R.id.map, mapFragment).commit();
        mapFragment.getMapAsync(this);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //TODO
        LatLng currentLocation = new LatLng(45.843, 15.963); //TODO
        //TODO googleMap.addTileOverlay(new TileOverlayOptions().tileProvider(new CustomTileProvider(getResources().getAssets())));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 13));
        googleMap.setMyLocationEnabled(true);

        LatLng zagreb = new LatLng(45.843, 15.963);
        googleMap.addMarker(new MarkerOptions().position(zagreb).title("Zagreb"));

        /*TODO
        LatLng zagreb = new LatLng(45.82, 15.98);
        LatLng gorica = new LatLng(45.70,16.07);
        googleMap.addMarker(new MarkerOptions().position(zagreb).title("Zagreb"));
        googleMap.addMarker(new MarkerOptions().position(gorica).title("Gorica"));
        googleMap.addPolyline(new PolylineOptions().add(zagreb).add(gorica));
        */
    }

    public class CustomTileProvider implements TileProvider {

        private static final int WIDTH = 256;
        private static final int HEIGHT = 256;
        private static final int BUFFER_SIZE = 16 * 1024;

        private AssetManager assets;

        public CustomTileProvider(AssetManager assets) {
            this.assets = assets;
        }

        @Override
        public Tile getTile(int x, int y, int zoom) {
            //TODO y = fixY(y, zoom);
            byte[] image = getImage(x, y, zoom);
            //TODO return image == null ? NO_TILE: new Tile(WIDTH, HEIGHT, image);
            return image == null ? null: new Tile(WIDTH, HEIGHT, image);
        }

        private int fixY(int y, int zoom) {
            int size = 1 << zoom;
            return size - 1 - y;
        }

        private byte[] getImage(int x, int y, int zoom) {
            InputStream input = null;
            ByteArrayOutputStream output = null;

            try {
                input = assets.open(getFilename(x, y, zoom));
                output = new ByteArrayOutputStream();

                int len;
                byte[] data = new byte[BUFFER_SIZE];

                while ((len = input.read(data, 0, BUFFER_SIZE)) != -1) {
                    output.write(data, 0, len);
                }
                output.flush();

                return output.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                return null;
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (Exception ignored) {}
                }
                if (output != null) {
                    try {
                        output.close();
                    } catch (Exception ignored) {}
                }
            }
        }

        private String getFilename(int x, int y, int zoom) {
            //TODO update bitmap path
            return "map/" + zoom + '/' + x + '/' + y + ".png";
        }
    }
}
