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
   private int time;
   private int grid[][];
   private int mines;
   public NewGui(Difficulty dif){
      diff = dif;
      x=0;
      y=0;
      mines = diff.getNumberOfMines();
      grid = new int[diff.colCount()][diff.rowCount()];
   }
   public NewGui(){
      x=0;
      y=0;
      grid = new int[diff.colCount()][diff.rowCount()];
   }
   public void setTime(int t){
      time = t;
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
      Color black = new Color(0,0,0);
      Color evenSquares = new Color(162,230,72);
      Color oddSquares = new Color(137,208,44);
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
      g.fillRect(0,30,x,y-70);
      g.setColor(evenSquares);
      mines = diff.getNumberOfMines();
      for(int i = 0; i<diff.colCount(); i++){
         for(int j = 0; j<diff.rowCount(); j++){
            if(((i+j)%2)==0){
               int x1 = i*tile;
               int y1 = j*tile;
               g.fillRect(x1,y1+30,tile,tile);
            }
            if(grid[i][j] == 10){
               mines--;
            }
         }
      }
      Font font1 = g.getFont().deriveFont( 30.0f );
      Font font2 = g.getFont().deriveFont( 18.0f );
      g.setFont( font2 );
      g.drawString("Time: "+time+"   Mines:"+mines,tile*diff.colCount()/2-60,18);
      g.setFont( font1 );
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
            if(grid[i][j] != 0 && grid[i][j] != 10){
               g.fillRect(i*tile,j*tile+30,tile,tile);
            }
            if(grid[i][j]!=0 && grid[i][j] != 10){
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
               if(grid[i][j] != -1){
               g.drawString(""+grid[i][j],i*tile+tile/3,j*tile+tile+20);
               }
               else{
                  g.drawString(""+"B", i*tile+tile/3,j*tile+tile+20);
               }
            }
            if(grid[i][j] == 10){
               g.setColor(black);
               g.fillRect(i*tile,j*tile+30,tile,tile);
            }
         }
      }
   }
}
