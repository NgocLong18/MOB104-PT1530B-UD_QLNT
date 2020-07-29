/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.model;


public class ThongKe {
    private String maDV,tenDV;
    private int DoanhThu;

    public ThongKe() {
    }

    public ThongKe(String maDV, String tenDV, int DoanhThu) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.DoanhThu = DoanhThu;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public int getDoanhThu() {
        return DoanhThu;
    }

    public void setDoanhThu(int DoanhThu) {
        this.DoanhThu = DoanhThu;
    }
    
}
