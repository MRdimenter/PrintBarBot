public class UpsSail {
    private int megaSail = 35; //мегараспродажа
    private int bolvanka = 70; //болванка
    private int hoodie = 200; //худи
    private int premCloth = 60; //прем ткань
    private int luxCloth = 80; //люкс ткань
    private int twoForPriceCloth = 60; //две по цене одной прем
    private int shirtMoney = 200; //100р футболка
    private int total;

    public UpsSail() {
    }

    public int getMegaSail() {
        return megaSail;
    }

    public int getBolvanka() {
        return bolvanka;
    }

    public int getHoodie() {
        return hoodie;
    }

    public int getPremCloth() {
        return premCloth;
    }

    public int getLuxCloth() {
        return luxCloth;
    }

    public int getTwoForPriceCloth() {
        return twoForPriceCloth;
    }

    public int getShirtMoney() {
        return shirtMoney;
    }

    public void setMegaSail(int megaSail) {
        this.megaSail = megaSail;
    }

    public void setBolvanka(int bolvanka) {
        this.bolvanka = bolvanka;
    }

    public void setHoodie(int hoodie) {
        this.hoodie = hoodie;
    }

    public void setPremCloth(int premCloth) {
        this.premCloth = premCloth;
    }

    public void setLuxCloth(int luxCloth) {
        this.luxCloth = luxCloth;
    }

    public void setTwoForPriceCloth(int twoForPriceCloth) {
        this.twoForPriceCloth = twoForPriceCloth;
    }

    public void setShirtMoney(int shirtMoney) {
        this.shirtMoney = shirtMoney;
    }

    public int getTotal() {
        return  total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int calcUps(String message) {
        if (message.equals("Худи 50%" + "\uD83C\uDF70")) {
            total += hoodie;
        }
        if((message.equals("Болванка" + "\uD83D\uDC55"))){
            total += bolvanka;
        }
        if((message.equals("Прем ткань" + "\uD83D\uDE4F"))){
            total += premCloth;
        }
        if((message.equals("Люкс ткань" + "\uD83D\uDE4C"))){
            total += luxCloth;
        }
        if((message.equals("Две ткани" + "\uD83C\uDF53"))){
            total += twoForPriceCloth;
        }
        if((message.equals("100р футболка" + "\uD83D\uDCB3"))){
            total += shirtMoney;
        }

        return total;

    }
}
