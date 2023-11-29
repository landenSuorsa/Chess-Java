import java.util.ArrayList;

public class King extends Piece {
    public King(Cell cell) {
        this.setCell(cell);
    }
    @Override
    ArrayList<Cell> getPossibleMoves() {
        return null;
    }
}
