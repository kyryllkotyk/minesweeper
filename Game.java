import java.awt.*;
import javax.swing.*;

//all info of current game kept in this class
public class Game {
	//player (name)
	private String player;
   
   //bombs
   public boolean[][] bombs;
   private int x;
   private int y;
   public int number;
   Bombs b = new Bombs(bombs, x, y, number);
	
	//whether a game is running (true) or not (false).
	private boolean playing; 
	
	//minefield
	private Minefield minefield;
	
	//difficulty settings
	public String diff;
   Difficulty d = new Difficulty();
	
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
		setMinefield(new Minefield(d.getNumberOfMines()));
		setDifficulty("Normal");
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
	
	public String getDifficulty() {
		return diff;
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
		diff = "Normal";
		//start game (timer).
		setPlaying(true);
		//set score.
		setScore(0);		
	}
		
	//checks if game is finished
	public boolean isFinished() {
		boolean isFinished = true;
		String fieldSolution;
		
		//rows
		for (int y = 0; y < d.rowCount(); y++) { 
			//cols
			for (int x = 0; x < d.colCount(); x++) {	
			
				//fieldContent contains the solution of a field
				//if a game is solved, the content of each field on the board must match fieldConten
				fieldSolution = Integer.toString(minefield.getMinefield()[x][y].getNeighbors());
				if(minefield.getMinefield()[x][y].getMine()) fieldSolution = "F";
			
				//compare the player's "answer" to the solution
				if(!minefield.getMinefield()[x][y].getContent().equals(fieldSolution)) {
					//field not solved yet
					isFinished = false;
					x = d.colCount();
					y = d.rowCount();
				}
			
			}
			
		}
		
		return isFinished;
	}
	
	public void gameOver() {
		setPlayer(null);
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
