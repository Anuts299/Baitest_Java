package com.pm;

import com.pm.models.Sach;
import com.pm.models.ThanhVien;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int luaChon = 0;
        HashMap<String ,Sach> danhSachSach = new HashMap<>();
        HashMap<String ,ThanhVien> danhSachThanhVien = new HashMap<>();
        do {
            System.out.println("\t\tHệ thống quản lý thư viện");
            System.out.println("\t1. Thêm sách \n"+
                    "\t2. Thêm thành viên\n"+
                    "\t3. Thực hiện mượn sách\n"+
                    "\t4. Thực hiện trả sách\n"+
                    "\t5. Số sách và các còn lại trong kho\n"+
                    "\t6. Kiểm tra mượn sách\n"+
                    "\t0. Thoát chương trình");
            System.out.println("Chọn chức năng: ");
            luaChon = sc.nextInt();
            sc.nextLine();
            switch (luaChon) {
                case 1:{
                    System.out.println("\tThêm sách");
                    System.out.println("Mã sách:");
                    String maSach = sc.nextLine();
                    System.out.println("Tên sách:");
                    String tenSach = sc.nextLine();
                    System.out.println("Tác giả:");
                    String tacGia = sc.nextLine();
                    System.out.println("Số lượng bản:");
                    int soLuong = sc.nextInt();
                    Sach sach = new Sach(maSach, tenSach, tacGia, soLuong);
                    Sach sachCu = danhSachSach.putIfAbsent(maSach, sach);

                    if(sachCu != null){
                        System.out.println("Lỗi! Mã sách: "+ sach.getMaSach()+" đã tồn tại trong hệ thống");
                    } else {
                        System.out.println("Thêm sách thành công!");
                    }
                    break;

                }
                case 2: {
                    System.out.println("\tThêm thành viên");
                    System.out.println("Mã thành viên:");
                    String maThanhVien= sc.nextLine();

                    System.out.println("Tên thành viên:");
                    String tenThanhVien= sc.nextLine();

                    System.out.println("Giới hạn mượn:");
                    int soLuong = sc.nextInt();

                    ThanhVien thanhVienMoi = new ThanhVien(maThanhVien, tenThanhVien, soLuong);
                    ThanhVien thanhVienCu = danhSachThanhVien.putIfAbsent(maThanhVien, thanhVienMoi);

                    if(thanhVienCu != null){
                        System.out.println("Lỗi! Mã thành viên: "+thanhVienMoi.maThanhVien()+" đã có trong hệ thống");
                    } else {
                        System.out.println("Thêm thành viên thành công!");
                    }
                    break;
                }
                case 3: {
                    System.out.println("\tThực hiện mượn sách");
                    for (ThanhVien tv : danhSachThanhVien.values()) {
                        System.out.println(tv.maThanhVien()+" - "+tv.getTenThanhVien());
                    }
                    System.out.println("Mã thành viên mượn:");
                    String maThanhVien = sc.nextLine();
                    for (Sach tv : danhSachSach.values()) {
                        System.out.println(tv.getMaSach()+" - "+tv.getTenSach());
                    }
                    System.out.println("Mã sách:");
                    String maSach = sc.nextLine();

                    if (danhSachThanhVien.containsKey(maThanhVien) && danhSachSach.containsKey(maSach)) {
                        danhSachThanhVien.get(maThanhVien).muonSach(maSach);
                        if (danhSachSach.get(maSach).giamSoLuong()) {
                            System.out.println("Mượn sách thành công!");
                        } else {
                            System.out.println("Không còn bản nào trong hệ thống");
                        }
                    } else {
                        System.out.println("Thành viên hoặc mã sách không tồn tại!");
                    }

                    break;
                }
                case 4: {
                    System.out.println("Thực hiện trả sách");
                    for (ThanhVien tv : danhSachThanhVien.values()) {
                        System.out.println("-"+tv.maThanhVien());
                    }
                    System.out.println("Mã thành viên trả sách:");
                    String maThanhVien = sc.nextLine();
                    for (Sach tv : danhSachSach.values()) {
                        System.out.println(tv.getMaSach()+" - "+tv.getTenSach());
                    }
                    System.out.println("Mã sach trả:");
                    String maSach = sc.nextLine();

                    if (danhSachThanhVien.containsKey(maThanhVien) && danhSachSach.containsKey(maSach)) {
                        danhSachThanhVien.get(maThanhVien).thucHienTraSach(maSach);
                        danhSachSach.get(maSach).tangSoLuong();
                        System.out.println("Trả sách thành công!");
                    } else {
                        System.out.println("Thành viên hoặc mã sách không tồn tại!");
                    }

                    break;
                }
                case 5: {
                    System.out.println("Các sách hiện có trong kho");
                    if (danhSachSach.isEmpty()) {
                        System.out.println("Chưa có sách trong kho");
                    } else {
                        for (Sach sach : danhSachSach.values()) {
                            System.out.println(sach.getMaSach()+" - "+sach.getTenSach()+" - "+sach.getSoLuong());
                        }
                    }

                    break;
                }
                case 6: {
                    System.out.println("Chi tiết mượn sách của thành viên:");
                    String maThanhVien = sc.nextLine();
                    if (danhSachThanhVien.containsKey(maThanhVien)) {
                        HashSet<String> danhSachMuon = danhSachThanhVien.get(maThanhVien).getDanhSachMuon();
                        if (danhSachMuon.isEmpty()) {
                            System.out.println("Thành viên này chưa mượn sách nào!");
                        } else {
                            System.out.println("Các sách đang mượn:");
                            for (String maSachMuon : danhSachMuon) {
                                System.out.println("- "+maSachMuon);
                            }
                        }

                    } else {
                        System.out.println("Thành viên không tồn tại!");
                    }
                    break;
                }
            }
        } while(luaChon != 0);
    }
}