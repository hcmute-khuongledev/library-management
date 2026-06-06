package com.library.model;

abstract public class Reader {
    private static long COUNTER_ID = 1;
    private long id;
    private String readerCode;
    private String name;
    private String email;

    public Reader(String name, String email) {
        this.id = COUNTER_ID++;
        this.readerCode = generateReaderCode();
        this.name = name;
        this.email = email;
    }

    private String generateReaderCode() {
        return String.format("HCMUTE-READER-%05d", id);
    }

    public abstract int getMaxBorrow();
    public abstract double calculateLateFee(int daysLate);
    public abstract String getDisplayType();

    public String getReaderCode() { return this.readerCode; }
    public String getFullName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email; }


    // @Override
    // public String toString() {
    //     return String.format("Reader Code: %s | Name: %s | Email: %s | Type: %s", readerCode, name, email, readerType == ReaderType.STUDENT ? "Student" : "Teacher");
    // }

    public abstract String getInfo();
}
