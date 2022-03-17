package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {
    private Theater theater;
    private static final Solver solver = Solver.getSolver();

    @BeforeEach
    public void init() {
        theater = new Theater();
    }

    @Test
    public void givenInputPath_whenGetOutputPath_returnCorrectOutput_test() throws IOException {
        String inputPath = "D:\\MovieTheaterSeating\\src\\com\\company\\input.txt";
        String outputPath = solver.output(theater, inputPath);

        List<String> actualResults = new ArrayList<>();
        File file = new File(outputPath);
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    actualResults.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(actualResults.get(0), "R001 J1,J2");
        assertEquals(actualResults.get(1), "R002 J6,J7,J8,J9");
        assertEquals(actualResults.get(2), "R003 J13,J14,J15,J16");
        assertEquals(actualResults.get(3), "R004 H1,H2,H3");
        assertEquals(actualResults.get(4), "R005 unassignable");
        assertEquals(actualResults.get(5), "R006 F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20");
    }
}