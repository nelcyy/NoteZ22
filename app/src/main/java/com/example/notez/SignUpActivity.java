package com.example.notez; // Change this to your actual package name

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.notez.databinding.SignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private TextView signInText;
    private Button signUpButton; // Declare the Sign Up button
    SignUpBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); // Ensure you create this layout

        databaseHelper = new DatabaseHelper(this);

        signInText = findViewById(R.id.sign_up_text);
        signUpButton = findViewById(R.id.sign_in_button); // Referencing the Sign Up button

        // Set an OnClickListener for the Sign Up button (if you want to handle Sign Up logic)
        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.username.getText().toString();
                String password = binding.password.getText().toString();
                String retypePassword = binding.retypePassword.getText().toString();

                if (username.equals("") || password.equals("") || retypePassword.equals("")) {
                    Toast.makeText(SignUpActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(retypePassword)) {
                        Boolean checkUsername = databaseHelper.checkUsername(username);

                        if (checkUsername == false) {
                            Boolean insert = databaseHelper.insertData(username, password);

                            if (insert == true) {
                                Toast.makeText(SignUpActivity.this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignUpActivity.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignUpActivity.this, "User already exists, Please Sign In", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignUpActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Set an OnClickListener for the Sign In text
        binding.signInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Sign In activity
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }
}
