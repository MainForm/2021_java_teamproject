import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SelectModePanel extends MenuPanel{
    public SelectModePanel(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        
        
        setFocusable(true);
        requestFocusInWindow();

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


        jPanel.add(newLine(Box.createVerticalStrut(50)));


        //�������� ���
        JButton btn1 = new JButton("�������� ���");
        btn1.setPreferredSize(new Dimension(150, 20));
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.mainFrame.changePanel(new GamePanel());
                // new Game(); //���� ����
                //mainFrame.dispose();
            }
        });
        jPanel.add(newLine(btn1));


        jPanel.add(newLine(Box.createVerticalStrut(30)));

        //���ǵ� ���
        JButton btn2 = new JButton("���ǵ� ���");
        btn2.setPreferredSize(new Dimension(150, 20));
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, Main.gameRule, "���ӱ�Ģ", JOptionPane.PLAIN_MESSAGE); //���ӱ�Ģ
            }
        });
        jPanel.add(newLine(btn2));


        jPanel.add(newLine(Box.createVerticalStrut(30)));

        //�޺� ���
        JButton btn3 = new JButton("�޺� ���");
        btn3.setPreferredSize(new Dimension(150, 20));
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        jPanel.add(newLine(btn3));

        jPanel.add(newLine(Box.createVerticalStrut(30)));

        //�����̹� ���
        JButton btn4 = new JButton("�����̹� ���");
        btn4.setPreferredSize(new Dimension(150, 20));
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // new Game();
                // mainFrame.dispose();
            }
        });
        jPanel.add(newLine(btn4));

        add(jPanel);
    }
}
