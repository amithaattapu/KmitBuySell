package com.example.kmitbuysell;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BooksActivity extends AppCompatActivity {
    private RecyclerView mrecyclerview;
    private BookAdapter mbookadapter;
    private DatabaseReference databaseReference;
    private List<Book> list;
    //private SearchView sv;
    private androidx.appcompat.widget.SearchView sv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        list=new ArrayList<Book>();
        databaseReference= FirebaseDatabase.getInstance().getReference("uploads");
        mrecyclerview=findViewById(R.id.rv);
        sv=findViewById(R.id.searchview);
        mrecyclerview.setHasFixedSize(true);
        mrecyclerview.setLayoutManager(new GridLayoutManager(this,2));
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    Book book=dataSnapshot1.getValue(Book.class);
                    list.add(book);
                }
                mbookadapter=new BookAdapter(BooksActivity.this,list);
                mrecyclerview.setAdapter(mbookadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BooksActivity.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (sv != null) {
            sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });

        }
    }
    public void search(String s)
    {
        ArrayList<Book> al=new ArrayList<Book>();
        for(Book a:list)
        {
            if(a.getBookName().toLowerCase().contains(s.toLowerCase()))
            {
                al.add(a);
            }
        }
        BookAdapter ba=new BookAdapter(this,al);
        mrecyclerview.setAdapter(ba);
    }
}