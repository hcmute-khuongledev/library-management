package com.library.model;

import com.library.constant.ReaderType;

public class LecturerReader extends Reader {

    public LecturerReader(String name, String email) {
        super(name, email);
    }
    
    @Override
    public int getMaxBorrowLimit() {
        return ReaderType.LECTURER.getMaxBooks();
    }

    @Override
    public double calculateLateFee(int daysLate) {
        if (daysLate <= 0) {
            return 0;
        }
        return daysLate * 2000;
    }

    @Override
    public boolean checkSpecialCondition(Book book) { return true; }

    @Override
    public String getSpecialConditionMessage() { return ""; }

    @Override
    public String getDisplayType() {
        return ReaderType.LECTURER.name();
    }

    // @Override
    // public String toString() {
    //     return super.toString() + " | Loai the: Giang vien - So sach duoc muon: " + getMaxBorrow() + " cuon";
    // }

    @Override 
    public String getInfo() { 
        return "[GV] " + getReaderCode() + " | " + getFullName() + " | Email: " + getEmail() + " | Han muon: " + getMaxBorrowLimit() + " cuon"; 
    } 
}