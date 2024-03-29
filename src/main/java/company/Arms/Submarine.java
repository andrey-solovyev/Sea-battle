package company.Arms;

import company.Field.Cell;
//import javafx.scene.paint.Color;

public class Submarine implements Common{
    private Cell submarine;
    private boolean isDead=false;
    public Submarine( int x, int y) {
        this.submarine = new Cell(x,y);

       // submarine.setCell_color(Color.YELLOW);
    }

    public Cell getSubmarine() {
        return submarine;
    }

    public void setSubmarine(Cell submarine) {
        this.submarine = submarine;
    }

    @Override
    public boolean check(int x, int y) {
        if (submarine.getX()==x && submarine.getY()==y && !isDead){
            isDead=true;
            return true;
        }
        return false;
    }

    @Override
    public boolean isDead() {
        return isDead;
    }
}
