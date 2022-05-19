public enum Difficulty {
	
	EASY {
      		public int rowCount() {
         		return 8;
      		}
      
      		public int colCount() {
			return 10;
      		}
      
		public int getNumberOfMines() {
			return 10;
		}
		
		public String toString() {
			return "Easy";
		}
		
	},
   
	NORMAL {
		public int rowCount() {
         		return 14;
      		}
      
      		public int colCount() {
        		return 18;
      		}

		public int getNumberOfMines() {
			return 40;
		}
		
		public String toString() {
			return "Normal";
		}
		
	},
   
	HARD {
      		public int rowCount() {
         		return 20;
     		}
      
      		public int colCount() {
         		return 24;
      		}
		
		public int getNumberOfMines() {
			return 99;
		}
		
		public String toString() {
			return "Hard";
		}
		
	};
	
	abstract int getNumberOfMines();
}
