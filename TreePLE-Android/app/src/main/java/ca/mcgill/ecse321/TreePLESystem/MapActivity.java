package ca.mcgill.ecse321.TreePLESystem;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import cz.msebera.android.httpclient.Header;

/**
 * Created by antho on 2018-03-05.
 */

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String error;
    private HashMap<Integer, ArrayList<String>> treeMap;
    public Bitmap imageBitmap;
    public static Bitmap resizedBitmap;
    private Button cutButton;
    private int mapClickCtr;
    private Marker lastMarkerClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        treeMap = new HashMap<>();
        mapClickCtr = 0;

        MapFragment mapFragment = (MapFragment)
                getFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(MapActivity.this);
        cutButton = (Button) findViewById(R.id.cutdown);
        cutButton.setVisibility(View.INVISIBLE);

        //showTrees(treeMap);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        refreshLists(treeMap, "trees");

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                cutButton.setClickable(true);
                cutButton.setVisibility(View.VISIBLE);  //Show the cut down tree button when a tree is pressed
                lastMarkerClicked = marker; //Get the marker ID incase we want to cut it down
                return false;
            }
        });

        mMap.setOnInfoWindowCloseListener(new GoogleMap.OnInfoWindowCloseListener() {
            @Override
            public void onInfoWindowClose(Marker marker) {

                cutButton.setClickable(false);  //Hide cut tree button when no tree is pressed
                cutButton.setVisibility(View.INVISIBLE);
            }
        });
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (mapClickCtr == 1) {
                    plantTree(latLng);  //Only show plant tree dialog if map is clicked twice, otherwise its annoying
                    mapClickCtr = 0;
                }
                else
                    mapClickCtr++;
            }
        });

    }

    public void onRefresh(View v){
        refreshLists(treeMap, "trees");
        showTrees(treeMap);
    }

    /*
    This function loops through our HashMap of trees. It extracts all the relevant information
    to show it in the info window on the Google map.
     */

    private void showTrees(HashMap<Integer, ArrayList<String>> treeMap) {

        for(int i = 0; i < treeMap.size(); i++){


            ArrayList<String> treeinfo = treeMap.get(i);
            String status = treeinfo.get(8);

            if(!status.equals("CutDown")) {

                MarkerOptions treeMarker = new MarkerOptions();

                Double longitude = Double.valueOf(treeinfo.get(5));
                Double latitude = Double.valueOf(treeinfo.get(6));


                LatLng location = new LatLng(latitude, longitude);

                treeMarker.position(location);

                treeMarker.title(treeinfo.get(0));  //Display species as title for now

                treeMarker.snippet(treeinfo.get(4) + "^" + treeinfo.get(1) + "^" + treeinfo.get(7) + "^" + treeinfo.get(8));


                imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.treemapicon);
                resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, 100, 100, false);
                treeMarker.icon(BitmapDescriptorFactory.fromBitmap(resizedBitmap));

                mMap.addMarker(treeMarker);

                CustomInfoWindowAdapter adapter = new CustomInfoWindowAdapter(MapActivity.this);
                mMap.setInfoWindowAdapter(adapter);
            }
        }
    }


    /*
    This function calls the getAllTrees function in the backend. It then stores the information for individual trees
    in an arraylist, which is then placed in a unique index in a HashMap. Each index in the hashmap
    has a key (integer), and an arraylist with all the information for a specific tree!
     */
    private void refreshLists(final HashMap<Integer, ArrayList<String>> myMap, String restFunctionName) {
        HttpUtils.get(restFunctionName, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                for( int i = 0; i < response.length(); i++){
                    try {
                        ArrayList<String> treeInfo = new ArrayList<>();
                        String species = response.getJSONObject(i).getString("species");
                        String height = response.getJSONObject(i).getString("height");
                        String date = response.getJSONObject(i).getString("date");
                        String diameter = response.getJSONObject(i).getString("diameter");
                        String personName = response.getJSONObject(i).getString("name");
                        String longitude = response.getJSONObject(i).getString("longitude");
                        String latitude = response.getJSONObject(i).getString("latitude");
                        String status = response.getJSONObject(i).getString("status");
                        String id = response.getJSONObject(i).getString("id");
                        //String municipality = response.getJSONObject(i).getString("municipality");

                        treeInfo.add(species);
                        treeInfo.add(height);
                        treeInfo.add(date);
                        treeInfo.add(diameter);
                        treeInfo.add(personName);
                        treeInfo.add(longitude);
                        treeInfo.add(latitude);
                        treeInfo.add(status);
                        treeInfo.add(id);
                        //treeInfo.add(municipality);

                        myMap.put(i, treeInfo);

                    } catch (Exception e) {
                        error += e.getMessage();
                    }
                    //refreshErrorMessage();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                //try {
                    //error += errorResponse.get("message").toString();
                //} catch (JSONException e) {
                    //error += e.getMessage();
                //}
                System.out.println("oops!");
                //refreshErrorMessage();
            }
        });
    }

    /*
    This method opens when the cut tree button is pressed. It allows a user to confirm if they wish
    to cut down a tree or not.
     */
    public void requestCutTree(View v){

        AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View mView = inflater.inflate(R.layout.cuttree_dialog, null);
        builder.setView(mView);

        builder.setCancelable(true);

        builder.setPositiveButton(R.string.cut, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                String snippetInfo[] = splitSnippets(lastMarkerClicked.getSnippet());

                httpMarkCutDownTree(snippetInfo[3]);
                dialog.cancel();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    /*
    This method executes when a user has confirmed to cut down a tree.  It calls the backend method of
    markTreeForCutDown. It displays a popup "toast" if it was succesful.
     */
    public void httpMarkCutDownTree(String treeID){
        HttpUtils.post("markCutDown/tree/" + treeID, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Toast.makeText(getApplicationContext(), "Your tree has been marked for cutdown",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                //try {
                //error += errorResponse.get("message").toString();
                //} catch (JSONException e) {
                //error += e.getMessage();
                //}
                System.out.println("oops!");
                //refreshErrorMessage();
            }
        });

    }

    /*
    This method executes once a user has tapped twice slowly on the map to open the dialog.
    It allows the user to input information about the tree they wish to plant.
     */
    public void plantTree(final LatLng latLng) {

        AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();



        final View mView = inflater.inflate(R.layout.planttree_dialog, null);
        builder.setView(mView);

        final Spinner mySpinner=(Spinner) mView.findViewById(R.id.speciesSpinner);
        mySpinner.setPrompt("Tree Species");
        List<String> species = Arrays.asList(getResources().getStringArray(R.array.spinnerItems));
        Collections.sort(species);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, species);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mySpinner.setAdapter(adapter);


        builder.setCancelable(true);

        builder.setPositiveButton(R.string.plant, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                EditText ownerTextView = (EditText) mView.findViewById(R.id.ownername);
                EditText heightTextView = (EditText) mView.findViewById(R.id.treeheight);
                EditText diameterTextiew = (EditText) mView.findViewById(R.id.treediameter);
                String species = mySpinner.getSelectedItem().toString();

                httpPostTree(String.valueOf(ownerTextView.getText()), species, String.valueOf(heightTextView.getText()), String.valueOf(diameterTextiew.getText()),
                        String.valueOf(latLng.longitude), String.valueOf(latLng.latitude));
                refreshLists(treeMap, "trees");
                dialog.cancel();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    /*
    This method executes once a user has clicked "plant tree". It performs checks to see if data is
    appropriate, and then calls the backend method to plant tree with the data passed.
     */

    public void httpPostTree(String owner, String species, String height, String diameter,  String longitude, String latitude){

        RequestParams rp = new RequestParams();
        int randomNum = ThreadLocalRandom.current().nextInt(10000000, 99999998 + 1);
        java.util.Date c = Calendar.getInstance().getTime();
        java.sql.Date sqlDate = new java.sql.Date(c.getTime());

        if(Double.parseDouble(height) > 20000 && !(Double.parseDouble(height) > 0)){
            Toast.makeText(getApplicationContext(),  "Height must be between 0 and 20000 cm",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if(Double.parseDouble(diameter) > 3500 && !(Double.parseDouble(diameter) > 0)){
            Toast.makeText(getApplicationContext(),  "Height must be between 0 and 3500 cm",
                    Toast.LENGTH_LONG).show();
            return;
        }

        HttpUtils.post("trees/" + species +"?" + "height=" + height +"&age=" + 1 + "&date=" + sqlDate
                + "&diameter=" + Float.valueOf(diameter) + "&id=" + randomNum + "&personName=" + owner + "&latitude=" + Float.valueOf(latitude)
                + "&longitude=" + Float.valueOf(longitude) + "&municipality=NDG" , new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //refreshErrorMessage();
                Toast.makeText(getApplicationContext(), "Your tree has been Planted!",
                        Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
            }
        });
    }

    /*
    This method separates the snippet for the google map marker so that we can easily extract information
     */
    public static String[] splitSnippets(String snippet){
        String[] arr=snippet.split("\\^");
        return arr;
    }
}
