package ru.mystudy;

public class Main {
    public static void main(String[] args) {
        Account account = new Account("owner");
        account.addValuta(Valuta.RUB, 100);
        AccountSave save = account.getSave();

        account.setOwnerName("Василий Иванов");
        account.addValuta(Valuta.RUB, 300);
        account.undo();

        account.setSave(save);
    }
}