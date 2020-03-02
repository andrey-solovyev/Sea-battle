package company;

import company.AllLogic.LogicGame;
import company.Bot.Bot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;


import java.util.Scanner;

public class Main {
    private static LogicGame logicGame = new LogicGame();

    public static void main(String[] args) {
        // write your code here
        //   logicGame.download();

//        ApiContextInitializer.init();
//        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
//        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
//        LogicGame logicGame = new LogicGame();
//
//        Bot bot = new Bot(botOptions,logicGame);
//
//        try {
//            telegramBotsApi.registerBot(bot);
//        } catch (TelegramApiRequestException e) {
//            e.printStackTrace();
//        }
        // logicGame.botShot();
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
