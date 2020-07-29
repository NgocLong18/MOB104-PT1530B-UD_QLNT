/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.ui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.sample.DAO.DichVuDAO;
import com.sample.model.DichVu;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class FrDichVu extends javax.swing.JInternalFrame {

    DefaultTableModel model;
    int index = 0;
    List<DichVu> listDV = new ArrayList<>();
    DichVuDAO dichVuDAO = new DichVuDAO();
    String maDV = "";
    String tenDV = "";
    String giaDV = "";

    public boolean checkRepeat(String maPhongCheck) {
        boolean trung = false;
        for (int i = 0; i < listDV.size(); i++) {
            if (maPhongCheck.equalsIgnoreCase(listDV.get(i).getMaDV())) {
                JOptionPane.showMessageDialog(this, "Trùng mã dịch vụ");
                trung = false;
                return trung;
            } else {
                trung = true;
            }
        }
        return trung;
    }
    
    public boolean checkNull() {
        giaDV = txt_GiaDV.getText();
        String inputPrice = "\\d{4,6}";
        if(giaDV.equals("")){
            JOptionPane.showConfirmDialog(this, "Không bỏ trống giá");
            return false;
        }
        if (!giaDV.matches(inputPrice)) {
            JOptionPane.showConfirmDialog(this, "Giá không hợp lệ!!! giá phải từ 1000-999999");
            return false;
        }
        return true;
    }

    public void loadTableDichVu() {
        model = (DefaultTableModel) tbl_DichVu.getModel();
        model.setRowCount(0);
        try {
            listDV = dichVuDAO.select();
            for (DichVu dv : listDV) {
                model.addRow(new Object[]{dv.getMaDV(), dv.getTenDV(), dv.getGia()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi đổ dữ liệu vào bảng" + e);
            e.printStackTrace();
        }
    }

    public void showDetail() {
        DichVu kt = listDV.get(index);
        txt_maDV.setText(kt.getMaDV());
        txt_TenDV.setText(kt.getTenDV());
        txt_GiaDV.setText(kt.getGia() + "");
        tbl_DichVu.setRowSelectionInterval(index, index);
    }
    public void insertDV(){
        try {
            if(checkNull()){
                DichVu dv = new DichVu(txt_maDV.getText(),txt_TenDV.getText(), Integer.parseInt(txt_GiaDV.getText()));
                dichVuDAO.insert(dv);
                loadTableDichVu();
                JOptionPane.showMessageDialog(this, "Insert dịch vụ thành công!!!"); 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: "+e);
        }
    }
    public void clearForm(){
        txt_maDV.setText("");
        txt_TenDV.setText("");
        txt_GiaDV.setText("");
        txt_maDV.setEditable(true);
    }

    public FrDichVu() {
        initComponents();
        loadTableDichVu();
        if (listDV.size() > 0) {
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

        PNL_Title = new javax.swing.JPanel();
        lbl_Title = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbl_MaDV = new javax.swing.JLabel();
        lbl_TenDV = new javax.swing.JLabel();
        txt_TenDV = new javax.swing.JTextField();
        lbl_GiaDV = new javax.swing.JLabel();
        txt_GiaDV = new javax.swing.JTextField();
        btn_Update = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_DichVu = new javax.swing.JTable();
        txt_maDV = new javax.swing.JTextField();
        btn_Insert = new javax.swing.JButton();
        btn_New = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("DỊCH VỤ PHÒNG TRỌ");
        setToolTipText("");

        lbl_Title.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl_Title.setForeground(new java.awt.Color(0, 0, 153));
        lbl_Title.setText("DỊCH VỤ PHÒNG TRỌ");

        javax.swing.GroupLayout PNL_TitleLayout = new javax.swing.GroupLayout(PNL_Title);
        PNL_Title.setLayout(PNL_TitleLayout);
        PNL_TitleLayout.setHorizontalGroup(
            PNL_TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNL_TitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Title)
                .addContainerGap(368, Short.MAX_VALUE))
        );
        PNL_TitleLayout.setVerticalGroup(
            PNL_TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNL_TitleLayout.createSequentialGroup()
                .addComponent(lbl_Title)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(PNL_Title, java.awt.BorderLayout.PAGE_START);

        lbl_MaDV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_MaDV.setText("Mã dịch vụ");

        lbl_TenDV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_TenDV.setText("Tên dịch vụ");

        lbl_GiaDV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_GiaDV.setText("Giá dịch vụ");

        btn_Update.setText("Update");
        btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateActionPerformed(evt);
            }
        });

        tbl_DichVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Dịch Vụ", "Tên Dịch Vụ", "Giá Dịch Vụ"
            }
        ));
        tbl_DichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DichVuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_DichVu);

        txt_maDV.setEditable(false);

        btn_Insert.setText("Insert");
        btn_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InsertActionPerformed(evt);
            }
        });

        btn_New.setText("New");
        btn_New.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(txt_GiaDV)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_TenDV, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_MaDV)
                            .addComponent(lbl_TenDV)
                            .addComponent(lbl_GiaDV)
                            .addComponent(txt_maDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_Insert)
                        .addGap(29, 29, 29)
                        .addComponent(btn_Update)
                        .addGap(24, 24, 24)
                        .addComponent(btn_New)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txt_TenDV, txt_maDV});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_MaDV)
                .addGap(9, 9, 9)
                .addComponent(txt_maDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_TenDV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TenDV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_GiaDV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_GiaDV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Update)
                    .addComponent(btn_Insert)
                    .addComponent(btn_New))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txt_TenDV, txt_maDV});

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_DichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_DichVuMouseClicked
        try {
            index = tbl_DichVu.getSelectedRow();
            if (index >= 0) {
                showDetail();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: " + e);
        }
    }//GEN-LAST:event_tbl_DichVuMouseClicked

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        try {
            if(checkNull()){
                DichVu dv = new DichVu(maDV, tenDV, Integer.parseInt(txt_GiaDV.getText()));
                dichVuDAO.update(dv);
                loadTableDichVu();
                JOptionPane.showMessageDialog(this, "Update giá thành công!!!"); 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: "+e);
        }
    }//GEN-LAST:event_btn_UpdateActionPerformed

    private void btn_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_InsertActionPerformed
        try {
            if(checkRepeat(txt_maDV.getText()) && checkNull()){
                insertDV();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_InsertActionPerformed

    private void btn_NewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NewActionPerformed
        this.clearForm();
    }//GEN-LAST:event_btn_NewActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PNL_Title;
    private javax.swing.JButton btn_Insert;
    private javax.swing.JButton btn_New;
    private javax.swing.JButton btn_Update;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_GiaDV;
    private javax.swing.JLabel lbl_MaDV;
    private javax.swing.JLabel lbl_TenDV;
    private javax.swing.JLabel lbl_Title;
    private javax.swing.JTable tbl_DichVu;
    private javax.swing.JTextField txt_GiaDV;
    private javax.swing.JTextField txt_TenDV;
    private javax.swing.JTextField txt_maDV;
    // End of variables declaration//GEN-END:variables
}
