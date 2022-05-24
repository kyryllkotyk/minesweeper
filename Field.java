public class Field {
	
	//false if field is safe; true if contains mine
	private boolean mine;
	
   //content of field can be "?" (unknown field), "F" (flagged field), "M" (mine), 0-8 (int neighbors)
	private String content;
	
	//indicates number of surrounding mines
	private int neighbors;

   //every field initialy has no mine; minefield class allocates certain # of fields mines
	public Field() {
		setMine(false);
		setContent("?");
		setNeighbors(0);
	}
	
	public boolean getMine() {
		return mine;
	}

	public void setMine(boolean mine) {
		this.mine = mine;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public int getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(int neighbors) {
		this.neighbors = neighbors;
	}
}
