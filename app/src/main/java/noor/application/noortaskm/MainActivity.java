package noor.application.noortaskm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import MyData.MyTaskAdapter;
import MyData.Mytask;
import MyData.PrayTime;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener {
    private FloatingActionButton FABmain;
    private SearchView SVTask;
    private Button BtnLoc;
    private TextView TvLon;
    private TextView TvLat;
    private TextView ETFajr;
    private TextView ETShorouq;
    private TextView ETDuhur;
    private TextView ETAsr;
    private TextView ETMaghrib;
    private TextView ETIshaa;
    private ImageView IVLine;
    private MyTaskAdapter taskAdapter;
    private LocationRequest locationRequest;
    private TextView AddressText;
    private double latitude;
    private double longitude;
    private boolean locUpdate=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BtnLoc=findViewById(R.id.BtnLoc);
        TvLon=findViewById(R.id.TvLon);
        TvLat=findViewById(R.id.TvLat);
        ETFajr=findViewById(R.id.ETFajr);
        ETShorouq=findViewById(R.id.ETShorouq);
        ETDuhur=findViewById(R.id.ETDuhur);
        ETAsr=findViewById(R.id.ETAsr);
        ETMaghrib=findViewById(R.id.ETMaghrib);
        ETIshaa=findViewById(R.id.ETIshaa);
        IVLine=findViewById(R.id.IVLine);
        FABmain = findViewById(R.id.FABmain);
        BtnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getCurrentLocation();
            }
        });
        FABmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddTaskActivity.class));
            }
        });
        SVTask = findViewById(R.id.SVTask);
        taskAdapter=new MyTaskAdapter(this,R.layout.task_item_layout);
        //read 3:set adapter to Listview(connect the data to List view)
    }
    //read 4:

    protected void onResume() {
        super.onResume();;
        readTasksFromFireBase("");
    }
    //read5:

    /**
     * read tasks from fire base and fill the adapter data structure
     * s- is text to speech, if it is empty the method show all results
     * @param s
     */

    private void readTasksFromFireBase(String s) {
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference();
        String uid=FirebaseAuth.getInstance().getUid();//current user id.
        //إضافة إمكانية "التحتلن" بكل تغيير سيحصل على القيم في قاعدة البيانات
        ref.child("mytasks").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                taskAdapter.clear();
                for (DataSnapshot d:snapshot.getChildren())
                {
                    Mytask t=d.getValue(Mytask.class);
                    taskAdapter.add(t);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.itmSettings) {
            Intent i = new Intent(getApplicationContext(),SettingsActivity.class);
            startActivity(i);
        }
        if (item.getItemId() == R.id.itmSignOut) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure");
            builder.setCancelable(true);
            //listener 3. add Listener to the buttons
            builder.setPositiveButton("YES", this);
            builder.setNegativeButton("NO", this);
            AlertDialog dialog = builder.create();
            dialog.show();

        }
        return true;
    }

    public void onClick(DialogInterface dialogInterface, int which) {//Listener 4. react for each action
        if (which == dialogInterface.BUTTON_POSITIVE) {
            Toast.makeText(getApplicationContext(), "Loging out", Toast.LENGTH_SHORT).show();
            dialogInterface.cancel();
            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.signOut();
            finish();//to close current activity
        }


        if (which == dialogInterface.BUTTON_NEGATIVE) {
            Toast.makeText(getApplicationContext(), "Loging out canceled", Toast.LENGTH_SHORT).show();
            dialogInterface.cancel();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){

                if (isGPSEnabled()) {

                    getCurrentLocation();

                }else {

                    turnOnGPS();
                }
            }
        }


    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 2) {
//            if (resultCode == Activity.RESULT_OK) {
//
//                getCurrentLocation();
//            }
//        }
//    }

    private void getCurrentLocation() {
        locUpdate=false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){

                if (isGPSEnabled()) {

                    LocationServices.getFusedLocationProviderClient(MainActivity.this)
                            .requestLocationUpdates(locationRequest, new LocationCallback() {
                                @Override
                                public void onLocationResult(@NonNull LocationResult locationResult) {
                                    super.onLocationResult(locationResult);

                                    LocationServices.getFusedLocationProviderClient(MainActivity.this)
                                            .removeLocationUpdates(this);

                                    if (locationResult != null && locationResult.getLocations().size() >0){

                                        int index = locationResult.getLocations().size() - 1;
                                        latitude = locationResult.getLocations().get(index).getLatitude();
                                        longitude = locationResult.getLocations().get(index).getLongitude();
                                        Toast.makeText(getApplicationContext(), "Latitude: "+ latitude + "\n" + "Longitude: "+ longitude, Toast.LENGTH_SHORT).show();
                                        TvLat.setText("Latitude: "+ latitude);
                                        TvLon.setText( "Longitude: "+ longitude);
                                        updatePrayTimeList(latitude,longitude,10);//todo get Current Timezone Code
                                        locUpdate=true;
                                    }
                                }
                            }, Looper.getMainLooper());

                } else {
                    turnOnGPS();
                }

            } else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            }
        }
    }

    private void turnOnGPS() {



        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext())
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(MainActivity.this, "GPS is already tured on", Toast.LENGTH_SHORT).show();

                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(MainActivity.this, 2);
                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            //Device does not have location
                            break;
                    }
                }
            }
        });

    }

    private boolean isGPSEnabled() {
        LocationManager locationManager = null;
        boolean isEnabled = false;

        if (locationManager == null) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }

        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;

    }
    public void updatePrayTimeList(double latitude,double longitude,double timezone) {

        // Test Prayer times here
        PrayTime prayers = new PrayTime();

        prayers.setTimeFormat(prayers.Time12);
        prayers.setCalcMethod(prayers.MidNight);
        prayers.setAsrJuristic(prayers.Shafii);
        prayers.setAdjustHighLats(prayers.AngleBased);
        int[] offsets = {0, 0, 0, 0, 0, 0, 0}; // {Fajr,Sunrise,Dhuhr,Asr,Sunset,Maghrib,Isha}
        prayers.tune(offsets);

        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);

        ArrayList<String> prayerTimes = prayers.getPrayerTimes(cal,
                latitude, longitude, timezone);
        ArrayList<String> prayerNames = prayers.getTimeNames();

        for (int i = 0; i < prayerTimes.size(); i++) {
            System.out.println(prayerNames.get(i) + " - " + prayerTimes.get(i));
        }

    }


}


