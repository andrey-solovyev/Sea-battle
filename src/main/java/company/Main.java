package company;

import company.AllLogic.LogicGame;

import java.util.Scanner;

public class Main {
    private static LogicGame logicGame = new LogicGame();

    public static void main(String[] args) {
        // write your code here
     //   logicGame.download();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Начать новую игру (YES) или загрузить(NO)");
        String game=scanner.nextLine();
        if (game.contains("NO")){

        } else {
            logicGame.who();
            System.out.println("Check robot vs user(1),or no(2)");
        }
    }
}
