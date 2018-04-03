package ca.mcgill.ecse321.TreePLESystem;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;

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
import java.util.HashMap;
import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        treeMap = new HashMap<>();

        MapFragment mapFragment = (MapFragment)
                getFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(MapActivity.this);

        //showTrees(treeMap);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        refreshLists(treeMap, "trees");

    }

    public void onRefresh(View v){
        showTrees(treeMap);
    }

    private void showTrees(HashMap<Integer, ArrayList<String>> treeMap) {

        for(int i = 0; i < treeMap.size(); i++){

            MarkerOptions treeMarker = new MarkerOptions();

            ArrayList<String> treeinfo = treeMap.get(i);

            Double longitude = Double.valueOf(treeinfo.get(5));
            Double latitude = Double.valueOf(treeinfo.get(6));

            LatLng location = new LatLng(latitude, longitude);

            treeMarker.position(location);

            treeMarker.title(treeinfo.get(0));  //Display species as title for now

            treeMarker.snippet(treeinfo.get(4) + "^" + treeinfo.get(1));

            //Bitmap bit = BitmapFactory.decodeFile(String.valueOf(R.drawable.treemapicon));
            imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.treemapicon);
            resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, 100, 100, false);
            //BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.treemapicon);
            treeMarker.icon(BitmapDescriptorFactory.fromBitmap(resizedBitmap));

            mMap.addMarker(treeMarker);

            CustomInfoWindowAdapter adapter = new CustomInfoWindowAdapter(MapActivity.this);
            mMap.setInfoWindowAdapter(adapter);
        }
    }

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
                        //String municipality = response.getJSONObject(i).getString("municipality");

                        treeInfo.add(species);
                        treeInfo.add(height);
                        treeInfo.add(date);
                        treeInfo.add(diameter);
                        treeInfo.add(personName);
                        treeInfo.add(longitude);
                        treeInfo.add(latitude);
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


}
