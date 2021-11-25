import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LogInPanel extends MenuPanel{
    private JTextField tf_id;
    private JTextField tf_pw;

    public LogInPanel(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

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


        jPanel.add(newLine(Box.createVerticalStrut(100)));

        //ID 입력
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
        pwPanel.add(tf_pw = new JTextField(15));
        pwPanel.add(Box.createHorizontalStrut(150));

        jPanel.add(pwPanel);
        jPanel.add(newLine(Box.createVerticalStrut(10)));

        //Btn
        JPanel confirmPanel = new JPanel();
        confirmPanel.setLayout(new BoxLayout(confirmPanel,BoxLayout.X_AXIS));
        confirmPanel.add(Box.createHorizontalStrut(50));
        JButton btn_OK = new JButton("OK");
        btn_OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(tf_id.getText().equals("Test1") && tf_pw.getText().equals("Password")){
                    Main.mainFrame.changePanel(new SelectModePanel());
                }
                else{
                    JOptionPane.showMessageDialog(null,"틀렸습니다.","Failed",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        confirmPanel.add(btn_OK);
        confirmPanel.add(Box.createHorizontalStrut(50));

        jPanel.add(confirmPanel);

        jPanel.add(newLine(Box.createVerticalStrut(200)));

        add(jPanel);
    }
}