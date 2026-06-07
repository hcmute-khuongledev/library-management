package com.library.model;

import com.library.constant.ReaderType;

public class SeniorReader extends Reader {
    private String seniorCardNumber;

    public SeniorReader(String name, String email, String seniorCardNumber) {
        super(name, email);
        this.seniorCardNumber = seniorCardNumber;
    }

    @Override
    public int getMaxBorrowLimit() {
        return ReaderType.SENIOR.getMaxBooks();
    }

    @Override
    public boolean checkSpecialCondition(Book book) { return true; }

    @Override
    public String getSpecialConditionMessage() { return ""; }

    @Override
    public double calculateLateFee(int daysLate) {
        return 0.0;
    }

    @Override
    public void onBorrowSuccess(Book book) {
        super.onBorrowSuccess(book);
        System.out.println("  -> Da ghi nhan: Nguoi cao tuoi — khong thu phi phat");
    }

    @Override
    public String getDisplayType() {
        return ReaderType.SENIOR.name();
    }

    @Override
    public String getInfo() {
        return super.getInfo() + String.format(" | So the NCT: %s | [NCT - Mien Phat]", this.seniorCardNumber);
    }

    public String getSeniorCardNumber() {
        return this.seniorCardNumber;
    }
}
