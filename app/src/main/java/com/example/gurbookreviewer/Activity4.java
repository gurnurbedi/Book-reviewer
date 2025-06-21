package com.example.gurbookreviewer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Activity4 extends AppCompatActivity {

    ConnectionClass connectionClass;
    Connection conn;
    TextView txtReviewDisp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        String bookName = getIntent().getStringExtra("bookName");
        connectionClass = new ConnectionClass();
        txtReviewDisp = findViewById(R.id.txtViewReviewDisp);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            StringBuilder bStr = new StringBuilder("\n");
            try {
                conn = connectionClass.CONN();
                if (conn != null) {
                    String query = "SELECT bookName, bookReview, userName FROM review WHERE bookName = ?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, bookName);
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        bStr.append("User: ").append(rs.getString("userName")).append("\n");
                        bStr.append("Review: ").append(rs.getString("bookReview")).append("\n");
                        bStr.append("\n");
                    }
                }
            } catch (SQLException e) {
                bStr.append("SQL Exception: ").append(e.getMessage());
            }

            txtReviewDisp.setText(bStr.toString());
        });
    }
}
