package vn.edu.example.quanlysach;

import java.util.ArrayList;

// Lưu danh sách sách trong bộ nhớ khi ứng dụng đang chạy.
public class BookData {
    public static final ArrayList<Book> books = new ArrayList<>();

    public static void initSampleData() {
        if (!books.isEmpty()) return;
        books.add(new Book("Lập trình Android cơ bản", "Nguyễn Văn A",
                "Công nghệ", "Đang đọc", 4, "Sách học Android cơ bản."));
        books.add(new Book("Đắc nhân tâm", "Dale Carnegie",
                "Kỹ năng sống", "Đã đọc", 5, "Đọc để phát triển bản thân."));
        books.add(new Book("Dế Mèn phiêu lưu ký", "Tô Hoài",
                "Văn học", "Chưa đọc", 0, ""));
    }
}
