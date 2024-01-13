package com.example.pagedebase;
import android.os.SystemClock;
import android.widget.Chronometer;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.infowindow.InfoWindow;

import java.util.ArrayList;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements MapEventsReceiver {

    private MapView map;
    private Chronometer chronoDureeActuelle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_en_cours); // Utilisez le layout correct ici

        // Le reste de votre code reste inchangé
        map = findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);
        map.setBuiltInZoomControls(false);
        GeoPoint startPoint = new GeoPoint(48.7120476, 2.1683975);
        IMapController mapController = map.getController();
        mapController.setZoom(18);
        mapController.setCenter(startPoint);
        // Récupérez la référence au Chronometer
        chronoDureeActuelle = findViewById(R.id.chronoDureeActuelle);

        // Démarrez le chronomètre
        chronoDureeActuelle.setBase(SystemClock.elapsedRealtime());
        chronoDureeActuelle.start();
    }

    // Le reste de vos méthodes reste inchangé
    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
        // Arrêtez le chronomètre lorsque l'activité est en pause, si nécessaire
        chronoDureeActuelle.stop();
    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
        // Redémarrez le chronomètre lorsque l'activité reprend, si nécessaire
        chronoDureeActuelle.start();
    }

    @Override
    public boolean singleTapConfirmedHelper(GeoPoint p) {
        return true;
    }

    @Override
    public boolean longPressHelper(GeoPoint p) {
        return false;
    }
}
