package company.Player;

import company.Arms.*;
import company.Field.Cell;
import company.Field.Game_field;
import javafx.scene.paint.Color;


import java.util.Scanner;

public class User implements Player {


    private Arms arms;
    private Game_field game_field = new Game_field();
    private Scanner scanner = new Scanner(System.in);

    public User() {
        fieldShip();
    }

    private void fieldShip() {
        Arms arms1 =
                new Arms(new Ship(4, 2, 1, 6, 1, false), (new Ship(3, 2, 3, 4, 3, false)), new Ship(3, 1, 5, 1, 7, true), new Ship(2, 19, 1, 10, 1, true), new Ship(2, 18, 3, 18, 4, false), new Ship(2, 5, 5, 5, 6, false), new Ship(1, 3, 20, 3, 20, false), new Ship(1, 18, 20, 18, 20, false), new Ship(1, 4, 18, 4, 18, false), new Ship(1, 19, 17, 19, 17, false), new Mine(1, 1), new Submarine(1, 8), new Minesweeper(20, 20));
        arms = arms1;
    }

    @Override
    public boolean hit(Cell cell) {
        return arms.hit(cell.getX(), cell.getY());
    }

    @Override
    public Game_field getGameField() {
        return game_field;
    }

    @Override
    public void setArms(Arms arm){
        arms=arm;
    }

    @Override
    public Arms getArms(){
        return arms;
    }

    public void colorShotShip(Cell cell) {
        //  game_field.getGame_field()[cell.getX()][cell.getY()].setCell_color(Color.RED);
    }

    private void draw() {
        System.out.println("  1  2  3  4  5  6  7  8  9  10");
        int g = 1;
        for (int i = 0; i < game_field.getGame_field().length; i++) {
            System.out.print(g);
            for (int k = 0; k < game_field.getGame_field().length; k++) {
                if (game_field.getGame_field()[i][k].isShot()) {
                    System.out.print(" # ");
                } else {
                    System.out.print(" * ");
                }

            }
            g++;
            System.out.println();
        }
    }
    private void outline(Ship ship) {

        for (int i = 0; i < ship.getShips_cells().length; i++) {
            int x = ship.getCell(i).getX();
            int y = ship.getCell(i).getY();
            if (ship.getSize() == 1) {
                outlineY(ship.getShips_cells()[0].getX(), ship.getCell(0).getY());
                if (x != 1) {
                    game_field.getGame_field()[x - 2][y - 1].setShot(true);
                    outlineY(x - 1, y);
                }
                if (x != 10) {
                    game_field.getGame_field()[x][y - 1].setShot(true);
                    outlineY(x + 1, y);
                }
                break;
            }
            if (ship.isVertically()) {
                if (x != 1 && i == 0) {
                    game_field.getGame_field()[x - 2][y - 1].setShot(true);
                    outlineY(x - 1, y);
                }
                if (i != ship.getShips_cells().length - 1) {
                    outlineY(x, y);
                } else {
                    if (x != 10) {
                        game_field.getGame_field()[x][y - 1].setShot(true);
                        outlineY(x + 1, y);
                    }
                }
            } else {
                if (y != 1 && i == 0) {
                    game_field.getGame_field()[x - 1][y - 2].setShot(true);
                    outlineX(x, y);
                } else if (i != ship.getShips_cells().length - 1) {
                    outlineX(x, y);
                } else {
                    if (y != 10) {
                        game_field.getGame_field()[x - 1][y].setShot(true);
                        outlineX(x, y + 1);
                    }
                }

            }

        }
    }

    private void outlineY(int x, int y) {
        if (y != 1) {
            game_field.getGame_field()[x][y - 2].setShot(true);
        }
        if (y != 10) {
            game_field.getGame_field()[x][y].setShot(true);
        }
    }

    private void outlineX(int x, int y) {
        if (x != 1) {
            game_field.getGame_field()[x - 2][y - 1].setShot(true);
        }
        if (x != 10) {
            game_field.getGame_field()[x][y - 1].setShot(true);
        }
    }

    @Override
    public Ship isDeadShip(Cell cell) {
        return arms.isDeadShip(cell);
    }

    @Override
    public void giveDeadShip(Ship ship) {
        if (ship != null) {
            outline(ship);
        }
    }

    @Override
    public Cell whereShot(boolean lastShot) {
        draw();
        System.out.println("Where shot?");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        game_field.getGame_field()[x-1][y-1].setShot(true);

        return new Cell(x, y);
    }



    @Override
    public boolean isSubarineMineOrMinesweeper(Cell cell) {
        if (arms.isMine(cell.getX(), cell.getY())) {
            return true;
        } else if (arms.isSubmarine(cell.getX(), cell.getY())) {
            return true;
        } else if (arms.isMineOrSubmarine(cell.getX(), cell.getY())) {
            return true;
        }
        return false;
    }

    @Override
    public Cell giveMineCell() {
        return arms.getMine().getMine();
    }

    @Override
    public void addMineCell(Cell cell) {
        game_field.getGame_field()[cell.getX() - 1][cell.getY() - 1].setCell_color(Color.RED);
    }

    @Override
    public boolean isMine(Cell cell) {
        return arms.isMine(cell.getX(), cell.getY());
    }

    @Override
    public boolean allShipIsDead() {
        return arms.deadAllShip();
    }

    @Override
    public boolean isSubmarine(Cell cell) {
        return arms.isSubmarine(cell.getX(), cell.getY());
    }

    @Override
    public boolean isMineswepeer(Cell cell) {
        return arms.isMineswepeer(cell.getX(), cell.getY());
    }

    @Override
    public void addCellShip(Cell cell) {
        game_field.getGame_field()[cell.getX() - 1][cell.getY() - 1].setCell_color(Color.BLUE);
    }

    @Override
    public Cell randomPointShip() {
        return arms.randomPoint();
    }

    public Game_field getGame_field() {
        return game_field;
    }

    public void setGame_field(Game_field game_field) {
        this.game_field = game_field;
    }
}
