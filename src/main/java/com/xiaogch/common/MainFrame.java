package com.xiaogch.common;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainFrame extends JFrame {

    JButton genButton = new JButton();
    JButton selectPathButton = new JButton();

    JTextField urlField = new JTextField();
    JTextField driverField = new JTextField();
    JTextField userNameField = new JTextField();
    JTextField passwordField = new JTextField();
    JTextField basePathField = new JTextField();
    JTextField packageText = new JTextField();
    JTextField tableText = new JTextField();

    JFileChooser jFileChooser;

    public MainFrame() {
        super("dao及实体生成工具");
        JLabel urlLabel = new JLabel("url:" , null , JLabel.RIGHT);
        urlLabel.setBounds(10 , 10 , 80 , 30);

        JLabel driverLabel = new JLabel("driver:", null , JLabel.RIGHT);
        driverLabel.setBounds(10 , 50 , 80 , 30);

        JLabel userNameLabel = new JLabel("userName:", null , JLabel.RIGHT);
        userNameLabel.setBounds(10 , 90 , 80 , 30);

        JLabel passwordLabel = new JLabel("password:", null , JLabel.RIGHT);
        passwordLabel.setBounds(10 , 130 , 80 , 30);

        JTextField urlField = new JTextField();
        urlField.setBounds(100 , 10 , 300 , 30);

        JTextField driverField = new JTextField();
        driverField.setBounds(100 , 50 , 300 , 30);

        JTextField userNameField = new JTextField();
        userNameField.setBounds(100 , 90 , 200 , 30);

        JTextField passwordField = new JTextField();
        passwordField.setBounds(100 , 130 , 200 , 30);

        JLabel basePath = new JLabel("生成路径：" , null , JLabel.RIGHT);
        basePath.setBounds(10 , 170 , 80,30);
        basePathField.setBounds(100 , 170 , 300 , 30);
        basePathField.setText("请选择生成路径！");
        basePathField.setEditable(false);


        selectPathButton.setText("浏览...");
        selectPathButton.setBounds(420, 170, 100, 30);
        selectPathButton.addActionListener(new OpenFileButtonActionListener());



        JLabel packageLabel = new JLabel("包名：" , null , JLabel.RIGHT);
        packageText.setBounds(100 , 210 , 300 , 30);
        packageLabel.setBounds(10 , 210 , 80,30);

        JLabel tableLabel = new JLabel("表名：" , null , JLabel.RIGHT);
        tableLabel.setBounds(10 , 250 , 80,30);
        tableText.setBounds(100 , 250 , 300 , 30);

        genButton.setText("生成文件");
        genButton.addActionListener(new GenRemarkButtonActionListener());
        genButton.setBounds(240, 290, 120, 30);

        this.add(urlLabel);
        this.add(urlField);

        this.add(driverLabel);
        this.add(driverField);

        this.add(userNameLabel);
        this.add(userNameField);
        this.add(passwordLabel);
        this.add(passwordField);

        this.add(basePath);
        this.add(basePathField);
        this.add(selectPathButton);
        this.add(packageLabel);
        this.add(packageText);
        this.add(tableLabel);
        this.add(tableText);
        this.add(genButton);
        this.setLayout(null);
        this.setBounds(300, 300, 630, 500);
        this.setResizable(false);
        this.setVisible(true);

        jFileChooser = new JFileChooser();
    }

    public static void main(String args[]) {
        new MainFrame();
    }

    class GenRemarkButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String filePath = "" ;// field.getText();
            if (filePath != null && !"请选择实体文件！".equals(filePath)) {
                // EntityRemarkUtil.genEntityRemark(filePath);
            }
        }
    }

    class OpenFileButtonActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            jFileChooser.setDialogTitle("选择实体文件");
            jFileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
            jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int res = jFileChooser.showOpenDialog(MainFrame.this);
            if (res == JFileChooser.APPROVE_OPTION) {
                File dir = jFileChooser.getSelectedFile();
                basePathField.setText(dir.getAbsolutePath());
                System.out.println(dir.getAbsolutePath());
            }
        }
    }



}
