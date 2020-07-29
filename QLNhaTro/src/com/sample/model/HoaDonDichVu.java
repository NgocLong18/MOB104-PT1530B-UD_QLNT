/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.model;


public class HoaDonDichVu {
   private int maHDDV;
   private String suDung, maPhong;
   
   private float tongTien;
   private String trangThai, nguoiTao;

    public HoaDonDichVu() {
    }

    public HoaDonDichVu(int maHDDV, String suDung, String maPhong, float tongTien, String trangThai, String nguoiTao) {
        this.maHDDV = maHDDV;
        this.suDung = suDung;
        this.maPhong = maPhong;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
        this.nguoiTao = nguoiTao;
    }

    public int getMaHDDV() {
        return maHDDV;
    }

    public void setMaHDDV(int maHDDV) {
        this.maHDDV = maHDDV;
    }

    public String getSuDung() {
        return suDung;
    }

    public void setSuDung(String suDung) {
        this.suDung = suDung;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    
}
