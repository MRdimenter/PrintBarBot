

import com.google.inject.internal.cglib.reflect.$FastMember;
import database.Users;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class BotMetods extends Start {
    Users users = new Users();
    private static Logger log = Logger.getLogger(Start.class.getName()); //логирование

    private Update update;
    private Message message;

    public BotMetods() {
    }

    public BotMetods(Update update) {
        this.update = update;

    }

    public BotMetods(Update update, Message message) {
        this.update = update;
        this.message = message;
    }




    //Start dialog
    public void startBotForUser() {
        message = update.getMessage();
        log.info("Пользователь " + update.getMessage().getChatId().toString() + " отправил сообщение: " + message.getText());

        if(message.getText().equals("/start")) {
            sendMessage("Привет, я неофициальный бот для работы в принтбаре! " +
                    "Я помогу собрать вам статистику ваших апсейлов :)");
            users.setId(update.getMessage().getChatId());
            users.setName("nickname");
            users.addUser();

        }

    }



    //Method for send message
    public void sendMessage(String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString()); //определяем в какой чат отправлять сообщение
        sendMessage.setText(text); //отправляем сообщение

        try {
            //setKeybord(sendMessage);
            execute(sendMessage);//отправляем сообщение


        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
     }


    public void sendPhoto() {

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(message.getChatId().toString());
        sendPhoto.setPhoto("https://www.business.ru/images/articles/1915/1.jpg");

    }


    //определит клавиатуру под текстовой панелью
    public void setKeybord(SendMessage sendMessage, String text) {


        sendMessage = new SendMessage();
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

            keyboardFirstRow.add("+Ups" + "\uD83D\uDCB5");
            keyboardFirstRow.add("статистика" + "\uD83D\uDCB9");

        if(text.equals("+Ups" + "\uD83D\uDCB5")) {

            keyboardFirstRow.clear();
            keyboardFirstRow.add("Худи 50%" + "\uD83C\uDF70");
            keyboardFirstRow.add("Болванка" + "\uD83D\uDC55");
            keyboardSecondRow.add("Прем ткань" + "\uD83D\uDE4F");
            keyboardSecondRow.add("Люкс ткань" + "\uD83D\uDE4C");
            keyboardSecondRow.add("Две ткани" + "\uD83C\uDF53");
            keyboardThreeRow.add("100р футболка" + "\uD83D\uDCB3");
            keyboardThreeRow.add("◀Назад");
        }



        // Добавляем все строчки клавиатуры в список

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThreeRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
        String getText = update.getMessage().getText();
        long getId = update.getMessage().getChatId();
        if (getText.equals("+Ups" + "\uD83D\uDCB5")){
            sendMessage.setChatId(message.getChatId().toString());
            sendMessage.setText("Добавьте апсейл:");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            return;

        };
        if (getText.equals("Худи 50%\uD83C\uDF70")){
            users.overMoney(getId, 200, "hoodie");
            return;
        };
        if (getText.equals("Болванка" + "\uD83D\uDC55")) {
            users.overMoney(getId, 70, "bolvanka");
            return;
        }
        if (getText.equals("Прем ткань" + "\uD83D\uDE4F")){
            users.overMoney(getId, 60, "premCloth");
            return;
        }
        if (getText.equals("Люкс ткань" + "\uD83D\uDE4C")) {
            users.overMoney(getId, 80, "luxCloth");
            return;
        }
        if (getText.equals("Две ткани" + "\uD83C\uDF53")){
            users.overMoney(getId, 60, "twoForPriceCloth");
            return;
        }
        if (getText.equals("100р футболка" + "\uD83D\uDCB3")) {
            users.overMoney(getId, 200, "shirtMoney");
            return;
        }
        if (getText.equals("статистика" + "\uD83D\uDCB9")) {
            sendMessage("Вы заработали сегодня " +users.getStaticDate(message.getChatId())[0] + " рублей" + "\n"
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
        if (getText.equals("◀Назад")) {
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
        sendMessage.setText("\uD83D\uDE0A");

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return;





    }




    }

