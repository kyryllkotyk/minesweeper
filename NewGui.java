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
   
   JPanel buttonPanel;
   public JButton[][] buttons = new JButton[diff.colCount()][diff.rowCount()];
   public void makeButtons() {
      //rows
   	for (int y = 0; y < diff.rowCount(); y++) {
   	   //cols
   	   for (int x = 0; x < diff.colCount(); x++) {
   		   //button name (x,y)
   		   buttons[x][y].setName(Integer.toString(x) + "," + Integer.toString(y));
   		   //add mouseListener, change to mousepoint gui thing
   		   buttons[x][y].addMouseListener(this);
            //button txt
            //if revealed == true -> show text, if revealed == false -> null
   		   buttons[x][y] = new JButton(); //add text once revealed? will need to access both bombs boolean and bombsAdjacent
         }
      }
   }
   
   public boolean revealed[][];
   public void revealTile(int bombsAdjacent(int x1, int y1)) {
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
               buttons[x1][y1].setForeground(Color.one); 
            }
            if (bombsAdjacent == 2) {
               buttons[x1][y1].setForeground(Color.two); 
            }
            if (bombsAdjacent == 3) {
               buttons[x1][y1].setForeground(Color.three); 
            }
            if (bombsAdjacent == 4) {
               buttons[x1][y1].setForeground(Color.four); 
            }
            if (bombsAdjacent == 5) {
               buttons[x1][y1].setForeground(Color.five);
            }
            if (bombsAdjacent == 6) {
               buttons[x1][y1].setForeground(Color.six);
            }
            if (bombsAdjacent == 7) {
               buttons[x1][y1].setForeground(Color.seven);
            }
            if (bombsAdjacent == 8) {
               buttons[x1][y1].setForeground(Color.eight);
            }
            buttons[x1][y1].setText(bombsAdjacent(x1, y1));
      } else { //nothing; make content blank
         buttons[x1][y1].setText("");
      }
   }
   
   public boolean isRevealed(int x1, int y1) {
      return revealed[x1][y1];
   }
}
