package com.example.gurbookreviewer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import androidx.appcompat.app.AppCompatActivity;

public class Activity5 extends AppCompatActivity {

    public RatingBar ratingBar1, ratingBar2, ratingBar3;
    public Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        ratingBar1 = findViewById(R.id.ratingBar1);
        ratingBar2 = findViewById(R.id.ratingBar2);
        ratingBar3 = findViewById(R.id.ratingBar3);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating1 = ratingBar1.getRating();
                float rating2 = ratingBar2.getRating();
                float rating3 = ratingBar3.getRating();

                Intent intent = new Intent(Activity5.this, Activity6.class);
                intent.putExtra("RATING1", rating1);
                intent.putExtra("RATING2", rating2);
                intent.putExtra("RATING3", rating3);
                startActivity(intent);
            }
        });
    }
}
