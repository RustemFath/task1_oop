package ru.mystudy;

public class ChangeNameCmd implements Command {

    private final String ownerName;

    public ChangeNameCmd(String ownerName) {
        this.ownerName = ownerName;
    }

    public void undo(Account account) {
        if (account != null) {
            account.setOwnerName(ownerName);
        }
    }
}
