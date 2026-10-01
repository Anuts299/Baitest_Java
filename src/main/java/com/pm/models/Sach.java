package com.pm.models;

public class Sach {
    private String maSach;
    private String tenSach;
    private String tacGia;
    private int soLuong;

    public Sach(String maSach, String tenSach, String tacGia, int soLuong) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.soLuong = soLuong;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public String getTenSach() {
        return tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public String getMaSach() {
        return maSach;
    }

    public String toString() {
        return maSach + " " + tenSach + " " + tacGia + " " + soLuong;
    }

    public boolean giamSoLuong() {
        if (this.soLuong-1 <= 0) {
            return false;
        }
        this.soLuong--;
        return true;
    }
    public void tangSoLuong() {
        this.soLuong++;
    }
}
