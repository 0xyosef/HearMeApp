package com.dark.hearme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class AddNewPhotoActivity extends AppCompatActivity {
    ImageView mAddNewPhotoIv;
    ImageView mAddNewSoundIv;
    Button mSelectPhotoBtn;
    Button mSelectSoundBtn;
    Button mSubmitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_photo);
        init();
    }
    private void init() {
        mAddNewPhotoIv = findViewById(R.id.imageViewNewPhoto);
        mAddNewSoundIv = findViewById(R.id.imageViewSoundAdd);
        mSelectPhotoBtn = findViewById(R.id.buttonSelectPhoto);
        mSelectSoundBtn = findViewById(R.id.buttonSelectSound);
        mSubmitBtn = findViewById(R.id.buttonSubmit);
        initListeners();
    }
    private void initListeners() {
        mSelectPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
            addPhoto();
            }
        });
        mSelectSoundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSound();
            }
        });
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }
    private void addPhoto(){
        Toast.makeText(AddNewPhotoActivity.this, "Select Photo", Toast.LENGTH_SHORT).show();
    }
    private void addSound(){
        Toast.makeText(AddNewPhotoActivity.this, "Select Sound", Toast.LENGTH_SHORT).show();
//
    }
    private void submit(){
        Toast.makeText(AddNewPhotoActivity.this, "Submit", Toast.LENGTH_SHORT).show();
    }
}