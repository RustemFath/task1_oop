package ru.mystudy;

import java.util.*;

public class Account {
    private String ownerName;
    private Map<Valuta, Integer> valutes = new HashMap<>();
    private final Deque<Command> stack = new ArrayDeque<>();

    public Account(String ownerName) {
        setOwnerName(ownerName);
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        if (ownerName == null || ownerName.isEmpty()) {
            throw new IllegalArgumentException("OwnerName must be not null and no empty");
        }
        if (this.ownerName != null) {
            stack.addLast(new ChangeNameCmd(this.ownerName));
        }
        this.ownerName = ownerName;
    }

    public Map<Valuta, Integer> getValutes() {
        return new HashMap<>(valutes);
    }

    public void addValuta(Valuta valuta, int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Count must be greater than or equal to zero");
        }
        if (valutes.containsKey(valuta)) {
            stack.addLast(new ChangeValutaCmd(valuta, valutes.get(valuta)));
        } else {
            stack.addLast(new AddValutaCmd(valuta));
        }
        valutes.put(valuta, count);
    }

    public void deleteValuta(Valuta valuta) {
        if (valutes.containsKey(valuta)) {
            stack.addLast(new DeleteValutaCmd(valuta, valutes.get(valuta)));
            valutes.remove(valuta);
        }
    }

    public void undo() {
        if (canUndo()) {
            Command command = stack.pollLast();
            if (command != null) {
                command.undo(this);
                stack.removeLast();
            }
            return;
        }
        throw new IllegalStateException("Stack of changes is empty");
    }

    private boolean canUndo() {
        return !stack.isEmpty();
    }

    public AccountSave getSave() {
        return new AccountSave(ownerName, valutes);
    }

    public void setSave(AccountSave save) {
        if (save != null) {
            ownerName = save.ownerName();
            valutes = save.valutes();
            stack.clear();
            return;
        }
        throw new IllegalStateException("Save is null");
    }
}
