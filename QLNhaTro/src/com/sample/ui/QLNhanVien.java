/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.ui;

import com.sample.DAO.NhanVienDAO;
import com.sample.DAO.UserDAO;
import com.sample.helper.JdbcHelper;
import com.sample.model.NhanVien;
import com.sample.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class QLNhanVien extends javax.swing.JInternalFrame {

    DefaultTableModel model;
    int index = 0;
    List<NhanVien> listNhanVien = new ArrayList<>();
    NhanVienDAO NhanVienDAO = new NhanVienDAO();
    List<User> listUser = new ArrayList<>();
    User userModel = new User();
    UserDAO userDAO = new UserDAO();
    String maNV = "";
    String tenNV = "";
    String soDT = "";
    String soCMT = "";
    String eMail = "";
    String maKhu = "";

    public boolean checkRepeat(String maNVcheck, String userIDcheck) {
        boolean trung = false;
        for (int i = 0; i < listNhanVien.size(); i++) {
            if (maNVcheck.equalsIgnoreCase(listNhanVien.get(i).getMaNV())) {
                JOptionPane.showMessageDialog(this, "Trùng mã Nhân Viên");
                trung = false;
                return trung;
            } else if (userIDcheck.equals(listNhanVien.get(i).getUserID() + "")) {
                JOptionPane.showMessageDialog(this, "Trùng userID");
                trung = false;
                return trung;
            } else {
                trung = true;
            }
        }
        return trung;
    }

    public boolean checkNull() {
        maNV = txt_MaNV.getText();
        tenNV = txt_TenNV.getText();
        soDT = txt_SoDT.getText();
        soCMT = txt_SoCMND.getText();
        eMail = txt_Email.getText();
        maKhu = txt_MaKhu.getText();
        String inputSoDT = "\\d{9,10}";
        String inputSoCMT = "\\d{12}";
        String eMailForm = "\\w+\\w@+\\w.+\\w";
        if (maNV.equals("")) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống mã Nhân viên!!");
            return false;
        } else if (tenNV.equals("")) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống Họ tên!!");
            return false;
        } else if (soDT.equals("")) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống SĐT");
            return false;
        } else if (!(soDT).matches(inputSoDT)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải là số từ 9 đến 10 số");
            return false;
        } else if (soCMT.equals("")) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống số CMT");
            return false;
        } else if (!(soCMT).matches(inputSoCMT)) {
            JOptionPane.showMessageDialog(this, "Số CMT phải là 12 số");
            return false;
        } else if (eMail.equals("")) {
            JOptionPane.showMessageDialog(this, "không bỏ trống email");
            return false;
        } else if (!eMail.matches(eMailForm)) {
            JOptionPane.showMessageDialog(this, "sai định dang, định dạng email: abc@abc.abc");
            return false;
        }
        return true;
    }

    public void loadTableNhanVien() {
        model = (DefaultTableModel) tbl_NhanVien.getModel();
        model.setRowCount(0);
        try {
            listNhanVien = NhanVienDAO.select();
            for (NhanVien nh : listNhanVien) {
                model.addRow(new Object[]{
                    nh.getUserID(), 
                    nh.getMaNV(), 
                    nh.getTenNV(),
                    nh.getGioiTinh(), 
                    nh.getSoDT(), 
                    nh.getSoCMT(), 
                    nh.geteMail(), 
                    nh.getMaKhu()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi đổ dữ liệu vào bảng" + e);
            e.printStackTrace();
        }
    }

    public void showDetail() {
        NhanVien nv = listNhanVien.get(index);
        txt_UserID.setText(nv.getUserID() + "");
        txt_MaNV.setText(nv.getMaNV());
        txt_TenNV.setText(nv.getTenNV());
        String gioiTinh = nv.getGioiTinh();
        if (gioiTinh.equalsIgnoreCase("Nam")) {
            rb_Nam.setSelected(true);
            rb_Nu.setSelected(false);
        } else {
            rb_Nam.setSelected(false);
            rb_Nu.setSelected(true);
        }
        txt_SoDT.setText(nv.getSoDT());
        txt_SoCMND.setText(nv.getSoCMT());
        txt_Email.setText(nv.geteMail());
        txt_MaKhu.setText(nv.getMaKhu());
        tbl_NhanVien.setRowSelectionInterval(index, index);
    }

    public void clearForm() {
        txt_MaNV.setEditable(true);
        txt_TenNV.setEditable(true);
        txt_MaNV.setText("");
        txt_UserID.setText("");
        txt_TenNV.setText("");
        txt_SoDT.setText("");
        txt_SoCMND.setText("");
        txt_Email.setText("");
        rb_Nam.setSelected(true);
        rb_Nu.setSelected(false);
        txt_MaKhu.setText("");
    }

    public void insertNhanVien() {
        if (checkRepeat(txt_MaNV.getText(), txt_UserID.getText()) && checkNull()) {
            String gioiTinh = "";
            if (rb_Nam.isSelected()) {
                gioiTinh = "Nam";
            } else {
                gioiTinh = "Nữ";
            }
            userModel = new User(null, maNV, "123", "NV");
            userDAO.insert(userModel);
            listUser = userDAO.findByUser(maNV);
            for (User us : listUser) {
                txt_UserID.setText(us.getUserID());
            }
            NhanVien nv = new NhanVien(maNV, Integer.parseInt(txt_UserID.getText()), tenNV, gioiTinh, soDT, soCMT, eMail, maKhu, "");
            NhanVienDAO.insert(nv);
            loadTableNhanVien();
            JOptionPane.showMessageDialog(this, "Thêm thành công!!");
        }
    }

    public void updateNhanVien() {
        try {
            if (checkNull()) {
                NhanVien objNhanVien = new NhanVien(maNV, Integer.parseInt(txt_UserID.getText()), null, null, soDT, soCMT, eMail, maKhu, null);
                NhanVienDAO.update(objNhanVien);
                loadTableNhanVien();
                JOptionPane.showMessageDialog(this, "Update thành công");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi update" + e);
            e.printStackTrace();
        }
    }

    public void deleteNhanVien() {
        int ask = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa");
        if (ask == JOptionPane.YES_OPTION) {
            NhanVien objNhanVien = new NhanVien(txt_MaNV.getText(), Integer.parseInt(txt_UserID.getText()), null, null, soDT, soCMT, eMail, maKhu, null);
            NhanVienDAO.delete(objNhanVien);
            JOptionPane.showMessageDialog(this, "Xóa thành công!!");
            loadTableNhanVien();
            if (listNhanVien.size() > 0) {
                if (index == listNhanVien.size()) {
                    index--;
                }
                showDetail();
            } else {
                clearForm();
            }
        }

    }

    private void troVeFirst() {
        try {
            index = 0;
            showDetail();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e);
        }
    }

    private void troVeTruoc() {
        try {
            if (index > 0) {
                index--;
                showDetail();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e);
        }
    }

    private void troVeSau() {
        try {
            if (index < listNhanVien.size() - 1) {
                index++;
                showDetail();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "đang ở cuối rồi ");
        }
    }

    private void troVeLast() {
        try {
            index = listNhanVien.size() - 1;
            showDetail();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e);
        }
    }

    public QLNhanVien() {
        initComponents();
        loadTableNhanVien();
        if (listNhanVien.size() >= 0) {
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
        lbl_UserID = new javax.swing.JLabel();
        txt_UserID = new javax.swing.JTextField();
        lbl_MaNV = new javax.swing.JLabel();
        txt_MaNV = new javax.swing.JTextField();
        lbl_TenNV = new javax.swing.JLabel();
        txt_TenNV = new javax.swing.JTextField();
        lbl_GioiTinh = new javax.swing.JLabel();
        lbl_SoDT = new javax.swing.JLabel();
        rb_Nam = new javax.swing.JRadioButton();
        rb_Nu = new javax.swing.JRadioButton();
        txt_SoDT = new javax.swing.JTextField();
        lbl_SoCMND = new javax.swing.JLabel();
        txt_SoCMND = new javax.swing.JTextField();
        lbl_Email = new javax.swing.JLabel();
        txt_Email = new javax.swing.JTextField();
        lbl_MaKhu = new javax.swing.JLabel();
        txt_MaKhu = new javax.swing.JTextField();
        btn_Save = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();
        btn_New = new javax.swing.JButton();
        btn_Last = new javax.swing.JButton();
        btn_Next = new javax.swing.JButton();
        btn_Previous = new javax.swing.JButton();
        btn_Fist = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_NhanVien = new javax.swing.JTable();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setResizable(true);
        setTitle("QUẢN LÝ NHÂN VIÊN");

        lbl_Title.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbl_Title.setText("QUẢN LÝ NHÂN VIÊN");

        javax.swing.GroupLayout pnl_TitleLayout = new javax.swing.GroupLayout(pnl_Title);
        pnl_Title.setLayout(pnl_TitleLayout);
        pnl_TitleLayout.setHorizontalGroup(
            pnl_TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_TitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Title)
                .addContainerGap(696, Short.MAX_VALUE))
        );
        pnl_TitleLayout.setVerticalGroup(
            pnl_TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_TitleLayout.createSequentialGroup()
                .addComponent(lbl_Title)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(pnl_Title, java.awt.BorderLayout.PAGE_START);

        lbl_UserID.setText("User ID");

        txt_UserID.setEditable(false);

        lbl_MaNV.setText("Mã Nhân Viên");

        txt_MaNV.setEditable(false);

        lbl_TenNV.setText("Tên Nhân Viên");

        txt_TenNV.setEditable(false);

        lbl_GioiTinh.setText("Giới Tính");

        lbl_SoDT.setText("Số Điện Thoại");

        buttonGroup1.add(rb_Nam);
        rb_Nam.setText("Nam");

        buttonGroup1.add(rb_Nu);
        rb_Nu.setText("Nữ");

        lbl_SoCMND.setText("Số CMND / Thẻ Căn Cước");

        txt_SoCMND.setToolTipText("");

        lbl_Email.setText("Email");

        lbl_MaKhu.setText("Mã Khu");

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

        btn_Delete.setText("Delete");
        btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteActionPerformed(evt);
            }
        });

        btn_New.setText("New");
        btn_New.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NewActionPerformed(evt);
            }
        });

        btn_Last.setText(">|");
        btn_Last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LastActionPerformed(evt);
            }
        });

        btn_Next.setText(">>");
        btn_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NextActionPerformed(evt);
            }
        });

        btn_Previous.setText("<<");
        btn_Previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PreviousActionPerformed(evt);
            }
        });

        btn_Fist.setText("|<");
        btn_Fist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_FistActionPerformed(evt);
            }
        });

        tbl_NhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "Mã Nhân Viên", "Tên Nhân Viên", "Giới Tính", "Số Điện Thoại", "Số CMND", "Email", "Mã Khu"
            }
        ));
        tbl_NhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_NhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_NhanVien);

        javax.swing.GroupLayout pnl_FormLayout = new javax.swing.GroupLayout(pnl_Form);
        pnl_Form.setLayout(pnl_FormLayout);
        pnl_FormLayout.setHorizontalGroup(
            pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_FormLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(pnl_FormLayout.createSequentialGroup()
                        .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_Email)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_SoDT, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txt_TenNV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                                            .addComponent(lbl_UserID, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_UserID, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addComponent(lbl_TenNV)))
                                .addComponent(lbl_SoDT))
                            .addComponent(lbl_Email)
                            .addGroup(pnl_FormLayout.createSequentialGroup()
                                .addComponent(btn_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addComponent(btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(btn_New, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(44, 44, 44)
                        .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_FormLayout.createSequentialGroup()
                                .addComponent(btn_Fist, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)
                                .addComponent(btn_Previous, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(btn_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(btn_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_MaKhu)
                            .addComponent(lbl_SoCMND)
                            .addComponent(lbl_GioiTinh)
                            .addComponent(lbl_MaNV)
                            .addComponent(txt_MaNV)
                            .addGroup(pnl_FormLayout.createSequentialGroup()
                                .addComponent(rb_Nam)
                                .addGap(18, 18, 18)
                                .addComponent(rb_Nu))
                            .addComponent(txt_SoCMND)
                            .addComponent(txt_MaKhu, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        pnl_FormLayout.setVerticalGroup(
            pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_FormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_UserID)
                    .addComponent(lbl_MaNV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_UserID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_TenNV)
                    .addComponent(lbl_GioiTinh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_TenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rb_Nam)
                    .addComponent(rb_Nu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_SoDT)
                    .addComponent(lbl_SoCMND))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_SoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_SoCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Email)
                    .addComponent(lbl_MaKhu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_MaKhu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Save)
                    .addComponent(btn_Update)
                    .addComponent(btn_Delete)
                    .addComponent(btn_New)
                    .addComponent(btn_Last)
                    .addComponent(btn_Next)
                    .addComponent(btn_Previous)
                    .addComponent(btn_Fist))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        getContentPane().add(pnl_Form, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SaveActionPerformed
        try {
            this.insertNhanVien();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e);
        }
    }//GEN-LAST:event_btn_SaveActionPerformed

    private void tbl_NhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_NhanVienMouseClicked
        try {
            index = tbl_NhanVien.getSelectedRow();
            if (index >= 0) {
                showDetail();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi đổ thông tin lên form");
        }
    }//GEN-LAST:event_tbl_NhanVienMouseClicked

    private void btn_FistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_FistActionPerformed
        try {
            this.troVeFirst();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: " + e);
        }
    }//GEN-LAST:event_btn_FistActionPerformed

    private void btn_PreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PreviousActionPerformed
        try {
            this.troVeTruoc();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: " + e);
        }
    }//GEN-LAST:event_btn_PreviousActionPerformed

    private void btn_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NextActionPerformed
        try {
            this.troVeSau();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: " + e);
        }
    }//GEN-LAST:event_btn_NextActionPerformed

    private void btn_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LastActionPerformed
        try {
            this.troVeLast();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: " + e);
        }
    }//GEN-LAST:event_btn_LastActionPerformed

    private void btn_NewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NewActionPerformed
        try {
            this.clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: " + e);
        }
    }//GEN-LAST:event_btn_NewActionPerformed

    private void btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteActionPerformed
        try {
            this.deleteNhanVien();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: " + e);
        }
    }//GEN-LAST:event_btn_DeleteActionPerformed

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        try {
            this.updateNhanVien();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: " + e);
        }
    }//GEN-LAST:event_btn_UpdateActionPerformed

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(QLNhanVien.class
//                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
//
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(QLNhanVien.class
//                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
//
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(QLNhanVien.class
//                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
//
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(QLNhanVien.class
//                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new QLNhanVien().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_Fist;
    private javax.swing.JButton btn_Last;
    private javax.swing.JButton btn_New;
    private javax.swing.JButton btn_Next;
    private javax.swing.JButton btn_Previous;
    private javax.swing.JButton btn_Save;
    private javax.swing.JButton btn_Update;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_Email;
    private javax.swing.JLabel lbl_GioiTinh;
    private javax.swing.JLabel lbl_MaKhu;
    private javax.swing.JLabel lbl_MaNV;
    private javax.swing.JLabel lbl_SoCMND;
    private javax.swing.JLabel lbl_SoDT;
    private javax.swing.JLabel lbl_TenNV;
    private javax.swing.JLabel lbl_Title;
    private javax.swing.JLabel lbl_UserID;
    private javax.swing.JPanel pnl_Form;
    private javax.swing.JPanel pnl_Title;
    private javax.swing.JRadioButton rb_Nam;
    private javax.swing.JRadioButton rb_Nu;
    private javax.swing.JTable tbl_NhanVien;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_MaKhu;
    private javax.swing.JTextField txt_MaNV;
    private javax.swing.JTextField txt_SoCMND;
    private javax.swing.JTextField txt_SoDT;
    private javax.swing.JTextField txt_TenNV;
    private javax.swing.JTextField txt_UserID;
    // End of variables declaration//GEN-END:variables
}
