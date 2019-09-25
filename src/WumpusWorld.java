
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
   	 
   	 while(!Board[row][col].hasGold) {
   		 
   		 Board[row][col].isVisited = true;
   		 Board[row][col].pitStatus = Block.NOT_PRESENT;
   		 Board[row][col].wumpusStatus = Block.NOT_PRESENT;
   		 
   		 if(!Board[row][col].hasBreeze) {
   			 if(row >= 1 && Board[row-1][col].pitStatus == Block.UNSURE)
   				 Board[row-1][col].pitStatus = Block.NOT_PRESENT;
   			 if(row <= (boardSize-2) && Board[row+1][col].pitStatus == Block.UNSURE)
   				 Board[row+1][col].pitStatus = Block.NOT_PRESENT;
   			 if(col >= 1 && Board[row][col-1].pitStatus == Block.UNSURE)
   				 Board[row][col-1].pitStatus = Block.NOT_PRESENT;
   			 if(col <= (boardSize-2) && Board[row][col+1].pitStatus == Block.UNSURE)
   				 Board[row][col+1].pitStatus = Block.NOT_PRESENT;
   		 }
   		 
   		 if(!Board[row][col].hasStench) {
   			 if(row >= 1 && Board[row-1][col].wumpusStatus == Block.UNSURE)
   				 Board[row-1][col].wumpusStatus = Block.NOT_PRESENT;
   			 if(row <= (boardSize-2) && Board[row+1][col].wumpusStatus == Block.UNSURE)
   				 Board[row+1][col].wumpusStatus = Block.NOT_PRESENT;
   			 if(col >= 1 && Board[row][col-1].wumpusStatus == Block.UNSURE)
   				 Board[row][col-1].wumpusStatus = Block.NOT_PRESENT;
   			 if(col <= (boardSize-2) && Board[row][col+1].wumpusStatus == Block.UNSURE)
   				 Board[row][col+1].wumpusStatus = Block.NOT_PRESENT;
   		 }
 
   		 boolean foundNewPath = false;
   		 
   		 if(row >= 1 && !((row-1) == rPrev && col == cPrev) && Board[row-1][col].isVisited == false && Board[row-1][col].pitStatus == Block.NOT_PRESENT && Board[row-1][col].wumpusStatus == Block.NOT_PRESENT) {
   			 rPrev = row;
   			 cPrev = col;
   			 
   			 row--;
   			 foundNewPath = true;
   		 }
   		 else if(row <= (boardSize-2) && !((row+1) == rPrev && col == cPrev) && Board[row+1][col].isVisited == false && Board[row+1][col].pitStatus == Block.NOT_PRESENT && Board[row+1][col].wumpusStatus == Block.NOT_PRESENT) {
   			 rPrev = row;
   			 cPrev = col;
   			 
   			 row++;
   			 foundNewPath = true;
   		 }
   		 else if(col >= 1 && !(row == rPrev && (col-1) == cPrev) && Board[row][col-1].isVisited == false && Board[row][col-1].pitStatus == Block.NOT_PRESENT && Board[row][col-1].wumpusStatus == Block.NOT_PRESENT) {
   			 rPrev = row;
   			 cPrev = col;
   			 
   			 col--;
   			 foundNewPath = true;
   		 }
   		 else if(col <= (boardSize-2) && !(row == rPrev && (col+1) == cPrev) && Board[row][col+1].isVisited == false && Board[row][col+1].pitStatus == Block.NOT_PRESENT && Board[row][col+1].wumpusStatus == Block.NOT_PRESENT) {
   			 rPrev = row;
   			 cPrev = col;
   			 
   			 col++;
   			 foundNewPath = true;
   		 }
   		 
   		 if(!foundNewPath) {
   			 int temp1 = rPrev;
   			 int temp2 = cPrev;
   			 
   			 rPrev = row;
   			 cPrev = col;
   			 
   			 row = temp1;
   			 col = temp2;
   		 }
   		 
   		 moves++;
 
   		 System.out.println("\n\nMove " + moves + ":");
   		 printBoard(row, col);
 
   		 if(moves > boardSize*boardSize) {
   			 System.out.println("\nNo solution found!");
   			 break;
   		 }
   	 }
   	 
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