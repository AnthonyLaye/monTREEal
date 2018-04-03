package ca.mcgill.ecse321.TreePLESystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private String error = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Bundle getTimeFromLabel(String text) {
        Bundle rtn = new Bundle();
        String comps[] = text.toString().split(":");
        int hour = 12;
        int minute = 0;

        if (comps.length == 2) {
            hour = Integer.parseInt(comps[0]);
            minute = Integer.parseInt(comps[1]);
        }

        rtn.putInt("hour", hour);
        rtn.putInt("minute", minute);

        return rtn;
    }

    private Bundle getDateFromLabel(String text) {
        Bundle rtn = new Bundle();
        String comps[] = text.toString().split("-");
        int day = 1;
        int month = 1;
        int year = 1;

        if (comps.length == 3) {
            day = Integer.parseInt(comps[0]);
            month = Integer.parseInt(comps[1]);
            year = Integer.parseInt(comps[2]);
        }

        rtn.putInt("day", day);
        rtn.putInt("month", month-1);
        rtn.putInt("year", year);

        return rtn;
    }

    private void refreshErrorMessage() {
        // set the error message
        //TextView tvError = (TextView) findViewById(R.id.error);
        //tvError.setText(error);

        //if (error == null || error.length() == 0) {
        //    tvError.setVisibility(View.GONE);
        //} else {
        //    tvError.setVisibility(View.VISIBLE);
        //}

    }

    public void setTime(int id, int h, int m) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d:%02d", h, m));
    }

    public void setDate(int id, int d, int m, int y) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d-%02d-%04d", d, m + 1, y));
    }

    public void plantTree(View view) {

        AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View mView = inflater.inflate(R.layout.planttree_dialog, null);
        builder.setView(mView);

        builder.setCancelable(true);

        builder.setPositiveButton(R.string.plant, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                EditText ownerTextView = (EditText) mView.findViewById(R.id.ownername);
                EditText speciesTextView = (EditText) mView.findViewById(R.id.treespecies);
                EditText longitudeTextView = (EditText) mView.findViewById(R.id.treelongitude);
                EditText latitudeTextView = (EditText) mView.findViewById(R.id.treelatitude);
                EditText heightTextView = (EditText) mView.findViewById(R.id.treeheight);
                EditText diameterTextiew = (EditText) mView.findViewById(R.id.treediameter);

                httpPostTree(String.valueOf(ownerTextView.getText()), String.valueOf(speciesTextView.getText()), String.valueOf(heightTextView.getText()), String.valueOf(diameterTextiew.getText()),
                        String.valueOf(longitudeTextView.getText()), String.valueOf(latitudeTextView.getText()));
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

    public void httpPostTree(String owner, String species, String height, String diameter,  String longitude, String latitude){

        RequestParams rp = new RequestParams();
        int randomNum = ThreadLocalRandom.current().nextInt(10000000, 99999998 + 1);
        java.util.Date c = Calendar.getInstance().getTime();
        java.sql.Date sqlDate = new java.sql.Date(c.getTime());
        
        HttpUtils.post("trees/" + species +"?" + "height=" + height +"&age=" + 1 + "&date=" + sqlDate
                + "&diameter=" + Float.valueOf(diameter) + "&id=" + randomNum + "&personName=" + owner + "&latitude=" + Float.valueOf(latitude)
                + "&longitude=" + Float.valueOf(longitude) + "&municipality=NDG" , new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
    }

    public void onMap(View view) {

        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);

        /*
        AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.cuttree_dialog, null));

        builder.setCancelable(true);

        builder.setPositiveButton(R.string.cut, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                TextView idTextView = (TextView) findViewById(R.id.treeid);
                httpRemoveTree(idTextView.toString());
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
        */

    }

    public void httpRemoveTree(String id){

        /*
        error = "";
        HttpUtils.("trees/", new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
        */

    }

    /*
    public void listTrees(View view) {
    }
    */
}
