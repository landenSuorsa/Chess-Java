import java.util.ArrayList;

public class King extends Piece {
    boolean check = false;
    public King() {
        super("King");
    }

    public void setCheck(boolean check) {this.check = check; }
    public boolean inCheck() {return check; }
}
