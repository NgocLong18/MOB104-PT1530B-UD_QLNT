/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.ui;

import com.sample.DAO.DichVuDAO;
import com.sample.DAO.HoaDonChiTietDAO;
import com.sample.DAO.HoaDonDichVuDAO;
import com.sample.model.DichVu;
import com.sample.model.HoaDonDichVu;
import com.sample.model.HoaDonChiTiet;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author User
 */
public class ThanhToan extends javax.swing.JInternalFrame {

    String userName;
    String maPhong;
    String maHDDV;
    String DVSD;

    float thanhTienDV;
    float tongTien;
    String donViTinh;
    DichVuDAO dichVuDAO = new DichVuDAO();
    HoaDonDichVuDAO DAO_HDDV = new HoaDonDichVuDAO();
    HoaDonChiTietDAO DAO_HDCT = new HoaDonChiTietDAO();
    DefaultTableModel model;
    List<HoaDonChiTiet> listHDCT = new ArrayList<>();

    public void LoadToCombo() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbx_DVSD.getModel();
        model.removeAllElements();
        try {
            List<DichVu> list = dichVuDAO.select();
            for (DichVu dv : list) {
                model.addElement(dv);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    public void ExportFileExxcel() {
        FileOutputStream fos = null;
        XSSFWorkbook exelWorkbook = null;
        BufferedOutputStream bfos = null;
        try {
//            JFileChooser exelFileChoser = new JFileChooser("D:\\");
//            exelFileChoser.setDialogTitle("Save as ..");
//            FileNameExtensionFilter fnef = new FileNameExtensionFilter("Files", "xls", "xlsx", "slsm");
//            exelFileChoser.setFileFilter(fnef);
//            int chooser = exelFileChoser.showSaveDialog(null);
//            if (chooser == JFileChooser.APPROVE_OPTION) {
            exelWorkbook = new XSSFWorkbook();
            XSSFSheet excelSheet = exelWorkbook.createSheet("HDCT");
            for (int i = 0; i < model.getRowCount(); i++) {
                Sheet firstSheet = exelWorkbook.getSheetAt(0);
                XSSFRow exCellRow = excelSheet.createRow(i);
                for (int j = 0; j < model.getColumnCount(); j++) {
                    XSSFCell eCell = exCellRow.createCell(j);
                    eCell.setCellValue(model.getValueAt(i, j).toString());
                }
            }
//            }
//            if (chooser == JFileChooser.CANCEL_OPTION) {
//                JOptionPane.showMessageDialog(this, "Lưu không thành công");
//                return;
//            }
            fos = new FileOutputStream("D:\\" + txt_MaPhong.getText() + ".xlsx");
            bfos = new BufferedOutputStream(fos);
            exelWorkbook.write(bfos);
            exelWorkbook.close();
            bfos.close();
            fos.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "lỗi: " + e);
        }
    }

    public void loadToTable() {
        model = (DefaultTableModel) tbl_ThanhToan.getModel();
//        model.setRowCount(0);
        try {
            //            listHDCT = DAO_HDCT.select();
            //            for (HoaDonChiTiet hdct : listHDCT) {
            //                model.addRow(new Object[]{
            //                    hdct.getMaDVSD(),
            //                    hdct.getMaPhong(),
            //                    hdct.getThanhTien(),
            //                    hdct.getThoiGian(),
            //                    hdct.getMaHDDV(),});
            //            }
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            DichVu dv = (DichVu) cbx_DVSD.getSelectedItem();
            String maDV = "";
            maDV = String.valueOf(dv.getMaDV());
            model.addRow(new Object[]{maDV, txt_MaPhong.getText(), lbl_ShowTien.getText(), date, txt_MaHDDV.getText()});
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi đổ dữ liệu vào bảng " + e);
            e.printStackTrace();
        }

    }

    public void clearForm() {
        txt_DVTinh.setText("0");
        btnThuTien.setEnabled(false);

    }

    public boolean checkGia() {
        donViTinh = txt_DVTinh.getText();
        String inputSo = "\\d{1,4}";
        if (!donViTinh.matches(inputSo)) {
            JOptionPane.showMessageDialog(this, "Số không hợp lệ!! Giá từ 1->9999");
            return false;
        }
        return true;
    }

    public void insertHoaDonChiTiet() {
        if (checkGia()) {
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            DichVu dv = (DichVu) cbx_DVSD.getSelectedItem();
            String maDV = "";
            maDV = String.valueOf(dv.getMaDV());
            HoaDonChiTiet objHDCT = new HoaDonChiTiet(0,maDV, txt_MaPhong.getText(), thanhTienDV, date + "", Integer.parseInt(txt_MaHDDV.getText()));
            DAO_HDCT.insert(objHDCT);
            btnThuTien.setEnabled(true);
        }
    }

    public ThanhToan(String userName, String maPhong, String maHDDV, String DVSD) {
        initComponents();
        LoadToCombo();
//        loadToTable();
        btnThuTien.setEnabled(false);
        this.userName = userName;
        this.maPhong = maPhong;
        this.maHDDV = maHDDV;
        this.DVSD = DVSD;
        if (maPhong.equals("") && maHDDV.equals("") && DVSD.equals("")) {
            clearForm();
        } else {
            txt_MaPhong.setText(maPhong);
            txt_MaHDDV.setText(maHDDV);
            txt_DVSD.setText(DVSD);
        }
//        System.out.println(userName);
//        System.out.println(maPhong);
//        System.out.println(this.maHDDV);
//        System.out.println(soNguoiO);
//        System.out.println(DVSD);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_Title = new javax.swing.JPanel();
        lbl_ThanhToan = new javax.swing.JLabel();
        pnl_Form = new javax.swing.JPanel();
        lbl_MaPhong = new javax.swing.JLabel();
        lbl_TienNuoc = new javax.swing.JLabel();
        btnThuTien = new javax.swing.JButton();
        lbl_ShowTien = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_MaHDDV = new javax.swing.JTextField();
        btn_ThanhTien = new javax.swing.JButton();
        txt_MaPhong = new javax.swing.JTextField();
        cbx_DVSD = new javax.swing.JComboBox<>();
        txt_GiaDVSD = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_ThanhToan = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_DVTinh = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_DVSD = new javax.swing.JTextField();
        txt_TongTien = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("THANH TOÁN");

        lbl_ThanhToan.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl_ThanhToan.setForeground(new java.awt.Color(0, 0, 153));
        lbl_ThanhToan.setText("THANH TOÁN");

        javax.swing.GroupLayout pnl_TitleLayout = new javax.swing.GroupLayout(pnl_Title);
        pnl_Title.setLayout(pnl_TitleLayout);
        pnl_TitleLayout.setHorizontalGroup(
            pnl_TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_TitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_ThanhToan)
                .addContainerGap(325, Short.MAX_VALUE))
        );
        pnl_TitleLayout.setVerticalGroup(
            pnl_TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_TitleLayout.createSequentialGroup()
                .addComponent(lbl_ThanhToan)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(pnl_Title, java.awt.BorderLayout.PAGE_START);

        lbl_MaPhong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_MaPhong.setText("Mã phòng");

        lbl_TienNuoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_TienNuoc.setText("Dịch vụ sử dụng");

        btnThuTien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThuTien.setText("Thu Tiền");
        btnThuTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThuTienActionPerformed(evt);
            }
        });

        lbl_ShowTien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_ShowTien.setForeground(new java.awt.Color(204, 0, 0));
        lbl_ShowTien.setText("...");
        lbl_ShowTien.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Mã hóa đơn dịch vụ");

        txt_MaHDDV.setEditable(false);

        btn_ThanhTien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_ThanhTien.setText("Tính tiền dịch vụ");
        btn_ThanhTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThanhTienActionPerformed(evt);
            }
        });

        txt_MaPhong.setEditable(false);
        txt_MaPhong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cbx_DVSD.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_DVSDItemStateChanged(evt);
            }
        });

        txt_GiaDVSD.setEditable(false);
        txt_GiaDVSD.setText("....");
        txt_GiaDVSD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_GiaDVSDActionPerformed(evt);
            }
        });

        tbl_ThanhToan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Dịch Vụ", "Mã Phòng", "Thành Tiền", "Ngày Thu", "Mã HDDV"
            }
        ));
        jScrollPane1.setViewportView(tbl_ThanhToan);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Giá");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Đơn vị tính");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Dịch vụ sử dụng");

        txt_DVSD.setEditable(false);

        txt_TongTien.setEditable(false);
        txt_TongTien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tổng tiền dịch vụ");

        javax.swing.GroupLayout pnl_FormLayout = new javax.swing.GroupLayout(pnl_Form);
        pnl_Form.setLayout(pnl_FormLayout);
        pnl_FormLayout.setHorizontalGroup(
            pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_FormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_FormLayout.createSequentialGroup()
                        .addComponent(lbl_MaPhong)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_MaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txt_MaHDDV))
                    .addGroup(pnl_FormLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txt_DVTinh))
                    .addGroup(pnl_FormLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txt_DVSD))
                    .addGroup(pnl_FormLayout.createSequentialGroup()
                        .addComponent(cbx_DVSD, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_GiaDVSD, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_FormLayout.createSequentialGroup()
                        .addComponent(lbl_TienNuoc)
                        .addGap(211, 211, 211)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnl_FormLayout.createSequentialGroup()
                        .addComponent(btn_ThanhTien)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_ShowTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_FormLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_FormLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(44, 44, 44)
                        .addComponent(txt_TongTien)
                        .addGap(18, 18, 18)
                        .addComponent(btnThuTien)))
                .addContainerGap())
        );
        pnl_FormLayout.setVerticalGroup(
            pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_FormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_MaHDDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_MaPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_MaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_DVSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_TienNuoc)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbx_DVSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_GiaDVSD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txt_DVTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_ThanhTien)
                    .addComponent(lbl_ShowTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(btnThuTien))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );

        pnl_FormLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbx_DVSD, jLabel2, jLabel4, lbl_MaPhong, txt_DVSD, txt_MaHDDV, txt_MaPhong});

        pnl_FormLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnThuTien, btn_ThanhTien, jLabel5, lbl_ShowTien, txt_TongTien});

        pnl_FormLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel3, txt_DVTinh, txt_GiaDVSD});

        getContentPane().add(pnl_Form, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThuTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThuTienActionPerformed
        try {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn thu tiền?");
            if (confirm == JOptionPane.YES_OPTION) {
                HoaDonDichVu objHDDV = new HoaDonDichVu(0, txt_DVSD.getText(), txt_MaPhong.getText(), Float.parseFloat(txt_TongTien.getText()), "Đã thanh toán", userName);
                DAO_HDDV.update(objHDDV);
                ExportFileExxcel();
                JOptionPane.showMessageDialog(this, "Thu tiền thành công");
                this.dispose();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: " + e);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThuTienActionPerformed

    private void btn_ThanhTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThanhTienActionPerformed
        try {
            if (checkGia()) {
                thanhTienDV = Float.parseFloat(txt_DVTinh.getText()) * Float.parseFloat(txt_GiaDVSD.getText());
                lbl_ShowTien.setText(thanhTienDV + "");
                if (txt_TongTien.getText().equals("")) {
                    txt_TongTien.setText(thanhTienDV + "");

                } else {
                    txt_TongTien.setText(Float.parseFloat(txt_TongTien.getText()) + thanhTienDV + "");
                }
                loadToTable();
                insertHoaDonChiTiet();
                btnThuTien.setEnabled(true);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi tính toán " + e);
        }
    }//GEN-LAST:event_btn_ThanhTienActionPerformed

    private void txt_GiaDVSDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_GiaDVSDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_GiaDVSDActionPerformed

    private void cbx_DVSDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_DVSDItemStateChanged
        try {
            DichVu dv = (DichVu) cbx_DVSD.getSelectedItem();
            txt_GiaDVSD.setText(String.valueOf(dv.getGia()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e);
        }
    }//GEN-LAST:event_cbx_DVSDItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThuTien;
    private javax.swing.JButton btn_ThanhTien;
    private javax.swing.JComboBox<String> cbx_DVSD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_MaPhong;
    private javax.swing.JLabel lbl_ShowTien;
    private javax.swing.JLabel lbl_ThanhToan;
    private javax.swing.JLabel lbl_TienNuoc;
    private javax.swing.JPanel pnl_Form;
    private javax.swing.JPanel pnl_Title;
    private javax.swing.JTable tbl_ThanhToan;
    private javax.swing.JTextField txt_DVSD;
    private javax.swing.JTextField txt_DVTinh;
    private javax.swing.JTextField txt_GiaDVSD;
    private javax.swing.JTextField txt_MaHDDV;
    private javax.swing.JTextField txt_MaPhong;
    private javax.swing.JTextField txt_TongTien;
    // End of variables declaration//GEN-END:variables
}
