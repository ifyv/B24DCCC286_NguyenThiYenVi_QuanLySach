package vn.edu.example.quanlysach;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class BookDetailActivity extends Activity {
    private int bookIndex;
    private Book book;
    private TextView txtTitle, txtAuthorCategory, txtStatus, txtNote, txtRatingValue;
    private RatingBar ratingDetail;
    private Button btnMarkRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        bookIndex = getIntent().getIntExtra("bookIndex", -1);
        if (bookIndex < 0 || bookIndex >= BookData.books.size()) {
            finish();
            return;
        }
        book = BookData.books.get(bookIndex);

        txtTitle = findViewById(R.id.txtDetailTitle);
        txtAuthorCategory = findViewById(R.id.txtDetailAuthorCategory);
        txtStatus = findViewById(R.id.txtDetailStatus);
        txtNote = findViewById(R.id.txtDetailNote);
        txtRatingValue = findViewById(R.id.txtRatingValue);
        ratingDetail = findViewById(R.id.ratingDetail);
        btnMarkRead = findViewById(R.id.btnMarkRead);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnDelete = findViewById(R.id.btnDelete);

        showBook();
        ratingDetail.setOnRatingBarChangeListener((bar, rating, fromUser) -> {
            if (fromUser) {
                book.setRating(rating);
                txtRatingValue.setText("Đánh giá: " + (int) rating + "/5 sao");
            }
        });

        btnMarkRead.setOnClickListener(v -> {
            book.setStatus("Đã đọc");
            showBook();
            Toast.makeText(this, "Đã cập nhật tình trạng đọc", Toast.LENGTH_SHORT).show();
        });
        btnBack.setOnClickListener(v -> finish());
        btnDelete.setOnClickListener(v -> confirmDelete());
    }

    private void showBook() {
        txtTitle.setText(book.getTitle());
        txtAuthorCategory.setText("Tác giả: " + book.getAuthor()
                + "\nThể loại: " + book.getCategory());
        txtStatus.setText("Tình trạng: " + book.getStatus());
        txtNote.setText(book.getNote().isEmpty() ? "Không có ghi chú" : book.getNote());
        ratingDetail.setRating(book.getRating());
        txtRatingValue.setText("Đánh giá: " + (int) book.getRating() + "/5 sao");
        btnMarkRead.setEnabled(!"Đã đọc".equals(book.getStatus()));
        btnMarkRead.setVisibility("Đã đọc".equals(book.getStatus()) ? View.GONE : View.VISIBLE);
    }

    private void confirmDelete() {
        new AlertDialog.Builder(this)
                .setTitle("Xóa sách")
                .setMessage("Bạn có chắc chắn muốn xóa cuốn sách này không?")
                .setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss())
                .setPositiveButton("Xóa", (dialog, which) -> {
                    BookData.books.remove(bookIndex);
                    Toast.makeText(this, "Đã xóa sách", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .show();
    }
}
