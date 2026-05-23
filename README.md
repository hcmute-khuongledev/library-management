26TX810014 - LE KHUONG


Bài tập 1: Phân tích yêu cầu và xác định lớp, thuộc tính, phương thức:

*class 1: Book
- Thuộc tính: 
COUNTER_ID (static long): Bộ đếm tĩnh dùng để tự động tăng ID.
id (long): ID hệ thống.
bookCode (String): Mã sách hiển thị theo chuẩn thư viện.
title (String): Tên sách.
author (String): Tác giả.
publishYear (int): Năm xuất bản.
quantity (int): Số lượng hiện có trong kho.
isAvailable (boolean): Trạng thái còn sách để mượn hay không.

- Phương thức:
generateBookCode() (private String): Tự động sinh mã sách theo định dạng HCMUTE-BOOK-%05d.
Các hàm get/set cho từng thuộc tính để đảm bảo tính đóng gói.
decreaseQuantity() (void): Giảm số lượng kho đi 1 khi có độc giả mượn và tự động cập nhật trạng thái isAvailable về false nếu hết sách.
increaseQuantity() (void): Tăng số lượng kho thêm 1 khi độc giả trả sách và cập nhật trạng thái isAvailable thành true.
toString() (String): Ghi đè để xuất chuỗi thông tin scannable của sách.


*class 2: Reader
- Thuộc tính:
COUNTER_ID (static long): Bộ đếm tĩnh tự động tăng tiến ID độc giả.
id (long): ID hệ thống.
readerCode (String): Mã thẻ độc giả hiển thị theo chuẩn.
name (String): Họ và tên độc giả.
email (String): Địa chỉ email liên hệ.
readerType (ReaderType): Loại thẻ (Sử dụng Enum gồm STUDENT or LECTURER).  

- Phương thức:
generateReaderCode() (private String): Tự động sinh mã độc giả theo định dạng HCMUTE-READER-%05d.
getMaxBorrowLimit() (int): Lấy giới hạn sách được mượn dựa trên cấu hình cấu trúc của Enum ReaderType.
Các hàm get/set đóng gói dữ liệu thuộc tính.
toString() (String): Định dạng chuỗi hiển thị thông tin độc giả ra màn hình.


*class 3: BorrowSlip
- Thuộc tính:
COUNTER_ID (static long): Bộ đếm tĩnh tự động tăng tiến ID phiếu mượn.
id (long): ID hệ thống.
slipCode (String): Mã phiếu.
reader (Reader): Tham chiếu đến Reader.
book (Book): Tham chiếu đến đối Book.
borrowDate (LocalDate): Ngày mượn sách thực tế.
dueDate (LocalDate): Ngày hẹn trả sách theo quy định.
returnDate (LocalDate): Ngày độc giả đem trả sách thực tế.
isReturned (boolean): Trạng thái phiếu (Đã trả / Chưa trả).

- Phương thức:
generateSlipCode() (private String): Tự động sinh mã phiếu theo định dạng HCMUTE-SLIP-%05d.
calculate(long lateDays) (static long): Phương thức tĩnh tính tiền phạt dựa trên số ngày trễ hạn.
markAsReturned() (void): Cập nhật trạng thái phiếu mượn thành đã hoàn thành trả sách (isReturned = true).
Các hàm get/set lấy thông tin thuộc tính của phiếu.
toString() (String): Định dạng dữ liệu hiển thị trạng thái chi tiết của phiếu mượn.