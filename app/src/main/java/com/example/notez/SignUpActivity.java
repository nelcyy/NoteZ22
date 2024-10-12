package com.example.notez; // Change this to your actual package name

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private TextView signInText;
    private Button signUpButton; // Declare the Sign Up button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up); // Ensure you create this layout

        signInText = findViewById(R.id.sign_up_text);
        signUpButton = findViewById(R.id.sign_in_button); // Referencing the Sign Up button

        // Set an OnClickListener for the Sign In text
        signInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Sign In activity
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        // Set an OnClickListener for the Sign Up button (if you want to handle Sign Up logic)
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add logic to handle user sign up
                // This could involve validating inputs and sending data to a server
            }
        });
    }
}
