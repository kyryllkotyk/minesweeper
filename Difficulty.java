public class Difficulty extends Gui {
   
   public int rowCount(int difficulty) {
      if (difficulty == 0) {
         return 8;
      }
      if (difficulty == 1) {
         return 14;
      } 
      if (difficulty == 2) {
         return 20;
      }
   }
      
   public int colCount(int difficulty) {
      if (difficulty == 0) {
         return 10;
      }
      if (difficulty == 1) {
         return 18;
      } 
      if (difficulty == 2) {
         return 24;
      }   }
      
   public int tileSize(int difficulty) {
	if (difficulty == 0) {
         return 50;
      }
      if (difficulty == 1) {
         return 40;
      } 
      if (difficulty == 2) {
         return 30;
      }
   }
      
   public int getNumberOfMines(int difficulty) {
	if (difficulty == 0) {
         return 10;
      }
      if (difficulty == 1) {
         return 40;
      } 
      if (difficulty == 2) {
         return 99;
      }
   }
		
   public String toString(int difficulty) {
	if (difficulty == 0) {
         return "Easy";
      }
      if (difficulty == 1) {
         return "Normal";
      } 
      if (difficulty == 2) {
         return "Hard";
      }
   }
}
