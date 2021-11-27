
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GamePanel extends JPanel {
	Game2048 game;
	
	JLabel[][] lbl_blocks = new JLabel[Game2048.BLOCK_HEIGHT][Game2048.BLOCK_WIDTH];

    public GamePanel(Game2048 game){
    	this.game = game;
    	
    	addEvent();
    	
        setLayout(new BorderLayout());

        createMenu();
        createGameScreen();
        
        startGame();
   
    }
    
    private void addEvent() {
    	addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent keyEvent) {
            	
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_UP:
                    	game.moveUp();
                        break;

                    case KeyEvent.VK_DOWN:
                    	game.moveDown();
                        break;

                    case KeyEvent.VK_LEFT:
                    	game.moveLeft();
                        break;

                    case KeyEvent.VK_RIGHT:
                    	game.moveRight();
                        break;

                }
                
                validate();
                repaint();
            }
        });
    }
    
    private void startGame() {
    	game.addBlock(0,0,4);
    	game.addBlock(1,0,4);
    	game.addBlock(2,0,2);
    	game.addBlock(3,0,2);
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

        for (int iy = 0; iy < Game2048.BLOCK_HEIGHT; iy++) {
        	for(int ix = 0;ix < Game2048.BLOCK_WIDTH;ix++) {
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
    
    @Override
    public void paint(Graphics g) {
    	super.paint(g);
    	for(int iy = 0;iy < Game2048.BLOCK_HEIGHT;iy++) {
    		for(int ix = 0;ix < Game2048.BLOCK_WIDTH;ix++) {
    			lbl_blocks[iy][ix].setBackground(game.getBlockColor(ix, iy));
    			lbl_blocks[iy][ix].setText(Integer.toString(game.getBlockValue(ix, iy)));
    		}
    	}
    }
}
