import java.util.ArrayList;

public class Piece {
    public String type;
    public int player;
    

    public Piece(String type, int player) {
        this.type = type;
        this.player = player;
       
    }

    public String getType() {return type; }
    public int getPlayer() {return player; }
}
