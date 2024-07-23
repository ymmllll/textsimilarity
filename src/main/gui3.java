
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

public class gui3 extends JFrame {

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
                    gui1 frame = new gui1();
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
    public gui3() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new FlowLayout()); // 设置为流式布局
        setContentPane(contentPane);
        this.setTitle("逐个对比相似度");

        // 创建第一个文本框和按钮
        textField1 = new JTextField(20);
        contentPane.add(textField1);
        JButton btnBrowse1 = new JButton("Browse...");
        setupButton(btnBrowse1, textField1);
        contentPane.add(btnBrowse1);
        // 创建用于显示结果的文本框
        resultField = new JTextArea(20,30);
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
                q1.ClosestCodeMatches(path1,list1);
                resultField.setText(" ");
                for(String i:list1) {
                	appendToTextField(i,resultField);
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
                int result = fileChooser.showOpenDialog(gui3.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    textField.setText(selectedFile.getAbsolutePath());
                }
            }
        });
    }
}

