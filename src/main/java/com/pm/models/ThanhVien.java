package com.pm.models;

import java.util.HashSet;

public class ThanhVien {
    private String maThanhVien;
    private String tenThanhVien;
    private int gioiHanMuon;
    private HashSet<String> danhSachMuon = new HashSet<>();

    public ThanhVien(String maThanhVien, String tenThanhVien, int gioiHanMuon) {
        this.maThanhVien = maThanhVien;
        this.tenThanhVien = tenThanhVien;
        this.gioiHanMuon = gioiHanMuon;
    }

    public String maThanhVien() {
        return maThanhVien;
    }

    public String getTenThanhVien() {
        return tenThanhVien;
    }

    public int getGioiHanMuon() {
        return gioiHanMuon;
    }

    public void muonSach(String maSach) {

        if (danhSachMuon.size() <= gioiHanMuon) {
            if(danhSachMuon.add(maSach)) {
                System.out.println("Thêm sách thành công!");
            } else {
                System.out.println("Sách đã được thành viên mượn trước đó");
            }
        } else {
            System.out.println("Thành viên đã đặt giới hạn mượn! Không thể mượn thêm sách");
        }

    }

    public HashSet<String> getDanhSachMuon() {
        return danhSachMuon;
    }

    public void thucHienTraSach(String maSach) {
        danhSachMuon.remove(maSach);
    }
}
