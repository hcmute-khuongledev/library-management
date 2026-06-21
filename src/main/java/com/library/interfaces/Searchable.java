package com.library.interfaces;

import java.util.List;
import com.library.model.Book;

public interface Searchable {
    List<Book> searchByTitle(String keyword);
    List<Book> searchByAuthor(String keyword);

    static String normalizeKeyword(String keyword) {
        if (keyword == null) return "";
        return keyword.trim().toLowerCase();
    }
}
