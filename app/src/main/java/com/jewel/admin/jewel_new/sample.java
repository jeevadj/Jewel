package com.jewel.admin.jewel_new;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class sample extends AppCompatActivity {

    ImageView im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        im = (ImageView)findViewById(R.id.roundedImageView);
        //im.setClipToOutline(true);
    }
}
