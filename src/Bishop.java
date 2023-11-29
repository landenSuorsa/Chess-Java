import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(Cell cell) {
        this.setCell(cell);
    }
    @Override
    ArrayList<Cell> getPossibleMoves() {
        return null;
    }
}
