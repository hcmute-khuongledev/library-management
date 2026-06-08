package com.library.model;

import java.time.LocalDate;
import java.util.List;

import com.library.service.Library;

public class Librarian {
    private static long COUNTER_ID = 1;
    private String employeeId;
    private String fullName;
    private String phone;
    private String shift;
    private Library library;

    public Librarian(String fullName, String phone, String shift, Library library) {
        this.employeeId = "LT" + COUNTER_ID++;
        this.fullName = fullName;
        this.phone = phone;
        this.shift = shift;
        this.library = library;
    }

    public void processLoan(Reader reader, List<Book> books) {
        System.out.println("[Thu thu " + fullName + "] Xu ly cho muon:");
        for (Book book : books) {
            BorrowResult result = reader.processBorrow(book);
            System.out.println("  Ket qua: " + result.getMessage());
            if (result.isSuccess()) {
                BorrowSlip slip = new BorrowSlip(reader, books, getCurrentDate(), getCurrentDate().plusDays(5));
                library.addBorrowSlip(slip);
            }
        }
    }

    public void processReturn(BorrowSlip slip) {
        int daysLate = slip.calculateDaysLate(getCurrentDate());
        if (daysLate > 0) {
            double fee = slip.getReader().calculateLateFee(daysLate);
            System.out.printf("Phat qua han %d ngay: %.0f VND%n", daysLate, fee);
        }
        for (Book book : slip.getBooks()) {
            book.increaseQuantity();
        }
        library.removeBorrowSlip(slip);
    }

    private LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}
