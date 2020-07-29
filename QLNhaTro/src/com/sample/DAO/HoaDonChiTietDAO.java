/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.DAO;

import com.sample.helper.JdbcHelper;
import com.sample.model.HoaDonChiTiet;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author akais
 */
public class HoaDonChiTietDAO implements DAO<HoaDonChiTiet>{

    @Override
    public List<HoaDonChiTiet> select() {
       String sql = "exec sp_selectAllHDCT ";
       return select(sql);
    }

    private List<HoaDonChiTiet> select(String sql, Object... args) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                HoaDonChiTiet model = new HoaDonChiTiet();
                model.setMaHDCT(rs.getInt(1));
                model.setMaDVSD(rs.getString(2));
                model.setMaPhong(rs.getString(3));
                model.setThanhTien(rs.getInt(4));
                model.setThoiGian(rs.getString(5));
                model.setMaHDDV(rs.getInt(6));
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
    public void insert(HoaDonChiTiet model) {
      String sql = "exec sp_insertHDCT ?, ?, ?, ?, ?";
      JdbcHelper.executeUpdate(sql,
              model.getMaDVSD(),
              model.getMaPhong(),
              model.getThanhTien(),
              model.getThoiGian(),
              model.getMaHDDV());
    }

    @Override
    public void update(HoaDonChiTiet model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(HoaDonChiTiet model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<HoaDonChiTiet> findMaHDCTByMaPhong(String maPhong){
        String sql = "exec sp_selectMaHDCTByMaPhong ? ";
        return select(sql, maPhong);
    }
    
}
