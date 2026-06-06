package com.library.model;

import com.library.constant.ReaderType;

class SeniorReader extends Reader {
    private String seniorCardNumber;

    public SeniorReader(String name, String email, String seniorCardNumber) {
        super(name, email);
        this.seniorCardNumber = seniorCardNumber;
    }

    @Override
    public int getMaxBorrow() {
        return ReaderType.SENIOR.getMaxBooks();
    }

    @Override
    public double calculateLateFee(int daysLate) {
        return 0.0;
    }

    @Override
    public String getDisplayType() {
        return ReaderType.SENIOR.name();
    }

    @Override
    public String getInfo() {
        return "[SR] " + getReaderCode() + " | " + getFullName() + " | Email: " + getEmail() + " | Han muon: " + getMaxBorrow() + " cuon | So the cao cap: " + seniorCardNumber;
    }
    
}
