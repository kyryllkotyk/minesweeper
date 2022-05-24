public class Minefield implements Difficulty {
   //change based on difficulty
	private int numberOfMines;
	
	//matrix of fields
	private Field minefield[][] = new Field[Difficulty.colCount][Difficulty.rowCount];
	
	//array of "mine" coordinates is generated | makeMines()
	//new minefield is generated, based on the previous array of mines | makeMinefield(makeMines())
	//current minefield is set | new.getMinefield() == makeMinefield(makeMines())
	public Minefield(int numberOfMines) {
		setNumberOfMines(numberOfMines);
		setMinefield(makeMinefield(makeMines()));
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
	public int[][] makeMines() {
		int[][] mines = new int[numberOfMines][2];
		int x,y;
		boolean isTaken;
		for(int mine = 0; mine < numberOfMines; mine++) {
			//random x
			x = (int)Math.floor(Math.random() * Difficulty.colCount);
			//random y
			y = (int)Math.floor(Math.random() * Difficulty.rowCount);
			
      //i forgor what this is for
			x = 4;
			y = 4;
			
			isTaken = true;
			while(isTaken) {
				isTaken = searchMine(mines, x, y);
				if(isTaken) {
					//generate new x/y coords
					x = (int)Math.floor(Math.random() * Difficulty.colCount());
					y = (int)Math.floor(Math.random() * Difficulty.rowCount());
				}
			}	
			// add to mines array
			mines[mine][0] = x;
			mines[mine][1] = y;	
		}
		return mines;
	}
	
	public Field[][] makeMinefield(int[][] mines) {	
		//fill matrix with fields and distribute the mines
		for( int x = 0; x < 10; x++ ) {
			for( int y = 0; y < 10; y++ ) {
				minefield[x][y] = new Field();
				if(searchMine(mines, x, y)) minefield[x][y].setMine(true);
			}
		}
		//set the neighbors attribute of each Field - change calculateNeighbors to whatever Eric's method is called
		for( int x = 0; x < diff.getColCount(); x++) {
			for( int y = 0; y < diff.getRowCount(); y++) {
				minefield[x][y].setNeighbors(calculateNeighbors(x,y));
			}
		}
		return minefield;
	}
	
	//checks if coord is in the mines array
	public boolean searchMine(int[][] mines, int x, int y) {
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
		for (int y = 0; y < diff.rowCount(); y++) { 
			//cols
			for (int x = 0; x < diff.colCount(); x++) {	
				minefield[x][y].setContent("?");
			}
		}
	}
	
}

