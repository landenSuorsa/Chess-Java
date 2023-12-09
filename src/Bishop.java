import java.io.*;
import java.util.*;

public class Bishop extends Piece{

private int[] position;
private int startCol;
private int startRow;

public Bishop(){
  super("Bishop", player);
  this.startCol = 
}

private void move(int[] newPosition){
  if(isValidMove(newPosition)){
    this.position = newPosition;
    Cell spaceOccupied = cell2DArray[newPosition[0]][newPosition[1]];
    if(spaceOccupied != null){//if there is a piece in this spot then take it
      
    }
  }
}

private boolean isValidMove(int[] newPosition, Cell[][] cell2DArray){
  boolean withinBoundary;
  int column = Math.abs(newPosition[0] - cell2DArray[0]);
  int row = Math.abs(newPosition[1] - cell2DArray[1]);
  withinBoundry = newPosition[0] >= 1 && newPosition[0] <= 8 && newPosition[1] >= 1 && newPosition[1] <=8;
  
  if(row == column && withinBoundry){
    Cell spaceOccupied = cell2DArray[newPosition[0]][newPosition[1]];
    return spaceOccupied == null || !spaceOccupied.getColor().equals(color);
  }
return false;
}



}//end of class
