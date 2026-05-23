### 26TX810014 - LE KHUONG

Bài tập 1: Phân tích yêu cầu và xác định lớp

# Class Book
## Thuộc tính
- COUNTER_ID (static long): Tăng ID tự động.
- id (long): ID hệ thống.
- bookCode (String): Mã sách hiển thị theo chuẩn thư viện.
- title (String): Tên sách.
- author (String): Tác giả.
- publishYear (int): Năm xuất bản.
- quantity (int): Số lượng hiện có trong kho.
- isAvailable (boolean): Trạng thái còn sách để mượn hay không.

## Phương thức
- generateBookCode() (private String): Tự động sinh mã sách theo định dạng HCMUTE-BOOK-%05d.
- decreaseQuantity() (void): Giảm số lượng kho đi 1 khi có độc giả mượn và tự động cập nhật trạng thái isAvailable về false nếu hết sách.
- increaseQuantity() (void): Tăng số lượng kho thêm 1 khi độc giả trả sách và cập nhật trạng thái isAvailable thành true.
- Các hàm getter/setter: Đảm bảo tính đóng gói dữ liệu.
- toString() (String): Hiển thị thông tin sách.


# Class Reader
## Thuộc tính
- COUNTER_ID (static long): Tăng ID tự động
- id (long): ID hệ thống.
- readerCode (String): Mã thẻ độc giả hiển thị theo chuẩn.
- name (String): Họ và tên độc giả.
- email (String): Địa chỉ email liên hệ.
- readerType (ReaderType): Loại thẻ (STUDENT hoặc LECTURER).

## Phương thức
- generateReaderCode() (private String): Tự động sinh mã độc giả theo định dạng HCMUTE-READER-%05d.
- getMaxBorrowLimit() (int): Lấy giới hạn sách được mượn dựa trên cấu hình của ReaderType.
- Các hàm getter/setter: Đóng gói dữ liệu thuộc tính.
- toString() (String): Hiển thị thông tin độc giả.


# Class BorrowSlip
## Thuộc tính
- COUNTER_ID (static long): Tăng ID tự động
- id (long): ID hệ thống.
- slipCode (String): Mã phiếu mượn.
- reader (Reader): Tham chiếu đến đối tượng Reader.
- book (Book): Tham chiếu đến đối tượng Book.
- borrowDate (LocalDate): Ngày mượn sách thực tế.
- dueDate (LocalDate): Ngày hẹn trả sách theo quy định.
- returnDate (LocalDate): Ngày độc giả trả sách thực tế.
- isReturned (boolean): Trạng thái phiếu (Đã trả / Chưa trả).

## Phương thức
- generateSlipCode() (private String): Tự động sinh mã phiếu theo định dạng HCMUTE-SLIP-%05d.
- calculate(long lateDays) (static long): Tính tiền phạt dựa trên số ngày trễ hạn.
- markAsReturned() (void): Cập nhật trạng thái phiếu mượn thành đã hoàn thành trả sách (isReturned = true).
- Các hàm getter/setter: Lấy và cập nhật thông tin thuộc tính.
- toString() (String): Hiển thị thông tin chi tiết của phiếu mượn.