package com.youzou.libsys.util;

import javax.swing.*;

/**
 * Created by 葉蔵 on 2018/1/25.
 */
public class Utils {
    public static void setVoid(JTextField textField){
        textField.setText("");
    }
    public static void setVoid(JPasswordField passwordField){
        passwordField.setText("");
    }

    /**
     * 清空注册窗口信息
     * @param textField1
     * @param textField2
     * @param passwordField1
     * @param passwordField2
     */
    public static void setRegisterVoid(JTextField textField1,JTextField textField2,JPasswordField passwordField1,JPasswordField passwordField2){
        setVoid(textField1);
        setVoid(textField2);
        setVoid(passwordField1);
        setVoid(passwordField2);
    }
    public static void setLoginVoid(JTextField textField,JPasswordField passwordField){
        setVoid(textField);
        setVoid(passwordField);
    }

    public static boolean isNumber(JTextField textField){
        String text=textField.getText().trim();
        try{
           int i=Integer.valueOf(text) ;
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}
