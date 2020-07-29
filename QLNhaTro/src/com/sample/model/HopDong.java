/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.model;

/**
 *
 * @author akais
 */
public class HopDong {
    private String maHD, maNguoiDaiDien;
    private int soNguoi;
    private String maNguoiO;
    private int maHDDV;
    private String maPhong, ngayThue, ngayTra;
    private int thanhTien;
    private String trangthai, nguoiTao, maDV, ngayThanhToan;

    public HopDong() {
    }

    public HopDong(String maHD, String maNguoiDaiDien, int soNguoi, String maNguoiO, int maHDDV, String maPhong, String ngayThue, String ngayTra, int thanhTien, String trangthai, String nguoiTao, String maDV, String ngayThanhToan) {
        this.maHD = maHD;
        this.maNguoiDaiDien = maNguoiDaiDien;
        this.soNguoi = soNguoi;
        this.maNguoiO = maNguoiO;
        this.maHDDV = maHDDV;
        this.maPhong = maPhong;
        this.ngayThue = ngayThue;
        this.ngayTra = ngayTra;
        this.thanhTien = thanhTien;
        this.trangthai = trangthai;
        this.nguoiTao = nguoiTao;
        this.maDV = maDV;
        this.ngayThanhToan = ngayThanhToan;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaNguoiDaiDien() {
        return maNguoiDaiDien;
    }

    public void setMaNguoiDaiDien(String maNguoiDaiDien) {
        this.maNguoiDaiDien = maNguoiDaiDien;
    }

    public int getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(int soNguoi) {
        this.soNguoi = soNguoi;
    }

    public String getMaNguoiO() {
        return maNguoiO;
    }

    public void setMaNguoiO(String maNguoiO) {
        this.maNguoiO = maNguoiO;
    }

    public int getMaHDDV() {
        return maHDDV;
    }

    public void setMaHDDV(int maHDDV) {
        this.maHDDV = maHDDV;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getNgayThue() {
        return ngayThue;
    }

    public void setNgayThue(String ngayThue) {
        this.ngayThue = ngayThue;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(String ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }
     
}
