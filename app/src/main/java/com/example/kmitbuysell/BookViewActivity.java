package com.example.kmitbuysell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BookViewActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view);
        Intent intent=getIntent();
        String name=intent.getExtras().get("name").toString();
        String imageurl=intent.getExtras().get("image").toString();
        imageView=findViewById(R.id.imageView3);
        textView=findViewById(R.id.textcost);
        textView.setText(name);
        //imageView.setImageURI(Uri.parse(imageurl));
        //imageView.setImage
        Picasso.with(this)
                .load(imageurl)
                .fit()
                .centerCrop()
                .into(imageView);
    }
}
