import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
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
        UpsSail upsSail = new UpsSail();
        log.info("Total:" + upsSail.calcUps(update.getMessage().getText()));
        log.info("" + upsSail.getTotal());
    }

    public String getBotUsername() {
        return "mrdimenter_bot";

    }

    public String getBotToken() {
        return "767618403:AAGM54j3vmKkUWiyOQRvX0HRI1yxS2kO7iM";
    }
}

