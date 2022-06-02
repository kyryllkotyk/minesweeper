import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;

public class NewGui extends JPanel{
   private Difficulty diff;
   private int x;
   private int y;
   private int grid[][];
   public NewGui(Difficulty dif){
      diff = dif;
      x=0;
      y=0;
   }
   public NewGui(){
      diff = new Difficulty();
      x=0;
      y=0;
      grid = new int[diff.colCount()][diff.rowCount()];
   }
   public void setSize(int xNew, int yNew){
      x=xNew;
      y=yNew;
   }
   public void updateGrid(int[][] tiles){
      grid = tiles;
   }
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      Color evenSquares = new Color(0,100,0);
      Color oddSquares = new Color(0,168,0);
      Color lightGray = new Color(204, 204, 204);
      Color gray = new Color(153, 153, 153);
      Color one = new Color(10,100,255);
      Color two = new Color(10,100,255);
      Color three = new Color(10,100,255);
      Color four = new Color(10,100,255);
      Color five = new Color(10,100,255);
      Color six = new Color(10,100,255);
      Color seven = new Color(10,100,255);
      Color eight = new Color(10,100,255);
      int tile = diff.tileSize();
      g.setColor(oddSquares);
      g.fillRect(0,0,x,y-60);
      g.setColor(evenSquares);
      for(int i = 0; i<diff.colCount(); i++){
         for(int j = 0; j<diff.rowCount(); j++){
            if(((i+j)%2)==0){
               int x1 = i*tile;
               int y1 = j*tile;
               g.fillRect(x1,y1,tile,tile);
            }
         }
      }
      Font font = g.getFont().deriveFont( 30.0f );
      g.setFont( font );
      //These are an example of the code needed to write the numbers
      //they are not going to be part of the final version
      //g.setColor(one);
      // g.drawString("1",80,80);
      for(int i = 0; i<diff.colCount(); i++){
         for(int j = 0; j<diff.rowCount(); j++){
            if(i%2==j%2){
               g.setColor(gray);
            }
            else{
               g.setColor(lightGray);
            }
            if(grid[i][j]!=0){
               g.fillRect(i*tile,j*tile,tile,tile);
               g.drawString(""+grid[i][j],i*tile+tile/3,j*tile+tile-10);
            }
         }
      }
   }
}
