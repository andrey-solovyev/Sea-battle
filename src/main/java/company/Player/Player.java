package company.Player;

import company.Arms.Ship;
import company.Field.Cell;

public interface Player {
    boolean hit(Cell cell);
    Ship isDeadShip(Cell cell);
    void giveDeadShip(Ship ship);
    boolean isSubarineMineOrMinesweeper(Cell cell);
    boolean isSubmarine(Cell cell);
    boolean isMine(Cell cell);
    boolean isMineswepeer(Cell cell);
    Cell whereShot(boolean lastShot);
    Cell randomPointShip();
    void addCellShip(Cell cell);
    void addMineCell(Cell cell);
    Cell giveMineCell();
    boolean allShipIsDead();

}
