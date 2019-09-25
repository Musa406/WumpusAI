public class Block {
	
	//This class is created only for variable
	
    public static final int NOT_PRESENT = 0; //wumpus is not here
    public static final int UNSURE = 1; //may be
    
    public boolean hasBreeze, hasPit;
    public int pitStatus = UNSURE;
    
    public boolean hasStench, hasWumpus;
    public int wumpusStatus = UNSURE;
    
    public boolean hasGold; //final stated
    public boolean isVisited;
}