package main.recources;

import org.w3c.dom.css.Counter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllForOne {

    public AllForOne() {}


    /**
     * Method list of all kind of functions
     * CSVReader -> reading .csv files and add them to a ArrayList records
     * CSVWriter -> write inside .csv files
     * CSVScanner -> reading a .csv file to check for different parameters
     */

    class CSV {
        public void CSVReader(File file) {
            if (file.isFile()) {
                List<List<String>> records = new ArrayList<>();
                try (BufferedReader csvReader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = csvReader.readLine()) != null) {
                        String[] values = line.split(" "); // or ";" or "." or ":"
                        records.add(Arrays.asList(values));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(records);
            }
        }

        public void CSVWriter(File file, String content) {
            List<Object[][]> requestsPending = new ArrayList<>();
            String[] splitCommand = content.split(" ");
            String first = splitCommand[0];
            String second = splitCommand[1];
            String third = splitCommand[2];
            // für mehr columns [i] erhöhen (Strings fortführen)
            if (file.isFile()) {
                List<List<String>> rows = Arrays.asList();
                try {
                    FileWriter csvWriter = new FileWriter(file);
                    Object[][] data = new Object[rows.size()][3];
                    for (int i = 0; i < rows.size(); i++) {
                        data[i][0] = rows.get(i).add(first);
                        data[i][1] = rows.get(i).add(second);
                        data[i][2] = rows.get(i).add(third);
                        requestsPending.add(data);
                    }

                    csvWriter.append(first);
                    csvWriter.append(",");
                    csvWriter.append(second);
                    csvWriter.append(",");
                    csvWriter.append(third);
                    csvWriter.append("\n");

                    for (List<String> rowData : rows) {
                        csvWriter.append(String.join(",", rowData));
                        csvWriter.append("\n");
                    }
                    csvWriter.flush();
                    csvWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public void CSVScanner(File file, String lookingFor) {
            if (file.isFile()) {
                List<List<String>> records = new ArrayList<>();
                try (BufferedReader csvReader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = csvReader.readLine()) != null) {
                        String[] values = line.split(" "); // or ";" or "." or ":"
                        records.add(Arrays.asList(values));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(records);
            }
        }
    }

    public void counter() {
        Counter counter = new Counter() {
            @Override
            public String getIdentifier() {
                return null;
            }

            @Override
            public String getListStyle() {
                return null;
            }

            @Override
            public String getSeparator() {
                return null;
            }
        };
    }
}
