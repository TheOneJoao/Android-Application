package com.outspin.app.ui.camera;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.outspin.app.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DisplayImageActivity extends AppCompatActivity {
    private ImageView imageView;
    private ImageButton imageButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_image_activity);

        imageView = (ImageView) findViewById(R.id.imageView);
        imageButton = (ImageButton) findViewById(R.id.cameraButton);

        Intent intent = getIntent();
        ImageFile imageFile = (ImageFile) intent.getSerializableExtra("ImageFile");

        try{
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(imageFile.getImageFileName()));
            imageView.setImageBitmap(b);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(DisplayImageActivity.this, CameraActivity.class);
                startActivity(cameraIntent);
            }
        });
    }
}