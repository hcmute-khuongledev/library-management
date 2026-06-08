package com.library.model;

import java.time.LocalDate;
import java.util.List;

import com.library.service.Library;

public class Librarian {
    private static long COUNTER_ID = 1;
    private long id;
    private String fullName;
    private String phone;
    private Library library;

    public Librarian(String fullName, String phone, Library library) {
        this.id = COUNTER_ID++;
        this.fullName = fullName;
        this.phone = phone;
        this.library = library;
    }

    public void processBorrow(Reader reader, List<Book> books, LocalDate borrowDate, LocalDate dueDate) {
        System.out.println("[Thu thu " + fullName + "]:");
        for (Book book : books) {
            BorrowResult result = reader.processBorrow(book);
            System.out.println("  Ket qua: " + result.getMessage());
            if (result.isSuccess()) {
                BorrowSlip slip = new BorrowSlip(reader, books, borrowDate, dueDate);
                library.addBorrowSlip(slip);
            }
        }
    }
}
