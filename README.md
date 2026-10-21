# Ứng dụng Quản lý thư viện sách cá nhân

## Công nghệ
- Java, XML, Android Studio
- ArrayList<Book> lưu dữ liệu trong bộ nhớ khi ứng dụng chạy
- Intent chuyển màn hình

## Đăng nhập
- Tên đăng nhập: `admin`
- Mật khẩu: `123456`

## Chạy dự án
1. Giải nén `QuanLySachAndroid.zip`.
2. Mở Android Studio → Open → chọn thư mục `QuanLySachAndroid`.
3. Chờ Gradle Sync hoàn tất. Nếu được hỏi cài Android SDK Platform 35, chọn Install.
4. Chọn máy ảo Android hoặc kết nối điện thoại đã bật USB debugging.
5. Nhấn Run (▶).

## Chức năng
- Đăng nhập, kiểm tra bỏ trống và sai thông tin.
- Hiển thị sách mẫu và thống kê.
- Thêm sách, kiểm tra tên sách và tác giả.
- Xem chi tiết, thay đổi đánh giá, đánh dấu đã đọc, xóa sách có xác nhận.
- Dữ liệu chỉ tồn tại trong bộ nhớ khi ứng dụng đang chạy; đóng tiến trình có thể làm mất dữ liệu mới thêm.

## Lưu ý
Dự án không kèm Gradle Wrapper. Android Studio có thể mở và đồng bộ dự án bằng Gradle tích hợp. Nếu môi trường yêu cầu wrapper, tạo wrapper từ Android Studio/Gradle đã cài đặt.
