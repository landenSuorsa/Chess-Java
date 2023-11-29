import java.util.ArrayList;
import java.util.Stack;

public class Board {
    Cell[][] cell2DArray = new Cell[8][8];
    ArrayList<Piece> whitePieces;
    ArrayList<Piece> blackPieces;
    Stack<Piece> whitePiecesTaken;
    Stack<Piece> blackPiecesTaken;

    public Board() {

    }
    public Cell getCellAt(int row, int col) {
        return cell2DArray[row][col];
    }

    public boolean checkmate() {
        return false;
    }

    public boolean stalemate() {
        return false;
    }

    public ArrayList<Cell> getPossibleMoves(Cell cell) {
        return null;
    }

    public void move(Cell start, Cell end) {

    }
}
