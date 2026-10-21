package vn.edu.example.quanlysach;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

public class AddBookActivity extends Activity {
    private EditText edtTitle, edtAuthor, edtNote;
    private Spinner spCategory;
    private RadioGroup rgStatus;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        edtTitle = findViewById(R.id.edtTitle);
        edtAuthor = findViewById(R.id.edtAuthor);
        edtNote = findViewById(R.id.edtNote);
        spCategory = findViewById(R.id.spCategory);
        rgStatus = findViewById(R.id.rgStatus);
        ratingBar = findViewById(R.id.ratingBar);

        String[] categories = {"Văn học", "Khoa học", "Công nghệ", "Kỹ năng sống", "Khác"};
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(categoryAdapter);

        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel = findViewById(R.id.btnCancel);
        btnSave.setOnClickListener(v -> saveBook());
        btnCancel.setOnClickListener(v -> finish());
    }

    private void saveBook() {
        String title = edtTitle.getText().toString().trim();
        String author = edtAuthor.getText().toString().trim();

        if (TextUtils.isEmpty(title)) {
            edtTitle.setError("Tên sách không được để trống");
            edtTitle.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(author)) {
            edtAuthor.setError("Tác giả không được để trống");
            edtAuthor.requestFocus();
            return;
        }

        int selectedId = rgStatus.getCheckedRadioButtonId();
        String status = "Chưa đọc";
        if (selectedId == R.id.rbReading) status = "Đang đọc";
        else if (selectedId == R.id.rbRead) status = "Đã đọc";

        Book book = new Book(title, author, spCategory.getSelectedItem().toString(),
                status, ratingBar.getRating(), edtNote.getText().toString().trim());
        BookData.books.add(book);
        Toast.makeText(this, "Đã thêm sách thành công", Toast.LENGTH_SHORT).show();
        finish(); // MainActivity sẽ cập nhật danh sách trong onResume().
    }
}
