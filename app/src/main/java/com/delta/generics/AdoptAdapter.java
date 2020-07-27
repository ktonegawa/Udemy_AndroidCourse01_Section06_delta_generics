package com.delta.generics;

import android.app.Activity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class AdoptAdapter<T extends Animal & Adoptable> {
    private Activity activity;
    private TextView name;
    private TextView description;
    private RatingBar ratingBar;
    private ImageView imageView;

    // T stands for "type"
    private T t;

    public AdoptAdapter(Activity aActivity, TextView aName, TextView aDescription, RatingBar aBar, ImageView anImageView){
        this.activity = aActivity;
        this.name = aName;
        this.description = aDescription;
        this.ratingBar = aBar;
        this.imageView = anImageView;
    }

    public void set(T t){
        this.t = t;
        updateView();
    }

    public T get(){
        return t;
    }

    private void updateView(){
        // because we extended Animal, now we have access to all of these methods/attributes
        int resID = t.getImageResourceId(activity);
        imageView.setImageResource(resID);
        name.setText(t.getName());
        description.setText(t.getDescription());
        ratingBar.setNumStars(5);
        ratingBar.setRating(t.getRating()); //getRating() is available because we are extending Adoptable as well
    }
}
