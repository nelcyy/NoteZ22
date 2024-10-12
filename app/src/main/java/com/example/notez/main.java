package com.example.notez;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class main extends AppCompatActivity {

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.main);

        // Handle window insets (for system bars)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve the username passed from SignInActivity
        Intent intent = getIntent();
        username = intent.getStringExtra("USERNAME");

        // Get references to the plus and circle ImageViews
        ImageView plusImageView = findViewById(R.id.plus);
        ImageView circleImageView = findViewById(R.id.circle);

        // Set onClick listener for plus ImageView
        plusImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start note_display activity when the plus button is clicked, passing the username
                Intent intent = new Intent(main.this, note_display.class);
                intent.putExtra("USERNAME", username);
                startActivity(intent);
            }
        });

        // Set onClick listener for circle ImageView if needed
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start note_display activity when the circle button is clicked, passing the username
                Intent intent = new Intent(main.this, note_display.class);
                intent.putExtra("USERNAME", username);
                startActivity(intent);
            }
        });
    }
}
