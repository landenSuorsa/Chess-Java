import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(Cell cell) {
        this.setCell(cell);
    }
    @Override
    ArrayList<Cell> getPossibleMoves() {
        return null;
    }
}
