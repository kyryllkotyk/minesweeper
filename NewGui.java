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
      int tile = diff.tileSize();
      g.setColor(background);
      g.fillRect(0,0,x,y);
      g.setColor(oddSquares);
      g.fillRect(0,60,x,y-60);
      g.setColor(evenSquares);
      for(int i = 0; i<diff.colCount(); i++){
         for(int j = 0; j<diff.rowCount(); j++){
            if(((i+j)%2)==0){
               int x1 = i*tile;
               int y1 = j*tile+60;
               g.fillRect(x1,y1,tile,tile);
            }
         }
      }
   }
}
