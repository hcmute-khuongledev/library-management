package com.library.model;

import com.library.constant.ReaderType;

public class Lecturer extends Reader {

    public Lecturer(String name, String email) {
        super(name, email, ReaderType.LECTURER);
    }

    @Override
    public int getMaxBorrow() {
        return getReaderType().getMaxBooks();
    }

    @Override
    public String toString() {
        return super.toString() + " | Loai the: Giang vien - So sach duoc muon: " + getMaxBorrow() + " cuon";
    }
}