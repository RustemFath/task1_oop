package ru.mystudy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AccountTest {

    @Test
    @DisplayName("testing success")
    public void success() {
        Account account = new Account("owner");
        Assertions.assertEquals("owner", account.getOwnerName(), "different names");

        account.addValuta(Valuta.EUR, 10);
        Assertions.assertEquals(10, account.getValutes().get(Valuta.EUR));

        account.addValuta(Valuta.EUR, 13);
        Assertions.assertEquals(13, account.getValutes().get(Valuta.EUR));

        account.getValutes().put(Valuta.EUR, -5);
        Assertions.assertEquals(13, account.getValutes().get(Valuta.EUR));
    }

    @Test
    @DisplayName("testing name is null and name is empty")
    public void errorOwnerName() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Account(null), "Name is null");
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Account(""), "Name is empty");
    }

    @Test
    @DisplayName("testing count of valuta is negative")
    public void errorNegativeCountOfValuta() {
        Account account = new Account("owner");
        Assertions.assertThrows(IllegalArgumentException.class, () -> account.addValuta(Valuta.RUB, -5));
    }

    @Test
    @DisplayName("testing undo")
    public void success_undo() {
        Account account = new Account("owner");
        account.addValuta(Valuta.RUB, 100);
        account.setOwnerName("Василий Иванов");
        account.addValuta(Valuta.RUB, 300);

        account.undo();
        Assertions.assertEquals(100, account.getValutes().get(Valuta.RUB));

        account.undo();
        Assertions.assertEquals("owner", account.getOwnerName());

        account.undo();
        Assertions.assertFalse(account.getValutes().containsKey(Valuta.RUB));

        Assertions.assertThrows(IllegalStateException.class, account::undo);
    }

    @Test
    @DisplayName("testing save")
    public void success_save() {
        Account account = new Account("owner");
        account.addValuta(Valuta.RUB, 100);
        AccountSave save1 = account.getSave();

        account.setOwnerName("Василий Иванов");
        account.addValuta(Valuta.RUB, 300);

        save1.valutes().put(Valuta.RUB, 350);

        account.setSave(save1);
        Assertions.assertEquals(100, account.getValutes().get(Valuta.RUB));
        Assertions.assertEquals("owner", account.getOwnerName());
    }
}
