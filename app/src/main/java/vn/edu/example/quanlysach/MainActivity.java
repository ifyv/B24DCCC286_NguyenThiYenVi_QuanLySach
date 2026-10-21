package vn.edu.example.quanlysach;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private TextView txtStatistics;
    private ListView listBooks;
    private ArrayAdapter<String> adapter;
    private final ArrayList<String> displayItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BookData.initSampleData();
        txtStatistics = findViewById(R.id.txtStatistics);
        listBooks = findViewById(R.id.listBooks);
        Button btnAddBook = findViewById(R.id.btnAddBook);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayItems);
        listBooks.setAdapter(adapter);

        listBooks.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, BookDetailActivity.class);
            intent.putExtra("bookIndex", position);
            startActivity(intent);
        });

        btnAddBook.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, AddBookActivity.class)));
        refreshBooks();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshBooks();
    }

    private void refreshBooks() {
        displayItems.clear();
        for (Book book : BookData.books) {
            String stars = book.getRating() == 0 ? "Chưa đánh giá"
                    : String.format("%.0f sao", book.getRating());
            displayItems.add(book.getTitle() + "\n"
                    + "Tác giả: " + book.getAuthor() + "\n"
                    + "Thể loại: " + book.getCategory() + " | " + book.getStatus()
                    + " | " + stars);
        }
        adapter.notifyDataSetChanged();

        int total = BookData.books.size();
        int read = 0;
        int unfinished = 0;
        for (Book book : BookData.books) {
            if ("Đã đọc".equals(book.getStatus())) read++;
            else unfinished++; // Gồm Chưa đọc và Đang đọc.
        }
        txtStatistics.setText("Tổng số sách: " + total
                + "     Đã đọc: " + read
                + "     Chưa đọc xong: " + unfinished);
    }
}
