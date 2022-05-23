import java.awt.*;
import javax.swing.*;

//all info of current game kept in this class
public class Game {
	//player (name)
	private String player;
	
	//whether a game is running (true) or not (false).
	private boolean playing; 
	
	//minefield
	private Minefield minefield;
	
	//difficulty settings
	private Difficulty difficulty;
	
	// timer settings
	private Thread timer;
	private int timeElapsed;
	
	// scores
	private String[][] lastScores;
	private int score;
	
	//initializes application, not actual game
	public Game(String player) {
		setPlayer(player);
		setPlaying(false);
		setMinefield(new Minefield(Difficulty.NORMAL.getNumberOfMines()));
		setDifficulty(Difficulty.NORMAL);
		setLastScores(new String[4][2]);
		setScore(0);
		resetScores();		
	}
	
	//startup
	public Game() {
		this("");
	}
	
	//sets player name to "player"
  	public void setPlayer(String player) {
		this.player = player;
	}

	public String getPlayer() {
		return player;
	}
	
	public void setPlaying(boolean playing) {
		this.playing = playing;
	}
	
	public boolean isPlaying() {
		return playing;
	}
	
	private void setMinefield(Minefield minefield) {
		this.minefield = minefield;
	}
	
	public Minefield getMinefield() {
		return minefield;
	}
	
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	
	public Difficulty getDifficulty() {
		return difficulty;
	}
	
	public Thread getTimer() {
		return timer;
	}
	
	private void setLastScores(String[][] lastScores) {
		this.lastScores = lastScores;
	}
	
	public String[][] getLastScores() {
		return lastScores;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}
	
   //adds new score to top of lastScores array, every element in array moved one down
   //last element is removed
	public void addScore(String player, int score) {
		for (int i = lastScores.length - 1; i > 0; i--) {
			if (lastScores[i-1][0] == null || lastScores[i-1][1] == null) {
				lastScores[i][0] = "";
				lastScores[i][1] = "";
			} else {
				lastScores[i][0] = lastScores[i-1][0];
				lastScores[i][1] = lastScores[i-1][1];
			}
		}
		lastScores[0][0] = player;
		lastScores[0][1] = Integer.toString(score);
	}
   
	//scores are reset
   private void resetScores() {
		for (int i = 0; i < lastScores.length; i++) {
			lastScores[i][0] = "";
			lastScores[i][1] = "";
		}
	}
	
   /** not necessary (i think)
	public void increaseTime() {
		this.timeElapsed++;
	}
   */
	
	//initializes new game
	public void newGame(Difficulty difficulty) {
		//new minefield
		setMinefield( new Minefield( difficulty.getNumberOfMines() ) );
		//set difficulty.
		setDifficulty(difficulty);
		//start game (timer).
		setPlaying(true);
		//set score.
		setScore(0);		
	}
	
	/**
	 * If a player clicks on a zero, all surrounding fields ("neighbors") must revealed.
	 * This method is recursive: if a neighbour is also a zero, his neighbors must also be revealed...
	 * 
	 * @param buttons
	 * @param xCo
	 * @param yCo
	 * @pre The field (xCo, yCo) must be a "zero" field. 
	 * 	| minefield.getMinefield()[xCo][yCo].getNeighbors() == 0
	 */
	public void findZeroes(JButton[][] buttons, int xCo, int yCo) {
		int neighbors;
		//col
		for(int x = minefield.makeValidCoordinate(xCo - 1); x <= minefield.makeValidCoordinate(xCo + 1); x++) {	
			//row
			for(int y = minefield.makeValidCoordinate(yCo - 1); y <= minefield.makeValidCoordinate(yCo + 1); y++) {
				
				// Only unrevealed fields need to be revealed...
				if(minefield.getMinefield()[x][y].getContent().equals("?")) {
					
					// Get the # neighbors of the current (neighbouring) field.
					neighbors = minefield.getMinefield()[x][y].getNeighbors();
					
					// Reveal the # neighbors of the current (neighbouring) field
					minefield.getMinefield()[x][y].setContent(Integer.toString(neighbors));
					buttons[x][y].setText(Integer.toString(neighbors));
					
					// Is this (neighbouring) field a "zero" field itself?
					if(neighbors == 0){
						// Yes, give it a special color and recurse!
						buttons[x][y].setBackground(Color.lightGray);
						findZeroes(buttons, x, y);
					}else{
						// No, give it a boring gray color.
						buttons[x][y].setBackground(Color.gray);
					}
					
				}
				
			}
			
		}
	}
	
	/**
	 * Checks the game to see if it is finished (true) or not (false).
	 * 
	 * @return true if the game is over, else false
	 */
	public boolean isFinished()
	{
		boolean isFinished = true;
		String fieldSolution;
		
		//rows
		for (int y = 0; y < Difficulty.rowCount; y++) { 
			//cols
			for (int x = 0; x < Difficulty.colCount; x++) {	
			
				// fieldContent contains the solution of a field
				// If a game is solved, the content of each field on the board must match fieldContent.
				fieldSolution = Integer.toString(minefield.getMinefield()[x][y].getNeighbors());
				if(minefield.getMinefield()[x][y].getMine()) fieldSolution = "F";
			
				// Compare the player's "answer" to the solution.
				if(!minefield.getMinefield()[x][y].getContent().equals(fieldSolution)) {
					// This field is not solved yet!
					isFinished = false;
					// No need to check the rest of the minefield: stop the for's.
					x = 10;
					y = 10;
				}
			
			}
			
		}
		
		return isFinished;
	}
	
	public void gameOver()
	{
		setPlayer(null);
		setDifficulty(null);
	}
	
	//ends game
  	public void endGame() {
		setPlaying(false);
	}
	
	//quits game
	public void quitGame() {
		endGame();
		System.exit(0);
	}

}
