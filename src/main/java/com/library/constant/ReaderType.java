package com.library.constant;

public enum ReaderType {
    SINH_VIEN(3),
    GIANG_VIEN(5);

    private final int maxBooks;

    ReaderType(int maxBooks) {
        this.maxBooks = maxBooks;
    }

    public int getMaxBooks() {
        return maxBooks;
    }
}