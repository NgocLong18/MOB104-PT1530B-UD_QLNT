/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.DAO;

import com.sample.helper.JdbcHelper;
import com.sample.model.User;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User> {

    private List<User> list = new ArrayList<>();

    @Override
    public List<User> select() {
        String sql = "select * from Users";
        return select(sql);
    }

    private List<User> select(String sql, Object... args) {

        ResultSet rs = null;
        try {
            rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                User model = new User();
                model.setUserID(rs.getString(1));
                model.setUser(rs.getString(2));
                model.setPassWord(rs.getString(3));
                model.setRoles(rs.getString(4));
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
    public void insert(User model) {
        String sql = "insert into Users values(?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getUser(),
                model.getPassWord(),
                model.getRoles());

    }

    @Override
    public void update(User model) {
        String sql = "exec sp_changePass ?, ? ";
        JdbcHelper.executeUpdate(sql, model.getUser(),model.getPassWord());
    }

    @Override
    public void delete(User model) {
        String sql = "exec sp_deleteUser ? ";
        JdbcHelper.executeUpdate(sql, model.getUserID());
    }

    public List<User> findByUser(String userID) {
        String sql = "exec sp_selectUser ? ";
        return select(sql, userID);
    }

     public void convertPassToString(String userName) {
        String sql = "exec sp_convertPasstoString ? ";
        JdbcHelper.executeUpdate(sql, userName);
    }
    
    public void convertStringtoPass(String userNamePass) {
        String sql = "exec sp_convertStringToPass ? ";
        JdbcHelper.executeUpdate(sql, userNamePass);
    }

    public List<User> selectLogin(String userName, String passWord) {
        String sql = "exec sp_Login ?, ? ";
        return select(sql,userName, passWord);
    }
    
    
}
