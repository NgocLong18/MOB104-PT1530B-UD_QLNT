/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.DAO;

import com.sample.helper.JdbcHelper;
import com.sample.model.KhachTro;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KhachTroDAO implements DAO<KhachTro> {

    /**
     *
     * @param model
     */
    @Override
    public void insert(KhachTro model) {
        String sql = "exec sp_insertKT ?, ?, ?, ?, ?, ?, ?, ? ";
        JdbcHelper.executeUpdate(sql,
                model.getMaKT(),
                model.getTenKT(),
                model.getGioiTinh(),
                model.getSoDT(),
                model.getSoCMT(),
                model.geteMail(),
                model.getMaPhongThue(),
                model.getTrangthai());
    }

    @Override
    public List<KhachTro> select() {
        String sql = "exec sp_selectAllKT";
        return select(sql);
    }

    private List<KhachTro> select(String sql, Object... args) {
        List<KhachTro> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                KhachTro model = new KhachTro();
                model.setMaKT(rs.getString(1));
                model.setTenKT(rs.getString(2));
                model.setGioiTinh(rs.getString(3));
                model.setSoDT(rs.getString(4));
                model.setSoCMT(rs.getString(5));
                model.seteMail(rs.getString(6));
                model.setMaPhongThue(rs.getString(7));
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
    public void delete(KhachTro model) {
        String sql = "exec sp_deleteKT ?";
        JdbcHelper.executeUpdate(sql, model.getMaKT());
    }

    @Override
    public void update(KhachTro model) {
        String sql = "exec sp_updateKT ?, ?, ?, ?";
        JdbcHelper.executeUpdate(sql, model.getMaKT(), model.getSoDT(), model.getSoCMT(), model.geteMail());
    }

    public List<KhachTro> findByMaKT(String maKT) {
        String sql = "exec sp_findKTByMAKT ? ";
        return select(sql, maKT);
    }

}
