package com.library.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.library.model.Book;
import com.library.model.BorrowSlip;
import com.library.model.Lecturer;
import com.library.model.Reader;
import com.library.model.Student;
import com.library.model.Senior;

public class LibraryService {
    private List<Book> books = new ArrayList<>();
    private List<Reader> readers = new ArrayList<>();
    private List<BorrowSlip> borrowSlips = new ArrayList<>();

    public void initData() {
        books.add(new Book("Lap trinh Java Core", "Nguyen Van A", 2023, 8));
        books.add(new Book("Huong doi tuong voi Java", "Tran Thi B", 2022, 9));
        books.add(new Book("Cau truc du lieu & giai thuat", "Nguyen Van A", 2024, 15));

        readers.add(new Student("Le Khuong", "26tx810014@student.hcmute.edu.vn"));
        readers.add(new Student("Nguyen Van N", "26tx810015@student.hcmute.edu.vn"));
        readers.add(new Lecturer("Thay Phuc", "phuc@hcmute.edu.vn"));
        readers.add(new Senior("Ong Nguyen Van A", "nguyen@hcmute.edu.vn", "SR-001"));
    }

    public void addBook(Book book) { books.add(book); }
    public void addReader(Reader reader) { readers.add(reader); }

    public List<Book> getBooks() { return books; }
    public List<Reader> getReaders() { return readers; }

    public boolean borrowBook(String readerEmail, List<String> bookCodes, LocalDate borrowDate, LocalDate dueDate) {
        Reader reader = findReaderByEmail(readerEmail);
        
        if (reader == null) {
            System.out.println("Reader " + readerEmail + " not found!");
            return false;
        } 

        int activeBorrowedCount = 0;
        for (BorrowSlip slip : borrowSlips) {
            if (slip.getReader().getEmail().equalsIgnoreCase(readerEmail) && !slip.isReturned()) {
                activeBorrowedCount += slip.getBooks().size();
            }
        }

        int newRequestCount = bookCodes.size();
        if (activeBorrowedCount + newRequestCount > reader.getMaxBorrow()) {
            System.out.println("Reader has reached the maximum borrow limit!");
            System.out.println("You have: " + activeBorrowedCount + " books borrowed. Requesting: " + newRequestCount + " books.");
            System.out.println("Maximum borrow limit for this reader: " + reader.getMaxBorrow() + " books.");
            return false;
        }
        
        List<Book> selectedBooks = new ArrayList<>();
        for (String code : bookCodes) {
            Book book = findBookByCode(code);
            if (book == null) {
                System.out.println("Error: Book with code " + code + " not found!");
                return false;
            }
            if (!book.isAvailable()) {
                System.out.println("Error: Book " + book.getTitle() + " is currently unavailable!");
                return false;
            }
            selectedBooks.add(book);
        }
        BorrowSlip slip = new BorrowSlip(reader, selectedBooks, borrowDate, dueDate);
        borrowSlips.add(slip);
        System.out.println("\n========================================");
        System.out.println("CREATED NEW BORROW SLIP!");
        System.out.println("Your slip code: " + slip.getSlipCode());
        System.out.println("Total books borrowed today: " + newRequestCount + " books");
        System.out.println("========================================\n");
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
        for (Book b : borrowSlip.getBooks()) {
            b.increaseQuantity();
        }

        if (returnDate.isAfter(borrowSlip.getDueDate())) {
            long lateDays = ChronoUnit.DAYS.between(borrowSlip.getDueDate(), returnDate);
            // long fine = borrowSlip.getReader().calculateFine((int) lateDays);

            System.out.println("Late: " + lateDays + " days");
            // System.out.println("Fine: " + fine + " VND");
        }

        System.out.println("Book returned successfully.");
        return true;
    }

    public void showLateFees(int daysLate) { 
        System.out.println("=== PHI PHAT TRE HAN (" + daysLate + " ngay) ==="); 
        for (Reader r : readers) { 
            System.out.printf("%-25s | Fee: %,.0f VND%n", 
                r.getFullName(), r.calculateLateFee(daysLate)); 
        } 
    } 

    public void printAllReaders() {
        System.out.println("=== DANH SACH DOC GIA ("+ readers.size() + " nguoi ) ===");
        for (Reader r : readers) {
            System.out.println(r.getInfo());
        }
    }

    public void showAllBooks() { 
        System.out.println("=== DANH SACH SACH HIEN CO ==="); 
        for (Book b : books) { 
            System.out.println(b);
        } 
    }

    public double calculateTotalLateFees(int daysLate) {
        double totalFees = 0.0;
        for (Reader r : readers) {
            totalFees += r.calculateLateFee(daysLate);
        }
        return totalFees;
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
            for (Book book : borrowSlip.getBooks()) {
                String bookCode = book.getBookCode();
                bookCounts.put(bookCode, bookCounts.getOrDefault(bookCode, 0) + 1);
            }
            String readerCode = borrowSlip.getReader().getReaderCode();
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

    public List<Reader> searchReader(String keyword) {
        List<Reader> result = new ArrayList<>();
        for (Reader reader : readers) {
            if (reader.getFullName().toLowerCase().contains(keyword.toLowerCase()) || reader.getEmail().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(reader);
            }
        }
        return result;
    }

    public Reader findReaderByCode(String readerCode) {
        for (Reader reader : readers) {
            if (reader.getReaderCode().equals(readerCode)) {
                return reader;
            }
        }
        return null;
    }

    public List<Senior> printSeniorReaders() {
        List<Senior> seniors = new ArrayList<>();
        for (Reader reader : readers) {
            if (reader instanceof Senior) {
                seniors.add((Senior) reader);
            }
        }
        return seniors;
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
