package com.example.gurbookreviewer;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Activity6 extends AppCompatActivity {

  public TextView reviewTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);

        reviewTextView = findViewById(R.id.reviewtxtView);

        float rating1 = getIntent().getFloatExtra("RATING1", 0);
        float rating2 = getIntent().getFloatExtra("RATING2", 0);
        float rating3 = getIntent().getFloatExtra("RATING3", 0);

        String review = "You rated Tomorrow, and Tomorrow, and Tomorrow by Gabrielle Zevin " + rating1 + " stars\n\n\n" +
                "You rated The Midnight Library by Matt Haig: " + rating2 + " stars\n\n\n" +
                "You rated Harry Potter by J.K. Rowling: " + rating3 + " stars";

        reviewTextView.setText(review);
    }
}
