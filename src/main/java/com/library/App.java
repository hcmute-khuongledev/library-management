package com.library;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import com.library.model.Book;
import com.library.model.BorrowSlip;
import com.library.service.LibraryService;
import com.library.model.Reader;

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
            System.out.println("0. Exit");
            System.out.print("Chon chuc nang: ");
            
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("--- DANH SACH SACH HIEN CO ---");
                    for (Book b : library.getBooks()) System.out.println(b);
                    System.out.println("--- DANH SACH DOC GIA ---");
                    for (Reader r : library.getReaders()) System.out.println(r);
                    break;

                case 2:
                    System.out.print("Nhap email edu: "); String readerEmail = scanner.nextLine();
                    System.out.print("Nhap ma sach: "); String bookCode = scanner.nextLine();
                    System.out.print("So ngay muon: "); int loanDays = scanner.nextInt();
                    LocalDate borrowDate = LocalDate.now();
                    LocalDate dueDate = borrowDate.plusDays(loanDays);

                    library.borrowBook(readerEmail, bookCode, borrowDate, dueDate);
                    break;

                case 3:
                    System.out.println("Nhap ma phieu muon: "); String slipCode = scanner.nextLine();
                    library.returnBook(slipCode);
                    break;

                case 4:
                    System.out.print("Nhap ten sach hoac tac gia: "); String keyword = scanner.nextLine();
                    System.out.println("--- KET QUA TIM KIEM ---");
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
                    System.out.println("--- PHIEU MUON QUA HAN ---");
                    for (BorrowSlip slip : library.getOverdueSlips()) {
                        System.out.println(slip);
                    }
                    break;

                case 6:
                    System.out.println("--- BAO CAO THONG KE ---");
                    System.out.println("Tong so sach: " + library.getBooks().size());
                    System.out.println("Tong so doc gia: " + library.getReaders().size());
                    library.getStatistics();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Is not a valid choice. Please choose again.");
            }
        } while (choice != 0);
        scanner.close();
    }
}
