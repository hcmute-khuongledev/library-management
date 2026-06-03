package com.library.model;

import com.library.constant.LibraryConstant;
import java.time.LocalDate;
import java.util.List;

public class BorrowSlip {
    private static long COUNTER_ID = 1;
    private long id;
    private String slipCode;
    private Reader reader;
    private List<Book> books;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
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
        StringBuilder bookTitles = new StringBuilder();
        for (Book b : books) {
            bookTitles.append("【").append(b.getTitle()).append("】 ");
        }

        return String.format("Slip Code: %s | Reader: %s | Books: %s | Borrow Date: %s | Due Date: %s | Status: %s",
                slipCode, reader.getName(), bookTitles.toString(), borrowDate, dueDate, isReturned ? "Returned" : "Not Returned");
    }
}
