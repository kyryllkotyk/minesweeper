import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;

public class NewGui extends JPanel{
   private Difficulty diff = new Difficulty();
   GuiController gui = new GuiController(diff);
   private int x;
   private int y;
   private int grid[][];
   public NewGui(Difficulty dif, int tiles[][]){
      diff = dif;
      x=0;
      y=0;
   }
   public NewGui(){
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
      Color evenSquares = new Color(28,119,34);
      Color oddSquares = new Color(11,152,20);
      Color lightGray = new Color(198, 206, 123);
      Color gray = new Color(153, 153, 153);
      Color one = new Color(4,56,226);
      Color two = new Color(22,129,22);
      Color three = new Color(249,10,0);
      Color four = new Color(129,3,117);
      Color five = new Color(255,128,0);
      Color six = new Color(55,185,172);
      Color seven = new Color(0,0,0);
      Color eight = new Color(82,97,95);
      Color negative = new Color(255, 255, 255);
      Color zero = new Color(150,150,150);
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
            if(grid[i][j] != 0){
               g.fillRect(i*tile,j*tile,tile,tile);
            }
            if(grid[i][j]!=0){
               if(grid[i][j] == 1){
                  g.setColor(one);
               }
               else if(grid[i][j] == 2){
                  g.setColor(two);
               }
               else if(grid[i][j] == 3){
                  g.setColor(three);
               }
               else if(grid[i][j] == 4){
                  g.setColor(four);
               }
               else if(grid[i][j] == 5){
                  g.setColor(five);
               }
               else if(grid[i][j] == 6){
                  g.setColor(six);
               }
               else if(grid[i][j] == 7){
                  g.setColor(seven);
               }
               else if(grid[i][j] == 8){
                  g.setColor(eight);
               }
               else if(grid[i][j] == -1){
                  g.setColor(negative);
               }
              
               g.drawString(""+grid[i][j],i*tile+tile/3,j*tile+tile-10);
            }
          
      }
     }
   }
}
