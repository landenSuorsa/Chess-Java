import java.util.ArrayList;

public class Piece {
    public String type;
    public int player;
    public int row, col;
    public int xPos, yPos;
    public int value;
    

    public Piece(String type, int player, int row, int col, int xPos, int yPos, int value) {
        this.type = type;
        this.player = player;
        this.row = row;
        this.col = col;
        this.xPos = xPos;
        this.yPos = yPos;
        this.value = value;
    }

    public Piece(Board 

    public String getType() {return type; }
    public int getPlayer() {return player; }
}
