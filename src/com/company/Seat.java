package com.company;

public class Seat {
    private int column;
    private int row;
    private boolean isOccupied;

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return toRowLetter(row) + (column + 1);
    }

    private String toRowLetter(int index) {
        return String.valueOf((char) (index + 65));
    }

    public int getRow() {
        return row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public Seat(int column, int row) {
        this.column = column;
        this.row = row;
        this.isOccupied = false;
    }
}
