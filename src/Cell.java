import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cell extends JButton {
    Piece piece = null;
    int row;
    int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public Piece getPiece() {
        return piece;
    }
    public int getRow() {return row; }
    public int getCol() {return col; }

    public void setPiece(Piece piece) {
        this.piece = piece;
        this.updateIcon();
    }

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
