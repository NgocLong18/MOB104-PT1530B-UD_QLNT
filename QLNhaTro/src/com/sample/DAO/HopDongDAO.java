package com.sample.DAO;

import com.sample.helper.JdbcHelper;
import com.sample.model.HopDong;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HopDongDAO implements DAO<HopDong> {

    @Override
    public List<HopDong> select() {
        String sql = "exec sp_selectAllHD";
        return select(sql);
    }

    private List<HopDong> select(String sql, Object... args) {
        List<HopDong> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                HopDong model = new HopDong();
                model.setMaHD(rs.getString(1));
                model.setMaNguoiDaiDien(rs.getString(2));
                model.setSoNguoi(rs.getInt(3));
                model.setMaNguoiO(rs.getString(4));
                model.setMaHDDV(rs.getInt(5));
                model.setMaPhong(rs.getString(6));
                model.setNgayThue(rs.getString(7));
                model.setNgayTra(rs.getString(8));
                model.setThanhTien(rs.getInt(9));
                model.setTrangthai(rs.getString(10));
                model.setNguoiTao(rs.getString(11));
                model.setMaDV(rs.getString(12));
                model.setNgayThanhToan(rs.getString(13));
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
    public void insert(HopDong model) {
        String sql = "exec sp_insertHD ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?  ";
        JdbcHelper.executeUpdate(sql, 
                model.getMaNguoiDaiDien(),
                model.getSoNguoi(), 
                model.getMaNguoiO(),
                model.getMaHDDV(),
                model.getMaPhong(),
                model.getNgayThue(),
                model.getNgayTra(),
                model.getThanhTien(),
                model.getTrangthai(),
                model.getNguoiTao(),
                model.getMaDV(),
                model.getNgayThanhToan());
    }

    @Override
    public void update(HopDong model) {
        String sql = "exec sp_updateHD ?, ?, ?, ?, ?,? ";
        JdbcHelper.executeUpdate(sql, 
                model.getMaHD(),
                model.getSoNguoi(),
                model.getMaNguoiO(),
                model.getNgayTra(),
                model.getThanhTien(),
                model.getTrangthai());
    }

    @Override
    public void delete(HopDong model) {
        String sql = "exec sp_deleteHD ? ";
        JdbcHelper.executeUpdate(sql, model.getMaHD());
    }
    
    public List<HopDong> findByMaPhong(String maPhong){
        String sql = "exec sp_findHDByMaPhong ? ";
        return select(sql, maPhong);
    }

}
