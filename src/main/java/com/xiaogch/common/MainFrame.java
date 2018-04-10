package com.xiaogch.common;

import freemarker.template.Template;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static java.io.File.separator;

public class MainFrame extends JFrame {

    JButton genButton = new JButton();
    JButton selectPathButton = new JButton();
    JButton selectConfigFilePathButton = new JButton();

    JTextField hostField = new JTextField();
    JTextField portField = new JTextField();

    JTextField driverField = new JTextField();
    JTextField userNameField = new JTextField();
    JTextField passwordField = new JTextField();
    JTextField configFilePathField = new JTextField();

    JTextField basePathField = new JTextField();
    JTextField packageField = new JTextField();
    JTextField tableField = new JTextField();

    JComboBox configureComboBox;
    JComboBox dataBaseComboBox;
    JFileChooser jFileChooserBasePath;
    JFileChooser jFileChooserConfigFile;

    public MainFrame() {
        super("dao及实体生成工具");

        JLabel configureComboLabel = new JLabel("数据库配置:" , null , JLabel.RIGHT);
        configureComboBox = new JComboBox(new String[]{"自主填写","配置文件"});
        configureComboLabel.setBounds(10 , 10 , 90 , 30);
        configureComboBox.setBounds(110 , 10 , 300 , 30);
        configureComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if ("自主填写".equals(e.getItem().toString())) {
                    selectConfigFilePathButton.setEnabled(false);
                    dataBaseComboBox.setEnabled(true);
                    hostField.setEnabled(true);
                    portField.setEnabled(true);
                    userNameField.setEnabled(true);
                    passwordField.setEnabled(true);
                } else {
                    selectConfigFilePathButton.setEnabled(true);
                    dataBaseComboBox.setEnabled(false);
                    hostField.setEnabled(false);
                    portField.setEnabled(false);
                    userNameField.setEnabled(false);
                    passwordField.setEnabled(false);
                }
            }
        });
        this.add(configureComboLabel);
        this.add(configureComboBox);

        JLabel configureFilePathLabel = new JLabel("配置文件：" , null , JLabel.RIGHT);
        configureFilePathLabel.setBounds(10 , 50 , 90,30);
        configFilePathField.setBounds(110 , 50 , 300 , 30);
        configFilePathField.setText("请选择配置文件路径！");
        configFilePathField.setEditable(false);
        selectConfigFilePathButton.setEnabled(false);
        selectConfigFilePathButton.setText("浏览...");
        selectConfigFilePathButton.setBounds(430, 50, 110, 30);
        selectConfigFilePathButton.addActionListener(new OpenChooserConfigFileDialogButtonActionListener());
        this.add(configureFilePathLabel);
        this.add(configFilePathField);
        this.add(selectConfigFilePathButton);

        JLabel databaseLabel = new JLabel("数据库类型:" , null , JLabel.RIGHT);
        databaseLabel.setBounds(10 , 90 , 90 , 30);
        dataBaseComboBox = new JComboBox(new String[]{"mysql"});
        dataBaseComboBox.setBounds(110 , 90 , 300 , 30);
        this.add(databaseLabel);
        this.add(dataBaseComboBox);


        JLabel hostLabel = new JLabel("服务器地址:" , null , JLabel.RIGHT);
        hostLabel.setBounds(10 , 130 , 90 , 30);
        hostField.setBounds(110 , 130 , 300 , 30);
        this.add(hostLabel);
        this.add(hostField);

        JLabel portLabel = new JLabel("服务器端口:", null , JLabel.RIGHT);
        portLabel.setBounds(10 , 170 , 90 , 30);
        portField.setBounds(110 , 170 , 300 , 30);
        this.add(portLabel);
        this.add(portField);


        JLabel userNameLabel = new JLabel("数据库用户名:", null , JLabel.RIGHT);
        userNameLabel.setBounds(10 , 210 , 90 , 30);
        userNameField.setBounds(110 , 210 , 200 , 30);
        this.add(userNameLabel);
        this.add(userNameField);


        JLabel passwordLabel = new JLabel("数据库密码:", null , JLabel.RIGHT);
        passwordLabel.setBounds(10 , 250 , 90 , 30);
        passwordField.setBounds(110 , 250 , 200 , 30);
        this.add(passwordLabel);
        this.add(passwordField);


        JLabel basePath = new JLabel("生成路径：" , null , JLabel.RIGHT);
        basePath.setBounds(10 , 290 , 90,30);
        basePathField.setBounds(110 , 290 , 300 , 30);
        basePathField.setText("请选择生成路径！");
        basePathField.setEditable(false);
        selectPathButton.setText("浏览...");
        selectPathButton.setBounds(430, 290, 110, 30);
        selectPathButton.addActionListener(new OpenChooserBasePathFileDialogButtonActionListener());
        this.add(basePath);
        this.add(basePathField);
        this.add(selectPathButton);

        JLabel packageLabel = new JLabel("包名：" , null , JLabel.RIGHT);
        packageLabel.setBounds(10 , 330 , 90,30);
        packageField.setBounds(110 , 330 , 300 , 30);
        this.add(packageLabel);
        this.add(packageField);

        JLabel tableLabel = new JLabel("表名：" , null , JLabel.RIGHT);
        tableLabel.setBounds(10 , 370 , 90,30);
        tableField.setBounds(110 , 370 , 300 , 30);
        this.add(tableLabel);
        this.add(tableField);

        genButton.setText("生成文件");
        genButton.setBounds(240, 410, 120, 30);
        genButton.addActionListener(new GenFileButtonActionListener());
        this.add(genButton);

        this.setLayout(null);
        this.setBounds(300, 300 ,600, 500);
        this.setVisible(true);

        jFileChooserBasePath = new JFileChooser();
        jFileChooserConfigFile = new JFileChooser();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    class GenFileButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String filePath = basePathField.getText();// field.getText();
            String configFilePath = configFilePathField.getText();
            BaseConfigureDTO configureDTO = new BaseConfigureDTO();
            System.out.println("configureComboBox selected " + configureComboBox.getSelectedItem());
            if ("自主填写".equals(configureComboBox.getSelectedItem().toString())) {
                System.out.println("dataBaseComboBox selected " + dataBaseComboBox.getSelectedItem());

                String host = hostField.getText();
                String port = portField.getText();
                if ("mysql".equals(dataBaseComboBox.getSelectedItem().toString())) {
                    configureDTO.setUrl("jdbc:mysql://" + host + ":" + port + "/information_schema");
                    configureDTO.setDriver("com.mysql.jdbc.Driver");
                }
                configureDTO.setUserName(userNameField.getText());
                configureDTO.setPassword(passwordField.getText());
            } else {
                if (configFilePath != null && !"请选择配置文件路径！".equals(configFilePath)) {
                    Properties properties = new Properties();

                    try {
                        properties.load(new FileInputStream(configFilePath));
                        configureDTO.setUrl(properties.getProperty("url"));
                        configureDTO.setDriver(properties.getProperty("driver"));
                        configureDTO.setUserName(properties.getProperty("username"));
                        configureDTO.setPassword(properties.getProperty("password"));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            configureDTO.setTable(tableField.getText());
            configureDTO.setPackageName(packageField.getText());

            if (filePath != null && !"请选择文件！".equals(filePath)) {
                configureDTO.setBasePath(filePath);
            }

            System.out.println(configureDTO);

            DatabaseDao databaseDao = new DatabaseDao(configureDTO);

            List<ColumnDTO> columnDTOList = databaseDao.getColumnList();
            Map<String , Object> model = new HashMap<String, Object>();
            model.put("table" , configureDTO.getTable());
            String temp;
            if (configureDTO.getTable().startsWith("t_")) {
                temp = configureDTO.getTable().substring(2);
            } else {
                temp = configureDTO.getTable();
            }
            temp = DatabaseDao.underlineToCamel(temp);
            model.put("entityInstanceName" , temp);
            model.put("entityClassName" , temp.substring(0 , 1).toUpperCase() + temp.substring(1));

            model.put("basePackage" , configureDTO.getPackageName());
            model.put("attributes" , columnDTOList);
            model.put("useDate" , false);

            columnDTOList.forEach(columnDTO -> {
                if ("Date".equals(columnDTO.getType())) {
                     model.put("useDate" , true);
                }
            });

            try {
                createFileByType(model , configureDTO.getBasePath() , (String) model.get("basePackage") , TYPE_ENTITY);
                createFileByType(model , configureDTO.getBasePath() , (String) model.get("basePackage") , TYPE_DAO);
                createFileByType(model , configureDTO.getBasePath() , (String) model.get("basePackage") , TYPE_MAPPER);
                createFileByType(model , configureDTO.getBasePath() , (String) model.get("basePackage") , TYPE_SERVICE);
                createFileByType(model , configureDTO.getBasePath() , (String) model.get("basePackage") , TYPE_SERVICE_IMPL);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }


    class OpenChooserBasePathFileDialogButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            jFileChooserBasePath.setDialogTitle("选择生成路径");
            jFileChooserBasePath.setDialogType(JFileChooser.OPEN_DIALOG);
            jFileChooserBasePath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int res = jFileChooserBasePath.showOpenDialog(MainFrame.this);
            if (res == JFileChooser.APPROVE_OPTION) {
                File dir = jFileChooserBasePath.getSelectedFile();
                basePathField.setText(dir.getAbsolutePath());
                System.out.println(dir.getAbsolutePath());
            }
        }
    }

    class OpenChooserConfigFileDialogButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            jFileChooserConfigFile.setDialogTitle("选择配置数据库文件");
            jFileChooserConfigFile.setDialogType(JFileChooser.OPEN_DIALOG);
            jFileChooserConfigFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jFileChooserConfigFile.setFileFilter(new FileFilter() {

                @Override
                public boolean accept(File f) {
                    return f.getName().endsWith(".properties");
                }
                @Override
                public String getDescription() {
                    return null;
                }
            });

            int res = jFileChooserConfigFile.showOpenDialog(MainFrame.this);
            if (res == JFileChooser.APPROVE_OPTION) {
                File dir = jFileChooserConfigFile.getSelectedFile();
                configFilePathField.setText(dir.getAbsolutePath());
                System.out.println(dir.getAbsolutePath());
            }
        }
    }


    public static void mkdir(final String basePath , final String packageName) {
        String[] temp = packageName.split("\\.");
        String tempPath = basePath ;
        for (String str : temp) {
            File file = new File(tempPath + separator + str);
            if (!file.exists()) {
                boolean result = file.mkdir();
                System.out.println(tempPath + separator + str + " create " + (result ? "success" : "failure"));
            } else {
                System.out.println(tempPath + separator + str + " is exists !");
            }
            tempPath = tempPath + separator + str;
        }
    }


    private static String TYPE_ENTITY = "entity";
    private static String TYPE_DAO = "dao";
    private static String TYPE_MAPPER = "mapper";
    private static String TYPE_SERVICE = "service";
    private static String TYPE_SERVICE_IMPL = "service_impl";

    public static void createFileByType(Map<String , Object> model , final String basePath , final String packageName , final String type) throws Exception {

        String[] temp = packageName.split("\\.");
        String path = basePath;
        for (String str : temp) {
            path =path + separator + str;
        }
        if (TYPE_ENTITY.equals(type)) {
            mkdir(basePath , packageName + ".entity");
            path = path + separator + "entity" + separator +  model.get("entityClassName") + ".java";
        } else if (TYPE_DAO.equals(type)) {
            mkdir(basePath , packageName + ".dao");
            path = path + separator + "dao" + separator +  model.get("entityClassName") + "Dao.java";
        } else if (TYPE_MAPPER.equals(type)) {
            mkdir(basePath , packageName + ".dao");
            path = path + separator + "dao" + separator + model.get("entityClassName") + "Dao.xml";
        } else if (TYPE_SERVICE.equals(type)) {
            mkdir(basePath , packageName + ".service");
            path = path + separator + "service" + separator +  model.get("entityClassName") + "Service.java";
        } else if (TYPE_SERVICE_IMPL.equals(type)) {
            mkdir(basePath , packageName + ".service.impl");
            path = path + separator + "service" + separator + "impl" + separator + model.get("entityClassName") + "ServiceImpl.java";
        } else {
            throw new IllegalArgumentException("无效的type");
        }



        FileOutputStream fileOutputStream = new FileOutputStream(path);
        PrintWriter printWriter = new PrintWriter(fileOutputStream);

        Template template = null;
        if (TYPE_ENTITY.equals(type)) {
            template = FreemakerUtil.getEntityTemplate();
        } else if (TYPE_DAO.equals(type)) {
            template = FreemakerUtil.getDaoTemplate();
        } else if (TYPE_MAPPER.equals(type)) {
            template = FreemakerUtil.getMapperTemplate();
        } else if (TYPE_SERVICE.equals(type)) {
            template = FreemakerUtil.getServiceTemplate();
        } else if (TYPE_SERVICE_IMPL.equals(type)) {
            template = FreemakerUtil.getServiceImplTemplate();
        }

        template.process(model , printWriter);
        printWriter.flush();
        printWriter.close();
        fileOutputStream.close();
    }

}
