/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.DAO;

import com.sample.helper.JdbcHelper;
import com.sample.model.DichVu;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DichVuDAO implements DAO<DichVu>{

    @Override
    public List<DichVu> select() {
        String sql = "exec sp_selectAllDV";
        return select(sql);
    }

    private List<DichVu> select(String sql, Object... args) {
        List<DichVu> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                DichVu model = new DichVu();
                model.setMaDV(rs.getString(1));
                model.setTenDV(rs.getString(2));
                model.setGia(rs.getInt(3));
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
    public void insert(DichVu model) {
        String sql = "exec sp_insertDV ?, ?, ? ";
        JdbcHelper.executeUpdate(sql, model.getMaDV(), model.getTenDV(), model.getGia());
    }

    @Override
    public void update(DichVu model) {
        String sql = "exec sp_updateDV ?, ? ";
        JdbcHelper.executeUpdate(sql, model.getMaDV(), model.getGia());
    }

    @Override
    public void delete(DichVu model) {
        String sql = "exec sp_deleteDV ? ";
        JdbcHelper.executeUpdate(sql, model.getMaDV());
    }

}
