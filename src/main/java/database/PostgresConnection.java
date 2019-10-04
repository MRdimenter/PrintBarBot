package database;


import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.Calendar;

import java.util.logging.Logger;

public class PostgresConnection {
    private static Logger log = Logger.getLogger(PostgresConnection.class.getName()); //логирование

    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";
    private static final String ADDUSERS = "insert into users (id, name, overmoney, date) values(?,?,?,?)";
    private static final String CalcMoney = "update users set overmoney = overmoney + ? where id = ?;";
    private static final String INPUTDATE = "select overmoney from users where id = ?";

    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;


    public PostgresConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD); //получение коннекшона
            statement = connection.createStatement(); //получение стейтмента

            // statement.execute("insert into users (id, name, overmoney) VALUES (5, 'lol', 1000);");

        } catch (SQLException e) {
            log.severe("Не удалось загрузить класс драйвера");
        }
    }

    /**
     * public static void main(String[] args) {
     * Connection connection;
     * try {
     * connection = DriverManager.getConnection(URL, USER, PASSWORD); //получение коннекшона
     * Statement statement = connection.createStatement(); //получение стейтмента
     * <p>
     * // statement.execute("insert into users (id, name, overmoney) VALUES (5, 'lol', 1000);");
     * //statement.executeUpdate() //в помощью этого метода выполняются запросы insert, update, delete
     * <p>
     * /**Получение данных с таблицы
     * <p>
     * String query = "SELECT * from users ";
     * ResultSet resultSet = statement.executeQuery(query);
     * while (resultSet.next()) { //изначально стоит на нулевой позиции
     * int anInt = resultSet.getInt("id");
     * String name = resultSet.getString("name");
     * int overmoney = resultSet.getInt("overmoney");
     * log.info("" + anInt + " " + name + " " + overmoney);
     * <p>
     * }
     * <p>
     * <p>
     * PreparedStatement preparedStatement = connection.prepareStatement("insert into users VALUES (?, ?, ?,?);");
     * preparedStatement.setInt(1, 100);
     * preparedStatement.setString(2, "Nikita");
     * preparedStatement.setInt(3, 200);
     * preparedStatement.setDate(4, new Date(Calendar.getInstance().getTimeInMillis()));
     * <p>
     * preparedStatement.execute(); //выполняем запрос
     * <p>
     * } catch (SQLException e) {
     * log.severe("Не удалось загрузить класс драйвера");
     * }
     * <p>
     * }
     */

    public void addUser(long id, String name) {

        try {
            preparedStatement = connection.prepareStatement(ADDUSERS);

            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, 0);
            preparedStatement.setDate(4, new Date(Calendar.getInstance().getTimeInMillis()));
            preparedStatement.executeUpdate();
        } catch (PSQLException e) {
            log.info("Аккаунт уже существует");
        } catch (SQLException e) {
            log.info("Ошибка PostgresConnection");
        }

    }

    public void UpSailCalc(long id, int overmoney) {
        try {
            log.info("id = " + id);
            log.info("money=" + overmoney);
            preparedStatement = connection.prepareStatement(CalcMoney);
            preparedStatement.setInt(1, overmoney);
            preparedStatement.setLong(2, id);


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int getStaticDate(long id) {
        int overmoney = 0;
        try {

            preparedStatement = connection.prepareStatement(INPUTDATE);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) { //изначально стоит на нулевой позиции


                 overmoney = resultSet.getInt("overmoney");


                log.info("overmoneyget = " + overmoney);

            }


             } catch (SQLException e) {
            e.printStackTrace();
        }

        return overmoney;
    }

    }



