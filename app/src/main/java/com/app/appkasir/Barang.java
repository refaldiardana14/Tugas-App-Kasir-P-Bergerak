package com.app.appkasir;

import android.os.Parcel;
import android.os.Parcelable;

public class Barang implements Parcelable {

    private String barang;
    private int harga;

    public Barang(String barang, int harga) {
        this.barang = barang;
        this.harga = harga;
    }

    protected Barang(Parcel in) {
        barang = in.readString();
        harga = in.readInt();
    }

    public static final Creator<Barang> CREATOR = new Creator<Barang>() {
        @Override
        public Barang createFromParcel(Parcel in) {
            return new Barang(in);
        }

        @Override
        public Barang[] newArray(int size) {
            return new Barang[size];
        }
    };

    public String getBarang() {
        return barang;
    }

    public void setBarang(String barang) {
        this.barang = barang;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(barang);
        dest.writeInt(harga);
    }
}
