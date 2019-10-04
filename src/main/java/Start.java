import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.util.logging.Logger;

public class Start extends TelegramLongPollingBot {
    private static Logger log = Logger.getLogger(Start.class.getName()); //логирование
    public static void main(String[] args) {

        ApiContextInitializer.init(); //инициализируем api
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(); //объект api

        try {
            telegramBotsApi.registerBot(new Start()); //регистрируем нашего бота
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }


    /**
     * Метод для приема сообщений.
     * @param update Содержит сообщение от пользователя.
     */
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();

        BotMetods botMetods = new BotMetods(update); //методы для бота

        botMetods.startBotForUser();
        botMetods.setKeybord(sendMessage, update.getMessage().getText());
    }

    public String getBotUsername() {
        return "UpsailBot";
        //return "mrdimenter_bot"; debug bot

    }

    public String getBotToken() {
       // return "767618403:AAGM54j3vmKkUWiyOQRvX0HRI1yxS2kO7iM"; debug bot
        return "737587067:AAGk9eIOkIK8U8zpE5M5uevbHgYTdbcoIn4";
    }
}

