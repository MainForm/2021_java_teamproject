import javax.swing.*;

import com.google.gson.*;

import java.awt.*;
import java.awt.event.*;

import jdbc_mysql.DBConnector;

import java.sql.*;
import java.util.*;

import java.io.*;
import java.nio.file.Files;

public class Main {
    static MainFrame mainFrame;
    public static DBConnector dbConn;
    
    public static void main(String[] args){
    	
        try {
        	File jsonFile = new File("mySQL.json");
        	FileReader jsonReader = new FileReader(jsonFile.getPath());
        	JsonObject jsonObj = new JsonObject();
        	
        	Gson gsonObj = new Gson();
        	
        	jsonObj = gsonObj.fromJson(jsonReader, JsonObject.class);
        	
        	
        	
        	dbConn = new DBConnector(jsonObj.get("IP").getAsString(),jsonObj.get("Port").getAsInt());
        	mainFrame = new MainFrame(new LogInPanel());
        }
        catch(Exception err) {
        	JOptionPane.showMessageDialog(null,"서버 연결에 실패 했습니다.","Failed",JOptionPane.ERROR_MESSAGE);
        	mainFrame = new MainFrame(new SelectModePanel());
        	mainFrame.setMenuItemVisible(3, false);
        }
    }
}

class MainFrame extends JFrame{
    //MainPenel mainPenel;
    JMenuBar mb = new JMenuBar(); //메뉴바 생성
    JMenu mn = new JMenu("메뉴");
    
    final static String gameRule = "1: 게임을 시작하면 랜덤으로 숫자2 2개가 나온다.\n"+
    								"2: 숫자를 계속 더하여 2048을 만들고 방향키를 옮기면 블록이 전부 방향키가 눌린 방향 이동한다\n"+
    								"3: 옮기는 도중 같은 숫자가 있을 경우 합쳐지며 빈지리중 한 칸에 랜덤하게 2가 나온다 \n"+
    								"4: 2048을 만들기 전 이동할 수 없는 경우 즉 16칸이 꽉 차있으면서 인접한 두 칸이 같지 않을 때, 게임 오버가 되며, 2048을 만듦과 동시에 이동 불가면 역시 마찬가지다";
	
    public MainFrame(JPanel StartPanel){
    	try {
        	if(Main.dbConn == null || Main.dbConn.isClosed())
        		setTitle("Game2048 (Off Line)");
        	else
        		setTitle("Game2048 (On Line)");
    	}
    	catch(Exception err) {
    		
    	}

        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        createMenu();
        changePanel(StartPanel);
        setVisible(true);
    }

    //상단 메뉴바
    public void createMenu() {
        
        JMenuItem rank = new JMenuItem("순위보기");
        JMenuItem rule = new JMenuItem("게임규칙");
        JMenuItem main_menu = new JMenuItem("메인 메뉴");
        JMenuItem logout = new JMenuItem("로그아웃");
        

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
                JOptionPane.showMessageDialog(null, gameRule, "게임규칙", JOptionPane.PLAIN_MESSAGE); //게임규칙
            }
        });
        
        main_menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //게임규칙 이벤트
            	setMenuItemVisible(2,true);
            	Main.mainFrame.changePanel(new SelectModePanel());
            }
        });

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	setMenuItemVisible(2,false);
                Main.mainFrame.changePanel(new LogInPanel());
            }
        });
        
        mb.add(mn);

        mn.add(rank);
        mn.add(rule);
        mn.add(main_menu);
        mn.add(logout);
        
        setJMenuBar(mb);
    }
    
    public void setMenuItemVisible(int pos,boolean visible) {
    	mn.getItem(pos).setVisible(visible);
    }
    	
    public void changePanel(JPanel newPanel) {
        getContentPane().removeAll();
        add(newPanel,BorderLayout.CENTER);
        newPanel.setRequestFocusEnabled(true);
        newPanel.setFocusable(true);
        newPanel.requestFocus();
        revalidate();
        repaint();
        
        //process
        if(newPanel instanceof LogInPanel) {
        	mn.getItem(2).setVisible(false);
        	mn.getItem(3).setVisible(false);
        }
        else if(newPanel instanceof SelectModePanel) {
        	mn.getItem(2).setVisible(false);
        	mn.getItem(3).setVisible((Main.dbConn == null)?false:true);
        }
        else if(newPanel instanceof GamePanel) {
        	mn.getItem(2).setVisible(true);
        }
        
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




