import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;

public class Gui extends JFrame {

	//game & its settings
	public Game game;
	
	//jbuttons
	private JButton[][] buttons = new JButton[10][10]; //CHANGE BASED ON ROW/COL COUNT
	
	//timer
   	private JLabel timeElapsedLabel;
	private Thread timer;
	
	//scores
	private JLabel[][] lastScoresLabel;
	
	//diff constructor (modify/remove?)
	public Difficulty diff = new Difficulty();
   
	//frame
	private final String FRAME_TITLE = "Minesweeper";
	private final int FRAME_WIDTH = 700;
	private final int FRAME_HEIGHT = 500;
	//private final int FRAME_LOC_X = 100;
	//private final int FRAME_LOC_Y = 100;
	public int frameWidth = diff.tileSize() * diff.colCount() + 200;
   public int frameHeight = diff.tileSize() * diff.rowCount();
	
	public Gui(Game game) {
		setGame(game);
		
		//initialize time elapsed label 
		setTimeElapsedLabel(new JLabel(Integer.toString(time) + " seconds"));
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
		setLocation(FRAME_WIDTH, FRAME_HEIGHT);
		
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout(10,0));
		
		//build board. CHANGE ACCORDING TO SIZES DECIDED ON
		gameBoard = new JPanel();
		gameBoard.setLayout(new GridLayout(diff.rowCount(),diff.colCount()));
		//rows
		for (int y = 0; y < diff.rowCount(); y++) {
			//cols
			for (int x = 0; x < diff.colCount(); x++) {
				//button txt
				buttons[x][y] = new JButton(this.game.getMinefield().getMinefield()[x][y].getContent());
				//button name (x,y)
				buttons[x][y].setName(Integer.toString(x) + "," + Integer.toString(y));
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
   
   public int click;
   int row = diff.colCount();
   int col = diff.rowCount();
   public boolean[][] flags = new boolean[row][col];
   public boolean[][] bombs = new boolean[row][col];
   public int x;
   public int y;
   //use to record x and y of the click and which click it is.
      public void mousePressed(MouseEvent event){
              int x = event.getX();
              int y = event.getY();
              click = event.getButton();
              whatToDo(x, y);
      }
      
      public void whatToDo(int x, int y){
      int number = diff.getNumberOfMines();
      int flagCount = diff.getNumberOfMines();
      Bombs bomb = new Bombs(bombs, x, y, number);
      int count = 0;
      //Left click
         if(click == 1){
            //Might need to be changed to GUI !*!
            //diff.tile(bomb.bombsAdjacent(x, y));
            //Bomb might need to be changed to GUI !*!
            //bomb.leftClick();
         }
         //Middle click
         if(click == 2){
         //Runs through adjacent(and the tile clicked on) and checks for flags
           for(int i = x - 1; i <= x + 1; i++){
               for(int j = y - 1; j <= y + 1; j++){
                  //Counts the amount of flags in the proximity
                  if(flags[x][y] == true){
                     count++;
                  }
               }
           }
           //Reveals the adjacent 
           if(count == bomb.bombsAdjacent(x,y)){
               for(int i = x - 1; i <= x + 1; i++){
                     for(int j = y - 1; i <= y + 1; j++){
 /* Might need to change from diff to GUI */          if(diff.isRevealed() == false){
                                 int x1 = x;
                                 int y1 = y;
                                 x = i;
                                 y = j;
 /* Might need to change from diff to GUI */ diff.tile(bomb.bombsAdjacent(x, y));
                                 x = x1;
                                 y = y1;
                           }
                     }
               }
           }
         }
         //Right click
         if(click == 3){
            flags[x][y] = !flags[x][y];
            if(flags[x][y] == true){
               flagCount--;
            }
            else{
               flagCount++;
            }
         }
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
	
	private void initGame() {
		hideAll();
		enableAll();
		startTimer();
		game.newGame(diff);
	}
	
	//asks player is s/he wants to start new game
	private void newGame() {
		int startNew = JOptionPane.showConfirmDialog(null, "Are you sure you wish to start a new game?", 
				"New game?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);	
		
      switch(startNew) {
			case JOptionPane.YES_OPTION: 
				String player = game.getPlayer();				
				//check if we should ask the player name and difficulty again.
				while( player == null || diff == null || player.equals("") ) {
					player = "Player";
					difficulty = askDifficulty();
				}
				
				game.setPlayer(player);
				game.setDifficulty(difficulty);
				initGame(difficulty);
				break;		
		  case JOptionPane.NO_OPTION: break;
		  case JOptionPane.CLOSED_OPTION: break;  
		}
      time = 0;
      clock.scheduleAtFixedRate(task, 1000, 1000);
	}

	public Difficulty askDifficulty() {
		Difficulty difficulty = null;
		
		String[] list = {"Easy", "Normal", "Hard"};
		String answer = null;
		
		while( answer == null ){
			answer = (String)JOptionPane.showInputDialog(null, "Choose a difficulty level.", 
				JOptionPane.INFORMATION_MESSAGE, null, list, list[1]);
		}
		
		if ( answer.equals( list[0] ) )
			difficulty = newDiff("Easy");
		else if ( answer.equals( list[1] ) )
			difficulty = newDiff("Normal");
		else if ( answer.equals( list[2] ) )
			difficulty = newDiff("Hard");
		
		return difficulty;
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
	public int flagsLeft(int flagCount) {
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
		for (int y = 0; y < Difficulty.rowCount; y++) { 
			//cols
			for (int x = 0; x < Difficulty.colCount; x++) {		
           			buttons[x][y].setEnabled(false);
				fieldSolution = this.game.getMinefield().getMinefield()[x][y].getContent();
				//if field is unrevealed
				if ( fieldSolution.equals("?") ) {
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
		for (int y = 0; y < Difficulty.rowCount; y++) {
			//cols
			for (int x = 0; x < Difficulty.colCount; x++) {
				buttons[x][y].setEnabled(true);
			}
		}
	}
	
	//makes fields unclickable (for welcome message/quiting game)
	private void disableAll() {
		//rows
		for (int y = 0; y < Difficulty.rowCount; y++) {
			//cols
			for (int x = 0; x < Difficulty.colCount; x++) {
				buttons[x][y].setEnabled(false);
			}
		}
	}
	

   //hides all fields; makes content "?" and bg white
	private void hideAll() {
		game.getMinefield().resetAll();
     		//rows
		for (int y = 0; y < Difficulty.rowCount; y++) {
			//cols
			for (int x = 0; x < Difficulty.colCount; x++) {
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
				//check if we should ask for difficulty again.
				if (diff == null) {
					diff = askDifficulty();
				}
				
				game.setPlayer(player);
				game.setDifficulty(difficulty);
				
				//initializes new game
				initGame(difficulty);
				
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
