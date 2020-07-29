/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.DAO;

import com.sample.helper.JdbcHelper;
import com.sample.model.Phong;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PhongDAO implements DAO<Phong>{

    public void insert(Phong model) {
        String sql = "exec sp_insertRoom ?, ?, ?, ?, ?, ?,? ";
        JdbcHelper.executeUpdate(sql,
                model.getMaPhong(),
                model.getTenPhong(),
                model.getGiaTien(),
                model.getDienTich(),
                model.getTrangThai(),
                model.getMaKhu(),
                model.getNgayThanhToan());
    }

    public List<Phong> select() {
        String sql = "exec sp_selectAllRoom";
        return select(sql);
    }

    private List<Phong> select(String sql, Object... args) {
        List<Phong> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                Phong model = new Phong();
                model.setMaPhong(rs.getString(1));
                model.setTenPhong(rs.getString(2));
                model.setGiaTien(rs.getInt(3));
                model.setDienTich(rs.getInt(4));
                model.setTrangThai(rs.getString(5));
                model.setMaKhu(rs.getString(6));
                model.setNgayThanhToan(rs.getString(7));
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

    public void update(Phong model) {
        String sql = "exec sp_updateRoom ?, ?, ?";
        JdbcHelper.executeUpdate(sql, model.getMaPhong(), model.getGiaTien(), model.getTrangThai());
    }

    public List<Phong> findBySatus(String STT) {
        String sql = "exec sp_findNVBySTT ? ";
        return select(sql, STT);
    }

    @Override
    public void delete(Phong model) {
        String sql =" exec sp_deleteRoom ? ";
        JdbcHelper.executeUpdate(sql, model.getMaPhong());
    }
}
