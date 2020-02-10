public enum Button {
    MEGASAIL ("МР\uD83D\uDC8E"),
    HOODIE ("Худи 50%\uD83C\uDF70"),
    PREMCLOTH ("Прем ткань\uD83D\uDE4F"),
    LUXCLOTH ("Люкс ткань\uD83D\uDE4C"),
    TWOFORPRICECLOTH ("Две ткани\uD83C\uDF53"),
    SHIRTMONEY ("100р футболка\uD83D\uDCB3"),
    BOLVANKA ("Болванка\uD83D\uDC55"),
    BACK ("◀Назад"),
    STATISTICS ("статистика\uD83D\uDCB9"),
    UPS ("Ups\uD83D\uDCB5"),
    DELETEUPS ("Удалить апсейл\uD83C\uDF00"), //Удаление апсейлов
    DMEGASAIL ("МР \uD83D\uDC8E"),
    DHOODIE ("Худи 50% \uD83C\uDF70"),
    DPREMCLOTH ("Прем ткань \uD83D\uDE4F"),
    DLUXCLOTH ("Люкс ткань \uD83D\uDE4C"),
    DTWOFORPRICECLOTH ("Две ткани \uD83C\uDF53"),
    DSHIRTMONEY ("100р футболка \uD83D\uDCB3"),
    DBOLVANKA ("Болванка \uD83D\uDC55");



    private String title;
    Button(String title) {
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }


}
