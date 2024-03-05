package com.app.appkasir;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.app.appkasir.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding bind;
    List<Barang> list;
    BarangAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(bind.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String[] barang = getResources().getStringArray(R.array.barang);
        String[] harga = getResources().getStringArray(R.array.harga);

        List<Barang> dataBarang = new ArrayList<>();
        for (int i = 0; i < Math.min(barang.length, harga.length); i++) {
            dataBarang.add(new Barang(barang[i], Integer.parseInt(harga[i])));
        }

        ArrayAdapter<String> arrayAdapt = new ArrayAdapter<>(this, R.layout.dropdown_item,
                dataBarang.stream().map(Barang::getBarang).toArray(String[]::new));
        arrayAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bind.spinner.setAdapter(arrayAdapt);

        list = new ArrayList<>();

        bind.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BarangAdapter((ArrayList<Barang>) list);
        bind.recyclerView.setAdapter(adapter);

        bind.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Barang selectData = dataBarang.get(position);
                System.out.println("ini data = " + selectData.getBarang() + " dan " + selectData.getHarga());

                bind.btn.setOnClickListener(v -> {
                    list.add(new Barang(selectData.getBarang(), selectData.getHarga()));
                    adapter.notifyDataSetChanged();

                    int total = 0;
                    int admin = 2000;
                    for (Barang item : list) {
                        total += item.getHarga();
                        admin += 500;
                    }

                    bind.total.setText("Total Harga " + total + " + " + admin);
                    String dataHarga = total + " " + admin;

                    bind.pesan.setOnClickListener(v1 -> {
                        Intent i = new Intent(MainActivity.this, PesananActivity.class);
                        i.putExtra("data", dataHarga);
                        i.putParcelableArrayListExtra("barang", new ArrayList<>(list));
                        startActivity(i);
                        finish();
                    });
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Not implemented yet
            }
        });

    }
}