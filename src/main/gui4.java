package main;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.io.IOException;



public class gui4 extends JFrame {

    private JPanel contentPane;
    private JTextField textField1;
    private JTextArea resultField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    gui4 frame = new gui4();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public gui4() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 650);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new FlowLayout()); // 设置为流式布局
        setContentPane(contentPane);
        this.setTitle("对比项目相似度");

        // 创建第一个文本框和按钮
        textField1 = new JTextField(20);
        contentPane.add(textField1);
        JButton btnBrowse1 = new JButton("Browse...");
        setupButton(btnBrowse1, textField1);
        contentPane.add(btnBrowse1);

        // 创建用于显示结果的文本框
        resultField = new JTextArea(30,50);
        resultField.setEditable(false); // 设置为不可编辑
        contentPane.add(resultField);

        // 创建处理数据的按钮
        JButton btnProcess = new JButton("Process");
        btnProcess.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String path1 = textField1.getText();
                Q1 q1=new Q1();
                ArrayList<String> list1=new ArrayList<>();
                ArrayList<String> list2=new ArrayList<>();
                double[] x=new double[30];
                try {
                    q1.closestCodes(path1, ".*\\.java", 0.0, false, list1, list2, x);
                } catch (IOException a) {
                    System.err.println("处理文件时发生了一个错误：" + a.getMessage());
                    // 这里可以加入更多的错误处理代码，比如重试或者退出程序
                }

                resultField.setText(" ");
                String str;
                for(int i=0;i<list1.size();i++) {
                	System.out.println(x[i]);
                	str="文件相似度："+Double.toString(x[i])+"\n"+list1.get(i)+"\n"+list2.get(i)+"\n";
                	appendToTextField(str,resultField);
                }
            }
        });
        contentPane.add(btnProcess);
    }
    public void appendToTextField(String text,JTextArea textField) {
        String existingText = textField.getText();  // 获取当前文本
        String newText = existingText + text;       // 将新文本追加到现有文本后面
        textField.setText(newText);                 // 设置更新后的文本
        textField.setLineWrap(true);  // 设置自动换行
    }
    private void setupButton(JButton button, JTextField textField) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showOpenDialog(gui4.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    textField.setText(selectedFile.getAbsolutePath());
                }
            }
        });
    }
}

