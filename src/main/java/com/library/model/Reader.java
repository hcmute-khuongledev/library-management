package com.library.model;

import com.library.constant.ReaderType;

public class Reader {
    private static long COUNTER_ID = 1;
    private long id;
    private String readerCode;
    private String name;
    private String email;
    private ReaderType readerType;

    public Reader(String name, String email, ReaderType readerType) {
        this.id = COUNTER_ID++;
        this.readerCode = generateReaderCode();
        this.name = name;
        this.email = email;
        this.readerType = readerType;
    }

    private String generateReaderCode() {
        return String.format("HCMUTE-READER-%05d", id);
    }

    public int getMaxBorrow() {
        return 0; 
    }

    public String getReaderCode() { return readerCode; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public ReaderType getReaderType() { return readerType; }
    public void setReaderType(ReaderType readerType) { this.readerType = readerType; }

    public int getMaxBorrowLimit() {
        return getReaderType().getMaxBooks();
    }   

    @Override
    public String toString() {
        return String.format("Reader Code: %s | Name: %s | Email: %s | Type: %s", readerCode, name, email, readerType == ReaderType.STUDENT ? "Student" : "Teacher");
    }

}
