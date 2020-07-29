/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.ui;

import com.sample.model.NhanVien;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.Timer;

public class FrMain extends javax.swing.JFrame {

    Timer t;
    String roles;
    String userName;
    public static FrMain main;

    public FrMain() {
        main = this;
        initComponents();
    }

    public FrMain(String roles, String userName, JButton btn_Exit, JButton btn_LogOut, JButton btn_QLDichVu, JButton btn_QLHopDong, JButton btn_QLNguoiTro, JButton btn_QLNhanVien, JButton btn_QLPhong, JDesktopPane dsk_main, JLabel jLabel1, JMenuBar jMenuBar1, JMenuItem jMenuItem1, JPanel jPanel1, JPanel jPanel3, JPopupMenu.Separator jSeparator1, JToolBar.Separator jSeparator2, JToolBar.Separator jSeparator4, JPopupMenu.Separator jSeparator5, JPopupMenu.Separator jSeparator6, JToolBar.Separator jSeparator7, JToolBar jToolBar2, JMenuItem mni_ChangePassword, JMenuItem mni_Exit, JMenuItem mni_LogOut, JMenuItem mni_QLDichVu, JMenuItem mni_QLHoaDon, JMenuItem mni_QLHopDong, JMenuItem mni_QLKhachTro, JMenuItem mni_QLNhanVien, JMenuItem mni_QLPhong, JMenu mnu_HeThong, JMenu mnu_QuanLy) throws HeadlessException {
        this.roles = roles;
        this.userName = userName;
        this.btn_Exit = btn_Exit;
        this.btn_LogOut = btn_LogOut;
        this.btn_QLDichVu = btn_QLDichVu;
        this.btn_QLHopDong = btn_QLHopDong;
        this.btn_QLNguoiTro = btn_QLNguoiTro;
        this.btn_QLNhanVien = btn_QLNhanVien;
        this.btn_QLPhong = btn_QLPhong;
        this.dsk_main = dsk_main;
        this.lbl_Time = jLabel1;
        this.jMenuBar1 = jMenuBar1;
        this.jMenuItem1 = jMenuItem1;
        this.jPanel1 = jPanel1;
        this.jPanel3 = jPanel3;
        this.jSeparator1 = jSeparator1;
        this.jSeparator2 = jSeparator2;
        this.jSeparator4 = jSeparator4;
        this.jSeparator5 = jSeparator5;
        this.jSeparator6 = jSeparator6;
        this.jSeparator7 = jSeparator7;
        this.jToolBar2 = jToolBar2;
        this.mni_ChangePassword = mni_ChangePassword;
        this.mni_Exit = mni_Exit;
        this.mni_LogOut = mni_LogOut;
        this.mni_QLDichVu = mni_QLDichVu;
        this.mni_HDCT = mni_QLHoaDon;
        this.mni_QLHopDong = mni_QLHopDong;
        this.mni_QLKhachTro = mni_QLKhachTro;
        this.mni_QLNhanVien = mni_QLNhanVien;
        this.mni_QLPhong = mni_QLPhong;
        this.mnu_HeThong = mnu_HeThong;
        this.mnu_QuanLy = mnu_QuanLy;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static FrMain getMain() {
        return main;
    }

    public static void setMain(FrMain main) {
        FrMain.main = main;
    }

    public JButton getBtn_Exit() {
        return btn_Exit;
    }

    public void setBtn_Exit(JButton btn_Exit) {
        this.btn_Exit = btn_Exit;
    }

    public JButton getBtn_LogOut() {
        return btn_LogOut;
    }

    public void setBtn_LogOut(JButton btn_LogOut) {
        this.btn_LogOut = btn_LogOut;
    }

    public JButton getBtn_QLDichVu() {
        return btn_QLDichVu;
    }

    public void setBtn_QLDichVu(JButton btn_QLDichVu) {
        this.btn_QLDichVu = btn_QLDichVu;
    }

    public JButton getBtn_QLHopDong() {
        return btn_QLHopDong;
    }

    public void setBtn_QLHopDong(JButton btn_QLHopDong) {
        this.btn_QLHopDong = btn_QLHopDong;
    }

    public JButton getBtn_QLNguoiTro() {
        return btn_QLNguoiTro;
    }

    public void setBtn_QLNguoiTro(JButton btn_QLNguoiTro) {
        this.btn_QLNguoiTro = btn_QLNguoiTro;
    }

    public JButton getBtn_QLNhanVien() {
        return btn_QLNhanVien;
    }

    public void setBtn_QLNhanVien(JButton btn_QLNhanVien) {
        this.btn_QLNhanVien = btn_QLNhanVien;
    }

    public JButton getBtn_QLPhong() {
        return btn_QLPhong;
    }

    public void setBtn_QLPhong(JButton btn_QLPhong) {
        this.btn_QLPhong = btn_QLPhong;
    }

    public JDesktopPane getDsk_main() {
        return dsk_main;
    }

    public void setDsk_main(JDesktopPane dsk_main) {
        this.dsk_main = dsk_main;
    }

    public JLabel getjLabel1() {
        return lbl_Time;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.lbl_Time = jLabel1;
    }

    public JMenuBar getjMenuBar1() {
        return jMenuBar1;
    }

    public void setjMenuBar1(JMenuBar jMenuBar1) {
        this.jMenuBar1 = jMenuBar1;
    }

    public JMenuItem getjMenuItem1() {
        return jMenuItem1;
    }

    public void setjMenuItem1(JMenuItem jMenuItem1) {
        this.jMenuItem1 = jMenuItem1;
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }

    public void setjPanel1(JPanel jPanel1) {
        this.jPanel1 = jPanel1;
    }

    public JPanel getjPanel3() {
        return jPanel3;
    }

    public void setjPanel3(JPanel jPanel3) {
        this.jPanel3 = jPanel3;
    }

    public JPopupMenu.Separator getjSeparator1() {
        return jSeparator1;
    }

    public void setjSeparator1(JPopupMenu.Separator jSeparator1) {
        this.jSeparator1 = jSeparator1;
    }

    public JToolBar.Separator getjSeparator2() {
        return jSeparator2;
    }

    public void setjSeparator2(JToolBar.Separator jSeparator2) {
        this.jSeparator2 = jSeparator2;
    }

    public JToolBar.Separator getjSeparator4() {
        return jSeparator4;
    }

    public void setjSeparator4(JToolBar.Separator jSeparator4) {
        this.jSeparator4 = jSeparator4;
    }

    public JPopupMenu.Separator getjSeparator5() {
        return jSeparator5;
    }

    public void setjSeparator5(JPopupMenu.Separator jSeparator5) {
        this.jSeparator5 = jSeparator5;
    }

    public JPopupMenu.Separator getjSeparator6() {
        return jSeparator6;
    }

    public void setjSeparator6(JPopupMenu.Separator jSeparator6) {
        this.jSeparator6 = jSeparator6;
    }

    public JToolBar.Separator getjSeparator7() {
        return jSeparator7;
    }

    public void setjSeparator7(JToolBar.Separator jSeparator7) {
        this.jSeparator7 = jSeparator7;
    }

    public JToolBar getjToolBar2() {
        return jToolBar2;
    }

    public void setjToolBar2(JToolBar jToolBar2) {
        this.jToolBar2 = jToolBar2;
    }

    public JMenuItem getMni_ChangePassword() {
        return mni_ChangePassword;
    }

    public void setMni_ChangePassword(JMenuItem mni_ChangePassword) {
        this.mni_ChangePassword = mni_ChangePassword;
    }

    public JMenuItem getMni_Exit() {
        return mni_Exit;
    }

    public void setMni_Exit(JMenuItem mni_Exit) {
        this.mni_Exit = mni_Exit;
    }

    public JMenuItem getMni_LogOut() {
        return mni_LogOut;
    }

    public void setMni_LogOut(JMenuItem mni_LogOut) {
        this.mni_LogOut = mni_LogOut;
    }

    public JMenuItem getMni_QLDichVu() {
        return mni_QLDichVu;
    }

    public void setMni_QLDichVu(JMenuItem mni_QLDichVu) {
        this.mni_QLDichVu = mni_QLDichVu;
    }

    public JMenuItem getMni_QLHoaDon() {
        return mni_HDCT;
    }

    public void setMni_QLHoaDon(JMenuItem mni_QLHoaDon) {
        this.mni_HDCT = mni_QLHoaDon;
    }

    public JMenuItem getMni_QLHopDong() {
        return mni_QLHopDong;
    }

    public void setMni_QLHopDong(JMenuItem mni_QLHopDong) {
        this.mni_QLHopDong = mni_QLHopDong;
    }

    public JMenuItem getMni_QLKhachTro() {
        return mni_QLKhachTro;
    }

    public void setMni_QLKhachTro(JMenuItem mni_QLKhachTro) {
        this.mni_QLKhachTro = mni_QLKhachTro;
    }

    public JMenuItem getMni_QLNhanVien() {
        return mni_QLNhanVien;
    }

    public void setMni_QLNhanVien(JMenuItem mni_QLNhanVien) {
        this.mni_QLNhanVien = mni_QLNhanVien;
    }

    public JMenuItem getMni_QLPhong() {
        return mni_QLPhong;
    }

    public void setMni_QLPhong(JMenuItem mni_QLPhong) {
        this.mni_QLPhong = mni_QLPhong;
    }

    public JMenu getMnu_HeThong() {
        return mnu_HeThong;
    }

    public void setMnu_HeThong(JMenu mnu_HeThong) {
        this.mnu_HeThong = mnu_HeThong;
    }

    public JMenu getMnu_QuanLy() {
        return mnu_QuanLy;
    }

    public void setMnu_QuanLy(JMenu mnu_QuanLy) {
        this.mnu_QuanLy = mnu_QuanLy;
    }

    public FrMain(String userName, String roles) {
        main = this;
        initComponents();
        setLocationRelativeTo(null);
        this.roles = roles;
        this.userName = userName;
        if (this.roles.equalsIgnoreCase("NV")) {
            btn_QLNhanVien.setEnabled(false);
            mni_QLNhanVien.setEnabled(false);
        }
        setExtendedState(MAXIMIZED_BOTH);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        t = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                lbl_Time.setText(sdf.format(d));
            }
        });
        t.start();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jToolBar2 = new javax.swing.JToolBar();
        btn_QLNhanVien = new javax.swing.JButton();
        btn_QLNguoiTro = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btn_QLPhong = new javax.swing.JButton();
        btn_QLDichVu = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btn_QLHopDong = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        btn_LogOut = new javax.swing.JButton();
        btn_Exit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lbl_Time = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        dsk_main = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnu_HeThong = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        mni_ChangePassword = new javax.swing.JMenuItem();
        mni_LogOut = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mni_Exit = new javax.swing.JMenuItem();
        mnu_QuanLy = new javax.swing.JMenu();
        mni_QLNhanVien = new javax.swing.JMenuItem();
        mni_QLKhachTro = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        mni_QLPhong = new javax.swing.JMenuItem();
        mni_QLDichVu = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        mnu_HDCT = new javax.swing.JMenuItem();
        mni_HDCT = new javax.swing.JMenuItem();
        mnu_ThongKe = new javax.swing.JMenuItem();
        mni_QLHopDong = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QUẢN LÝ NHÀ TRỌ");

        jToolBar2.setRollover(true);
        jToolBar2.setPreferredSize(new java.awt.Dimension(70, 50));

        btn_QLNhanVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_QLNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sample/icon/Conference.png"))); // NOI18N
        btn_QLNhanVien.setText("Nhân Viên");
        btn_QLNhanVien.setFocusable(false);
        btn_QLNhanVien.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_QLNhanVien.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_QLNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_QLNhanVienActionPerformed(evt);
            }
        });
        jToolBar2.add(btn_QLNhanVien);

        btn_QLNguoiTro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_QLNguoiTro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sample/icon/Boy.png"))); // NOI18N
        btn_QLNguoiTro.setText("Người Trọ");
        btn_QLNguoiTro.setFocusable(false);
        btn_QLNguoiTro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_QLNguoiTro.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_QLNguoiTro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_QLNguoiTroActionPerformed(evt);
            }
        });
        jToolBar2.add(btn_QLNguoiTro);
        jToolBar2.add(jSeparator2);

        btn_QLPhong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_QLPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sample/icon/Home.png"))); // NOI18N
        btn_QLPhong.setText("Phòng");
        btn_QLPhong.setFocusable(false);
        btn_QLPhong.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_QLPhong.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_QLPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_QLPhongActionPerformed(evt);
            }
        });
        jToolBar2.add(btn_QLPhong);

        btn_QLDichVu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_QLDichVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sample/icon/Documents.png"))); // NOI18N
        btn_QLDichVu.setText("Dịch Vụ");
        btn_QLDichVu.setFocusable(false);
        btn_QLDichVu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_QLDichVu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_QLDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_QLDichVuActionPerformed(evt);
            }
        });
        jToolBar2.add(btn_QLDichVu);
        jToolBar2.add(jSeparator4);

        btn_QLHopDong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_QLHopDong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sample/icon/Clien list.png"))); // NOI18N
        btn_QLHopDong.setText("Hợp Đồng");
        btn_QLHopDong.setFocusable(false);
        btn_QLHopDong.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_QLHopDong.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_QLHopDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_QLHopDongActionPerformed(evt);
            }
        });
        jToolBar2.add(btn_QLHopDong);

        jSeparator7.setPreferredSize(new java.awt.Dimension(7, 0));
        jToolBar2.add(jSeparator7);

        btn_LogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sample/icon/Exit.png"))); // NOI18N
        btn_LogOut.setText("Đăng Xuất");
        btn_LogOut.setFocusable(false);
        btn_LogOut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_LogOut.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_LogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LogOutActionPerformed(evt);
            }
        });
        jToolBar2.add(btn_LogOut);

        btn_Exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sample/icon/Stop.png"))); // NOI18N
        btn_Exit.setText("Thoát");
        btn_Exit.setFocusable(false);
        btn_Exit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_Exit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ExitActionPerformed(evt);
            }
        });
        jToolBar2.add(btn_Exit);

        getContentPane().add(jToolBar2, java.awt.BorderLayout.PAGE_START);

        jPanel1.setPreferredSize(new java.awt.Dimension(825, 27));

        lbl_Time.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_Time.setForeground(new java.awt.Color(255, 51, 51));
        lbl_Time.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sample/icon/Alarm.png"))); // NOI18N
        lbl_Time.setText("TIME");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(748, Short.MAX_VALUE)
                .addComponent(lbl_Time)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbl_Time))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel3.setPreferredSize(new java.awt.Dimension(800, 522));

        javax.swing.GroupLayout dsk_mainLayout = new javax.swing.GroupLayout(dsk_main);
        dsk_main.setLayout(dsk_mainLayout);
        dsk_mainLayout.setHorizontalGroup(
            dsk_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 825, Short.MAX_VALUE)
        );
        dsk_mainLayout.setVerticalGroup(
            dsk_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 514, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dsk_main)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dsk_main)
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        jMenuBar1.setMaximumSize(new java.awt.Dimension(130, 327));

        mnu_HeThong.setText("Hệ thống");
        mnu_HeThong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sample/icon/Calculator.png"))); // NOI18N
        jMenuItem2.setText("Thanh Toán");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        mnu_HeThong.add(jMenuItem2);

        mni_ChangePassword.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        mni_ChangePassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sample/icon/Refresh.png"))); // NOI18N
        mni_ChangePassword.setText("Đổi mật khẩu");
        mni_ChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_ChangePasswordActionPerformed(evt);
            }
        });
        mnu_HeThong.add(mni_ChangePassword);

        mni_LogOut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        mni_LogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sample/icon/Log out.png"))); // NOI18N
        mni_LogOut.setText("Đăng xuất");
        mni_LogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_LogOutActionPerformed(evt);
            }
        });
        mnu_HeThong.add(mni_LogOut);
        mnu_HeThong.add(jSeparator1);

        mni_Exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        mni_Exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sample/icon/Stop.png"))); // NOI18N
        mni_Exit.setText("Thoát");
        mni_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_ExitActionPerformed(evt);
            }
        });
        mnu_HeThong.add(mni_Exit);

        jMenuBar1.add(mnu_HeThong);

        mnu_QuanLy.setText("Quản lý");
        mnu_QuanLy.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        mni_QLNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sample/icon/Conference.png"))); // NOI18N
        mni_QLNhanVien.setText("Nhân viên");
        mni_QLNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_QLNhanVienActionPerformed(evt);
            }
        });
        mnu_QuanLy.add(mni_QLNhanVien);

        mni_QLKhachTro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sample/icon/Boy.png"))); // NOI18N
        mni_QLKhachTro.setText("Khách trọ");
        mni_QLKhachTro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_QLKhachTroActionPerformed(evt);
            }
        });
        mnu_QuanLy.add(mni_QLKhachTro);
        mnu_QuanLy.add(jSeparator5);

        mni_QLPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sample/icon/Home.png"))); // NOI18N
        mni_QLPhong.setText("Phòng");
        mni_QLPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_QLPhongActionPerformed(evt);
            }
        });
        mnu_QuanLy.add(mni_QLPhong);

        mni_QLDichVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sample/icon/Documents.png"))); // NOI18N
        mni_QLDichVu.setText("Dịch vụ");
        mni_QLDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_QLDichVuActionPerformed(evt);
            }
        });
        mnu_QuanLy.add(mni_QLDichVu);
        mnu_QuanLy.add(jSeparator6);

        mnu_HDCT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sample/icon/Add to basket.png"))); // NOI18N
        mnu_HDCT.setText("Hóa đơn Dịch Vụ");
        mnu_HDCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_HDCTActionPerformed(evt);
            }
        });
        mnu_QuanLy.add(mnu_HDCT);

        mni_HDCT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sample/icon/Cash register.png"))); // NOI18N
        mni_HDCT.setText("Hoá đơn Chi Tiết");
        mni_HDCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_HDCTActionPerformed(evt);
            }
        });
        mnu_QuanLy.add(mni_HDCT);

        mnu_ThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sample/icon/Database.png"))); // NOI18N
        mnu_ThongKe.setText("Thống Kê ");
        mnu_ThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_ThongKeActionPerformed(evt);
            }
        });
        mnu_QuanLy.add(mnu_ThongKe);

        mni_QLHopDong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sample/icon/Clien list.png"))); // NOI18N
        mni_QLHopDong.setText("Hợp đồng");
        mni_QLHopDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_QLHopDongActionPerformed(evt);
            }
        });
        mnu_QuanLy.add(mni_QLHopDong);

        jMenuBar1.add(mnu_QuanLy);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mni_QLDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_QLDichVuActionPerformed
        try {
            FrDichVu dv = new FrDichVu();
            dsk_main.add(dv);
            dv.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: " + e);
        }
    }//GEN-LAST:event_mni_QLDichVuActionPerformed

    private void btn_QLNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_QLNhanVienActionPerformed
        QLNhanVien qlnv = new QLNhanVien();
        dsk_main.add(qlnv);
        qlnv.setVisible(true);
    }//GEN-LAST:event_btn_QLNhanVienActionPerformed

    private void btn_QLNguoiTroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_QLNguoiTroActionPerformed
        QLKhachTro qlkt = new QLKhachTro(roles);
        dsk_main.add(qlkt);
        qlkt.setVisible(true);
    }//GEN-LAST:event_btn_QLNguoiTroActionPerformed

    private void btn_QLPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_QLPhongActionPerformed
        QLPhong qlp = new QLPhong(userName, roles);
        dsk_main.add(qlp);
        qlp.setVisible(true);
    }//GEN-LAST:event_btn_QLPhongActionPerformed

    private void mni_ChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_ChangePasswordActionPerformed
        try {
            ChangePass cp = new ChangePass(userName);
            dsk_main.add(cp);
            cp.setVisible(true);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_mni_ChangePasswordActionPerformed

    private void btn_QLDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_QLDichVuActionPerformed
        try {
            FrDichVu dv = new FrDichVu();
            dsk_main.add(dv);
            dv.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: " + e);
        }
    }//GEN-LAST:event_btn_QLDichVuActionPerformed

    private void btn_ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ExitActionPerformed
        try {
            int ex = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát?");
            if (ex == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_ExitActionPerformed

    private void btn_QLHopDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_QLHopDongActionPerformed
        try {
            QLHopDong hd = new QLHopDong(userName, roles, "");
            dsk_main.add(hd);
            hd.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: " + e);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_QLHopDongActionPerformed

    private void btn_LogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LogOutActionPerformed
        try {
            this.dispose();
            new Login().setVisible(true);

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_LogOutActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        try {
            ThanhToan hd = new ThanhToan(userName, "", "", "");
            dsk_main.add(hd);
            hd.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: " + e);
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void mni_LogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_LogOutActionPerformed
        try {
            this.dispose();
            new Login().setVisible(true);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_mni_LogOutActionPerformed

    private void mni_ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_ExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mni_ExitActionPerformed

    private void mni_QLNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_QLNhanVienActionPerformed
        QLNhanVien qlnv = new QLNhanVien();
        dsk_main.add(qlnv);
        qlnv.setVisible(true);
    }//GEN-LAST:event_mni_QLNhanVienActionPerformed

    private void mni_QLKhachTroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_QLKhachTroActionPerformed
        QLKhachTro qlkt = new QLKhachTro(roles);
        dsk_main.add(qlkt);
        qlkt.setVisible(true);
    }//GEN-LAST:event_mni_QLKhachTroActionPerformed

    private void mni_QLPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_QLPhongActionPerformed
        QLPhong qlp = new QLPhong(userName, roles);
        dsk_main.add(qlp);
        qlp.setVisible(true);
    }//GEN-LAST:event_mni_QLPhongActionPerformed

    private void mni_QLHopDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_QLHopDongActionPerformed
        try {
            QLHopDong hd = new QLHopDong(userName, roles, "");
            dsk_main.add(hd);
            hd.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: " + e);
            e.printStackTrace();
        }
    }//GEN-LAST:event_mni_QLHopDongActionPerformed

    private void mni_HDCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_HDCTActionPerformed
        try {
            FrHoaDonChiTiet hdct = new FrHoaDonChiTiet();
            dsk_main.add(hdct);
            hdct.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: " + e);
            e.printStackTrace();
        }
    }//GEN-LAST:event_mni_HDCTActionPerformed

    private void mnu_HDCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_HDCTActionPerformed
        try {
            FrHoaDonDV hddv = new FrHoaDonDV();
            dsk_main.add(hddv);
            hddv.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: " + e);
            e.printStackTrace();
        }
    }//GEN-LAST:event_mnu_HDCTActionPerformed

    private void mnu_ThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_ThongKeActionPerformed
        try {
            FrThongKe hd = new FrThongKe();
            dsk_main.add(hd);
            hd.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Opps!! Error: " + e);
        }
    }//GEN-LAST:event_mnu_ThongKeActionPerformed

    /**
     * @param args the command line arguments
     */
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
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FrMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrMain("", "").setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Exit;
    private javax.swing.JButton btn_LogOut;
    private javax.swing.JButton btn_QLDichVu;
    private javax.swing.JButton btn_QLHopDong;
    private javax.swing.JButton btn_QLNguoiTro;
    private javax.swing.JButton btn_QLNhanVien;
    private javax.swing.JButton btn_QLPhong;
    private javax.swing.JDesktopPane dsk_main;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JLabel lbl_Time;
    private javax.swing.JMenuItem mni_ChangePassword;
    private javax.swing.JMenuItem mni_Exit;
    private javax.swing.JMenuItem mni_HDCT;
    private javax.swing.JMenuItem mni_LogOut;
    private javax.swing.JMenuItem mni_QLDichVu;
    private javax.swing.JMenuItem mni_QLHopDong;
    private javax.swing.JMenuItem mni_QLKhachTro;
    private javax.swing.JMenuItem mni_QLNhanVien;
    private javax.swing.JMenuItem mni_QLPhong;
    private javax.swing.JMenuItem mnu_HDCT;
    private javax.swing.JMenu mnu_HeThong;
    private javax.swing.JMenu mnu_QuanLy;
    private javax.swing.JMenuItem mnu_ThongKe;
    // End of variables declaration//GEN-END:variables
}
