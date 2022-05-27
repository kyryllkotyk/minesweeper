import java.util.*;
public class Minefield{
	//change based on difficulty
	private int numberOfMines;
   Game game = new Game();
	public Gui gui = new Gui(game);
	//matrix of fields
	private Field minefield[][] = new Field[gui.diff.colCount()][gui.diff.rowCount()];
	
	//array of "mine" coordinates is generated | makeMines()
	//new minefield is generated, based on the previous array of mines | makeMinefield(makeMines())
	//current minefield is set | new.getMinefield() == makeMinefield(makeMines())
	public Minefield(int numberOfMines) {
		setNumberOfMines(numberOfMines);
		setMinefield(makeMinefield(makeMines(gui.x, gui.y, gui.bombs, gui.diff)));
	}
	
	private void setNumberOfMines(int numberOfMines) {
		this.numberOfMines = numberOfMines;
	}
	
	public int getNumberOfMines() {
		return numberOfMines;
	}
	
	private void setMinefield(Field[][] minefield) {
		this.minefield = minefield;
	}
	
	//returns minefield
	public Field[][] getMinefield() {
		return minefield;
	}
	
	//generates 2D array of mine coordinates
	public boolean[][] makeMines(int x, int y, boolean[][] bombs, Difficulty difficulty){
      Random r = new Random();
      int total = difficulty.getNumberOfMines();
		int bombsGenerated = 0;
      while(bombsGenerated<total){
         int x1 = r.nextInt(bombs.length);
         int y1 = r.nextInt(bombs[0].length);
         if(!(x1<=x+1 && y1<=y+1 && x1>=x-1 && y1>=y-1) && !bombs[x1][y1]){
            bombs[x1][y1] = true;
            bombsGenerated++;
         }
      }
      }
	
	public Field[][] makeMinefield(boolean[][] mines) {	
		//fill matrix with fields and distribute the mines
		for( int x = 0; x < 10; x++ ) {
			for( int y = 0; y < 10; y++ ) {
				minefield[x][y] = new Field();
				if(searchMine(mines, x, y)) minefield[x][y].setMine(true);
			}
		}
		//set the neighbors attribute of each Field - change calculateNeighbors to whatever Eric's method is called
		for( int x = 0; x < gui.diff.colCount(); x++) {
			for( int y = 0; y < gui.diff.rowCount(); y++) {
				minefield[x][y].setNeighbors(bombsAdjacent(x,y));
			}
		}
		return minefield;
	}
	
	//checks if coord is in the mines array
	public boolean searchMine(boolean[][] mines, int x, int y) {
		boolean isMine = false;
		//walk through the array.
		for (int i = 0; i < numberOfMines; i++) {
			//check x/y coordinates
			isMine = (mines[i][0] == x && mines[i][1] == y);
			//if coordinate was found, skip to end
			if(isMine) i=numberOfMines;
		}
		return isMine;
	}
	
	//makes x coordinate within bounds of minefield
	public int makeValidXCoordinate(int i) {
		if (i < 0) {
			i = 0;
		} else if (i > diff.colCount() - 1) {
			i = diff.colCount() - 1;
		}
		return i;
	}
   
   //makes y coordinate within boundaries of minefield
   public int makeValidYCoordinate(int j) {
      if (j < 0) {
         j = 0;
      } else if (j > diff.rowCount() - 1) {
         j = diff.rowCount() - 1;
      }
		return j;
	}
      
	
	public void resetAll() {
		//rows
		for (int y = 0; y < gui.diff.rowCount(); y++) { 
			//cols
			for (int x = 0; x < gui.diff.colCount(); x++) {	
				minefield[x][y].setContent("?");
			}
		}
	}
	public int bombsAdjacent(int x, int y){
      boolean containsBomb = bombs[x][y];
      if(containsBomb){
         return -1;
      }
      int adjacent = 0;
      for(int i = x-1; i<=x+1; i++){
         for(int j = y-1; j<=y+1; j++){
            containsBomb = bombs[i][j];
            if(containsBomb){
               adjacent++;
            }
         }
      }
      return adjacent;
   }

}
