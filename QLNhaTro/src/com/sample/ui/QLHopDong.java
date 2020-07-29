/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.ui;

import com.sample.DAO.DichVuDAO;
import com.sample.DAO.HoaDonDichVuDAO;
import com.sample.DAO.HopDongDAO;
import com.sample.DAO.KhachTroDAO;
import com.sample.DAO.UserDAO;
import com.sample.helper.checkDate;
import com.sample.model.DichVu;
import com.sample.model.HoaDonDichVu;
import com.sample.model.HopDong;
import com.sample.model.KhachTro;
import com.sample.model.User;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class QLHopDong extends javax.swing.JInternalFrame {

    String roles;
    String username;

    DefaultTableModel model;
    DefaultTableModel modelDV;
    int index = 0;
    List<HopDong> listHopDong = new ArrayList<>();
    HopDongDAO hopDongDAO = new HopDongDAO();
    DichVuDAO dicVuDAO = new DichVuDAO();
    HoaDonDichVuDAO DAOHDDV = new HoaDonDichVuDAO();
    String maNguoiDD = "";
    String soNguoiO = "";
    String maNguoiO = "";
    String maPhong;
    String ngayThue = "";
    String ngayTra = "";
    String thanhTien = "";
    String maSuDung = "DV1, DV2";

    public boolean checkNull() {
        maNguoiDD = txt_MaNguoiDungTen.getText();
        soNguoiO = txt_SoNguoiO.getText();
        maNguoiO = txt_MaKTO.getText();
        maPhong = txt_MaPhong.getText();
        ngayThue = txt_NgayThue.getText();
        ngayTra = txt_NgayHetHan.getText();
        thanhTien = txt_ThanhTien.getText();
        String gioiHan = "\\d{1}";
        String giaTien = "\\d{6,8}";
        if (maNguoiDD.equals("")) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống mã người đại diện");
            return false;
        } else if (maNguoiO.equals("")) {
            if (!soNguoiO.equals("")) {
                JOptionPane.showMessageDialog(this, "Không bỏ trống mã khách trọ");
                return false;
            }
        } else if (soNguoiO.equals("")) {
            if (!maNguoiO.equals("")) {
                JOptionPane.showMessageDialog(this, "Không bỏ trống số người ở");
                return false;
            }
        } else if (!soNguoiO.matches(gioiHan)) {
            JOptionPane.showMessageDialog(this, "Tối đa 9 người ở!!");
            return false;
        } else if (maPhong.equals("")) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống mã phòng");
            return false;
        } else if (ngayThue.equals("")) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống ngày thuê");
            return false;
        } else if (ngayTra.equals("")) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống ngày trả");
            return false;
        } else if (thanhTien.equals("")) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống thành tiền");
            return false;
        } else if (!thanhTien.matches(giaTien)) {
            JOptionPane.showMessageDialog(this, "Giá không hợp lệ!! Tiền từ: 100000 -> 99999999");
            return false;
        }
        return true;
    }

    public boolean checkRepeat(String maKTCheck) {
        KhachTroDAO DAO = new KhachTroDAO();
        List<KhachTro> list = DAO.select();
        boolean trung = false;
        for (int i = 0; i < list.size(); i++) {
            if (!maKTCheck.equalsIgnoreCase(list.get(i).getMaKT())) {
                JOptionPane.showMessageDialog(this, "Không có mã khách trọ này!!");
                trung = false;
                return trung;
            } else {
                trung = true;
            }
        }
        return trung;
    }

    public void clearForm() {
        txt_MaHD.setText("");
        txt_MaHDDV.setText("");
        txt_MaKTO.setText("");
        txt_MaNguoiDungTen.setText("");
        txt_MaPhong.setText("");
        txt_NgayHetHan.setText("");
        txt_NgayThue.setText("");
        txt_SoNguoiO.setText("");
        txt_ThanhTien.setText("");
        txt_NguoiTao.setText(username);
    }

    public void loadTableDichVu() {
        modelDV = (DefaultTableModel) tblBangDV.getModel();
        modelDV.setRowCount(0);
        try {
            List<DichVu> listDV = dicVuDAO.select();
            for (DichVu dv : listDV) {
                modelDV.addRow(new Object[]{dv.getMaDV(), dv.getTenDV(), dv.getGia()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi đổ dữ liệu vào bảng" + e);
            e.printStackTrace();
        }
    }

    public void loadToTableHopDong() {
        model = (DefaultTableModel) tbl_HopDong.getModel();
        model.setRowCount(0);
        try {
            listHopDong = hopDongDAO.select();
            for (HopDong hd : listHopDong) {
                model.addRow(new Object[]{
                    hd.getMaHD(),
                    hd.getMaNguoiDaiDien(),
                    hd.getSoNguoi(),
                    hd.getMaNguoiO(),
                    hd.getMaHDDV(),
                    hd.getMaPhong(),
                    hd.getNgayThue(),
                    hd.getNgayTra(),
                    hd.getThanhTien(),
                    hd.getTrangthai(),
                    hd.getNguoiTao(),
                    hd.getNgayThanhToan()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi đổ dữ liệu vào bảng " + e);
            e.printStackTrace();
        }
    }

    public void loadToTableHopDongByMAPhong() {
        model = (DefaultTableModel) tbl_HopDong.getModel();
        model.setRowCount(0);
        try {
            List<HopDong> listFHD = hopDongDAO.findByMaPhong(txt_Search.getText());
            for (HopDong hd : listFHD) {
                model.addRow(new Object[]{
                    hd.getMaHD(),
                    hd.getMaNguoiDaiDien(),
                    hd.getSoNguoi(),
                    hd.getMaNguoiO(),
                    hd.getMaHDDV(),
                    hd.getMaPhong(),
                    hd.getNgayThue(),
                    hd.getNgayTra(),
                    hd.getThanhTien(),
                    hd.getTrangthai(),
                    hd.getNguoiTao(),
                    hd.getNgayThanhToan()
                });
            }
            if (listFHD.size() > 0) {
                JOptionPane.showMessageDialog(this, "Tìm thành công");
                index = 0;
                tbl_HopDong.setRowSelectionInterval(index, index);
                showDetail();
            } else {
                loadToTableHopDong();
                JOptionPane.showMessageDialog(this, "Không có thông tin mã phòng này");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi đổ dữ liệu vào bảng " + e);
            e.printStackTrace();
        }
    }

    public void showDetail() {
        HopDong hd = listHopDong.get(index);
        txt_MaHD.setText(hd.getMaHD());
        txt_MaNguoiDungTen.setText(hd.getMaNguoiDaiDien());
        txt_SoNguoiO.setText(hd.getSoNguoi() + "");
        txt_MaKTO.setText(hd.getMaNguoiO());
        txt_MaHDDV.setText(hd.getMaHDDV() + "");
        txt_MaPhong.setText(hd.getMaPhong());
        txt_NgayThue.setText(hd.getNgayThue());
        txt_NgayHetHan.setText(hd.getNgayTra());
        txt_ThanhTien.setText(hd.getThanhTien() + "");
        String status = hd.getTrangthai();
        if (status.equalsIgnoreCase("Chưa thanh toán")) {
            rb_ChuaThanhToan.setSelected(true);
            rb_ChuaThanhToan.setSelected(false);
        } else {
            rb_ChuaThanhToan.setSelected(false);
            rb_ChuaThanhToan.setSelected(true);
        }
        txt_NguoiTao.setText(hd.getNguoiTao());
        List<HoaDonDichVu> listHDDV = DAOHDDV.findMaHDDVByMaPhong(txt_MaPhong.getText());
        txt_MaDV.setText(listHDDV.get(0).getSuDung());
    }

    public void insertHopDong() {
        if (checkNull() && checkRepeat(txt_MaNguoiDungTen.getText())) {
            if (checkDate.checkBirthday(txt_NgayHetHan.getText()) && checkDate.checkBirthday(txt_NgayThue.getText())) {
                HoaDonDichVu objHDDV = new HoaDonDichVu(0, txt_MaDV.getText(), txt_MaPhong.getText(), 0, "Chưa thanh toán", username);
                DAOHDDV.insert(objHDDV);
                List<HoaDonDichVu> listHDDV = new ArrayList<>();
                listHDDV = DAOHDDV.findMaHDDVByMaPhong(txt_MaPhong.getText());
                for (HoaDonDichVu hddv : listHDDV) {
                    txt_MaHDDV.setText(hddv.getMaHDDV() + "");
                }
                if (txt_SoNguoiO.getText().equals("")) {
                    soNguoiO = "0";
                }
                if (txt_MaKTO.getText().equals("")) {
                    maNguoiO = "";
                }
                String status = "";
                String ngayTT = "";
                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                if (rb_ChuaThanhToan.isSelected()) {
                    status = "Chưa thanh toán";
                } else if (rb_DaThanhToan.isSelected()) {
                    status = "Đã thanh toán";
                    ngayTT = date + "";
                }
                HopDong objHD = new HopDong("0",
                        txt_MaNguoiDungTen.getText(),
                        Integer.parseInt(soNguoiO),
                        txt_MaKTO.getText(),
                        Integer.parseInt(txt_MaHDDV.getText()),
                        txt_MaPhong.getText(),
                        txt_NgayThue.getText(),
                        txt_NgayHetHan.getText(),
                        Integer.parseInt(thanhTien),
                        status,
                        username,
                        "DV1",
                        ngayTT);
                hopDongDAO.insert(objHD);
                loadToTableHopDong();
                JOptionPane.showMessageDialog(this, "Thêm thành công!!");
            } else {
                JOptionPane.showMessageDialog(this, "Sai định dạng ngày: YYYY-MM-DD ");
            }
        }
    }

    public void updateHopDong() {
        try {
            if (checkNull()) {
                if (checkDate.checkBirthday(txt_NgayHetHan.getText()) && checkDate.checkBirthday(txt_NgayThue.getText())) {
                    long millis = System.currentTimeMillis();
                    java.sql.Date date = new java.sql.Date(millis);
                    DAOHDDV.update(new HoaDonDichVu(0, txt_MaDV.getText(), maPhong, 0, "Chưa thanh toán", username));
                    String status = "";
                    String ngayTT = "";
                    if (rb_ChuaThanhToan.isSelected()) {
                        status = "Chưa thanh toán";
                    } else if (rb_DaThanhToan.isSelected()) {
                        status = "Đã Thanh Toán";
                        ngayTT = date + "";
                    }
                    if (txt_SoNguoiO.getText().equals("")) {
                        soNguoiO = "0";
                    }
                    if (txt_MaKTO.getText().equals("")) {
                        maNguoiO = "";
                    }
                    HopDong objHopDong = new HopDong(txt_MaHD.getText(), null, Integer.parseInt(txt_SoNguoiO.getText()), txt_MaKTO.getText(), 0, txt_MaPhong.getText(), null, txt_NgayHetHan.getText(), Integer.parseInt(txt_ThanhTien.getText()), status, null, null, null);
                    hopDongDAO.update(objHopDong);
                    loadToTableHopDong();
                    JOptionPane.showMessageDialog(this, "Update thành công");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi update" + e);
            e.printStackTrace();
        }
    }

    public void deleteHopDong() {
        int ask = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa");
        if (ask == JOptionPane.YES_OPTION) {
            HopDong objHopDong = new HopDong(txt_MaHD.getText(), null, Integer.parseInt(txt_SoNguoiO.getText()), txt_MaKTO.getText(), 0, txt_MaPhong.getText(), null, txt_NgayHetHan.getText(), Integer.parseInt(txt_ThanhTien.getText()), null, null, null, null);
            hopDongDAO.delete(objHopDong);
            JOptionPane.showMessageDialog(this, "Xóa thành công!!");
            loadToTableHopDong();
            if (listHopDong.size() > 0) {
                if (index == listHopDong.size()) {
                    index--;
                }
                showDetail();
            } else {
                clearForm();
            }
        }
    }

    public QLHopDong(String userName, String roles, String maPhong) {
        initComponents();
        loadTableDichVu();
        loadToTableHopDong();
        this.username = userName;
        this.roles = roles;
        this.maPhong = maPhong;
        if (this.roles.equalsIgnoreCase("NV")) {
            btn_Delete.setEnabled(false);
            btn_Update.setEnabled(false);
        }
        if (maPhong.equals("")) {
            if (listHopDong.size() >= 0) {
                index = 0;
                showDetail();
            }
        } else {
            clearForm();
            txt_MaPhong.setText(maPhong);
            txt_NguoiTao.getText();
        }

        System.out.println(userName);
        System.out.println(roles);

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
        PNL_Form = new javax.swing.JPanel();
        lbl_MaHD = new javax.swing.JLabel();
        txt_MaHD = new javax.swing.JTextField();
        lbl_MaNguoiDungTen = new javax.swing.JLabel();
        txt_MaNguoiDungTen = new javax.swing.JTextField();
        lbl_SoNguoiO = new javax.swing.JLabel();
        txt_SoNguoiO = new javax.swing.JTextField();
        lbl_MaND = new javax.swing.JLabel();
        txt_MaKTO = new javax.swing.JTextField();
        lbl_MaDV = new javax.swing.JLabel();
        lbl_MaPhong = new javax.swing.JLabel();
        txt_MaPhong = new javax.swing.JTextField();
        lbl_NgayThue = new javax.swing.JLabel();
        lbl_NgayHetHan = new javax.swing.JLabel();
        txt_NgayThue = new javax.swing.JTextField();
        txt_NgayHetHan = new javax.swing.JTextField();
        lbl_ThanhTien = new javax.swing.JLabel();
        lbl_TrangThai = new javax.swing.JLabel();
        txt_ThanhTien = new javax.swing.JTextField();
        rb_DaThanhToan = new javax.swing.JRadioButton();
        rb_ChuaThanhToan = new javax.swing.JRadioButton();
        btn_save = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();
        btn_New = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_HopDong = new javax.swing.JTable();
        pnl_Search = new javax.swing.JPanel();
        txt_Search = new javax.swing.JTextField();
        btn_Search = new javax.swing.JButton();
        btn_ThanhToan = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_MaHDDV = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_NguoiTao = new javax.swing.JTextField();
        txt_MaDV = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBangDV = new javax.swing.JTable();
        btnChonMaDV = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("QUẢN LÝ HỢP ĐỒNG");

        lbl_Title.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl_Title.setForeground(new java.awt.Color(0, 0, 153));
        lbl_Title.setText("HỢP ĐỒNG THUÊ NHÀ TRỌ");

        javax.swing.GroupLayout pnl_TitleLayout = new javax.swing.GroupLayout(pnl_Title);
        pnl_Title.setLayout(pnl_TitleLayout);
        pnl_TitleLayout.setHorizontalGroup(
            pnl_TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_TitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Title)
                .addContainerGap(1495, Short.MAX_VALUE))
        );
        pnl_TitleLayout.setVerticalGroup(
            pnl_TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_TitleLayout.createSequentialGroup()
                .addComponent(lbl_Title)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(pnl_Title, java.awt.BorderLayout.PAGE_START);

        lbl_MaHD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_MaHD.setText("Mã hợp đồng");

        txt_MaHD.setEditable(false);

        lbl_MaNguoiDungTen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_MaNguoiDungTen.setText("Mã người đứng tên");

        lbl_SoNguoiO.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_SoNguoiO.setText("Số người ở ghép");

        lbl_MaND.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_MaND.setText("Mã Khách Trọ");

        lbl_MaDV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_MaDV.setText("Mã dịch vụ sử dụng");

        lbl_MaPhong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_MaPhong.setText("Mã phòng");

        lbl_NgayThue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_NgayThue.setText("Ngày thuê");

        lbl_NgayHetHan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_NgayHetHan.setText("Ngày hết hạn");

        lbl_ThanhTien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_ThanhTien.setText("Thành tiền");

        lbl_TrangThai.setText("Trạng thái thanh toán Hợp đồng :");

        buttonGroup1.add(rb_DaThanhToan);
        rb_DaThanhToan.setSelected(true);
        rb_DaThanhToan.setText("Đã thanh toán");

        buttonGroup1.add(rb_ChuaThanhToan);
        rb_ChuaThanhToan.setText("Chưa thanh toán");

        btn_save.setText("Save");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
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

        tbl_HopDong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hợp Đồng", "Mã Người Đứng Tên", "Số Người Ở Ghép", "Mã Khách Trọ", "Mã Hóa Đơn DV", "Mã Phòng", "Ngày Thuê", "Ngày Hết Hạn", "Thành Tiền", "Trạng Thái", "Người Tạo", "Ngày Thanh Toán DV"
            }
        ));
        tbl_HopDong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_HopDongMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_HopDong);

        pnl_Search.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

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
            .addGroup(pnl_SearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_Search)
                .addGap(18, 18, 18)
                .addComponent(btn_Search)
                .addContainerGap())
        );
        pnl_SearchLayout.setVerticalGroup(
            pnl_SearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_SearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_SearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Search))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_ThanhToan.setText("Thanh Toán Tiền Dịch Vụ");
        btn_ThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThanhToanActionPerformed(evt);
            }
        });

        jLabel1.setText("Mã Hóa Đơn Dịch Vụ");

        txt_MaHDDV.setEditable(false);

        jLabel2.setText("Mã Người Tạo");

        txt_NguoiTao.setEditable(false);

        tblBangDV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã dịch vụ", "Tên dịch vụ", "Giá dịch vụ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblBangDV);

        btnChonMaDV.setText("Chọn");
        btnChonMaDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonMaDVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PNL_FormLayout = new javax.swing.GroupLayout(PNL_Form);
        PNL_Form.setLayout(PNL_FormLayout);
        PNL_FormLayout.setHorizontalGroup(
            PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNL_FormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PNL_FormLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(PNL_FormLayout.createSequentialGroup()
                        .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnl_Search, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(PNL_FormLayout.createSequentialGroup()
                                .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PNL_FormLayout.createSequentialGroup()
                                        .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_SoNguoiO)
                                            .addComponent(txt_MaHD)
                                            .addGroup(PNL_FormLayout.createSequentialGroup()
                                                .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lbl_MaDV)
                                                    .addComponent(lbl_MaHD)
                                                    .addComponent(lbl_SoNguoiO))
                                                .addGap(0, 681, Short.MAX_VALUE)))
                                        .addGap(133, 133, 133))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PNL_FormLayout.createSequentialGroup()
                                        .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(PNL_FormLayout.createSequentialGroup()
                                                .addComponent(jScrollPane2)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnChonMaDV))
                                            .addComponent(txt_MaDV))
                                        .addGap(139, 139, 139)))
                                .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_MaPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
                                    .addComponent(txt_NgayHetHan)
                                    .addComponent(txt_MaKTO)
                                    .addComponent(txt_MaNguoiDungTen)
                                    .addGroup(PNL_FormLayout.createSequentialGroup()
                                        .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbl_MaNguoiDungTen)
                                            .addComponent(lbl_NgayHetHan)
                                            .addComponent(lbl_MaND)
                                            .addComponent(lbl_MaPhong))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(18, 18, 18))
                    .addGroup(PNL_FormLayout.createSequentialGroup()
                        .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PNL_FormLayout.createSequentialGroup()
                                .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PNL_FormLayout.createSequentialGroup()
                                        .addComponent(lbl_NgayThue)
                                        .addGap(0, 718, Short.MAX_VALUE))
                                    .addGroup(PNL_FormLayout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_MaHDDV)))
                                .addGap(133, 133, 133))
                            .addGroup(PNL_FormLayout.createSequentialGroup()
                                .addComponent(lbl_TrangThai)
                                .addGap(47, 47, 47)
                                .addComponent(rb_DaThanhToan)
                                .addGap(18, 18, 18)
                                .addComponent(rb_ChuaThanhToan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PNL_FormLayout.createSequentialGroup()
                                .addComponent(txt_NgayThue)
                                .addGap(139, 139, 139)))
                        .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PNL_FormLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txt_NguoiTao))
                            .addComponent(txt_ThanhTien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                            .addGroup(PNL_FormLayout.createSequentialGroup()
                                .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PNL_FormLayout.createSequentialGroup()
                                        .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(btn_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(btn_New, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addComponent(btn_ThanhToan))
                                    .addComponent(lbl_ThanhTien))
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        PNL_FormLayout.setVerticalGroup(
            PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNL_FormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_MaHD)
                    .addComponent(lbl_MaNguoiDungTen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_MaHD, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txt_MaNguoiDungTen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_SoNguoiO)
                    .addComponent(lbl_MaND))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_SoNguoiO, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txt_MaKTO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_MaPhong)
                    .addComponent(lbl_MaDV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_MaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_MaDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_NgayHetHan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PNL_FormLayout.createSequentialGroup()
                        .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_NgayHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnChonMaDV))
                        .addGap(80, 80, 80)
                        .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_ThanhTien)
                            .addComponent(lbl_NgayThue))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_ThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_NgayThue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PNL_FormLayout.createSequentialGroup()
                                .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_TrangThai)
                                    .addComponent(rb_DaThanhToan)
                                    .addComponent(rb_ChuaThanhToan))
                                .addGap(8, 8, 8))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PNL_FormLayout.createSequentialGroup()
                                .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txt_NguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)))
                        .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(txt_MaHDDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(PNL_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_ThanhToan)
                                    .addComponent(btn_New)
                                    .addComponent(btn_Delete))
                                .addComponent(btn_save)
                                .addComponent(btn_Update)))
                        .addGap(29, 29, 29)
                        .addComponent(pnl_Search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PNL_FormLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txt_NgayHetHan, txt_NguoiTao});

        PNL_FormLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txt_MaHDDV, txt_ThanhTien});

        PNL_FormLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txt_MaDV, txt_SoNguoiO});

        getContentPane().add(PNL_Form, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        try {
            insertHopDong();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps! Error: " + e);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void tbl_HopDongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_HopDongMouseClicked
        try {
            index = tbl_HopDong.getSelectedRow();
            if (index >= 0) {
                showDetail();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps! Error: " + e);
        }
    }//GEN-LAST:event_tbl_HopDongMouseClicked

    private void btn_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SearchActionPerformed
        try {
            loadToTableHopDongByMAPhong();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps! Error: " + e);
        }
    }//GEN-LAST:event_btn_SearchActionPerformed

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        try {
            this.updateHopDong();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps! Error: " + e);
        }
    }//GEN-LAST:event_btn_UpdateActionPerformed

    private void btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteActionPerformed
        try {
            deleteHopDong();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps! Error: " + e);
        }
    }//GEN-LAST:event_btn_DeleteActionPerformed

    private void btn_NewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NewActionPerformed
        try {
            clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps! Error: " + e);
        }
    }//GEN-LAST:event_btn_NewActionPerformed

    private void btn_ThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThanhToanActionPerformed
        try {
            List<HoaDonDichVu> listHDDV = DAOHDDV.findMaHDDVByMaPhong(txt_MaPhong.getText());
            maSuDung = listHDDV.get(0).getSuDung();
            ThanhToan ttdv = new ThanhToan(username, txt_MaPhong.getText(), txt_MaHDDV.getText(), maSuDung);
            FrMain.main.getDsk_main().add(ttdv);
            ttdv.show();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps! Error: " + e);
        }

    }//GEN-LAST:event_btn_ThanhToanActionPerformed

    private void btnChonMaDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonMaDVActionPerformed
        try {
            int idx = tblBangDV.getSelectedRow();
            if (idx >= 0) {
                if (txt_MaDV.getText().equals("")) {
                    txt_MaDV.setText(modelDV.getValueAt(idx, 0).toString());
                } else if (!txt_MaDV.getText().equals("")) {
                    txt_MaDV.setText(txt_MaDV.getText() + ", " + modelDV.getValueAt(idx, 0).toString());
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps! Error: " + e);
        }
    }//GEN-LAST:event_btnChonMaDVActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PNL_Form;
    private javax.swing.JButton btnChonMaDV;
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_New;
    private javax.swing.JButton btn_Search;
    private javax.swing.JButton btn_ThanhToan;
    private javax.swing.JButton btn_Update;
    private javax.swing.JButton btn_save;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_MaDV;
    private javax.swing.JLabel lbl_MaHD;
    private javax.swing.JLabel lbl_MaND;
    private javax.swing.JLabel lbl_MaNguoiDungTen;
    private javax.swing.JLabel lbl_MaPhong;
    private javax.swing.JLabel lbl_NgayHetHan;
    private javax.swing.JLabel lbl_NgayThue;
    private javax.swing.JLabel lbl_SoNguoiO;
    private javax.swing.JLabel lbl_ThanhTien;
    private javax.swing.JLabel lbl_Title;
    private javax.swing.JLabel lbl_TrangThai;
    private javax.swing.JPanel pnl_Search;
    private javax.swing.JPanel pnl_Title;
    private javax.swing.JRadioButton rb_ChuaThanhToan;
    private javax.swing.JRadioButton rb_DaThanhToan;
    private javax.swing.JTable tblBangDV;
    private javax.swing.JTable tbl_HopDong;
    private javax.swing.JTextField txt_MaDV;
    private javax.swing.JTextField txt_MaHD;
    private javax.swing.JTextField txt_MaHDDV;
    private javax.swing.JTextField txt_MaKTO;
    private javax.swing.JTextField txt_MaNguoiDungTen;
    private javax.swing.JTextField txt_MaPhong;
    private javax.swing.JTextField txt_NgayHetHan;
    private javax.swing.JTextField txt_NgayThue;
    private javax.swing.JTextField txt_NguoiTao;
    private javax.swing.JTextField txt_Search;
    private javax.swing.JTextField txt_SoNguoiO;
    private javax.swing.JTextField txt_ThanhTien;
    // End of variables declaration//GEN-END:variables
}
