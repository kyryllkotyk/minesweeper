import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuiController{
   private int time;
   private int flagCount;
   private Difficulty diff;
   public GuiController(Difficulty dif){
      diff = dif;
      flagCount = diff.getNumberOfMines();
   }
   public void runGame(){
      JFrame frame = new JFrame();
      Difficulty diff = new Difficulty();
      int sizeX = diff.colCount()*diff.tileSize();
      int sizeY = diff.rowCount()*diff.tileSize()+60;
      frame.setSize(sizeX+11, sizeY);
      frame.setTitle("Minesweeper");
      frame.setLayout(new BorderLayout());
      JPanel north = new JPanel(new FlowLayout());
      north.add(new JLabel("flags:"+flagCount+"     time:"+time));
      frame.add(north, BorderLayout.NORTH);
      NewGui gui = new NewGui();
      gui.setSize(sizeX,sizeY);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(gui, BorderLayout.CENTER);
      frame.setVisible(true);
   }
   /*public int click;
   int row = diff.colCount();
   int col = diff.rowCount();
   public boolean[][] flags = new boolean[row][col];
   //use to record x and y of the click and which click it is.
      public void mousePressed(MouseEvent event){
              int x = event.getX();
              int y = event.getY();
              click = event.getButton();
              whatToDo(x, y);
      }
      
      public void whatToDo(int x, int y){
      int number = diff.getNumberOfMines();
      boolean[][] bombs = new boolean[diff.colCount()][diff.rowCount()];
      Bombs bomb = new Bombs(bombs, x, y, number);
      int count = 0;
      //Left click
         if(click == 1){
            //Might need to be changed to GUI !*!
            //diff.tile(bomb.bombsAdjacent(x, y));
            //Bomb might need to be changed to GUI !*!
            //bomb.leftClick();
         }
         //Middle click
         if(click == 2){
         //Runs through adjacent(and the tile clicked on) and checks for flags
           for(int i = x - 1; i <= x + 1; i++){
               for(int j = y - 1; j <= y + 1; j++){
                  //Counts the amount of flags in the proximity
                  if(flags[x][y] == true){
                     count++;
                  }
               }
           }
           //Reveals the adjacent 
           if(count == bomb.bombsAdjacent(x,y)){
               for(int i = x - 1; i <= x + 1; i++){
                     for(int j = y - 1; i <= y + 1; j++){
 /* Might need to change from diff to GUI           if(diff.isRevealed() == false){
                                 int x1 = x;
                                 int y1 = y;
                                 x = i;
                                 y = j;
 /* Might need to change from diff to GUI  diff.tile(bomb.bombsAdjacent(x, y));
                                 x = x1;
                                 y = y1;
                           }
                     }
               }
           }
         }
         //Right click
         if(click == 3){
            flags[x][y] = !flags[x][y];
            if(flags[x][y] == true){
               flagCount--;
            }
            else{
               flagCount++;
            }
         }
      }*/

}
