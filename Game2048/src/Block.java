import javax.swing.*;
import java.awt.*;

public class Block {
	private static Color BLOCK_COLORS[] = new Color[]{
		new Color(0,0,0)
	};

   private int val;

   public Block(int val) {
      this.val = val;
   }
   void increaseValue(int val) {
      this.val = val*2;
   }
   Color getBlockColor() {
      int cnt = 0;
      while (val == 0) {
         val /= 2;
         cnt++;
      }
      return BLOCK_COLORS[cnt];
   }
   
   int getValue() {return val;}
}
