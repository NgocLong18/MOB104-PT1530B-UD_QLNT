/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.model;

public class KhachTro {

    private String maKT, tenKT, gioiTinh, soDT, soCMT, eMail, maPhongThue, trangthai;

    public KhachTro() {
    }

    public KhachTro(String maKT, String tenKT, String gioiTinh, String soDT, String soCMT, String eMail, String maPhongThue, String trangthai) {
        this.maKT = maKT;
        this.tenKT = tenKT;
        this.gioiTinh = gioiTinh;
        this.soDT = soDT;
        this.soCMT = soCMT;
        this.eMail = eMail;
        this.maPhongThue = maPhongThue;
        this.trangthai = trangthai;
    }

    public String getMaKT() {
        return maKT;
    }

    public void setMaKT(String maKT) {
        this.maKT = maKT;
    }

    public String getTenKT() {
        return tenKT;
    }

    public void setTenKT(String tenKT) {
        this.tenKT = tenKT;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getSoCMT() {
        return soCMT;
    }

    public void setSoCMT(String soCMT) {
        this.soCMT = soCMT;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getMaPhongThue() {
        return maPhongThue;
    }

    public void setMaPhongThue(String maPhongThue) {
        this.maPhongThue = maPhongThue;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
    

}
