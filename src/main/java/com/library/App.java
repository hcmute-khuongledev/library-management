package com.library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.library.model.Book;
import com.library.model.BorrowSlip;
import com.library.model.Lecturer;
import com.library.service.LibraryService;
import com.library.model.Reader;
import com.library.model.Student;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        LibraryService library = new LibraryService();
        library.initData();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n****** HE THONG QUAN LY THU VIEN ******");
            System.out.println("1. Xem danh sach doc gia va sach");
            System.out.println("2. Muon sach");
            System.out.println("3. Tra sach & tinh tien phat");
            System.out.println("4. TTim kiem sach theo ten hoac tac gia");
            System.out.println("5. Liet ke cac phieu muon qua han");
            System.out.println("6. Xem bao cao thong ke");
            System.out.println("7. Them sach");
            System.out.println("8. Them doc gia");
            System.out.println("0. Exit");
            System.out.print("Chon chuc nang: ");
            
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    library.showAllBooks();
                    library.showAllReaders();
                    break;

                case 2:
                    System.out.print("Nhap email edu: "); String readerEmail = scanner.nextLine();
                    List<String> listBookCodes = new ArrayList<>();
                    while (true) {
                        System.out.print("Nhap ma sach: (Hoac nhan ENTER de ket thuc chon): ");
                        String code = scanner.nextLine().trim();
                        
                        if (code.isEmpty()) {
                            break;
                        }
                        listBookCodes.add(code);
                    }

                    if (listBookCodes.isEmpty()) {
                        System.out.println("Ban chua chon sach nao!");
                        break;
                    }
                    System.out.print("So ngay muon: "); int loanDays = scanner.nextInt();
                    scanner.nextLine(); 
                    if (loanDays <= 0) {
                        System.out.println("So ngay muon phai lon hon 0!");
                        break;
                    }
                    LocalDate borrowDate = LocalDate.now();
                    LocalDate dueDate = borrowDate.plusDays(loanDays);

                    library.borrowBook(readerEmail, listBookCodes, borrowDate, dueDate);
                    break;

                case 3:
                    System.out.print("Nhap ma phieu muon: "); String slipCode = scanner.nextLine();
                    library.returnBook(slipCode);
                    break;

                case 4:
                    System.out.print("Nhap ten sach hoac tac gia: "); String keyword = scanner.nextLine();
                    System.out.println("\n--- KET QUA TIM KIEM ---");
                    List<Book> searchResults = library.searchBook(keyword);
                    if (searchResults.isEmpty()) {
                        System.out.println("Khong tim thay sach nao!");
                    } else {
                        for (Book b : searchResults) {
                            System.out.println(b);
                        }
                    }

                    break;

                case 5:
                    System.out.println("\n--- PHIEU MUON QUA HAN ---");
                    List<BorrowSlip> overdueSlips = library.getOverdueSlips();
                    if (overdueSlips.isEmpty()) {
                        System.out.println("Khong co phieu muon nao qua han!");
                    } else {
                        for (BorrowSlip slip : overdueSlips) {
                            System.out.println(slip);
                        }
                    }
                    break;

                case 6:
                    System.out.println("\n--- BAO CAO THONG KE ---");
                    System.out.println("Tong so sach: " + library.getBooks().size());
                    System.out.println("Tong so doc gia: " + library.getReaders().size());
                    library.getStatistics();
                    break;

                case 7:
                    System.out.print("Nhap ten sach: "); String title = scanner.nextLine();
                    System.out.print("Nhap ten tac gia: "); String author = scanner.nextLine();
                    System.out.print("Nhap nam xuat ban: "); int publishYear = scanner.nextInt();
                    System.out.print("Nhap so luong: "); int quantity = scanner.nextInt();
                    scanner.nextLine();

                    Book newBook = new Book(title, author, publishYear, quantity);
                    library.addBook(newBook);
                    System.out.println("Sach moi da duoc them vao thu vien!");
                    break;

                case 8:
                    System.out.print("Nhap ten doc gia: "); String newReaderName = scanner.nextLine();
                    System.out.print("Nhap email: "); String newReaderEmail = scanner.nextLine();
                    System.out.print("Nhap loai doc gia (1 - Sinh vien, 2 - Giang vien): "); int readerTypeChoice = scanner.nextInt();
                    scanner.nextLine();

                    Reader newReader;
                    if (readerTypeChoice == 1) {
                        newReader = new Student(newReaderName, newReaderEmail);
                    } else if (readerTypeChoice == 2) {
                        newReader = new Lecturer(newReaderName, newReaderEmail);
                    } else {
                        System.out.println("Lua chon khong hop le!");
                        break;
                    }
                    library.addReader(newReader);
                    System.out.println("Doc gia moi da duoc them vao thu vien!");
                    break;

                default:
                    System.out.println("Is not a valid choice. Please choose again.");
            }
        } while (choice != 0);
        scanner.close();
    }
}
