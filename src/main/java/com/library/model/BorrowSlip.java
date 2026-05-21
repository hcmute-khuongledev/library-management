package com.library.model;

import com.library.constant.LibraryConstant;
import java.time.LocalDate;

public class BorrowSlip {
    private static long COUNTER_ID = 1;
    private long id;
    private String slipCode;
    private Reader reader;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean isReturned;

    public BorrowSlip(Reader reader, Book book, LocalDate borrowDate, LocalDate dueDate) {
        this.id = COUNTER_ID++;
        this.slipCode = generateSlipCode();
        this.reader = reader;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.isReturned = false;

        book.decreaseQuantity();
    }
    
    public String getSlipCode() { return this.slipCode; }
    public Reader getReader() { return this.reader; }
    public Book getBook() { return this.book; }
    public LocalDate getBorrowDate() { return this.borrowDate; }
    public LocalDate getDueDate() { return this.dueDate; }
    public LocalDate getReturnDate() { return this.returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    public boolean isReturned() { return this.isReturned; }
    
    private String generateSlipCode() {
        return String.format("HCMUTE-SLIP-%05d", id);
    }

    public static long calculate(long lateDays) {
        if (lateDays <= 0) {
            return 0;
        }
        return lateDays * LibraryConstant.FINE_PER_DAY;
    }

    public void markAsReturned() {
        this.isReturned = true;
    }

    @Override
    public String toString() {
        return String.format("Slip Code: %s | Reader: %s | Book: %s | Borrow Date: %s | Due Date: %s | Status: %s",
                slipCode, reader.getName(), book.getTitle(), borrowDate, dueDate, isReturned ? "Returned" : "Not Returned");
    }
}
