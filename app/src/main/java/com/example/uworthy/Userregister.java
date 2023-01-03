package com.example.uworthy;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class Userregister extends AppCompatActivity {
    String Storage_Path = "All_Image_Uploads_User/";

    // Root Database Name for Firebase Database.
    public static final String Database_Path = "All_Image_Uploads_Database_User";

  private  EditText name1,mobile,emailidedit,addressedit,usernameedit,passwordedit,cpasswordedit;
    Button save,browserbtn;
    String gender;
    RadioButton malebtn,femalebtn;
    ImageView profileimg;
    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;

    int Image_Request_Code = 7;
    StorageReference storageReference;
    ProgressDialog progressDialog ;
    Uri FilePathUri;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference =firebaseDatabase.getReference();
    private DatabaseReference  mChildreference = databaseReference.child("user");









    @Override
    protected void onStart() {
        super.onStart();
        mChildreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userregister);
        FirebaseApp.initializeApp(this);




        storageReference = FirebaseStorage.getInstance().getReference();

        // Assign FirebaseDatabase instance with root database name.
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);
        progressDialog = new ProgressDialog(Userregister.this);

        browserbtn=findViewById(R.id.browseruser);
        browserbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                // Setting intent type as image to select image from phone storage.
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Please Select Image"), Image_Request_Code);

            }
        });
        profileimg=findViewById(R.id.profileimage);
        malebtn=(RadioButton)findViewById(R.id.radiomale);
        femalebtn=(RadioButton)findViewById(R.id.radiofemale);
        emailidedit=(EditText) findViewById(R.id.editemailid);

        addressedit=(EditText)findViewById(R.id.editaddress);
        usernameedit=(EditText)findViewById(R.id.editusername);
        passwordedit=(EditText)findViewById(R.id.editpassword);
        cpasswordedit=(EditText)findViewById(R.id.editconfirmpass);
        if(malebtn.isChecked()){
            gender=malebtn.getText().toString();
        }
        else if(femalebtn.isChecked()){
            gender=femalebtn.getText().toString();
        }
        name1=(EditText)findViewById(R.id.editname);
        mobile=(EditText)findViewById(R.id.editphone);
        save=(Button)findViewById(R.id.usersavebtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UploadImageFileToFirebaseStorage();



            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {

                // Getting selected image into Bitmap.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);

                // Setting up bitmap selected image into ImageView.
                profileimg.setImageBitmap(bitmap);

                // After selecting image change choose button above text.
                // ChooseButton.setText("Image Selected");

            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    // Creating Method to get the selected image file Extension from File Path URI.
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }

    public void UploadImageFileToFirebaseStorage() {

        // Checking whether FilePathUri Is empty or not.
        if (FilePathUri != null) {

            // Setting progressDialog Title.
            progressDialog.setTitle("Image is Uploading...");

            // Showing progressDialog.
            progressDialog.show();

            // Creating second StorageReference.
            final StorageReference storageReference2nd = storageReference.child(Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));

            // Adding addOnSuccessListener to second StorageReference.
            storageReference2nd.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            // Getting image name from EditText and store into string variable.
                            String TempImageName = name1.getText().toString().trim();

                            // taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();

                            //  Toast.makeText(UploadActivity.this,"Upload successful",Toast.LENGTH_SHORT).show();
                            //progressDialog.dismiss();
                            // Hiding the progressDialog after done uploading.
                            progressDialog.dismiss();

                            // Showing toast message after done uploading.
                            Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(),Choosecolor.class);
                            startActivity(i);

                            String emailid=emailidedit.getText().toString().trim();


                            String clnicaddress=addressedit.getText().toString().trim();
                            String useername=usernameedit.getText().toString().trim();
                            String password=passwordedit.getText().toString().trim();
                            String usertype="1";
                            String name = name1.getText().toString().trim();
                            String mobileno= mobile.getText().toString().trim();
                            String id= databaseReference.push().getKey();

                            @SuppressWarnings("VisibleForTests")
                            Savedatauser savedate = new Savedatauser(id,name,mobileno,emailid,gender,clnicaddress,useername,password,usertype,TempImageName,taskSnapshot.getStorage().getDownloadUrl().toString());

                            //ImageUploadInfo imageUploadInfo = new ImageUploadInfo(TempImageName, taskSnapshot.getDownloadUrl().toString());

                            // Getting image upload ID.
                            // String ImageUploadId = databaseReference.push().getKey();

                            // Adding image upload id s child element into databaseReference.
                            databaseReference.child(id).setValue(savedate);
                        }
                    })
                    // If something goes wrong .
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                            // Hiding the progressDialog.
                            progressDialog.dismiss();

                            // Showing exception erro message.
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })

                    // On progress change upload time.
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            // Setting progressDialog Title.
                            progressDialog.setTitle("Image is Uploading...");

                        }
                    });
        }
        else {

            Toast.makeText(Userregister.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }
    }

}
