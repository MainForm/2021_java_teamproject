import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import jdbc_mysql.DBConnector;

import java.sql.*;

public class Main {
    static MainFrame mainFrame;
    final static String gameRule = "2048���ӱ�Ģ";
    public static DBConnector dbConn;
    
    public static void main(String[] args){

        try {
        	dbConn = new DBConnector();
        	mainFrame = new MainFrame(new LogInPanel());
        }
        catch(Exception err) {
        	JOptionPane.showMessageDialog(null,"���� ���ῡ ���� �߽��ϴ�.","Failed",JOptionPane.ERROR_MESSAGE);
        	mainFrame = new MainFrame(new SelectModePanel());
        }
    }
}

class MainFrame extends JFrame{
    //MainPenel mainPenel;

    public MainFrame(JPanel StartPanel){
        setTitle("Game2048");
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        createMenu();
        add(StartPanel,BorderLayout.CENTER);
        setVisible(true);
    }

    //��� �޴���
    public void createMenu() {
        //��� �޴���
        JMenuBar mb = new JMenuBar(); //�޴��� ����
        JMenu mn = new JMenu("�޴�");
        JMenuItem set = new JMenuItem("����");
        JMenuItem rank = new JMenuItem("��������");
        JMenuItem rule = new JMenuItem("���ӱ�Ģ");
        JMenuItem logout = new JMenuItem("�α׾ƿ�");
        set.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //set �̺�Ʈ
            }
        });
        rank.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //�������� �̺�Ʈ
            }
        });


        rule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //���ӱ�Ģ �̺�Ʈ
                JOptionPane.showMessageDialog(null, Main.gameRule, "���ӱ�Ģ", JOptionPane.PLAIN_MESSAGE); //���ӱ�Ģ
            }
        });

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.mainFrame.changePanel(new LogInPanel());
            }
        });

        mb.add(mn);
        mn.add(set);
        mn.add(rank);
        mn.add(rule);
        
        
        mn.add(logout);
        
        setJMenuBar(mb);
    }

    public void changePanel(JPanel newPanel) {
        getContentPane().removeAll();
        add(newPanel,BorderLayout.CENTER);
        newPanel.setRequestFocusEnabled(true);
        newPanel.setFocusable(true);
        newPanel.requestFocus();
        revalidate();
        repaint();
    }
}

abstract class MenuPanel extends JPanel{
    public JPanel newLine(Component c) {

        JPanel jp = new JPanel();
        jp.setLayout(new BoxLayout(jp, BoxLayout.X_AXIS));
        jp.add(Box.createHorizontalGlue());
        jp.add(c);
        jp.add(Box.createHorizontalGlue());
        jp.setOpaque(false);

        return jp;
    }
}




