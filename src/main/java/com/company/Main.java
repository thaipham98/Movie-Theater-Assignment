package com.company;

import java.io.IOException;

public class Main {
    private static final Solver solver = Solver.getSolver();

    public static void main(String[] args) throws IOException {
        Theater theater = new Theater();
        String outputPath = solver.output(theater, "src/main/java/com/company/input.txt");
        System.out.println(outputPath);
    }
}
