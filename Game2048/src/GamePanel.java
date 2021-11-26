
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class GamePanel extends JPanel {
	public static final int BLOCK_HEIGHT = 4;
	public static final int BLOCK_WIDTH = 4;
	
	Block[][] Board = new Block[BLOCK_HEIGHT][BLOCK_WIDTH];
	JLabel[][] lbl_blocks = new JLabel[BLOCK_HEIGHT][BLOCK_WIDTH];

    public GamePanel(){
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

                        break;

                    case KeyEvent.VK_DOWN:

                        break;

                    case KeyEvent.VK_LEFT:

                        break;

                    case KeyEvent.VK_RIGHT:
                    	moveRight();
                        break;

                }
                
                validate();
                repaint();
            }
        });
    }
    
    private void startGame() {
    	addBlock(0,0,2);
    	addBlock(2,0,2);
    }
    
    boolean addBlock(int x,int y,int value) {
        //board가 이미 다 차있는 경우
    	if(Board[y][x] != null) return false;

    	Board[y][x] = new Block(value);
        return true;
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
    
    @Override
    public void paint(Graphics g) {
    	super.paint(g);
    	for(int iy = 0;iy < BLOCK_HEIGHT;iy++) {
    		for(int ix = 0;ix < BLOCK_WIDTH;ix++) {
    			if(Board[iy][ix] == null) {
    				lbl_blocks[iy][ix].setBackground(Block.BLOCK_COLORS[0]);
    				lbl_blocks[iy][ix].setText("0");
    				continue;
    			}
    			lbl_blocks[iy][ix].setBackground(Board[iy][ix].getBlockColor());
    			lbl_blocks[iy][ix].setText(Integer.toString(Board[iy][ix].getValue()));
    		}
    	}
    }
    public void moveRight() {
 	   int count = 0;
 	      
        
        for(int x=0; x<4; x++) {
     	   for(int a=2; a>=0; a--) {
     		   for(int b=a; b<3; b++) {
 	              if(Board[x][b+1] == null) {
 	            	 Board[x][b+1] = Board[x][b];
 	            	 Board[x][b] = null;
 	              }
 	              else if(Board[x][b] == Board[x][b+1] && count != 2) {
 	                 count++;
 	                 mergeBlock(x, b+1, x, b);
 	              }
     		   }
     	   }
     	   count = 0;
        }
    }
    public int mergeBlock(int x, int y, int dx, int dy) {
    	Board[x][y].increaseValue();
    	Board[dx][dy] = null;
    	return Board[x][y].getValue();
    }
}

class Point { int height, width; }
