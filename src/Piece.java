import java.util.ArrayList;

public class Piece {
    String type;
    int player;

    public Piece(String type, int player) {
        this.type = type;
        this.player = player;
    }

    public String getType() {return type; }
}
