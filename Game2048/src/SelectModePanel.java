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

        //게임 메뉴
        JPanel jPanel = new JPanel();

        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        jPanel.add(newLine(Box.createVerticalStrut(25)));

        JLabel jLabel = new JLabel("돌아온 2048");
        jLabel.setForeground(new java.awt.Color(0x776e65));
        jLabel.setFont(new java.awt.Font("맑은 고딕", 1, 60));
        jPanel.add(newLine(jLabel));

        /*
        JLabel author = new JLabel("by xxx");
        jPanel.add(newLine(author));
        */


        jPanel.add(newLine(Box.createVerticalStrut(50)));


        //오리지널 모드
        JButton btn1 = new JButton("오리지널 모드");
        btn1.setPreferredSize(new Dimension(150, 20));
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.mainFrame.changePanel(new GamePanel());
                // new Game(); //게임 시작
                //mainFrame.dispose();
            }
        });
        jPanel.add(newLine(btn1));


        jPanel.add(newLine(Box.createVerticalStrut(30)));

        //스피드 모드
        JButton btn2 = new JButton("스피드 모드");
        btn2.setPreferredSize(new Dimension(150, 20));
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, Main.gameRule, "게임규칙", JOptionPane.PLAIN_MESSAGE); //게임규칙
            }
        });
        jPanel.add(newLine(btn2));


        jPanel.add(newLine(Box.createVerticalStrut(30)));

        //콤보 모드
        JButton btn3 = new JButton("콤보 모드");
        btn3.setPreferredSize(new Dimension(150, 20));
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        jPanel.add(newLine(btn3));

        jPanel.add(newLine(Box.createVerticalStrut(30)));

        //서바이벌 모드
        JButton btn4 = new JButton("서바이벌 모드");
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
