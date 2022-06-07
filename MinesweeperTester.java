import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MinesweeperTester{
   public static void main(String[] args){
      Difficulty diff = new Difficulty();
      GuiController gui = new GuiController(diff);
      Time t = new Time();
      gui.runGame();
   }
}
