package com.example.notez;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class note_display extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText titleEditText;
    private EditText subtitleEditText;
    private EditText noteContentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.note_display);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Bind the EditText fields
        titleEditText = findViewById(R.id.editTextTextPostalAddress2); // Title EditText
        subtitleEditText = findViewById(R.id.editTextTextPostalAddress); // Subtitle EditText
        noteContentEditText = findViewById(R.id.textView5); // Note content EditText

        // Get reference to the back arrow ImageView
        ImageView backArrowImageView = findViewById(R.id.imageView);
        backArrowImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close this activity and return to the previous activity
                finish();
            }
        });

        // Get reference to the check circle ImageView
        ImageView checkCircleImageView = findViewById(R.id.imageView2);
        checkCircleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote(); // Call save note method when clicked
            }
        });
    }

    // Method to save the note
    private void saveNote() {
        String title = titleEditText.getText().toString().trim(); // Get title
        String subtitle = subtitleEditText.getText().toString().trim(); // Get subtitle
        String content = noteContentEditText.getText().toString().trim(); // Get note content

        if (!title.isEmpty() && !subtitle.isEmpty() && !content.isEmpty()) {
            // Insert the note into the database
            boolean isSaved = databaseHelper.insertNote(title, subtitle, content);
            if (isSaved) {
                Toast.makeText(this, "Note saved successfully!", Toast.LENGTH_SHORT).show();
                finish(); // Close the activity after saving
            } else {
                Toast.makeText(this, "Failed to save note.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please fill all fields.", Toast.LENGTH_SHORT).show();
        }
    }
}
