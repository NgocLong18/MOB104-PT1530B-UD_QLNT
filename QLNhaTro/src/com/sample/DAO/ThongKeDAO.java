/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.DAO;

import com.sample.helper.JdbcHelper;
import com.sample.model.ThongKe;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author akais
 */
public class ThongKeDAO implements DAO<ThongKe> {

    @Override
    public List<ThongKe> select() {
        String sql = "exec sp_ThongKeDoanhThu";
        return select(sql);
    }

    private List<ThongKe> select(String sql, Object... args) {
        List<ThongKe> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                ThongKe model = new ThongKe();
                model.setMaDV(rs.getString(1));
                model.setTenDV(rs.getString(2));
                model.setDoanhThu(rs.getInt(3));
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
    public void insert(ThongKe model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(ThongKe model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(ThongKe model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<ThongKe> selectByThangAndNam(int thang, int nam) {
        String sql = "exec sp_ThongKeDoanhThuTheoThang ?, ?";
        return select(sql, thang, nam);
    }

    public List<ThongKe> selectByAsc(int thang, int nam) {
        String sql = "exec sp_SapXepThongKeTang ?, ?";
        return select(sql, thang, nam);
    }

    public List<ThongKe> selectByDesc(int thang, int nam) {
        String sql = "exec sp_SapXepThongKeGiam ?, ?";
        return select(sql, thang, nam);
    }
}
