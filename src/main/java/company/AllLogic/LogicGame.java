package company.AllLogic;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import company.Field.Cell;
import company.Player.Player;
import company.Player.Robot;
import company.Player.User;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.parseInt;


public class LogicGame {
    private Game games;
    private boolean haveUser = true;
    private boolean playerOneGo = false;
    private boolean playerTwoGo = false;
    private boolean playerOneLastShot = false;
    private boolean playerTwoLastShot = false;
    private Player playerOne;
    private Player playerTwo;
    private Scanner scanner = new Scanner(System.in);
    private SaveGameJson saveGameJson = new SaveGameJson();
    private Cell lastShot;

    //выбор игроков
    //if (robot vs robot then...) another robot vs user


    public LogicGame() {

    }

    public void who() {
        if (scanner.nextLine().contains("YES")) {
            haveUser = true;
        }
        if (haveUser) {

            Game game = new Game(new User(), new Robot());
            playerOne = game.getUser();
            playerTwo = game.getRobot();
            games = game;
            saveGameJson.setHaveUser(true);
            firstHit();
        } else {
            Game game = new Game(new Robot(), new Robot());
            playerOne = game.getRobot();
            playerTwo = game.getRoborTwo();
            games = game;
            firstHit();
        }
    }

    private void firstHit() {
        int i = new Random().nextInt(2);
        if (i == 1) {
            playerOneGo = true;
            WhoIsmove();
        } else {
            playerTwoGo = true;
            WhoIsmove();
        }
    }

    public void download(String path) {
        readJsonFromFile(path);
        WhoIsmove();
    }


    private void saveJson() {

        saveGameJson.setPlayerOne(playerOne.getGameField().getGame_field());
        testDrawMatrix(saveGameJson.getPlayerOne());
        saveGameJson.setPlayerTwo(playerTwo.getGameField().getGame_field());
        saveGameJson.setPlayerOneArms(playerOne.getArms());
        saveGameJson.setPlayerTwoArms(playerTwo.getArms());
        if (playerOneGo) {
            saveGameJson.setLastShotOne(true);
        }
        Gson gsons = new GsonBuilder().setPrettyPrinting().create();
        String json = gsons.toJson(saveGameJson);
        System.out.println("Укажите путь сохранения");
        String path = scanner.nextLine();
        try (FileWriter fileWriter = new FileWriter(path)) {
            gsons.toJson(saveGameJson, fileWriter);
        } catch (IOException e) {
            System.out.println("HEY");
        }
    }

    public void readJsonFromFile(String path) {
        who();
        try (Reader reader = new FileReader(path)) {
            Gson gson = new Gson();
            SaveGameJson saveGameJson = gson.fromJson(reader, SaveGameJson.class);
            testDrawMatrix(saveGameJson.getPlayerOne());
            playerOneGo = !saveGameJson.isLastShotOne();
            playerTwoGo = !playerOneGo;

            reader.close();
        } catch (IOException e) {
            System.out.println("IOEXCEPTION");
        }
    }

    private void testDrawMatrix(Cell[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                if (cells[i][j].isShot()) {
                    System.out.print("#");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }

    public void setLastShot(Message message) {

        this.lastShot = new Cell(parseInt(message.getText().split(" ")[0]), parseInt(message.getText().split(" ")[1]));
    }

    public void botShot() {
        who();
        firstHit();
        while (!playerOne.allShipIsDead() && !playerTwo.allShipIsDead()) {
            if (playerOneGo) {

                Cell cell = lastShot;

                System.out.println("One " + cell.getX() + " " + cell.getY());
                checkShot(playerOne, playerTwo, cell);
            } else if (playerTwoGo) {
                Cell cell = playerTwo.whereShot(playerTwoLastShot);
                System.out.println("Two" + cell.getX() + " " + cell.getY());
                checkShot(playerTwo, playerOne, cell);
            }
        }
        //standart path to json is
        //  readJsonFromFile("D:\\Sea battle\\json\\Output.json");
        finish();

    }

    private void WhoIsmove() {
        int q = 0;
        while (!playerOne.allShipIsDead() && !playerTwo.allShipIsDead()) {
            System.out.println("Сохранить текущее состояние(YES) или нет(NO)?");
            String game = scanner.nextLine();
            if (game.contains("YES")) {
                saveJson();
            }
            q++;
            if (q == 30) {
                saveJson();
            }

            if (playerOneGo) {
                Cell cell = playerOne.whereShot(playerOneLastShot);
                System.out.println(playerOne.cells());
                System.out.println("One " + cell.getX() + " " + cell.getY());
                checkShot(playerOne, playerTwo, cell);
            } else if (playerTwoGo) {
                Cell cell = playerTwo.whereShot(playerTwoLastShot);
                System.out.println(playerTwo.cells());
                System.out.println("Two" + cell.getX() + " " + cell.getY());
                checkShot(playerTwo, playerOne, cell);
            }
        }
        //standart path to json is
        //  readJsonFromFile("D:\\Sea battle\\json\\Output.json");
        finish();

    }


    private void checkShot(Player One, Player Two, Cell cell) {
        if (Two.hit(cell)) {
            if (Two.isSubarineMineOrMinesweeper(cell)) {
                notShip(One, Two, cell);
            }
            One.giveDeadShip(Two.isDeadShip(cell));
            if (playerOneGo) {
                playerOneLastShot = true;
                playerTwoLastShot = false;
            } else if (playerTwoGo) {
                playerTwoLastShot = true;
                playerOneLastShot = false;
            }
        } else {
            redefinition();
        }
    }


    private void finish() {
        if (playerOneGo) {
            System.out.println("ONE IS WIN");
        } else {
            System.out.println("TWO IF WIN");
        }

    }

    public void notShip(Player one, Player two, Cell cell) {
        if (two.isMine(cell)) {
            two.addCellShip(one.randomPointShip());
            redefinition();
        } else if (two.isSubmarine(cell)) {
            redefinition();
            checkShot(two, one, cell);
        } else if (two.isMineswepeer(cell)) {
            redefinition();
            two.addMineCell(one.giveMineCell());

        }
    }

    private void redefinition() {
        if (playerOneGo) {
            playerOneGo = false;
            playerTwoGo = true;
        } else {
            playerOneGo = true;
            playerTwoGo = false;
        }
    }

}