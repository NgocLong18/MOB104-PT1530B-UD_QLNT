/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.model;

public class Phong {
   private String maPhong, tenPhong;
   private float giaTien,dienTich;
   private String trangThai, maKhu;
   private String ngayThanhToan;

    public Phong() {
    }

    public Phong(String maPhong, String tenPhong, float giaTien, float dienTich, String trangThai, String maKhu, String ngayThanhToan) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.giaTien = giaTien;
        this.dienTich = dienTich;
        this.trangThai = trangThai;
        this.maKhu = maKhu;
        this.ngayThanhToan = ngayThanhToan;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public float getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(float giaTien) {
        this.giaTien = giaTien;
    }

    public float getDienTich() {
        return dienTich;
    }

    public void setDienTich(float dienTich) {
        this.dienTich = dienTich;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaKhu() {
        return maKhu;
    }

    public void setMaKhu(String maKhu) {
        this.maKhu = maKhu;
    }

    public String getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(String ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }
   
    
   
   
}
