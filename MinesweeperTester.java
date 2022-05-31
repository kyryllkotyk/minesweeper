import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MinesweeperTester{
   public static void main(String[] args){
      JFrame frame = new JFrame();
      Difficulty diff = new Difficulty();
      int sizeX = diff.colCount()*diff.tileSize();
      int sizeY = diff.rowCount()*diff.tileSize()+60;
      frame.setSize(sizeX+11, sizeY+35);
      frame.setTitle("Minesweeper");
      NewGui gui = new NewGui();
      gui.setSize(sizeX,sizeY);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(gui);
      frame.setVisible(true);
   }
}
