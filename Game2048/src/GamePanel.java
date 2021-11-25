
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel(){
        setLayout(new BorderLayout());

        createMenu();

        createGameScreen();
    }

    private void createMenu(){
        JPanel pnl = new JPanel();

        pnl.setLayout(new FlowLayout());

        pnl.add(new JLabel("Socre:"));
        pnl.add(new JLabel("12345"));

        pnl.add(new JLabel("Time:"));
        pnl.add(new JLabel("00:10:00"));

        add(pnl,BorderLayout.NORTH);
    }

    private void createGameScreen(){
        JPanel pnl = new JPanel();

        pnl.setLayout(new GridLayout(4, 4));
        pnl.setBackground(new java.awt.Color(0xCDC1B4));

        JLabel[] jLabels = new JLabel[16];
        JLabel j;
        for (int i = 0; i < 16; i++) {
            jLabels[i] = new JLabel("0", JLabel.CENTER);
            j = jLabels[i];
            j.setOpaque(true);
            j.setBorder(BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(0xBBADA0)));

            //j.setForeground(new java.awt.Color(0x776E65));
            j.setFont(new java.awt.Font("Dialog", 1, 52));
            pnl.add(j);
        }

        add(pnl,BorderLayout.CENTER);
    }
}
