/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.ui;

import com.sample.DAO.PhongDAO;
import com.sample.model.Phong;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class QLPhong extends javax.swing.JInternalFrame {

    String roles;
    String userName;
    DefaultTableModel model;
    int index = 0;
    List<Phong> listPhong = new ArrayList<>();
    PhongDAO phongDAO = new PhongDAO();
    String maPhong = "";
    String tenPhong = "";
    String giaTien = "";
    String dienTich = "";
    String trangThai = "";
    String maKhu = "";

    public boolean checkRepeat(String maPhongCheck) {
        boolean trung = false;
        for (int i = 0; i < listPhong.size(); i++) {
            if (maPhongCheck.equalsIgnoreCase(listPhong.get(i).getMaPhong())) {
                JOptionPane.showMessageDialog(this, "Trùng mã phòng");
                trung = false;
                return trung;
            } else {
                trung = true;
            }
        }
        return trung;
    }

    public boolean checkNull() {
        maPhong = txt_MaPhong.getText();
        tenPhong = txt_TenPhong.getText();
        giaTien = txt_GiaPhong.getText();
        dienTich = txt_DienTich.getText();
        maKhu = txt_MaKhu.getText();
        String inputNumber = "\\d{6,10}";
        String inputS = "\\d{1,2}";
        if (maPhong.equals("")) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống mã phòng!!");
            return false;
        } else if (tenPhong.equals("")) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống tên phòng!!");
            return false;
        } else if (giaTien.equals("")) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống giá tiền");
            return false;
        } else if (!(giaTien).matches(inputNumber)) {
            JOptionPane.showMessageDialog(this, "Giá không hơp lệ!! Giá phải có từ 100000 trở lên");
            return false;
        } else if (dienTich.equals("")) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống diện tích phòng");
            return false;
        } else if (!(dienTich).matches(inputS)) {
            JOptionPane.showMessageDialog(this, "Diện tích không hợp lệ!! Diện tích không quá 100 mét vuông");
            return false;
        } else if (maKhu.equals("")) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống mã khu");
            return false;
        }
        return true;
    }

    public void loadTablePhong() {
        model = (DefaultTableModel) tbl_QLPhong.getModel();
        model.setRowCount(0);
        try {
            listPhong = phongDAO.select();
            for (Phong nh : listPhong) {
                model.addRow(new Object[]{nh.getMaKhu(), nh.getMaPhong(), nh.getTenPhong(), nh.getGiaTien(), nh.getDienTich(), nh.getTrangThai()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi đổ dữ liệu vào bảng" + e);
            e.printStackTrace();
        }
    }

    public void loadTablePhongByStatus(String tinhTrang) {
        model = (DefaultTableModel) tbl_QLPhong.getModel();
        model.setRowCount(0);
        try {
            listPhong = phongDAO.findBySatus(tinhTrang);
            for (Phong nh : listPhong) {
                model.addRow(new Object[]{nh.getMaKhu(), nh.getMaPhong(), nh.getTenPhong(), nh.getGiaTien(), nh.getDienTich(), nh.getTrangThai()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi đổ dữ liệu vào bảng" + e);
            e.printStackTrace();
        }
    }

    public void insertPhong() {
        try {
            if (checkRepeat(txt_MaPhong.getText()) && checkNull()) {
                String status = "";
                if (rb_ConTrong.isSelected()) {
                    status = "Trống";
                } else if (rb_DangSuaChua.isSelected()) {
                    status = "Sửa";
                }
                Phong objPhong = new Phong(maPhong, tenPhong, Integer.parseInt(txt_GiaPhong.getText()), Integer.parseInt(txt_DienTich.getText()), status, maKhu, "");
                phongDAO.insert(objPhong);
                loadTablePhong();
                JOptionPane.showMessageDialog(this, "Thêm thành công!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi Thêm" + e);
        }
    }

    public void updatePhong() {
        try {
            String status = "";
            if (rb_ConTrong.isSelected()) {
                status = "Trống";
            } else if (rb_DangSuaChua.isSelected()) {
                status = "Sửa";
            }
            Phong objPhong = new Phong(txt_MaPhong.getText(), tenPhong, Integer.parseInt(txt_GiaPhong.getText()), Integer.parseInt(txt_DienTich.getText()), status, maKhu, "");
            phongDAO.update(objPhong);
            loadTablePhong();
            JOptionPane.showMessageDialog(this, "Update thành công");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi update" + e);
        }
    }

    public void deletePhong() {
        try {
            int ask = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa");
            if (ask == JOptionPane.YES_OPTION) {
                Phong objPhong = new Phong(txt_MaPhong.getText(), null, 0, 0, null, null, null);
                phongDAO.delete(objPhong);
                JOptionPane.showMessageDialog(this, "Xóa thành công!!");
                loadTablePhong();
                if (listPhong.size() > 0) {
                    if (index == listPhong.size()) {
                        index--;
                    }
                    showDetail();
                } else {
                    clearForm();
                }
            }
        } catch (Exception e) {
        }
    }

    public void clearForm() {
        rb_DaThue.setEnabled(false);
        txt_MaPhong.setEditable(true);
        txt_TenPhong.setEditable(true);
        txt_MaKhu.setEditable(true);
        txt_MaPhong.setText("");
        txt_TenPhong.setText("");
        txt_GiaPhong.setText("");
        txt_DienTich.setText("");
        txt_MaKhu.setText("");
    }

    public void showDetail() {
        if (model.getValueAt(index, 5).equals("Đã thuê")) {
            btn_Update.setEnabled(false);
            btn_Delete.setEnabled(false);
        } else {
            btn_Update.setEnabled(true);
            btn_Delete.setEnabled(true);
        }
        if (model.getValueAt(index, 5).equals("Trống")) {
            btn_VietHD.setEnabled(true);
        } else {
            btn_VietHD.setEnabled(false);
        }
        rb_DaThue.setEnabled(false);
        Phong nv = listPhong.get(index);
        txt_MaPhong.setText(nv.getMaPhong());
        txt_TenPhong.setText(nv.getTenPhong());
        txt_GiaPhong.setText(nv.getGiaTien() + "");
        txt_DienTich.setText(nv.getDienTich() + "");
        txt_MaKhu.setText(nv.getMaKhu());
        String status = nv.getTrangThai();
        if (status.equalsIgnoreCase("Đã thuê")) {
            rb_DaThue.setSelected(true);
            rb_ConTrong.setSelected(false);
            rb_DangSuaChua.setSelected(false);
        } else if (status.equalsIgnoreCase("Trống")) {
            rb_DaThue.setSelected(false);
            rb_ConTrong.setSelected(true);
            rb_DangSuaChua.setSelected(false);
        } else if (status.equalsIgnoreCase("Sửa")) {
            rb_DaThue.setSelected(false);
            rb_ConTrong.setSelected(false);
            rb_DangSuaChua.setSelected(true);
        }
        tbl_QLPhong.setRowSelectionInterval(index, index);
    }

    public QLPhong(String userName, String roles) {
        this.roles = roles;
        this.userName = userName;
        initComponents();
        loadTablePhong();
        if (roles.equalsIgnoreCase("NV")) {
            btn_Save.setEnabled(false);
            btn_new.setEnabled(false);
            btn_Update.setEnabled(false);
            btn_Delete.setEnabled(false);
        }
        if (listPhong.size() >= 0) {
            index = 0;
            showDetail();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnl_Title = new javax.swing.JPanel();
        lbl_Title = new javax.swing.JLabel();
        pnl_Form = new javax.swing.JPanel();
        lbl_MaKhu = new javax.swing.JLabel();
        txt_MaKhu = new javax.swing.JTextField();
        lbl_MaPhong = new javax.swing.JLabel();
        txt_MaPhong = new javax.swing.JTextField();
        lbl_TenPhong = new javax.swing.JLabel();
        txt_TenPhong = new javax.swing.JTextField();
        lbl_GiaPhong = new javax.swing.JLabel();
        txt_GiaPhong = new javax.swing.JTextField();
        lbl_DienTich = new javax.swing.JLabel();
        txt_DienTich = new javax.swing.JTextField();
        lbl_TrangThai = new javax.swing.JLabel();
        rb_DaThue = new javax.swing.JRadioButton();
        rb_ConTrong = new javax.swing.JRadioButton();
        rb_DangSuaChua = new javax.swing.JRadioButton();
        btn_Save = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        btn_new = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_QLPhong = new javax.swing.JTable();
        btn_DaThue = new javax.swing.JButton();
        btnTrong = new javax.swing.JButton();
        btn_Fix = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();
        btn_VietHD = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("QUẢN LÝ PHÒNG");

        lbl_Title.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl_Title.setForeground(new java.awt.Color(0, 0, 153));
        lbl_Title.setText("QUẢN LÝ PHÒNG");

        javax.swing.GroupLayout pnl_TitleLayout = new javax.swing.GroupLayout(pnl_Title);
        pnl_Title.setLayout(pnl_TitleLayout);
        pnl_TitleLayout.setHorizontalGroup(
            pnl_TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_TitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Title)
                .addContainerGap(632, Short.MAX_VALUE))
        );
        pnl_TitleLayout.setVerticalGroup(
            pnl_TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_TitleLayout.createSequentialGroup()
                .addComponent(lbl_Title)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(pnl_Title, java.awt.BorderLayout.PAGE_START);

        lbl_MaKhu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_MaKhu.setText("Mã khu");

        txt_MaKhu.setEditable(false);

        lbl_MaPhong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_MaPhong.setText("Mã Phòng");

        txt_MaPhong.setEditable(false);

        lbl_TenPhong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_TenPhong.setText("Tên phòng");

        txt_TenPhong.setEditable(false);

        lbl_GiaPhong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_GiaPhong.setText("Giá phòng");

        lbl_DienTich.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_DienTich.setText("Diện tích");

        lbl_TrangThai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_TrangThai.setText("Trạng thái");

        buttonGroup1.add(rb_DaThue);
        rb_DaThue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rb_DaThue.setText("Đã thuê");

        buttonGroup1.add(rb_ConTrong);
        rb_ConTrong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rb_ConTrong.setText("Còn trống");

        buttonGroup1.add(rb_DangSuaChua);
        rb_DangSuaChua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rb_DangSuaChua.setText("Đang sửa chữa");

        btn_Save.setText("Save");
        btn_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SaveActionPerformed(evt);
            }
        });

        btn_Update.setText("Update");
        btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateActionPerformed(evt);
            }
        });

        btn_new.setText("New");
        btn_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newActionPerformed(evt);
            }
        });

        tbl_QLPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Khu", "Mã Phòng", "Tên Phòng", "Giá Phòng", "Diện Tích", "Trạng Thái"
            }
        ));
        tbl_QLPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_QLPhongMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_QLPhong);

        btn_DaThue.setText("Đã thuê");
        btn_DaThue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DaThueActionPerformed(evt);
            }
        });

        btnTrong.setText("Còn Trống");
        btnTrong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrongActionPerformed(evt);
            }
        });

        btn_Fix.setText("Đang sửa chữa");
        btn_Fix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_FixActionPerformed(evt);
            }
        });

        btn_Delete.setText("Delete");
        btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteActionPerformed(evt);
            }
        });

        btn_VietHD.setText("Viết hợp đồng");
        btn_VietHD.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                btn_VietHDComponentMoved(evt);
            }
        });
        btn_VietHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_VietHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_FormLayout = new javax.swing.GroupLayout(pnl_Form);
        pnl_Form.setLayout(pnl_FormLayout);
        pnl_FormLayout.setHorizontalGroup(
            pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_FormLayout.createSequentialGroup()
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnl_FormLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_DienTich)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_TenPhong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                                    .addComponent(lbl_MaKhu, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_MaKhu, javax.swing.GroupLayout.Alignment.LEADING))
                                .addComponent(lbl_TenPhong))
                            .addComponent(lbl_DienTich)
                            .addGroup(pnl_FormLayout.createSequentialGroup()
                                .addComponent(btn_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_new, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_Delete)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_TrangThai)
                            .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbl_MaPhong)
                                .addComponent(txt_MaPhong)
                                .addComponent(lbl_GiaPhong)
                                .addComponent(txt_GiaPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE))
                            .addGroup(pnl_FormLayout.createSequentialGroup()
                                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(pnl_FormLayout.createSequentialGroup()
                                        .addComponent(btn_DaThue)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnTrong))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnl_FormLayout.createSequentialGroup()
                                        .addComponent(rb_DaThue)
                                        .addGap(18, 18, 18)
                                        .addComponent(rb_ConTrong)))
                                .addGap(18, 18, 18)
                                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rb_DangSuaChua)
                                    .addComponent(btn_Fix)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnl_FormLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)))
                .addGap(15, 15, 15))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_FormLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_VietHD)
                .addContainerGap())
        );
        pnl_FormLayout.setVerticalGroup(
            pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_FormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_MaKhu)
                    .addComponent(lbl_MaPhong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_MaKhu, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txt_MaPhong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_TenPhong)
                    .addComponent(lbl_GiaPhong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_TenPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txt_GiaPhong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_DienTich)
                    .addComponent(lbl_TrangThai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_DienTich, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rb_DaThue)
                    .addComponent(rb_ConTrong)
                    .addComponent(rb_DangSuaChua))
                .addGap(18, 18, 18)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Save)
                    .addComponent(btn_Update)
                    .addComponent(btn_new)
                    .addComponent(btn_DaThue)
                    .addComponent(btnTrong)
                    .addComponent(btn_Fix)
                    .addComponent(btn_Delete))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_VietHD)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        getContentPane().add(pnl_Form, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_QLPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_QLPhongMouseClicked
        try {
            index = tbl_QLPhong.getSelectedRow();
            if (index >= 0) {
                showDetail();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi đổ thông tin lên form");
        }
    }//GEN-LAST:event_tbl_QLPhongMouseClicked

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        try {
            if (checkNull()) {
                this.updatePhong();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps! Error: " + e);
        }
    }//GEN-LAST:event_btn_UpdateActionPerformed

    private void btn_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newActionPerformed
        try {
            this.clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps! Error: " + e);
        }
    }//GEN-LAST:event_btn_newActionPerformed

    private void btn_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SaveActionPerformed
        try {
            this.insertPhong();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps! Error: " + e);
        }
    }//GEN-LAST:event_btn_SaveActionPerformed

    private void btn_DaThueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DaThueActionPerformed
        try {
            btn_Update.setEnabled(false);
            btn_Save.setEnabled(false);
            btn_Delete.setEnabled(false);
            loadTablePhongByStatus("Đã thuê");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps! Error: " + e);
        }
    }//GEN-LAST:event_btn_DaThueActionPerformed

    private void btnTrongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrongActionPerformed
        try {
            loadTablePhongByStatus("Trống");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps! Error: " + e);
        }
    }//GEN-LAST:event_btnTrongActionPerformed

    private void btn_FixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_FixActionPerformed
        try {
            loadTablePhongByStatus("Sửa");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps! Error: " + e);
        }
    }//GEN-LAST:event_btn_FixActionPerformed

    private void btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteActionPerformed
        try {
            this.deletePhong();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps! Error: " + e);
        }
    }//GEN-LAST:event_btn_DeleteActionPerformed

    private void btn_VietHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VietHDActionPerformed
        try {
            QLHopDong qlhd = new QLHopDong(userName, roles, txt_MaPhong.getText());

            FrMain.main.getDsk_main().add(qlhd);
            qlhd.show();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps! Error: " + e);
        }
    }//GEN-LAST:event_btn_VietHDActionPerformed

    private void btn_VietHDComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_btn_VietHDComponentMoved

    }//GEN-LAST:event_btn_VietHDComponentMoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTrong;
    private javax.swing.JButton btn_DaThue;
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_Fix;
    private javax.swing.JButton btn_Save;
    private javax.swing.JButton btn_Update;
    private javax.swing.JButton btn_VietHD;
    private javax.swing.JButton btn_new;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_DienTich;
    private javax.swing.JLabel lbl_GiaPhong;
    private javax.swing.JLabel lbl_MaKhu;
    private javax.swing.JLabel lbl_MaPhong;
    private javax.swing.JLabel lbl_TenPhong;
    private javax.swing.JLabel lbl_Title;
    private javax.swing.JLabel lbl_TrangThai;
    private javax.swing.JPanel pnl_Form;
    private javax.swing.JPanel pnl_Title;
    private javax.swing.JRadioButton rb_ConTrong;
    private javax.swing.JRadioButton rb_DaThue;
    private javax.swing.JRadioButton rb_DangSuaChua;
    private javax.swing.JTable tbl_QLPhong;
    private javax.swing.JTextField txt_DienTich;
    private javax.swing.JTextField txt_GiaPhong;
    private javax.swing.JTextField txt_MaKhu;
    private javax.swing.JTextField txt_MaPhong;
    private javax.swing.JTextField txt_TenPhong;
    // End of variables declaration//GEN-END:variables
}
