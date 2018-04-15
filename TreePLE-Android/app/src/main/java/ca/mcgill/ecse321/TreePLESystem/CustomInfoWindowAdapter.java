package ca.mcgill.ecse321.TreePLESystem;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;


public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    /* This class implements Google Map's info window so that when a user clicks on a tree, they can
        view important information. For now, it optains the snippet of information from the marker,
        and displays the species name, owner name, height of the tree(cm) and status
    */
    private Activity context;
    public Bitmap imageBitmap;
    public Bitmap resizedBitmap;
    public Typeface quickRegular;

    public CustomInfoWindowAdapter(Activity context){
        this.context = context;
        this.quickRegular = Typeface.createFromAsset(this.context.getAssets(),
                "fonts/quicksandregular.ttf");
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {

        View view = context.getLayoutInflater().inflate(R.layout.custominfowindow, null);

        String species = marker.getTitle();
        String snippetInfo[] = splitSnippets(marker.getSnippet());
        String ownername = snippetInfo[0];
        String height = snippetInfo[1];
        String status = snippetInfo[2];


        TextView speciestv = (TextView) view.findViewById(R.id.speciesName);
        TextView  ownertv= (TextView) view.findViewById(R.id.ownername);
        ImageView ivIcon = (ImageView) view.findViewById(R.id.treeIcon);
        TextView heighttv = (TextView) view.findViewById(R.id.treeheight);
        TextView statustv = (TextView) view.findViewById(R.id.status);

        speciestv.setTypeface(quickRegular);
        speciestv.setText(species);

        ownertv.setTypeface(quickRegular);
        ownertv.setText(ownername);

        heighttv.setTypeface(quickRegular);
        heighttv.setText(height + "cm");

        statustv.setTypeface(quickRegular);
        statustv.setText(status);

        imageBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.treemapicon);
        resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, 100, 100, false);

        ivIcon.setImageBitmap(resizedBitmap);


        return view;

    }

    /*
        This method separates the snippets into an array so that we can extract the important information
    */
    public static String[] splitSnippets(String snippet){
        String[] arr=snippet.split("\\^");
        return arr;
    }
}
