package com.FarmPe.Oxkart.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;


public class GetLocationFragment extends Fragment {

    LocationManager manager;
    boolean permission_navigated = false,gps_navigated = false,got_latlong=false;
    int attempts=0,permission_attempt=0,gps_attempt=0;

    Intent intent;
    LinearLayout back_feed;
    public static String livelocation;
    String loc;
    public static double latitude,longitude;

    TextView current_address;
    Fragment selectedFragment;
    SessionManager sessionManager;
    LocationManager locationManager;

    LocationListener locationListener;
    boolean valid = true;
    boolean loc_frst_set;

   // private LatLng currLatLong=null;


    Geocoder geocoder;
    List<Address> addresses;
    private  Location location;
    AutocompleteSupportFragment autocompleteFragment;
    String currentLocation;


    public static GetLocationFragment newInstance() {
        GetLocationFragment fragment = new GetLocationFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.get_loc_layout, container, false);
        locationManager  = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

      /*  intent = getIntent();
        Bundle b = intent.getExtras();
        if (b!=null) {
            Log.d("Navfrom", b.getString("NavigationFrom"));
            nav = b.getString("NavigationFrom");
        }
        */
        //String nav = intent.getExtras().getString("NavigateFrom");
        //nomap = findViewById(R.id.nomap);


        current_address= view.findViewById(R.id.curr_address);
        back_feed= view.findViewById(R.id.back_feed);


       current_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentAddress();
            }
        });
        sessionManager=new SessionManager(getActivity());
       /* ImageView current = findViewById(R.id.current);
        current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("gdghd");

            }
        });*/

        if (!Places.isInitialized()) {
            Places.initialize(getActivity(), "AIzaSyB3KUa9_XplyH7iI2CIq3WcGisxvivwhOU");
        }

// Initialize the AutocompleteSupportFragment.

     autocompleteFragment = (AutocompleteSupportFragment)
                getChildFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
                loc=place.getName();
                sessionManager.savelocation(place.getName());
                Bundle bundle = new Bundle();
                bundle.putString("homelocation",loc);

                selectedFragment = Home_Menu_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                selectedFragment.setArguments(bundle);
                transaction.addToBackStack("home");
                transaction.commit();
            }


            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack ("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    return true;
                }
                return false;
            }
        });


// fetching places related strats
/*

        if (!Places.isInitialized()) {
            Places.initialize(getActivity(), "AIzaSyCyRkqPmInGduAPsL7kpTZ4Wb1DtKsn0-M");
        }
// Initialize the AutocompleteSupportFragment.
         mapFragment = (AutocompleteSupportFragment)
                getActivity().getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
    //    autocompleteFragment.setCountry("IN");
       // autocompleteFragment.setHint("Enter your Location");
        mapFragment.setPlaceFields(Arrays.asList(Place.Field.NAME, Place.Field.LAT_LNG));

        mapFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                System.out.println("dddd = "+place.getLatLng().longitude);
                latitude=place.getLatLng().latitude;
                longitude = place.getLatLng().longitude;
                //mapFragment.getMapAsync(GetLocationFragment.this);
                 loc=place.getAddress();
current_address.setText(sessionManager.getRegId("location"));
                loc_frst_set=false;
                got_latlong=true;
             //   getLatLongfromPlaceId(place.getId()); to get place ID pass "Place.Field.ID" in setPlaceFields
            }
            @Override
            public void onError(Status status) {
                Toast.makeText(getActivity(),status.toString(), Toast.LENGTH_SHORT).show();
                Log.i("dddd1", "An error occurred: " + status);
            }
        });
*/

        //fetching places related ends



        manager = (LocationManager)getActivity()
                .getSystemService(Context.LOCATION_SERVICE);
        checkLocationPermission();

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

          //  locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,locationListener);
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            updateLocation(lastKnownLocation);

        }else {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        return view;
    }
    private void checkLocationPermission()
    {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            //ask for permission pop up
            askForPermission();

        } else {

            if(permission_navigated)permission_navigated=false;

            //check if gps is turned on
           // checkGPSTurnedOn();

        }
    }



    public void updateLocation ( Location location) {
        if(addresses!=null) {
            // current_address.setText(getAddressFromLatLong(currLatLong.latitude, currLatLong.longitude));
            //currentgoogleMap.addMarker(new MarkerOptions().position(india).title("India"));
            System.out.println("gdghddddd");
            geocoder = new Geocoder(getActivity(), Locale.getDefault());
            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1);
                // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                // String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                //  String city = addresses.get(0).getLocality();
                //   String state = addresses.get(0).getAdminArea();
                //  String country = addresses.get(0).getCountryName();
                //   String postalCode = addresses.get(0).getPostalCode();
                //   String knownName = addresses.get(0).getFeatureName();
                livelocation = addresses.get(0).getSubLocality();
                current_address.setText(livelocation);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            current_address.setText("Current Location");
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
// dialogue box event handling

        switch (requestCode) {
            case 99: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //check if gps is turned on
                 //   checkGPSTurnedOn();

                    }
                    else {

                    permission_attempt++;

                    askForPermission();

                    }
                }

            }
        }

        private void askForPermission()
        {

            if(permission_attempt<=2)

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // if location permission is not on will be promfted for first time
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 99);

            } else {

                //user pressed dont show again
                permissionDeclined();

            }
            else

                Toast.makeText(getActivity(),"Enter Location Manually", Toast.LENGTH_SHORT).show();
        }
        //don't delete this its required

