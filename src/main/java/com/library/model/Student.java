package com.library.model;

import com.library.constant.ReaderType;

public class Student extends Reader {
    public Student(String name, String email) {
        super(name, email);
    }

    @Override
    public int getMaxBorrow() {
        return ReaderType.STUDENT.getMaxBooks();
    }

    @Override
    public double calculateLateFee(int daysLate) {
        return 0;
    }

    @Override
    public String getDisplayType() {
        return ReaderType.STUDENT.name();
    }

    @Override 
    public String getInfo() { 
        return "[SV] " + getReaderCode() + " | " + getFullName() + " | Email: " + getEmail() + " | Han muon: " + getMaxBorrow() + " cuon"; 
    } 
}