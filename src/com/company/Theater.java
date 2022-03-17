package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Theater {
    public final static int numRow = 10;
    public final static int numSeatsPerRow = 20;
    public final static int rowBuffer = 1;
    public final static int seatBuffer = 3;
    private final static List<Seat> EMPTY_LIST = new ArrayList<>();

    private List<Row> seats;

    public Theater() {
        this.seats = IntStream.range(0, numRow)
                .mapToObj(Row::new)
                .collect(Collectors.toList());
    }

    public List<List<Seat>> assign(List<Integer> reservation) {
        List<List<Seat>> assignment = new ArrayList<>();
        for (int i = 0; i < reservation.size(); i++) {
            int seatsRequired = reservation.get(i);
            List<Seat> occupyingSeats = occupySeats(seatsRequired);
            socialDistancing(occupyingSeats);
            assignment.add(occupyingSeats);
        }

        return assignment;
    }

    private List<Seat> occupySeats(int seatsRequired) {
        List<Seat> availableSeats = findAvailableSeats(seatsRequired);
        for (Seat seat : availableSeats) seat.setOccupied(true);
        return availableSeats;
    }

    private List<Seat> findAvailableSeats(int seatsRequired) {
        if (isUnassignable(seatsRequired)) {
            return EMPTY_LIST;
        }

        for (int i = numRow - 1; i >= 0; i--) {
            Row currentRow = seats.get(i);
            if (currentRow.getNumSlotsAvailable() >= seatsRequired) {
                Seat availableStartingSeat = currentRow.findNextAvailableSeat();
                Row row = seats.get(availableStartingSeat.getRow());
                int column = availableStartingSeat.getColumn();
                List<Seat> availableSeats = new ArrayList<>();

                for (int j = column; j < column + seatsRequired; j++) {
                    if (isValidSeat(i, j)) {
                        Seat currentSeat = row.getSeats().get(j);
                        currentSeat.setOccupied(true);
                        int slotsAvailable = currentRow.getNumSlotsAvailable() - 1;
                        currentRow.setNumSlotsAvailable(slotsAvailable);
                        availableSeats.add(currentSeat);
                    }
                }

                return availableSeats;
            }
        }

        return EMPTY_LIST;
    }

    private void socialDistancing(List<Seat> availableSeats) {
        if (availableSeats.isEmpty()) return;
        Seat start = availableSeats.get(0);
        Seat end = availableSeats.get(availableSeats.size() - 1);

        Row currentRow = seats.get(end.getRow());
        for (int i = 1; i <= seatBuffer; i++) {
            if (isValidSeat(end.getRow(), end.getColumn() + i)) {
                Seat seat = currentRow.getSeats().get(end.getColumn() + i);
                if (!seat.isOccupied()) {
                    currentRow.getSeats().get(end.getColumn() + i).setOccupied(true);
                    int slotsAvailable = currentRow.getNumSlotsAvailable() - 1;
                    currentRow.setNumSlotsAvailable(slotsAvailable);
                }
            }
        }

        int upperRowIdx = start.getRow() - rowBuffer;
        clear(start, end, upperRowIdx);

        int lowerRowIdx = start.getRow() + rowBuffer;
        clear(start, end, lowerRowIdx);
    }

    private void clear(Seat start, Seat end, int rowIndex) {
        for (int i = start.getColumn() - seatBuffer; i < end.getColumn() + seatBuffer + 1; i++) {
            if (isValidSeat(rowIndex, i)) {
                Row currentRow = seats.get(rowIndex);
                Seat seat = currentRow.getSeats().get(i);
                if (!seat.isOccupied()) {
                    seat.setOccupied(true);
                    int slotsAvailable = currentRow.getNumSlotsAvailable() - 1;
                    currentRow.setNumSlotsAvailable(slotsAvailable);
                }
            }
        }
    }

    private boolean isValidSeat(int row, int column) {
        return row < numRow && column < numSeatsPerRow && row >= 0 && column >= 0;
    }

    private boolean isUnassignable(int seatsRequired) {
        return seatsRequired > numSeatsPerRow || seatsRequired < 0;
    }
}
