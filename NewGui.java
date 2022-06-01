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
      Color evenSquares = new Color(0,255,0);
      Color oddSquares = new Color(0,168,0);
      Color background = new Color(0,100,0);
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
      /*for(int i = 0; i<grid.length; i++){
         for(int j = 0; j<grid[i].length; j++){
            if(i%2==j%2){
               
            }
            else{
               
            }
         }
      }*/

   }
   //jbuttons
	public JButton[][] buttons = new JButton[diff.colCount()][diff.rowCount()];
   buttons[x1][y1] = new JButton;
   JPanel buttonPanel;
   //finish making buttons out of coordinates. figure out how to do this.
   
   public boolean revealed[][];
   public void revealTile(int bombsAdjacent(x1, y1)) {
      revealed[x1][y1] = true;
      //sets background color to beige or sand dollar
      buttons[x1][y1].setBackground(245, 245, 220);
      if((x1+y1)%2==0) {
         buttons[x1][y1].setBackground(194, 178, 128);
      }
      if(bombs[x1][y1] == true) { //checks if bomb
         buttons[x1][y1].setBackground(Color.RED);
         buttons[x1][y1].setText("M"); //change to image later?
      } else if (bombsAdjacent > 0) { 
            //go through each value of bombs adjacent to assign foreground color
            if (bombsAdjacent == 1) {
               buttons[x1][y1].setForeground(Color.BLUE); 
            }
            if (bombsAdjacent == 2) {
               buttons[x1][y1].setForeground(Color.GREEN); 
            }
            if (bombsAdjacent == 3) {
               buttons[x1][y1].setForeground(Color.RED); 
            }
            if (bombsAdjacent == 4) {
               buttons[x1][y1].setForeground(Color.PURPLE); 
            }
            if (bombsAdjacent == 5) {
               buttons[x1][y1].setForeground(Color.WHITE);
            }
            if (bombsAdjacent == 6) {
               buttons[x1][y1].setForeground(Color.WHITE);
            }
            if (bombsAdjacent == 7) {
               buttons[x1][y1].setForeground(Color.WHITE);
            }
            if (bombsAdjacent == 8) {
               buttons[x1][y1].setForeground(Color.WHITE);
            }
            buttons[x1][y1].setText(bombsAdjacent(x1, y1));
      } else { //nothing; make content blank
         buttons[x1][y1].setText("");
      }
   }
   
   public boolean isRevealed(x1, y1) {
      return revealed[x1][y1];
   }
}
