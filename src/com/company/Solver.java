package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solver {

    private static Solver solver;

    private Solver() {}

    public static Solver getSolver() {
        if (solver == null) {
            solver = new Solver();
        }
        return solver;
    }

    public String output(Theater theater, String inputPath) throws IOException {
        List<Integer> reservation = processInputFile(inputPath);
        List<List<Seat>> assignment = theater.assign(reservation);
        return processOutputFile(assignment);
    }

    private String processOutputFile(List<List<Seat>> assignment) throws IOException {
        File fout = new File("output.txt");
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (int i = 0; i < assignment.size(); i++) {
            List<Seat> order = assignment.get(i);
            StringBuilder result = new StringBuilder();
            result.append(String.format("R%03d ", (i + 1)));
            if (order.isEmpty()) {
                result.append("unassignable");
                bw.write(result.toString());
            }
            else {

                for (int j = 0; j < assignment.get(i).size(); j++) {
                    result.append(assignment.get(i).get(j).toString()).append(",");
                }
                bw.write(result.substring(0, result.length() - 1));
            }
            bw.newLine();

        }

        bw.close();

        return fout.getAbsolutePath();
    }

    private List<Integer> processInputFile(String inputPath) {
        List<Integer> reservation = new ArrayList<>();
        File file = new File(inputPath);

        try {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    String[] splited = line.split("\\s+");
                    reservation.add(Integer.parseInt(splited[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return reservation;
    }

}
