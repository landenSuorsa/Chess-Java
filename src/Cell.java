import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cell extends JButton {
    Piece piece = null;

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        this.updateIcon();
    }

    public void updateIcon() {
        ImageIcon icon;
        if (piece == null) {
            icon = new ImageIcon("emptyCell");
        } else {
            icon = new ImageIcon("" + piece.getPlayer() + piece.getType() + ".png");
        }
        this.setIcon(icon);
    }
}
