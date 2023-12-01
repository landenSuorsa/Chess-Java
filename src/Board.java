import java.util.ArrayList;
import java.util.Stack;

public class Board {
    Cell[][] cell2DArray = new Cell[8][8];
    ArrayList<Piece> whitePieces;
    ArrayList<Piece> blackPieces;
    Stack<Piece> whitePiecesTaken;
    Stack<Piece> blackPiecesTaken;

    public Board() {
        //TODO: initialize pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cell2DArray[i][j] = new Cell();
            }
        }
    }
    public Cell getCellAt(int row, int col) {
        return cell2DArray[row][col];
    }
    public Cell[][] getCell2DArray() {return cell2DArray; }

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
