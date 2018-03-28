package user.admin.com.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class SendHelpSignal extends AppCompatActivity {
    private Button button;
    private TextView txtview;
    private LocationManager locationManager;
    private LocationListener locationListener;
    public double lat=19.1066790,lng=72.8371930;
    private static final String SSID = "Need Help!";

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mChatPhotosStorageReference;

    public String musername = "Anonymous";
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_help_signal);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CHANGE_WIFI_STATE},1);
        count = 0;
        // Initialize Firebase components
        boolean Parth=isNetworkAvailable();
        if(Parth) {
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mFirebaseAuth = FirebaseAuth.getInstance();
            mFirebaseStorage = FirebaseStorage.getInstance();
            mAuthStateListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user != null) {
                        // User is signed in
                        musername = firebaseAuth.getCurrentUser().getDisplayName();
                        //onSignedInInitialize(user.getDisplayName());
                    } else {
                        // User is signed out
                        musername = "anonymous";
                    }
                }
            };
            mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("currentlocation");


            // button = (Button) findViewById(R.id.button);
            //txtview = (TextView) findViewById(R.id.txtview);
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    lat = location.getLatitude();
                    lng = location.getLongitude();

                    //        Toast.makeText(getApplicationContext(),""+lat+""+lng,Toast.LENGTH_SHORT).show();
                    if (count == 0) {
                        CurrentLocation currentLocation = new CurrentLocation(musername, lat, lng);
                        mMessagesDatabaseReference.push().setValue(currentLocation);
                        count++;
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
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            };

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET
                    }, 10);
                    return;
                }
            } else {
                configureButton();
            }
        }
        else
        {
            SmsManager Manager = SmsManager.getDefault();
            Manager.sendTextMessage("8976019785", null, "\nLatitude:"+lat+"\nLongitude:"+lng, null, null);
            SendHelpSignal.setWifiApState(getApplicationContext(), true);

        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        //txtview.setText("requestingPermission");
        switch(requestCode){
            case 10:
                if(grantResults.length>=0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    configureButton();
                return;
        }
    }

    private void configureButton() {

                locationManager.requestLocationUpdates("network", 5000, 0, locationListener);
        Handler handlerTimer = new Handler();

        handlerTimer.postDelayed(new Runnable(){
            public void run() {
           finish();     // do something
            }}, 5000);
            }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

   /* private void turnGPSOn(){
        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if(!provider.contains("gps")){ //if gps is disabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            sendBroadcast(poke);
        }
    }

    private void turnGPSOff(){
        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if(provider.contains("gps")){ //if gps is enabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            sendBroadcast(poke);
        }
    }*/

    public static boolean setWifiApState(Context context, boolean enabled) {
        //config = Preconditions.checkNotNull(config);
        try {
            WifiManager mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            if (enabled) {
                mWifiManager.setWifiEnabled(false);
            }
            WifiConfiguration conf = getWifiApConfiguration();
            mWifiManager.addNetwork(conf);

            return (Boolean) mWifiManager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, boolean.class).invoke(mWifiManager, conf, enabled);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static WifiConfiguration getWifiApConfiguration() {
        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID =  SSID;
        conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        return conf;
    }
}


