package com.library.model;

import com.library.constant.ReaderType;

public class StudentReader extends Reader {
    public StudentReader(String name, String email) {
        super(name, email);
    }

    @Override
    public int getMaxBorrowLimit() {
        return ReaderType.STUDENT.getMaxBooks();
    }

    @Override
    protected boolean checkSpecialCondition(Book book) {
        return !book.isReferenceOnly();
    }


    @Override
    public String getSpecialConditionMessage() { 
        return "Sach tham khao chi doc tai cho — sinh vien khong duoc mang ve";
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
        return "[SV] " + getReaderCode() + " | " + getFullName() + " | Email: " + getEmail() + " | Han muon: " + getMaxBorrowLimit() + " cuon"; 
    } 
}