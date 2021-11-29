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
        	JOptionPane.showMessageDialog(null,"���� ���ῡ ���� �߽��ϴ�.","Failed",JOptionPane.ERROR_MESSAGE);
        	mainFrame = new MainFrame(new SelectModePanel());
        	mainFrame.setMenuItemVisible(3, false);
        }
    }
}

class MainFrame extends JFrame{
    //MainPenel mainPenel;
    JMenuBar mb = new JMenuBar(); //�޴��� ����
    JMenu mn = new JMenu("�޴�");
    
    final static String gameRule = "1: ������ �����ϸ� �������� ����2 2���� ���´�.\n"+
    								"2: ���ڸ� ��� ���Ͽ� 2048�� ����� ����Ű�� �ű�� ����� ���� ����Ű�� ���� ���� �̵��Ѵ�\n"+
    								"3: �ű�� ���� ���� ���ڰ� ���� ��� �������� �������� �� ĭ�� �����ϰ� 2�� ���´� \n"+
    								"4: 2048�� ����� �� �̵��� �� ���� ��� �� 16ĭ�� �� �������鼭 ������ �� ĭ�� ���� ���� ��, ���� ������ �Ǹ�, 2048�� ����� ���ÿ� �̵� �Ұ��� ���� ����������";
	
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

    //��� �޴���
    public void createMenu() {
        
        JMenuItem rank = new JMenuItem("��������");
        JMenuItem rule = new JMenuItem("���ӱ�Ģ");
        JMenuItem main_menu = new JMenuItem("���� �޴�");
        JMenuItem logout = new JMenuItem("�α׾ƿ�");
        

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
                JOptionPane.showMessageDialog(null, gameRule, "���ӱ�Ģ", JOptionPane.PLAIN_MESSAGE); //���ӱ�Ģ
            }
        });
        
        main_menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //���ӱ�Ģ �̺�Ʈ
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




