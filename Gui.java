import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;


public class Gui extends JFrame {
	
	//game & its settings
	private Game game;
   Difficulty d = new Difficulty();
   
	//jbuttons
   //change once i can obtain difficulty methods
	private JButton[][] buttons = new JButton[10][10];
	
	//timer
   private JLabel timeElapsedLabel;
   public int time;
	
	//scores
	private JLabel[][] lastScoresLabel;
	
	//frame
	private final String FRAME_TITLE = "Java Minesweeper";
	private final int FRAME_LOC_X = 100;
	private final int FRAME_LOC_Y = 100;
   public int frameWidth = d.tileSize() * d.colCount() + 200;
   public int frameHeight = d.tileSize() * d.rowCount();
	
	public Gui(Game game) {
		setGame(game);
		
		//initialize time elapsed label 
		setTimeElapsedLabel(new JLabel(Integer.toString(time + " seconds")));
		
		showWelcomeMessage();
		
      //frame layout
		Container contentPane;
		JPanel gameBoard;
		JPanel controlPanel;
		JPanel timePanel;
		JPanel scorePanel;
		JPanel buttonPanel;
		
		setSize(frameWidth, frameHeight);
		setTitle(FRAME_TITLE);
		setLocation(FRAME_LOC_X, FRAME_LOC_Y);
		
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout(10,0));
		
		//build board. CHANGE ACCORDING TO SIZES DECIDED ON
		gameBoard = new JPanel();
		gameBoard.setLayout(new GridLayout(d.rowCount(),d.colCount()));
		//rows
		for (int y = 0; y < d.rowCount(); y++) {
			//cols
			for (int x = 0; x < d.colCount(); x++) {
				//button txt
				buttons[x][y] = new JButton(this.game.getMinefield().getMinefield()[x][y].getContent());
				//button name (x,y)
				buttons[x][y].setName(Integer.toString(x) + "," + Integer.toString(y));
				//add mouseListener
				buttons[x][y].addMouseListener(this);
				//add this button to the gameboard
				gameBoard.add(buttons[x][y]);
			}
		}

		controlPanel = new JPanel();
		controlPanel.setLayout(new BorderLayout());
		
		timePanel = new JPanel();
		timePanel.setBorder(BorderFactory.createTitledBorder("Time elapsed:"));
		timePanel.add(timeElapsedLabel);
		controlPanel.add(timePanel, BorderLayout.NORTH);
		
		//scorepanel; part of controlpanel
		scorePanel = new JPanel();
		scorePanel.setBorder(BorderFactory.createTitledBorder("Last scores:"));
		scorePanel.setLayout(new GridLayout(4,2));
		
		lastScoresLabel = new JLabel[4][2];
		initScores();
		
		for (int i=0; i<lastScoresLabel.length; i++) {
			scorePanel.add(lastScoresLabel[i][0]);
			scorePanel.add(lastScoresLabel[i][1]);
		}
		
		controlPanel.add(scorePanel, BorderLayout.CENTER);
		
		buttonPanel = new JPanel();
		
		//"new game" button
		JButton newGameButton = new JButton("New game");
		newGameButton.setName("newGameButton");
		newGameButton.addMouseListener(this);
		buttonPanel.add(newGameButton);
		
		//"quit game" button
		JButton quitButton = new JButton("Quit game");
		quitButton.setName("quitButton");
		quitButton.addMouseListener(this);
		buttonPanel.add(quitButton);
		
		controlPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		//adds everything to contentpane
		contentPane.add(gameBoard, BorderLayout.CENTER);
		contentPane.add(controlPanel, BorderLayout.EAST);
		
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		
		//doesn't allow player to click on the gameboard (yet)
		disableAll();
	}
	
	private void setGame(Game game) {
		this.game = game;
	}
	
	public Game getGame() {
		return game;
	}
	
	private void settimeElapsedLabel(JLabel timeElapsedLabel) {
		this.timeElapsedLabel = timeElapsedLabel;
	}
	
	public JLabel getTimeElapsedLabel() {
		return timeElapsedLabel;
	}
	
	private void showWelcomeMessage() {
		JOptionPane.showMessageDialog(null, 
				"Welcome!\n\n" +
				"The goal of this game is to detect\n" +
				"all fields with no mines.\n" +
				"By clicking on the fields, you get either a number\n" +
				"indicating the number of surrounding mines\n" +
				"(horizontal, vertical and diagonal), or you step on a mine\n" +
				"and the game ends.\n\n" +
				"GL!" +
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void initGame(String difficulty) {
		hideAll();
		enableAll();
		startTimer();
		game.newGame(difficulty);
	}
	
	//asks player is s/he wants to start new game
	private void newGame() {
		int startNew = JOptionPane.showConfirmDialog(null, "Are you sure you wish to start a new game?", 
				"New game?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);	
		
      switch(startNew) {
			case JOptionPane.YES_OPTION: 
				String player = game.getPlayer();
				String diff = game.getDifficulty();
				
				//check if we should ask the player name and difficulty again.
				while( player == null || diff == null || player.equals("") ) {
					player = "Player";
					diff = askDifficulty();
				}
				
				game.setPlayer(player);
				game.setDifficulty(diff);
				initGame(diff);
				break;		
		  case JOptionPane.NO_OPTION: break;
		  case JOptionPane.CLOSED_OPTION: break;  
		}
	}

	public String askDifficulty() {
		String diff = null;
		
		String[] list = {"Easy", "Normal", "Hard"};
		String answer = null;
		
		while(answer == null){
			answer = (String)JOptionPane.showInputDialog(null, "Choose a difficulty level.", 
				JOptionPane.INFORMATION_MESSAGE, null, list, list[1]);
		}
		
		if (answer.equals(list[0]))
			diff = "Easy";
		else if (answer.equals(list[1]))
			diff = "Normal";
		else if (answer.equals(list[2]))
			diff = "Hard";
		
		return diff;
	}
	
	
		
	//adds and removes flags
	public void addRemoveFlag() {
		if (flagCheck == true) {
			//make panel normal
			//increase flags left by 1
		} else if (flagCheck == false) {
			//add flag
			//decrease flags left by 1
		}
	}

	//counts flags left. make panel showing this later.
	//call flagsleft for every click?
	public int flagsLeft() {
		int initialFlagCt;
		if (diff= "Easy") {
			initialFlagCt = 10;
		} else if (diff = "Normal") {
			initialFlagCt = 40;
		} else if (diff = "Hard") {
			initialFlagCt = 99;
		}
		flagsLeft = initialFlagCt;
		if (flagCheck == true) {
			flagsLeft--;
		}
	}
		
		
	//initiates score list
	private void initScores() {
		for (int i=0; i<lastScoresLabel.length; i++) {
			lastScoresLabel[i][0] = new JLabel();
			lastScoresLabel[i][1] = new JLabel();
		}
	}
	
	//refreshes scores list
	private void refreshScores() {
		for (int i=0; i<lastScoresLabel.length; i++) {
			lastScoresLabel[i][0].setText("  " + this.game.getLastScores()[i][0]);
			lastScoresLabel[i][1].setText("        " + this.game.getLastScores()[i][1]);
		}
	}
	
	private void checkGame() {	
      //if player successfully finishes game	
		if (game.isFinished()) {
			game.setScore( game.getTimeElapsed() );
			endGame();
			JOptionPane.showMessageDialog(null, "Congratulations, you have finished the game!");
			gameOver();
		}
		
	}
	
	//shows 'solution'
	private void showAll() {
		String fieldSolution;
		//rows
		for (int y = 0; y < d.rowCount(); y++) { 
			//cols
			for (int x = 0; x < d.colCount(); x++) {		
           			buttons[x][y].setEnabled(false);
				fieldSolution = this.game.getMinefield().getMinefield()[x][y].getContent();
				//if field is unrevealed
				if (fieldSolution.equals("?"))  {
					fieldSolution = Integer.toString(this.game.getMinefield().getMinefield()[x][y].getNeighbors());
					//if mine
               				if (this.game.getMinefield().getMinefield()[x][y].getMine()) {
						fieldSolution = "M";
						buttons[x][y].setBackground(Color.red);
					} else {
						if (fieldSolution.equals("0"))
							buttons[x][y].setBackground(Color.lightGray);
						else
							buttons[x][y].setBackground(Color.gray);
					}
				}
				
				//if field already flagged
     				if ( fieldSolution.equals("F") ) {
					//correctly flagged?
					if (!this.game.getMinefield().getMinefield()[x][y].getMine()) {
						//no = color orange
						buttons[x][y].setBackground(Color.orange);
					}
				}
				
				buttons[x][y].setText(fieldSolution);
			}		
		}
	}
	
   //makes fields clickable
	private void enableAll() {
		//rows
		for (int y = 0; y < d.rowCount(); y++) {
			//cols
			for (int x = 0; x < d.colCount(); x++) {
				buttons[x][y].setEnabled(true);
			}
		}
	}
	
	//makes fields unclickable (for welcome message/quiting game)
	private void disableAll() {
		//rows
		for (int y = 0; y < d.rowCount(); y++) {
			//cols
			for (int x = 0; x < d.colCount(); x++) {
				buttons[x][y].setEnabled(false);
			}
		}
	}
	

   //hides all fields; makes content "?" and bg white
	private void hideAll() {
		game.getMinefield().resetAll();
     		//rows
		for (int y = 0; y < d.rowCount(); y++) {
			//cols
			for (int x = 0; x < d.colCount(); x++) {
				buttons[x][y].setText("?");
				buttons[x][y].setBackground(Color.white);
			}
		}
	}
	
	private void endGame() {
		game.endGame();
		showAll();
		disableAll();
		game.addScore(game.getPlayer(), game.getScore() );
		refreshScores();
	}
	
	private void gameOver() {
		int startNew = JOptionPane.showConfirmDialog(null, "Game over. Do you want to start a new game?", 
				"New game?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		switch(startNew) {
		
			case JOptionPane.YES_OPTION: 
			
				String player = game.getPlayer();
				String diff = game.getDifficulty();
				
				//check if we should ask for difficulty again.
				if (diff == null) {
					diff = askDifficulty();
				}
				
				game.setPlayer(player);
				game.setDifficulty(diff);
				
				//initializes new game
				initGame(diff);
				
				break;
			
		  case JOptionPane.NO_OPTION: game.gameOver(); break;
		  case JOptionPane.CLOSED_OPTION: game.gameOver(); break;
	  
		}
	}
	
	private void quitGame() {
		int quit = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", 
				"Quit game?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		switch(quit) {
			case JOptionPane.YES_OPTION: 
				game.quitGame();
				break;
			case JOptionPane.NO_OPTION: break;
			case JOptionPane.CLOSED_OPTION: break;
		}
	}
	public int time;
   	public java.util.Timer clock = new Timer();
   	//Returns the value of the time int
   	public int getTime(){
      		return time;
   	}
   	//This handles the timer
   	TimerTask task = new TimerTask(){
      		public void run(){
        time++;
      	}
   };
}
