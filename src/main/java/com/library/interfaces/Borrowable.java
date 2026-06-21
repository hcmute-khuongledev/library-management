package com.library.interfaces;

public interface Borrowable {
    int MAX_BORROW_DAYS = 14;
    double FINE_PER_DAY = 5000.0;

    void borrowBy(String readerId, String date);
    void returnBook(String date);
    boolean isAvailable();
    String getBorrowerId();


    default double calculateFine(int daysOverdue) {
        if (daysOverdue <= 0) return 0.0;
        return daysOverdue * FINE_PER_DAY;
    }

    static boolean isValidBorrowDuration(int days) {
        return days > 0 && days <= MAX_BORROW_DAYS;
    }
}
