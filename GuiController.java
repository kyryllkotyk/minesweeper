import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class GuiController extends MouseInputAdapter{
   private int time;
   private int flagCount;
   private Time timer;
   private Difficulty diff;
   public Bombs bomb;
   public GuiController(Difficulty dif){
      diff = dif;
      flagCount = diff.getNumberOfMines();
      row = diff.colCount();
      col = diff.rowCount();
      flags = new boolean[row][col];
      bombs = new boolean[row][col];
      numbers = new int[row][col];
   }
   public void newTime(int t){
      time = t;
   }
   public void runGame(){
      JFrame frame = new JFrame();
      frame.addMouseListener(this);
      Difficulty diff = new Difficulty();
      int sizeX = diff.colCount()*diff.tileSize();
      int sizeY = diff.rowCount()*diff.tileSize()+70;
      frame.setSize(sizeX+11, sizeY);
      frame.setTitle("Minesweeper");
      gui = new NewGui(diff);
      gui.setSize(sizeX,sizeY);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(gui, BorderLayout.CENTER);
      frame.setVisible(true);
   }
   public NewGui gui;
   public int click;
   public int row;
   public int col;
   public boolean[][] flags;
   public boolean[][] bombs;
   public int[][] numbers;
   public int x1;
   public int y1;
   //use to record x and y of the click and which click it is.
   public void mousePressed(MouseEvent event){
      x1 = event.getX()/diff.tileSize();
      y1 = (event.getY()-20-diff.tileSize())/diff.tileSize();
      click = event.getButton();
      whatToDo();
   }
   public void whatToDo(){
      int number = diff.getNumberOfMines();
      int flagCount = diff.getNumberOfMines();
      if(bomb ==null){
         bomb = new Bombs(bombs, x1, y1, number);
         timer = new Time(gui);
         timer.newGame();
      }
      int count = 0;
      //Left click
      if(click == 1){
         System.out.print("left");
         if(numbers[x1][y1] != 10){
            numbers[x1][y1] = bomb.bombsAdjacent(x1, y1);
            int x2 = x1;
            int y2 = y1;
            if(numbers[x1][y1] == 0){
               numbers[x1][y1] = 9;
               for(int i = x1 - 1; i <= x1 + 1; i++){
                  for(int j = y1 - 1; j <= y1 + 1; j++){
                     if(i >= 0 && j >= 0 && i <= 17 && j <= 13){
                        x2 = i;
                        y2 = j;  
                        numbers[x2][y2] = bomb.bombsAdjacent(x2,y2);
                        if(numbers[x2][y2] == 0){
                           numbers[x2][y2] = 9;
                           zero(i,j);
                        }
                     //if 0, x3 y3, then replace x1 y1 with it.
                     }
                  }
               }
            
               x1 = x2;
               y1 = y2;
            }
         }
         gui.updateGrid(numbers);
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
         if(numbers[x1][y1] == 0 || numbers[x1][y1] == 10){
            if(flags[x1][y1] == true){
               flagCount--;
               numbers[x1][y1] = 10;
            }
            else{
               flagCount++;
               numbers[x1][y1] = 0;
            }
         }
         gui.updateGrid(numbers);
         System.out.print("right");
      }
      gui.repaint();
   }
   public void zero(int x3, int y3){
      for(int i = x3 - 1; i <= x3 + 1; i++){
         for(int j = y3 - 1; j <= y3 + 1; j++){
            if(i >= 0 && j >= 0 && i <= 17 && j <= 13){
               int x2 = i;
               int y2 = j;  
               if(numbers[x2][y2] != 9){
                  numbers[x2][y2] = bomb.bombsAdjacent(x2,y2);
               }
               if(numbers[x2][y2] == 0){
                  numbers[x2][y2] = 9;
                  zero(i,j);
               }
            }
         }
      }
   }
}
