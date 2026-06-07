package com.library.model;

import com.library.constant.ReaderType;

public class Senior extends Reader {
    private String seniorCardNumber;

    public Senior(String name, String email, String seniorCardNumber) {
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
        return super.getInfo() + String.format(" | Senior Card Number: %s | [NCT - Mien Phat]", seniorCardNumber);
    }
}
