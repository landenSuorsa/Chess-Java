import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cell acts as a cell on the 8x8 chess board. Cell extends JButton, adding
 * variables for row, col, and what piece is on it.
 * @author Landen Suorsa, Johnathan Hall, Christopher Hall.
 */
public class Cell extends JButton {
    private Piece piece = null;
    private int row;
    private int col;

    /**
     * Creates a new Cell. Only used during initialization of board.
     * @param row - row number (0-7)
     * @param col - column number (0-7)
     */
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Creates a deep copy of a Cell for simulation purposes.
     * @param cell - cell to be copied.
     */
    public Cell(Cell cell) {
        this.row = cell.getRow();
        this.col = cell.getCol();
        if (cell.getPiece() instanceof Pawn) {
            this.piece = new Pawn((Pawn)cell.getPiece());
        } else if (cell.getPiece() != null) {
            this.piece = new Piece(cell.getPiece());
        }
    }

    /**
     * @return piece or null if no piece.
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * @return row value (0-7)
     */
    public int getRow() {return row; }
    /**
     * @return column value (0-7)
     */
    public int getCol() {return col; }

    /**
     * Sets a piece on the cell and updates the imageIcon.
     * @param piece
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
        this.updateIcon();
    }

    /**
     * Adds onto the setEnabled function to change the color of the cell depending on if it is enabled or not.
     * @param b  true to enable the button, otherwise false
     */
    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);

        Color brown = new Color(104,80,40);
        Color lightBrown = new Color(130,100,60);
        Color gray = new Color(60,60,60);
        Color lightGray = new Color(90,90,90);

        if (b) {
            if (getBackground().equals(brown) || getBackground().equals(gray)) {
                setBackground(brown);
            } else {
                setBackground(lightBrown);
            }
        } else {
            if (getBackground().equals(brown) || getBackground().equals(gray)) {
                setBackground(gray);
            } else {
                setBackground(lightGray);
            }
        }
    }

    /**
     * Updates the icon and disabledIcon depending on what piece is on the cell.
     */
    public void updateIcon() {
        ImageIcon icon;
        if (piece == null) {
            icon = new ImageIcon();
        } else {
            icon = new ImageIcon("" + piece.getPlayer() + piece.getType() + ".png");
        }
        this.setIcon(icon);
        this.setDisabledIcon(icon);
    }
}
