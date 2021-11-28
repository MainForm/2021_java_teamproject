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
    final static String gameRule = "2048게임규칙";
    public static DBConnector dbConn;
    
    public static void main(String[] args){

        try {
        	dbConn = new DBConnector();
        	mainFrame = new MainFrame(new LogInPanel());
        }
        catch(Exception err) {
        	JOptionPane.showMessageDialog(null,"서버 연결에 실패 했습니다.","Failed",JOptionPane.ERROR_MESSAGE);
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

    //상단 메뉴바
    public void createMenu() {
        //상단 메뉴바
        JMenuBar mb = new JMenuBar(); //메뉴바 생성
        JMenu mn = new JMenu("메뉴");
        JMenuItem set = new JMenuItem("설정");
        JMenuItem rank = new JMenuItem("순위보기");
        JMenuItem rule = new JMenuItem("게임규칙");
        JMenuItem logout = new JMenuItem("로그아웃");
        set.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //set 이벤트
            }
        });
        rank.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //순위보기 이벤트
            }
        });


        rule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //게임규칙 이벤트
                JOptionPane.showMessageDialog(null, Main.gameRule, "게임규칙", JOptionPane.PLAIN_MESSAGE); //게임규칙
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




