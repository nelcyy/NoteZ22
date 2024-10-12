package com.example.notez;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.pm.PackageManager;

public class note_display extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 100;
    private ImageView cameraImageView;
    private EditText noteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_display); // Use your actual layout file

        // Initialize camera icon and note field
        cameraImageView = findViewById(R.id.imageView5); // Your camera icon ID
        noteEditText = findViewById(R.id.textView5); // Your note field ID

        // Set up the camera click listener
        cameraImageView.setOnClickListener(v -> checkCameraPermission());
    }

    private boolean hasCamera() {
        boolean hasCamera = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA) ||
                getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
        Log.d("CameraCheck", "Has camera: " + hasCamera);
        return hasCamera;
    }


    private void openCamera() {
        if (!hasCamera()) {
            Toast.makeText(this, "No camera found on this device", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
        } else {
            Log.d("CameraCheck", "No app available to handle camera intent");
            Toast.makeText(this, "Camera not supported", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkCameraPermission() {
        if (checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            // Permission is granted, open the camera
            openCamera();
        } else {
            // Permission is not granted, request for permission
            requestPermissions(new String[]{android.Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
            Log.d("CameraCheck", "Camera permission not granted");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @Nullable String[] permissions, @Nullable int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_REQUEST_CODE) {
            // Check if the request was granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, open the camera
                openCamera();
            } else {
                // Permission denied, show a toast message
                Toast.makeText(this, "Camera permission is required to use the camera", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");

            // Display the captured image in the note (as text or in an image view)
            if (photo != null) {
                // Assuming the note text displays the image information
                noteEditText.setText("Image captured: " + photo.toString());
                // Optionally, you can save the image to storage and update the note with the file path
            }
        }
    }
}
