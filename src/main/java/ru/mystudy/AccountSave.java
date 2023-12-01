package ru.mystudy;

import java.util.HashMap;
import java.util.Map;

public record AccountSave(String ownerName, Map<Valuta, Integer> valutes) {
    public AccountSave(String ownerName, Map<Valuta, Integer> valutes) {
        this.ownerName = ownerName;
        this.valutes = new HashMap<>(valutes);
    }

    @Override
    public Map<Valuta, Integer> valutes() {
        return new HashMap<>(valutes);
    }
}
