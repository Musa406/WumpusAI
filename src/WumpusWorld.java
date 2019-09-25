
import java.util.Scanner;

class WumpusWorld {
    static Block[][] Board;
    static int boardSize;
    
    public static void main(String[] args) {
   	 Scanner input = new Scanner(System.in);
   	 
   	 System.out.print("Enter the Size(n*n) of the Board: ");
   	boardSize = input.nextInt();
   	 
   	 Board = new Block[boardSize][boardSize];
   	 for(int i=0; i<boardSize; i++) {
   		 Board[i] = new Block[boardSize];
   		 for(int j=0; j<boardSize; j++)
   			 Board[i][j] = new Block();
   	 }
   	 
   	 System.out.print("\nEnter the number of pits: ");
   	 int pits = input.nextInt();
   	 
   	 for(int i=0; i<pits; i++) {
   		 System.out.print("Enter the location of pit " + (i+1) + ": ");
   		 addPit(boardSize-input.nextInt(), input.nextInt()-1);
   	 }
   	 
   	 System.out.print("\nEnter the location of wumpus: ");
   	 addWumpus(boardSize-input.nextInt(), input.nextInt()-1);
   	 
   	 System.out.print("\nEnter the location of gold: ");
   	 addGold(boardSize-input.nextInt(), input.nextInt()-1);
   	 
   	 System.out.print("\nEnter the starting location: ");
   	 int row = boardSize - input.nextInt();
   	 int col = input.nextInt() - 1;
   	 int rPrev = -1, cPrev = -1;
   	 
   	 int moves = 0;
   	 System.out.println("\nInitial state:");
   	 printBoard(row, col);
   	 
   	 if(moves <= boardSize*boardSize)
   		 System.out.println("\nFound gold in " + moves + " moves.");
   	 
   	 input.close();
    }
    
    static void addPit(int r, int c) {
   	 Board[r][c].hasPit = true;
   	 
   	 if(r >= 1)
   		 Board[r-1][c].hasBreeze = true;
   	 if(r <= (boardSize-2))
   		 Board[r+1][c].hasBreeze = true;
   	 if(c >= 1)
   		 Board[r][c-1].hasBreeze = true;
   	 if(c <= (boardSize-2))
   		 Board[r][c+1].hasBreeze = true;
    }
    
    static void addWumpus(int r, int c) {
   	 Board[r][c].hasWumpus = true;
   	 
   	 if(r >= 1)
   		 Board[r-1][c].hasStench = true;
   	 if(r <= (boardSize-2))
   		 Board[r+1][c].hasStench = true;
   	 if(c >= 1)
   		 Board[r][c-1].hasStench = true;
   	 if(c <= (-2))
   		 Board[r][c+1].hasStench = true;
    }
    
    static void addGold(int r, int c) {
   	 Board[r][c].hasGold = true;
    }
    
    static void printBoard(int r, int c) {
   	 for(int i=0; i<boardSize; i++) {
   		 for(int j=0; j<boardSize; j++) {
   			 char charToPrint = '-';
   			 if(r == i && c == j)
   				 charToPrint = '*';
   			 else if(Board[i][j].hasPit)
   				 charToPrint = 'O';
   			 else if(Board[i][j].hasWumpus)
   				 charToPrint = 'X';
   			 else if(Board[i][j].hasGold)
   				 charToPrint = '$';
   			 
   			 System.out.print(charToPrint + "\t");
   		 }
   		 System.out.println();
   	 }
    }
}