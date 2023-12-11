import java.util.ArrayList;

public class Piece {
    public String type;
    public int player;
    

    public Piece(String type, int player) {
        this.type = type;
        this.player = player;
    }

    public Piece(Piece piece) {
        this.type = piece.getType();
        this.player = piece.getPlayer();
    }

    public String getType() {return type; }
    public int getPlayer() {return player; }
}
