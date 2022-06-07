import java.util.*;

public class Bombs{
   private boolean[][] grid;
   //accepts an empty 2d boolean array and the x and y coodrinates of the first click and the number of bombs to generate
   public Bombs(boolean[][] bombs, int x, int y, int number){
      int total = number;
      Random r = new Random();
      grid = bombs;
      int bombsGenerated = 0;
      while(bombsGenerated<total){
         int x1 = r.nextInt(bombs.length);
         int y1 = r.nextInt(bombs[0].length);
         if(!(x1<=x+1 && y1<=y+1 && x1>=x-1 && y1>=y-1) && !bombs[x1][y1]){
            grid[x1][y1] = true;
            bombsGenerated++;
         }
      }
   }
   
   //returns the number of bombs adjacent to the given coordinates
   //returns -1 in that tile is a bomb
   public int bombsAdjacent(int x, int y){
      boolean containsBomb = grid[x][y];
      if(containsBomb){
         return -1;
      }
      int adjacent = 0;
      for(int i = x-1; i<=x+1; i++){
         for(int j = y-1; j<=y+1; j++){
            if(!(i<0 || j<0 || i>grid.length-1 || j>grid[0].length-1)){
               containsBomb = grid[i][j];
            }
            if(containsBomb){
               adjacent++;
            }
         }
      }
      return adjacent;
   }
}
