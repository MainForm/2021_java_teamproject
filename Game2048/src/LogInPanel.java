import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import java.sql.*;

public class LogInPanel extends MenuPanel{
    private JTextField tf_id;
    private JPasswordField tf_pw;

    public LogInPanel(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        //���� �޴�
        JPanel jPanel = new JPanel();

        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        jPanel.add(newLine(Box.createVerticalStrut(25)));

        JLabel jLabel = new JLabel("���ƿ� 2048");
        jLabel.setForeground(new java.awt.Color(0x776e65));
        jLabel.setFont(new java.awt.Font("���� ���", 1, 60));
        jPanel.add(newLine(jLabel));

        /*
        JLabel author = new JLabel("by xxx");
        jPanel.add(newLine(author));
        */


        jPanel.add(newLine(Box.createVerticalStrut(100)));

        //ID �Է�
        JPanel idPanel = new JPanel();
        idPanel.setLayout(new BoxLayout(idPanel,BoxLayout.X_AXIS));
        idPanel.add(Box.createHorizontalStrut(150));
        idPanel.add(new JLabel("ID  "));
        idPanel.add(Box.createHorizontalStrut(10));
        idPanel.add(tf_id = new JTextField(20));
        idPanel.add(Box.createHorizontalStrut(150));

        jPanel.add(idPanel);

        jPanel.add(newLine(Box.createVerticalStrut(10)));

        //Password
        JPanel pwPanel = new JPanel();
        pwPanel.setLayout(new BoxLayout(pwPanel,BoxLayout.X_AXIS));
        pwPanel.add(Box.createHorizontalStrut(150));
        pwPanel.add(new JLabel("PW"));
        pwPanel.add(Box.createHorizontalStrut(10));
        pwPanel.add(tf_pw = new JPasswordField(15));
        pwPanel.add(Box.createHorizontalStrut(150));

        jPanel.add(pwPanel);
        jPanel.add(newLine(Box.createVerticalStrut(10)));
        
        tf_pw.addKeyListener(new LoginEventAdoptor());
        tf_id.addKeyListener(new LoginEventAdoptor());

        //Btn
        JPanel confirmPanel = new JPanel();
        confirmPanel.setLayout(new BoxLayout(confirmPanel,BoxLayout.X_AXIS));
        confirmPanel.add(Box.createHorizontalStrut(50));
        JButton btn_SignIn = new JButton("Sign in");
        btn_SignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	LogIn();
            }
        });
        
        JButton btn_SignUp = new JButton("Sign up");
        btn_SignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	SignUp();
            }
        });
        
        
        confirmPanel.add(btn_SignIn);
        confirmPanel.add(Box.createHorizontalStrut(10));
        confirmPanel.add(btn_SignUp);
        confirmPanel.add(Box.createHorizontalStrut(50));

        jPanel.add(confirmPanel);

        jPanel.add(newLine(Box.createVerticalStrut(200)));

        add(jPanel);
    }
    
    public void LogIn() {
    	try {
	        if(Main.dbConn.SignIn(tf_id.getText(),tf_pw.getText()) == true){
	            Main.mainFrame.changePanel(new SelectModePanel());
	        }
	        else{
	            JOptionPane.showMessageDialog(null,"Ʋ�Ƚ��ϴ�.","Failed",JOptionPane.ERROR_MESSAGE);
	        }
    	}
    	catch(SQLException err) {
    		System.out.println("Failed to connect Server");
    	}
    }
    public void SignUp() {
    	try {
    		if(tf_id.getText().trim().length() == 0) {
    			JOptionPane.showMessageDialog(null,"���̵� �Է��� �ּ���","Failed",JOptionPane.ERROR_MESSAGE);
    			return;
    		}
    		if(tf_pw.getText().trim().length() == 0) {
    			JOptionPane.showMessageDialog(null,"��� ��ȣ�� �Է��� �ּ���","Failed",JOptionPane.ERROR_MESSAGE);
    			return;
    		}
	        if(Main.dbConn.SignUp (tf_id.getText(),tf_pw.getText()) == true){
	        	JOptionPane.showMessageDialog(null,"ȸ������ �Ǿ����ϴ�.","OK!",JOptionPane.INFORMATION_MESSAGE);
	            Main.mainFrame.changePanel(new SelectModePanel());
	        }
	        else{
	            JOptionPane.showMessageDialog(null,"�̹� ������ ���̵� �ֽ��ϴ�.","Failed",JOptionPane.ERROR_MESSAGE);
	        }
    	}
    	catch(SQLException err) {
    		System.out.println("Failed to connect Server");
    	}
    }
    
    class LoginEventAdoptor extends KeyAdapter{
    	@Override
        public void keyPressed(KeyEvent e) { 
	   	     int key = e.getKeyCode();
	   	     if (key == KeyEvent.VK_ENTER) {
	   	        Toolkit.getDefaultToolkit().beep(); 
	   	        LogInPanel.this.LogIn();
	   	     }

    	}
    }
}