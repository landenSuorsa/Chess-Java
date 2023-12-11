import java.util.ArrayList;

public class King extends Piece {
    boolean check = false;
    public King(int player) {
        super("King", player);
    }

    public King(King king) {
        super("King", king.getPlayer());
        this.check = king.inCheck();
    }

    public void setCheck(boolean check) {this.check = check; }
    public boolean inCheck() {return check; }
}
