package com.example.gurbookreviewer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Activity1 extends AppCompatActivity {

    ConnectionClass connectionClass; // var
    Connection conn;
    Button btnDB;
    EditText editTextSearch;
    HashMap<String, Integer> bookImageMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        connectionClass = new ConnectionClass();
        connect();

        bookImageMap = new HashMap<>();
        bookImageMap.put("Hunger Games", R.drawable.hg);
        bookImageMap.put("The Midnight Library", R.drawable.tml);
        bookImageMap.put("Dracula", R.drawable.dracula);
        bookImageMap.put("The Catcher in the Rye", R.drawable.rye);
        bookImageMap.put("To Kill a Mockingbird", R.drawable.mockingbirdie);
        bookImageMap.put("1984", R.drawable.nineteen84);
        bookImageMap.put("Little Women", R.drawable.littlewomen);
        bookImageMap.put("Lord of the Rings", R.drawable.lotr);
        bookImageMap.put("Tomorrow, and Tomorrow, and Tomorrow", R.drawable.tatat);
        bookImageMap.put("Pride and Prejudice", R.drawable.pap);
        bookImageMap.put("The Chronicles of Narnia", R.drawable.narnia);
        bookImageMap.put("Animal Farm", R.drawable.farm);
        bookImageMap.put("Frankenstein", R.drawable.franke);
        bookImageMap.put("Harry Potter", R.drawable.hp);
        bookImageMap.put("Catch-22", R.drawable.catch22);

        btnDB = findViewById(R.id.btnDB);
        editTextSearch = findViewById(R.id.searchText);

        btnDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = editTextSearch.getText().toString().trim();
                if (searchText.isEmpty()) {
                    Toast.makeText(Activity1.this, "Please enter a book name to search", Toast.LENGTH_SHORT).show();
                }

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(() -> {
                    StringBuilder bStr = new StringBuilder();
                    int imageResId=0;

                    try {
                        conn = connectionClass.CONN();
                        if (conn != null) {
                            String query = "SELECT name, authorName, description FROM books WHERE name = ?";
                            PreparedStatement stmt = conn.prepareStatement(query);
                            stmt.setString(1, searchText);
                            ResultSet rs = stmt.executeQuery();
                            if (rs.next()) {
                                bStr.append(rs.getString("name"));
                                bStr.append(" by ").append(rs.getString("authorName")).append("\n\n");
                                bStr.append("Description: ").append(rs.getString("description")).append("\n\n");
                                imageResId = bookImageMap.getOrDefault(rs.getString("name"), 0);
                            }

                            else {
                                runOnUiThread(() -> {
                                    Intent i = new Intent(Activity1.this, NotAvailable.class);
                                    startActivity(i);
                                });
                            }
                        }
                    } catch (SQLException e) {
                        bStr.append("SQL Exception: ").append(e.getMessage());
                    }

                    if (bStr.length() > 1) {
                        final int finalImageResId = imageResId;
                        runOnUiThread(() -> {
                            Intent intent = new Intent(Activity1.this, Activity2.class);
                            intent.putExtra("bookData", bStr.toString());
                            intent.putExtra("imageResId", finalImageResId);
                            startActivity(intent);
                        });
                    }
                });
            }
        });
    }

    public void connect() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                conn = connectionClass.CONN();
                if (conn == null) {
                    runOnUiThread(() -> Toast.makeText(Activity1.this, "Error connecting to database", Toast.LENGTH_SHORT).show());
                } else {
                    runOnUiThread(() -> Toast.makeText(Activity1.this, "Connected to MySQL database", Toast.LENGTH_SHORT).show());
                }
            } catch (Exception e) {
                runOnUiThread(() -> Toast.makeText(Activity1.this, "Exception: " + e.toString(), Toast.LENGTH_SHORT).show());
            }
        });
    }
}
