package com.dark.hearme;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.time.Instant;

public class AddNewPhotoActivity extends AppCompatActivity {
    ImageView mAddNewPhotoIv;
    ImageView mAddNewSoundIv;
    Button mSelectPhotoBtn;
    Button mSelectSoundBtn;
    Button mSubmitBtn;
    Uri mSelectedPhotoUri;
    private static final int READ_PHOTO_FROM_GALLERY_PERMISSION = 130;
    private static final int PICK_IMAGE= 131;
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
            selectPhoto();
            }
        });
        mSelectSoundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectSound();
            }
        });
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // If request is cancelled, the result arrays are empty.
        if (requestCode == READ_PHOTO_FROM_GALLERY_PERMISSION){
            //condition to check if the permission is granted or not
            if(grantResults.length >0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                firePhotoPickerIntent();

            }else {
                Toast.makeText(this, R.string.read_permisstion_needed_to_access_file, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && data != null && data.getData() != null) {
            // Get the image from data
            setSelectedPhoto(data.getData());
            getContentResolver().takePersistableUriPermission(mSelectedPhotoUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }else
            Toast.makeText(this, R.string.error_selecting_photo, Toast.LENGTH_SHORT).show();
    }

    private void setSelectedPhoto(Uri data) {
        mAddNewPhotoIv.setImageURI(data);
        mSelectedPhotoUri = data;
    }

    private void selectPhoto(){
         if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                 != PackageManager.PERMISSION_GRANTED) {
          ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},READ_PHOTO_FROM_GALLERY_PERMISSION );
         }
         else {
             //call activity to select photo
         firePhotoPickerIntent();
         }
         }
    private void selectSound(){
        Toast.makeText(AddNewPhotoActivity.this, "Select Sound", Toast.LENGTH_SHORT).show();
    }
    private void submit(){
        Toast.makeText(AddNewPhotoActivity.this, "Submit", Toast.LENGTH_SHORT).show();
    }
    //create inplicit intent to get to address file type photo
    private void firePhotoPickerIntent() {
        //create an intent to get to address file type photo
        Intent intent =new Intent();
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        //set the type of file to get
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        //start the activity to get the file
        startActivityForResult(Intent.createChooser(intent,getString(R.string.select_picture)),PICK_IMAGE);
    }

}