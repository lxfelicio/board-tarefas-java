package com.taskboard;

import java.util.ArrayList;
import java.util.List;

public class Column {
    private int id;
    private String name;
    private ColumnType type;
    private int order;
    private List<Card> cards;
    
    public enum ColumnType { INICIAL, PENDENTE, FINAL, CANCELAMENTO }
    
    public Column(String name, ColumnType type, int order) {
        this.name = name;
        this.type = type;
        this.order = order;
        this.cards = new ArrayList<>();
    }
    
    // Getters e Setters
    public void addCard(Card card) {
        this.cards.add(card);
    }
    
    public void removeCard(Card card) {
        this.cards.remove(card);
    }
}
