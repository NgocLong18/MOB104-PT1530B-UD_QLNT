/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.ui;

import com.sample.DAO.KhachTroDAO;
import com.sample.model.KhachTro;
import com.sample.model.NhanVien;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class QLKhachTro extends javax.swing.JInternalFrame {

    String roles;
    DefaultTableModel model;
    int index = 0;
    List<KhachTro> listKhachTro = new ArrayList<>();
    KhachTroDAO KhachTroDAO = new KhachTroDAO();
    String maKT = "";
    String tenKT = "";
    String soDT = "";
    String soCMT = "";
    String eMail = "";
    String maPhongThue = "";

    public boolean checkRepeat(String maKTCheck) {
        boolean trung = false;
        for (int i = 0; i < listKhachTro.size(); i++) {
            if (maKTCheck.equalsIgnoreCase(listKhachTro.get(i).getMaKT())) {
                JOptionPane.showMessageDialog(this, "Trùng mã Nhân Viên");
                trung = false;
                return trung;
            } else {
                trung = true;
            }
        }
        return trung;
    }

    public boolean checkNull() {
        maKT = txt_MaKT.getText();
        tenKT = txt_TenKT.getText();
        soDT = txt_SoDT.getText();
        soCMT = txt_SoCMND.getText();
        eMail = txt_Email.getText();
        maPhongThue = txt_maPhongThue.getText();
        String inputSoDT = "\\d{9,10}";
        String inputSoCMT = "\\d{12}";
        String eMailForm = "\\w+\\w@+\\w.+\\w";
        if (maKT.equals("")) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống mã Khách Trọ!!");
            return false;
        } else if (tenKT.equals("")) {
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
            JOptionPane.showMessageDialog(this, "Không bỏ trống email");
            return false;
        } else if (!eMail.matches(eMailForm)) {
            JOptionPane.showMessageDialog(this, "sai định dang, định dạng email: abc@abc.abc");
            return false;
        } else if (maPhongThue.equals("")) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống Mã phòng thuê");
            return false;
        }
        return true;
    }

    public void loadTableKhachTro() {
        model = (DefaultTableModel) tbl_KhachTro.getModel();
        model.setRowCount(0);
        try {
            listKhachTro = KhachTroDAO.select();
            for (KhachTro kt : listKhachTro) {
                model.addRow(new Object[]{kt.getMaKT(), kt.getTenKT(), kt.getGioiTinh(), kt.getSoDT(), kt.getSoCMT(), kt.geteMail(), kt.getMaPhongThue()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi đổ dữ liệu vào bảng" + e);
            e.printStackTrace();
        }
    }

    public void showDetail() {
        KhachTro kt = listKhachTro.get(index);
        txt_MaKT.setText(kt.getMaKT());
        txt_TenKT.setText(kt.getTenKT());
        String gioiTinh = kt.getGioiTinh();
        if (gioiTinh.equalsIgnoreCase("Nam")) {
            rb_Nam.setSelected(true);
            rb_Nu.setSelected(false);
        } else {
            rb_Nam.setSelected(false);
            rb_Nu.setSelected(true);
        }
        txt_SoDT.setText(kt.getSoDT());
        txt_SoCMND.setText(kt.getSoCMT());
        txt_Email.setText(kt.geteMail());
        txt_maPhongThue.setText(kt.getMaPhongThue());
        tbl_KhachTro.setRowSelectionInterval(index, index);
    }

    public void clearForm() {
        txt_MaKT.setEditable(true);
        txt_TenKT.setEditable(true);
        txt_MaKT.setText("");
        txt_TenKT.setText("");
        txt_SoDT.setText("");
        txt_SoCMND.setText("");
        txt_Email.setText("");
        rb_Nam.setSelected(true);
        rb_Nu.setSelected(false);
        txt_maPhongThue.setText("");
    }

    public void insertKhachTro() {
        if (checkRepeat(txt_MaKT.getText()) && checkNull()) {
            String gioiTinh = "";
            if (rb_Nam.isSelected()) {
                gioiTinh = "Nam";
            } else {
                gioiTinh = "Nữ";
            }
            KhachTro objKhachTro = new KhachTro(maKT, tenKT, gioiTinh, soDT, soCMT, eMail, maPhongThue,"");
            KhachTroDAO.insert(objKhachTro);
            loadTableKhachTro();
            JOptionPane.showMessageDialog(this, "Thêm thành công!!");
        }
    }

    public void updateNhanVien() {
        try {
            if (checkNull()) {
                KhachTro objKhachTro = new KhachTro(maKT, tenKT, null, soDT, soCMT, eMail, maPhongThue,null);
                KhachTroDAO.update(objKhachTro);
                loadTableKhachTro();
                JOptionPane.showMessageDialog(this, "Update thành công");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi update" + e);
            e.printStackTrace();
        }
    }

    public void deleteNguoiHoc() {
        KhachTro objKhachTro = new KhachTro(txt_MaKT.getText(), tenKT, null, soDT, soCMT, eMail, maPhongThue,null);
        KhachTroDAO.delete(objKhachTro);
        JOptionPane.showMessageDialog(this, "Xóa thành công!!");
        loadTableKhachTro();
        if (listKhachTro.size() > 0) {
            if (index == listKhachTro.size()) {
                index--;
            }
            showDetail();
        } else {
            clearForm();
        }
    }

    public void findByMaKT() {
        DefaultTableModel model = (DefaultTableModel) tbl_KhachTro.getModel();
        model.setRowCount(0);
        try {
            String maKTS = txt_Search.getText();
            List<KhachTro> list = KhachTroDAO.findByMaKT(maKTS);
            for (KhachTro kt : list) {
                model.addRow(new Object[]{kt.getMaKT(), kt.getTenKT(), kt.getGioiTinh(), kt.getSoDT(), kt.getSoCMT(), kt.geteMail(), kt.getMaPhongThue()});
            }
            if (list.size() > 0) {
                JOptionPane.showMessageDialog(this, "Tìm thành công");
                index = 0;
                tbl_KhachTro.setRowSelectionInterval(index, index);
                showDetail();
            } else {
                loadTableKhachTro();
                JOptionPane.showMessageDialog(this, "Không có thông tin mã khách trọ này");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi câu truy vấn " + e);
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
            if (index < listKhachTro.size() - 1) {
                index++;
                showDetail();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "đang ở cuối rồi ");
        }
    }

    private void troVeLast() {
        try {
            index = listKhachTro.size() - 1;
            showDetail();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e);
        }
    }

    public QLKhachTro(String roles) {
        this.roles = roles;
        initComponents();
        loadTableKhachTro();
        if (listKhachTro.size() >= 0) {
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
        lbl_MaND = new javax.swing.JLabel();
        txt_MaKT = new javax.swing.JTextField();
        lbl_TenND = new javax.swing.JLabel();
        txt_TenKT = new javax.swing.JTextField();
        lbl_GioiTinh = new javax.swing.JLabel();
        lbl_SoDT = new javax.swing.JLabel();
        txt_SoDT = new javax.swing.JTextField();
        lbl_SoCMND = new javax.swing.JLabel();
        txt_SoCMND = new javax.swing.JTextField();
        lbl_Email = new javax.swing.JLabel();
        txt_Email = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_KhachTro = new javax.swing.JTable();
        rb_Nam = new javax.swing.JRadioButton();
        rb_Nu = new javax.swing.JRadioButton();
        pnl_Search = new javax.swing.JPanel();
        txt_Search = new javax.swing.JTextField();
        btn_Search = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_maPhongThue = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btn_New = new javax.swing.JButton();
        btn_Next = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        btn_Save = new javax.swing.JButton();
        btn_Fist = new javax.swing.JButton();
        btn_Previous = new javax.swing.JButton();
        btn_Last = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("QUẢN LÝ KHÁCH TRỌ");

        lbl_Title.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbl_Title.setForeground(new java.awt.Color(0, 0, 153));
        lbl_Title.setText("QUẢN LÝ KHÁCH TRỌ");

        javax.swing.GroupLayout pnl_TitleLayout = new javax.swing.GroupLayout(pnl_Title);
        pnl_Title.setLayout(pnl_TitleLayout);
        pnl_TitleLayout.setHorizontalGroup(
            pnl_TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_TitleLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lbl_Title)
                .addContainerGap(661, Short.MAX_VALUE))
        );
        pnl_TitleLayout.setVerticalGroup(
            pnl_TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_TitleLayout.createSequentialGroup()
                .addComponent(lbl_Title)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(pnl_Title, java.awt.BorderLayout.PAGE_START);

        lbl_MaND.setText("Mã khách trọ");

        txt_MaKT.setEditable(false);

        lbl_TenND.setText("Tên khách trọ");

        txt_TenKT.setEditable(false);

        lbl_GioiTinh.setText("Giới tính");

        lbl_SoDT.setText("Số điện thoại");

        lbl_SoCMND.setText("Số CMND / Thẻ căn cước");

        lbl_Email.setText("Email");

        tbl_KhachTro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Người Dùng", "Tên Người Dùng", "Giới Tính", "Số ĐT", "Số CMND", "Email", "Mã Phòng Thuê"
            }
        ));
        tbl_KhachTro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_KhachTroMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_KhachTro);

        buttonGroup1.add(rb_Nam);
        rb_Nam.setSelected(true);
        rb_Nam.setText("Nam");

        buttonGroup1.add(rb_Nu);
        rb_Nu.setText("Nữ");

        pnl_Search.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SEARCH NGƯỜI TRỌ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        btn_Search.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Search.setText("Search");
        btn_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_SearchLayout = new javax.swing.GroupLayout(pnl_Search);
        pnl_Search.setLayout(pnl_SearchLayout);
        pnl_SearchLayout.setHorizontalGroup(
            pnl_SearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_SearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(btn_Search)
                .addContainerGap())
        );
        pnl_SearchLayout.setVerticalGroup(
            pnl_SearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_SearchLayout.createSequentialGroup()
                .addGroup(pnl_SearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Search))
                .addGap(0, 5, Short.MAX_VALUE))
        );

        jLabel1.setText("Mã phòng thuê");

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btn_New.setText("New");
        btn_New.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NewActionPerformed(evt);
            }
        });

        btn_Next.setText(">>");
        btn_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NextActionPerformed(evt);
            }
        });

        btn_Update.setText("Update");
        btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateActionPerformed(evt);
            }
        });

        btn_Save.setText("Save");
        btn_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SaveActionPerformed(evt);
            }
        });

        btn_Fist.setText("|<");
        btn_Fist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_FistActionPerformed(evt);
            }
        });

        btn_Previous.setText("<<");
        btn_Previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PreviousActionPerformed(evt);
            }
        });

        btn_Last.setText(">|");
        btn_Last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_Save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Fist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(btn_Previous, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(btn_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(btn_Update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_New, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Last, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_New, btn_Save, btn_Update});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Save)
                    .addComponent(btn_Update)
                    .addComponent(btn_New))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Fist)
                    .addComponent(btn_Previous)
                    .addComponent(btn_Next)
                    .addComponent(btn_Last))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnl_FormLayout = new javax.swing.GroupLayout(pnl_Form);
        pnl_Form.setLayout(pnl_FormLayout);
        pnl_FormLayout.setHorizontalGroup(
            pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_FormLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_FormLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbl_MaND)
                                .addComponent(lbl_GioiTinh)
                                .addComponent(lbl_SoCMND)
                                .addComponent(txt_MaKT, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                                .addGroup(pnl_FormLayout.createSequentialGroup()
                                    .addComponent(rb_Nam)
                                    .addGap(18, 18, 18)
                                    .addComponent(rb_Nu))
                                .addComponent(txt_SoCMND, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(txt_maPhongThue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(38, 38, 38)
                        .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_FormLayout.createSequentialGroup()
                                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_TenND)
                                    .addComponent(lbl_SoDT)
                                    .addComponent(lbl_Email))
                                .addGap(0, 392, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_FormLayout.createSequentialGroup()
                                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_TenKT, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_SoDT, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_Email, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_FormLayout.createSequentialGroup()
                        .addComponent(pnl_Search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addComponent(jScrollPane1)))
        );

        pnl_FormLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, lbl_GioiTinh});

        pnl_FormLayout.setVerticalGroup(
            pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_FormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_MaND)
                    .addComponent(lbl_TenND))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_MaKT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TenKT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_GioiTinh)
                    .addComponent(lbl_SoDT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_SoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rb_Nam)
                    .addComponent(rb_Nu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Email, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_SoCMND))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_SoCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_FormLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(28, 28, 28)
                        .addComponent(txt_maPhongThue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(pnl_Search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnl_FormLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txt_SoCMND, txt_maPhongThue});

        getContentPane().add(pnl_Form, java.awt.BorderLayout.LINE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_KhachTroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_KhachTroMouseClicked
        try {
            index = tbl_KhachTro.getSelectedRow();
            if (index >= 0) {
                showDetail();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: " + e);
        }
    }//GEN-LAST:event_tbl_KhachTroMouseClicked

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        try {
            this.updateNhanVien();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: " + e);
        }
    }//GEN-LAST:event_btn_UpdateActionPerformed

    private void btn_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SaveActionPerformed
        try {
            if (checkRepeat(txt_MaKT.getText()) && checkNull()) {
                this.insertKhachTro();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: " + e);
        }
    }//GEN-LAST:event_btn_SaveActionPerformed

    private void btn_NewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NewActionPerformed
        try {
            this.clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: " + e);
        }
    }//GEN-LAST:event_btn_NewActionPerformed

    private void btn_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SearchActionPerformed
        try {
            this.findByMaKT();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: " + e);
        }
    }//GEN-LAST:event_btn_SearchActionPerformed

    private void btn_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LastActionPerformed
        try {
            this.troVeLast();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: " + e);
        }
    }//GEN-LAST:event_btn_LastActionPerformed

    private void btn_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NextActionPerformed
        try {
            this.troVeSau();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: " + e);
        }
    }//GEN-LAST:event_btn_NextActionPerformed

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

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Fist;
    private javax.swing.JButton btn_Last;
    private javax.swing.JButton btn_New;
    private javax.swing.JButton btn_Next;
    private javax.swing.JButton btn_Previous;
    private javax.swing.JButton btn_Save;
    private javax.swing.JButton btn_Search;
    private javax.swing.JButton btn_Update;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_Email;
    private javax.swing.JLabel lbl_GioiTinh;
    private javax.swing.JLabel lbl_MaND;
    private javax.swing.JLabel lbl_SoCMND;
    private javax.swing.JLabel lbl_SoDT;
    private javax.swing.JLabel lbl_TenND;
    private javax.swing.JLabel lbl_Title;
    private javax.swing.JPanel pnl_Form;
    private javax.swing.JPanel pnl_Search;
    private javax.swing.JPanel pnl_Title;
    private javax.swing.JRadioButton rb_Nam;
    private javax.swing.JRadioButton rb_Nu;
    private javax.swing.JTable tbl_KhachTro;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_MaKT;
    private javax.swing.JTextField txt_Search;
    private javax.swing.JTextField txt_SoCMND;
    private javax.swing.JTextField txt_SoDT;
    private javax.swing.JTextField txt_TenKT;
    private javax.swing.JTextField txt_maPhongThue;
    // End of variables declaration//GEN-END:variables
}
