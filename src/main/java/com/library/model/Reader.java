package com.library.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.library.interfaces.Notifiable;

abstract public class Reader implements Notifiable {
    private static long COUNTER_ID = 1;
    private long id;
    private String readerCode;
    private String name;
    private String email;
    protected int currentBorrowCount;
    private final List<String> notifications = new ArrayList<>();

    public Reader(String name, String email) {
        this.id = COUNTER_ID++;
        this.readerCode = generateReaderCode();
        this.name = name;
        this.email = email;
    }

    private String generateReaderCode() {
        return String.format("HCMUTE-READER-%05d", id);
    }

    @Override
    public void sendNotification(String message) {
        notifications.add(message);
        System.out.println("[" + name + "] " + message);
    }

    @Override
    public List<String> getNotificationHistory() {
        return Collections.unmodifiableList(notifications);
    }

    public abstract double calculateLateFee(int daysLate);
    public abstract int getMaxBorrowLimit();
    public abstract String getDisplayType();
    
    private boolean checkBorrowQuota() {
        return currentBorrowCount < getMaxBorrowLimit();
    }
    protected abstract boolean checkSpecialCondition(Book book);
    protected abstract String  getSpecialConditionMessage();
    protected void onBorrowSuccess(Book book) {
        System.out.println(getFullName() + " muon: " + book.getTitle());
    }

    public String getReaderCode() { return this.readerCode; }
    public String getFullName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email; }

    // Template Method: quy trình cho mượn chuẩn (4 bước cố định) 
    public final BorrowResult processBorrow(Book book) {
        // Bước 1: kiểm tra độc giả còn quyền mượn không (cố định)
        if (!checkBorrowQuota()) {
            return new BorrowResult(false, "Da dat gioi han muon: " + getMaxBorrowLimit() + " cuon");
        }
        // Bước 2: kiểm tra điều kiện đặc thù của từng loại độc giả (abstract)
        if (!checkSpecialCondition(book)) {
            return new BorrowResult(false, getSpecialConditionMessage());
        }
        // Bước 3: trừ tồn kho sách (cố định)
        book.decreaseQuantity();
        currentBorrowCount++;
        // Bước 4: ghi nhận và thông báo (có thể override — Hook method)
        onBorrowSuccess(book);
        return new BorrowResult(true, "Muon thanh cong: " + book.getTitle());
    }


    // @Override
    // public String toString() {
    //     return String.format("Reader Code: %s | Name: %s | Email: %s | Type: %s", readerCode, name, email, readerType == ReaderType.STUDENT ? "Student" : "Teacher");
    // }

    public String getInfo() {
        return String.format("Ten: %s | Email: %s | Type: %s", name, email, getDisplayType());
    };
}