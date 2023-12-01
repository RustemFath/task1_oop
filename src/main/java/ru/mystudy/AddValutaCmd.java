package ru.mystudy;

public class AddValutaCmd implements Command {
    private final Valuta valuta;

    public AddValutaCmd(Valuta valuta) {
        this.valuta = valuta;
    }

    @Override
    public void undo(Account account) {
        if (account != null) {
            account.deleteValuta(valuta);
        }
    }
}
