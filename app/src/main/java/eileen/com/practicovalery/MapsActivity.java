package eileen.com.practicovalery;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback , GoogleMap.OnMapClickListener {

    private static final int REQUEST_CODE = 11 ;
    private GoogleMap mMap;
    private LocationManager manager;
    private Marker me;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        manager = (LocationManager) getSystemService(LOCATION_SERVICE);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMinZoomPreference(6.0f);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        }, REQUEST_CODE);

        mMap.setOnMapClickListener(this);




        //si tiene los permisos con la red
        if(manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, new LocationListener() {


                @Override
                public void onLocationChanged(Location location) {
                    Log.e(">>>", "LAT: " + location.getLatitude() + " , LONG: " + location.getLongitude());

                    if (me != null) {
                        me.remove();
                    }
                    me = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(location.getLatitude(), location.getLongitude()))
                            .title("Me")
                    );
                    mMap.moveCamera(CameraUpdateFactory
                            .newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 20));
                    me.setSnippet("Usted se encuentra en: " + getAddress(location.getLatitude(),location.getLongitude()));

                    LatLng latLngCurrent = new LatLng(location.getLatitude(), location.getLongitude());


                    LatLng latLngBiblioUpA= new LatLng(3.341950, -76.530073);
                    LatLng latLngBiblioUpB= new LatLng(3.341950, -76.5298169);
                    LatLng latLngBiblioDownC= new LatLng(3.341671, -76.530073);
                    LatLng latLngBiblioDownD= new LatLng(3.341671, -76.529805);

                    PolygonOptions polygonOptions=new PolygonOptions();


                    Polyline lineAB = mMap.addPolyline(new PolylineOptions()
                            .add(latLngBiblioUpA,latLngBiblioUpB)
                            .width(5)
                            .color(Color.RED));

                    Polyline lineAC = mMap.addPolyline(new PolylineOptions()
                            .add(latLngBiblioUpA,latLngBiblioDownC)
                            .width(5)
                            .color(Color.RED));

                    Polyline lineDB = mMap.addPolyline(new PolylineOptions()
                            .add(latLngBiblioDownD,latLngBiblioUpB)
                            .width(5)
                            .color(Color.RED));

                    Polyline lineDC = mMap.addPolyline(new PolylineOptions()
                            .add(latLngBiblioDownD,latLngBiblioDownC)
                            .width(5)
                            .color(Color.RED));


                    if((latLngCurrent.latitude >latLngBiblioDownD.latitude) &&(latLngCurrent.latitude < latLngBiblioUpA.latitude)){
                        //cumple con la latitud

                        if((latLngCurrent.longitude >latLngBiblioDownD.longitude) &&(latLngCurrent.longitude < latLngBiblioUpA.longitude)){
                            //cumple con la longitud y se encuentra dentro de la biblioteca

                            //Aqui debo aparecer el boton

//                            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.imageButton);
//                            fab.setEnabled(true);

                            Marker ahorita = mMap.addMarker(new MarkerOptions()
                                    .position(latLngCurrent)
                                    .title("ahorita")
                            );
                        }

                    }




                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });


        }
        //si no los tiene uso entonces el gps normal
        //Agregar el listener de ubicacion
        else  if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, new LocationListener() {

                @Override
                public void onLocationChanged(Location location) {
                    Log.e(">>>", "LAT: " + location.getLatitude() + " , LONG: " + location.getLongitude());





                    
                    if (me != null) {
                        me.remove();
                    }
                    me = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(location.getLatitude(), location.getLongitude()))
                            .title("Me")
                    );
                    mMap.moveCamera(CameraUpdateFactory
                            .newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 20));
                    me.setSnippet("Usted se encuentra en: " + getAddress(location.getLatitude(),location.getLongitude()));

                    LatLng latLngCurrent = new LatLng(location.getLatitude(), location.getLongitude());
                    //Esas cordenadas las saque con un texto del marker donde lo puse en las esquinas de la biblio
                    LatLng latLngBiblioDown= new LatLng(3.3416307, -76.5298169);
                    LatLng latLngBiblioUp= new LatLng(3.319336233, -76.53010088);
                    if((latLngCurrent.latitude >latLngBiblioDown.latitude) &&(latLngCurrent.latitude < latLngBiblioUp.latitude)){
                        //cumple con la latitud

                        if((latLngCurrent.longitude >latLngBiblioDown.longitude) &&(latLngCurrent.longitude < latLngBiblioUp.longitude)){
                            //cumple con la longitud y se encuentra dentro de la biblioteca

                            //Aqui debo aparecer el boton

//                            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.imageButton);
//                            fab.setEnabled(true);

//                            fab.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    //Va a la actividad que gestiona el punto de canje
//                                    Intent i = new Intent(MapsActivity.this, MainActivity.class);
//                                    startActivity(i);
//
//                                }
//                            });

                            Marker ahorita = mMap.addMarker(new MarkerOptions()
                                    .position(latLngCurrent)
                                    .title("ahorita")
                            );
                        }

                    }




                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        }

    }

    private String getAddress(double latitude, double longitude) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());
        String res="";
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            res=address;

        } catch (IOException e) {
            e.printStackTrace();
            res="paila";
        }
        return res;

    }


    @Override
    public void onMapClick(LatLng point) {

        mMap.addMarker(new MarkerOptions().position(point));


        mMap.addMarker(new MarkerOptions().position(point).title(point.latitude+""));
        me.setSnippet("Ubicacion guardada en : " + getAddress(point.latitude, point.longitude));


    }
}
