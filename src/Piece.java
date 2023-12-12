/**
 * Generic chess piece containing what type of piece they are and whose piece they are.
 * @author Landen Suorsa, Johnathan Hall, Christopher Hall
 */
public class Piece {
    protected String type;
    private int player; // 1 for white, 2 for black


    /**
     * Creates a chess piece.
     * @param type Types consist of "Pawn", "Bishop", "King", "Knight" "Queen", and "Rook"
     * @param player 1 for white, 2 for black
     */
    public Piece(String type, int player) {
        this.type = type;
        this.player = player;
    }

    /**
     * Creates a deep copy of the piece for simulation purposes.
     * @param piece
     */
    public Piece(Piece piece) {
        this.type = piece.getType();
        this.player = piece.getPlayer();
    }

    /**
     * @return String type ("Pawn", "Bishop", "King", "Knight" "Queen", or "Rook")
     */
    public String getType() {return type; }

    /**
     * @return int player (1 for white, 2 for black)
     */
    public int getPlayer() {return player; }
}
