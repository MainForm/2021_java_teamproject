
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
	public static final int BLOCK_HEIGHT = 4;
	public static final int BLOCK_WIDTH = 4;
	
	JLabel[][] lbl_blocks = new JLabel[BLOCK_HEIGHT][BLOCK_WIDTH];

    public GamePanel(){
        setLayout(new BorderLayout());

        createMenu();
        createGameScreen();
        
        refreshScreen();
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

        for (int iy = 0; iy < BLOCK_HEIGHT; iy++) {
        	for(int ix = 0;ix < BLOCK_WIDTH;ix++) {
	            JLabel jl =  new JLabel("0", JLabel.CENTER);
	            
	            jl.setOpaque(true);
	            jl.setBorder(BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(0xBBADA0)));
	
	            //j.setForeground(new java.awt.Color(0x776E65));
	            jl.setFont(new java.awt.Font("Dialog", 1, 52));
	            
	            lbl_blocks[iy][ix] = jl;
	            pnl.add(jl);
        	}
        }

        add(pnl,BorderLayout.CENTER);
    }
    
    public void refreshScreen() {
    	
    }
}
