package com.example.gurbookreviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        EditText txtPersonName = findViewById(R.id.txtPersonName);
        EditText txtInput = findViewById(R.id.txtInput);
        Button btnPost = findViewById(R.id.btnPost);
        Button btnSee = findViewById(R.id.btnSee);

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookReview = txtInput.getText().toString();
                String userName = txtPersonName.getText().toString();
                String bookName = getIntent().getStringExtra("bookName");

                if (bookReview.isEmpty()) {
                    Toast.makeText(Activity3.this, "Please enter a review", Toast.LENGTH_SHORT).show();
                } else {
                    new SaveReviewTask().execute(bookName, bookReview, userName);
                }
            }
        });

        btnSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Activity3.this, Activity4.class);
                String bookName = getIntent().getStringExtra("bookName");//
                i.putExtra("bookName", bookName);
                startActivity(i);
            }
        });

    }

    private class SaveReviewTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            String bookName = params[0];
            String bookReview = params[1];
            String userName = params[2];

            ConnectionClass connectionClass = new ConnectionClass();
            return connectionClass.saveReview(bookName, bookReview,userName);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Toast.makeText(Activity3.this, "Review saved successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Activity3.this, "Failed to save review.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
