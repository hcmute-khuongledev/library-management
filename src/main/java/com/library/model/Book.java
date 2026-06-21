package com.library.model;
import com.library.interfaces.Borrowable;

public class Book implements Borrowable {
    private static long COUNTER_ID = 1;
    private long id;
    private String bookCode;
    private String title;
    private String author;
    private int publishYear;
    private int quantity;
    private boolean isAvailable;
    private boolean referenceOnly;
    private String currentBorrowerId;
    private String borrowDate;

    
    public Book(String title, String author, int year, int quantity, boolean referenceOnly) {
        this.id = COUNTER_ID++;
        this.bookCode = generateBookCode();
        this.title = title;
        this.author = author;
        this.publishYear = year;
        this.quantity = quantity;
        this.isAvailable = quantity > 0;
        this.referenceOnly = referenceOnly;
    }

    private String generateBookCode() {
        return String.format("HCMUTE-BOOK-%05d", id);
    }

    @Override
    public void borrowBy(String readerId, String date) {
        if (!isAvailable()) {
            System.out.println("Book '" + title + "' is not available.");
            return;
        }
        this.currentBorrowerId = readerId;
        this.borrowDate = date;
        System.out.println("Book '" + title + "' borrowed by " + readerId);
    }

    @Override
    public void returnBook(String date) {
        System.out.println("Book '" + title + "' returned on " + date);
        this.currentBorrowerId = null;
        this.borrowDate = null;
    }

    @Override
    public String getBorrowerId() { return currentBorrowerId; }


    public String getBookCode() { return bookCode; }

    public String getTitle() { return title;}
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getPublishYear() { return publishYear;}
    public void setPublishYear(int publishYear) { this.publishYear = publishYear; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { 
        this.quantity = quantity; 
        this.isAvailable = quantity > 0;
    }

    public boolean isAvailable() { return isAvailable; }

    public boolean isReferenceOnly() { return referenceOnly; }

    @Override
    public String toString() {
        return String.format("Ma sach: %s | Ten sach: %s | Tac gia: %s | Co san: %d | Sach hiem tham khao: %s", bookCode, title, author, quantity, referenceOnly ? "Co" : "Khong");
    }

    public void decreaseQuantity() {
        if (this.quantity > 0) {
            this.quantity--;
            if (this.quantity == 0) {
                this.isAvailable = false;
            }
        }
    }

    public void increaseQuantity() {
        this.quantity++;
        if (this.quantity > 0) {
            this.isAvailable = true;
        }
    }


}
