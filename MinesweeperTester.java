import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MinesweeperTester{
   public static void main(String[] args){
      //JFrame frame = new JFrame();
      Difficulty diff = new Difficulty();
      /*int sizeX = diff.colCount()*diff.tileSize();
      int sizeY = diff.rowCount()*diff.tileSize()+60;
      frame.setSize(sizeX+11, sizeY);
      frame.setTitle("Minesweeper");
      frame.setLayout(new BorderLayout());
      JPanel north = new JPanel(new FlowLayout());
      north.add(new JLabel("flags:"+12+"     time:"+40));
      frame.add(north, BorderLayout.NORTH);
      NewGui gui = new NewGui();
      gui.setSize(sizeX,sizeY);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(gui, BorderLayout.CENTER);
      frame.setVisible(true);*/
      GuiController gui = new GuiController(diff);
      Time t = new Time();
      gui.runGame();
   }
}
