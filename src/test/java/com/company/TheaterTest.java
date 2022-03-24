package com.company;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TheaterTest {
    private Theater theater;

    @BeforeEach
    public void init() {
        theater = new Theater();
    }

    @Test
    public void givenReservation_whenAssign_returnCorrectOutPut_test() {
        List<Integer> reservation = new ArrayList<Integer>(){{
            add(2);
            add(4);
            add(4);
            add(3);
            add(30);
            add(20);
        }};
        List<List<Seat>> actualAssignment = theater.assign(reservation);

        List<List<Seat>> expectedAssignment = new ArrayList<>();
        List<Seat> firstOrder = new ArrayList<Seat>();
        for (int i = 0; i < 2; i++) {
            firstOrder.add(new Seat(i, 9));
        }
        expectedAssignment.add(firstOrder);

        List<Seat> secondOrder = new ArrayList<Seat>();
        for (int i = 5; i < 9; i++) {
            secondOrder.add(new Seat(i, 9));
        }
        expectedAssignment.add(secondOrder);

        List<Seat> thirdOrder = new ArrayList<Seat>();
        for (int i = 12; i < 16; i++) {
            thirdOrder.add(new Seat(i, 9));
        }
        expectedAssignment.add(thirdOrder);


        List<Seat> fourthOrder = new ArrayList<Seat>();
        for (int i = 0; i < 3; i++) {
            fourthOrder.add(new Seat(i, 7));
        }
        expectedAssignment.add(fourthOrder);

        List<Seat> fifthOrder = new ArrayList<Seat>();
        expectedAssignment.add(fifthOrder);

        List<Seat> sixthOrder = new ArrayList<Seat>();
        for (int i = 0; i < 20; i++) {
            sixthOrder.add(new Seat(i, 5));
        }
        expectedAssignment.add(sixthOrder);

        for (int i = 0; i < expectedAssignment.size(); i++) {
            List<Seat> expectedOrder = expectedAssignment.get(i);
            List<Seat> actualOrder = actualAssignment.get(i);
            assertEquals(expectedOrder.size(), actualOrder.size());

            for (int j = 0; j < actualOrder.size(); j++) {
                Seat actualSeat = actualOrder.get(j);
                Seat expectedSeat = expectedOrder.get(j);

                assertEquals(actualSeat.getColumn(), expectedSeat.getColumn());
                assertEquals(actualSeat.getRow(), expectedSeat.getRow());
            }
        }
    }
}