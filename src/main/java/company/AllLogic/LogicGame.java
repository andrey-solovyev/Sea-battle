package company.AllLogic;


import com.fasterxml.jackson.databind.ObjectMapper;
import company.Field.Cell;
import company.Field.Game_field;
import company.Player.Player;
import company.Player.Robot;
import company.Player.User;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Random;
import java.util.Scanner;

public class LogicGame {
    private Game games;
    private boolean haveUser = false;
    private boolean playerOneGo = false;
    private boolean playerTwoGo = false;
    private boolean playerOneLastShot = false;
    private boolean playerTwoLastShot = false;
    private Player playerOne;
    private Player playerTwo;
    private Scanner scanner = new Scanner(System.in);
    private SaveGameJson saveGameJson = new SaveGameJson();

    //выбор игроков
    //if (robot vs robot then...) another robot vs user


    public LogicGame() {
    }

    public void who() {
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
  /*  public void download(){
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Choose file");
        File file = fileChooser.showOpenDialog(new Stage());

    }*/

    /* private void WhoIsmove() {
         if (playerOneGo) {
             Cell cell = playerOne.whereShot();
             System.out.println("One"+cell.getX()+" " +cell.getY());
           if (checkShot(playerOne, playerTwo, cell) && !playerTwo.allShipIsDead()){
               WhoIsmove();
           } else {
               if (playerTwo.allShipIsDead()){
                   finish();
               } else {
                   redefinition();
                   WhoIsmove();
                   }
           }
         } else if (playerTwoGo) {
             Cell cell = playerTwo.whereShot();
             System.out.println("Two"+cell.getX()+" " +cell.getY());

             if ( checkShot(playerTwo, playerOne, cell) && !playerTwo.allShipIsDead()){
                 WhoIsmove();
             } else {
                 if (playerOne.allShipIsDead()){
                     finish();
                 } else {
                     redefinition();
                     WhoIsmove();
                 }
             }
         }
     }*/

    private void saveJson() {
        StringWriter stringWriter = new StringWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        saveGameJson.setPlayerOne(playerOne.getGameField().getGame_field());
        saveGameJson.setPlayerTwo(playerTwo.getGameField().getGame_field());
        saveGameJson.setPlayerOneArms(playerOne.getAllArms());
        saveGameJson.setPlayerTwoArms(playerTwo.getAllArms());
        if (playerOneGo){
            saveGameJson.setLastShotOne(true);
        }
        try {
            objectMapper.writeValue(stringWriter, saveGameJson);
        } catch (IOException e) {
            System.out.println("IOEXCEPTION");
        }

        System.out.println(stringWriter.toString());
    }

    private void WhoIsmove() {
        int q = 0;
        while (!playerOne.allShipIsDead() && !playerTwo.allShipIsDead()) {
          /*  System.out.println("Сохранить текущее состояние игры?");
            System.out.println("Введите 1, если да, иначе 2");
            int key = scanner.nextInt();
            if (key == 1) {

            }*/
            if (q == 15) {
                saveJson();
            }
            q++;

            if (playerOneGo) {
                Cell cell = playerOne.whereShot(playerOneLastShot);
                System.out.println("One " + cell.getX() + " " + cell.getY());
                checkShot(playerOne, playerTwo, cell);
            } else if (playerTwoGo) {
                Cell cell = playerTwo.whereShot(playerTwoLastShot);
                System.out.println("Two" + cell.getX() + " " + cell.getY());
                checkShot(playerTwo, playerOne, cell);
            }
        }
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
            //     checkShot(two, one, cell);
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