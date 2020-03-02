package company.Player;

import company.Arms.*;
import company.Field.Cell;
import company.Field.Game_field;

public interface Player {
    boolean hit(Cell cell);

    String cells();

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

    Game_field getGameField();

    Arms getArms();

    void setArms(Arms arms);

}
