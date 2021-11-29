package Game2048;
import javax.swing.*;
import java.awt.*;

public class Block {
	   public static final Color BLOCK_COLORS[] = new Color[]{
			      new Color(255,255,255),         //0 
			      new Color(255,204,255),            //2
			      new Color(255,153,255),            //4
			      new Color(255,102,255),            //8
			      new Color(204,153,255),            //16
			      new Color(153,102,255),            //32
			      new Color(153,102,204),            //64
			      new Color(204,153,204),            //128
			      new Color(153,102,153),            //256
			      new Color(204,102,204),            //512
			      new Color(204,51,204),            //1024
			      new Color(255,0,255)            //2048
			      //http://www.n2n.pe.kr/lev-1/color.htm
			   };


   private int val;

   public Block(int val) {
      this.val = val;
   }
   void increaseValue() {
      this.val *= 2;
   }
   Color getBlockColor() {
      int cnt = 0;
      
      int tmp = val;
      while (tmp >= 2) {
    	  tmp /= 2;
         cnt++;
      }
      return BLOCK_COLORS[cnt];
   }
   
   int getValue() {return val;}
}
