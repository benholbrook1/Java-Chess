package chess;

public class Move {

    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private boolean isEmpassentCapture;

    public Move(int x1, int y1, int x2, int y2){
        this.startX = x1;
        this.startY = y1;
        this.endX = x2;
        this.endY = y2;
        this.isEmpassentCapture = false;
    }

    public Move(int x1, int y1, int x2, int y2, boolean isEmpassentCapture){
        this.startX = x1;
        this.startY = y1;
        this.endX = x2;
        this.endY = y2;
        this.isEmpassentCapture = isEmpassentCapture;
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

    public boolean getIsEmpassentCapture(){
        return isEmpassentCapture;
    }

    @Override
    public boolean equals(Object o){

        Move c = (Move) o;

        if(c.getStartX() != this.startX) return false;
        if(c.getStartY() != this.startY) return false;
        if(c.getEndX() != this.endX) return false;
        if(c.getEndY() != this.endY) return false;

        return true;
    }

    @Override
    public String toString(){




        return "[" + String.valueOf((char)(startX + 65)) + (startY + 1) + "]" + "->[" + String.valueOf((char)(endX + 65)) + (endY + 1) + "]";
    }
    
}
