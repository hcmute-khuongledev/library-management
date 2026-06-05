package com.library.model;

import com.library.constant.ReaderType;

class SeniorReader extends Reader {
    private String seniorCardNumber;

    // public SeniorReader(String name, String seniorCardNumber) {
    //     // super(name);
    // }

    @Override
    public int getMaxBorrow() {
        return 10;
    }

    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 0.5; // 0.5 currency units per day late
    }

    @Override
    public String getInfo() {
        return String.format("Reader Code: %s | Name: %s | Email: %s | Type: Senior", getReaderCode(), getFullName(), getEmail());
    }
    
}
