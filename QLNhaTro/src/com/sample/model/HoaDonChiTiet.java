
package com.sample.model;


public class HoaDonChiTiet {
    private int maHDCT;
    private String maDVSD, maPhong;
    private float thanhTien;
    private String thoiGian;
    private int maHDDV;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int maHDCT, String maDVSD, String maPhong, float thanhTien, String thoiGian, int maHDDV) {
        this.maHDCT = maHDCT;
        this.maDVSD = maDVSD;
        this.maPhong = maPhong;
        this.thanhTien = thanhTien;
        this.thoiGian = thoiGian;
        this.maHDDV = maHDDV;
    }

    public int getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(int maHDCT) {
        this.maHDCT = maHDCT;
    }

    public String getMaDVSD() {
        return maDVSD;
    }

    public void setMaDVSD(String maDVSD) {
        this.maDVSD = maDVSD;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public int getMaHDDV() {
        return maHDDV;
    }

    public void setMaHDDV(int maHDDV) {
        this.maHDDV = maHDDV;
    }
    
  
}
