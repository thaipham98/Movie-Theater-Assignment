package com.company;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Row {
    private String name;
    private List<Seat> seats;
    private int numSlotsAvailable;

    public Row(int rowLetter) {
        this.name = toName(rowLetter);
        this.seats = IntStream.range(0, Theater.numSeatsPerRow)
                .mapToObj(i -> new Seat(i, rowLetter))
                .collect(Collectors.toList());
        this.numSlotsAvailable = Theater.numSeatsPerRow;
    }

    public Seat findNextAvailableSeat() {
        for (Seat seat : seats) {
            if (!seat.isOccupied()) return seat;
        }
        return null;
    }

    private String toName(int index) {
        return String.valueOf((char) (index + 65));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public int getNumSlotsAvailable() {
        return numSlotsAvailable;
    }

    public void setNumSlotsAvailable(int numSlotsAvailable) {
        this.numSlotsAvailable = numSlotsAvailable;
    }
}
