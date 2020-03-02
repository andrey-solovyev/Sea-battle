package company.Bot;

import com.sun.javafx.scene.shape.MeshHelper;
import company.AllLogic.LogicGame;
import company.Field.Cell;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.logging.Level;

import static java.lang.Integer.parseInt;

public class Bot extends TelegramLongPollingBot {
    public long id = 0;
    private Update update;
    private LogicGame logicGame;
    private Message lastUserMessage;

    public Message getLastUserMessage() {
        return lastUserMessage;
    }

    public void setLastUserMessage(Message lastUserMessage) {
        this.lastUserMessage = lastUserMessage;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message=update.getMessage();
        if (message!=null){
            logicGame.setLastShot(message);
        }
    }

    public Bot(DefaultBotOptions options, LogicGame logicGame) {
        super(options);
        logicGame.botShot();
    }

    public void SetLogicGame(LogicGame logicGame) {
        this.logicGame = new LogicGame();

    }

  /* public  void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            new SendMessage(sendMessage);
        } catch (TelegramApiException e) {
        }
    }*/

    public Cell whereShot(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText("Where do u want for shot");
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println("OPS");
        }
        String message = update.getMessage().getText();
        return new Cell(parseInt(message.split(" ")[0]), parseInt(message.split(" ")[1]));
    }

    public void sendMessage(long chatid,String message){
        SendMessage sendMessage=new SendMessage();
        sendMessage.setChatId(chatid);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println("sendMessage error,generall method");
        }
    }
    @Override
    public String getBotToken() {
        return "925758227:AAE3Og96C06CzvjGl8KrThu1NI875LhEPks";
    }

    @Override
    public String getBotUsername() {
        return "@Stupid bot";
    }
}
