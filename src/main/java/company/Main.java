package company;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
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
            System.out.println("Укажите путь до json");
            logicGame.download(new Scanner(System.in).nextLine());
        } else {
            System.out.println("Check robot vs user(YES),or no(NO)");
            logicGame.who();
        }
    }
}
