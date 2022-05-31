import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;

public class NewGui extends JPanel{
   private Difficulty diff;
   private int x;
   private int y;
   public NewGui(Difficulty dif){
      diff = dif;
      x=0;
      y=0;
   }
   public NewGui(){
      diff = new Difficulty();
      x=0;
      y=0;
   }
   public void setSize(int xNew, int yNew){
      x=xNew;
      y=yNew;
   }
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      Color evenSquares = new Color(0,255,0);
      Color oddSquares = new Color(0,168,0);
      Color background = new Color(0,100,0);
      int tile= diff.tileSize();
      g.setColor(background);
      g.fillRect(0,0,tile*diff.colCount(),tile*diff.rowCount()+40);
      g.setColor(evenSquares);
      g.fillRect(0,40,x,y);
      g.setColor(oddSquares);
      for(int i = 0; i<diff.rowCount(); i++){
         for(int j = 0; j<diff.colCount(); j++){
            if((i+j)%2==0){
               g.fillRect(j*tile,i*tile+40,
               (j+1)*tile, (i+1)*tile+40);
            }
         }
      }
   }
}
