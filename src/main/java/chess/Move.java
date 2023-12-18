package chess;

public class Move {

    private int startX;
    private int startY;
    private int endX;
    private int endY;

    public Move(int x1, int y1, int x2, int y2){
        this.startX = x1;
        this.startY = y1;
        this.endX = x2;
        this.endY = y2;
    }

    /**
     * Constructor for a Move object with a given String
     * 
     * 
     * @param moveString will have the following format [rank][number][rank][number] (EX. "a2a4")
     * 
     */
    public Move(String moveString) throws Exception{

        if(moveString.length() != 4){
            throw new Exception("Error, invalid move string");
        }

        this.startX = Character.toLowerCase(moveString.charAt(0)) - 'a';
        this.startY = Character.getNumericValue(moveString.charAt(1) - 1);
        
        this.endX = Character.toLowerCase(moveString.charAt(2)) - 'a';
        this.endY = Character.getNumericValue(moveString.charAt(3) - 1);
    }

    public int getStartX(){
        return startX;
    }

    public int getStartY(){
        return startY;
    }

    public int getEndX(){
        return endX;
    }

    public int getEndY(){
        return endY;
    }
    
}
