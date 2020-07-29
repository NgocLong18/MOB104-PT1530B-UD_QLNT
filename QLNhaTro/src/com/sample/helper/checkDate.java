/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.helper;

import java.util.Date;
import java.util.Calendar;

/**
 *
 * @author akais
 */
public class checkDate {

    public static boolean checkBirthday(String n) {
        if (n == null || n.length() < 10) {
            return false;
        } else {
            boolean flag = true;
            String dd, mm, yyyy;
            int d, m, y;
            yyyy = n.substring(0, 4);
            mm = n.substring(5, 7);
            dd = n.substring(8, 9);
            try {
                Date dat = new Date();
                Calendar date = Calendar.getInstance();
                date.setTime(dat);
                int y1 = date.get(Calendar.YEAR);
                int m1 = date.get(Calendar.MONTH);
                int d1 = date.get(Calendar.DAY_OF_MONTH);
                m = Integer.parseInt(mm);
                d = Integer.parseInt(dd);
                y = Integer.parseInt(yyyy);
                if ((d < 1) && (d > 31)) {
                    flag = false;
                }
                if ((m < 1) && (m > 12)) {
                    flag = false;
                }
                if (y < 1) {
                    flag = false;
                }
                if (y1 < y) {
                    flag = false;
                }
            } catch (Exception e) {
                flag = false;
            }
            return flag;
        }
    }
}
