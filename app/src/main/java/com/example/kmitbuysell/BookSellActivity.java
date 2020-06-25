package com.example.kmitbuysell;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class BookSellActivity extends AppCompatActivity {
    public static final int PICK_IMAGE_REQUEST=1;
    private Button mupload;
    private EditText mbookname,mprice,mbdescription;
    private ImageView mimage;
    private Spinner mcategory;
    private Uri mimageuri;
    private StorageReference mstorageref;
    private DatabaseReference mdatabaseref;
    private Button post;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_sell);
        mupload=findViewById(R.id.upload);
        mbookname=findViewById(R.id.bookname);
        mimage=findViewById(R.id.imageView2);
        mbdescription=findViewById(R.id.mbdesc);
        mcategory=findViewById(R.id.category);
        mprice=findViewById(R.id.price);
        post=findViewById(R.id.post);
        radioGroup=findViewById(R.id.rgp);
        mstorageref= FirebaseStorage.getInstance().getReference().child("Photos");
        mdatabaseref= FirebaseDatabase.getInstance().getReference("uploads");
        mupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
            }
        });
    }
    private void openFileChooser()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK
                && data!=null && data.getData()!=null)
        {
            mimageuri=data.getData();
            Picasso.with(this).load(mimageuri).into(mimage);
        }
    }
    private  String getFileExtension(Uri uri)
    {
        ContentResolver cR=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadFile()
    {
         /*if(mimageuri!=null)
         {Uri x;
             StorageReference fileReference=mstorageref.child(System.currentTimeMillis()
             +"."+getFileExtension(mimageuri));
             UploadTask ut= (UploadTask) fileReference.putFile(mimageuri);
             Task<Uri> urlTask = ut.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                 @Override
                 public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                     if (!task.isSuccessful()) {
                         throw task.getException();
                     }

                     // Continue with the task to get the download URL
                     return mstorageref.getDownloadUrl();
                 }
             }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                 @Override
                 public void onComplete(@NonNull Task<Uri> task) {
                     if (task.isSuccessful()) {
                         Uri downloadUri = task.getResult();
                         Toast.makeText(MainActivity.this,"Succesful",Toast.LENGTH_LONG).show();
                         String s=mcategory.getSelectedItem().toString();
                         Book book=new Book(downloadUri.toString(),mbookname.getText().toString(),mprice.getText().toString(),s);
                         String id=mdatabaseref.push().getKey();
                         mdatabaseref.child(id).setValue(book);

                     } else {
                         // Handle failures
                         // ...
                     }
                 }
             });
                    /* ut.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                         @Override
                         public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                          Toast.makeText(MainActivity.this,"Succesful",Toast.LENGTH_LONG).show();
                          String s=mcategory.getSelectedItem().toString();
                          Book book=new Book(,mbookname.getText().toString(),mprice.getText().toString(),s);
                          String id=mdatabaseref.push().getKey();
                         mdatabaseref.child(id).setValue(book);
                         }
                     })
                     .addOnFailureListener(new OnFailureListener() {
                         @Override
                         public void onFailure(@NonNull Exception e) {
                           Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                         }
                     });

         }
         else
         {
             Toast.makeText(this,"Insert Image",Toast.LENGTH_LONG).show();
         }*/
        /*if (mimageuri != null)
        {StorageReference fileReference=mstorageref.child(System.currentTimeMillis()
                +"."+getFileExtension(mimageuri));
            UploadTask ut= (UploadTask) fileReference.putFile(mimageuri);
            ut.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>()
            {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                {
                    if (!task.isSuccessful())
                    {
                        throw task.getException();
                    }
                    return mstorageref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>()
            {
                @Override
                public void onComplete(@NonNull Task<Uri> task)
                {
                    if (task.isSuccessful())
                    {
                        Uri downloadUri = task.getResult();
                        // Log.e(TAG, "then: " + downloadUri.toString());


                        Book book = new Book(
                                downloadUri.toString()
                                ,mbookname.getText().toString()
                                ,mprice.getText().toString()
                                ,mcategory.getSelectedItem().toString()
                        ,mbdescription.toString());

                        mdatabaseref.push().setValue(book);
                    } else
                    {
                        Toast.makeText(BookSellActivity.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }*/
        /*if(mimageuri!=null)
        {
            final StorageReference fileReference=mstorageref.child(System.currentTimeMillis()
                    +"."+getFileExtension(mimageuri));
            fileReference.putFile(mimageuri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            Task<Uri> downloadUrl = fileReference.getDownloadUrl();
                            Uri duri=downloadUrl.getResult();
                            Book book = new Book(
                                    duri.toString()
                                    ,mbookname.getText().toString()
                                    ,mprice.getText().toString()
                                    ,mcategory.getSelectedItem().toString()
                                    ,mbdescription.toString());

                            mdatabaseref.push().setValue(book);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                        }
                    });
        }*/
        if (mimageuri != null)
        {final StorageReference fileReference=mstorageref.child(System.currentTimeMillis()
                +"."+getFileExtension(mimageuri));
            fileReference.putFile(mimageuri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>()
            {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                {
                    if (!task.isSuccessful())
                    {
                        throw task.getException();
                    }
                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>()
            {
                @Override
                public void onComplete(@NonNull Task<Uri> task)
                {
                    if (task.isSuccessful())
                    {if(mbookname.getText().toString()==null)
                    {
                        mbookname.setError("Fill this up!");
                    }
                        Uri downloadUri = task.getResult();
                        //Log.e(TAG, "then: " + downloadUri.toString());
                        int t=radioGroup.getCheckedRadioButtonId();
                        Book book = new Book(
                                downloadUri.toString()
                                ,mbookname.getText().toString()
                                ,mprice.getText().toString()
                                ,mcategory.getSelectedItem().toString()
                                ,mbdescription.toString()
                        ,t);

                        mdatabaseref.push().setValue(book);
                    } else
                    {
                        Toast.makeText(BookSellActivity.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
