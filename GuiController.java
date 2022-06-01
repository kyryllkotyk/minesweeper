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
      row = diff.colCount();
      col = diff.rowCount();
   }
   public void newTime(int t){
      time = t;
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
   public int click;
   public int row;
   public int col;
   public boolean[][] flags = new boolean[row][col];
   public boolean[][] bombs = new boolean[row][col];
   public int x1;
   public int y1;
   //use to record x and y of the click and which click it is.
      public void mousePressed(MouseEvent event){
              int x1 = event.getX();
              int y1 = event.getY();
              click = event.getButton();
              whatToDo();
      }
      
      public void whatToDo(){
      int number = diff.getNumberOfMines();
      int flagCount = diff.getNumberOfMines();
      Bombs bomb = new Bombs(bombs, x1, y1, number);
      int count = 0;
      //Left click
         if(click == 1){
            //Might need to be changed to GUI !*!
            //diff.tile(bomb.bombsAdjacent(x, y));
            //Bomb might need to be changed to GUI !*!
            //bomb.leftClick();
            System.out.print("Damn");
         }
         //Middle click
         if(click == 2){
         //Runs through adjacent(and the tile clicked on) and checks for flags
           for(int i = x1 - 1; i <= x1 + 1; i++){
               for(int j = y1 - 1; j <= y1 + 1; j++){
                  //Counts the amount of flags in the proximity
                  if(flags[x1][y1] == true){
                     count++;
                  }
               }
           }
           //Reveals the adjacent 
         /*  if(count == bomb.bombsAdjacent(x1,y1)){
               for(int i = x1 - 1; i <= x1 + 1; i++){
                     for(int j = y1 - 1; j <= y1 + 1; j++){
                         if(isRevealed() == false){
                                 int x2 = x1;
                                 int y2 = y1;
                                 x1 = i;
                                 y1 = j;
                                 tile(bomb.bombsAdjacent(x1, y1));
                                 x1 = x2;
                                 y1 = y2;
                           }
                     }
               }
           }
        */ }
         //Right click
         if(click == 3){
            flags[x1][y1] = !flags[x1][y1];
            if(flags[x1][y1] == true){
               flagCount--;
            }
            else{
               flagCount++;
            }
         }
      }
}
