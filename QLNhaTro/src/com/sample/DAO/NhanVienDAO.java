/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.DAO;

import com.sample.helper.JdbcHelper;
import com.sample.model.NhanVien;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO implements DAO<NhanVien> {

    public void insert(NhanVien model) {
        String sql = "exec sp_insertNV ?, ?, ?, ?, ?, ?, ?, ?, ?";
        JdbcHelper.executeUpdate(sql,
                model.getMaNV(),
                model.getUserID(),
                model.getTenNV(),
                model.getGioiTinh(),
                model.getSoDT(),
                model.getSoCMT(),
                model.geteMail(),
                model.getMaKhu(),
                model.getTrangThai());
    }

    @Override
    public List<NhanVien> select() {
        String sql = "exec sp_selectAllNV";
        return select(sql);
    }

    private List<NhanVien> select(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                NhanVien model = new NhanVien();
                model.setMaNV(rs.getString(1));
                model.setUserID(rs.getInt(2));
                model.setTenNV(rs.getString(3));
                model.setGioiTinh(rs.getString(4));
                model.setSoDT(rs.getString(5));
                model.setSoCMT(rs.getString(6));
                model.seteMail(rs.getString(7));
                model.setMaKhu(rs.getString(8));
                list.add(model);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            };
        }
        return list;
    }

    @Override
    public void update(NhanVien model) {
        String sql = "exec sp_updateNV ?, ?, ?, ?, ? ";
        JdbcHelper.executeUpdate(sql, model.getMaNV(), model.getSoDT(), model.getSoCMT(), model.geteMail(), model.getMaKhu());
    }

    @Override
    public void delete(NhanVien model) {
        String sql = "exec sp_deleteNV ?";
        JdbcHelper.executeUpdate(sql, model.getMaNV());
    }
    
    public List<NhanVien> findByMaNV(String maNV){
        String sql = "exec sp_findNVByMANV ? ";
        return select(sql, maNV);
    }
}
