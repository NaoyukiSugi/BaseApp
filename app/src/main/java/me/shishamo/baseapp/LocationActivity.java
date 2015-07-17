package me.shishamo.baseapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class LocationActivity extends FragmentActivity {
//    // 関西国際空港 : 34.435912,135.24349641
//    private LatLng mKansai = new LatLng(34.435912, 135.243496);
//    // 伊丹空港 : 34.785500 ,135.438004
//    private LatLng mItami = new LatLng(34.785500, 135.438004);
//    // 神戸空港　: 34.636245, 135.224061
//    private LatLng mKobe = new LatLng(34.636245, 135.224061);

    private GoogleMap mMap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);


        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        if (mMap != null) {
            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng point) {
                    CameraPosition cameraPos = new CameraPosition.Builder().target(point).zoom(10.0f).bearing(0).build();
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPos));

                    MarkerOptions options = new MarkerOptions();
                    options.position(point);
                    options.title("こ↑こ↓");
                    options.draggable(true);
                    mMap.addMarker(options);
                }
            });
        }
    }
}