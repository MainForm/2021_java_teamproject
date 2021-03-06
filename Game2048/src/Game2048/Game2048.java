package Game2048;

import java.awt.*;
import java.util.*; //for Random class

public abstract class Game2048 {
	public static final int BLOCK_HEIGHT = 4;
	public static final int BLOCK_WIDTH = 4;
	
	private Block[][] Board = new Block[BLOCK_HEIGHT][BLOCK_WIDTH];
	
	protected int score = 0;
	
	private boolean __bOver = false;
	
    public boolean addBlock(int x,int y,int value) {
        //board가 이미 다 차있는 경우
    	if(Board[y][x] != null) return false;

    	Board[y][x] = new Block(value);
        return true;
    }
    
    
    public void start() {
    	score = 0;
    	__bOver = false;
    	
    	Board = new Block[BLOCK_HEIGHT][BLOCK_WIDTH];
    
    }
    
    public void GameOver() {
    	__bOver = true;
    }
	
    public int moveRight() {
  	   int count = 0;
  	   int score = 0;
  	      
         for(int x=0; x<4; x++) {
      	   for(int a=2; a>=0; a--) {
      		   for(int b=a; b<3; b++) {
  	              if(Board[x][b+1] == null) {
  	            	 Board[x][b+1] = Board[x][b];
  	            	 Board[x][b] = null;
  	              }
  	              else if(Board[x][b] == null) {
  	            	  continue;
  	              }
  	              else if(Board[x][b].getValue() == Board[x][b+1].getValue() && count != 2) {
	                     if(count == 1 && b == 2)
  	                    	 continue;
  	            	  count++;
  	                 score += mergeBlock(x, b+1, x, b);
  	              }
      		   }
      	   }
      	   count = 0;
         }
         return score;
     }
     
     
     public int moveLeft() {
  	   int count = 0;
  	   int score = 0;
  	   
  	   for(int x=0; x<4; x++) {
  		   for(int a=0; a<3; a++) {
  			   for(int b=a; b>=0; b--) {
  				   if(Board[x][b] == null) {
  					   Board[x][b] = Board[x][b+1];
  					   Board[x][b+1] = null;
  				   }
  				   else if(Board[x][b+1] == null) {
  					   continue;
  				   }
  				   else if(Board[x][b].getValue() == Board[x][b+1].getValue() && count != 2) {
	                     if(count == 1 && b == 2)
  	                    	 continue;
  					   count++;
  					   score += mergeBlock(x, b, x, b+1);
  				   }
  			   }
  		   }
  		   count = 0;
  	   }
  	   return score;
     }
     
     public int moveDown() {
  	   int count = 0;
  	   int score = 0;
  	      
  	   for(int y=0; y<4; y++) {
      	  for(int a=2; a>=0; a--) {
      		  for(int b=a; b<3; b++) {
      			  if(Board[b+1][y] == null) {
      				 Board[b+1][y] = Board[b][y];
      				 Board[b][y] = null;
                   }
 				  else if(Board[b][y] == null) {
  					   continue;
  				  }
                   else if(Board[b][y].getValue() == Board[b+1][y].getValue() && count != 2) {
	                     if(count == 1 && b == 2)
  	                    	 continue;
                	   count++;
                       score += mergeBlock(b+1, y, b, y);
                   }
      		   }
      	   }
      	   count = 0;
         }
  	   
  	   return score;
     }
     
     public int moveUp() {
    	 int count = 0;
    	 int score = 0;
  	      
  	      for(int y=0; y<4; y++) {
  	    	  for(int a=0; a<3; a++) {
  	    		  for(int b=a; b>=0; b--) {
  	    			  if(Board[b][y] == null) {
  	                     Board[b][y] = Board[b+1][y];
  	                     Board[b+1][y] = null;
  	                  }
  					  else if(Board[b+1][y] == null) {
  	 					   continue;
  	 				  }
  	                  else if(Board[b][y].getValue() == Board[b+1][y].getValue() && count != 2) {
  	                     if(count == 1 && b == 2)
  	                    	 continue;
  	                	  count++;
  	                	score += mergeBlock(b, y, b+1, y);
  	                  }
  	    		  }
  	    	  }
  	    	  count = 0;
  	      }
  	      
  	      return score;
     }
     
     public int getBlockValue(int x,int y) {
    	 if(Board[y][x] == null)
    		 return 0;
    	 
    	 return Board[y][x].getValue();
     }
     
     public Color getBlockColor(int x,int y) {
    	 if(Board[y][x] == null)
    		 return Block.BLOCK_COLORS[0];
    	 
    	 return Board[y][x].getBlockColor();
     }
     
     private int mergeBlock(int x, int y, int dx, int dy) {
     	Board[x][y].increaseValue();
     	Board[dx][dy] = null;
     	return Board[x][y].getValue();
     }
     
     public boolean isOver() {
    	 return __bOver;
     }
     
     public int getScore() {
    	 return score;
     }
     
     public abstract int addScore(int value);
}
