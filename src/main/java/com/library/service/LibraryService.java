package com.library.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.library.constant.ReaderType;
import com.library.model.Book;
import com.library.model.BorrowSlip;
import com.library.model.Reader;

public class LibraryService {
    private List<Book> books = new ArrayList<>();
    private List<Reader> readers = new ArrayList<>();
    private List<BorrowSlip> borrowSlips = new ArrayList<>();

    public void initData() {
        books.add(new Book("Lap trinh Java Core", "Nguyen Van A", 2023, 8));
        books.add(new Book("Huong doi tuong voi Java", "Tran Thi B", 2022, 9));
        books.add(new Book("Cau truc du lieu & giai thuat", "Nguyen Van A", 2024, 15));
        
        readers.add(new Reader("Le Khuong", "26tx810014@student.hcmute.edu.vn", ReaderType.STUDENT));
        readers.add(new Reader("Thay Phuc", "phuc@hcmute.edu.vn", ReaderType.LECTURER));
    }

    public void addBook(Book book) { books.add(book); }
    public void addReader(Reader reader) { readers.add(reader); }

    public List<Book> getBooks() { return books; }
    public List<Reader> getReaders() { return readers; }

    public boolean borrowBook(String readerEmail, String bookCode, LocalDate borrowDate, LocalDate dueDate) {
        Reader reader = findReaderByEmail(readerEmail);
        Book book = findBookByCode(bookCode);

        if (reader == null) {
            System.out.println("Reader not found!");
            return false;
        } 
        if (book == null) {
            System.out.println("Book not found!");
            return false;
        }
        if (!book.isAvailable()) {
            System.out.println("Book is currently unavailable!");
            return false;
        }

        int activeBorrowCount = (int) borrowSlips.stream()
            .filter(slip -> slip.getReader().equals(reader) && !slip.isReturned())
            .count();

        if (activeBorrowCount >= reader.getMaxBorrowLimit()) {
            System.out.println("Reader has reached the maximum borrow limit");
            return false;
        }
        BorrowSlip newSlip = new BorrowSlip(reader, book, borrowDate, dueDate);
        borrowSlips.add(newSlip);
        System.out.println("Book borrowed successfully!");
        System.out.println(newSlip);
        return true;
    }

    public boolean returnBook(String slipCode) {
        BorrowSlip borrowSlip = findSlipByCode(slipCode);
        LocalDate returnDate = LocalDate.now();

        if (borrowSlip == null) {
            System.out.println("Borrow slip not found!");
            return false;
        }

        if (borrowSlip.isReturned()) {
            System.out.println("This book has already been returned!");
            return false;
        }

        borrowSlip.setReturnDate(returnDate);
        borrowSlip.markAsReturned();
        borrowSlip.getBook().increaseQuantity();

         if (returnDate.isAfter(borrowSlip.getDueDate())) {
            long lateDays = ChronoUnit.DAYS.between(borrowSlip.getDueDate(), returnDate);
            long fine = BorrowSlip.calculate(lateDays);

            System.out.println("Late: " + lateDays + " days");
            System.out.println("Fine: " + fine + " VND");
        }

        System.out.println("Book returned successfully.");
        return true;
    }

    public List<Book> searchBook(String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) || book.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public List<BorrowSlip> getOverdueSlips() {
        List<BorrowSlip> overdueSlips = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (BorrowSlip borrowSlip : borrowSlips) {
            if (!borrowSlip.isReturned() && borrowSlip.getDueDate().isBefore(today)) {
                overdueSlips.add(borrowSlip);
            }
        }
        return overdueSlips;
    }

    public void getStatistics() {
        if (borrowSlips.isEmpty()) {
            System.out.println("No borrow slips found!");
            return;
        }

        Map<String, Integer> bookCounts = new HashMap<>();
        Map<String, Integer> readerCounts = new HashMap<>();

        for (BorrowSlip borrowSlip : borrowSlips) {
            String bookCode = borrowSlip.getBook().getBookCode();
            String readerCode = borrowSlip.getReader().getReaderCode();

            bookCounts.put(bookCode, bookCounts.getOrDefault(bookCode, 0) + 1);
            readerCounts.put(readerCode, readerCounts.getOrDefault(readerCode, 0) + 1);
        }

        String topBook = "";
        int maxBookCount = 0;
        for (Map.Entry<String, Integer> entry : bookCounts.entrySet()) {
            if (entry.getValue() > maxBookCount) {
                maxBookCount = entry.getValue();
                topBook = entry.getKey();
            }
        }

        String topReader = "";
        int maxReaderCount = 0;
        for (Map.Entry<String, Integer> entry : readerCounts.entrySet()) {
            if (entry.getValue() > maxReaderCount) {
                maxReaderCount = entry.getValue();
                topReader = entry.getKey();
            }
        }
        System.out.println("Top borrowed book: " + topBook + " (" + maxBookCount + " times)");
        System.out.println("Top reader: " + topReader + " (" + maxReaderCount + " times)");
    }

    public BorrowSlip findSlipByCode(String slipCode) {
        for (BorrowSlip borrowSlip : borrowSlips) {
            if (borrowSlip.getSlipCode().equals(slipCode)) {
                return borrowSlip;
            }
        }
        return null;
    }

    public Reader findReaderByCode(String readerCode) {
        for (Reader reader : readers) {
            if (reader.getReaderCode().equals(readerCode)) {
                return reader;
            }
        }
        return null;
    }

    public Reader findReaderByEmail(String email) {
        for (Reader reader : readers) {
            if (reader.getEmail().equalsIgnoreCase(email)) {
                return reader;
            }
        }
        return null;
    }

    public Book findBookByCode(String bookCode) {
        for (Book book : books) {
            if (book.getBookCode().equals(bookCode)) {
                return book;
            }
        }
        return null;
    }
}
