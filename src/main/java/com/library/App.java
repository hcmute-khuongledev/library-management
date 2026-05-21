package com.library;

import java.time.LocalDate;
import java.util.Scanner;
import com.library.model.Book;
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
            System.out.println("\n****** HỆ THỐNG QUẢN LÝ THƯ VIỆN ******");
            System.out.println("1. Xem danh sách sách & độc giả");
            System.out.println("2. Mượn sách");
            System.out.println("3. Trả sách & tính tiền phạt");
            System.out.println("4. Tìm kiếm sách theo tên hoặc tác giả");
            System.out.println("5. Liệt kê các phiếu mượn quá hạn");
            System.out.println("6. Xem báo cáo thống kê");
            System.out.println("0. Exit");
            System.out.print("Chọn chức năng: ");
            
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("--- DANH SÁCH SÁCH HIỆN CÓ ---");
                    for (Book b : library.getBooks()) System.out.println(b);
                    System.out.println("--- DANH SÁCH ĐỘC GIẢ ĐĂNG KÝ ---");
                    for (Reader r : library.getReaders()) System.out.println(r);
                    break;

                case 2:
                    System.out.print("Nhập email edu: "); String readerCode = scanner.nextLine();
                    System.out.print("Nhập mã sách: "); String bookCode = scanner.nextLine();
                    System.out.print("Số ngày mượn: "); int loanDays = scanner.nextInt();
                    LocalDate borrowDate = LocalDate.now();
                    LocalDate dueDate = borrowDate.plusDays(loanDays);

                    library.borrowBook(readerCode, bookCode, borrowDate, dueDate);
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    break;

                case 6:
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
