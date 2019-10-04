package database;

import java.util.Date;
import java.util.logging.Logger;

public class Users {
    private static Logger log = Logger.getLogger(Users.class.getName()); //логирование
    PostgresConnection postgresConnection = new PostgresConnection();
    private long id;
    private String name;
    private int overMoney;
    private Date date;

    public Users(long id, String name, int overMoney, Date date) {
        this.id = id;
        this.name = name;
        this.overMoney = overMoney;
        this.date = date;
    }

    public Users() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOverMoney() {
        return overMoney;
    }

    public void setOverMoney(int overMoney) {
        this.overMoney = overMoney;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void addUser() {
        log.severe("id= " + getId());
        log.severe("name= " + getName());

        postgresConnection.addUser(getId(),getName());
    }

    public void overMoney(long id,int money) {
        postgresConnection.UpSailCalc(id, money);
    }

    public void overMoney(long id,int money, int nameUps) {
       //postgresConnection.UpSailCalc(id, money, nameUps);
    }

    public void overMoney(long id,int money, String nameUps) {
        postgresConnection.UpSailCalc(id, money, nameUps);
    }

    public int[] getStaticDate(long id) { return postgresConnection.getStaticDate(id);} //вывод статистики по апсейлам

}
