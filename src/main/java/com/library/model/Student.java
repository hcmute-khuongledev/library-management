package com.library.model;

import com.library.constant.ReaderType;

public class Student extends Reader {

    public Student(String name, String email) {
        super(name, email, ReaderType.STUDENT);
    }

    @Override
    public int getMaxBorrow() {
        return getReaderType().getMaxBooks();
    }

    @Override
    public String toString() {
        return super.toString() + " | Loai the: Sinh vien - So sach duoc muon: " + getMaxBorrow() + " cuon";
    }
}