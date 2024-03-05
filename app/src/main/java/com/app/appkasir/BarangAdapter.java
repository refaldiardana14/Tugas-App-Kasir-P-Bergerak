package com.app.appkasir;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.app.appkasir.databinding.ListItemBinding;
import java.util.ArrayList;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.ListViewHolder> {
    ArrayList<Barang> listBarang;

    public BarangAdapter(ArrayList<Barang> listBarang) {
        this.listBarang = listBarang;
    }

    @NonNull
    @Override
    public BarangAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListItemBinding bind = ListItemBinding.inflate(inflater, parent, false);
        return new ListViewHolder(bind);
    }

    @Override
    public void onBindViewHolder(@NonNull BarangAdapter.ListViewHolder holder, int position) {
        Barang item = listBarang.get(position);

        holder.bind.barang.setText(item.getBarang());
        holder.bind.harga.setText(String.valueOf(item.getHarga()));
    }

    @Override
    public int getItemCount() {
        return listBarang.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        ListItemBinding bind;

        public ListViewHolder(ListItemBinding bind) {
            super(bind.getRoot());
            this.bind = bind;
        }
    }
}
