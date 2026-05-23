package com.library.constant;

public enum ReaderType {
    STUDENT(3),
    LECTURER(5);

    private final int maxBooks;

    ReaderType(int maxBooks) {
        this.maxBooks = maxBooks;
    }

    public int getMaxBooks() {
        return maxBooks;
    }
}