/*    private void askTogetHighAccuracy() {

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
       // alertDialogBuilder.setTitle("Turn on GPS?");
        alertDialogBuilder.setMessage("Unable to fetch lat long please reset GPS or allow High Accuracy");
        alertDialogBuilder.setPositiveButton("High Accuracy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //will be navigated to turn on gps
                attempts++;
                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));

            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
                //alertDialogBuilder.show();
            }
        });
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.show();

    }*/

    private void buildAlertMessageNoGps() {
        gps_attempt++;
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle("Turn on GPS?");
        alertDialogBuilder.setMessage("Your GPS seems to be disabled, do you want to enable it?");
        alertDialogBuilder.setPositiveButton("Turn On", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                gps_navigated=true;
                //will be navigated to turn on gps
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));

            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();

                if(gps_attempt<2)
                alertDialogBuilder.show();
                else
                    Toast.makeText(getActivity(),"Enter Location Manually", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.show();

    }

    private void permissionDeclined()
    {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle("Allow Location Access");
        alertDialogBuilder.setMessage("You have declined location access permission please turn it on to navigate into the App.");
        alertDialogBuilder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                permission_navigated=true;
                //will navigate to permission section to check location
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);



            }
        });
        alertDialogBuilder.setNegativeButton("Deny", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                permission_attempt++;
                dialogInterface.dismiss();

                if(permission_attempt!=2)
                alertDialogBuilder.show();
                Toast.makeText(getActivity(),"Enter Location Manually", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.show();
    }




 private String getAddressFromLatLong(Double Latitude, Double Longitude)
 {
     Geocoder geocoder;
     List<Address> addresses;
     geocoder = new Geocoder(getActivity(), Locale.getDefault());
     try {
         addresses = geocoder.getFromLocation(Latitude, Longitude, 1);
         String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
      //   livelocation = addresses.get(0).getSubLocality();
        /* String city2 = addresses.get(0).getSubAdminArea();
         String state = addresses.get(0).getAdminArea();
         String country = addresses.get(0).getCountryName();
         String postalCode = addresses.get(0).getPostalCode();
         String knownName = addresses.get(0).getFeatureName();*/
        // Log.e("tyfshgdsahgsah",livelocation);
         valid=true;
return address;

     } catch (IOException e) {
         valid=false;
    return "Address Not Available for this Location";

     }
     catch (IndexOutOfBoundsException e)
     {
         valid=false;
         return "Please choose a valid location";
     }
 }
    @SuppressLint("NewApi")
    public void getCurrentAddress() {
        // Get the location manager
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            try {
                locationManager = (LocationManager)
                        getActivity(). getSystemService(Context.LOCATION_SERVICE);
            } catch (Exception ex) {
                Log.i("msg", "fail to request location update, ignore", ex);
            }
            if (locationManager != null) {
                if (getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
                location = locationManager
                        .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
            Geocoder gcd = new Geocoder(getActivity(),
                    Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = gcd.getFromLocation(location.getLatitude(),
                        location.getLongitude(), 1);
                if (addresses.size() > 0) {
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    String locality = addresses.get(0).getLocality();
                    String subLocality = addresses.get(0).getSubLocality();
                    String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    String postalCode = addresses.get(0).getPostalCode();
                    String knownName = addresses.get(0).getFeatureName();
                    if (subLocality != null) {
                        currentLocation = locality + "," + subLocality;
                        System.out.println("effd"+currentLocation);
                        current_address.setText(currentLocation);

                        sessionManager.savelocation(currentLocation);

                        selectedFragment = Home_Menu_Fragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_menu, selectedFragment);
                        transaction.commit();

                    } else {

                        currentLocation = locality;
                    }
                    // current_locality = locality;
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Toast.makeText(getActivity(), Constants.ToastConstatnts.ERROR_FETCHING_LOCATION, Toast.LENGTH_SHORT).show();
            }
        }
    }
}

