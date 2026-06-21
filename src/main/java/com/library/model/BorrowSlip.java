package com.library.model;

import java.time.LocalDate;
import java.util.List;
import com.library.interfaces.Returnable;

public class BorrowSlip implements Returnable {
    private static long COUNTER_ID = 1;
    private long id;
    private String slipCode;
    private Reader reader;
    private List<Book> books;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private String returnDate;
    private boolean isReturned;

    public BorrowSlip(Reader reader, List<Book> books, LocalDate borrowDate, LocalDate dueDate) {
        this.id = COUNTER_ID++;
        this.slipCode = generateSlipCode();
        this.reader = reader;
        this.books = books;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.isReturned = false;

        for (Book book : books) {
            book.decreaseQuantity();
        }
    }
    
    public String getSlipCode() { return this.slipCode; }
    public Reader getReader() { return this.reader; }
    public List<Book> getBooks() { return this.books; }
    public LocalDate getBorrowDate() { return this.borrowDate; }
    public LocalDate getDueDate() { return this.dueDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }
    
    private String generateSlipCode() {
        return String.format("HCMUTE-SLIP-%05d", id);
    }

    @Override
    public void confirmReturn(String date) {
        this.returnDate = date;
        System.out.println("Slip " + slipCode + " confirmed return on " + date);
    }

    @Override
    public String getReturnDate() {
        return returnDate;
    }

    @Override
    public boolean isReturned() {
        return returnDate != null;
    }

    public void markAsReturned() {
        this.isReturned = true;
    }

    @Override
    public String toString() {
        StringBuilder bookTitles = new StringBuilder();
        for (Book b : books) {
            bookTitles.append("【").append(b.getTitle()).append("】 ");
        }

        return String.format("Slip Code: %s | Reader: %s | Books: %s | Borrow Date: %s | Due Date: %s | Status: %s",
                slipCode, reader.getFullName(), bookTitles.toString(), borrowDate, dueDate, isReturned ? "Returned" : "Not Returned");
    }
}