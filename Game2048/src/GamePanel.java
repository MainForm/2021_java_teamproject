
import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import java.text.SimpleDateFormat;

import Game2048.*;

public class GamePanel extends JPanel {
	Game2048 game;
	private Random rand = new Random();
	JLabel lbl_score = new JLabel("0");
	JLabel lbl_time = new JLabel("00:00");
	
	JLabel[][] lbl_blocks = new JLabel[Game2048.BLOCK_HEIGHT][Game2048.BLOCK_WIDTH];
	
	private LinkedList<Point> Blank = new LinkedList<Point>();
	
	private gameKeyAdaptor keyEvent = new gameKeyAdaptor();
	
	Timer timer_Game;
	
    public GamePanel(Game2048 game){
    	this.game = game;
    	
        setLayout(new BorderLayout());

        createMenu();
        createGameScreen();
       
        
        startGame();
    }
    
    private int blankSet() {
    	Blank.clear();
    	for(int iy=0; iy<Game2048.BLOCK_HEIGHT; iy++) {
    		for(int ix=0; ix<Game2048.BLOCK_WIDTH; ix++) {
    			//빈 블럭인 경우
    			if(game.getBlockValue(ix, iy)==0) {
    				Blank.add(new Point(ix,iy));
    			}
    		}
    	}
    	
    	return Blank.size();
    }
    
    
    private void startGame() {
    	game.start();
    	
    	for(int i = 0;i < 2;i++) {
    		game.addBlock(rand.nextInt(Game2048.BLOCK_WIDTH),rand.nextInt(Game2048.BLOCK_HEIGHT),2);
    	}
    	addKeyListener(keyEvent);
    	
        timer_Game = new Timer(1000,new ActionListener() {
        	int second = 0;
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		second++;
        		
        		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");  
        		
        		lbl_time.setText(sdf.format(new Date(second * 1000)));
        		
        	}
        });
        
        timer_Game.start();
    }
    
	private void GameOver() {
		game.GameOver();
		timer_Game.stop();
		
		//delete key event
		removeKeyListener(keyEvent);
		
		JOptionPane.showMessageDialog(null,"Game Over!","Over",JOptionPane.ERROR_MESSAGE);
	}
    
    private void createMenu(){
        JPanel pnl = new JPanel();

        pnl.setLayout(new FlowLayout());

        pnl.add(new JLabel("Socre:"));
        pnl.add(lbl_score);

        pnl.add(new JLabel("Time:"));
        pnl.add(lbl_time);

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
    	
    	lbl_score.setText(Integer.toString(game.getScore()));
    	
    }
    
    class gameKeyAdaptor extends KeyAdapter{
    	@Override
        public void keyPressed(KeyEvent keyEvent) {
        	int score = 0;
            switch (keyEvent.getKeyCode()) {
                case KeyEvent.VK_UP:
                	score = game.moveUp();
                    break;

                case KeyEvent.VK_DOWN:
                	score = game.moveDown();
                    break;

                case KeyEvent.VK_LEFT:
                	score = game.moveLeft();
                    break;

                case KeyEvent.VK_RIGHT:
                	score = game.moveRight();
                    break;
            }
            
            game.addScore(score);
            
            blankSet();
            
            //add Block if some empty blocks are left
            if(Blank.size() > 0) {
	        	Point ptRandom = Blank.get(rand.nextInt(Blank.size()));
	        	game.addBlock(ptRandom.x,ptRandom.y,2);
            }
            
            //checking game is over
            if(Blank.size() == 0) {
            	boolean bOver = true;
            	checking : for(int iy = 0;iy < Game2048.BLOCK_HEIGHT;iy++) {
            		for(int ix = 0;ix < Game2048.BLOCK_WIDTH;ix++) {
            			if(iy < Game2048.BLOCK_HEIGHT - 1) {
                			if(game.getBlockValue(ix, iy) == game.getBlockValue(ix, iy + 1)) {
                				bOver = false;
                				break checking;
                			}
            			}
            			
            			if(ix < Game2048.BLOCK_WIDTH - 1) {
            				if(game.getBlockValue(ix, iy) == game.getBlockValue(ix + 1, iy)) {
                  				bOver = false;
                				break checking;
                			}
            			}
            		}
            	}
            	
            	if(bOver == true)
            		GameOver();
            	return;
            }
            
            validate();
            repaint();
        }
    }
}

class Point { 
	int x, y; 
	
	public Point(int x,int y) {
		this.x = x;
		this.y = y;
	}
}
