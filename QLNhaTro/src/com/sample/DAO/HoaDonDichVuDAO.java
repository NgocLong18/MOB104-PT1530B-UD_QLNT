/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.DAO;

import com.sample.helper.JdbcHelper;
import com.sample.model.HoaDonDichVu;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author akais
 */
public class HoaDonDichVuDAO implements DAO<HoaDonDichVu> {

    @Override
    public List<HoaDonDichVu> select() {
        String sql = "exec sp_selectAllHDDV";
        return select(sql);
    }

    private List<HoaDonDichVu> select(String sql, Object... args) {
        List<HoaDonDichVu> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                HoaDonDichVu model = new HoaDonDichVu();
                model.setMaHDDV(rs.getInt(1));
                model.setSuDung(rs.getString(2));
                model.setNguoiTao(rs.getString(3));
                model.setTongTien(rs.getInt(4));
                model.setTrangThai(rs.getString(5));
                model.setNguoiTao(rs.getString(6));
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
    public void insert(HoaDonDichVu model) {
        String sql = "exec sp_insertHDDV ?, ?, ?, ?, ?";
        JdbcHelper.executeUpdate(sql,
                model.getSuDung(),
                model.getMaPhong(),
                model.getTongTien(),
                model.getTrangThai(),
                model.getNguoiTao()
        );
    }

    @Override
    public void update(HoaDonDichVu model) {
        String sql = "exec sp_updateHDDV ?, ?, ?, ?";
        JdbcHelper.executeUpdate(sql,
                model.getSuDung(),
                model.getMaPhong(),
                model.getTongTien(),
                model.getTrangThai());
    }

    @Override
    public void delete(HoaDonDichVu model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<HoaDonDichVu> findMaHDDVByMaPhong(String maPhong){
        String sql = "exec sp_selectMaHDDVByMaPhong ? ";
        return select(sql, maPhong);
    }

}
