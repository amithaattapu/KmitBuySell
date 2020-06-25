package com.example.kmitbuysell;

import android.content.Context;
//import android.support.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ImageViewHolder> {
    private Context mcontext;
    private List<Book> mBooks;

    public BookAdapter(Context mcontext, List<Book> mbooks) {
        this.mcontext = mcontext;
        this.mBooks = mbooks;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.card, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Book book = mBooks.get(position);
        holder.textviewname.setText(book.getBookName());
        holder.price.setText(book.getPrice());
        Picasso.with(mcontext)
                .load(book.getImageURL())
                .fit()
                .centerCrop()
                .into(holder.imageview);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent=new Intent(mcontext,BookViewActivity.class);
                intent.putExtra(mBooks.get(position).getBookName().toString(),"bookname");
                Bundle bundle=new Bundle();
                bundle.putString("name",mBooks.get(position).getBookName());
                bundle.putString("author",mBooks.get(position).getBdesc());
                bundle.putString("image",mBooks.get(position).getImageURL());
                bundle.putString("price",mBooks.get(position).getPrice());
                bundle.putString("price",mBooks.get(position).getCategory());
                bundle.putInt("type",mBooks.get(position).getType());

                intent.putExtras(bundle);
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
    { public TextView textviewname,price;
       private ItemClickListener itemClickListener;
        public ImageView imageview;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textviewname=itemView.findViewById(R.id.text1);
            imageview=itemView.findViewById(R.id.image_view1);
            price=itemView.findViewById(R.id.text2);
            itemView.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }
          public void setItemClickListener(ItemClickListener itemClickListener)
          {
              this.itemClickListener=itemClickListener;
          }
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),true);
            return true;
        }


    }
}