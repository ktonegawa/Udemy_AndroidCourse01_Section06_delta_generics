package com.delta.generics;

import android.app.Activity;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.service.dreams.DreamService;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class GenericsActivity extends Activity {

    public TextView nameTextView = null;
    public TextView descriptionTextView = null;
    public RatingBar ratingView = null;
    public ImageView portraitView = null;
    public Button nextButton = null;

    private int currentSelection = 0;

//    CatAdapter catAdapter;
    AdoptAdapter<Cat> catAdoptAdapter;
    AdoptAdapter<Dog> dogAdoptAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generics);

        nameTextView = (TextView) findViewById(R.id.nameTextView);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        ratingView = (RatingBar) findViewById(R.id.ratingView);
        portraitView = (ImageView) findViewById(R.id.portraitView);
        nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNext();
            }
        });

//        commenting this out in favour of AdoptAdapter objects
//        catAdapter = new CatAdapter(this,nameTextView,descriptionTextView,ratingView,portraitView);
//        catAdapter.set(AdoptData.mCatList.get(0));

        catAdoptAdapter = new AdoptAdapter<Cat>(this, nameTextView, descriptionTextView, ratingView, portraitView);
        dogAdoptAdapter = new AdoptAdapter<Dog>(this, nameTextView, descriptionTextView, ratingView, portraitView);

        catAdoptAdapter.set(AdoptData.mCatList.get(0));
//        dogAdoptAdapter.set(AdoptData.mDogList.get(0));
        // mCatList and mDogList is an object already exists in AdoptData.java

    }

    public void showNext(){
        int random = currentSelection;
        int animal_random = 0;
        animal_random = (int )(Math.random() * 2);
        Log.e("animal_random", String.valueOf(animal_random));
        switch(animal_random){
            case 0:
                while(random == currentSelection){
                    //avoid same selection twice.
                    random = (int )(Math.random() * AdoptData.mCatList.size());
                }
                currentSelection = random;
                Cat c = AdoptData.mCatList.get(random);
                catAdoptAdapter.set(c);
                break;
            case 1:
                while(random == currentSelection){
                    //avoid same selection twice.
                    random = (int )(Math.random() * AdoptData.mDogList.size());
                }
                currentSelection = random;
                Dog d = AdoptData.mDogList.get(random);
                dogAdoptAdapter.set(d);
                break;

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.generics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
