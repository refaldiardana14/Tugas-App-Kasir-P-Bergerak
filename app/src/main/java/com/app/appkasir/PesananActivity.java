package com.app.appkasir;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.app.appkasir.databinding.ActivityPesananBinding;

import java.util.ArrayList;

public class PesananActivity extends AppCompatActivity {

    ActivityPesananBinding bind;
    BarangAdapter adapter;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityPesananBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(bind.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList<Barang> list = getIntent().getParcelableArrayListExtra("barang");
        String data = getIntent().getStringExtra("data");

        bind.total.setText("Total Harga " + data);

        bind.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BarangAdapter(list);
        bind.recyclerView.setAdapter(adapter);
    }
}