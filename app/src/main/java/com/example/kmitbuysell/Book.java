package com.example.kmitbuysell;

public class Book {
    String imageURL;
    String bdesc;
    String bookName;
    String price;
    String category;
    int type;
    Book()
    {

    }
    Book(String imageURL,String bookName,String price,String category,String bdesc,int type)
    {
        this.bookName=bookName;
        this.imageURL=imageURL;
        this.price=price;
        this.category=category;
        this.bdesc=bdesc;
        this.type=type;
    }

    public String getBdesc() {
        return bdesc;
    }

    public void setBdesc(String bdesc) {
        this.bdesc = bdesc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;}
}
