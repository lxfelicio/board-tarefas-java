package com.taskboard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Board {
    private int id;
    private String name;
    private List<Column> columns;
    
    public Board(String name) {
        this.name = name;
        this.columns = new ArrayList<>();
        initializeColumns();
    }
    
    private void initializeColumns() {
        // Ordem obrigatória: Inicial -> Pendente... -> Final -> Cancelamento
        columns.add(new Column("To Do", Column.ColumnType.INICIAL, 1));
        columns.add(new Column("Doing", Column.ColumnType.PENDENTE, 2));
        columns.add(new Column("Done", Column.ColumnType.FINAL, 3));
        columns.add(new Column("Cancelled", Column.ColumnType.CANCELAMENTO, 4));
    }
    
    // Getters e método para mover cards entre colunas
    public Column getColumnByType(Column.ColumnType type) {
        return columns.stream()
            .filter(c -> c.getType() == type)
            .findFirst()
            .orElse(null);
    }
}
