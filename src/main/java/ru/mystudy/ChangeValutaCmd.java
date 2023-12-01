package ru.mystudy;

public class ChangeValutaCmd implements Command {
    private final Valuta valuta;
    private final int count;

    public ChangeValutaCmd(Valuta valuta, int count) {
        this.valuta = valuta;
        this.count = count;
    }

    @Override
    public void undo(Account account) {
        if (account != null) {
            account.addValuta(valuta, count);
        }
    }
}
