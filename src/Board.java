import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class Board {
    Cell[][] cell2DArray = new Cell[8][8];
    King whiteKing;
    King blackKing;
    JPanel whiteTakenPieces;
    JPanel blackTakenPieces;
    Cell clickedCell = null;
    Color brown = new Color(104,80,40);
    Color lightBrown = new Color(130,100,60);
    Color gray = new Color(60,60,60);
    Color lightGray = new Color(90,90,90);

    public Board() {
        String[] pieceNames = {"Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook"};
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int ifin = i;
                int jfin = j;
                cell2DArray[i][j] = new Cell();
                cell2DArray[i][j].addActionListener(e -> clickCell(cell2DArray[ifin][jfin]));
                if ((i % 2 == 1 && j % 2 == 0) || (i % 2 == 0 && j % 2 == 1)) {
                    cell2DArray[i][j].setBackground(brown);
                } else {
                    cell2DArray[i][j].setBackground(lightBrown);
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            cell2DArray[0][i].setPiece(new Piece(pieceNames[i],2));
            cell2DArray[1][i].setPiece(new Pawn(false,2));
            cell2DArray[7][i].setPiece(new Piece(pieceNames[i],1));
            cell2DArray[6][i].setPiece(new Pawn(true,1));
        }

        cell2DArray[0][4].setPiece(new King(2));
        cell2DArray[7][4].setPiece(new King(1));
        blackKing = (King)cell2DArray[0][4].getPiece();
        whiteKing = (King)cell2DArray[7][4].getPiece();

        whiteTakenPieces = new JPanel();
        whiteTakenPieces.setLayout(new GridLayout(2,8));
        blackTakenPieces = new JPanel();
        whiteTakenPieces.setLayout(new GridLayout(2,8));
    }

    public Cell getCellAt(int row, int col) {
        return cell2DArray[row][col];
    }

    public Cell[][] getCell2DArray() {
        return cell2DArray;
    }

    public JPanel getWhiteTakenPieces() {
        return whiteTakenPieces;
    }

    public JPanel getBlackTakenPieces() {
        return blackTakenPieces;
    }

    public boolean checkmate() {
        return false;
    } //TODO: make this function return if the board is in state of checkmate.

    public boolean stalemate() {
        return false;
    } //TODO: make this function return if the board is in a state of stalemate.

    public void showPossibleMoves(Cell cell) {
        for (Cell[] row : cell2DArray) {
            for (Cell c : row) {
                c.setEnabled(false);
            }
        }

        ArrayList<Cell> moves = new ArrayList<>();
        switch (cell.getPiece().getType()) {
            case "Pawn":
                moves = pawnMoves(cell);
            case "Bishop":
                moves = bishopMoves(cell);
            case "Rook":
                moves = rookMoves(cell);
            case "Queen":
                moves = queenMoves(cell);
            case "King":
                moves = kingMoves(cell);
            case "Knight":
                moves = knightMoves(cell);
            default:
        }

        //TODO: Remove moves that have their King in check after.

        cell.setEnabled(true);
        if (moves != null && moves.size() != 0) {
            for (Cell move : moves) {
                move.setEnabled(true);
            }
        }
    }

    public ArrayList<Cell> pawnMoves(Cell cell) {
        //TODO: write this function to return an arraylist of all possible moves for a pawn on Cell cell.
        /* can't move back only forward
        Pawn move: 
        1.)move from start position--> 2 or 1 space(s) forward
        2.)every move from then is 1 space
        Take piece:
        1.)the diagonal space in front of the piece
        */
        if(
        return null;
    }

    public ArrayList<Cell> bishopMoves(Cell cell) {
        //TODO: write this function to return an arraylist of all possible moves for a bishop on Cell cell.
        return null;
    }

    public ArrayList<Cell> rookMoves(Cell cell) {
        //TODO: write this function to return an arraylist of all possible moves for a rook on Cell cell.
        return null;
    }

    public ArrayList<Cell> queenMoves(Cell cell) {
        ArrayList<Cell> moves = rookMoves(cell);
        if (bishopMoves(cell) != null) {
            for (Cell c : bishopMoves(cell)) {
                moves.add(c);
            }
        }
        return moves;
    }

    public ArrayList<Cell> kingMoves(Cell cell) {
        //TODO: write this function to return an arraylist of all possible moves for a king on Cell cell.
        return null;
    }

    public ArrayList<Cell> knightMoves(Cell cell) {
        //TODO: write this function to return an arraylist of all possible moves for a knight on Cell cell.
        return null;
    }

    public void clickCell(Cell cell) {
        if (cell.getPiece() != null && clickedCell == null) {
            // case for if no cell has been pressed yet.
            clickedCell = cell;
            showPossibleMoves(cell);
        } else if (clickedCell != null && cell.equals(clickedCell)) {
            // case for if the same cell is clicked twice, unselecting it.
            clickedCell = null;
            enablePlayersPieces(cell.getPiece().getPlayer());
        } else if (clickedCell != null) {
            // cases for moving a piece
            if (cell.getPiece() == null) {
                // case for if nothing is in the spot the piece is going.
                cell.setPiece(clickedCell.getPiece());
                clickedCell.setPiece(null);
                if (cell.getPiece().getPlayer() == 1) enablePlayersPieces(2);
                else enablePlayersPieces(1);
            } else if (cell.getPiece().getPlayer() != clickedCell.getPiece().getPlayer()) {
                // cases for if a piece is in the spot it is going
                if (cell.getPiece().getPlayer() == 1) {
                    // puts the piece taken in the respective spot
                    ImageIcon icon = new ImageIcon("1" + cell.getPiece().getType() + ".png");
                    icon = new ImageIcon(icon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
                    whiteTakenPieces.add(new JLabel(icon));
                } else {
                    // puts the piece taken in the respective spot
                    ImageIcon icon = new ImageIcon("2" + cell.getPiece().getType() + ".png");
                    icon = new ImageIcon(icon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
                    blackTakenPieces.add(new JLabel(icon));
                }

                // moves the piece.
                cell.setPiece(clickedCell.getPiece());
                clickedCell.setPiece(null);
                if (cell.getPiece().getPlayer() == 1) enablePlayersPieces(2);
                else enablePlayersPieces(1);
            }
        } else {
            // shouldn't be reached.
            throw new RuntimeException();
        }
    }

    public void enablePlayersPieces(int player) {
        for (Cell[] row : cell2DArray) {
            for (Cell cell : row) {
                if (cell.getPiece() != null && cell.getPiece().getPlayer() == player) {
                    cell.setEnabled(true);
                } else {
                    cell.setEnabled(false);
                }
            }
        }
    }
}
