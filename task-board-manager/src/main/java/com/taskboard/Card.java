package com.taskboard;

import java.time.LocalDateTime;

public class Card {
    private int id;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private boolean isBlocked;
    private String blockReason;
    
    // Construtor
    public Card(String title, String description) {
        this.title = title;
        this.description = description;
        this.creationDate = LocalDateTime.now();
        this.isBlocked = false;
        this.blockReason = null;
    }
    
    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    // ... (demais getters/setters)
    
    public void block(String reason) {
        this.isBlocked = true;
        this.blockReason = reason;
    }
    
    public void unblock(String reason) {
        this.isBlocked = false;
        this.blockReason = reason;
    }
      }
