import database.Users;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BotMetods extends Start {
    SendMessage sendMessage = new SendMessage();
    Users users = new Users();

    private static Logger log = Logger.getLogger(Start.class.getName()); //логирование

    private Update update;
    private Message message;
    private String getText;
    private long getId;

    public BotMetods() {
    }

    public BotMetods(Update update) {

        this.update = update;
        this.message = update.getMessage();
        this.getText = update.getMessage().getText();
        this.getId = update.getMessage().getChatId();
    }


    //Start dialog
    public void startBotForUser() {
        //log.info("Пользователь " + update.getMessage().getChatId().toString() + " отправил сообщение: " + message.getText());

        if (message.getText().equals("/start")) {
            sendMessage("""
                    Привет, я неофициальный бот для работы в принтбаре!
                    Я помогу собрать вам статистику ваших апсейлов :)""");

            users.setId(update.getMessage().getChatId());
            users.setName("nickname");
            users.addUser();
            sendPhoto("https://blog-partnera.ru/wp-content/uploads/2017/12/printbar.png");


        }

    }


    //Method for send message
    public void sendMessage(String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString()); //определяем в какой чат отправлять сообщение
        sendMessage.setText(text); //отправляем сообщение

        try {
            execute(sendMessage);//отправляем сообщение

        } catch (TelegramApiException e) {
            log.severe("Проблема с методом для отправки сообщения");
            e.printStackTrace();
        }
    }


    //Method for send photo
    public void sendPhoto(String url) {

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(message.getChatId());
        sendPhoto.setPhoto(url);

        try {
            sendPhoto(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    //Определяет клавиатуру под текстовой панелью
    public void setKeybord(String text) {


        //sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);


        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        KeyboardRow keyboardThreeRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры

        keyboardFirstRow.add(Button.UPS.toString());
        keyboardFirstRow.add(Button.STATISTICS.toString());
        keyboardSecondRow.add(Button.DELETEUPS.toString());

        if (text.equals(Button.UPS.toString())) {

            keyboardFirstRow.clear();
            keyboardSecondRow.clear();
            keyboardThreeRow.clear();
            keyboardFirstRow.add(Button.HOODIE.toString());
            keyboardFirstRow.add(Button.BOLVANKA.toString());
            keyboardFirstRow.add(Button.MEGASAIL.toString());
            keyboardSecondRow.add(Button.PREMCLOTH.toString());
            keyboardSecondRow.add(Button.LUXCLOTH.toString());
            keyboardSecondRow.add(Button.TWOFORPRICECLOTH.toString());
            keyboardThreeRow.add(Button.SHIRTMONEY.toString());
            keyboardThreeRow.add(Button.BACK.toString());
        }

        if (text.equals(Button.DELETEUPS.toString())) {
            keyboardFirstRow.clear();
            keyboardSecondRow.clear();
            keyboardThreeRow.clear();
            keyboardFirstRow.add(Button.DHOODIE.toString());
            keyboardFirstRow.add(Button.DBOLVANKA.toString());
            keyboardFirstRow.add(Button.DMEGASAIL.toString());
            keyboardSecondRow.add(Button.DPREMCLOTH.toString());
            keyboardSecondRow.add(Button.DLUXCLOTH.toString());
            keyboardSecondRow.add(Button.DTWOFORPRICECLOTH.toString());
            keyboardThreeRow.add(Button.DSHIRTMONEY.toString());
            keyboardThreeRow.add(Button.BACK.toString());
        }


        // Добавляем все строчки клавиатуры в список

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThreeRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);


        buttonProcessing();

    }


    /**
     * Метод для обработки нажатия кнопок
     */
    public void buttonProcessing() {
        if (getText.equals(Button.UPS.toString())) {
            sendMessage.setChatId(message.getChatId().toString());
            sendMessage.setText("Добавьте апсейл \uD83D\uDCA1" + "\uD83D\uDCA5" + "\uD83D\uDCB0");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            return;

        }

        if (getText.equals(Button.HOODIE.toString())) {
            users.overMoney(getId, 200, "hoodie");
            return;
        }

        if (getText.equals(Button.MEGASAIL.toString())) {
            users.overMoney(getId, 35, "megasail");
            return;
        }

        if (getText.equals(Button.BOLVANKA.toString())) {
            users.overMoney(getId, 70, "bolvanka");
            return;
        }
        if (getText.equals(Button.PREMCLOTH.toString())) {
            users.overMoney(getId, 60, "premCloth");
            return;
        }
        if (getText.equals(Button.LUXCLOTH.toString())) {
            users.overMoney(getId, 80, "luxCloth");
            return;
        }
        if (getText.equals(Button.TWOFORPRICECLOTH.toString())) {
            users.overMoney(getId, 60, "twoForPriceCloth");
            return;
        }
        if (getText.equals(Button.SHIRTMONEY.toString())) {
            users.overMoney(getId, 200, "shirtMoney");
            return;
        }

        /**Обработка кнопок удаления апсейлов */
        if (getText.equals(Button.DHOODIE.toString())) {
            users.deleteUPS(getId, 200, "hoodie");
            return;
        }

        if (getText.equals(Button.DMEGASAIL.toString())) {
            users.deleteUPS(getId, 35, "megasail");
            return;
        }

        if (getText.equals(Button.DBOLVANKA.toString())) {
            users.deleteUPS(getId, 70, "bolvanka");
            return;
        }

        if (getText.equals(Button.DPREMCLOTH.toString())) {
            users.deleteUPS(getId, 60, "premCloth");
            return;
        }

        if (getText.equals(Button.DLUXCLOTH.toString())) {
            users.deleteUPS(getId, 80, "luxCloth");
            return;
        }

        if (getText.equals(Button.DTWOFORPRICECLOTH.toString())) {
            users.deleteUPS(getId, 60, "twoForPriceCloth");
            return;
        }

        if (getText.equals(Button.DSHIRTMONEY.toString())) {
            users.deleteUPS(getId, 200, "shirtMoney");
            return;
        }

        if (getText.equals(Button.DELETEUPS.toString())) { //кнопка меню для удаления апсейла

            sendMessage.setChatId(message.getChatId().toString());
            sendMessage.setText("Удалите апсейл \uD83D\uDE14" + "\uD83D\uDE33" + "\uD83D\uDE48");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            return;


            //
        }
        if (getText.equals(Button.STATISTICS.toString())) {
            sendMessage("Вы заработали сегодня " + users.getStaticDate(message.getChatId())[0] + " рублей" + "\n"
                    + "Ваша статистика апсейлов:" + "\n" +
                    "Худи: " + users.getStaticDate(message.getChatId())[1] + "\n" +
                    "Мегараспродажа: " + users.getStaticDate(message.getChatId())[2] + "\n" +
                    "100р Футболка: " + users.getStaticDate(message.getChatId())[3] + "\n" +
                    "Прем ткань: " + users.getStaticDate(message.getChatId())[4] + "\n" +
                    "Люкс ткань: " + users.getStaticDate(message.getChatId())[5] + "\n" +
                    "Две по цене одной прем: " + users.getStaticDate(message.getChatId())[6] + "\n" +
                    "Болванка: " + users.getStaticDate(message.getChatId())[7] + "\n"
            );


            return;
        }
        if (getText.equals(Button.BACK.toString())) {
            sendMessage.setChatId(message.getChatId().toString());
            sendMessage.setText("Основное меню");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            return;
        }

        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("\uD83D\uDE0D");

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }


}

