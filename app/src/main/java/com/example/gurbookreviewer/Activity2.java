package com.example.gurbookreviewer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        String data = getIntent().getStringExtra("bookData");
        int imageResId = getIntent().getIntExtra("imageResId", 0);

        TextView txtListDisplay = findViewById(R.id.txtViewDisp);
        txtListDisplay.setText(data);

        ImageView imgBook = findViewById(R.id.imageBookCover);
        if (imageResId != 0)
        {
            imgBook.setImageResource(imageResId);
            imgBook.setVisibility(View.VISIBLE);
        }

        else
        {
            imgBook.setVisibility(View.GONE);
        }


        Button btnSubmitReview = findViewById(R.id.btnReview);

        btnSubmitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookName = data.split(" by ")[0];

                Intent intent = new Intent(Activity2.this, Activity3.class);
                intent.putExtra("bookName", bookName);
                startActivity(intent);
            }
        });
    }
}
