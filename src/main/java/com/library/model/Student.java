package com.library.model;

import com.library.constant.ReaderType;

public class Student extends Reader {
    public Student(String name, String email) {
        super(name, email, ReaderType.STUDENT);
    }

    
    public int getMaxBorrow() {
        return getReaderType().getMaxBooks();
    }

    public double calculateLateFee(int daysLate) {
        if (daysLate <= 0) {
            return 0;
        }
        return daysLate * 0.25;
    }

    // @Override
    // public String toString() {
    //     return super.toString() + " | Loai the: Sinh vien - So sach duoc muon: " + getMaxBorrow() + " cuon";
    // }

    @Override 
    public String getInfo() { 
        return "[SV] " + getReaderCode() + " | " + getFullName() + " | Email: " + getEmail() + " | Han muon: " + getMaxBorrow() + " cuon"; 
    } 
}