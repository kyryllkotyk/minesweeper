public class Difficulty{
   public String diff;
   public Difficulty(){
      diff = "Normal";
   }
   //Easy, Normal, or Hard
   public void newDiff(String newDiff){
      diff = newDiff;
   }
   public int rowCount(){
      if(diff.equals("Easy")){
         return 8;
      }
      if(diff.equals("Normal")){
         return 14;
      }
      return 20;
   }
   public int colCount(){
      if(diff.equals("Easy")){
         return 10;
      }
      if(diff.equals("Normal")){
         return 18;
      }
      return 24;
   }
   public int tileSize(){
      if(diff.equals("Easy")){
         return 50;
      }
      if(diff.equals("Normal")){
         return 40;
      }
      return 30;
   }
   public int getNumberOfMines(){
      if(diff.equals("Easy")){
         return 10;
      }
      if(diff.equals("Normal")){
         return 40;
      }
      return 99;
   }
   public String toString(){
      return diff;
   }
}
