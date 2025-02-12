/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.ui;

import com.sample.DAO.UserDAO;
import com.sample.model.User;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author akais
 */
public class ChangePass extends javax.swing.JInternalFrame {

    String userName;
    UserDAO usDAO = new UserDAO();
    String oldPass = "";
    String newPass = "";
    String confirmPass = "";

    public boolean checkError() {
        oldPass = new String(ps_oldPass.getPassword());
        newPass = new String(ps_newPass.getPassword());
        confirmPass = new String(ps_confirmPass.getPassword());
        if (oldPass.equals("")) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống mật khẩu cũ");
            return false;
        } else if (newPass.equals("")) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống mật khẩu mới");
            return false;
        } else if (confirmPass.equals("")) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống Xác nhận mật khẩu");
            return false;
        } else if (!confirmPass.equals(newPass)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không trùng");
            return false;
        }
        return true;

    }

    public ChangePass(String userName) {
        this.userName = userName;
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        bnt_Confirm = new javax.swing.JButton();
        ps_oldPass = new javax.swing.JPasswordField();
        ps_newPass = new javax.swing.JPasswordField();
        ps_confirmPass = new javax.swing.JPasswordField();

        jLabel1.setText("jLabel1");

        setClosable(true);
        setTitle("CHANGE PASSWORD");

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Mật khẩu cũ");

        jLabel3.setText("Xác nhận mật khẩu");

        jLabel4.setText("Mật khẩu mới");

        bnt_Confirm.setText("Đổi mật khẩu");
        bnt_Confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnt_ConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ps_oldPass))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ps_newPass, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ps_confirmPass, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(bnt_Confirm)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ps_oldPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ps_newPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ps_confirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(bnt_Confirm)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel2, ps_confirmPass, ps_newPass, ps_oldPass});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bnt_ConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnt_ConfirmActionPerformed
        try {
            if (checkError()) {
                int ask = JOptionPane.showConfirmDialog(this, "Bạn muốn đổi mật khẩu???");
                if (ask == JOptionPane.YES_OPTION) {
                    User objuser = new User(null, userName, newPass, null);
                    usDAO.update(objuser);
                    usDAO.convertStringtoPass(userName);
                    JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công!!");
                    this.dispose();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: " + e);
        }
    }//GEN-LAST:event_bnt_ConfirmActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnt_Confirm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField ps_confirmPass;
    private javax.swing.JPasswordField ps_newPass;
    private javax.swing.JPasswordField ps_oldPass;
    // End of variables declaration//GEN-END:variables
}
