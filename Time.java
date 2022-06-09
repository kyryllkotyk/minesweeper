import java.util.*;

public class Time{
   public int time;
   private Timer clock;
   private NewGui gui;
   public Time(NewGui GUI){
      gui = GUI;
   }
   //Returns the value of the time int
   public int getTime(){
      return time;
   }
   //Resets the time value and starts the clock
   public void newGame(){
      Timer clock = new Timer();
      time = 0;
      clock.scheduleAtFixedRate(task, 1000, 1000);
   }
   //This handles the timer
   TimerTask task = new TimerTask(){
      public void run(){
         time++;
         gui.setTime(time);
         gui.repaint();
      }
   };
}
