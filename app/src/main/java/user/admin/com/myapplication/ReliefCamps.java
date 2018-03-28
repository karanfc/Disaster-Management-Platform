package user.admin.com.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import static android.R.attr.y;
import static user.admin.com.myapplication.TalkToOtherSurvivors.ANONYMOUS;

public class ReliefCamps extends FragmentActivity implements OnMapReadyCallback {




    private  GoogleMap mMap;
    SupportMapFragment mFragment;
    Marker currLocationMarker;
    String details;
    Bitmap quake, fire, ter, sunami;

    private String mUsername;

    // Firebase instance variables
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mChatPhotosStorageReference;




    Double a[] = new Double[15];
    int i = 0;
    String z[]=new String[15];




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relief_camps);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseStorage = FirebaseStorage.getInstance();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);





    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mUsername = ANONYMOUS;
        mMessagesDatabaseReference = mFirebaseDatabase.getReference("rescuelocation");

        mMessagesDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.e(" ss", "ss");

                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {

                    String rightLocation = postsnapshot.getValue().toString();
                    double d = Double.parseDouble(rightLocation);

                    a[i] = d;
                    Log.e("getdata", "" + a[i]);
                    i++;

                }

                for (int j = 0; j < 5; j++) {
                    double x=a[j];
                    double y= a[j+5];
                    LatLng pos = new LatLng(x,y);
                    mMap.addMarker(new MarkerOptions().position(pos).title("Shelter").snippet("Safe Place"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(pos));
                }
                LatLng pos = new LatLng(19.2,72.87);
                CameraPosition cameraPosition = new CameraPosition.Builder().target(pos).zoom(10).build();
                mMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}

