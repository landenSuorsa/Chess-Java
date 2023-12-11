import java.util.ArrayList;

public class Pawn extends Piece {
    boolean facingUp;

    public Pawn(Boolean facingUp, int player) {
        super("Pawn", player);
        this.facingUp = facingUp;
    }

    public Pawn(Pawn pawn) {
        super("Pawn", pawn.getPlayer());
        this.facingUp = pawn.facingUp;
    }

    public void evolve(String type) {
        this.type = type;
    }
}
